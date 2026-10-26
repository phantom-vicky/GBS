/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.dao.impl;

import com.isl.gbm.common.DateUtils;
import com.isl.gbm.common.audit.log.common.AuditLogConstants;
import com.isl.gbm.common.audit.log.common.OperationModes;
import com.isl.gbm.common.audit.log.dao.AuditLogDao;
import com.isl.gbm.common.audit.log.dto.AuditLogReportParams;
import com.isl.gbm.common.audit.log.model.AuditLog;
import com.isl.gbm.common.audit.log.model.ColumnDetails;
import com.isl.gbm.user.management.dao.HibernateDao;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The Class AuditLogDaoImpl.
 */
@Repository
public class AuditLogDaoImpl extends HibernateDao implements AuditLogDao {

    /**
     * The Constant AUTH_ID.
     */
    private static final String AUTH_ID = "authId";

    /**
     * The Constant AUTH_DATE.
     */
    private static final String AUTH_DATE = "authDate";

    /**
     * The Constant PERCENTAGE.
     */
    private static final String PERCENTAGE = "%";

    /**
     * The Constant OPERATION_MODE.
     */
    private static final String OPERATION_MODE = "operationMode";

    private static final String IS_DELETED = "isDeleted";

    private static final String SCHEME_CODE = "schemeCode";

    /**
     * The Constant IS_VERIFIED.
     */
    private static final String IS_VERIFIED = "isVerified";

    /**
     * The Constant LEVEL_CODE.
     */
    private static final String LEVEL_CODE = "levelCode";

    /**
     * The session factory.
     */
    private SessionFactory sessionFactory;

    @PersistenceContext
    EntityManager em;

    /**
     * Builds the authorized by predicate.
     *
     * @param auditLogReportParams  the audit log report params
     * @param builder               the builder
     * @param root                  the root
     * @param predicates            the predicates
     * @param includeSolIdCondition the include sol id condition
     */
    private void buildAuthorizedByPredicate(final AuditLogReportParams auditLogReportParams,
                                            final CriteriaBuilder builder, final Root<AuditLog> root, final List<Predicate> predicates,
                                            final boolean includeSolIdCondition) {
        if (StringUtils.isNotBlank(auditLogReportParams.getVerifiedBy())) {
            final Predicate authByPredicate = builder.like(builder.upper(root.get(AUTH_ID)),
                    PERCENTAGE + auditLogReportParams.getVerifiedBy().toUpperCase() + PERCENTAGE);
            predicates.add(authByPredicate);
        }

        if (null != auditLogReportParams.getAuthorizedFromDate()) {
            final Predicate onStart = builder.greaterThanOrEqualTo(root.get(AUTH_DATE),
                    DateUtils.getStartDate(auditLogReportParams.getAuthorizedFromDate()));
            predicates.add(onStart);

            if (includeSolIdCondition && StringUtils.isNotBlank(auditLogReportParams.getSolId())) {
                predicates.add(builder.equal(root.get("verifierSolId"), auditLogReportParams.getSolId()));
            }
        }
        if (null != auditLogReportParams.getAuthorizedToDate()) {
            final Predicate onEnd = builder.lessThanOrEqualTo(root.get(AUTH_DATE),
                    DateUtils.getEndDate(auditLogReportParams.getAuthorizedToDate()));
            predicates.add(onEnd);
        }

    }

