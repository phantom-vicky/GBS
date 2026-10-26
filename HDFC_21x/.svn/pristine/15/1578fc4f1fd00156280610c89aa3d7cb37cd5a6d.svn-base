package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.BondSubAccClosureBeforeMaturityBO;
import com.isl.gbm.rbibond.bo.impl.SubscriptionAccClosingOnMaturityBOImpl;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.exceptions.FileDownloadException;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanValResponseDTO;
import com.isl.gbm.rbibond.externalservice.service.ExternalService;
import com.isl.gbm.rbibond.service.BondSubAccClosureBeforeMaturityService;
import com.isl.gbm.rbibond.util.Sftp;
import com.isl.gbm.rbibond.util.SftpUploader;
import com.isl.gbm.user.isac.common.CommonConstant;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class BondSubAccClosureBeforeMaturityServiceImpl implements BondSubAccClosureBeforeMaturityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BondSubAccClosureBeforeMaturityServiceImpl.class);
    private static final String STR_WITH_NINE_SPACES = "         ";
    private static final String STR_WITH_SEVEN_SPACES = "       ";
    private final BondSubAccClosureBeforeMaturityBO bo;
    private final ExternalService externalService;
    private final Sftp sftp;

    @Value("${sftp.payment.file.bond.sub-acc.closure.before-maturity.in.path}")
    private String remoteDir;

    @Value("${profile.type}")
    private String stubProcess;
    private final SubscriptionAccClosingOnMaturityBOImpl achFileUtils;

    private final SftpUploader uploadUtils;

    public BondSubAccClosureBeforeMaturityServiceImpl(final BondSubAccClosureBeforeMaturityBO bo, final Sftp sftp, final ExternalService externalService, final SubscriptionAccClosingOnMaturityBOImpl achFileUtils,SftpUploader uploadUtils) {
        this.bo = bo;
        this.sftp = sftp;
        this.externalService = externalService;
        this.achFileUtils=achFileUtils;
        this.uploadUtils=uploadUtils;
    }

    private static BondInterestPaymentFileHeaderDTO headerRowParser(String line) {
        BondInterestPaymentFileHeaderDTO headerDTO = new BondInterestPaymentFileHeaderDTO();
        headerDTO.setAchTranCode(line.substring(0, 2));
        headerDTO.setControl1(line.substring(2, 9));
        headerDTO.setUserName(line.substring(9, 49));
        headerDTO.setControl2(line.substring(49, 63));
        headerDTO.setAchFileNo(line.substring(63, 72));
        headerDTO.setControl3(line.substring(72, 81));
        headerDTO.setControl4(line.substring(81, 96));
        headerDTO.setLedgerFolioNo(line.substring(96, 99));
        headerDTO.setUserDefLimit(line.substring(99, 112));
        headerDTO.setTotalAmt(line.substring(112, 125));
        headerDTO.setSettlementDate(line.substring(125, 133));
        headerDTO.setItemSeqNo(line.substring(133, 143));
        headerDTO.setCheckSum(line.substring(143, 153));
        headerDTO.setFiller1(line.substring(153, 156));
        headerDTO.setUserNo(line.substring(156, 174));
        headerDTO.setUserRef(line.substring(174, 192));
        headerDTO.setSponsorBankIFSCMICR(line.substring(192, 203));
        headerDTO.setUserBankAccNo(line.substring(203, 238));
        headerDTO.setTotalItems(line.substring(238, 247));
        headerDTO.setSettlementCycle(line.substring(247, 249));
        headerDTO.setFiller2(line.substring(249, 305));
        return headerDTO;
    }

    private static BondInterestPaymentFileDetailsDTO detailsRowParser(String line) {
        BondInterestPaymentFileDetailsDTO detailsDTO = new BondInterestPaymentFileDetailsDTO();
        detailsDTO.setAchTranCode(line.substring(0, 2));
        detailsDTO.setControl1(line.substring(2, 11));
        detailsDTO.setDestAccType(line.substring(11, 13));
        detailsDTO.setLedgerFolioNo(line.substring(13, 16));
        detailsDTO.setControl2(line.substring(16, 31));
        detailsDTO.setBeneficiaryAccName(line.substring(31, 71));
        detailsDTO.setControl3(line.substring(71, 80));
        detailsDTO.setControl4(line.substring(80, 87));
        detailsDTO.setUserName(line.substring(87, 107));
        detailsDTO.setControl5(line.substring(107, 120));
        detailsDTO.setAmount(line.substring(120, 133));
        detailsDTO.setAchItemSeqNo(line.substring(133, 143));
        detailsDTO.setChecksum(line.substring(143, 153));
        detailsDTO.setFlagForSuccess(line.substring(153, 154));
        detailsDTO.setReasonCode(line.substring(154, 156));
        detailsDTO.setDestBankIFSCMICR(line.substring(156, 167));
        detailsDTO.setBeneficiaryBankAccNo(line.substring(167, 202));
        detailsDTO.setSponsorBankIFSCMICR(line.substring(202, 213));
        detailsDTO.setUserNo(line.substring(213, 231));
        detailsDTO.setTransRef(line.substring(231, 261).trim());
        detailsDTO.setProductType(line.substring(261, 264));
        detailsDTO.setBeneficiaryAadharNo(line.substring(264, 278));
        detailsDTO.setuMRN(line.substring(278, 298));
        detailsDTO.setFiller(line.substring(298, 305));
        return detailsDTO;
    }

    private static ResponseDataDTO parseUploadedTextFile(final MultipartFile file) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondInterestPaymentFileDTO paymentFileDTO = new BondInterestPaymentFileDTO();
        List<BondInterestPaymentFileDetailsDTO> detailsDTOList = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> listOfRows = bufferedReader.lines().collect(Collectors.toList());
            List<String> rows = listOfRows.stream().filter(row -> row.length() == 306)
                    .collect(Collectors.toList());
            if ((!listOfRows.isEmpty() && !rows.isEmpty()) && (listOfRows.size() == rows.size())) {
                paymentFileDTO.setFileHeaderDTO(headerRowParser(listOfRows.get(0)));
                listOfRows.stream().skip(1).forEach(
                        item -> detailsDTOList.add(detailsRowParser(item))
                );
                paymentFileDTO.setFileDetailsDTO(detailsDTOList);
                responseDataDTO.setResult(paymentFileDTO);
                responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                return responseDataDTO;
            } else {
                responseDataDTO.setMessage("Upload Valid File with Length of the Rows as 306 Character");
                return responseDataDTO;
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
        }
        return responseDataDTO;
    }

    public FileDownloadWrapperDTO downloadFile(String remoteFilePath, String fileName) {
        ChannelSftp channelSftp = null;
        FileDownloadWrapperDTO fileDownloadWrapperDTO = new FileDownloadWrapperDTO();
        String fullPath = remoteFilePath + fileName + ".txt";
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            channelSftp = this.sftp.createSftp();
            LOGGER.info("SFTP channel established for file download: {}", fullPath);
            try {
                Vector<?> fileList = channelSftp.ls(remoteFilePath);
                LOGGER.info("Read access granted for directory: {}", remoteFilePath);
            } catch (SftpException se) {
                LOGGER.error("Read access denied or directory not found: {}", remoteFilePath, se);
                throw new FileDownloadException("Read access denied for directory: " + remoteFilePath, se);
            }
            try (InputStream inputStream = channelSftp.get(fullPath)) {
                IOUtils.copy(inputStream, outputStream);
                fileDownloadWrapperDTO.setFileContent(outputStream);
                fileDownloadWrapperDTO.setMessage(CommonConstants.SUCCESS_CONST);
                LOGGER.info("File downloaded successfully: {}", fullPath);
                return fileDownloadWrapperDTO;
            }
        } catch (Exception ex) {
            LOGGER.error("Failed to download file from SFTP: {}. Reason: {}", fullPath, ex.getMessage(), ex);
            fileDownloadWrapperDTO.setMessage(ex.getMessage());
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                try {
                    channelSftp.disconnect();
                    LOGGER.info("SFTP channel disconnected.");
                } catch (Exception ex) {
                    LOGGER.warn("Error while disconnecting SFTP channel: {}", ex.getMessage(), ex);
                }
            }
        }
        return fileDownloadWrapperDTO;
    }

    @Override
    public ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        if ("torch".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit()))
            return this.bo.addDetails(paymentRequestDTO);
        else if ("valid".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit())) {
            ResponseDataDTO responseDataDTO2=new ResponseDataDTO();
            List<PanValidationRetriggerResponseDTO> successList = new ArrayList<>();
            List<PanValidationRetriggerResponseDTO> failureList = new ArrayList<>();
            ResponseDataDTO responseDataDTO = this.bo.addDetails(paymentRequestDTO);
            if (!ObjectUtils.isEmpty(responseDataDTO) && responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                List<BondActualInterestPaymentRequestDTO> panValidation = (List<BondActualInterestPaymentRequestDTO>) responseDataDTO.getResult();
                PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO=null;
                for (int i = 0; i < panValidation.size(); i++) {
                    LOGGER.info("Pan Validation Request From DTO  Name {}, DOB {}", panValidation.get(i).getName(), panValidation.get(i).getDob());
                    if ("TEST".equalsIgnoreCase(stubProcess)) {
                        panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
                        panValidationRetriggerResponseDTO.setSchemeCode(paymentRequestDTO.getSchemeCode());
                        panValidationRetriggerResponseDTO.setPanStatus("E");
                        panValidationRetriggerResponseDTO.setOldPan(panValidation.get(i).getPanNoName());
                        panValidationRetriggerResponseDTO.setDob("Y");
                        panValidationRetriggerResponseDTO.setName("Y");
                        panValidationRetriggerResponseDTO.setProcessStatus("Y");
                        panValidationRetriggerResponseDTO.setItrFlag("I");
                        responseDataDTO2=bo.updatePANStatus(panValidationRetriggerResponseDTO);
                        if(responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                            LOGGER.info("{}{}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                            successList.add(panValidationRetriggerResponseDTO);
                            responseDataDTO2.setResult(successList);// ✅ Store success PANs
                        } else {
                            LOGGER.info("{}{}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                            failureList.add(panValidationRetriggerResponseDTO);
                            responseDataDTO2.setResult1(failureList); // ❌ Store failure PANs
                        }
                    } else {
//                     Actual PAN Validation which is going to get call N-SDL Portal
                        PanDetailsDTO panDetailsDTO = new PanDetailsDTO();
                        panDetailsDTO.setDob(panValidation.get(i).getDob());
                        panDetailsDTO.setPan(panValidation.get(i).getPanNoName());
                        panDetailsDTO.setName(panValidation.get(i).getName());
                        ResponseDataDTO panValidationResponse = externalService.panValidation(panDetailsDTO);
                        PanValResponseDTO panValResponseDTO = (PanValResponseDTO) panValidationResponse.getResult();
                        panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
                        panValidationRetriggerResponseDTO.setSchemeCode(paymentRequestDTO.getSchemeCode());
                        if (null!=panValResponseDTO&&null!=panValResponseDTO.getReturnCode()&&panValResponseDTO.getReturnCode().equals("1") &&
                                null!=panValResponseDTO.getReturnDescription()&& panValResponseDTO.getReturnDescription().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                            panValidationRetriggerResponseDTO.setSchemeCode(paymentRequestDTO.getSchemeCode());
                            panValidationRetriggerResponseDTO.setPanNumber(panDetailsDTO.getPan());
                            panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
                            panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
                            panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
                            panValidationRetriggerResponseDTO.setPanStatusDescription(panValResponseDTO.getPanStatusDescription());
                            panValidationRetriggerResponseDTO.setPanNameDescription(panValResponseDTO.getPanNameDescription());
                            panValidationRetriggerResponseDTO.setFatherName(panValResponseDTO.getFatherName());
                            panValidationRetriggerResponseDTO.setPanDobDescription(panValResponseDTO.getPanDobDescription());
                            panValidationRetriggerResponseDTO.setReturnMessage(panValResponseDTO.getReturnMessage());
                            panValidationRetriggerResponseDTO.setReturnCode(panValResponseDTO.getReturnCode());
                            panValidationRetriggerResponseDTO.setReturnDescription(panValResponseDTO.getReturnDescription());
                            panValidationRetriggerResponseDTO.setSeedingStatus(panValResponseDTO.getSeedingStatus());
                            panValidationRetriggerResponseDTO.setSeedingStatusDescription(panValResponseDTO.getSeedingStatusDescription());
                            if ("E".equalsIgnoreCase(panValResponseDTO.getPanStatus()) &&
                                    "Y".equalsIgnoreCase(panValResponseDTO.getName()) &&
                                    "Y".equalsIgnoreCase(panValResponseDTO.getDob())) {
                                panValidationRetriggerResponseDTO.setProcessStatus("Y");}
                            else{panValidationRetriggerResponseDTO.setProcessStatus("M");}
                            panValidationRetriggerResponseDTO.setItrFlag(panValResponseDTO.getSeedingStatus());
                            responseDataDTO2 = bo.updatePANStatus(panValidationRetriggerResponseDTO);
                            if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                                LOGGER.info("{}: {}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                                successList.add(panValidationRetriggerResponseDTO); // ✅ Store success PANs
                            } else {
                                LOGGER.info("{}: {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                                failureList.add(panValidationRetriggerResponseDTO); // ❌ Store failure PANs
                            }
                            responseDataDTO2.setMessage(CommonConstant.SUCCESS_DESC);
                        } else {
                            LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO);
                            panValidationRetriggerResponseDTO.setSchemeCode(paymentRequestDTO.getSchemeCode());
                            panValidationRetriggerResponseDTO.setPanNumber(panDetailsDTO.getPan());
                            panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
                            panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
                            panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
                            panValidationRetriggerResponseDTO.setReturnMessage(panValResponseDTO.getReturnMessage());
                            panValidationRetriggerResponseDTO.setReturnCode(panValResponseDTO.getReturnCode());
                            panValidationRetriggerResponseDTO.setReturnDescription(panValResponseDTO.getReturnDescription());
                            if ("E".equalsIgnoreCase(panValResponseDTO.getPanStatus()) &&
                                    "Y".equalsIgnoreCase(panValResponseDTO.getName()) &&
                                    "Y".equalsIgnoreCase(panValResponseDTO.getDob())) {
                                panValidationRetriggerResponseDTO.setProcessStatus("Y");
                            }
                            else{panValidationRetriggerResponseDTO.setProcessStatus("M");}
                            ResponseDataDTO res = bo.updatePANStatus(panValidationRetriggerResponseDTO);
                            if (null!=res&&res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                                LOGGER.info("External DB update Status: {}", res.getMessage());
                                successList.add(panValidationRetriggerResponseDTO); // ✅ Store success PANs
                            } else {
                                LOGGER.info("External DB update Failure: {}", res.getMessage());
                                failureList.add(panValidationRetriggerResponseDTO); // ❌ Store failure PANs
                            }
                            LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO);
                            responseDataDTO2.setMessage(res.getMessage());
                        }
                    }
                    responseDataDTO2.setResult(successList);
                    responseDataDTO2.setResult1(failureList);
                        /*if (panValResponseDTO.getReturnCode().equals("1") &&
                                panValResponseDTO.getReturnDescription().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                            panValidationRetriggerResponseDTO.setSchemeCode(paymentRequestDTO.getSchemeCode());
                            panValidationRetriggerResponseDTO.setPanNumber(panDetailsDTO.getPan());
                            panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
                            panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
                            panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
                            panValidationRetriggerResponseDTO.setPanStatusDescription(panValResponseDTO.getPanStatusDescription());
                            panValidationRetriggerResponseDTO.setPanNameDescription(panValResponseDTO.getPanNameDescription());
                            panValidationRetriggerResponseDTO.setFatherName(panValResponseDTO.getFatherName());
                            panValidationRetriggerResponseDTO.setPanDobDescription(panValResponseDTO.getPanDobDescription());
                            panValidationRetriggerResponseDTO.setReturnMessage(panValResponseDTO.getReturnMessage());
                            panValidationRetriggerResponseDTO.setReturnCode(panValResponseDTO.getReturnCode());
                            panValidationRetriggerResponseDTO.setReturnDescription(panValResponseDTO.getReturnDescription());
                            panValidationRetriggerResponseDTO.setSeedingStatus(panValResponseDTO.getSeedingStatus());
                            panValidationRetriggerResponseDTO.setSeedingStatusDescription(panValResponseDTO.getSeedingStatusDescription());
                            if (panValResponseDTO.getPanStatus().equalsIgnoreCase("E") &&
                                    panValResponseDTO.getName().equalsIgnoreCase("Y") &&
                                    panValResponseDTO.getDob().equalsIgnoreCase("Y")) {
                                panValidationRetriggerResponseDTO.setProcessStatus("Y");
                            } else {
                                panValidationRetriggerResponseDTO.setProcessStatus("M");
                            }
                            panValidationRetriggerResponseDTO.setItrFlag(panValResponseDTO.getSeedingStatus());
                        }
                        responseDataDTO2=bo.updatePANStatus(panValidationRetriggerResponseDTO);
                        if(responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                            LOGGER.info("{}{}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                            successList.add(panValidationRetriggerResponseDTO); // ✅ Store success PANs
                            responseDataDTO2.setResult(successList);
                        } else {
                            LOGGER.info("{}{}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                            failureList.add(panValidationRetriggerResponseDTO); // ❌ Store failure PANs
                            responseDataDTO2.setResult1(failureList); // ❌ Store failure PANs
                        }
                    }*/
                }
            }
            return responseDataDTO2;
        } else if ("details".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit()))
            return this.bo.addDetailsForDetails(paymentRequestDTO);
        else if ("submit".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit()))
            return this.bo.addSubmit(paymentRequestDTO);

        throw new IllegalArgumentException("Unknown Option");
    }

    @Override
    public ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        if ("blaTorch".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit()))
            return this.bo.verifyBLADetails(paymentRequestDTO);
        else if ("scheme".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit()))  // Scheme Details and
            // Verify details are getting fetched from here
            return this.bo.verifyDetails(paymentRequestDTO);
        else if ("verify".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit())) {
            ResponseDataDTO responseDataDTO = this.bo.verifySubmit(paymentRequestDTO);
            if (responseDataDTO != null && CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())) {
                BondACHFileWrapperDTO fileWrapperDTO = (BondACHFileWrapperDTO) responseDataDTO.getResult();
                BondACHFileHeaderDTO headerDTO = fileWrapperDTO.getAchFileHeaderDTO();
                List<BondACHFileDetailsDTO> detailsList = fileWrapperDTO.getAchFileDetailsList();
                String fileName = fileWrapperDTO.getFileName();
                List<String> listOfData = new LinkedList<>();
                listOfData.add(achFileUtils.headerStringCreationForACHFile(headerDTO));
                Consumer<BondACHFileDetailsDTO> consumeFn = element ->
                        listOfData.add(achFileUtils.detailsStringCreationForACHFile(element));
                detailsList.forEach(consumeFn);
                uploadUtils.uploadFileToSFTP(remoteDir, fileName, listOfData);
                LOGGER.info(" File written successfully!!!! with file name as {}", fileName);
                return responseDataDTO;
            }
            return responseDataDTO;
        } else if ("reject".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit()))
            return this.bo.delete(paymentRequestDTO);

        throw new IllegalArgumentException("Unknown Option");
    }

    @Override
    public ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.bo.inquiry(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            ResponseDataDTO boResponse = this.bo.downloadFileFromSFTP(paymentRequestDTO);
            if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(boResponse.getMessage())
                    && boResponse.getResult() instanceof String) {
                String fileName = (String) responseDataDTO.getResult();
                FileDownloadWrapperDTO fileDownloadWrapperDTO = downloadFile(remoteDir, fileName);
                LOGGER.info("FileName: {}", fileName);
                if (fileDownloadWrapperDTO != null && fileDownloadWrapperDTO.getFileContent() != null) {
                FileResponseDto fileResponseDto = new FileResponseDto();
                fileResponseDto.setFileName(fileName + ".txt");
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(fileDownloadWrapperDTO.getFileContent().toByteArray()));
                responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                responseDataDTO.setResult(fileResponseDto);
                } else if (fileDownloadWrapperDTO != null && fileDownloadWrapperDTO.getMessage() != null && fileDownloadWrapperDTO.getMessage().equalsIgnoreCase("No such file")) {
                    ResponseDataDTO bOResponse = this.bo.downloadFileManual(paymentRequestDTO);
                    responseDataDTO.setMessage(bOResponse.getMessage());
                    responseDataDTO.setResult(bOResponse.getResult());
                } else {
                    ResponseDataDTO bOResponse = this.bo.downloadFileManual(paymentRequestDTO);
                    responseDataDTO.setMessage(bOResponse.getMessage());
                    responseDataDTO.setResult(bOResponse.getResult());
                }
            } else {
                responseDataDTO.setMessage(boResponse.getMessage());
            }
        }catch(Exception ex){
            LOGGER.info("Unexpected error occurred: "+ex.getMessage());
            ResponseDataDTO bOResponse = this.bo.downloadFileManual(paymentRequestDTO);
            responseDataDTO.setMessage(bOResponse.getMessage());
            responseDataDTO.setResult(bOResponse.getResult());
            return responseDataDTO;
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO uploadFile(MultipartFile textFile, String schemeCode, String paymentDate) {
        ResponseDataDTO responseDataDTO =new ResponseDataDTO();
        if (textFile.getOriginalFilename() == null || !textFile.getOriginalFilename().toLowerCase().endsWith(".txt")) {
            responseDataDTO.setMessage("Invalid file type. Only .txt files are allowed");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        responseDataDTO = parseUploadedTextFile(textFile);
        if (!ObjectUtils.isEmpty(responseDataDTO) && CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())
                && responseDataDTO.getResult() instanceof BondInterestPaymentFileDTO) {
            return this.bo.uploadFile((BondInterestPaymentFileDTO) responseDataDTO.getResult(), schemeCode, paymentDate);
        }
        return responseDataDTO;
    }

    private String headerStringCreationForACHFile(BondACHFileHeaderDTO headerDTO) {
        String control1 = headerDTO.getControl() != null && !ObjectUtils.isEmpty(headerDTO.getControl()) ? headerDTO.getControl() : STR_WITH_SEVEN_SPACES;  // with 7 Space size
        String control2 = headerDTO.getControl1() != null && !ObjectUtils.isEmpty(headerDTO.getControl1()) ? headerDTO.getControl1() : "              "; // with 14 space size
        String control3 = headerDTO.getControl2() != null && !ObjectUtils.isEmpty(headerDTO.getControl2()) ? headerDTO.getControl2() : STR_WITH_NINE_SPACES; // with 9 space size
        String control4 = headerDTO.getControl3() != null && !ObjectUtils.isEmpty(headerDTO.getControl3()) ? headerDTO.getControl3() : "               "; // with 15 space size
        String filler1 = headerDTO.getFiller1() != null && !ObjectUtils.isEmpty(headerDTO.getFiller1()) ? headerDTO.getFiller1() : "   "; // with 3 space size
        String filler2 = headerDTO.getFiller2() != null && !ObjectUtils.isEmpty(headerDTO.getFiller2()) ? headerDTO.getFiller2() : "                                                         "; // with 57 space size

        return headerDTO.getAchTransactionCode() + control1 + headerDTO.getUserName() + control2 + headerDTO.getAchFileNumber()
                + control3 + control4 + headerDTO.getLedgerFolioNumber() + headerDTO.getUserDefLimit() + headerDTO.getTotalAmtInPaise() +
                headerDTO.getSettlementDate() + headerDTO.getReserved1() + headerDTO.getReserved2() + filler1 + headerDTO.getUserName() +
                headerDTO.getUserReference() + headerDTO.getSponsorBankIFSCMICRIIN() + headerDTO.getUserBankAccountNumber() +
                headerDTO.getTotalItems() + headerDTO.getSettlementCycle() + filler2;
    }

    private String detailsStringCreationForACHFile(BondACHFileDetailsDTO fileDetailsDTO) {
        String control = fileDetailsDTO.getControl() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getControl()) ? fileDetailsDTO.getControl() : STR_WITH_NINE_SPACES;  // 9 spaces
        String destAccType = fileDetailsDTO.getDestinationAccountType() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getDestinationAccountType()) ? fileDetailsDTO.getDestinationAccountType() : "  ";
        String ledgerPortFolioNo = fileDetailsDTO.getLedgerFolioNumber() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getLedgerFolioNumber()) ? fileDetailsDTO.getLedgerFolioNumber() : "   ";
        String control1 = fileDetailsDTO.getControl1() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getControl1()) ? fileDetailsDTO.getControl1() : "               "; // 15 spaces
        String control2 = fileDetailsDTO.getControl2() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getControl2()) ? fileDetailsDTO.getControl2() : STR_WITH_NINE_SPACES;  // 9 spaces
        String control3 = fileDetailsDTO.getControl3() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getControl3()) ? fileDetailsDTO.getControl3() : STR_WITH_SEVEN_SPACES;  // 7 spaces
        String control4 = fileDetailsDTO.getControl4() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getControl4()) ? fileDetailsDTO.getControl4() : "             ";  // 13 spaces
        String reserved1 = fileDetailsDTO.getReserved1() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getReserved1()) ? fileDetailsDTO.getReserved1() : "          ";  // 10 spaces
        String reserved2 = fileDetailsDTO.getReserved2() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getReserved2()) ? fileDetailsDTO.getReserved2() : "          ";  // 10 spaces
        String reserved3 = fileDetailsDTO.getReserved3() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getReserved3()) ? fileDetailsDTO.getReserved3() : " ";  // 1 spaces
        String reserved4 = fileDetailsDTO.getReserved4() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getReserved4()) ? fileDetailsDTO.getReserved4() : "  ";  // 2 spaces
        String filler = fileDetailsDTO.getFiller() != null && !ObjectUtils.isEmpty(fileDetailsDTO.getFiller()) ? fileDetailsDTO.getFiller() : STR_WITH_SEVEN_SPACES;  // 7 spaces

        return fileDetailsDTO.getAchTransactionCode() + control + destAccType + ledgerPortFolioNo +
                control1 + fileDetailsDTO.getBeneficiaryAccHolderName() + control2 + control3 + fileDetailsDTO.getUserName() + control4 +
                fileDetailsDTO.getAmt() + reserved1 + reserved2 + reserved3 + reserved4 + fileDetailsDTO.getDestinationBankIFSCMICRIIN() +
                fileDetailsDTO.getBeneficiaryBankAccNo() + fileDetailsDTO.getSponsorBankIFSCMICRIIN() + fileDetailsDTO.getUserNo() +
                fileDetailsDTO.getTxnRef() + fileDetailsDTO.getPdtType() + fileDetailsDTO.getBeneficiaryArNo() +
                fileDetailsDTO.getUMRN() + filler;
    }
}
