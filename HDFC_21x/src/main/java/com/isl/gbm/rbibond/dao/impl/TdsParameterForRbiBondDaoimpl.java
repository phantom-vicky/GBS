package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dao.TdsParameterForRbiBondDao;
import com.isl.gbm.rbibond.dto.RbiBondTdsParameterDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.internal.OracleTypes;
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


@Repository
public class TdsParameterForRbiBondDaoimpl implements TdsParameterForRbiBondDao {

    private static final Logger log = LoggerFactory.getLogger(TdsParameterForRbiBondDaoimpl.class);
    private static final String TDS_PARAM_CODE_REGEX = "^[a-zA-Z0-9]{1,10}$";
    private static final String TDS_PARAM_CODE_DESC_REGEX =
            "^[a-zA-Z0-9][a-zA-Z0-9 ]{0,29}$";
    private static final String TDS_ANNUAL_INTEREST_REGEX = "^[0-9]{1,6}$";
    private static final String TDS_RATE_PERCENT_REGEX =
            "^(100(\\.0{1,2})?|([1-9]?\\d)(\\.\\d{1,2})?|0(\\.\\d{1,2})?)$";
    private static final String CESS_PERCENT_REGEX =
            "^([0-9]{1,2})(\\.\\d{1,2})?$";
    private static final String LIMIT_AMOUNT_REGEX = "^[0-9]{1,7}$";

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;


    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Override
    public ResponseDataDTO add(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String tdsParamCode = rbiBondTdsParameterDTO.getTdsParamCode();
        if (tdsParamCode == null
                || tdsParamCode.trim().isEmpty()
                || !tdsParamCode.trim().matches(TDS_PARAM_CODE_REGEX)) {

            responseDataDTO.setMessage(
                    "TDS Param Code is mandatory and must be alphanumeric without spaces (max 10 characters)"
            );
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String tdsParamCodeDesc = rbiBondTdsParameterDTO.getTdsParamCodeDesc();
        if (tdsParamCodeDesc == null
                || tdsParamCodeDesc.trim().isEmpty()
                || !tdsParamCodeDesc.matches(TDS_PARAM_CODE_DESC_REGEX)) {

            responseDataDTO.setMessage(
                    "TDS Param Code Description is mandatory, must start with an alphanumeric character, " +
                            "and can contain only alphanumeric characters and spaces (max 30 characters)"
            );
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String tdsAnnualInterest = rbiBondTdsParameterDTO.getExceptionForSeniorCitizen();
        if (tdsAnnualInterest == null
                || tdsAnnualInterest.trim().isEmpty()
                || !tdsAnnualInterest.matches(TDS_ANNUAL_INTEREST_REGEX)) {

            responseDataDTO.setMessage(
                    "TDS Exemption on Annual Interest is mandatory and must be a numeric value between 0 and 999999"
            );
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String tdsRatePan = rbiBondTdsParameterDTO.getTdsRateWithPan();
        if (tdsRatePan == null
                || tdsRatePan.trim().isEmpty()
                || !tdsRatePan.trim().matches(TDS_RATE_PERCENT_REGEX)) {

            responseDataDTO.setMessage(
                    "TDS Rate (PAN Available) is mandatory and must be a valid percentage between 0 and 100 with up to 2 decimal places"
            );
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String tdsRateNoPan = rbiBondTdsParameterDTO.getTdsRateWithPan();
        if (tdsRateNoPan == null
                || tdsRateNoPan.trim().isEmpty()
                || !tdsRateNoPan.trim().matches(TDS_RATE_PERCENT_REGEX)) {

            responseDataDTO.setMessage(
                    "TDS Rate (PAN not available) is mandatory and must be a valid percentage between 0 and 100 with up to 2 decimal places"
            );
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String educationCess = rbiBondTdsParameterDTO.getEducationCess();

        if (educationCess == null
                || educationCess.trim().isEmpty()
                || !educationCess.trim().matches(CESS_PERCENT_REGEX)) {

            responseDataDTO.setMessage(
                    "Education Cess is mandatory and must be a valid percentage between 0 and 99.99"
            );
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String otherCess = rbiBondTdsParameterDTO.getOtherCess();

        if (otherCess == null
                || otherCess.trim().isEmpty()
                || !otherCess.trim().matches(CESS_PERCENT_REGEX)) {

            responseDataDTO.setMessage(
                    "Other Cess is mandatory and must be a valid percentage between 0 and 99.99"
            );
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String fifteenGLimit = rbiBondTdsParameterDTO.getFifteenGlimit();

        if (fifteenGLimit == null
                || fifteenGLimit.trim().isEmpty()
                || !fifteenGLimit.matches(LIMIT_AMOUNT_REGEX)) {

            responseDataDTO.setMessage(
                    "15G Limit is mandatory and must be a numeric value up to 7 digits"
            );
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String fifteenHLimit = rbiBondTdsParameterDTO.getFifteenHlimit();

        if (fifteenHLimit == null
                || fifteenHLimit.trim().isEmpty()
                || !fifteenHLimit.matches(LIMIT_AMOUNT_REGEX)) {

            responseDataDTO.setMessage(
                    "15H Limit is mandatory and must be a numeric value up to 7 digits"
            );
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TDS_PARAM_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getModuleId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getTdsParamCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getTdsParamCodeDesc());
                stmt.setString(index++, rbiBondTdsParameterDTO.getEffFromDate());
                stmt.setString(index++, rbiBondTdsParameterDTO.getExceptionForSeniorCitizen());
                stmt.setString(index++, rbiBondTdsParameterDTO.getTdsRateWithPan());
                stmt.setString(index++, rbiBondTdsParameterDTO.getTdsRateWithoutPan());
                stmt.setString(index++, rbiBondTdsParameterDTO.getEducationCess());
                stmt.setString(index++, rbiBondTdsParameterDTO.getOtherCess());
                stmt.setString(index++, rbiBondTdsParameterDTO.getRemark());
                stmt.setString(index++, rbiBondTdsParameterDTO.getFifteenGlimit());
                stmt.setString(index++, rbiBondTdsParameterDTO.getFifteenHlimit());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}:{}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO addValidation(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(rbiBondTdsParameterDTO.getModuleId() != null) {
            String tdsParamCode = rbiBondTdsParameterDTO.getTdsParamCode();
            if (tdsParamCode == null
                    || tdsParamCode.trim().isEmpty()
                    || !tdsParamCode.trim().matches(TDS_PARAM_CODE_REGEX)) {

                responseDataDTO.setMessage(
                        "TDS Param Code is mandatory and must be alphanumeric without spaces (max 10 characters)"
                );
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String tdsParamCodeDesc = rbiBondTdsParameterDTO.getTdsParamCodeDesc();

            if (tdsParamCodeDesc == null
                    || tdsParamCodeDesc.trim().isEmpty()
                    || !tdsParamCodeDesc.matches(TDS_PARAM_CODE_DESC_REGEX)) {

                responseDataDTO.setMessage(
                        "TDS Param Code Description is mandatory, must start with an alphanumeric character, " +
                                "and can contain only alphanumeric characters and spaces (max 30 characters)"
                );
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TDS_PARAM_ADD_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getModuleId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getTdsParamCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}:{}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (rbiBondTdsParameterDTO.getModuleId()==null) {
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(5);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null && !StringUtils.equalsIgnoreCase(resultSet.getString(1), "NDF")) {
                                    temp = new HashMap<>();
                                    temp.put("schemeCode", resultSet.getString(1));
                                    temp.put("schemeName", resultSet.getString(2));
                                    temp.put("schemeStatus", resultSet.getString(3));
                                    tdsCode.add(temp);
                                }
                            }
                            rbiBondTdsParameterDTO.setTdsCode(tdsCode);
                        }
                    }
                    responseDataDTO.setResult(rbiBondTdsParameterDTO);
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
    public ResponseDataDTO inquiryDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TDS_PARAM_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getModuleId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getTdsParamCode());
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
                log.info("{}:{}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (rbiBondTdsParameterDTO.getModuleId()==null) {
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(16);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null && !StringUtils.equalsIgnoreCase(resultSet.getString(1), "NDF")) {
                                    temp = new HashMap<>();
                                    temp.put("schemeCode", resultSet.getString(1));
                                    temp.put("schemeName", resultSet.getString(2));
                                    temp.put("schemeStatus", resultSet.getString(3));
                                    tdsCode.add(temp);
                                }
                            }
                            rbiBondTdsParameterDTO.setTdsCode(tdsCode);
                        }
                    }
                    else if (rbiBondTdsParameterDTO.getTdsParamCode() == null && rbiBondTdsParameterDTO.getVersionNo() == null) {
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(16);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null && !StringUtils.equalsIgnoreCase(resultSet.getString(1), "NDF")) {
                                    temp = new HashMap<>();
                                    temp.put("tdsCode", resultSet.getString(1));
                                    temp.put("tdsDesc", resultSet.getString(2));
                                    tdsCode.add(temp);
                                }
                            }
                            rbiBondTdsParameterDTO.setTdsCode(tdsCode);
                        }
                    }
                    else if (rbiBondTdsParameterDTO.getTdsParamCode() != null && rbiBondTdsParameterDTO.getVersionNo() == null) {
                        List<HashMap<String, String>> versionList = null;
                        resultSet = (ResultSet) stmt.getObject(16);
                        if (resultSet != null) {
                            HashMap<String, String> map = null;
                            versionList = new ArrayList<>();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null && !StringUtils.equalsIgnoreCase(resultSet.getString(1), "NDF")) {
                                    map = new HashMap<>();
                                    map.put("versionNo", resultSet.getString(1));
                                    map.put("effectiveFromDate", resultSet.getString(2));
                                    map.put("verifiedStatus", resultSet.getString(3));
                                    versionList.add(map);
                                }
                            }
                            rbiBondTdsParameterDTO.setVersionNoList(versionList);
                        }
                    }
                    else {
                        rbiBondTdsParameterDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(16)));
