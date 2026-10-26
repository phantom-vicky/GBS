package com.isl.gbm.common.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dao.InterestRateCodeDAO;
import com.isl.gbm.common.dto.InterestRateCodeRequestDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.ScssInterestRateCodeMaintenanceDto;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.common.util.ScssProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InterestRateCodeDAOImpl implements InterestRateCodeDAO {

    private static final Logger log = LoggerFactory.getLogger(InterestRateCodeDAOImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO add(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ScssProcNameEnum.SP_GBM_SCSS_INT_RATE_INS.getProcName());
                stmt.setString(index++, interestRateCodeRequestDTO.getModuleId());
                stmt.setString(index++, interestRateCodeRequestDTO.getInterestRateCode());
                stmt.setString(index++, interestRateCodeRequestDTO.getInterestRateCodeDescription());
                stmt.setString(index++, CommonConstants.SCSS_VERSION_NO);
                stmt.setString(index++, interestRateCodeRequestDTO.getEffectiveFromDate());
                stmt.setString(index++, interestRateCodeRequestDTO.getInterestRate());
                stmt.setString(index++, interestRateCodeRequestDTO.getRemarks());
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
    public ResponseDataDTO getModify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ScssInterestRateCodeMaintenanceDto scssInterestRateCodeMaintenanceDtoOut = null;
        List<ScssInterestRateCodeMaintenanceDto> scssInterestRateCodeMaintenanceDtoOutList = new ArrayList<>();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ScssProcNameEnum.SP_GBM_SCSS_INT_RATE_MOD_SEL.getProcName());
                stmt.setString(index++, interestRateCodeRequestDTO.getModuleId());
                stmt.setString(index++, interestRateCodeRequestDTO.getInterestRateCode());
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    scssInterestRateCodeMaintenanceDtoOut = new ScssInterestRateCodeMaintenanceDto();
                    scssInterestRateCodeMaintenanceDtoOut.setId(stmt.getString(3));
                    scssInterestRateCodeMaintenanceDtoOut.setCreatedBy(stmt.getString(4));
                    scssInterestRateCodeMaintenanceDtoOut.setCreatedDate(stmt.getString(5));
                    scssInterestRateCodeMaintenanceDtoOut.setModifiedBy(stmt.getString(6));
                    scssInterestRateCodeMaintenanceDtoOut.setModifiedDate(stmt.getString(7));
                    scssInterestRateCodeMaintenanceDtoOut.setVerifiedBy(stmt.getString(8));
                    scssInterestRateCodeMaintenanceDtoOut.setVerifiedDate(stmt.getString(9));
                    scssInterestRateCodeMaintenanceDtoOut.setPreviousOperationType(stmt.getString(10));
                    scssInterestRateCodeMaintenanceDtoOut.setVerifiedStatus(stmt.getString(11));
                    if (interestRateCodeRequestDTO.getInterestRateCode() == null) {
                        scssInterestRateCodeMaintenanceDtoOut.setScssInterestRateCodeMaintenanceList(DataBaseUtils.getGenericColumnValueFromResultSet((ResultSet) stmt.getObject(12)));
                    } else {
                        scssInterestRateCodeMaintenanceDtoOut.setScssInterestRateCodeMaintenanceList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(12)));
                    }
                    scssInterestRateCodeMaintenanceDtoOut.setScssInterestRateCodeTorchList(DataBaseUtils.getGenericColumnValueFromResultSet((ResultSet) stmt.getObject(13)));
                    scssInterestRateCodeMaintenanceDtoOutList.add(scssInterestRateCodeMaintenanceDtoOut);
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
        responseDataDTO.setResult(scssInterestRateCodeMaintenanceDtoOutList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ScssProcNameEnum.SP_GBM_SCSS_INT_RATE_MOD.getProcName());
                stmt.setString(index++, interestRateCodeRequestDTO.getModuleId());
                stmt.setString(index++, interestRateCodeRequestDTO.getInterestRateCode());
                stmt.setString(index++, interestRateCodeRequestDTO.getEffectiveFromDate());
                stmt.setString(index++, interestRateCodeRequestDTO.getInterestRate());
                stmt.setString(index++, interestRateCodeRequestDTO.getRemarks());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    responseDataDTO.setResult(stmt.getString(8));
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
    public ResponseDataDTO getVerifyDeleteDetails(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ScssInterestRateCodeMaintenanceDto scssInterestRateCodeMaintenanceDtoOut = null;
        List<ScssInterestRateCodeMaintenanceDto> scssInterestRateCodeMaintenanceDtoOutList = new ArrayList<>();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ScssProcNameEnum.SP_GBM_SCSS_INT_RATE_VER_SEL.getProcName());
                stmt.setString(index++, interestRateCodeRequestDTO.getModuleId());
                stmt.setString(index++, interestRateCodeRequestDTO.getInterestRateCode());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    scssInterestRateCodeMaintenanceDtoOut = new ScssInterestRateCodeMaintenanceDto();
                    scssInterestRateCodeMaintenanceDtoOut.setId(stmt.getString(4));
                    scssInterestRateCodeMaintenanceDtoOut.setCreatedBy(stmt.getString(5));
                    scssInterestRateCodeMaintenanceDtoOut.setCreatedDate(stmt.getString(6));
                    scssInterestRateCodeMaintenanceDtoOut.setModifiedBy(stmt.getString(7));
                    scssInterestRateCodeMaintenanceDtoOut.setModifiedDate(stmt.getString(8));
                    scssInterestRateCodeMaintenanceDtoOut.setVerifiedBy(stmt.getString(9));
                    scssInterestRateCodeMaintenanceDtoOut.setVerifiedDate(stmt.getString(10));
                    scssInterestRateCodeMaintenanceDtoOut.setPreviousOperationType(stmt.getString(11));
                    scssInterestRateCodeMaintenanceDtoOut.setVerifiedStatus(stmt.getString(12));
                    if (interestRateCodeRequestDTO.getInterestRateCode() == null) {
                        scssInterestRateCodeMaintenanceDtoOut.setScssInterestRateCodeMaintenanceList(DataBaseUtils.getGenericColumnValueFromResultSet((ResultSet) stmt.getObject(13)));
                    } else {
                        scssInterestRateCodeMaintenanceDtoOut.setScssInterestRateCodeMaintenanceList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(13)));
                    }
                    scssInterestRateCodeMaintenanceDtoOut.setScssInterestRateCodeTorchList(DataBaseUtils.getGenericColumnValueFromResultSet((ResultSet) stmt.getObject(14)));
                    scssInterestRateCodeMaintenanceDtoOutList.add(scssInterestRateCodeMaintenanceDtoOut);
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
        responseDataDTO.setResult(scssInterestRateCodeMaintenanceDtoOutList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ScssProcNameEnum.SP_GBM_SCSS_INT_RATE_VER.getProcName());
                stmt.setString(index++, interestRateCodeRequestDTO.getModuleId());
                stmt.setString(index++, interestRateCodeRequestDTO.getInterestRateCode());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, interestRateCodeRequestDTO.getRemarks());
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
    public ResponseDataDTO delete(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ScssProcNameEnum.SP_GBM_SCSS_INT_RATE_DEL.getProcName());
                stmt.setString(index++, interestRateCodeRequestDTO.getInterestRateCode());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, interestRateCodeRequestDTO.getRemarks());
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
    public ResponseDataDTO inquiry(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ScssInterestRateCodeMaintenanceDto scssInterestRateCodeMaintenanceDtoOutFinal = new ScssInterestRateCodeMaintenanceDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ScssProcNameEnum.SP_GBM_SCSS_INT_RATE_INQ.getProcName());
                stmt.setString(index++, interestRateCodeRequestDTO.getModuleId());
                stmt.setString(index++, interestRateCodeRequestDTO.getInterestRateCode());
                stmt.setString(index++, interestRateCodeRequestDTO.getVersion());
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
                stmt.setString(index++, interestRateCodeRequestDTO.getIsVersionFlag());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (interestRateCodeRequestDTO.getInterestRateCode() != null && StringUtils.equalsIgnoreCase(interestRateCodeRequestDTO.getIsVersionFlag(), "N")) {
                        scssInterestRateCodeMaintenanceDtoOutFinal.setScssInterestRateCodeMaintenanceList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(4)));
                    }
                    if (interestRateCodeRequestDTO.getInterestRateCode() == null) {
                        scssInterestRateCodeMaintenanceDtoOutFinal.setScssInterestRateCodeTorchList(DataBaseUtils.getGenericColumnValueFromResultSet((ResultSet) stmt.getObject(5)));
                    }
                    List<Map<String, String>> versionTorchList = new ArrayList<>();
                    Map<String, String> versionTorch = null;
                    resultSet = (ResultSet) stmt.getObject(6);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                versionTorch = new HashMap<>();
                                versionTorch.put("interestRate", resultSet.getString(1));
                                versionTorch.put("interestRateCodeDescription", resultSet.getString(2));
                                //versionTorch.put("interestRateCode", resultSet.getString(2));
                                versionTorch.put("version", resultSet.getString(3));
                                versionTorch.put("effectiveFromDate", resultSet.getString(4));
                                // versionTorch.put("interestRateCodeDescription", resultSet.getString(5));
                                versionTorchList.add(versionTorch);
                            }
                        }
                        scssInterestRateCodeMaintenanceDtoOutFinal.setVersionTorch(versionTorchList);

                    }
                    List<Map<String, String>> oldList = new ArrayList<>();
                    Map<String, String> OldRecords = null;
                    resultSet = (ResultSet) stmt.getObject(7);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                OldRecords = new HashMap<>();
                                OldRecords.put("sNo", resultSet.getString(1));
                                OldRecords.put("versionNo", resultSet.getString(2));
                                OldRecords.put("effectiveFromDate", resultSet.getString(3));
                                OldRecords.put("effectiveToDate", resultSet.getString(4));
                                OldRecords.put("interestRate", resultSet.getString(5));
                                OldRecords.put("remarks", resultSet.getString(6));
                                OldRecords.put("verifiedStatus", resultSet.getString(7));
                                OldRecords.put("isActive", resultSet.getString(8));
                                oldList.add(OldRecords);
                            }
                        }
                        scssInterestRateCodeMaintenanceDtoOutFinal.setOldList(oldList);
                    }
                    scssInterestRateCodeMaintenanceDtoOutFinal.setCreatedBy(stmt.getString(8));
                    scssInterestRateCodeMaintenanceDtoOutFinal.setCreatedDate(stmt.getString(9));
                    scssInterestRateCodeMaintenanceDtoOutFinal.setModifiedBy(stmt.getString(10));
                    scssInterestRateCodeMaintenanceDtoOutFinal.setModifiedDate(stmt.getString(11));
                    scssInterestRateCodeMaintenanceDtoOutFinal.setVerifiedBy(stmt.getString(12));
                    scssInterestRateCodeMaintenanceDtoOutFinal.setVerifiedDate(stmt.getString(13));
                    scssInterestRateCodeMaintenanceDtoOutFinal.setPreviousOperationType(stmt.getString(14));
                    scssInterestRateCodeMaintenanceDtoOutFinal.setVerifiedStatus(stmt.getString(15));
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
        responseDataDTO.setResult(scssInterestRateCodeMaintenanceDtoOutFinal);
        return responseDataDTO;
    }
}
