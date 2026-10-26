package com.isl.gbm.rbibond.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TransactionReponseDto;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.dao.RbiBondClosureBeforeMaturDeathDao;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDeathDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDeathLegalHierDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDeathNomiineeDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Repository
public class RbiBondClosureBeforeMaturDeathDaoImpl implements RbiBondClosureBeforeMaturDeathDao {

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;


    @Autowired
    TransactionValidator transactionValidator;

    private static final Logger log = LoggerFactory.getLogger(RbiBondClosureBeforeMaturDeathDaoImpl.class);


    @Override
    public ResponseDataDTO getAddDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        Map<String, Object> rbiBondClosureBefMaturDetMap = new HashMap<>();

        RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDeathDTO();
        List<RbiBondClosureBeforeMaturDeathDTO> rbiBondBLAList = new ArrayList<>();
        List<RbiBondClosureBeforeMaturDeathDTO> rbiBondBondSubsList = new ArrayList<>();
        List<RbiBondClosureBeforeMaturDeathDTO> rbiBondBondNomineeDetailsList = new ArrayList<>();

        RbiBondClosureBeforeMaturDeathDTO rbiBondBlaDet = new RbiBondClosureBeforeMaturDeathDTO();
        RbiBondClosureBeforeMaturDeathDTO rbiBondNomineeData = new RbiBondClosureBeforeMaturDeathDTO();



        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_DEATH_SP_ADD_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModuleId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());

                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getProceedsToBePaid());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getPenalityToBeRecovered());

                stmt.registerOutParameter(index++, Types.VARCHAR); //deitbondsubsacc
                stmt.registerOutParameter(index++, Types.VARCHAR); //debitbondsundryacc
                stmt.registerOutParameter(index++, Types.VARCHAR); //credit tds acc
                stmt.registerOutParameter(index++,Types.VARCHAR);  //DD/PO ACC NUM
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                resultMsg = stmt.getString(index);
                responseDataDTO.setMessage(resultMsg);


                if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                    if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("bla")) {

                        ResultSet rs = (ResultSet) stmt.getObject(14);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDeathDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("bla_no"));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("name_of_the_bond_holder"));
                            rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString("mode_of_holding"));
                            rbiBondClosureBeforeMaturDTO1.setVerifiedStatus(rs.getString("verified_status"));
                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }
                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("boSubsId")) {

                        ResultSet rs = (ResultSet) stmt.getObject(14);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDeathDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("bond_ledger_account_number"));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("bond_holder_name"));
                            rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString("bond_subscription_id"));
                            rbiBondClosureBeforeMaturDTO1.setMaturityDate(rs.getString("maturity_date"));
                            rbiBondBondSubsList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBondSubsList);
                    }

                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("det")) {

                        ResultSet rs = (ResultSet) stmt.getObject(14);

                        if(rs != null) {
                            while (rs.next()) {
                                rbiBondClosureBeforeMaturDTO1.setSubsDate(rs.getString("Subscription_Date"));
                                rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString("bond_subscription_id"));
                                rbiBondClosureBeforeMaturDTO1.setSubsAmt(rs.getString("subscription_amount"));
                                rbiBondClosureBeforeMaturDTO1.setBondType(rs.getString("bond_type"));
                                rbiBondClosureBeforeMaturDTO1.setMaturityDate(rs.getString("maturity_date"));
                                rbiBondClosureBeforeMaturDTO1.setIntPaidUpto(rs.getString("interest_paid_up_to"));
                                rbiBondClosureBeforeMaturDTO1.setIntCalFromDate(rs.getString("interest_cal_from_date"));
                                rbiBondClosureBeforeMaturDTO1.setIntCalToDate(rs.getString("interest_cal_to_date"));
                                rbiBondClosureBeforeMaturDTO1.setLastHalfYrIntRate(rs.getString("Last_half_Year_int_Rate"));
                                rbiBondClosureBeforeMaturDTO1.setLastHalfYrIntAmt(rs.getString("Last_half_Year_int_amt"));
                                rbiBondClosureBeforeMaturDTO1.setPenality(rs.getString("penalty_to_be_recovered"));
                                rbiBondClosureBeforeMaturDTO1.setNetIntLastHalfYr(rs.getString("Net_int_for_last_half_year"));
                                rbiBondClosureBeforeMaturDTO1.setPendIntLastHalfYr(rs.getString("pending_int_previous_half_year"));
                                rbiBondClosureBeforeMaturDTO1.setTotIntBefTDS(rs.getString("total_int_before_tds"));
                                rbiBondClosureBeforeMaturDTO1.setTdsAmt(rs.getString("tds_amount"));
                                rbiBondClosureBeforeMaturDTO1.setTotIntAftTDS(rs.getString("total_int_after_tds"));
                                rbiBondClosureBeforeMaturDTO1.setAmtPaid(rs.getString("tot_paid_amt"));
                                rbiBondClosureBeforeMaturDTO1.setModeOfInterestPay(rs.getString("payment_mode"));
                                rbiBondClosureBeforeMaturDTO1.setCredAccNum(rs.getString("credit_acc_num"));
                                rbiBondClosureBeforeMaturDTO1.setOprAccBrId(rs.getString("oper_acc_branch_id"));
                                rbiBondClosureBeforeMaturDTO1.setBankName(rs.getString("bank_name"));
                                rbiBondClosureBeforeMaturDTO1.setBrCode(rs.getString("branch_code"));
                                rbiBondClosureBeforeMaturDTO1.setIfscCode(rs.getString("ifsc_code_of_credit_bank"));
                                rbiBondClosureBeforeMaturDTO1.setPayeeName(rs.getString("name_of_the_payee"));
                                rbiBondClosureBeforeMaturDTO1.setIslastYearIntPaid(rs.getString("last_int_per_flg"));
                                rbiBondClosureBeforeMaturDTO1.setDodBondHolder(rs.getString("date_of_dth_of_bond_hldr"));
                                rbiBondClosureBeforeMaturDTO1.setDateOfReceiptDod(rs.getString("date_of_receipt_of_dth_cert"));

                            }

                            rbiBondClosureBeforeMaturDTO1.setDebBondSusAcc(stmt.getString(8));
                            rbiBondClosureBeforeMaturDTO1.setDebBondSundryAcc(stmt.getString(9));
                            rbiBondClosureBeforeMaturDTO1.setCredBondTdsAcc(stmt.getString(10));
                            rbiBondClosureBeforeMaturDTO1.setDdPoAccNum(stmt.getString(11));
                            rbiBondClosureBeforeMaturDTO1.setDebBondSusAccFlag(stmt.getString(15));
                            rbiBondClosureBeforeMaturDTO1.setDebBondSundryAccFlag(stmt.getString(16));
                            rbiBondClosureBeforeMaturDTO1.setCredBondTdsAccFlag(stmt.getString(17));
                            rbiBondClosureBeforeMaturDTO1.setDdPoAccNumFlag(stmt.getString(18));
                        }

                        ResponseDataDTO officeAccountValidation =  officeAccountValidation(rbiBondClosureBeforeMaturDTO1);
                        rbiBondClosureBefMaturDetMap.put("ddPoAccNum", rbiBondClosureBeforeMaturDTO1.getDdPoAccNum());
                        rbiBondClosureBefMaturDetMap.put("bondDetails", rbiBondClosureBeforeMaturDTO1);

                        ResultSet rs1 = (ResultSet) stmt.getObject(12);

                      if(rs1 != null) {
                          while (rs1.next()) {

                              rbiBondBlaDet.setModeOfHoldingCode(rs1.getString("mode_of_holding_1"));
                              rbiBondBlaDet.setModeOfHolding(rs1.getString("mode_of_holding"));
                              rbiBondBlaDet.setHolderType(rs1.getString("holder_type"));
                              rbiBondBlaDet.setHolderTypeCode(rs1.getString("holder_type_1"));
                              rbiBondBlaDet.setIsNri(rs1.getString("is_nri"));
                              if (rs1.getString("joint_holder_type_1").equalsIgnoreCase("1")) {
                                  rbiBondBlaDet.setFirstJointBondHolderBLA(rs1.getString("bla_no"));
                                  rbiBondBlaDet.setFirstJointBondHolderName(rs1.getString("name_of_the_bond_holder"));
                              } else if (rs1.getString("joint_holder_type_1").equalsIgnoreCase("2")) {
                                  rbiBondBlaDet.setSecondJointBondHolderBLA(rs1.getString("bla_no"));
                                  rbiBondBlaDet.setSecondJointBondHolderName(rs1.getString("name_of_the_bond_holder"));
                              } else {
                                  rbiBondBlaDet.setBlaNo(rs1.getString("bla_no"));
                                  rbiBondBlaDet.setBondHolderName(rs1.getString("name_of_the_bond_holder"));
                              }

                          }
                      }
                          rbiBondClosureBefMaturDetMap.put("blaDetails", rbiBondBlaDet);

                        ResultSet rs2 = (ResultSet) stmt.getObject(13);
                        if(rs2 != null) {
                            while (rs2.next()) {
                                rbiBondNomineeData = new RbiBondClosureBeforeMaturDeathDTO();

                                rbiBondNomineeData.setNomineeName(rs2.getString("nominee_name"));
                                rbiBondNomineeData.setSharePercentage(rs2.getString("share_percentage"));
                                rbiBondNomineeData.setShareAmountSettled(rs2.getString("share_amount_settled"));
                                rbiBondNomineeData.setAccNum(rs2.getString("credit_acc_no"));
                                rbiBondNomineeData.setInitalNomineeAccNo(rs2.getString("credit_acc_no"));
                                rbiBondNomineeData.setInitalNomineeBranchId(rs2.getString("credit_acc_branch"));
                                rbiBondNomineeData.setAccBranch(rs2.getString("credit_acc_branch"));
                                rbiBondNomineeData.setNomineeId(rs2.getString("nomination_id"));
                                rbiBondNomineeData.setModeOfInterestPay(rs2.getString("mode_of_payment"));

//                                rbiBondNomineeData.setNomineeId(rs2.getString("modeOfInterestPay"));
                                rbiBondBondNomineeDetailsList.add(rbiBondNomineeData);
                            }
                        }
                        rbiBondClosureBefMaturDetMap.put("nomineeDetailsList", rbiBondBondNomineeDetailsList);
                        responseDataDTO.setResult(rbiBondClosureBefMaturDetMap);

                        if(officeAccountValidation.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)){
                            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                        }
                        else{
                            responseDataDTO.setMessage(officeAccountValidation.getMessage());
                        }
                    }

                }
            }
        }
        catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;
    }