//                        rbiBondTdsParameterDTO.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(15)));
                        rbiBondTdsParameterDTO.setPkColumnValue(stmt.getString(6));
                        rbiBondTdsParameterDTO.setCreatedBy(stmt.getString(7));
                        rbiBondTdsParameterDTO.setCreatedDate(stmt.getString(8));
                        rbiBondTdsParameterDTO.setModifiedBy(stmt.getString(9));
                        rbiBondTdsParameterDTO.setModifiedDate(stmt.getString(10));
                        rbiBondTdsParameterDTO.setVerifiedBy(stmt.getString(11));
                        rbiBondTdsParameterDTO.setVerifiedDate(stmt.getString(12));
                        rbiBondTdsParameterDTO.setPrevOperType(stmt.getString(13));
                        rbiBondTdsParameterDTO.setVerifiedStatus(stmt.getString(14));
                        rbiBondTdsParameterDTO.setRejectionRemarks(stmt.getString(15));
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
        responseDataDTO.setResult(rbiBondTdsParameterDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyDeleteDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TDS_PARAM_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getModuleId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getTdsParamCode());
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
                stmt.registerOutParameter(index++, oracle.jdbc.internal.OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}:{}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (rbiBondTdsParameterDTO.getModuleId()==null) {
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(16);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null && !StringUtils.equalsIgnoreCase(resultSet.getString(1), "NDF")) {
                                    temp = new HashMap<>();
                                    temp.put("schemeCode", resultSet.getString(1));
                                    temp.put("schemeName", resultSet.getString(2));
                                    temp.put("schemeStatus", resultSet.getString(3));
                                    tdsCode.add(temp);
                                }
                            }
                            rbiBondTdsParameterDTO.setTdsCode(tdsCode);
                        }
                    }
                    else if (rbiBondTdsParameterDTO.getTdsParamCode() == null) {
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(16);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null && !StringUtils.equalsIgnoreCase(resultSet.getString(1), "NDF")) {
                                    temp = new HashMap<>();
                                    temp.put("tdsCode", resultSet.getString(1));
                                    temp.put("tdsDesc", resultSet.getString(2));
                                    temp.put("versionNo", resultSet.getString(3));
                                    tdsCode.add(temp);
                                }
                            }
                            rbiBondTdsParameterDTO.setTdsCode(tdsCode);
                        }
                    }
                    else {
                        rbiBondTdsParameterDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(16)));
