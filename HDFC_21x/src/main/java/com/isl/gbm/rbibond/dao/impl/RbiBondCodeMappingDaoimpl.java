package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.RbiBondCodeMappingDao;
import com.isl.gbm.rbibond.dto.RbiBondCodeMappingDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.internal.OracleTypes;
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
public class RbiBondCodeMappingDaoimpl implements RbiBondCodeMappingDao {


    private static final Logger log = LoggerFactory.getLogger(RbiBondCodeMappingDaoimpl.class);


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO add(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BCH_CD_MAP_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getPdoCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getRbiCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBlaPrefix());
                stmt.setString(index++, rbiBondTdsParameterDTO.getEffFromDate());
                stmt.setString(index++, rbiBondTdsParameterDTO.getRemark());
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
    public ResponseDataDTO addValidation() {

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BCH_CD_MAP_ADD_VAL.getProcName());
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
    public ResponseDataDTO inquiryDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BCH_CD_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getPdoCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getVersionNo());
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
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (rbiBondTdsParameterDTO.getPdoCode() == null || rbiBondTdsParameterDTO.getPdoCode().isEmpty()) {
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(15);

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("branchCode", resultSet.getString(1));
                                temp.put("verifiedStatus", resultSet.getString(2));
                                tdsCode.add(temp);
                            }
                            rbiBondTdsParameterDTO.setTorchValue(tdsCode);
                        }
                    }
                    else if(rbiBondTdsParameterDTO.getVersionNo() == null || rbiBondTdsParameterDTO.getVersionNo().isEmpty()){

                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(15);

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("versionNo", resultSet.getString(1));
                                temp.put("effFromDate", resultSet.getString(2));
                                tdsCode.add(temp);
                            }
                            rbiBondTdsParameterDTO.setVersionNoList(tdsCode);
                        }
                    }

                    else {
                        rbiBondTdsParameterDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(15)));
                        rbiBondTdsParameterDTO.setPkColumnValue(stmt.getString(5));
                        rbiBondTdsParameterDTO.setCreatedBy(stmt.getString(6));
                        rbiBondTdsParameterDTO.setCreatedDate(stmt.getString(7));
                        rbiBondTdsParameterDTO.setModifiedBy(stmt.getString(8));
                        rbiBondTdsParameterDTO.setModifiedDate(stmt.getString(9));
                        rbiBondTdsParameterDTO.setVerifiedBy(stmt.getString(10));
                        rbiBondTdsParameterDTO.setVerifiedDate(stmt.getString(11));
                        rbiBondTdsParameterDTO.setPrevOperType(stmt.getString(12));
                        rbiBondTdsParameterDTO.setVerifiedStatus(stmt.getString(13));
                        rbiBondTdsParameterDTO.setRejectionRemark(stmt.getString(14));
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
        responseDataDTO.setResult(rbiBondTdsParameterDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyDeleteDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BCH_CD_MAP_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getPdoCode());
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
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (rbiBondTdsParameterDTO.getPdoCode() == null || rbiBondTdsParameterDTO.getPdoCode().isEmpty()) {
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(14);

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("branchCode", resultSet.getString(1));
                                temp.put("verifiedStatus", resultSet.getString(2));
                                tdsCode.add(temp);
                            }
                            rbiBondTdsParameterDTO.setTorchValue(tdsCode);
                        }
                    }
                    else {

                        rbiBondTdsParameterDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));
                        rbiBondTdsParameterDTO.setPkColumnValue(stmt.getString(4));
                        rbiBondTdsParameterDTO.setCreatedBy(stmt.getString(5));
                        rbiBondTdsParameterDTO.setCreatedDate(stmt.getString(6));
                        rbiBondTdsParameterDTO.setModifiedBy(stmt.getString(7));
                        rbiBondTdsParameterDTO.setModifiedDate(stmt.getString(8));
                        rbiBondTdsParameterDTO.setVerifiedBy(stmt.getString(9));
                        rbiBondTdsParameterDTO.setVerifiedDate(stmt.getString(10));
                        rbiBondTdsParameterDTO.setPrevOperType(stmt.getString(11));
                        rbiBondTdsParameterDTO.setVerifiedStatus(stmt.getString(12));
                        rbiBondTdsParameterDTO.setRejectionRemark(stmt.getString(13));
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
        responseDataDTO.setResult(rbiBondTdsParameterDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BCH_CD_MAP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getId());
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
    public ResponseDataDTO delete(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondTdsParameterDTO.getRejectionRemark() == null
                || rbiBondTdsParameterDTO.getRejectionRemark().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BCH_CD_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getRejectionRemark());
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
    public ResponseDataDTO modifyDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BCH_CD_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getPdoCode());
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
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(rbiBondTdsParameterDTO.getPdoCode() == null || rbiBondTdsParameterDTO.getPdoCode().isEmpty()){
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(14);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {

                                temp = new HashMap<>();
                                temp.put("branchCode", resultSet.getString(1));
                                temp.put("verifiedStatus", resultSet.getString(2));
                                temp.put("effFromDate", resultSet.getString(3));
                                tdsCode.add(temp);

                            }
                            rbiBondTdsParameterDTO.setTorchValue(tdsCode);
                        }
                    }else{
                        rbiBondTdsParameterDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));
                        rbiBondTdsParameterDTO.setPkColumnValue(stmt.getString(4));
                        rbiBondTdsParameterDTO.setCreatedBy(stmt.getString(5));
                        rbiBondTdsParameterDTO.setCreatedDate(stmt.getString(6));
                        rbiBondTdsParameterDTO.setModifiedBy(stmt.getString(7));
                        rbiBondTdsParameterDTO.setModifiedDate(stmt.getString(8));
                        rbiBondTdsParameterDTO.setVerifiedBy(stmt.getString(9));
                        rbiBondTdsParameterDTO.setVerifiedDate(stmt.getString(10));
                        rbiBondTdsParameterDTO.setPrevOperType(stmt.getString(11));
                        rbiBondTdsParameterDTO.setVerifiedStatus(stmt.getString(12));
                        rbiBondTdsParameterDTO.setRejectionRemark(stmt.getString(13));

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
        responseDataDTO.setResult(rbiBondTdsParameterDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySubmit(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BCH_CD_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getId());
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, rbiBondTdsParameterDTO.getColumnValues()));
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
    public ResponseDataDTO getBranchCode(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<Map<String, String>> list = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BCH_CD_MAP_GET_BCH.getProcName());
                stmt.setString(index++, rbiBondTdsParameterDTO.getModuleId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject(2);

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        list = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();
                            temp.put("branchCode", resultSet.getString(1));
                            temp.put("branchDescription", resultSet.getString(2));
                            list.add(temp);
                        }
                        rbiBondTdsParameterDTO.setBranchList(list);
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
        responseDataDTO.setResult(rbiBondTdsParameterDTO);
        return responseDataDTO;
    }


}
