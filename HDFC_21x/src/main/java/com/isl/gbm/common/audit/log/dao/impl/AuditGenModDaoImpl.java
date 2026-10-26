/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.dao.impl;

import com.isl.gbm.common.audit.log.common.AuditLogConstants;
import com.isl.gbm.common.audit.log.common.OperationModes;
import com.isl.gbm.common.audit.log.dao.AuditGenModDao;
import com.isl.gbm.common.audit.log.model.AuditGenMod;
import com.isl.gbm.common.audit.log.model.ColumnDetails;
import com.isl.gbm.user.management.dao.HibernateDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The Class AuditGenModDaoImpl.
 */
@Repository
public class AuditGenModDaoImpl extends HibernateDao implements AuditGenModDao {

    /**
     * The session factory.
     */
    private SessionFactory sessionFactory;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditGenModDao#delete(com.isl.gbm.audit.log.model.
     * AuditGenMod)
     */
    @Override
    public void delete(final AuditGenMod auditGenMod) {
        final Session session = getSession();
        auditGenMod.setDeleted(Boolean.TRUE);
        auditGenMod.setRemarks(OperationModes.DELETE.getOperationMode());
        auditGenMod.setOperationMode(OperationModes.DELETE.getOperationMode());
        auditGenMod.setRecordUpdatedDate(new Date());
        session.saveOrUpdate(auditGenMod);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditGenModDao#execute(com.isl.gbm.audit.log.model.
     * AuditGenMod)
     */
    @Override
    public void execute(final AuditGenMod auditGenMod) {
        final Session session = getSession();
        final StringBuilder builder = new StringBuilder();
        builder.append("update ").append(auditGenMod.getTablePropertyName()).append(" set ");

        final List<ColumnDetails> detailsList = auditGenMod.getModifiedFieldsData();
        String delimeter = "";
        final Map<String, Object> paramMap = new HashMap<>();
        for (final ColumnDetails details : detailsList) {
            builder.append(delimeter).append(details.getPropertyName()).append("= :").append(details.getPropertyName());
            paramMap.put(details.getPropertyName(), AuditLogDaoUtil.getValue(details));
            delimeter = ",";
        }
        builder.append(" where ").append(auditGenMod.getTablePkPropertyName()).append("=")
                .append(auditGenMod.getTablePkColumnValue());
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
     * com.isl.gbm.audit.log.dao.AuditGenModDao#getAuditLogDetails(java.lang.String,
     * java.lang.String, java.lang.Long)
     */
    @Override
    public AuditGenMod getAuditGenModDetails(final String tablePropertyName, final String tablePkPropertyColumnName,
                                             final BigDecimal tablePkColumnValue) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<AuditGenMod> criteriaQuery = builder.createQuery(AuditGenMod.class);
        final Root<AuditGenMod> root = criteriaQuery.from(AuditGenMod.class);
        criteriaQuery.select(root).where(builder.equal(root.get(AuditLogConstants.TABLE_PROP_NAME), tablePropertyName),
                builder.equal(root.get(AuditLogConstants.TABLE_PK_PROP_NAME), tablePkPropertyColumnName),
                builder.equal(root.get(AuditLogConstants.TABLE_PK_COLUMN_VALUE), tablePkColumnValue),
                builder.equal(root.get(AuditLogConstants.DELETED), Boolean.FALSE));
        // Use criteria to query with session to fetch the record
        final AuditGenMod genMod = session.createQuery(criteriaQuery).uniqueResult();
        if (null != genMod) {
            session.evict(genMod);
        }
        return genMod;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditGenModDao#getAuditLogDetailsById(java.math.
     * BigDecimal)
     */
    @Override
    public AuditGenMod getAuditGenModDetailsById(final BigDecimal id) {
        final Session session = getSession();
        return session.get(AuditGenMod.class, id);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditGenModDao#revert(com.isl.gbm.audit.log.model.
     * AuditGenMod)
     */
    @Override
    public void revert(final AuditGenMod auditGenMod) {
        final Session session = getSession();
        auditGenMod.setDeleted(Boolean.TRUE);
        auditGenMod.setRemarks(OperationModes.REVERT.getOperationMode());
        auditGenMod.setOperationMode(OperationModes.REVERT.getOperationMode());
        auditGenMod.setRecordUpdatedDate(new Date());
        session.saveOrUpdate(auditGenMod);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.dao.AuditGenModDao#save(com.isl.gbm.audit.log.model.
     * AuditGenMod)
     */
    @Override
    public void save(final AuditGenMod auditGenMod) {
        auditGenMod.setRecordUpdatedDate(new Date());
        getSession().saveOrUpdate(auditGenMod);
    }

    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
