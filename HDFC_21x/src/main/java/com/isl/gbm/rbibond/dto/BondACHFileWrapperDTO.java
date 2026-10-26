package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondACHFileWrapperDTO {

    private BondACHFileHeaderDTO achFileHeaderDTO;
    private List<BondACHFileDetailsDTO> achFileDetailsList;
    private String fileName;

    @Override
    public String toString() {
        return "BondACHFileWrapperDTO{" +
                "achFileHeaderDTO=" + achFileHeaderDTO +
                "fileName=" + fileName +
                ", achFileDetailsDTO=" + achFileDetailsList +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<BondACHFileDetailsDTO> getAchFileDetailsList() {
        return achFileDetailsList;
    }

    public void setAchFileDetailsList(List<BondACHFileDetailsDTO> achFileDetailsList) {
        this.achFileDetailsList = achFileDetailsList;
    }

    public BondACHFileHeaderDTO getAchFileHeaderDTO() {
        return achFileHeaderDTO;
    }

    public void setAchFileHeaderDTO(BondACHFileHeaderDTO achFileHeaderDTO) {
        this.achFileHeaderDTO = achFileHeaderDTO;
    }
}
