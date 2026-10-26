package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.common.util.ScssProcNameEnum;
import com.isl.gbm.rbibond.dao.SubscriptionWorkflowDao;
import com.isl.gbm.rbibond.dto.SubcriptionWorkFlowDTO;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class SubscriptionWorkflowDaoImpl implements SubscriptionWorkflowDao {


    private static final Logger log = LoggerFactory.getLogger(SubscriptionWorkflowDaoImpl.class);


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO getAddDetails(SubcriptionWorkFlowDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        SubcriptionWorkFlowDTO output = new SubcriptionWorkFlowDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ScssProcNameEnum.SP_GBM_SCSS_ACCT_OPEN_INS_TOR.getProcName());
                stmt.setString(index++, input.getScssCustomerId());
                stmt.setString(index++, input.getTransactionType());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(3);
                    List<HashMap<String, String>> torchOut = null;
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("scssCustomerId", resultSet.getString(1));
                                temp.put("name", resultSet.getString(2));
                                temp.put("accountHolderType", resultSet.getString(3));
                                torchOut.add(temp);
                            }
                        }
                        output.setCustomerIdOut(torchOut);
                    }
                    resultSet = (ResultSet) stmt.getObject(4);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("scssCustomerId", resultSet.getString(1));
                                temp.put("accountHolderType", resultSet.getString(2));
                                temp.put("accountHolderNumber", resultSet.getString(3));
                                temp.put("name", resultSet.getString(4));
                                temp.put("branchId", resultSet.getString(5));
                                temp.put("operativeAccountNumber", resultSet.getString(6));

                                torchOut.add(temp);
                            }
                        }
                        output.setCustomerMasterOut(torchOut);
                    }
                    output.setDdPayAccountNumber(stmt.getString(5));
                    output.setCreditAccountNumber(stmt.getString(6));
                    output.setDebitAccountNumber(stmt.getString(7));
                    output.setDebitAccountNumberFlag(stmt.getString(8));
                    output.setCreditAccountNumberFlag(stmt.getString(9));
                    output.setDdPayAccountNumberFlag(stmt.getString(10));
                    output.setBranchId(stmt.getString(11));
                    output.setCashLimitOut(stmt.getString(12));
                    output.setMaturityDate(stmt.getString(13));
                    output.setInterestRateOut(stmt.getString(14));
                    output.setPeriodOut(stmt.getString(15));
                    output.setTotalAmount(stmt.getString(16));
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(output);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO add(SubcriptionWorkFlowDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_ADD.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getSchemeCode());
                stmt.setString(index++, input.getSingleBondAccNo());
                stmt.setString(index++, input.getModeOfHolding());

                stmt.setString(index++, input.getBondHolderName());
                stmt.setString(index++, input.getFirstJointHolderBlaNo());
                stmt.setString(index++, input.getFirstJointHolderName());
                stmt.setString(index++, input.getSecondJointHolderBlaNo());
                stmt.setString(index++, input.getSecondJointHolderName());

                stmt.setString(index++, input.getIsNri());
                stmt.setString(index++, input.getApplicationNo());
                stmt.setString(index++, input.getApplicationDate());
                stmt.setString(index++, input.getBrokerAgencyCode());
                stmt.setString(index++, input.getSubscriptionAmount());

                stmt.setString(index++, input.getBondType());
                stmt.setString(index++, input.getAccountOpenDate());
                stmt.setString(index++, input.getValueDate());
                stmt.setString(index++, input.getPeriodOfDeposit());
                stmt.setString(index++, input.getMaturityDate());

                stmt.setString(index++, input.getRateOfInterest());
                stmt.setString(index++, input.getModeOfInterestPayment());
                stmt.setString(index++, input.getOperativeAccNo());
                stmt.setString(index++, input.getOperativeAccBranchId());
                stmt.setString(index++, input.getDdPoAccNo());

                stmt.setString(index++, input.getBankCode());
                stmt.setString(index++, input.getBranchCode());
                stmt.setString(index++, input.getIfscCodeOfCreditBank());
                stmt.setString(index++, input.getNameOfThePayee());
                stmt.setString(index++, input.getCreditAccType());

                stmt.setString(index++, input.getNeftCreditAccNo());
                stmt.setString(index++, input.getTranDebitAccNo());
                stmt.setString(index++, input.getCreditAccNo());
                stmt.setString(index++, input.getTranMode());
                stmt.setString(index++, input.getDebitAccNo());

                stmt.setString(index++, input.getDebitAccType());
                stmt.setString(index++, input.getBranchid());
                stmt.setString(index++, input.getInstrumentType());
                stmt.setString(index++, input.getInstrumentAmount());
                stmt.setString(index++, input.getInstrumentNo());

                stmt.setString(index++, input.getInstrumentDate());
                stmt.setString(index++, input.getTranCode());
                stmt.setString(index++, input.getZoneCode());
                stmt.setString(index++, input.getZoneDate());
                stmt.setString(index++, input.getBankCodeNonMicr());

                stmt.setString(index++, input.getBranchCodeNonMicr());
                stmt.setString(index++, input.getSortCode());
                stmt.setString(index++, input.getRemarks());
                stmt.setString(index++, input.getIsNominee());
                if(input.getNomineeDetailsList() != null && input.getNomineeDetailsList().size() > 0){
                    stmt.setArray(index++, generateTYP_ty_gbm_bond_holder_dtls(connection, input.getNomineeDetailsList()));
                }else{
                    stmt.setNull(index++, Types.ARRAY, "TYP_GBM_NOMINEE_DTLS");
                }


                stmt.setString(index++, input.getTotalSharingPercent());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);

                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    input.setSubscriptionId(stmt.getString(52));
                    input.setGbmTranId(stmt.getString(53));
                    input.setBlaNo(stmt.getString(54));
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(input);
        return responseDataDTO;
    }

    private ARRAY generateTYP_ty_gbm_bond_holder_dtls(Connection connection, List<SubcriptionWorkFlowDTO.NomineeDetails> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_BOND_HOLDER_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_NOMINEE_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        SubcriptionWorkFlowDTO.NomineeDetails dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[26];
            dto = list.get(index);
            paramArray[0] = dto.getFirstName();
            paramArray[1] = dto.getMiddleName();
            paramArray[2] = dto.getLastName();
            paramArray[3] = dto.getAddressOne();
            paramArray[4] = dto.getAddressTwo();
            paramArray[5] = dto.getCity();
            paramArray[6] = dto.getState();
            paramArray[7] = dto.getPinCode();
            paramArray[8] = dto.getPhoneNo();
            paramArray[9] = dto.getSharePercentage();
            paramArray[10] = dto.getOperativeAccNo();
            paramArray[11] = dto.getOperativeAccBranchId();
            paramArray[12] = dto.getIsNomineeMinor();
            paramArray[13] = dto.getRelationWithHolder();
            paramArray[14] = dto.getDateOfBirthOfMinor();
            paramArray[15] = dto.getGuardianFirstName();
            paramArray[16] = dto.getGuardianMiddleName();
            paramArray[17] = dto.getGuardianLastName();
            paramArray[18] = dto.getGuardianAddressOne();
            paramArray[19] = dto.getGuardianAddressTwo();
            paramArray[20] = dto.getGuardianCity();
            paramArray[21] = dto.getGuardianState();
            paramArray[22] = dto.getGuardianPinCode();
            paramArray[23] = dto.getGuardianMobileNo();
            paramArray[24] = dto.getGuardianEmail();
            paramArray[25] = dto.getNomineeStatus();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


    @Override
    public ResponseDataDTO addValidation(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_ADD_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, subcriptionWorkFlowDTO.getSchemeCode());
                stmt.setString(index++, subcriptionWorkFlowDTO.getBlaNo());
                stmt.registerOutParameter(index++, Types.VARCHAR);

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();


                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(14);
                    List<HashMap<String, String>> torchOut = null;

                    if(subcriptionWorkFlowDTO.getBlaNo() == null){

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("blaNo", resultSet.getString(1));
                                temp.put("nameOfBondHolder", resultSet.getString(2));
                                temp.put("jointHolderType", resultSet.getString(3));
                                temp.put("modeOfHolding", resultSet.getString(4));
                                temp.put("verifiedStatus", resultSet.getString(5));
                                torchOut.add(temp);
                            }
                            subcriptionWorkFlowDTO.setTorchValue(torchOut);
                        }
                    }
                    else{
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("blaNo", resultSet.getString(1));
                                temp.put("nameOfBondHolder", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(4));
                                temp.put("verifiedStatus", resultSet.getString(5));
                                temp.put("modeOfHoldingKeyword", resultSet.getString(6));
                                temp.put("isNri", resultSet.getString(7));
                                temp.put("holderType", resultSet.getString(3));
                                temp.put("jointHolderNo", resultSet.getString(8));
                                torchOut.add(temp);
                            }
                            subcriptionWorkFlowDTO.setTorchValue(torchOut);

                        }

                        subcriptionWorkFlowDTO.setPeriodOfDeposit(stmt.getString(5));
                        subcriptionWorkFlowDTO.setRateOfInterest(stmt.getString(6));
                        subcriptionWorkFlowDTO.setBondCashAccount(stmt.getString(7));
                        subcriptionWorkFlowDTO.setBondClearingAccount(stmt.getString(8));
                        subcriptionWorkFlowDTO.setBondCollectionAccount(stmt.getString(9));
                        subcriptionWorkFlowDTO.setDdPoAccountNo(stmt.getString(10));
                        subcriptionWorkFlowDTO.setNeftPlaceHolderAccount(stmt.getString(11));
                        subcriptionWorkFlowDTO.setCashLimitForCash(stmt.getString(12));
                        subcriptionWorkFlowDTO.setCashLimitForClearingTransfer(stmt.getString(13));
                        subcriptionWorkFlowDTO.setDepositMultiples(stmt.getString(15));
                        subcriptionWorkFlowDTO.setMinCashLimit(stmt.getString(16));
                        subcriptionWorkFlowDTO.setBondCashAccFlgOut(stmt.getString(17));
                        subcriptionWorkFlowDTO.setBondClearingAccFlgOut(stmt.getString(18));
                        subcriptionWorkFlowDTO.setBondCollectionAccFlgOut(stmt.getString(19));
                        subcriptionWorkFlowDTO.setDdPoAccFlgOut(stmt.getString(20));
                        subcriptionWorkFlowDTO.setNeftPlaceholderFlgOut(stmt.getString(21));
                    }

                }



            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(subcriptionWorkFlowDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiry(SubcriptionWorkFlowDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getSchemeCode());
                stmt.setString(index++, input.getBlaNo());
                stmt.setString(index++, input.getSubscriptionId());

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);


                ResultSet resultSetOne = null;
                ResultSet resultSetTwo = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    List<HashMap<String, String>> torchOut = null;

                    if (input.getBlaNo() == null && input.getSubscriptionId() == null) {
                        resultSetOne = (ResultSet) stmt.getObject(15);
                        if (resultSetOne != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSetOne.next()) {
                                temp = new HashMap<>();
                                temp.put("blaNo", resultSetOne.getString(1));
                                temp.put("bondHolderName", resultSetOne.getString(2));
                                temp.put("modeOfHolding", resultSetOne.getString(3));
                                temp.put("holderType", resultSetOne.getString(4));
                                torchOut.add(temp);
                            }
                            input.setTorchValue(torchOut);
                            responseDataDTO.setResult(input);
                        }
                    }
                    if (input.getBlaNo() != null && input.getSubscriptionId() == null) {
                        resultSetOne = (ResultSet) stmt.getObject(15);
                        if (resultSetOne != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSetOne.next()) {
                                temp = new HashMap<>();
                                temp.put("bondSubscriptionId", resultSetOne.getString(1));
                                temp.put("holderName", resultSetOne.getString(2));
                                temp.put("subscriptionAmount", resultSetOne.getString(3));
                                temp.put("verifiedStatus", resultSetOne.getString(4));
                                torchOut.add(temp);
                            }
                            input.setTorchValue(torchOut);
                            responseDataDTO.setResult(input);
                        }
                    }
                    if (input.getBlaNo() != null && input.getSubscriptionId() != null) {

                        resultSetOne = (ResultSet) stmt.getObject(15);
                        resultSetTwo = (ResultSet) stmt.getObject(16);

                        input.setPkColumnValue(stmt.getString(6));
                        input.setCreatedBy(stmt.getString(7));
                        input.setCreatedDate(stmt.getString(8));
                        input.setLastModifiedBy(stmt.getString(9));
                        input.setLastModifiedDate(stmt.getString(10));
                        input.setLastVerifiedBy(stmt.getString(11));
                        input.setLastVerifiedDate(stmt.getString(12));
                        input.setPreviousOperationType(stmt.getString(13));
                        input.setVerifiedStatus(stmt.getString(14));
                        input.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSetOne));
                        input.setNomineeValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSetTwo));
                        responseDataDTO.setResult(input);
                    }

                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySelect(SubcriptionWorkFlowDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getSchemeCode());
                stmt.setString(index++, input.getBlaNo());
                stmt.setString(index++, input.getSubscriptionId());

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);


                ResultSet resultSetOne = null;
                ResultSet resultSetTwo = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    List<HashMap<String, String>> torchOut = null;

                    if (input.getBlaNo() == null && input.getSubscriptionId() == null) {
                        resultSetOne = (ResultSet) stmt.getObject(15);
                        if (resultSetOne != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSetOne.next()) {
                                temp = new HashMap<>();
                                temp.put("blaNo", resultSetOne.getString(1));
                                temp.put("bondHolderName", resultSetOne.getString(2));
                                temp.put("modeOfHolding", resultSetOne.getString(3));
                                temp.put("holderType", resultSetOne.getString(4));
                                torchOut.add(temp);
                            }
                            input.setTorchValue(torchOut);
                            responseDataDTO.setResult(input);
                        }
                    }
                    if (input.getBlaNo() != null && input.getSubscriptionId() == null) {
                        resultSetOne = (ResultSet) stmt.getObject(15);
                        if (resultSetOne != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSetOne.next()) {
                                temp = new HashMap<>();
                                temp.put("bondSubscriptionId", resultSetOne.getString(1));
                                temp.put("holderName", resultSetOne.getString(2));
                                temp.put("subscriptionAmount", resultSetOne.getString(3));
                                temp.put("verifiedStatus", resultSetOne.getString(4));
                                torchOut.add(temp);
                            }
                            input.setTorchValue(torchOut);
                            responseDataDTO.setResult(input);
                        }
                    }
                    if (input.getBlaNo() != null && input.getSubscriptionId() != null) {

                        resultSetOne = (ResultSet) stmt.getObject(15);
                        resultSetTwo = (ResultSet) stmt.getObject(16);

                        input.setPkColumnValue(stmt.getString(6));
                        input.setCreatedBy(stmt.getString(7));
                        input.setCreatedDate(stmt.getString(8));
                        input.setLastModifiedBy(stmt.getString(9));
                        input.setLastModifiedDate(stmt.getString(10));
                        input.setLastVerifiedBy(stmt.getString(11));
                        input.setLastVerifiedDate(stmt.getString(12));
                        input.setPreviousOperationType(stmt.getString(13));
                        input.setVerifiedStatus(stmt.getString(14));
                        input.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSetOne));
                        input.setNomineeValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSetTwo));
                        responseDataDTO.setResult(input);
                    }

                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<com.isl.gbm.transaction.channel.GbmTransactionMasterDto> gbmTransactionMasterDtoList = null;
        com.isl.gbm.transaction.channel.GbmTransactionMasterDto ddto = null;
        Date tranDate = null;

        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, subcriptionWorkFlowDTO.getSchemeCode());
                stmt.setString(index++, subcriptionWorkFlowDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, Types.VARCHAR);//gbm train id

                stmt.registerOutParameter(index++, Types.VARCHAR);//sol id
                stmt.registerOutParameter(index++, Types.VARCHAR);//tran date
                stmt.registerOutParameter(index++, Types.VARCHAR);//scheme code
                stmt.registerOutParameter(index++, Types.VARCHAR);//screen tran code
                stmt.registerOutParameter(index++, Types.VARCHAR);//channel type

                stmt.registerOutParameter(index++, Types.VARCHAR);//instr no
                stmt.registerOutParameter(index++, Types.VARCHAR);// instru type
                stmt.registerOutParameter(index++, Types.VARCHAR);//intru date
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);

                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    if(subcriptionWorkFlowDTO.getLastVerifiedBy() == null){
                        tranDate = (new SimpleDateFormat("dd/MM/yyyy").parse(stmt.getString(7)));
                        resultSet = (ResultSet) stmt.getObject(14);
                        gbmTransactionMasterDtoList = new ArrayList<>();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                ddto = new GbmTransactionMasterDto();
                                ddto.setAcctNum(resultSet.getString(1));
                                ddto.setTranLeg(resultSet.getString(2).charAt(0));
                                ddto.setAmount(resultSet.getBigDecimal(3).doubleValue());


                                ddto.setTransactionChannel(stmt.getString(10));
                                ddto.setTranId(stmt.getString(5));
                                ddto.setSolId(stmt.getString(6));
                                ddto.setTranDate(tranDate);
                                ddto.setSchemeCode(stmt.getString(8));

                                String instNo = stmt.getString(11);
                                if(instNo != null){
                                    if(instNo.length() > 0){
                                        ddto.setInstNumber(instNo);
                                    }
                                }

                                ddto.setInstStatus(stmt.getString(12));
                                ddto.setGbmTranId(stmt.getString(5));
                                ddto.setCrn(subcriptionWorkFlowDTO.getSubscriptionId());
                                gbmTransactionMasterDtoList.add(ddto);


                            }
                        }
                        subcriptionWorkFlowDTO.setGbmTranId(stmt.getString(5));
                        subcriptionWorkFlowDTO.setChannelType(stmt.getString(10));
                        subcriptionWorkFlowDTO.setGbmTransactionMasterDtoList(gbmTransactionMasterDtoList);
                        responseDataDTO.setResult(subcriptionWorkFlowDTO);
                    }



                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyFinal(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_VER_RES.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, subcriptionWorkFlowDTO.getGbmTranId());
                stmt.setString(index++, subcriptionWorkFlowDTO.getCbsTranId());
                stmt.setString(index++, subcriptionWorkFlowDTO.getCbsTranStatus());

                stmt.setString(index++, subcriptionWorkFlowDTO.getCbsTranStatusDescription());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    subcriptionWorkFlowDTO.setCbsTranId(stmt.getString(7));
                    subcriptionWorkFlowDTO.setCbsTranStatus(stmt.getString(8));
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(subcriptionWorkFlowDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, subcriptionWorkFlowDTO.getSchemeCode());
                stmt.setString(index++, subcriptionWorkFlowDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySelect(SubcriptionWorkFlowDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getSchemeCode());
                stmt.setString(index++, input.getBlaNo());
                stmt.setString(index++, input.getSubscriptionId());

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);


                ResultSet resultSetOne = null;
                ResultSet resultSetTwo = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    List<HashMap<String, String>> torchOut = null;

                    if (input.getBlaNo() == null && input.getSubscriptionId() == null) {
                        resultSetOne = (ResultSet) stmt.getObject(15);
                        if (resultSetOne != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSetOne.next()) {
                                temp = new HashMap<>();
                                temp.put("blaNo", resultSetOne.getString(1));
                                temp.put("bondHolderName", resultSetOne.getString(2));
                                temp.put("modeOfHolding", resultSetOne.getString(3));
                                temp.put("holderType", resultSetOne.getString(4));
                                temp.put("modeOfHoldingKeyword", resultSetOne.getString(5));
                                torchOut.add(temp);
                            }
                            input.setTorchValue(torchOut);
                            responseDataDTO.setResult(input);
                        }
                    }
                    if (input.getBlaNo() != null && input.getSubscriptionId() == null) {
                        resultSetOne = (ResultSet) stmt.getObject(15);
                        if (resultSetOne != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSetOne.next()) {
                                temp = new HashMap<>();
                                temp.put("bondSubscriptionId", resultSetOne.getString(1));
                                temp.put("holderName", resultSetOne.getString(2));
                                temp.put("subscriptionAmount", resultSetOne.getString(3));
                                temp.put("verifiedStatus", resultSetOne.getString(4));
                                torchOut.add(temp);
                            }
                            input.setTorchValue(torchOut);
                            responseDataDTO.setResult(input);
                        }
                    }
                    if (input.getBlaNo() != null && input.getSubscriptionId() != null) {

                        resultSetOne = (ResultSet) stmt.getObject(15);
                        resultSetTwo = (ResultSet) stmt.getObject(16);

                        input.setPkColumnValue(stmt.getString(6));
                        input.setCreatedBy(stmt.getString(7));
                        input.setCreatedDate(stmt.getString(8));
                        input.setLastModifiedBy(stmt.getString(9));
                        input.setLastModifiedDate(stmt.getString(10));
                        input.setLastVerifiedBy(stmt.getString(11));
                        input.setLastVerifiedDate(stmt.getString(12));
                        input.setPreviousOperationType(stmt.getString(13));
                        input.setVerifiedStatus(stmt.getString(14));
                        input.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSetOne));
                        input.setNomineeValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSetTwo));
                        input.setBondCashAccount(stmt.getString(17));
                        input.setBondClearingAccount(stmt.getString(18));
                        input.setBondCollectionAccount(stmt.getString(19));
                        input.setDdPoAccountNo(stmt.getString(20));
                        input.setNeftCreditAccNo(stmt.getString(21));
                        input.setDepositMultiples(stmt.getString(22));
                        input.setCashLimitForCash(stmt.getString(23));
                        input.setCashLimitForClearingTransfer(stmt.getString(24));
                        input.setMinCashLimit(stmt.getString(25));
                        responseDataDTO.setResult(input);
                    }

                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modify(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, subcriptionWorkFlowDTO.getSchemeCode());
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, subcriptionWorkFlowDTO.getTranDetails()));
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, subcriptionWorkFlowDTO.getNomineeDetails()));
                stmt.setString(index++, subcriptionWorkFlowDTO.getDebitAccNo());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
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
    public ResponseDataDTO getApplicationDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_GET_APP_DET.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, subcriptionWorkFlowDTO.getSchemeCode());
                stmt.setString(index++, subcriptionWorkFlowDTO.getApplicationNo());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(7);
                    List<HashMap<String, String>> torchOut = null;

                    if(subcriptionWorkFlowDTO.getApplicationNo() == null){

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("applicationNo", resultSet.getString(1));
                                temp.put("applicationDate", resultSet.getString(2));
                                temp.put("bondHolderName", resultSet.getString(3));
                                torchOut.add(temp);
                            }
                            subcriptionWorkFlowDTO.setTorchValue(torchOut);
                        }
                    }
                    else{
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("applicationNo", resultSet.getString(1));
                                temp.put("applicationDate", resultSet.getString(2));
                                temp.put("brokerId", resultSet.getString(3));
                                temp.put("subscriptionAmount", resultSet.getString(4));
                                temp.put("debitAccNo", resultSet.getString(5));
                                temp.put("branchId", resultSet.getString(6));
                                temp.put("tranMode", resultSet.getString(7));
                                torchOut.add(temp);
                            }
                            subcriptionWorkFlowDTO.setTorchValue(torchOut);

                        }
                        subcriptionWorkFlowDTO.setPeriodOfDeposit(stmt.getString(5));
                        subcriptionWorkFlowDTO.setRateOfInterest(stmt.getString(6));
                    }

                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(subcriptionWorkFlowDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBrokerDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_GET_BRK_DET.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(3);
                    List<HashMap<String, String>> torchOut = null;

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();
                            temp.put("brokerAgencyCode", resultSet.getString(1));
                            temp.put("brokerName", resultSet.getString(2));
                            temp.put("operativeAccBranchId", resultSet.getString(3));
                            torchOut.add(temp);
                        }
                        subcriptionWorkFlowDTO.setTorchValue(torchOut);
                    }

                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(subcriptionWorkFlowDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getOtherBankDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUS_WRK_FLW_GET_OTR_BNK_DTLS.getProcName());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(1);
                    List<HashMap<String, String>> torchOut = null;

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();
                            temp.put("bankCode", resultSet.getString(1));
                            temp.put("branchCode", resultSet.getString(2));
                            torchOut.add(temp);
                        }
                        subcriptionWorkFlowDTO.setTorchValue(torchOut);
                    }

                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(subcriptionWorkFlowDTO);
        return responseDataDTO;
    }

}