//#siva for date format convertion from string
    public String dateFormat(String inputDate){
        String inputFormat = "dd-MM-yy";
        String outputFormat = "dd-MMM-yyyy";
        String formattedDate ="";
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
            Date date = inputDateFormat.parse(inputDate);
             formattedDate = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }


    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        Map<String, Object> rbiBondClosureBefMaturDetMap = new HashMap<>();

        RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDeathDTO();
        RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO2 = new RbiBondClosureBeforeMaturDeathDTO();

        List<RbiBondClosureBeforeMaturDeathDTO> rbiBondBLAList = new ArrayList<>();
        List<RbiBondClosureBeforeMaturDeathDTO> rbiBondBondSubsList = new ArrayList<>();
        RbiBondClosureBeforeMaturDeathDTO    rbiBondNomineeData = new RbiBondClosureBeforeMaturDeathDTO();
        List<RbiBondClosureBeforeMaturDeathDTO> rbiBondNomineeDetailsList = new ArrayList<>();
        List<RbiBondClosureBeforeMaturDeathDTO> rbiBondLegalHierDetailsList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;

                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_DEATH_SP_VER_DEL_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModuleId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR); //cursorval out
                stmt.registerOutParameter(index++, OracleTypes.CURSOR); //nominee details
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                resultMsg = stmt.getString(index);
                responseDataDTO.setMessage(resultMsg);


                if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                    if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("bla")) {

                        ResultSet rs = (ResultSet) stmt.getObject(index - 3);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDeathDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("bla_no"));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("bond_holder_name"));
                            rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString("mode_of_holding"));
