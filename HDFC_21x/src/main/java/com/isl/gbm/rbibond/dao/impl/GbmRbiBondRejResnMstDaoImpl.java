package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.user.management.dao.HibernateDao;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class GbmRbiBondRejResnMstDaoImpl extends HibernateDao implements GbmRbiBondRejResnMstDao {

    @Override
    public boolean isRejectionRemarkInMaster(String rejectionRemarks) {
        if (rejectionRemarks == null || rejectionRemarks.isBlank()) {
            return false;
        }
        String normalized = rejectionRemarks.trim();
        return countBySubCodeDescIgnoreCase(normalized) > 0;
    }

    private long countBySubCodeDescIgnoreCase(String subCodeDesc) {
        Session session = getSession();
        return session
                .createQuery(
                        "select count(m) from GbmRbiBondRejResnMst m "
                                + "where lower(trim(m.subCodeDesc)) = lower(:subCodeDesc) "
                                + "and m.subCodeDesc is not null "
                                + "and (m.isDeleted is null or m.isDeleted = 'N')",
                        Long.class)
                .setParameter("subCodeDesc", subCodeDesc)
                .getSingleResult();
    }
}
