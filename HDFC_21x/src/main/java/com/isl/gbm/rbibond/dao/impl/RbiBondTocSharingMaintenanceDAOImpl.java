package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.LevelDetailsTocSharingDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.RbiBondTocSharingMaintenanceDAO;
import com.isl.gbm.rbibond.dto.RbiBondTocSharingMaintenanceDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
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

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RbiBondTocSharingMaintenanceDAOImpl implements RbiBondTocSharingMaintenanceDAO {

    private static final Logger log = LoggerFactory.getLogger(RbiBondTocSharingMaintenanceDAOImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO add(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SHAR_INS.getProcName());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getVersionNo());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getEffeFromDate());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getRoundOff());
                stmt.setArray(index++, generateArray(connection, rbiBondTocSharingMaintenanceDTO.getLevelDetailsTocSharingDto()));
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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
    public ResponseDataDTO addValidation(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondTocSharingMaintenanceDTO output = new RbiBondTocSharingMaintenanceDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SHAR_INS_TOR.getProcName());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    List<HashMap<String, String>> noOfLevels = null;
                    resultSet = (ResultSet)
                            stmt.getObject(2);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        noOfLevels = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("levelId", resultSet.getString(1));
                                temp.put("levelName", resultSet.getString(2));
                                temp.put("sharingPercentage", null);
                                noOfLevels.add(temp);
                            }
                        }
                        output.setLevelsOut(noOfLevels);
                    }
                    output.setVersionNo(stmt.getString(3));
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
    public ResponseDataDTO modifySelect(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondTocSharingMaintenanceDTO output = new RbiBondTocSharingMaintenanceDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SHAR_MOD_SEL.getProcName());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getEffeFromDate());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(5)));
                    List<HashMap<String, String>> versionOut = null;
                    resultSet = (ResultSet) stmt.getObject(6);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        versionOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("versionNo", resultSet.getString(1));
                                temp.put("effectiveFromDate", resultSet.getString(2));
                                versionOut.add(temp);
                            }
                        }
                        output.setVersionOut(versionOut);
                    }
                    output.setEffectiveFromDateOut(stmt.getString(7));
                    output.setPreviousOperationType(stmt.getString(8));
                    output.setCreatedBy(stmt.getString(9));
                    output.setCreatedDate(stmt.getString(10));
                    output.setModifiedBy(stmt.getString(11));
                    output.setModifiedDate(stmt.getString(12));
                    output.setVerifiedBy(stmt.getString(13));
                    output.setVerifiedDate(stmt.getString(14));
                    output.setVerifiedStatus(stmt.getString(15));
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
    public ResponseDataDTO modify(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SHAR_MOD.getProcName());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getEffeFromDate());
                stmt.setArray(index++, generateArray(connection, rbiBondTocSharingMaintenanceDTO.getLevelDetailsTocSharingDto()));
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getRoundOff());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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
    public ResponseDataDTO verifyDeleteSelect(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondTocSharingMaintenanceDTO output = new RbiBondTocSharingMaintenanceDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SHAR_VER_SEL.getProcName());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getVersionNo());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getEffeFromDate());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
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
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    List<HashMap<String, String>> versionOut = null;
                    resultSet = (ResultSet) stmt.getObject(14);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        versionOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("versionNo", resultSet.getString(1));
                                temp.put("effectiveFromDate", resultSet.getString(2));
                                versionOut.add(temp);
                            }
                        }
                        output.setVersionOut(versionOut);
                        output.setCreatedBy(stmt.getString(5));
                        output.setCreatedDate(stmt.getString(6));
                        output.setModifiedBy(stmt.getString(7));
                        output.setModifiedDate(stmt.getString(8));
                        output.setVerifiedBy(stmt.getString(9));
                        output.setVerifiedDate(stmt.getString(10));
                        output.setPreviousOperationType(stmt.getString(11));
                        output.setVerifiedStatus(stmt.getString(12));
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(13)));
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
        responseDataDTO.setResult(output);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SHAR_VER.getProcName());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getVersionNo());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getEffeFromDate());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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
    public ResponseDataDTO delete(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SHAR_DEL.getProcName());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getVersionNo());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getEffeFromDate());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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
    public ResponseDataDTO inquiry(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondTocSharingMaintenanceDTO output = new RbiBondTocSharingMaintenanceDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SHAR_INQ.getProcName());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getVersionNo());
                stmt.setString(index++, rbiBondTocSharingMaintenanceDTO.getEffeFromDate());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    List<HashMap<String, String>> versionOut = null;
                    resultSet = (ResultSet) stmt.getObject(4);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        versionOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null && !StringUtils.equalsIgnoreCase(resultSet.getString(1), "NDF")) {
                                temp = new HashMap<>();
                                temp.put("versionNo", resultSet.getString(1));
                                temp.put("effectiveFromDate", resultSet.getString(2));
                                versionOut.add(temp);
                            }
                        }
                        output.setVersionOut(versionOut);
                    }
                    output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(5)));
                    output.setPreviousOperationType(stmt.getString(6));
                    output.setVersionNo(stmt.getString(7));
                    output.setEffeFromDate(stmt.getString(8));
                    output.setRoundOff(stmt.getString(9));
                    output.setCreatedBy(stmt.getString(10));
                    output.setCreatedDate(stmt.getString(11));
                    output.setModifiedBy(stmt.getString(12));
                    output.setModifiedDate(stmt.getString(13));
                    output.setVerifiedBy(stmt.getString(14));
                    output.setVerifiedDate(stmt.getString(15));
                    output.setVerifiedStatus(stmt.getString(16));

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

    private Array generateArray(Connection connection, List<LevelDetailsTocSharingDto> levelDetailsTocSharingDtoList) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COL_TOC_SHARING, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_TOC_SHARING_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[levelDetailsTocSharingDtoList.size()];
        LevelDetailsTocSharingDto levelDetailsTocSharingDto = null;
        for (int index = 0; index < levelDetailsTocSharingDtoList.size(); index++) {
            Object[] paramArray = new Object[4];
            levelDetailsTocSharingDto = levelDetailsTocSharingDtoList.get(index);
            paramArray[0] = levelDetailsTocSharingDto.getId();
            paramArray[1] = levelDetailsTocSharingDto.getLevelId();
            paramArray[2] = levelDetailsTocSharingDto.getLevelName();
            paramArray[3] = levelDetailsTocSharingDto.getSharingPercentage();
            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
}
