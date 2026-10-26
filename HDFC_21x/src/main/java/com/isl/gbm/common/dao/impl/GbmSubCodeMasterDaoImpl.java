package com.isl.gbm.common.dao.impl;

import com.isl.gbm.common.SchemeCodes;
import com.isl.gbm.common.dao.GbmSubCodeMasterDao;
import com.isl.gbm.common.model.GbmSubCodeMaster;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.user.management.dao.HibernateDao;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GbmSubCodeMasterDaoImpl extends HibernateDao implements GbmSubCodeMasterDao {

    /**
     * The Constant SUB_CODE.
     */
    private static final String SUB_CODE = "subCode";

    /**
     * The Constant COUNTRY_CODE.
     */
    private static final String COUNTRY_CODE_INDIA = "C001";

    /**
     * The Constant DELETED_FLAG.
     */
    private static final String DELETED_FLAG = "deleted";

    /**
     * The Constant CODE.
     */
    private static final String CODE = "code";

    /**
     * The Constant SCHEME_TYPE_1.
     */
    private static final String SCHEME_TYPE_1 = "code";

    /**
     * The Constant SCHEME_TYPE_2.
     */
    private static final String SCHEME_TYPE_2 = "code1";

    /**
     * The Constant SCHEME_TYPE_3.
     */
    private static final String SCHEME_TYPE_3 = "code2";

    @PersistenceContext
    EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.common.dao.GbmSubCodeMasterDao#getStateCodeDetails(java.lang.
     * String)
     */
    @Override
    public List<GbmSubCodeMaster> getCodeDetails(final String code) {
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder cBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<GbmSubCodeMaster> cQuery = cBuilder.createQuery(GbmSubCodeMaster.class);
        final Root<GbmSubCodeMaster> root = cQuery.from(GbmSubCodeMaster.class);
        cQuery.select(root);
        cQuery.where(cBuilder.equal(root.get(CODE), code), cBuilder.equal(root.get(DELETED_FLAG), CommonConstants.NO));
        final List<GbmSubCodeMaster> resultList = em.createQuery(cQuery).getResultList();
        return CollectionUtils.isEmpty(resultList) ? new ArrayList<>() : resultList;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.common.dao.GbmSubCodeMasterDao#getGspmSchemeDetails()
     */
    @Override
    public List<GbmSubCodeMaster> getGspmSchemeDetails() {
        //final Session session = this.sessionFactory.getCurrentSession();
        final Session session = getSession();
        final Query<GbmSubCodeMaster> query = session.createQuery(
                " from com.isl.gbm.common.model.GbmSubCodeMaster gscm where gscm.subCode in (:code,:code1,:code2) and gscm.deleted= :deleted "
                        + "order by gscm.code ",
                GbmSubCodeMaster.class);

        query.setParameter(SCHEME_TYPE_1, SchemeCodes.COMMON.getSchemeCode());
        query.setParameter(SCHEME_TYPE_2, SchemeCodes.CBDT.getSchemeCode());
        query.setParameter(SCHEME_TYPE_3, SchemeCodes.CBEC.getSchemeCode());
        query.setParameter(DELETED_FLAG, CommonConstants.NO);
        final List<GbmSubCodeMaster> serviceMasterList = query.getResultList();
        return CollectionUtils.isEmpty(serviceMasterList) ? new ArrayList<>() : serviceMasterList;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.common.dao.GbmSubCodeMasterDao#getPresentCountry()
     */
    @Override
    public GbmSubCodeMaster getPresentCountry() {
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<GbmSubCodeMaster> criteriaQuery = criteriaBuilder.createQuery(GbmSubCodeMaster.class);
        final Root<GbmSubCodeMaster> root = criteriaQuery.from(GbmSubCodeMaster.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get(SUB_CODE), COUNTRY_CODE_INDIA));
        final List<GbmSubCodeMaster> SubCodeMaster = em.createQuery(criteriaQuery).getResultList();
        return CollectionUtils.isEmpty(SubCodeMaster) ? new GbmSubCodeMaster() : SubCodeMaster.get(0);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.common.dao.GbmSubCodeMasterDao#getSchemeCodeDetails(java.lang
     * .String)
     */
    @Override
    public GbmSubCodeMaster getSubCodeDetails(final String subCode) {
        //final Session session = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder cBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<GbmSubCodeMaster> cQuery = cBuilder.createQuery(GbmSubCodeMaster.class);
        final Root<GbmSubCodeMaster> root = cQuery.from(GbmSubCodeMaster.class);
        cQuery.select(root);
        cQuery.where(cBuilder.equal(root.get(SUB_CODE), subCode),
                cBuilder.equal(root.get(DELETED_FLAG), CommonConstants.NO));
        final List<GbmSubCodeMaster> resultList = em.createQuery(cQuery).getResultList();
        return CollectionUtils.isEmpty(resultList) ? new GbmSubCodeMaster() : resultList.get(0);
    }


}
