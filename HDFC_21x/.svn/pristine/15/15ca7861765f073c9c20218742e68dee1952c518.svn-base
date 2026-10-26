package com.isl.gbm.common.dao.impl;

import com.isl.gbm.common.dao.GbmSchemeParameterMasterDao;
import com.isl.gbm.common.model.GbmSchemeParameterMaster;
import com.isl.gbm.user.management.dao.HibernateDao;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class GbmSchemeParameterMasterDaoImpl extends HibernateDao implements GbmSchemeParameterMasterDao {


    /**
     * The Constant PROPERTY_ACTIVE.
     */
    private static final String PROPERTY_ACTIVE = "active";

    /**
     * The Constant SCHEME_CODE.
     */
    private static final String SCHEME_CODE = "schemeType";

    private static final String PARAM_NAME = "parameterName";

    /**
     * The Constant YES.
     */
    private static final Character YES = 'Y';

    /**
     * The session factory.
     */
    private SessionFactory sessionFactory;

    @PersistenceContext
    EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.common.dao.GbmSchemeParameterMasterDao#findById(java.math.
     * BigDecimal)
     */
    @Override
    public GbmSchemeParameterMaster findById(final BigDecimal id) {
        //final Session session = this.sessionFactory.getCurrentSession();
        final Session session = getSession();
        final GbmSchemeParameterMaster param = session.get(GbmSchemeParameterMaster.class, id);
        if (null != param) {
            session.evict(param);
        }
        return param;
    }

    @Override
    public GbmSchemeParameterMaster getSysParamDetails(final String paramName) {
        //final Session session = this.sessionFactory.getCurrentSession();
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        GbmSchemeParameterMaster returnValue = new GbmSchemeParameterMaster();
        final CriteriaQuery<GbmSchemeParameterMaster> criteria = builder.createQuery(GbmSchemeParameterMaster.class);
        final Root<GbmSchemeParameterMaster> npsSystemParameterRoot = criteria.from(GbmSchemeParameterMaster.class);
        criteria.select(npsSystemParameterRoot)
                .where(builder.equal(npsSystemParameterRoot.get(PARAM_NAME), paramName), builder.equal(npsSystemParameterRoot.get(PROPERTY_ACTIVE), YES));
        final List<GbmSchemeParameterMaster> resultList = session.createQuery(criteria).getResultList();

        if (CollectionUtils.isNotEmpty(resultList)) {
            returnValue = resultList.get(0);
            session.evict(returnValue);
        }

        return returnValue;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.common.dao.GbmSchemeParameterMasterDao#
     * getGeneralSchemeParameterMaintenance(java.lang.String)
     */
    @Override
    public List<GbmSchemeParameterMaster> getGeneralSchemeParameterMaintenance(final String schemeType) {
        //final Session session = this.sessionFactory.getCurrentSession();
        final Session session = getSession();
        final Query<GbmSchemeParameterMaster> query = session
                .createQuery("FROM GbmSchemeParameterMaster WHERE schemeCode = :schemeType AND active = :active");
        query.setParameter(PROPERTY_ACTIVE, YES);
        query.setParameter(SCHEME_CODE, schemeType);
        return query.list();

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.common.dao.GbmSchemeParameterMasterDao#update(java.util.List)
     */
    @Override
    public void update(final List<GbmSchemeParameterMaster> modelList) {
        //final Session session = this.sessionFactory.getCurrentSession();
        final Session session = getSession();
        for (final GbmSchemeParameterMaster param : modelList) {
            session.saveOrUpdate(param);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.common.dao.GbmSchemeParameterMasterDao#verify(java.util.List)
     */
    @Override
    public void verify(final List<GbmSchemeParameterMaster> modelList) {
        //final Session session = this.sessionFactory.getCurrentSession();
        final Session session = getSession();
        final String HQL = "update GbmSchemeParameterMaster set verified = :verified, verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable=:auditable  where id = :id";
        for (final GbmSchemeParameterMaster parameter : modelList) {
            final Query<GbmSchemeParameterMaster> query = session.createQuery(HQL);
            query.setParameter("verified", parameter.getVerified());
            query.setParameter("verifiedBy", parameter.getVerifiedBy());
            query.setParameter("verifiedDate", parameter.getVerifiedDate());
            query.setParameter("auditable", parameter.getAuditable());
            query.setParameter("id", parameter.getId());

            query.executeUpdate();
        }

    }

}
