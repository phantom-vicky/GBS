package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.dao.RoleDao;
import com.isl.gbm.user.management.dto.RoleDetailsDto;
import com.isl.gbm.user.management.dto.RoleDto;
import com.isl.gbm.user.management.model.Role;
import com.isl.gbm.user.management.util.ProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class RoleDaoImpl.
 */

@Repository
public class RoleDaoImpl extends HibernateDao implements RoleDao {


    /**
     * The Constant NAME.
     */
    private static final String NAME = "name";

    /**
     * The Constant ID.
     */
    private static final String ID = "id";

    private static final Logger log = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;


    @PersistenceContext
    EntityManager em;


    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.dao.RoleDao#createRole(com.afl.gbm.user.
     * management.model.Role)
     */
    @Override
    public void saveRole(Role role) {
        this.getSession().merge(role);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.dao.RoleDao#getRoleByName(java.lang.String)
     */
    @Override
    public Role getRoleByName(String name) {
        final Session session = this.getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
        final Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root).where(builder.equal(root.get(NAME), name));
        return session.createQuery(criteriaQuery).uniqueResult();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.dao.RoleDao#getRole(int)
     */
    @Override
    public Role getRole(int id) {
        final Session session = this.getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
        final Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), id));
        return session.createQuery(criteriaQuery).uniqueResult();
    }


    /* (non-Javadoc)
     * @see com.afl.gbm.user.management.dao.RoleDao#getRoles()
     */
    @Override
    public List<Role> getRoles() {
        /*final Session session = this.sessionFactory.getCurrentSession();*/
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
        final Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root).where(builder.notEqual(root.get(NAME), CommonConstants.SUPER_ADMIN));
        return em.createQuery(criteriaQuery.select(root)).getResultList();
    }

    /* (non-Javadoc)
     * @see com.afl.gbm.user.management.dao.RoleDao#deleteRole(java.lang.Integer)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void deleteRole(Integer id) {
        final Session session = this.getSession();
        Query query = session.createQuery("delete from RolePermission where role_id= :id");
        query.setParameter("id", id);
        query.executeUpdate();
        Object persistentInstance = session.load(Role.class, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
        }
    }


    @Override
    public ResponseDataDTO saveRole(RoleDto roleDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_MGMT_ADD.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, roleDto.getName());
                stmt.setString(index++, roleDto.getDescription());
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
    public ResponseDataDTO modifyRole(RoleDetailsDto roleDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_MGMT_MOD.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, roleDto.getId());
                stmt.setString(index++, roleDto.getName());
                stmt.setString(index++, roleDto.getDescription());
                stmt.setString(index++, roleDto.getDisableRole());
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
    public ResponseDataDTO deleteRole(RoleDetailsDto roleDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_MGMT_DEL.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, roleDto.getId());
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
    public ResponseDataDTO verifyRole(RoleDetailsDto roleDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_MGMT_VER.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, roleDto.getId());
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
    public ResponseDataDTO inquiryRoleDetails(String roleId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_MGMT_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, roleId);
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet)stmt.getObject(12);
                    if(StringUtils.isEmpty(roleId)){
                        List<RoleDetailsDto> roleList = new ArrayList<>();
                        RoleDetailsDto roleDto = null;

                        while(resultSet.next()){
                            roleDto = new RoleDetailsDto();
                            roleDto.setId(resultSet.getString(1));
                            roleDto.setName(resultSet.getString(2));
                            roleDto.setDescription(resultSet.getString(3));
                            roleList.add(roleDto);
                        }
                        responseDataDTO.setResult(roleList);
                    }

                    else {
                        RoleDetailsDto roleDetailsDto = null;

                        if(resultSet.next()){
                            roleDetailsDto = new RoleDetailsDto();
                            roleDetailsDto.setId(resultSet.getString(1));
                            roleDetailsDto.setName(resultSet.getString(2));
                            roleDetailsDto.setDescription(resultSet.getString(3));
                            roleDetailsDto.setDisableRole(resultSet.getString(4));
                            roleDetailsDto.setNameNew(resultSet.getString(5));
                            roleDetailsDto.setDescriptionNew(resultSet.getString(6));
                            roleDetailsDto.setDisableRoleNew(resultSet.getString(7));

                            roleDetailsDto.setCreatedByUsername(stmt.getString(4));
                            roleDetailsDto.setCreatedDate(stmt.getString(5));
                            roleDetailsDto.setLastModifiedByUsername(stmt.getString(6));
                            roleDetailsDto.setLastModifiedDate(stmt.getString(7));
                            roleDetailsDto.setLastVerifiedBy(stmt.getString(8));
                            roleDetailsDto.setVerifiedDate(stmt.getString(9));
                            roleDetailsDto.setPreOpr(stmt.getString(10));
                            roleDetailsDto.setVerifyStatus(stmt.getString(11));
                        }
                        responseDataDTO.setResult(roleDetailsDto);
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
    public ResponseDataDTO modifyRoleDetails(String roleId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_MGMT_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, roleId);
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet)stmt.getObject(12);
                    if(StringUtils.isEmpty(roleId)){
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
                        RoleDetailsDto roleDetailsDto = null;

                        if(resultSet.next()){
                            roleDetailsDto = new RoleDetailsDto();
                            roleDetailsDto.setId(resultSet.getString(1));
                            roleDetailsDto.setName(resultSet.getString(2));
                            roleDetailsDto.setDescription(resultSet.getString(3));
                            roleDetailsDto.setDisableRole(resultSet.getString(4));
                           /* roleDetailsDto.setNameNew(resultSet.getString(5));
                            roleDetailsDto.setDescriptionNew(resultSet.getString(6));
                            roleDetailsDto.setDisableRoleNew(resultSet.getString(7));*/

                            roleDetailsDto.setCreatedByUsername(stmt.getString(4));
                            roleDetailsDto.setCreatedDate(stmt.getString(5));
                            roleDetailsDto.setLastModifiedByUsername(stmt.getString(6));
                            roleDetailsDto.setLastModifiedDate(stmt.getString(7));
                            roleDetailsDto.setLastVerifiedBy(stmt.getString(8));
                            roleDetailsDto.setVerifiedDate(stmt.getString(9));
                            roleDetailsDto.setPreOpr(stmt.getString(10));
                            roleDetailsDto.setVerifyStatus(stmt.getString(11));
                        }
                        responseDataDTO.setResult(roleDetailsDto);
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
    public ResponseDataDTO verifyRoleDetails(String roleId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_ROLE_MGMT_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, roleId);
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet)stmt.getObject(12);
                    if(StringUtils.isEmpty(roleId)){
                        List<RoleDetailsDto> roleList = new ArrayList<>();
                        RoleDetailsDto roleDto = null;

                        while(resultSet.next()){
                            roleDto = new RoleDetailsDto();
                            roleDto.setId(resultSet.getString(1));
                            roleDto.setName(resultSet.getString(2));
                            roleDto.setDescription(resultSet.getString(3));
                            roleList.add(roleDto);
                        }
                        responseDataDTO.setResult(roleList);
                    }

                    else {
                        RoleDetailsDto roleDetailsDto = null;

                        if(resultSet.next()){
                            roleDetailsDto = new RoleDetailsDto();
                            roleDetailsDto.setId(resultSet.getString(1));
                            roleDetailsDto.setName(resultSet.getString(2));
                            roleDetailsDto.setDescription(resultSet.getString(3));
                            roleDetailsDto.setDisableRole(resultSet.getString(4));
                            roleDetailsDto.setNameNew(resultSet.getString(5));
                            roleDetailsDto.setDescriptionNew(resultSet.getString(6));
                            roleDetailsDto.setDisableRoleNew(resultSet.getString(7));

                            roleDetailsDto.setCreatedByUsername(stmt.getString(4));
                            roleDetailsDto.setCreatedDate(stmt.getString(5));
                            roleDetailsDto.setLastModifiedByUsername(stmt.getString(6));
                            roleDetailsDto.setLastModifiedDate(stmt.getString(7));
                            roleDetailsDto.setLastVerifiedBy(stmt.getString(8));
                            roleDetailsDto.setVerifiedDate(stmt.getString(9));
                            roleDetailsDto.setPreOpr(stmt.getString(10));
                            roleDetailsDto.setVerifyStatus(stmt.getString(11));
                        }
                        responseDataDTO.setResult(roleDetailsDto);
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
