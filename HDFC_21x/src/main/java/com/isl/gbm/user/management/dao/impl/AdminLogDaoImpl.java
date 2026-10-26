package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.common.DateUtils;
import com.isl.gbm.user.management.dao.AdminLogDao;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.model.AdminLog;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * The Class AdminLogDaoImpl.
 */
@Repository
public class AdminLogDaoImpl extends HibernateDao implements AdminLogDao {

    private static final String CREATED_TIME = "createdTime";


    @Override
    public void save(AdminLog adminLog) {
        getSession().saveOrUpdate(adminLog);
    }

    @Override
    public List<AdminLog> getAdminActivityLogs(Date date) {
        //sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<AdminLog> criteriaQuery = builder.createQuery(AdminLog.class);
        final Root<AdminLog> root = criteriaQuery.from(AdminLog.class);
        criteriaQuery.select(root).where(builder.between(root.get(CREATED_TIME), DateUtils.getStartDate(date),
                DateUtils.getEndDate(date)));
        return session.createQuery(criteriaQuery).getResultList();

    }

}
