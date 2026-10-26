package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.SubscriptionAccClosingOnMaturityBO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.exceptions.FileDownloadException;
import com.isl.gbm.rbibond.service.SubscriptionAccClosingOnMaturityService;
import com.isl.gbm.rbibond.util.FileUploaderInServer;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
import com.isl.gbm.rbibond.util.Sftp;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;


@Service
public class SubscriptionAccClosingOnMaturityServiceImpl implements SubscriptionAccClosingOnMaturityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionAccClosingOnMaturityServiceImpl.class);

    private final Sftp sftp;
    private final SubscriptionAccClosingOnMaturityBO subscriptionAccClosingOnMaturityBO;
    private final FileUploaderInServer fileUploaderInServer;
    @Value("${sftp.payment.file.bond.sub-acc.closure.on-maturity.in.path}")
    private String remoteDir;

    @Value("${app.bond.sub-acc.closure.on-maturity.payment.file.location.app.in.folder}")
    private String remoteDirLocal;

    public SubscriptionAccClosingOnMaturityServiceImpl(final Sftp sftp, final SubscriptionAccClosingOnMaturityBO subscriptionAccClosingOnMaturityBO, final FileUploaderInServer fileUploaderInServer) {
        this.sftp = sftp;
        this.subscriptionAccClosingOnMaturityBO = subscriptionAccClosingOnMaturityBO;
        this.fileUploaderInServer=fileUploaderInServer;
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
    public ResponseDataDTO add(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return subscriptionAccClosingOnMaturityBO.add(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    public ResponseDataDTO addValidation(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != subscriptionAccClosingOnMaturityDTO && null != subscriptionAccClosingOnMaturityDTO.getPanNoName() && !subscriptionAccClosingOnMaturityDTO.getPanNoName().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(subscriptionAccClosingOnMaturityDTO.getPanNoName());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return subscriptionAccClosingOnMaturityBO.addValidation(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    public ResponseDataDTO inquiry(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != subscriptionAccClosingOnMaturityDTO && null != subscriptionAccClosingOnMaturityDTO.getPanNoName() && !subscriptionAccClosingOnMaturityDTO.getPanNoName().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(subscriptionAccClosingOnMaturityDTO.getPanNoName());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return subscriptionAccClosingOnMaturityBO.inquiry(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != subscriptionAccClosingOnMaturityDTO && null != subscriptionAccClosingOnMaturityDTO.getPanNoName() && !subscriptionAccClosingOnMaturityDTO.getPanNoName().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(subscriptionAccClosingOnMaturityDTO.getPanNoName());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return subscriptionAccClosingOnMaturityBO.verifySelect(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    public ResponseDataDTO verify(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return subscriptionAccClosingOnMaturityBO.verify(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    public ResponseDataDTO delete(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return subscriptionAccClosingOnMaturityBO.delete(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            ResponseDataDTO boResponse = this.subscriptionAccClosingOnMaturityBO.downloadFileFromSFTP(paymentRequestDTO);
            if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(boResponse.getMessage())
                    && boResponse.getResult() instanceof String) {
                FileResponseDto fileResponseDto = new FileResponseDto();
                String fileName = (String) boResponse.getResult();
                FileDownloadWrapperDTO fileDownloadWrapperDTO = downloadFile(remoteDir, fileName);
                LOGGER.info("FileName: {}", fileName);
                if (fileDownloadWrapperDTO != null && fileDownloadWrapperDTO.getFileContent() != null) {
                    fileResponseDto.setFileName(fileName + ".txt");
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(fileDownloadWrapperDTO.getFileContent().toByteArray()));
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                } else if (fileDownloadWrapperDTO != null && fileDownloadWrapperDTO.getMessage() != null && fileDownloadWrapperDTO.getMessage().equalsIgnoreCase("No such file")) {
                    ResponseDataDTO bOResponse = this.subscriptionAccClosingOnMaturityBO.downloadFileManual(paymentRequestDTO);
                    responseDataDTO.setMessage(bOResponse.getMessage());
                    responseDataDTO.setResult(bOResponse.getResult());
                } else {
                    ResponseDataDTO bOResponse = this.subscriptionAccClosingOnMaturityBO.downloadFileManual(paymentRequestDTO);
                    responseDataDTO.setMessage(bOResponse.getMessage());
                    responseDataDTO.setResult(bOResponse.getResult());
                }
            } else {
                responseDataDTO.setMessage(boResponse.getMessage());
            }
        }catch(Exception ex){
            LOGGER.info("Unexpected error occurred: "+ex.getMessage());
            ResponseDataDTO bOResponse = this.subscriptionAccClosingOnMaturityBO.downloadFileManual(paymentRequestDTO);
            responseDataDTO.setMessage(bOResponse.getMessage());
            responseDataDTO.setResult(bOResponse.getResult());
            return responseDataDTO;
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO uploadFile(MultipartFile textFile, String schemeCode, String fromDate, String toDate) {
        ResponseDataDTO responseDataDTO =new ResponseDataDTO();
        if (textFile.getOriginalFilename() == null || !textFile.getOriginalFilename().toLowerCase().endsWith(".txt")) {
            responseDataDTO.setMessage("Invalid file type. Only .txt files are allowed");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        responseDataDTO = parseUploadedTextFile(textFile);
        if (!ObjectUtils.isEmpty(responseDataDTO) && CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())
                && responseDataDTO.getResult() instanceof BondInterestPaymentFileDTO) {
            return this.subscriptionAccClosingOnMaturityBO.uploadFile((BondInterestPaymentFileDTO) responseDataDTO.getResult(), schemeCode, fromDate, toDate);
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
}
