package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.RbiBondLevelMasterMappingDAO;
import com.isl.gbm.rbibond.dto.RbiBondLevelMasterMappingDTO;
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

@Repository
public class RbiBondLevelMasterMappingDAOImpl implements RbiBondLevelMasterMappingDAO {

    private static final Logger log = LoggerFactory.getLogger(RbiBondLevelMasterMappingDAOImpl.class);


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;
    

    @Override
    public ResponseDataDTO getAddDetails(RbiBondLevelMasterMappingDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondLevelMasterMappingDTO output = new RbiBondLevelMasterMappingDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_LEVEL_MASTER_MAP_TOR.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getLevelId());
                stmt.setString(index++, input.getParentLevelId());
                stmt.setString(index++, input.getParentBranchId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                    resultSet = (ResultSet) stmt.getObject(6);
                    List<HashMap<String, String>> torchOut = null;
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("levelId", resultSet.getString(1));
                                temp.put("levelName", resultSet.getString(2));
                                temp.put("parentLevelId", resultSet.getString(3));
                                temp.put("parentLevelName", resultSet.getString(4));
                                torchOut.add(temp);
                            }
                        }
                        output.setLevelsOut(torchOut);
                    }
                    resultSet = (ResultSet) stmt.getObject(7);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("parentBranchId", resultSet.getString(1));
                                temp.put("parentBranchName", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                        }
                        output.setParentBranchesOut(torchOut);
                    }
                    resultSet = (ResultSet) stmt.getObject(8);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("parentBranchId", resultSet.getString(1));
                                temp.put("parentBranchName", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                        }
                        output.setUnmappedBranchesOut(torchOut);
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
    public ResponseDataDTO add(RbiBondLevelMasterMappingDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_LEVEL_MASTER_MAP_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getLevelId());
                stmt.setString(index++, input.getParentLevelId());
                stmt.setString(index++, input.getParentBranchId());
                stmt.setArray(index++, generateArray(connection, input.getBranchesToAdd()));
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
    public ResponseDataDTO inquiry(RbiBondLevelMasterMappingDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondLevelMasterMappingDTO output = new RbiBondLevelMasterMappingDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_LEVEL_MASTER_MAP_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getLevelId());
                stmt.setString(index++, input.getParentLevelId());
                stmt.setString(index++, input.getParentBranchId());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(6);
                    List<HashMap<String, String>> torchOut = null;
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("levelId", resultSet.getString(1));
                                temp.put("levelName", resultSet.getString(2));
                                temp.put("parentLevelId", resultSet.getString(3));
                                temp.put("parentLevelName", resultSet.getString(4));
                                torchOut.add(temp);
                            }
                        }
                        output.setLevelsOut(torchOut);
                    }

                    resultSet = (ResultSet) stmt.getObject(7);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("parentBranchId", resultSet.getString(1));
                                temp.put("parentBranchName", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                        }
                        output.setParentBranchesOut(torchOut);
                    }

                    resultSet = (ResultSet) stmt.getObject(8);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("parentBranchId", resultSet.getString(1));
                                temp.put("parentBranchName", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                        }
                        output.setMappedBranchesOut(torchOut);
                    }

                    resultSet = (ResultSet) stmt.getObject(9);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("parentBranchId", resultSet.getString(1));
                                temp.put("parentBranchName", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                        }
                        output.setUnmappedBranchesOut(torchOut);
                    }
                    output.setCreatedBy(stmt.getString(10));
                    output.setCreatedDate(stmt.getString(11));
                    output.setModifiedBy(stmt.getString(12));
                    output.setModifiedDate(stmt.getString(13));
                    output.setVerifiedBy(stmt.getString(14));
                    output.setVerifiedDate(stmt.getString(15));
                    output.setVerifiedStatus(stmt.getString(16));
                    output.setRemarks(stmt.getString(17));

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
    public ResponseDataDTO getModifyDetails(RbiBondLevelMasterMappingDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondLevelMasterMappingDTO output = new RbiBondLevelMasterMappingDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_LEVEL_MASTER_MAP_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getLevelId());
                stmt.setString(index++, input.getParentLevelId());
                stmt.setString(index++, input.getParentBranchId());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(6);
                    List<HashMap<String, String>> torchOut = null;
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("levelId", resultSet.getString(1));
                                temp.put("levelName", resultSet.getString(2));
                                temp.put("parentLevelId", resultSet.getString(3));
                                temp.put("parentLevelName", resultSet.getString(4));
                                torchOut.add(temp);
                            }
                        }
                        output.setLevelsOut(torchOut);
                    }

                    resultSet = (ResultSet) stmt.getObject(7);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("parentBranchId", resultSet.getString(1));
                                temp.put("parentBranchName", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                        }
                        output.setParentBranchesOut(torchOut);
                    }

                    resultSet = (ResultSet) stmt.getObject(8);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("parentBranchId", resultSet.getString(1));
                                temp.put("parentBranchName", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                        }
                        output.setMappedBranchesOut(torchOut);
                    }

                    resultSet = (ResultSet) stmt.getObject(9);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("parentBranchId", resultSet.getString(1));
                                temp.put("parentBranchName", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                        }
                        output.setUnmappedBranchesOut(torchOut);
                    }
                    output.setCreatedBy(stmt.getString(10));
                    output.setCreatedDate(stmt.getString(11));
                    output.setModifiedBy(stmt.getString(12));
                    output.setModifiedDate(stmt.getString(13));
                    output.setVerifiedBy(stmt.getString(14));
                    output.setVerifiedDate(stmt.getString(15));
                    output.setVerifiedStatus(stmt.getString(16));
                    output.setRemarks(stmt.getString(17));
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
    public ResponseDataDTO getVerifyDeleteDetails(RbiBondLevelMasterMappingDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondLevelMasterMappingDTO output = new RbiBondLevelMasterMappingDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_LEVEL_MASTER_MAP_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getLevelId());
                stmt.setString(index++, input.getParentLevelId());
                stmt.setString(index++, input.getParentBranchId());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(6);
                    List<HashMap<String, String>> torchOut = null;
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("levelId", resultSet.getString(1));
                                temp.put("levelName", resultSet.getString(2));
                                temp.put("parentLevelId", resultSet.getString(3));
                                temp.put("parentLevelName", resultSet.getString(4));
                                torchOut.add(temp);
                            }
                        }
                        output.setLevelsOut(torchOut);
                    }

                    resultSet = (ResultSet) stmt.getObject(7);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("parentBranchId", resultSet.getString(1));
                                temp.put("parentBranchName", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                        }
                        output.setParentBranchesOut(torchOut);
                    }

                    resultSet = (ResultSet) stmt.getObject(8);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("parentBranchId", resultSet.getString(1));
                                temp.put("parentBranchName", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                        }
                        output.setMappedBranchesOut(torchOut);
                    }

                    resultSet = (ResultSet) stmt.getObject(9);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("parentBranchId", resultSet.getString(1));
                                temp.put("parentBranchName", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                        }
                        output.setUnmappedBranchesOut(torchOut);
                    }
                    output.setCreatedBy(stmt.getString(10));
                    output.setCreatedDate(stmt.getString(11));
                    output.setModifiedBy(stmt.getString(12));
                    output.setModifiedDate(stmt.getString(13));
                    output.setVerifiedBy(stmt.getString(14));
                    output.setVerifiedDate(stmt.getString(15));
                    output.setVerifiedStatus(stmt.getString(16));
                    output.setRemarks(stmt.getString(17));

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
    public ResponseDataDTO verify(RbiBondLevelMasterMappingDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_LEVEL_MASTER_MAP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getLevelId());
                stmt.setString(index++, input.getParentLevelId());
                stmt.setString(index++, input.getParentBranchId());
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
    public ResponseDataDTO delete(RbiBondLevelMasterMappingDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondLevelMasterMappingDTO output = new RbiBondLevelMasterMappingDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_LEVEL_MASTER_MAP_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getLevelId());
                stmt.setString(index++, input.getParentLevelId());
                stmt.setString(index++, input.getParentBranchId());
                stmt.setString(index++, input.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

            if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                resultSet = (ResultSet) stmt.getObject(7);
                List<HashMap<String, String>> torchOut = null;
                if (resultSet != null) {
                    HashMap<String, String> temp = null;
                    torchOut = new ArrayList<>();
                    while (resultSet.next()) {
                        if (resultSet.getString(1) != null) {
                            temp = new HashMap<>();
                            temp.put("levelId", resultSet.getString(1));
                            temp.put("levelName", resultSet.getString(2));
                            temp.put("parentLevelId", resultSet.getString(3));
                            temp.put("parentLevelName", resultSet.getString(4));
                            torchOut.add(temp);
                        }
                    }
                    output.setLevelsOut(torchOut);
                }

                resultSet = (ResultSet) stmt.getObject(8);
                if (resultSet != null) {
                    HashMap<String, String> temp = null;
                    torchOut = new ArrayList<>();
                    while (resultSet.next()) {
                        if (resultSet.getString(1) != null) {
                            temp = new HashMap<>();
                            temp.put("parentBranchId", resultSet.getString(1));
                            temp.put("parentBranchName", resultSet.getString(2));
                            torchOut.add(temp);
                        }
                    }
                    output.setParentBranchesOut(torchOut);
                }

                resultSet = (ResultSet) stmt.getObject(9);
                if (resultSet != null) {
                    HashMap<String, String> temp = null;
                    torchOut = new ArrayList<>();
                    while (resultSet.next()) {
                        if (resultSet.getString(1) != null) {
                            temp = new HashMap<>();
                            temp.put("parentBranchId", resultSet.getString(1));
                            temp.put("parentBranchName", resultSet.getString(2));
                            torchOut.add(temp);
                        }
                    }
                    output.setMappedBranchesOut(torchOut);
                }

                resultSet = (ResultSet) stmt.getObject(10);
                if (resultSet != null) {
                    HashMap<String, String> temp = null;
                    torchOut = new ArrayList<>();
                    while (resultSet.next()) {
                        if (resultSet.getString(1) != null) {
                            temp = new HashMap<>();
                            temp.put("parentBranchId", resultSet.getString(1));
                            temp.put("parentBranchName", resultSet.getString(2));
                            torchOut.add(temp);
                        }
                    }
                    output.setUnmappedBranchesOut(torchOut);
                }


            }}
            else {
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
    public ResponseDataDTO modify(RbiBondLevelMasterMappingDTO input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_LEVEL_MASTER_MAP_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getLevelId());
                stmt.setString(index++, input.getParentLevelId());
                stmt.setString(index++, input.getParentBranchId());
                stmt.setArray(index++, generateArray(connection, input.getBranchesToAdd()));
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

    private Array generateArray(Connection connection, List<String> branchesToAdd) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_LEVEL_MAPPING_MASTER, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_LEVEL_MAPPING_MASTER_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[branchesToAdd.size()];
        for (int index = 0; index < branchesToAdd.size(); index++) {
            Object[] paramArray = new Object[1];
            paramArray[0] = branchesToAdd.get(index);
            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

}
