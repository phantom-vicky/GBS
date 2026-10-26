package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.StatewiseSgstMasterForBrokerageDto;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isl.gbm.rbibond.service.*;
import com.isl.gbm.rbibond.bo.*;


@Service
public class StatewiseSgstMasterForBrokerageServiceImpl implements StatewiseSgstMasterForBrokerageService {

   @Autowired
   private StatewiseSgstMasterForBrokerageBO statewiseSgstMasterForBrokerageBO;

//    @Override
//  public ResponseDataDTO getAddDetails(StatewiseSgstMasterForBrokerageDto StatewiseSgstMasterForBrokerageDto) {
//      return StatewiseSgstMasterForBrokeragBO.getAddDetails(StatewiseSgstMasterForBrokerageDto);
//   }

    @Override
    public ResponseDataDTO add(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        try {
            if (null != statewiseSgstMasterForBrokerageDto && null != statewiseSgstMasterForBrokerageDto.getSgstRate()) {
                RbiBondServerSideRegexUtility.validatePercentage(statewiseSgstMasterForBrokerageDto.getSgstRate());
            }
        }catch(Exception e){
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return statewiseSgstMasterForBrokerageBO.add(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO inquiry(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageBO.inquiry(statewiseSgstMasterForBrokerageDto);
    }

//    @Override
    public ResponseDataDTO getModifyDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageBO.getModifyDetails(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO getVerifyDeleteDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageBO.getVerifyDeleteDetails(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO verify(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageBO.verify(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO modify(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            if (statewiseSgstMasterForBrokerageDto.getColumnValues() != null && statewiseSgstMasterForBrokerageDto.getColumnValues().get(3).getValue() != null) {
                RbiBondServerSideRegexUtility.validatePercentage(statewiseSgstMasterForBrokerageDto.getColumnValues().get(3).getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return statewiseSgstMasterForBrokerageBO.modify(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO delete(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageBO.delete(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO getStateDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageBO.getStateDetails(statewiseSgstMasterForBrokerageDto);
    }

}
