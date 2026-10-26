package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.CommonFlagDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.facade.CommonFlagFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/gu/commonFlag")
public class CommonFlagController {


    @Autowired
    CommonFlagFacade commonFlagFacade;

    @PostMapping("/modifydetails")
    public ResponseEntity<ResponseDataDTO> getModifyDetails(@RequestBody CommonFlagDTO commonFlagDTO){
        return APIResponseBuilder.buildResponseFromDto(commonFlagFacade.getModifyDetails(commonFlagDTO));
    }

    @PostMapping("/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody CommonFlagDTO commonFlagDTO){
        return APIResponseBuilder.buildResponseFromDto(commonFlagFacade.modify(commonFlagDTO));
    }


    @PostMapping("/verifydetails")
    public ResponseEntity<ResponseDataDTO> getVerifyDetails(@RequestBody CommonFlagDTO commonFlagDTO){
        return APIResponseBuilder.buildResponseFromDto (commonFlagFacade.getVerifyDetails(commonFlagDTO));
    }


    @PostMapping("/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody CommonFlagDTO commonFlagDTO){
        return APIResponseBuilder.buildResponseFromDto (commonFlagFacade.verify(commonFlagDTO));
    }


    @PostMapping("/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody CommonFlagDTO commonFlagDTO){
        return APIResponseBuilder.buildResponseFromDto(commonFlagFacade.delete(commonFlagDTO));
    }

    @PostMapping("inquiry")
    public ResponseEntity<ResponseDataDTO> getInquiryDetails(@RequestBody CommonFlagDTO commonFlagDTO){
        return APIResponseBuilder.buildResponseFromDto(commonFlagFacade.getInquiryDetails(commonFlagDTO));
    }





}