    /**
     * Builds the created by predicate.
     *
     * @param auditLogReportParams  the audit log report params
     * @param builder               the builder
     * @param root                  the root
     * @param predicates            the predicates
     * @param includeSolIdCondition the include sol id condition
     */
    private void buildCreatedByPredicate(final AuditLogReportParams auditLogReportParams, final CriteriaBuilder builder,
                                         final Root<AuditLog> root, final List<Predicate> predicates, final boolean includeSolIdCondition) {
        if (StringUtils.isNotBlank(auditLogReportParams.getEnteredBy())) {
            final Predicate enteredByPredicate = builder.like(builder.upper(root.get("entererId")),
                    PERCENTAGE + auditLogReportParams.getEnteredBy().toUpperCase() + PERCENTAGE);
            predicates.add(enteredByPredicate);
        }
        if (null != auditLogReportParams.getCreatedFromDate()) {
            final Predicate onStart = builder.greaterThanOrEqualTo(root.get("entererDate"),
                    DateUtils.getStartDate(auditLogReportParams.getCreatedFromDate()));
            predicates.add(onStart);
            if (includeSolIdCondition && StringUtils.isNotBlank(auditLogReportParams.getSolId())) {
                predicates.add(builder.equal(root.get("entererSolId"), auditLogReportParams.getSolId()));
            }
        }
        if (null != auditLogReportParams.getCreatedToDate()) {
            final Predicate onEnd = builder.lessThanOrEqualTo(root.get("entererDate"),
                    DateUtils.getEndDate(auditLogReportParams.getCreatedToDate()));
            predicates.add(onEnd);
        }

    }

    /**
     * Builds the modified by predicate.
     *
     * @param auditLogReportParams  the audit log report params
     * @param builder               the builder
     * @param root                  the root
     * @param predicates            the predicates
     * @param includeSolIdCondition the include sol id condition
     */
    private void buildModifiedByPredicate(final AuditLogReportParams auditLogReportParams,
                                          final CriteriaBuilder builder, final Root<AuditLog> root, final List<Predicate> predicates,
                                          final boolean includeSolIdCondition) {
        if (StringUtils.isNotBlank(auditLogReportParams.getModifiedBy())) {
            final Predicate modifiedByPredicate = builder.like(builder.upper(root.get("lastModifiedBy")),
                    PERCENTAGE + auditLogReportParams.getModifiedBy().toUpperCase() + PERCENTAGE);
            predicates.add(modifiedByPredicate);
        }

        if (null != auditLogReportParams.getModifiedFromDate()) {
            final Predicate onStart = builder.greaterThanOrEqualTo(root.get("lastModifiedDate"),
                    DateUtils.getStartDate(auditLogReportParams.getModifiedFromDate()));
            predicates.add(onStart);

            if (includeSolIdCondition && StringUtils.isNotBlank(auditLogReportParams.getSolId())) {
                predicates.add(builder.equal(root.get("modifierSolId"), auditLogReportParams.getSolId()));
            }
        }
        if (null != auditLogReportParams.getModifiedToDate()) {
            final Predicate onEnd = builder.lessThanOrEqualTo(root.get("lastModifiedDate"),
                    DateUtils.getEndDate(auditLogReportParams.getModifiedToDate()));
            predicates.add(onEnd);
        }

    }

    /**
     * Builds the module name predicate.
     *
     * @param auditLogReportParams the audit log report params
     * @param builder              the builder
     * @param root                 the root
     * @param predicates           the predicates
     */
    private void buildModuleNamePredicate(final AuditLogReportParams auditLogReportParams,
                                          final CriteriaBuilder builder, final Root<AuditLog> root, final List<Predicate> predicates) {
        if (StringUtils.isNotBlank(auditLogReportParams.getModuleId())) {
            final Predicate operationModePredicate = builder.equal(builder.upper(root.get("moduleName")),
                    auditLogReportParams.getModuleId().toUpperCase());
            predicates.add(operationModePredicate);
        }
    }

    /**
     * Builds the operation mode predicate.
     *
     * @param auditLogReportParams the audit log report params
     * @param builder              the builder
     * @param root                 the root
     * @param predicates           the predicates
     */
    private void buildOperationModePredicate(final AuditLogReportParams auditLogReportParams,
                                             final CriteriaBuilder builder, final Root<AuditLog> root, final List<Predicate> predicates) {
        if (StringUtils.isNotBlank(auditLogReportParams.getOperation())) {
            final Predicate operationModePredicate = builder.equal(builder.upper(root.get(OPERATION_MODE)),
                    auditLogReportParams.getOperation().toUpperCase());
            predicates.add(operationModePredicate);
        }
    }

