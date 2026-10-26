package com.isl.gbm.common.controller;

import com.isl.gbm.common.dto.FinancialYearDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/gbmultra/common/finyear")
public class FinancialYearController {
    @PostMapping(path = "/currentFinancialyear")
    public ArrayList<FinancialYearDTO> getCurrentAndListFinancialyears() {
        ArrayList<FinancialYearDTO> finyearList = new ArrayList();
        FinancialYearDTO financialYearDTO = null;
        financialYearDTO = new FinancialYearDTO();
        int year = getYearFromDate(new Date());
        System.out.println("Financial Year : " + year + "-" + (year + 1));
        System.out.println("Financial Year : " + (year - 1) + "-" + (year));
        financialYearDTO.setCode((year - 1) + "-" + (year));
        financialYearDTO.setDesc((year - 1) + "-" + (year));
        finyearList.add(financialYearDTO);
        financialYearDTO = new FinancialYearDTO();
        financialYearDTO.setCode(year + "-" + (year + 1));
        financialYearDTO.setDesc(year + "-" + (year + 1));
        finyearList.add(financialYearDTO);
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setResult(finyearList);
        responseDataDTO.setMessage("finyear List");
        return finyearList;
    }

    public static int getYearFromDate(Date date) {
        int result = -1;
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            result = cal.get(Calendar.YEAR);
        }
        return result;
    }
}
