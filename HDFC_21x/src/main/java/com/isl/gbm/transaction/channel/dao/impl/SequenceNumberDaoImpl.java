/**
 *
 */
package com.isl.gbm.transaction.channel.dao.impl;

import com.isl.gbm.transaction.channel.dao.SequenceNumberDao;
import com.isl.gbm.transaction.channel.model.SequenceNumber;
import com.isl.gbm.user.management.dao.HibernateDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

/**
 * The Class SequenceNumberDaoImpl.
 *
 * @author Rahul.Kumar
 */
@Repository
public class SequenceNumberDaoImpl extends HibernateDao implements SequenceNumberDao {

    /** The Constant SEQUENCE_TYPE. */
    private static final String SEQUENCE_TYPE = "sequenceType";

    /** The Constant NEXT_SEQUNECE_NUMBER. */
    private static final String NEXT_SEQUNECE_NUMBER = "nextSequenceNumber";

    /** The Constant CONSTANT_FT_SEQUENCE. */
    private static final String CONSTANT_FT_SEQUENCE = "FT_SEQUENCE";

    /** The session factory. */
    private SessionFactory sessionFactory;

    /**
     * Gets the sequence number.
     *
     * @return the sequence number
     */
    private Long getSequenceNumber() {

        final Session session = getSession();
        final CriteriaBuilder cBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<SequenceNumber> cQuery = cBuilder.createQuery(SequenceNumber.class);
        final Root<SequenceNumber> root = cQuery.from(SequenceNumber.class);
        cQuery.select(root);
        cQuery.where(cBuilder.equal(root.get(SEQUENCE_TYPE), CONSTANT_FT_SEQUENCE));
        SequenceNumber sequenceNumber = session.createQuery(cQuery).getResultList()
                .stream().findFirst().orElse(null);
        return sequenceNumber != null ? sequenceNumber.getNextSequenceNumber() : 0l;

    }


    /*
     * (non-Javadoc)
     * @see
     * com.isl.gbm.transaction.dao.SequenceNumberDao#updateAndGetSequenceNumber(
     * )
     */
    @Override

    public Long updateAndGetSequenceNumber() {
        final Long sequenceNumber = this.getSequenceNumber();
        final Long nextSequenceNumber = sequenceNumber + 1;
        this.updateSequenceNumber(nextSequenceNumber);
        return this.getSequenceNumber();
    }

    /**
     * Update sequence number.
     *
     * @param nextSequenceNumber
     *            the next sequence number
     */
    private void updateSequenceNumber(final Long nextSequenceNumber) {

        final Session session = getSession();
        final CriteriaBuilder cBuilder = session.getCriteriaBuilder();
        final CriteriaUpdate<SequenceNumber> cUpdate = cBuilder.createCriteriaUpdate(SequenceNumber.class);
        final Root<SequenceNumber> root = cUpdate.from(SequenceNumber.class);
        cUpdate.set(root.get(NEXT_SEQUNECE_NUMBER), nextSequenceNumber);
        cUpdate.where(cBuilder.equal(root.get(SEQUENCE_TYPE), CONSTANT_FT_SEQUENCE));
        session.createQuery(cUpdate).executeUpdate();
    }

}
