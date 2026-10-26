package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RbiStatusFilesDTO {

    @NotNull
    private String fromDate;
    @NotNull
    private String toDate;

    private List<RbiStatusFilesResponseDTO> rbiStatusFilesResponseDTOList;

    public static class RbiStatusFilesResponseDTO {

        private String fileName;
        private String noOfCrn;
        private String groupStatus;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getNoOfCrn() {
            return noOfCrn;
        }

        public void setNoOfCrn(String noOfCrn) {
            this.noOfCrn = noOfCrn;
        }

        public String getGroupStatus() {
            return groupStatus;
        }

        public void setGroupStatus(String groupStatus) {
            this.groupStatus = groupStatus;
        }
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<RbiStatusFilesResponseDTO> getRbiStatusFilesResponseDTOList() {
        return rbiStatusFilesResponseDTOList;
    }

    public void setRbiStatusFilesResponseDTOList(List<RbiStatusFilesResponseDTO> rbiStatusFilesResponseDTOList) {
        this.rbiStatusFilesResponseDTOList = rbiStatusFilesResponseDTOList;
    }
}
