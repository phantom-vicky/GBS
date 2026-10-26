package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.isl.gbm.rbibond.facade.*;
import com.isl.gbm.rbibond.dto.*;



    @RequestMapping(path = "gbmultra/rbiBond/StatewiseSgstMasterForBrokerage")
    @RestController
    public class StatewiseSgstMasterForBrokerageController {

        @Autowired
        StatewiseSgstMasterForBrokerageFacade statewiseSgstMasterForBrokerageFacade;


        @PostMapping(path = "/add", produces = "application/json")
        public ResponseEntity<ResponseDataDTO> add(@RequestBody StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
            return APIResponseBuilder.buildResponseFromDto(statewiseSgstMasterForBrokerageFacade.add(statewiseSgstMasterForBrokerageDto));
        }

        @PostMapping(path = "/inquiry", produces = "application/json")
        public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
            return APIResponseBuilder.buildResponseFromDto(statewiseSgstMasterForBrokerageFacade.inquiry(statewiseSgstMasterForBrokerageDto));
        }

        @PostMapping(path = "/verifyDetails", produces = "application/json")
        public ResponseEntity<ResponseDataDTO> getVerifyDeleteDetails(@RequestBody StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
            return APIResponseBuilder.buildResponseFromDto(statewiseSgstMasterForBrokerageFacade.getVerifyDeleteDetails(statewiseSgstMasterForBrokerageDto));
        }


        @PostMapping(path = "/modify", produces = "application/json")
        public ResponseEntity<ResponseDataDTO> modify(@RequestBody StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
            return APIResponseBuilder.buildResponseFromDto(statewiseSgstMasterForBrokerageFacade.modify(statewiseSgstMasterForBrokerageDto));
        }

        @PostMapping(path = "/modifyDetails", produces = "application/json")
        public ResponseEntity<ResponseDataDTO> getModifyDetails(@RequestBody final StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
            return APIResponseBuilder.buildResponseFromDto(statewiseSgstMasterForBrokerageFacade.getModifyDetails(statewiseSgstMasterForBrokerageDto));
        }


        @PostMapping(path = "/delete", produces = "application/json")
        public ResponseEntity<ResponseDataDTO> delete(@RequestBody StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
            return APIResponseBuilder.buildResponseFromDto(statewiseSgstMasterForBrokerageFacade.delete(statewiseSgstMasterForBrokerageDto));
        }

        @PostMapping(path = "/verify", produces = "application/json")
        public ResponseEntity<ResponseDataDTO> verify(@RequestBody StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
            return APIResponseBuilder.buildResponseFromDto(statewiseSgstMasterForBrokerageFacade.verify(statewiseSgstMasterForBrokerageDto));
        }


        @PostMapping(path = "/getStateDetails")
        public ResponseEntity<ResponseDataDTO> getStateDetails(@RequestBody StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
            return APIResponseBuilder.buildResponseFromDto(statewiseSgstMasterForBrokerageFacade.getStateDetails(statewiseSgstMasterForBrokerageDto));
        }

    }


