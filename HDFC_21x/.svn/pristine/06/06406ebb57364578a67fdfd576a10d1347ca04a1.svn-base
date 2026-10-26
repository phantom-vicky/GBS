/**
 *
 */
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.GbmDtdViewDto;
import com.isl.gbm.cbs.integration.dto.GbmOzhViewDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.dto.wrapper.TellerDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.TranInqDTO;
import com.isl.gbm.cbs.integration.service.TranDetailsIntegrationService;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.util.CommonConstants;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * The Class TranDetailsIntegrationController.
 *
 * @author Vasanthi
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class TranDetailsIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(TranDetailsIntegrationController.class);

    /** The tran details integration service. */
    @Autowired
    private TranDetailsIntegrationService tranDetailsIntegrationService;


    /**
     * Gets the clearing tran details.
     *
     * @param solId
     *            the sol id
     * @param clearingZoneCode
     *            the clearing zone code
     * @param clearingZoneDate
     *            the clearing zone date
     * @return the clearing tran details
     */
    @GetMapping(value = "/clearedzonetranid")
    public ResponseEntity<GbmOzhViewDto> getClearingTranDetails(
            @RequestParam(name = "solId", required = true) final String solId,
            @RequestParam(name = "clearingZoneCode", required = true) final String clearingZoneCode,
            @RequestParam(name = "clearingZoneDate", required = true) final String clearingZoneDate) throws ParseException {

            Date date = null;
                date = DateUtils.parseDate(clearingZoneDate, "dd/MM/yyyy");

            return new ResponseEntity<>(
                    (this.tranDetailsIntegrationService.getClearingTranDetails(solId, clearingZoneCode, date)),
                    HttpStatus.OK);

    }

    /**
     * Gets the daily tran details.
     *
     * @param gbmTranRefIdList
     *            the gbm tran ref id list
     * @param tranDate
     *            the tran date
     * @param solId
     *            the sol id
     * @param entryUserId
     *            the entry user id
     * @return the daily tran details
     */
    @GetMapping(value = "/dailytrandetails")
    public ResponseEntity<GbmDtdViewDto> getDailyTranDetails(
            @RequestParam(name = "gbmTranRefId", required = true) final List<String> gbmTranRefIdList,
            @RequestParam(name = "tranDate", required = true) final String tranDate,
            @RequestParam(name = "solId", required = true) final String solId,
            @RequestParam(name = "entryUserId", required = true) final String entryUserId) throws ParseException {

            Date date = null;
                date = DateUtils.parseDate(tranDate, "dd/MM/yyyy");

            return new ResponseEntity<>(this.tranDetailsIntegrationService.getDailyTransactionDetails(gbmTranRefIdList,
                    solId, date, entryUserId), HttpStatus.OK);


    }

    /**
     * Gets the daily unverified transaction details.
     *
     * @param acid
     *            the acid
     * @param entryUserId
     *            the entry user id
     * @return the daily unverified transaction details
     */
    @GetMapping(value = "/unverifiedtrandetails")
    public ResponseEntity<List<GbmDtdViewDto>> getDailyUnverifiedTransactionDetails(
            @RequestParam(name = "acid", required = true) final String acid,
            @RequestParam(name = "userId", required = true) final String entryUserId) {

            return new ResponseEntity<>(
                    this.tranDetailsIntegrationService.getDailyUnverifiedTransactionDetails(acid, entryUserId),
                    HttpStatus.OK);


    }

    /**
     * Gets the dtd transaction details.
     *
     * @param acid
     *            the acid
     * @param entryUserId
     *            the entry user id
     * @return the dtd transaction details
     */
    @GetMapping(value = "/dtdtrandetails")
    public ResponseEntity<List<GbmDtdViewDto>> getDtdTransactionDetails(
            @RequestParam(name = "acid", required = true) final String acid,
            @RequestParam(name = "userId", required = true) final String entryUserId) {

            return new ResponseEntity<>(this.tranDetailsIntegrationService.getDtdTransactionDetails(acid, entryUserId),
                    HttpStatus.OK);


    }

    @PostMapping(value = "/trandetails")
    public ResponseEntity<List<TranDetailsIntegrationDto>> getTranDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<TranDetailsIntegrationDto> dtoList = this.tranDetailsIntegrationService.getTranDetails(cbsCommonDTo.getTranCode());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }

    @GetMapping(value = "/trandtls")
    public ResponseEntity<List<GbmDtdViewDto>> tranIdDetails(
            @RequestParam(name = "solId", required = true) final String solId,
            @RequestParam(name = "solDate", required = true) final String solDate,
            @RequestParam(name = "tranId", required = true) final String tranId,
            @RequestParam(name = "acid", required = true) final String acid) throws ParseException {

            Date date = null;
                date = DateUtils.parseDate(solDate, "dd/MM/yyyy");


            return new ResponseEntity<>((this.tranDetailsIntegrationService.tranIdDetails(solId, date, tranId, acid)),
                    HttpStatus.OK);
    }
    @PostMapping(value = "/mockservices/tellerAmt")
    public ResponseEntity<TellerDTO> tellerAmtAPI(@RequestBody TellerDTO tellerDTO) {

        return new ResponseEntity<TellerDTO>(this.tranDetailsIntegrationService.tellerAmtAPI(tellerDTO.getCbsTranDate(), tellerDTO.getTellerTranId()), HttpStatus.OK);

    }
}
