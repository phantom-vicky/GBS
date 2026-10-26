/**
 *
 */
package com.isl.gbm.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 * The Class NpsSystemParameterDetails.
 *
 * @author harishbabu.t
 */
@Entity
@Table(name = "GBM_SYSTEM_PARAMETER_DETAILS")
public class NpsSystemParameterDetails {

    /** The parameter id. */
    @jakarta.persistence.Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal Id;

    /** The parameter id. */
    @Column(name = "PARAMETER_ID")
    private String parameterId;

    /** The parameter value. */
    @Column(name = "PARAMETER_VALUES")
    private String parameterValues;

    /** The parameter val desc. */
    @Column(name = "PARAMETER_VAL_DESC")
    private String parameterValDesc;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public BigDecimal getId() {
        return this.Id;
    }

    /**
     * Gets the parameter id.
     *
     * @return the parameterId
     */
    public String getParameterId() {
        return this.parameterId;
    }

    /**
     * Gets the parameter val desc.
     *
     * @return the parameterValDesc
     */
    public String getParameterValDesc() {
        return this.parameterValDesc;
    }

    /**
     * Gets the parameter values.
     *
     * @return the parameterValues
     */
    public String getParameterValues() {
        return this.parameterValues;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the id to set
     */
    public void setId(final BigDecimal id) {
        this.Id = id;
    }

    /**
     * Sets the parameter id.
     *
     * @param parameterId
     *            the parameterId to set
     */
    public void setParameterId(final String parameterId) {
        this.parameterId = parameterId;
    }

    /**
     * Sets the parameter val desc.
     *
     * @param parameterValDesc
     *            the parameterValDesc to set
     */
    public void setParameterValDesc(final String parameterValDesc) {
        this.parameterValDesc = parameterValDesc;
    }

    /**
     * Sets the parameter values.
     *
     * @param parameterValues
     *            the parameterValues to set
     */
    public void setParameterValues(final String parameterValues) {
        this.parameterValues = parameterValues;
    }

}
