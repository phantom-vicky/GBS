package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.facade.RbiBondBodEodFacade;
import com.isl.gbm.rbibond.dto.BodEodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/gbmultra/rbibond/bodEod")
public class RbiBondBodEodController {
    @Autowired
    private RbiBondBodEodFacade rbiBondBodEodFacade;

    @PostMapping(path = "/startBodProcess", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> startBodProcess(@RequestBody BodEodDto bodEodDto) {
        if (CommonConstants.LEVEL_CODE_LINK_CELL.equals(bodEodDto.getLevelCode())) {
            return APIResponseBuilder.buildResponseFromDto(rbiBondBodEodFacade.startLinkCellBodProcess(bodEodDto));
        } else if (CommonConstants.LEVEL_CODE_PAY_BRANCH.equals(bodEodDto.getLevelCode())) {
            return APIResponseBuilder.buildResponseFromDto(rbiBondBodEodFacade.startPayBranchBodProcess(bodEodDto));
        } else {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage(CommonConstants.INVALID_LEVEL_CODE_MSG);
            return APIResponseBuilder.buildResponseFromDto(responseDataDTO);
        }
    }

    @PostMapping(path = "/startEodProcess", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> startEodProcess(@RequestBody BodEodDto bodEodDto) {
        if (CommonConstants.LEVEL_CODE_LINK_CELL.equals(bodEodDto.getLevelCode())) {
            return APIResponseBuilder.buildResponseFromDto(rbiBondBodEodFacade.startLinkCellEodProcess(bodEodDto));
        } else if (CommonConstants.LEVEL_CODE_PAY_BRANCH.equals(bodEodDto.getLevelCode())) {
            return APIResponseBuilder.buildResponseFromDto(rbiBondBodEodFacade.startPayBranchEodProcess(bodEodDto));
        } else {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage(CommonConstants.INVALID_LEVEL_CODE_MSG);
            return APIResponseBuilder.buildResponseFromDto(responseDataDTO);
        }
    }

    @PostMapping(path="/getTimeValidation", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTimeValidation() {
        return APIResponseBuilder.buildResponseFromDto(rbiBondBodEodFacade.getTimeValidation());
    }

}
