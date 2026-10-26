package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.common.util.DatabaseConnectionFactory;
import com.isl.gbm.user.management.dao.RolePermissionMappingDao;
import com.isl.gbm.user.management.dto.PermissionDetailsDTO;
import com.isl.gbm.user.management.dto.RoleDetailsDto;
import com.isl.gbm.user.management.dto.RolePermissionDTO;
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
public class RolePermissionMappingDaoImpl implements RolePermissionMappingDao {

    private static final Logger log = LoggerFactory.getLogger(RolePermissionMappingDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;
    @Autowired
    DatabaseConnectionFactory databaseConnectionFactory;

    @Override
    public ResponseDataDTO getRoleMappingDetailsForSave(String roleId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_PERM_MAP_ADD_TOR.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
                    resultSet = (ResultSet)stmt.getObject(3);
                    List<PermissionDetailsDTO> mappedList  = new ArrayList<>();
                    PermissionDetailsDTO permissionDTO = null;
                    while(resultSet.next()){
                        permissionDTO = new PermissionDetailsDTO();
                        permissionDTO.setPermissionId(resultSet.getString(1));
                        permissionDTO.setPermissionName(resultSet.getString(2));
                        mappedList.add(permissionDTO);
                    }
                    rolePermissionDTO.setMappedPermissionList(mappedList);

                    resultSet = (ResultSet)stmt.getObject(4);
                    List<PermissionDetailsDTO> unmappedList  = new ArrayList<>();
                    while(resultSet.next()){
                        permissionDTO = new PermissionDetailsDTO();
                        permissionDTO.setPermissionId(resultSet.getString(1));
                        permissionDTO.setPermissionName(resultSet.getString(2));
                        unmappedList.add(permissionDTO);
                    }

                    rolePermissionDTO.setMappedPermissionList(mappedList);
                    rolePermissionDTO.setUnmappedPermissionList(unmappedList);
                    responseDataDTO.setResult(rolePermissionDTO);
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
    public ResponseDataDTO save(RolePermissionDTO rolePermissionDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_PERM_MAP_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rolePermissionDTO.getRoleName());
                stmt.setString(index++, rolePermissionDTO.getRoleDescription());
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(connection, rolePermissionDTO.getPermissionList()));
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
    public ResponseDataDTO verify(RolePermissionDTO rolePermissionDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_PERM_MAP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rolePermissionDTO.getRoleId());
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
            databaseConnectionFactory.closeResources(connection, stmt);
        }


        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO delete(RolePermissionDTO rolePermissionDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rolePermissionDTO.getRemarks() == null
                || rolePermissionDTO.getRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_PERM_MAP_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rolePermissionDTO.getRoleId());
                stmt.setString(index++, rolePermissionDTO.getRemarks());
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
    public ResponseDataDTO getModifyDetails(String roleId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_PERM_MAP_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, roleId);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    if(StringUtils.isEmpty(roleId)){

                        resultSet = (ResultSet)stmt.getObject(4);
                        List<RoleDetailsDto> roleList = new ArrayList<>();
                        RoleDetailsDto roleDto = null;

                        while(resultSet.next()){
                            roleDto = new RoleDetailsDto();
                            roleDto.setId(resultSet.getString(1));
                            roleDto.setName(resultSet.getString(2));
                            roleDto.setDescription(resultSet.getString(3));
                            roleDto.setDisableRole(resultSet.getString(4));
                            roleList.add(roleDto);
                        }
                        responseDataDTO.setResult(roleList);
                    }

