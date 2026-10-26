package com.isl.gbm.rbibond.externalservice.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.externalservice.dto.FileDownloadInfoDTO;
import com.isl.gbm.rbibond.externalservice.dto.FtsResponseDataDTO;
import com.isl.gbm.rbibond.externalservice.service.FileDownloadServiceHCP;
import com.isl.gbm.rbibond.externalservice.service.FtsService;
import com.isl.gbm.rbibond.externalservice.utility.IntegrationCommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/external-service")
public class FtsHcpIntegratedServiceController {

    private final FtsService ftsService;
    private final FileDownloadServiceHCP fileDownloadService;

    private static final Logger log = LoggerFactory.getLogger(FtsHcpIntegratedServiceController.class);

    public FtsHcpIntegratedServiceController(FileDownloadServiceHCP fileDownloadService,FtsService ftsService) {
        this.fileDownloadService = fileDownloadService;
        this.ftsService = ftsService;
    }

    @PostMapping("/fts-hcp-integrated-service")
    public ResponseEntity<Object> getFtsHcpIntegratedServiceResponse(@RequestParam String applicationNumber) {
        try {
            // Fetch file details based on the application number
            ResponseDataDTO responseData = ftsService.getFileDetails(applicationNumber);

            // Validate if responseData or result is null
            if (responseData == null
                    || responseData.getResult() == null
                    || !IntegrationCommonConstants.FTS_RES_SUCCESS.equalsIgnoreCase(responseData.getMessage())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file details found for the provided application number.");
            }

            // Extract FtsResponseDataDTO from response data
            FtsResponseDataDTO ftsResponseDataDTO = (FtsResponseDataDTO) responseData.getResult();

            // Check if ListData is empty or null
            if (ftsResponseDataDTO.getResponseString().getListData().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No files found in the response.");
            }

            // Fetch indexing names for images
            Map<String, String> indexingMap = ftsService.fetchIndexingNamesByImageName(ftsResponseDataDTO);

            // Download the file based on the file name
            String fileName = ftsResponseDataDTO.getResponseString().getListData().get(0).getFileName();
            String filePath = ftsResponseDataDTO.getResponseString().getListData().get(0).getFilePath();
            ResponseDataDTO downloadResp = fileDownloadService.downloadFile(fileName,filePath);

            // Extract download status and validate
            FileDownloadInfoDTO fileDownloadInfo = (FileDownloadInfoDTO) downloadResp.getResult();
            if (!IntegrationCommonConstants.FTS_RES_SUCCESS.equalsIgnoreCase(fileDownloadInfo.getStatus())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File download failed. Please try again later.");
            }

            // Retrieve images as Base64 and return the response
            Object base64Images = fileDownloadService.getAllImagesAsBase64(removeZipExtension(fileName), indexingMap);

            return ResponseEntity.ok(base64Images);

        } catch (Exception e) {
            // Log the exception and return a generic error message
            log.error("Error occurred while processing the request: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request.");
        }
    }

    private String removeZipExtension(String fileName) {
        // Check if the file name ends with ".ZIP" and remove the extension
        if (fileName != null && fileName.toUpperCase().endsWith(IntegrationCommonConstants.ZIP)) {
            return fileName.substring(0, fileName.length() - 4);  // Remove last 4 characters (".ZIP")
        }
        return fileName;  // Return the original file name if it doesn't end with ".ZIP"
    }
}
