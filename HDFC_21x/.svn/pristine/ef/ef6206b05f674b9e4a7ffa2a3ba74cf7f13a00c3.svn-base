package com.isl.gbm.rbibond.externalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FtsResponseDataDTO {

    @JsonProperty("status")
    private Status status;

    @JsonProperty("maintenanceType")
    private String maintenanceType;

    @JsonProperty("configVersionId")
    private String configVersionId;

    @JsonProperty("responseString")
    private ResponseString responseString;

    // Getters and Setters

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getConfigVersionId() {
        return configVersionId;
    }

    public void setConfigVersionId(String configVersionId) {
        this.configVersionId = configVersionId;
    }

    public ResponseString getResponseString() {
        return responseString;
    }

    public void setResponseString(ResponseString responseString) {
        this.responseString = responseString;
    }

public static class Status {

    @JsonProperty("isOverriden")
    private boolean isOverridden;

    @JsonProperty("replyCode")
    private int replyCode;

    @JsonProperty("replyText")
    private String replyText;

    @JsonProperty("memo")
    private String memo;

    @JsonProperty("externalReferenceNo")
    private String externalReferenceNumber;

    @JsonProperty("internalReferenceNumber")
    private String internalReferenceNumber;

    @JsonProperty("postingDate")
    private PostingDate postingDate;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("extendedReply")
    private ExtendedReply extendedReply;

    @JsonProperty("validationErrors")
    private Object validationErrors; // Use appropriate type if known

    @JsonProperty("userReferenceNumber")
    private String userReferenceNumber;

    // Getters and Setters

    public boolean isOverridden() {
        return isOverridden;
    }

    public void setOverridden(boolean overridden) {
        isOverridden = overridden;
    }

    public int getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(int replyCode) {
        this.replyCode = replyCode;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getExternalReferenceNumber() {
        return externalReferenceNumber;
    }

    public void setExternalReferenceNumber(String externalReferenceNumber) {
        this.externalReferenceNumber = externalReferenceNumber;
    }

    public String getInternalReferenceNumber() {
        return internalReferenceNumber;
    }

    public void setInternalReferenceNumber(String internalReferenceNumber) {
        this.internalReferenceNumber = internalReferenceNumber;
    }

    public PostingDate getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(PostingDate postingDate) {
        this.postingDate = postingDate;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ExtendedReply getExtendedReply() {
        return extendedReply;
    }

    public void setExtendedReply(ExtendedReply extendedReply) {
        this.extendedReply = extendedReply;
    }

    public Object getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Object validationErrors) {
        this.validationErrors = validationErrors;
    }

    public String getUserReferenceNumber() {
        return userReferenceNumber;
    }

    public void setUserReferenceNumber(String userReferenceNumber) {
        this.userReferenceNumber = userReferenceNumber;
    }
}

static class PostingDate {

    @JsonProperty("dateString")
    private String dateString;

    // Getters and Setters

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}

static class ExtendedReply {

    @JsonProperty("messages")
    private Object messages; // Use appropriate type if known

    // Getters and Setters


    public Object getMessages() {
        return messages;
    }

    public void setMessages(Object messages) {
        this.messages = messages;
    }
}

public static class ResponseString {

    @JsonProperty("Acknowledgement_No")
    private String acknowledgmentNumber;

    @JsonProperty("Reject_Reason")
    private String rejectReason;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("LstData")
    private List<ListData> listData;

    // Getters and Setters


    public String getAcknowledgmentNumber() {
        return acknowledgmentNumber;
    }

    public void setAcknowledgmentNumber(String acknowledgmentNumber) {
        this.acknowledgmentNumber = acknowledgmentNumber;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ListData> getListData() {
        return listData;
    }

    public void setListData(List<ListData> listData) {
        this.listData = listData;
    }
}

public static class ListData {

    @JsonProperty("Channel")
    private String channel;

    @JsonProperty("File_Name")
    private String fileName;

    @JsonProperty("File_Path")
    private String filePath;

    @JsonProperty("LstIndexingDetails")
    private List<IndexingDetail> listIndexingDetails;

    // Getters and Setters


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<IndexingDetail> getListIndexingDetails() {
        return listIndexingDetails;
    }

    public void setListIndexingDetails(List<IndexingDetail> listIndexingDetails) {
        this.listIndexingDetails = listIndexingDetails;
    }
}

public static class IndexingDetail {

    @JsonProperty("Image_Name")
    private String imageName;

    @JsonProperty("Image_Path")
    private String imagePath;

    @JsonProperty("Indexing_Name")
    private String indexingName;

    @JsonProperty("Indexing_Value")
    private String indexingValue;

    // Getters and Setters

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getIndexingName() {
        return indexingName;
    }

    public void setIndexingName(String indexingName) {
        this.indexingName = indexingName;
    }

    public String getIndexingValue() {
        return indexingValue;
    }

    public void setIndexingValue(String indexingValue) {
        this.indexingValue = indexingValue;
    }
  }

}
