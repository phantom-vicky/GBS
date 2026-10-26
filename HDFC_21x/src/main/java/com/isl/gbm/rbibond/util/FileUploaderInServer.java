package com.isl.gbm.rbibond.util;

import java.io.*;
import java.nio.file.*;
import java.util.List;

import com.isl.gbm.rbibond.dto.FileDownloadWrapperDTO;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileUploaderInServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploaderInServer.class);


    /**
     * Uploads a file to a specified server directory with appropriate checks.
     *
     * @param serverDir The target directory on the server where the file will be uploaded.
     * @param fileName  The name of the file to be created (without extension).
     * @param data      The content to be written to the file, represented as a list of strings.
     */
    public void uploadInServer(String serverDir, String fileName, List<String> data) {
        BufferedWriter writer = null;
        try {
            // Validate input parameters
            if (serverDir == null || serverDir.trim().isEmpty()) {
                LOGGER.error("Server directory path cannot be null or empty.");
                throw new IllegalArgumentException("Server directory path cannot be null or empty.");
            }

            if (fileName == null || fileName.trim().isEmpty()) {
                LOGGER.error("File name cannot be null or empty.");
                throw new IllegalArgumentException("File name cannot be null or empty.");
            }

            if (data == null || data.isEmpty()) {
                LOGGER.error("Data to write to the file cannot be null or empty.");
                throw new IllegalArgumentException("Data to write to the file cannot be null or empty.");
            }

            // Add .txt extension to the fileName
            String finalFileName = fileName + ".txt";

            // Specify the target file path on the server
            Path targetPath = Paths.get(serverDir, finalFileName);
            LOGGER.info("Target file path: {}", targetPath);

            // Ensure the target directory exists, or create it
            File targetDirectory = new File(serverDir);
            if (!targetDirectory.exists()) {
                LOGGER.info("Target directory does not exist. Creating directory...");
                if (!targetDirectory.mkdirs()) {
                    LOGGER.error("Failed to create the target directory: {}", serverDir);
                    throw new IOException("Failed to create the target directory: " + serverDir);
                }
            }

            // Check if the target directory has write permissions
            if (!targetDirectory.canWrite()) {
                LOGGER.error("Cannot write to the target directory: {}", serverDir);
                throw new IOException("Write permission denied for directory: " + serverDir);
            }

            // Create a file object for the target file
            File targetFile = targetPath.toFile();

            LOGGER.info("Target file path: {}", targetPath);
            // Write data to the file
            writer = new BufferedWriter(new FileWriter(targetFile));
            LOGGER.info("Writing data to file: {}", targetFile.getAbsolutePath());
            for (String line : data) {
                LOGGER.info("Writing line: {}", line); // Debugging line
                writer.write(line);
                writer.newLine();
            }

            // Ensure the data is flushed to disk
            writer.flush();  // Flush any remaining data in the buffer

            LOGGER.info("File successfully uploaded to: {}", targetFile.getAbsolutePath());

        } catch (IOException e) {
            LOGGER.error("Error occurred during file upload to server: {}", e.getMessage(), e);
        } catch (SecurityException e) {
            LOGGER.error("Security error occurred: {}", e.getMessage(), e);
        }finally {
            // Close the BufferedWriter if it was opened
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    LOGGER.warn("Failed to close BufferedWriter: {}", e.getMessage());
                }
            }
        }
    }
    /**
     * Downloads the file from a local server path and returns the content.
     * @param serverFilePath the base directory on the server
     * @param fileName the name of the file to be downloaded (without extension)
     * @return a FileDownloadWrapperDTO containing file content and message
     */
    public FileDownloadWrapperDTO downloadFile(String serverFilePath, String fileName) {
        FileDownloadWrapperDTO fileDownloadWrapperDTO = new FileDownloadWrapperDTO();

        // Validate the inputs
        if (serverFilePath == null || serverFilePath.isEmpty() || fileName == null || fileName.isEmpty()) {
            LOGGER.error("Invalid input parameters: serverFilePath and fileName cannot be null or empty.");
            fileDownloadWrapperDTO.setMessage("Invalid input parameters.");
            return fileDownloadWrapperDTO;
        }

        // Clean and secure the file path to avoid directory traversal attacks
        Path filePath = sanitizeFilePath(serverFilePath, fileName);
        if (filePath == null) {
            fileDownloadWrapperDTO.setMessage("Invalid file path.");
            return fileDownloadWrapperDTO;
        }

        // Check if the file exists
        if (!Files.exists(filePath)) {
            LOGGER.warn("File not found: {}", filePath);
            fileDownloadWrapperDTO.setMessage("File not found: " + filePath.toString());
            return fileDownloadWrapperDTO;
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Read the file from the server path (local file system)
            try (InputStream inputStream = new FileInputStream(filePath.toFile())) {
                IOUtils.copy(inputStream, outputStream);
                fileDownloadWrapperDTO.setFileContent(outputStream);
                fileDownloadWrapperDTO.setMessage("SUCCESS");
                LOGGER.info("File downloaded successfully: {}", filePath);
            }
        } catch (IOException e) {
            LOGGER.error("Error occurred while reading the file: {}", e.getMessage());
            fileDownloadWrapperDTO.setMessage("Error occurred while downloading the file: " + e.getMessage());
        }

        return fileDownloadWrapperDTO;
    }

    /**
     * Sanitizes the file path to prevent directory traversal attacks.
     * @param serverFilePath the base directory on the server
     * @param fileName the name of the file to be downloaded (without extension)
     * @return the sanitized Path object if valid, or null if invalid
     */
    private Path sanitizeFilePath(String serverFilePath, String fileName) {
        // Normalize the paths to avoid directory traversal attacks (e.g., "../file.txt" will not be allowed)
        Path basePath = Paths.get(serverFilePath).normalize();
        Path requestedPath = basePath.resolve(fileName + ".txt").normalize();

        // If the requested file path is outside the base directory, it's invalid
        if (!requestedPath.startsWith(basePath)) {
            LOGGER.error("Potential directory traversal attack detected. Invalid file path: {}", requestedPath);
            return null;
        }

        return requestedPath;
    }


}
