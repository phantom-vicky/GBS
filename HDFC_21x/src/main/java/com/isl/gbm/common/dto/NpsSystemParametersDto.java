package com.isl.gbm.common.dto;

public class NpsSystemParametersDto {

    private String parameterId;

    /**
     * The parameter desc.
     */
    private String parameterDesc;

    /**
     * The parameter value.
     */
    private String parameterValue;

    /**
     * The parameter type.
     */
    private Character parameterType;

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterDesc() {
        return parameterDesc;
    }

    public void setParameterDesc(String parameterDesc) {
        this.parameterDesc = parameterDesc;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public Character getParameterType() {
        return parameterType;
    }

    public void setParameterType(Character parameterType) {
        this.parameterType = parameterType;
    }
}
