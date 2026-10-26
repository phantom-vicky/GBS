package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.wrapper.SignatureDTO;
import com.isl.gbm.cbs.integration.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class SignatureController {
    @Autowired
    private SignatureService signatureService;

    @PostMapping(value = "/mockservices/signature")
    public ResponseEntity<SignatureDTO> getSignature(@RequestBody SignatureDTO signatureDTO)  {
        final SignatureDTO dtoList = this.signatureService.getSignature(signatureDTO);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
