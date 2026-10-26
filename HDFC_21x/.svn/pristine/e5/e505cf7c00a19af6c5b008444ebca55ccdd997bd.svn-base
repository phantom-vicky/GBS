package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.BondActualInterestPaymentDAO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.util.JDBCTemplateUtil;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import jakarta.annotation.PostConstruct;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

@Repository
public class BondActualInterestPaymentDAOImpl implements BondActualInterestPaymentDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BondActualInterestPaymentDAOImpl.class);
    private static final String RESULT_SET_EMPTY = "Result Set is Empty";
    private static final String CONNECTION_EMPTY = "Connection is Empty";

    private final ConnectionPoolFactory connectionPoolFactory;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JDBCTemplateUtil jDBCTemplateUtil;

    @PostConstruct
    public void init() {
        jDBCTemplateUtil = new JDBCTemplateUtil(jdbcTemplate);
    }

    public BondActualInterestPaymentDAOImpl(final ConnectionPoolFactory connectionPoolFactory) {
        this.connectionPoolFactory = connectionPoolFactory;
    }

    @Override
    public ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new LinkedList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACTUAL_INT_PAYMENT_ADD_VAL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getHalfYear());
                statement.setString(index++, paymentRequestDTO.getYear());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info("{}: {}",CommonConstants.STATUS_MESSAGE , resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(6);
                    if (paymentRequestDTO.getSchemeCode() == null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setSchemeCode(resultSet.getString(1));
                            schemeCodeDTO.setSchemeName(resultSet.getString(2));
                            schemeCodeDTO.setSchemeStatus(resultSet.getString(3));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else if (paymentRequestDTO.getHalfYear() == null && paymentRequestDTO.getYear() == null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setHalfYear(resultSet.getString(1));
                            schemeCodeDTO.setYear(String.valueOf(LocalDate.now().getYear()));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else if (paymentRequestDTO.getYear() != null && paymentRequestDTO.getHalfYear() != null
                            && paymentRequestDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO bondTrailRunDTO = new BondActualInterestPaymentRequestDTO();
                            bondTrailRunDTO.setModeOfPayment(resultSet.getString(1));
                            bondTrailRunDTO.setBranchRefNo(resultSet.getString(2));
                            bondTrailRunDTO.setBlaNo(resultSet.getString(3));
                            bondTrailRunDTO.setRefNo(resultSet.getString(4));
                            bondTrailRunDTO.setAppName1(resultSet.getString(5));
                            bondTrailRunDTO.setAppName2(resultSet.getString(6));
                            bondTrailRunDTO.setAppName3(resultSet.getString(7));
                            bondTrailRunDTO.setAddress1(resultSet.getString(8));
                            bondTrailRunDTO.setAddress2(resultSet.getString(9));
                            bondTrailRunDTO.setRecName(resultSet.getString(10));
                            bondTrailRunDTO.setRecParentName(resultSet.getString(11));
                            bondTrailRunDTO.setPinCode(resultSet.getString(12));
                            bondTrailRunDTO.setPhoneNo(resultSet.getString(13));
                            bondTrailRunDTO.setAppStatus(resultSet.getString(14));
                            bondTrailRunDTO.setInvestmentAmount(resultSet.getString(15));
                            bondTrailRunDTO.setCfd(resultSet.getString(16));
                            bondTrailRunDTO.setMaturityDate(resultSet.getString(17));
                            bondTrailRunDTO.setAccountNo(resultSet.getString(18));
                            bondTrailRunDTO.setSubscriptionCount(resultSet.getString(19));
                            bondTrailRunDTO.setBankSortCode(resultSet.getString(20));
                            bondTrailRunDTO.setBankName(resultSet.getString(21));
                            bondTrailRunDTO.setPanNoName(resultSet.getString(22));
                            bondTrailRunDTO.setInterestPayOut8Per(resultSet.getString(23));
                            bondTrailRunDTO.setNonCumulativeInt(resultSet.getString(24));
                            bondTrailRunDTO.setCumulativeInt(resultSet.getString(25));
                            bondTrailRunDTO.setInterestPayOut7Per(resultSet.getString(26));
                            bondTrailRunDTO.setNonCumulativeIntFor7(resultSet.getString(27));
                            bondTrailRunDTO.setCumulativeIntFor7(resultSet.getString(28));
                            bondTrailRunDTO.setInterestPayOut85Per(resultSet.getString(29));
                            bondTrailRunDTO.setNonCumulativeIntFor85(resultSet.getString(30));
                            bondTrailRunDTO.setCumulativeIntFor85(resultSet.getString(31));
                            bondTrailRunDTO.setTotal(resultSet.getString(32));
                            bondTrailRunDTO.setInOperativePAN(resultSet.getString(33));
                            bondTrailRunDTO.setNonFilers(resultSet.getString(34));
                            bondTrailRunDTO.setAge(resultSet.getString(35));
                            bondTrailRunDTO.setTaxableLimit(resultSet.getString(36));
                            bondTrailRunDTO.setWaiverDocument(resultSet.getString(37));
                            bondTrailRunDTO.setWaiverDocumentRefNo(resultSet.getString(38));
                            bondTrailRunDTO.setRrbTDSRate(resultSet.getString(39));
                            bondTrailRunDTO.setThresholdExceedAmount(resultSet.getString(40));
                            bondTrailRunDTO.setThresholdExceedTDSAmount(resultSet.getString(41));
                            bondTrailRunDTO.setExemptionRate(resultSet.getString(42));
                            bondTrailRunDTO.setExemptionAmount(resultSet.getString(43));
                            bondTrailRunDTO.setExemptionTDSAmount(resultSet.getString(44));
                            bondTrailRunDTO.setSurcharge(resultSet.getString(45));
                            bondTrailRunDTO.setEduCess(resultSet.getString(46));
                            bondTrailRunDTO.setBaseRateAmount(resultSet.getString(47));
                            bondTrailRunDTO.setSurchargeAmount(resultSet.getString(48));
                            bondTrailRunDTO.setEduCessAmount(resultSet.getString(49));
                            bondTrailRunDTO.setTdsAmount(resultSet.getString(50));
                            bondTrailRunDTO.setTdsRecoveryAmount(resultSet.getString(51));
                            bondTrailRunDTO.setTdsRefundAmount(resultSet.getString(52));
                            bondTrailRunDTO.setIntRate(resultSet.getString(53));
                            bondTrailRunDTO.setLastIntPaid(resultSet.getString(54));
                            bondTrailRunDTO.setPendingInterest(resultSet.getString(55));
                            bondTrailRunDTO.setCurrentHalfYearlyInterest(resultSet.getString(56));
                            bondTrailRunDTO.setFutureInterest(resultSet.getString(57));
                            bondTrailRunDTO.setGrossInterestAmount(resultSet.getString(58));
                            bondTrailRunDTO.setIntPaid(resultSet.getString(59));
                            listOfResponse.add(bondTrailRunDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else
                        resultMsg = RESULT_SET_EMPTY;
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            resultMsg = ErrorMessages.DATABASE_ERROR;
            LOGGER.error("Database Error: resultMsg={}, exception={}",resultMsg, e.getMessage(), e);
        } finally {
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addSubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACTUAL_INT_PAYMENT_ADD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getHalfYear());
                statement.setString(index++, paymentRequestDTO.getYear());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info("{}: {}",CommonConstants.STATUS_MESSAGE , resultMsg);
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            resultMsg = ErrorMessages.DATABASE_ERROR;
            LOGGER.error("Database Error: resultMsg={}, exception={}",resultMsg, e.getMessage(), e);
        } finally {
            connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new LinkedList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACTUAL_INT_PAYMENT_INQUIRY.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getHalfYear());
                statement.setString(index++, paymentRequestDTO.getYear());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info("{}: {}",CommonConstants.STATUS_MESSAGE, resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(7);
                    if (paymentRequestDTO.getSchemeCode() == null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setSchemeCode(resultSet.getString(1));
                            schemeCodeDTO.setSchemeName(resultSet.getString(2));
                            schemeCodeDTO.setSchemeStatus(resultSet.getString(3));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                    } else if (paymentRequestDTO.getHalfYear() == null && paymentRequestDTO.getYear() == null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setHalfYear(resultSet.getString(1));
                            schemeCodeDTO.setYear(String.valueOf(LocalDate.now().getYear()));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                    } else if (null!=paymentRequestDTO&&paymentRequestDTO.getYear() != null && paymentRequestDTO.getHalfYear() != null
                            && paymentRequestDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO bondTrailRunDTO = new BondActualInterestPaymentRequestDTO();
                            // Mapping based on new query structure: bla_no, Appl_Name1, Appl_Name2, Appl_Name3, investment_amt, Account_No, pan_no, tds_Amount, payment_date, total_amount_to_be_paid, tran_id, Payment_Status, reject_reason
                            bondTrailRunDTO.setBlaNo(resultSet.getString(1)); // bla_no
                            bondTrailRunDTO.setAppName1(resultSet.getString(2)); // Appl_Name1
                            bondTrailRunDTO.setAppName2(resultSet.getString(3)); // Appl_Name2
                            bondTrailRunDTO.setAppName3(resultSet.getString(4)); // Appl_Name3
                            bondTrailRunDTO.setInvestmentAmount(resultSet.getString(5)); // investment_amt
                            bondTrailRunDTO.setAccountNo(resultSet.getString(6)); // Account_No
                            bondTrailRunDTO.setPanNoName(resultSet.getString(7)); // pan_no
                            bondTrailRunDTO.setTdsAmount(resultSet.getString(8)); // tds_Amount
                            bondTrailRunDTO.setPaymentDate(resultSet.getString(9)); // payment_date (null)
                            bondTrailRunDTO.setTotalAmtToBePaid(resultSet.getString(10)); // total_amount_to_be_paid (null)
                            bondTrailRunDTO.setRefNo(resultSet.getString(11)); // tran_id (null, mapped to refNo)
                            bondTrailRunDTO.setPaymentStatus(resultSet.getString(12)); // Payment_Status (NULL)
                            bondTrailRunDTO.setReturnReason(resultSet.getString(13)); // reject_reason (NULL, mapped to returnReason)
                            listOfResponse.add(bondTrailRunDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                    } else
                        resultMsg = RESULT_SET_EMPTY;
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            resultMsg = ErrorMessages.DATABASE_ERROR;
            LOGGER.error("Database Error: resultMsg={}, exception={}",resultMsg, e.getMessage(), e);
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new LinkedList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACTUAL_INT_PAYMENT_VERIFY_SEL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getHalfYear());
                statement.setString(index++, paymentRequestDTO.getYear());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info("{}: {}",CommonConstants.STATUS_MESSAGE , resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(6);
                    if (paymentRequestDTO.getSchemeCode() == null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setSchemeCode(resultSet.getString(1));
                            schemeCodeDTO.setSchemeName(resultSet.getString(2));
                            schemeCodeDTO.setSchemeStatus(resultSet.getString(3));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else if (paymentRequestDTO.getHalfYear() == null && paymentRequestDTO.getYear() == null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setHalfYear(resultSet.getString(1));
                            schemeCodeDTO.setYear(String.valueOf(LocalDate.now().getYear()));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else if (paymentRequestDTO.getYear() != null && paymentRequestDTO.getHalfYear() != null
                            && paymentRequestDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO bondTrailRunDTO = new BondActualInterestPaymentRequestDTO();
                            bondTrailRunDTO.setModeOfPayment(resultSet.getString(1));
                            bondTrailRunDTO.setBranchRefNo(resultSet.getString(2));
                            bondTrailRunDTO.setBlaNo(resultSet.getString(3));
                            bondTrailRunDTO.setRefNo(resultSet.getString(4));
                            bondTrailRunDTO.setAppName1(resultSet.getString(5));
                            bondTrailRunDTO.setAppName2(resultSet.getString(6));
                            bondTrailRunDTO.setAppName3(resultSet.getString(7));
                            bondTrailRunDTO.setAddress1(resultSet.getString(8));
                            bondTrailRunDTO.setAddress2(resultSet.getString(9));
                            bondTrailRunDTO.setRecName(resultSet.getString(10));
                            bondTrailRunDTO.setRecParentName(resultSet.getString(11));
                            bondTrailRunDTO.setPinCode(resultSet.getString(12));
                            bondTrailRunDTO.setPhoneNo(resultSet.getString(13));
                            bondTrailRunDTO.setAppStatus(resultSet.getString(14));
                            bondTrailRunDTO.setInvestmentAmount(resultSet.getString(15));
                            bondTrailRunDTO.setCfd(resultSet.getString(16));
                            bondTrailRunDTO.setMaturityDate(resultSet.getString(17));
                            bondTrailRunDTO.setAccountNo(resultSet.getString(18));
                            bondTrailRunDTO.setSubscriptionCount(resultSet.getString(19));
                            bondTrailRunDTO.setBankSortCode(resultSet.getString(20));
                            bondTrailRunDTO.setBankName(resultSet.getString(21));
                            bondTrailRunDTO.setPanNoName(resultSet.getString(22));
                            bondTrailRunDTO.setInterestPayOut8Per(resultSet.getString(23));
                            bondTrailRunDTO.setNonCumulativeInt(resultSet.getString(24));
                            bondTrailRunDTO.setCumulativeInt(resultSet.getString(25));
                            bondTrailRunDTO.setInterestPayOut7Per(resultSet.getString(26));
                            bondTrailRunDTO.setNonCumulativeIntFor7(resultSet.getString(27));
                            bondTrailRunDTO.setCumulativeIntFor7(resultSet.getString(28));
                            bondTrailRunDTO.setInterestPayOut85Per(resultSet.getString(29));
                            bondTrailRunDTO.setNonCumulativeIntFor85(resultSet.getString(30));
                            bondTrailRunDTO.setCumulativeIntFor85(resultSet.getString(31));
                            bondTrailRunDTO.setTotal(resultSet.getString(32));
                            bondTrailRunDTO.setInOperativePAN(resultSet.getString(33));
                            bondTrailRunDTO.setNonFilers(resultSet.getString(34));
                            bondTrailRunDTO.setAge(resultSet.getString(35));
                            bondTrailRunDTO.setTaxableLimit(resultSet.getString(36));
                            bondTrailRunDTO.setWaiverDocument(resultSet.getString(37));
                            bondTrailRunDTO.setWaiverDocumentRefNo(resultSet.getString(38));
                            bondTrailRunDTO.setRrbTDSRate(resultSet.getString(39));
                            bondTrailRunDTO.setThresholdExceedAmount(resultSet.getString(40));
                            bondTrailRunDTO.setThresholdExceedTDSAmount(resultSet.getString(41));
                            bondTrailRunDTO.setExemptionRate(resultSet.getString(42));
                            bondTrailRunDTO.setExemptionAmount(resultSet.getString(43));
                            bondTrailRunDTO.setExemptionTDSAmount(resultSet.getString(44));
                            bondTrailRunDTO.setSurcharge(resultSet.getString(45));
                            bondTrailRunDTO.setEduCess(resultSet.getString(46));
                            bondTrailRunDTO.setBaseRateAmount(resultSet.getString(47));
                            bondTrailRunDTO.setSurchargeAmount(resultSet.getString(48));
                            bondTrailRunDTO.setEduCessAmount(resultSet.getString(49));
                            bondTrailRunDTO.setTdsAmount(resultSet.getString(50));
                            bondTrailRunDTO.setTdsRecoveryAmount(resultSet.getString(51));
                            bondTrailRunDTO.setTdsRefundAmount(resultSet.getString(52));
                            bondTrailRunDTO.setIntRate(resultSet.getString(53));
                            bondTrailRunDTO.setLastIntPaid(resultSet.getString(54));
                            bondTrailRunDTO.setPendingInterest(resultSet.getString(55));
                            bondTrailRunDTO.setCurrentHalfYearlyInterest(resultSet.getString(56));
                            bondTrailRunDTO.setFutureInterest(resultSet.getString(57));
                            bondTrailRunDTO.setGrossInterestAmount(resultSet.getString(58));
                            bondTrailRunDTO.setIntPaid(resultSet.getString(59));
                            bondTrailRunDTO.setPaymentDate(resultSet.getString(60));
                            bondTrailRunDTO.setReturnReason(resultSet.getString(61));
                            bondTrailRunDTO.setReturnAmt(resultSet.getString(62));
                            bondTrailRunDTO.setReInitiatedAmt(resultSet.getString(63));
                            bondTrailRunDTO.setReInitiatedDate(resultSet.getString(64));
                            bondTrailRunDTO.setPaymentDateOfReturn(resultSet.getString(65));
                            bondTrailRunDTO.setReInitiatedPaymentMode(resultSet.getString(66));
                            listOfResponse.add(bondTrailRunDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else
                        resultMsg = RESULT_SET_EMPTY;
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            resultMsg = ErrorMessages.DATABASE_ERROR;
            LOGGER.error("Database Error: resultMsg={}, exception={}",resultMsg, e.getMessage(), e);
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondACHFileWrapperDTO fileWrapperDTO = new BondACHFileWrapperDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACTUAL_INT_PAYMENT_VERIFY.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getHalfYear());
                statement.setString(index++, paymentRequestDTO.getYear());
                if (!ObjectUtils.isEmpty(paymentRequestDTO.getRecordsToBeVerified())
                        && !paymentRequestDTO.getRecordsToBeVerified().isEmpty())
                    statement.setArray(index++, generateArrayForRejectBondInterestPayments(connection, new ArrayList<>(new HashSet<>(paymentRequestDTO.getRecordsToBeVerified()))));
                else
                    statement.setArray(index++, generateArrayForRejectBondInterestPayments(connection, paymentRequestDTO.getRecordsToBeVerified()));
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info("{}: {}",CommonConstants.STATUS_MESSAGE , resultMsg);

                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(7);
                    if (resultSet != null) {
                        BondACHFileHeaderDTO headerDTO = new BondACHFileHeaderDTO();
                        while (resultSet.next()) {
                            headerDTO.setAchTransactionCode(resultSet.getString(1));
                            headerDTO.setControl(resultSet.getString(2));
                            headerDTO.setUserName(resultSet.getString(3));
                            headerDTO.setControl1(resultSet.getString(4));
                            headerDTO.setAchFileNumber(resultSet.getString(5));
                            headerDTO.setControl2(resultSet.getString(6));
                            headerDTO.setControl3(resultSet.getString(7));
                            headerDTO.setLedgerFolioNumber(resultSet.getString(8));
                            headerDTO.setUserDefLimit(resultSet.getString(9));
                            headerDTO.setTotalAmtInPaise(resultSet.getString(10));
                            headerDTO.setSettlementDate(resultSet.getString(11));
                            headerDTO.setReserved1(resultSet.getString(12));
                            headerDTO.setReserved2(resultSet.getString(13));
                            headerDTO.setFiller1(resultSet.getString(14));
                            headerDTO.setUserNumber(resultSet.getString(15));
                            headerDTO.setUserReference(resultSet.getString(16));
                            headerDTO.setSponsorBankIFSCMICRIIN(resultSet.getString(17));
                            headerDTO.setUserBankAccountNumber(resultSet.getString(18));
                            headerDTO.setTotalItems(resultSet.getString(19));
                            headerDTO.setSettlementCycle(resultSet.getString(20));
                            headerDTO.setFiller2(resultSet.getString(21));
                        }
                        fileWrapperDTO.setAchFileHeaderDTO(headerDTO);
                    }
                    resultSet = (ResultSet) statement.getObject(8);
                    if (resultSet != null) {
                        List<BondACHFileDetailsDTO> listOfACHDetailsDTO = new LinkedList<>();
                        while (resultSet.next()) {
                            BondACHFileDetailsDTO detailsDTO = new BondACHFileDetailsDTO();
                            detailsDTO.setAchTransactionCode(resultSet.getString(1));
                            detailsDTO.setControl(resultSet.getString(2));
                            detailsDTO.setDestinationAccountType(resultSet.getString(3));
                            detailsDTO.setLedgerFolioNumber(resultSet.getString(4));
                            detailsDTO.setControl1(resultSet.getString(5));
                            detailsDTO.setBeneficiaryAccHolderName(resultSet.getString(6));
                            detailsDTO.setControl2(resultSet.getString(7));
                            detailsDTO.setControl3(resultSet.getString(8));
                            detailsDTO.setUserName(resultSet.getString(9));
                            detailsDTO.setControl4(resultSet.getString(10));
                            detailsDTO.setAmt(resultSet.getString(11));
                            detailsDTO.setReserved1(resultSet.getString(12));
                            detailsDTO.setReserved2(resultSet.getString(13));
                            detailsDTO.setReserved3(resultSet.getString(14));
                            detailsDTO.setReserved4(resultSet.getString(15));
                            detailsDTO.setDestinationBankIFSCMICRIIN(resultSet.getString(16));
                            detailsDTO.setBeneficiaryBankAccNo(resultSet.getString(17));
                            detailsDTO.setSponsorBankIFSCMICRIIN(resultSet.getString(18));
                            detailsDTO.setUserNo(resultSet.getString(19));
                            detailsDTO.setTxnRef(resultSet.getString(20));
                            detailsDTO.setPdtType(resultSet.getString(21));
                            detailsDTO.setBeneficiaryArNo(resultSet.getString(22));
                            detailsDTO.setUMRN(resultSet.getString(23));
                            detailsDTO.setFiller(resultSet.getString(24));
                            listOfACHDetailsDTO.add(detailsDTO);
                        }
                        fileWrapperDTO.setAchFileDetailsList(listOfACHDetailsDTO);
                    }
                    fileWrapperDTO.setFileName(statement.getString(9));
                    responseDataDTO.setResult(fileWrapperDTO);
                    responseDataDTO.setMessage(resultMsg);
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            resultMsg = ErrorMessages.DATABASE_ERROR;
            LOGGER.error("Database Error: resultMsg={}, exception={}",resultMsg, e.getMessage(), e);
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO,
                                      String schemeCode, String halfYear, String year) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACTUAL_INT_PAYMENT_UPLOAD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, schemeCode);
                statement.setString(index++, halfYear);
                statement.setString(index++, year);
                statement.setArray(index++, generateArrayForUploadInterestPaymentFile(connection, bondInterestPaymentFileDTO.getFileDetailsDTO()));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info("{}: {}",CommonConstants.STATUS_MESSAGE , resultMsg);
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            resultMsg = ErrorMessages.DATABASE_ERROR;
            LOGGER.error("Database Error: resultMsg={}, exception={}",resultMsg, e.getMessage(), e);
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO downloadMergedUnMerged(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new LinkedList<>();
        String dbFlag = "MERGED".equalsIgnoreCase(paymentRequestDTO.getFlag()) ? "M" : "UM";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACTUAL_INT_PAYMENT_MERGED_UNMERGED.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getHalfYear());
                statement.setString(index++, paymentRequestDTO.getYear());
                statement.setString(index++, dbFlag);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info("{}: {}",CommonConstants.STATUS_MESSAGE , resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(7);
                    if (paymentRequestDTO.getYear() != null && paymentRequestDTO.getHalfYear() != null
                            && paymentRequestDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            if(null!=dbFlag&&dbFlag.equalsIgnoreCase("UM")) {
                                BondActualInterestPaymentRequestDTO bondTrailRunDTO = new BondActualInterestPaymentRequestDTO();
                                bondTrailRunDTO.setModeOfPayment(resultSet.getString(1));
                                bondTrailRunDTO.setBranchRefNo(resultSet.getString(2));
                                bondTrailRunDTO.setBlaNo(resultSet.getString(3));
                                bondTrailRunDTO.setRefNo(resultSet.getString(4));
                                bondTrailRunDTO.setAppName1(resultSet.getString(5));
                                bondTrailRunDTO.setAppName2(resultSet.getString(6));
                                bondTrailRunDTO.setAppName3(resultSet.getString(7));
                                bondTrailRunDTO.setAddress1(resultSet.getString(8));
                                bondTrailRunDTO.setAddress2(resultSet.getString(9));
                                bondTrailRunDTO.setRecName(resultSet.getString(10));
                                bondTrailRunDTO.setRecParentName(resultSet.getString(11));
                                bondTrailRunDTO.setPinCode(resultSet.getString(12));
                                bondTrailRunDTO.setPhoneNo(resultSet.getString(13));
                                bondTrailRunDTO.setAppStatus(resultSet.getString(14));
                                bondTrailRunDTO.setInvestmentAmount(resultSet.getString(15));
                                bondTrailRunDTO.setCfd(resultSet.getString(16));
                                bondTrailRunDTO.setMaturityDate(resultSet.getString(17));
                                bondTrailRunDTO.setAccountNo(resultSet.getString(18));
                                bondTrailRunDTO.setSubscriptionCount(resultSet.getString(19));
                                bondTrailRunDTO.setBankSortCode(resultSet.getString(20));
                                bondTrailRunDTO.setBankName(resultSet.getString(21));
                                bondTrailRunDTO.setPanNoName(resultSet.getString(22));
                                bondTrailRunDTO.setInterestPayOut8Per(resultSet.getString(23));
                                bondTrailRunDTO.setNonCumulativeInt(resultSet.getString(24));
                                bondTrailRunDTO.setCumulativeInt(resultSet.getString(25));
                                bondTrailRunDTO.setInterestPayOut7Per(resultSet.getString(26));
                                bondTrailRunDTO.setNonCumulativeIntFor7(resultSet.getString(27));
                                bondTrailRunDTO.setCumulativeIntFor7(resultSet.getString(28));
                                bondTrailRunDTO.setInterestPayOut85Per(resultSet.getString(29));
                                bondTrailRunDTO.setNonCumulativeIntFor85(resultSet.getString(30));
                                bondTrailRunDTO.setCumulativeIntFor85(resultSet.getString(31));
                                bondTrailRunDTO.setTotal(resultSet.getString(32));
                                bondTrailRunDTO.setInOperativePAN(resultSet.getString(33));
                                bondTrailRunDTO.setNonFilers(resultSet.getString(34));
                                bondTrailRunDTO.setAge(resultSet.getString(35));
                                bondTrailRunDTO.setTaxableLimit(resultSet.getString(36));
                                bondTrailRunDTO.setWaiverDocument(resultSet.getString(37));
                                bondTrailRunDTO.setWaiverDocumentRefNo(resultSet.getString(38));
                                bondTrailRunDTO.setRrbTDSRate(resultSet.getString(39));
                                bondTrailRunDTO.setThresholdExceedAmount(resultSet.getString(40));
                                bondTrailRunDTO.setThresholdExceedTDSAmount(resultSet.getString(41));
                                bondTrailRunDTO.setExemptionRate(resultSet.getString(42));
                                bondTrailRunDTO.setExemptionAmount(resultSet.getString(43));
                                bondTrailRunDTO.setExemptionTDSAmount(resultSet.getString(44));
                                bondTrailRunDTO.setSurcharge(resultSet.getString(45));
                                bondTrailRunDTO.setEduCess(resultSet.getString(46));
                                bondTrailRunDTO.setBaseRateAmount(resultSet.getString(47));
                                bondTrailRunDTO.setSurchargeAmount(resultSet.getString(48));
                                bondTrailRunDTO.setEduCessAmount(resultSet.getString(49));
                                bondTrailRunDTO.setTdsAmount(resultSet.getString(50));
                                bondTrailRunDTO.setTdsRecoveryAmount(resultSet.getString(51));
                                bondTrailRunDTO.setTdsRefundAmount(resultSet.getString(52));
                                bondTrailRunDTO.setIntRate(resultSet.getString(53));
                                bondTrailRunDTO.setLastIntPaid(resultSet.getString(54));
                                bondTrailRunDTO.setPendingInterest(resultSet.getString(55));
                                bondTrailRunDTO.setCurrentHalfYearlyInterest(resultSet.getString(56));
                                bondTrailRunDTO.setFutureInterest(resultSet.getString(57));
                                bondTrailRunDTO.setGrossInterestAmount(resultSet.getString(58));
                                bondTrailRunDTO.setIntPaid(resultSet.getString(59));
                                listOfResponse.add(bondTrailRunDTO);
                            }else{
                                BondActualInterestPaymentRequestDTO bondTrailRunDTO = new BondActualInterestPaymentRequestDTO();
                                bondTrailRunDTO.setModeOfPayment(resultSet.getString(1));
                                bondTrailRunDTO.setBlaNo(resultSet.getString(2));
                                bondTrailRunDTO.setAppName1(resultSet.getString(3));
                                bondTrailRunDTO.setAppName2(resultSet.getString(4));
                                bondTrailRunDTO.setAppName3(resultSet.getString(5));
                                bondTrailRunDTO.setAddress1(resultSet.getString(6));
                                bondTrailRunDTO.setAddress2(resultSet.getString(7));
                                bondTrailRunDTO.setRecName(resultSet.getString(8));
                                bondTrailRunDTO.setRecParentName(resultSet.getString(9));
                                bondTrailRunDTO.setPinCode(resultSet.getString(10));
                                bondTrailRunDTO.setPhoneNo(resultSet.getString(11));
                                bondTrailRunDTO.setAppStatus(resultSet.getString(12));
                                bondTrailRunDTO.setInvestmentAmount(resultSet.getString(13));
                                bondTrailRunDTO.setAccountNo(resultSet.getString(14));
                                bondTrailRunDTO.setSubscriptionCount(resultSet.getString(15));
                                bondTrailRunDTO.setBankSortCode(resultSet.getString(16));
                                bondTrailRunDTO.setBankName(resultSet.getString(17));
                                bondTrailRunDTO.setPanNoName(resultSet.getString(18));
                                bondTrailRunDTO.setInterestPayOut8Per(resultSet.getString(19));
                                bondTrailRunDTO.setNonCumulativeInt(resultSet.getString(20));
                                bondTrailRunDTO.setCumulativeInt(resultSet.getString(21));
                                bondTrailRunDTO.setInterestPayOut7Per(resultSet.getString(22));
                                bondTrailRunDTO.setNonCumulativeIntFor7(resultSet.getString(23));
                                bondTrailRunDTO.setCumulativeIntFor7(resultSet.getString(24));
                                bondTrailRunDTO.setInterestPayOut85Per(resultSet.getString(25));
                                bondTrailRunDTO.setNonCumulativeIntFor85(resultSet.getString(26));
                                bondTrailRunDTO.setCumulativeIntFor85(resultSet.getString(27));
                                bondTrailRunDTO.setTotal(resultSet.getString(28));
                                bondTrailRunDTO.setInOperativePAN(resultSet.getString(29));
                                bondTrailRunDTO.setNonFilers(resultSet.getString(30));
                                bondTrailRunDTO.setAge(resultSet.getString(31));
                                bondTrailRunDTO.setTaxableLimit(resultSet.getString(32));
                                bondTrailRunDTO.setWaiverDocument(resultSet.getString(33));
                                bondTrailRunDTO.setWaiverDocumentRefNo(resultSet.getString(34));
                                bondTrailRunDTO.setRrbTDSRate(resultSet.getString(35));
                                bondTrailRunDTO.setThresholdExceedAmount(resultSet.getString(36));
                                bondTrailRunDTO.setThresholdExceedTDSAmount(resultSet.getString(37));
                                bondTrailRunDTO.setExemptionRate(resultSet.getString(38));
                                bondTrailRunDTO.setExemptionAmount(resultSet.getString(39));
                                bondTrailRunDTO.setExemptionTDSAmount(resultSet.getString(40));
                                bondTrailRunDTO.setSurcharge(resultSet.getString(41));
                                bondTrailRunDTO.setEduCess(resultSet.getString(42));
                                bondTrailRunDTO.setBaseRateAmount(resultSet.getString(43));
                                bondTrailRunDTO.setSurchargeAmount(resultSet.getString(44));
                                bondTrailRunDTO.setEduCessAmount(resultSet.getString(45));
                                bondTrailRunDTO.setTdsAmount(resultSet.getString(46));
                                bondTrailRunDTO.setTdsRecoveryAmount(resultSet.getString(47));
                                bondTrailRunDTO.setTdsRefundAmount(resultSet.getString(48));
                                bondTrailRunDTO.setIntRate(resultSet.getString(49));
                                bondTrailRunDTO.setLastIntPaid(resultSet.getString(50));
                                bondTrailRunDTO.setPendingInterest(resultSet.getString(51));
                                bondTrailRunDTO.setCurrentHalfYearlyInterest(resultSet.getString(52));
                                bondTrailRunDTO.setFutureInterest(resultSet.getString(53));
                                bondTrailRunDTO.setGrossInterestAmount(resultSet.getString(54));
                                bondTrailRunDTO.setIntPaid(resultSet.getString(55));
                                bondTrailRunDTO.setPaymentDate(resultSet.getString(56));
                                listOfResponse.add(bondTrailRunDTO);
                            }
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else
                        resultMsg = RESULT_SET_EMPTY;
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            resultMsg = ErrorMessages.DATABASE_ERROR;
            LOGGER.error("Database Error: resultMsg={}, exception={}",resultMsg, e.getMessage(), e);
        } finally {
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACTUAL_INT_PAYMENT_DOWNLOAD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getHalfYear());
                statement.setString(index++, paymentRequestDTO.getYear());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info("{}: {}",CommonConstants.STATUS_MESSAGE , resultMsg);
                responseDataDTO.setMessage(resultMsg);
                responseDataDTO.setResult(statement.getString(6));
            } else
                resultMsg = CONNECTION_EMPTY;

        } catch (Exception e) {
            resultMsg = ErrorMessages.DATABASE_ERROR;
            LOGGER.error("Database Error: resultMsg={}, exception={}",resultMsg, e.getMessage(), e);
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondACHFileWrapperDTO fileWrapperDTO = new BondACHFileWrapperDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACTUAL_INT_PAYMENT_MANUAL_DOWNLOAD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getHalfYear());
                statement.setString(index++, paymentRequestDTO.getYear());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info("{}: {}",CommonConstants.STATUS_MESSAGE , resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(6);
                    if (resultSet != null) {
                        BondACHFileHeaderDTO headerDTO = new BondACHFileHeaderDTO();
                        while (resultSet.next()) {
                            headerDTO.setAchTransactionCode(resultSet.getString(1));
                            headerDTO.setControl(resultSet.getString(2));
                            headerDTO.setUserName(resultSet.getString(3));
                            headerDTO.setControl1(resultSet.getString(4));
                            headerDTO.setAchFileNumber(resultSet.getString(5));
                            headerDTO.setControl2(resultSet.getString(6));
                            headerDTO.setControl3(resultSet.getString(7));
                            headerDTO.setLedgerFolioNumber(resultSet.getString(8));
                            headerDTO.setUserDefLimit(resultSet.getString(9));
                            headerDTO.setTotalAmtInPaise(resultSet.getString(10));
                            headerDTO.setSettlementDate(resultSet.getString(11));
                            headerDTO.setReserved1(resultSet.getString(12));
                            headerDTO.setReserved2(resultSet.getString(13));
                            headerDTO.setFiller1(resultSet.getString(14));
                            headerDTO.setUserNumber(resultSet.getString(15));
                            headerDTO.setUserReference(resultSet.getString(16));
                            headerDTO.setSponsorBankIFSCMICRIIN(resultSet.getString(17));
                            headerDTO.setUserBankAccountNumber(resultSet.getString(18));
                            headerDTO.setTotalItems(resultSet.getString(19));
                            headerDTO.setSettlementCycle(resultSet.getString(20));
                            headerDTO.setFiller2(resultSet.getString(21));
                        }
                        fileWrapperDTO.setAchFileHeaderDTO(headerDTO);
                    }
                    resultSet = (ResultSet) statement.getObject(7);
                    if (resultSet != null) {
                        List<BondACHFileDetailsDTO> listOfACHDetailsDTO = new LinkedList<>();
                        while (resultSet.next()) {
                            BondACHFileDetailsDTO detailsDTO = new BondACHFileDetailsDTO();
                            detailsDTO.setAchTransactionCode(resultSet.getString(1));
                            detailsDTO.setControl(resultSet.getString(2));
                            detailsDTO.setDestinationAccountType(resultSet.getString(3));
                            detailsDTO.setLedgerFolioNumber(resultSet.getString(4));
                            detailsDTO.setControl1(resultSet.getString(5));
                            detailsDTO.setBeneficiaryAccHolderName(resultSet.getString(6));
                            detailsDTO.setControl2(resultSet.getString(7));
                            detailsDTO.setControl3(resultSet.getString(8));
                            detailsDTO.setUserName(resultSet.getString(9));
                            detailsDTO.setControl4(resultSet.getString(10));
                            detailsDTO.setAmt(resultSet.getString(11));
                            detailsDTO.setReserved1(resultSet.getString(12));
                            detailsDTO.setReserved2(resultSet.getString(13));
                            detailsDTO.setReserved3(resultSet.getString(14));
                            detailsDTO.setReserved4(resultSet.getString(15));
                            detailsDTO.setDestinationBankIFSCMICRIIN(resultSet.getString(16));
                            detailsDTO.setBeneficiaryBankAccNo(resultSet.getString(17));
                            detailsDTO.setSponsorBankIFSCMICRIIN(resultSet.getString(18));
                            detailsDTO.setUserNo(resultSet.getString(19));
                            detailsDTO.setTxnRef(resultSet.getString(20));
                            detailsDTO.setPdtType(resultSet.getString(21));
                            detailsDTO.setBeneficiaryArNo(resultSet.getString(22));
                            detailsDTO.setUMRN(resultSet.getString(23));
                            detailsDTO.setFiller(resultSet.getString(24));
                            listOfACHDetailsDTO.add(detailsDTO);
                        }
                        fileWrapperDTO.setAchFileDetailsList(listOfACHDetailsDTO);
                    }
                    fileWrapperDTO.setFileName(statement.getString(8));
                    responseDataDTO.setResult(fileWrapperDTO);
                    responseDataDTO.setMessage(resultMsg);
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            resultMsg = ErrorMessages.DATABASE_ERROR;
            LOGGER.error("Database Error: resultMsg={}, exception={}",resultMsg, e.getMessage(), e);
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO reject(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACTUAL_INT_PAYMENT_DELETE.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getHalfYear());
                statement.setString(index++, paymentRequestDTO.getYear());
                if (!ObjectUtils.isEmpty(paymentRequestDTO.getRecordsToBeRejected()) && !paymentRequestDTO.getRecordsToBeRejected().isEmpty()) {
                    statement.setArray(index++, generateArrayForRejectBondInterestPayments(connection, new ArrayList<>(new HashSet<>(paymentRequestDTO.getRecordsToBeRejected()))));
                } else {
                    statement.setArray(index++, generateArrayForRejectBondInterestPayments(connection, paymentRequestDTO.getRecordsToBeRejected()));
                }
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info("{}: {}",CommonConstants.STATUS_MESSAGE, resultMsg);
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            resultMsg = ErrorMessages.DATABASE_ERROR;
            LOGGER.error("Database Error: resultMsg={}, exception={}",resultMsg, e.getMessage(), e);
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private Array generateArrayForRejectBondInterestPayments(Connection connection, List<String> list) throws
            SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);
        STRUCT[] struct;
        if (!ObjectUtils.isEmpty(list) && !list.isEmpty()) {
            struct = new STRUCT[list.size()];
            for (int index = 0; index < list.size(); index++) {
                Object[] paramArray = new Object[1];
                paramArray[0] = list.get(index);
                struct[index] = new STRUCT(genericTableStruct, connection, paramArray);
            }
        } else
            struct = new STRUCT[0];

        return new ARRAY(genericTableDescriptor, connection, struct);
    }

    private Array generateArrayForUploadInterestPaymentFile(Connection connection, List<BondInterestPaymentFileDetailsDTO> list) throws
            SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PMNT_RES_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PMNT_RES", connection);
        Struct[] struct = list != null && !list.isEmpty() ?
                list.stream().map(
                        item -> {
                            try {
                                return new STRUCT(genericTableStruct, connection,
                                        new Object[]{
                                                item.getAchItemSeqNo(), item.getChecksum(),
                                                item.getFlagForSuccess(), item.getReasonCode(),
                                                item.getTransRef()
                                        }
                                );
                            } catch (SQLException e) {
                                e.printStackTrace();
                                throw new IllegalArgumentException("Error creating STRUCT", e);
                            }
                        }
                ).toArray(Struct[]::new) : new Struct[0];
        return new ARRAY(genericTableDescriptor, connection, struct);
    }

    @Override
    public ResponseDataDTO processPaymentFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACTUAL_INT_PAYMENT_RESPONSE_JOB.getProcName());
                statement.setArray(index++, generateArrayForUploadInterestPaymentFile(connection, bondInterestPaymentFileDTO.getFileDetailsDTO()));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info("{}: {}",CommonConstants.STATUS_MESSAGE , statement.getString(index));
                responseDataDTO.setMessage(statement.getString(index));
                return responseDataDTO;
            } else
                LOGGER.info(CONNECTION_EMPTY);
        } catch (Exception e) {
            LOGGER.error("Database Error: resultMsg={}, exception={}", e.getMessage(), e);
            responseDataDTO.setMessage(ErrorMessages.DATABASE_ERROR);
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        return responseDataDTO;
    }
}