//                            rbiBondClosureBeforeMaturDTO1.setVerifiedStatus(rs.getString("verified_status"));
                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }

                        responseDataDTO.setResult(rbiBondBLAList);

                    }
                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("boSubsId")) {

                        ResultSet rs = (ResultSet) stmt.getObject(index - 3);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDeathDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("bla_no"));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("bond_holder_name"));
                            rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString("subscription_id"));
                            rbiBondClosureBeforeMaturDTO1.setGbmTranId(rs.getString("gbm_tran_id"));
                            rbiBondClosureBeforeMaturDTO1.setMaturityDate(rs.getString("maturity_date"));
                            rbiBondClosureBeforeMaturDTO1.setClosureType(rs.getString("closure_type"));
                            rbiBondClosureBeforeMaturDTO1.setVerifiedStatus(rs.getString("verified_status"));
                            rbiBondBondSubsList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBondSubsList);
                    }

                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("det")) {



                        ResultSet rs = (ResultSet) stmt.getObject(index - 3);

                        if(rs != null) {
                            while (rs.next()) {

                                rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString("mode_of_holding"));
                                rbiBondClosureBeforeMaturDTO1.setModeOfHoldingCode(rs.getString("mode_of_holding_1"));
                                rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("single_bla_no"));
                                rbiBondClosureBeforeMaturDTO1.setHolderType(rs.getString("holder_type"));
                                rbiBondClosureBeforeMaturDTO1.setFirstJointBondHolderBLA(rs.getString("first_joint_hldr_bla_no"));
                                rbiBondClosureBeforeMaturDTO1.setFirstJointBondHolderName(rs.getString("first_joint_hldr_name"));
                                rbiBondClosureBeforeMaturDTO1.setSecondJointBondHolderBLA(rs.getString("sec_joint_hldr_bla_no"));
                                rbiBondClosureBeforeMaturDTO1.setSecondJointBondHolderName(rs.getString("sec_joint_hldr_name"));
                                rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("single_hldr_name"));
                                rbiBondClosureBeforeMaturDTO1.setIsNri(rs.getString("is_nri"));
                                rbiBondClosureBefMaturDetMap.put("blaDetails", rbiBondClosureBeforeMaturDTO1);


                                rbiBondClosureBeforeMaturDTO2.setDodBondHolder(rs.getString("date_of_death_of_bond_hldr"));
                                rbiBondClosureBeforeMaturDTO2.setDateOfReceiptDod(rs.getString("date_of_receipt_of_death_certi"));
                                rbiBondClosureBeforeMaturDTO2.setSubsDate(rs.getString("subscription_date"));
                                rbiBondClosureBeforeMaturDTO2.setBoSubsId(rs.getString("subscription_id"));
                                rbiBondClosureBeforeMaturDTO2.setSubsAmt(rs.getString("subscription_amount"));
                                rbiBondClosureBeforeMaturDTO2.setBondType(rs.getString("bond_type"));
                                rbiBondClosureBeforeMaturDTO2.setMaturityDate(rs.getString("maturity_date"));
                                rbiBondClosureBeforeMaturDTO2.setAccClosedDate(rs.getString("Account_Closed_Date"));
                                rbiBondClosureBeforeMaturDTO2.setProceedsToBePaid(rs.getString("proceeds_to_be_paid_to"));
                                rbiBondClosureBeforeMaturDTO2.setProceedsToPaidCode(rs.getString("proceeds_to_be_paid_to1")); //code
                                rbiBondClosureBeforeMaturDTO2.setIntPaidUpto(rs.getString("interest_paid_up_to"));
                                rbiBondClosureBeforeMaturDTO2.setIntCalFromDate(rs.getString("interest_cal_from_date"));
                                rbiBondClosureBeforeMaturDTO2.setIntCalToDate(rs.getString("interest_cal_to_date"));
                                rbiBondClosureBeforeMaturDTO2.setLastHalfYrIntRate(rs.getString("last_half_year_per_int_rate"));
                                rbiBondClosureBeforeMaturDTO2.setLastHalfYrIntAmt(rs.getString("last_half_year_per_int_amt"));
                                rbiBondClosureBeforeMaturDTO2.setPenality(rs.getString("penalty_to_be_recovered"));
                                rbiBondClosureBeforeMaturDTO2.setNetIntLastHalfYr(rs.getString("net_int_last_half_year_per"));
                                rbiBondClosureBeforeMaturDTO2.setTotIntBefTDS(rs.getString("total_interest_before_tds"));

                                rbiBondClosureBeforeMaturDTO2.setTdsAmt(rs.getString("tds_amount"));
                                rbiBondClosureBeforeMaturDTO2.setTotIntAftTDS(rs.getString("total_interest_after_tds"));
                                rbiBondClosureBeforeMaturDTO2.setAmtPaid(rs.getString("total_amount_to_be_paid"));
                                rbiBondClosureBeforeMaturDTO2.setClosureRemarks(rs.getString("closure_remarks"));
                                rbiBondClosureBeforeMaturDTO2.setPenalityToBeRecovered(rs.getString("whether_penalty_to_be_recover"));
                                rbiBondClosureBeforeMaturDTO2.setPendingInterest(rs.getString("pending_interest"));


                            }

                            rbiBondClosureBefMaturDetMap.put("bondDetails", rbiBondClosureBeforeMaturDTO2);


                            ResultSet rs2 = (ResultSet) stmt.getObject(index - 2);
                            if(rs2 != null) {
                                while (rs2.next()) {

                                    if (rbiBondClosureBeforeMaturDTO2.getProceedsToPaidCode().equalsIgnoreCase("N")) {
                                        rbiBondNomineeData = new RbiBondClosureBeforeMaturDeathDTO();
                                        rbiBondNomineeData.setNomineeId(rs2.getString("nomination_id"));
                                        rbiBondNomineeData.setNomineeName(rs2.getString("nominee_name"));
                                        rbiBondNomineeData.setSharePercentage(rs2.getString("share_percentage"));
                                        rbiBondNomineeData.setShareAmountSettled(rs2.getString("settled_share_amt"));
                                        rbiBondNomineeData.setModeOfInterestPay(rs2.getString("payment_mode"));
                                        rbiBondNomineeData.setAccNum(rs2.getString("credit_acc_num"));
                                        rbiBondNomineeData.setAccBranch(rs2.getString("credit_acc_branch"));
                                        rbiBondNomineeData.setRemarks(rs2.getString("remarks"));

                                        rbiBondNomineeDetailsList.add(rbiBondNomineeData);
                                        rbiBondClosureBefMaturDetMap.put("nomineeDetails", rbiBondNomineeDetailsList);


                                    } else if (rbiBondClosureBeforeMaturDTO2.getProceedsToPaidCode().equalsIgnoreCase("L")) {
                                        rbiBondNomineeData = new RbiBondClosureBeforeMaturDeathDTO();
                                        rbiBondNomineeData.setLegalheirName(rs2.getString("legalheir_name"));
                                        rbiBondNomineeData.setRelationShip(rs2.getString("relation_with_customer"));
                                        rbiBondNomineeData.setSharePercentage(rs2.getString("share_percentage"));
                                        rbiBondNomineeData.setShareAmountSettled(rs2.getString("amount_settled"));
                                        rbiBondNomineeData.setModeOfInterestPay(rs2.getString("payment_mode"));
                                        rbiBondNomineeData.setAccNum(rs2.getString("oper_acct_num"));
                                        rbiBondNomineeData.setAccBranch(rs2.getString("oper_acc_branch_id"));
                                        rbiBondNomineeData.setRemarks(rs2.getString("remarks"));
                                        rbiBondLegalHierDetailsList.add(rbiBondNomineeData);

                                        rbiBondClosureBefMaturDetMap.put("legalHeirDeatails", rbiBondLegalHierDetailsList);


                                    }
                                }
                            }

