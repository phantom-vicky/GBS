package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.RbiBondAccTransferOutDAO;
import com.isl.gbm.rbibond.dto.RbiBondAccTransferOutDTO;
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
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Repository
public class RbiBondAccTransferOutDAOimpl implements RbiBondAccTransferOutDAO {


    private static final Logger log = LoggerFactory.getLogger(RbiBondAccTransferOutDAOimpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO getOtherBankDetails(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<Map<String, String>> list = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACC_TRF_OUT_GET_OTR_BNK_DTS.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
                resultMsg = stmt.getString(index);


                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(4);
                    if(resultSet != null) {
                        list = new ArrayList<>();
                        Map<String, String> item = null;
                        while (resultSet.next()){
                            item = new HashMap<>();
                            item.put("branchId", resultSet.getString(1));
                            item.put("branchDescription", resultSet.getString(2));
                            list.add(item);
                        }
                        responseDataDTO.setResult(list);
                    }
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
    public ResponseDataDTO getNomineeDetails(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACC_TRF_OUT_GET_NOM_DTS.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getSchemeCode());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBlaNo());
                stmt.setArray(index++, generateArrayForNomineeDetails(connection, rbiBondAccTransferOutDTO.getSubscriptionIds()));
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    if(resultSet != null) {

                        List<Map<String, String>> list = new ArrayList<>();
                        Map<String, String> item = null;
                        while (resultSet.next()) {
                            item = new HashMap<>();
                            item.put("subscriptionId", resultSet.getString(1));
                            item.put("nomineeName", resultSet.getString(2));
                            item.put("nomineeShare", resultSet.getString(3));
                            list.add(item);
                        }
                        responseDataDTO.setResult(list);
                    }
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


    private Array generateArrayForNomineeDetails(Connection connection, List<String> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_BOND_SUBS_ID_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_BOND_SUBS_ID_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[1];
            paramArray[0] = list.get(index);

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private Array generateArrayForSubscriptionDetailsAdd(Connection connection, List<RbiBondAccTransferOutDTO.SubscriptionDetails> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_BOND_TRNF_OUT_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_BOND_TRNF_OUT_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[8];
            RbiBondAccTransferOutDTO.SubscriptionDetails item = list.get(index);
            paramArray[0] = item.getSubscriptionDate();
            paramArray[1] = item.getSubscriptionId();
            paramArray[2] = item.getSubscriptionAmount();
            paramArray[3] = item.getBondType();
            paramArray[4] = item.getMaturityDate();
            paramArray[5] = item.getRateOfInterest();
            paramArray[6] = item.getInterestPaidUpTo();
            paramArray[7] = item.getTransferAmount();

            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


    private Array generateArrayForNomineeDetailsAdd(Connection connection, List<RbiBondAccTransferOutDTO.NomineeDetails> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_BOND_TRNF_NOM_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_BOND_TRNF_NOM_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[3];
            RbiBondAccTransferOutDTO.NomineeDetails item = list.get(index);
            paramArray[0] = item.getSubscriptionId();
            paramArray[1] = item.getNomineeName();
            paramArray[2] = item.getNomineeShare();

            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    @Override
    public ResponseDataDTO add(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(rbiBondAccTransferOutDTO.getTransferToBankName() == null || rbiBondAccTransferOutDTO.getTransferToBankName().length() > 20 || rbiBondAccTransferOutDTO.getTransferToBankName().matches("^[A-Za-z]+$") ){
            responseDataDTO.setMessage("Transfer To BankName in mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(rbiBondAccTransferOutDTO.getTransferToOutBranchName() == null || rbiBondAccTransferOutDTO.getTransferToOutBranchName().length() > 50 || rbiBondAccTransferOutDTO.getTransferToOutBranchName().matches("^[A-Za-z]+$")){
            responseDataDTO.setMessage("Transfer To Out BranchName is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACC_TRF_OUT_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getSchemeCode());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBlaNo());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getTranFlag());
                stmt.setArray(index++, generateArrayForSubscriptionDetailsAdd(connection, rbiBondAccTransferOutDTO.getSubscriptionDetailsList()));

                stmt.setString(index++, rbiBondAccTransferOutDTO.getTransferToBankName());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getTransferToBranchName());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getTransferToBranchId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getTransferToSameBranchName());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getRemarks());

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
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
    public ResponseDataDTO addValidation(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACC_TRF_OUT_ADD_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getSchemeCode());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getDetFlag());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBondHolderName());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getPanNum());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBlaNo());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getTranFlag());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(rbiBondAccTransferOutDTO.getSchemeCode() == null && rbiBondAccTransferOutDTO.getDetFlag() == null  && rbiBondAccTransferOutDTO.getBondHolderName() == null  && rbiBondAccTransferOutDTO.getPanNum() == null  && rbiBondAccTransferOutDTO.getBlaNo() == null  && rbiBondAccTransferOutDTO.getTranFlag() == null  ){

                        ResultSet resultSet = (ResultSet) stmt.getObject(9);

                        if(resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();

                                item.put("schemeCode", resultSet.getString(1));
                                item.put("schemeName", resultSet.getString(2));
                                item.put("schemeStatus", resultSet.getString(3));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }
                    else
                    if(rbiBondAccTransferOutDTO.getSchemeCode() != null && rbiBondAccTransferOutDTO.getDetFlag().equalsIgnoreCase("P") && rbiBondAccTransferOutDTO.getBlaNo() == null  && rbiBondAccTransferOutDTO.getTranFlag() == null ){

                        ResultSet resultSet = (ResultSet) stmt.getObject(9);

                        if(resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();

                                item.put("panNum", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else
                    if(rbiBondAccTransferOutDTO.getSchemeCode() != null && rbiBondAccTransferOutDTO.getDetFlag().equalsIgnoreCase("B")   && rbiBondAccTransferOutDTO.getTranFlag() == null ){

                        ResultSet resultSet = (ResultSet) stmt.getObject(9);

                        if(resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();

                                item.put("panNum", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("blaNo", resultSet.getString(4));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }
                    else{

                        ResultSet resultSetOne = (ResultSet) stmt.getObject(9);
                        if(resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()){
                                item = new HashMap<>();
                                item.put("modeOfHolding", resultSetOne.getString(1));
                                item.put("holderType", resultSetOne.getString(2));
                                item.put("singleBondAccNo", resultSetOne.getString(3));
                                item.put("singleBondHolderName", resultSetOne.getString(4));
                                item.put("firstJointHolderBlaNo", resultSetOne.getString(5));
                                item.put("firstJointHolderName", resultSetOne.getString(6));
                                item.put("secondJointHolderBlaNo", resultSetOne.getString(7));
                                item.put("secondJointHolderName", resultSetOne.getString(8));
                                item.put("isNri", resultSetOne.getString(9));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondHolderDetails(list);
                        }

                        ResultSet resultSetTwo = (ResultSet) stmt.getObject(10);
                        if(resultSetTwo != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetTwo.next()){
                                item = new HashMap<>();
                                item.put("subscriptionDate", resultSetTwo.getString(1));
                                item.put("subscriptionId", resultSetTwo.getString(2));
                                item.put("subscriptionAmount", resultSetTwo.getString(3));
                                item.put("bondType", resultSetTwo.getString(4));
                                item.put("maturityDate", resultSetTwo.getString(5));
                                item.put("rateOfInterest", resultSetTwo.getString(6));
                                item.put("interestPaidUpTo", resultSetTwo.getString(7));
                                item.put("transferAmount", resultSetTwo.getString(8));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondSubscriptionDetails(list);
                        }

                        ResultSet resultSetThree = (ResultSet) stmt.getObject(11);
                        if(resultSetThree != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetThree.next()){
                                item = new HashMap<>();
                                item.put("subscriptionId", resultSetThree.getString(1));
                                item.put("nomineeName", resultSetThree.getString(2));
                                item.put("nomineeShare", resultSetThree.getString(3));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondNomineeDetails(list);
                        }
                        responseDataDTO.setResult(rbiBondAccTransferOutDTO);
                    }
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
    public ResponseDataDTO inquiry(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACC_TRF_OUT_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getSchemeCode());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getDetFlag());
                stmt.setString(index++, null);
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBondHolderName());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getPanNum());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBlaNo());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getTranFlag());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBondSubsId());

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(rbiBondAccTransferOutDTO.getSchemeCode()  == null && rbiBondAccTransferOutDTO.getDetFlag()  == null && rbiBondAccTransferOutDTO.getBondHolderName()  == null && rbiBondAccTransferOutDTO.getPanNum() == null && rbiBondAccTransferOutDTO.getBlaNo()  == null && rbiBondAccTransferOutDTO.getTranFlag()  == null && rbiBondAccTransferOutDTO.getBondSubsId() == null){

                        ResultSet resultSetOne = (ResultSet) stmt.getObject(11);
                        if(resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()){
                                item = new HashMap<>();

                                item.put("schemeCode", resultSetOne.getString(1));
                                item.put("schemeName", resultSetOne.getString(2));
                                item.put("schemeStatus", resultSetOne.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }
                    if(rbiBondAccTransferOutDTO.getSchemeCode() != null && rbiBondAccTransferOutDTO.getDetFlag().equalsIgnoreCase("P")  && rbiBondAccTransferOutDTO.getBlaNo()  == null && rbiBondAccTransferOutDTO.getTranFlag()  == null && rbiBondAccTransferOutDTO.getBondSubsId() == null){

                        ResultSet resultSetOne = (ResultSet) stmt.getObject(11);
                        if(resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSetOne.getString(1));
                                item.put("bondHolderName", resultSetOne.getString(2));
                                item.put("blaNo", resultSetOne.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }

                    if(rbiBondAccTransferOutDTO.getSchemeCode() != null && rbiBondAccTransferOutDTO.getDetFlag().equalsIgnoreCase("B") && rbiBondAccTransferOutDTO.getTranFlag()  == null && rbiBondAccTransferOutDTO.getBondSubsId() == null) {


                        ResultSet resultSetOne = (ResultSet) stmt.getObject(11);
                        if (resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSetOne.getString(1));
                                item.put("bondHolderName", resultSetOne.getString(2));
                                item.put("modeOfHolding", resultSetOne.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    if(rbiBondAccTransferOutDTO.getSchemeCode() != null && rbiBondAccTransferOutDTO.getDetFlag().equalsIgnoreCase("N") &&  rbiBondAccTransferOutDTO.getPanNum() == null && rbiBondAccTransferOutDTO.getBlaNo()  == null && rbiBondAccTransferOutDTO.getTranFlag()  == null && rbiBondAccTransferOutDTO.getBondSubsId() == null) {


                        ResultSet resultSetOne = (ResultSet) stmt.getObject(11);
                        if (resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()) {
                                item = new HashMap<>();
                                item.put("bondHolderName", resultSetOne.getString(1));
                                item.put("panNum", resultSetOne.getString(2));
                                item.put("modeOfHolding", resultSetOne.getString(3));
                                item.put("blaNo", resultSetOne.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }

                    if(rbiBondAccTransferOutDTO.getSchemeCode() != null && rbiBondAccTransferOutDTO.getDetFlag().equalsIgnoreCase("S") && rbiBondAccTransferOutDTO.getTranFlag() != null && rbiBondAccTransferOutDTO.getBondSubsId() == null) {


                        ResultSet resultSetOne = (ResultSet) stmt.getObject(11);
                        if (resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()) {
                                item = new HashMap<>();
                                item.put("bondSubsId", resultSetOne.getString(1));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    if(rbiBondAccTransferOutDTO.getSchemeCode() != null && rbiBondAccTransferOutDTO.getDetFlag().equalsIgnoreCase("D") && rbiBondAccTransferOutDTO.getBlaNo() != null &&  rbiBondAccTransferOutDTO.getTranFlag() != null ){


                        ResultSet resultSetOne = (ResultSet) stmt.getObject(11);
                        if(resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()){
                                item = new HashMap<>();
                                item.put("holderType", resultSetOne.getString(1));
                                item.put("singleBondAccNo", resultSetOne.getString(2));
                                item.put("singleBondHolderName", resultSetOne.getString(3));
                                item.put("firstJointHolderBlaNo", resultSetOne.getString(4));
                                item.put("firstJointHolderName", resultSetOne.getString(5));
                                item.put("secondJointHolderBlaNo", resultSetOne.getString(6));
                                item.put("secondJointHolderName", resultSetOne.getString(7));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondHolderDetails(list);
                        }

                        ResultSet resultSetTwo = (ResultSet) stmt.getObject(12);
                        if(resultSetTwo != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetTwo.next()){
                                item = new HashMap<>();
                                item.put("subscriptionDate", resultSetTwo.getString(1));
                                item.put("subscriptionId", resultSetTwo.getString(2));
                                item.put("subscriptionAmount", resultSetTwo.getString(3));
                                item.put("bondType", resultSetTwo.getString(4));
                                item.put("maturityDate", resultSetTwo.getString(5));
                                item.put("rateOfInterest", resultSetTwo.getString(6));
                                item.put("interestPaidUpTo", resultSetTwo.getString(7));
                                item.put("transferAmount", resultSetTwo.getString(8));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondSubscriptionDetails(list);
                        }

                        ResultSet resultSetThree = (ResultSet) stmt.getObject(13);
                        if(resultSetThree != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetThree.next()){
                                item = new HashMap<>();
                                item.put("subscriptionId", resultSetThree.getString(1));
                                item.put("nomineeName", resultSetThree.getString(2));
                                item.put("nomineeShare", resultSetThree.getString(3));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondNomineeDetails(list);
                        }
                        ResultSet resultSetFour = (ResultSet) stmt.getObject(14);
                        if(resultSetFour != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetFour.next()) {
                                item = new HashMap<>();
                                item.put("transferOutToBankName", resultSetFour.getString(1));
                                item.put("transferOutToBranchName", resultSetFour.getString(2));
                                item.put("transferOutThrough", resultSetFour.getString(3));
                                item.put("creditAccDdpoNum", resultSetFour.getString(4));
                                item.put("transferInToBranchId", resultSetFour.getString(5));
                                item.put("transferInToSameBranchName", resultSetFour.getString(6));
                                item.put("remarks", resultSetFour.getString(7));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondTransferOutDetails(list);
                        }
                        rbiBondAccTransferOutDTO.setVerifiedStatus(stmt.getString(15));
                        responseDataDTO.setResult(rbiBondAccTransferOutDTO);

                    }
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
    public ResponseDataDTO verifySelect(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACC_TRF_OUT_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getSchemeCode());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBlaNo());

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);

                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(rbiBondAccTransferOutDTO.getSchemeCode() == null
                            && rbiBondAccTransferOutDTO.getBlaNo() == null){

                        ResultSet resultSetOne = (ResultSet) stmt.getObject(5);
                        if(resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()){
                                item = new HashMap<>();
                                item.put("schemeCode", resultSetOne.getString(1));
                                item.put("schemeName", resultSetOne.getString(2));
                                item.put("schemeStatus", resultSetOne.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }
                    if(rbiBondAccTransferOutDTO.getBlaNo() == null
                            && rbiBondAccTransferOutDTO.getSchemeCode() != null){

                        ResultSet resultSetOne = (ResultSet) stmt.getObject(5);
                        if(resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSetOne.getString(1));
                                item.put("bondHolderName", resultSetOne.getString(2));
                                item.put("modeOfHolding", resultSetOne.getString(3));
                                item.put("verifiedStatus", resultSetOne.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }

                    if(rbiBondAccTransferOutDTO.getBlaNo() != null
                            && rbiBondAccTransferOutDTO.getSchemeCode() != null){


                        ResultSet resultSetOne = (ResultSet) stmt.getObject(5);
                        if(resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()) {
                                item = new HashMap<>();
                                item.put("holderType", resultSetOne.getString(1));
                                item.put("singleBlaNo", resultSetOne.getString(2));
                                item.put("singleHolderName", resultSetOne.getString(3));
                                item.put("firstJointBlaNo", resultSetOne.getString(4));
                                item.put("firstJointHolderName", resultSetOne.getString(5));
                                item.put("secondJointBlaNo", resultSetOne.getString(6));
                                item.put("secondJointHolderName", resultSetOne.getString(7));
                                 list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondHolderDetails(list);
                        }

                        ResultSet resultSetTwo = (ResultSet) stmt.getObject(6);
                        if(resultSetTwo != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetTwo.next()) {
                                item = new HashMap<>();
                                item.put("subscriptionDate", resultSetTwo.getString(1));
                                item.put("subscriptionId", resultSetTwo.getString(2));
                                item.put("subscriptionAmount", resultSetTwo.getString(3));
                                item.put("bondType", resultSetTwo.getString(4));
                                item.put("maturityDate", resultSetTwo.getString(5));
                                item.put("rateOfInterest", resultSetTwo.getString(6));
                                item.put("interestPaidUpTo", resultSetTwo.getString(7));
                                item.put("transferAmount", resultSetTwo.getString(8));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondSubscriptionDetails(list);
                        }

                        ResultSet resultSetThree = (ResultSet) stmt.getObject(7);
                        if(resultSetThree != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetThree.next()) {
                                item = new HashMap<>();
                                item.put("nomineeName", resultSetThree.getString(1));
                                item.put("nomineeShare", resultSetThree.getString(2));
                                item.put("subscriptionId", resultSetThree.getString(3));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondNomineeDetails(list);
                        }

                        ResultSet resultSetFour = (ResultSet) stmt.getObject(8);
                        if(resultSetFour != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetFour.next()) {
                                item = new HashMap<>();
                                item.put("transferOutToBankName", resultSetFour.getString(1));
                                item.put("transferOutToBranchName", resultSetFour.getString(2));
                                 item.put("transferInToBranchId", resultSetFour.getString(3));
                                item.put("transferInToSameBranchName", resultSetFour.getString(4));
                                item.put("remarks", resultSetFour.getString(5));
                                item.put("isTranOutToOtherBank", resultSetFour.getString(6));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondTransferOutDetails(list);
                        }
                         rbiBondAccTransferOutDTO.setVerifiedStatus(stmt.getString(9));
                        responseDataDTO.setResult(rbiBondAccTransferOutDTO);
                    }

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
    public ResponseDataDTO verify(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACC_TRF_OUT_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getSchemeCode());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBlaNo());
                stmt.setArray(index++, generateArrayForSubscriptionDetailsAdd(connection, rbiBondAccTransferOutDTO.getSubscriptionDetailsList()));

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
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
    public ResponseDataDTO verifyResponse(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACC_TRF_OUT_VER_RES.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getGbmTranId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getCbsTranId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getCbsTranStatus());

                stmt.setString(index++, rbiBondAccTransferOutDTO.getCbsTranStatusDescription());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    rbiBondAccTransferOutDTO.setCbsTranId(stmt.getString(7));
                    rbiBondAccTransferOutDTO.setCbsTranStatus(stmt.getString(8));
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
        responseDataDTO.setResult(rbiBondAccTransferOutDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondAccTransferOutDTO.getRemarks() == null
                || rbiBondAccTransferOutDTO.getRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACC_TRF_OUT_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getSchemeCode());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBlaNo());
                stmt.setArray(index++, generateArrayForSubscriptionDetailsAdd(connection, rbiBondAccTransferOutDTO.getSubscriptionDetailsList()));
                stmt.setString(index++, rbiBondAccTransferOutDTO.getRemarks());

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
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
    public ResponseDataDTO modifySelect(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACC_TRF_OUT_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getSchemeCode());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBlaNo());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getGbmTranId());

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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(rbiBondAccTransferOutDTO.getBlaNo() == null
                            && rbiBondAccTransferOutDTO.getIsFinalSubmit().equalsIgnoreCase("N")){

                        ResultSet resultSetOne = (ResultSet) stmt.getObject(15);
                        if(resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()){
                                item = new HashMap<>();
                                item.put("blaNo", resultSetOne.getString(1));
                                item.put("holderName", resultSetOne.getString(2));
                                item.put("modeOfHolding", resultSetOne.getString(3));
                                item.put("verifiedStatus", resultSetOne.getString(4));
                                item.put("isTransferToOtherBank", resultSetOne.getString(5));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }
                    if(rbiBondAccTransferOutDTO.getBlaNo() != null
                            && rbiBondAccTransferOutDTO.getIsFinalSubmit().equalsIgnoreCase("N")){

                        ResultSet resultSetOne = (ResultSet) stmt.getObject(15);
                        if(resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSetOne.getString(1));
                                item.put("holderName", resultSetOne.getString(2));
                                item.put("gbsTranId", resultSetOne.getString(3));
                                item.put("verifiedStatus", resultSetOne.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }

                    if(rbiBondAccTransferOutDTO.getBlaNo() != null
                            && rbiBondAccTransferOutDTO.getIsFinalSubmit().equalsIgnoreCase("Y")){


                        ResultSet resultSetOne = (ResultSet) stmt.getObject(15);
                        if(resultSetOne != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetOne.next()) {
                                item = new HashMap<>();
                                item.put("gbsTranId", resultSetOne.getString(1));
                                item.put("singleBlaNo", resultSetOne.getString(2));
                                item.put("singleHolderName", resultSetOne.getString(3));
                                item.put("firstJointBlaNo", resultSetOne.getString(4));
                                item.put("firstJointHolderName", resultSetOne.getString(5));
                                item.put("secondJointBlaNo", resultSetOne.getString(6));
                                item.put("secondJointHolderName", resultSetOne.getString(7));
                                item.put("bondHolderType", resultSetOne.getString(8));
                                item.put("isNri", resultSetOne.getString(9));
                                item.put("modeOfHolding", resultSetOne.getString(10));
                                item.put("modeOfHoldingCode", resultSetOne.getString(11));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondHolderDetails(list);
                        }

                        ResultSet resultSetTwo = (ResultSet) stmt.getObject(16);
                        if(resultSetTwo != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetTwo.next()) {
                                item = new HashMap<>();
                                item.put("subscriptionDate", resultSetTwo.getString(1));
                                item.put("subscriptionId", resultSetTwo.getString(2));
                                item.put("subscriptionAmount", resultSetTwo.getString(3));
                                item.put("bondType", resultSetTwo.getString(4));
                                item.put("maturityDate", resultSetTwo.getString(5));
                                item.put("rateOfInterest", resultSetTwo.getString(6));
                                item.put("interestPaidUpTo", resultSetTwo.getString(7));
                                item.put("transferAmount", resultSetTwo.getString(8));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondSubscriptionDetails(list);
                        }

                        ResultSet resultSetThree = (ResultSet) stmt.getObject(17);
                        if(resultSetThree != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetThree.next()) {
                                item = new HashMap<>();
                                item.put("nomineeName", resultSetThree.getString(1));
                                item.put("nomineeShare", resultSetThree.getString(2));
                                item.put("subscriptionId", resultSetThree.getString(3));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondNomineeDetails(list);
                        }

                        ResultSet resultSetFour = (ResultSet) stmt.getObject(18);
                        if(resultSetFour != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSetFour.next()) {
                                item = new HashMap<>();
                                item.put("transferOutToBankName", resultSetFour.getString(1));
                                item.put("transferOutToBranchName", resultSetFour.getString(2));
                                item.put("transferOutThrough", resultSetFour.getString(3));
                                item.put("creditAccDdpoNum", resultSetFour.getString(4));
                                item.put("transferInToBankName", resultSetFour.getString(5));
                                item.put("transferInToBranchName", resultSetFour.getString(6));
                                item.put("remarks", resultSetFour.getString(7));
                                list.add(item);
                            }
                            rbiBondAccTransferOutDTO.setBondTransferOutDetails(list);
                        }

                        rbiBondAccTransferOutDTO.setPkColumnValue(stmt.getString(6));
                        rbiBondAccTransferOutDTO.setCreatedBy(stmt.getString(7));
                        rbiBondAccTransferOutDTO.setCreatedDate(stmt.getString(8));
                        rbiBondAccTransferOutDTO.setLastModifiedBy(stmt.getString(9));
                        rbiBondAccTransferOutDTO.setLastModifiedDate(stmt.getString(10));
                        rbiBondAccTransferOutDTO.setVerifiedBy(stmt.getString(11));
                        rbiBondAccTransferOutDTO.setVerifiedDate(stmt.getString(12));
                        rbiBondAccTransferOutDTO.setPreviousOperationType(stmt.getString(13));
                        rbiBondAccTransferOutDTO.setVerifiedStatus(stmt.getString(14));
                        responseDataDTO.setResult(rbiBondAccTransferOutDTO);
                    }

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
    public ResponseDataDTO modify(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACC_TRF_OUT_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getSchemeCode());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getIsChanged());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getBlaNo());

                stmt.setArray(index++, generateArrayForNomineeDetails(connection, rbiBondAccTransferOutDTO.getSubscriptionIds()));
                stmt.setString(index++, rbiBondAccTransferOutDTO.getTransferToOutBankName());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getTransferToOutBranchName());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getTransferOutThroughIn());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getCreditAccNumDdPpo());

                stmt.setString(index++, rbiBondAccTransferOutDTO.getTotTransferAmount());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getTransferToInBankName());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getTransferToInBranchName());
                stmt.setString(index++, rbiBondAccTransferOutDTO.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);

                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
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



}
