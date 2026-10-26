package com.isl.gbm.rbibond.externalservice.service.Impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.externalservice.config.RestTemplateConfigNoSSL;
import com.isl.gbm.rbibond.externalservice.dto.FileDownloadInfoDTO;
import com.isl.gbm.rbibond.externalservice.service.FileDownloadServiceHCP;
import com.isl.gbm.rbibond.externalservice.utility.ZipUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import java.io.*;


@Service
public class FileDownloadServiceHCPImpl implements FileDownloadServiceHCP {


    private static final Logger logger = LoggerFactory.getLogger(FileDownloadServiceHCPImpl.class);


    @Value("${external.hcp.api.url}")
    private String apiUrl;

    @Value("${external.hcp.api.auth}")
    private String authorization;

    @Value("${external.hcp.api.host}")
    private String host;

    @Value("${file.download.directory.hcp}")
    private String baseDirectory;

    @Value("${ssl.hcp.config}")
    private boolean sslHcpConfig;

    @Value("${profile.type}")
    private String activeType;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("webClientNoSSL")
    private WebClient webClientNoSSL;

    @Override
    public ResponseDataDTO downloadFile(String imageName, String hcpFilePath) {
        ResponseDataDTO responseDTO = new ResponseDataDTO();
        FileDownloadInfoDTO fileDownloadInfo = new FileDownloadInfoDTO();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        headers.set("Host", host);

        try {
            // ✅ Basic validation
            if (imageName == null || imageName.isBlank()) {
                throw new IllegalArgumentException("Image name (file name) is null or empty.");
            }
            if (baseDirectory == null || baseDirectory.isBlank()) {
                throw new IllegalArgumentException("File download directory is not configured in properties file.");
            }

            logger.info("Loaded HCP API URL from properties: {}", apiUrl);

            // ✅ Build full URL
            URI baseUri = new URI(apiUrl.endsWith("/") ? apiUrl : apiUrl + "/");
            String normalizedFilePath = hcpFilePath.startsWith("/") ? hcpFilePath.substring(1) : hcpFilePath;
            String normalizedImageName = imageName.startsWith("/") ? imageName.substring(1) : imageName;
            URI fullPath = baseUri.resolve(normalizedFilePath).resolve(normalizedImageName);
            String apiUrlHCP = fullPath.toString();

            logger.info("Constructed API URL: {}", apiUrlHCP);
            logger.info("Using {} client for file download.", sslHcpConfig ? "SSL-enabled RestTemplate" : "non-SSL WebClient");

            // ✅ Create folder for today's date
            Path folderPath = Paths.get(baseDirectory, LocalDate.now().toString());
            Files.createDirectories(folderPath);
            Path filePath = folderPath.resolve(imageName);

            // ✅ Download file
            if (sslHcpConfig) {
                // --- Using RestTemplate for SSL-enabled connection ---
                ResponseEntity<byte[]> response = restTemplate.exchange(
                        apiUrlHCP,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        byte[].class
                );

                HttpStatus statusCode = (HttpStatus)response.getStatusCode();
                logger.info("HTTP Response Status: {} ({})", statusCode.value(), statusCode.getReasonPhrase());

                if (!statusCode.is2xxSuccessful()) {
                    throw new RuntimeException("Failed to download file: " + statusCode);
                }

                byte[] responseBody = response.getBody();
                if (responseBody == null || responseBody.length == 0) {
                    throw new IOException("Downloaded file is empty or missing content.");
                }

                Files.write(filePath, responseBody);

            } else {
                // --- Using WebClient (NoSSL) for non-SSL connection ---
                try (OutputStream outputStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE)) {
                    webClientNoSSL.get()
                            .uri(apiUrlHCP)
                            .headers(httpHeaders -> httpHeaders.setAll(headers.toSingleValueMap()))
                            .retrieve()
                            .onStatus(HttpStatusCode::isError, clientResponse ->
                                    clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                                        logger.error("WebClient error response: {}", errorBody);
                                        return Mono.error(new RuntimeException("Failed to download file: " + errorBody));
                                    })
                            )
                            // ✅ Stream to avoid DataBufferLimitException
                            .bodyToFlux(DataBuffer.class)
                            .doOnNext(buffer -> {
                                try {
                                    byte[] bytes = new byte[buffer.readableByteCount()];
                                    buffer.read(bytes);
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    throw new UncheckedIOException(e);
                                } finally {
                                    DataBufferUtils.release(buffer);   // ✅ VERY IMPORTANT
                                }
                            })
                            .blockLast();
                }
            }

            // ✅ Unzip downloaded file
            List<String> fileNames = ZipUtils.unzipFiles(filePath.toString(), folderPath.toString());

            fileDownloadInfo.setFileName(imageName);
            fileDownloadInfo.setDirectoryPath(folderPath.toString());
            fileDownloadInfo.setFilePath(filePath.toString());
            fileDownloadInfo.setStatus("SUCCESS");
            fileDownloadInfo.setMessage("File downloaded successfully");
            fileDownloadInfo.setTimestamp(LocalDateTime.now());
            fileDownloadInfo.setFileNames(fileNames);

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("HTTP error occurred: {} - {}", e.getStatusCode(), e.getResponseBodyAsString(), e);
            fileDownloadInfo.setStatus("ERROR");
            fileDownloadInfo.setMessage("HTTP error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            fileDownloadInfo.setTimestamp(LocalDateTime.now());

        } catch (RestClientException e) {
            logger.error("RestClientException occurred: {}", e.getMessage(), e);
            fileDownloadInfo.setStatus("ERROR");
            fileDownloadInfo.setMessage("RestClientException: " + e.getMessage());
            fileDownloadInfo.setTimestamp(LocalDateTime.now());

        } catch (IOException e) {
            logger.error("File I/O error occurred: {}", e.getMessage(), e);
            fileDownloadInfo.setStatus("ERROR");
            fileDownloadInfo.setMessage("File I/O error: " + e.getMessage());
            fileDownloadInfo.setTimestamp(LocalDateTime.now());

        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            fileDownloadInfo.setStatus("ERROR");
            fileDownloadInfo.setMessage("Unexpected error: " + e.getMessage());
            fileDownloadInfo.setTimestamp(LocalDateTime.now());
        }

        responseDTO.setResult(fileDownloadInfo);
        return responseDTO;
    }


    /**
     * Deletes a file from the server based on the given file name.
     *
     * @param fileName The name of the file to delete
     * @return true if file was deleted successfully, false otherwise
     */
    public boolean deleteFileByName(String fileName) {
        // Ensure the base directory is valid
        if (baseDirectory == null || baseDirectory.trim().isEmpty()) {
            String errorMessage = "File download directory is not configured in application.properties.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        // Sanitize the file name to prevent directory traversal attacks
        String sanitizedFileName = sanitizeFileName(fileName);

        // Construct the full path of the file
        Path zipFilePath = Paths.get(baseDirectory, LocalDate.now().toString(),sanitizedFileName);

        // Construct the path of the extracted folder (assuming it was extracted with the same base name as the ZIP file)
        String folderName = zipFilePath.getFileName().toString().replaceAll("\\.ZIP$", "");
        Path extractedFolderPath = Paths.get(baseDirectory, LocalDate.now().toString(), folderName);

        try {
            // Check if the file exists
            if (Files.exists(zipFilePath)) {
                // Attempt to delete the file
                Files.delete(zipFilePath);
                logger.info("File deleted successfully: {}", zipFilePath.toString());

                // Delete the extracted folder if it exists
                if (Files.exists(extractedFolderPath)) {
                    deleteDirectoryRecursively(extractedFolderPath);
                    logger.info("Extracted folder deleted successfully: {}", extractedFolderPath.toString());
                } else {
                    logger.info("Extracted folder not found: {}", extractedFolderPath.toString());
                }

                return true;
            } else {
                logger.warn("File not found: {}", zipFilePath.toString());
                return false;
            }
        } catch (IOException e) {
            logger.error("Error deleting file: {}", zipFilePath.toString(), e);
            return false;
        }
    }

    // Recursively delete a directory and its contents
    private void deleteDirectoryRecursively(Path path) throws IOException {
        // Ensure the directory exists
        if (Files.exists(path)) {
            try (Stream<Path> paths = Files.walk(path)) {
                paths
                        .sorted(Comparator.reverseOrder()) // Reverse order to delete files before directories
                        .forEach(p -> {
                            try {
                                Files.delete(p);
                                logger.info("Deleted: {}", p);
                            } catch (IOException e) {
                                logger.info("Error deleting file: {} due to {}", p, e.getMessage());
                            }
                        });
            }
        }
    }

    /**
     * Sanitizes the file name to prevent security risks like directory traversal.
     *
     * @param fileName The file name to sanitize
     * @return A sanitized file name
     */
    private String sanitizeFileName(String fileName) {
        // Avoid directory traversal (e.g., "../")
        // Make sure file name does not contain any special characters that may lead to path manipulation
        if (fileName.contains("..") || fileName.contains("/") || fileName.contains("\\")) {
            logger.error("Potential directory traversal detected in file name: {}", fileName);
            throw new IllegalArgumentException("Invalid file name.");
        }

        // Optionally, further sanitize the file name (e.g., remove or replace spaces, special characters)
        return fileName.replaceAll("[^a-zA-Z0-9.-]", "_");
    }

    @Override
    public ResponseEntity<byte[]> fetchFile(String folderName, String fileName) {

        // Sanitize both folder and file names
        String sanitizedFolderName = sanitizeFileName(folderName);
        String sanitizedFileName = sanitizeFileName(fileName);
        try {
            // Build the file path by combining the folder name and file name
            Path folderPath = Paths.get(baseDirectory, LocalDate.now().toString(), sanitizedFolderName,sanitizedFolderName);  // Folder path
            Path filePath = folderPath.resolve(sanitizedFileName);  // Full path to the file

            if (Files.exists(filePath)) {
                byte[] fileContent = Files.readAllBytes(filePath);  // Read the file content

                // Set the response headers
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", Files.probeContentType(filePath));  // Set correct MIME type
                headers.add("Content-Disposition", "attachment; filename=" + fileName);  // For download prompt

                return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);  // Return file content with headers
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if file is not found
            }
        } catch (IOException e) {
            // Handle error reading the file
            System.err.println("Error fetching file: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Return 500 in case of error
        }
    }
    @Override
    public ResponseEntity<Object> getImageAsBase64(String folderName, String fileName) {
        String sanitizedFolderName = sanitizeFileName(folderName);
        String sanitizedFileName = sanitizeFileName(fileName);

        try {
            // Build the file path by combining the folder name and file name
            Path folderPath = Paths.get(baseDirectory, LocalDate.now().toString(),sanitizedFolderName);
            if ("TEST".equalsIgnoreCase(activeType)) {
                folderPath = folderPath.resolve(sanitizedFolderName);  // Append the folder name again if not "UAT"
            }
            Path filePath = folderPath.resolve(sanitizedFileName);

            // Check if the file exists
            if (Files.exists(filePath)) {
                // Check the file size (add additional check for empty file if needed)
                if (Files.size(filePath) == 0) {
                    logger.error("File is empty: {}", filePath);
                    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "The file is empty.");
                }

                // Log the file details for debugging
                logger.info("Attempting to read file: {}", filePath);

                // Call convertTiffToPng to handle TIFF conversion and return Base64-encoded image
                byte[] base64Image = convertTiffToPng(filePath.toFile());

                if (base64Image == null) {
                    logger.error("Failed to convert TIFF image to PNG for file: {}", filePath);
                    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to convert TIFF image to PNG.");
                }

                // Encode to Base64
                String base64EncodedImage = Base64.getEncoder().encodeToString(base64Image);
                return buildSuccessResponse(base64EncodedImage);

            } else {
                logger.error("File not found: {}", filePath);
                return buildErrorResponse(HttpStatus.NOT_FOUND, "Image file not found.");
            }
        } catch (IOException e) {
            logger.error("Error processing the image: {}", fileName, e);
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing the image: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> getAllImagesAsBase64(String folderName) {
        String sanitizedFolderName = sanitizeFileName(folderName);
        List<Map<String, String>> responseList = new ArrayList<>();

        try {

            Path folderPath;
            if ("UAT".equalsIgnoreCase(activeType) || "PROD".equalsIgnoreCase(activeType)) {
                folderPath = Paths.get(baseDirectory, LocalDate.now().toString(), sanitizedFolderName);
            } else {
                folderPath = Paths.get(baseDirectory, LocalDate.now().toString(), sanitizedFolderName, sanitizedFolderName);
            }
            // Check if the folder exists
            if (Files.exists(folderPath) && Files.isDirectory(folderPath)) {
                // Iterate through all files in the folder
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
                    for (Path filePath : stream) {
                        // Process only files (skip directories)
                        if (Files.isRegularFile(filePath)) {
                            logger.info("Processing file: {}", filePath);

                            // Check the file size (add additional check for empty file if needed)
                            if (Files.size(filePath) == 0) {
                                logger.error("File is empty: {}", filePath);
                                return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "The file is empty.");
                            }

                            // Call convertTiffToPng to handle TIFF conversion and return Base64-encoded image
                            byte[] base64Image = convertTiffToPng(filePath.toFile());

                            if (base64Image != null) {
                                String base64EncodedImage = Base64.getEncoder().encodeToString(base64Image);

                                // Prepare the response object for the file
                                Map<String, String> fileResponse = new HashMap<>();
                                fileResponse.put("image_name", filePath.getFileName().toString());
                                fileResponse.put("encrypted_value", base64EncodedImage);
                                responseList.add(fileResponse);
                            } else {
                                logger.error("Failed to convert TIFF to PNG for file: {}", filePath);
                            }
                        }
                    }//loop ends here
                }
                if(!(responseList.isEmpty())){
                    return buildSuccessResponseImages(responseList);
                }else {
                    return buildErrorResponse(HttpStatus.NOT_FOUND, "Empty Images.");
                }
            } else {
                logger.error("Folder not found: {}", folderPath);
                return buildErrorResponse(HttpStatus.NOT_FOUND, "Folder not found.");
            }
        } catch (IOException e) {
            logger.error("Error processing folder: {}", folderName, e);
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing the folder: " + e.getMessage());
        }
    }
    @Override
    public ResponseEntity<Object> getAllImagesAsBase64(String folderName,Map<String, String> map) {
        String sanitizedFolderName = sanitizeFileName(folderName);
        List<Map<String, String>> responseList = new ArrayList<>();

        try {
            // Build the folder path
            Path folderPath;
            if ("UAT".equalsIgnoreCase(activeType) || "PROD".equalsIgnoreCase(activeType)) {
                folderPath = Paths.get(baseDirectory, LocalDate.now().toString(), sanitizedFolderName);
            } else {
                folderPath = Paths.get(baseDirectory, LocalDate.now().toString(), sanitizedFolderName, sanitizedFolderName);
            }
            // Check if the folder exists
            if (Files.exists(folderPath) && Files.isDirectory(folderPath)) {
                // Iterate through all files in the folder
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
                    for (Path filePath : stream) {
                        // Process only files (skip directories)
                        if (Files.isRegularFile(filePath)) {
                            logger.info("Processing file: {}", filePath);

                            // Check the file size (add additional check for empty file if needed)
                            if (Files.size(filePath) == 0) {
                                logger.error("File is empty: {}", filePath);
                                return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "The file is empty.");
                            }

                            // Call convertTiffToPng to handle TIFF conversion and return Base64-encoded image
                            byte[] base64Image = convertTiffToPng(filePath.toFile());

                            if (base64Image != null) {
                                String base64EncodedImage = Base64.getEncoder().encodeToString(base64Image);

                                // Prepare the response object for the file
                                Map<String, String> fileResponse = new HashMap<>();
                                fileResponse.put("image_name", filePath.getFileName().toString());
                                fileResponse.put("encrypted_value", base64EncodedImage);
                                fileResponse.put("Indexing_Name",map.getOrDefault((filePath.getFileName().toString().toUpperCase(Locale.ROOT)),"Default"));
                                responseList.add(fileResponse);
                            } else {
                                logger.error("Failed to convert TIFF to PNG for file: {}", filePath);
                            }
                        }
                    }//loop ends here
                }
                if(!(responseList.isEmpty())){
                    return buildSuccessResponseImages(responseList);
                }else {
                    return buildErrorResponse(HttpStatus.NOT_FOUND, "Empty Images.");
                }
            } else {
                logger.error("Folder not found: {}", folderPath);
                return buildErrorResponse(HttpStatus.NOT_FOUND, "Folder not found.");
            }
        } catch (IOException e) {
            logger.error("Error processing folder: {}", folderName, e);
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing the folder: " + e.getMessage());
        }
    }

    // Method to convert TIFF to PNG and return as byte array
    private byte[] convertTiffToPng(File file) {
        try {
            // Open the TIFF file with ImageIO
            try (InputStream is = new FileInputStream(file)) {
                try (ImageInputStream imageInputStream = ImageIO.createImageInputStream(is)) {
                    Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInputStream);
                    if (!iterator.hasNext()) {
                        logger.error("Image file format not supported by ImageIO: {}", file.getAbsolutePath());
                        throw new UnsupportedImageFormatException("Image file format not supported: " + file.getAbsolutePath());
                    }

                    ImageReader reader = iterator.next();
                    reader.setInput(imageInputStream);

                    int numPages = reader.getNumImages(true);
                    String name = file.getName();
                    String parentFolder = file.getParentFile().getAbsolutePath();

                    // Processing each page of the TIFF (if it's a multi-page TIFF)
                    for (int pageIndex = 0; pageIndex < numPages; pageIndex++) {
                        BufferedImage tiffImage = reader.read(pageIndex);

                        // Convert to PNG and write to ByteArrayOutputStream
                        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
                        boolean writeSuccess = ImageIO.write(tiffImage, "PNG", pngOutputStream);
                        if (!writeSuccess) {
                            logger.error("Failed to convert TIFF to PNG for file: {}", file.getAbsolutePath());
                            return null;  // Conversion failed
                        }

                        // Return the PNG image as byte array
                        return pngOutputStream.toByteArray();
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error converting TIFF to PNG: {}", file.getAbsolutePath(), e);
        }
        return null;  // In case of failure
    }

    // Custom Exception for unsupported image format
    static class UnsupportedImageFormatException extends RuntimeException {
        public UnsupportedImageFormatException(String message) {
            super(message);
        }
    }
    private ResponseEntity<Object> buildSuccessResponse(String base64EncodedImage) {
        // Prepare the success response with base64 image data
        ResponseBody responseBody = new ResponseBody("success", "Image processed successfully.", base64EncodedImage);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    private ResponseEntity<Object> buildSuccessResponseImages(List<Map<String, String>> responseList) {
        // Prepare the success response with base64 image data
        ResponseBody responseBody = new ResponseBody("success", "Images processed successfully.", responseList);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String errorMessage) {
        // Prepare the error response with an appropriate message
        ResponseBody responseBody = new ResponseBody("error", errorMessage);
        return new ResponseEntity<>(responseBody, status);
    }

    public static class ResponseBody {
        private String status;
        private String message;
        private String image;
        private List<Map<String, String>> imagesList;

        // Constructor for success
        public ResponseBody(String status, String message, String image) {
            this.status = status;
            this.message = message;
            this.image = image;
        }

        public ResponseBody(String status, String message, List<Map<String, String>> imagesList) {
            this.status = status;
            this.message = message;
            this.imagesList = imagesList;
        }

        // Constructor for error
        public ResponseBody(String status, String message) {
            this.status = status;
            this.message = message;
        }

        // Getters and Setters
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<Map<String, String>> getImagesList() {
            return imagesList;
        }

        public void setImagesList(List<Map<String, String>> imagesList) {
            this.imagesList = imagesList;
        }
    }


}
