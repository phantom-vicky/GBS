package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondTaxExcemptionMasterDTO;
import com.isl.gbm.rbibond.facade.TdsExemptionMasterFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gbmultra/rbibond/tdsexcemptionmaster")
public class TdsExemptionMasterController {

    @Autowired
    TdsExemptionMasterFacade tdsExemptionMasterFacade;

    @PostMapping(path = "/addSelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getAddPanNoDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.getAddPanNoDetails(bondTaxExcemptionMasterDTO));
    }


    @PostMapping(path = "/add",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> addCutomerDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.addCutomerDetails(bondTaxExcemptionMasterDTO));
    }

    @PostMapping(value = "/ageValidation",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getHolderAge(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.getHolderAge(bondTaxExcemptionMasterDTO));
    }

    @PostMapping(value = "/getCertNumber",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getCertNumber(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.getCertNumber(bondTaxExcemptionMasterDTO));
    }

    @RequestMapping(value = "/financialyear", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getFinancialYear() {
        return tdsExemptionMasterFacade.getFinancialYear();
    }

    @PostMapping(path = "/modifySelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getModifyPanNoDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.getModifyPanNoDetails(bondTaxExcemptionMasterDTO));
    }

    @PostMapping(path = "/modifyDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getModifyDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.getModifyDetails(bondTaxExcemptionMasterDTO));
    }

    @PostMapping(path = "/modify",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> modifyCutomerDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.modifyCutomerDetails(bondTaxExcemptionMasterDTO));
    }



    @PostMapping(path = "/verDelSelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getVerDelDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.getVerDelPanDetails(bondTaxExcemptionMasterDTO));
    }

    @PostMapping(path = "/verifyDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getVerifyDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.getVerifyDetails(bondTaxExcemptionMasterDTO));
    }


    @PostMapping(path = "/verify",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> verifyCutomerDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.verifyCutomerDetails(bondTaxExcemptionMasterDTO));
    }



    @PostMapping(path = "/deleteDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getDeleteDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.getDeleteDetails(bondTaxExcemptionMasterDTO));
    }


    @PostMapping(path = "/delete",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> deleteCutomerDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.deleteCutomerDetails(bondTaxExcemptionMasterDTO));
    }

    @PostMapping(path = "/getVersionNoDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getVersionNoDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.getVersionNoDetails(bondTaxExcemptionMasterDTO));
    }

    @PostMapping(path = "/inquirySelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getInquiryDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.getInquiryPanDetails(bondTaxExcemptionMasterDTO));
    }

    @PostMapping(path = "/inquiryDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> inquiryDetails(@RequestBody BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsExemptionMasterFacade.getInquiryDetails(bondTaxExcemptionMasterDTO));
    }
    
    
    
}
