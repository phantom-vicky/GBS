/**
 *
 */
package com.isl.gbm.transaction.channel.dao.impl;

import com.isl.gbm.transaction.channel.dao.GbmFlagsDao;
import com.isl.gbm.transaction.channel.model.GbmFlags;
import com.isl.gbm.user.management.dao.HibernateDao;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * The Class GbmFlagsDaoImpl.
 *
 * @author Rahul.Kumar
 */
@Repository
public class GbmFlagsDaoImpl extends HibernateDao implements GbmFlagsDao {

    /** The session factory. */
    private SessionFactory sessionFactory;

    /**
     * Gets the flag details.
     *
     * @return the flag details
     */
    @Override
    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.transaction.dao.GbmFlagsDao#getFlagDetails()
     */

    public List<GbmFlags> getFlagDetails() {
        final Session session = getSession();
        final CriteriaBuilder cBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<GbmFlags> cQuery = cBuilder.createQuery(GbmFlags.class);
        final Root<GbmFlags> root = cQuery.from(GbmFlags.class);
        cQuery.select(root);
        return CollectionUtils.isEmpty(session.createQuery(cQuery).getResultList()) ? Collections.emptyList()
                : session.createQuery(cQuery).getResultList();
    }

    /**
     * Sets the session factory.
     *
     * @param sessionFactory
     *            the new session factory
     */
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
