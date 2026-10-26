/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.wrapper.BankBranchDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.generator.CbsBulkUploadGenerator;
import com.isl.gbm.cbs.integration.manager.BankBranchManager;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.cbs.integration.service.BankBranchService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dto.FileResponseDto;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * The Class BankBranchServiceImpl.
 */
@Service
@Transactional
public class BankBranchServiceImpl implements BankBranchService {

    /**
     * The bank branch manager.
     */
    @Autowired
    private BankBranchManager bankBranchManager;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.BankBranchService#saveBank(com.isl.
     * gbm.cbs.integration.dto.BankBranchDto)
     */
    @Override
    public void create(final BankBranchDto bankBranchDto) {
        this.bankBranchManager.create(bankBranchDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.BankBranchService#deleteBank(com.isl.
     * gbm.cbs.integration.dto.BankBranchDto)
     */
    @Override
    public void delete(final BankBranchDto bankBranchDto) {
        this.bankBranchManager.delete(bankBranchDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.BranchBankService#deleteBanks(java.
     * lang.String)
     */
    @Override
    public ResponseDataDTO deleteBanks(final String fileName, final String remarks) {
        if (remarks == null || remarks.trim().isEmpty()) {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
      return  this.bankBranchManager.deleteBanks(fileName, remarks);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.BranchBankService#fileNameExists(java
     * .lang.String)
     */
    @Override
    public boolean fileNameExists(final String fileName) {
        return this.bankBranchManager.fileNameExists(fileName);
    }

    @Override
    public boolean unVerifiedDetails() {
        return this.bankBranchManager.unVerifiedDetails();
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.BankBranchService#getAllBankDetails()
     */
    @Override
    public List<BankBranchDto> getAllBankDetails() {
        return this.bankBranchManager.getAllBankDetails();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.BankBranchService#
     * getAllBankDetailsinquiry()
     */
    @Override
    public List<BankBranchDtoAuditLogWrapper> getAllBankDetailsForInquiry() {
        return this.bankBranchManager.getAllBankDetailsForInquiry();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.BankBranchService#getBank(java.lang.
     * String)
     */
    @Override
    public BankBranchDtoAuditLogWrapper getBankBranchDetails(final int id) {
        return this.bankBranchManager.getBankBranchDetails(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.BranchBankService#getBranchBankList()
     */
    @Override
    public List<BankBranchDto> getBranchBankList() {
        return this.bankBranchManager.getBranchBankList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.BankBranchService#
     * getSolIdandDescription()
     */
    @Override
    public List<BankBranchDto> getSolIdandDescription() {
        return this.bankBranchManager.getSolIdandDescription();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.BranchBankService#
     * getUnverifiedBankDetails(java.lang.String)
     */
    @Override
    public List<BankBranchDto> getUnverifiedBankDetails(final String fileName) {
        return this.bankBranchManager.getUnverifiedBankDetails(fileName);
    }

    @Override
    public List<BankBranchDto> getInquiryBankDetails(final String fileName) {
        return this.bankBranchManager.getInquiryBankDetails(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.BranchBankService#getUnverifiedFiles( )
     */
    @Override
    public List<String> getUnverifiedFiles() {
        return this.bankBranchManager.getUnverifiedFiles();
    }

    @Override
    public List<String> getInquiryFiles() {
        return this.bankBranchManager.getInquiryFiles();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.BankBranchService#getUnverifiedBanks( )
     */
    @Override
    public List<BankBranchDtoAuditLogWrapper> getUnverifiedRecords() {
        return this.bankBranchManager.getUnverifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.BankBranchService#getAllBankDetails()
     */
    @Override
    public List<BankBranchDto> getVerifiedRecords() {
        return this.bankBranchManager.getVerifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.BankBranchService#isSolIdExists(java.
     * lang.String)
     */
    @Override
    public Boolean isSolIdExists(final String solId) {
        return this.bankBranchManager.isSolIdExists(solId);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.BranchBankService#saveDetails(java.
     * util.List)
     */
    @Override
    public ResponseDataDTO saveDetails(final List<BankBranchDto> branchBankDtoList) {
        return this.bankBranchManager.saveDetails(branchBankDtoList);

    }

    /**
     * Sets the bank branch manager.
     *
     * @param bankBranchManager the new bank branch manager
     */
    public void setBankBranchManager(final BankBranchManager bankBranchManager) {
        this.bankBranchManager = bankBranchManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.BankBranchService#updateBankDetails(
     * com.isl.gbm.cbs.integration.dto.BankBranchDto)
     */
    @Override
    public void update(final BankBranchDto bankBranchDto) {
        this.bankBranchManager.update(bankBranchDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.BankBranchService#verifyBank(com.isl.
     * gbm.cbs.integration.dto.BankBranchDto)
     */
    @Override
    public void verify(final BankBranchDto bankBranchDto) {
        this.bankBranchManager.verify(bankBranchDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.BranchBankService#verifyBanks(java.
     * lang.String)
     */
    @Override
    public ResponseDataDTO verifyBanks(final String fileName) {
        return this.bankBranchManager.verifyBanks(fileName);
    }

    @Override
    public ResponseDataDTO addDetails(BankBranchDto dto) {
        return bankBranchManager.addDetails(dto);
    }

    @Override
    public ResponseDataDTO inquiry(BankBranchDto dto) {
        return bankBranchManager.inquiry(dto);
    }
    @Override
    public ResponseDataDTO verDelDetails(BankBranchDto dto) {
        return bankBranchManager.verDelDetails(dto);
    }
    @Override
    public ResponseDataDTO modifyDetails(BankBranchDto dto) {
        return bankBranchManager.modifyDetails(dto);
    }
    @Override
    public ResponseDataDTO modify(BankBranchDto dto) {
        return bankBranchManager.modify(dto);
    }

    @Override
    public List<BankBranchMaster> getAllBranchId(BankBranchDto dto) {
        return bankBranchManager.getAllBranchId(dto);
    }



    @Override
    public ResponseDataDTO verifySubmit(BankBranchDto dto) {
        return bankBranchManager.verifySubmit(dto);
    }

    @Override
    public ResponseDataDTO deleteSubmit(BankBranchDto dto) {
        return bankBranchManager.deleteSubmit(dto);
    }

    @Override
    public ResponseEntity<ResponseDataDTO> getUnverifiedBankDetailsDownload(CbsCommonDTo cbsCommonDTo) throws IOException, InvalidFormatException {
        FileResponseDto fileResponseDto = new FileResponseDto();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(cbsCommonDTo.getFunctionality().equalsIgnoreCase("bankbranch")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(CbsBulkUploadGenerator.bankBranchToExcelFile()));
            fileResponseDto.setFileName(cbsCommonDTo.getFunctionality()+ ".xls");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(cbsCommonDTo.getFunctionality().equalsIgnoreCase("officeaccountplaceholder")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(CbsBulkUploadGenerator.officeAccPlaceHolder()));
            fileResponseDto.setFileName(cbsCommonDTo.getFunctionality()+ ".xls");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(cbsCommonDTo.getFunctionality().equalsIgnoreCase("instruments")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(CbsBulkUploadGenerator.intrumentDetails()));
            fileResponseDto.setFileName(cbsCommonDTo.getFunctionality()+ ".xls");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(cbsCommonDTo.getFunctionality().equalsIgnoreCase("transactioncodes")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(CbsBulkUploadGenerator.listOfTransactionDetailCodes()));
            fileResponseDto.setFileName(cbsCommonDTo.getFunctionality()+ ".xls");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(cbsCommonDTo.getFunctionality().equalsIgnoreCase("otherbankdetails")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(CbsBulkUploadGenerator.otherBankDetails()));
            fileResponseDto.setFileName(cbsCommonDTo.getFunctionality()+ ".xls");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(cbsCommonDTo.getFunctionality().equalsIgnoreCase("otherbankbranches")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(CbsBulkUploadGenerator.branchsOfTheOtherBank()));
            fileResponseDto.setFileName(cbsCommonDTo.getFunctionality()+ ".xls");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(cbsCommonDTo.getFunctionality().equalsIgnoreCase("calendarentries")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(CbsBulkUploadGenerator.maintenanceOfCalenderEntreies()));
            fileResponseDto.setFileName(cbsCommonDTo.getFunctionality()+ ".xls");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(cbsCommonDTo.getFunctionality().equalsIgnoreCase("schemecodes")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(CbsBulkUploadGenerator.schemeCodeExcel()));
            fileResponseDto.setFileName(cbsCommonDTo.getFunctionality()+ ".xls");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(cbsCommonDTo.getFunctionality().equalsIgnoreCase("userdetails")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(CbsBulkUploadGenerator.userWorkClassInformation()));
            fileResponseDto.setFileName(cbsCommonDTo.getFunctionality()+ ".xls");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(cbsCommonDTo.getFunctionality().equalsIgnoreCase("branchifscdetails")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(CbsBulkUploadGenerator.branchIfscCodes()));
            fileResponseDto.setFileName(cbsCommonDTo.getFunctionality()+ ".xls");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
//        else{
//            responseDataDTO.setMessage(CommonConstants.EXCEPTION_MESSAGE);
//        }
        return APIResponseBuilder.buildResponseFromDto(responseDataDTO);
    }


}
