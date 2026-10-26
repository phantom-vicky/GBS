package com.isl.gbm.user.management.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Class ResponseDataDTO.
 *
 * @author Bhavya Mohan
 * <p>
 * The Class ResponseDataDTO.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDataDTO {

    /**
     * The message.
     */
    private String message;

    /**
     * The result.
     */
    private Object result;

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

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
    public void setMessage(final String message) {
        this.message = message;
    }

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
    @Override
    public String toString() {
        return "ResponseDataDTO [message=" + this.message + ", result=" + this.result + "]";
    }

}
