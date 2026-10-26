package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.SubscriptionAccClosingOnMaturityDAO;
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

import jakarta.annotation.PostConstruct;
import java.sql.*;
import java.util.*;


@Repository
public class SubscriptionAccClosingOnMaturityDAOImpl implements SubscriptionAccClosingOnMaturityDAO {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionAccClosingOnMaturityDAOImpl.class);
    private static final String RESULT_SET_EMPTY = "Result Set is Empty";
    private static final String CONNECTION_EMPTY = "Connection is Empty";
    private static final String ERROR_CREATION_TYPE = "Error creating STRUCT";

    private final ConnectionPoolFactory connectionPoolFactory;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JDBCTemplateUtil jDBCTemplateUtil;

    @PostConstruct
    public void init() {
        jDBCTemplateUtil = new JDBCTemplateUtil(jdbcTemplate);
    }
    public SubscriptionAccClosingOnMaturityDAOImpl(ConnectionPoolFactory connectionPoolFactory) {
        this.connectionPoolFactory = connectionPoolFactory;
    }

    @Override
    public ResponseDataDTO add(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getSchemeCode());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getClosureType());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getModeOfHolding());

                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getHolderTypeCode());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getSingleBlaNo());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getSingleBondHolderName());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getFirstJointBlaNo());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getFirstJointHolderName());

                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getSecondJointBlaNo());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getSecondJointHolderName());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getIsNri());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getSubscriptionDate());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getSubscriptionId());

                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getSubscriptionAmount());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getBondType());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getMaturityDate());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getRateOfInterest());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getInterestPaidUpTo());

                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getInterestCalculationFromDate());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getInterestCalculationToDate());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getBrokenPeriodInterestRate());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getBrokenPeriodInterestAmount());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getPendingInterest());

                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getTotalInterestBeforeTds());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getTdsAmount());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getTotalInterestAfterTds());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getTotalAmountToBePaid());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getPaymentMode());

                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getOperativeAccBranchId());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getCreditAccNo());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getClosureRemarks());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getBankName());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getBranchName());

                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getIfscCode());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getPayeeName());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getCreditAccType());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getCreditAccNoTds());

                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getCreditAccNoPaid());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getDebitAccNoSubs());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getDebitAccNoTotalInterestBeforeTds());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getDataValues().getDebitAccType());
                stmt.registerOutParameter(index++, Types.VARCHAR);

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                resultMsg = stmt.getString(index);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE,resultMsg);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    subscriptionAccClosingOnMaturityDTO.setGbsTranId(stmt.getString(44));
                    responseDataDTO.setResult(subscriptionAccClosingOnMaturityDTO);
                }

            } else {
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
    public ResponseDataDTO addValidation(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new ArrayList<>();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new LinkedList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_ADD_VAL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getSchemeCode());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getPanNoName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getBlaNo());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getFromDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getToDate());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE,resultMsg);

                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
                    if (subscriptionAccClosingOnMaturityDTO.getSchemeCode() == null && resultSet != null) {
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setSchemeCode(resultSet.getString(1));
                            maturityDTO.setSchemeName(resultSet.getString(2));
                            maturityDTO.setSchemeStatus(resultSet.getString(3));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else if (subscriptionAccClosingOnMaturityDTO.getSchemeCode() != null && resultSet != null) {
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
                            bondTrailRunDTO.setInterestPayOut7Per(resultSet.getString(29));
                            bondTrailRunDTO.setNonCumulativeIntFor7(resultSet.getString(30));
                            bondTrailRunDTO.setCumulativeIntFor7(resultSet.getString(31));
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
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public ResponseDataDTO addPANTorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_ADD_PAN_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getSchemeCode());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getFromDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getToDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getPanNoName());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(8);
                    if (subscriptionAccClosingOnMaturityDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setName(resultSet.getString(1));
                            maturityDTO.setPanNoName(resultSet.getString(2));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
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

    public ResponseDataDTO addBLATorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_ADD_BLA_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getSchemeCode());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getFromDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getToDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getPanNoName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getBlaNo());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE,resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
                    if (subscriptionAccClosingOnMaturityDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setPanNoName(resultSet.getString(1));
                            maturityDTO.setBlaNo(resultSet.getString(2));
                            maturityDTO.setName(resultSet.getString(3));
                            maturityDTO.setModeOfHolding(resultSet.getString(4));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
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
    public ResponseDataDTO addSubmit(SubscriptionAccClosingOnMaturityDTO maturityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_ADD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, maturityDTO.getSchemeCode());
                statement.setString(index++, maturityDTO.getName());
                statement.setString(index++, maturityDTO.getPanNoName());
                statement.setString(index++, maturityDTO.getBlaNo());
                statement.setString(index++, maturityDTO.getFromDate());
                statement.setString(index++, maturityDTO.getToDate());
                statement.setArray(index++, generateArrayForRejectBondInterestPayments(connection, maturityDTO.getListOfStrings()));
                statement.setString(index++, maturityDTO.getPaymentDate());
                statement.setString(index++, maturityDTO.getClosureRemarks());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, resultMsg);
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

    private Array generateArrayForRejectBondInterestPayments(Connection connection, List<String> list) throws
            SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);
        Struct[] struct = list != null && !list.isEmpty() ?
                list.stream().map(
                        item -> {
                            try {
                                return new STRUCT(genericTableStruct, connection, new Object[]{item});
                            } catch (SQLException e) {
                                e.printStackTrace();
                                throw new IllegalArgumentException(ERROR_CREATION_TYPE, e);
                            }
                        }
                ).toArray(Struct[]::new) : new Struct[0];
        return new ARRAY(genericTableDescriptor, connection, struct);
    }


    @Override
    public ResponseDataDTO inquiryPANTorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_INQ_PAN_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getSchemeCode());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getFromDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getToDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getPanNoName());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE,resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(8);
                    if (subscriptionAccClosingOnMaturityDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setName(resultSet.getString(1));
                            maturityDTO.setPanNoName(resultSet.getString(2));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
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
    public ResponseDataDTO inquiryBLATorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_INQ_BLA_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getSchemeCode());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getFromDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getToDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getPanNoName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getBlaNo());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
                    if (subscriptionAccClosingOnMaturityDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setPanNoName(resultSet.getString(1));
                            maturityDTO.setBlaNo(resultSet.getString(2));
                            maturityDTO.setName(resultSet.getString(3));
                            maturityDTO.setModeOfHolding(resultSet.getString(4));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
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
    public ResponseDataDTO inquiry(SubscriptionAccClosingOnMaturityDTO onMaturityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new LinkedList<>();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new LinkedList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_INQ.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, onMaturityDTO.getSchemeCode());
                statement.setString(index++, onMaturityDTO.getName() != null ? onMaturityDTO.getName().trim() : onMaturityDTO.getName());
                statement.setString(index++, onMaturityDTO.getPanNoName());
                statement.setString(index++, onMaturityDTO.getBlaNo());
                statement.setString(index++, onMaturityDTO.getFromDate());
                statement.setString(index++, onMaturityDTO.getToDate());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, resultMsg);

                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
                    if (onMaturityDTO.getSchemeCode() == null && resultSet != null) {
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setSchemeCode(resultSet.getString(1));
                            maturityDTO.setSchemeName(resultSet.getString(2));
                            maturityDTO.setSchemeStatus(resultSet.getString(3));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else if (onMaturityDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO bondTrailRunDTO = new BondActualInterestPaymentRequestDTO();
                            // Mapping based on new query structure: bla_no, appl_name1, appl_name2, appl_name3, investment_amt, account_no, pan_no, tds_amount, total_amount_to_be_paid, payment_date, tran_id, Payment_Status, reject_reason
                            bondTrailRunDTO.setBlaNo(resultSet.getString(1)); // bla_no
                            bondTrailRunDTO.setAppName1(resultSet.getString(2)); // appl_name1
                            bondTrailRunDTO.setAppName2(resultSet.getString(3)); // appl_name2
                            bondTrailRunDTO.setAppName3(resultSet.getString(4)); // appl_name3
                            bondTrailRunDTO.setInvestmentAmount(resultSet.getString(5)); // investment_amt
                            bondTrailRunDTO.setAccountNo(resultSet.getString(6)); // account_no
                            bondTrailRunDTO.setPanNoName(resultSet.getString(7)); // pan_no
                            bondTrailRunDTO.setTdsAmount(resultSet.getString(8)); // tds_amount
                            bondTrailRunDTO.setTotalAmtToBePaid(resultSet.getString(9)); // total_amount_to_be_paid
                            bondTrailRunDTO.setPaymentDate(resultSet.getString(10)); // payment_date
                            bondTrailRunDTO.setRefNo(resultSet.getString(11)); // tran_id (mapped to refNo, but JS uses ref_no)
                            bondTrailRunDTO.setPaymentStatus(resultSet.getString(12)); // Payment_Status
                            bondTrailRunDTO.setReturnReason(resultSet.getString(13)); // reject_reason (mapped to returnReason)
                            listOfResponse.add(bondTrailRunDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else
                        resultMsg = RESULT_SET_EMPTY;
                }
            } else {
                resultMsg = CONNECTION_EMPTY;
            }
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
    public ResponseDataDTO verifyPANTorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_VER_SEL_PAN_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getSchemeCode());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getFromDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getToDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getPanNoName());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE,resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(8);
                    if (subscriptionAccClosingOnMaturityDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setName(resultSet.getString(1));
                            maturityDTO.setPanNoName(resultSet.getString(2));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
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
    public ResponseDataDTO verifyBLATorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_VER_SEL_BLA_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getSchemeCode());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getFromDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getToDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getPanNoName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getBlaNo());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
                    if (subscriptionAccClosingOnMaturityDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setPanNoName(resultSet.getString(1));
                            maturityDTO.setBlaNo(resultSet.getString(2));
                            maturityDTO.setName(resultSet.getString(3));
                            maturityDTO.setModeOfHolding(resultSet.getString(4));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
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
    public ResponseDataDTO verifySelect(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new ArrayList<>();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new LinkedList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getSchemeCode());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getName());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getPanNoName());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getBlaNo());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getFromDate());
                stmt.setString(index++, subscriptionAccClosingOnMaturityDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                resultMsg = stmt.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, resultMsg);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(9);
                    if (subscriptionAccClosingOnMaturityDTO.getSchemeCode() == null && resultSet != null) {
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setSchemeCode(resultSet.getString(1));
                            maturityDTO.setSchemeName(resultSet.getString(2));
                            maturityDTO.setSchemeStatus(resultSet.getString(3));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else if (subscriptionAccClosingOnMaturityDTO.getSchemeCode() != null && resultSet != null) {
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
                            bondTrailRunDTO.setPaymentDate(resultSet.getString(62));
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
            connectionPoolFactory.closeConnection(connection, stmt, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg;
        BondACHFileWrapperDTO fileWrapperDTO = new BondACHFileWrapperDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_VER.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getSchemeCode());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getPanNoName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getBlaNo());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getFromDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getToDate());
                statement.setArray(index++, generateArrayForRejectBondInterestPayments(connection, subscriptionAccClosingOnMaturityDTO.getListOfStrings()));
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getClosureRemarks());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE,resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(11);
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
                    resultSet = (ResultSet) statement.getObject(12);
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
                    fileWrapperDTO.setFileName(statement.getString(13));
                    responseDataDTO.setResult(fileWrapperDTO);
                    responseDataDTO.setMessage(resultMsg);
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
    public ResponseDataDTO verifyResponse(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return null;
    }

    @Override
    public ResponseDataDTO delete(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (subscriptionAccClosingOnMaturityDTO.getClosureRemarks() == null
                || subscriptionAccClosingOnMaturityDTO.getClosureRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_DEL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getSchemeCode());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getPanNoName());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getBlaNo());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getFromDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getToDate());
                statement.setString(index++, subscriptionAccClosingOnMaturityDTO.getClosureRemarks());
                statement.setArray(index++, generateArrayForRejectBondInterestPayments(connection, subscriptionAccClosingOnMaturityDTO.getListOfStrings()));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, resultMsg);
            } else
                resultMsg = CONNECTION_EMPTY;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
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
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_FILE_NAME_OUT.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getFromDate());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, resultMsg);
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
    public ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg;
        BondACHFileWrapperDTO fileWrapperDTO = new BondACHFileWrapperDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_DOWNLOAD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getFromDate());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE,resultMsg);
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
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO, String schemeCode, String fromDate, String toDate) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_UPLOAD_FILE.getProcName()); // Procedure Name need to change in ProcedureEnum Class
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, schemeCode);
                statement.setString(index++, fromDate);
                statement.setArray(index++, generateArrayForUploadPaymentFile(connection, bondInterestPaymentFileDTO.getFileDetailsDTO()));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE,resultMsg);
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
    public ResponseDataDTO processPaymentFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBS_ACC_CLS_MTY_SCHEDULER.getProcName());
                statement.setArray(index++, generateArrayForUploadPaymentFile(connection, bondInterestPaymentFileDTO.getFileDetailsDTO()));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, statement.getString(index));
                responseDataDTO.setMessage(statement.getString(index));
                return responseDataDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        return responseDataDTO;
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
