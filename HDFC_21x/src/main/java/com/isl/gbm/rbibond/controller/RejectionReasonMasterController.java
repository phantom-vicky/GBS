package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.RejectionReasonDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;

import com.isl.gbm.rbibond.facade.RejectionReasonMasterFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("apis/rejection-reason-master")

public class RejectionReasonMasterController {




         @Autowired
         RejectionReasonMasterFacade rejectionReasonMasterFacade;



        @PostMapping("/add-details")

        public ResponseEntity<ResponseDataDTO> addDetails(@RequestBody RejectionReasonDTO rejectionReasonDTO) {
            return APIResponseBuilder.buildResponseFromDto(rejectionReasonMasterFacade.addDetails(rejectionReasonDTO));

        }

        @PostMapping("/modify-details")
        public ResponseEntity<ResponseDataDTO> modifyDetails(@RequestBody RejectionReasonDTO rejectionReasonDTO) {
            return APIResponseBuilder.buildResponseFromDto(rejectionReasonMasterFacade.modifyDetails(rejectionReasonDTO));

        }

        @PostMapping("/modify-submit")
        public ResponseEntity<ResponseDataDTO> modifySubmit(@RequestBody RejectionReasonDTO rejectionReasonDTO) {
            return APIResponseBuilder.buildResponseFromDto(rejectionReasonMasterFacade.modifySubmit(rejectionReasonDTO));
        }

        @PostMapping("/verify-details")

        public ResponseEntity<ResponseDataDTO> verifyDetails(@RequestBody RejectionReasonDTO rejectionReasonDTO) {
            return APIResponseBuilder.buildResponseFromDto(rejectionReasonMasterFacade.verifyDetails(rejectionReasonDTO));
        }

        @PostMapping("/verify-submit")
        public ResponseEntity<ResponseDataDTO> verifySubmit(@RequestBody RejectionReasonDTO rejectionReasonDTO) {
            return APIResponseBuilder.buildResponseFromDto(rejectionReasonMasterFacade.verifySubmit(rejectionReasonDTO));
        }

        @PostMapping("/inquiry")

        public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody RejectionReasonDTO responseDataDTO) {
            return APIResponseBuilder.buildResponseFromDto(rejectionReasonMasterFacade.inquiry(responseDataDTO));
        }

        @PostMapping("/reject")

        public ResponseEntity<ResponseDataDTO> reject(@RequestBody RejectionReasonDTO responseDataDTO) {
            return APIResponseBuilder.buildResponseFromDto(rejectionReasonMasterFacade.reject(responseDataDTO));
        }

    }

