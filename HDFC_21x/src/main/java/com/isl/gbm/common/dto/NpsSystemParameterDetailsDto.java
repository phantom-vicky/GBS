/**
 *
 */
package com.isl.gbm.common.dto;

/**
 * The Class NpsSystemParameterDetailsDto.
 *
 * @author harishbabu.t
 */
public class NpsSystemParameterDetailsDto {

    /** The parameter id. */
    private String parameterId;

    /** The parameter value. */
    private String parameterValues;

    /** The parameter val desc. */
    private String parameterValDesc;


    /**
     * The sch code
     */
    private String schCode;

    /**
     *
     * @return
     */
    public String getSchCode() {
        return schCode;
    }

    /**
     *
     * @param schCode
     */
    public void setSchCode(String schCode) {
        this.schCode = schCode;
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
