package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.facade.CafuFileDownloadFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


@RequestMapping("gbmultra/rbiBond/CafuFileDownload")
@RestController
public class CafuFileDownloadController {

    @Autowired
    CafuFileDownloadFacade cafuFileDownloadFacade;
    @PostMapping(path="/getCafuFileDownload")
    public ResponseEntity<ResponseDataDTO> getCafuFileDownload( @Validated @RequestBody CafuFileDownloadDTO cafuFileDownloadDTO)  {
        ResponseDataDTO responseDataDTO1=new ResponseDataDTO();
        ResponseDataDTO responseDataDTO=cafuFileDownloadFacade.getCafuFile(cafuFileDownloadDTO);
        CafuFileDownloadResponseDTO cafuFileDownloadResponseDTO=new CafuFileDownloadResponseDTO();
        if(responseDataDTO.getMessage().equalsIgnoreCase("SUCCESS")) {
            cafuFileDownloadResponseDTO=(CafuFileDownloadResponseDTO)responseDataDTO.getResult();
            byte[] fileData = generateCufuFile(cafuFileDownloadResponseDTO.getHeaderList().get(0), cafuFileDownloadResponseDTO.getDepositList(), cafuFileDownloadResponseDTO.getChequeList(), cafuFileDownloadResponseDTO.getFooterList().get(0));
            CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO=new CashFileUploadVerifySelectRequestDTO();
            cashFileUploadVerifySelectRequestDTO.setFile(fileData);
            cashFileUploadVerifySelectRequestDTO.setFileName(cafuFileDownloadResponseDTO.getHeaderList().get(0).getFileName());
            responseDataDTO1.setResult(cashFileUploadVerifySelectRequestDTO);
        }
        responseDataDTO1.setMessage(responseDataDTO.getMessage());
        responseDataDTO1.setStatusCode(responseDataDTO.getStatusCode());
        return APIResponseBuilder.buildResponseFromDto(responseDataDTO1);
    }

    @PostMapping(path="/getCafuFile")
    public ResponseEntity<ResponseDataDTO> getCafuFile(@RequestBody CafuFileDownloadDTO cafuFileDownloadDTO) {
        return APIResponseBuilder.buildResponseFromDto(cafuFileDownloadFacade.getCafuFile(cafuFileDownloadDTO));
    }

