/**
 *
 */
package com.isl.gbm.transaction.channel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Class SequenceNumberView.
 *
 * @author Rahul.Kumar
 */
@Entity
@Table(name = "SEQN")
public class SequenceNumber {

    /** The next sequence number. */
    @Id
    @Column(name = "NEXT_SEQ_NO")
    private Long nextSequenceNumber;

    /** The sequence type. */
    @Column(name = "SQNC_TYPE")
    private String sequenceType;

    /**
     * Gets the next sequence number.
     *
     * @return the next sequence number
     */
    public Long getNextSequenceNumber() {
        return this.nextSequenceNumber;
    }

    /**
     * Gets the sequence type.
     *
     * @return the sequence type
     */
    public String getSequenceType() {
        return this.sequenceType;
    }

    /**
     * Sets the next sequence number.
     *
     * @param nextSequenceNumber
     *            the new next sequence number
     */
    public void setNextSequenceNumber(final Long nextSequenceNumber) {
        this.nextSequenceNumber = nextSequenceNumber;
    }

    /**
     * Sets the sequence type.
     *
     * @param sequenceType
     *            the new sequence type
     */
    public void setSequenceType(final String sequenceType) {
        this.sequenceType = sequenceType;
    }

}
