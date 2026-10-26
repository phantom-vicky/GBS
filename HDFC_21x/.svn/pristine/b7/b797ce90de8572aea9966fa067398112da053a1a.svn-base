package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.ByteArrayOutputStream;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDownloadWrapperDTO {

    private String message;
    private ByteArrayOutputStream fileContent;

    @Override
    public String toString() {
        return "FileDownloadWrapperDTO{" +
                "message='" + message + '\'' +
                ", fileContent=" + fileContent +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ByteArrayOutputStream getFileContent() {
        return fileContent;
    }

    public void setFileContent(ByteArrayOutputStream fileContent) {
        this.fileContent = fileContent;
    }
}