    public byte[] generateCufuFile(CafuFileHeaderRecordDTO header, List<CafuFileDepositRecordDTO> deposits, List<CafuFileChequeRecordDTO> cheques, CafuFileFooterRecordDTO footer) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos, StandardCharsets.UTF_8))) {
            // Write Header Record
            writer.write(formatHeaderRecord(header));
            writer.write(System.lineSeparator());
            // Write Deposit Records
            for (CafuFileDepositRecordDTO deposit : deposits) {
                writer.write(formatDepositRecord(deposit));
                writer.write(System.lineSeparator());
            }
            String myString = String.format("%-20s", "X7.15");
            writer.write(myString);
            writer.write(System.lineSeparator());
            // Write Cheque Records
            for (CafuFileChequeRecordDTO cheque : cheques) {
                writer.write(formatChequeRecord(cheque));
                writer.write(System.lineSeparator());
            }
            // Write Footer Record
            writer.write(formatFooterRecord(footer));
            writer.write(System.lineSeparator());
        }catch(Exception e){
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
    private String formatHeaderRecord(CafuFileHeaderRecordDTO record){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%1$-1s", record.getRecordIdentifier()));
        sb.append(String.format("%1$-12s", record.getFileName()!= null ? record.getFileName() : ""));
        sb.append(String.format("%1$-10s", record.getOriginatingBranch()!= null ? record.getOriginatingBranch() : ""));
        sb.append(String.format("%1$-8s",  record.getActivationDate()!= null ? record.getActivationDate() : ""));
        sb.append(String.format("%1$-12s", record.getTheirReferenceNmbr()!= null ? record.getTheirReferenceNmbr() : ""));
        sb.append(String.format("%1$-10s", record.getSourceOfUpload()!= null ? record.getSourceOfUpload() : ""));
        sb.append(String.format("%1$-10s", record.getSourceCode()!= null ? record.getSourceCode() : ""));
        sb.append(String.format("%1$-10s", record.getDispatchBankArrangement()!= null ? record.getDispatchBankArrangement() : ""));
        sb.append(String.format("%1$-1s", record.getPdcFlag()!= null ? record.getPdcFlag() : "N"));
        return sb.toString().trim();
    }

    private String formatDepositRecord(CafuFileDepositRecordDTO record){
        String result ="";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%1$-1s", record.getRecordIdentifier()));
        sb.append(String.format("%1$-10s", record.getClientCode()!= null ? record.getClientCode() : ""));
        sb.append(String.format("%1$-10s", record.getPickUpPoint()!= null ? record.getPickUpPoint() : ""));
        sb.append(String.format("%1$-18s", record.getDepositRemarks()!= null ? record.getDepositRemarks() : ""));
        String depositSlipNo = record.getDepositSlipNo() != null ? record.getDepositSlipNo() : "";
        if (depositSlipNo.length() > 10) {
            depositSlipNo = depositSlipNo.substring(0, 10);
        }
        sb.append(String.format("%1$-10s", depositSlipNo));  // Pad to the right if it's less than 10
        sb.append(String.format("%1$-8s", record.getDepositDate()!= null ? record.getDepositDate() : ""));
        sb.append(String.format("%1$-5s", record.getTotalNoOfInstruments()!= null ? record.getTotalNoOfInstruments() : ""));
        sb.append(String.format("%1$-15s", record.getTotalAmount()!= null ? record.getTotalAmount() : ""));
        sb.append(String.format("%1$-10s", (record.getClientArrg() != null ? record.getClientArrg().toUpperCase() : "").toUpperCase()));

        result = sb.toString().trim();
        if (result.length() < 87) {
            // Append empty spaces until the length becomes 87
            int spacesToAdd = 87 - result.length();
            StringBuilder sbWithSpaces = new StringBuilder(result);
            for (int i = 0; i < spacesToAdd; i++) {
                sbWithSpaces.append(" ");
            }
            result = sbWithSpaces.toString();
        }
        return result;
    }

    private String formatChequeRecord(CafuFileChequeRecordDTO record) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%1$-1s", record.getRecordIdentifier() != null ? record.getRecordIdentifier() : ""));
        sb.append(String.format("%1$-10s", record.getInstrumentNumber() != null ? record.getInstrumentNumber() : ""));
        sb.append(String.format("%1$-10s", record.getDraweeBankCode() != null ? record.getDraweeBankCode() : ""));
        sb.append(String.format("%1$-10s", record.getRoutingCode() != null ? record.getRoutingCode() : ""));
        sb.append(String.format("%1$-12s", record.getInstrumentAmount()!= null ? record.getInstrumentAmount() : ""));
        sb.append(String.format("%1$-8s", record.getInstrumentDate() != null ? record.getInstrumentDate() : ""));
        sb.append(String.format("%1$-40s", record.getDrawerName() != null ? record.getDrawerName() : ""));
        sb.append(String.format("%1$-10s", record.getClearingLocation() != null ? record.getClearingLocation() : ""));
        sb.append(String.format("%1$-40s",  record.getDrawerName() != null ? record.getDrawerName() : ""));
        sb.append(String.format("%1$-14s",   record.getOperatingInstructions() != null ? record.getOperatingInstructions() : ""));
        sb.append(String.format("%1$-14s", record.getCheckListNumber() != null ? record.getCheckListNumber() : ""));
        sb.append(String.format("%1$-1s", record.getCohCoi() != null ? record.getCohCoi() : ""));
        List<String> nonCumulativeValues = Arrays.asList("Non Cumulative", "Non-Cumulative", "N", "enrichment");
        String resultSchCode = (nonCumulativeValues.contains(record.getSchCode())) ? "N" : record.getSchCode();
        sb.append(String.format("%1$-1s", resultSchCode != null ? resultSchCode : ""));
        sb.append(String.format("%1$-14s", record.getExistingBlaNumber() != null ? record.getExistingBlaNumber() : ""));
        List<String> nonCumulativeValues1 = Arrays.asList("Individual", "I", "enrichment");
        String resultTypeOfApplicant = (nonCumulativeValues1.contains(record.getTypeOfApplicant())) ? "I" : (record.getTypeOfApplicant() != null && !record.getTypeOfApplicant().trim().isEmpty())? record.getTypeOfApplicant().trim().substring(0, 1).toUpperCase() : " ";
        sb.append(String.format("%1$-1s", resultTypeOfApplicant != null ? resultTypeOfApplicant : ""));
        sb.append(String.format("%1$-10s", record.getPanNo() != null ? record.getPanNo() : ""));
        sb.append(String.format("%1$-100s", record.getEmail1stApplicant() != null ? record.getEmail1stApplicant() : ""));
        sb.append(String.format("%1$-50s", record.getBankNameInterestPayment() != null ? record.getBankNameInterestPayment() : ""));
        sb.append(String.format("%1$-20s", record.getAccountNoInterestPayment() != null ? record.getAccountNoInterestPayment() : ""));
        sb.append(String.format("%1$-11s", record.getIfscCodeInterestPayment() != null ? record.getIfscCodeInterestPayment() : ""));
        sb.append(String.format("%1$-10s",
                (record.getMobileNo() != null && record.getMobileNo().length() == 12)
                        ? record.getMobileNo().substring(2)
                        : record.getMobileNo()));
        sb.append(String.format("%1$-7714s", record.getEnrichment() != null ? record.getEnrichment() : ""));
        sb.append(String.format("%1$-20s", record.getChksum() != null ? record.getChksum() : ""));
        return sb.toString().trim();
    }
    private String formatFooterRecord(CafuFileFooterRecordDTO record){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%1$-1s",  record.getRecordIdentifier()));
        sb.append(String.format("%1$-5s",  record.getTotalNumberOfdeposits() != null ? record.getTotalNumberOfdeposits() : ""));
        sb.append(String.format("%1$-15s", record.getTotalAmount() != null ? record.getTotalAmount() : ""));
        sb.append(String.format("%1$-20s", record.getTotalChkum() != null ? record.getTotalChkum() : ""));
        return sb.toString().trim();
    }
}
