package com.isl.gbm.rbibond.dao.impl;


import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.RbiBondSchemeParameterDAO;
import com.isl.gbm.rbibond.dto.RbiBondSchemeParameterDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
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
public class RbiBondSchemeParameterDaoImpl implements RbiBondSchemeParameterDAO {

    private static final Logger log = LoggerFactory.getLogger(RbiBondSchemeParameterDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO isRecordExist(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SCHM_PARAM_ADD_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondSchemeParameterDTO.getSchemeCode());
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO add(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        log.info("Rbi bond add Payload: {}", rbiBondSchemeParameterDTO.getColumnValues());
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SCHM_PARAM_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondSchemeParameterDTO.getSchemeCode());
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, rbiBondSchemeParameterDTO.getColumnValues()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO modifySelect(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        rbiBondSchemeParameterDTO.setInterestRateCode("Y");
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        Map<String, Object> resultMap = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SCHM_PARAM_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondSchemeParameterDTO.getSchemeCode());
//                stmt.setString(index++, rbiBondSchemeParameterDTO.getInterestRateCode());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(StringUtils.isEmpty(rbiBondSchemeParameterDTO.getSchemeCode())){
                        ResultSet resultSet = (ResultSet) stmt.getObject(14);

                        if (resultSet != null) {
                            List<RbiBondSchemeParameterDTO> schemeParameterResponseDtoList = new ArrayList<>();
                            RbiBondSchemeParameterDTO schemeParameterResponseDto = null;
                            while (resultSet.next()) {
                                schemeParameterResponseDto = new RbiBondSchemeParameterDTO();
                                schemeParameterResponseDto.setSchemeCode(resultSet.getString(1));
                                schemeParameterResponseDto.setSchemeName(resultSet.getString(2));
                                schemeParameterResponseDto.setSchemeStatus(resultSet.getString(3));
                                schemeParameterResponseDto.setEffFromDate(resultSet.getString(5));
                                schemeParameterResponseDtoList.add(schemeParameterResponseDto);
                            }
                            resultMap = new HashMap<>();
                            resultMap.put("schemeParameterResponseDtoList", schemeParameterResponseDtoList);
                            responseDataDTO.setResult(resultMap);

                        }

                    } else {
                        RbiBondSchemeParameterDTO efreightSchemeParameterDTO = new RbiBondSchemeParameterDTO();
                        efreightSchemeParameterDTO.setPkColumnValue(stmt.getString(4));
                        efreightSchemeParameterDTO.setCreatedBy(stmt.getString(5));
                        efreightSchemeParameterDTO.setCreatedDate(stmt.getString(6));
                        efreightSchemeParameterDTO.setLastModifiedBy(stmt.getString(7));
                        efreightSchemeParameterDTO.setLastModifiedDate(stmt.getString(8));
                        efreightSchemeParameterDTO.setLastVerifiedBy(stmt.getString(9));
                        efreightSchemeParameterDTO.setLastVerifiedDate(stmt.getString(10));
                        efreightSchemeParameterDTO.setPreviousOperationType(stmt.getString(11));
                        efreightSchemeParameterDTO.setVerifiedStatus(stmt.getString(12));
                        efreightSchemeParameterDTO.setRemarks(stmt.getString(13));
                        efreightSchemeParameterDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));
                        responseDataDTO.setResult(efreightSchemeParameterDTO);
                    }
                }
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
    public ResponseDataDTO modify(RbiBondSchemeParameterDTO efreightSchemeParameterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SCHM_PARAM_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, efreightSchemeParameterDTO.getPkColumnValue());
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, efreightSchemeParameterDTO.getColumnValues()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO unVerifySelect(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        log.info("Rbi bond scheme code: {}",rbiBondSchemeParameterDTO.getSchemeCode());
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SCHM_PARAM_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondSchemeParameterDTO.getSchemeCode());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(15) != null && stmt.getString(15).equals(CommonConstants.SUCCESS)) {
                    RbiBondSchemeParameterDTO efreightSchemeParameterDTO = new RbiBondSchemeParameterDTO();
                    efreightSchemeParameterDTO.setPkColumnValue(stmt.getString(4));
                    efreightSchemeParameterDTO.setCreatedBy(stmt.getString(5));
                    efreightSchemeParameterDTO.setCreatedDate(stmt.getString(6));
                    efreightSchemeParameterDTO.setLastModifiedBy(stmt.getString(7));
                    efreightSchemeParameterDTO.setLastModifiedDate(stmt.getString(8));
                    efreightSchemeParameterDTO.setLastVerifiedBy(stmt.getString(9));
                    efreightSchemeParameterDTO.setLastVerifiedDate(stmt.getString(10));
                    efreightSchemeParameterDTO.setPreviousOperationType(stmt.getString(11));
                    efreightSchemeParameterDTO.setVerifiedStatus(stmt.getString(12));
                    efreightSchemeParameterDTO.setRemarks(stmt.getString(13));
                    efreightSchemeParameterDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));
                    responseDataDTO.setResult(efreightSchemeParameterDTO);
                }
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
    public ResponseDataDTO verify(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SCHM_PARAM_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondSchemeParameterDTO.getId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO delete(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondSchemeParameterDTO.getRemarks() == null
                || rbiBondSchemeParameterDTO.getRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SCHM_PARAM_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondSchemeParameterDTO.getId());
                stmt.setString(index++, rbiBondSchemeParameterDTO.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO inquiry(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SCHM_PARAM_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondSchemeParameterDTO.getSchemeCode());
                stmt.setString(index++, rbiBondSchemeParameterDTO.getVersionNo());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (StringUtils.isEmpty(rbiBondSchemeParameterDTO.getVersionNo())) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(15);
                        if (resultSet != null) {
                            List<RbiBondSchemeParameterDTO> schemeParameterResponseDtoList = new ArrayList<>();
                            RbiBondSchemeParameterDTO schemeParameterResponseDto = null;
                            while (resultSet.next()) {
                                schemeParameterResponseDto = new RbiBondSchemeParameterDTO();
                                schemeParameterResponseDto.setVersionNo(resultSet.getString(1));
                                schemeParameterResponseDto.setEffFromDate(resultSet.getString(2));
                                schemeParameterResponseDtoList.add(schemeParameterResponseDto);
                            }
                            responseDataDTO.setResult(schemeParameterResponseDtoList);
                        }
                    } else {
                        RbiBondSchemeParameterDTO ppfSchemeParameterResponseDto = new RbiBondSchemeParameterDTO();
                        ppfSchemeParameterResponseDto.setPkColumnValue(stmt.getString(5));
                        ppfSchemeParameterResponseDto.setCreatedBy(stmt.getString(6));
                        ppfSchemeParameterResponseDto.setCreatedDate(stmt.getString(7));
                        ppfSchemeParameterResponseDto.setLastModifiedBy(stmt.getString(8));
                        ppfSchemeParameterResponseDto.setLastModifiedDate(stmt.getString(9));
                        ppfSchemeParameterResponseDto.setLastVerifiedBy(stmt.getString(10));
                        ppfSchemeParameterResponseDto.setLastVerifiedDate(stmt.getString(11));
                        ppfSchemeParameterResponseDto.setPreviousOperationType(stmt.getString(12));
                        ppfSchemeParameterResponseDto.setVerifiedStatus(stmt.getString(13));
                        ppfSchemeParameterResponseDto.setRemarks(stmt.getString(14));
                        ppfSchemeParameterResponseDto.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(15)));

                        responseDataDTO.setResult(ppfSchemeParameterResponseDto);

                    }
                }
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO interestRtCdTorch(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SCHM_PARAM_INT_RT_CD_TORCH.getProcName());
                stmt.setString(index++, rbiBondSchemeParameterDTO.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(2);

                    if (resultSet != null) {
                        List<String> list = new ArrayList<>();
                        while (resultSet.next()) {
                            list.add(resultSet.getString(1));
                        }
                        responseDataDTO.setResult(list);
                    }
                }

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


}
