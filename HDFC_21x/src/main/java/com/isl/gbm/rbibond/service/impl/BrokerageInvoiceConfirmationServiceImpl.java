package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.BrokerageInvoiceConfirmationBO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationInquiryDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationVerifyDTO;
import com.isl.gbm.rbibond.dto.FileResponseDto;
import com.isl.gbm.rbibond.service.BrokerageInvoiceConfirmationService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;


@Service
public class BrokerageInvoiceConfirmationServiceImpl implements BrokerageInvoiceConfirmationService {

    @Autowired
    private BrokerageInvoiceConfirmationBO brokerageInvoiceConfirmationBO;
    @Override
    public ResponseDataDTO getAgencyCode(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO){
        return brokerageInvoiceConfirmationBO.getAgencyCode(brokerageInvoiceConfirmationDTO);
    }

    @Override
    public ResponseDataDTO getInvoiceConfirmationDetails(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO){
        return brokerageInvoiceConfirmationBO.getInvoiceConfirmationDetails(brokerageInvoiceConfirmationDTO);
    }

    @Override
    public ResponseDataDTO invoiceVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO){
        return brokerageInvoiceConfirmationBO.invoiceVerify(brokerageInvoiceConfirmationVerifyDTO);
    }

    @Override
    public ResponseDataDTO getInvoiceConfirmationVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO){
        return brokerageInvoiceConfirmationBO.getInvoiceConfirmationVerify(brokerageInvoiceConfirmationVerifyDTO);
    }

    @Override
    public ResponseDataDTO getInvoiceConfirmationInquiry(BrokerageInvoiceConfirmationInquiryDTO brokerageInvoiceConfirmationInquiryDTO){
        return brokerageInvoiceConfirmationBO.getInvoiceConfirmationInquiry(brokerageInvoiceConfirmationInquiryDTO);
    }

    @Override
    public ResponseDataDTO submitDeleteForm(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO){
        return brokerageInvoiceConfirmationBO.submitDeleteForm(brokerageInvoiceConfirmationDTO);
    }

    @Override
    public ResponseDataDTO getDeleteSubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO){
        return brokerageInvoiceConfirmationBO.getDeleteSubmit(brokerageInvoiceConfirmationVerifyDTO);
    }

    @Override
    public ResponseDataDTO verifySubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO){
        return brokerageInvoiceConfirmationBO.verifySubmit(brokerageInvoiceConfirmationVerifyDTO);
    }

    @Override
    public ResponseDataDTO upload(MultipartFile file, String brokerageInvoiceConfirmationVerifyDTO, BrokerageInvoiceConfirmationVerifyDTO dto){
        return brokerageInvoiceConfirmationBO.upload(file,brokerageInvoiceConfirmationVerifyDTO,dto);
    }

    @Override
    public ResponseEntity<ResponseDataDTO> paymentConfirmation() throws IOException, InvalidFormatException {
        FileResponseDto fileResponseDto = new FileResponseDto();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(excelFile()));
        fileResponseDto.setFileName("PAYMENT_CONFIRMATION.xls");
        responseDataDTO.setResult(fileResponseDto);
        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        return APIResponseBuilder.buildResponseFromDto(responseDataDTO);
    }

    public static byte[] excelFile() throws IOException, InvalidFormatException {
        String headers = "VBMS Reference Number,Payment Ref,Paid On,Invoice Receive Date,Invoice Raised on,VBMS Maker,VBMS Checker";
        return generateExcelSheet(headers);
    }
    public static byte[] generateExcelSheet(String headers) throws InvalidFormatException, IOException {
        String[] headersList = headers.split(",");

        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            Sheet sheet = wb.createSheet();

            Row row = sheet.createRow(0);
            CellStyle headerCellStyle = wb.createCellStyle();
            CellStyle headerCellStyle1 = wb.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerCellStyle1.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Creating header

            for(int i = 0; i < headersList.length; i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(headersList[i]);
                cell.setCellStyle(headerCellStyle);
                if(cell.getStringCellValue().equalsIgnoreCase("Text 1") || cell.getStringCellValue().equalsIgnoreCase("Text 2") || cell.getStringCellValue().equalsIgnoreCase("Text 3")){
                    cell.setCellStyle(headerCellStyle1);
                }
                sheet.autoSizeColumn(i);
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            wb.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
