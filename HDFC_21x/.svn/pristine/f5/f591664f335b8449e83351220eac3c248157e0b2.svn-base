package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.InterstRateCodeMaintenanceDAO;
import com.isl.gbm.rbibond.dto.RateCodeMaintenanceDTO;
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
import java.util.List;


@Repository
public class InterstRateCodeMaintenanceDAOimpl implements InterstRateCodeMaintenanceDAO {

    private static final Logger log = LoggerFactory.getLogger(InterstRateCodeMaintenanceDAOimpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO add(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_INT_RATE_CODE_MAIN_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rateCodeMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rateCodeMaintenanceDTO.getRateCodeMaintenance());
                stmt.setString(index++, rateCodeMaintenanceDTO.getRateCodeMaintenanceDescription());
//                stmt.setString(index++, rateCodeMaintenanceDTO.getVersionNo());
                stmt.setString(index++, rateCodeMaintenanceDTO.getEffFromDate());
                stmt.setString(index++, rateCodeMaintenanceDTO.getInterestRateNatSavCertiIn());
                stmt.setString(index++, rateCodeMaintenanceDTO.getSpreadRate());
                stmt.setString(index++, rateCodeMaintenanceDTO.getCouponCode());
                stmt.setString(index++, rateCodeMaintenanceDTO.getRemarks());
                stmt.setString(index++, rateCodeMaintenanceDTO.getFixedFloatingInterest());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                if (rateCodeMaintenanceDTO.getSchemeCode() == null) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(12);
                    if (resultSet != null) {
                        List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                        RateCodeMaintenanceDTO item = null;
                        while (resultSet.next()) {
                            item = new RateCodeMaintenanceDTO();
                            item.setSchemeCode(resultSet.getString(1));
                            item.setSubCodeDescription(resultSet.getString(2));

                            list.add(item);
                        }
                        responseDataDTO.setResult(list);
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


        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO modifySelect(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_INT_RATE_CODE_MAIN_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rateCodeMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rateCodeMaintenanceDTO.getInterestRateCode());
                stmt.setString(index++, rateCodeMaintenanceDTO.getEffFromDate());
                stmt.setString(index++, rateCodeMaintenanceDTO.getVersionNo());
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

                    if(rateCodeMaintenanceDTO.getInterestRateCode() == null && rateCodeMaintenanceDTO.getSchemeCode() != null) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(16);
                        if (resultSet != null) {
                            List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                            RateCodeMaintenanceDTO item = null;
                            while (resultSet.next()) {
                                item = new RateCodeMaintenanceDTO();
                                item.setId(resultSet.getString(1));
                                item.setInterestRateCode(resultSet.getString(2));
                                item.setRateCodeMaintenanceDescription(resultSet.getString(3));
                                item.setVersionNo(resultSet.getString(4));
                                item.setEffFromDate(resultSet.getString(5));
                                item.setVerifiedStatus(resultSet.getString(6));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                        else if(rateCodeMaintenanceDTO.getSchemeCode() == null){
                            ResultSet resultSet = (ResultSet) stmt.getObject(16);
                            if (resultSet != null) {
                                List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                                RateCodeMaintenanceDTO item = null;
                                while (resultSet.next()) {
                                    item = new RateCodeMaintenanceDTO();
                                    item.setSchemeCode(resultSet.getString(1));
                                    item.setSubCodeDescription(resultSet.getString(2));
                                    item.setStatus(resultSet.getString(3));

                                    list.add(item);
                                }
                                responseDataDTO.setResult(list);
                            }
                        }else{
                        rateCodeMaintenanceDTO.setPkColumnValue(stmt.getString(7));
                        rateCodeMaintenanceDTO.setCreatedBy(stmt.getString(8));
                        rateCodeMaintenanceDTO.setCreatedDate(stmt.getString(9));
                        rateCodeMaintenanceDTO.setLastModifiedBy(stmt.getString(10));
                        rateCodeMaintenanceDTO.setLastModifiedDate(stmt.getString(11));
                        rateCodeMaintenanceDTO.setLastVerifiedBy(stmt.getString(12));
                        rateCodeMaintenanceDTO.setLastVerifiedDate(stmt.getString(13));
                        rateCodeMaintenanceDTO.setVerifiedStatus(stmt.getString(14));
                        rateCodeMaintenanceDTO.setRejectionRemarks(stmt.getString(15));

                        ResultSet resultSet = (ResultSet) stmt.getObject(16);

                        if(resultSet != null) {

                            while (resultSet.next()) {
                                rateCodeMaintenanceDTO.setId(resultSet.getString(1));
                                rateCodeMaintenanceDTO.setInterestRateCode(resultSet.getString(2));
                                rateCodeMaintenanceDTO.setRateCodeMaintenanceDescription(resultSet.getString(3));
                                rateCodeMaintenanceDTO.setVersionNo(resultSet.getString(4));
                                rateCodeMaintenanceDTO.setInterestRateNatSavCertiIn(resultSet.getString(6));
                                rateCodeMaintenanceDTO.setSpreadRate(resultSet.getString(7));
                                rateCodeMaintenanceDTO.setCoupanInterestRateBond(resultSet.getString(8));
                                rateCodeMaintenanceDTO.setRemarks(resultSet.getString(9));
                                rateCodeMaintenanceDTO.setFixedFloatingInterest(resultSet.getString(10));
                            }

                        }

                        //rateCodeMaintenanceDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(13)));
                        responseDataDTO.setResult(rateCodeMaintenanceDTO);
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

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modify(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_INT_RATE_CODE_MAIN_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rateCodeMaintenanceDTO.getId());
                stmt.setString(index++, rateCodeMaintenanceDTO.getEffFromDate());
                stmt.setString(index++, rateCodeMaintenanceDTO.getInterestRateNatSavCertiIn());
                stmt.setString(index++, rateCodeMaintenanceDTO.getSpreadRate());
                stmt.setString(index++, rateCodeMaintenanceDTO.getCouponCode());
                stmt.setString(index++, rateCodeMaintenanceDTO.getRemarks());
                stmt.setString(index++, rateCodeMaintenanceDTO.getFixedFloatingInterest());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO verify(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_INT_RATE_CODE_MAIN_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rateCodeMaintenanceDTO.getId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO unVerifySelect(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_INT_RATE_CODE_MAIN_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rateCodeMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rateCodeMaintenanceDTO.getInterestRateCode());
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

                if (stmt.getString(index) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {


                    if(rateCodeMaintenanceDTO.getInterestRateCode() == null && rateCodeMaintenanceDTO.getSchemeCode() != null){
                        ResultSet resultSet = (ResultSet) stmt.getObject(15);
                        if(resultSet != null) {
                            List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                            RateCodeMaintenanceDTO item = null;
                            while (resultSet.next()) {
                                item = new RateCodeMaintenanceDTO();
                                item.setId(resultSet.getString(1));
                                item.setInterestRateCode(resultSet.getString(2));
                                item.setCoupanInterestRateBond(

                                        resultSet.getString(3));
                                item.setVersionNo(resultSet.getString(4));
                                item.setEffFromDate(resultSet.getString(5));
                                item.setVerifiedStatus(resultSet.getString(6));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }
                    else if(rateCodeMaintenanceDTO.getSchemeCode() == null){
                        ResultSet resultSet = (ResultSet) stmt.getObject(15);
                        if (resultSet != null) {
                            List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                            RateCodeMaintenanceDTO item = null;
                            while (resultSet.next()) {
                                item = new RateCodeMaintenanceDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSubCodeDescription(resultSet.getString(2));
                                item.setStatus(resultSet.getString(3));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    else{
                        rateCodeMaintenanceDTO.setPkColumnValue(stmt.getString(5));
                        rateCodeMaintenanceDTO.setCreatedBy(stmt.getString(6));
                        rateCodeMaintenanceDTO.setCreatedDate(stmt.getString(7));
                        rateCodeMaintenanceDTO.setLastModifiedBy(stmt.getString(8));
                        rateCodeMaintenanceDTO.setLastModifiedDate(stmt.getString(9));
                        rateCodeMaintenanceDTO.setLastVerifiedBy(stmt.getString(10));
                        rateCodeMaintenanceDTO.setLastVerifiedDate(stmt.getString(11));
                        rateCodeMaintenanceDTO.setPreviousOperationType(stmt.getString(12));
                        rateCodeMaintenanceDTO.setVerifiedStatus(stmt.getString(13));
                        rateCodeMaintenanceDTO.setRejectionRemarks(stmt.getString(14));
                        rateCodeMaintenanceDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(15)));
                        responseDataDTO.setResult(rateCodeMaintenanceDTO);

                    }

                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rateCodeMaintenanceDTO.getRejectionRemarks() == null
                || rateCodeMaintenanceDTO.getRejectionRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_INT_RATE_CODE_MAIN_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rateCodeMaintenanceDTO.getId());
                stmt.setString(index++, rateCodeMaintenanceDTO.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO inquiry(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_INT_RATE_CODE_MAIN_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rateCodeMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rateCodeMaintenanceDTO.getInterestRateCode());
                stmt.setString(index++, rateCodeMaintenanceDTO.getVersionNo());
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
                    if (rateCodeMaintenanceDTO.getInterestRateCode() == null && rateCodeMaintenanceDTO.getSchemeCode() != null) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(16);
                        if (resultSet != null) {
                            List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                            RateCodeMaintenanceDTO item = null;
                            while (resultSet.next()) {
                                item = new RateCodeMaintenanceDTO();

                            item.setInterestRateCode(resultSet.getString(1));
                            item.setCoupanInterestRateBond(resultSet.getString(2));
                            item.setVersionNo(resultSet.getString(3));
                            item.setEffFromDate(resultSet.getString(4));
                            item.setVerifiedStatus(resultSet.getString(5));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }

                    else if(rateCodeMaintenanceDTO.getInterestRateCode() != null && rateCodeMaintenanceDTO.getVersionNo() == null){
                        ResultSet resultSet = (ResultSet) stmt.getObject(16);
                        if (resultSet != null) {
                            List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                            RateCodeMaintenanceDTO item = null;
                            while (resultSet.next()) {
                                item = new RateCodeMaintenanceDTO();

                                item.setVersionNo(resultSet.getString(1));
                                item.setEffFromDate(resultSet.getString(2));
                                item.setVerifiedStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }

                    else if(rateCodeMaintenanceDTO.getSchemeCode() == null){
                        ResultSet resultSet = (ResultSet) stmt.getObject(16);
                        if (resultSet != null) {
                            List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                            RateCodeMaintenanceDTO item = null;
                            while (resultSet.next()) {
                                item = new RateCodeMaintenanceDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSubCodeDescription(resultSet.getString(2));
                                item.setStatus(resultSet.getString(3));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    else if(rateCodeMaintenanceDTO.getInterestRateCode() != null && rateCodeMaintenanceDTO.getVersionNo() != null){
                        RateCodeMaintenanceDTO mainDTO = new RateCodeMaintenanceDTO();


                        mainDTO.setPkColumnValue(stmt.getString(6));
                        mainDTO.setCreatedBy(stmt.getString(7));
                        mainDTO.setCreatedDate(stmt.getString(8));
                        mainDTO.setLastModifiedBy(stmt.getString(9));
                        mainDTO.setLastModifiedDate(stmt.getString(10));
                        mainDTO.setLastVerifiedBy(stmt.getString(11));
                        mainDTO.setLastVerifiedDate(stmt.getString(12));
                        mainDTO.setPreviousOperationType(stmt.getString(13));
                        mainDTO.setVerifiedStatus(stmt.getString(14));
                        mainDTO.setRejectionRemarks(stmt.getString(15));
                        mainDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(16)));
                        responseDataDTO.setResult(mainDTO);
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

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }



}
