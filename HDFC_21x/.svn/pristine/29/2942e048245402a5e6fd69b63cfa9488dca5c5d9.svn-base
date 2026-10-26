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
import com.isl.gbm.rbibond.dao.RbiBondClosureBeforeMaturDao;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class RbiBondClosureBeforeMaturDaoImpl implements RbiBondClosureBeforeMaturDao {

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    TransactionValidator transactionValidator;


    private static final Logger log = LoggerFactory.getLogger(RbiBondClosureBeforeMaturDaoImpl.class);


    @Override
    public ResponseDataDTO getAddDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        Map<String, Object> rbiBondClosureBefMaturDetMap = new HashMap<>();

        RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDTO();
        List<RbiBondClosureBeforeMaturDTO> rbiBondBLAList = new ArrayList<>();
        List<RbiBondClosureBeforeMaturDTO> rbiBondBondSubsList = new ArrayList<>();
        RbiBondClosureBeforeMaturDTO rbiBondBlaDet = new RbiBondClosureBeforeMaturDTO();


        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_SP_ADD_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModuleId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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

                        ResultSet rs = (ResultSet) stmt.getObject(11);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("bla_no"));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("name_of_the_bond_holder"));
                            rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString("mode_of_holding"));
                            rbiBondClosureBeforeMaturDTO1.setVerifiedStatus(rs.getString("verified_status"));
                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }
                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("boSubsId")) {

                        ResultSet rs = (ResultSet) stmt.getObject(11);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("bond_ledger_account_number"));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("bond_holder_name"));
                            rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString("bond_subscription_id"));
                            rbiBondClosureBeforeMaturDTO1.setMaturityDate(rs.getString("maturity_date"));
                            rbiBondBondSubsList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBondSubsList);
                    }

                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("det")) {

                        ResultSet rs = (ResultSet) stmt.getObject(11);

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
                            }

                            rbiBondClosureBeforeMaturDTO1.setDebBondSusAcc(stmt.getString(6));
                            rbiBondClosureBeforeMaturDTO1.setDebBondSundryAcc(stmt.getString(7));
                            rbiBondClosureBeforeMaturDTO1.setCredAccNumber(stmt.getString(8));
                            rbiBondClosureBeforeMaturDTO1.setCredBondTdsAcc(stmt.getString(9));
                            rbiBondClosureBeforeMaturDTO1.setDebBondSusAccFlag(stmt.getString(12));
                            rbiBondClosureBeforeMaturDTO1.setDebBondSundryAccFlag(stmt.getString(13));
                            rbiBondClosureBeforeMaturDTO1.setCredBondTdsAccFlag(stmt.getString(14));

                        }

                        ResponseDataDTO offAccVal = officeAccountValidation(rbiBondClosureBeforeMaturDTO1);

                        rbiBondClosureBefMaturDetMap.put("bondDetails", rbiBondClosureBeforeMaturDTO1);

                        ResultSet rs1 = (ResultSet) stmt.getObject(10);

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
                          responseDataDTO.setResult(rbiBondClosureBefMaturDetMap);

                        if(offAccVal.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)){
                            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                        }
                        else{
                            responseDataDTO.setMessage(offAccVal.getMessage());
                        }
                    }

                }
            }
        }
        catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            responseDataDTO.setMessage(e.getMessage());
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        Map<String, Object> rbiBondClosureBefMaturDetMap = new HashMap<>();

        RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDTO();
        List<RbiBondClosureBeforeMaturDTO> rbiBondBLAList = new ArrayList<>();
        List<RbiBondClosureBeforeMaturDTO> rbiBondBondSubsList = new ArrayList<>();


        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;

                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_SP_VER_DEL_SEL.getProcName());
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                resultMsg = stmt.getString(index);
                responseDataDTO.setMessage(resultMsg);


                if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                    if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("bla")) {

                        ResultSet rs = (ResultSet) stmt.getObject(index - 2);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("bla_no"));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("bond_holder_name"));
                            rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString("mode_of_holding"));
