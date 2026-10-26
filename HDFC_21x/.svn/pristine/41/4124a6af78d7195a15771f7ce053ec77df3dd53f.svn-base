package com.isl.gbm.admin.dao.impl;

import com.isl.gbm.admin.dao.GbmBondBulkIntPmtDao;
import com.isl.gbm.admin.model.SackUserMaster;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GbmBondBulkIntPmtDaoImpl implements GbmBondBulkIntPmtDao {

    /**
     * The remarks.
     */
    private static final String USER_REMARKS = "Abnormal Termination";

    /**
     * The session factory.
     */
    private SessionFactory sessionFactory;

    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.admin.dao.GbmBondBulkIntPmtDao#updateGbmBondBulkIntPmt(java.
     * lang.String, java.lang.String, java.util.List)
     */
    @Override
    @SuppressWarnings("rawtypes")
    public void updateGbmBondBulkIntPmt(final String userId, final String solId, List<SackUserMaster> paymentMaster) {
        final Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "UPDATE GbmBondBulkMaster  SET  isDeleted = :isDeletedYes, remarks = :remarks WHERE  solId = :solId AND paymentStatus = 'I' AND	   isDeleted = :isDeletedNo  "
                + " AND	   initiated_by NOT IN (SELECT userId FROM   SackUserMaster WHERE  solId = :solId  "
                + " AND	   isDeleted = :isDeletedPay ) ";
        for (final SackUserMaster master : paymentMaster) {

            final Query query = session.createQuery(sqlQuery);
            query.setParameter("isDeletedYes", Boolean.TRUE);
            query.setParameter("remarks", USER_REMARKS);
            query.setParameter("solId", master.getSolId());
            query.setParameter("isDeletedNo", Boolean.FALSE);
            query.setParameter("solId", master.getSolId());
            query.setParameter("isDeletedPay", Boolean.FALSE);
            query.executeUpdate();
        }
    }


}
