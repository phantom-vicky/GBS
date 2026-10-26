/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.utils;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;


/**
 * The Class APIResponseBuilder.
 */
public class APIResponseBuilder {

    private static final Logger log = LoggerFactory.getLogger(APIResponseBuilder.class);

    public static ResponseEntity<ResponseDataDTO> buildErrorResponse(final String message,
                                                                     final Map<String, String> errorMessages, final HttpStatus statusCode) {
        final ResponseDataDTO errorDto = APIResponseBuilder.buildResponse(message);
        errorDto.setResult(errorMessages);
        return new ResponseEntity(errorDto, statusCode);
    }

    /**
     * Builds the response.
     *
     * @param message the message
     * @return the response data DTO
     */
    public static ResponseDataDTO buildResponse(final String message) {
        final ResponseDataDTO dataDto = new ResponseDataDTO();
        dataDto.setMessage(message);
        return dataDto;
    }

    /**
     * Builds the success response.
     *
     * @param message the message
     * @param result  the result
     * @return the response entity
     */
    public static ResponseEntity<ResponseDataDTO> buildSuccessResponse(final String message, final Object result) {
        if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(message))
            return new ResponseEntity(result, HttpStatus.OK);
        else if (message.contains(ErrorMessages.ERROR)|| message.contains(ErrorMessages.ORA)) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, message);
            return APIResponseBuilder.buildErrorResponse(ErrorMessages.DATABASE_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else if (ErrorMessages.INTERNAL_SERVER_ERROR.equalsIgnoreCase(message)) {
            return APIResponseBuilder.buildErrorResponse(ErrorMessages.INTERNAL_SERVER_ERROR_DESC, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return APIResponseBuilder.buildErrorResponse(message, null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Instantiates a new API response builder.
     */
    private APIResponseBuilder() {
        // Private Constructor
    }

    public static ResponseEntity<ResponseDataDTO> buildCbsErrorResponse(final Exception errorMessage) {

        log.error(CommonConstants.EXCEPTION_MESSAGE, errorMessage);
        final ResponseDataDTO errorDto = com.isl.gbm.common.util.APIResponseBuilder.buildResponse(ErrorMessages.INTERNAL_SERVER_ERROR_DESC);
        errorDto.setResult(null);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
