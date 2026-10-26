/**
 *
 */
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.service.SchemeDetailsIntegrationService;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.util.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class SchemeDetailsIntegrationController.
 *
 * @author Msocadmin
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class SchemeDetailsIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(SchemeDetailsIntegrationController.class);

    /** The scheme details integration service. */
    @Autowired
    private SchemeDetailsIntegrationService schemeDetailsIntegrationService;


    /**
     * Gets the scheme type list.
     *
     * @return the scheme type list
     */
    @RequestMapping(value = "/accountsearch/schemetype", method = RequestMethod.POST)
    public ResponseEntity<List<SchemeCodesDto>> getSchemeTypeList() {

            final List<SchemeCodesDto> dtoList = this.schemeDetailsIntegrationService.getSchemeTypeList();
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }


    @RequestMapping(value = "/accountsearch/schemecode", method = RequestMethod.POST)
    public ResponseEntity<List<SchemeCodesDto>> getSchemeTypeList(@RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<SchemeCodesDto> dtoList = this.schemeDetailsIntegrationService.getSchemeCodeList(cbsCommonDTo.getSchemeType(), cbsCommonDTo.getSchemeCode());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/accountsearch/glsubheadlist", method = RequestMethod.POST)
    public ResponseEntity<List<SchemeCodesDto>> getGlSubHeadList(@RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<SchemeCodesDto> dtoList = this.schemeDetailsIntegrationService.getGlSubHeadList(cbsCommonDTo.getSchemeType(), cbsCommonDTo.getSchemeCode(), cbsCommonDTo.getGlSubHeadCode());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }
}
