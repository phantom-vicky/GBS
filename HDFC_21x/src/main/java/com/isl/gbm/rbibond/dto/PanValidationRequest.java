package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PanValidationRequest {

    private String Source_System;
    private List<InputData> inputData;

    // Getters and Setters
    public String getSource_System() {
        return Source_System;
    }

    public void setSource_System(String source_System) {
        Source_System = source_System;
    }

    public List<InputData> getInputData() {
        return inputData;
    }

    public void setInputData(List<InputData> inputData) {
        this.inputData = inputData;
    }
    public static class InputData {

        @NotBlank(message = "PAN is mandatory")
        private String pan;

        @NotBlank(message = "Name is mandatory")
        private String name;

        private String fatherName;

        @NotBlank(message = "Date of Birth is mandatory")
        @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Date of Birth must be in DD/MM/YYYY format")
        private String dob;

        // Getters and Setters
        public String getPan() {
            return pan;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }


        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        @Override
        public String toString() {
            return "InputData{" +
                    "pan='" + pan + '\'' +
                    ", name='" + name + '\'' +
                    ", fatherName='" + fatherName + '\'' +
                    ", dob='" + dob + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PanValidationRequest{" +
                "Source_System='" + Source_System + '\'' +
                ", inputData=" + inputData +
                '}';
    }
}
