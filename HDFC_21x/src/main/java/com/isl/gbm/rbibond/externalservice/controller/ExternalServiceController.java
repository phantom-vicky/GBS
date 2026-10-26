package com.isl.gbm.rbibond.externalservice.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.externalservice.dto.AccountValidationRequestDTO;
import com.isl.gbm.rbibond.externalservice.dto.FetchCustomerAccountAmlFatcaDetailsRequestDTO;
import com.isl.gbm.rbibond.externalservice.dto.FileDownloadInfoDTO;
import com.isl.gbm.rbibond.externalservice.dto.SessionContextDTO;
import com.isl.gbm.rbibond.externalservice.service.ExternalService;
import com.isl.gbm.rbibond.externalservice.service.FileDownloadServiceHCP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/external-service")
public class ExternalServiceController {

    // Assuming externalService is injected here
    private final ExternalService externalService;

    private final FileDownloadServiceHCP fileDownloadService;

    public ExternalServiceController(ExternalService externalService,FileDownloadServiceHCP fileDownloadService) {
        this.externalService = externalService;
        this.fileDownloadService = fileDownloadService;
    }

    // Adding validation annotations for accountNumber
    @PostMapping("/api/account-validation")
    public ResponseEntity<ResponseDataDTO> add(@RequestParam @NotNull @Pattern(regexp = "\\d{9,15}", message = "Account number must be between 9 and 15 digits") String accountNumber) {
        ResponseDataDTO dto = new ResponseDataDTO();

        // Null or empty validation
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            dto.setMessage("Account number must not be empty or null");
            return ResponseEntity.badRequest().body(dto);
        }

        // Account number format validation (assuming numeric, between 10 and 15 digits)
        if (!accountNumber.matches("\\d{9,15}")) {
            dto.setMessage("Invalid account number format. Must be 9 to 15 digits long");
            return ResponseEntity.badRequest().body(dto);
        }

        // Proceed with service call
        try {
            AccountValidationRequestDTO request = new AccountValidationRequestDTO();
            FetchCustomerAccountAmlFatcaDetailsRequestDTO incomingData = new FetchCustomerAccountAmlFatcaDetailsRequestDTO();
            SessionContextDTO session = new SessionContextDTO();
            incomingData.setAccountNumber(accountNumber);
            request.setSessionContext(session);
            request.setFetchCustomerAccountAmlFatcaDetailsRequestDTO(incomingData);
            // Call the external service to validate the account
            ResponseDataDTO responseData = externalService.accountValidationFTS(request);

            // Return a successful response
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            // Handle other unexpected errors
            dto.setMessage("Internal server error while processing account validation");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
        }
    }

    @PostMapping("/container/image/download")
    public ResponseEntity<FileDownloadInfoDTO> downloadFile(@RequestParam @NotNull String imageName,@RequestParam @NotNull String filePath) {
        // Call the service method to download the file
        ResponseDataDTO data = fileDownloadService.downloadFile(imageName,filePath);
        FileDownloadInfoDTO fileDownloadInfoDTO= (FileDownloadInfoDTO) data.getResult();
        // Return the DTO as a response entity with status OK
        return ResponseEntity.ok(fileDownloadInfoDTO);
    }

    @DeleteMapping("/hcp-files/delete")
    public boolean deleteFileInDirectory(@RequestParam @NotNull String fileName) {
        try {
            return fileDownloadService.deleteFileByName(fileName);
        } catch (Exception e) {
            System.err.println("Error deleting file: " + e.getMessage());
            return false;
        }
    }

    @GetMapping("/hcp-files/fetch")
    public ResponseEntity<Object> fetchFile(@RequestParam String folderName) {
        return fileDownloadService.getAllImagesAsBase64(folderName);
    }
}
