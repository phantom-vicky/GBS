package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.dao.UserParamDao;
import com.isl.gbm.user.management.model.UserParam;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

/**
 * The Class UserParamDaoImpl.
 */

@Repository
public class UserParamDaoImpl extends HibernateDao implements UserParamDao {


    @PersistenceContext
    EntityManager em;


    /* (non-Javadoc)
     * @see com.afl.gbm.user.management.dao.UserParamDao#save(com.afl.gbm.user.management.model.UserParam)
     */
    @Override
    public void save(UserParam userParam) {
        this.getSession().merge(userParam);
    }

    /* (non-Javadoc)
     * @see com.afl.gbm.user.management.dao.UserParamDao#getUserParam()
     */
    @Override
    public UserParam getUserParam() {
        final Session session = this.getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<UserParam> criteriaQuery = builder.createQuery(UserParam.class);
        final Root<UserParam> root = criteriaQuery.from(UserParam.class);
        return (UserParam) em.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);

    }

}
