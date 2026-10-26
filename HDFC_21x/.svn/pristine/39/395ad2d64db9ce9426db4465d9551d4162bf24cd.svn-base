package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.RbiBondPanStatusUpdateForTaxBO;
import com.isl.gbm.rbibond.dao.RbiBondPanStatusUpdateForTaxDao;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.util.IncomeTaxPanValidationUtlity;
import com.isl.gbm.user.isac.common.CommonConstant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@Component
public class RbiBondPanStatusUpdateForTaxBOImpl implements RbiBondPanStatusUpdateForTaxBO {
    private static final Logger LOGGER = LoggerFactory.getLogger(RbiBondPanStatusUpdateForTaxBOImpl.class);
    private final RbiBondPanStatusUpdateForTaxDao rbiBondPanStatusUpdateForTaxDao;
    private final IncomeTaxPanValidationUtlity utility;
    @Autowired
    private Validator validator;
    @Autowired
    public RbiBondPanStatusUpdateForTaxBOImpl(RbiBondPanStatusUpdateForTaxDao rbiBondPanStatusUpdateForTaxDao,IncomeTaxPanValidationUtlity utility){
        this.rbiBondPanStatusUpdateForTaxDao=rbiBondPanStatusUpdateForTaxDao;
        this.utility = utility;
    }

