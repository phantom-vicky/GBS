/**
 *
 */
package com.isl.gbm.cbs.integration.dao.impl;

import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.user.management.dao.HibernateDao;
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
 * The Class GeneralSetupDaoImpl.
 *
 * @author harishbabu.t
 */
@Repository
public class GeneralSetupDaoImpl extends HibernateDao implements GeneralSetupDao {

    /** The session factory. */
    private SessionFactory sessionFactory;

    @PersistenceContext
    EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.GeneralSetupDao#findById(int)
     */
    @Override
    public GeneralSetupDetails findById(final int id) {
        final Session session = this.getSession();
        final GeneralSetupDetails details = session.get(GeneralSetupDetails.class, id);
        if (null != details) {
            session.evict(details);
        }
        return details;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.GeneralSetupDao#getGeneralSetupDetails()
     */
    @Override
    public List<GeneralSetupDetails> getGeneralSetupDetails() {
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<GeneralSetupDetails> criteriaQuery = builder.createQuery(GeneralSetupDetails.class);
        final Root<GeneralSetupDetails> generalSetupRoot = criteriaQuery.from(GeneralSetupDetails.class);
        criteriaQuery.select(generalSetupRoot);
        return em.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.GeneralSetupDao#getGeneralSetupDetails(
     * java.lang.String)
     */
    @Override
    public GeneralSetupDetails getGeneralSetupDetails(final String paramName) {
        /*final Session session = this.sessionFactory.getCurrentSession();*/
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<GeneralSetupDetails> criteriaQuery = builder.createQuery(GeneralSetupDetails.class);
        final Root<GeneralSetupDetails> generalSetupDetailsRoot = criteriaQuery.from(GeneralSetupDetails.class);
        criteriaQuery.select(generalSetupDetailsRoot).where(
                builder.equal(generalSetupDetailsRoot.get("parameterName"), paramName),
                builder.equal(generalSetupDetailsRoot.get("isVerified"), Boolean.TRUE));


        return (GeneralSetupDetails) em.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);
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
     * @see
     * com.isl.gbm.cbs.integration.dao.GeneralSetupDao#update(com.isl.gbm.cbs.
     * integration.model.GeneralSetupDetails)
     */
    @Override
    public void update(final List<GeneralSetupDetails> generalSetupDetailsList) {
        final Session session = getSession();
        for (final GeneralSetupDetails generalSetupDetails : generalSetupDetailsList) {
            //session.saveOrUpdate(generalSetupDetails);
            session.saveOrUpdate(generalSetupDetails);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.GeneralSetupDao#verify(java.util.List)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final List<GeneralSetupDetails> generalSetupDetailslist) {
        final Session session = this.getSession();
        final String HQL = "update GeneralSetupDetails set isVerified = :verified, verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where id = :id";
        for (final GeneralSetupDetails details : generalSetupDetailslist) {
            final Query query = (Query) session.createQuery(HQL);
            query.setParameter("verified", details.getIsVerified());
            query.setParameter("verifiedBy", details.getVerifiedBy());
            query.setParameter("verifiedDate", details.getVerifiedDate());
            query.setParameter("auditable", details.getAuditable());
            query.setParameter("id", details.getId());
            query.executeUpdate();
        }
    }

}
