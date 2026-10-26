package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.ModeOfHoldingChangeDao;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.util.NumberToWordConverter;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ModeOfHoldingChangeDaoImpl implements ModeOfHoldingChangeDao {
    private static final Logger log = LoggerFactory.getLogger(ModeOfHoldingChangeDaoImpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private NumberToWordConverter numberToWordConverter;

    @Value("${profile.type}")
    private String stubProcess;

    @Override
    public ResponseDataDTO getRbiBondModuleNameDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        Connection connection = null;
        PreparedStatement stmt = null;
        CallableStatement stmt1 = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if(null!=modeOfHoldingChangeDTO&&null!=modeOfHoldingChangeDTO.getReferenceId()&&modeOfHoldingChangeDTO.getReferenceId().equalsIgnoreCase("I")){
                    stmt1 = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_INQUIRY.getProcName());
                    stmt1.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt1.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt1.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                    stmt1.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                    stmt1.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                    stmt1.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                    stmt1.setString(index++, modeOfHoldingChangeDTO.getSubscriptionId());
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index, Types.VARCHAR);
                    stmt1.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt1.getString(index));
                    resultMsg = stmt1.getString(index);
                    if (stmt1.getString(index) != null && stmt1.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        List<HashMap<String, String>> resultList = new ArrayList<>();
                        resultSet = (ResultSet) stmt1.getObject(8);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("subCode", resultSet.getString(1));
                                resultMap.put("subCodeDescription", resultSet.getString(2));
                                resultMap.put("status", resultSet.getString(3));
                                resultList.add(resultMap);
                            }
                        }
                        responseDataDTO.setResult(resultList);
                    }
                }else if(null!=modeOfHoldingChangeDTO&&null!=modeOfHoldingChangeDTO.getReferenceId()&&modeOfHoldingChangeDTO.getReferenceId().equalsIgnoreCase("M")){
                    stmt1 = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_MODIFY_SEL.getProcName());
                    stmt1.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt1.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt1.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                    stmt1.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                    stmt1.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                    stmt1.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                    stmt1.setString(index++, modeOfHoldingChangeDTO.getSubscriptionId());
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index, Types.VARCHAR);
                    stmt1.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt1.getString(index));
                    resultMsg = stmt1.getString(index);
                    if (stmt1.getString(index) != null && stmt1.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        List<HashMap<String, String>> resultList = new ArrayList<>();
                        resultSet = (ResultSet) stmt1.getObject(8);
                        if (resultSet != null){
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("subCode", resultSet.getString(1));
                                resultMap.put("subCodeDescription", resultSet.getString(2));
                                resultMap.put("status", resultSet.getString(3));
                                resultList.add(resultMap);
                            }
                        }
                        responseDataDTO.setResult(resultList);
                    }
                }
                else {
                    if("PROD".equalsIgnoreCase(stubProcess)) {
                        stmt = connection.prepareStatement("SELECT T2.sub_code AS sub_code,T2.sub_code_desc AS sub_code_desc,\n" +
                                "CASE WHEN T3.scheme_status ='C' THEN 'Current'\n" +
                                "WHEN T3.scheme_status ='A' THEN 'Active'\n" +
                                "WHEN T3.scheme_status ='CL' THEN 'Closed' ELSE NULL END AS scheme_status\n" +
                                "FROM gbm_code_master T1 INNER JOIN\n" +
                                "gbm_sub_code_master T2 ON T1.code =T2.code\n" +
                                "INNER JOIN gbm_rbi_bond_scheme_details T3 ON T3.sch_code =T2.sub_code\n" +
                                "WHERE T2.code='012' AND T2.verified_status ='Y' AND T2.is_Active ='Y' AND T2.is_deleted ='N' \n" +
                                "AND T3.verified_status ='Y' AND T3.is_active ='Y' AND T3.is_deleted ='N' AND T3.scheme_status IN ('C')");
                    }else {
                        stmt = connection.prepareStatement("SELECT T2.sub_code AS sub_code,T2.sub_code_desc AS sub_code_desc,\n" +
                                "CASE WHEN T3.scheme_status ='C' THEN 'Current'\n" +
                                "WHEN T3.scheme_status ='A' THEN 'Active'\n" +
                                "WHEN T3.scheme_status ='CL' THEN 'Closed' ELSE NULL END AS scheme_status\n" +
                                "FROM gbm_code_master T1 INNER JOIN\n" +
                                "gbm_sub_code_master T2 ON T1.code =T2.code\n" +
                                "INNER JOIN gbm_rbi_bond_scheme_details T3 ON T3.sch_code =T2.sub_code\n" +
                                "WHERE T2.code='012' AND T2.verified_status ='Y' AND T2.is_Active ='Y' AND T2.is_deleted ='N' \n" +
                                "AND T3.verified_status ='Y' AND T3.is_active ='Y' AND T3.is_deleted ='N' AND T3.scheme_status IN ('C','A','CL')");
                    }
                    resultSet = stmt.executeQuery();

                    List<HashMap<String, String>> resultList = new ArrayList<>();
                    if (resultSet != null)
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("subCode", resultSet.getString(1));
                            resultMap.put("subCodeDescription", resultSet.getString(2));
                            resultMap.put("status", resultSet.getString(3));
                            resultList.add(resultMap);
                        }
                    responseDataDTO.setResult(resultList);
                    resultMsg = CommonConstants.SUCCESS_CONST;
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
    public ResponseDataDTO validatePanRequest(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        PanDetailsDTO panDetailsDTO=null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_PAN_TRIGGER_REQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setArray(index++, generateTYP_typ_rbi_bond_moh_req_OBJ(connection, modeOfHoldingChangeDTO));
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(null!=modeOfHoldingChangeDTO){
                        resultSet = (ResultSet) stmt.getObject(5);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                panDetailsDTO=new PanDetailsDTO();
                                panDetailsDTO.setPan(resultSet.getString(1));
                                panDetailsDTO.setName(resultSet.getString(2));
                                panDetailsDTO.setDob(resultSet.getString(3));
                                responseDataDTO.setResult(panDetailsDTO);
                            }
                        }
                    }
                }
                else{
                    responseDataDTO.setStatusCode("0");
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO apiResponse(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_PAN_TRIGGER_RES.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, panValidationRetriggerResponseDTO.getSchemeCode());
                stmt.setArray(index++, generateTYP_typ_rbi_bond_moh_res_OBJ(connection, panValidationRetriggerResponseDTO));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    private ARRAY generateTYP_typ_rbi_bond_moh_res_OBJ(Connection connection, PanValidationRetriggerResponseDTO dto) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_PAN_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_PAN", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[1];
        Object[] paramArray = new Object[14];
        paramArray[0] = dto.getPanNumber();
        paramArray[1] = dto.getNameStatus();
        paramArray[2] = dto.getPanStatus();
        paramArray[3] = dto.getDobStatus();
        paramArray[4] = dto.getProcessStatus();
        // New fields added to paramArray
        paramArray[5] = dto.getPanStatusDescription();
        paramArray[6] = dto.getPanNameDescription();
        paramArray[7] = dto.getFatherName();
        paramArray[8] = dto.getPanDobDescription();
        paramArray[9] = dto.getReturnMessage();
        paramArray[10] = dto.getReturnCode();
        paramArray[11] = dto.getReturnDescription();
        paramArray[12] = dto.getSeedingStatus();
        paramArray[13] = dto.getSeedingStatusDescription();
        STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
        genericTableInsertStructs[0] = struct;
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    private ARRAY generateTYP_typ_rbi_bond_moh_req_OBJ(Connection connection, ModeOfHoldingChangeDTO dto) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PAN_DET_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PAN_DET", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[1];
        Object[] paramArray = new Object[3];
        paramArray[0] = dto.getPanNum();
        paramArray[1] = dto.getBondHolderName();
        paramArray[2] = dto.getDob();
        STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
        genericTableInsertStructs[0] = struct;
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    @Override
    public ResponseDataDTO getMinorDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_ADD_GET_MINOR_DETAILS_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getModeOfHolding());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    List<HashMap<String, String>> torchOut = null;
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();
                            temp.put("blaNo", resultSet.getString(1));
                            temp.put("customerName", resultSet.getString(2));
                            temp.put("modeOfHolding", resultSet.getString(3));
                            temp.put("panNo", resultSet.getString(4));
                            temp.put("isMinor", resultSet.getString(5));
                            torchOut.add(temp);
                        }
                        modeOfHoldingChangeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(modeOfHoldingChangeDTO);
        return responseDataDTO;
    }



    @Override
    public ResponseDataDTO getWboBranchDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareStatement("SELECT sol_id AS branch_code_wbo,sol_desc  AS branch_name_wbo FROM gbm_bank_branches WHERE \n" +
                        "del_flg='0' AND ver_flg='1' AND status ='1' AND auditable ='1'");
                resultSet = stmt.executeQuery();
                List<HashMap<String, String>> resultList = new ArrayList<>();
                if (resultSet != null)
                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("wboBranchCode",resultSet.getString(1));
                        resultMap.put("wboBranchName", resultSet.getString(2));

                        resultList.add(resultMap);
                    }
                responseDataDTO.setResult(resultList);
                resultMsg = CommonConstants.SUCCESS_CONST;
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
    public ResponseDataDTO getBrokerTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_ADD_GET_BROKER_ID.getProcName());
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
                            temp.put("brokerAgencyCode", resultSet.getString(1));
                            temp.put("brokerName", resultSet.getString(2));
                            temp.put("operativeAccBranchId", resultSet.getString(3));
                            torchOut.add(temp);
                        }
                        modeOfHoldingChangeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(modeOfHoldingChangeDTO);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO getGuardianDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_ADD_GET_GUARD_ID.getProcName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, modeOfHoldingChangeDTO.getGuardianId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(4);

                    if (modeOfHoldingChangeDTO.getGuardianId() == null) {

                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("guardianId", resultSet.getString(4));
                                item.put("guardianName", resultSet.getString(5));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else {
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("guardianFirstName", resultSet.getString(1));
                                item.put("guardianMiddleName", resultSet.getString(2));
                                item.put("guardianLastName", resultSet.getString(3));
                                item.put("relationWithMinor", resultSet.getString(4));
                                item.put("guardianDateOfBirth", resultSet.getString(5));
                                item.put("guardianCommunicationAddress", resultSet.getString(6));
                                item.put("mobileNo", resultSet.getString(7));
                                item.put("email", resultSet.getString(8));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                        responseDataDTO.setResult(list);
                    }

                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAddModuleDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ModeOfHoldingChangeDTO modeOfHoldingChangeDTO1=new ModeOfHoldingChangeDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_ADD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getFlag());
                stmt.setString(index++, modeOfHoldingChangeDTO.getModeOfHolding());
                stmt.setString(index++, modeOfHoldingChangeDTO.getFromDate1());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(10);
                    ResultSet resultSet1 = (ResultSet) stmt.getObject(11);
                    if (modeOfHoldingChangeDTO.getSchemeCode() == null) {
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("subCode", resultSet.getString(1));
                                item.put("subCodeDescription", resultSet.getString(2));
                                item.put("status", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    else if (null!=modeOfHoldingChangeDTO.getSchemeCode()&&null!=modeOfHoldingChangeDTO.getFlag()&&
                            modeOfHoldingChangeDTO.getFlag().equalsIgnoreCase("P")) {
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panHldName", resultSet.getString(1));
                                item.put("panNum", resultSet.getString(2));
                                item.put("fromDate1", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else if(null!=modeOfHoldingChangeDTO.getSchemeCode()&&null!=modeOfHoldingChangeDTO.getFlag()&&
                            modeOfHoldingChangeDTO.getFlag().equalsIgnoreCase("B")) {
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("bondHldName", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("panNum", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else if(null!=modeOfHoldingChangeDTO.getSchemeCode()&&null!=modeOfHoldingChangeDTO.getFlag()&&
                            modeOfHoldingChangeDTO.getFlag().equalsIgnoreCase("D")) {
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("panNo", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("noOfJointAcc", resultSet.getString(4));
                                item.put("blaNo", resultSet.getString(5));
                                item.put("bondHldFirstName", resultSet.getString(6));
                                item.put("bondHldMidName", resultSet.getString(7));
                                item.put("bondHldLastName", resultSet.getString(8));
                                item.put("cifId", resultSet.getString(9));
                                item.put("ftsAppNum", resultSet.getString(10));
                                item.put("holderType", resultSet.getString(11));
                                item.put("fatherName", resultSet.getString(12));
                                item.put("motherName", resultSet.getString(13));
                                item.put("gender", resultSet.getString(14));
                                item.put("bondHldDob", resultSet.getString(15));
                                item.put("isMinor", resultSet.getString(16));
                                item.put("detailsOfBirthCertificate", resultSet.getString(17));
                                item.put("bondHldAddOne", resultSet.getString(18));
                                item.put("bondHldAddTwo", resultSet.getString(19));
                                item.put("bondHldAddThree", resultSet.getString(20));
                                item.put("bondHldPinCode", resultSet.getString(21));
                                item.put("bondHldCity", resultSet.getString(22));
                                item.put("bondHldState", resultSet.getString(23));
                                item.put("bondHolderPhoneNumber", resultSet.getString(24));
                                item.put("bondHldMobNum", resultSet.getString(25));
                                item.put("bondHldEmail", resultSet.getString(26));
                                item.put("hufRegPowerOfAttorney", resultSet.getString(27));
                                item.put("powerOfAttorneyKartaName", resultSet.getString(28));
                                item.put("hdfcOtherBank", resultSet.getString(29));
                                item.put("operativeAccNum", resultSet.getString(30));
                                item.put("modeOfInterestPayment", resultSet.getString(31));
                                item.put("ifscCode", resultSet.getString(32));
                                item.put("micrCode", resultSet.getString(33));
                                item.put("accTypeCode", resultSet.getString(34));
                                item.put("bankCode", resultSet.getString(35));
                                item.put("branchCode", resultSet.getString(36));
                                item.put("nameOfthePayee", resultSet.getString(37));
                                item.put("operativeAccNumber", resultSet.getString(38));
                                item.put("aadhaarNo", resultSet.getString(39));
                                item.put("witnessNameOne", resultSet.getString(40));
                                item.put("witnessAddressOne", resultSet.getString(41));
                                item.put("witnessNameTwo", resultSet.getString(42));
                                item.put("witnessAddressTwo", resultSet.getString(43));
                                item.put("birthCertificate", resultSet.getString(44));
                                item.put("passport", resultSet.getString(45));
                                item.put("seniorCitizenCard", resultSet.getString(46));
                                item.put("voterIdCard", resultSet.getString(47));
                                item.put("panCard", resultSet.getString(48));
                                item.put("drivingLicence", resultSet.getString(49));
                                item.put("rationCard", resultSet.getString(50));
                                //item.put("otherBankFlg", resultSet.getString(44));
                                item.put("otherDocuments", resultSet.getString(51));
                                item.put("detailsForOthers", resultSet.getString(52));
                                item.put("remarks", resultSet.getString(53));
                                item.put("blaGroupId", resultSet.getString(54));
                                item.put("jointHolderType", resultSet.getString(55));
                                list.add(item);
                            }
                            modeOfHoldingChangeDTO1.setCustomerMasterOut(list);
                            modeOfHoldingChangeDTO1.setBlaFlag(stmt.getString(12));
                            responseDataDTO.setResult(modeOfHoldingChangeDTO1);
                        }
                        if (resultSet1 != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet1.next()) {
                                item = new HashMap<>();
                                item.put("guardianId", resultSet1.getString(1));
                                item.put("guardianFirstName", resultSet1.getString(2));
                                item.put("guardianMiddleName", resultSet1.getString(3));
                                item.put("guardianLastName", resultSet1.getString(4));
                                item.put("relationWithMinor", resultSet1.getString(5));
                                item.put("guardianDateOfBirth", resultSet1.getString(6));
                                item.put("guardianCommunicationAddress", resultSet1.getString(7));
                                item.put("guardianMobileNo", resultSet1.getString(8));
                                item.put("guardianEmailId", resultSet1.getString(9));
                                list.add(item);
                            }
                            modeOfHoldingChangeDTO1.setGuardianDetailsOut(list);
                            responseDataDTO.setResult(modeOfHoldingChangeDTO1);
                        }
                    }
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO getDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        BondSubscripReversalAccNoDTO values = new BondSubscripReversalAccNoDTO();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_ADD_FROM_TO_BLA.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getFlag());
                stmt.setString(index++, modeOfHoldingChangeDTO.getModeOfHolding());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, null!=modeOfHoldingChangeDTO.getFromBlaNo()?modeOfHoldingChangeDTO.getFromBlaNo():modeOfHoldingChangeDTO.getToBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(modeOfHoldingChangeDTO.getSchemeCode() == null) {
                        log.info("in scheme");
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    else if (modeOfHoldingChangeDTO.getFlag() != null && modeOfHoldingChangeDTO.getFlag().equalsIgnoreCase("FB")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();
                                item.setPanNum(resultSet.getString(1));
                                item.setBondHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));
                                item.setFromBlaNo(resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    else if (modeOfHoldingChangeDTO.getSchemeCode()!=null && modeOfHoldingChangeDTO.getFlag() != null
                            && modeOfHoldingChangeDTO.getFlag().equalsIgnoreCase("S")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();
                                item.setSubscriptionId(resultSet.getString(1));
                                item.setSubscriptionDate(resultSet.getString(2));
                                item.setSubscriptionAmt(resultSet.getString(3));
                                list.add(item);
                            }
                            values.setCursorOut(list);
                            values.setCount(stmt.getString(9));
                            responseDataDTO.setResult(values);
                        }
                    } else if (modeOfHoldingChangeDTO.getSchemeCode()!=null && modeOfHoldingChangeDTO.getFlag() != null && modeOfHoldingChangeDTO.getFlag().equalsIgnoreCase("TB")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();
                                item.setPanNum(resultSet.getString(1));
                                item.setBondHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));
                                item.setToBlaNo(resultSet.getString(4));
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
    public ResponseDataDTO add(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO1=new BondLedgerAccountMaintenanceDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_ADD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getModeOfHolding());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPan_No1());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPan_No2());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPan_No3());
                stmt.setString(index++, modeOfHoldingChangeDTO.getId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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

                bondLedgerAccountMaintenanceDTO1.setBlaFlag(stmt.getString(14));
                bondLedgerAccountMaintenanceDTO1.setDdPayAccountNumber(stmt.getString(15));
                bondLedgerAccountMaintenanceDTO1.setPeriodOfDeposit(stmt.getString(16));
                bondLedgerAccountMaintenanceDTO1.setSchemeBondType(stmt.getString(17));
                bondLedgerAccountMaintenanceDTO1.setRateOfInterest(stmt.getString(18));
                bondLedgerAccountMaintenanceDTO1.setCashLimitForCash(stmt.getString(19));
                bondLedgerAccountMaintenanceDTO1.setCashLimitForClearingTransfer(stmt.getString(20));
                bondLedgerAccountMaintenanceDTO1.setDepositMultiples(stmt.getString(21));
                bondLedgerAccountMaintenanceDTO1.setMinCashLimit(stmt.getString(22));
                bondLedgerAccountMaintenanceDTO1.setAadharCapture(stmt.getString(23));
                bondLedgerAccountMaintenanceDTO1.setAadharMandatory(stmt.getString(24));
                bondLedgerAccountMaintenanceDTO1.setAadharDisplay(stmt.getString(25));


                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    if ("Y".equals(bondLedgerAccountMaintenanceDTO1.getBlaFlag())) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(10);

                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("jointHolderType", resultSet.getString(3));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setBlaNoOut(list);
                            responseDataDTO.setResult(list);
                        }


                    }  if ("Y".equals(bondLedgerAccountMaintenanceDTO1.getBlaFlag())) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(12);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("blaGroupId", resultSet.getString(2));
                                item.put("jointHolderType", resultSet.getString(3));
                                item.put("schemeCode", resultSet.getString(4));
                                item.put("guardianId", resultSet.getString(5));
                                item.put("guardianFirstName", resultSet.getString(6));
                                item.put("guardianMiddleName", resultSet.getString(7));
                                item.put("guardianLastName", resultSet.getString(8));
                                item.put("relationWithMinor", resultSet.getString(9));
                                item.put("guardianDateOfBirth", resultSet.getString(10));
                                item.put("guardianCommunicationAddress", resultSet.getString(11));
                                item.put("guardianMobileNo", resultSet.getString(12));
                                item.put("guardianEmailId", resultSet.getString(13));
                                item.put("guardianDeathDate", resultSet.getString(14));
                                item.put("dateOfReceivedDeathcerti", resultSet.getString(15));
                                item.put("curGuardianReq", resultSet.getString(16));
                                item.put("remarks", resultSet.getString(17));
                                item.put("fetchGuardId", resultSet.getString(18));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setGuardianDetailsOut(list);
                            responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                        }

                    }  if ("Y".equals(bondLedgerAccountMaintenanceDTO1.getBlaFlag())) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(11);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("panNo", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("noOfJointAcc", resultSet.getString(4));
                                item.put("blaNo", resultSet.getString(5));
                                item.put("bondHldFirstName", resultSet.getString(6));
                                item.put("bondHldMidName", resultSet.getString(7));
                                item.put("bondHldLastName", resultSet.getString(8));
                                item.put("bondHldDob", resultSet.getString(9));
                                item.put("cifId", resultSet.getString(10));
                                item.put("ftsAppNum", resultSet.getString(11));
                                item.put("holderType", resultSet.getString(12));
                                item.put("fatherName", resultSet.getString(13));
                                item.put("motherName", resultSet.getString(14));
                                item.put("gender", resultSet.getString(15));
                                item.put("isMinor", resultSet.getString(16));
                                item.put("detailsOfBirthCertificate", resultSet.getString(17));
                                item.put("bondHldAddOne", resultSet.getString(18));
                                item.put("bondHldAddTwo", resultSet.getString(19));
                                item.put("bondHldAddThree", resultSet.getString(20));
                                item.put("bondHldPinCode", resultSet.getString(21));
                                item.put("bondHldCity", resultSet.getString(22));
                                item.put("bondHldState", resultSet.getString(23));
                                item.put("bondHolderPhoneNumber", resultSet.getString(24));
                                item.put("bondHldMobNum", resultSet.getString(25));
                                item.put("bondHldEmail", resultSet.getString(26));
                                item.put("hufRegPowerOfAttorney", resultSet.getString(27));
                                item.put("powerOfAttorneyKartaName", resultSet.getString(28));
                                item.put("hdfcOtherBank", resultSet.getString(29));
                                item.put("operativeAccNum", resultSet.getString(30));
                                item.put("ifscCode", resultSet.getString(31));
                                item.put("aadhaarNo", resultSet.getString(32));
                                item.put("witnessNameOne", resultSet.getString(33));
                                item.put("witnessAddressOne", resultSet.getString(34));
                                item.put("witnessNameTwo", resultSet.getString(35));
                                item.put("witnessAddressTwo", resultSet.getString(36));
                                item.put("birthCertificate", resultSet.getString(37));
                                item.put("passport", resultSet.getString(38));
                                item.put("seniorCitizenCard", resultSet.getString(39));
                                item.put("voterIdCard", resultSet.getString(40));
                                item.put("panCard", resultSet.getString(41));
                                item.put("drivingLicence", resultSet.getString(42));
                                item.put("rationCard", resultSet.getString(43));
                                item.put("otherBankFlg", resultSet.getString(44));
                                item.put("otherDocuments", resultSet.getString(45));
                                item.put("remarks", resultSet.getString(46));
                                item.put("blaGroupId", resultSet.getString(47));
                                item.put("jointHolderType", resultSet.getString(48));
                                item.put("modeOfInterestPayment", resultSet.getString(49));
                                item.put("operativeAccNumber", resultSet.getString(50));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setCustomerMasterOut(list);
                            responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                        }

                    } if (modeOfHoldingChangeDTO.getId() != null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(13);
                        if ("Y".equals(bondLedgerAccountMaintenanceDTO1.getBlaFlag())) {
                            if (resultSet != null) {
                                list = new ArrayList<>();
                                HashMap<String, String> item; // Declare the item variable
                                while (resultSet.next()) {
                                    item = new HashMap<>();
                                    item.put("subscriptionAmount", resultSet.getString("SUBSCRIPTION_AMT"));
                                    item.put("bondType", resultSet.getString("BOND_TYPE"));
                                    item.put("accountOpeningDate", resultSet.getString("account_open_date"));
                                    item.put("modeOfInterestPayment", resultSet.getString("mode_of_interest"));
                                    item.put("operativeAccNum", resultSet.getString("operative_acc_number"));
                                    item.put("id", resultSet.getString("cashin_id"));
                                    item.put("brokerAgencyCode", resultSet.getString("broker_id"));
                                    list.add(item);
                                }
                                bondLedgerAccountMaintenanceDTO1.setDataCopersondetailsList1(list);
                                responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                            }
                        } else {
                            if (resultSet != null) {
                                list = new ArrayList<>();
                                HashMap<String, String> item; // Declare the item variable
                                while (resultSet.next()) {
                                    item = new HashMap<>(); // Initialize the item variable
                                    item.put("bondHldFirstName", resultSet.getString("FIRST_NAME"));
                                    item.put("bondHldDob", resultSet.getString("DOB"));
                                    item.put("holderType", resultSet.getString("holder_type"));
                                    item.put("gender", resultSet.getString("gender"));
                                    item.put("isMinor", resultSet.getString("minor_flg"));
                                    item.put("bondHldAddOne", resultSet.getString("ADD1"));
                                    item.put("bondHldAddTwo", resultSet.getString("ADD2"));
                                    item.put("bondHldPinCode", resultSet.getString("PINCODE"));
                                    item.put("bondHldState", resultSet.getString("STATE"));
                                    item.put("bondHldCity", resultSet.getString("CITY"));
                                    item.put("bondHldEmail", resultSet.getString("EMAIL_ADDRESS"));
                                    item.put("subscriptionAmount", resultSet.getString("SUBSCRIPTION_AMT"));
                                    item.put("bondType", resultSet.getString("BOND_TYPE"));
                                    item.put("accountOpeningDate", resultSet.getString("account_open_date"));
                                    item.put("modeOfInterestPayment", resultSet.getString("mode_of_interest"));
                                    item.put("operativeAccNumber", resultSet.getString("operative_acc_number"));
                                    item.put("firstName", resultSet.getString("nominee_first_name"));
                                    item.put("middleName", resultSet.getString("nominee_mid_name"));
                                    item.put("lastName", resultSet.getString("nominee_last_name"));
                                    item.put("addressOne", resultSet.getString("address1"));
                                    item.put("addressTwo", resultSet.getString("address2"));
                                    item.put("city", resultSet.getString("city"));
                                    item.put("state", resultSet.getString("STATE"));
                                    item.put("pinCode", resultSet.getString("pin_code"));
                                    item.put("phoneNo", resultSet.getString("phone_no"));
                                    item.put("sharePercentage", resultSet.getString("share_percentage"));
                                    item.put("isNomineeMinor", resultSet.getString("is_nominee_minor"));
                                    item.put("relationWithHolder", resultSet.getString("relation_with_holder"));
                                    item.put("dateOfBirthOfMinor", resultSet.getString("date_of_birth_of_minor"));
                                    item.put("id", resultSet.getString("cashin_id"));
                                    String rsNumber = resultSet.getString("mobile");
                                    if (rsNumber != null) {
                                        rsNumber = rsNumber.replaceFirst("^000+", "").replaceFirst("^00+", "").replaceFirst("^0+", "");
                                    } else {
                                        rsNumber = "";  // Default to empty string if mobile number is null
                                    }
                                    item.put("bondHldMobNum", rsNumber);
                                    item.put("instrumentNo", resultSet.getString("instrument_number"));
                                    item.put("valueDate", resultSet.getString("value_date"));
                                    item.put("instrumentDate", resultSet.getString("INSTRUMENT_DATE"));
                                    item.put("transactionMode", resultSet.getString("TRAN_MODE"));
                                    item.put("brokerAgencyCode", resultSet.getString("broker_id"));


                                    list.add(item);



                                }
                                bondLedgerAccountMaintenanceDTO1.setInstrumentDetailsOut(list);
                                responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                            }
                        }
                    }else{
                        bondLedgerAccountMaintenanceDTO1.setBlaFlag(stmt.getString(14));
                        bondLedgerAccountMaintenanceDTO1.setDdPayAccountNumber(stmt.getString(15));
                        bondLedgerAccountMaintenanceDTO1.setPeriodOfDeposit(stmt.getString(16));
                        bondLedgerAccountMaintenanceDTO1.setSchemeBondType(stmt.getString(17));
                        bondLedgerAccountMaintenanceDTO1.setRateOfInterest(stmt.getString(18));
                        bondLedgerAccountMaintenanceDTO1.setCashLimitForCash(stmt.getString(19));
                        bondLedgerAccountMaintenanceDTO1.setCashLimitForClearingTransfer(stmt.getString(20));
                        bondLedgerAccountMaintenanceDTO1.setDepositMultiples(stmt.getString(21));
                        bondLedgerAccountMaintenanceDTO1.setMinCashLimit(stmt.getString(22));
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                    }
                    }


            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO addSubmit(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaType());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, modeOfHoldingChangeDTO.getModeOfHolding());
                stmt.setString(index++, modeOfHoldingChangeDTO.getFromBlaNo());
                stmt.setString(index++, modeOfHoldingChangeDTO.getToBlaNo());
                stmt.setString(index++, modeOfHoldingChangeDTO.getReversalRemarks());
                if (modeOfHoldingChangeDTO.getDataList() != null&& "N".equalsIgnoreCase(modeOfHoldingChangeDTO.getBlaType()) &&null!=modeOfHoldingChangeDTO.getBlaType()) {
                    stmt.setArray(index++, generateTYP_ty_gbm_bond_holder_dtls(connection, modeOfHoldingChangeDTO.getDataList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_BLA_ARRAY);
                }
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(null!=modeOfHoldingChangeDTO&&null!=modeOfHoldingChangeDTO.getBlaType()&&
                            modeOfHoldingChangeDTO.getBlaType().equalsIgnoreCase("N")) {
                        modeOfHoldingChangeDTO.setBlaNo(stmt.getString(12));
                        modeOfHoldingChangeDTO.setFirstGuardianId(stmt.getString(13));
                    }
                    if(null!=stmt.getString(14)){
                        modeOfHoldingChangeDTO.setSubscriptionId(stmt.getString(14));}
                    responseDataDTO.setResult(modeOfHoldingChangeDTO);
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiry(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_INQUIRY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getFlag());
                stmt.setString(index++, modeOfHoldingChangeDTO.getModeOfHolding());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, modeOfHoldingChangeDTO.getFromBlaNo());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(10);
                    if (modeOfHoldingChangeDTO.getSchemeCode() == null) {
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("schemeName", resultSet.getString(2));
                                item.put("status", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else if(null!=modeOfHoldingChangeDTO.getSchemeCode()&&modeOfHoldingChangeDTO.getFlag().equalsIgnoreCase("P")){
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panHldName", resultSet.getString(1));
                                item.put("panNum", resultSet.getString(2));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else if (modeOfHoldingChangeDTO.getSchemeCode() != null && ("F".equalsIgnoreCase(modeOfHoldingChangeDTO.getFlag()) || "B".equalsIgnoreCase(modeOfHoldingChangeDTO.getFlag()))) {
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("blaNo", resultSet.getString(4));
                                item.put("verifiedStatus", resultSet.getString(5));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else if(null!=modeOfHoldingChangeDTO.getSchemeCode()&&modeOfHoldingChangeDTO.getFlag().equalsIgnoreCase("D")){
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(11);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(12);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(13);
                        if (resultSet != null) {
                            modeOfHoldingChangeDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        }if(resultSet2!= null){
                            modeOfHoldingChangeDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
                        }if(resultSet3!=null){
                            List<BondSubscripReversalAccNoDTO> list1=new ArrayList<>();
                            BondSubscripReversalAccNoDTO dto=null;
                            while (resultSet3.next()) {
                                dto=new BondSubscripReversalAccNoDTO();
                                dto.setBondHolderName(resultSet3.getString(1));
                                dto.setPanNum(resultSet3.getString(2));
                                dto.setModeOfHolding(resultSet3.getString(3));
                                dto.setFromBlaNo(resultSet3.getString(4));
                                dto.setCount(resultSet3.getString(5));
                                dto.setToBlaNo(resultSet3.getString(6));
                                dto.setRemarks(resultSet3.getString(7));
                                modeOfHoldingChangeDTO.setReversalObj(dto);
                            }
                            if(resultSet4!=null){
                                while (resultSet4.next()) {
                                    dto=new BondSubscripReversalAccNoDTO();
                                    dto.setSubscriptionId(resultSet4.getString(1));
                                    dto.setToSubscriptionId(resultSet4.getString(2));
                                    dto.setSubscriptionDate(resultSet4.getString(3));
                                    dto.setSubscriptionAmt(resultSet4.getString(4));
                                    list1.add(dto);
                                }}
                            modeOfHoldingChangeDTO.setReversalList(list1);
                            modeOfHoldingChangeDTO.setCreatedBy(stmt.getString(14));
                            modeOfHoldingChangeDTO.setCreatedDate(stmt.getString(15));
                            modeOfHoldingChangeDTO.setLastVerifiedBy(stmt.getString(16));
                            modeOfHoldingChangeDTO.setLastVerifiedDate(stmt.getString(17));
                            modeOfHoldingChangeDTO.setVerifiedStatus(stmt.getString(18));
                            responseDataDTO.setResult(modeOfHoldingChangeDTO);
                        }
                    }
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySelect(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_MODIFY_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSubscriptionId());

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(8);

                    if (modeOfHoldingChangeDTO.getBlaNo() == null) {


                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("verifiedStatus", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (modeOfHoldingChangeDTO.getSubscriptionId() == null) {


                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("subscriptionId", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                item.put("subscriptionAmount", resultSet.getString(3));
                                item.put("verifiedStatus", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else {
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(9);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(10);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(11);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(12);
                        modeOfHoldingChangeDTO.setBlaGroupId(stmt.getString(13));
                        modeOfHoldingChangeDTO.setPkColumnValue(stmt.getString(14));
                        modeOfHoldingChangeDTO.setCreatedBy(stmt.getString(15));
                        modeOfHoldingChangeDTO.setCreatedDate(stmt.getString(16));
                        modeOfHoldingChangeDTO.setLastModifiedBy(stmt.getString(17));
                        modeOfHoldingChangeDTO.setLastModifiedDate(stmt.getString(18));
                        modeOfHoldingChangeDTO.setLastVerifiedBy(stmt.getString(19));
                        modeOfHoldingChangeDTO.setLastVerifiedDate(stmt.getString(20));
                        modeOfHoldingChangeDTO.setPreviousOperationType(stmt.getString(21));
                        modeOfHoldingChangeDTO.setVerifiedStatus(stmt.getString(22));
                        modeOfHoldingChangeDTO.setInvoiceFlag(stmt.getString(25));
                        modeOfHoldingChangeDTO.setPaymentFlag(stmt.getString(26));
                        modeOfHoldingChangeDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        modeOfHoldingChangeDTO.setCoparsDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet1));
                        modeOfHoldingChangeDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
                        modeOfHoldingChangeDTO.setSubscriptionDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet3));
                        modeOfHoldingChangeDTO.setNomineeDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet4));
                        responseDataDTO.setResult(modeOfHoldingChangeDTO);
                    }

                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modify(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_MODIFY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSubscriptionId());
                if (modeOfHoldingChangeDTO.getColumnValueDetailsList() != null) {
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, modeOfHoldingChangeDTO.getColumnValueDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                }
                if (modeOfHoldingChangeDTO.getGuardianModValueDetailsList() != null) {
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, modeOfHoldingChangeDTO.getGuardianModValueDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                }
                if (modeOfHoldingChangeDTO.getGuardianAddDetailsList() != null) {
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, modeOfHoldingChangeDTO.getGuardianAddDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                }
                if (modeOfHoldingChangeDTO.getSubscriptionDetailsList() != null) {
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, modeOfHoldingChangeDTO.getSubscriptionDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                }
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
        public ResponseDataDTO verifySelect(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_VERIFY_SEl.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getFlag());
                stmt.setString(index++, modeOfHoldingChangeDTO.getModeOfHolding());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, modeOfHoldingChangeDTO.getFromBlaNo());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(10);
                    if (modeOfHoldingChangeDTO.getSchemeCode() == null) {
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("schemeName", resultSet.getString(2));
                                item.put("status", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else if(null!=modeOfHoldingChangeDTO.getSchemeCode()&&modeOfHoldingChangeDTO.getFlag().equalsIgnoreCase("P")){
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panHldName", resultSet.getString(1));
                                item.put("panNum", resultSet.getString(2));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else if (modeOfHoldingChangeDTO.getSchemeCode() != null && ("F".equalsIgnoreCase(modeOfHoldingChangeDTO.getFlag()) || "B".equalsIgnoreCase(modeOfHoldingChangeDTO.getFlag()))) {
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("blaNo", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else if(null!=modeOfHoldingChangeDTO.getSchemeCode()&&modeOfHoldingChangeDTO.getFlag().equalsIgnoreCase("D")){
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(11);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(12);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(13);
                        if (resultSet != null) {
                            modeOfHoldingChangeDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        }if(resultSet2!= null){
                            modeOfHoldingChangeDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
                        }if(resultSet3!=null){
                            List<BondSubscripReversalAccNoDTO> list1=new ArrayList<>();
                            BondSubscripReversalAccNoDTO dto=null;
                            while (resultSet3.next()) {
                                dto=new BondSubscripReversalAccNoDTO();
                                dto.setBondHolderName(resultSet3.getString(1));
                                dto.setPanNum(resultSet3.getString(2));
                                dto.setModeOfHolding(resultSet3.getString(3));
                                dto.setFromBlaNo(resultSet3.getString(4));
                                dto.setCount(resultSet3.getString(5));
                                dto.setToBlaNo(resultSet3.getString(6));
                                dto.setRemarks(resultSet3.getString(7));
                                modeOfHoldingChangeDTO.setReversalObj(dto);
                            }
                            if(resultSet4!=null){
                            while (resultSet4.next()) {
                                dto=new BondSubscripReversalAccNoDTO();
                                dto.setSubscriptionId(resultSet4.getString(1));
                                dto.setToSubscriptionId(resultSet4.getString(2));
                                dto.setSubscriptionDate(resultSet4.getString(3));
                                dto.setSubscriptionAmt(resultSet4.getString(4));
                                list1.add(dto);
                            }}
                            modeOfHoldingChangeDTO.setReversalList(list1);
                            modeOfHoldingChangeDTO.setBlaGroupId(stmt.getString(14));
                            modeOfHoldingChangeDTO.setCreatedBy(stmt.getString(15));
                            modeOfHoldingChangeDTO.setCreatedDate(stmt.getString(16));
                            modeOfHoldingChangeDTO.setLastVerifiedBy(stmt.getString(17));
                            modeOfHoldingChangeDTO.setLastVerifiedDate(stmt.getString(18));
                            modeOfHoldingChangeDTO.setVerifiedStatus(stmt.getString(19));
                            responseDataDTO.setResult(modeOfHoldingChangeDTO);
                        }
                    }
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_VERIFY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getModeOfHolding());
                stmt.setString(index++, modeOfHoldingChangeDTO.getFromBlaNo());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaGroupId());
                if(null!=modeOfHoldingChangeDTO&&null!=modeOfHoldingChangeDTO.getReversalList()&&
                        !modeOfHoldingChangeDTO.getReversalList().isEmpty()&&
                        modeOfHoldingChangeDTO.getReversalList().size()>0){
                    stmt.setArray(index++, generateTYP_ty_gbm_bond_subscription(connection, modeOfHoldingChangeDTO.getReversalList()));}
                else{
                    stmt.setNull(index++, Types.ARRAY, "TY_GBM_RBI_BOND_SUBS_DTLS");
                }
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
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
    public ResponseDataDTO delete(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (modeOfHoldingChangeDTO.getRejectionRemarks() == null
                || modeOfHoldingChangeDTO.getRejectionRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_DELETE.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getModeOfHolding());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, modeOfHoldingChangeDTO.getFromBlaNo());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaGroupId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
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
    public ResponseDataDTO blaNo(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_INQ_BLA_TORCH_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(modeOfHoldingChangeDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("blaNo", resultSet.getString(1));
                                temp.put("nameOfTheBondHolder", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));
                                temp.put("verifiedStatus", resultSet.getString(4));

                                torchOut.add(temp);
                            }
                            modeOfHoldingChangeDTO.setTorchValue(torchOut);
                        }
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
        responseDataDTO.setResult(modeOfHoldingChangeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getblaNoInq(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_INQ_BLA_TORCH_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(modeOfHoldingChangeDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("blaNo", resultSet.getString(1));
                                temp.put("nameOfTheBondHolder", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));
                                temp.put("verifiedStatus", resultSet.getString(4));

                                torchOut.add(temp);
                            }
                            modeOfHoldingChangeDTO.setTorchValue(torchOut);
                        }
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
        responseDataDTO.setResult(modeOfHoldingChangeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getsubscriptionIdMod(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_INQ_SUB_TORCH_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(modeOfHoldingChangeDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("subscriptionId", resultSet.getString(1));
                                temp.put("bondHolderName", resultSet.getString(2));
                                temp.put("subscriptionAmount", resultSet.getString(3));
                                temp.put("verifiedStatus", resultSet.getString(4));

                                torchOut.add(temp);
                            }
                            modeOfHoldingChangeDTO.setTorchValue(torchOut);
                        }
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
        responseDataDTO.setResult(modeOfHoldingChangeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getsubscriptionIdInq(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_INQ_SUB_TORCH_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBlaNo());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(modeOfHoldingChangeDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("subscriptionId", resultSet.getString(1));
                                temp.put("bondHolderName", resultSet.getString(2));
                                temp.put("subscriptionAmount", resultSet.getString(3));
                                temp.put("verifiedStatus", resultSet.getString(4));

                                torchOut.add(temp);
                            }
                            modeOfHoldingChangeDTO.setTorchValue(torchOut);
                        }
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
        responseDataDTO.setResult(modeOfHoldingChangeDTO);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO blaTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_INQ_BLA_NO.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    List<HashMap<String, String>> torchOut = null;

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();

                            temp.put("panHldName", resultSet.getString(1));
                            temp.put("panNum", resultSet.getString(2));

                            torchOut.add(temp);
                        }
                        modeOfHoldingChangeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(modeOfHoldingChangeDTO);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO modifyPanTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_MOD_BLA_NO.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, modeOfHoldingChangeDTO.getSchemeCode());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBondHolderName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getPanNum());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    List<HashMap<String, String>> torchOut = null;

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();

                            temp.put("panHldName", resultSet.getString(1));
                            temp.put("panNum", resultSet.getString(2));

                            torchOut.add(temp);
                        }
                        modeOfHoldingChangeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(modeOfHoldingChangeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getNonHdfcTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_ADD_GET_BANKCODE.getProcName());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    List<HashMap<String, String>> torchOut = null;
                    resultSet = (ResultSet) stmt.getObject(1);

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {

                            temp = new HashMap<>();
                            temp.put("bankCode", resultSet.getString(1));
                            temp.put("branchCode", resultSet.getString(2));

                            torchOut.add(temp);

                        }
                        modeOfHoldingChangeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(modeOfHoldingChangeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getNonHdfcBranchTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MODE_OF_HOLDING_CHNAGE_ADD_GET_BRANCHCODE.getProcName());
                stmt.setString(index++, modeOfHoldingChangeDTO.getBankCodeNonMicr());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    List<HashMap<String, String>> tdsCode = null;
                    resultSet = (ResultSet) stmt.getObject(2);

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        tdsCode = new ArrayList<>();
                        while (resultSet.next()) {

                            temp = new HashMap<>();
                            temp.put("bankCode", resultSet.getString(1));
                            temp.put("branchCode", resultSet.getString(2));

                            tdsCode.add(temp);

                        }
                        modeOfHoldingChangeDTO.setTorchValue(tdsCode);
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
        responseDataDTO.setResult(modeOfHoldingChangeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO unlockBla(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = null; // Default message

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                // Using a native SQL query
                String sql = "UPDATE gbm_rbi_bond_inet_file_upd SET ACTION_FLG='N' WHERE ID=? AND ACTION_FLG='I'";
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, modeOfHoldingChangeDTO.getId()); // Assuming getId() exists

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 1) {
                    resultMsg = "Success";
                    responseDataDTO.setResult("unlocked Successfully");
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = "An error occurred while unlocking the account.";
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO fetchState(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "Success"; // Default success message

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            // Get connection from the connection pool
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                // SQL query to fetch city and state based on the pin code
                String sql = "SELECT city AS city, " +
                        "( " +
                        "    SELECT sub_code " +
                        "    FROM gbm_rbi_bond_sub_code_master " +
//                        "    WHERE code='037' AND sub_code_desc = state_name AND verified_status = 'Y' " +
                        "    WHERE code='037' AND sub_code_desc = state_name " +
                        "    AND is_deleted = 'N' " +
                        ") AS state_name " +
                        "FROM GBM_RBI_BOND_STATE_MASTER " +
                        "WHERE verified_status = 'Y' AND is_active = 'Y' " +
                        "AND is_deleted = 'N' AND audit_cre_flg = '1' " +
                        "AND pin_code = ?";

                // Prepare the statement and set the pin code parameter
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, modeOfHoldingChangeDTO.getPinCode());

                resultSet = stmt.executeQuery();

                List<HashMap<String, String>> tdsCode = new ArrayList<>();

                if (resultSet.next()) {
                    HashMap<String, String> temp = new HashMap<>();
                    temp.put("bondHldCity", resultSet.getString("city"));
                    temp.put("bondHldState", resultSet.getString("state_name"));
                    tdsCode.add(temp);
                } else {
                    resultMsg = "No data found for the given pin code Enter Manually.";
                    responseDataDTO.setResult(new ArrayList<>());
                }
                if (!tdsCode.isEmpty()) {
                    responseDataDTO.setResult(tdsCode);
                }

            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (SQLException e) {
            log.error("Database error occurred while fetching state info", e);
            resultMsg = "An error occurred while fetching the state information.";
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            resultMsg = "Unexpected error occurred while fetching the state information.";
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    private ARRAY generateTYP_ty_gbm_bond_subscription(Connection connection, List<BondSubscripReversalAccNoDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_RBI_BOND_SUBS_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_RBI_BOND_SUBS_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        BondSubscripReversalAccNoDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[1];
            dto = list.get(index);
            paramArray[0] = dto.getSubscriptionId();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    private ARRAY generateTYP_ty_gbm_bond_holder_dtls(Connection connection, List<CustomerDataDTO> dtoList) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_RBI_BOND_BLA_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_RBI_BOND_BLA_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[1];
            Object[] paramArray = new Object[59];
            CustomerDataDTO dto=dtoList.get(0);
            paramArray[0] = dto.getPanNo();
            paramArray[1] = dto.getBondHldFirstName();
            paramArray[2] = dto.getBondHldMidName();
            paramArray[3] = dto.getBondHldLastName();
            paramArray[4] = dto.getBondHldDob();
            paramArray[5] = dto.getCbsCustId();
            paramArray[6] = dto.getFtsAppNum();
            paramArray[7] = dto.getHolderType();
            paramArray[8] = dto.getFatherName();
            paramArray[9] = dto.getMotherName();
            paramArray[10] = dto.getGender();
            paramArray[11] = dto.getIsMinor();
            paramArray[12] = dto.getDetailsOfBirthCertificate();
            paramArray[13] = dto.getBondHldAddOne();
            paramArray[14] = dto.getBondHldAddTwo();
            paramArray[15] = dto.getBondHldAddThree();
            paramArray[16] = dto.getBondHldPinCode();
            paramArray[17] = dto.getBondHldCity();
            paramArray[18] = dto.getBondHldState();
            paramArray[19] = dto.getBondHolderPhoneNumber();
            paramArray[20] = dto.getBondHldMobNum();
            paramArray[21] = dto.getBondHldEmail();
            paramArray[22] = dto.getHufRegPowerOfAttorney();
            paramArray[23] = dto.getPowerOfAttorneyKartaName();
            paramArray[24] = dto.getHdfcOtherBank();
            paramArray[25] = dto.getOperativeAccNum();
            paramArray[26] = (dto.getCreditBankIfscCode() != null && !dto.getCreditBankIfscCode().trim().isEmpty()) ? dto.getCreditBankIfscCode() : dto.getIfscCode();
            paramArray[27] = dto.getAadhaarNo();
            paramArray[28] = dto.getWitnessNameOne();
            paramArray[29] = dto.getWitnessAddressOne();
            paramArray[30] = dto.getWitnessNameTwo();
            paramArray[31] = dto.getWitnessAddressTwo();
            paramArray[32] = dto.getBirthCertificate();
            paramArray[33] = dto.getPassport();
            paramArray[34] = dto.getSeniorCitizenCard();
            paramArray[35] = dto.getVoterIdCard();
            paramArray[36] = dto.getPanCard();
            paramArray[37] = dto.getDrivingLicence();
            paramArray[38] = dto.getRationCard();
            paramArray[39] = dto.getOtherDocuments();
            paramArray[40] = dto.getOtherDetails();
            paramArray[41] = dto.getRemarks();
            paramArray[42] = dto.getGuardianId();
            paramArray[43] = dto.getGuardianFirstName();
            paramArray[44] = dto.getGuardianMiddleName();
            paramArray[45] = dto.getGuardianLastName();
            paramArray[46] = dto.getRelationWithMinor();
            paramArray[47] = dto.getGuardianDateOfBirth();
            paramArray[48] = dto.getGuardianCommunicationAddress();
            paramArray[49] = dto.getGuardianMobileNo();
            paramArray[50] = dto.getGuardianEmailId();
            paramArray[51] = dto.getJointHolderType();
            paramArray[52] = dto.getModeOfInterestPayment();
            paramArray[53] = dto.getBankCode();
            paramArray[54] = dto.getBranchCode();
            paramArray[55] = dto.getNameOfthePayee();
            paramArray[56] = dto.getOperativeAccNumber();
            paramArray[57] = dto.getMicrCode();
            paramArray[58] = dto.getAccountType();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[0] = struct;
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }



        private ARRAY generateTYP_gbm_rbi_bond_copar(Connection connection, List<DataCopersondetails> list) throws Exception {
            StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_COPAR_OBJ", connection);
            ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_COPAR", connection);
            STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
            DataCopersondetails dto = null;
            for (int index = 0; index < list.size(); index++) {
                Object[] paramArray = new Object[3];
                dto = list.get(index);
                paramArray[0] = dto.getCoparcenersName();
                paramArray[1] = dto.getCoparcenersRelation();
                paramArray[2] = dto.getCoparcenersDob();
                STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
                genericTableInsertStructs[index] = struct;
                System.out.println(dto.toString());
            }
            return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
        }
    private ARRAY generateTYP_gbm_rbi_bond_nom_dtls(Connection connection, List<NomineeDetails> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        NomineeDetails dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[25];
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
            paramArray[11] = dto.getIsNomineeMinor();
            paramArray[12] = dto.getRelationWithHolder();
            paramArray[13] = dto.getDateOfBirthOfMinor();
            paramArray[14] = dto.getGuardianFirstName();
            paramArray[15] = dto.getGuardianMiddleName();
            paramArray[16] = dto.getGuardianLastName();
            paramArray[17] = dto.getGuardianAddressOne();
            paramArray[18] = dto.getGuardianAddressTwo();
            paramArray[19] = dto.getGuardianCity();
            paramArray[20] = dto.getGuardianState();
            paramArray[21] = dto.getGuardianPinCode();
            paramArray[22] = dto.getGuardianMobileNo();
            paramArray[23] = dto.getGuardianEmail();
            paramArray[24] = dto.getRelationOfGuardianWithNominee();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
            System.out.println(dto.toString());
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    }


