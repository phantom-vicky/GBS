package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;

import com.isl.gbm.rbibond.dto.RbiBondTdsParameterDTO;
import com.isl.gbm.rbibond.facade.TdsParameterForRbiBondFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gbmultra/rbiBond/tdsParameter")
public class TdsParameterForRbiBondController {

    @Autowired
    private TdsParameterForRbiBondFacade tdsParameterForRbiBondFacade;

    
    /**
     * @return
     * @author Adharsh
     */
    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsParameterForRbiBondFacade.add(rbiBondTdsParameterDTO));
    }

    /**
     * @return
     * @author Adharsh
     */
    @PostMapping(path = "/addValidation")
    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsParameterForRbiBondFacade.addValidation(rbiBondTdsParameterDTO));
    }

    /**
     * @return
     * @author Adharsh
     */
    @PostMapping(path = "/inquiryDetails")
    public ResponseEntity<ResponseDataDTO> inquiryDetails(@RequestBody RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsParameterForRbiBondFacade.inquiryDetails(rbiBondTdsParameterDTO));
    }

    /**
     * @return
     * @author Adharsh
     */
    @PostMapping(path = "/verifyDeleteDetails")
    public ResponseEntity<ResponseDataDTO> verifyDeleteDetails(@RequestBody RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsParameterForRbiBondFacade.verifyDeleteDetails(rbiBondTdsParameterDTO));
    }

    /**
     * @return
     * @author Adharsh
     */
    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsParameterForRbiBondFacade.verify(rbiBondTdsParameterDTO));
    }

    /**
     * @return
     * @author Adharsh
     */
    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsParameterForRbiBondFacade.delete(rbiBondTdsParameterDTO));
    }

    /**
     * @return
     * @author Adharsh
     */
    @PostMapping(path = "/modifyDetails")
    public ResponseEntity<ResponseDataDTO> modifyDetails(@RequestBody RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsParameterForRbiBondFacade.modifyDetails(rbiBondTdsParameterDTO));
    }

    /**
     * @return
     * @author Adharsh
     */
    @PostMapping(path = "/modifySubmit")
    public ResponseEntity<ResponseDataDTO> modifySubmit(@RequestBody RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(tdsParameterForRbiBondFacade.modifySubmit(rbiBondTdsParameterDTO));
    }

}
