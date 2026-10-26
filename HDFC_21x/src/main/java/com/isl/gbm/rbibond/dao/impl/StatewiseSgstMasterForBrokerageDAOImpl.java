package com.isl.gbm.rbibond.dao.impl;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.StatewiseSgstMasterForBrokerageDAO;
import com.isl.gbm.rbibond.dto.StatewiseSgstMasterForBrokerageDto;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class StatewiseSgstMasterForBrokerageDAOImpl implements StatewiseSgstMasterForBrokerageDAO {


    private static final Logger log = LoggerFactory.getLogger(StatewiseSgstMasterForBrokerageDAOImpl.class);


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO modify(StatewiseSgstMasterForBrokerageDto input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ST_WISE_SGST_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getPrimaryKeyvalue());
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection,input.getColumnValues()));
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
        public ResponseDataDTO add(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
            Connection connection = null;
            CallableStatement stmt = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "";
            Map<String, Object> resultMap = null;
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ST_WISE_SGST_ADD.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getSchemeCode());
                    stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getStateCodeId());
                    stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getSgstRate());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                   log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        if (null != statewiseSgstMasterForBrokerageDto.getSchemeCode()&&
                                (null == statewiseSgstMasterForBrokerageDto.getStateCodeId() ||
                                        !statewiseSgstMasterForBrokerageDto.getStateCodeId().isEmpty())) {
                            ResultSet resultSet = (ResultSet) stmt.getObject(6);
                            List<StatewiseSgstMasterForBrokerageDto> list = new ArrayList<>();
                            StatewiseSgstMasterForBrokerageDto output = null;
                            while (resultSet.next()) {
                                output = new StatewiseSgstMasterForBrokerageDto();
                                output.setStateCodeId(resultSet.getString(1));
                                output.setStateName(resultSet.getString(2));
                                list.add(output);
                            }
                            resultMap = new HashMap<>();
                            resultMap.put("list", list);
                            responseDataDTO.setResult(resultMap);
                        } else if((null == statewiseSgstMasterForBrokerageDto.getSchemeCode() || statewiseSgstMasterForBrokerageDto.getSchemeCode().isEmpty())&&
                                (null == statewiseSgstMasterForBrokerageDto.getStateCodeId() || statewiseSgstMasterForBrokerageDto.getStateCodeId().isEmpty())){
                            ResultSet resultSet = (ResultSet) stmt.getObject(6);
                            List<StatewiseSgstMasterForBrokerageDto> list = new ArrayList<>();
                            StatewiseSgstMasterForBrokerageDto output = null;
                            while (resultSet.next()) {
                                output = new StatewiseSgstMasterForBrokerageDto();
                                output.setSchemeCode(resultSet.getString(1));
                                output.setSchemeName(resultSet.getString(2));
                                output.setSchemeStatus(resultSet.getString(3));
                                list.add(output);
                            }
                            resultMap = new HashMap<>();
                            resultMap.put("list", list);
                            responseDataDTO.setResult(resultMap);
                        }
                    }else{
                        responseDataDTO.setStatusCode("0");
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
    public ResponseDataDTO getModifyDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        Map<String, Object> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ST_WISE_SGST_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getSchemeCode());
                stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getStateCodeId());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (null != statewiseSgstMasterForBrokerageDto.getSchemeCode() && null != statewiseSgstMasterForBrokerageDto.getStateCodeId()) {
                        StatewiseSgstMasterForBrokerageDto output = new StatewiseSgstMasterForBrokerageDto();
                        output.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(5)));
                        output.setPrimaryKeyvalue(stmt.getString(6));
                        output.setCreatedBy(stmt.getString(7));
                        output.setCreatedDate(stmt.getString(8));
                        output.setModifiedBy(stmt.getString(9));
                        output.setModifiedDate(stmt.getString(10));
                        output.setVerifiedBy(stmt.getString(11));
                        output.setVerifiedDate(stmt.getString(12));
                        output.setPrevOprType(stmt.getString(13));
                        output.setVerifiedStatus(stmt.getString(14));
                        output.setDeleteRemarks(stmt.getString(15));
                        responseDataDTO.setResult(output);
                    }else if(null != statewiseSgstMasterForBrokerageDto.getSchemeCode() && (null == statewiseSgstMasterForBrokerageDto.getStateCodeId()|| statewiseSgstMasterForBrokerageDto.getStateCodeId().isEmpty())) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(5);
                        List<StatewiseSgstMasterForBrokerageDto> list = new ArrayList<>();
                        StatewiseSgstMasterForBrokerageDto output = null;
                        while (resultSet.next()) {
                            output = new StatewiseSgstMasterForBrokerageDto();
                            output.setStateCodeId(resultSet.getString(1));
                            output.setStateName(resultSet.getString(2));
                            list.add(output);
                        }
                        resultMap = new HashMap<>();
                        resultMap.put("list", list);
                        responseDataDTO.setResult(resultMap);
                    } else
                    {
                        ResultSet resultSet = (ResultSet) stmt.getObject(5);
                        List<StatewiseSgstMasterForBrokerageDto> list = new ArrayList<>();
                        StatewiseSgstMasterForBrokerageDto output = null;
                        while (resultSet.next()) {
                            output = new StatewiseSgstMasterForBrokerageDto();
                            output.setSchemeCode(resultSet.getString(1));
                            output.setSchemeName(resultSet.getString(2));
                            output.setSchemeStatus(resultSet.getString(3));
                            list.add(output);
                        }
                        resultMap = new HashMap<>();
                        resultMap.put("list", list);
                        responseDataDTO.setResult(resultMap);
                    }
                }else{
                    responseDataDTO.setStatusCode("0");
                }
            }else{
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
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
    public ResponseDataDTO getVerifyDeleteDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        Map<String, Object> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ST_WISE_SGST_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getSchemeCode());
                stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getStateCodeId());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (null != statewiseSgstMasterForBrokerageDto.getSchemeCode() && null != statewiseSgstMasterForBrokerageDto.getStateCodeId()) {
                        StatewiseSgstMasterForBrokerageDto output = new StatewiseSgstMasterForBrokerageDto();
                        output.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(5)));
                        output.setPrimaryKeyvalue(stmt.getString(6));
                        output.setCreatedBy(stmt.getString(7));
                        output.setCreatedDate(stmt.getString(8));
                        output.setModifiedBy(stmt.getString(9));
                        output.setModifiedDate(stmt.getString(10));
                        output.setVerifiedBy(stmt.getString(11));
                        output.setVerifiedDate(stmt.getString(12));
                        output.setPrevOprType(stmt.getString(13));
                        output.setVerifiedStatus(stmt.getString(14));
                        output.setDeleteRemarks(stmt.getString(15));
                        responseDataDTO.setResult(output);
                    }else if(null != statewiseSgstMasterForBrokerageDto.getSchemeCode() && (null == statewiseSgstMasterForBrokerageDto.getStateCodeId()|| statewiseSgstMasterForBrokerageDto.getStateCodeId().isEmpty())) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(5);
                        List<StatewiseSgstMasterForBrokerageDto> list = new ArrayList<>();
                        StatewiseSgstMasterForBrokerageDto output = null;
                        while (resultSet.next()) {
                            output = new StatewiseSgstMasterForBrokerageDto();
                            output.setStateCodeId(resultSet.getString(1));
                            output.setStateName(resultSet.getString(2));
                            list.add(output);
                        }
                        resultMap = new HashMap<>();
                        resultMap.put("list", list);
                        responseDataDTO.setResult(resultMap);
                    } else
                     {
                        ResultSet resultSet = (ResultSet) stmt.getObject(5);
                        List<StatewiseSgstMasterForBrokerageDto> list = new ArrayList<>();
                        StatewiseSgstMasterForBrokerageDto output = null;
                        while (resultSet.next()) {
                            output = new StatewiseSgstMasterForBrokerageDto();
                            output.setSchemeCode(resultSet.getString(1));
                            output.setSchemeName(resultSet.getString(2));
                            output.setSchemeStatus(resultSet.getString(3));
                            list.add(output);
                        }
                        resultMap = new HashMap<>();
                        resultMap.put("list", list);
                        responseDataDTO.setResult(resultMap);
                    }
                } else{
                    responseDataDTO.setStatusCode("0");
                }}else{
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
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
        public ResponseDataDTO inquiry(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto){
            Connection connection = null;
            CallableStatement stmt = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "";
            Map<String, Object> resultMap = null;
                try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ST_WISE_SGST_INQ.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getSchemeCode());
                    stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getStateCodeId());
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
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        if(null!=statewiseSgstMasterForBrokerageDto.getSchemeCode()&&null!=statewiseSgstMasterForBrokerageDto.getStateCodeId()){
                            StatewiseSgstMasterForBrokerageDto output = new StatewiseSgstMasterForBrokerageDto();
                            output.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(5)));
                            output.setPrimaryKeyvalue(stmt.getString(6));
                            output.setCreatedBy(stmt.getString(7));
                            output.setCreatedDate(stmt.getString(8));
                            output.setModifiedBy(stmt.getString(9));
                            output.setModifiedDate(stmt.getString(10));
                            output.setVerifiedBy(stmt.getString(11));
                            output.setVerifiedDate(stmt.getString(12));
                            output.setPrevOprType(stmt.getString(13));
                            output.setVerifiedStatus(stmt.getString(14));
                            output.setDeleteRemarks(stmt.getString(15));
                            responseDataDTO.setResult(output);
                        } else if(null != statewiseSgstMasterForBrokerageDto.getSchemeCode() && (null == statewiseSgstMasterForBrokerageDto.getStateCodeId()|| statewiseSgstMasterForBrokerageDto.getStateCodeId().isEmpty())) {
                            ResultSet resultSet = (ResultSet) stmt.getObject(5);
                            List<StatewiseSgstMasterForBrokerageDto> list = new ArrayList<>();
                            StatewiseSgstMasterForBrokerageDto output = null;
                            while (resultSet.next()) {
                                output = new StatewiseSgstMasterForBrokerageDto();
                                output.setStateCodeId(resultSet.getString(1));
                                output.setStateName(resultSet.getString(2));
                                list.add(output);
                            }
                            resultMap = new HashMap<>();
                            resultMap.put("list", list);
                            responseDataDTO.setResult(resultMap);
                        }else {
                            ResultSet resultSet = (ResultSet) stmt.getObject(5);
                            List<StatewiseSgstMasterForBrokerageDto> list = new ArrayList<>();
                            StatewiseSgstMasterForBrokerageDto output=null;
                            while (resultSet.next()) {
                                output = new StatewiseSgstMasterForBrokerageDto();
                                output.setSchemeCode(resultSet.getString(1));
                                output.setSchemeName(resultSet.getString(2));
                                output.setSchemeStatus(resultSet.getString(3));
                                list.add(output);
                            }
                            resultMap = new HashMap<>();
                            resultMap.put("list", list);
                            responseDataDTO.setResult(resultMap);
                        }
                    } else{
                        responseDataDTO.setStatusCode("0");
                    }
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
        public ResponseDataDTO verify(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
            Connection connection = null;
            CallableStatement stmt = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "";
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ST_WISE_SGST_VER.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getPrimaryKeyvalue());
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index-1));
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
    public ResponseDataDTO delete(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (statewiseSgstMasterForBrokerageDto.getDeleteRemarks() == null
                || statewiseSgstMasterForBrokerageDto.getDeleteRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ST_WISE_SGST_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getPrimaryKeyvalue());
                stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getDeleteRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            }
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
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO getStateDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        java.lang.String resultMsg = "";
        ResultSet resultSet = null;
        List<HashMap<String, String>> torchOut = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ST_WISE_SGST_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getSchemeCode());
                stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getStateCodeId());
                stmt.setString(index++, statewiseSgstMasterForBrokerageDto.getSgstRate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(6);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();
                            temp.put("state_code", resultSet.getString(1));
                            temp.put("state_name", resultSet.getString(2));
                            torchOut.add(temp);
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
        responseDataDTO.setResult(torchOut);
        return responseDataDTO;
    }
}