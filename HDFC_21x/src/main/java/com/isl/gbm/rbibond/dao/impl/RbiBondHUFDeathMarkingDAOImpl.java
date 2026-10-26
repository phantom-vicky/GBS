package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dao.RbiBondHUFDeathMarkingDAO;
import com.isl.gbm.rbibond.dto.RbiBondHUFDeathMarkingDTO;
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
public class RbiBondHUFDeathMarkingDAOImpl implements RbiBondHUFDeathMarkingDAO {

    private static final Logger log = LoggerFactory.getLogger(RbiBondHUFDeathMarkingDAOImpl.class);
    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;
    @Override
    public ResponseDataDTO getAddPanNoDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_HUF_DOD_MAS_ADD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getPanNo());
                stmt.setString(index++, "N");
                //stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(5);
                    log.info("ResultSet : {}", resultSet);

                    if (resultSet != null) {
                        resultList = new ArrayList<>();

                        while (resultSet.next()) {

                            log.info("PAN : {}", resultSet.getString(1));
                            log.info("NAME : {}", resultSet.getString(2));

                            resultMap = new HashMap<>();
                            resultMap.put("panNo", resultSet.getString(1));
                            resultMap.put("bondHolderName", resultSet.getString(2));
                            resultMap.put("POWER_ATTORNEY_KARTA_NAME", resultSet.getString(3));
                            resultList.add(resultMap);
                        }

                        log.info("Result List : {}", resultList);
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
    public ResponseDataDTO getCoParcenerDtls(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_HUF_DOD_MAS_COPARCENER_ADD_SEL.getProcName());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getPanNo());
                stmt.setString(index++, "A");
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(3);
                    if (resultSet != null) {

                        resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("coParcenerName", resultSet.getString(1));
                            resultMap.put("relationship", resultSet.getString(2));
                            resultMap.put("dob", resultSet.getString(3));
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
    public ResponseDataDTO addCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(rbiBondHUFDeathMarkingDTO.getCertDate() ==null || rbiBondHUFDeathMarkingDTO.getCertDate().isEmpty()){
            responseDataDTO.setMessage("Date of Receipt of Inward Request is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(rbiBondHUFDeathMarkingDTO.getDodDate() ==null || rbiBondHUFDeathMarkingDTO.getDodDate().isEmpty()){
            responseDataDTO.setMessage("Deceased Date of Bond Holder is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(rbiBondHUFDeathMarkingDTO.getRemarks() ==null || rbiBondHUFDeathMarkingDTO.getRemarks().isEmpty()){
            responseDataDTO.setMessage("HUF Deceased Marking Remarks is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(rbiBondHUFDeathMarkingDTO.getMarkingDate() ==null || rbiBondHUFDeathMarkingDTO.getMarkingDate().isEmpty()){
            responseDataDTO.setMessage("HUF Deceased Marking Date is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if(rbiBondHUFDeathMarkingDTO.getNewKartaName() ==null || rbiBondHUFDeathMarkingDTO.getNewKartaName().isEmpty()){
            responseDataDTO.setMessage(" Co-Parcener Name is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(rbiBondHUFDeathMarkingDTO.getRelationshipWithKarta() ==null || rbiBondHUFDeathMarkingDTO.getRelationshipWithKarta().isEmpty()){
            responseDataDTO.setMessage("Co-Parcener Relationship is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(rbiBondHUFDeathMarkingDTO.getCoParcenerDob() ==null || rbiBondHUFDeathMarkingDTO.getCoParcenerDob().isEmpty()){
            responseDataDTO.setMessage("Co-Parcener DOB is mandatory");
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_HUF_DOD_MAS_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getPanNo());
                stmt.setString(index++,"ADD");
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getBondHolderName());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getNewKartaName());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getCoParcenerDob());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getRelationshipWithKarta());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getDodDate());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getCertDate());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getMarkingDate());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getRemarks());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getSchemeCode());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    responseDataDTO.setResult(stmt.getString(14));

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
    public ResponseDataDTO getVerDelPanDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
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
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_HUF_DOD_MAS_VER.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getPanNo());
                    stmt.setString(index++, "N");
                    stmt.setString(index++, "");
                    stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getSchemeCode());
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

                        ResultSet resultSet = (ResultSet) stmt.getObject(21);
                        log.info("resultset ver sel {}" , resultSet);
                        if (resultSet != null) {

                            resultList = new ArrayList<>();

                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("panNo", resultSet.getString(1));
                                resultMap.put("bondHolderName", resultSet.getString(2));
                                resultMap.put("POWER_ATTORNEY_KARTA_NAME", resultSet.getString(3));
                                resultMap.put("id", resultSet.getString(4));

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
    public ResponseDataDTO getVerifyDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
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
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_HUF_DOD_MAS_VER.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getPanNo());
                    stmt.setString(index++, "Y");
                    stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getPanId());
                    stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getSchemeCode());
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

                        ResultSet  resultSet = (ResultSet) stmt.getObject(22);
                        rbiBondHUFDeathMarkingDTO.setCreatedBy(stmt.getString(7));
                        rbiBondHUFDeathMarkingDTO.setCreatedDate(stmt.getString(8));
                        rbiBondHUFDeathMarkingDTO.setVerifiedBy(stmt.getString(9));
                        rbiBondHUFDeathMarkingDTO.setVerifiedDate(stmt.getString(10));
                        rbiBondHUFDeathMarkingDTO.setVerifiedStatus(stmt.getString(11));
                        rbiBondHUFDeathMarkingDTO.setNewKartaName(stmt.getString(12));
                        rbiBondHUFDeathMarkingDTO.setBondHolderName(stmt.getString(13));
                        rbiBondHUFDeathMarkingDTO.setDodDate(stmt.getString(14));
                        rbiBondHUFDeathMarkingDTO.setRelationshipWithKarta(stmt.getString(15));
                        rbiBondHUFDeathMarkingDTO.setKcrnNo(stmt.getString(16));
                                rbiBondHUFDeathMarkingDTO.setCoParcenerDob(stmt.getString(17));
                        rbiBondHUFDeathMarkingDTO.setMarkingDate(stmt.getString(18));
                        rbiBondHUFDeathMarkingDTO.setRemarks(stmt.getString(19));
                        rbiBondHUFDeathMarkingDTO.setCertDate(stmt.getString(20));



                        if (resultSet!= null) {

                            resultList =new ArrayList<>();

                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("schemeName", resultSet.getString(1));
                                resultMap.put("blaNo", resultSet.getString(2));
                                resultMap.put("bondSubscriptionId", resultSet.getString(3));
                                resultList.add(resultMap);
                            }

                            rbiBondHUFDeathMarkingDTO.setHolderDetails(resultList);

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
            responseDataDTO.setResult(rbiBondHUFDeathMarkingDTO);
            return responseDataDTO;

        }
    }
    @Override
    public ResponseDataDTO verifyCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_HUF_DOD_MAS_VER_SUB.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getPanId());
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
    public ResponseDataDTO deleteCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondHUFDeathMarkingDTO.getRejectRemarks() == null
                || rbiBondHUFDeathMarkingDTO.getRejectRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(rbiBondHUFDeathMarkingDTO.getRejectRemarks())) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_HUF_DOD_MAS_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getPanId());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getRejectRemarks());
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
    public ResponseDataDTO getInquiryPanDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_HUF_DOD_MAS_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getPanNo());
                stmt.setString(index++,"");
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getSchemeCode());
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
                    ResultSet  resultSet = (ResultSet) stmt.getObject(20);
                    if (resultSet!= null) {
                        resultList = new ArrayList<>();
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("panNo", resultSet.getString(1));
                            resultMap.put("bondHolderName", resultSet.getString(2));
                            resultMap.put("POWER_ATTORNEY_KARTA_NAME", resultSet.getString(3));
                         //   resultMap.put("id", resultSet.getString(4));
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
    public ResponseDataDTO getInquiryDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
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
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_HUF_DOD_MAS_INQ.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getPanNo());
                    stmt.setString(index++, "Y");
                    stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getSchemeCode());
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
                        rbiBondHUFDeathMarkingDTO.setCreatedBy(stmt.getString(6));
                        rbiBondHUFDeathMarkingDTO.setCreatedDate(stmt.getString(7));
                        rbiBondHUFDeathMarkingDTO.setVerifiedBy(stmt.getString(8));
                        rbiBondHUFDeathMarkingDTO.setVerifiedDate(stmt.getString(9));
                        rbiBondHUFDeathMarkingDTO.setVerifiedStatus(stmt.getString(10));
                        rbiBondHUFDeathMarkingDTO.setNewKartaName(stmt.getString(11));
                        rbiBondHUFDeathMarkingDTO.setBondHolderName(stmt.getString(12));
                        rbiBondHUFDeathMarkingDTO.setDodDate(stmt.getString(13));
                        rbiBondHUFDeathMarkingDTO.setRelationshipWithKarta(stmt.getString(14));
                        rbiBondHUFDeathMarkingDTO.setKcrnNo(stmt.getString(15));
                        rbiBondHUFDeathMarkingDTO.setCoParcenerDob(stmt.getString(16));
                        rbiBondHUFDeathMarkingDTO.setMarkingDate(stmt.getString(17));
                        rbiBondHUFDeathMarkingDTO.setRemarks(stmt.getString(18));
                        rbiBondHUFDeathMarkingDTO.setCertDate(stmt.getString(19));
                        ResultSet  resultSet = (ResultSet) stmt.getObject(21);
                        if (resultSet!= null) {
                            resultList =new ArrayList<>();
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                log.info(resultSet.getString(1));
                                resultMap.put("schemeName", resultSet.getString(1));
                                resultMap.put("blaNo", resultSet.getString(2));
                                resultMap.put("bondSubscriptionId", resultSet.getString(3));
                                resultList.add(resultMap);
                            }
  rbiBondHUFDeathMarkingDTO.setHolderDetails(resultList);

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
            responseDataDTO.setResult(rbiBondHUFDeathMarkingDTO);
            return responseDataDTO;

        }
    }
    @Override
    public ResponseDataDTO getAddDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_HUF_DOD_MAS_ADD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondHUFDeathMarkingDTO.getPanNo());
                stmt.setString(index++, "Y");
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(5);

                    log.info("resultset add {}" , resultSet);
                    if (resultSet != null) {

                        resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("schemeName", resultSet.getString(1));
                            resultMap.put("blaNo", resultSet.getString(2));
                            resultMap.put("bondSubscriptionId", resultSet.getString(3));
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
