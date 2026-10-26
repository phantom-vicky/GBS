package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.user.management.dao.UserRoleMappingDAO;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.util.ProcNameEnum;
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
import java.util.List;

@Repository
public class UserRoleMappingDaoImpl implements UserRoleMappingDAO {

    private static final Logger log = LoggerFactory.getLogger(UserRoleMappingDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO getUserRoleMappingDetailsForSave(String userId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_ROLE_MAP_ADD_TOR.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, userId);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    if(StringUtils.isEmpty(userId)){

                        resultSet = (ResultSet)stmt.getObject(4);
                        List<UserDetailsDto> userList = new ArrayList<>();
                        UserDetailsDto roleDto = null;

                        while(resultSet.next()){
                            roleDto = new UserDetailsDto();
                            roleDto.setId(resultSet.getString(1));
                            roleDto.setFirstName(resultSet.getString(2));
                            roleDto.setLastName(resultSet.getString(3));
                            roleDto.setUsername(resultSet.getString(4));
                            roleDto.setApplicationName(resultSet.getString(5));
                            roleDto.setSolId(resultSet.getString(6));
                            roleDto.setEmployeeCode(resultSet.getString(7));
                            roleDto.setStatus(resultSet.getString(8));
                            userList.add(roleDto);
                        }
                        responseDataDTO.setResult(userList);
                    }

                    else {

                        UserRoleMappingDTO userRoleMappingDTO = new UserRoleMappingDTO();

                        resultSet = (ResultSet)stmt.getObject(5);
                        List<RoleDetailsDto> mappedList  = new ArrayList<>();
                        RoleDetailsDto roleDetailsDto = null;
                        while(resultSet.next()){
                            roleDetailsDto = new RoleDetailsDto();
                            roleDetailsDto.setId(resultSet.getString(1));
                            roleDetailsDto.setName(resultSet.getString(2));
                            roleDetailsDto.setDescription(resultSet.getString(3));
                            mappedList.add(roleDetailsDto);
                        }
                        userRoleMappingDTO.setMappedUserRoleList(mappedList);

                        resultSet = (ResultSet)stmt.getObject(6);
                        List<RoleDetailsDto> unmappedList  = new ArrayList<>();
                        while(resultSet.next()){
                            roleDetailsDto = new RoleDetailsDto();
                            roleDetailsDto.setId(resultSet.getString(1));
                            roleDetailsDto.setName(resultSet.getString(2));
                            roleDetailsDto.setDescription(resultSet.getString(3));
                            unmappedList.add(roleDetailsDto);
                        }

                        userRoleMappingDTO.setMappedUserRoleList(mappedList);
                        userRoleMappingDTO.setUnmappedUserRoleList(unmappedList);

                        responseDataDTO.setResult(userRoleMappingDTO);
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
    public ResponseDataDTO save(UserRoleMappingDTO userRoleMappingDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_ROLE_MAP_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, userRoleMappingDTO.getUserId());
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(connection, userRoleMappingDTO.getRoleList()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
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


    @Override
    public ResponseDataDTO verify(UserRoleMappingDTO userRoleMappingDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_ROLE_MAP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, userRoleMappingDTO.getUserId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
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

    @Override
    public ResponseDataDTO getInquiryDetails(String userId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_ROLE_MAP_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, userId);
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    if(StringUtils.isEmpty(userId)){

                        resultSet = (ResultSet)stmt.getObject(4);
                        List<UserDetailsDto> userList = new ArrayList<>();
                        UserDetailsDto roleDto = null;

                        while(resultSet.next()){
                            roleDto = new UserDetailsDto();
                            roleDto.setId(resultSet.getString(1));
                            roleDto.setFirstName(resultSet.getString(2));
                            roleDto.setLastName(resultSet.getString(3));
                            roleDto.setUsername(resultSet.getString(4));
                            roleDto.setApplicationName(resultSet.getString(5));
                            roleDto.setSolId(resultSet.getString(6));
                            roleDto.setEmployeeCode(resultSet.getString(7));
                            roleDto.setStatus(resultSet.getString(8));
                            userList.add(roleDto);
                        }
                        responseDataDTO.setResult(userList);
                    }

                    else {

                        UserRoleMappingDTO userRoleMappingDTO = new UserRoleMappingDTO();

                        resultSet = (ResultSet)stmt.getObject(5);
                        List<RoleDetailsDto> mappedList  = new ArrayList<>();
                        RoleDetailsDto roleDetailsDto = null;
                        while(resultSet.next()){
                            roleDetailsDto = new RoleDetailsDto();
                            roleDetailsDto.setId(resultSet.getString(1));
                            roleDetailsDto.setName(resultSet.getString(2));
                            roleDetailsDto.setDescription(resultSet.getString(3));
                            mappedList.add(roleDetailsDto);
                        }
                        userRoleMappingDTO.setMappedUserRoleList(mappedList);

                        resultSet = (ResultSet)stmt.getObject(6);
                        List<RoleDetailsDto> unmappedList  = new ArrayList<>();
                        while(resultSet.next()){
                            roleDetailsDto = new RoleDetailsDto();
                            roleDetailsDto.setId(resultSet.getString(1));
                            roleDetailsDto.setName(resultSet.getString(2));
                            roleDetailsDto.setDescription(resultSet.getString(3));
                            unmappedList.add(roleDetailsDto);
                        }

                        userRoleMappingDTO.setMappedUserRoleList(mappedList);
                        userRoleMappingDTO.setUnmappedUserRoleList(unmappedList);

                        userRoleMappingDTO.setCreatedBy(stmt.getString(7));
                        userRoleMappingDTO.setCreatedDate(stmt.getString(8));
                        userRoleMappingDTO.setLastModifiedBy(stmt.getString(9));
                        userRoleMappingDTO.setLastModifiedDate(stmt.getString(10));
                        userRoleMappingDTO.setVerifiedBy(stmt.getString(11));
                        userRoleMappingDTO.setVerifiedDate(stmt.getString(12));
                        userRoleMappingDTO.setVerifyStatus(stmt.getString(13));



                        responseDataDTO.setResult(userRoleMappingDTO);
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
    public ResponseDataDTO getModifyDetails(String userId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_ROLE_MAP_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, userId);
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    if(StringUtils.isEmpty(userId)){

                        resultSet = (ResultSet)stmt.getObject(4);
                        List<UserDetailsDto> userList = new ArrayList<>();
                        UserDetailsDto roleDto = null;

                        while(resultSet.next()){
                            roleDto = new UserDetailsDto();
                            roleDto.setId(resultSet.getString(1));
                            roleDto.setFirstName(resultSet.getString(2));
                            roleDto.setLastName(resultSet.getString(3));
                            roleDto.setUsername(resultSet.getString(4));
                            roleDto.setApplicationName(resultSet.getString(5));
                            roleDto.setSolId(resultSet.getString(6));
                            roleDto.setEmployeeCode(resultSet.getString(7));
                            roleDto.setStatus(resultSet.getString(8));
                            userList.add(roleDto);
                        }
                        responseDataDTO.setResult(userList);
                    }

                    else {

                        UserRoleMappingDTO userRoleMappingDTO = new UserRoleMappingDTO();

                        resultSet = (ResultSet)stmt.getObject(5);
                        List<RoleDetailsDto> mappedList  = new ArrayList<>();
                        RoleDetailsDto roleDetailsDto = null;
                        while(resultSet.next()){
                            roleDetailsDto = new RoleDetailsDto();
                            roleDetailsDto.setId(resultSet.getString(1));
                            roleDetailsDto.setName(resultSet.getString(2));
                            roleDetailsDto.setDescription(resultSet.getString(3));

                            mappedList.add(roleDetailsDto);
                        }
                        userRoleMappingDTO.setMappedUserRoleList(mappedList);

                        resultSet = (ResultSet)stmt.getObject(6);
                        List<RoleDetailsDto> unmappedList  = new ArrayList<>();
                        while(resultSet.next()){
                            roleDetailsDto = new RoleDetailsDto();
                            roleDetailsDto.setId(resultSet.getString(1));
                            roleDetailsDto.setName(resultSet.getString(2));
                            roleDetailsDto.setDescription(resultSet.getString(3));
                            unmappedList.add(roleDetailsDto);
                        }

                        userRoleMappingDTO.setMappedUserRoleList(mappedList);
                        userRoleMappingDTO.setUnmappedUserRoleList(unmappedList);

                        userRoleMappingDTO.setCreatedBy(stmt.getString(7));
                        userRoleMappingDTO.setCreatedDate(stmt.getString(8));
                        userRoleMappingDTO.setLastModifiedBy(stmt.getString(9));
                        userRoleMappingDTO.setLastModifiedDate(stmt.getString(10));
                        userRoleMappingDTO.setVerifiedBy(stmt.getString(11));
                        userRoleMappingDTO.setVerifiedDate(stmt.getString(12));
                        userRoleMappingDTO.setVerifyStatus(stmt.getString(13));



                        responseDataDTO.setResult(userRoleMappingDTO);
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
    public ResponseDataDTO modify(UserRoleMappingDTO userRoleMappingDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_ROLE_MAP_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, userRoleMappingDTO.getUserId());
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(connection, userRoleMappingDTO.getRoleList()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
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


    @Override
    public ResponseDataDTO getVerifyDetails(String userId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_ROLE_MAP_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, userId);
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    if(StringUtils.isEmpty(userId)){

                        resultSet = (ResultSet)stmt.getObject(4);
                        List<UserDetailsDto> userList = new ArrayList<>();
                        UserDetailsDto roleDto = null;

                        while(resultSet.next()){
                            roleDto = new UserDetailsDto();
                            roleDto.setId(resultSet.getString(1));
                            roleDto.setFirstName(resultSet.getString(2));
                            roleDto.setLastName(resultSet.getString(3));
                            roleDto.setUsername(resultSet.getString(4));
                            roleDto.setApplicationName(resultSet.getString(5));
                            roleDto.setSolId(resultSet.getString(6));
                            roleDto.setEmployeeCode(resultSet.getString(7));
                            roleDto.setStatus(resultSet.getString(8));
                            userList.add(roleDto);
                        }
                        responseDataDTO.setResult(userList);
                    }

                    else {

                        UserRoleMappingDTO userRoleMappingDTO = new UserRoleMappingDTO();

                        resultSet = (ResultSet)stmt.getObject(5);
                        List<RoleDetailsDto> mappedList  = new ArrayList<>();
                        RoleDetailsDto roleDetailsDto = null;
                        while(resultSet.next()){
                            roleDetailsDto = new RoleDetailsDto();
                            roleDetailsDto.setId(resultSet.getString(1));
                            roleDetailsDto.setName(resultSet.getString(2));
                            roleDetailsDto.setDescription(resultSet.getString(3));
                            mappedList.add(roleDetailsDto);
                        }
                        userRoleMappingDTO.setMappedUserRoleList(mappedList);

                        resultSet = (ResultSet)stmt.getObject(6);
                        List<RoleDetailsDto> unmappedList  = new ArrayList<>();
                        while(resultSet.next()){
                            roleDetailsDto = new RoleDetailsDto();
                            roleDetailsDto.setId(resultSet.getString(1));
                            roleDetailsDto.setName(resultSet.getString(2));
                            roleDetailsDto.setDescription(resultSet.getString(3));
                            unmappedList.add(roleDetailsDto);
                        }

                        userRoleMappingDTO.setMappedUserRoleList(mappedList);
                        userRoleMappingDTO.setUnmappedUserRoleList(unmappedList);

                        userRoleMappingDTO.setCreatedBy(stmt.getString(7));
                        userRoleMappingDTO.setCreatedDate(stmt.getString(8));
                        userRoleMappingDTO.setLastModifiedBy(stmt.getString(9));
                        userRoleMappingDTO.setLastModifiedDate(stmt.getString(10));
                        userRoleMappingDTO.setVerifiedBy(stmt.getString(11));
                        userRoleMappingDTO.setVerifiedDate(stmt.getString(12));
                        userRoleMappingDTO.setVerifyStatus(stmt.getString(13));



                        responseDataDTO.setResult(userRoleMappingDTO);
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
