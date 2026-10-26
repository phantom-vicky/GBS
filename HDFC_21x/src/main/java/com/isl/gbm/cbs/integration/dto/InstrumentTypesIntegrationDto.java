/**
 *
 */
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Msocadmin
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrumentTypesIntegrationDto {

    private String schemeCode;

    private String instType;

    private String instTypeDesc;

    private Character drCrInd;

    public Character getDrCrInd() {
        return this.drCrInd;
    }

    public String getInstType() {
        return this.instType;
    }

    public String getInstTypeDesc() {
        return this.instTypeDesc;
    }

    public String getSchemeCode() {
        return this.schemeCode;
    }

    public void setDrCrInd(final Character drCrInd) {
        this.drCrInd = drCrInd;
    }

    public void setInstType(final String instType) {
        this.instType = instType;
    }

    public void setInstTypeDesc(final String instTypeDesc) {
        this.instTypeDesc = instTypeDesc;
    }

    public void setSchemeCode(final String schemeCode) {
        this.schemeCode = schemeCode;
    }

    @Override
    public String toString() {
        return "InstrumentTypesIntegrationDto [schemeCode=" + this.schemeCode + ",  instType=" + this.instType
                + ", drCrInd=" + this.drCrInd + "]";
    }
}
