package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PanValidationResponse {
    private String response_Code;
    private List<OutputData> outputData;

    // Getters and Setters
    public String getResponse_Code() {
        return response_Code;
    }

    public void setResponse_Code(String response_Code) {
        this.response_Code = response_Code;
    }

    public List<OutputData> getOutputData() {
        return outputData;
    }

    public void setOutputData(List<OutputData> outputData) {
        this.outputData = outputData;
    }

    public static class OutputData {
        private String pan;
        @JsonProperty("pan_status")
        private String panStatus;
        private String name;
        private String fatherName;
        private String dob;
        @JsonProperty("seeding_status")
        private String seedingStatus;

        // Getters and Setters
        public String getSeedingStatus() {
            return seedingStatus;
        }

        public void setSeedingStatus(String seedingStatus) {
            this.seedingStatus = seedingStatus;
        }

        public String getPan() {
            return pan;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }

        public String getPanStatus() {
            return panStatus;
        }

        public void setPanStatus(String panStatus) {
            this.panStatus = panStatus;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFathername() {
            return fatherName;
        }

        public void setFathername(String fathername) {
            this.fatherName = fathername;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        @Override
        public String toString() {
            return "OutputData{" +
                    "pan='" + pan + '\'' +
                    ", pan_status='" + panStatus + '\'' +
                    ", name='" + name + '\'' +
                    ", fathername='" + fatherName + '\'' +
                    ", dob='" + dob + '\'' +
                    ", seedingStatus='" + seedingStatus + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PanValidationResponse{" +
                "response_Code='" + response_Code + '\'' +
                ", outputData=" + outputData +
                '}';
    }
}
