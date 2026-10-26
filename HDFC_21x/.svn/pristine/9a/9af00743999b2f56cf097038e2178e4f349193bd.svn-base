/*
 *
 */
package com.isl.gbm.cbs.integration.parser;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.parser.model.SchemeCodesExcelRow;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchemeCodesParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchemeCodesParser.class);

    private static void handleParseError(final ExcelParsingException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
    }

    @Autowired
    private Validator validator;

    private List<SchemeCodesDto> buildResultList(final MultipartFile file,
                                                 final List<SchemeCodesExcelRow> schemeExcelRows) throws DuplicateRecordException, InvalidDataException {

        final HashSet<SchemeCodesExcelRow> rowSet = new HashSet<>(schemeExcelRows.size());
        final List<SchemeCodesDto> dtoList = new ArrayList<>(schemeExcelRows.size());
        for (final SchemeCodesExcelRow schemeCodesExcelRows : schemeExcelRows) {
            if (BooleanUtils.isFalse(rowSet.add(schemeCodesExcelRows))) {
                throw new DuplicateRecordException("Duplicate records found in the excel file");
            }
            final Errors errors = new BeanPropertyBindingResult(schemeCodesExcelRows,
                    schemeCodesExcelRows.getClass().getName());
            this.validator.validate(schemeCodesExcelRows, errors);

            if (errors.hasErrors()) {
                throw new InvalidDataException(errors.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList()).toString());
            }
            dtoList.add(this.mapRowsToDto(schemeCodesExcelRows, file.getOriginalFilename()));
        }
        return dtoList;
    }

    /**
     * Map rows to dto.
     *
     * @param schemeCodesExcelRows the scheme codes excel rows
     * @param originalFilename     the original filename
     * @return the scheme codes dto
     */
    private SchemeCodesDto mapRowsToDto(final SchemeCodesExcelRow schemeCodesExcelRows, final String originalFilename) {
        final SchemeCodesDto schemeCodeDto = new SchemeCodesDto();
        schemeCodeDto.setSchemeCode(schemeCodesExcelRows.getSchemeCode());
        schemeCodeDto.setDescription(schemeCodesExcelRows.getDescription());
        schemeCodeDto.setGlSubHeadCode(schemeCodesExcelRows.getGlSubHeadCode());
        schemeCodeDto.setSchemeType(schemeCodesExcelRows.getSchemeType());
        schemeCodeDto.setAddText1(schemeCodesExcelRows.getAddText1());
        schemeCodeDto.setAddText2(schemeCodesExcelRows.getAddText2());
        schemeCodeDto.setAddText3(schemeCodesExcelRows.getAddText3());
        schemeCodeDto.setCreatedDate(new Date());
        schemeCodeDto.setFileName(originalFilename);
        return schemeCodeDto;
    }

    public List<SchemeCodesDto> parse(final MultipartFile file)
            throws DuplicateRecordException, InvalidDataException, IOException {
        final List<SchemeCodesExcelRow> excelRows = this.readFile(file);
        return this.buildResultList(file, excelRows);
    }

    private List<SchemeCodesExcelRow> readFile(final MultipartFile file) throws IOException {
        final SheetParser sheetParser = new SheetParser();
        final List<SchemeCodesExcelRow> excelRows = new ArrayList<>(1000);
        final InputStream inputStream = file.getInputStream();
        final HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            final List<SchemeCodesExcelRow> entityList = sheetParser.createEntity(sheet, SchemeCodesExcelRow.class,
                    SchemeCodesParser::handleParseError);
            if (CollectionUtils.isNotEmpty(entityList)) {
                excelRows.addAll(entityList);
            }
        }
        workbook.close();
        return excelRows;
    }

    public void setValidator(final Validator validator) {
        this.validator = validator;
    }

}
