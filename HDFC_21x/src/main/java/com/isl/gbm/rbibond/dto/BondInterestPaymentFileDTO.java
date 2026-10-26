package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondInterestPaymentFileDTO {

    private BondInterestPaymentFileHeaderDTO fileHeaderDTO;
    private List<BondInterestPaymentFileDetailsDTO> fileDetailsDTO;

    @Override
    public String toString() {
        return "BondInterestPaymentFileDTO{" +
                "fileHeaderDTO=" + fileHeaderDTO +
                ", fileDetailsDTO=" + fileDetailsDTO +
                '}';
    }

    public BondInterestPaymentFileHeaderDTO getFileHeaderDTO() {
        return fileHeaderDTO;
    }

    public void setFileHeaderDTO(BondInterestPaymentFileHeaderDTO fileHeaderDTO) {
        this.fileHeaderDTO = fileHeaderDTO;
    }

    public List<BondInterestPaymentFileDetailsDTO> getFileDetailsDTO() {
        return fileDetailsDTO;
    }

    public void setFileDetailsDTO(List<BondInterestPaymentFileDetailsDTO> fileDetailsDTO) {
        this.fileDetailsDTO = fileDetailsDTO;
    }
}
