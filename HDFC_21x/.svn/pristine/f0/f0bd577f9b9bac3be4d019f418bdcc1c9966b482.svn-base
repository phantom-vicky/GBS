/*
 *
 */
package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.cbs.integration.service.SolDateService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class SchemeCodesValidator.
 */
@Component
public class SolDateValidator {

    /**
     * The Constant DATE_FORMAT.
     */
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    /**
     * The sol date service.
     */
    @Autowired
    private SolDateService solDateService;

    public boolean isDataUpdated() {
        final SolDateDto savedSolDate = this.solDateService.getSolDateRecord();
        if (null == savedSolDate.getSolDate()) {
            return Boolean.FALSE;
        }
        return BooleanUtils.isTrue(
                this.DATE_FORMAT.format(new Date()).equals(this.DATE_FORMAT.format(savedSolDate.getModifiedDate())));
    }

    /**
     * Checks if is current date.
     *
     * @param dto the dto
     * @return true, if is date does not same
     * @throws ParseException
     */
    public boolean isValidDate(final SolDateDto dto) {
        if (BooleanUtils
                .isTrue(this.DATE_FORMAT.format(dto.getSolDate()).compareTo(this.DATE_FORMAT.format(new Date())) < 0)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Sets the sol date service.
     *
     * @param solDateService the new sol date service
     */
    public void setSolDateService(final SolDateService solDateService) {
        this.solDateService = solDateService;
    }

}
