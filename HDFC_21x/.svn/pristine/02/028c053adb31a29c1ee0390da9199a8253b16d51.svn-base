package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.BondInwardOutwardDAO;
import com.isl.gbm.rbibond.dto.BondInwardOutwardDTO;
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
import java.util.*;


@Repository
public class BondInwardOutwardDAOimpl implements BondInwardOutwardDAO {

    private static final Logger log = LoggerFactory.getLogger(BondInwardOutwardDAOimpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO getAddInRefNo(BondInwardOutwardDTO bondInwardOutwardDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_OUT.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInwardOutwardDTO.getSchemeCode());
                stmt.setString(index++, bondInwardOutwardDTO.getInRefNo());
                stmt.setString(index++, bondInwardOutwardDTO.getBlaNo());
                stmt.setString(index++, bondInwardOutwardDTO.getFlag().equalsIgnoreCase("S") || bondInwardOutwardDTO.getFlag().equalsIgnoreCase("F") ? null:bondInwardOutwardDTO.getFlag());
                stmt.setString(index++, bondInwardOutwardDTO.getRequestType());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(8);

                    if(bondInwardOutwardDTO.getFlag().equalsIgnoreCase("I") && "C".equalsIgnoreCase(bondInwardOutwardDTO.getRequestType())) {
                        if (resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("inRefNo", resultSet.getString(1));
                                item.put("panNo", resultSet.getString(2));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if(bondInwardOutwardDTO.getFlag().equalsIgnoreCase("I") && "B".equalsIgnoreCase(bondInwardOutwardDTO.getRequestType())) {
                        if (resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("inRefNo", resultSet.getString(1));
                                item.put("panNo", resultSet.getString(2));
                                item.put("brokerCode", resultSet.getString(3));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else{
                        if (resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("uniqueRefNo", resultSet.getString(1));
                                item.put("ftsAppNum", resultSet.getString(2));
                                item.put("blaNo", resultSet.getString(3));
                                item.put("boSubsId", resultSet.getString(4));
                                item.put("userName", resultSet.getString(5));
                                item.put("requestCode", resultSet.getString(6));
                                item.put("requestDesc", resultSet.getString(7));
                                item.put("inRefNo", resultSet.getString(8));
                                item.put("inwDate", resultSet.getString(9));
                                item.put("schemeCode", resultSet.getString(10));
                                item.put("schemeName", resultSet.getString(11));
//                                item.put("requestType", resultSet.getString(12));
//                                item.put("brokerName", resultSet.getString(13));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
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
    public ResponseDataDTO addOutward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_OUT_RES.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setArray(index++, generateArrayForOutAdd(connection, bondInwardOutwardDTO.getInwDetailsList()));
                stmt.setString(index++, bondInwardOutwardDTO.getOutwardRefNo());
                stmt.setString(index++, bondInwardOutwardDTO.getInwDate());
                stmt.setString(index++, bondInwardOutwardDTO.getTranStatus());
                stmt.setString(index++, bondInwardOutwardDTO.getRemarks());
                stmt.setString(index++, bondInwardOutwardDTO.getPodConsignmentNumber());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                //15
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
    public ResponseDataDTO rejectOutward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_OUT_REJ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setArray(index++, generateArrayForOutReject(connection, bondInwardOutwardDTO.getInwDetailsList()));
                stmt.setString(index++, bondInwardOutwardDTO.getOutwardRefNo());
                stmt.setString(index++, bondInwardOutwardDTO.getInwDate());
                stmt.setString(index++, bondInwardOutwardDTO.getTranStatus());
                stmt.setString(index++, bondInwardOutwardDTO.getRemarks());
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
    public ResponseDataDTO addInward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_INW.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInwardOutwardDTO.getScreenType());
                stmt.setString(index++, bondInwardOutwardDTO.getFtsAppNum());
                String holderName = "B".equalsIgnoreCase(bondInwardOutwardDTO.getRequestType())
                        ? bondInwardOutwardDTO.getBrokerName()
                        : bondInwardOutwardDTO.getNameOfTheBondHolder();
                stmt.setString(index++, holderName);
                stmt.setString(index++, bondInwardOutwardDTO.getPanNo());
                stmt.setString(index++, bondInwardOutwardDTO.getInRefNo());
                stmt.setString(index++, bondInwardOutwardDTO.getInwDate());
                stmt.setString(index++, bondInwardOutwardDTO.getInwardRemarks());
                stmt.setString(index++, bondInwardOutwardDTO.getRequestType());
                stmt.setArray(index++, generateArrayForInwAdd(connection, bondInwardOutwardDTO.getInwDetailsList()));
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                //15
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                /*if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS) && bondInwardOutwardDTO.getSchemeCode() == null) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(15);
                    if(bondInwardOutwardDTO.getSchemeCode() == null){
                        if(resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("schemeName",  resultSet.getString(2));
                                item.put("schemeStatus",  resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                }
*/
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

    private Array generateArrayForInwAdd(Connection connection, List<BondInwardOutwardDTO> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_TYP_GBM_RBI_BOND_IN_OUT_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_TYP_GBM_RBI_BOND_IN_OUT, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[6];

            BondInwardOutwardDTO item = list.get(index);
            paramArray[0] = item.getRequestCode();
            paramArray[1] = item.getRequestDesc();
            paramArray[2] = item.getBlaNo();
            paramArray[3] = item.getSchemeCode();
            paramArray[4] = item.getUniqueRefNo();
            paramArray[5] = item.getBrokerCode();
            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private Array generateArrayForOutAdd(Connection connection, List<BondInwardOutwardDTO> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_TYP_GBM_RBI_BOND_OUT_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_TYP_GBM_RBI_BOND_OUT, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[4];
            BondInwardOutwardDTO item = list.get(index);
            paramArray[0] = item.getInRefNo();
            paramArray[1] = item.getRequestCode();
            paramArray[2] = item.getSchemeCode();
            paramArray[3] = item.getBlaNo();
            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private Array generateArrayForOutReject(Connection connection, List<BondInwardOutwardDTO> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_TYP_GBM_RBI_BOND_OUT_REJ_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_TYP_GBM_RBI_BOND_OUT_REJ, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[4];
            BondInwardOutwardDTO item = list.get(index);
            paramArray[0] = item.getInRefNo();
            paramArray[1] = item.getRequestCode();
            paramArray[2] = item.getSchemeCode();
            paramArray[3] = item.getBlaNo();
            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private Array generateArrayForBrokerDetailsVerify(Connection connection, List<BondInwardOutwardDTO> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_TYP_RBI_BOND_BRK_PAYMNT_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_TYP_RBI_BOND_BRK_PAYMNT, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[2];
            BondInwardOutwardDTO item = list.get(index);
            paramArray[0] = item.getBrokerAgencyCode();
            paramArray[1] = item.getRemarks();


            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


    @Override
    public ResponseDataDTO getPanNoTor(BondInwardOutwardDTO bondInwardOutwardDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_INW_PAN_TOR.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInwardOutwardDTO.getSchemeCode());
                stmt.setString(index++, bondInwardOutwardDTO.getRequestType());
                stmt.setString(index++, bondInwardOutwardDTO.getNameOfTheBondHolder());
                stmt.setString(index++, bondInwardOutwardDTO.getPanNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if(resultSet != null && "C".equalsIgnoreCase(bondInwardOutwardDTO.getRequestType())) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();
                                item.put("nameOfTheBondHolder", resultSet.getString(1));
                                item.put("panNo",  resultSet.getString(2));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                        else if(resultSet != null && "B".equalsIgnoreCase(bondInwardOutwardDTO.getRequestType())) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();
                                item.put("brokerName", resultSet.getString(1));
                                item.put("panNo",  resultSet.getString(2));
                                item.put("brokerCode",  resultSet.getString(3));

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
    public ResponseDataDTO inrRefNum(BondInwardOutwardDTO bondInwardOutwardDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_INW_OUT_REF_NO.getProcName());
                stmt.setString(index++, bondInwardOutwardDTO.getScreenType());
                stmt.setString(index++, bondInwardOutwardDTO.getInwDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                //10
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                BondInwardOutwardDTO inwardOutwardDTO = new BondInwardOutwardDTO();
                inwardOutwardDTO.setInRefNo(stmt.getString(3));
                responseDataDTO.setResult(inwardOutwardDTO.getInRefNo());

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
    public ResponseDataDTO getBlaNoTor(BondInwardOutwardDTO bondInwardOutwardDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_INW_BLA_TOR.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInwardOutwardDTO.getSchemeCode());
                stmt.setString(index++, bondInwardOutwardDTO.getNameOfTheBondHolder());
                stmt.setString(index++, bondInwardOutwardDTO.getPanNo());
                stmt.setString(index++, bondInwardOutwardDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                //10
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if(resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder",  resultSet.getString(2));
                                item.put("modeOfHolding",  resultSet.getString(3));
                                item.put("panNo",  resultSet.getString(4));
                                item.put("ftsAppNum",  resultSet.getString(5));
                                item.put("schemeCode",  resultSet.getString(6));
                                item.put("schemeName",  resultSet.getString(7));
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
    public ResponseDataDTO verifyResponse(BondInwardOutwardDTO bondInwardOutwardDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_PAY_VER_RES.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInwardOutwardDTO.getGbsTranId());
                stmt.setString(index++, bondInwardOutwardDTO.getCbsTranId());
                stmt.setString(index++, bondInwardOutwardDTO.getCbsTranStatus());

                stmt.setString(index++, bondInwardOutwardDTO.getCbsTranStatusDescription());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    bondInwardOutwardDTO.setCbsTranId(stmt.getString(7));
                    bondInwardOutwardDTO.setCbsTranStatus(stmt.getString(8));
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
        responseDataDTO.setResult(bondInwardOutwardDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBlaSubsIdTor(BondInwardOutwardDTO bondInwardOutwardDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_INW_SUBS_ID_TOR.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInwardOutwardDTO.getSchemeCode());
                stmt.setString(index++, bondInwardOutwardDTO.getNameOfTheBondHolder());
                stmt.setString(index++, bondInwardOutwardDTO.getPanNo());
                stmt.setString(index++, bondInwardOutwardDTO.getBlaNo());
                stmt.setString(index++, bondInwardOutwardDTO.getBoSubsId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(8);
                    if(resultSet != null) {
                        List<Map<String, String>> list = new ArrayList<>();

                        Map<String, String> item = null;
                        while (resultSet.next()){
                            item = new HashMap<>();
                            item.put("boSubsId",resultSet.getString(1));
                            item.put("nameOfTheBondHolder",resultSet.getString(2));
                            item.put("modeOfHolding",resultSet.getString(3));
                            item.put("panNo",resultSet.getString(4));
                            list.add(item);

                        }
                        responseDataDTO.setResult(list);
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
    public ResponseDataDTO delete(BondInwardOutwardDTO bondInwardOutwardDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_PAY_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInwardOutwardDTO.getSchemeCode());
                stmt.setArray(index++, generateArrayForBrokerDetailsVerify(connection, bondInwardOutwardDTO.getInwDetailsList()));
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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

}
