package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.StateMasterDTO;
import com.isl.gbm.cbs.integration.generator.CbsBulkUploadGenerator;
import com.isl.gbm.cbs.integration.manager.StateMasterManager;
import com.isl.gbm.cbs.integration.service.StateMasterService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dto.FileResponseDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class StateMasterServiceImpl implements StateMasterService {

    @Autowired
    StateMasterManager stateMasterManager;

    @Override
    public ResponseDataDTO save(final List<StateMasterDTO> stateMasterDTOList) {
        return this.stateMasterManager.save(stateMasterDTOList);

    }

    @Override
    public ResponseDataDTO getInquiryDetails(StateMasterDTO stateMasterDTO) {
        return this.stateMasterManager.getInquiryDetails(stateMasterDTO);

    }

    @Override
    public ResponseDataDTO getVerifyDetails(StateMasterDTO stateMasterDTO) {
        return this.stateMasterManager.getVerifyDetails(stateMasterDTO);

    }

    @Override
    public ResponseDataDTO verifySubmit() {
        return this.stateMasterManager.verifySubmit();

    }

    @Override
    public ResponseDataDTO deleteSubmit(StateMasterDTO stateMasterDTO) {
        return this.stateMasterManager.deleteSubmit(stateMasterDTO);

    }

    @Override
    public ResponseDataDTO dataExistsCheck() {
        return this.stateMasterManager.dataExistsCheck();

    }

    @Override
    public ResponseEntity<ResponseDataDTO> downloadPincode() throws IOException, InvalidFormatException {
        FileResponseDto fileResponseDto = new FileResponseDto();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(excelFile()));
        fileResponseDto.setFileName("STATE_CITY_PINCODE_MASTER.xls");
        responseDataDTO.setResult(fileResponseDto);
        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        return APIResponseBuilder.buildResponseFromDto(responseDataDTO);
    }

    public static byte[] excelFile() throws IOException, InvalidFormatException {
        String headers = "STATE,CITY,PIN_CODE,POST_OFFICE_NAME";
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