//                            rbiBondClosureBeforeMaturDTO2.setDebBondSusAcc(stmt.getString(6));
//                            rbiBondClosureBeforeMaturDTO2.setDebBondSundryAcc(stmt.getString(7));
//                            rbiBondClosureBeforeMaturDTO2.setCredAccNumber(stmt.getString(8));
//                            rbiBondClosureBeforeMaturDTO2.setCredBondTdsAcc(stmt.getString(9));

                        }


                        rbiBondClosureBeforeMaturDTO2.setCreatedBy(stmt.getString(6));
                        rbiBondClosureBeforeMaturDTO2.setCreatedDate(stmt.getString(7));
                        rbiBondClosureBeforeMaturDTO2.setVerifiedBy(stmt.getString(10));
                        rbiBondClosureBeforeMaturDTO2.setVerifiedDate(stmt.getString(11));
                        rbiBondClosureBeforeMaturDTO2.setVerifiedStatus(stmt.getString(12));
                        rbiBondClosureBefMaturDetMap.put("auditDetails", rbiBondClosureBeforeMaturDTO2);

                        responseDataDTO.setResult(rbiBondClosureBefMaturDetMap);
                    }

                }
            }
        }
        catch (Exception e) {
            log.info(e.getMessage());
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getInqDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {


            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            Connection connection = null;
            CallableStatement stmt = null;
            String resultMsg = "";
            Map<String, Object> rbiBondClosureBefMaturDetMap = new HashMap<>();

            RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDeathDTO();
            List<RbiBondClosureBeforeMaturDeathDTO> rbiBondBLAList = new ArrayList<>();
            List<RbiBondClosureBeforeMaturDeathDTO> rbiBondBondSubsList = new ArrayList<>();

        RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO2 = new RbiBondClosureBeforeMaturDeathDTO();

        RbiBondClosureBeforeMaturDeathDTO    rbiBondNomineeData = new RbiBondClosureBeforeMaturDeathDTO();
        List<RbiBondClosureBeforeMaturDeathDTO> rbiBondNomineeDetailsList = new ArrayList<>();
        List<RbiBondClosureBeforeMaturDeathDTO> rbiBondLegalHierDetailsList = new ArrayList<>();



        try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {

                    int index = 1;

                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_DEATH_SP_INQ.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModuleId());
                    stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                    stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);

                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                    resultMsg = stmt.getString(index);
                    responseDataDTO.setMessage(resultMsg);


                    if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                        if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("bla")) {

                            ResultSet rs = (ResultSet) stmt.getObject(index - 3);

                            while (rs.next()) {
                                rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDeathDTO();
                                rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("bla_no"));
                                rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("bond_holder_name"));
                                rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString("mode_of_holding"));