//                            rbiBondClosureBeforeMaturDTO1.setVerifiedStatus(rs.getString("verified_status"));
                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }

                        responseDataDTO.setResult(rbiBondBLAList);

                    }
                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("boSubsId")) {

                        ResultSet rs = (ResultSet) stmt.getObject(index - 2);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDTO();
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

                        ResultSet rs = (ResultSet) stmt.getObject(index - 2);

                        if(rs != null) {
                            while (rs.next()) {

                                rbiBondClosureBeforeMaturDTO1.setSubsDate(rs.getString("subscription_date"));
                                rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString("subscription_id"));
                                rbiBondClosureBeforeMaturDTO1.setSubsAmt(rs.getString("subscription_amount"));
                                rbiBondClosureBeforeMaturDTO1.setBondType(rs.getString("bond_type"));
                                rbiBondClosureBeforeMaturDTO1.setMaturityDate(rs.getString("maturity_date"));
                                rbiBondClosureBeforeMaturDTO1.setIntPaidUpto(rs.getString("interest_paid_up_to"));
                                rbiBondClosureBeforeMaturDTO1.setIntCalFromDate(rs.getString("interest_cal_from_date"));
                                rbiBondClosureBeforeMaturDTO1.setIntCalToDate(rs.getString("interest_cal_to_date"));
                                rbiBondClosureBeforeMaturDTO1.setLastHalfYrIntRate(rs.getString("Last_half_Year_per_int_Rate"));
                                rbiBondClosureBeforeMaturDTO1.setLastHalfYrIntAmt(rs.getString("Last_half_Year_per_int_amt"));
                                rbiBondClosureBeforeMaturDTO1.setPenality(rs.getString("penalty_to_be_recovered"));
                                rbiBondClosureBeforeMaturDTO1.setNetIntLastHalfYr(rs.getString("net_int_last_half_year_per"));
                                rbiBondClosureBeforeMaturDTO1.setPendIntLastHalfYr(rs.getString("pending_interest"));
                                rbiBondClosureBeforeMaturDTO1.setTotIntBefTDS(rs.getString("total_interest_before_tds"));
                                rbiBondClosureBeforeMaturDTO1.setTdsAmt(rs.getString("tds_amount"));
                                rbiBondClosureBeforeMaturDTO1.setTotIntAftTDS(rs.getString("total_interest_after_tds"));
                                rbiBondClosureBeforeMaturDTO1.setAmtPaid(rs.getString("total_amount_to_be_paid"));
                                rbiBondClosureBeforeMaturDTO1.setModeOfInterestPay(rs.getString("payment_mode"));
                                rbiBondClosureBeforeMaturDTO1.setCredAccNum(rs.getString("operative_acc_num"));
                                rbiBondClosureBeforeMaturDTO1.setOprAccBrId(rs.getString("oper_acc_branch_id"));
                                rbiBondClosureBeforeMaturDTO1.setBankName(rs.getString("bank_name"));
                                rbiBondClosureBeforeMaturDTO1.setBrCode(rs.getString("branch_name"));
                                rbiBondClosureBeforeMaturDTO1.setIfscCode(rs.getString("ifsc_code"));
                                rbiBondClosureBeforeMaturDTO1.setPayeeName(rs.getString("payee_name"));
                                rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("single_bla_no"));
                                rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("single_hldr_name"));
                                rbiBondClosureBeforeMaturDTO1.setSecondJointBondHolderBLA(rs.getString("sec_joint_hldr_bla_no"));
                                rbiBondClosureBeforeMaturDTO1.setSecondJointBondHolderName(rs.getString("sec_joint_hldr_name"));
                                rbiBondClosureBeforeMaturDTO1.setFirstJointBondHolderBLA(rs.getString("first_joint_hldr_bla_no"));
                                rbiBondClosureBeforeMaturDTO1.setFirstJointBondHolderName(rs.getString("first_joint_hldr_name"));
                                rbiBondClosureBeforeMaturDTO1.setModeOfHoldingCode(rs.getString("mode_of_holding_1"));
                                rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString("mode_of_holding"));
                                rbiBondClosureBeforeMaturDTO1.setHolderType(rs.getString("HOLDER_TYPE"));
                                rbiBondClosureBeforeMaturDTO1.setIsNri(rs.getString("is_nri"));
                                rbiBondClosureBeforeMaturDTO1.setClosureRemarks(rs.getString("closure_remarks"));
                                rbiBondClosureBeforeMaturDTO1.setIslastYearIntPaid(rs.getString("last_half_year_int_is_paid"));
                            }

