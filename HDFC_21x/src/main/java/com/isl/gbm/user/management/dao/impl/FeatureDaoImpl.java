package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.user.management.dao.FeatureDao;
import com.isl.gbm.user.management.model.Feature;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 * The Class FeatureDaoImpl.
 */

@Repository
public class FeatureDaoImpl implements FeatureDao {


    /**
     * The Constant MODULE_ID.
     */
    private static final String MODULE_ID = "moduleId";

    /**
     * The Constant STATUS.
     */
    private static final String STATUS = "status";

    /**
     * The Constant NAME.
     */
    private static final String NAME = "name";

    /**
     * The Constant ID.
     */
    private static final String ID = "id";
    @PersistenceContext
    EntityManager em;

    /**
     * Sets the session factory.
     *
     * @paramsessionFactory the new session factory
     */


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.user.management.dao.FeatureDao#getFeatures(java.lang.String)
     */
    @Override
    public List<Feature> getFeatures(String moduleId) {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<Feature> criteriaQuery = builder.createQuery(Feature.class);
        final Root<Feature> root = criteriaQuery.from(Feature.class);

        criteriaQuery.select(root).where(builder.equal(root.get(STATUS), 1),
                builder.equal(root.get(MODULE_ID), moduleId));

        return em.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.user.management.dao.FeatureDao#isValidFeatureId(int)
     */
    @Override
    public Boolean isValidFeatureId(int featureId) {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<Feature> criteriaQuery = builder.createQuery(Feature.class);
        final Root<Feature> root = criteriaQuery.from(Feature.class);

        criteriaQuery.select(root).where(builder.equal(root.get(ID), featureId));
        return CollectionUtils.size(em.createQuery(criteriaQuery).getResultList()) > 0;
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.FeatureDao#getFeatureByName(java.lang.String)
     */
    @Override
    public Feature getFeatureByName(String featureName) {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<Feature> criteriaQuery = builder.createQuery(Feature.class);
        final Root<Feature> root = criteriaQuery.from(Feature.class);

        criteriaQuery.select(root).where(builder.equal(root.get(NAME), featureName));
        return (Feature) em.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);

    }

}