//                                rbiBondClosureBeforeMaturDTO1.setVerifiedStatus(rs.getString("verified_status"));
                                rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                            }

                            responseDataDTO.setResult(rbiBondBLAList);

                        }
                        else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("boSubsId")) {

                            ResultSet rs = (ResultSet) stmt.getObject(index - 3);

                            while (rs.next()) {
                                rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDeathDTO();
                                rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("bla_no"));
                                rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("bond_holder_name"));
                                rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString("subscription_id"));
                                rbiBondClosureBeforeMaturDTO1.setGbmTranId(rs.getString("gbm_tran_id"));
                                rbiBondClosureBeforeMaturDTO1.setMaturityDate(rs.getString("maturity_date"));
                                rbiBondClosureBeforeMaturDTO1.setClosureType(rs.getString("closure_type"));
                                rbiBondClosureBeforeMaturDTO1.setVerifiedStatus(rs.getString("verified_status"));
                                rbiBondBondSubsList.add(rbiBondClosureBeforeMaturDTO1);
                            }
                            responseDataDTO.setResult(rbiBondBondSubsList);
                        }

                        else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("det")) {

                            ResultSet rs = (ResultSet) stmt.getObject(index - 3);

                            if(rs != null) {
                                while (rs.next()) {

                                    rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString("mode_of_holding"));
                                    rbiBondClosureBeforeMaturDTO1.setModeOfHoldingCode(rs.getString("mode_of_holding_1"));
                                    rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("single_bla_no"));
                                    rbiBondClosureBeforeMaturDTO1.setHolderType(rs.getString("holder_type"));
                                    rbiBondClosureBeforeMaturDTO1.setFirstJointBondHolderBLA(rs.getString("first_joint_hldr_bla_no"));
                                    rbiBondClosureBeforeMaturDTO1.setFirstJointBondHolderName(rs.getString("first_joint_hldr_name"));
                                    rbiBondClosureBeforeMaturDTO1.setSecondJointBondHolderBLA(rs.getString("sec_joint_hldr_bla_no"));
                                    rbiBondClosureBeforeMaturDTO1.setSecondJointBondHolderName(rs.getString("sec_joint_hldr_name"));
                                    rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("single_hldr_name"));
                                    rbiBondClosureBeforeMaturDTO1.setIsNri(rs.getString("is_nri"));
                                    rbiBondClosureBefMaturDetMap.put("blaDetails", rbiBondClosureBeforeMaturDTO1);


                                    rbiBondClosureBeforeMaturDTO2.setDodBondHolder(rs.getString("date_of_death_of_bond_hldr"));
                                    rbiBondClosureBeforeMaturDTO2.setDateOfReceiptDod(rs.getString("date_of_receipt_of_death_certi"));
                                    rbiBondClosureBeforeMaturDTO2.setSubsDate(rs.getString("subscription_date"));
                                    rbiBondClosureBeforeMaturDTO2.setBoSubsId(rs.getString("subscription_id"));
                                    rbiBondClosureBeforeMaturDTO2.setSubsAmt(rs.getString("subscription_amount"));
                                    rbiBondClosureBeforeMaturDTO2.setBondType(rs.getString("bond_type"));
                                    rbiBondClosureBeforeMaturDTO2.setMaturityDate(rs.getString("maturity_date"));
                                    rbiBondClosureBeforeMaturDTO2.setAccClosedDate(rs.getString("Account_Closed_Date"));
                                    rbiBondClosureBeforeMaturDTO2.setProceedsToBePaid(rs.getString("proceeds_to_be_paid_to"));
                                    rbiBondClosureBeforeMaturDTO2.setProceedsToPaidCode(rs.getString("proceeds_to_be_paid_to1")); //code
                                    rbiBondClosureBeforeMaturDTO2.setIntPaidUpto(rs.getString("interest_paid_up_to"));
                                    rbiBondClosureBeforeMaturDTO2.setIntCalFromDate(rs.getString("interest_cal_from_date"));
                                    rbiBondClosureBeforeMaturDTO2.setIntCalToDate(rs.getString("interest_cal_to_date"));
                                    rbiBondClosureBeforeMaturDTO2.setLastHalfYrIntRate(rs.getString("last_half_year_per_int_rate"));
                                    rbiBondClosureBeforeMaturDTO2.setLastHalfYrIntAmt(rs.getString("last_half_year_per_int_amt"));
                                    rbiBondClosureBeforeMaturDTO2.setPenality(rs.getString("penalty_to_be_recovered"));
                                    rbiBondClosureBeforeMaturDTO2.setNetIntLastHalfYr(rs.getString("net_int_last_half_year_per"));
                                    rbiBondClosureBeforeMaturDTO2.setTotIntBefTDS(rs.getString("total_interest_before_tds"));

                                    rbiBondClosureBeforeMaturDTO2.setTdsAmt(rs.getString("tds_amount"));
                                    rbiBondClosureBeforeMaturDTO2.setTotIntAftTDS(rs.getString("total_interest_after_tds"));
                                    rbiBondClosureBeforeMaturDTO2.setAmtPaid(rs.getString("total_amount_to_be_paid"));
                                    rbiBondClosureBeforeMaturDTO2.setClosureRemarks(rs.getString("closure_remarks"));
                                    rbiBondClosureBeforeMaturDTO2.setPenalityToBeRecovered(rs.getString("whether_penalty_to_be_recover"));
                                    rbiBondClosureBeforeMaturDTO2.setPendingInterest(rs.getString("pending_interest"));


                                }

                                rbiBondClosureBefMaturDetMap.put("bondDetails", rbiBondClosureBeforeMaturDTO2);


                                ResultSet rs2 = (ResultSet) stmt.getObject(index - 2);
                                if(rs2 != null) {
                                    while (rs2.next()) {

                                        if (rbiBondClosureBeforeMaturDTO2.getProceedsToPaidCode().equalsIgnoreCase("N")) {
                                            rbiBondNomineeData = new RbiBondClosureBeforeMaturDeathDTO();
                                            rbiBondNomineeData.setNomineeId(rs2.getString("nomination_id"));
                                            rbiBondNomineeData.setNomineeName(rs2.getString("nominee_name"));
                                            rbiBondNomineeData.setSharePercentage(rs2.getString("share_percentage"));
                                            rbiBondNomineeData.setShareAmountSettled(rs2.getString("settled_share_amt"));
                                            rbiBondNomineeData.setModeOfInterestPay(rs2.getString("payment_mode"));
                                            rbiBondNomineeData.setAccNum(rs2.getString("credit_acc_num"));
                                            rbiBondNomineeData.setAccBranch(rs2.getString("credit_acc_branch"));
                                            rbiBondNomineeData.setRemarks(rs2.getString("remarks"));

                                            rbiBondNomineeDetailsList.add(rbiBondNomineeData);
                                            rbiBondClosureBefMaturDetMap.put("nomineeDetails", rbiBondNomineeDetailsList);


                                        } else if (rbiBondClosureBeforeMaturDTO2.getProceedsToPaidCode().equalsIgnoreCase("L")) {
                                            rbiBondNomineeData = new RbiBondClosureBeforeMaturDeathDTO();
                                            rbiBondNomineeData.setLegalheirName(rs2.getString("legalheir_name"));
                                            rbiBondNomineeData.setRelationShip(rs2.getString("relation_with_customer"));
                                            rbiBondNomineeData.setSharePercentage(rs2.getString("share_percentage"));
                                            rbiBondNomineeData.setShareAmountSettled(rs2.getString("amount_settled"));
                                            rbiBondNomineeData.setModeOfInterestPay(rs2.getString("payment_mode"));
                                            rbiBondNomineeData.setAccNum(rs2.getString("oper_acct_num"));
                                            rbiBondNomineeData.setAccBranch(rs2.getString("oper_acc_branch_id"));
                                            rbiBondNomineeData.setRemarks(rs2.getString("remarks"));
                                            rbiBondLegalHierDetailsList.add(rbiBondNomineeData);

                                            rbiBondClosureBefMaturDetMap.put("legalHeirDeatails", rbiBondLegalHierDetailsList);

                                        }
                                    }
                                }

