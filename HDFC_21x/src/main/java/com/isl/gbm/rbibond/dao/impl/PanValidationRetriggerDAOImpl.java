package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.DatabaseConnectionFactory;
import com.isl.gbm.rbibond.dao.PanValidationRetriggerDAO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.externalservice.connections.ConnectionFactoryHcp;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PanValidationRetriggerDAOImpl implements PanValidationRetriggerDAO {

    private static final Logger log = LoggerFactory.getLogger(PanValidationRetriggerDAOImpl.class);

    @Autowired
    ConnectionFactoryHcp hikariPoolFactory;


    @Autowired
    DatabaseConnectionFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO details(PanValidationRetriggerDTO panValidationRetriggerDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PAN_VALIDATION_RETRIGGER_sp_req.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,panValidationRetriggerDTO.getSchemeCode());
                stmt.setString(index++,panValidationRetriggerDTO.getPanStatusValidateDate());
                stmt.setString(index++,panValidationRetriggerDTO.getFileMode());
                stmt.setString(index++,panValidationRetriggerDTO.getFileStatus());
                //stmt.setString(index++,panValidationRetriggerDTO.getFileStatusFlg());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (panValidationRetriggerDTO.getSchemeCode() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                            RateCodeMaintenanceDTO item = null;
                            while (resultSet.next()) {
                                item = new RateCodeMaintenanceDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSubCodeDescription(resultSet.getString(2));
                                item.setStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else{
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<PanValidationRetriggerResponseDTO> list = new ArrayList<>();
                            PanValidationRetriggerResponseDTO item = null;
                            while (resultSet.next()) {
                                item = new PanValidationRetriggerResponseDTO();
                                item.setName(resultSet.getString(1));
                                item.setNameStatus(resultSet.getString(2));
                                item.setNewName(resultSet.getString(3));
                                item.setDob(resultSet.getString(4));
                                item.setDobStatus(resultSet.getString(5));
                                item.setNewDob(resultSet.getString(6));
                                item.setOldPan(resultSet.getString(7));
                                item.setPanStatus(resultSet.getString(8));
                                item.setNewPan(resultSet.getString(9));
                                item.setProcessStatus(resultSet.getString(10));
                                item.setRemarks(resultSet.getString(11));
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
            connectionPoolFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO verify(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PAN_VALIDATION_RETRIGGER_sp_res.getProcName());
                stmt.setString(index++,panValidationRetriggerResponseDTO.getSchemeCode());
                stmt.setString(index++,panValidationRetriggerResponseDTO.getPanStatusValidateDate());
                stmt.setString(index++,panValidationRetriggerResponseDTO.getFileMode());
                stmt.setArray(index++, generateTYP_GBM_VALIDATION_RESPONSE_ARRAY_OBJ(connection, panValidationRetriggerResponseDTO.getList()));
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
            connectionPoolFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private ARRAY generateTYP_GBM_VALIDATION_RESPONSE_ARRAY_OBJ(Connection connection, List<PanValidationRetriggerResponseDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PAN_RTG_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PAN_RTG", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        PanValidationRetriggerResponseDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            dto = list.get(index);
            Object[] paramArray = new Object[13];
            log.info("Loop Pan ReTrigger :- ");
            paramArray[0] = dto.getName();
            paramArray[1] = dto.getNameStatus();
            paramArray[2] = dto.getNewName();
            paramArray[3] = dto.getDob();
            paramArray[4] = dto.getDobStatus();
            paramArray[5] = dto.getNewDob();
            paramArray[6] = dto.getOldPan();
            paramArray[7] = dto.getPanStatus();
            paramArray[8] = dto.getNewPan();
            paramArray[9] = dto.getProcessStatus();
            paramArray[10] = dto.getRemarks();
            paramArray[11] = dto.getSeedingStatus();
            paramArray[12] = dto.getSeedingStatusDescription();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    @Override
    public ResponseDataDTO validatePanSchedulerRequest() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO dataDTO = new ResponseDataDTO();
        PanDetailsDTO panDetailsDTO=null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PAN_VALIDATION_TRIGGER_SCHEDULER_sp_req.getProcName());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(1);
                        List<PanDetailsDTO> list = new ArrayList<>();
                    if (resultSet != null) {
                            while (resultSet.next()) {
                                panDetailsDTO=new PanDetailsDTO();
                                panDetailsDTO.setPan(resultSet.getString(1));
                                panDetailsDTO.setName(resultSet.getString(2));
                                panDetailsDTO.setDob(resultSet.getString(3));
                                list.add(panDetailsDTO);
                            }
                            dataDTO.setResult(list);
                        }
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            hikariPoolFactory.closeResources(connection, stmt);
        }
        dataDTO.setMessage(resultMsg);
        return dataDTO;
    }

    @Override
    public ResponseDataDTO validatePanSchedulerResponse(PanValidationRetriggerResponseDTO response) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PAN_VALIDATION_TRIGGER_SCHEDULER_sp_res.getProcName());
                stmt.setArray(index++, generateTYP_GBM_RBI_BOND_ARRAY_OBJ(connection,response));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                log.info("Status : {}", resultMsg);
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            hikariPoolFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    private ARRAY generateTYP_GBM_RBI_BOND_ARRAY_OBJ(Connection connection, PanValidationRetriggerResponseDTO dto) throws Exception {
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
}