//                            rbiBondClosureBeforeMaturDTO1.setDebBondSusAcc(stmt.getString(6));
//                            rbiBondClosureBeforeMaturDTO1.setDebBondSundryAcc(stmt.getString(7));
//                            rbiBondClosureBeforeMaturDTO1.setCredAccNumber(stmt.getString(8));
//                            rbiBondClosureBeforeMaturDTO1.setCredBondTdsAcc(stmt.getString(9));
                        }
                        rbiBondClosureBefMaturDetMap.put("bondDetails", rbiBondClosureBeforeMaturDTO1);


                        RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO2 = new RbiBondClosureBeforeMaturDTO();
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
    public ResponseDataDTO getInqDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {


            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            Connection connection = null;
            CallableStatement stmt = null;
            String resultMsg = "";
            Map<String, Object> rbiBondClosureBefMaturDetMap = new HashMap<>();

            RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDTO();
            List<RbiBondClosureBeforeMaturDTO> rbiBondBLAList = new ArrayList<>();
            List<RbiBondClosureBeforeMaturDTO> rbiBondBondSubsList = new ArrayList<>();


            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {

                    int index = 1;

                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_SP_INQ.getProcName());
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
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);

                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                    resultMsg = stmt.getString(index);
                    responseDataDTO.setMessage(resultMsg);


                    if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                        if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("bla")) {

                            ResultSet rs = (ResultSet) stmt.getObject(index - 2);

                            while (rs.next()) {
                                rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDTO();
                                rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("bla_no"));
                                rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("bond_holder_name"));
                                rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString("mode_of_holding"));
//                                rbiBondClosureBeforeMaturDTO1.setVerifiedStatus(rs.getString("verified_status"));
                                rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                            }

                            responseDataDTO.setResult(rbiBondBLAList);

                        }
                        else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("boSubsId")) {

                            ResultSet rs = (ResultSet) stmt.getObject(index - 2);

                            while (rs.next()) {
                                rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureBeforeMaturDTO();
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

                            ResultSet rs = (ResultSet) stmt.getObject(index - 2);

                            if(rs != null) {
                                while (rs.next()) {

                                    rbiBondClosureBeforeMaturDTO1.setSubsDate(rs.getString("subscription_date"));
                                    rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString("subscription_id"));
                                    rbiBondClosureBeforeMaturDTO1.setSubsAmt(rs.getString("subscription_amount"));
                                    rbiBondClosureBeforeMaturDTO1.setBondType(rs.getString("bond_type"));
                                    rbiBondClosureBeforeMaturDTO1.setMaturityDate(rs.getString("maturity_date"));
                                    rbiBondClosureBeforeMaturDTO1.setIntPaidUpto(rs.getString("interest_paid_up_to"));
                                    rbiBondClosureBeforeMaturDTO1.setIntCalFromDate(rs.getString("interest_cal_from_date"));
                                    rbiBondClosureBeforeMaturDTO1.setIntCalToDate(rs.getString("interest_cal_to_date"));
                                    rbiBondClosureBeforeMaturDTO1.setLastHalfYrIntRate(rs.getString("Last_half_Year_per_int_Rate"));
                                    rbiBondClosureBeforeMaturDTO1.setLastHalfYrIntAmt(rs.getString("Last_half_Year_per_int_amt"));
                                    rbiBondClosureBeforeMaturDTO1.setPenality(rs.getString("penalty_to_be_recovered"));
                                    rbiBondClosureBeforeMaturDTO1.setNetIntLastHalfYr(rs.getString("net_int_last_half_year_per"));
                                    rbiBondClosureBeforeMaturDTO1.setPendIntLastHalfYr(rs.getString("pending_interest"));
                                    rbiBondClosureBeforeMaturDTO1.setTotIntBefTDS(rs.getString("total_interest_before_tds"));
                                    rbiBondClosureBeforeMaturDTO1.setTdsAmt(rs.getString("tds_amount"));
                                    rbiBondClosureBeforeMaturDTO1.setTotIntAftTDS(rs.getString("total_interest_after_tds"));
                                    rbiBondClosureBeforeMaturDTO1.setAmtPaid(rs.getString("total_amount_to_be_paid"));
                                    rbiBondClosureBeforeMaturDTO1.setModeOfInterestPay(rs.getString("payment_mode"));
                                    rbiBondClosureBeforeMaturDTO1.setCredAccNum(rs.getString("operative_acc_num"));
                                    rbiBondClosureBeforeMaturDTO1.setOprAccBrId(rs.getString("oper_acc_branch_id"));
                                    rbiBondClosureBeforeMaturDTO1.setBankName(rs.getString("bank_name"));
                                    rbiBondClosureBeforeMaturDTO1.setBrCode(rs.getString("branch_name"));
                                    rbiBondClosureBeforeMaturDTO1.setIfscCode(rs.getString("ifsc_code"));
                                    rbiBondClosureBeforeMaturDTO1.setPayeeName(rs.getString("payee_name"));
                                    rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString("single_bla_no"));
                                    rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString("single_hldr_name"));
                                    rbiBondClosureBeforeMaturDTO1.setSecondJointBondHolderBLA(rs.getString("sec_joint_hldr_bla_no"));
                                    rbiBondClosureBeforeMaturDTO1.setSecondJointBondHolderName(rs.getString("sec_joint_hldr_name"));
                                    rbiBondClosureBeforeMaturDTO1.setFirstJointBondHolderBLA(rs.getString("first_joint_hldr_bla_no"));
                                    rbiBondClosureBeforeMaturDTO1.setFirstJointBondHolderName(rs.getString("first_joint_hldr_name"));
                                    rbiBondClosureBeforeMaturDTO1.setModeOfHoldingCode(rs.getString("mode_of_holding_1"));
                                    rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString("mode_of_holding"));
                                    rbiBondClosureBeforeMaturDTO1.setHolderType(rs.getString("HOLDER_TYPE"));
                                    rbiBondClosureBeforeMaturDTO1.setIsNri(rs.getString("is_nri"));
                                    rbiBondClosureBeforeMaturDTO1.setClosureRemarks(rs.getString("closure_remarks"));
                                    rbiBondClosureBeforeMaturDTO1.setIslastYearIntPaid(rs.getString("last_half_year_int_is_paid"));
                                }