//                            rbiBondClosureBeforeMaturDTO2.setDebBondSusAcc(stmt.getString(6));
//                            rbiBondClosureBeforeMaturDTO2.setDebBondSundryAcc(stmt.getString(7));
//                            rbiBondClosureBeforeMaturDTO2.setCredAccNumber(stmt.getString(8));
//                            rbiBondClosureBeforeMaturDTO2.setCredBondTdsAcc(stmt.getString(9));

                            }


                            rbiBondClosureBeforeMaturDTO2.setCreatedBy(stmt.getString(6));
                            rbiBondClosureBeforeMaturDTO2.setCreatedDate(stmt.getString(7));
                            rbiBondClosureBeforeMaturDTO2.setVerifiedBy(stmt.getString(10));
                            rbiBondClosureBeforeMaturDTO2.setVerifiedDate(stmt.getString(11));
                            rbiBondClosureBeforeMaturDTO2.setVerifiedStatus(stmt.getString(12));
                            rbiBondClosureBefMaturDetMap.put("auditDetails", rbiBondClosureBeforeMaturDTO2);

                            responseDataDTO.setResult(rbiBondClosureBefMaturDetMap);
                        }

                    }
                }
            }
            catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
            finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }
            return responseDataDTO;
        }

    @Override
    public ResponseDataDTO add(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO1) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        Map<String, String> bondDetMap = (Map<String, String>) rbiBondClosureBeforeMaturDTO1.getBondDetails();

        final ObjectMapper mapper = new ObjectMapper();
        final RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO = mapper.convertValue(bondDetMap, RbiBondClosureBeforeMaturDeathDTO.class);


        if(rbiBondClosureBeforeMaturDTO.getModeOfHoldingCode().equalsIgnoreCase("MH002") || rbiBondClosureBeforeMaturDTO.getModeOfHoldingCode().equalsIgnoreCase("MH003")){

            rbiBondClosureBeforeMaturDTO.setBlaNo(null);
            rbiBondClosureBeforeMaturDTO.setBondHolderName(null);

        }

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_DEATH_SP_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModuleId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getClosureType());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getProceedsToBePaid());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModeOfHoldingCode());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getHolderTypeCode());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBondHolderName());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getFirstJointBondHolderBLA());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getFirstJointBondHolderName());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getSecondJointBondHolderBLA());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getSecondJointBondHolderName());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getIsNri());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getSubsDate());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getSubsAmt());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBondType());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getMaturityDate());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getIntPaidUpto());

                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getIntCalFromDate());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getIntCalToDate());



                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getLastHalfYrIntRate());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getLastHalfYrIntAmt());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getPenalityToBeRecovered()); //y or n

                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getPenality()); //amt
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getNetIntLastHalfYr());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getPendIntLastHalfYr());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getTotIntBefTDS());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getTdsAmt());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getTotIntAftTDS());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getAmtPaid());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getClosureRemarks());

                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getDodBondHolder());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getDateOfReceiptDod());

                if (rbiBondClosureBeforeMaturDTO.getProceedsToBePaid().equalsIgnoreCase("N")){
                    stmt.setArray(index++, generateArrayOfNominee(connection, rbiBondClosureBeforeMaturDTO1.getNomineeDetails()));
                   stmt.setNull(index++, Types.ARRAY, "TYP_GBM_RBI_BOND_LEG_HEIR");
                }
                else if(rbiBondClosureBeforeMaturDTO.getProceedsToBePaid().equalsIgnoreCase("L")){
                    stmt.setNull(index++, Types.ARRAY, "TYP_GBM_RBI_BOND_NOM_DET");
                    stmt.setArray(index++, generateArrayOfLegalHeir(connection, rbiBondClosureBeforeMaturDTO1.getLegalHeirDeatails()));
                }
                else{
                    stmt.setNull(index++, Types.ARRAY, "TYP_GBM_RBI_BOND_NOM_DET");
                    stmt.setNull(index++, Types.ARRAY, "TYP_GBM_RBI_BOND_LEG_HEIR");
                }
                stmt.setString(index++, null); //cred acc type in
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getCredBondTdsAcc());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getDebBondSusAcc());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getDebBondSundryAcc());
                stmt.setString(index++, null); //deb acc type in

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                resultMsg = stmt.getString(index);
                if(stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                    responseDataDTO.setResult(stmt.getString(index - 2));
                }
                log.info(stmt.getString(index));
            }
            else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);

            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);

        return responseDataDTO;
    }


    private static ARRAY generateArrayOfLegalHeir(Connection connection, List<RbiBondClosureBeforeMaturDeathLegalHierDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_LEG_HEIR_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_LEG_HEIR", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RbiBondClosureBeforeMaturDeathLegalHierDTO rbiBondClosureBeforeMaturDeathLegalHierDTO = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[8];
            rbiBondClosureBeforeMaturDeathLegalHierDTO = list.get(index);
            paramArray[0] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getLegalheirName();
            paramArray[1] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getRelationShip();
            paramArray[2] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getSharePercentage();
            paramArray[3] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getShareAmountSettled();
            paramArray[4] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getModeOfInterestPay();
            paramArray[5] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getAccNum();
            paramArray[6] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getAccBranch();
            paramArray[7] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getRemarks();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


    private static ARRAY generateArrayOfNominee(Connection connection, List<RbiBondClosureBeforeMaturDeathNomiineeDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_DET_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_DET", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RbiBondClosureBeforeMaturDeathNomiineeDTO rbiBondClosureBeforeMaturDeathNomiineeDTO = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[8];
            rbiBondClosureBeforeMaturDeathNomiineeDTO = list.get(index);
            paramArray[0] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getNomineeId();
            paramArray[1] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getNomineeName();
            paramArray[2] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getSharePercentage();
            paramArray[3] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getShareAmountSettled();
            paramArray[4] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getModeOfInterestPay();
            paramArray[5] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getAccNum();
            paramArray[6] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getAccBranch();
            paramArray[7] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getRemarks();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }








    @Override
    public ResponseDataDTO delete(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_DEATH_SP_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModuleId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                resultMsg = stmt.getString(index);
            }
            else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        List<GbmTransactionMasterDto> gbmTransactionMasterDtos = new ArrayList();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_DEATH_SP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModuleId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                resultMsg = stmt.getString(index);


                Date tranDate = new Date();
                    try {
                        tranDate = new SimpleDateFormat("dd/MM/yyyy").parse(stmt.getString(8));
                    }catch (ParseException e) {
                        e.printStackTrace();
                    }
                ResultSet rs = (ResultSet) stmt.getObject(index-2);

                while(rs.next()){
                    GbmTransactionMasterDto gbmTransactionMasterDto = new GbmTransactionMasterDto();
                    gbmTransactionMasterDto.setAcctNum(rs.getString("acct_num"));
                    gbmTransactionMasterDto.setTranLeg(rs.getString("tran_type").charAt(0));
                    gbmTransactionMasterDto.setAmount(Double.parseDouble(rs.getString("amt")));
                    gbmTransactionMasterDto.setTransactionChannel(stmt.getString(5));
                    gbmTransactionMasterDto.setTranId(stmt.getString(6));
                    gbmTransactionMasterDto.setTranDate(tranDate);
                    gbmTransactionMasterDto.setSchemeCode(stmt.getString(9));
                    gbmTransactionMasterDto.setCrn(rbiBondClosureBeforeMaturDTO.getBoSubsId());

                    gbmTransactionMasterDtos.add(gbmTransactionMasterDto);

                }
                responseDataDTO.setResult(gbmTransactionMasterDtos);
            }
            else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyTranResponse(TransactionReponseDto transactionReponseDto) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_DEATHSP_VER_RES.getProcName());
                stmt.setString("p_verifier_id_in", UserAccountUtils.getCurrentUsername());
                stmt.setString("p_verifier_sol_id_in", UserAccountUtils.getCurrentSolId());
                stmt.setString("p_gbm_tran_id_in", transactionReponseDto.getGbmTranId());
                stmt.setString("p_cbs_tran_id_in", transactionReponseDto.getCbsTranId());
                stmt.setString("p_cbs_tran_stat_in", transactionReponseDto.getCbsTranStatus());
                stmt.setString("p_cbs_tran_statdesc_in",transactionReponseDto.getCbsTranStatusDesc() );
                stmt.registerOutParameter("p_gbm_tran_id_out", Types.VARCHAR);
                stmt.registerOutParameter("p_cbs_tran_id_out", Types.VARCHAR);
                stmt.registerOutParameter("p_cbs_tran_stat_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_desc_out", Types.VARCHAR);
                stmt.execute();

                responseDataDTO.setMessage(stmt.getString("p_msg_desc_out"));

            }
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;

    }

    private ResponseDataDTO officeAccountValidation(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO){


        String finacleValidationReqString = "TDS" + "^" + rbiBondClosureBeforeMaturDTO.getCredBondTdsAcc() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + rbiBondClosureBeforeMaturDTO.getCredBondTdsAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getCredBondTdsAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
        finacleValidationReqString += "!" + "SUS" + "^" + rbiBondClosureBeforeMaturDTO.getDebBondSusAcc() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + rbiBondClosureBeforeMaturDTO.getDebBondSusAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getDebBondSusAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
        finacleValidationReqString += "!" + "SUN" + "^" + rbiBondClosureBeforeMaturDTO.getDebBondSundryAcc() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + rbiBondClosureBeforeMaturDTO.getDebBondSundryAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getDebBondSundryAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
        finacleValidationReqString += "!" + "DDPO" + "^" + rbiBondClosureBeforeMaturDTO.getDdPoAccNum() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + rbiBondClosureBeforeMaturDTO.getDdPoAccNumFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getDdPoAccNumFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        ValidatorWrapperDTO validatorWrapperDTO = transactionValidator.validateBacidForAcidDetails(finacleValidationReqString);
        if (CommonConstants.SUCCESS.equalsIgnoreCase(validatorWrapperDTO.getStatus())) {
            List<TranDetailsDTO> transactionList = validatorWrapperDTO.getTranDetailsDTOList();
            for (TranDetailsDTO dto : transactionList) {
                if (CommonConstants.SUCCESS.equalsIgnoreCase(dto.getValidityStatus())) {
                    String[] accNo = dto.getAccountNumber().split("\\^");
                    if ("TDS".equalsIgnoreCase(accNo[0])) {
                        rbiBondClosureBeforeMaturDTO.setCredBondTdsAcc(accNo[1]);
                        rbiBondClosureBeforeMaturDTO.setCredBondTdsAccFlag(dto.getIsSysAcct());
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    }
                    else if ("SUS".equalsIgnoreCase(accNo[0])) {
                        rbiBondClosureBeforeMaturDTO.setDebBondSusAcc(accNo[1]);
                        rbiBondClosureBeforeMaturDTO.setDebBondSusAccFlag(dto.getIsSysAcct());
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    }
                    else if ("SUN".equalsIgnoreCase(accNo[0])) {
                        rbiBondClosureBeforeMaturDTO.setDebBondSundryAcc(accNo[1]);
                        rbiBondClosureBeforeMaturDTO.setDebBondSundryAccFlag(dto.getIsSysAcct());
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    }
                    else if ("DDPO".equalsIgnoreCase(accNo[0])) {
                        rbiBondClosureBeforeMaturDTO.setDdPoAccNum(accNo[1]);
                        rbiBondClosureBeforeMaturDTO.setDdPoAccNumFlag(dto.getIsSysAcct());
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    }
                    else {
                        responseDataDTO.setMessage("INVALID ACCOUNT NAME");
                    }
                } else {
                    responseDataDTO.setMessage(dto.getReason());
                }
            }
        } else {
            responseDataDTO.setMessage(validatorWrapperDTO.getReason());
        }
        return responseDataDTO;
    }


}
