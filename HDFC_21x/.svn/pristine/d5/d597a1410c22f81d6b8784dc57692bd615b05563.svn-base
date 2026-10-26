/**
 *
 */
package com.isl.gbm.common.dao.impl;

import com.isl.gbm.common.dao.NpsSystemParameterDao;
import com.isl.gbm.common.model.NpsSystemParameter;
import com.isl.gbm.common.model.NpsSystemParameterDetails;
import com.isl.gbm.user.management.dao.HibernateDao;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 * The Class NpsSystemParameterDaoImpl.
 *
 * @author harishbabu.t
 */
@Repository
public class NpsSystemParameterDaoImpl extends HibernateDao implements NpsSystemParameterDao {
    private static final String PARAMETER_ID = "parameterId";

    /** The session factory. */
    private SessionFactory sessionFactory;

    /** The Entity Manager. */
    @PersistenceContext
    EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.nps.dao.NpsSystemParameterDao#findById(java.lang.String)
     */
    @Override

    public NpsSystemParameter findById(final String id) {
        //final Session session = this.sessionFactory.getCurrentSession();
        final Session session = getSession();
        final NpsSystemParameter parameter = session.get(NpsSystemParameter.class, id);
        if (null != parameter) {
            session.evict(parameter);
        }
        return parameter;
    }

    @Override

    public NpsSystemParameter getSysParamDetails(final String parameterId) {
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        NpsSystemParameter returnValue = new NpsSystemParameter();
        final CriteriaQuery<NpsSystemParameter> criteria = builder.createQuery(NpsSystemParameter.class);
        final Root<NpsSystemParameter> npsSystemParameterRoot = criteria.from(NpsSystemParameter.class);
        criteria.select(npsSystemParameterRoot)
                .where(builder.equal(npsSystemParameterRoot.get(PARAMETER_ID), parameterId));
        final List<NpsSystemParameter> resultList = em.createQuery(criteria).getResultList();

        if (CollectionUtils.isNotEmpty(resultList)) {
            returnValue = resultList.get(0);
            em.detach(returnValue);
        }

        return returnValue;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.nps.dao.NpsSystemParameterDao#getSysParmDetailsList()
     */
    @Override
    public List<NpsSystemParameterDetails> getSysParamDetailsList() {
        //final Session session = this.sessionFactory.getCurrentSession();

        //final Session session =getSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<NpsSystemParameterDetails> criteria = builder.createQuery(NpsSystemParameterDetails.class);
        final Root<NpsSystemParameterDetails> npsSystemParameteDetrRoot = criteria
                .from(NpsSystemParameterDetails.class);
        criteria.select(npsSystemParameteDetrRoot);
        criteria.orderBy(builder.asc(npsSystemParameteDetrRoot.get("Id")));
        return em.createQuery(criteria).getResultList();
    }


    @Override
    public List<NpsSystemParameterDetails> getCommonDetailsList() {
        //final Session session = this.sessionFactory.getCurrentSession();

        //final Session session =getSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<NpsSystemParameterDetails> criteria = builder.createQuery(NpsSystemParameterDetails.class);
        final Root<NpsSystemParameterDetails> npsSystemParameteDetrRoot = criteria
                .from(NpsSystemParameterDetails.class);
        criteria.select(npsSystemParameteDetrRoot);
        criteria.where(builder.equal(npsSystemParameteDetrRoot.get("parameterId"), "SCH_CODE"));
        return em.createQuery(criteria).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.nps.dao.NpsSystemParameterDao#getSysParmList()
     */
    @Override

    public List<NpsSystemParameter> getSysParamList() {
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<NpsSystemParameter> criteria = builder.createQuery(NpsSystemParameter.class);
        final Root<NpsSystemParameter> npsSystemParameterRoot = criteria.from(NpsSystemParameter.class);
        criteria.select(npsSystemParameterRoot);
       criteria.orderBy(builder.asc(npsSystemParameterRoot.get("id")));
        return em.createQuery(criteria).getResultList();
    }


    public List<NpsSystemParameter> getCommonList() {
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<NpsSystemParameter> criteria = builder.createQuery(NpsSystemParameter.class);
        final Root<NpsSystemParameter> npsSystemParameterRoot = criteria.from(NpsSystemParameter.class);
        criteria.select(npsSystemParameterRoot);
        criteria.where(builder.equal(npsSystemParameterRoot.get("parameterId"), "SCH_CODE"));

        return em.createQuery(criteria).getResultList();
    }


    /**
     *
     * @param moduleId
     * @return
     */
    @Override
    public List<NpsSystemParameter> getSysParamModuleList(String moduleId) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<NpsSystemParameter> criteriaQuery = criteriaBuilder.createQuery(NpsSystemParameter.class);
        final Root<NpsSystemParameter> npsSystemParameterRoot = criteriaQuery.from(NpsSystemParameter.class);
        criteriaQuery.select(npsSystemParameterRoot);
        criteriaQuery.where(criteriaBuilder.equal(npsSystemParameterRoot.get("schCode"), moduleId));
        criteriaQuery.orderBy(criteriaBuilder.asc(npsSystemParameterRoot.get("id")));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Sets the session factory.
     *
     * @param sessionFactory
     *            the sessionFactory to set
     */
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.nps.dao.NpsSystemParameterDao#updateSysParam(java.util.List)
     */
    @Override

    public void updateSysParam(final List<NpsSystemParameter> npsSysParamDtoList) {
        //final Session session = this.sessionFactory.getCurrentSession();
        final Session session = getSession();
        for (final NpsSystemParameter sysParam : npsSysParamDtoList) {
            session.update(sysParam);
        }
    }

    @SuppressWarnings("rawtypes")
    @Override

    public void verifySysParam(final List<NpsSystemParameter> npsSysParamDtoList) {
        //final Session session = this.sessionFactory.getCurrentSession();

        final Session session = getSession();
        final String HQL = "update NpsSystemParameter set verified = :verified, verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where id = :id";
        for (final NpsSystemParameter parameter : npsSysParamDtoList) {
            final Query query = session.createQuery(HQL);
            query.setParameter("verified", parameter.getVerified());
            query.setParameter("verifiedBy", parameter.getVerifiedBy());
            query.setParameter("verifiedDate", parameter.getVerifiedDate());
            query.setParameter("auditable", parameter.getAuditable());
            query.setParameter("id", parameter.getId());

            query.executeUpdate();
        }
    }

}
