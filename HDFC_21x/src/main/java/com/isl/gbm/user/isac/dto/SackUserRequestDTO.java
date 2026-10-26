package com.isl.gbm.user.isac.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SackUserRequestDTO {



    @NotBlank(message = "UserID is Required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "UserID cannot contain spaces or special characters")
    @Size(min = 1, max = 20, message="UserID length must be between 1 to 20")
    private String userID;

    @NotBlank(message = "InitiatedBy is Required")
    @Size(min = 1, max = 20,message="InitiatedBy length must be between 1 to 20")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "InitiatedBy cannot contain spaces or special characters")
    private String initiatedBy;

    @NotBlank(message = "ApprovedBy is Required")
    @Size(min = 1, max = 20,message="ApprovedBy length must be between 1 to 20")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ApprovedBy cannot contain spaces or special characters")
    private String approvedBy;

    @NotBlank(message = "ISACTicketNo is Required")
    @Size(min=25,max=25 , message ="ISACTicketNo length must be 25")
    private String iSACTicketNo;


    @Override
    public String toString() {
        return "{" +
                "userID='" + userID + '\'' +
                ", initiatedBy='" + initiatedBy + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", iSACTicketNo='" + iSACTicketNo + '\'' +
                '}';
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(String initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getiSACTicketNo() {
        return iSACTicketNo;
    }

    public void setiSACTicketNo(String iSACTicketNo) {
        this.iSACTicketNo = iSACTicketNo;
    }
}
