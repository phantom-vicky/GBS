package com.isl.gbm.rbibond.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import com.isl.gbm.common.dto.AccountValidationDto;
import com.isl.gbm.common.dto.PaymentProgressDto;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.dao.RbiBondCentralizedInterestTrailRunDao;
import com.isl.gbm.rbibond.dto.FileResponseDto;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDownloadDto;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDto;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;
import com.isl.gbm.rbibond.service.RbiBondCentralizedInterestTrailRunService;
import com.isl.gbm.rbibond.util.RbiBulkPaymentEvent;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class RbiBondCentralizedInterestTrailRunServiceImpl implements RbiBondCentralizedInterestTrailRunService {
    private static final Logger log = LoggerFactory.getLogger(RbiBondCentralizedInterestTrailRunServiceImpl.class);

    @Autowired
    private RbiBondCentralizedInterestTrailRunDao rbiBondCentralizedInterestTrailRunDao;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private TransactionValidator transactionValidator;

    @Autowired
    private JobLauncher jobLauncher;


    @Qualifier("RbiBondIntPayJob")
    @Autowired
    private Job rbiBondInterestPayementJob;

    @Qualifier("RbibondIntPayJobForDirectTransaction")
    @Autowired
    private Job rbiBondInterestPayementJobForDirectTran;

    private static Map<String, PaymentProgressDto> paymentProgressMap = new Hashtable<>();

    @Scheduled(fixedDelay = 28800000L)    //Every 8 Hours
    public void clearCompletedBatches() {
        PaymentProgressDto tempDto;
        for (String pId : paymentProgressMap.keySet()) {
            tempDto = paymentProgressMap.get(pId);
            if (tempDto.getValidationDone() && tempDto.getTransactionDone())
                paymentProgressMap.remove(pId);
        }
    }


    @Override
    public ResponseDataDTO add(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunDao.add(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    public ResponseDataDTO addTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunDao.addTorch(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    public ResponseDataDTO getTrialRunProcess(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
//        if(rbiBondCentralizedInterestTrailRunDto.getBlaList().size() > 0 || rbiBondCentralizedInterestTrailRunDto.getSubsList().size() > 0){
//            responseDataDTO.setMessage("Unverified Records Exists, Please verify the records to continue Trail Run");
//        } else {
            responseDataDTO = rbiBondCentralizedInterestTrailRunDao.getTrialRunProcess(rbiBondCentralizedInterestTrailRunDto);
//        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getReportTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunDao.getReportTorch(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    public ResponseDataDTO getTrialRunDetailsReport(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) throws IOException {
        return rbiBondCentralizedInterestTrailRunDao.getTrialRunDetailsReport(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    public ResponseDataDTO interestPaymentAddTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunDao.interestPaymentAddTorch(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    public ResponseDataDTO intPayUnverifiedRecords(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunDao.intPayUnverifiedRecords(rbiBondCentralizedInterestTrailRunDto);
    }


    @Override
    public ResponseDataDTO getInterestPayBatchTranDetails(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto) {


        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ResponseDataDTO trailRunResponse = new ResponseDataDTO();
        ResponseDataDTO BulkGetDetResponse = new ResponseDataDTO();



        try {
//creating process id for batch process
            String processId = "RBI_BOND_" + UserAccountUtils.getCurrentUsername() + "_" + UserAccountUtils.getCurrentSolId() + (StringUtils.isEmpty(rbiBondCentralizedInterestTrailRunDto.getBranchId()) ? ("") : ("_" + rbiBondCentralizedInterestTrailRunDto.getBranchId())) + (StringUtils.isEmpty(rbiBondCentralizedInterestTrailRunDto.getBlaNo()) ? ("") : ("_" + rbiBondCentralizedInterestTrailRunDto.getBlaNo())) + (StringUtils.isEmpty(rbiBondCentralizedInterestTrailRunDto.getBondSubsId()) ? ("") : ("_" + rbiBondCentralizedInterestTrailRunDto.getBondSubsId())) + ("_" + System.currentTimeMillis());

//setting the process id to  paymentprogress dto and storing in map
            System.out.println("*****bulk payment process initiated******"+"processId: "+processId);
            paymentProgressMap.put("paymentProcessId", new PaymentProgressDto(processId));

            Map<String, Object> resultData = new HashMap<>();
            resultData.put("paymentProcessId", processId);
            responseDataDTO.setResult(resultData);
            rbiBondCentralizedInterestTrailRunDto.setProcessId(processId);

//  trial run process
            log.info("TRIAL RUN STATRTED");
            trailRunResponse =  rbiBondCentralizedInterestTrailRunDao.trailrun(rbiBondCentralizedInterestTrailRunDto);

            if(trailRunResponse.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {


                BulkGetDetResponse = rbiBondCentralizedInterestTrailRunDao.bulkPaymentGetDet(rbiBondCentralizedInterestTrailRunDto);
                if (BulkGetDetResponse.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {

                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
//   FINCAL ACCOUNT VALIDATION
                    ResponseDataDTO cbsAccValditionRes = new ResponseDataDTO();

                    cbsAccValditionRes=cbsAccValidation(rbiBondCentralizedInterestTrailRunDto);

                    if(cbsAccValditionRes.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)){

                        List<String> validationBatchList = this.rbiBondCentralizedInterestTrailRunDao.getValidationBatchList(rbiBondCentralizedInterestTrailRunDto);
                        if(validationBatchList != null) {
                            applicationEventPublisher.publishEvent(new RbiBulkPaymentEvent(this, CommonConstants.EVENT_TYPE_VALIDATION,  rbiBondCentralizedInterestTrailRunDto.getBranchId(),rbiBondCentralizedInterestTrailRunDto.getBlaNo(), rbiBondCentralizedInterestTrailRunDto.getBondSubsId(),rbiBondCentralizedInterestTrailRunDto.getCentralizedFlag(),rbiBondCentralizedInterestTrailRunDto.getProcessId(),rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear(),rbiBondCentralizedInterestTrailRunDto.getYear(), validationBatchList, rbiBondCentralizedInterestTrailRunDto.getModuleId()));
                        }

                        else{
                            List<String> availableTranBatchList = this.rbiBondCentralizedInterestTrailRunDao.getTransactionBatchList(rbiBondCentralizedInterestTrailRunDto.getProcessId(), rbiBondCentralizedInterestTrailRunDto.getCentralizedFlag(), rbiBondCentralizedInterestTrailRunDto.getBranchId(), rbiBondCentralizedInterestTrailRunDto.getBondSubsId(), rbiBondCentralizedInterestTrailRunDto.getBlaNo(),rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear(),rbiBondCentralizedInterestTrailRunDto.getYear(),rbiBondCentralizedInterestTrailRunDto.getModuleId()  );
                            applicationEventPublisher.publishEvent(new RbiBulkPaymentEvent(this, CommonConstants.EVENT_TYPE_TRANSACTION,  rbiBondCentralizedInterestTrailRunDto.getBranchId(),rbiBondCentralizedInterestTrailRunDto.getBlaNo(), rbiBondCentralizedInterestTrailRunDto.getBondSubsId(),rbiBondCentralizedInterestTrailRunDto.getCentralizedFlag(),rbiBondCentralizedInterestTrailRunDto.getProcessId(),rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear(),rbiBondCentralizedInterestTrailRunDto.getYear(), availableTranBatchList, rbiBondCentralizedInterestTrailRunDto.getModuleId()));
                        }

                    }else{
                        System.out.println("*****finacle side failure *****");
                        responseDataDTO.setMessage("OFFICE ACCOUNT VALIDATION FAILED"); }

                    }else{
                    System.out.println("*****db GetDet failed *****");
                    responseDataDTO.setMessage("NO RECORDS FOUND FOR PAYMENT");
                     }
            }



//            return rbiBondCentralizedInterestTrailRunDao.getInterestPayBatchTranDetails(rbiBondCentralizedInterestTrailRunDto);
        } catch (Exception e) {
            responseDataDTO.setMessage("FAILURE");
            e.printStackTrace();

            e.getStackTrace();
            log.info(e.getMessage());
        }
//        System.out.println( RbiBulkPaymentEvent.eventType);

    return responseDataDTO;
    }

    @Override
    public ResponseDataDTO download(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ResponseDataDTO responseDataDTO1 = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            if(rbiBondCentralizedInterestTrailRunDto.getFlag().equalsIgnoreCase("UNMERGED")) {
                responseDataDTO1=rbiBondCentralizedInterestTrailRunDao.download(rbiBondCentralizedInterestTrailRunDto);
                if(responseDataDTO1.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(trailRunExcelUnmerged(responseDataDTO1, rbiBondCentralizedInterestTrailRunDto)));
                    fileResponseDto.setFileName("INTEREST_TRAIL_RUN_ADD_UNMERGED.xlsx");
                }else {
                    return responseDataDTO1;
                }
            }else if(rbiBondCentralizedInterestTrailRunDto.getFlag().equalsIgnoreCase("MERGED")){
                responseDataDTO1=rbiBondCentralizedInterestTrailRunDao.downloadMerged(rbiBondCentralizedInterestTrailRunDto);
                if(responseDataDTO1.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(trailRunExcelMerged(responseDataDTO1, rbiBondCentralizedInterestTrailRunDto)));
                    fileResponseDto.setFileName("INTEREST_TRAIL_RUN_ADD_MERGED.xlsx");
                }else {
                    return responseDataDTO1;
                }
            }
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in RbiBondCentralizedInterestTrailRunDto, RbiBondCentralizedTrailRunServiceImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySelect(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) {
        return rbiBondCentralizedInterestTrailRunDao.verifySelect(rbiBondCentralizedInterestTrailRunDtoRequest);

    }

    @Override
    public ResponseDataDTO getInterestTrailRunVerify(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException {
        return  rbiBondCentralizedInterestTrailRunDao.getInterestTrailRunVerify(rbiBondCentralizedInterestTrailRunDtoRequest);
    }
    @Override
    public ResponseDataDTO getInterestTrailRunVerifyDownload(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ResponseDataDTO responseDataDTO1 = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            if(rbiBondCentralizedInterestTrailRunDtoRequest.getFlag().equalsIgnoreCase("UNMERGED")) {
                responseDataDTO1=rbiBondCentralizedInterestTrailRunDao.getInterestTrailRunVerify(rbiBondCentralizedInterestTrailRunDtoRequest);
                if(null!=responseDataDTO1&&null!=responseDataDTO1.getMessage()&&responseDataDTO1.getMessage().equalsIgnoreCase("SUCCESS")){
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(trailRunExcelUnmerged(responseDataDTO1, rbiBondCentralizedInterestTrailRunDtoRequest)));
                fileResponseDto.setFileName("INTEREST_TRAIL_RUN_UNMERGED.xlsx");
                }else {
                return responseDataDTO1;
            }
            }else if(rbiBondCentralizedInterestTrailRunDtoRequest.getFlag().equalsIgnoreCase("MERGED")) {
                responseDataDTO1 = rbiBondCentralizedInterestTrailRunDao.downloadMerged(rbiBondCentralizedInterestTrailRunDtoRequest);
                if (null != responseDataDTO1 && null != responseDataDTO1.getMessage() && responseDataDTO1.getMessage().equalsIgnoreCase("SUCCESS")) {
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(trailRunExcelMerged(responseDataDTO1, rbiBondCentralizedInterestTrailRunDtoRequest)));
                    fileResponseDto.setFileName("INTEREST_TRAIL_RUN_MERGED.xlsx");
                } else {
                    return responseDataDTO1;
                }
            }
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in RbiBondCentralizedInterestTrailRunDto, RbiBondCentralizedTrailRunServiceImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    public byte[] trailRunExcelUnmerged(ResponseDataDTO responseDataDTO,RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) {
        List<RbiBondCentralizedInterestTrailRunDownloadDto> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
        String solDesc = Optional.ofNullable(UserAccountUtils.getCurrentUsername()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            workbook.setCompressTempFiles(true);
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("CENTRALIZED TRAIL RUN UNMERGED");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));
            // Create bank information row
            SXSSFRow bankInfoRow1 = sheet.createRow(2);
            SXSSFCell bankInfoCell1 = bankInfoRow1.createCell(0);
            bankInfoCell1.setCellValue("Interest for the Half Year : " + rbiBondCentralizedInterestTrailRunDtoRequest.getInterestForHalfYear() + " Year : " + rbiBondCentralizedInterestTrailRunDtoRequest.getYear());

// Create and set bank info cell style
            CellStyle bankInfoCellStyle1 = workbook.createCellStyle();
            bankInfoCellStyle1.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell1.setCellStyle(bankInfoCellStyle1);

            // Merge cells for bank information
            SXSSFRow headerRow1 = sheet.createRow(3);
            String[] mainHeaders = {
                    "","Basic Details From BLA cum Subscription", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "",
                    "OTHER SCHEMES INTEREST RELATED INFO", "", "", "", "", "", "", "",
                    "PAN STATUS", "", "","","","",
                    "TDS EXEMPTION DETAILS", "", "", "", "", "", "",
                    "FRSB INTEREST PAYOUT DETAILS", ""
            };

            CellStyle headerCellStyle1 = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font1 = workbook.createFont();
            font1.setBold(true);
            headerCellStyle1.setFont(font1);

            // Track maximum column widths
            int[] maxColumnWidths1 = new int[mainHeaders.length];
            for (int i = 0; i < mainHeaders.length; i++) {
                SXSSFCell cell = headerRow1.createCell(i);
                cell.setCellValue(mainHeaders[i]);
                cell.setCellStyle(headerCellStyle1);
                maxColumnWidths1[i] = mainHeaders[i].length();
            }

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(4);
            String[] headers = {
                    "Sl.No", "ModeOfPayment", "BranchRefNo", "BlaNo", "RefNo",
                    "ApplName1", "ApplName2", "ApplName3", "Addr1", "Addr2",
                    "RecName", "RecParentName", "PinCode", "PhoneNo", "ApplStatus",
                    "InvestmentAmount", "CFD", "MatDate", "AccountNo", "SubscriptionCount",
                    "BankSortCode", "BankName", "PanNo", "InterestPayout8Percent",
                    "NonCumulativeInt8", "CumulativeInt8", "InterestPayout7.75Percent",
                    "NonCumulativeInt7.75", "CumulativeInt7.75", "InterestPayout8.05Percent",
                    "NonCumulativeInt8.05", "CumulativeInt8.05", "TotalInterestPayout",
                    "InOperativePan", "NonFilers", "Age", "TaxableLimit",
                    "WaiverDocument", "WaiverDocumentRefNo", "RrbTdsRate",
                    "ThresholdExceedAmount", "ThresholdExceedTdsAmount", "ExemptionRate",
                    "ExemptionAmount", "ExemptionTdsAmount", "SurchargePercent", "EduCessPercent",
                    "BaseRateAmt", "SurchargeAmt", "EduCessAmt", "TdsAmount",
                    "TdsRecoveryAmount", "TdsRefundAmount", "IntRate", "LastIntPaid",
                    "PendingInterest", "CurrentHalfYearlyInterest", "FutureInterest",
                    "GrossInterestAmount", "IntPaidNet", "PaymentDate", "TotalAmountToBePaid"
            };


            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
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

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<RbiBondCentralizedInterestTrailRunDownloadDto>) finalData.get("gstAckFileDownloadDTOList");

            // Populate data rows
            int rowNumber = 5;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (RbiBondCentralizedInterestTrailRunDownloadDto data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getModeOfPayment() != null ? data.getModeOfPayment() : "",
                            data.getBranchRefNo() != null ? data.getBranchRefNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getRefNo() != null ? data.getRefNo() : "",
                            data.getApplName1() != null ? data.getApplName1() : "",
                            data.getApplName2() != null ? data.getApplName2() : "",
                            data.getApplName3() != null ? data.getApplName3() : "",
                            data.getAddr1() != null ? data.getAddr1() : "",
                            data.getAddr2() != null ? data.getAddr2() : "",
                            data.getRecName() != null ? data.getRecName() : "",
                            data.getRecParentName() != null ? data.getRecParentName() : "",
                            data.getPinCode() != null ? data.getPinCode() : "",
                            data.getPhoneNo() != null ? data.getPhoneNo() : "",
                            data.getApplStatus() != null ? data.getApplStatus() : "",
                            data.getInvestmentAmt() != null ? data.getInvestmentAmt() : "",
                            data.getCfd() != null ? data.getCfd() : "",
                            data.getMatDate() != null ? data.getMatDate() : "",
                            data.getAccountNo() != null ? data.getAccountNo() : "",
                            data.getSubscriptionCount() != null ? data.getSubscriptionCount() : "",
                            data.getBankSortCode() != null ? data.getBankSortCode() : "",
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getInterestPayout8Percent() != null ? data.getInterestPayout8Percent() : "",
                            data.getNonCumulativeInt8() != null ? data.getNonCumulativeInt8() : "",
                            data.getCumulativeInt8() != null ? data.getCumulativeInt8() : "",
                            data.getInterestPayout775Percent() != null ? data.getInterestPayout775Percent() : "",
                            data.getNonCumulativeInt775() != null ? data.getNonCumulativeInt775() : "",
                            data.getCumulativeInt775() != null ? data.getCumulativeInt775() : "",
                            data.getInterestPayout805Percent() != null ? data.getInterestPayout805Percent() : "",
                            data.getNonCumulativeInt805() != null ? data.getNonCumulativeInt805() : "",
                            data.getCumulativeInt805() != null ? data.getCumulativeInt805() : "",
                            data.getTotalInterestPayout() != null ? data.getTotalInterestPayout() : "",
                            data.getInOperativePan() != null ? data.getInOperativePan() : "",
                            data.getNonFilers() != null ? data.getNonFilers() : "",
                            data.getAge() != null ? data.getAge() : "",
                            data.getTaxableLimit() != null ? data.getTaxableLimit() : "",
                            data.getWaiverDocument() != null ? data.getWaiverDocument() : "",
                            data.getWaiverDocumentRefNo() != null ? data.getWaiverDocumentRefNo() : "",
                            data.getRrbTdsRate() != null ? data.getRrbTdsRate() : "",
                            data.getThresholdExceedAmount() != null ? data.getThresholdExceedAmount() : "",
                            data.getThresholdExceedTdsAmount() != null ? data.getThresholdExceedTdsAmount() : "",
                            data.getExemptionRate() != null ? data.getExemptionRate() : "",
                            data.getExemptionAmount() != null ? data.getExemptionAmount() : "",
                            data.getExemptionTdsAmount() != null ? data.getExemptionTdsAmount() : "",
                            data.getSurchargePercent() != null ? data.getSurchargePercent() : "",
                            data.getEduCessPercent() != null ? data.getEduCessPercent() : "",
                            data.getBaseRateAmt() != null ? data.getBaseRateAmt() : "",
                            data.getSurchargeAmt() != null ? data.getSurchargeAmt() : "",
                            data.getEduCessAmt() != null ? data.getEduCessAmt() : "",
                            data.getTdsAmount() != null ? data.getTdsAmount() : "",
                            data.getTdsRecoveryAmount() != null ? data.getTdsRecoveryAmount() : "",
                            data.getTdsRefundAmount() != null ? data.getTdsRefundAmount() : "",
                            data.getIntRate() != null ? data.getIntRate() : "",
                            data.getLastIntPaid() != null ? data.getLastIntPaid() : "",
                            data.getPendingInterest() != null ? data.getPendingInterest() : "",
                            data.getCurrentHalfYearlyInterest() != null ? data.getCurrentHalfYearlyInterest() : "",
                            data.getFutureInterest() != null ? data.getFutureInterest() : "",
                            data.getGrossInterestAmount() != null ? data.getGrossInterestAmount() : "",
                            data.getIntPaidNet() != null ? data.getIntPaidNet() : "",
                            data.getPaymentDate() != null ? data.getPaymentDate() : "",
                            data.getTotalAmountToBePaid() != null ? data.getTotalAmountToBePaid() : ""
                    };

                    //log.info("cellValues.length: "+cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            }
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("CENTRALIZED TRAIL RUN DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }
    public byte[] trailRunExcelMerged(ResponseDataDTO responseDataDTO,RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) {
        List<RbiBondCentralizedInterestTrailRunDownloadDto> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
        String solDesc = Optional.ofNullable(UserAccountUtils.getCurrentUsername()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            workbook.setCompressTempFiles(true);
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("CENTRALIZED TRAIL RUN MERGED");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));
            // Create bank information row
            SXSSFRow bankInfoRow1 = sheet.createRow(2);
            SXSSFCell bankInfoCell1 = bankInfoRow1.createCell(0);
            bankInfoCell1.setCellValue("Interest for the Half Year : " + rbiBondCentralizedInterestTrailRunDtoRequest.getInterestForHalfYear() + " Year : " + rbiBondCentralizedInterestTrailRunDtoRequest.getYear());

// Create and set bank info cell style
            CellStyle bankInfoCellStyle1 = workbook.createCellStyle();
            bankInfoCellStyle1.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell1.setCellStyle(bankInfoCellStyle1);

            // Merge cells for bank information
            SXSSFRow headerRow1 = sheet.createRow(3);
            String[] mainHeaders = {
                    "","Basic Details From BLA cum Subscription", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "",
                    "OTHER SCHEMES INTEREST RELATED INFO", "", "", "", "", "", "", "","","","",
                    "PAN STATUS", "", "","","","",
                    "TDS EXEMPTION DETAILS", "", "", "", "", "", "",
                    "FRSB INTEREST PAYOUT DETAILS", ""
            };

            CellStyle headerCellStyle1 = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font1 = workbook.createFont();
            font1.setBold(true);
            headerCellStyle1.setFont(font1);

            // Track maximum column widths
            int[] maxColumnWidths1 = new int[mainHeaders.length];
            for (int i = 0; i < mainHeaders.length; i++) {
                SXSSFCell cell = headerRow1.createCell(i);
                cell.setCellValue(mainHeaders[i]);
                cell.setCellStyle(headerCellStyle1);
                maxColumnWidths1[i] = mainHeaders[i].length();
            }

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(4);
            String[] headers = {
                    "Sl.No", "ModeOfPayment", "BlaNo",
                    "ApplName1", "ApplName2", "ApplName3", "Addr1", "Addr2",
                    "RecName", "RecParentName", "PinCode", "PhoneNo", "ApplStatus",
                    "InvestmentAmount", "AccountNo", "SubscriptionCount",
                    "BankSortCode", "BankName", "PanNo", "InterestPayout8Percent",
                    "NonCumulativeInt8", "CumulativeInt8", "InterestPayout7.75Percent",
                    "NonCumulativeInt7.75", "CumulativeInt7.75",
                    "InterestPayout8.05Percent","NonCumulativeInt8.05", "CumulativeInt8.05",
                    "TotalInterestPayout","InOperativePan", "NonFilers", "Age", "TaxableLimit",
                    "WaiverDocument", "WaiverDocumentRefNo", "RrbTdsRate",
                    "ThresholdExceedAmount", "ThresholdExceedTdsAmount", "ExemptionRate",
                    "ExemptionAmount", "ExemptionTdsAmount", "SurchargePercent", "EduCessPercent",
                    "BaseRateAmt", "SurchargeAmt", "EduCessAmt", "TdsAmount",
                    "TdsRecoveryAmount", "TdsRefundAmount", "IntRate", "LastIntPaid",
                    "PendingInterest", "CurrentHalfYearlyInterest", "FutureInterest",
                    "GrossInterestAmount", "IntPaidNet"
            };


            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
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

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<RbiBondCentralizedInterestTrailRunDownloadDto>) finalData.get("gstAckFileDownloadDTOList");

            // Populate data rows
            int rowNumber = 5;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (RbiBondCentralizedInterestTrailRunDownloadDto data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getModeOfPayment() != null ? data.getModeOfPayment() : "",
//                            data.getBranchRefNo() != null ? data.getBranchRefNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
//                            data.getRefNo() != null ? data.getRefNo() : "",
                            data.getApplName1() != null ? data.getApplName1() : "",
                            data.getApplName2() != null ? data.getApplName2() : "",
                            data.getApplName3() != null ? data.getApplName3() : "",
                            data.getAddr1() != null ? data.getAddr1() : "",
                            data.getAddr2() != null ? data.getAddr2() : "",
                            data.getRecName() != null ? data.getRecName() : "",
                            data.getRecParentName() != null ? data.getRecParentName() : "",
                            data.getPinCode() != null ? data.getPinCode() : "",
                            data.getPhoneNo() != null ? data.getPhoneNo() : "",
                            data.getApplStatus() != null ? data.getApplStatus() : "",
                            data.getInvestmentAmt() != null ? data.getInvestmentAmt() : "",
//                            data.getCfd() != null ? data.getCfd() : "",
//                            data.getMatDate() != null ? data.getMatDate() : "",
                            data.getAccountNo() != null ? data.getAccountNo() : "",
                            data.getSubscriptionCount() != null ? data.getSubscriptionCount() : "",
                            data.getBankSortCode() != null ? data.getBankSortCode() : "",
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getInterestPayout8Percent() != null ? data.getInterestPayout8Percent() : "",
                            data.getNonCumulativeInt8() != null ? data.getNonCumulativeInt8() : "",
                            data.getCumulativeInt8() != null ? data.getCumulativeInt8() : "",
                            data.getInterestPayout775Percent() != null ? data.getInterestPayout775Percent() : "",
                            data.getNonCumulativeInt775() != null ? data.getNonCumulativeInt775() : "",
                            data.getCumulativeInt775() != null ? data.getCumulativeInt775() : "",
                            data.getInterestPayout805Percent() != null ? data.getInterestPayout805Percent() : "",
                            data.getNonCumulativeInt805() != null ? data.getNonCumulativeInt805() : "",
                            data.getCumulativeInt805() != null ? data.getCumulativeInt805() : "",
                            data.getTotalInterestPayout() != null ? data.getTotalInterestPayout() : "",
                            data.getInOperativePan() != null ? data.getInOperativePan() : "",
                            data.getNonFilers() != null ? data.getNonFilers() : "",
                            data.getAge() != null ? data.getAge() : "",
                            data.getTaxableLimit() != null ? data.getTaxableLimit() : "",
                            data.getWaiverDocument() != null ? data.getWaiverDocument() : "",
                            data.getWaiverDocumentRefNo() != null ? data.getWaiverDocumentRefNo() : "",
                            data.getRrbTdsRate() != null ? data.getRrbTdsRate() : "",
                            data.getThresholdExceedAmount() != null ? data.getThresholdExceedAmount() : "",
                            data.getThresholdExceedTdsAmount() != null ? data.getThresholdExceedTdsAmount() : "",
                            data.getExemptionRate() != null ? data.getExemptionRate() : "",
                            data.getExemptionAmount() != null ? data.getExemptionAmount() : "",
                            data.getExemptionTdsAmount() != null ? data.getExemptionTdsAmount() : "",
                            data.getSurchargePercent() != null ? data.getSurchargePercent() : "",
                            data.getEduCessPercent() != null ? data.getEduCessPercent() : "",
                            data.getBaseRateAmt() != null ? data.getBaseRateAmt() : "",
                            data.getSurchargeAmt() != null ? data.getSurchargeAmt() : "",
                            data.getEduCessAmt() != null ? data.getEduCessAmt() : "",
                            data.getTdsAmount() != null ? data.getTdsAmount() : "",
                            data.getTdsRecoveryAmount() != null ? data.getTdsRecoveryAmount() : "",
                            data.getTdsRefundAmount() != null ? data.getTdsRefundAmount() : "",
                            data.getIntRate() != null ? data.getIntRate() : "",
                            data.getLastIntPaid() != null ? data.getLastIntPaid() : "",
                            data.getPendingInterest() != null ? data.getPendingInterest() : "",
                            data.getCurrentHalfYearlyInterest() != null ? data.getCurrentHalfYearlyInterest() : "",
                            data.getFutureInterest() != null ? data.getFutureInterest() : "",
                            data.getGrossInterestAmount() != null ? data.getGrossInterestAmount() : "",
                            data.getIntPaidNet() != null ? data.getIntPaidNet() : "",
//                            data.getPaymentDate() != null ? data.getPaymentDate() : "",
//                            data.getTotalAmountToBePaid() != null ? data.getTotalAmountToBePaid() : ""
                    };

                    //log.info("cellValues.length: "+cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            }
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("CENTRALIZED TRAIL RUN MERGED DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO verifySubmit(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunDao.verifySubmit(rbiBondCentralizedInterestTrailRunDto);

    }

    @Override
    public ResponseDataDTO delete(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunDao.delete(rbiBondCentralizedInterestTrailRunDto);
    }

    public byte[] excelFileGeneratorReport(List<RbiBondCentralizedInterestTrailRunDownloadDto> downloadResponseDTOList , RbiBondCentralizedInterestTrailRunDtoRequest downloadDto) throws IOException {


        XSSFWorkbook workbook = new XSSFWorkbook();
        final String sheet1="sheet1";
        XSSFSheet sheet = workbook.createSheet(sheet1);

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

//            CellStyle centerStyle = workbook.createCellStyle();
//            centerStyle.setAlignment(HorizontalAlignment.CENTER);

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row,6, "Trial run for Brokerage", style);


        row = sheet.createRow(1);
        createCell(row, 6, "Branch Id :",style);
        createCell(row,7, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(2);
        createCell(row, 6, "Interest for the Half Year :",style);
        createCell(row,7, downloadDto.getInterestForHalfYear());

        row = sheet.createRow(3);
        createCell(row, 6, "Year :",style);
        createCell(row,7, downloadDto.getYear());


        int n = 9;
        if (downloadResponseDTOList.size() > 0) {
            // Define header styles
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont1 = workbook.createFont();
            headerFont1.setBold(true);
            headerStyle.setFont(headerFont1);

            // Add the main header row
            Row mainHeaderRow = sheet.createRow(0);
            createCell(mainHeaderRow, 0, "Basic Details From BLA cum Subscription", headerStyle);
            createCell(mainHeaderRow, 22, "OTHER SCHEMES INTEREST RELATED INFO", headerStyle);
            createCell(mainHeaderRow, 30, "PAN STATUS", headerStyle);
            createCell(mainHeaderRow, 33, "TDS EXEMPTION DETAILS", headerStyle);
            createCell(mainHeaderRow, 41, "FRSB INTEREST PAYOUT DETAILS", headerStyle);

            // Add sub-header row
            Row subHeaderRow = sheet.createRow(1);
            String[] subHeaders = {
                    "Sl.No", "Mode of Payment", "Branch Ref No", "Bla_no", "Ref No",
                    "Appl_Name1", "Appl_Name2", "Appl_Name3", "Addr1", "Addr2",
                    "Rec_Name", "Rec_Parent_Name", "Pin_Code", "Phone_no", "appl_status",
                    "Investment Amt", "CFD", "Mat Date", "Account_No", "Subscription Count",
                    "BankSortCode", "Bank_name", "Pan_No", "8% interest payout",
                    "Non Cumulative Int", "Cumulative Int", "7.75% interest payout",
                    "Non Cumulative Int 775", "Cumulative Int 775", "Total",
                    "In-operative PAN", "Non-filers", "Age", "Taxable Limit",
                    "Waiver Document", "Waiver Document Ref No.", "RRB-TDS rate",
                    "Threshold_Exceed_Amount", "Threshold_Exceed_TDS_Amount", "Exemption Rate %",
                    "Exemption_Amount", "Exemption_TDS_Amount", "Surcharge %", "Edu Cess %",
                    "Base Rate Amt", "Surcharge Amt", "Edu Cess Amt", "TDS Amount",
                    "TDS Recovery Amount", "TDS Refund Amount", "Int_Rate", "Last Int Paid",
                    "Pending Interest", "Current Half Yearly Interest", "Future Interest",
                    "Gross Interest Amount", "Int Paid (Net Interest Amount)"
            };
            for (int i = 0; i < subHeaders.length; i++) {
                createCell(subHeaderRow, i, subHeaders[i], headerStyle);
            }

            // Populate data rows based on DTO
            int rowIndex = 2;
            int index = 1;
            for (RbiBondCentralizedInterestTrailRunDownloadDto item : downloadResponseDTOList) {
                Row dataRow = sheet.createRow(rowIndex++);

                createCell(dataRow, 0, String.valueOf(index++));
                createCell(dataRow, 1, item.getModeOfPayment());
                createCell(dataRow, 2, item.getBranchRefNo());
                createCell(dataRow, 3, item.getBlaNo());
                createCell(dataRow, 4, item.getRefNo());
                createCell(dataRow, 5, item.getApplName1());
                createCell(dataRow, 6, item.getApplName2());
                createCell(dataRow, 7, item.getApplName3());
                createCell(dataRow, 8, item.getAddr1());
                createCell(dataRow, 9, item.getAddr2());
                createCell(dataRow, 10, item.getRecName());
                createCell(dataRow, 11, item.getRecParentName());
                createCell(dataRow, 12, item.getPinCode());
                createCell(dataRow, 13, item.getPhoneNo());
                createCell(dataRow, 14, item.getApplStatus());
                createCell(dataRow, 15, item.getInvestmentAmt());
                createCell(dataRow, 16, item.getCfd());
                createCell(dataRow, 17, item.getMatDate());
                createCell(dataRow, 18, item.getAccountNo());
                createCell(dataRow, 19, item.getSubscriptionCount());
                createCell(dataRow, 20, item.getBankSortCode());
                createCell(dataRow, 21, item.getBankName());
                createCell(dataRow, 22, item.getPanNo());
                createCell(dataRow, 23, item.getInterestPayout8Percent());
                createCell(dataRow, 24, item.getNonCumulativeInt8());
                createCell(dataRow, 25, item.getCumulativeInt8());
                createCell(dataRow, 26, item.getInterestPayout8Percent());
                createCell(dataRow, 27, item.getNonCumulativeInt775());
                createCell(dataRow, 28, item.getCumulativeInt775());
                createCell(dataRow, 29, item.getTotalInterestPayout());
                createCell(dataRow, 30, item.getInOperativePan());
                createCell(dataRow, 31, item.getNonFilers());
                createCell(dataRow, 32, item.getAge());
                createCell(dataRow, 33, item.getTaxableLimit());
                createCell(dataRow, 34, item.getWaiverDocument());
                createCell(dataRow, 35, item.getWaiverDocumentRefNo());
                createCell(dataRow, 36, item.getRrbTdsRate());
                createCell(dataRow, 37, item.getThresholdExceedAmount());
                createCell(dataRow, 38, item.getThresholdExceedTdsAmount());
                createCell(dataRow, 39, item.getExemptionRate());
                createCell(dataRow, 40, item.getExemptionAmount());
                createCell(dataRow, 41, item.getExemptionTdsAmount());
                createCell(dataRow, 42, item.getSurchargePercent());
                createCell(dataRow, 43, item.getEduCessPercent());
                createCell(dataRow, 44, item.getBaseRateAmt());
                createCell(dataRow, 45, item.getSurchargeAmt());
                createCell(dataRow, 46, item.getEduCessAmt());
                createCell(dataRow, 47, item.getTdsAmount());
                createCell(dataRow, 48, item.getTdsRecoveryAmount());
                createCell(dataRow, 49, item.getTdsRefundAmount());
                createCell(dataRow, 50, item.getIntRate());
                createCell(dataRow, 51, item.getLastIntPaid());
                createCell(dataRow, 52, item.getPendingInterest());
                createCell(dataRow, 53, item.getCurrentHalfYearlyInterest());
                createCell(dataRow, 54, item.getFutureInterest());
                createCell(dataRow, 55, item.getGrossInterestAmount());
                createCell(dataRow, 56, item.getTotalInterestPayout());
            }

            // Auto-size columns based on content
            for (int i = 0; i < subHeaders.length; i++) {
                sheet.autoSizeColumn(i);
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        System.out.println(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }
    public ResponseDataDTO cbsAccValidation(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest){
        ResponseDataDTO cbsAccValditionReq = new ResponseDataDTO();
        ResponseDataDTO cbsAccValditionRes = new ResponseDataDTO();

        cbsAccValditionReq = rbiBondCentralizedInterestTrailRunDao.cbsAccValidation(rbiBondCentralizedInterestTrailRunDtoRequest);
            log.info("cbs acc validations started");
        if (cbsAccValditionReq.getMessage().equalsIgnoreCase("success")){
            if (CommonConstants.SUCCESS_CONST.equals(cbsAccValditionReq.getMessage())) {
                List<AccountValidationDto> accountValidationDtoList = (List<AccountValidationDto>) cbsAccValditionReq.getResult();
                if (!CollectionUtils.isEmpty(accountValidationDtoList)) {
                    String finacleValidationReqString = accountValidationDtoList.get(0).getAccountName() + "^" + accountValidationDtoList.get(0).getAccNo() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + accountValidationDtoList.get(0).getIsCentralized() + (CommonConstants.NO_STATUS.equalsIgnoreCase(accountValidationDtoList.get(0).getIsCentralized()) ? ("~" + accountValidationDtoList.get(0).getBranchId()) : (""));
                    AccountValidationDto tempDto;
                    for (int i = 1; i < accountValidationDtoList.size(); i++) {
                        tempDto = accountValidationDtoList.get(i);
                        finacleValidationReqString += "!" + tempDto.getAccountName() + "^" + tempDto.getAccNo() +"~"+ CommonConstants.TRAN_LEG_CREDIT + "~" + tempDto.getIsCentralized() + (CommonConstants.NO_STATUS.equalsIgnoreCase(tempDto.getIsCentralized()) ? ("~" + tempDto.getBranchId()) : (""));
                    }

                    ValidatorWrapperDTO validatorWrapperDTO = transactionValidator.validateBacidForAcidDetails(finacleValidationReqString);
                    if (CommonConstants.SUCCESS.equalsIgnoreCase(validatorWrapperDTO.getStatus())) {
                        List<TranDetailsDTO> transactionList = validatorWrapperDTO.getTranDetailsDTOList();
                        Map<String, TranDetailsDTO> cbsResponseMap = new HashMap<>();
                        for (TranDetailsDTO dto : transactionList) {
                            if (CommonConstants.SUCCESS.equalsIgnoreCase(dto.getValidityStatus())) {
                                String[] accNo = dto.getAccountNumber().split("\\^");
                                dto.setAccountNumber(accNo[1]);
                                cbsResponseMap.put(accNo[0], dto);
                            } else {
                                if (dto.getAccountNumber().startsWith(String.valueOf(CommonConstants.TRAN_LEG_DEBIT)))
                                    throw new GenericBusinessException(dto.getReason());
                            }
                        }
                        for (AccountValidationDto accountValidationDto : accountValidationDtoList) {
                            if (cbsResponseMap.containsKey(accountValidationDto.getAccountName())) {
                                accountValidationDto.setCbsAccNo(cbsResponseMap.get(accountValidationDto.getAccountName()).getAccountNumber());
                                accountValidationDto.setCbsAccType(cbsResponseMap.get(accountValidationDto.getAccountName()).getIsSysAcct());
                            } else {
                                throw new GenericBusinessException("CBS ACCOUNT ID NOT FOUND IN FINACLE RESPONSE");
                            }
                        }

                        rbiBondCentralizedInterestTrailRunDtoRequest.setTypeIn(CommonConstants.PROC_TYPE_IN_RESPONSE);
                        rbiBondCentralizedInterestTrailRunDtoRequest.setCbsAcctDet(accountValidationDtoList);

                        rbiBondCentralizedInterestTrailRunDao.cbsAccValidation(rbiBondCentralizedInterestTrailRunDtoRequest);
                    } else {
                        throw new GenericBusinessException(validatorWrapperDTO.getReason());
                    }
                    rbiBondCentralizedInterestTrailRunDtoRequest.setCbsAcctDet(accountValidationDtoList);
                    cbsAccValditionRes= rbiBondCentralizedInterestTrailRunDao.cbsAccValidation(rbiBondCentralizedInterestTrailRunDtoRequest);
                    log.info("cbs acc validations completed"+" messgae :"+cbsAccValditionRes.getMessage());
                }
            } else {
                throw new GenericBusinessException(cbsAccValditionRes.getMessage());
            }
            return cbsAccValditionRes;
        }
        return cbsAccValditionRes;
    }



     /*   public byte[] excelFileGeneratorForInterestRunAsciiReport(RbiBondCentralizedInterestTrailRunDtoRequest bondAsciiInterestRunCreditDTO) throws IOException {
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("sheet1");

            CellStyle style = workbook.createCellStyle();
            XSSFFont headerFont = workbook.createFont();

            headerFont.setBold(true);
            headerFont.setFontHeight(14);
            style.setFont(headerFont);

            Row row = sheet.createRow(0);
            createCell(row,0, "ASCII file for Bond Interest Payment Report", style);

            row = sheet.createRow(1);
            createCell(row,0, bankName);


            row = sheet.createRow(2);
            createCell(row,0, UserAccountUtils.getCurrentSolId());

            row = sheet.createRow(3);
            createCell(row,0, bondAsciiInterestRunCreditDTO.getBranchName());


            row = sheet.createRow(4);
            createCell(row, 0, this.getSchemeName(bondAsciiInterestRunCreditDTO.getSchemeCode()));

            row = sheet.createRow(5);
            createCell(row, 0, "ASCII file for Bond Interest Payment Report",style);


            row = sheet.createRow(6);
            createCell(row, 0,"for Period from:"+  bondAsciiInterestRunCreditDTO.getInterestPayFrom() + "\t" + "to: " + bondAsciiInterestRunCreditDTO.getInterestPayTo(),style);





            row=sheet.createRow(7);
            createCell(row, 0, "S.No",style);
            createCell(row, 1, "Branch Id",style);
            createCell(row, 2, "BOND Ledger Account Number",style);
            createCell(row, 3, "Customer Name",style);
            createCell(row, 4, "Address",style);
            createCell(row, 5, "Interest From Period",style);
            createCell(row, 6, "Interest To Period",style);
            createCell(row, 7, "Interest paid Date",style);
            createCell(row, 8, "Total Interest Rs.",style);



            int n = 7;
            int index = 1;

            double totIntAmt = 0;
            double totTds = 0;
            double totNet = 0;

            for(Map<String, Object> item : bondAsciiInterestRunCreditDTO.getData())
            {

                System.out.println("item value for xslx"+item.toString());
                String totIntAmtS = (String)  item.get("Gross_Interest_Amount");

                totIntAmt =  totIntAmtS == null ? 0 : Double.parseDouble(totIntAmtS) + totIntAmt;


                row=sheet.createRow(++n);

                createCell(row, 0, String.valueOf(index++));
                createCell(row, 1, (String) item.get("BRANCH_ID"));
                createCell(row, 2, (String) item.get("BLA_NO"));
                createCell(row, 3, (String) item.get("CUSTOMER_NAME"));
                createCell(row, 4, (String) item.get("ADDRESS"));
                createCell(row, 5, (String) item.get("INTEREST_PERIOD_FROM"));
                createCell(row, 6, (String) item.get("INTERST_PERIOD_TO"));
                createCell(row, 7, (String) item.get("INTREST_DATE"));
                createCell(row, 8, (String) String.valueOf(item.get("Gross_Interest_Amount")));





            }

            row = sheet.createRow(++n);
            createCell(row, 0, "Total ",style);
            createCell(row,8, String.valueOf(totIntAmt));

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);



            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);

            System.out.println(" excel file created successfully");
            workbook.close();

            return byteArrayOutputStream.toByteArray();
        }
*/
/*

    public byte[] pdfFileGeneratorForInterestRunAsciiReport(RbiBondCentralizedInterestTrailRunDtoRequest bondAsciiInterestRunCreditDTO) throws DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        double totIntAmt = 0;
        double totTds = 0;
//        Double totArr = 0d;
        double totNet = 0;

        Document document = new Document(PageSize.A1.rotate(), 20f, 20f, 20f, 0f);
        //  document.setPageSize(PageSize.A4.rotate());
        PdfWriter.getInstance(document, baos);


        document.open();

        Paragraph header = new Paragraph("ASCII file for Bond Interest Payment Report");
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);

        document.add(new Paragraph("\n\n"));


        // Adding a table to the PDF
        PdfPTable table = new PdfPTable(2);
        table.addCell(bankName);

        table.addCell(UserAccountUtils.getCurrentSolId());
        table.addCell(bondAsciiInterestRunCreditDTO.getBranchName());
        table.addCell(this.getSchemeName(bondAsciiInterestRunCreditDTO.getSchemeCode()));
        table.addCell("ASCII file for Bond Interest Payment Report");
        table.addCell("for Period from:"+bondAsciiInterestRunCreditDTO.getInterestPayFrom()+"to:"+bondAsciiInterestRunCreditDTO.getInterestPayTo());


        document.add(table);

        PdfPTable table1 = new PdfPTable(16);
        table1.addCell("S.No");
        table1.addCell("Branch Id");
        table1.addCell("BOND Ledger Account Number");
        table1.addCell("Customer Name");
        table1.addCell("BOND Subscription ID");
        table1.addCell("Subscription Date");
        table1.addCell("Subscription Amount");
        table1.addCell("Interest From Period");
        table1.addCell("Interest To Period");
        table1.addCell("Interest payment mode");
        table1.addCell("Credit Account Number");
        table1.addCell("Total Interest Rs.");
        table1.addCell("TDS Amount Rs.");
        table1.addCell("Net Interest Amount Rs.");
        table1.addCell("CBS Tran ID");
        table1.addCell("GBM Tran ID");


        int index = 1;
        for(Map<String, Object> item : bondAsciiInterestRunCreditDTO.getData())
        {
            String totIntAmtS = (String)  item.get("Gross_Interest_Amount");
            String totTdsS = (String)  item.get("TDS_AMT");
            String totNetS = (String)  item.get("NET_INTEREST_AMOUNT");
            totIntAmt = totIntAmtS == null ? 0 : Double.parseDouble(totIntAmtS) + totIntAmt;
            totTds = totTdsS == null ? 0 : Double.parseDouble(totTdsS) + totTds;
            totNet = totNetS == null ? 0 : Double.parseDouble(totNetS) + totNet;

            table1.addCell( String.valueOf(index++));
            table1.addCell( (String) item.get("BRANCH_ID"));
            table1.addCell( (String) item.get("BLA_NO"));
            table1.addCell((String) item.get("CUSTOMER_NAME"));
            table1.addCell( (String) item.get("BOND_SUBSCRIPTION_ID"));
            table1.addCell( (String) item.get("SUBSCRIPTION_DATE"));
            table1.addCell( (String) String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            table1.addCell( (String) item.get("INTEREST_PERIOD_FROM"));
            table1.addCell( (String) item.get("INTERST_PERIOD_TO"));
            table1.addCell( (String) item.get("MODE_OF_INTEREST_PAYMENT"));
            table1.addCell( (String) item.get("CREDIT_ACCOUNT_NUMBER"));
            table1.addCell( (String) String.valueOf(item.get("Gross_Interest_Amount")));
            table1.addCell( (String) String.valueOf(item.get("TDS_AMT")));
            table1.addCell( (String) String.valueOf(item.get("NET_INTEREST_AMOUNT")));
            table1.addCell( (String) item.get("CBS_Tran_Id"));
            table1.addCell( (String) item.get("GBM_TRAN_ID"));


        }

        document.add(table1);

        PdfPTable tot = new PdfPTable(16);
        tot.addCell("Total");
        tot.addCell( "");
        tot.addCell( "");
        tot.addCell( "");
        tot.addCell( "");
        tot.addCell( "");
        tot.addCell( "");
        tot.addCell( "");
        tot.addCell( "");
        tot.addCell( "");
        tot.addCell( "");
        tot.addCell( String.valueOf(totIntAmt));
        tot.addCell( String.valueOf(totTds));
        tot.addCell( String.valueOf(totNet));
        tot.addCell( "");
        tot.addCell( "");
        document.add(tot);

        System.out.println(" pdf file created successfully");


        document.close();


        return baos.toByteArray();
    }

    public byte[] csvFileGeneratorForInterestRunAsciiReport(RbiBondCentralizedInterestTrailRunDtoRequest bondAsciiInterestRunCreditDTO) throws IOException {
        StringBuilder csvContent = new StringBuilder();
        System.out.println("inside csv 1");
        // Header Section
        csvContent.append("ASCII file for Bond Interest Payment Report\n");
        csvContent.append(bankName).append("\n");
        csvContent.append(UserAccountUtils.getCurrentSolId()).append("\n");
        csvContent.append(bondAsciiInterestRunCreditDTO.getBranchName()).append("\n");
        csvContent.append(this.getSchemeName(bondAsciiInterestRunCreditDTO.getSchemeCode())).append("\n");
        csvContent.append("ASCII file for Bond Interest Payment Report\n");
        csvContent.append("for Period from:").append(bondAsciiInterestRunCreditDTO.getInterestPayFrom())
                .append("\t").append("to: ").append(bondAsciiInterestRunCreditDTO.getInterestPayTo()).append("\n");

        // Column Headers
        */
/*csvContent.append("S.No,Branch Id,BOND Ledger Account Number,Customer Name,BOND Subscription ID,")
                .append("Subscription Date,Subscription Amount,Interest From Period,Interest To Period,")
                .append("Interest payment mode,Credit Account Number,Total Interest Rs.,TDS Amount Rs.,")
                .append("Net Interest Amount Rs.,CBS Tran ID,GBM Tran ID\n");*//*


        csvContent.append("S.No,Branch Id,BOND Ledger Account Number,Customer Name,Address,")
                .append("Interest From Date,Interest To Date,Interest payment Date,Total Interest Rs.\n");

        int index = 1;
        double totIntAmt = 0;
        double totTds = 0;
        double totNet = 0;
        System.out.println("inside csv 2");
        // Data Rows
        for (Map<String, Object> item : bondAsciiInterestRunCreditDTO.getData()) {
            String totIntAmtS = (String) item.get("Gross_Interest_Amount");
            String totTdsS = (String) item.get("TDS_AMT");
            String totNetS = (String) item.get("NET_INTEREST_AMOUNT");

            totIntAmt = totIntAmtS == null ? 0 : Double.parseDouble(totIntAmtS) + totIntAmt;
            totTds = totTdsS == null ? 0 : Double.parseDouble(totTdsS) + totTds;
            totNet = totNetS == null ? 0 : Double.parseDouble(totNetS) + totNet;

            csvContent.append(index++).append(',')
                    .append(item.get("BRANCH_ID")).append(',')
                    .append(item.get("BLA_NO")).append(',')
                    .append(item.get("CUSTOMER_NAME")).append(',')
                    //.append(item.get("BOND_SUBSCRIPTION_ID")).append(',')
                    //  .append(item.get("SUBSCRIPTION_DATE")).append(',')
                    // .append(item.get("SUBSCRIPTION_AMOUNT")).append(',')
                    .append(item.get("ADDRESS")).append(',')
                    .append(item.get("INTEREST_PERIOD_FROM")).append(',')
                    .append(item.get("INTERST_PERIOD_TO")).append(',')
                    .append(item.get("INTERST_DATE")).append(',')
                    // .append(item.get("MODE_OF_INTEREST_PAYMENT")).append(',')
                    //.append(item.get("CREDIT_ACCOUNT_NUMBER")).append(',')
                    .append(item.get("Gross_Interest_Amount"))//.append(',')
                    // .append(item.get("TDS_AMT")).append(',')
                    //  .append(item.get("NET_INTEREST_AMOUNT")).append(',')
                    //  .append(item.get("CBS_Tran_Id")).append(',')
                    //  .append(item.get("GBM_TRAN_ID")).append('\n');
                    .append('\n');
        }

        // Totals
        csvContent.append("Total,,")
                .append(",,,,,,,,,,,") // Skipping the columns without totals
                .append(totIntAmt).append('\n');
        // .append(totTds).append(',')
        // .append(totNet).append('\n');

        // Convert StringBuilder to byte array
        byte[] csvBytes = csvContent.toString().getBytes(StandardCharsets.UTF_8);

        System.out.println("CSV file created successfully");

        return csvBytes;
    }
*/


    @Override
    public ResponseDataDTO interestPaymentStatus(RbiBondCentralizedInterestTrailRunDtoRequest requestDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        try {
            responseDataDTO.setResult(rbiBondCentralizedInterestTrailRunDao.interestPaymentStatus(requestDto));
        } catch (Exception e) {
            log.error("Exception : ", e);
            responseDataDTO.setMessage(e.getMessage());
        }
        return responseDataDTO;
    }

//
//@EventListener
//public void RbiBulkPaymentEvent(RbiBulkPaymentEvent paymentInitiatedEvent) throws Exception {
//    if (paymentInitiatedEvent.getEventType().equals(CommonConstants.EVENT_TYPE_VALIDATION)) {
//
//
//        JobParameters rbiBondInterestPaymentJobParameters = new JobParametersBuilder()
//                .addString(CommonConstants.PROCESS_ID_STRING, paymentInitiatedEvent.getProcessId())
//                .addString(CommonConstants.CENTRALIZED_FLAG_STRING, paymentInitiatedEvent.getCentralizedFlag())
//                .addString(CommonConstants.BRANCH_ID_STRING, paymentInitiatedEvent.getBranchId())
//                .addString("bondSubId", paymentInitiatedEvent.getBondSubId())
//                .addString("blaNo", paymentInitiatedEvent.getBlaNum())
//                .addString("interestForHalfYear" ,paymentInitiatedEvent.getInterestForHalfYear())
//                .addString("year" ,paymentInitiatedEvent.getYear())
//                .addString(CommonConstants.AVAILABLE_VAL_BATCH_STRING, new ObjectMapper().writeValueAsString(paymentInitiatedEvent.getValidationBatchList()))
//                .toJobParameters();
//        this.jobLauncher.run(this.rbiBondInterestPayementJob ,rbiBondInterestPaymentJobParameters);
//}
//    }

    @EventListener(condition = "#rbiBulkPaymentEvent.eventType == @CommonConstants.EVENT_TYPE_VALIDATION")
    public void postTransactionForRbiBondInterestPayment(RbiBulkPaymentEvent rbiBulkPaymentEvent) throws Exception {
        JobParameters rbiBondInterestPaymentJobParameters = new JobParametersBuilder()
                .addString(CommonConstants.PROCESS_ID_STRING, rbiBulkPaymentEvent.getProcessId())
                .addString(CommonConstants.CENTRALIZED_FLAG_STRING, rbiBulkPaymentEvent.getCentralizedFlag())
                .addString(CommonConstants.BRANCH_ID_STRING, rbiBulkPaymentEvent.getBranchId())
                .addString("bondSubId", rbiBulkPaymentEvent.getBondSubId())
                .addString("blaNo", rbiBulkPaymentEvent.getBlaNum())
                .addString("interestForHalfYear" ,rbiBulkPaymentEvent.getInterestForHalfYear())
                .addString("year" ,rbiBulkPaymentEvent.getYear())
                .addString("moduleId", rbiBulkPaymentEvent.getModuleId())
                .addString(CommonConstants.AVAILABLE_VAL_BATCH_STRING, new ObjectMapper().writeValueAsString(rbiBulkPaymentEvent.getValidationBatchList()))
                .toJobParameters();
        this.jobLauncher.run(this.rbiBondInterestPayementJob ,rbiBondInterestPaymentJobParameters);
    }

    @EventListener(condition = "#rbiBulkPaymentEvent.eventType == @CommonConstants.EVENT_TYPE_TRANSACTION")
    public void postTransactionForRbiBulkInterestPaymentDirectTransaction(RbiBulkPaymentEvent rbiBulkPaymentEvent) throws Exception {
        JobParameters rbiBondInterestPaymentJobParametersDirectTransaction = new JobParametersBuilder()
                .addString(CommonConstants.PROCESS_ID_STRING, rbiBulkPaymentEvent.getProcessId())
                .addString(CommonConstants.CENTRALIZED_FLAG_STRING, rbiBulkPaymentEvent.getCentralizedFlag())
                .addString(CommonConstants.BRANCH_ID_STRING, rbiBulkPaymentEvent.getBranchId())
                .addString("bondSubId", rbiBulkPaymentEvent.getBondSubId())
                .addString("blaNo", rbiBulkPaymentEvent.getBlaNum())
                .addString("interestForHalfYear" ,rbiBulkPaymentEvent.getInterestForHalfYear())
                .addString("year" ,rbiBulkPaymentEvent.getYear())
                .addString("moduleId", rbiBulkPaymentEvent.getModuleId())
                .addString(CommonConstants.AVAILABLE_VAL_BATCH_STRING, new ObjectMapper().writeValueAsString(rbiBulkPaymentEvent.getValidationBatchList()))
                .toJobParameters();
        this.jobLauncher.run(this.rbiBondInterestPayementJobForDirectTran, rbiBondInterestPaymentJobParametersDirectTransaction);
    }
}
