package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.RbiBondClosureOnMaturDeathBo;
import com.isl.gbm.rbibond.dao.RbiBondClosureOnMaturDeathDao;
import com.isl.gbm.rbibond.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class RbiBondClosureOnMaturDeathBoImpl implements RbiBondClosureOnMaturDeathBo {


    @Autowired
    RbiBondClosureOnMaturDeathDao rbiBondClosureOnMaturDeathDao;

    private final SubscriptionAccClosingOnMaturityBOImpl achFileUtils;

    public RbiBondClosureOnMaturDeathBoImpl(RbiBondClosureOnMaturDeathDao rbiBondClosureOnMaturDeathDao, SubscriptionAccClosingOnMaturityBOImpl achFileUtils) {
        this.rbiBondClosureOnMaturDeathDao = rbiBondClosureOnMaturDeathDao;
        this.achFileUtils = achFileUtils;
    }

    @Override
    public ResponseDataDTO getAddDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathDao.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }
    @Override
    public ResponseDataDTO getAddValidation(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathDao.getAddValidation(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathDao.getVerDelDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getInqDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathDao.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO add(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathDao.add(rbiBondClosureBeforeMaturDTO);
    }
    @Override
    public ResponseDataDTO getPanValidation(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathDao.getPanValidation(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO verifyBLADetails(RbiBondClosureOnMaturDeathDTO paymentRequestDTO) {
        return rbiBondClosureOnMaturDeathDao.verifySubmit(paymentRequestDTO);

    }

    @Override
    public ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO, String schemeCode, String paymentDate) {
        return rbiBondClosureOnMaturDeathDao.uploadFile(bondInterestPaymentFileDTO, schemeCode, paymentDate);
    }

    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return rbiBondClosureOnMaturDeathDao.downloadFileFromSFTP(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        FileResponseDto fileResponseDto = new FileResponseDto();
        try{
            responseDataDTO=rbiBondClosureOnMaturDeathDao.downloadFileManual(paymentRequestDTO);
            if(responseDataDTO != null && CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())) {
                BondACHFileWrapperDTO fileWrapperDTO = (BondACHFileWrapperDTO) responseDataDTO.getResult();
                BondACHFileHeaderDTO headerDTO = fileWrapperDTO.getAchFileHeaderDTO();
                List<BondACHFileDetailsDTO> detailsList = fileWrapperDTO.getAchFileDetailsList();
                String fileName = fileWrapperDTO.getFileName();
                List<String> listOfData = new LinkedList<>();
                listOfData.add(achFileUtils.headerStringCreationForACHFile(headerDTO));
                Consumer<BondACHFileDetailsDTO> consumeFn = element ->
                        listOfData.add(achFileUtils.detailsStringCreationForACHFile(element));
                detailsList.forEach(consumeFn);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                for (String line : listOfData) {
                    baos.write(line.getBytes(StandardCharsets.UTF_8));
                    baos.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
                }
                byte[] fileBytes = baos.toByteArray();
                if(null!=fileBytes) {
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(fileBytes));
                    fileResponseDto.setFileName(fileName+".txt");
                    responseDataDTO.setResult(fileResponseDto);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            responseDataDTO.setMessage("Error: " + e.getMessage());
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO updatePANStatus(PanValidationRetriggerResponseDTO responseDTO) {
        return this.rbiBondClosureOnMaturDeathDao.updatePANStatus(responseDTO);
    }
    @Override
    public ResponseDataDTO verify(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathDao.verifySubmit(rbiBondClosureBeforeMaturDTO);

    }

    @Override
    public ResponseDataDTO delete(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathDao.delete(rbiBondClosureBeforeMaturDTO);
    }
}
