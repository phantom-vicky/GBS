package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.CafuFileDownloadDao;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.util.GenerateSequenceCAFU;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CafuFileDownloadDaoImpl implements CafuFileDownloadDao {

    private static final Logger log = LoggerFactory.getLogger(CafuFileDownloadDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO getCafuFile(CafuFileDownloadDTO cafuFileDownloadDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<CafuFileDownloadDTO> cafulist = null;
        CafuFileDownloadDTO cafuFileDownloadDTO1=null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        CafuFileFooterRecordDTO cafuFileFooterRecordDTO=null;
        List<CafuFileFooterRecordDTO> cafuFileFooterRecordDTOList=null;
        CafuFileHeaderRecordDTO cafuFileHeaderRecordDTO=null;
        List<CafuFileHeaderRecordDTO> cafuFileHeaderRecordDTOList=null;
        CafuFileDepositRecordDTO cafuFileDepositRecordDTO=null;
        List<CafuFileDepositRecordDTO> cafuFileDepositRecordDTOList=null;
        CafuFileChequeRecordDTO cafuFileChequeRecordDTO=null;
        List<CafuFileChequeRecordDTO> cafuFileChequeRecordDTOList=null;
        CafuFileDownloadResponseDTO cafuFileDownloadResponseDTO=new CafuFileDownloadResponseDTO();

        long totalChkSum = 0;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_gbm_rbi_bond_cafu_file_dn.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cafuFileDownloadDTO.getSubCode());
                stmt.setString(index++, cafuFileDownloadDTO.getFileType());
                stmt.setString(index++, cafuFileDownloadDTO.getFileMode());
                stmt.setString(index++, cafuFileDownloadDTO.getLiquidationDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(7);
                    if (cafuFileDownloadDTO.getSubCode() == null) {
                        if (resultSet != null) {
                            cafulist = new ArrayList<>();
                            while (resultSet.next()) {
                                cafuFileDownloadDTO1 = new CafuFileDownloadDTO();
                                cafuFileDownloadDTO1.setSubCode(resultSet.getString("sub_code"));
                                cafuFileDownloadDTO1.setSubCodeDesc(resultSet.getString("sub_code_desc"));
                                cafuFileDownloadDTO1.setSchemeStatus(resultSet.getString("scheme_status"));
                                cafulist.add(cafuFileDownloadDTO1);
                            }
                            responseDataDTO.setResult(cafulist);
                        }
                    }
                    else if(cafuFileDownloadDTO.getSubCode() != null && !cafuFileDownloadDTO.getSubCode().isEmpty()) {
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(7);
                        if (resultSet1 != null) {
                            cafuFileHeaderRecordDTOList = new ArrayList<>();
                            while (resultSet1.next()) {
                                cafuFileHeaderRecordDTO=new CafuFileHeaderRecordDTO();
                                cafuFileHeaderRecordDTO.setRecordIdentifier(resultSet1.getString(1));
                                /*
                                file name logic implemented
                                 */
                                String countSequence =GenerateSequenceCAFU.generateSequence(getValueAfterDecimalAsInt(resultSet1.getString(2)));
                                String fileName =replaceValueAfterDot(resultSet1.getString(2),countSequence);
                                cafuFileHeaderRecordDTO.setFileName(fileName);
                                cafuFileHeaderRecordDTO.setOriginatingBranch(resultSet1.getString(3));
                                cafuFileHeaderRecordDTO.setActivationDate(resultSet1.getString(4));
                                cafuFileHeaderRecordDTO.setTheirReferenceNmbr(resultSet1.getString(5));
                                cafuFileHeaderRecordDTO.setSourceOfUpload(resultSet1.getString(6));
                                cafuFileHeaderRecordDTO.setSourceCode(resultSet1.getString(7));
                                cafuFileHeaderRecordDTO.setDispatchBankArrangement(resultSet1.getString(8));
                                cafuFileHeaderRecordDTO.setPdcFlag(resultSet1.getString(9));
                                cafuFileHeaderRecordDTOList.add(cafuFileHeaderRecordDTO);
                            }
                            cafuFileDownloadResponseDTO.setHeaderList(cafuFileHeaderRecordDTOList);
                        }
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(8);
                        if (resultSet2 != null) {
                            cafuFileDepositRecordDTOList = new ArrayList<>();
                            while (resultSet2.next()) {
                                cafuFileDepositRecordDTO=new CafuFileDepositRecordDTO();
                                cafuFileDepositRecordDTO.setRecordIdentifier(resultSet2.getString(1));
                                cafuFileDepositRecordDTO.setClientCode(resultSet2.getString(2));
                                cafuFileDepositRecordDTO.setPickUpPoint(resultSet2.getString(3));
                                cafuFileDepositRecordDTO.setDepositRemarks(resultSet2.getString(4));
                                cafuFileDepositRecordDTO.setDepositSlipNo(resultSet2.getString(5));
                                cafuFileDepositRecordDTO.setDepositDate(resultSet2.getString(6));
                                cafuFileDepositRecordDTO.setTotalNoOfInstruments(formatNumber5(resultSet2.getString(7)));
                                cafuFileDepositRecordDTO.setTotalAmount(formatNumber15(resultSet2.getString(8)));
                                cafuFileDepositRecordDTO.setClientArrg(resultSet2.getString(9));
                                cafuFileDepositRecordDTOList.add(cafuFileDepositRecordDTO);
                            }
                            cafuFileDownloadResponseDTO.setDepositList(cafuFileDepositRecordDTOList);
                        }
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(9);
                        if (resultSet3 != null) {
                            cafuFileChequeRecordDTOList = new ArrayList<>();
                            while (resultSet3.next()) {
                                cafuFileChequeRecordDTO=new CafuFileChequeRecordDTO();
                                cafuFileChequeRecordDTO.setRecordIdentifier(resultSet3.getString(1));
                                cafuFileChequeRecordDTO.setInstrumentNumber(resultSet3.getString(2));
                                cafuFileChequeRecordDTO.setDraweeBankCode(resultSet3.getString(3));
                                cafuFileChequeRecordDTO.setRoutingCode(resultSet3.getString(4));
                                cafuFileChequeRecordDTO.setInstrumentAmount(formatNumber12(resultSet3.getString(5)));
                                cafuFileChequeRecordDTO.setInstrumentDate(resultSet3.getString(6));
                                cafuFileChequeRecordDTO.setDrawerName(resultSet3.getString(7));
                                cafuFileChequeRecordDTO.setEmail1stApplicant(resultSet3.getString(8));
                                cafuFileChequeRecordDTO.setMobileNo(resultSet3.getString(9));
                                cafuFileChequeRecordDTO.setPanNo(resultSet3.getString(10));
                                cafuFileChequeRecordDTO.setTypeOfApplicant(resultSet3.getString(11));
                                cafuFileChequeRecordDTO.setCheckListNumber(resultSet3.getString(12));
                                cafuFileChequeRecordDTO.setExistingBlaNumber(resultSet3.getString(13));
                                cafuFileChequeRecordDTO.setCohCoi(resultSet3.getString(14));
                                cafuFileChequeRecordDTO.setBankNameInterestPayment(resultSet3.getString(15));
                                cafuFileChequeRecordDTO.setAccountNoInterestPayment(resultSet3.getString(16));
                                cafuFileChequeRecordDTO.setIfscCodeInterestPayment(resultSet3.getString(17));
                                cafuFileChequeRecordDTO.setOperatingInstructions(resultSet3.getString(18));
                                cafuFileChequeRecordDTO.setSchCode(resultSet3.getString(19));
                                cafuFileChequeRecordDTO.setClearingLocation(resultSet3.getString(20));
                                cafuFileChequeRecordDTO.setEnrichment(resultSet3.getString(21));
                                long chkSum=convertValues(resultSet3.getString(5),resultSet3.getString(6));
                                totalChkSum+=chkSum;
                                cafuFileChequeRecordDTO.setChksum(generateChecksum(chkSum));
                                cafuFileChequeRecordDTOList.add(cafuFileChequeRecordDTO);
                            }
                            cafuFileDownloadResponseDTO.setChequeList(cafuFileChequeRecordDTOList);
                        }
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(10);
                        if (resultSet4 != null) {
                            cafuFileFooterRecordDTOList = new ArrayList<>();
                            while (resultSet4.next()) {
                                cafuFileFooterRecordDTO=new CafuFileFooterRecordDTO();
                                cafuFileFooterRecordDTO.setRecordIdentifier(resultSet4.getString(1));
                                cafuFileFooterRecordDTO.setTotalNumberOfdeposits(formatNumber5(resultSet4.getString(2)));
                                cafuFileFooterRecordDTO.setTotalAmount(formatNumber15(resultSet4.getString(3)));
                                cafuFileFooterRecordDTO.setTotalChkum(formatNumber20(String.valueOf(totalChkSum)));
                                cafuFileFooterRecordDTOList.add(cafuFileFooterRecordDTO);
                            }
                            cafuFileDownloadResponseDTO.setFooterList(cafuFileFooterRecordDTOList);
                        }
                        responseDataDTO.setResult(cafuFileDownloadResponseDTO);
                    }
                }else{
                    responseDataDTO.setStatusCode("0");
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public static Integer getValueAfterDecimalAsInt(String input) {
        // Check if the input is null or empty
        if (input == null || input.isEmpty()) {
            return null; // Return null if the input is null or empty
        }

        // Check if the input contains a decimal point
        int dotIndex = input.indexOf(".");
        if (dotIndex != -1) {
            // If the decimal exists, extract the substring after the dot
            String valueAfterDot = input.substring(dotIndex + 1);

            try {
                // Convert the substring to an integer
                return Integer.parseInt(valueAfterDot);
            } catch (NumberFormatException e) {
                // If parsing fails, return null (invalid number after the decimal)
                return null;
            }
        } else {
            return null; // Return null if there's no decimal point
        }
    }

    public static String replaceValueAfterDot(String originalValue, String newValue) {
        // Check if the input is null or empty
        if (originalValue == null || originalValue.isEmpty()) {
            return null; // Return null if the input is null or empty
        }

        // Find the index of the dot
        int dotIndex = originalValue.indexOf(".");

        // If there's no dot, return the original value as it is
        if (dotIndex == -1) {
            return originalValue; // No dot present, return original value
        }

        // Replace the value after the dot with the new value
        String updatedValue = originalValue.substring(0, dotIndex + 1) + newValue;

        return updatedValue; // Return the updated string
    }

    public static String formatNumber5(String input) {
        if (input == null || input.isEmpty() || !input.matches("\\d+")) {
            return "00000"; // Return default value for null or empty string
        }
        try {
            // Parse the string to an integer
            BigInteger number = new BigInteger(input);
            return String.format("%05d", number); // Format with leading zeros
        } catch (NumberFormatException e) {
            // Handle case where input is not a valid number
            return "00000"; // Return default for invalid input
        }
    }

    public static String formatNumber15(String input) {
        if (input == null || input.isEmpty() || !input.matches("\\d+")) {
            return "000000000000000"; // Return default value for null or empty string
        }
        try {
            // Parse the string to an integer
            BigInteger number = new BigInteger(input);
            return String.format("%015d", number); // Format with leading zeros
        } catch (NumberFormatException e) {
            // Handle case where input is not a valid number
            return "000000000000000"; // Return default for invalid input
        }
    }
    public static String formatNumber12(String input) {
        if (input == null || input.isEmpty() || !input.matches("\\d+")) {
            return "000000000000"; // Return default value for null or empty string (12 digits)
        }
        try {
            // Parse the string to an integer
            BigInteger number = new BigInteger(input);
            return String.format("%012d", number); // Format with leading zeros to make it 12 digits
        } catch (NumberFormatException e) {
            // Handle case where input is not a valid number
            return "000000000000"; // Return default for invalid input (10 digits)
        }
    }



    public static String formatNumber20(String input) {
        if (input == null || input.isEmpty() || !input.matches("\\d+")) {
            return "00000000000000000000"; // Return default value for null or empty string
        }
        try {
            // Parse the string to an integer
            BigInteger number = new BigInteger(input);
            return String.format("%020d", number); // Format with leading zeros
        } catch (NumberFormatException e) {
            // Handle case where input is not a valid number
            return "00000000000000000000"; // Return default for invalid input
        }
    }
    public static String generateChecksum( long checksum) {
        // Format the checksum with prefix
        String formattedChecksum = String.format("CHECKSUM%08d", checksum);

        // Calculate how many padding characters are needed
        int paddingLength = 20 - formattedChecksum.length();
        StringBuilder paddedOutput = new StringBuilder(formattedChecksum);

        // Append padding (e.g., zeros) to the left of the checksum
        for (long i = 0; i < paddingLength; i++) {
            paddedOutput.insert(8, '0'); // Insert zeros before the checksum
        }
        return paddedOutput.toString();
    }
    public long convertValues(String instAmt,String instDate){
        long instAmount = 0;
        long instDate1 = 0;
        if(null!=instAmt && !instAmt.isEmpty()) {
            for (char c : instAmt.toCharArray()) {
                int digit = Character.getNumericValue(c);
                instAmount+= digit;
            }
        }
        if(null!=instDate && !instDate.isEmpty()) {
            for (char c : instDate.toCharArray()) {
                int digit = Character.getNumericValue(c);
                instDate1+= digit;
            }
        }
        return instAmount+instDate1;
    }

}
