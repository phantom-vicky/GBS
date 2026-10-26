package com.isl.gbm.admin.dao.impl;

import com.isl.gbm.admin.dao.SackUserDao;
import com.isl.gbm.admin.model.SackUserMaster;
import com.isl.gbm.user.management.dao.HibernateDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SackUserDaoImpl extends HibernateDao implements SackUserDao {

    /**
     * The Constant USER_ID.
     */
    private static final String USER_ID = "userId";

    @Override

    public List<SackUserMaster> getSackUserList(final String userId, final String solId) {

        final Session session = getSession();

        final Query<Object[]> query = session.createQuery(
                "Select id, userId, solId, inTime, isDeleted From SackUserMaster Where isDeleted = :isDeleted and solId = :solId "
                        + "AND userId != :userId and TO_CHAR(inTime,'DD/MM/YYYY') = TO_CHAR(SYSDATE,'DD/MM/YYYY')  order by userId");

        query.setParameter("isDeleted", Boolean.FALSE);
        query.setParameter("solId", solId);
        query.setParameter(USER_ID, userId);

        final List<Object[]> rows = query.getResultList();
        final List<SackUserMaster> resultList = new ArrayList<>();
        for (final Object[] objArray : rows) {
            final SackUserMaster sackUserMaster = new SackUserMaster();
            sackUserMaster.setId((Integer) objArray[0]);
            sackUserMaster.setUserId((String) objArray[1]);
            sackUserMaster.setSolId((String) objArray[2]);
            sackUserMaster.setInTime((Date) objArray[3]);
            sackUserMaster.setDeleted((Boolean) objArray[4]);
            resultList.add(sackUserMaster);
        }
        return resultList;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.admin.dao.SackUserDao#getSolId(java.lang.String)
     */


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.admin.dao.SackUserDao#getUserId(java.lang.String)
     */
    @Override
    public SackUserMaster getUserId(final String userId) {

        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<SackUserMaster> criteria = builder.createQuery(SackUserMaster.class);
        final Root<SackUserMaster> root = criteria.from(SackUserMaster.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(USER_ID), userId));
        return session.createQuery(criteria).getResultList()
                .stream().findFirst().orElse(null);

    }


    @Override
    public void updateSackUserList(final String userId, final String solId, final List<SackUserMaster> profileMaster) {

        final Session session = getSession();
        for (final SackUserMaster master : profileMaster) {

            final Query<Object> query = session.createQuery(
                    "update SackUserMaster set isDeleted = :isDeleted, id = :id, outTime = :outTime  where userId = :userId ");
            query.setParameter("isDeleted", Boolean.TRUE);
            query.setParameter("id", master.getId());
            query.setParameter("outTime", master.getOutTime());
            query.setParameter(USER_ID, master.getUserId());
            query.executeUpdate();
        }
    }

}
