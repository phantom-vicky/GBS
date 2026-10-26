package com.isl.gbm.rbibond.jobs;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dao.BondSubAccClosureBeforeMaturityDAO;
import com.isl.gbm.rbibond.dto.BondInterestPaymentFileDTO;
import com.isl.gbm.rbibond.dto.RBIBondPaymentFileDetails;
import com.isl.gbm.rbibond.util.Sftp;
import com.isl.gbm.user.isac.common.CommonConstant;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component
public class BondSubAccClosureBeforeMaturityPaymentFileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(BondSubAccClosureBeforeMaturityPaymentFileReader.class);

    private final Sftp sftp;
    private final BondSubAccClosureBeforeMaturityDAO paymentDAO;

    @Value("${sftp.payment.file.bond.sub-acc.closure.before-maturity.in.path}")
    private String beforeMaturitySFTPInFolderLoc;

    @Value("${sftp.payment.bond.sub-acc.closure.before-maturity.out.path}")
    private String beforeMaturitySFTPOutFolderLoc;

    @Value("${sftp.payment.file.bond.sub-acc.closure.before-maturity.nck.path}")
    private String beforeMaturitySFTPNCKFolderLoc;

    @Value("${sftp.payment.file.bond.sub-acc.closure.before-maturity.done.path}")
    private String beforeMaturitySFTPDoneFolderLoc;

    @Value("${app.bond.sub-acc.closure.before-maturity.payment.file.location.app.in.folder}")
    private String ourAppInFolderLoc;

    @Value("${app.bond.sub-acc.closure.before-maturity.payment.file.location.app.done.folder}")
    private String ourAppDoneFolderLoc;

    public BondSubAccClosureBeforeMaturityPaymentFileReader(final Sftp sftp, final BondSubAccClosureBeforeMaturityDAO paymentDAO) {
        this.sftp = sftp;
        this.paymentDAO = paymentDAO;
    }

