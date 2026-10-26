package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.bo.SubscriptionAccClosingOnMaturityBO;
import com.isl.gbm.rbibond.dao.SubscriptionAccClosingOnMaturityDAO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.util.FileUploaderInServer;
import com.isl.gbm.rbibond.util.Sftp;
import com.isl.gbm.rbibond.util.SftpUploader;
import com.isl.gbm.user.management.util.UserAccountUtils;
import com.jcraft.jsch.ChannelSftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;


@Component
public class SubscriptionAccClosingOnMaturityBOImpl implements SubscriptionAccClosingOnMaturityBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionAccClosingOnMaturityBOImpl.class);

    private static final String PAN_TORCH = "pan";
    private static final String BLA_TORCH = "bla";
    private static final String INVALID_OPTIONS = "Invalid Options";
    private static final String STR_WITH_NINE_SPACES = "         ";
    private static final String STR_WITH_SEVEN_SPACES = "       ";
    private static final String SCHEME = "scheme";
    private final SubscriptionAccClosingOnMaturityDAO subscriptionAccClosingOnMaturityDAO;
    private final TransactionValidator transactionValidator;
    private final Sftp sftp;
    private final FileUploaderInServer fileUploaderInServer;
    private final SftpUploader uploadUtils;


    @Value("${sftp.payment.file.bond.sub-acc.closure.on-maturity.in.path}")
    private String remoteDir;

    @Value("${app.bond.sub-acc.closure.on-maturity.payment.file.location.app.in.folder}")
    private String remoteDirLocal;

    public SubscriptionAccClosingOnMaturityBOImpl(final SubscriptionAccClosingOnMaturityDAO subscriptionAccClosingOnMaturityDAO, final TransactionValidator transactionValidator,
                                                  final Sftp sftp,
                                                  final FileUploaderInServer fileUploaderInServer,final SftpUploader uploadUtils) {
        this.subscriptionAccClosingOnMaturityDAO = subscriptionAccClosingOnMaturityDAO;
        this.transactionValidator = transactionValidator;
        this.sftp = sftp;
        this.fileUploaderInServer =fileUploaderInServer;
        this.uploadUtils=uploadUtils;
    }

    private String headerStringCreationForACHFileOld(BondACHFileHeaderDTO headerDTO) {
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
    //testing

    // Helper method to ensure exact length with padding spaces
    private String padRight(String input, int length) {
        if (input == null || input.isEmpty()) {
            return String.format("%-" + length + "s", "");  // Pad with spaces if input is null/empty
        }
        return String.format("%-" + length + "s", input);  // Pad the input string to the right
    }
    public String headerStringCreationForACHFile(BondACHFileHeaderDTO headerDTO) {
        // Fixed lengths for each field based on ACH file standards
        final int ACH_TRANSACTION_CODE_LENGTH = 2;
        final int CONTROL1_LENGTH = 7;
        final int USERNAME_LENGTH = 40;
        final int CONTROL2_LENGTH = 14;
        final int ACH_FILE_NUMBER_LENGTH = 9;
        final int CONTROL3_LENGTH = 9;
        final int CONTROL4_LENGTH = 15;
        final int LEDGER_FOLIO_NUMBER_LENGTH = 3;
        final int USER_DEF_LIMIT_LENGTH = 13;
        final int TOTAL_AMT_IN_PAISE_LENGTH = 13;
        final int SETTLEMENT_DATE_LENGTH = 8;
        final int RESERVED_LENGTH = 10;
        final int FILLER1_LENGTH = 3;
        final int FILLER2_LENGTH = 57;
        final int USER_REFERENCE_LENGTH = 18;
        final int SPONSOR_BANK_IFSC_MICR_IN_LENGTH = 11;
        final int USER_BANK_ACCOUNT_NUMBER_LENGTH = 35;
        final int TOTAL_ITEMS_LENGTH = 9;
        final int SETTLEMENT_CYCLE_LENGTH = 2;

        // Building the final header string
        StringBuilder headerString = new StringBuilder();

        // Add ACH Transaction Code with proper padding
        headerString.append(padRight(headerDTO.getAchTransactionCode(), ACH_TRANSACTION_CODE_LENGTH));

        // Add Control 1
        headerString.append(padRight(headerDTO.getControl(), CONTROL1_LENGTH));

        // Add User Name
        headerString.append(padRight(headerDTO.getUserName(), USERNAME_LENGTH));

        // Add Control 2
        headerString.append(padRight(headerDTO.getControl1(), CONTROL2_LENGTH));

        // Add ACH File Number
        headerString.append(padRight(headerDTO.getAchFileNumber(), ACH_FILE_NUMBER_LENGTH));

        // Add Control 3
        headerString.append(padRight(headerDTO.getControl2(), CONTROL3_LENGTH));

        // Add Control 4
        headerString.append(padRight(headerDTO.getControl3(), CONTROL4_LENGTH));

        // Add Ledger Folio Number
        headerString.append(padRight(headerDTO.getLedgerFolioNumber(), LEDGER_FOLIO_NUMBER_LENGTH));

        // Add User Defined Limit
        String userDefLimit = headerDTO.getUserDefLimit();
        if (userDefLimit == null) {
            userDefLimit = "0000000000000";  // 13 zeros
        }
        headerString.append(padRight(userDefLimit, USER_DEF_LIMIT_LENGTH));

        // Add Total Amount in Paise
        headerString.append(amountPadding(headerDTO.getTotalAmtInPaise(), TOTAL_AMT_IN_PAISE_LENGTH));

        // Add Settlement Date
        headerString.append(padRight(headerDTO.getSettlementDate(), SETTLEMENT_DATE_LENGTH));

        // Add Reserved fields
        headerString.append(padRight(headerDTO.getReserved1(), RESERVED_LENGTH));
        headerString.append(padRight(headerDTO.getReserved2(), RESERVED_LENGTH));

        // Add Filler 1
        headerString.append(padRight(headerDTO.getFiller1(), FILLER1_LENGTH));

        // Add User Name again (as per the standard)
        headerString.append(padRight(headerDTO.getUserNumber(), USER_REFERENCE_LENGTH));

        // Add User Reference
        headerString.append(padRight(headerDTO.getUserReference(), USER_REFERENCE_LENGTH));

        // Add Sponsor Bank IFSC/MICR Code
        headerString.append(padRight(headerDTO.getSponsorBankIFSCMICRIIN(), SPONSOR_BANK_IFSC_MICR_IN_LENGTH));

        // Add User Bank Account Number
        headerString.append(padRight(headerDTO.getUserBankAccountNumber(), USER_BANK_ACCOUNT_NUMBER_LENGTH));

        // Add Total Items
        headerString.append(countPadding(headerDTO.getTotalItems(), TOTAL_ITEMS_LENGTH));

        // Add Settlement Cycle
        headerString.append(padRight(headerDTO.getSettlementCycle(), SETTLEMENT_CYCLE_LENGTH));

        // Add Filler 2
        headerString.append(padRight(headerDTO.getFiller2(), FILLER2_LENGTH));

        // Returning the final concatenated string
        return headerString.toString();
    }


    private String countPadding(String input, int length) {
        if (input == null || input.isEmpty()) {
            return String.format("%0" + length + "d", 0);  // Pad with zeros if input is null/empty
        }

        input = input.replace(".", "");

        // Pad the string to the right with zeros or left if necessary
        return String.format("%0" + length + "d", Long.parseLong(input));  // Ensure proper length with zeros
    }

    private String amountPadding(String input, int length) {
        if (input == null || input.trim().isEmpty()) {
            return String.format("%0" + length + "d", 0);
        }

        try {
            // Convert input to BigDecimal and scale to 2 decimal places
            BigDecimal amount = new BigDecimal(input).setScale(2, RoundingMode.DOWN);

            // Remove decimal by multiplying with 100
            BigDecimal scaledAmount = amount.multiply(BigDecimal.valueOf(100));

            // Get the long value (no decimal part anymore)
            long numericValue = scaledAmount.longValue();

            // Format with left padding
            return String.format("%0" + length + "d", numericValue);

        } catch (NumberFormatException e) {
            // Handle invalid input gracefully
            throw new IllegalArgumentException("Invalid amount input: " + input, e);
        }
    }



    /**
     * Truncates the input string to the first 40 characters.
     *
     * @param input the original string
     * @return truncated string (max 40 characters), or empty string if null
     */
    private static final int USERNAME_LENGTH = 40;

    private String truncate(String input) {
        if (input == null) {
            return "";
        }
        return input.length() > USERNAME_LENGTH ? input.substring(0, USERNAME_LENGTH) : input;
    }


    //testing
    public String detailsStringCreationForACHFile(BondACHFileDetailsDTO fileDetailsDTO) {
        // Fixed lengths for each field based on ACH file standards (adjust as per your exact specification)
        final int CONTROL_LENGTH = 9;
        final int DEST_ACC_TYPE_LENGTH = 2;
        final int LEDGER_PORTFOLIO_NO_LENGTH = 3;
        final int CONTROL1_LENGTH = 15;
        final int CONTROL2_LENGTH = 9;
        final int CONTROL3_LENGTH = 7;
        final int CONTROL4_LENGTH = 13;
        final int RESERVED1_LENGTH = 10;
        final int RESERVED2_LENGTH = 10;
        final int RESERVED3_LENGTH = 1;
        final int RESERVED4_LENGTH = 2;
        final int FILLER_LENGTH = 7;
        final int ACH_TRANSACTION_CODE_LENGTH = 2;
        final int BENEFICIARY_ACC_HOLDER_NAME_LENGTH = 40;  // Assuming max 20 characters for beneficiary name
        final int USERNAME_LENGTH = 20;
        final int AMT_LENGTH = 13;
        final int DEST_BANK_IFSC_MICR_IN_LENGTH = 11;  // IFSC Code length
        final int BENEFICIARY_BANK_ACC_NO_LENGTH = 35;  // Max bank account number length
        final int SPONSOR_BANK_IFSC_MICR_IN_LENGTH = 11;
        final int USER_NO_LENGTH = 18;  // Assuming max 6 characters for user number
        final int TXN_REF_LENGTH = 30;  // Assuming max 12 characters for transaction reference
        final int PDT_TYPE_LENGTH = 3;  // Assuming 1 character for payment type
        final int BENEFICIARY_AR_NO_LENGTH = 15;  // Assuming max 15 characters for beneficiary AR number
        final int UMRN_LENGTH = 20;  // Assuming 15 characters for UMRN

        // Building the final details string with exact padding for each field
        StringBuilder detailsString = new StringBuilder();

        // Add ACH Transaction Code with proper padding
        detailsString.append(padRight(fileDetailsDTO.getAchTransactionCode(), ACH_TRANSACTION_CODE_LENGTH));

        // Add Control (9 spaces)
        detailsString.append(padRight(fileDetailsDTO.getControl(), CONTROL_LENGTH));

        // Add Destination Account Type (2 spaces)
        detailsString.append(padRight(fileDetailsDTO.getDestinationAccountType(), DEST_ACC_TYPE_LENGTH));

        // Add Ledger Portfolio Number (3 spaces)
        detailsString.append(padRight(fileDetailsDTO.getLedgerFolioNumber(), LEDGER_PORTFOLIO_NO_LENGTH));

        // Add Control1 (15 spaces)
        detailsString.append(padRight(fileDetailsDTO.getControl1(), CONTROL1_LENGTH));

        // Add Beneficiary Account Holder Name (20 characters assumed)
        detailsString.append(padRight(truncate(fileDetailsDTO.getBeneficiaryAccHolderName()), BENEFICIARY_ACC_HOLDER_NAME_LENGTH));

        // Add Control2 (9 spaces)
        detailsString.append(padRight(fileDetailsDTO.getControl2(), CONTROL2_LENGTH));

        // Add Control3 (7 spaces)
        detailsString.append(padRight(fileDetailsDTO.getControl3(), CONTROL3_LENGTH));

        // Add User Name (10 characters assumed)
        detailsString.append(padRight(fileDetailsDTO.getUserName(), USERNAME_LENGTH));

        // Add Control4 (13 spaces)
        detailsString.append(padRight(fileDetailsDTO.getControl4(), CONTROL4_LENGTH));

        // Add Amount (15 characters assumed)
        detailsString.append(amountPadding(fileDetailsDTO.getAmt(), AMT_LENGTH));

        // Add Reserved1 (10 spaces)
        detailsString.append(padRight(fileDetailsDTO.getReserved1(), RESERVED1_LENGTH));

        // Add Reserved2 (10 spaces)
        detailsString.append(padRight(fileDetailsDTO.getReserved2(), RESERVED2_LENGTH));

        // Add Reserved3 (1 space)
        detailsString.append(padRight(fileDetailsDTO.getReserved3(), RESERVED3_LENGTH));

        // Add Reserved4 (2 spaces)
        detailsString.append(padRight(fileDetailsDTO.getReserved4(), RESERVED4_LENGTH));

        // Add Destination Bank IFSC/MICR Code (14 characters)
        detailsString.append(padRight(fileDetailsDTO.getDestinationBankIFSCMICRIIN(), DEST_BANK_IFSC_MICR_IN_LENGTH));

        // Add Beneficiary Bank Account Number (21 characters)
        detailsString.append(padRight(fileDetailsDTO.getBeneficiaryBankAccNo(), BENEFICIARY_BANK_ACC_NO_LENGTH));

        // Add Sponsor Bank IFSC/MICR Code (14 characters)
        detailsString.append(padRight(fileDetailsDTO.getSponsorBankIFSCMICRIIN(), SPONSOR_BANK_IFSC_MICR_IN_LENGTH));

        // Add User Number (6 characters)
        detailsString.append(padRight(fileDetailsDTO.getUserNo(), USER_NO_LENGTH));

        // Add Transaction Reference (12 characters)
        detailsString.append(padRight(fileDetailsDTO.getTxnRef(), TXN_REF_LENGTH));

        // Add Payment Type (1 character)
        detailsString.append(padRight(fileDetailsDTO.getPdtType(), PDT_TYPE_LENGTH));

        // Add Beneficiary AR Number (15 characters)
        detailsString.append(padRight(fileDetailsDTO.getBeneficiaryArNo(), BENEFICIARY_AR_NO_LENGTH));

        // Add UMRN (15 characters)
        detailsString.append(padRight(fileDetailsDTO.getUMRN(), UMRN_LENGTH));

        // Add Filler (7 spaces)
        detailsString.append(padRight(fileDetailsDTO.getFiller(), FILLER_LENGTH));

        // Return the concatenated string
        return detailsString.toString();
    }


    @Override
    public ResponseDataDTO add(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {


        String finacleValidationReqString = "TDS" + "^" + subscriptionAccClosingOnMaturityDTO.getDataValues().getCreditAccNoTds() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + subscriptionAccClosingOnMaturityDTO.getDataValues().getCreditBondTdsAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(subscriptionAccClosingOnMaturityDTO.getDataValues().getCreditBondTdsAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
        finacleValidationReqString += "!" + "SUS" + "^" + subscriptionAccClosingOnMaturityDTO.getDataValues().getDebitAccNoSubs() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + subscriptionAccClosingOnMaturityDTO.getDataValues().getDebitBondSuspenseAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(subscriptionAccClosingOnMaturityDTO.getDataValues().getDebitBondSuspenseAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
        finacleValidationReqString += "!" + "SUN" + "^" + subscriptionAccClosingOnMaturityDTO.getDataValues().getDebitAccNoTotalInterestBeforeTds() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + subscriptionAccClosingOnMaturityDTO.getDataValues().getDebitBondSundryAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(subscriptionAccClosingOnMaturityDTO.getDataValues().getDebitBondSundryAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));


        ValidatorWrapperDTO validatorWrapperDTO = transactionValidator.validateBacidForAcidDetails(finacleValidationReqString);
        if (CommonConstants.SUCCESS.equalsIgnoreCase(validatorWrapperDTO.getStatus())) {
            List<TranDetailsDTO> transactionList = validatorWrapperDTO.getTranDetailsDTOList();
            for (TranDetailsDTO dto : transactionList) {
                if (CommonConstants.SUCCESS.equalsIgnoreCase(dto.getValidityStatus())) {
                    String[] accNo = dto.getAccountNumber().split("\\^");
                    if ("TDS".equalsIgnoreCase(accNo[0])) {
                        subscriptionAccClosingOnMaturityDTO.getDataValues().setCreditAccNoTds(accNo[1]);
                        subscriptionAccClosingOnMaturityDTO.getDataValues().setCreditBondTdsAccFlag(dto.getIsSysAcct());
                    } else if ("SUS".equalsIgnoreCase(accNo[0])) {
                        subscriptionAccClosingOnMaturityDTO.getDataValues().setDebitAccNoSubs(accNo[1]);
                        subscriptionAccClosingOnMaturityDTO.getDataValues().setDebitBondSuspenseAccFlag(dto.getIsSysAcct());
                    } else if ("SUN".equalsIgnoreCase(accNo[0])) {
                        subscriptionAccClosingOnMaturityDTO.getDataValues().setDebitAccNoTotalInterestBeforeTds(accNo[1]);
                        subscriptionAccClosingOnMaturityDTO.getDataValues().setDebitBondSundryAccFlag(dto.getIsSysAcct());
                    } else {
                        throw new GenericBusinessException("INVALID ACCOUNT NAME");
                    }
                } else {
                    throw new GenericBusinessException(dto.getReason());
                }
            }
        } else {
            throw new GenericBusinessException(validatorWrapperDTO.getReason());
        }

        return subscriptionAccClosingOnMaturityDAO.add(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    public ResponseDataDTO addValidation(SubscriptionAccClosingOnMaturityDTO maturityDTO) {

        if (maturityDTO != null && SCHEME.equalsIgnoreCase(maturityDTO.getPageType()))
            return subscriptionAccClosingOnMaturityDAO.addValidation(maturityDTO);
        else if (maturityDTO != null && PAN_TORCH.equalsIgnoreCase(maturityDTO.getPageType()))
            return subscriptionAccClosingOnMaturityDAO.addPANTorch(maturityDTO);
        else if (maturityDTO != null && BLA_TORCH.equalsIgnoreCase(maturityDTO.getPageType()))
            return subscriptionAccClosingOnMaturityDAO.addBLATorch(maturityDTO);
        else if (maturityDTO != null && "ADD".equalsIgnoreCase(maturityDTO.getPageType()))
            return subscriptionAccClosingOnMaturityDAO.addSubmit(maturityDTO);
        else
            throw new IllegalArgumentException(INVALID_OPTIONS);
    }

    @Override
    public ResponseDataDTO inquiry(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        switch (subscriptionAccClosingOnMaturityDTO.getPageType().toLowerCase()) {
            case SCHEME:
                return subscriptionAccClosingOnMaturityDAO.inquiry(subscriptionAccClosingOnMaturityDTO);
            case PAN_TORCH:
                return subscriptionAccClosingOnMaturityDAO.inquiryPANTorch(subscriptionAccClosingOnMaturityDTO);
            case BLA_TORCH:
                return subscriptionAccClosingOnMaturityDAO.inquiryBLATorch(subscriptionAccClosingOnMaturityDTO);
            default:
                throw new IllegalArgumentException(INVALID_OPTIONS);
        }
    }

    @Override
    public ResponseDataDTO verifySelect(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        switch (subscriptionAccClosingOnMaturityDTO.getPageType().toLowerCase()) {
            case SCHEME:
                return subscriptionAccClosingOnMaturityDAO.verifySelect(subscriptionAccClosingOnMaturityDTO);
            case PAN_TORCH:
                return subscriptionAccClosingOnMaturityDAO.verifyPANTorch(subscriptionAccClosingOnMaturityDTO);
            case BLA_TORCH:
                return subscriptionAccClosingOnMaturityDAO.verifyBLATorch(subscriptionAccClosingOnMaturityDTO);
            case "verify":
                ResponseDataDTO responseDataDTO = subscriptionAccClosingOnMaturityDAO.verify(subscriptionAccClosingOnMaturityDTO);
                if (responseDataDTO != null && CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())) {
                    BondACHFileWrapperDTO fileWrapperDTO = (BondACHFileWrapperDTO) responseDataDTO.getResult();
                    BondACHFileHeaderDTO headerDTO = fileWrapperDTO.getAchFileHeaderDTO();
                    List<BondACHFileDetailsDTO> detailsList = fileWrapperDTO.getAchFileDetailsList();
                    String fileName = fileWrapperDTO.getFileName();
                    List<String> listOfData = new LinkedList<>();
                    listOfData.add(headerStringCreationForACHFile(headerDTO));
                    Consumer<BondACHFileDetailsDTO> consumeFn = element ->
                            listOfData.add(detailsStringCreationForACHFile(element));
                    detailsList.forEach(consumeFn);
                    fileUploaderInServer.uploadInServer(remoteDirLocal,fileName, listOfData);
                    LOGGER.info(" File written successfully!!!! Directory {}", remoteDirLocal);
                    LOGGER.info(" FileName: {}", fileName);
                    uploadUtils.uploadFileToSFTP(remoteDir, fileName, listOfData);
                    LOGGER.info(" File written successfully!!!! Directory {}", remoteDir);
                    return responseDataDTO;
                }
                return responseDataDTO;
            case "delete":
                return subscriptionAccClosingOnMaturityDAO.delete(subscriptionAccClosingOnMaturityDTO);
            default:
                throw new IllegalArgumentException(INVALID_OPTIONS);
        }

    }

    public void uploadFileToSFTP(String remoteDir, String fileName, List<String> data) {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = this.sftp.createSftp();

            // Check if the remote directory exists, and create it if it doesn't
            if (!isDirectoryExists(channelSftp, remoteDir)) {
                LOGGER.warn("Remote directory {} does not exist. Creating it.", remoteDir);
                channelSftp.mkdir(remoteDir);
            }

            // Check if we have write permission on the remote directory
            if (!hasWritePermission(channelSftp, remoteDir)) {
                LOGGER.error("No write permission to the remote directory {}", remoteDir);
                return;
            }

            // Write data to a temporary file
            File tempFile = new File(fileName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                for (String line : data) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            // Upload file to remote SFTP directory
            String remoteFilePath = remoteDir + "/" + fileName + ".txt";
            channelSftp.rename(tempFile.getAbsolutePath(), remoteFilePath);
            LOGGER.info("File uploaded successfully to {}", remoteFilePath);

            // Delete the temporary file
            if (tempFile.delete()) {
                LOGGER.info("Temp file {} deleted successfully.", tempFile.getAbsolutePath());
            } else {
                LOGGER.warn("Failed to delete temp file {}", tempFile.getAbsolutePath());
            }

        } catch (Exception e) {
            LOGGER.error("Error during file upload to SFTP", e);
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
                LOGGER.info("Disconnected from SFTP server.");
            }
        }
    }

    private boolean isDirectoryExists(ChannelSftp channelSftp, String remoteDir) {
        try {
            channelSftp.lstat(remoteDir);  // Will throw an exception if the directory does not exist
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean hasWritePermission(ChannelSftp channelSftp, String remoteDir) {
        File tempFile = null;
        String dummyFileName = "tempWriteCheck.txt";

        try {
            // Step 1: Create a small temporary local file
            tempFile = File.createTempFile("tempWriteCheck", ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                writer.write("write-check");
            }
            // Step 2: Try to upload it to the remote directory
            String remotePath = remoteDir + "/" + dummyFileName;
            channelSftp.put(tempFile.getAbsolutePath(), remotePath);
            LOGGER.info("Put methods Working or Not Checking: ");
            // Step 3: Try to delete it from the remote directory
            channelSftp.rm(remotePath);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            // Step 4: Delete the local temp file
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }

    @Override
    public ResponseDataDTO verify(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        ResponseDataDTO responseDataDTO = subscriptionAccClosingOnMaturityDAO.verify(subscriptionAccClosingOnMaturityDTO);
/*
        if (responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
            SubscriptionAccClosingOnMaturityDTO dbResponse = (SubscriptionAccClosingOnMaturityDTO) responseDataDTO.getResult();
            CbsTransactionResponseDetails cbsResponse = transactionManger.postTransactionToCbs(dbResponse.getGbmTransactionMasterDtoList(), dbResponse.getChannelType());

            LOGGER.info("Cbs Response In Service Layer : {}", cbsResponse.toString());
            if (StringUtils.equalsIgnoreCase(cbsResponse.getFinacleResponseStatus(), "SUCCESS")) {
                dbResponse.setCbsTranId(cbsResponse.getCbsTransactionId());
                dbResponse.setCbsTranStatus(cbsResponse.getTranStatus().toString());
                dbResponse.setCbsTranStatusDescription("Successful Transaction");
            } else if (StringUtils.equalsIgnoreCase(cbsResponse.getFinacleResponseStatus(), "FAILURE") || StringUtils.equalsIgnoreCase(cbsResponse.getFinacleResponseStatus(), "FAILED")) {
                dbResponse.setCbsTranStatus(CommonConstants.NO.toString());
                if (cbsResponse.getTranStatus() != null)
                    dbResponse.setCbsTranStatus(cbsResponse.getTranStatus().toString());
                else
                    dbResponse.setCbsTranStatus(CommonConstants.NO_STATUS);
                if (cbsResponse.getErrorDescription().contains("INSUFFICIENT AVAIL BAL")) {
                    dbResponse.setCbsTranStatusDescription("Insufficient Balance");
                } else {
                    dbResponse.setCbsTranStatusDescription(cbsResponse.getErrorDescription());
                }
                responseDataDTO.setMessage(cbsResponse.getErrorDescription());
            } else {
                dbResponse.setCbsTranStatus(CommonConstants.NO_STATUS);
                dbResponse.setCbsTranStatusDescription("CBS Connection Failed");
                responseDataDTO.setMessage("CBS Connection Failed");
            }
            LOGGER.info("Cbs Response In Service Layer to update In DB: {}", dbResponse.getCbsTranStatusDescription());
            return subscriptionAccClosingOnMaturityDAO.verifyResponse(dbResponse);
        } else {*/
            return responseDataDTO;

    }

    @Override
    public ResponseDataDTO delete(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return subscriptionAccClosingOnMaturityDAO.delete(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return subscriptionAccClosingOnMaturityDAO.downloadFileFromSFTP(paymentRequestDTO);
    }
    @Override
    public ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        FileResponseDto fileResponseDto = new FileResponseDto();
        try{
         responseDataDTO=subscriptionAccClosingOnMaturityDAO.downloadFileManual(paymentRequestDTO);
        if(responseDataDTO != null && CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())) {
            BondACHFileWrapperDTO fileWrapperDTO = (BondACHFileWrapperDTO) responseDataDTO.getResult();
            BondACHFileHeaderDTO headerDTO = fileWrapperDTO.getAchFileHeaderDTO();
            List<BondACHFileDetailsDTO> detailsList = fileWrapperDTO.getAchFileDetailsList();
            String fileName = fileWrapperDTO.getFileName();
            List<String> listOfData = new LinkedList<>();
            listOfData.add(headerStringCreationForACHFile(headerDTO));
            Consumer<BondACHFileDetailsDTO> consumeFn = element ->
                    listOfData.add(detailsStringCreationForACHFile(element));
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
    public ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO, String schemeCode, String fromDate, String toDate) {
        return subscriptionAccClosingOnMaturityDAO.uploadFile(bondInterestPaymentFileDTO, schemeCode, fromDate, toDate);
    }

    //fields validations
    public boolean validateACHFile(BondACHFileHeaderDTO headerDTO) {
        // Validate ACH Transaction Code (Field 1)
        if (headerDTO.getAchTransactionCode() == null || !headerDTO.getAchTransactionCode().matches("12|33|56")) {
            LOGGER.error("Validation failed: ACH Transaction Code must be one of 12, 33, or 56.");
            return false;
        }
        // Validate User Name (Field 3)
        if (headerDTO.getUserName() == null || headerDTO.getUserName().length() != 40 || !headerDTO.getUserName().matches("[A-Za-z0-9 ]+")) {
            LOGGER.error("Validation failed: User Name must be 40 alphanumeric characters.");
            return false;
        }

        // Validate Total Amount in Paise (Field 10)
        if (headerDTO.getTotalAmtInPaise() == null || !headerDTO.getTotalAmtInPaise().matches("\\d{13}")) {
            LOGGER.error("Validation failed: Total Amount in Paise must be a 13-digit numeric value.");
            return false;
        }

        // Validate Settlement Date (Field 11)
        String settlementDate = headerDTO.getSettlementDate();
        if (settlementDate == null || !settlementDate.matches("\\d{8}")) {
            LOGGER.error("Validation failed: Settlement Date must be in DDMMYYYY format.");
            return false;
        }

         // Validate User Number (Field 15)
        if (headerDTO.getUserName() == null || headerDTO.getUserName().length() != 18 || !headerDTO.getUserName().matches("[A-Za-z0-9]+")) {
            LOGGER.error("Validation failed: User Number must be 18 alphanumeric characters.");
            return false;
        }

        // Validate User Reference (Field 16)
        if (headerDTO.getUserReference() == null || headerDTO.getUserReference().length() != 18 || !headerDTO.getUserReference().matches("[A-Za-z0-9]+")) {
            LOGGER.error("Validation failed: User Reference must be 18 alphanumeric characters.");
            return false;
        }

        // Validate Sponsor Bank IFSC/MICR/IIN (Field 17)
        if (headerDTO.getSponsorBankIFSCMICRIIN() == null || headerDTO.getSponsorBankIFSCMICRIIN().length() != 11 || !headerDTO.getSponsorBankIFSCMICRIIN().matches("[A-Za-z0-9]+")) {
            LOGGER.error("Validation failed: Sponsor Bank IFSC/MICR/IIN must be 11 alphanumeric characters.");
            return false;
        }

        // Validate Total Items (Field 19)
        if (headerDTO.getTotalItems() == null || !headerDTO.getTotalItems().matches("\\d{9}")) {
            LOGGER.error("Validation failed: Total Items must be a 9-digit numeric value.");
            return false;
        }
        LOGGER.info("Validation passed for all fields.");
        return true;
    }
}
