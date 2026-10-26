package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.BondActualInterestPaymentBO;
import com.isl.gbm.rbibond.dao.BondActualInterestPaymentDAO;
import com.isl.gbm.rbibond.dto.*;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class BondActualInterestPaymentBOImpl implements BondActualInterestPaymentBO {

    private final BondActualInterestPaymentDAO paymentDAO;
    private final SubscriptionAccClosingOnMaturityBOImpl achFileUtils;

    public BondActualInterestPaymentBOImpl(final BondActualInterestPaymentDAO paymentDAO,final SubscriptionAccClosingOnMaturityBOImpl achFileUtils) {
        this.paymentDAO = paymentDAO;
        this.achFileUtils=achFileUtils;
    }

    @Override
    public ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        if (paymentRequestDTO.getDetailsOrSubmit().equalsIgnoreCase("details"))
            return this.paymentDAO.addDetails(paymentRequestDTO);
        else
            return this.paymentDAO.addSubmit(paymentRequestDTO);

    }

    @Override
    public ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentDAO.inquiry(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentDAO.verifyDetails(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO reject(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentDAO.reject(paymentRequestDTO);
    }


    @Override
    public ResponseDataDTO verifySubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentDAO.verifySubmit(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO,
                                      String schemeCode, String halfYear, String year) {
        return this.paymentDAO.uploadFile(bondInterestPaymentFileDTO, schemeCode, halfYear, year);
    }

    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentDAO.downloadFileFromSFTP(paymentRequestDTO);
    }
    @Override
    public ResponseDataDTO downloadMergedUnMerged(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentDAO.downloadMergedUnMerged(paymentRequestDTO);
    }
    @Override
    public ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        FileResponseDto fileResponseDto=new FileResponseDto();
        try{
            responseDataDTO=paymentDAO.downloadFileManual(paymentRequestDTO);
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
}
