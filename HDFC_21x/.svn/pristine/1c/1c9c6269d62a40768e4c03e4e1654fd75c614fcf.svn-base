package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.user.management.dao.UserParameterDao;
import com.isl.gbm.user.management.dto.UserParamDto;
import com.isl.gbm.user.management.util.ProcNameEnum;
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

public class UserParameterDaoImpl implements UserParameterDao {

    private static final Logger log = LoggerFactory.getLogger(UserRoleMappingDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Override
    public ResponseDataDTO updateUserParams() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_user_param_ModSel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);



                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {


                    resultSet = (ResultSet) stmt.getObject(3);
                    List<UserParamDto> userList = new ArrayList<>();
                    UserParamDto userParamDto1 = null;
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            userParamDto1 = new UserParamDto();
                            userParamDto1.setDormancyPeriod(resultSet.getInt(1));
                            userParamDto1.setActivationPeriod(resultSet.getInt(2));
                            userParamDto1.setNoOfRetries(resultSet.getInt(3));
                            userParamDto1.setMaxAccPeriod(resultSet.getInt(4));

                            userList.add(userParamDto1);
                        }
                    }

                    userParamDto1.setCreatedBy(stmt.getString(4));
                    userParamDto1.setCreatedDate(stmt.getString(5));
                    userParamDto1.setLastModifiedBy(stmt.getString(6));
                    userParamDto1.setLastModifiedDate(stmt.getString(7));
                    userParamDto1.setVerifiedBy(stmt.getString(8));
                    userParamDto1.setVerifiedDate(stmt.getString(9));
                    userParamDto1.setVerifiedStatus(stmt.getString(10));
                    userParamDto1.setRemarks(stmt.getString(11));
                    responseDataDTO.setResult(userList);
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
    public ResponseDataDTO modify(UserParamDto users) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (users.getDormancyPeriod() == null || users.getDormancyPeriod() < 1 || users.getDormancyPeriod() > 999) {
            responseDataDTO.setMessage("Dormancy Period is mandatory and must be between 1 and 999");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (users.getNoOfRetries() == null || users.getNoOfRetries() < 1 || users.getNoOfRetries() > 99) {
            responseDataDTO.setMessage("No of Retries for Log in Attempts is mandatory and must be between 1 and 99");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (users.getActivationPeriod() == null || users.getActivationPeriod() < 1 || users.getActivationPeriod() > 999) {
            responseDataDTO.setMessage("Activation Period is mandatory and must be between 1 and 999");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (users.getMaxAccPeriod() == null || users.getMaxAccPeriod() < 1 || users.getMaxAccPeriod() > 999) {
            responseDataDTO.setMessage("Maximum Acceptable Period of User inactivity is mandatory and must be between 1 and 999");
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
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_user_param_Mod.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setInt(index++, users.getDormancyPeriod());
                stmt.setInt(index++, users.getNoOfRetries());
                stmt.setInt(index++, users.getActivationPeriod());
                stmt.setInt(index++, users.getMaxAccPeriod());
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
    public ResponseDataDTO getUserParams() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_user_param_sp_inq.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);



                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {


                    resultSet = (ResultSet) stmt.getObject(3);
                    List<UserParamDto> userList = new ArrayList<>();
                    UserParamDto userParamDto1 = null;
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            userParamDto1 = new UserParamDto();
                            userParamDto1.setDormancyPeriod(resultSet.getInt(1));
                            userParamDto1.setActivationPeriod(resultSet.getInt(2));
                            userParamDto1.setNoOfRetries(resultSet.getInt(3));
                            userParamDto1.setMaxAccPeriod(resultSet.getInt(4));
                            userParamDto1.setDormancyPeriod1(resultSet.getInt(5));
                            userParamDto1.setActivationPeriod1(resultSet.getInt(6));
                            userParamDto1.setNoOfRetries1(resultSet.getInt(7));
                            userParamDto1.setMaxAccPeriod1(resultSet.getInt(8));

                            userList.add(userParamDto1);
                        }
                    }

                    userParamDto1.setCreatedBy(stmt.getString(4));
                    userParamDto1.setCreatedDate(stmt.getString(5));
                    userParamDto1.setLastModifiedBy(stmt.getString(6));
                    userParamDto1.setLastModifiedDate(stmt.getString(7));
                    userParamDto1.setVerifiedBy(stmt.getString(8));
                    userParamDto1.setVerifiedDate(stmt.getString(9));
                    userParamDto1.setVerifiedStatus(stmt.getString(10));
                    userParamDto1.setPerviousOperation(stmt.getString(11));
                    userParamDto1.setRemarks(stmt.getString(12));
                    responseDataDTO.setResult(userList);
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

//    @Override
//    public ResponseDataDTO getUserParams() {
//        Connection connection = null;
//        CallableStatement stmt = null;
//        String resultMsg = "";
//        ResultSet resultSet = null;
//        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
//        try {
//            connection = connectionPoolFactory.getConnection();
//            if (connection != null) {
//                int index = 1;
//                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_user_param_sp_inq.getProcName());
//                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
//                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index, Types.VARCHAR);
//                stmt.execute();
//
//                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
//                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
//                resultMsg = stmt.getString(index);
//
//
//
//                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {
//
//
//                    resultSet = (ResultSet) stmt.getObject(3);
//                    List<UserParamDto> userList = new ArrayList<>();
//                    UserParamDto userParamDto1 = null;
//                    userParamDto1.setPerviousOperation(stmt.getString(11));
//                    if (resultSet != null && userParamDto1.getPerviousOperation()=="I" ) {
//                        while (resultSet.next()) {
//                            userParamDto1 = new UserParamDto();
//                            userParamDto1.setDormancyPeriod(resultSet.getInt(1));
//                            userParamDto1.setActivationPeriod(resultSet.getInt(2));
//                            userParamDto1.setNoOfRetries(resultSet.getInt(3));
//                            userParamDto1.setMaxAccPeriod(resultSet.getInt(4));
//
//                            userList.add(userParamDto1);
//                        }
//                    }
//                    else if(resultSet != null && userParamDto1.getPerviousOperation()=="M" ){
//                        userParamDto1 = new UserParamDto();
//                        userParamDto1.setDormancyPeriod1(resultSet.getInt(5));
//                        userParamDto1.setActivationPeriod1(resultSet.getInt(6));
//                        userParamDto1.setNoOfRetries1(resultSet.getInt(7));
//                        userParamDto1.setMaxAccPeriod1(resultSet.getInt(8));
//
//                        userList.add(userParamDto1);
//                    }
//
//                    userParamDto1.setCreatedBy(stmt.getString(4));
//                    userParamDto1.setCreatedDate(stmt.getString(5));
//                    userParamDto1.setLastModifiedBy(stmt.getString(6));
//                    userParamDto1.setLastModifiedDate(stmt.getString(7));
//                    userParamDto1.setVerifiedBy(stmt.getString(8));
//                    userParamDto1.setVerifiedDate(stmt.getString(9));
//                    userParamDto1.setVerifiedStatus(stmt.getString(10));
//                    userParamDto1.setPerviousOperation(stmt.getString(11));
//                    responseDataDTO.setResult(userList);
//                }
//            } else {
//                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
//            }
//
//        } catch (Exception e) {
//            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
//            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
//        } finally {
//            connectionPoolFactory.closeConnection(connection, stmt);
//        }
//
//
//        responseDataDTO.setMessage(resultMsg);
//        return responseDataDTO;
//    }


    @Override
    public ResponseDataDTO getverDelParam() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_user_param_sp_ver_sel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);



                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {


                    resultSet = (ResultSet) stmt.getObject(3);
                    List<UserParamDto> userList = new ArrayList<>();
                    UserParamDto userParamDto1 = null;
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            userParamDto1 = new UserParamDto();
                            userParamDto1.setDormancyPeriod(resultSet.getInt(1));
                            userParamDto1.setActivationPeriod(resultSet.getInt(2));
                            userParamDto1.setNoOfRetries(resultSet.getInt(3));
                            userParamDto1.setMaxAccPeriod(resultSet.getInt(4));
                            userParamDto1.setDormancyPeriod1(resultSet.getInt(5));
                            userParamDto1.setActivationPeriod1(resultSet.getInt(6));
                            userParamDto1.setNoOfRetries1(resultSet.getInt(7));
                            userParamDto1.setMaxAccPeriod1(resultSet.getInt(8));

                            userList.add(userParamDto1);
                        }
                    }

                    userParamDto1.setCreatedBy(stmt.getString(4));
                    userParamDto1.setCreatedDate(stmt.getString(5));
                    userParamDto1.setLastModifiedBy(stmt.getString(6));
                    userParamDto1.setLastModifiedDate(stmt.getString(7));
                    userParamDto1.setVerifiedBy(stmt.getString(8));
                    userParamDto1.setVerifiedDate(stmt.getString(9));
                    userParamDto1.setVerifiedStatus(stmt.getString(10));
                    userParamDto1.setPerviousOperation(stmt.getString(11));
                    userParamDto1.setRemarks(stmt.getString(12));
                    responseDataDTO.setResult(userList);
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
    public ResponseDataDTO verifySubmit() {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
//        UserParamDto users = new UserParamDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_user_param_sp_ver.getProcName());

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
    public ResponseDataDTO delete(UserParamDto userParamDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (userParamDto.getRemarks() == null
                || userParamDto.getRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(userParamDto.getRemarks())) {
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
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_user_param_sp_del.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, userParamDto.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();


                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
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