package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.AgeBseLkIPdDetlPmteEncasmetBO;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.AgeBseLkIPdDetlPmteEncasmetDTO;
import com.isl.gbm.rbibond.service.AgeBseLkIPdDetlPmteEncasmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgeBseLkIPdDetlPmteEncasmetServiceImpl implements AgeBseLkIPdDetlPmteEncasmetService {


    @Autowired
    private AgeBseLkIPdDetlPmteEncasmetBO ageBseLkIPdDetlPmteEncasmetBO;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    ResponseDataDTO responseDataDTO = new ResponseDataDTO();
    @Override
    public ResponseDataDTO addValidation(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {

        if (ageBseLkIPdDetlPmteEncasmetDTO.getFlag() != null && ageBseLkIPdDetlPmteEncasmetDTO.getFlag().equalsIgnoreCase("D")) {


            if (ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() == null || ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode().isEmpty()){
                responseDataDTO.setMessage("Name of the Bond scheme Should Not Be Blank");
            return responseDataDTO;
        }

        }
        return ageBseLkIPdDetlPmteEncasmetBO.addValidation(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO add(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {


        if(ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate()==null || ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate().isEmpty()){

            responseDataDTO.setMessage("Effective From Date should not be blank");
            return responseDataDTO;
        }

        if(ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode()==null || ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode().isEmpty()){

            responseDataDTO.setMessage("Scheme Code should not be blank");
            return responseDataDTO;
        }




            for(AgeBseLkIPdDetlPmteEncasmetDTO value : ageBseLkIPdDetlPmteEncasmetDTO.getValues()){

                if(value.getSubscriberAgeBracketFrom()==null || value.getSubscriberAgeBracketFrom().isEmpty()){

                    responseDataDTO.setMessage("Subscriber’s Age Bracket From should not be blank");
                    return responseDataDTO;
                }

                if(value.getSubscriberAgeBracketTo()==null || value.getSubscriberAgeBracketTo().isEmpty()){

                    responseDataDTO.setMessage("Subscriber’s Age Bracket To should not be blank");
                    return responseDataDTO;
                }

                if(value.getLockInPeriod()==null || value.getLockInPeriod().isEmpty()){

                    responseDataDTO.setMessage("Lock-in Period (Years) should not be blank");
                    return responseDataDTO;
                }
            }

      return ageBseLkIPdDetlPmteEncasmetBO.add(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {


        if (ageBseLkIPdDetlPmteEncasmetDTO.getFlag() != null && ageBseLkIPdDetlPmteEncasmetDTO.getFlag().equalsIgnoreCase("D")) {


            if (ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() == null || ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode().isEmpty()) {
                responseDataDTO.setMessage("Name of the Bond scheme Should Not Be Blank");
                return responseDataDTO;
            }

            if (ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo() == null || ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo().isEmpty()) {
                responseDataDTO.setMessage("Version No Should Not Be Blank");
                return responseDataDTO;
            }


            if (ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate() == null || ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate().isEmpty()) {
                responseDataDTO.setMessage("Effective From Date Should Not Be Blank");
                return responseDataDTO;
            }
        }
        return ageBseLkIPdDetlPmteEncasmetBO.verifySelect(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO verify(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetBO.verify(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {

        if (ageBseLkIPdDetlPmteEncasmetDTO.getFlag() != null && ageBseLkIPdDetlPmteEncasmetDTO.getFlag().equalsIgnoreCase("D")) {


            if (ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() == null || ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode().isEmpty()){
                responseDataDTO.setMessage("Name of the Bond scheme Should Not Be Blank");
                return responseDataDTO;
            }

            if (ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate() == null || ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate().isEmpty()){
                responseDataDTO.setMessage("Effective From Date Should Not Be Blank");
                return responseDataDTO;
            }

        }



        return ageBseLkIPdDetlPmteEncasmetBO.modifySelect(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO modify(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {



        if( ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() == null || ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode().isEmpty()) {


            responseDataDTO.setMessage("Schme Code should not be blank");
            return responseDataDTO;
        }


        List<List<ColumnValueDTO>> columnList = ageBseLkIPdDetlPmteEncasmetDTO.getColumnValueDetailsList();

            for (List<ColumnValueDTO> innerList : columnList) {

                for (ColumnValueDTO rrr : innerList) {
                    if ("C005".equals(rrr.getColName())) {
                        if (rrr.getValue() == null || rrr.getValue().trim().isEmpty() || !rrr.getValue().matches("\\d+")) {
                            responseDataDTO.setMessage("Subscriber’s Age Bracket From should not be blank and must contain only digits");
                            return responseDataDTO;
                        }


                    }
                    if ("C006".equals(rrr.getColName())) {
                        if (rrr.getValue() == null || rrr.getValue().trim().isEmpty() || !rrr.getValue().matches("\\d+")) {
                            responseDataDTO.setMessage("Subscriber’s Age Bracket To should not be blank and must contain only digits");
                            return responseDataDTO;
                        }
                    }
                    if ("C007".equals(rrr.getColName())) {
                        if (rrr.getValue() == null || rrr.getValue().trim().isEmpty()||!rrr.getValue().matches("\\d+")) {
                            responseDataDTO.setMessage("Lock-in Period  should not be blank and must contain only digits");
                            return responseDataDTO;
                        }
                    }
                    }
                }

       return ageBseLkIPdDetlPmteEncasmetBO.modify(ageBseLkIPdDetlPmteEncasmetDTO);


    }

    @Override
    public ResponseDataDTO inquiry(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {



        if (ageBseLkIPdDetlPmteEncasmetDTO.getFlag() != null && ageBseLkIPdDetlPmteEncasmetDTO.getFlag().equalsIgnoreCase("V")) {


            if (ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() == null || ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode().isEmpty()) {
                responseDataDTO.setMessage("Name of the Bond scheme Should Not Be Blank");
                return responseDataDTO;
            }


        }



        if (ageBseLkIPdDetlPmteEncasmetDTO.getFlag() != null && ageBseLkIPdDetlPmteEncasmetDTO.getFlag().equalsIgnoreCase("D")) {


            if (ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() == null || ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode().isEmpty()) {
                responseDataDTO.setMessage("Name of the Bond scheme Should Not Be Blank");
                return responseDataDTO;
            }

            if (ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo() == null || ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo().isEmpty()) {
                responseDataDTO.setMessage("Version No Should Not Be Blank");
                return responseDataDTO;
            }


            if (ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate() == null || ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate().isEmpty()) {
                responseDataDTO.setMessage("Effective From Date Should Not Be Blank");
                return responseDataDTO;
            }
        }


        return ageBseLkIPdDetlPmteEncasmetBO.inquiry(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO delete(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {


        if (ageBseLkIPdDetlPmteEncasmetDTO.getRejectionRemarks() == null || ageBseLkIPdDetlPmteEncasmetDTO.getRejectionRemarks().isEmpty()) {
            responseDataDTO.setMessage("Rejection Remarks Should Not Be Blank");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(ageBseLkIPdDetlPmteEncasmetDTO.getRejectionRemarks())) {
            responseDataDTO.setMessage("Invalid Rejection Remarks");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }



        return ageBseLkIPdDetlPmteEncasmetBO.delete(ageBseLkIPdDetlPmteEncasmetDTO);
    }


}
