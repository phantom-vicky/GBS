package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.CashFileUploadAndNetBankingFileUploadBO;
import com.isl.gbm.rbibond.dao.CashFileUploadAndNetBankingFileUploadDAO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.util.FileSizeValidationService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CashFileUploadAndNetBankingFileUploadBoImpl implements CashFileUploadAndNetBankingFileUploadBO {


    private static final Logger log = LoggerFactory.getLogger(CashFileUploadAndNetBankingFileUploadBoImpl.class);

    private CashFileUploadAndNetBankingFileUploadDAO cashFileUploadAndNetBankingFileUploadDAO;
    private final Validator validator;
    private final FileSizeValidationService fileSizeValidationService;

    public CashFileUploadAndNetBankingFileUploadBoImpl(CashFileUploadAndNetBankingFileUploadDAO cashFileUploadAndNetBankingFileUploadDAO, FileSizeValidationService fileSizeValidationService){
    this.cashFileUploadAndNetBankingFileUploadDAO=cashFileUploadAndNetBankingFileUploadDAO;
    this.fileSizeValidationService=fileSizeValidationService;
    this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
    String nameFieldRejex = "^[a-zA-Z!@#$%^&*(),.?\":{}|<>_\\-+=~` ]+$";
    String fileNotExpected="File not in Expected Format Validation errors: ";
    public String dateConversionddmmyyyy(String date1){
    String formattedDateStr=null;
        try {
            String outputFormat1 = "ddMMyyyy";
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            Date date = inputFormat.parse(date1);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputFormat1);
            formattedDateStr= outputFormat.format(date);
        }catch(Exception e){
            e.printStackTrace();
        }
    return formattedDateStr;
}
    public String dateConversionddmmyy(String date1){
        String formattedDateStr=null;
        try {
            SimpleDateFormat initialFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            // Desired format
            SimpleDateFormat desiredFormat = new SimpleDateFormat("ddMMyy");
            Date date = initialFormat.parse(date1);
            formattedDateStr= desiredFormat.format(date);
        }catch(Exception e){
            e.printStackTrace();
        }
        return formattedDateStr;
    }
    @Override
    public ResponseDataDTO add(MultipartFile[] file, CashFileUploadAndNetBankingFileUploadRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        try {
            String ddmmyyyy=dateConversionddmmyyyy(cashFileUploadAndNetBankingFileUploadRequestDTO.getLiquidationDate());
            String ddmmyy=dateConversionddmmyy(cashFileUploadAndNetBankingFileUploadRequestDTO.getLiquidationDate());
            if(null!=file){
            for (MultipartFile file1 : file) {
                String filename = file1.getOriginalFilename();
                byte[] data = file1.getBytes();
                boolean isValid =fileSizeValidationService.validateFileSizeMultiPart(file);
                if (isValid) {
                    log.error("File size exceeds the maximum allowed size of 1MB for {}", filename);
                    responseDataDTO.setMessage("File size exceeds the maximum allowed size of 1MB for: "+filename);
                    responseDataDTO.setStatusCode("0");
                    return responseDataDTO;
                }
            try{
                if (null != file && null!=filename&&filename.equals("RRBNB_"+ddmmyyyy+"_BB.csv")) {
                    try (Reader reader = new InputStreamReader(file1.getInputStream())) {
                    CsvToBean<RRBNBBBRequestDTO> csvToBean = new CsvToBeanBuilder<RRBNBBBRequestDTO>(reader)
                            .withType(RRBNBBBRequestDTO.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                        List<RRBNBBBRequestDTO> csvData = csvToBean.parse();
                    csvData.remove(0);
                    csvData = csvData.stream()
                            .filter(dto -> dto.getPanNo() != null && !dto.getPanNo().isEmpty())
                            .peek(dto -> dto.setPanNo(dto.getPanNo().toUpperCase()))
                            .peek(dto -> validatePanNumber(dto.getPanNo()))
                            .collect(Collectors.toList());
                    // Validate each DTO and collect errors
                    List<String> errors = csvData.stream()
                            .flatMap(dto -> {
                                Set<ConstraintViolation<RRBNBBBRequestDTO>> violations = validator.validate(dto);
                                return violations.stream().map(ConstraintViolation::getMessage);
                            })
                            .collect(Collectors.toList());
                    if (!errors.isEmpty()) {
                        throw new IOException(fileNotExpected+filename+String.join(", ", errors));
                    }
                        BigDecimal total = BigDecimal.ZERO;
                        for (RRBNBBBRequestDTO data1 : csvData) {
                            if (data1.getInstrumentAmount() != null && !data1.getInstrumentAmount().isEmpty()) {
                                BigDecimal amount = new BigDecimal(data1.getInstrumentAmount().trim());
                                calculateTotalAndNomineeAmountRRBNBBB(data1,filename);
                                total = total.add(amount);
                            }
                        }
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setList1(csvData);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setFileName1(filename);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setFile1(data);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setCount1(String.valueOf(csvData.size()));
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setAmount1(String.valueOf(total));
                    }}
            if (null != file &&null!=filename&&filename.equals("TBNB_"+ddmmyyyy+"_BB.csv")) {
                try (Reader reader = new InputStreamReader(file1.getInputStream())) {
                CsvToBean<TBNBBBRequestDTO> csvToBean = new CsvToBeanBuilder<TBNBBBRequestDTO>(reader)
                        .withType(TBNBBBRequestDTO.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                List<TBNBBBRequestDTO> csvData = csvToBean.parse();
                csvData.remove(0);
                csvData = csvData.stream()
                        .filter(dto -> dto.getPanNo() != null && !dto.getPanNo().isEmpty())
                        .peek(dto -> dto.setPanNo(dto.getPanNo().toUpperCase()))
                        .peek(dto -> validatePanNumber(dto.getPanNo()))
                        .collect(Collectors.toList());
                // Validate each DTO and collect errors
                List<String> errors = csvData.stream()
                        .flatMap(dto -> {
                            Set<ConstraintViolation<TBNBBBRequestDTO>> violations = validator.validate(dto);
                            return violations.stream().map(ConstraintViolation::getMessage);
                        })
                        .collect(Collectors.toList());
                if (!errors.isEmpty()) {
                    throw new IOException(fileNotExpected+filename+String.join(", ", errors));
                }
                BigDecimal total = BigDecimal.ZERO;
                for (TBNBBBRequestDTO data1 : csvData) {
                    if (data1.getInstrumentAmount() != null && !data1.getInstrumentAmount().isEmpty()) {
                        BigDecimal amount = new BigDecimal(data1.getInstrumentAmount().trim());
                        total = total.add(amount);
                    }
                }
                cashFileUploadAndNetBankingFileUploadRequestDTO.setList2(csvData);
                cashFileUploadAndNetBankingFileUploadRequestDTO.setFileName2(filename);
                cashFileUploadAndNetBankingFileUploadRequestDTO.setFile2(data);
                cashFileUploadAndNetBankingFileUploadRequestDTO.setCount2(String.valueOf(csvData.size()));
                cashFileUploadAndNetBankingFileUploadRequestDTO.setAmount2(String.valueOf(total));
                    if (cashFileUploadAndNetBankingFileUploadRequestDTO.getCount1() != null && cashFileUploadAndNetBankingFileUploadRequestDTO.getCount2() != null &&
                            !cashFileUploadAndNetBankingFileUploadRequestDTO.getCount1().equals(cashFileUploadAndNetBankingFileUploadRequestDTO.getCount2())) {
                        log.error("Count mismatch for file: {}", cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName1() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName2());
                        responseDataDTO.setMessage("Count mismatch between files: " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName1() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName2());
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }else if(cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount1() != null && cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount2() != null &&
                            !cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount1().equals(cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount2())){
                        log.error("Amount mismatch for file: {}", cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName1() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName2());
                        responseDataDTO.setMessage("Amount mismatch for file: " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName1() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName2());
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                }}
                if (null != file && null!=filename&&filename.equals("RRBNB"+ddmmyy+".csv")) {
                    try (Reader reader = new InputStreamReader(file1.getInputStream())) {
                    CsvToBean<RRBNBRequestDTO> csvToBean = new CsvToBeanBuilder<RRBNBRequestDTO>(reader)
                            .withType(RRBNBRequestDTO.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<RRBNBRequestDTO> csvData= csvToBean.parse();
                    csvData.remove(0);
                    csvData = csvData.stream()
                            .filter(dto -> dto.getPan() != null && !dto.getPan().isEmpty())
                            .peek(dto -> dto.setPan(dto.getPan().toUpperCase()))
                            .peek(dto -> validatePanNumber(dto.getPan()))
                            .collect(Collectors.toList());
                    // Validate each DTO and collect errors
                    List<String> errors = csvData.stream()
                            .flatMap(dto -> {
                                Set<ConstraintViolation<RRBNBRequestDTO>> violations = validator.validate(dto);
                                return violations.stream().map(ConstraintViolation::getMessage);
                            })
                            .collect(Collectors.toList());
                    if (!errors.isEmpty()) {
                        throw new IOException(fileNotExpected+filename+String.join(", ", errors));
                    }
                    BigDecimal total = BigDecimal.ZERO;
                    for (RRBNBRequestDTO data1 : csvData) {
                        if (data1.getInstrumentAmount() != null && !data1.getInstrumentAmount().isEmpty()) {
                            BigDecimal amount = new BigDecimal(data1.getInstrumentAmount().trim());
                            calculateTotalAndNomineeAmountRRBNB(data1,filename);
                            total = total.add(amount);
                        }
                    }
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setList3(csvData);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setFileName3(filename);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setFile3(data);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setCount3(String.valueOf(csvData.size()));
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setAmount3(String.valueOf(total));
                    }}
            if (null != file &&null!=filename&&filename.equals("TBNB"+ddmmyy+".csv")) {
                try (Reader reader = new InputStreamReader(file1.getInputStream())) {
                CsvToBean<TBNBRequestDTO> csvToBean = new CsvToBeanBuilder<TBNBRequestDTO>(reader)
                        .withType(TBNBRequestDTO.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                List<TBNBRequestDTO> csvData= csvToBean.parse();
                csvData.remove(0);
                csvData = csvData.stream()
                        .filter(dto -> dto.getPanNo() != null && !dto.getPanNo().isEmpty())
                        .peek(dto -> dto.setPanNo(dto.getPanNo().toUpperCase()))
                        .peek(dto -> validatePanNumber(dto.getPanNo()))
                        .collect(Collectors.toList());
                // Validate each DTO and collect errors
                List<String> errors = csvData.stream()
                        .flatMap(dto -> {
                            Set<ConstraintViolation<TBNBRequestDTO>> violations = validator.validate(dto);
                            return violations.stream().map(ConstraintViolation::getMessage);
                        })
                        .collect(Collectors.toList());

                if (!errors.isEmpty()) {
                    throw new IOException(fileNotExpected+filename+String.join(", ", errors));
                }
                    BigDecimal total = BigDecimal.ZERO;
                    for (TBNBRequestDTO data1 : csvData) {
                        if (data1.getInstrumentAmount() != null && !data1.getInstrumentAmount().isEmpty()) {
                            BigDecimal amount = new BigDecimal(data1.getInstrumentAmount().trim());
                            total = total.add(amount);
                        }
                    }
                cashFileUploadAndNetBankingFileUploadRequestDTO.setList4(csvData);
                cashFileUploadAndNetBankingFileUploadRequestDTO.setFileName4(filename);
                cashFileUploadAndNetBankingFileUploadRequestDTO.setFile4(data);
                cashFileUploadAndNetBankingFileUploadRequestDTO.setCount4(String.valueOf(csvData.size()));
                cashFileUploadAndNetBankingFileUploadRequestDTO.setAmount4(String.valueOf(total));
                    if (cashFileUploadAndNetBankingFileUploadRequestDTO.getCount3() != null && cashFileUploadAndNetBankingFileUploadRequestDTO.getCount4() != null &&
                            !cashFileUploadAndNetBankingFileUploadRequestDTO.getCount3().equals(cashFileUploadAndNetBankingFileUploadRequestDTO.getCount4())) {
                        log.error("Count mismatch for file: {}", cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName3() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName4());
                        responseDataDTO.setMessage("Count mismatch between files: " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName3() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName4());
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }else if(cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount3() != null && cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount4() != null &&
                            !cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount3().equals(cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount4())){
                        log.error("Amount mismatch for file: {}", cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName3() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName4());
                        responseDataDTO.setMessage("Amount mismatch for file: " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName3() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName4());
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                }}if (null != file && null!=filename&&filename.equals("RRBNB_"+ddmmyyyy+"_Saksham.csv")) {
                    try (Reader reader = new InputStreamReader(file1.getInputStream())) {
                        CsvToBean<RRBNBNOVOPAYRequestDTO> csvToBean = new CsvToBeanBuilder<RRBNBNOVOPAYRequestDTO>(reader)
                                .withType(RRBNBNOVOPAYRequestDTO.class)
                                .withIgnoreLeadingWhiteSpace(true)
                                .build();
                        List<RRBNBNOVOPAYRequestDTO> csvData = csvToBean.parse();
                        csvData.remove(0);
                        csvData = csvData.stream()
                                .filter(dto -> dto.getPanNo() != null && !dto.getPanNo().isEmpty())
                                .peek(dto -> dto.setPanNo(dto.getPanNo().toUpperCase()))
                                .peek(dto -> validatePanNumber(dto.getPanNo()))
                                .collect(Collectors.toList());
                        // Validate each DTO and collect errors
                        List<String> errors = csvData.stream()
                                .flatMap(dto -> {
                                    Set<ConstraintViolation<RRBNBNOVOPAYRequestDTO>> violations = validator.validate(dto);
                                    return violations.stream().map(ConstraintViolation::getMessage);
                                })
                                .collect(Collectors.toList());
                        if (!errors.isEmpty()) {
                            throw new IOException(fileNotExpected + filename + String.join(", ", errors));
                        }
                        BigDecimal total = BigDecimal.ZERO;
                        for (RRBNBNOVOPAYRequestDTO data1 : csvData) {
                            if (data1.getInstrumentAmount() != null && !data1.getInstrumentAmount().isEmpty()) {
                                BigDecimal amount = new BigDecimal(data1.getInstrumentAmount().trim());
                                calculateTotalAndNomineeAmountNovoPay(data1,filename);
                                total = total.add(amount);
                            }
                        }
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setList6(csvData);
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setFileName6(filename);
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setFile6(data);
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setCount6(String.valueOf(csvData.size()));
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setAmount6(String.valueOf(total));
                    }
                }
                if (null != file &&null!=filename&&filename.equals("TBNB_"+ddmmyyyy+"_Saksham.csv")) {
                    try (Reader reader = new InputStreamReader(file1.getInputStream())) {
                    CsvToBean<TBNBBBRequestDTO> csvToBean = new CsvToBeanBuilder<TBNBBBRequestDTO>(reader)
                            .withType(TBNBBBRequestDTO.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<TBNBBBRequestDTO> csvData = csvToBean.parse();
                    csvData.remove(0);
                    csvData = csvData.stream()
                            .filter(dto -> dto.getPanNo() != null && !dto.getPanNo().isEmpty())
                            .peek(dto -> dto.setPanNo(dto.getPanNo().toUpperCase()))
                            .peek(dto -> validatePanNumber(dto.getPanNo()))
                            .collect(Collectors.toList());
                    // Validate each DTO and collect errors
                    List<String> errors = csvData.stream()
                            .flatMap(dto -> {
                                Set<ConstraintViolation<TBNBBBRequestDTO>> violations = validator.validate(dto);
                                return violations.stream().map(ConstraintViolation::getMessage);
                            })
                            .collect(Collectors.toList());
                    if (!errors.isEmpty()) {
                        throw new IOException(fileNotExpected+filename+String.join(", ", errors));
                    }
                        BigDecimal total = BigDecimal.ZERO;
                        for (TBNBBBRequestDTO data1 : csvData) {
                            if (data1.getInstrumentAmount() != null && !data1.getInstrumentAmount().isEmpty()) {
                                BigDecimal amount = new BigDecimal(data1.getInstrumentAmount().trim());
                                total = total.add(amount);
                            }
                        }
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setList7(csvData);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setFileName7(filename);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setFile7(data);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setCount7(String.valueOf(csvData.size()));
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setAmount7(String.valueOf(total));
        if (cashFileUploadAndNetBankingFileUploadRequestDTO.getCount6() != null && cashFileUploadAndNetBankingFileUploadRequestDTO.getCount7() != null &&
        !cashFileUploadAndNetBankingFileUploadRequestDTO.getCount6().equals(cashFileUploadAndNetBankingFileUploadRequestDTO.getCount7())) {
            log.error("Count mismatch for file: {}", cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName6() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName7());
            responseDataDTO.setMessage("Count mismatch between files: " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName6() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName7());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }else if(cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount6() != null && cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount7() != null &&
        !cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount6().equals(cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount7())){
            log.error("Amount mismatch for file: {}", cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName6() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName7());
            responseDataDTO.setMessage("Amount mismatch for file: " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName6() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName7());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
    }}if (null != file && null!=filename&&filename.equals("RRBNB_"+ddmmyyyy+"_NOVOPAY.csv")) {
                    try (Reader reader = new InputStreamReader(file1.getInputStream())) {
                        CsvToBean<RRBNBNOVOPAYRequestDTO> csvToBean = new CsvToBeanBuilder<RRBNBNOVOPAYRequestDTO>(reader)
                                .withType(RRBNBNOVOPAYRequestDTO.class)
                                .withIgnoreLeadingWhiteSpace(true)
                                .build();
                        List<RRBNBNOVOPAYRequestDTO> csvData = csvToBean.parse();
                        csvData.remove(0);
                        csvData = csvData.stream()
                                .filter(dto -> dto.getPanNo() != null && !dto.getPanNo().isEmpty())
                                .peek(dto -> dto.setPanNo(dto.getPanNo().toUpperCase()))
                                .peek(dto -> validatePanNumber(dto.getPanNo()))
                                .collect(Collectors.toList());
                        // Validate each DTO and collect errors
                        List<String> errors = csvData.stream()
                                .flatMap(dto -> {
                                    Set<ConstraintViolation<RRBNBNOVOPAYRequestDTO>> violations = validator.validate(dto);
                                    return violations.stream().map(ConstraintViolation::getMessage);
                                })
                                .collect(Collectors.toList());
                        if (!errors.isEmpty()) {
                            throw new IOException(fileNotExpected + filename + String.join(", ", errors));
                        }
                        BigDecimal total = BigDecimal.ZERO;
                        for (RRBNBNOVOPAYRequestDTO data1 : csvData) {
                            if (data1.getInstrumentAmount() != null && !data1.getInstrumentAmount().isEmpty()) {
                                BigDecimal amount = new BigDecimal(data1.getInstrumentAmount().trim());
                                calculateTotalAndNomineeAmountNovoPay(data1,filename);
                                total = total.add(amount);
                            }
                        }
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setList8(csvData);
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setFileName8(filename);
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setFile8(data);
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setCount8(String.valueOf(csvData.size()));
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setAmount8(String.valueOf(total));
                    }
                }
                if (null != file &&null!=filename&&filename.equals("TBNB_"+ddmmyyyy+"_NOVOPAY.csv")) {
                    try (Reader reader = new InputStreamReader(file1.getInputStream())) {
                        CsvToBean<TBNBBBRequestDTO> csvToBean = new CsvToBeanBuilder<TBNBBBRequestDTO>(reader)
                                .withType(TBNBBBRequestDTO.class)
                                .withIgnoreLeadingWhiteSpace(true)
                                .build();
                        List<TBNBBBRequestDTO> csvData = csvToBean.parse();
                        csvData.remove(0);
                        csvData = csvData.stream()
                                .filter(dto -> dto.getPanNo() != null && !dto.getPanNo().isEmpty())
                                .peek(dto -> dto.setPanNo(dto.getPanNo().toUpperCase()))
                                .peek(dto -> validatePanNumber(dto.getPanNo()))
                                .collect(Collectors.toList());
                        // Validate each DTO and collect errors
                        List<String> errors = csvData.stream()
                                .flatMap(dto -> {
                                    Set<ConstraintViolation<TBNBBBRequestDTO>> violations = validator.validate(dto);
                                    return violations.stream().map(ConstraintViolation::getMessage);
                                })
                                .collect(Collectors.toList());
                        if (!errors.isEmpty()) {
                            throw new IOException(fileNotExpected+filename+String.join(", ", errors));
                        }
                        BigDecimal total = BigDecimal.ZERO;
                        for (TBNBBBRequestDTO data1 : csvData) {
                            if (data1.getInstrumentAmount() != null && !data1.getInstrumentAmount().isEmpty()) {
                                BigDecimal amount = new BigDecimal(data1.getInstrumentAmount().trim());
                                total = total.add(amount);
                            }
                        }
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setList9(csvData);
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setFileName9(filename);
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setFile9(data);
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setCount9(String.valueOf(csvData.size()));
                        cashFileUploadAndNetBankingFileUploadRequestDTO.setAmount9(String.valueOf(total));
                        if (cashFileUploadAndNetBankingFileUploadRequestDTO.getCount8() != null && cashFileUploadAndNetBankingFileUploadRequestDTO.getCount9() != null &&
                                !cashFileUploadAndNetBankingFileUploadRequestDTO.getCount8().equals(cashFileUploadAndNetBankingFileUploadRequestDTO.getCount9())) {
                            log.error("Count mismatch for file: {}", cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName8() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName9());
                            responseDataDTO.setMessage("Count mismatch between files: " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName8() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName9());
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }else if(cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount8() != null && cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount9() != null &&
                                !cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount8().equals(cashFileUploadAndNetBankingFileUploadRequestDTO.getAmount9())){
                            log.error("Amount mismatch for file: {}", cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName8() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName9());
                            responseDataDTO.setMessage("Amount mismatch for file: " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName8() + " and " + cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName9());
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }
                    }}
                if (null != file &&null!=filename&&filename.equals("TAXBOND"+ddmmyyyy+".txt")) {
                    List<TaxBondTextFileDTO> transactions = new ArrayList<>();
                    List<TaxBondTextFileDTO> transactions1 = new ArrayList<>();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(file1.getInputStream()))) {
                        String line;
                    while ((line = br.readLine()) != null) {
                        TaxBondTextFileDTO transaction = parseLine(line);
                        if(!line.substring(0, 1).trim().equalsIgnoreCase("H")) {
                            validateAccountNumber(transaction.getAcctNo());
                            validateMobileNumber(transaction.getMobile());
                            validatePanNumber(transaction.getPan());
                            //validateDrawerName(transaction.getDrawerName());
                            //validateApplicantName(transaction.getApplicantName());
                        }
                        transactions.add(transaction);
                    }
                    if (transactions != null && !transactions.isEmpty()) {
                        String header = transactions.get(0).getHeader();
                        String headerDate=null;
                        if(header != null && header.trim().startsWith("H") ){
                         headerDate = header.substring(1, 9);
                        log.info("File Header date " + headerDate);
                        log.info("File Name date " + ddmmyyyy);
                        } else {
                            responseDataDTO.setMessage("Headers are missing ");
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }
                        if (null!=headerDate&& headerDate.equalsIgnoreCase(ddmmyyyy)) {
                            transactions.remove(0);
                        }else {
                            responseDataDTO.setMessage("Date validation failed.");
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }
                    }else{
                        responseDataDTO.setMessage("Records are empty");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                    transactions1.addAll(transactions);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setList5(transactions1);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setFileName5(filename);
                    cashFileUploadAndNetBankingFileUploadRequestDTO.setFile5(data);
                }}
              }catch(IOException er){
               log.info(fileNotExpected + filename );
               responseDataDTO.setMessage(fileNotExpected + filename + " - " + er.getMessage());
               responseDataDTO.setStatusCode("0");
                return responseDataDTO;
              }finally{

            }
            }
            }
        }catch (ValidationException e) {
            log.error("Validation error: {}", e.getMessage());
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage() != null ? e.getMessage() : "Error while processing the file");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return cashFileUploadAndNetBankingFileUploadDAO.add(cashFileUploadAndNetBankingFileUploadRequestDTO);
    }
    private RRBNBNOVOPAYRequestDTO calculateTotalAndNomineeAmountNovoPay(RRBNBNOVOPAYRequestDTO dto,String filename) {
        String nomineeOneAmountValue = dto.getNomineeOneAmount();
        String nomineeTwoAmountValue = dto.getNomineeTwoAmount();
        // If both nominee amounts are not provided, no calculation required
        if ((nomineeOneAmountValue == null || nomineeOneAmountValue.trim().isEmpty() || nomineeOneAmountValue.trim().equals("0"))
                && (nomineeTwoAmountValue == null || nomineeTwoAmountValue.trim().isEmpty() || nomineeTwoAmountValue.trim().equals("0"))) {
            return dto;
        }
        if (dto.getInstrumentAmount() == null || dto.getInstrumentAmount().trim().isEmpty() || dto.getInstrumentAmount().trim().equals("0")) {
            throw new ValidationException("Instrument Amount should not be empty "+filename);
        }
        BigDecimal instrumentAmount;
        BigDecimal nomineeOneAmount = BigDecimal.ZERO;
        BigDecimal nomineeTwoAmount = BigDecimal.ZERO;
        try {
            instrumentAmount = new BigDecimal(dto.getInstrumentAmount().trim());
            // Nominee One
            if (nomineeOneAmountValue != null && !nomineeOneAmountValue.trim().isEmpty()) {
                nomineeOneAmount = new BigDecimal(nomineeOneAmountValue.trim());
                validateMultipleOfThousand(nomineeOneAmount,filename);
            }
            // Nominee Two
            if (nomineeTwoAmountValue != null && !nomineeTwoAmountValue.trim().isEmpty()) {
                nomineeTwoAmount = new BigDecimal(nomineeTwoAmountValue.trim());
                validateMultipleOfThousand(nomineeTwoAmount,filename);
            }
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid Instrument or Nominee Amount "+filename);
        }
        // Nominee 1 + Nominee 2 must equal Instrument Amount
        BigDecimal nomineeTotal = nomineeOneAmount.add(nomineeTwoAmount);
        if (nomineeTotal.compareTo(instrumentAmount) != 0) {
            log.info("Instrument Amount: {}, Nominee Total: {}", instrumentAmount, nomineeTotal);
            throw new ValidationException("Nominee Amount does not match the Instrument Amount: "+filename);
        }
        // Calculate percentage - 2 decimal places
        BigDecimal nomineeOnePercentage = nomineeOneAmount
                .multiply(BigDecimal.valueOf(100))
                .divide(instrumentAmount, 2, RoundingMode.HALF_UP);
        BigDecimal nomineeTwoPercentage = nomineeTwoAmount.multiply(BigDecimal.valueOf(100)).divide(instrumentAmount, 2, RoundingMode.HALF_UP);
        // Set percentage in DTO
        dto.setNomineeOnePercentage(nomineeOnePercentage.toPlainString());
        dto.setNomineeTwoPercentage(nomineeTwoPercentage.toPlainString());
        return dto;
    }
    private RRBNBBBRequestDTO calculateTotalAndNomineeAmountRRBNBBB(RRBNBBBRequestDTO dto,String filename) {
        String nomineeOneAmountValue = dto.getNomineeOneAmount();
        String nomineeTwoAmountValue = dto.getNomineeTwoAmount();
        // If both nominee amounts are not provided, no calculation required
        if ((nomineeOneAmountValue == null || nomineeOneAmountValue.trim().isEmpty() || nomineeOneAmountValue.trim().equals("0"))
                && (nomineeTwoAmountValue == null || nomineeTwoAmountValue.trim().isEmpty() || nomineeTwoAmountValue.trim().equals("0"))) {
            return dto;
        }
        if (dto.getInstrumentAmount() == null || dto.getInstrumentAmount().trim().isEmpty() || dto.getInstrumentAmount().trim().equals("0")) {
            throw new ValidationException("Instrument Amount should not be empty "+filename);
        }
        BigDecimal instrumentAmount;
        BigDecimal nomineeOneAmount = BigDecimal.ZERO;
        BigDecimal nomineeTwoAmount = BigDecimal.ZERO;
        try {
            instrumentAmount = new BigDecimal(dto.getInstrumentAmount().trim());
            // Nominee One
            if (nomineeOneAmountValue != null && !nomineeOneAmountValue.trim().isEmpty()) {
                nomineeOneAmount = new BigDecimal(nomineeOneAmountValue.trim());
                validateMultipleOfThousand(nomineeOneAmount,filename);
            }
            // Nominee Two
            if (nomineeTwoAmountValue != null && !nomineeTwoAmountValue.trim().isEmpty()) {
                nomineeTwoAmount = new BigDecimal(nomineeTwoAmountValue.trim());
                validateMultipleOfThousand(nomineeTwoAmount,filename);
            }
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid Instrument or Nominee Amount "+filename);
        }
        // Nominee 1 + Nominee 2 must equal Instrument Amount
        BigDecimal nomineeTotal = nomineeOneAmount.add(nomineeTwoAmount);
        if (nomineeTotal.compareTo(instrumentAmount) != 0) {
            log.info("Instrument Amount: {}, Nominee Total: {}", instrumentAmount, nomineeTotal);
            throw new ValidationException("Nominee Amount does not match the Instrument Amount: "+filename);
        }
        // Calculate percentage - 2 decimal places
        BigDecimal nomineeOnePercentage = nomineeOneAmount
                .multiply(BigDecimal.valueOf(100))
                .divide(instrumentAmount, 2, RoundingMode.HALF_UP);
        BigDecimal nomineeTwoPercentage = nomineeTwoAmount.multiply(BigDecimal.valueOf(100)).divide(instrumentAmount, 2, RoundingMode.HALF_UP);
        // Set percentage in DTO
        dto.setNomineeOnePercentage(nomineeOnePercentage.toPlainString());
        dto.setNomineeTwoPercentage(nomineeTwoPercentage.toPlainString());
        return dto;
    }
    private RRBNBRequestDTO calculateTotalAndNomineeAmountRRBNB(RRBNBRequestDTO dto,String filename) {
        String nomineeAmountValue = dto.getNomineeAmount();
        // If nominee amount is not provided, no calculation required
        if (nomineeAmountValue == null || nomineeAmountValue.trim().isEmpty() || nomineeAmountValue.trim().equals("0")) {
            return dto;
        }
        // Validate Instrument Amount
        if (dto.getInstrumentAmount() == null || dto.getInstrumentAmount().trim().isEmpty() || dto.getInstrumentAmount().trim().equals("0")) {
            throw new ValidationException("Instrument Amount should not be empty "+filename);
        }
        try {
            BigDecimal instrumentAmount =new BigDecimal(dto.getInstrumentAmount().trim());
            BigDecimal nomineeAmount =new BigDecimal(nomineeAmountValue.trim());
            // Validate nominee amount is multiple of 1000
            validateMultipleOfThousand(nomineeAmount,filename);
            // Nominee Amount must equal Instrument Amount
            if (nomineeAmount.compareTo(instrumentAmount) != 0) {
                log.info("Instrument Amount: {}, Nominee Total: {}", instrumentAmount, nomineeAmount);
                throw new ValidationException("Nominee Amount does not match the Instrument Amount: "+filename);
            }
            // Calculate percentage - 3 decimal places
            BigDecimal nomineePercentage = nomineeAmount.multiply(BigDecimal.valueOf(100))
                    .divide(instrumentAmount, 3, RoundingMode.HALF_UP);
            // Only one nominee, so percentage will be 100.000
            dto.setNomineePercentage(nomineePercentage.toPlainString());
            return dto;
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid Instrument or Nominee Amount "+filename);
        }
    }
    private void validateMultipleOfThousand(BigDecimal amount, String filename) {
        if (amount.remainder(BigDecimal.valueOf(1000)).compareTo(BigDecimal.ZERO) != 0) {
            log.info("Amount: {}, Filename: {}", amount, filename);
            throw new ValidationException("Nominee Amount should be in multiples of 1000 "+filename);
        }
    }
    private void validateAccountNumber(String acctNo) {
        if(acctNo == null || acctNo.isEmpty()){
        }else if (null!=acctNo&&!acctNo.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Invalid Account Number: " + acctNo);
        }
    }
    private void validateMobileNumber(String mobileNo) {
        // Check if the mobile number is null or empty
        if (mobileNo == null || mobileNo.isEmpty()) {
            throw new IllegalArgumentException("Mobile number cannot be null or empty.");
        }

        // Check if the mobile number contains only digits
        if (!mobileNo.matches("[0-9]+")) {
            throw new IllegalArgumentException("Invalid mobile number: " + mobileNo + ". It must contain only numeric digits.");
        }

       /* // Check if the mobile number length is either 10 or 12 digits
        if (mobileNo.length() != 10 && mobileNo.length() != 12) {
            throw new IllegalArgumentException("Invalid mobile number length: " + mobileNo + ". It must be 10 or 12 digits long.");
        }*/
    }

    private void validatePanNumber(String panNo) {
        if (null!=panNo&&!panNo.matches(regex)) {
            throw new IllegalArgumentException(CommonConstants.INVALID_PAN_NO + panNo);
        }
    }

    private void validateDrawerName(String drawerName) {
        if(drawerName == null || drawerName.isEmpty()){
        }else if (null!=drawerName&&!drawerName.matches(nameFieldRejex)) {
            throw new IllegalArgumentException(CommonConstants.INVALID_DRAWER_NAME + drawerName);
        }
    }
    private void validateApplicantName(String applicantName) {
        if(applicantName == null || applicantName.isEmpty()){
        }else if (null!=applicantName&&!applicantName.matches(nameFieldRejex)) {
            throw new IllegalArgumentException(CommonConstants.INVALID_APPLICANT_NAME + applicantName);
        }
    }
    public boolean singleFileMovementInGbm1(File file, String targetDirectory) throws Exception {
        boolean isMoved = false;
        try{
            Files.move(Paths.get(file.getAbsolutePath()), Paths.get(targetDirectory + "/" + file.getName()), StandardCopyOption.REPLACE_EXISTING);
            isMoved = true;
        }catch (Exception e){
            e.printStackTrace();
            log.info("Error: "+e.getMessage());
            throw new GenericBusinessException("GBM file movement failure TargetPath: " + targetDirectory + " File Name: " + file.getName());
        }
        return isMoved;
    }
    public TaxBondTextFileDTO parseLine(String line) {
        TaxBondTextFileDTO transaction = new TaxBondTextFileDTO();
        if(line.substring(0, 1).trim().equalsIgnoreCase("H")) {
            transaction.setHeader(line.substring(0,17));
        }else {
            transaction.setTranType(line.substring(0, 1).trim());
            transaction.setEntityId(line.substring(1, 13).trim());
            transaction.setTypeOfEntry(line.substring(13, 14).trim());
            transaction.setDebitCreditFlag(line.substring(14, 15).trim());
            String accEntryAmountStr = line.substring(15,30).trim();
            if (null!=accEntryAmountStr&&accEntryAmountStr.length() == 15) {
                String integerPart = accEntryAmountStr.substring(0, 13);  // First 13 digits as the integer part
                String decimalPart = accEntryAmountStr.substring(13);    // Last 2 digits as the decimal part
                String accEntryAmount = integerPart + "." + decimalPart;
                BigDecimal accEntryAmnt = new BigDecimal(accEntryAmount);
                String accEntryAmountStrFinal = accEntryAmnt.toString();
                log.info("AccEntry Amount (BigDecimal): " + accEntryAmountStrFinal);
                transaction.setAccEntryAmount(accEntryAmountStrFinal);
            } else {
                log.info("Invalid AccEntry amount length!");
            }
            transaction.setValueDate(line.substring(30, 38).trim());
            transaction.setPostingDate(line.substring(38, 46).trim());
            transaction.setProductCode(line.substring(46, 56).trim());
            transaction.setPickupLocation(line.substring(56, 96).trim());
            transaction.setPickupPoint(line.substring(96, 136).trim());
            transaction.setPickupDate(line.substring(136, 144).trim());
            transaction.setDepositSlipNmbr(line.substring(144, 154).trim());
            transaction.setDepositDate(line.substring(154, 162).trim());
            String depositAmountStr = line.substring(162, 177).trim();
            if (null!=depositAmountStr&&depositAmountStr.length() == 15) {
                String integerPart = depositAmountStr.substring(0, 13);
                String decimalPart = depositAmountStr.substring(13);
                String combinedAmount = integerPart + "." + decimalPart;
                BigDecimal depositAmount = new BigDecimal(combinedAmount);
                String depositAmountStrFinal = depositAmount.toString();
                log.info("Deposit Amount (BigDecimal): " + depositAmountStrFinal);
                transaction.setDepositAmnt(depositAmountStrFinal);
            } else {
                log.info("Invalid Deposit amount length!");
            }
            transaction.setNoOfInst(line.substring(177, 182).trim());
            transaction.setDepositRemarks(line.substring(182, 442).trim());
            transaction.setInstNmbr(line.substring(442, 452).trim());
            transaction.setDraweeBankDescription(line.substring(452, 492).trim());
            transaction.setLocDescription(line.substring(492, 532).trim());
            String instrumentAmountStr = line.substring(532, 547).trim();
            if (null!=instrumentAmountStr&&instrumentAmountStr.length() == 15) {
                String integerPart = instrumentAmountStr.substring(0, 13);  // First 13 digits as the integer part
                String decimalPart = instrumentAmountStr.substring(13);    // Last 2 digits as the decimal part
                String combinedAmount = integerPart + "." + decimalPart;
                BigDecimal depositAmount = new BigDecimal(combinedAmount);
                String instAmountStrFinal = depositAmount.toString();
                log.info("Instrument Amount (BigDecimal): " + instAmountStrFinal);
                transaction.setInstrumentAmnt(instAmountStrFinal);
            } else {
                log.info("Invalid Instrument amount length!");
            }
            transaction.setInstrumentDate(line.substring(547, 555).trim());
            transaction.setDrawerName(line.substring(555, 595).trim());
            transaction.setApplicantName(line.substring(595, 635).trim());
            transaction.setOperatingInstructions(line.substring(635, 649).trim());
            transaction.setCheckListNumber(line.substring(649, 663).trim());
            transaction.setCoh(line.substring(663, 664).trim());
            transaction.setScheme(line.substring(664, 665).trim());
            transaction.setBla(line.substring(665, 679).trim());
            transaction.setType(line.substring(679, 680).trim());
            transaction.setPan(line.substring(680, 690).trim().toUpperCase());
            transaction.setEmail(line.substring(690, 790).trim());
            transaction.setBankName(line.substring(790, 840).trim());
            transaction.setAcctNo(line.substring(840, 860).trim());
            transaction.setIfsc(line.substring(860, 871).trim());
            transaction.setMobile(line.substring(871, 885).trim());
            return transaction;
        }
        return transaction;
    }
    @Override
    public ResponseDataDTO verifySelect(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadDAO.verifySelect(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO verify(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadDAO.verify(cashFileUploadVerifySelectRequestDTO);
    }


    @Override
    public ResponseDataDTO delete(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadDAO.delete(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO inquiry(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadDAO.inquiry(cashFileUploadVerifySelectRequestDTO);
    }
}