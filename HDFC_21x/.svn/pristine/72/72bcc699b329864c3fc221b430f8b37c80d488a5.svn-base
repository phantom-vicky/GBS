package com.isl.gbm.rbibond.util;

import com.isl.gbm.common.dto.ResponseDataDTO;

public final class FreezeUnfreezeValidationHelper {

    private FreezeUnfreezeValidationHelper() {
    }

    public static ResponseDataDTO validateMakerReason(String makerReason) {
        try {
            RbiBondServerSideRegexUtility.validateMakerReason(makerReason);
        } catch (IllegalArgumentException ex) {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage(ex.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return null;
    }
}
