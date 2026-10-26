package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondNominationBo;
import com.isl.gbm.rbibond.dao.RbiBondNominationDao;
import com.isl.gbm.rbibond.dto.RbiBondNominationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RbiBondNominationBoImpl implements RbiBondNominationBo{


    @Autowired
    private RbiBondNominationDao rbiBondNominationDao;

    @Override
    public ResponseDataDTO inquiry(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.inquiry(rbiBondNominationDto);
    }

    @Override
    public ResponseDataDTO getVerifyDeleteDetails(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.getVerifyDeleteDetails(rbiBondNominationDto);
    }

    @Override
    public ResponseDataDTO delete(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.delete(rbiBondNominationDto);
    }

    @Override
    public ResponseDataDTO verify(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.verify(rbiBondNominationDto);
    }

    @Override
    public ResponseDataDTO addModify(RbiBondNominationDto rbiBondNominationDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
 /*       for (List<ColumnValueDTO> nominee : rbiBondNominationDto.getNominationAddDtoList()) {
            for (ColumnValueDTO col : nominee) {
                if ("C012".equals(col.getColName())
                        && col.getValue() != null
                        && !"null".equalsIgnoreCase(col.getValue())) {
                    try {
                        int num = Integer.parseInt(col.getValue().trim());
                        if (num < 0) {
                            responseDataDTO.setMessage("Nominee Percentage must not be negative");
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }
                    } catch (NumberFormatException e) {
                        responseDataDTO.setMessage("Invalid numeric format for Nominee Percentage");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                }
            }
        }*/

        return rbiBondNominationDao.addModify(rbiBondNominationDto);
    }

    @Override
    public ResponseDataDTO modifySelect(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.modifySelect(rbiBondNominationDto);
    }

    @Override
    public ResponseDataDTO modifyPanTorch(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.modifyPanTorch(rbiBondNominationDto);
    }

    @Override
    public ResponseDataDTO inquiryPanTorch(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.inquiryPanTorch(rbiBondNominationDto);
    }

    @Override
    public ResponseDataDTO blaNo(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.blaNo(rbiBondNominationDto);
    }
    @Override
    public ResponseDataDTO getInqBlaNo(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.getInqBlaNo(rbiBondNominationDto);
    }
    @Override
    public ResponseDataDTO getsubscriptionIdMod(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.getsubscriptionIdMod(rbiBondNominationDto);
    }
    @Override
    public ResponseDataDTO getsubscriptionIdInq(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.getsubscriptionIdInq(rbiBondNominationDto);
    }

    @Override
    public ResponseDataDTO nomineeValidation(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.nomineeValidation(rbiBondNominationDto);
    }

    @Override
    public ResponseDataDTO fetchState(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationDao.fetchState(rbiBondNominationDto);

    }
}
