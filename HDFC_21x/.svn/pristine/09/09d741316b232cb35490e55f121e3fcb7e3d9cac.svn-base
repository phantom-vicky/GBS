/**
 *
 */
package com.isl.gbm.cbs.integration.parser;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.parser.model.BankBranchExcelRow;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.javafunk.excelparser.SheetParser;
import org.javafunk.excelparser.exception.ExcelParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The Class BranchBankFileParser.
 *
 * @author harishbabu.t
 */
@Component
public class BankBranchFileParser {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BankBranchFileParser.class);

    /**
     * Handle parse error.
     *
     * @param ex
     *            the ex
     */
    private static void handleParseError(final ExcelParsingException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
    }

    /** The validator. */
    @Autowired
    private Validator validator;

    /**
     * Builds the result list.
     *
     * @param file
     *            the file
     * @param branchBanksExcelRows
     *            the branch banks excel rows
     * @return the list
     * @throws DuplicateRecordException
     *             the duplicate record exception
     */
    private List<BankBranchDto> buildResultList(final MultipartFile file,
                                                final List<BankBranchExcelRow> branchBanksExcelRows) throws DuplicateRecordException {

        final HashSet<BankBranchExcelRow> rowSet = new HashSet<>(branchBanksExcelRows.size());
        final List<BankBranchDto> dtoList = new ArrayList<>(branchBanksExcelRows.size());

        for (final BankBranchExcelRow row : branchBanksExcelRows) {
            if (BooleanUtils.isFalse(rowSet.add(row))) {
                throw new DuplicateRecordException("Duplicate records found in the excel file");
            }

            // Check if any mandatory field is empty
            if (hasMandatoryFieldEmpty(row)) {
                throw new InvalidDataException("Mandatory Field is Empty");
            }

            final Errors errors = new BeanPropertyBindingResult(row, row.getClass().getName());
            this.validator.validate(row, errors);

            if (errors.hasErrors()) {
                throw new InvalidDataException(errors.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList()).toString());
            }
            dtoList.add(this.mapRowsToDto(row, file.getOriginalFilename()));
        }
        return dtoList;

    }

    /**
     * Checks if any mandatory field in BankBranchExcelRow is empty.
     * @param row BankBranchExcelRow object
     * @return true if any mandatory field is empty, false otherwise
     */
    private boolean hasMandatoryFieldEmpty(BankBranchExcelRow row) {
        if (row == null) {
            return true;
        }
        // List of mandatory fields
        List<String> mandatoryFields = Arrays.asList(
                "solId", "solDesc", "addr1", "addr2", "pinCode",
                "pdoCode", "shortName"
        );
        for (String fieldName : mandatoryFields) {
            try {
                Field field = row.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(row);
                if (value == null || value.toString().trim().isEmpty()) {
                    return true; // Found an empty mandatory field
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                LOGGER.error("Error checking mandatory fields", e);
            }
        }
        return false; // All mandatory fields are filled
    }

    /**
     * Map rows to dto.
     *
     * @param banksExcelRows
     *            the banks excel rows
     * @param fileName
     *            the file name
     * @return the branch bank dto
     */
    private BankBranchDto mapRowsToDto(final BankBranchExcelRow banksExcelRows, final String fileName) {
        final BankBranchDto branchBankDto = new BankBranchDto();
        branchBankDto.setSolId(banksExcelRows.getSolId());
        branchBankDto.setSolDesc(banksExcelRows.getSolDesc());
        branchBankDto.setBankCode(banksExcelRows.getBankCode());
        branchBankDto.setMicrBankCode(banksExcelRows.getMicrBankCode());
        branchBankDto.setMicrBranchCode(banksExcelRows.getMicrBranchCode());
        branchBankDto.setMicrCertreCode(banksExcelRows.getMicrCertreCode());
        branchBankDto.setAddr1(banksExcelRows.getAddr1());
        branchBankDto.setAddr2(banksExcelRows.getAddr2());
        branchBankDto.setCityCode(banksExcelRows.getCityCode());
        branchBankDto.setStateCode(banksExcelRows.getStateCode());
        branchBankDto.setPinCode(banksExcelRows.getPinCode());
        branchBankDto.setPanGirNumber(banksExcelRows.getPanGirNumber());
        branchBankDto.setTaxDednDetails(banksExcelRows.getTaxDednDetails());
        branchBankDto.setTdsCircleName(banksExcelRows.getTdsCircleName());
        branchBankDto.setDivisionName(banksExcelRows.getDivisionName());
        branchBankDto.setCirclOfficeName(banksExcelRows.getCirclOfficeName());
        branchBankDto.setUniformBrCode(banksExcelRows.getUniformBrCode());
        branchBankDto.setBranchLocation(banksExcelRows.getBranchLocation());
        branchBankDto.setZoneName(banksExcelRows.getZoneName());
        branchBankDto.setZoneCode(banksExcelRows.getZoneCode());
        branchBankDto.setPdoCode(banksExcelRows.getPdoCode());
        branchBankDto.setShortName(banksExcelRows.getShortName());
        branchBankDto.setBranchEmailId(banksExcelRows.getBranchEmailId());
        branchBankDto.setFileName(fileName);
        branchBankDto.setCreatedDate(new Date());
        branchBankDto.setCreatedBy(StringUtils.EMPTY);
        branchBankDto.setDeleteFlag(Boolean.FALSE);
        branchBankDto.setVerifiedFlag(Boolean.FALSE);
        if(banksExcelRows.getBranchStatus() != null && banksExcelRows.getBranchStatus().equalsIgnoreCase("ACTIVE")){
            branchBankDto.setBranchStatus(1);
        }
        else if(banksExcelRows.getBranchStatus() != null && banksExcelRows.getBranchStatus().equalsIgnoreCase("MERGED")) {
            branchBankDto.setBranchStatus(2);
        }
        else if(banksExcelRows.getBranchStatus() != null && banksExcelRows.getBranchStatus().equalsIgnoreCase("CLOSED")) {
            branchBankDto.setBranchStatus(3);
        }
        else{
            branchBankDto.setBranchStatus(0);
        }
        branchBankDto.setIfscCode(banksExcelRows.getBranchIfscCode());

        return branchBankDto;
    }

    /**
     * Parses the file.
     *
     * @param file
     *            the file
     * @return the list
     * @throws DuplicateRecordException
     *             the duplicate record exception
     */
    public List<BankBranchDto> parseFile(final MultipartFile file) throws DuplicateRecordException {
        final SheetParser sheetParser = new SheetParser();
        final List<BankBranchExcelRow> banksExcelRows = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();) {
            Workbook workbook;
            if (file.getOriginalFilename().endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("Invalid File Type");
            }
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final Sheet sheet = workbook.getSheetAt(sheetIndex);
                final List<BankBranchExcelRow> entityList = sheetParser.createEntity(sheet, BankBranchExcelRow.class,
                        BankBranchFileParser::handleParseError);
                if (CollectionUtils.isNotEmpty(entityList)) {
                    List<BankBranchExcelRow> nonEmptyEntityList = entityList.stream()
                            .filter(row -> !isRowEmpty(row))
                            .collect(Collectors.toList());
                    banksExcelRows.addAll(nonEmptyEntityList);
                }
            }
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }

        return this.buildResultList(file, banksExcelRows);
    }



    public List<BankBranchDto> parseFile1(final MultipartFile file) throws DuplicateRecordException {
        final List<BankBranchExcelRow> banksExcelRows = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook;

            if (file.getOriginalFilename() != null && file.getOriginalFilename().endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream); // for .xls files
            } else {
                throw new IllegalArgumentException("Invalid File Type. Only .xls is supported.");
            }

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final Sheet sheet = workbook.getSheetAt(sheetIndex);

                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // assuming row 0 is header
                    final Row row = sheet.getRow(rowIndex);
                    if (row == null) continue;

                    BankBranchExcelRow excelRow = new BankBranchExcelRow();

                    // Map cells to fields (adjust column indices as needed)
                    excelRow.setSolId(getStringCellValue(row.getCell(0)));
                    excelRow.setSolDesc(getStringCellValue(row.getCell(1)));
                    excelRow.setBankCode(getStringCellValue(row.getCell(2)));
                    excelRow.setMicrCertreCode(getStringCellValue(row.getCell(3)));
                    excelRow.setMicrBankCode(getStringCellValue(row.getCell(4)));
                    excelRow.setMicrBranchCode(getStringCellValue(row.getCell(5)));
                    excelRow.setAddr1(getStringCellValue(row.getCell(6)));
                    excelRow.setAddr2(getStringCellValue(row.getCell(7)));
                    excelRow.setCityCode(getStringCellValue(row.getCell(8)));
                    excelRow.setStateCode(getStringCellValue(row.getCell(9)));
                    excelRow.setPinCode(getStringCellValue(row.getCell(10)));
                    excelRow.setPanGirNumber(getStringCellValue(row.getCell(11)));
                    excelRow.setTaxDednDetails(getStringCellValue(row.getCell(12)));
                    excelRow.setTdsCircleName(getStringCellValue(row.getCell(13)));
                    excelRow.setDivisionName(getStringCellValue(row.getCell(14)));
                    excelRow.setCirclOfficeName(getStringCellValue(row.getCell(15)));
                    excelRow.setUniformBrCode(getStringCellValue(row.getCell(16)));
                    excelRow.setBranchLocation(getStringCellValue(row.getCell(17)));
                    excelRow.setZoneCode(getStringCellValue(row.getCell(18)));
                    excelRow.setZoneName(getStringCellValue(row.getCell(19)));
                    excelRow.setPdoCode(getStringCellValue(row.getCell(20)));
                    excelRow.setShortName(getStringCellValue(row.getCell(21)));
                    excelRow.setBranchEmailId(getStringCellValue(row.getCell(22)));
                    excelRow.setBranchStatus(getStringCellValue(row.getCell(23)));
                    excelRow.setBranchIfscCode(getStringCellValue(row.getCell(24)));

                    if (!isRowEmpty(excelRow)) {
                        banksExcelRows.add(excelRow);
                    }
                }
            }
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }

        return this.buildResultList(file, banksExcelRows);
    }


    private boolean isRowEmpty(BankBranchExcelRow row) {
        if (row == null) {
            return true;
        }

        for (Field field : row.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(row);
                if (value != null && !value.toString().trim().isEmpty()) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }


    /**
     * Sets the validator.
     *
     * @param validator
     *            the validator to set
     */
    public void setValidator(final Validator validator) {
        this.validator = validator;
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) return null;
        cell.setCellType(CellType.STRING); // converts numeric/date/etc. to string
        return cell.getStringCellValue().trim();
    }
}
