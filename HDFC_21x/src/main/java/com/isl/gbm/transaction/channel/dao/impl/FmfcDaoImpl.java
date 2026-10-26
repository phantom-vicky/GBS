/**
 *
 */
package com.isl.gbm.transaction.channel.dao.impl;

import com.isl.gbm.transaction.channel.dao.FmfcDao;
import com.isl.gbm.transaction.channel.model.Fmfc;
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
 * The Class FmfcDaoImpl.
 *
 * @author Rahul.Kumar
 */
@Repository
public class FmfcDaoImpl extends HibernateDao implements FmfcDao {

    /** The Constant PRODUCT_PROCESSOR_ID. */
    private static final String PRODUCT_PROCESSOR_ID = "productProcessorId";

    /** The Constant REQUEST_TYPE. */
    private static final String REQUEST_TYPE = "requestType";

    /** The Constant IS_REQUEST. */
    private static final String IS_REQUEST = "isRequest";

    /** The Constant FIELD_ID. */
    private static final String FIELD_ID = "fieldId";

    /** The Constant SUB_FIELD_ID. */
    private static final String SUB_FIELD_ID = "subFieldId";

    /** The Constant REC_FIELD_ID. */
    private static final String REC_FIELD_ID = "recFieldId";

    /** The session factory. */
    private SessionFactory sessionFactory;

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.common.dao.FmfcDao#getMessageFormatConfigDetails()
     */
    @Override
    public List<Fmfc> getMessageFormatConfigDetails() {

        final Session session = getSession();
        final CriteriaBuilder cBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<Fmfc> cQuery = cBuilder.createQuery(Fmfc.class);
        final Root<Fmfc> root = cQuery.from(Fmfc.class);
        cQuery.select(root);
        cQuery.where(cBuilder.equal(root.get(PRODUCT_PROCESSOR_ID), "BANCS_CONNECT"),
                cBuilder.equal(root.get(REQUEST_TYPE), "BC_REQ"), cBuilder.equal(root.get(IS_REQUEST), "Y"));
        cQuery.orderBy(cBuilder.asc(root.get(FIELD_ID)), cBuilder.asc(root.get(SUB_FIELD_ID)),
                cBuilder.asc(root.get(REC_FIELD_ID)));
        final List<Fmfc> resultList = session.createQuery(cQuery).getResultList();
        return (CollectionUtils.isEmpty(resultList) ? null : resultList);
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