//                        rbiBondTdsParameterDTO.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(15)));
                        rbiBondTdsParameterDTO.setPkColumnValue(stmt.getString(6));
                        rbiBondTdsParameterDTO.setCreatedBy(stmt.getString(7));
                        rbiBondTdsParameterDTO.setCreatedDate(stmt.getString(8));
                        rbiBondTdsParameterDTO.setModifiedBy(stmt.getString(9));
                        rbiBondTdsParameterDTO.setModifiedDate(stmt.getString(10));
                        rbiBondTdsParameterDTO.setVerifiedBy(stmt.getString(11));
                        rbiBondTdsParameterDTO.setVerifiedDate(stmt.getString(12));
                        rbiBondTdsParameterDTO.setPrevOperType(stmt.getString(13));
                        rbiBondTdsParameterDTO.setVerifiedStatus(stmt.getString(14));
                        rbiBondTdsParameterDTO.setRejectionRemarks(stmt.getString(15));
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
        responseDataDTO.setResult(rbiBondTdsParameterDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TDS_PARAM_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}:{}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO delete(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondTdsParameterDTO.getRejectionRemarks() == null
                || rbiBondTdsParameterDTO.getRejectionRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(rbiBondTdsParameterDTO.getRejectionRemarks())) {
            responseDataDTO.setMessage("Invalid Rejection Remarks");
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TDS_PARAM_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}:{}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO modifyDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TDS_PARAM_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getModuleId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getTdsParamCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getEffFromDate());
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
                stmt.registerOutParameter(index++, oracle.jdbc.internal.OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}:{}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (rbiBondTdsParameterDTO.getModuleId()==null) {
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(16);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null && !StringUtils.equalsIgnoreCase(resultSet.getString(1), "NDF")) {
                                    temp = new HashMap<>();
                                    temp.put("schemeCode", resultSet.getString(1));
                                    temp.put("schemeName", resultSet.getString(2));
                                    temp.put("schemeStatus", resultSet.getString(3));
                                    tdsCode.add(temp);
                                }
                            }
                            rbiBondTdsParameterDTO.setTdsCode(tdsCode);
                        }
                    }
                    else if (rbiBondTdsParameterDTO.getTdsParamCode() == null && rbiBondTdsParameterDTO.getEffFromDate()==null) {
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(16);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null && !StringUtils.equalsIgnoreCase(resultSet.getString(1), "NDF")) {
                                    temp = new HashMap<>();
                                    temp.put("tdsCode", resultSet.getString(1));
                                    temp.put("tdsDesc", resultSet.getString(2));
                                    temp.put("versionNo", resultSet.getString(3));
                                    temp.put("effFromDate", resultSet.getString(4));
                                    tdsCode.add(temp);
                                }
                            }
                            rbiBondTdsParameterDTO.setTdsCode(tdsCode);
                        }
                    }
                    else {
                        rbiBondTdsParameterDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(16)));
                        rbiBondTdsParameterDTO.setPkColumnValue(stmt.getString(6));
                        rbiBondTdsParameterDTO.setCreatedBy(stmt.getString(7));
                        rbiBondTdsParameterDTO.setCreatedDate(stmt.getString(8));
                        rbiBondTdsParameterDTO.setModifiedBy(stmt.getString(9));
                        rbiBondTdsParameterDTO.setModifiedDate(stmt.getString(10));
                        rbiBondTdsParameterDTO.setVerifiedBy(stmt.getString(11));
                        rbiBondTdsParameterDTO.setVerifiedDate(stmt.getString(12));
                        rbiBondTdsParameterDTO.setPrevOperType(stmt.getString(13));
                        rbiBondTdsParameterDTO.setVerifiedStatus(stmt.getString(14));
                        rbiBondTdsParameterDTO.setRejectionRemarks(stmt.getString(15));
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
        responseDataDTO.setResult(rbiBondTdsParameterDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySubmit(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        rbiBondTdsParameterDTO.setColumnValues(this.removeDuplicateValue(rbiBondTdsParameterDTO.getColumnValues()));
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TDS_PARAM_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getId());
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, rbiBondTdsParameterDTO.getColumnValues()));
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


    public List<ColumnValueDTO> removeDuplicateValue(List<ColumnValueDTO> list){

        List<ColumnValueDTO> resultList = new ArrayList<>();
        for(ColumnValueDTO item : list){
            if(!resultList.contains(item)){
                resultList.add(item);
            }
        }

        return resultList;
    }




}
