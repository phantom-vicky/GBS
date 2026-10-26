package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.user.management.constants.UserConstants;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.dao.PermissionDao;
import com.isl.gbm.user.management.dto.PermissionMenuLinkDTO;
import com.isl.gbm.user.management.model.Permission;
import com.isl.gbm.user.management.model.PermissionType;
import com.isl.gbm.user.management.util.ProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
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
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Class UserPermissionDaoImpl.
 */
@Repository
public class PermissionDaoImpl extends HibernateDao implements PermissionDao {

    private static final Logger log = LoggerFactory.getLogger(PermissionDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    /**
     * The Constant DISPLAY_NAME.
     */
    private static final String DISPLAY_NAME = "displayName";

    /**
     * The Constant ID.
     */
    private static final String ID = "id";

    /**
     * The Constant FEATURE_ID.
     */
    private static final String FEATURE_ID = "featureId";

    /**
     * The Constant TYPE.
     */
    private static final String TYPE = "type";

    @PersistenceContext
    EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.user.management.dao.UserPermissionDao#createPermission(com.isl.
     * gbm.user.management.model.Permission)
     */
    @Override
    public void savePermission(final Permission permission) {
        final Session session = getSession();
       /* sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
		this.sessionFactory.getCurrentSession().saveOrUpdate(permission);*/
        session.saveOrUpdate(permission);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.user.management.dao.UserPermissionDao#getPermissionByName(java.
     * lang.String)
     */
    @Override
    public Permission getPermissionByName(String permissionName) {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        // Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Permission> criteriaQuery = builder.createQuery(Permission.class);
        final Root<Permission> root = criteriaQuery.from(Permission.class);
        criteriaQuery.select(root).where(builder.equal(root.get(DISPLAY_NAME), permissionName));
        return (Permission) em.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);

    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.PermissionDao#getPermissions()
     */
    @Override
    public List<Permission> getPermissions() {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<Permission> criteriaQuery = builder.createQuery(Permission.class);
        final Root<Permission> root = criteriaQuery.from(Permission.class);
        criteriaQuery.select(root).where(builder.notEqual(root.get(DISPLAY_NAME), "*"));
        return (List<Permission>) em.createQuery(criteriaQuery.select(root)).getResultList();
    }

    @Override
    public Permission getPermission(Integer id) {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Permission> criteriaQuery = builder.createQuery(Permission.class);
        final Root<Permission> root = criteriaQuery.from(Permission.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), id));
        return (Permission) em.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);

    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.PermissionDao#getPermissionTypes()
     */
    @Override
    public List<PermissionType> getPermissionTypes() {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<PermissionType> criteriaQuery = builder.createQuery(PermissionType.class);
        final Root<PermissionType> root = criteriaQuery.from(PermissionType.class);

        return em.createQuery(criteriaQuery.select(root)).getResultList();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.PermissionDao#deletePermission(java.lang.Integer)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void deletePermission(Integer id) {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        final Session session = getSession();
        Query query = session.createQuery("delete from RolePermission where permission_id= :id");
        query.setParameter("id", id);
        query.executeUpdate();
        Object persistentInstance = session.load(Permission.class, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
        }
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.PermissionDao#getEnquiryPermissionByFeature(java.lang.Integer)
     */
    @Override
    public Permission getEnquiryPermissionByFeature(Integer featureId) {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Permission> criteriaQuery = builder.createQuery(Permission.class);
        final Root<Permission> root = criteriaQuery.from(Permission.class);
        criteriaQuery.select(root).where(builder.equal(root.get(FEATURE_ID), featureId), builder.equal(root.get(TYPE), "ENQUIRY"));
        return (Permission) em.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);

    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.PermissionDao#getParentPermissions()
     */
    @Override
    public List<Permission> getParentPermissions() {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Permission> criteriaQuery = builder.createQuery(Permission.class);
        final Root<Permission> root = criteriaQuery.from(Permission.class);

        Expression<String> exp = root.get(DISPLAY_NAME);
        List<String> parentPermissions = new ArrayList<>();
        parentPermissions.addAll(UserConstants.PARENT_PERMISISONS);
        Predicate predicate = exp.in(parentPermissions);
        criteriaQuery.select(root).where(predicate);
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public ResponseDataDTO getMenuLinks() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_MENU_LINKS.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.registerOutParameter("P_USER_MENU_OUT", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject("P_USER_MENU_OUT");

                    if (resultSet != null) {
                        PermissionMenuLinkDTO permissionMenuLinkDTO = null;
                        Map<String, PermissionMenuLinkDTO> linksDataMap = new LinkedHashMap<>();

                        while (resultSet.next()) {
                            if (!linksDataMap.containsKey(resultSet.getString("ID"))) {
                                permissionMenuLinkDTO = new PermissionMenuLinkDTO();
                                permissionMenuLinkDTO.setLevel(resultSet.getString("DISPLY_LEVEL"));
                                permissionMenuLinkDTO.setLinkId(resultSet.getString("ID"));
                                permissionMenuLinkDTO.setLinkName(resultSet.getString("MENU_NAME"));
                                permissionMenuLinkDTO.setParentLinkId(resultSet.getString("PARENT_ID"));
                                permissionMenuLinkDTO.setMenuUrl(resultSet.getString("URL"));
                                permissionMenuLinkDTO.setIsLink(resultSet.getString("IS_LINK"));
                                permissionMenuLinkDTO.setOrderId(resultSet.getString("ORDER_ID"));
                                linksDataMap.put(resultSet.getString("ID"), permissionMenuLinkDTO);
                            }
                        }

                        for (PermissionMenuLinkDTO item : linksDataMap.values()) {
                            if (Integer.valueOf(item.getParentLinkId()) > 0) {
                                if (linksDataMap.get(item.getParentLinkId()).getChildren() == null)
                                    linksDataMap.get(item.getParentLinkId()).setChildren(new ArrayList<>());
                                linksDataMap.get(item.getParentLinkId()).getChildren().add(item);
                            }
                        }
                        Map<String, Object> resMap = new HashMap<>();
                        resMap.put("menuItemList", linksDataMap.values().stream().filter(item -> item.getLevel().equals("1")).collect(Collectors.toList()));

                        responseDataDTO.setResult(resMap);
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public Collection<? extends String> getPermissionName() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        //ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Set<String> linksDataSet = new HashSet<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_PERMISSION_NAMES.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString("P_SOL_ID_IN", UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_CURSOR_OUT, OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject(CommonConstants.PROC_PARAM_CURSOR_OUT);

                    if (resultSet != null) {
                        resultSet.setFetchSize(4000);
                        while (resultSet.next()) {
                            linksDataSet.add(resultSet.getString("DISPLAY_NAME"));
                        }
                        //responseDataDTO.setResult(linksDataSet);
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        //responseDataDTO.setMessage(resultMsg);
        return linksDataSet;
    }

}
