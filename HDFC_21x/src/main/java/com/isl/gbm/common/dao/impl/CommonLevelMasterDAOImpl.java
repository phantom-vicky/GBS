package com.isl.gbm.common.dao.impl;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dao.CommonLevelMasterDAO;
import com.isl.gbm.common.dto.LevelMasterDto;
import com.isl.gbm.common.dto.LevelMasterSubDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.*;
import com.isl.gbm.common.utils.ProcNameEnum;
import com.isl.gbm.user.management.util.CbdtProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommonLevelMasterDAOImpl implements CommonLevelMasterDAO {

    private static final Logger log = LoggerFactory.getLogger(CommonLevelMasterDAOImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO inquiry(String schemeCode) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        LevelMasterDto levelMasterDto = new LevelMasterDto();
        LevelMasterSubDto levelMasterSubDto = null;
        List<LevelMasterSubDto> levelMasterSubDtoList = new ArrayList<>();
        String resultMsg = "";

        if(schemeCode.equalsIgnoreCase(CommonConstants.ICEGATE_MODULE_ID)){
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(IGProcNameEnum.PKG_GBM_IGATE_LEVEL_MASTER_SP_INQ.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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
                    log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;
                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        resultSet = (ResultSet) stmt.getObject(index-2);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    levelMasterSubDto = new LevelMasterSubDto();
                                    levelMasterSubDto.setId(resultSet.getString(1));
                                    levelMasterSubDto.setLevelId(resultSet.getString(2));
                                    levelMasterSubDto.setLevelName(resultSet.getString(3));
                                    levelMasterSubDto.setParentLevelName(resultSet.getString(4));
                                    levelMasterSubDto.setParentLevelId(resultSet.getString(5));
                                    levelMasterSubDtoList.add(levelMasterSubDto);
                                }
                            }
                            levelMasterDto.setLevelMasterSubDtoList(levelMasterSubDtoList);
                        }
                        levelMasterDto.setCreatedBy(stmt.getString(3));
                        levelMasterDto.setCreatedDate(stmt.getString(4));
                        levelMasterDto.setVerifiedStatus(stmt.getString(9));
                        levelMasterDto.setModifiedBy(stmt.getString(5));
                        levelMasterDto.setModifiedDate(stmt.getString(6));
                        levelMasterDto.setVerifiedBy(stmt.getString(7));
                        levelMasterDto.setVerifiedDate(stmt.getString(8));

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
        }
        else {

            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    if (CommonConstants.CBDT_MODULE_CODE.equalsIgnoreCase(schemeCode)) {
                        stmt = connection.prepareCall(CbdtProcNameEnum.GBM_CBDT_HIR_LEVEL_SEL.getProcName());
                    } else if (CommonConstants.SCSS_MODULE_ID.equalsIgnoreCase(schemeCode)) {
                        stmt = connection.prepareCall(ScssProcNameEnum.GBM_SCSS_HIR_LEVEL_SEL.getProcName());
                    } else if (CommonConstants.KVP_MODULE_ID.equalsIgnoreCase(schemeCode)) {
                        stmt = connection.prepareCall(ProcNameEnum.GBM_KVP_HIR_LEVEL_SEL.getProcName());
                    } else if (CommonConstants.SSA_MODULE_ID.equalsIgnoreCase(schemeCode)) {
                        stmt = connection.prepareCall("");
                    } else {
                        stmt = connection.prepareCall(PpfProcNamesEnum.GBM_PPF_HIR_LEVEL_SEL.getProcName());
                    }
                    stmt.setString(index++, schemeCode);
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
                    log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;
                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        resultSet = (ResultSet) stmt.getObject(2);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    levelMasterSubDto = new LevelMasterSubDto();
                                    levelMasterSubDto.setId(resultSet.getString(1));
                                    levelMasterSubDto.setLevelId(resultSet.getString(2));
                                    levelMasterSubDto.setLevelName(resultSet.getString(3));
                                    levelMasterSubDto.setParentLevelName(resultSet.getString(4));
                                    levelMasterSubDto.setParentLevelId(resultSet.getString(5));
                                    levelMasterSubDtoList.add(levelMasterSubDto);
                                }
                            }
                            levelMasterDto.setLevelMasterSubDtoList(levelMasterSubDtoList);
                        }
                        levelMasterDto.setCreatedBy(stmt.getString(3));
                        levelMasterDto.setCreatedDate(stmt.getString(4));
                        levelMasterDto.setVerifiedStatus(stmt.getString(5));
                        levelMasterDto.setModifiedBy(stmt.getString(6));
                        levelMasterDto.setModifiedDate(stmt.getString(7));
                        levelMasterDto.setVerifiedBy(stmt.getString(8));
                        levelMasterDto.setVerifiedDate(stmt.getString(9));

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
        }
        if (StringUtils.equalsIgnoreCase(resultMsg, "SUCCESS")) {
            responseDataDTO.setMessage(resultMsg);
        } else {
            responseDataDTO.setMessage("No Records Found");
        }
        responseDataDTO.setResult(levelMasterDto);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getVerifyDeleteDetails(String schemeCode) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        LevelMasterDto levelMasterDto = new LevelMasterDto();
        LevelMasterSubDto levelMasterSubDto = null;
        List<LevelMasterSubDto> levelMasterSubDtoList = new ArrayList<>();
        String resultMsg = "";

        if(schemeCode.equalsIgnoreCase(CommonConstants.ICEGATE_MODULE_ID)){
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(IGProcNameEnum.PKG_GBM_IGATE_LEVEL_MASTER_SP_VER_SEL.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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
                    log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;
                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        resultSet = (ResultSet) stmt.getObject(index-2);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    levelMasterSubDto = new LevelMasterSubDto();
                                    levelMasterSubDto.setId(resultSet.getString(1));
                                    levelMasterSubDto.setLevelId(resultSet.getString(2));
                                    levelMasterSubDto.setLevelName(resultSet.getString(3));
                                    levelMasterSubDto.setParentLevelName(resultSet.getString(4));
                                    levelMasterSubDto.setParentLevelId(resultSet.getString(5));
                                    levelMasterSubDtoList.add(levelMasterSubDto);
                                }
                            }
                            levelMasterDto.setLevelMasterSubDtoList(levelMasterSubDtoList);
                        }
                        levelMasterDto.setCreatedBy(stmt.getString(3));
                        levelMasterDto.setCreatedDate(stmt.getString(4));
                        levelMasterDto.setVerifiedStatus(stmt.getString(9));
                        levelMasterDto.setModifiedBy(stmt.getString(5));
                        levelMasterDto.setModifiedDate(stmt.getString(6));
                        levelMasterDto.setVerifiedBy(stmt.getString(7));
                        levelMasterDto.setVerifiedDate(stmt.getString(8));

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
        }
        else {
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    if (CommonConstants.CBDT_MODULE_CODE.equalsIgnoreCase(schemeCode)) {
                        stmt = connection.prepareCall(CbdtProcNameEnum.GBM_CBDT_HIR_LEVEL_SEL_VER.getProcName());
                    } else if (CommonConstants.SCSS_MODULE_ID.equalsIgnoreCase(schemeCode)) {
                        stmt = connection.prepareCall(ScssProcNameEnum.GBM_SCSS_HIR_LEVEL_SEL_VER.getProcName());
                    } else if (CommonConstants.KVP_MODULE_ID.equalsIgnoreCase(schemeCode)) {
                        stmt = connection.prepareCall(ProcNameEnum.GBM_KVP_HIR_LEVEL_SEL_VER.getProcName());
                    } else if (CommonConstants.SSA_MODULE_ID.equalsIgnoreCase(schemeCode)) {
                        stmt = connection.prepareCall("SsaProcNameEnum.GBM_SSA_HIR_LEVEL_SEL_VER.getProcName()");
                    } else {
                        stmt = connection.prepareCall(PpfProcNamesEnum.GBM_PPF_HIR_LEVEL_SEL_VER.getProcName());
                    }
                    stmt.setString(index++, schemeCode);
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
                    log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;
                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        resultSet = (ResultSet) stmt.getObject(2);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    levelMasterSubDto = new LevelMasterSubDto();
                                    levelMasterSubDto.setId(resultSet.getString(1));
                                    levelMasterSubDto.setLevelId(resultSet.getString(2));
                                    levelMasterSubDto.setLevelName(resultSet.getString(3));
                                    levelMasterSubDto.setParentLevelName(resultSet.getString(4));
                                    levelMasterSubDto.setParentLevelId(resultSet.getString(5));
                                    levelMasterSubDtoList.add(levelMasterSubDto);
                                }
                            }
                            levelMasterDto.setLevelMasterSubDtoList(levelMasterSubDtoList);
                        }
                        levelMasterDto.setCreatedBy(stmt.getString(3));
                        levelMasterDto.setCreatedDate(stmt.getString(4));
                        levelMasterDto.setVerifiedStatus(stmt.getString(5));
                        levelMasterDto.setModifiedBy(stmt.getString(6));
                        levelMasterDto.setModifiedDate(stmt.getString(7));
                        levelMasterDto.setVerifiedBy(stmt.getString(8));
                        levelMasterDto.setVerifiedDate(stmt.getString(9));

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
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(levelMasterDto);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(String schemeCode) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if(CommonConstants.CBDT_MODULE_CODE.equalsIgnoreCase(schemeCode)) {
                    stmt = connection.prepareCall(CbdtProcNameEnum.GBM_CBDT_HIR_LEVEL_DEL.getProcName());
                    stmt.setString(index++, schemeCode);
                }else if(CommonConstants.SCSS_MODULE_ID.equalsIgnoreCase(schemeCode)) {
                    stmt = connection.prepareCall(ScssProcNameEnum.GBM_SCSS_HIR_LEVEL_DEL.getProcName());
                    stmt.setString(index++, schemeCode);
                }else if(CommonConstants.KVP_MODULE_ID.equalsIgnoreCase(schemeCode)) {
                    stmt = connection.prepareCall(ProcNameEnum.GBM_KVP_HIR_LEVEL_DEL.getProcName());
                    stmt.setString(index++, schemeCode);
                }else if(CommonConstants.SSA_MODULE_ID.equalsIgnoreCase(schemeCode)) {
                    stmt = connection.prepareCall("SsaProcNameEnum.GBM_SSA_HIR_LEVEL_DEL.getProcName()");
                    stmt.setString(index++, schemeCode);
                }else if(CommonConstants.ICEGATE_MODULE_ID.equalsIgnoreCase(schemeCode)) {
                    stmt = connection.prepareCall(IGProcNameEnum.PKG_GBM_IGATE_LEVEL_MASTER_SP_DEL.getProcName());
                }
                else{
                    stmt = connection.prepareCall(PpfProcNamesEnum.GBM_PPF_HIR_LEVEL_DEL.getProcName());
                    stmt.setString(index++, schemeCode);
                }
                //stmt.setString(index++, "PRADEEP");
                //stmt.setString(index++, "101");
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
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
    public ResponseDataDTO verify(LevelMasterDto levelMasterDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if(CommonConstants.CBDT_MODULE_CODE.equalsIgnoreCase(levelMasterDto.getSchemeCode())) {
                    stmt = connection.prepareCall(CbdtProcNameEnum.GBM_CBDT_HIR_LEVEL_VER.getProcName());
                    stmt.setString(index++, levelMasterDto.getSchemeCode());
                }else if(CommonConstants.SCSS_MODULE_ID.equalsIgnoreCase(levelMasterDto.getSchemeCode())) {
                    stmt = connection.prepareCall(ScssProcNameEnum.GBM_SCSS_HIR_LEVEL_VER.getProcName());
                    stmt.setString(index++, levelMasterDto.getSchemeCode());
                }else if(CommonConstants.KVP_MODULE_ID.equalsIgnoreCase(levelMasterDto.getSchemeCode())) {
                    stmt = connection.prepareCall(ProcNameEnum.GBM_KVP_HIR_LEVEL_VER.getProcName());
                    stmt.setString(index++, levelMasterDto.getSchemeCode());
                }else if(CommonConstants.SSA_MODULE_ID.equalsIgnoreCase(levelMasterDto.getSchemeCode())) {
                    stmt = connection.prepareCall("");
                    stmt.setString(index++, levelMasterDto.getSchemeCode());
                }else if(CommonConstants.ICEGATE_MODULE_ID.equalsIgnoreCase(levelMasterDto.getSchemeCode())){
                    stmt = connection.prepareCall(IGProcNameEnum.PKG_GBM_IGATE_LEVEL_MASTER_SP_VER.getProcName());
                }else{
                    stmt = connection.prepareCall(PpfProcNamesEnum.GBM_PPF_HIR_LEVEL_VER.getProcName());
                    stmt.setString(index++, levelMasterDto.getSchemeCode());
                }

                //stmt.setString(index++, "PRADEEP");
                //stmt.setString(index++, "101");
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
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
    public ResponseDataDTO add(LevelMasterDto levelMasterDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";

        if(levelMasterDto.getSchemeCode().equalsIgnoreCase(CommonConstants.ICEGATE_MODULE_ID)){

            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                        stmt = connection.prepareCall(IGProcNameEnum.PKG_GBM_IGATE_LEVEL_MASTER_SP_ADD.getProcName());
                        stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                        stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    //    stmt.setString(index++, levelMasterDto.getLevelsCount());
                    if (levelMasterDto.getLevelMasterSubDtoList() != null) {
                        stmt.setArray(index++, generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, levelMasterDto.getLevelMasterSubDtoList()));
                    } else {
                        stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_LEVEL_MASTER_ARRAY);
                    }
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
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


        }

        else {

            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    if (CommonConstants.CBDT_MODULE_CODE.equalsIgnoreCase(levelMasterDto.getSchemeCode())) {
                        stmt = connection.prepareCall(CbdtProcNameEnum.GBM_CBDT_HIR_LEVEL_INS.getProcName());
                    } else if (CommonConstants.SCSS_MODULE_ID.equalsIgnoreCase(levelMasterDto.getSchemeCode())) {
                        stmt = connection.prepareCall(ScssProcNameEnum.GBM_SCSS_HIR_LEVEL_INS.getProcName());
                    } else if (CommonConstants.KVP_MODULE_ID.equalsIgnoreCase(levelMasterDto.getSchemeCode())) {
                        stmt = connection.prepareCall(ProcNameEnum.GBM_KVP_HIR_LEVEL_INS.getProcName());
                    } else if (CommonConstants.SSA_MODULE_ID.equalsIgnoreCase(levelMasterDto.getSchemeCode())) {
                        stmt = connection.prepareCall("");
                    } else {
                        stmt = connection.prepareCall(PpfProcNamesEnum.GBM_PPF_HIR_LEVEL_INS.getProcName());
                    }

                    stmt.setString(index++, levelMasterDto.getSchemeCode());
                    if (levelMasterDto.getLevelMasterSubDtoList() != null) {
                        stmt.setArray(index++, generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, levelMasterDto.getLevelMasterSubDtoList()));
                    } else {
                        stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_LEVEL_MASTER_ARRAY);
                    }
                    //stmt.setString(index++, "PRABHA");
                    //stmt.setString(index++, "101");
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
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
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private Array generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(Connection connection, List<LevelMasterSubDto> levelMasterSubDtoList) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_LEVEL_MASTER, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_LEVEL_MASTER_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[levelMasterSubDtoList.size()];
        LevelMasterSubDto levelMasterSubDto = null;
        for (int index = 0; index < levelMasterSubDtoList.size(); index++) {
            levelMasterSubDto = levelMasterSubDtoList.get(index);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("LEVEL_ID", levelMasterSubDto.getLevelId());
            paramMap.put("LEVEL_NAME", levelMasterSubDto.getLevelName());
            paramMap.put("PARENT_LEVEL_ID", levelMasterSubDto.getParentLevelId());

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramMap);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
}
