package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.GbmBctView;
import com.isl.gbm.cbs.integration.service.BankBranchesDetailsService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class BankBranchesDetailsController {


    @Autowired
    private BankBranchesDetailsService bankBranchesDetailsService;


    @PostMapping(value = "/mockservices/isValidSortCode")
    public ResponseEntity<ResponseDataDTO> isValidSortCode(){
        return null;
    }

}

