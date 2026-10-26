package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.RbiBondAccountingEntriesDAO;
import com.isl.gbm.rbibond.dto.RbiBondAccountingEntriesDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@Component
public class RbiBondAccountingEntriesDAOImpl implements RbiBondAccountingEntriesDAO {
    private static final Logger log = LoggerFactory.getLogger(RbiBondAccountingEntriesDAOImpl.class);
    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO getModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACC_ENTS_ADD_VAL.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getModuleId());

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.execute();

                resultMsg = stmt.getString(index - 1);

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index - 1));
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS_CONST)) {
                    resultSet = (ResultSet) stmt.getObject(4);
                    if (resultSet != null) {
                        List<RbiBondAccountingEntriesDTO> list = new ArrayList<>();
                        RbiBondAccountingEntriesDTO listDTO = null;

                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {

                                listDTO = new RbiBondAccountingEntriesDTO();
                                listDTO.setSchemeCode(resultSet.getString(1));
                                listDTO.setSchemeName(resultSet.getString(2));
                                listDTO.setSchemeStatus(resultSet.getString(3));
                                listDTO.setParameterEffectiveFromDate(resultSet.getString(4));
                                listDTO.setParameterEffectiveToDate(resultSet.getString(5));
                                listDTO.setBondSchemeEffectiveFromDate(resultSet.getString(6));
                                listDTO.setBondSchemeEffectiveToDate(resultSet.getString(7));

                                list.add(listDTO);
                            }
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
        if (responseDataDTO == null) {
            responseDataDTO = new ResponseDataDTO();
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getVersionNo(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACC_ENTS_INQ.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getSchemeCode());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getVersionNo());

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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.execute();

                resultMsg = stmt.getString(index - 1);
                log.info("resultMsg {}" , resultMsg);

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index - 1));

                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS_CONST)) {
                    resultSet = (ResultSet) stmt.getObject(15);

                    if (resultSet != null && rbiBondAccountingEntriesDTO.getVersionNo() == null) {
                        List<RbiBondAccountingEntriesDTO> list = new ArrayList<>();
                        RbiBondAccountingEntriesDTO listDTO = null;

                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {

                                listDTO = new RbiBondAccountingEntriesDTO();
                                listDTO.setVersionNo(resultSet.getString(1));
                                listDTO.setEffectiveFromDate(resultSet.getString(2));

                                list.add(listDTO);
                            }
                        }
                        responseDataDTO.setResult(list);
                    } else if (resultSet != null && rbiBondAccountingEntriesDTO.getSchemeCode() != null && rbiBondAccountingEntriesDTO.getVersionNo() != null) {
                        List<RbiBondAccountingEntriesDTO> list = new ArrayList<>();

                        while (resultSet.next()) {
                            RbiBondAccountingEntriesDTO dto = new RbiBondAccountingEntriesDTO();

                            dto.setPkColumnValues(stmt.getString(5));
                            dto.setCreatedBy(stmt.getString(6));
                            dto.setCreatedDate(stmt.getString(7));
                            dto.setModifiedBy(stmt.getString(8));
                            dto.setModifiedDate(stmt.getString(9));
                            dto.setVerifiedBy(stmt.getString(10));
                            dto.setVerifiedDate(stmt.getString(11));
                            dto.setPreviousOperationType(stmt.getString(12));
                            dto.setVerifiedStatus(stmt.getString(13));
                            dto.setDeleteRemarks(stmt.getString(14));
                            dto.setAccountingEntriesOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(15)));

                            responseDataDTO.setResult(dto);
                        }
                        responseDataDTO.setResult(list);
                    }
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index - 1));
                    resultMsg = stmt.getString(index - 1);
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
        if (responseDataDTO == null) {
            responseDataDTO = new ResponseDataDTO();
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getModifyModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACC_ENTS_MOD_VALUES.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getModuleId());

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.execute();

                resultMsg = stmt.getString(index - 1);

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index - 1));
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS_CONST)) {
                    resultSet = (ResultSet) stmt.getObject(4);
                    if (resultSet != null) {
                        List<RbiBondAccountingEntriesDTO> list = new ArrayList<>();
                        RbiBondAccountingEntriesDTO listDTO = null;

                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {

                                listDTO = new RbiBondAccountingEntriesDTO();
                                listDTO.setSchemeCode(resultSet.getString(1));
                                listDTO.setSchemeName(resultSet.getString(2));
                                listDTO.setSchemeStatus(resultSet.getString(3));
                                listDTO.setEffectiveFromDate(resultSet.getString(8));
                                listDTO.setVersionNo(resultSet.getString(9));

                                list.add(listDTO);
                            }
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
        if (responseDataDTO == null) {
            responseDataDTO = new ResponseDataDTO();
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getVerifyModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACC_ENTS_VER_VAL.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getModuleId());

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.execute();

                resultMsg = stmt.getString(index - 1);
                log.info("resultMsg {}" , resultMsg);

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index - 1));
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS_CONST)) {
                    resultSet = (ResultSet) stmt.getObject(4);
                    if (resultSet != null) {
                        List<RbiBondAccountingEntriesDTO> list = new ArrayList<>();
                        RbiBondAccountingEntriesDTO listDTO = null;

                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {

                                listDTO = new RbiBondAccountingEntriesDTO();
                                listDTO.setSchemeCode(resultSet.getString(1));
                                listDTO.setSchemeName(resultSet.getString(2));
                                listDTO.setSchemeStatus(resultSet.getString(3));
                                listDTO.setEffectiveFromDate(resultSet.getString(8));
                                listDTO.setVersionNo(resultSet.getString(9));

                                list.add(listDTO);
                            }
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
        if (responseDataDTO == null) {
            responseDataDTO = new ResponseDataDTO();
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addAccountEntriesValues(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            List<ColumnValueDTO> columnValueDTOList = new ArrayList<>();
            if (rbiBondAccountingEntriesDTO.getAccountingEntriesListModel() != null) {
                Set<String> allowedKeys = new HashSet<>(Arrays.asList("C003", "C004", "C005", "C123"));
                for (Map.Entry<String, String> entry : rbiBondAccountingEntriesDTO.getAccountingEntriesListModel().entrySet()) {
                    if (!allowedKeys.contains(entry.getKey())) {
                        if (entry.getValue() != null && !entry.getValue().trim().isEmpty() && !entry.getValue().trim().matches("\\d+")) {
                            responseDataDTO.setMessage("Account number format is not valid");
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }
                    }
                    ColumnValueDTO columnValueDTO = new ColumnValueDTO();
                    columnValueDTO.setColName(entry.getKey());
                    columnValueDTO.setValue(entry.getValue());
                    columnValueDTOList.add(columnValueDTO);
                }
            }
            rbiBondAccountingEntriesDTO.setColumnValues(columnValueDTOList);
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACC_ENTS_ADD.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getSchemeCode());
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, rbiBondAccountingEntriesDTO.getColumnValues()));

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                resultMsg = stmt.getString(index);

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiryDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        Connection connection = null;
        CallableStatement stmt = null;

        String resultMsg = "";
        try {
            List<ColumnValueDTO> columnValueDTOList = new ArrayList<>();
            if (rbiBondAccountingEntriesDTO.getAccountingEntriesListModel() != null) {
                for (Map.Entry<String, String> entry : rbiBondAccountingEntriesDTO.getAccountingEntriesListModel().entrySet()) {
                    ColumnValueDTO columnValueDTO = new ColumnValueDTO();
                    columnValueDTO.setColName(entry.getKey());
                    columnValueDTO.setValue(entry.getValue());
                    columnValueDTOList.add(columnValueDTO);
                    log.info("Column Values: {}" , columnValueDTOList);
                }
                log.info("Column Values: {}" , columnValueDTOList);
            }

            rbiBondAccountingEntriesDTO.setColumnValues(columnValueDTOList);

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACC_ENTS_ADD.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getSchemeCode());
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, rbiBondAccountingEntriesDTO.getColumnValues()));

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getModifyDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACC_ENTS_MOD_VAL.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getModuleId());

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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.execute();

                resultMsg = stmt.getString(index - 1);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index - 1));
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS_CONST)) {
                    resultSet = (ResultSet) stmt.getObject(14);
                    List<RbiBondAccountingEntriesDTO> list = new ArrayList<>();

                    while (resultSet.next()) {
                        RbiBondAccountingEntriesDTO dto = new RbiBondAccountingEntriesDTO();

                        dto.setPkColumnValues(stmt.getString(4));
                        dto.setAccountingEntriesOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));

                        responseDataDTO.setResult(dto);
                    }
                    responseDataDTO.setResult(list);

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
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
        if (responseDataDTO == null) {
            responseDataDTO = new ResponseDataDTO();
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getVerifyDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACC_ENTS_VER_SEL.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getModuleId());

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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.execute();

                resultMsg = stmt.getString(index - 1);

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index - 1));
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS_CONST)) {
                    resultSet = (ResultSet) stmt.getObject(14);

                    if (resultSet != null && rbiBondAccountingEntriesDTO.getModuleId() != null) {
                        while (resultSet.next()) {
                            RbiBondAccountingEntriesDTO dto = new RbiBondAccountingEntriesDTO();

                            dto.setPkColumnValues(stmt.getString(4));
                            dto.setCreatedBy(stmt.getString(5));
                            dto.setCreatedDate(stmt.getString(6));
                            dto.setModifiedBy(stmt.getString(7));
                            dto.setModifiedDate(stmt.getString(8));
                            dto.setVerifiedBy(stmt.getString(9));
                            dto.setVerifiedDate(stmt.getString(10));
                            dto.setPreviousOperationType(stmt.getString(11));
                            dto.setVerifiedStatus(stmt.getString(12));
                            dto.setDeleteRemarks(stmt.getString(13));
                            dto.setAccountingEntriesOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));

                            responseDataDTO.setResult(dto);
                        }
                    }

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
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
        if (responseDataDTO == null) {
            responseDataDTO = new ResponseDataDTO();
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        String resultMsg = "";

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACC_ENTS_VER.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getPkColumnValues());

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.execute();

                resultMsg = stmt.getString(index - 1);

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index - 1));
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS_CONST)) {
                    String outParam1 = stmt.getString(index - 2); // First OUT parameter
                    String outParam2 = stmt.getString(index - 1); // Second OUT parameter
                    log.info("OUT Parameter 1: {}" , outParam1);
                    log.info("OUT Parameter 2: {}" , outParam2);
                    responseDataDTO.setMessage(outParam1);
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
        if (responseDataDTO == null) {
            responseDataDTO = new ResponseDataDTO();
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            List<ColumnValueDTO> columnValueDTOList = new ArrayList<>();
            if (rbiBondAccountingEntriesDTO.getAccountingEntriesListModel() != null) {
                Set<String> allowedKeys = new HashSet<>(Arrays.asList("C003", "C004", "C005", "C123"));
                for (Map.Entry<String, String> entry : rbiBondAccountingEntriesDTO.getAccountingEntriesListModel().entrySet()) {
                        if (!allowedKeys.contains(entry.getKey())) {
                            if (entry.getValue() != null && !entry.getValue().trim().isEmpty() && !entry.getValue().trim().matches("\\d+")) {
                                responseDataDTO.setMessage("Account number format is not valid");
                                responseDataDTO.setStatusCode("0");
                                return responseDataDTO;
                            }
                        }
                    ColumnValueDTO columnValueDTO = new ColumnValueDTO();
                    columnValueDTO.setColName(entry.getKey());
                    columnValueDTO.setValue(entry.getValue());
                    columnValueDTOList.add(columnValueDTO);
                    log.info("Column Values: {}" , columnValueDTOList);
                }
            }
            rbiBondAccountingEntriesDTO.setColumnValues(columnValueDTOList);

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACC_ENTS_MOD.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getPkColumnValues());
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, rbiBondAccountingEntriesDTO.getColumnValues()));

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO deleteSubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondAccountingEntriesDTO.getRejectionRemarks() == null
                || rbiBondAccountingEntriesDTO.getRejectionRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACC_ENTS_DEL.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getPkColumnValues());
                stmt.setString(index++, rbiBondAccountingEntriesDTO.getRejectionRemarks());

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info("Executed the sql query :");
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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