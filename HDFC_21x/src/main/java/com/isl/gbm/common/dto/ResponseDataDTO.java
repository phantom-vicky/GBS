package com.isl.gbm.common.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * The Class ResponseDataDTO.
 *
 * @author Bhavya Mohan
 * <p>
 * The Class ResponseDataDTO.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDataDTO {

    private Object FileResponseDataDto;

    /**
     * The message.
     */
    private String message;
    private String statusCode;
    private double total;
    private String error;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * The result.
     */
    private Object result;
    private Object result1;

    public Object getResult1() {
        return result1;
    }

    public void setResult1(Object result1) {
        this.result1 = result1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Object getFileResponseDataDto() {
        return FileResponseDataDto;
    }

    public void setFileResponseDataDto(Object fileResponseDataDto) {
        FileResponseDataDto = fileResponseDataDto;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
//    public String getMessage() {
//        return this.message;
//    }
//    public String getStatus() {
//        return this.status;
//    }
    /**
     * Gets the result.
     *
     * @return the result
     */
    public Object getResult() {
        return this.result;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
//    public void setMessage(final String message) {
//        this.message = message;
//    }
//    public void setStatus(final String status) {
//        this.status = status;
//    }

    /**
     * Sets the result.
     *
     * @param result the new result
     */
    public void setResult(final Object result) {
        this.result = result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
//    @Override
//    public String toString() {
//        return "ResponseDataDTO [status=" + this.status + ", message=" + this.message + ", result=" + this.result + "]";
//    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseDataDTO{" +
                "FileResponseDataDto=" + FileResponseDataDto +
                ", message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", total=" + total +
                ", error='" + error + '\'' +
                ", result=" + result +
                ", result1=" + result1 +
                '}';
    }
}
