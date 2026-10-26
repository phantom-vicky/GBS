package com.isl.gbm.rbibond.service.impl;


import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondTransferInBo;
import com.isl.gbm.rbibond.dto.CustomerData;
import com.isl.gbm.rbibond.dto.RbiBondTransferInDTO;
import com.isl.gbm.rbibond.service.RbiBondTransferInService;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.Locale;

@Service
public class RbiBondTransferInServiceImpl implements RbiBondTransferInService {

    private static final DateTimeFormatter DATE_FMT = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("dd-MMM-uuuu")
            .toFormatter(Locale.ENGLISH)
            .withResolverStyle(ResolverStyle.SMART);


    @Autowired
    RbiBondTransferInBo rbiBondTransferInBo;
    @Override
    public ResponseDataDTO getblaNoInq(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInBo.getblaNoInq(rbiBondTransferInDTO);
    }
    @Override
    public ResponseDataDTO getsubscriptionIdInq(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInBo.getsubscriptionIdInq(rbiBondTransferInDTO);
    }

    @Override
    public ResponseDataDTO panTorch(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInBo.panTorch(rbiBondTransferInDTO);
    }

    @Override
    public ResponseDataDTO addTorch(RbiBondTransferInDTO rbiBondTransferInDTO) {

        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != rbiBondTransferInDTO && null != rbiBondTransferInDTO.getPanNum1() && !rbiBondTransferInDTO.getPanNum1().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondTransferInDTO.getPanNum1());
            }
            if (null != rbiBondTransferInDTO && null != rbiBondTransferInDTO.getPanNum2() && !rbiBondTransferInDTO.getPanNum2().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondTransferInDTO.getPanNum2());
            }
            if (null != rbiBondTransferInDTO && null != rbiBondTransferInDTO.getPanNum3() && !rbiBondTransferInDTO.getPanNum3().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondTransferInDTO.getPanNum3());
            }


        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondTransferInBo.addTorch(rbiBondTransferInDTO);

    }



    public ResponseDataDTO  otherBankDetails() throws SQLException {
        return rbiBondTransferInBo.otherBankDetails();

    }

    @Override
    public ResponseDataDTO bondTransferInAdd(RbiBondTransferInDTO rbiBondTransferInAddDto) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            if (rbiBondTransferInAddDto.getRateOfInterest() != null && !rbiBondTransferInAddDto.getRateOfInterest().trim().isEmpty()) {
                if (Double.parseDouble(rbiBondTransferInAddDto.getRateOfInterest().trim()) < 0) {
                    responseDataDTO.setMessage("Rate of Interest must not be negative");
                    responseDataDTO.setStatusCode("0");
                    return responseDataDTO;
                }
            }
            if (rbiBondTransferInAddDto.getDataList() != null) {
                for (CustomerData customer : rbiBondTransferInAddDto.getDataList()) {
                    if (null != customer.getPanNo() && !customer.getPanNo().equalsIgnoreCase("")) {
                        RbiBondServerSideRegexUtility.validatePAN(customer.getPanNo());
                    }
                    if (null != customer.getBondHldEmail() && !customer.getBondHldEmail().equalsIgnoreCase("")) {
                        RbiBondServerSideRegexUtility.validateEmail(customer.getBondHldEmail());
                    }
                    if (null != customer.getIfscCode() && !customer.getIfscCode().equalsIgnoreCase("")) {
                        RbiBondServerSideRegexUtility.validateIFSC(customer.getIfscCode());
                    }
                    if (null != customer.getBondHolderPhoneNumber() && !customer.getBondHolderPhoneNumber().equalsIgnoreCase("")) {
                        RbiBondServerSideRegexUtility.validateMobileNumber(customer.getBondHolderPhoneNumber());
                    }
                }
            }
            RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO1= (RbiBondTransferInDTO) rbiBondTransferInBo.addTorch(rbiBondTransferInAddDto).getResult();
            if(Integer.parseInt(rbiBondTransferInAddDto.getSubsAmt()) < Integer.parseInt(bondLedgerAccountMaintenanceDTO1.getMinCashLimit())){
                if(rbiBondTransferInAddDto.getTranMode().equalsIgnoreCase("C")) {
                    responseDataDTO.setMessage("Subscription Amount must be between " + bondLedgerAccountMaintenanceDTO1.getMinCashLimit() + " and " + bondLedgerAccountMaintenanceDTO1.getCashLimitForCash());
                }else {
                    responseDataDTO.setMessage("Subscription Amount must be between " + bondLedgerAccountMaintenanceDTO1.getMinCashLimit() + " and " + bondLedgerAccountMaintenanceDTO1.getCashLimitForClearingTransfer());
                }
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            if(Integer.parseInt(rbiBondTransferInAddDto.getSubsAmt()) % Integer.parseInt(bondLedgerAccountMaintenanceDTO1.getDepositMultiples()) !=0){
                responseDataDTO.setMessage("Subscription Amount must be multiples of " + bondLedgerAccountMaintenanceDTO1.getDepositMultiples());
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        if(!validateMaturityDate( rbiBondTransferInAddDto.getValueDate(), rbiBondTransferInAddDto.getMaturityDate(), rbiBondTransferInAddDto.getPeriodOfDeposit())){
            responseDataDTO.setMessage("Maturity Date is not valid");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondTransferInBo.bondTransferInAdd(rbiBondTransferInAddDto);

    }

    @Override
    public ResponseDataDTO verifySelect(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInBo.verifySelect(rbiBondTransferInDTO);
    }


    @Override
    public ResponseDataDTO modifySelect(RbiBondTransferInDTO rbiBondTransferInDTO) {

        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != rbiBondTransferInDTO && null != rbiBondTransferInDTO.getPanNum() && !rbiBondTransferInDTO.getPanNum().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondTransferInDTO.getPanNum());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondTransferInBo.modifySelect(rbiBondTransferInDTO);
    }

    @Override
    public ResponseDataDTO modifyPAN(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInBo.modifyPAN(rbiBondTransferInDTO);
    }
    @Override
    public ResponseDataDTO validatePan(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        return rbiBondTransferInBo.validatePan(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO inquiry(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInBo.inquiry(rbiBondTransferInDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInBo.delete(rbiBondTransferInDTO);
    }

    @Override
    public ResponseDataDTO modify(RbiBondTransferInDTO rbiBondTransferInDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();


            // Validate all fields
            for(int i=0;i<rbiBondTransferInDTO.getColumnValueDetailsList().size();i++) {
                if (null != rbiBondTransferInDTO && null != rbiBondTransferInDTO.getColumnValueDetailsList().get(i)) {
                    for (ColumnValueDTO columnValueDTO : rbiBondTransferInDTO.getColumnValueDetailsList().get(i)) {
                        String colName = columnValueDTO.getColName();
                        String value = columnValueDTO.getValue();
                        try {
                            if (null != colName && null != value && "C002".equals(colName)) {
                                RbiBondServerSideRegexUtility.validatePAN(value);
                            }
                            if (null != colName && null != value && "C025".equals(colName)) {
                                RbiBondServerSideRegexUtility.validateMobileNumber(value);
                            }
                            if (null != colName && null != value && "C026".equals(colName)) {
                                RbiBondServerSideRegexUtility.validateEmail(value);
                            }
                            if (null != colName && null != value && "C031".equals(colName)) {
                                RbiBondServerSideRegexUtility.validateIFSC(value);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            responseDataDTO.setMessage(e.getMessage());
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }
                    }
                }

                // Validate each customer in the list
                if (null != rbiBondTransferInDTO && null!=rbiBondTransferInDTO.getGuardianAddDetailsList()&&rbiBondTransferInDTO.getGuardianAddDetailsList().size()>0&&null != rbiBondTransferInDTO.getGuardianAddDetailsList().get(i)) {
                    for (ColumnValueDTO columnValueDTO : rbiBondTransferInDTO.getGuardianAddDetailsList().get(i)) {
                        String colName = columnValueDTO.getColName();
                        String value = columnValueDTO.getValue();
                        try {

                            if (null != colName && null != value && "C012".equals(colName)) {
                                RbiBondServerSideRegexUtility.validateMobileNumber(value);
                            }
                            if (null != colName && null != value && "C013".equals(colName)) {
                                RbiBondServerSideRegexUtility.validateEmail(value);
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            responseDataDTO.setMessage(e.getMessage());
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }
                    }
                }

                if (null != rbiBondTransferInDTO && null!=rbiBondTransferInDTO.getGuardianModValueDetailsList()&&rbiBondTransferInDTO.getGuardianModValueDetailsList().size()>0&&null != rbiBondTransferInDTO.getGuardianModValueDetailsList().get(i)) {
                    for (ColumnValueDTO columnValueDTO : rbiBondTransferInDTO.getGuardianModValueDetailsList().get(i)) {
                        String colName = columnValueDTO.getColName();
                        String value = columnValueDTO.getValue();
                        try {

                            if (null != colName && null != value && "C012".equals(colName)) {
                                RbiBondServerSideRegexUtility.validateMobileNumber(value);
                            }
                            if (null != colName && null != value && "C013".equals(colName)) {
                                RbiBondServerSideRegexUtility.validateEmail(value);
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            responseDataDTO.setMessage(e.getMessage());
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }
                    }
                }
            }
        return rbiBondTransferInBo.modify(rbiBondTransferInDTO);
    }
    @Override
    public ResponseDataDTO getGuardianDetails(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInBo.getGuardianDetails(rbiBondTransferInDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondTransferInDTO rbiBondTransferInDTO) {

            return rbiBondTransferInBo.verify(rbiBondTransferInDTO);

    }

    private boolean validateMaturityDate(String valueDateStr, String maturityDateStr, String periodOfDepositStr) {
        String expected = calculateMaturityDate(valueDateStr, periodOfDepositStr);
        LocalDate incoming = LocalDate.parse(maturityDateStr.trim(), DATE_FMT);
        return expected.equals(incoming.format(DATE_FMT));
    }

    public static String calculateMaturityDate(String valueDateStr, String periodOfDepositStr) {
        LocalDate valueDate = LocalDate.parse(valueDateStr.trim(), DATE_FMT);
        int months = Integer.parseInt(periodOfDepositStr.trim());
        // Standard maturity date
        LocalDate maturity = valueDate.plusMonths(months);
        // Custom rule: If start date is Feb 29
        if (valueDate.getMonthValue() == 2 && valueDate.getDayOfMonth() == 29) {
            // If resulting month does not have 29 → roll forward to 1st of next month
            if (maturity.getDayOfMonth() < 29) {
                maturity = maturity.withDayOfMonth(1).plusMonths(1); // 01-MAR (or next month)
            }
        }
        return maturity.format(DATE_FMT);
    }
}
