/*
 *
 */
package com.isl.gbm.cbs.integration.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * The Class SchemeCodes.
 */
@Entity
@Table(name = "GBM_SOL_DATE")
@DynamicUpdate
@SelectBeforeUpdate
public class GbmSolDate implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The solId.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SOL_DATE")
    @SequenceGenerator(name = "SEQ_SOL_DATE", sequenceName = "SEQ_SOL_DATE", allocationSize = 1)
    private Integer id;

    /**
     * The sol date.
     */
    @Column(name = "SOL_DATE")
    //@Temporal(TemporalType.DATE)
    private Date solDate;

    /**
     * The modified date.
     */
    @Column(name = "MODIFIED_DATE", updatable = true)
    private Date modifiedDate;

    /**
     * The add text 1.
     */
    @Column(name = "ADDL_TEXT1")
    private String addlText1;

    /**
     * The add text 2.
     */
    @Column(name = "ADDL_TEXT2")
    private String addlText2;

    /**
     * The add text 3.
     */
    @Column(name = "ADDL_TEXT3")
    private String addlText3;

    /**
     * Gets the adds the text 1.
     *
     * @return the adds the text 2
     */
    public String getAddlText1() {
        return this.addlText1;
    }

    /**
     * Gets the adds the text 2.
     *
     * @return the adds the text 2
     */
    public String getAddlText2() {
        return this.addlText2;
    }

    /**
     * Gets the adds the text 3.
     *
     * @return the adds the text 3
     */
    public String getAddlText3() {
        return this.addlText3;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    /**
     * Gets the sol date.
     *
     * @return the sol date
     */
    public Date getSolDate() {
        return this.solDate;
    }

    /**
     * Sets the adds the text 1.
     *
     * @param addlText1 the new addl text 1
     */
    public void setAddlText1(final String addlText1) {
        this.addlText1 = addlText1;
    }

    /**
     * Sets the adds the text 2.
     *
     * @param addlText2 the new addl text 2
     */
    public void setAddlText2(final String addlText2) {
        this.addlText2 = addlText2;
    }

    /**
     * Sets the adds the text 3.
     *
     * @param addlText3 the new addl text 3
     */
    public void setAddlText3(final String addlText3) {
        this.addlText3 = addlText3;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the new modified date
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Sets the sol date.
     *
     * @param solDate the new sol date
     */
    public void setSolDate(final Date solDate) {
        this.solDate = solDate;
    }

}
