package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.RbiBondClosureOnMaturDeathBo;
import com.isl.gbm.rbibond.bo.impl.SubscriptionAccClosingOnMaturityBOImpl;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.exceptions.FileDownloadException;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanValResponseDTO;
import com.isl.gbm.rbibond.externalservice.service.ExternalService;
import com.isl.gbm.rbibond.service.RbiBondClosureOnMaturDeathService;
import com.isl.gbm.rbibond.util.Sftp;
import com.isl.gbm.rbibond.util.SftpUploader;
import com.isl.gbm.user.isac.common.CommonConstant;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class RbiBondClosureOnMaturDeathServiceImpl implements RbiBondClosureOnMaturDeathService {


    private static final Logger LOGGER = LoggerFactory.getLogger(BondSubAccClosureBeforeMaturityServiceImpl.class);
    private static final String STR_WITH_NINE_SPACES = "         ";
    private static final String STR_WITH_SEVEN_SPACES = "       ";
    @Value("${sftp.payment.file.bond.sub-acc.closure.on-maturity.in.path}")
    private String remoteDir;
    private final ExternalService externalService;
    private final Sftp sftp;

    @Autowired
    private CommonConstants commonConstants;

    @Autowired
    RbiBondClosureOnMaturDeathBo rbiBondClosureOnMaturDeathBo;

    private final SftpUploader uploadUtils;


    private final SubscriptionAccClosingOnMaturityBOImpl achFileUtils;

    public RbiBondClosureOnMaturDeathServiceImpl(ExternalService externalService, final Sftp sftp, SubscriptionAccClosingOnMaturityBOImpl achFileUtils,SftpUploader uploadUtils) {
        this.externalService = externalService;
        this.sftp = sftp;
        this.achFileUtils=achFileUtils;
        this.uploadUtils=uploadUtils;

    }

    @Override
    public ResponseDataDTO getAddDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathBo.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getAddValidation(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathBo.getAddValidation(rbiBondClosureBeforeMaturDTO);
    }


    /*@Override
    public ResponseDataDTO getVerDelDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathBo.getVerDelDetails(rbiBondClosureBeforeMaturDTO);
    }*/
    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondClosureOnMaturDeathDTO paymentRequestDTO) {
       if ("scheme".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit()))  // Scheme Details and
            // Verify details are getting fetched from here
            return rbiBondClosureOnMaturDeathBo.getVerDelDetails(paymentRequestDTO);
        else if ("verify".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit())) {
            ResponseDataDTO responseDataDTO = rbiBondClosureOnMaturDeathBo.verify(paymentRequestDTO);
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
            return rbiBondClosureOnMaturDeathBo.delete(paymentRequestDTO);
        throw new IllegalArgumentException("Unknown Option");
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



    private void uploadFileToSFTP(String remoteDir, String fileName, List<String> data) {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = this.sftp.createSftp();
            // Write data to a temporary file
            File tempFile = new File(fileName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                for (String line : data) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            // Upload file to remote SFTP directory
            channelSftp.rename(tempFile.getAbsolutePath(), remoteDir + fileName + ".txt");
            LOGGER.info("File uploaded successfully to {}", remoteDir);

            // Delete the temporary file
            if (tempFile.delete()) {
                LOGGER.info("Temp File Deleted SuccessFully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channelSftp != null)
                channelSftp.disconnect();
        }
    }


    @Override
    public ResponseDataDTO getInqDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathBo.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO add(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathBo.add(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathBo.verify(rbiBondClosureBeforeMaturDTO);
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
            return rbiBondClosureOnMaturDeathBo.uploadFile((BondInterestPaymentFileDTO) responseDataDTO.getResult(), schemeCode, paymentDate);
        }
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            ResponseDataDTO boResponse = rbiBondClosureOnMaturDeathBo.downloadFileFromSFTP(paymentRequestDTO);
            if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(boResponse.getMessage())
                    && boResponse.getResult() instanceof String) {
                String fileName = (String) boResponse.getResult();
                FileDownloadWrapperDTO fileDownloadWrapperDTO = downloadFile(remoteDir, fileName);
                if(fileDownloadWrapperDTO != null && fileDownloadWrapperDTO.getFileContent() != null){
                FileResponseDto fileResponseDto = new FileResponseDto();
                fileResponseDto.setFileName(fileName + ".txt");
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(fileDownloadWrapperDTO.getFileContent().toByteArray()));
                responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                responseDataDTO.setResult(fileResponseDto);
            } else if (fileDownloadWrapperDTO != null && fileDownloadWrapperDTO.getMessage() != null && fileDownloadWrapperDTO.getMessage().equalsIgnoreCase("No such file")) {
                ResponseDataDTO bOResponse = this.rbiBondClosureOnMaturDeathBo.downloadFileManual(paymentRequestDTO);
                responseDataDTO.setMessage(bOResponse.getMessage());
                responseDataDTO.setResult(bOResponse.getResult());
            } else {
                ResponseDataDTO bOResponse = this.rbiBondClosureOnMaturDeathBo.downloadFileManual(paymentRequestDTO);
                responseDataDTO.setMessage(bOResponse.getMessage());
                responseDataDTO.setResult(bOResponse.getResult());
            }
            } else {
                responseDataDTO.setMessage(boResponse.getMessage());
            }
        }catch(Exception ex){
            LOGGER.info("Unexpected error occurred: "+ex.getMessage());
            ResponseDataDTO bOResponse = this.rbiBondClosureOnMaturDeathBo.downloadFileManual(paymentRequestDTO);
            responseDataDTO.setMessage(bOResponse.getMessage());
            responseDataDTO.setResult(bOResponse.getResult());
            return responseDataDTO;
        }
        return responseDataDTO;
    }

    public FileDownloadWrapperDTO downloadFile(String remoteFilePath, String fileName) {
        ChannelSftp channelSftp = null;
        FileDownloadWrapperDTO fileDownloadWrapperDTO = new FileDownloadWrapperDTO();
        String fullPath = remoteFilePath + fileName + ".txt";

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            channelSftp = sftp.createSftp();
            LOGGER.info("SFTP channel established for file download: {}", fullPath);

            // ✅ Check read access on the directory
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
    @Override
    public ResponseDataDTO getPanValidation(RbiBondClosureOnMaturDeathDTO paymentRequestDTO) {
        ResponseDataDTO responseDataDTO2=new ResponseDataDTO();

        if (paymentRequestDTO == null) {
            throw new IllegalArgumentException("paymentRequestDTO cannot be null");
        }
        if (paymentRequestDTO.getPanNumList() == null) {
            throw new IllegalArgumentException("PanNumList cannot be null");
        }
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<PanValidationRetriggerResponseDTO> successList = new ArrayList<>();
        List<PanValidationRetriggerResponseDTO> failureList = new ArrayList<>();
         responseDataDTO2 = rbiBondClosureOnMaturDeathBo.getPanValidation(paymentRequestDTO);
         try{
        if (!ObjectUtils.isEmpty(responseDataDTO2) && responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
            PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();

            for (int i = 0; i < paymentRequestDTO.getPanNumList().size(); i++) {
                LOGGER.info("Pan Validation Request From DTO Pan No {}, Name {}, DOB {}",
                        paymentRequestDTO.getPanNumList().get(i).getPanNo(), paymentRequestDTO.getPanNumList().get(i).getNomineeName(), paymentRequestDTO.getPanNumList().get(i).getDateOfBirthOfMinor());

                PanDetailsDTO panDetailsDTO = new PanDetailsDTO();

                String dob = paymentRequestDTO.getPanNumList().get(i).getDateOfBirthOfMinor();

                try {
                    dob = outputFormat.format(inputFormat.parse(dob)); // Convert and reassign
                } catch (Exception e) {
                    e.printStackTrace(); // Handle errors
                }
                panDetailsDTO.setDob(dob);
                panDetailsDTO.setPan(paymentRequestDTO.getPanNumList().get(i).getPanNo());
                panDetailsDTO.setName(paymentRequestDTO.getPanNumList().get(i).getNomineeName());

                ResponseDataDTO panValidationResponse = externalService.panValidation(panDetailsDTO);
                if (panValidationResponse == null || panValidationResponse.getResult() == null) {
                    throw new IllegalStateException("PAN validation response or result cannot be null");
                }

                PanValResponseDTO panValResponseDTO = (PanValResponseDTO) panValidationResponse.getResult();
                if (panValResponseDTO == null) {
                    throw new IllegalStateException("PAN validation response DTO cannot be null");
                }

                Map<String, String> panStatusMap = new HashMap<>(); // Map to store PAN and its status
                panStatusMap.put("pan", panValResponseDTO.getPan()); // Store PAN number

                if (null!=panValResponseDTO&&null!=panValResponseDTO.getReturnCode()&&panValResponseDTO.getReturnCode().equals("1") &&
                        null!=panValResponseDTO.getReturnDescription()&&panValResponseDTO.getReturnDescription().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
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
                        panValidationRetriggerResponseDTO.setProcessStatus("Y");
                    } else {
                        panValidationRetriggerResponseDTO.setProcessStatus("M");
                    }
                        panValidationRetriggerResponseDTO.setItrFlag(panValResponseDTO.getSeedingStatus());

                    responseDataDTO2 = rbiBondClosureOnMaturDeathBo.updatePANStatus(panValidationRetriggerResponseDTO);
                    if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                        LOGGER.info(CommonConstants.DB_STATUS + responseDataDTO2.getMessage());
                        successList.add(panValidationRetriggerResponseDTO); // ✅ Store success PANs
                    } else {
                        LOGGER.info("{}: {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                        failureList.add(panValidationRetriggerResponseDTO);
                    }
                    responseDataDTO2.setMessage(CommonConstant.SUCCESS_DESC);
                } else {
                    LOGGER.info(CommonConstants.PAN_VALID_FAILURE + ": " + (null != panValResponseDTO ? panValResponseDTO.toString() : ""));
                    panValidationRetriggerResponseDTO.setSchemeCode(paymentRequestDTO.getSchemeCode());
                    panValidationRetriggerResponseDTO.setPanNumber(panDetailsDTO.getPan());
                    panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
                    panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
                    panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
                    if ("E".equalsIgnoreCase(panValResponseDTO.getPanStatus()) &&
                            "Y".equalsIgnoreCase(panValResponseDTO.getName()) &&
                            "Y".equalsIgnoreCase(panValResponseDTO.getDob())) {
                        panValidationRetriggerResponseDTO.setProcessStatus("Y");
                    }
                    else{panValidationRetriggerResponseDTO.setProcessStatus("M");}
                    ResponseDataDTO res = rbiBondClosureOnMaturDeathBo.updatePANStatus(panValidationRetriggerResponseDTO);
                    if (res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                        LOGGER.info("External DB update Status: {}", res.getMessage());
                        successList.add(panValidationRetriggerResponseDTO); // ✅ Store success PANs
                    } else {
                        LOGGER.info("External DB update Failure: {}", res.getMessage());
                        failureList.add(panValidationRetriggerResponseDTO); // ❌ Store failure PANs
                    }
                    LOGGER.info(CommonConstants.PAN_VALID_FAILURE + ": " + (null != panValResponseDTO ? panValResponseDTO.toString() : ""));
                    responseDataDTO2.setStatusCode("0");
                    responseDataDTO2.setMessage(res.getMessage());
                    responseDataDTO2.setResult(panValResponseDTO);
                }}
            responseDataDTO2.setResult(successList);
            responseDataDTO2.setResult1(failureList);
        }
         } catch (NullPointerException npe) {
             npe.printStackTrace();
             responseDataDTO2.setMessage("A null value was encountered: " + npe.getMessage());
         } catch (Exception e) {
             e.printStackTrace();
             responseDataDTO2.setMessage("An unexpected error occurred: " + e.getMessage());
         }
        return responseDataDTO2;
    }

    @Override
    public ResponseDataDTO delete(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathBo.delete(rbiBondClosureBeforeMaturDTO);
    }
}
