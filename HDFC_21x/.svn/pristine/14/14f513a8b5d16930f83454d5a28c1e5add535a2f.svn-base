package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.dao.PasswordHistoryDao;
import com.isl.gbm.user.management.model.PasswordHistory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 * The Class PasswordHistoryDaoImpl.
 */
@Repository
public class PasswordHistoryDaoImpl extends HibernateDao implements PasswordHistoryDao {

    /**
     * The Constant USER_ID.
     */
    private static final String USERNAME = "username";

    /**
     * The Constant CREATED_DATE.
     */
    private static final String CREATED_DATE = "createdDate";


    /**
     * Sets the session factory.
     *
     * @paramsessionFactory the new session factory
     */


    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.PasswordHistoryDao#save(com.isl.gbm.user.management.model.PasswordHistory)
     */
    @Override
    public void save(PasswordHistory passwordHistory) {
        final Session session = getSession();
/*		sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
		this.sessionFactory.getCurrentSession().saveOrUpdate(passwordHistory);*/
        session.saveOrUpdate(passwordHistory);
        System.out.println("password history" + passwordHistory.getId());
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.PasswordHistoryDao#getPasswordHistory(int, int)
     */
    @Override
    public List<PasswordHistory> getPasswordHistory(String username, int limit) {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<PasswordHistory> criteriaQuery = builder.createQuery(PasswordHistory.class);
        final Root<PasswordHistory> root = criteriaQuery.from(PasswordHistory.class);
        criteriaQuery.select(root)
                .where(builder.equal(root.get(USERNAME), username))
                .orderBy(builder.desc(root.get(CREATED_DATE)));
        return session.createQuery(criteriaQuery).setMaxResults(limit).getResultList();
    }

}