//                                rbiBondClosureBeforeMaturDTO1.setDebBondSusAcc(stmt.getString(6));
//                                rbiBondClosureBeforeMaturDTO1.setDebBondSundryAcc(stmt.getString(7));
//                                rbiBondClosureBeforeMaturDTO1.setCredAccNumber(stmt.getString(8));
//                                rbiBondClosureBeforeMaturDTO1.setCredBondTdsAcc(stmt.getString(9));
                            }
                            rbiBondClosureBefMaturDetMap.put("bondDetails", rbiBondClosureBeforeMaturDTO1);


                            RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO2 = new RbiBondClosureBeforeMaturDTO();
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
    public ResponseDataDTO add(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO1) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        Map<String, String> bondDetMap = (Map<String, String>) rbiBondClosureBeforeMaturDTO1.getBondDetails();

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO = mapper.convertValue(bondDetMap, RbiBondClosureBeforeMaturDTO.class);

        if(rbiBondClosureBeforeMaturDTO.getModeOfHoldingCode().equalsIgnoreCase("MH002") || rbiBondClosureBeforeMaturDTO.getModeOfHoldingCode().equalsIgnoreCase("MH003")){

            rbiBondClosureBeforeMaturDTO.setBlaNo(null);
            rbiBondClosureBeforeMaturDTO.setBondHolderName(null);

        }

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_SP_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModuleId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getClosureType());
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
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getIslastYearIntPaid());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getPenality());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getNetIntLastHalfYr());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getPendIntLastHalfYr());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getTotIntBefTDS());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getTdsAmt());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getTotIntAftTDS());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getAmtPaid());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModeOfInterestPay());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getOprAccBrId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getCredAccNum());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getClosureRemarks());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBankName());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBrCode());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getIfscCode());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getPayeeName());
                stmt.setString(index++, null); //cred acc type in
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getCredBondTdsAcc());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getCredAccNumber());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getDebBondSusAcc());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getDebBondSundryAcc());
                stmt.setString(index++, null); //deb acc type in
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                resultMsg = stmt.getString(index);

                if(resultMsg.equalsIgnoreCase(CommonConstants.SUCCESS_CONST)){

                    responseDataDTO.setResult(stmt.getString(index-2));

                }
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
    public ResponseDataDTO delete(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_SP_DEL.getProcName());
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
    public ResponseDataDTO verify(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        List<GbmTransactionMasterDto> gbmTransactionMasterDtos = new ArrayList();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_SP_VER.getProcName());
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_BM_SP_VER_RES.getProcName());
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

    private ResponseDataDTO officeAccountValidation(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO){


        String finacleValidationReqString = "TDS" + "^" + rbiBondClosureBeforeMaturDTO.getCredBondTdsAcc() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + rbiBondClosureBeforeMaturDTO.getCredBondTdsAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getCredBondTdsAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
        finacleValidationReqString += "!" + "SUS" + "^" + rbiBondClosureBeforeMaturDTO.getDebBondSusAcc() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + rbiBondClosureBeforeMaturDTO.getDebBondSusAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getDebBondSusAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
        finacleValidationReqString += "!" + "SUN" + "^" + rbiBondClosureBeforeMaturDTO.getDebBondSundryAcc() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + rbiBondClosureBeforeMaturDTO.getDebBondSundryAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getDebBondSundryAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));

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
