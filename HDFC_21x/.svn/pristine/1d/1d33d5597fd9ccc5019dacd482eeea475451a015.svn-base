package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.BondSubAccClosureBeforeMaturityDAO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BondSubAccClosureBeforeMaturityDAOImpl implements BondSubAccClosureBeforeMaturityDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BondSubAccClosureBeforeMaturityDAOImpl.class);
    private static final String RESULT_SET_EMPTY = "Result Set is Empty";
    private static final String CONNECTION_EMPTY = "Connection is Empty";
    private static final String ERROR_CREATION_TYPE = "Error creating STRUCT";
    private final ConnectionPoolFactory connectionPoolFactory;

    public BondSubAccClosureBeforeMaturityDAOImpl(ConnectionPoolFactory connectionPoolFactory) {
        this.connectionPoolFactory = connectionPoolFactory;
    }

    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_DOWNLOAD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getPaymentDate());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    responseDataDTO.setMessage(resultMsg);
                    responseDataDTO.setResult(statement.getString(5));
                    return responseDataDTO;
                }
            } else
                resultMsg = CONNECTION_EMPTY;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new LinkedList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_INQUIRY.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getFlag());
                statement.setString(index++, paymentRequestDTO.getName() != null ? paymentRequestDTO.getName().trim() : paymentRequestDTO.getName());
                statement.setString(index++, paymentRequestDTO.getPanNoName());
                statement.setString(index++, paymentRequestDTO.getBlaNo());
                statement.setString(index++, paymentRequestDTO.getClosureType());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
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
                    } else if (paymentRequestDTO.getSchemeCode() != null && "P".equalsIgnoreCase(paymentRequestDTO.getFlag())
                            && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setPanNoName(resultSet.getString(1));
                            schemeCodeDTO.setName(resultSet.getString(2));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else if (paymentRequestDTO.getSchemeCode() != null && "B".equalsIgnoreCase(paymentRequestDTO.getFlag())
                            && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setPanNoName(resultSet.getString(1));
                            schemeCodeDTO.setName(resultSet.getString(2));
                            schemeCodeDTO.setModeOfHolding(resultSet.getString(3));
                            schemeCodeDTO.setBlaNo(resultSet.getString(4));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else if (paymentRequestDTO.getSchemeCode() != null && "D".equalsIgnoreCase(paymentRequestDTO.getFlag())
                            && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO bondTrailRunDTO = new BondActualInterestPaymentRequestDTO();
                            // Mapping based on new query structure: bla_no, appl_name1, appl_name2, appl_name3, investment_amt, account_no, pan_no, tds_amount, payment_date, total_amount_to_be_paid, tran_id, Payment_Status, reject_reason
                            bondTrailRunDTO.setBlaNo(resultSet.getString(1)); // bla_no
                            bondTrailRunDTO.setAppName1(resultSet.getString(2)); // appl_name1
                            bondTrailRunDTO.setAppName2(resultSet.getString(3)); // appl_name2
                            bondTrailRunDTO.setAppName3(resultSet.getString(4)); // appl_name3
                            bondTrailRunDTO.setInvestmentAmount(resultSet.getString(5)); // investment_amt
                            bondTrailRunDTO.setAccountNo(resultSet.getString(6)); // account_no
                            bondTrailRunDTO.setPanNoName(resultSet.getString(7)); // pan_no
                            bondTrailRunDTO.setTdsAmount(resultSet.getString(8)); // tds_amount
                            bondTrailRunDTO.setPaymentDate(resultSet.getString(9)); // payment_date
                            bondTrailRunDTO.setTotalAmtToBePaid(resultSet.getString(10)); // total_amount_to_be_paid
                            bondTrailRunDTO.setRefNo(resultSet.getString(11)); // tran_id (mapped to refNo, but JS uses ref_no)
                            bondTrailRunDTO.setPaymentStatus(resultSet.getString(12)); // Payment_Status
                            bondTrailRunDTO.setReturnReason(resultSet.getString(13)); // reject_reason (mapped to returnReason)
                            listOfResponse.add(bondTrailRunDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    }
                }
            } else
                resultMsg = CONNECTION_EMPTY;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new LinkedList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_PAN_VAL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getBlaNo());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(5);
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
                    } else if (paymentRequestDTO.getBlaNo() == null && paymentRequestDTO.getModeOfHolding() == null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setBlaNo(resultSet.getString(1));
                            schemeCodeDTO.setName(resultSet.getString(2));
                            schemeCodeDTO.setModeOfHolding(resultSet.getString(3));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else if (paymentRequestDTO.getBlaNo() != null && !"details".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit()) && paymentRequestDTO.getPanNoName() == null && paymentRequestDTO.getDob() == null && resultSet != null) {
                        BondActualInterestPaymentRequestDTO schemeCodeDTO=null;
                        List<BondActualInterestPaymentRequestDTO> list=new ArrayList<>();
                        while (resultSet.next()) {
                            schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setPanNoName(resultSet.getString(1));
                            schemeCodeDTO.setName(resultSet.getString(2));
                            schemeCodeDTO.setDob(resultSet.getString(3));
                            list.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(list);
                        responseDataDTO.setMessage(resultMsg);
                    } else
                        resultMsg = RESULT_SET_EMPTY;
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addDetailsForDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<BondActualInterestPaymentRequestDTO> listOfBLAs = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_ADD_DETAILS_PAGE.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getBlaNo());
                statement.setString(index++, paymentRequestDTO.getClosureType());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(6);
                    if (resultSet != null) {
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
                            bondTrailRunDTO.setPrincipalAmt(resultSet.getString(60));
                            bondTrailRunDTO.setTotalAmtToBePaid(resultSet.getString(61));
                            bondTrailRunDTO.setPaymentDateAtEnd(resultSet.getString(62));
                            bondTrailRunDTO.setTotalPayOutOfTheYear(resultSet.getString(63));
                            listOfBLAs.add(bondTrailRunDTO);
                        }
                        responseDataDTO.setResult(listOfBLAs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else
                        resultMsg = RESULT_SET_EMPTY;
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addSubmit(BondActualInterestPaymentRequestDTO responseDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_ADD_SUBMIT.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, responseDTO.getSchemeCode());
                statement.setString(index++, responseDTO.getPanNoName());
                statement.setString(index++, responseDTO.getBlaNo());
                statement.setArray(index++, generateArrayForAddDetailsSubAccClosure(connection, responseDTO.getRecordsToBeVerified())); // subscription Id's
                statement.setString(index++, responseDTO.getClosureType());
                statement.setString(index++, responseDTO.getPaymentDate());
                statement.setString(index++, responseDTO.getClosureRemark());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_VER_SEL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getBlaNo());
                statement.setString(index++, paymentRequestDTO.getClosureType());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
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
                        return responseDataDTO;
                    } else if (paymentRequestDTO.getSchemeCode() != null && paymentRequestDTO.getClosureType() != null &&
                            resultSet != null) {
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
                            bondTrailRunDTO.setPrincipalAmt(resultSet.getString(60));
                            bondTrailRunDTO.setTotalAmtToBePaid(resultSet.getString(61));
                            bondTrailRunDTO.setPaymentDateAtEnd(resultSet.getString(62));
                            bondTrailRunDTO.setClosureRemark(resultSet.getString(63));
                            bondTrailRunDTO.setTotalPayOutOfTheYear(resultSet.getString(64));
                            listOfResponse.add(bondTrailRunDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else
                        resultMsg = RESULT_SET_EMPTY;
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyBLADetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_VER_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(4);
                    if (paymentRequestDTO.getSchemeCode() != null && paymentRequestDTO.getBlaNo() == null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setBlaNo(resultSet.getString(1));
                            schemeCodeDTO.setName(resultSet.getString(2));
                            schemeCodeDTO.setModeOfHolding(resultSet.getString(3));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else
                        resultMsg = RESULT_SET_EMPTY;
                }
            } else
                resultMsg = CONNECTION_EMPTY;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
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
        String resultMsg;
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondACHFileWrapperDTO fileWrapperDTO = new BondACHFileWrapperDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_VER_REQUEST.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getClosureType());
                statement.setArray(index++, generateArrayForVerifySubAccClosure(connection, paymentRequestDTO.getVerifyArrayObject()));
                statement.setString(index++, paymentRequestDTO.getCheckerRemarkForClosure());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
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
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondACHFileWrapperDTO fileWrapperDTO = new BondACHFileWrapperDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_SP_MANUAL_DOWNLOAD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getPaymentDate());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(5);
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
                    resultSet = (ResultSet) statement.getObject(6);
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
                    fileWrapperDTO.setFileName(statement.getString(7));
                    responseDataDTO.setResult(fileWrapperDTO);
                    responseDataDTO.setMessage(resultMsg);
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (paymentRequestDTO.getCheckerRemarkForClosure() == null
                || paymentRequestDTO.getCheckerRemarkForClosure().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_DELETE.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getClosureType());
                statement.setArray(index++, generateArrayForVerifySubAccClosure(connection, paymentRequestDTO.getVerifyArrayObject()));
                statement.setString(index++, paymentRequestDTO.getCheckerRemarkForClosure());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
            } else
                resultMsg = CONNECTION_EMPTY;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private Array generateArrayForVerifySubAccClosure(Connection connection, List<VerifyArrayObject> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PRE_CL_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PRE_CL_DTLS", connection);
        Struct[] struct = list != null && !list.isEmpty() ?
                list.stream().map(item -> {
                            try {
                                return new STRUCT(genericTableStruct, connection,
                                        new Object[]{item.getBlaNo(), item.getSubId(),
                                                item.getPanNo(), item.getPaymentDate()}
                                );
                            } catch (SQLException e) {
                                e.printStackTrace();
                                throw new IllegalArgumentException(ERROR_CREATION_TYPE, e);
                            }
                        }
                ).toArray(Struct[]::new) : new Struct[0];
        return new ARRAY(genericTableDescriptor, connection, struct);
    }

    @Override
    public ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO, String
            schemeCode, String paymentDate) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_UPLOAD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, schemeCode);
                statement.setString(index++, paymentDate);
                statement.setArray(index++, generateArrayForUploadPaymentFile(connection, bondInterestPaymentFileDTO.getFileDetailsDTO()));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
            } else
                resultMsg = CONNECTION_EMPTY;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO updatePANStatus(PanValidationRetriggerResponseDTO responseDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_PAN_RES.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, responseDTO.getSchemeCode());
                statement.setArray(index++, generateArrayForPanStatusUpdate(connection, responseDTO));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    responseDataDTO.setMessage(resultMsg);
                    responseDataDTO.setResult(responseDTO.getOldPan());
                    return responseDataDTO;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
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
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUB_ACC_CLOSURE_BEFORE_MAT_SCH.getProcName());
                statement.setArray(index++, generateArrayForUploadPaymentFile(connection, bondInterestPaymentFileDTO.getFileDetailsDTO()));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                LOGGER.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                LOGGER.info(CommonConstants.STATUS_MESSAGE + "{}", statement.getString(index));
                responseDataDTO.setMessage(statement.getString(index));
                return responseDataDTO;
            } else
                LOGGER.info(CONNECTION_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        return responseDataDTO;
    }

    private Array generateArrayForPanStatusUpdate(Connection connection, PanValidationRetriggerResponseDTO
            responseDTO) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_PAN_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_PAN", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[1];
        Object[] paramArray = new Object[14];
        LOGGER.info("ResponseDTO: {}" , responseDTO);
        paramArray[0] = responseDTO.getPanNumber();
        paramArray[1] = responseDTO.getNameStatus();
        paramArray[2] = responseDTO.getPanStatus();
        paramArray[3] = responseDTO.getDobStatus();
        paramArray[4] = responseDTO.getProcessStatus();
        // New fields added to paramArray
        paramArray[5] = responseDTO.getPanStatusDescription();
        paramArray[6] = responseDTO.getPanNameDescription();
        paramArray[7] = responseDTO.getFatherName();
        paramArray[8] = responseDTO.getPanDobDescription();
        paramArray[9] = responseDTO.getReturnMessage();
        paramArray[10] = responseDTO.getReturnCode();
        paramArray[11] = responseDTO.getReturnDescription();
        paramArray[12] = responseDTO.getSeedingStatus();
        paramArray[13] = responseDTO.getSeedingStatusDescription();
        STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
        genericTableInsertStructs[0] = struct;
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private Array generateArrayForAddDetailsSubAccClosure(Connection connection, List<String> list) throws
            SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);
        Struct[] struct = list != null && !list.isEmpty() ?
                list.stream().map(
                        item -> {
                            try {
                                return new STRUCT(genericTableStruct, connection,
                                        new Object[]{item}
                                );
                            } catch (SQLException e) {
                                e.printStackTrace();
                                throw new IllegalArgumentException(ERROR_CREATION_TYPE, e);
                            }
                        }
                ).toArray(Struct[]::new) : new Struct[0];
        return new ARRAY(genericTableDescriptor, connection, struct);
    }

    private Array generateArrayForUploadPaymentFile(Connection connection, List<BondInterestPaymentFileDetailsDTO> list) throws
            SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PMNT_RES_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PMNT_RES", connection); //
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
                                throw new IllegalArgumentException(ERROR_CREATION_TYPE, e);
                            }
                        }
                ).toArray(Struct[]::new) : new Struct[0];
        return new ARRAY(genericTableDescriptor, connection, struct);
    }

}