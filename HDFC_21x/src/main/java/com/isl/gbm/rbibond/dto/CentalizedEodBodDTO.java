package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CentalizedEodBodDTO {
    private String levelName;
    private String moduleName;
    private String bodDate;
    private String eodDate;
    private String branchId;
    private List<CentalizedEodBodDTO> centalizedEodBodDTOList;
    List<HashMap<String, String>> branchCode;
    List<HashMap<String, String>> levelCodeList;

    public String getEodDate() {
        return eodDate;
    }

    public void setEodDate(String eodDate) {
        this.eodDate = eodDate;
    }

    public List<HashMap<String, String>> getLevelCodeList() {
        return levelCodeList;
    }

    public void setLevelCodeList(List<HashMap<String, String>> levelCodeList) {
        this.levelCodeList = levelCodeList;
    }

    public List<HashMap<String, String>> getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(List<HashMap<String, String>> branchCode) {
        this.branchCode = branchCode;
    }

    public List<CentalizedEodBodDTO> getCentalizedEodBodDTOList() {
        return centalizedEodBodDTOList;
    }

    public void setCentalizedEodBodDTOList(List<CentalizedEodBodDTO> centalizedEodBodDTOList) {
        this.centalizedEodBodDTOList = centalizedEodBodDTOList;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getBodDate() {
        return bodDate;
    }

    public void setBodDate(String bodDate) {
        this.bodDate = bodDate;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
