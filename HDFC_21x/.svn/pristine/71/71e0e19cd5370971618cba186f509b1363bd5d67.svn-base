/**
 *
 */
package com.isl.gbm.transaction.channel.dao.impl;

import com.isl.gbm.transaction.channel.dao.CurrencyDecimalDao;
import com.isl.gbm.transaction.channel.model.CurrencyDecimal;
import com.isl.gbm.user.management.dao.HibernateDao;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 * The Class CurrencyDecimalDaoImpl.
 *
 * @author Rahul.Kumar
 */
@Repository

public class CurrencyDecimalDaoImpl extends HibernateDao implements CurrencyDecimalDao {

    /** The session factory. */
    private SessionFactory sessionFactory;

    /*
     * (non-Javadoc)
     * @see
     * com.isl.gbm.transaction.dao.CurrencyDecimalDao#getCurrencyDecimalDetails(
     * )
     */
    @Override
    public List<CurrencyDecimal> getCurrencyDecimalDetails() {
        final Session session = getSession();
        final CriteriaBuilder cBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<CurrencyDecimal> cQuery = cBuilder.createQuery(CurrencyDecimal.class);
        final Root<CurrencyDecimal> root = cQuery.from(CurrencyDecimal.class);
        cQuery.select(root);
        return (CollectionUtils.isEmpty(session.createQuery(cQuery).getResultList()) ? null
                : session.createQuery(cQuery).getResultList());
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