    @Override
    public ResponseDataDTO upload(MultipartFile multipartFile, PanStatusUploadForTdsRequestAndResponseDTO dto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            if (null != multipartFile) {
                String filename = multipartFile.getOriginalFilename();
                byte[] data = multipartFile.getBytes();
                if (null != dto && null != dto.getFileType() && "I001".equalsIgnoreCase(dto.getFileType())) {
                    responseDataDTO = parseFile(multipartFile);
                    if(null!=responseDataDTO&&null!=responseDataDTO.getMessage()
                            &&(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.HEADER_VALIDATION_FAILURE) ||
                            String.valueOf(responseDataDTO.getResult()).equalsIgnoreCase("Validation Failed"))){
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }else{
                        if(null!=responseDataDTO&&null!=responseDataDTO.getResult()) {
                            dto.setList((responseDataDTO != null && responseDataDTO.getResult() instanceof List<?>)
                                    ? (List<PanStatusUploadForTdsFileUploadDTO>) responseDataDTO.getResult(): null);
                            dto.setFileName(filename);
                            dto.setFile(data);
                        }
                    }
                } else if (null != dto && null != dto.getFileType() && "R001".equalsIgnoreCase(dto.getFileType())) {
                    responseDataDTO = parseFile(multipartFile);
                    if(null!=responseDataDTO&&null!=responseDataDTO.getMessage()
                            &&(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.HEADER_VALIDATION_FAILURE) ||
                            String.valueOf(responseDataDTO.getResult()).equalsIgnoreCase("Validation Failed"))){
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }else {
                        if(null!=responseDataDTO&&null!=responseDataDTO.getResult()) {
                            dto.setList((responseDataDTO != null && responseDataDTO.getResult() instanceof List<?>)
                                    ? (List<PanStatusUploadForTdsFileUploadDTO>) responseDataDTO.getResult(): null);
                            dto.setFileName(filename);
                            dto.setFile(data);
                        }
                    }
                } else {
                    responseDataDTO.setMessage("File Name Wrong");
                    responseDataDTO.setStatusCode("0");
                    return responseDataDTO;
                }
            } else {
                responseDataDTO.setMessage("File Data Empty");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        } catch (Exception e) {
            LOGGER.info("File not in expected format. Validation errors: {}", e.toString(), e);
            responseDataDTO.setMessage("File Validation Error ");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondPanStatusUpdateForTaxDao.upload(dto);
    }

    public ResponseDataDTO parseFile(MultipartFile file) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<PanStatusUploadForTdsFileUploadDTO> panDetailsList = new ArrayList<>();
        try (InputStream inp = file.getInputStream(); XSSFWorkbook workbook=new XSSFWorkbook(inp)) {
            String[] expectedHeaders = {
                    "PAN_NO", "NAME", "DOB", "OLD_PAN_STATUS", "NEW_PAN_STATUS",
                    "OLD_AADHAR_SEEDING_STATUS", "NEW_AADHAR_SEEDING_STATUS"
            };
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final Sheet sheet = workbook.getSheetAt(sheetIndex);
                if (!validateHeaders(sheet, expectedHeaders)) {
                    LOGGER.error("Header validation failed for sheet: {}",sheet.getSheetName());
                    responseDataDTO.setMessage("Header validation failed");
                    return responseDataDTO;
                }
                List<PanStatusUploadForTdsFileUploadDTO> entityList = new ArrayList<>();
                DataFormatter formatter = new DataFormatter();
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue;
                    PanStatusUploadForTdsFileUploadDTO dto = new PanStatusUploadForTdsFileUploadDTO();
                    dto.setOldPan(formatter.formatCellValue(row.getCell(0)));
                    dto.setOldName(formatter.formatCellValue(row.getCell(1)));
                    dto.setOldDob(formatter.formatCellValue(row.getCell(2)));
                    dto.setOldPanStatus(formatter.formatCellValue(row.getCell(3)));
                    dto.setNewPanStatus(formatter.formatCellValue(row.getCell(4)));
                    dto.setOldAadharSeedingStatus(formatter.formatCellValue(row.getCell(5)));
                    dto.setNewAadharSeedingStatus(formatter.formatCellValue(row.getCell(6)));
                    entityList.add(dto);
                }
                List<PanStatusUploadForTdsFileUploadDTO> nonEmptyRows = entityList.stream()
                        .filter(row -> !isRowEmpty(row))
                        .collect(Collectors.toList());
                if (nonEmptyRows == null || nonEmptyRows.isEmpty()) {
                    LOGGER.info("No data available for the specified file");
                    responseDataDTO.setMessage("No data available for the specified file");
                    responseDataDTO.setResult("Validation Failed");
                    return responseDataDTO;
                }
                String errorMessage = utility.processDataTds(nonEmptyRows);
                if(!errorMessage.equals("NR")){
                    LOGGER.info("validation failed: {}",errorMessage);
                    responseDataDTO.setMessage(errorMessage);
                    responseDataDTO.setResult("Validation Failed");
                    return responseDataDTO;
                }
                if (CollectionUtils.isNotEmpty(nonEmptyRows))
                    panDetailsList.addAll(nonEmptyRows);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        }
        responseDataDTO.setResult(this.buildResultList(panDetailsList));
        return responseDataDTO;
    }
    private byte[] panStatusUpdateForTdsRedemptionExcel(ResponseDataDTO responseDataDTO) {
        List<PanStatusUploadForTdsRequestAndResponseDTO> list = (List<PanStatusUploadForTdsRequestAndResponseDTO>) responseDataDTO.getResult();
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();
            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(0);
            String[] headers = {
                    "PAN_NO", "NAME", "DOB", "OLD_PAN_STATUS", "NEW_PAN_STATUS",
                    "OLD_AADHAR_SEEDING_STATUS", "NEW_AADHAR_SEEDING_STATUS"
            };
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);
            // Track maximum column widths
            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }
            list = (List<PanStatusUploadForTdsRequestAndResponseDTO>) responseDataDTO.getResult();
            int rowNumber = 1;
            if (!list.isEmpty()) {
                for (PanStatusUploadForTdsRequestAndResponseDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = {
                            data.getOldPan() != null ? data.getOldPan() : "",
                            data.getOldName() != null ? data.getOldName() : "",
                            data.getOldDob() != null ? data.getOldDob() : "",
                            data.getOldPanStatus() != null ? data.getOldPanStatus() : "",
                            data.getNewPanStatus() != null ? data.getNewPanStatus() : "",
                            data.getOldAadharSeedingStatus() != null ? data.getOldAadharSeedingStatus() : "",
                            data.getNewAadharSeedingStatus() != null ? data.getNewAadharSeedingStatus() : "",
                    };
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            }
            else
            {
                LOGGER.info("PAN STATUS UPDATE OBJECT HAVING EMPTY DATA ");
            }
            LOGGER.info("PAN STATUS UPDATE DATA GENERATION COMPLETED...");
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Exception while writing the Excel file: {}",e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }
    private boolean validateHeaders(Sheet sheet, String[] expectedHeaders) {
        Row headerRow = sheet.getRow(0); // Assuming the first row contains headers
        if (headerRow == null) {
            return false; // No header row found
        }
        for (int i = 0; i < expectedHeaders.length; i++) {
            Cell cell = headerRow.getCell(i);
            String headerValue = cell != null ? cell.getStringCellValue().trim() : "";
            if (!headerValue.equalsIgnoreCase(expectedHeaders[i])) {
                return false; // Header does not match
            }
        }
        return true; // All headers are valid
    }

    public List<PanStatusUploadForTdsFileUploadDTO> buildResultList(
            final List<PanStatusUploadForTdsFileUploadDTO> incomeTaxPanStatusDTO) throws DuplicateRecordException {
        final Set<PanStatusUploadForTdsFileUploadDTO> rowSet = new HashSet<>(incomeTaxPanStatusDTO.size());
        final List<PanStatusUploadForTdsFileUploadDTO> dtoList = new ArrayList<>(incomeTaxPanStatusDTO.size());
        for (final PanStatusUploadForTdsFileUploadDTO row : incomeTaxPanStatusDTO) {
            if (!rowSet.add(row)) {
                throw new DuplicateRecordException("Duplicate Records Found In The Excel File");
            }
            final Errors errors = new BeanPropertyBindingResult(row, row.getClass().getName());
            this.validator.validate(row, errors);
            if (errors.hasErrors()) {
                throw new InvalidDataException(errors.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList())
                        .toString());
            }
            dtoList.add(this.mapRowToPanDetails(row));
        }
        return dtoList;
    }
    private boolean isRowEmpty(PanStatusUploadForTdsFileUploadDTO row) {
        // Assuming the fields in the row are of type String; you can adjust the checks as needed.
        return (row.getOldPan() == null || row.getOldPan().trim().isEmpty()) &&
                (row.getOldName() == null || row.getOldName().trim().isEmpty()) &&
                (row.getOldDob() == null || row.getOldDob().trim().isEmpty()) &&
                (row.getOldPanStatus() == null || row.getOldPanStatus().trim().isEmpty()) &&
                (row.getNewPanStatus() == null || row.getNewPanStatus().trim().isEmpty()) &&
                (row.getOldAadharSeedingStatus() == null || row.getOldAadharSeedingStatus().trim().isEmpty()) &&
                (row.getNewAadharSeedingStatus() == null || row.getNewAadharSeedingStatus().trim().isEmpty());
    }

    private PanStatusUploadForTdsFileUploadDTO mapRowToPanDetails(PanStatusUploadForTdsFileUploadDTO row) {
        PanStatusUploadForTdsFileUploadDTO panDetails = new PanStatusUploadForTdsFileUploadDTO();
        panDetails.setOldPan(row.getOldPan());
        panDetails.setOldName(row.getOldName());
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy");
        if (null != row.getOldDob()) {
            String formattedDate = null;
            if (isNumeric(row.getOldDob())) {
                double serialNumber = Double.parseDouble(row.getOldDob());
                if (serialNumber > 0) {
                    Date javaDate = DateUtil.getJavaDate(serialNumber);
                    formattedDate = inputFormat.format(javaDate);
                }
            } else {
                try {
                    Date date = inputFormat.parse(row.getOldDob());
                    formattedDate = inputFormat.format(date);
                } catch (ParseException e) {
                    LOGGER.info("{}: {}", CommonConstants.INVALID_DATE_FORMAT, row.getOldDob());
                }
            }
            panDetails.setOldDob(formattedDate);
            panDetails.setOldPanStatus(row.getOldPanStatus());
            panDetails.setNewPanStatus(row.getNewPanStatus());
            panDetails.setOldAadharSeedingStatus(row.getOldAadharSeedingStatus());
            panDetails.setNewAadharSeedingStatus(row.getNewAadharSeedingStatus());
        }
        return panDetails;
    }

    @Override
    public ResponseDataDTO getUploadModuleName(PanStatusUploadForTdsRequestAndResponseDTO dto) {
        return rbiBondPanStatusUpdateForTaxDao.upload(dto);
    }
    @Override
    public ResponseDataDTO download(PanStatusUploadForTdsRequestAndResponseDTO dto) {
        ResponseDataDTO responseDataDTO=rbiBondPanStatusUpdateForTaxDao.download(dto);
        CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO=new CashFileUploadVerifySelectRequestDTO();
        try {
            if(CommonConstant.SUCCESS_DESC.equalsIgnoreCase(responseDataDTO.getMessage())) {
                if (null != dto &&null != dto.getFileType()&&dto.getFileType().equalsIgnoreCase("R001")) {
                    cashFileUploadVerifySelectRequestDTO.setFile(panStatusUpdateForTdsRedemptionExcel(responseDataDTO));
                    cashFileUploadVerifySelectRequestDTO.setFileName("PAN_STATUS_UPDATE_FOR_TDS_REDEMPTION.xlsx");
                    responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO);
                } else if (null != dto &&null != dto.getFileType()&&dto.getFileType().equalsIgnoreCase("I001")) {
                    cashFileUploadVerifySelectRequestDTO.setFile(panStatusUpdateForTdsRedemptionExcel(responseDataDTO));
                    cashFileUploadVerifySelectRequestDTO.setFileName("PAN_STATUS_UPDATE_FOR_TDS_INTEREST.xlsx");
                    responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage("Error: " + e.getMessage());
        }
        return responseDataDTO;
    }

}
