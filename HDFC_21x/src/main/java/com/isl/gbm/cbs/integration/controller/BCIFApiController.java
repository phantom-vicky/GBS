package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.wrapper.BCIFRequestDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.BCIFResponseDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.SignatureDTO;
import com.isl.gbm.cbs.integration.service.BCIFApiService;
import com.isl.gbm.cbs.integration.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class BCIFApiController {

    @Autowired
    private BCIFApiService bcifApiService;

    @PostMapping(value = "/h2h/accountdtls")
    public ResponseEntity<BCIFResponseDTO> getBcifDetails(@RequestBody BCIFRequestDTO bcifRequestDTO)  {
        final BCIFResponseDTO dtoList = this.bcifApiService.getBcifDetails(bcifRequestDTO);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

}