                    else {

                        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
                        resultSet = (ResultSet)stmt.getObject(4);

                        while(resultSet.next()){
                            rolePermissionDTO.setId(resultSet.getString(1));
                            rolePermissionDTO.setRoleName(resultSet.getString(2));
                            rolePermissionDTO.setRoleDescription(resultSet.getString(3));
                            rolePermissionDTO.setDisableRole(resultSet.getString(4));
                        }

                        rolePermissionDTO.setRemarks(stmt.getString(15));
                        resultSet = (ResultSet)stmt.getObject(5);
                        List<PermissionDetailsDTO> mappedList  = new ArrayList<>();
                        PermissionDetailsDTO permissionDTO = null;
                        while(resultSet.next()){
                            permissionDTO = new PermissionDetailsDTO();
                            permissionDTO.setPermissionId(resultSet.getString(1));
                            permissionDTO.setPermissionName(resultSet.getString(2));
                            mappedList.add(permissionDTO);
                        }
                        rolePermissionDTO.setMappedPermissionList(mappedList);

                        resultSet = (ResultSet)stmt.getObject(6);
                        List<PermissionDetailsDTO> unmappedList  = new ArrayList<>();
                        while(resultSet.next()){
                            permissionDTO = new PermissionDetailsDTO();
                            permissionDTO.setPermissionId(resultSet.getString(1));
                            permissionDTO.setPermissionName(resultSet.getString(2));
                            unmappedList.add(permissionDTO);
                        }

                        rolePermissionDTO.setMappedPermissionList(mappedList);
                        rolePermissionDTO.setUnmappedPermissionList(unmappedList);

                        rolePermissionDTO.setCreatedBy(stmt.getString(7));
                        rolePermissionDTO.setCreatedDate(stmt.getString(8));
                        rolePermissionDTO.setLastModifiedBy(stmt.getString(9));
                        rolePermissionDTO.setLastModifiedDate(stmt.getString(10));
                        rolePermissionDTO.setVerifiedBy(stmt.getString(11));
                        rolePermissionDTO.setVerifiedDate(stmt.getString(12));
                        rolePermissionDTO.setVerifyStatus(stmt.getString(13));



                        responseDataDTO.setResult(rolePermissionDTO);
                    }

                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            databaseConnectionFactory.closeResources(connection, stmt);
        }


        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO getInquiryDetails(String roleId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_role_map_mgmt.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, roleId);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    if(StringUtils.isEmpty(roleId)){

                        resultSet = (ResultSet)stmt.getObject(4);
                        List<RoleDetailsDto> roleList = new ArrayList<>();
                        RoleDetailsDto roleDto = null;

                        while(resultSet.next()){
                            roleDto = new RoleDetailsDto();
                            roleDto.setId(resultSet.getString(1));
                            roleDto.setName(resultSet.getString(2));
                            roleDto.setDescription(resultSet.getString(3));
                            roleDto.setDisableRole(resultSet.getString(4));
                            roleList.add(roleDto);
                        }
                        responseDataDTO.setResult(roleList);
                    }

                    else {
                        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
                        resultSet = (ResultSet)stmt.getObject(4);

                        while(resultSet.next()){
                            rolePermissionDTO.setRoleId(resultSet.getString(1));
                            rolePermissionDTO.setRoleName(resultSet.getString(2));
                            rolePermissionDTO.setRoleDescription(resultSet.getString(3));
                            rolePermissionDTO.setDisableRole(resultSet.getString(4));
                            rolePermissionDTO.setRoleNameNew(resultSet.getString(5));
                            rolePermissionDTO.setRoleDescriptionNew(resultSet.getString(6));
                            rolePermissionDTO.setDisableRoleNew(resultSet.getString(7));

                        }
                        rolePermissionDTO.setPreOpr(stmt.getString(14));
                        rolePermissionDTO.setRemarks(stmt.getString(15));

                        resultSet = (ResultSet)stmt.getObject(5);
                        List<PermissionDetailsDTO> mappedList  = new ArrayList<>();
                        PermissionDetailsDTO permissionDTO = null;
                        while(resultSet.next()){
                            permissionDTO = new PermissionDetailsDTO();
                            permissionDTO.setPermissionId(resultSet.getString(1));
                            permissionDTO.setPermissionName(resultSet.getString(2));
                            permissionDTO.setVerifiedStatus(resultSet.getString(3));
                            mappedList.add(permissionDTO);
                        }
                        rolePermissionDTO.setMappedPermissionList(mappedList);

                        resultSet = (ResultSet)stmt.getObject(6);
                        List<PermissionDetailsDTO> unmappedList  = new ArrayList<>();
                        while(resultSet.next()){
                            permissionDTO = new PermissionDetailsDTO();
                            permissionDTO.setPermissionId(resultSet.getString(1));
                            permissionDTO.setPermissionName(resultSet.getString(2));
                         permissionDTO.setVerifiedStatus(resultSet.getString(3));
                            unmappedList.add(permissionDTO);
                        }

                        rolePermissionDTO.setMappedPermissionList(mappedList);
                        rolePermissionDTO.setUnmappedPermissionList(unmappedList);

                        rolePermissionDTO.setCreatedBy(stmt.getString(7));
                        rolePermissionDTO.setCreatedDate(stmt.getString(8));
                        rolePermissionDTO.setLastModifiedBy(stmt.getString(9));
                        rolePermissionDTO.setLastModifiedDate(stmt.getString(10));
                        rolePermissionDTO.setVerifiedBy(stmt.getString(11));
                        rolePermissionDTO.setVerifiedDate(stmt.getString(12));
                        rolePermissionDTO.setVerifyStatus(stmt.getString(13));
                        responseDataDTO.setResult(rolePermissionDTO);
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
            databaseConnectionFactory.closeResources(connection, stmt);
        }


        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO getVerifyDetails(String roleId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_PERM_MAP_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, roleId);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    if(StringUtils.isEmpty(roleId)){

                        resultSet = (ResultSet)stmt.getObject(4);
                        List<RoleDetailsDto> roleList = new ArrayList<>();
                        RoleDetailsDto roleDto = null;

                        while(resultSet.next()){
                            roleDto = new RoleDetailsDto();
                            roleDto.setId(resultSet.getString(1));
                            roleDto.setName(resultSet.getString(2));
                            roleDto.setDescription(resultSet.getString(3));
                            roleDto.setDisableRole(resultSet.getString(4));
                            roleList.add(roleDto);
                        }
                        responseDataDTO.setResult(roleList);
                    }

                    else {

                        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();

                        resultSet = (ResultSet)stmt.getObject(4);

                        while(resultSet.next()){
                            rolePermissionDTO.setId(resultSet.getString(1));
                            rolePermissionDTO.setRoleName(resultSet.getString(2));
                            rolePermissionDTO.setRoleDescription(resultSet.getString(3));
                            rolePermissionDTO.setDisableRole(resultSet.getString(4));
                        }
                        rolePermissionDTO.setRemarks(stmt.getString(15));
                        resultSet = (ResultSet)stmt.getObject(5);
                        List<PermissionDetailsDTO> mappedList  = new ArrayList<>();
                        PermissionDetailsDTO permissionDTO = null;
                        while(resultSet.next()){
                            permissionDTO = new PermissionDetailsDTO();
                            permissionDTO.setPermissionId(resultSet.getString(1));
                            permissionDTO.setPermissionName(resultSet.getString(2));
                            permissionDTO.setVerifiedStatus(resultSet.getString(3));

                            mappedList.add(permissionDTO);
                        }
                        rolePermissionDTO.setMappedPermissionList(mappedList);

                        resultSet = (ResultSet)stmt.getObject(6);
                        List<PermissionDetailsDTO> unmappedList  = new ArrayList<>();
                        while(resultSet.next()){
                            permissionDTO = new PermissionDetailsDTO();
                            permissionDTO.setPermissionId(resultSet.getString(1));
                            permissionDTO.setPermissionName(resultSet.getString(2));
                            permissionDTO.setVerifiedStatus(resultSet.getString(3));

                            unmappedList.add(permissionDTO);
                        }

                        rolePermissionDTO.setMappedPermissionList(mappedList);
                        rolePermissionDTO.setUnmappedPermissionList(unmappedList);

                        rolePermissionDTO.setCreatedBy(stmt.getString(7));
                        rolePermissionDTO.setCreatedDate(stmt.getString(8));
                        rolePermissionDTO.setLastModifiedBy(stmt.getString(9));
                        rolePermissionDTO.setLastModifiedDate(stmt.getString(10));
                        rolePermissionDTO.setVerifiedBy(stmt.getString(11));
                        rolePermissionDTO.setVerifiedDate(stmt.getString(12));
                        rolePermissionDTO.setVerifyStatus(stmt.getString(13));

                        responseDataDTO.setResult(rolePermissionDTO);
                    }

                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            databaseConnectionFactory.closeResources(connection, stmt);
        }


        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO modify(RolePermissionDTO rolePermissionDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_PERM_MAP_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rolePermissionDTO.getRoleId());
                stmt.setString(index++, rolePermissionDTO.getRoleName());
                stmt.setString(index++, rolePermissionDTO.getRoleDescription());
                stmt.setString(index++, rolePermissionDTO.getDisableRole());
                log.info("Permission list size: {}",
                        rolePermissionDTO.getPermissionList() != null ? rolePermissionDTO.getPermissionList().size() : 0);
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(connection, rolePermissionDTO.getPermissionList()));
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
            databaseConnectionFactory.closeResources(connection, stmt);
        }


        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
}
