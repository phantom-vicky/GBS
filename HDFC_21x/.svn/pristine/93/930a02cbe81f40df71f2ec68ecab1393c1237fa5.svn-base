package com.isl.gbm.rbibond.dao.impl;


import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dao.RbiBondBlaDeathMarkingDAO;
import com.isl.gbm.rbibond.dto.RbiBondBlaDeathMarkingDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
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
public class RbiBondBlaDeathMarkingDAOImpl implements RbiBondBlaDeathMarkingDAO {

    private static final Logger log = LoggerFactory.getLogger(RbiBondBlaDeathMarkingDAOImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Override
    public ResponseDataDTO getAddPanNoDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        {

            Connection connection = null;
            CallableStatement stmt = null;
            String resultMsg = "";
            HashMap<String, String> resultMap = null;
            List<Map<String, String>> resultList = null;

            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_DOD_MAS_ADD_SEL.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanNo());
                    stmt.setString(index++, "N");
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getSchemeCode());
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);

                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {

                            resultList = new ArrayList<>();

                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("panNo", resultSet.getString(1));
                                resultMap.put("bondHolderName", resultSet.getString(2));
                                resultList.add(resultMap);
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

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage(resultMsg);
            responseDataDTO.setResult(resultList);
            return responseDataDTO;
        }
    }

    @Override
    public ResponseDataDTO getAddDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_DOD_MAS_ADD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanNo());
                stmt.setString(index++, "Y");
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getSchemeCode());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(7);

                    log.info("resultset add {}" , resultSet);
                    if (resultSet != null) {

                        resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("panNo", resultSet.getString(1));
                            resultMap.put("bondHolderName", resultSet.getString(2));
                            resultMap.put("schemeName", resultSet.getString(3));
                            resultMap.put("modeOfHolding", resultSet.getString(4));
                            resultMap.put("blaNo", resultSet.getString(5));
                            resultMap.put("holderType", resultSet.getString(6));
                            resultMap.put("schemeCode", resultSet.getString(7));
                            resultMap.put("bondSubscriptionId", resultSet.getString(8));
                            resultMap.put("latestSubIdDate", stmt.getString(6));
                            resultList.add(resultMap);
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

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(resultList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addCutomerDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(rbiBondBlaDeathMarkingDTO.getCertDate() ==null || rbiBondBlaDeathMarkingDTO.getCertDate().isEmpty()){
            responseDataDTO.setMessage("Date of Receipt of Inward Request is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(rbiBondBlaDeathMarkingDTO.getDodDate() ==null || rbiBondBlaDeathMarkingDTO.getDodDate().isEmpty()){
            responseDataDTO.setMessage("Deceased Date of Bond Holder is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(rbiBondBlaDeathMarkingDTO.getRemarks() ==null || rbiBondBlaDeathMarkingDTO.getRemarks().isEmpty()){
            responseDataDTO.setMessage("BLA Deceased Marking Remarks is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(rbiBondBlaDeathMarkingDTO.getMarkingDate() ==null || rbiBondBlaDeathMarkingDTO.getMarkingDate().isEmpty()){
            responseDataDTO.setMessage("BLA Deceased Marking Date is mandatory");
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_DOD_MAS_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanNo());
                stmt.setString(index++,"ADD");
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getBondHolderName());
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getDodDate());
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getCertDate());
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getMarkingDate());
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getRemarks());
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getSchemeCode());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    responseDataDTO.setResult(stmt.getString(11));

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
    public ResponseDataDTO getVerDelPanDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        {
            Connection connection = null;
            CallableStatement stmt = null;
            String resultMsg = "";
            HashMap<String, String> resultMap = null;
            List<Map<String, String>> resultList = null;

            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_DOD_MAS_VER_SEL.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanNo());
                    stmt.setString(index++, "N");
                    stmt.setString(index++, "");
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getSchemeCode());
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
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);

                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(18);
                        log.info("resultset ver sel {}" , resultSet);
                        if (resultSet != null) {

                            resultList = new ArrayList<>();

                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("panNo", resultSet.getString(1));
                                resultMap.put("bondHolderName", resultSet.getString(2));
                                resultMap.put("id", resultSet.getString(3));
                                resultList.add(resultMap);
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

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage(resultMsg);
            responseDataDTO.setResult(resultList);
            return responseDataDTO;

        }
    }

    @Override
    public ResponseDataDTO getVerifyDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        {
            log.info("getVerifyDetails");
            Connection connection = null;
            CallableStatement stmt = null;
            String resultMsg = "";
            HashMap<String, String> resultMap = null;
            List<Map<String, String>> resultList = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();

            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_DOD_MAS_VER_SEL.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanNo());
                    stmt.setString(index++, "Y");
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanId());
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getSchemeCode());
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
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                    resultMsg = stmt.getString(index);
                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                        ResultSet  resultSet = (ResultSet) stmt.getObject(19);

                        rbiBondBlaDeathMarkingDTO.setCreatedBy(stmt.getString(7));
                        rbiBondBlaDeathMarkingDTO.setCreatedDate(stmt.getString(8));
                        rbiBondBlaDeathMarkingDTO.setVerifiedBy(stmt.getString(9));
                        rbiBondBlaDeathMarkingDTO.setVerifiedDate(stmt.getString(10));
                        rbiBondBlaDeathMarkingDTO.setVerifiedStatus(stmt.getString(11));
                        rbiBondBlaDeathMarkingDTO.setBondHolderName(stmt.getString(12));
                        rbiBondBlaDeathMarkingDTO.setPanNumber(stmt.getString(13));
                        rbiBondBlaDeathMarkingDTO.setDodDate(stmt.getString(14));
                        rbiBondBlaDeathMarkingDTO.setCertDate(stmt.getString(15));
                        rbiBondBlaDeathMarkingDTO.setMarkingDate(stmt.getString(16));
                        rbiBondBlaDeathMarkingDTO.setRemarks(stmt.getString(17));

                        if (resultSet!= null) {

                            resultList =new ArrayList<>();

                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("schemeName", resultSet.getString(1));
                                resultMap.put("modeOfHolding", resultSet.getString(2));
                                resultMap.put("blaNo", resultSet.getString(3));
                                resultMap.put("holderType", resultSet.getString(4));
                                resultMap.put("panNo", resultSet.getString(5));
                                resultMap.put("bondHolderName", resultSet.getString(6));
                                resultMap.put("bondSubscriptionId", resultSet.getString(7));
                                resultList.add(resultMap);
                            }

                            rbiBondBlaDeathMarkingDTO.setHolderDetails(resultList);

                        }

                    }


                }

                else {
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }

            responseDataDTO.setMessage(resultMsg);
            responseDataDTO.setResult(rbiBondBlaDeathMarkingDTO);
            return responseDataDTO;

        }
    }

    @Override
    public ResponseDataDTO verifyCutomerDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_DOD_MAS_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    responseDataDTO.setResult(stmt.getString(4));
                }
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
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

    @Override
    public ResponseDataDTO getDeleteDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        {
            log.info("getDeleteDetails");
            Connection connection = null;
            CallableStatement stmt = null;
            String resultMsg = "";
            HashMap<String, String> resultMap = null;
            List<Map<String, String>> resultList = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();

            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_DOD_MAS_VER_SEL.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanNo());
                    stmt.setString(index++, "Y");
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanId());
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getSchemeCode());
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
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                    resultMsg = stmt.getString(index);
                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                        ResultSet  resultSet = (ResultSet) stmt.getObject(19);

                        rbiBondBlaDeathMarkingDTO.setCreatedBy(stmt.getString(7));
                        rbiBondBlaDeathMarkingDTO.setCreatedDate(stmt.getString(8));
                        rbiBondBlaDeathMarkingDTO.setVerifiedBy(stmt.getString(9));
                        rbiBondBlaDeathMarkingDTO.setVerifiedDate(stmt.getString(10));
                        rbiBondBlaDeathMarkingDTO.setVerifiedStatus(stmt.getString(11));
                        rbiBondBlaDeathMarkingDTO.setBondHolderName(stmt.getString(12));
                        rbiBondBlaDeathMarkingDTO.setPanNumber(stmt.getString(13));
                        rbiBondBlaDeathMarkingDTO.setDodDate(stmt.getString(14));
                        rbiBondBlaDeathMarkingDTO.setCertDate(stmt.getString(15));
                        rbiBondBlaDeathMarkingDTO.setMarkingDate(stmt.getString(16));
                        rbiBondBlaDeathMarkingDTO.setRemarks(stmt.getString(17));

                        if(resultSet!=null) {
                            resultList =new ArrayList<>();
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("schemeName", resultSet.getString(1));
                                resultMap.put("modeOfHolding", resultSet.getString(2));
                                resultMap.put("blaNo", resultSet.getString(3));
                                resultMap.put("holderType", resultSet.getString(4));
                                resultList.add(resultMap);
                            }
                            rbiBondBlaDeathMarkingDTO.setHolderDetails(resultList);
                        }


                    }

                }

                else {
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }


            responseDataDTO.setMessage(resultMsg);
            responseDataDTO.setResult(rbiBondBlaDeathMarkingDTO);
            return responseDataDTO;

        }
    }

    @Override
    public ResponseDataDTO deleteCutomerDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondBlaDeathMarkingDTO.getRejectRemarks() == null
                || rbiBondBlaDeathMarkingDTO.getRejectRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(rbiBondBlaDeathMarkingDTO.getRejectRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_DOD_MAS_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanId());
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getRejectRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    responseDataDTO.setResult(stmt.getString(5));
                }
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
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

    @Override
    public ResponseDataDTO getInquiryPanDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_DOD_MAS_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanNo());
                stmt.setString(index++,"");
                stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getSchemeCode());
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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE, stmt.getString(index-1));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet  resultSet = (ResultSet) stmt.getObject(17);
                    if (resultSet!= null) {
                        resultList = new ArrayList<>();
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("panNo", resultSet.getString(1));
                            resultMap.put("bondHolderName", resultSet.getString(2));
                            resultList.add(resultMap);
                        }
                    }

                }
            }

            else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }


        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(resultList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getInquiryDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        {
            log.info("getInquiryDetails");
            Connection connection = null;
            CallableStatement stmt = null;
            String resultMsg = "";
            HashMap<String, String> resultMap = null;
            List<Map<String, String>> resultList = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();

            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_DOD_MAS_INQ.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getPanNo());
                    stmt.setString(index++, "Y");
                    stmt.setString(index++, rbiBondBlaDeathMarkingDTO.getSchemeCode());
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
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                        rbiBondBlaDeathMarkingDTO.setCreatedBy(stmt.getString(6));
                        rbiBondBlaDeathMarkingDTO.setCreatedDate(stmt.getString(7));
                        rbiBondBlaDeathMarkingDTO.setVerifiedBy(stmt.getString(8));
                        rbiBondBlaDeathMarkingDTO.setVerifiedDate(stmt.getString(9));
                        rbiBondBlaDeathMarkingDTO.setVerifiedStatus(stmt.getString(10));
                        rbiBondBlaDeathMarkingDTO.setPanNumber(stmt.getString(11));
                        rbiBondBlaDeathMarkingDTO.setBondHolderName(stmt.getString(12));
                        rbiBondBlaDeathMarkingDTO.setDodDate(stmt.getString(13));
                        rbiBondBlaDeathMarkingDTO.setCertDate(stmt.getString(14));
                        rbiBondBlaDeathMarkingDTO.setMarkingDate(stmt.getString(15));
                        rbiBondBlaDeathMarkingDTO.setRemarks(stmt.getString(16));



                        ResultSet  resultSet = (ResultSet) stmt.getObject(18);



                        if (resultSet!= null) {

                            resultList =new ArrayList<>();

                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                log.info(resultSet.getString(1));
                                resultMap.put("schemeName", resultSet.getString(1));
                                resultMap.put("modeOfHolding", resultSet.getString(2));
                                resultMap.put("blaNo", resultSet.getString(3));
                                resultMap.put("holderType", resultSet.getString(4));
                                resultMap.put("panNo", resultSet.getString(5));
                                resultMap.put("bondHolderName", resultSet.getString(6));
                                resultMap.put("bondSubscriptionId", resultSet.getString(7));
                                resultList.add(resultMap);
                            }





                            rbiBondBlaDeathMarkingDTO.setHolderDetails(resultList);

                        }

                    }





                }

                else {
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }


            responseDataDTO.setMessage(resultMsg);
            responseDataDTO.setResult(rbiBondBlaDeathMarkingDTO);
            return responseDataDTO;

        }
    }


}
