package com.isl.gbm.common.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TocSlabDetailsDto;
import com.isl.gbm.common.facade.TocSlabDetailsFacade;
import com.isl.gbm.common.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/common/tocSlab")
public class TocSlabMaintenanceController {


    @Autowired
    private TocSlabDetailsFacade tocSlabDetailsFacade;

    /********************************************TOC Slab Maintainence Starts****************************************************/
    /**
     * @param tocSlabDetailsDto
     * @return
     */
    @PostMapping(value = "/selectModifySlab")
    public ResponseEntity<ResponseDataDTO> selectModify(@RequestBody final TocSlabDetailsDto tocSlabDetailsDto) {
        return APIResponseBuilder.buildResponseFromDto(tocSlabDetailsFacade.selectModifySlab(tocSlabDetailsDto));
    }

    /**
     * @param tocSlabDetailsDto
     * @return
     */
    @PostMapping(value = "/selectVerifySlab")
    public ResponseEntity<ResponseDataDTO> selectVerify(@RequestBody final TocSlabDetailsDto tocSlabDetailsDto) {
        return APIResponseBuilder.buildResponseFromDto(tocSlabDetailsFacade.selectVerifySlab(tocSlabDetailsDto));
    }

    /**
     * @param tocSlabDetailsDto
     * @return
     */
    @PostMapping(value = "/getAddDetails")
    public ResponseEntity<ResponseDataDTO> getAddDetails(@RequestBody final TocSlabDetailsDto tocSlabDetailsDto) {
        return APIResponseBuilder.buildResponseFromDto(tocSlabDetailsFacade.getAddDetails(tocSlabDetailsDto));
    }

    /**
     * @param tocSlabDetailsDto
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDataDTO> save(@RequestBody final TocSlabDetailsDto tocSlabDetailsDto) {
        return APIResponseBuilder.buildResponseFromDto(tocSlabDetailsFacade.save(tocSlabDetailsDto));
    }

    /**
     * @param tocSlabDetailsDto
     * @return
     */
    @PostMapping(value = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody final TocSlabDetailsDto tocSlabDetailsDto) {
        return APIResponseBuilder.buildResponseFromDto(tocSlabDetailsFacade.modify(tocSlabDetailsDto));
    }

    /**
     * @param tocSlabDetailsDto
     * @return
     */
    @PostMapping(value = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody final TocSlabDetailsDto tocSlabDetailsDto) {
        return APIResponseBuilder.buildResponseFromDto(tocSlabDetailsFacade.verify(tocSlabDetailsDto));
    }

    /**
     * @param tocSlabDetailsDto
     * @return
     */
    @PostMapping(value = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody final TocSlabDetailsDto tocSlabDetailsDto) {
        return APIResponseBuilder.buildResponseFromDto(tocSlabDetailsFacade.delete(tocSlabDetailsDto));
    }

    /**
     * @param tocSlabDetailsDto
     * @return
     */
    @PostMapping(value = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody final TocSlabDetailsDto tocSlabDetailsDto) {
        return APIResponseBuilder.buildResponseFromDto(tocSlabDetailsFacade.inquiry(tocSlabDetailsDto));
    }
    /********************************************TOC Slab Maintainence Ends****************************************************/

}
