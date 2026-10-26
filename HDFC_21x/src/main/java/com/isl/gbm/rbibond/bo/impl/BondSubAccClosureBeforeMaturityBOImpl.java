package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.BondSubAccClosureBeforeMaturityBO;
import com.isl.gbm.rbibond.dao.BondSubAccClosureBeforeMaturityDAO;
import com.isl.gbm.rbibond.dto.*;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class BondSubAccClosureBeforeMaturityBOImpl implements BondSubAccClosureBeforeMaturityBO {

    private final BondSubAccClosureBeforeMaturityDAO maturityDAO;
    private final SubscriptionAccClosingOnMaturityBOImpl achFileUtils;

    public BondSubAccClosureBeforeMaturityBOImpl(BondSubAccClosureBeforeMaturityDAO maturityDAO,SubscriptionAccClosingOnMaturityBOImpl achFileUtils) {
        this.maturityDAO = maturityDAO;
        this.achFileUtils=achFileUtils;
    }

    @Override
    public ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.maturityDAO.addDetails(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.maturityDAO.inquiry(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {

        return this.maturityDAO.downloadFileFromSFTP(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        FileResponseDto fileResponseDto = new FileResponseDto();
        try{
            responseDataDTO=maturityDAO.downloadFileManual(paymentRequestDTO);
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
    public ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO, String schemeCode, String paymentDate) {
        return this.maturityDAO.uploadFile(bondInterestPaymentFileDTO, schemeCode, paymentDate);
    }

    @Override
    public ResponseDataDTO updatePANStatus(PanValidationRetriggerResponseDTO responseDTO) {
        return this.maturityDAO.updatePANStatus(responseDTO);
    }

    @Override
    public ResponseDataDTO addDetailsForDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.maturityDAO.addDetailsForDetails(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO addSubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.maturityDAO.addSubmit(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.maturityDAO.verifyDetails(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO verifyBLADetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.maturityDAO.verifyBLADetails(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO verifySubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.maturityDAO.verifySubmit(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO delete(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.maturityDAO.delete(paymentRequestDTO);
    }
}