    /**
     * Builds the record type predicate.
     *
     * @param auditLogReportParams the audit log report params
     * @param builder              the builder
     * @param root                 the root
     * @param predicates           the predicates
     */
    private void buildRecordTypePredicate(final AuditLogReportParams auditLogReportParams,
                                          final CriteriaBuilder builder, final Root<AuditLog> root, final List<Predicate> predicates) {
        switch (auditLogReportParams.getIncludeVerifiedRecords()) {
            case UNVERIFIED:
                predicates.add(this.getUnverifiedRecordPredicate(builder, root));
                break;
            case VERIFIED:
                predicates.add(this.getVerifiedRecordPredicate(builder, root));
                break;
            case BOTH:
                final Predicate verifiedAndUnverifiedPredicate = builder.or(this.getVerifiedRecordPredicate(builder, root),
                        this.getUnverifiedRecordPredicate(builder, root));
                predicates.add(verifiedAndUnverifiedPredicate);
                break;
        }
    }

    /**
     * Builds the table name predicate.
     *
     * @param auditLogReportParams the audit log report params
     * @param builder              the builder
     * @param root                 the root
     * @param predicates           the predicates
     */
    private void buildTableNamePredicate(final AuditLogReportParams auditLogReportParams, final CriteriaBuilder builder,
                                         final Root<AuditLog> root, final List<Predicate> predicates) {
        if (StringUtils.isNotBlank(auditLogReportParams.getTableShortName())) {
            final Predicate tableNamePredicate = builder.like(builder.upper(root.get("tableName")),
                    PERCENTAGE + auditLogReportParams.getTableShortName().toUpperCase() + PERCENTAGE);
            predicates.add(tableNamePredicate);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditLogDao#delete(com.isl.gbm.audit.log.model.
     * AuditLog)
     */
    @Override
    public void delete(final AuditLog auditLog) {
        final Session session = getSession();
        auditLog.setDeleted(Boolean.TRUE);
        auditLog.setRemarks(OperationModes.DELETE.getOperationMode());
        auditLog.setOperationMode(OperationModes.DELETE.getOperationMode());
        auditLog.setRecordUpdatedDate(new Date());
        session.saveOrUpdate(auditLog);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditLogDao#execute(com.isl.gbm.audit.log.model.
     * AuditLog)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void execute(final AuditLog log) {
        final Session session = getSession();
        final StringBuilder builder = new StringBuilder();
        builder.append("update ").append(log.getTablePropertyName()).append(" set ");
        final List<ColumnDetails> detailsList = log.getModifiedFieldsData();
        String delimeter = "";
        final Map<String, Object> paramMap = new HashMap<>();
        for (final ColumnDetails details : detailsList) {
            builder.append(delimeter).append(details.getPropertyName()).append("= :").append(details.getPropertyName());
            paramMap.put(details.getPropertyName(), AuditLogDaoUtil.getValue(details));
            delimeter = ",";
        }
        builder.append(" where ").append(log.getTablePkPropertyName()).append("=").append(log.getTablePkColumnValue());
        final Query query = session.createQuery(builder.toString());
        for (final Entry<String, Object> entry : paramMap.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditLogDao#getActiveAuditLogDetails(java.lang.
     * String, java.lang.String, java.math.BigDecimal)
     */
    @Override
    public AuditLog getActiveAuditLogDetails(final String tablePropertyName, final String tablePkPropertyColumnName,
                                             final BigDecimal tablePkColumnValue) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<AuditLog> criteriaQuery = builder.createQuery(AuditLog.class);
        final Root<AuditLog> root = criteriaQuery.from(AuditLog.class);
        criteriaQuery.select(root).where(builder.equal(root.get(AuditLogConstants.TABLE_PROP_NAME), tablePropertyName),
                builder.equal(root.get(AuditLogConstants.TABLE_PK_PROP_NAME), tablePkPropertyColumnName),
                builder.equal(root.get(AuditLogConstants.TABLE_PK_COLUMN_VALUE), tablePkColumnValue),
                builder.equal(root.get(AuditLogConstants.DELETED), Boolean.FALSE));
        // Use criteria to query with session to fetch the record
        final AuditLog log = session.createQuery(criteriaQuery).uniqueResult();
        if (null != log) {
            session.evict(log);
        }
        return log;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditLogDao#getAuditLogDetails(java.lang.String,
     * java.lang.String, java.lang.Long)
     */
    @Override
    public AuditLog getAuditLogDetails(final String tablePropertyName, final String tablePkPropertyColumnName,
                                       final BigDecimal tablePkColumnValue) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<AuditLog> criteriaQuery = builder.createQuery(AuditLog.class);
        final Root<AuditLog> root = criteriaQuery.from(AuditLog.class);
        criteriaQuery.select(root).where(builder.equal(root.get(AuditLogConstants.TABLE_PROP_NAME), tablePropertyName),
                builder.equal(root.get(AuditLogConstants.TABLE_PK_PROP_NAME), tablePkPropertyColumnName),
                builder.equal(root.get(AuditLogConstants.TABLE_PK_COLUMN_VALUE), tablePkColumnValue));
        // Use criteria to query with session to fetch the record
        final AuditLog log = session.createQuery(criteriaQuery).uniqueResult();
        if (null != log) {
            session.evict(log);
        }
        return log;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.dao.AuditLogDao#getAuditLogDetailsById(java.math.
     * BigDecimal)
     */
    @Override
    public AuditLog getAuditLogDetailsById(final BigDecimal auditId) {
        final Session session = getSession();
        return session.get(AuditLog.class, auditId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditLogDao#getBranchLevelAuditReportDetails(com.
     * isl.gbm.audit.log.dto.AuditLogReportParams)
     */
    @Override
    public List<AuditLog> getBranchLevelAuditReportDetails(final AuditLogReportParams auditLogReportParams) {
        List<AuditLog> auditLogList = Collections.emptyList();
        final List<Predicate> predicates = new ArrayList<>(20);
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<AuditLog> criteriaQuery = builder.createQuery(AuditLog.class);
        final Root<AuditLog> root = criteriaQuery.from(AuditLog.class);
        // include record type condition
        this.buildRecordTypePredicate(auditLogReportParams, builder, root, predicates);
        // include entered by records condition
        this.buildCreatedByPredicate(auditLogReportParams, builder, root, predicates, Boolean.TRUE);
        // include modified by records condition
        this.buildModifiedByPredicate(auditLogReportParams, builder, root, predicates, Boolean.TRUE);
        // include verified by records condition
        this.buildAuthorizedByPredicate(auditLogReportParams, builder, root, predicates, Boolean.TRUE);
        // include table name condition
        this.buildTableNamePredicate(auditLogReportParams, builder, root, predicates);
        // include operation mode condition
        //this.buildOperationModePredicate(auditLogReportParams, builder, root, predicates);
        // include module name condition
        this.buildModuleNamePredicate(auditLogReportParams, builder, root, predicates);
        // Use criteria to query with session to fetch all accounts
        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[]{})));
        final Query<AuditLog> query = session.createQuery(criteriaQuery);

        if (CollectionUtils.isNotEmpty(predicates)) {
            auditLogList = query.getResultList();
        }
        return auditLogList;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditLogDao#getDcParameterizationDetails(com.isl.
     * gbm.audit.log.dto.AuditLogReportParams)
     */
    @Override
    public List<AuditLog> getDcParameterizationDetails(final AuditLogReportParams auditLogReportParams) {
        List<AuditLog> auditLogList = Collections.emptyList();
        final Session session = getSession();
        final List<Predicate> predicates = new ArrayList<>(20);
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<AuditLog> criteriaQuery = builder.createQuery(AuditLog.class);
        final Root<AuditLog> root = criteriaQuery.from(AuditLog.class);
        // include record type condition
        this.buildRecordTypePredicate(auditLogReportParams, builder, root, predicates);
        // include entered by records condition
        this.buildCreatedByPredicate(auditLogReportParams, builder, root, predicates, Boolean.FALSE);
        // include modified by records condition
        this.buildModifiedByPredicate(auditLogReportParams, builder, root, predicates, Boolean.FALSE);
        // include verified by records condition
        this.buildAuthorizedByPredicate(auditLogReportParams, builder, root, predicates, Boolean.FALSE);
        // include table name condition
        this.buildTableNamePredicate(auditLogReportParams, builder, root, predicates);
        // include operation mode condition
        //this.buildOperationModePredicate(auditLogReportParams, builder, root, predicates);
        // Use criteria to query with session to fetch all accounts
        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[]{})));
        final Query<AuditLog> query = session.createQuery(criteriaQuery);

        if (CollectionUtils.isNotEmpty(predicates)) {
            auditLogList = query.getResultList();
        }
        return auditLogList;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditLogDao#getGuLevelAuditReportDetails(com.isl.
     * gbm.audit.log.dto.AuditLogReportParams)
     */
    @Override
    public List<AuditLog> getGuLevelAuditReportDetails(final AuditLogReportParams auditLogReportParams) {
        return this.getBranchLevelAuditReportDetails(auditLogReportParams);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditLogDao#getHigherGuLevelAuditReportDetails(com.
     * isl.gbm.audit.log.dto.AuditLogReportParams)
     */
    @Override
    public List<AuditLog> getHigherGuLevelAuditReportDetails(final AuditLogReportParams auditLogReportParams) {
        return this.getBranchLevelAuditReportDetails(auditLogReportParams);
    }

    /**
     * Gets the unverified record predicate.
     *
     * @param builder the builder
     * @param root    the root
     * @return the unverified record predicate
     */
    private Predicate getUnverifiedRecordPredicate(final CriteriaBuilder builder, final Root<AuditLog> root) {
        final Predicate unVerifiedRecordPredicate = builder.and(builder.isNull(root.get(AUTH_DATE)),
                //builder.equal(root.get("deleted"), Boolean.FALSE),
                builder.notEqual(root.get(OPERATION_MODE), OperationModes.VERIFY.getOperationMode()));
        return unVerifiedRecordPredicate;
    }

    /**
     * Gets the verified record predicate.
     *
     * @param builder the builder
     * @param root    the root
     * @return the verified record predicate
     */
    private Predicate getVerifiedRecordPredicate(final CriteriaBuilder builder, final Root<AuditLog> root) {
        final Predicate verifiedRecordPredicate = builder.and(builder.isNotNull(root.get(AUTH_DATE)),
                builder.equal(root.get(OPERATION_MODE), OperationModes.VERIFY.getOperationMode()));
        return verifiedRecordPredicate;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditLogDao#revert(com.isl.gbm.audit.log.model.
     * AuditLog)
     */
    @Override
    public void revert(final AuditLog auditLog) {
        final Session session = getSession();
        auditLog.setDeleted(Boolean.TRUE);
        auditLog.setRemarks(OperationModes.REVERT.getOperationMode());
        auditLog.setOperationMode(OperationModes.REVERT.getOperationMode());
        auditLog.setRecordUpdatedDate(new Date());
        session.saveOrUpdate(auditLog);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.dao.AuditLogDao#save(com.isl.gbm.audit.log.model.
     * AuditLog)
     */
    @Override
    public void save(final AuditLog auditLog) {
        final Session session = getSession();
        auditLog.setRecordUpdatedDate(new Date());
        //this.sessionFactory.getCurrentSession().saveOrUpdate(auditLog);
        session.saveOrUpdate(auditLog);
    }


}