//    @Scheduled(fixedDelay = 120000L)
    public void readPaymentFileFromSFTPForBeforeMaturity() {
        ResponseDataDTO preCondition = this.paymentDAO.processPaymentFile(new BondInterestPaymentFileDTO());
        if (!ObjectUtils.isEmpty(preCondition) && CommonConstants.SUCCESS_CONST.equalsIgnoreCase(preCondition.getMessage())) {
            RBIBondPaymentFileDetails fileDetails = fileSftpDownload();
            if (!ObjectUtils.isEmpty(fileDetails) && CommonConstant.SUCCESS_DESC.equalsIgnoreCase(fileDetails.getMessage())) {
                File directory = new File(fileDetails.getGbmDestPath());
                if (directory.exists() && directory.isDirectory()) {
                    File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
                    if (files != null) {
                        for (File file : files) {
                            fileDetails.getFileNameList().forEach(
                                    element -> {
                                        if (element.equalsIgnoreCase(file.getName())) {
                                            ChannelSftp channelSftp = null;
                                            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                                                ResponseDataDTO responseDataDTO = PaymentFileUtility.parseTextFile(fileInputStream);
                                                if (!ObjectUtils.isEmpty(responseDataDTO) && CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())) {
                                                    ResponseDataDTO finalResponseDTO = this.paymentDAO.processPaymentFile((BondInterestPaymentFileDTO) responseDataDTO.getResult());
                                                    if (!ObjectUtils.isEmpty(finalResponseDTO) && CommonConstants.SUCCESS_CONST.equalsIgnoreCase(finalResponseDTO.getMessage()))
                                                        Files.move(Paths.get(ourAppInFolderLoc + file.getName()), Paths.get(ourAppDoneFolderLoc + file.getName()), StandardCopyOption.REPLACE_EXISTING);
                                                    else
                                                        LOGGER.info("Final Response From DB {}", finalResponseDTO);
                                                } else if ("Length of the Rows are less than 306 Character".equalsIgnoreCase(responseDataDTO.getMessage())) {
                                                    channelSftp = this.sftp.createSftp();
                                                    String sourceDir = beforeMaturitySFTPDoneFolderLoc.replace("\\", "/");
                                                    String destDir = beforeMaturitySFTPNCKFolderLoc.replace("\\", "/");
                                                    LOGGER.info("Change path to Done Directory {}", sourceDir);
                                                    channelSftp.cd(sourceDir);
                                                    LOGGER.info(" File Name from SFTP Location {} ", file.getName());
                                                    String sourceDone = sourceDir + file.getName();
                                                    String destNCK = destDir + file.getName();
                                                    LOGGER.info(" Move the file from SFTP Done Folder : {} To NCK SFTP folder Path: {}", sourceDone, destNCK);
                                                    channelSftp.rename(sourceDone, destNCK);
                                                } else
                                                    LOGGER.info("File not present in the specified directory {}", responseDataDTO);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            } finally {
                                                this.sftp.disconnect(channelSftp);
                                            }
                                        }
                                    }
                            );
                        }
                    }
                }
            } else
                LOGGER.info(" File Details From the SFTP Folder {}", fileDetails);
        } else
            LOGGER.info("Check any Records are there in Payment Status 'I' {} ", preCondition);
    }

    private RBIBondPaymentFileDetails fileSftpDownload() {
        RBIBondPaymentFileDetails fileDetails = new RBIBondPaymentFileDetails();
        ChannelSftp channelSftp = null;
        try {
            channelSftp = this.sftp.createSftp();
            String sourceDir = beforeMaturitySFTPOutFolderLoc.replace("\\", "/");
            String destDir = ourAppInFolderLoc.replace("\\", "/");
            String sftpDoneLoc = beforeMaturitySFTPDoneFolderLoc.replace("\\", "/");
            fileDetails.setGbmDestPath(ourAppInFolderLoc);
            List<String> fileNameList = new ArrayList<>();
            LOGGER.info("Change path to {}", sourceDir);
            channelSftp.cd(sourceDir);
            Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.txt");
            if (!list.isEmpty()) {
                for (ChannelSftp.LsEntry entry : list) {
                    String filename = entry.getFilename();
                    fileNameList.add(filename);
                    String remoteSourcePath = sourceDir + filename;
                    String remoteDonePath = destDir + filename;
                    String sftpDoneLocWithSFTP = sftpDoneLoc + filename;
                    channelSftp.get(filename, remoteDonePath);
                    LOGGER.info(" File Name from SFTP Location for Sub Acc Closure Before Maturity {} ", filename);
                    LOGGER.info(" For Sub Acc Closure Before Maturity Move the file from SFTP OUT Folder : {} and To Our APP folder Path: {}", remoteSourcePath, remoteDonePath);
                    channelSftp.rename(remoteSourcePath, sftpDoneLocWithSFTP);
                    LOGGER.info(" For Sub Acc Closure Before Maturity Move the file from SFTP OUT Folder : {} and To Our APP folder Path: {}", remoteSourcePath, sftpDoneLocWithSFTP);
                }
                fileDetails.setMessage(CommonConstants.SUCCESS_CONST);
                fileDetails.setFileNameList(fileNameList);
                return fileDetails;
            } else {
                LOGGER.info("There is no Payment Response File exist in the specified Location For Sub Acc Closure Before Maturity {}", sourceDir);
                fileDetails.setMessage("No Payment File Found");
            }
        } catch (SftpException e) {
            LOGGER.error("SFTP error while processing files: {}", e.getMessage(), e);
            fileDetails.setMessage(e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IO error while downloading file: {}", e.getMessage(), e);
            fileDetails.setMessage(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred: {}", e.getMessage(), e);
            fileDetails.setMessage(e.getMessage());
        } finally {
            this.sftp.disconnect(channelSftp);
        }
        return fileDetails;
    }

}
