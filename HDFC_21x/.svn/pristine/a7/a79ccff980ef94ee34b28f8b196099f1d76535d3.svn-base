package com.isl.gbm.rbibond.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TransactionReponseDto;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.dao.RbiBondClosureOnMaturDeathDao;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;



@Repository
public class RbiBondClosureOnMaturDeathDaoImpl implements RbiBondClosureOnMaturDeathDao {

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;
    private static final String ERROR_CREATION_TYPE = "Error creating STRUCT";

    private static final Logger log = LoggerFactory.getLogger(RbiBondClosureOnMaturDeathDaoImpl.class);

    @Autowired
    TransactionValidator transactionValidator;

    private static final String RESULT_SET_EMPTY = "Result Set is Empty";
    private static final String CONNECTION_EMPTY = "Connection is Empty";



    @Override
    public ResponseDataDTO getAddDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        Map<String, Object> rbiBondClosureBefMaturDetMap = new HashMap<>();

        RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureOnMaturDeathDTO();
        RbiBondClosureOnMaturDeathNomiineeDTO rbiBondClosureOnMaturDeathDTO = new RbiBondClosureOnMaturDeathNomiineeDTO();
        List<RbiBondClosureOnMaturDeathDTO> rbiBondBLAList = new ArrayList<>();
        List<RbiBondClosureOnMaturDeathNomiineeDTO> rbiBondNomineeList = new ArrayList<>();
        List<RbiBondClosureOnMaturDeathDTO> rbiBondBondSubsList = new ArrayList<>();
        List<RbiBondClosureOnMaturDeathDTO> rbiBondBondNomineeDetailsList = new ArrayList<>();

        RbiBondClosureOnMaturDeathDTO rbiBondBlaDet = new RbiBondClosureOnMaturDeathDTO();
        RbiBondClosureOnMaturDeathDTO rbiBondNomineeData = new RbiBondClosureOnMaturDeathDTO();



        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATH_SP_ADD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getSchemeCode());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getPanNo());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getClosureType());

                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getProceedsToBePaid());

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));

                resultMsg = stmt.getString(index);
                responseDataDTO.setMessage(resultMsg);


                if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {



                    if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("scheme")) {

                        ResultSet rs = (ResultSet) stmt.getObject(9);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureOnMaturDeathDTO();
                            rbiBondClosureBeforeMaturDTO1.setSchemeCode(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setSchemeName(rs.getString(2));
                            rbiBondClosureBeforeMaturDTO1.setSchemeStatus(rs.getString(3));

                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }



                    else
                    if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("pan")) {

                        ResultSet rs = (ResultSet) stmt.getObject(9);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureOnMaturDeathDTO();
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setPanNo(rs.getString(2));

                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }






                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("bla")) {

                        ResultSet rs = (ResultSet) stmt.getObject(9);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureOnMaturDeathDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(2));
                            rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString(3));
                            rbiBondClosureBeforeMaturDTO1.setVerifiedStatus(rs.getString(4));
                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }
                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("boSubsId")) {

                        ResultSet rs = (ResultSet) stmt.getObject(9);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureOnMaturDeathDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(2));
                            rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString(3));
                            rbiBondClosureBeforeMaturDTO1.setMaturityDate(rs.getString(4));
                            rbiBondBondSubsList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBondSubsList);
                    }

                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("det")) {

                        ResultSet rs = (ResultSet) stmt.getObject(9);

                        if(rs != null) {
                            while (rs.next()) {
                                rbiBondClosureOnMaturDeathDTO = new RbiBondClosureOnMaturDeathNomiineeDTO();
                                rbiBondClosureOnMaturDeathDTO.setNomineeName(rs.getString(1));
                                rbiBondClosureOnMaturDeathDTO.setDateOfBirthOfMinor(rs.getString(2));
                                rbiBondNomineeList.add(rbiBondClosureOnMaturDeathDTO);

                            }
                            responseDataDTO.setResult(rbiBondNomineeList);

  }

                    }

                }
            }
        }
        catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO getAddValidation(RbiBondClosureOnMaturDeathDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        RbiBondClosureOnMaturDeathDTO finalResponse =new RbiBondClosureOnMaturDeathDTO();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<RbiBondClosureOnMaturDeathDTO> listOfBLAs = new ArrayList<>();
        List<RbiBondClosureOnMaturDeathDTO> listOfSub = new ArrayList<>();
        List<RbiBondClosureOnMaturDeathDTO> listOfNominee = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATH_SP_ADD_VAL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getPanNo());
                statement.setString(index++, paymentRequestDTO.getBlaNo());
                statement.setString(index++, paymentRequestDTO.getBoSubsId());
                statement.setString(index++, paymentRequestDTO.getClosureType());
                statement.setString(index++, paymentRequestDTO.getProceedsToBePaid());
                statement.setArray(index++, generateArrayForPanValid(connection, paymentRequestDTO.getNomineeDetails()));
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(10);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondClosureOnMaturDeathDTO bondTrailRunDTO = new RbiBondClosureOnMaturDeathDTO();
                            bondTrailRunDTO.setModeOfHolding(resultSet.getString(1));
                            bondTrailRunDTO.setModeOfHoldingCode(resultSet.getString(2));
                            bondTrailRunDTO.setHolderType(resultSet.getString(3));
                            bondTrailRunDTO.setBlaNo(resultSet.getString(4));
                            bondTrailRunDTO.setBondHolderName(resultSet.getString(5));
                            bondTrailRunDTO.setFirstJointBondHolderBLA(resultSet.getString(6));
                            bondTrailRunDTO.setFirstJointBondHolderName(resultSet.getString(7));
                            bondTrailRunDTO.setSecondJointBondHolderBLA(resultSet.getString(8));
                            bondTrailRunDTO.setSecondJointBondHolderName(resultSet.getString(9));

                            listOfBLAs.add(bondTrailRunDTO);
                        }

                        finalResponse.setBlaDetailsList(listOfBLAs);

                    } else {
                        resultMsg = RESULT_SET_EMPTY;
                    }
                    resultSet = (ResultSet) statement.getObject(11);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondClosureOnMaturDeathDTO bondSubs = new RbiBondClosureOnMaturDeathDTO();

                            bondSubs.setSubsDate(resultSet.getString(1));
                            bondSubs.setBoSubsId(resultSet.getString(2));
                            bondSubs.setSubsAmt(resultSet.getString(3));
                            bondSubs.setBondType(resultSet.getString(4));
                            bondSubs.setMaturityDate(resultSet.getString(5));
                            bondSubs.setPanNo(resultSet.getString(6));
                            bondSubs.setInterestPayOut8Per(resultSet.getString(7));
                            bondSubs.setNonCumulativeInt(resultSet.getString(8));
                            bondSubs.setCumulativeInt(resultSet.getString(9));
                            bondSubs.setInterestPayOut7Per(resultSet.getString(10));
                            bondSubs.setNonCumulativeIntFor7(resultSet.getString(11));
                            bondSubs.setCumulativeIntFor7(resultSet.getString(12));
                            bondSubs.setInterestPayOut85Per(resultSet.getString(13));
                            bondSubs.setNonCumulativeIntFor85(resultSet.getString(14));
                            bondSubs.setCumulativeIntFor85(resultSet.getString(15));
                            bondSubs.setTotal(resultSet.getString(16));
                            bondSubs.setInOperativePAN(resultSet.getString(17));
                            bondSubs.setNonFilers(resultSet.getString(18));
                            bondSubs.setAge(resultSet.getString(19));
                            bondSubs.setTaxableLimit(resultSet.getString(20));
                            bondSubs.setWaiverDocument(resultSet.getString(21));
                            bondSubs.setWaiverDocumentRefNo(resultSet.getString(22));
                            bondSubs.setRrbTDSRate(resultSet.getString(23));
                            bondSubs.setThresholdExceedAmount(resultSet.getString(24));
                            bondSubs.setThresholdExceedTDSAmount(resultSet.getString(25));
                            bondSubs.setExemptionRate(resultSet.getString(26));
                            bondSubs.setExemptionAmount(resultSet.getString(27));
                            bondSubs.setExemptionTDSAmount(resultSet.getString(28));
                            bondSubs.setSurcharge(resultSet.getString(29));
                            bondSubs.setEduCess(resultSet.getString(30));
                            bondSubs.setBaseRateAmount(resultSet.getString(31));
                            bondSubs.setSurchargeAmount(resultSet.getString(32));
                            bondSubs.setEduCessAmount(resultSet.getString(33));
                            bondSubs.setTdsAmount(resultSet.getString(34));
                            bondSubs.setTdsRecoveryAmount(resultSet.getString(35));
                            bondSubs.setTdsRefundAmount(resultSet.getString(36));
                            bondSubs.setAccClosedDate(resultSet.getString(37));
                            bondSubs.setProceedsToBePaid(resultSet.getString(38));
                            bondSubs.setIntPaid(resultSet.getString(39));
                            bondSubs.setDod(resultSet.getString(40));
                            bondSubs.setDodCertReceiptDate(resultSet.getString(41));
                            bondSubs.setIntCalFromDate(resultSet.getString(42));
                            bondSubs.setIntCalToDate(resultSet.getString(43));
                            bondSubs.setBrokenIntRate(resultSet.getString(44));
                            bondSubs.setBrokenIntAmt(resultSet.getString(45));
                            bondSubs.setPendingInterest(resultSet.getString(46));
                            bondSubs.setTotIntBefTDS(resultSet.getString(47));
                            bondSubs.setTdsAmount(resultSet.getString(48));
                            bondSubs.setTotIntAftTDS(resultSet.getString(49));
                            bondSubs.setTotalAmtToBePaid(resultSet.getString(50));
                            bondSubs.setTotalPayoutOfTheYear(resultSet.getString(51));
                            listOfSub.add(bondSubs);
                        }
                        finalResponse.setBondSubsList(listOfSub);

                    } else {
                        resultMsg = RESULT_SET_EMPTY;
                    }
                    resultSet = (ResultSet) statement.getObject(12);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondClosureOnMaturDeathDTO bondNom = new RbiBondClosureOnMaturDeathDTO();
                            bondNom.setNomineeName(resultSet.getString(1));
                            bondNom.setNomineeDOB(resultSet.getString(2));
                            bondNom.setPanNo(resultSet.getString(3));
                            bondNom.setRelationShip(resultSet.getString(4));
                            bondNom.setSharePercentage(resultSet.getString(5));
                            bondNom.setShareAmountSettled(resultSet.getString(6));
//                            bondNom.setAccNum(resultSet.getString(8));
                            bondNom.setRemarks(resultSet.getString(9));
                            bondNom.setTdsRate(resultSet.getString(10));
                            bondNom.setTdsAmount(resultSet.getString(11));

                            listOfNominee.add(bondNom);

                        }
                        finalResponse.setNomineeDetailList(listOfNominee);
                    }
                        finalResponse.setDdPoAccNum(statement.getString(13));
                            responseDataDTO.setResult(finalResponse);
                    responseDataDTO.setMessage(resultMsg);
                    return responseDataDTO;
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
             connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


//#siva for date format convertion from string
    public String dateFormat(String inputDate){
        String inputFormat = "dd-MM-yy";
        String outputFormat = "dd-MMM-yyyy";
        String formattedDate ="";
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

            Date date = inputDateFormat.parse(inputDate);
             formattedDate = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }


    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        Map<String, Object> rbiBondClosureBefMaturDetMap = new HashMap<>();

        RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureOnMaturDeathDTO();
        RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO2 = new RbiBondClosureOnMaturDeathDTO();
        ResultSet resultSet = null;

        List<RbiBondClosureOnMaturDeathDTO> rbiBondBLAList = new ArrayList<>();
        List<RbiBondClosureOnMaturDeathDTO> rbiBondBondSubsList = new ArrayList<>();
        RbiBondClosureOnMaturDeathDTO    rbiBondNomineeData = new RbiBondClosureOnMaturDeathDTO();
        RbiBondClosureOnMaturDeathDTO rbiBondBlaDet = new RbiBondClosureOnMaturDeathDTO();
        List<RbiBondClosureOnMaturDeathDTO> rbiBondNomineeDetailsList = new ArrayList<>();
        List<RbiBondClosureOnMaturDeathDTO> rbiBondLegalHierDetailsList = new ArrayList<>();
        RbiBondClosureOnMaturDeathDTO finalResponse =new RbiBondClosureOnMaturDeathDTO();
        List<RbiBondClosureOnMaturDeathDTO> listOfBLAs = new ArrayList<>();
        List<RbiBondClosureOnMaturDeathDTO> listOfSub = new ArrayList<>();
        List<RbiBondClosureOnMaturDeathDTO> listOfNominee = new ArrayList<>();


        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;

                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATH_SP_VER_DEL_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getSchemeCode());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getPanNo());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getClosureType());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR); //cursorval out
                stmt.registerOutParameter(index++, OracleTypes.CURSOR); //nominee details
                stmt.registerOutParameter(index++, OracleTypes.CURSOR); //nominee details
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                responseDataDTO.setMessage(resultMsg);
                if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                    resultSet = (ResultSet) stmt.getObject(8);
                    if (rbiBondClosureBeforeMaturDTO.getSchemeCode() == null && resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondClosureOnMaturDeathDTO schemeCodeDTO = new RbiBondClosureOnMaturDeathDTO();
                            schemeCodeDTO.setSchemeCode(resultSet.getString(1));
                            schemeCodeDTO.setSchemeName(resultSet.getString(2));
                            schemeCodeDTO.setSchemeStatus(resultSet.getString(3));
                            rbiBondBLAList.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);
                    }
                    if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("pan")) {
                        ResultSet rs = (ResultSet) stmt.getObject(8);
                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureOnMaturDeathDTO();
                            rbiBondClosureBeforeMaturDTO1.setPanNo(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(2));

                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }



                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("bla")) {


                        ResultSet rs = (ResultSet) stmt.getObject(8);
                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureOnMaturDeathDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(2));
                            rbiBondClosureBeforeMaturDTO1.setModeOfHolding(rs.getString(3));
                            rbiBondClosureBeforeMaturDTO1.setVerifiedStatus(rs.getString(4));
                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }

                        responseDataDTO.setResult(rbiBondBLAList);

                    }
                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("boSubsId")) {

                        ResultSet rs = (ResultSet) stmt.getObject(8);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondClosureOnMaturDeathDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(2));
                            rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString(3));
                            rbiBondClosureBeforeMaturDTO1.setMaturityDate(rs.getString(4));
                            rbiBondClosureBeforeMaturDTO1.setGbmTranId(rs.getString(5));
                            rbiBondBondSubsList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBondSubsList);
                    }

                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("det")) {
                        resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                RbiBondClosureOnMaturDeathDTO bondTrailRunDTO = new RbiBondClosureOnMaturDeathDTO();
                                bondTrailRunDTO.setModeOfHolding(resultSet.getString(1));
                                bondTrailRunDTO.setHolderType(resultSet.getString(2));
                                bondTrailRunDTO.setBlaNo(resultSet.getString(3));
                                bondTrailRunDTO.setBondHolderName(resultSet.getString(4));
                                bondTrailRunDTO.setFirstJointBondHolderBLA(resultSet.getString(5));
                                bondTrailRunDTO.setFirstJointBondHolderName(resultSet.getString(6));
                                bondTrailRunDTO.setSecondJointBondHolderName(resultSet.getString(7));

                                listOfBLAs.add(bondTrailRunDTO);
                            }

                            finalResponse.setBlaDetailsList(listOfBLAs);

                        } else {
                            resultMsg = RESULT_SET_EMPTY;
                        }
                        resultSet = (ResultSet) stmt.getObject(9);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                RbiBondClosureOnMaturDeathDTO bondSubs = new RbiBondClosureOnMaturDeathDTO();

                                bondSubs.setSubsDate(resultSet.getString(1));
                                bondSubs.setBoSubsId(resultSet.getString(2));
                                bondSubs.setSubsAmt(resultSet.getString(3));
                                bondSubs.setBondType(resultSet.getString(4));
                                bondSubs.setMaturityDate(resultSet.getString(5));
                                bondSubs.setPanNo(resultSet.getString(6));
                                bondSubs.setInterestPayOut8Per(resultSet.getString(7));
                                bondSubs.setNonCumulativeInt(resultSet.getString(8));
                                bondSubs.setCumulativeInt(resultSet.getString(9));
                                bondSubs.setInterestPayOut7Per(resultSet.getString(10));
                                bondSubs.setNonCumulativeIntFor7(resultSet.getString(11));
                                bondSubs.setCumulativeIntFor7(resultSet.getString(12));
                                bondSubs.setInterestPayOut85Per(resultSet.getString(13));
                                bondSubs.setNonCumulativeIntFor85(resultSet.getString(14));
                                bondSubs.setCumulativeIntFor85(resultSet.getString(15));
                                bondSubs.setTotal(resultSet.getString(16));
                                bondSubs.setInOperativePAN(resultSet.getString(17));
                                bondSubs.setNonFilers(resultSet.getString(18));
                                bondSubs.setAge(resultSet.getString(19));
                                bondSubs.setTaxableLimit(resultSet.getString(20));
                                bondSubs.setWaiverDocument(resultSet.getString(21));
                                bondSubs.setWaiverDocumentRefNo(resultSet.getString(22));
                                bondSubs.setRrbTDSRate(resultSet.getString(23));
                                bondSubs.setThresholdExceedAmount(resultSet.getString(24));
                                bondSubs.setThresholdExceedTDSAmount(resultSet.getString(25));
                                bondSubs.setExemptionRate(resultSet.getString(26));
                                bondSubs.setExemptionAmount(resultSet.getString(27));
                                bondSubs.setExemptionTDSAmount(resultSet.getString(28));
                                bondSubs.setSurcharge(resultSet.getString(29));
                                bondSubs.setEduCess(resultSet.getString(30));
                                bondSubs.setBaseRateAmount(resultSet.getString(31));
                                bondSubs.setSurchargeAmount(resultSet.getString(32));
                                bondSubs.setEduCessAmount(resultSet.getString(33));
                                bondSubs.setTdsAmount(resultSet.getString(34));
                                bondSubs.setTdsRecoveryAmount(resultSet.getString(35));
                                bondSubs.setTdsRefundAmount(resultSet.getString(36));
                                bondSubs.setAccClosedDate(resultSet.getString(37));
                                bondSubs.setProceedsToBePaid(resultSet.getString(38));
                                bondSubs.setIntPaid(resultSet.getString(39));
                                bondSubs.setDod(resultSet.getString(40));
                                bondSubs.setDodCertReceiptDate(resultSet.getString(41));
                                bondSubs.setIntCalFromDate(resultSet.getString(42));
                                bondSubs.setIntCalToDate(resultSet.getString(43));
                                bondSubs.setBrokenIntRate(resultSet.getString(44));
                                bondSubs.setBrokenIntAmt(resultSet.getString(45));
                                bondSubs.setPendingInterest(resultSet.getString(46));
                                bondSubs.setTotIntBefTDS(resultSet.getString(47));
                                bondSubs.setTdsAmount(resultSet.getString(48));
                                bondSubs.setTotIntAftTDS(resultSet.getString(49));
                                bondSubs.setTotalAmtToBePaid(resultSet.getString(50));
                                bondSubs.setClosureRemarks(resultSet.getString(51));
                                bondSubs.setTotalPayoutOfTheYear(resultSet.getString(52));
                                listOfSub.add(bondSubs);
                            }
                            finalResponse.setBondSubsList(listOfSub);

                        } else {
                            resultMsg = RESULT_SET_EMPTY;
                        }
                        resultSet = (ResultSet) stmt.getObject(10);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                RbiBondClosureOnMaturDeathDTO bondNom = new RbiBondClosureOnMaturDeathDTO();
                                bondNom.setNomineeName(resultSet.getString(1));
                                bondNom.setNomineeDOB(resultSet.getString(2));
                                bondNom.setPanNo(resultSet.getString(3));
                                bondNom.setRelationShip(resultSet.getString(4));
                                bondNom.setSharePercentage(resultSet.getString(5));
                                bondNom.setShareAmountSettled(resultSet.getString(6));
                                bondNom.setModeOfInterestPay(resultSet.getString(7));
                                bondNom.setAccNum(resultSet.getString(8));
                                bondNom.setRemarks(resultSet.getString(9));
                                bondNom.setTdsRate(resultSet.getString(10));
                                bondNom.setTdsAmount(resultSet.getString(11));

                                listOfNominee.add(bondNom);

                            }
                            finalResponse.setNomineeDetailList(listOfNominee);
                        }
                        finalResponse.setCreatedBy(stmt.getString(11));
                        finalResponse.setCreatedDate(stmt.getString(12));
                        finalResponse.setVerifiedBy(stmt.getString(13));
                        finalResponse.setVerifiedDate(stmt.getString(14));
                        finalResponse.setVerifiedStatus(stmt.getString(15));

                        finalResponse.setDdPoAccNum(null);
                        responseDataDTO.setResult(finalResponse);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;

                    }

                }
            }
        }
        catch (Exception e) {
            log.info(e.getMessage());
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getInqDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<BondActualInterestPaymentRequestDTO> listOfResponse = new LinkedList<>();
        List<RbiBondClosureOnMaturDeathDTO> listOfResponse1 = new LinkedList<>();
        RbiBondClosureOnMaturDeathDTO finalResponse =new RbiBondClosureOnMaturDeathDTO();

        List<RbiBondClosureOnMaturDeathDTO> listOfBLAs = new ArrayList<>();
        List<RbiBondClosureOnMaturDeathDTO> listOfSub = new ArrayList<>();
        List<RbiBondClosureOnMaturDeathDTO> listOfNominee = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATH_SP_INQ.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getSchemeCode());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getName() != null ? rbiBondClosureBeforeMaturDTO.getName().trim() : rbiBondClosureBeforeMaturDTO.getName());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getPanNoName());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getClosureType());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
                    if (rbiBondClosureBeforeMaturDTO.getSchemeCode() == null && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setSchemeCode(resultSet.getString(1));
                            schemeCodeDTO.setSchemeName(resultSet.getString(2));
                            schemeCodeDTO.setSchemeStatus(resultSet.getString(3));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else if (rbiBondClosureBeforeMaturDTO.getSchemeCode() != null && "P".equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getFlag())
                            && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setPanNoName(resultSet.getString(1));
                            schemeCodeDTO.setName(resultSet.getString(2));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else if (rbiBondClosureBeforeMaturDTO.getSchemeCode() != null && "B".equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getFlag())
                            && resultSet != null) {
                        while (resultSet.next()) {
                            BondActualInterestPaymentRequestDTO schemeCodeDTO = new BondActualInterestPaymentRequestDTO();
                            schemeCodeDTO.setBlaNo(resultSet.getString(1));
                            schemeCodeDTO.setName(resultSet.getString(2));
                            schemeCodeDTO.setModeOfHolding(resultSet.getString(3));
                            schemeCodeDTO.setVerifyStatus(resultSet.getString(4));
                            listOfResponse.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                    } else if (rbiBondClosureBeforeMaturDTO.getSchemeCode() != null && "BS".equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getFlag())
                            && resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondClosureOnMaturDeathDTO schemeCodeDTO = new RbiBondClosureOnMaturDeathDTO();
                            schemeCodeDTO.setBlaNo(resultSet.getString(1));
                            schemeCodeDTO.setName(resultSet.getString(2));
                            schemeCodeDTO.setBoSubsId(resultSet.getString(3));
                            schemeCodeDTO.setMaturityDate(resultSet.getString(4));
                            schemeCodeDTO.setGbmTranId(resultSet.getString(4));
                            listOfResponse1.add(schemeCodeDTO);
                        }
                        responseDataDTO.setResult(listOfResponse1);
                        responseDataDTO.setMessage(resultMsg);
                    }

                        else if (rbiBondClosureBeforeMaturDTO.getSchemeCode() != null && "D".equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getFlag())
                            && resultSet != null) {
                        resultSet = (ResultSet) statement.getObject(9);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                RbiBondClosureOnMaturDeathDTO bondTrailRunDTO = new RbiBondClosureOnMaturDeathDTO();
                                bondTrailRunDTO.setModeOfHolding(resultSet.getString(1));
                                bondTrailRunDTO.setHolderType(resultSet.getString(2));
                                bondTrailRunDTO.setBlaNo(resultSet.getString(3));
                                bondTrailRunDTO.setBondHolderName(resultSet.getString(4));
                                bondTrailRunDTO.setFirstJointBondHolderBLA(resultSet.getString(5));
                                bondTrailRunDTO.setFirstJointBondHolderName(resultSet.getString(6));
                                bondTrailRunDTO.setSecondJointBondHolderName(resultSet.getString(7));

                                listOfBLAs.add(bondTrailRunDTO);
                            }

                            finalResponse.setBlaDetailsList(listOfBLAs);

                        } else {
                            resultMsg = RESULT_SET_EMPTY;
                        }
                        resultSet = (ResultSet) statement.getObject(10);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                RbiBondClosureOnMaturDeathDTO bondSubs = new RbiBondClosureOnMaturDeathDTO();
                                bondSubs.setSubsDate(resultSet.getString(1));
                            bondSubs.setBoSubsId(resultSet.getString(2));
                            bondSubs.setSubsAmt(resultSet.getString(3));
                            bondSubs.setBondType(resultSet.getString(4));
                            bondSubs.setMaturityDate(resultSet.getString(5));
                            bondSubs.setPanNo(resultSet.getString(6));
                            bondSubs.setInterestPayOut8Per(resultSet.getString(7));
                            bondSubs.setNonCumulativeInt(resultSet.getString(8));
                            bondSubs.setCumulativeInt(resultSet.getString(9));
                            bondSubs.setInterestPayOut7Per(resultSet.getString(10));
                            bondSubs.setNonCumulativeIntFor7(resultSet.getString(11));
                            bondSubs.setCumulativeIntFor7(resultSet.getString(12));
                            bondSubs.setInterestPayOut85Per(resultSet.getString(13));
                            bondSubs.setNonCumulativeIntFor85(resultSet.getString(14));
                            bondSubs.setCumulativeIntFor85(resultSet.getString(15));
                            bondSubs.setTotal(resultSet.getString(16));
                            bondSubs.setInOperativePAN(resultSet.getString(17));
                            bondSubs.setNonFilers(resultSet.getString(18));
                            bondSubs.setAge(resultSet.getString(19));
                            bondSubs.setTaxableLimit(resultSet.getString(20));
                            bondSubs.setWaiverDocument(resultSet.getString(21));
                            bondSubs.setWaiverDocumentRefNo(resultSet.getString(22));
                            bondSubs.setRrbTDSRate(resultSet.getString(23));
                            bondSubs.setThresholdExceedAmount(resultSet.getString(24));
                            bondSubs.setThresholdExceedTDSAmount(resultSet.getString(25));
                            bondSubs.setExemptionRate(resultSet.getString(26));
                            bondSubs.setExemptionAmount(resultSet.getString(27));
                            bondSubs.setExemptionTDSAmount(resultSet.getString(28));
                            bondSubs.setSurcharge(resultSet.getString(29));
                            bondSubs.setEduCess(resultSet.getString(30));
                            bondSubs.setBaseRateAmount(resultSet.getString(31));
                            bondSubs.setSurchargeAmount(resultSet.getString(32));
                            bondSubs.setEduCessAmount(resultSet.getString(33));
                            bondSubs.setTdsAmount(resultSet.getString(34));
                            bondSubs.setTdsRecoveryAmount(resultSet.getString(35));
                            bondSubs.setTdsRefundAmount(resultSet.getString(36));
                            bondSubs.setAccClosedDate(resultSet.getString(37));
                            bondSubs.setProceedsToBePaid(resultSet.getString(38));
                            bondSubs.setIntPaid(resultSet.getString(39));
                            bondSubs.setDod(resultSet.getString(40));
                            bondSubs.setDodCertReceiptDate(resultSet.getString(41));
                            bondSubs.setIntCalFromDate(resultSet.getString(42));
                            bondSubs.setIntCalToDate(resultSet.getString(43));
                            bondSubs.setBrokenIntRate(resultSet.getString(44));
                            bondSubs.setBrokenIntAmt(resultSet.getString(45));
                            bondSubs.setPendingInterest(resultSet.getString(46));
                            bondSubs.setTotIntBefTDS(resultSet.getString(47));
                            bondSubs.setTdsAmount(resultSet.getString(48));
                            bondSubs.setTotIntAftTDS(resultSet.getString(49));
                            bondSubs.setTotalAmtToBePaid(resultSet.getString(50));
                            bondSubs.setClosureRemarks(resultSet.getString(51));
                            bondSubs.setCheckerRemarkForClosure(resultSet.getString(52));
                            bondSubs.setTotalPayoutOfTheYear(resultSet.getString(53));
                            listOfSub.add(bondSubs);
                            }
                            finalResponse.setBondSubsList(listOfSub);

                        } else {
                            resultMsg = RESULT_SET_EMPTY;
                        }
                        resultSet = (ResultSet) statement.getObject(11);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                RbiBondClosureOnMaturDeathDTO bondNom = new RbiBondClosureOnMaturDeathDTO();
                                bondNom.setNomineeName(resultSet.getString(1));
                                bondNom.setNomineeDOB(resultSet.getString(2));
                                bondNom.setPanNo(resultSet.getString(3));
                                bondNom.setRelationShip(resultSet.getString(4));
                                bondNom.setSharePercentage(resultSet.getString(5));
                                bondNom.setShareAmountSettled(resultSet.getString(6));
                                bondNom.setModeOfInterestPay(resultSet.getString(7));
                                bondNom.setAccNum(resultSet.getString(8));
                                bondNom.setRemarks(resultSet.getString(9));
                                bondNom.setTdsRate(resultSet.getString(10));
                                bondNom.setTdsAmount(resultSet.getString(11));
                                listOfNominee.add(bondNom);

                            }
                            finalResponse.setNomineeDetailList(listOfNominee);
                        }
                        finalResponse.setDdPoAccNum(null);
                        finalResponse.setCreatedBy(statement.getString(12));
                        finalResponse.setCreatedDate(statement.getString(13));
                        finalResponse.setVerifiedBy(statement.getString(14));
                        finalResponse.setVerifiedDate(statement.getString(15));
                        finalResponse.setVerifiedStatus(statement.getString(16));
                        responseDataDTO.setResult(finalResponse);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    }
                }
            } else
                resultMsg = CONNECTION_EMPTY;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
        }





    @Override
    public ResponseDataDTO add(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO1) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATH_SP_ADD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO1.getSchemeCode());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO1.getPanNo());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO1.getBlaNo());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO1.getBoSubsId());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO1.getClosureType());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO1.getProceedsToBePaid());
                statement.setArray(index++, generateArrayOfNominee(connection, rbiBondClosureBeforeMaturDTO1.getNomineeDetails())); // subscription Id's
                statement.setString(index++, rbiBondClosureBeforeMaturDTO1.getClosureRemarks());
                statement.setString(index++, "76ERS");
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private static ARRAY generateArrayOfLegalHeir(Connection connection, List<RbiBondClosureOnMaturDeathLegalHierDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_LEG_HEIR_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_LEG_HEIR", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RbiBondClosureOnMaturDeathLegalHierDTO rbiBondClosureBeforeMaturDeathLegalHierDTO = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[8];
            rbiBondClosureBeforeMaturDeathLegalHierDTO = list.get(index);
            paramArray[0] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getLegalheirName();
            paramArray[1] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getRelationShip();
            paramArray[2] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getSharePercentage();
            paramArray[3] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getShareAmountSettled();
            paramArray[4] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getModeOfInterestPay();
            paramArray[5] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getAccNum();
            paramArray[6] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getAccBranch();
            paramArray[7] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getRemarks();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


    private static ARRAY generateArrayOfNominee(Connection connection, List<RbiBondClosureOnMaturDeathNomiineeDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_DET_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_DET", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RbiBondClosureOnMaturDeathNomiineeDTO rbiBondClosureBeforeMaturDeathNomiineeDTO = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[9];
            rbiBondClosureBeforeMaturDeathNomiineeDTO = list.get(index);
            paramArray[0] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getNomineeName();
            paramArray[1] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getNomineeDob();
            paramArray[2] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getPanNo();
            paramArray[3] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getRelationShip();
            paramArray[4] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getSharePercentage();
            paramArray[5] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getShareAmountSettled();
            paramArray[6] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getModeOfInterestPay();
            paramArray[7] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getAccNum();
            paramArray[8] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getRemarks();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }








    @Override
    public ResponseDataDTO delete(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondClosureBeforeMaturDTO.getRemarks() == null
                || rbiBondClosureBeforeMaturDTO.getRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATH_SP_DEL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getSchemeCode());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getPanNo());
                statement.setString(index++,  rbiBondClosureBeforeMaturDTO.getBlaNo());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getClosureType());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getRemarks());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
            } else
                resultMsg = CONNECTION_EMPTY;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        List<GbmTransactionMasterDto> gbmTransactionMasterDtos = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATH_SP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModuleId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                resultMsg = stmt.getString(index);


                Date tranDate = new Date();
                    try {
                        tranDate = new SimpleDateFormat("dd/MM/yyyy").parse(stmt.getString(8));
                    }catch (ParseException e) {
                        e.printStackTrace();
                    }
                ResultSet rs = (ResultSet) stmt.getObject(index-2);

                while(rs.next()){
                    GbmTransactionMasterDto gbmTransactionMasterDto = new GbmTransactionMasterDto();
                    gbmTransactionMasterDto.setAcctNum(rs.getString("acct_num"));
                    gbmTransactionMasterDto.setTranLeg(rs.getString("tran_type").charAt(0));
                    gbmTransactionMasterDto.setAmount(Double.parseDouble(rs.getString("amt")));
                    gbmTransactionMasterDto.setTransactionChannel(stmt.getString(5));
                    gbmTransactionMasterDto.setTranId(stmt.getString(6));
                    gbmTransactionMasterDto.setTranDate(tranDate);
                    gbmTransactionMasterDto.setSchemeCode(stmt.getString(9));
                    gbmTransactionMasterDto.setCrn(rbiBondClosureBeforeMaturDTO.getBoSubsId());

                    gbmTransactionMasterDtos.add(gbmTransactionMasterDto);

                }
                responseDataDTO.setResult(gbmTransactionMasterDtos);
            }
            else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO updatePANStatus(PanValidationRetriggerResponseDTO responseDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATH_SP_TRIGGER_RES.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, responseDTO.getSchemeCode());
                statement.setArray(index++, generateTYP_typ_rbi_bond_nsdl_res_OBJ(connection, responseDTO));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                resultMsg=statement.getString(index);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, statement.getString(index));
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    responseDataDTO.setMessage(statement.getString(index));
                    responseDataDTO.setResult(responseDTO.getOldPan());
                    return responseDataDTO;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private ARRAY generateTYP_typ_rbi_bond_nsdl_res_OBJ(Connection connection, PanValidationRetriggerResponseDTO dto) throws Exception {
        // Create the struct descriptor for the database object
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_PAN_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_PAN", connection);
        // Create an array to hold the single STRUCT object
        STRUCT[] genericTableInsertStructs = new STRUCT[1];
        // Prepare the parameter array
        Object[] paramArray = new Object[14];
        // Fill the parameter array with the data from the DTO
        paramArray[0] = dto.getPanNumber();
        paramArray[1] = dto.getNameStatus();
        paramArray[2] = dto.getPanStatus();
        paramArray[3] = dto.getDobStatus();
        paramArray[4] = dto.getProcessStatus();
        // New fields added to paramArray
        paramArray[5] = dto.getPanStatusDescription();
        paramArray[6] = dto.getPanNameDescription();
        paramArray[7] = dto.getFatherName();
        paramArray[8] = dto.getPanDobDescription();
        paramArray[9] = dto.getReturnMessage();
        paramArray[10] = dto.getReturnCode();
        paramArray[11] = dto.getReturnDescription();
        paramArray[12] = dto.getSeedingStatus();
        paramArray[13] = dto.getSeedingStatusDescription();
        // Create the STRUCT object using the parameter array
        STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
        genericTableInsertStructs[0] = struct; // Assign the STRUCT to the first (and only) index
        // Return the ARRAY containing the single STRUCT
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    @Override
    public ResponseDataDTO getPanValidation(RbiBondClosureOnMaturDeathDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<RbiBondClosureOnMaturDeathDTO> listOfResponse = new LinkedList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATH_SP_PAN_TRIGGER.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setArray(index++, generateArrayForPanValid(connection, paymentRequestDTO.getPanNumList()));
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, statement.getString(index));
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(5);
                   if ( !"details".equalsIgnoreCase(paymentRequestDTO.getDetailsOrSubmit())) {
                       if (resultSet != null) {
                       RbiBondClosureOnMaturDeathDTO schemeCodeDTO = new RbiBondClosureOnMaturDeathDTO();
                        while (resultSet.next()) {
                            schemeCodeDTO.setPanNo(resultSet.getString("pan_no"));
                            schemeCodeDTO.setBondHolderName(resultSet.getString("name"));
                            schemeCodeDTO.setDodBondHolder(resultSet.getString("dob"));
                            listOfResponse.add(schemeCodeDTO);
                        }

                        responseDataDTO.setResult(listOfResponse);
                        responseDataDTO.setMessage(resultMsg);
                        }
                    } else
                        resultMsg = RESULT_SET_EMPTY;
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySubmit(RbiBondClosureOnMaturDeathDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondACHFileWrapperDTO fileWrapperDTO = new BondACHFileWrapperDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATH_SP_VER.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getPanNo());
                statement.setString(index++, paymentRequestDTO.getBlaNo());
                statement.setString(index++, paymentRequestDTO.getBoSubsId());
                statement.setString(index++, paymentRequestDTO.getClosureType());
                statement.setString(index++, paymentRequestDTO.getRemarks());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, statement.getString(index));
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
                    if (resultSet != null) {
                        BondACHFileHeaderDTO headerDTO = new BondACHFileHeaderDTO();
                        while (resultSet.next()) {
                            headerDTO.setAchTransactionCode(resultSet.getString(1));
                            headerDTO.setControl(resultSet.getString(2));
                            headerDTO.setUserName(resultSet.getString(3));
                            headerDTO.setControl1(resultSet.getString(4));
                            headerDTO.setAchFileNumber(resultSet.getString(5));
                            headerDTO.setControl2(resultSet.getString(6));
                            headerDTO.setControl3(resultSet.getString(7));
                            headerDTO.setLedgerFolioNumber(resultSet.getString(8));
                            headerDTO.setUserDefLimit(resultSet.getString(9));
                            headerDTO.setTotalAmtInPaise(resultSet.getString(10));
                            headerDTO.setSettlementDate(resultSet.getString(11));
                            headerDTO.setReserved1(resultSet.getString(12));
                            headerDTO.setReserved2(resultSet.getString(13));
                            headerDTO.setFiller1(resultSet.getString(14));
                            headerDTO.setUserNumber(resultSet.getString(15));
                            headerDTO.setUserReference(resultSet.getString(16));
                            headerDTO.setSponsorBankIFSCMICRIIN(resultSet.getString(17));
                            headerDTO.setUserBankAccountNumber(resultSet.getString(18));
                            headerDTO.setTotalItems(resultSet.getString(19));
                            headerDTO.setSettlementCycle(resultSet.getString(20));
                            headerDTO.setFiller2(resultSet.getString(21));
                        }
                        fileWrapperDTO.setAchFileHeaderDTO(headerDTO);
                    }
                    resultSet = (ResultSet) statement.getObject(10);
                    if (resultSet != null) {
                        List<BondACHFileDetailsDTO> listOfACHDetailsDTO = new LinkedList<>();
                        while (resultSet.next()) {
                            BondACHFileDetailsDTO detailsDTO = new BondACHFileDetailsDTO();
                            detailsDTO.setAchTransactionCode(resultSet.getString(1));
                            detailsDTO.setControl(resultSet.getString(2));
                            detailsDTO.setDestinationAccountType(resultSet.getString(3));
                            detailsDTO.setLedgerFolioNumber(resultSet.getString(4));
                            detailsDTO.setControl1(resultSet.getString(5));
                            detailsDTO.setBeneficiaryAccHolderName(resultSet.getString(6));
                            detailsDTO.setControl2(resultSet.getString(7));
                            detailsDTO.setControl3(resultSet.getString(8));
                            detailsDTO.setUserName(resultSet.getString(9));
                            detailsDTO.setControl4(resultSet.getString(10));
                            detailsDTO.setAmt(resultSet.getString(11));
                            detailsDTO.setReserved1(resultSet.getString(12));
                            detailsDTO.setReserved2(resultSet.getString(13));
                            detailsDTO.setReserved3(resultSet.getString(14));
                            detailsDTO.setReserved4(resultSet.getString(15));
                            detailsDTO.setDestinationBankIFSCMICRIIN(resultSet.getString(16));
                            detailsDTO.setBeneficiaryBankAccNo(resultSet.getString(17));
                            detailsDTO.setSponsorBankIFSCMICRIIN(resultSet.getString(18));
                            detailsDTO.setUserNo(resultSet.getString(19));
                            detailsDTO.setTxnRef(resultSet.getString(20));
                            detailsDTO.setPdtType(resultSet.getString(21));
                            detailsDTO.setBeneficiaryArNo(resultSet.getString(22));
                            detailsDTO.setUMRN(resultSet.getString(23));
                            detailsDTO.setFiller(resultSet.getString(24));
                            listOfACHDetailsDTO.add(detailsDTO);
                        }
                        fileWrapperDTO.setAchFileDetailsList(listOfACHDetailsDTO);
                    }
                    fileWrapperDTO.setFileName(statement.getString(11));
                    responseDataDTO.setResult(fileWrapperDTO);
                    responseDataDTO.setMessage(resultMsg);
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondACHFileWrapperDTO fileWrapperDTO = new BondACHFileWrapperDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATH_SP_MANUAL_DOWNLOAD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getFromDate());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, statement.getString(index));
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(5);
                    if (resultSet != null) {
                        BondACHFileHeaderDTO headerDTO = new BondACHFileHeaderDTO();
                        while (resultSet.next()) {
                            headerDTO.setAchTransactionCode(resultSet.getString(1));
                            headerDTO.setControl(resultSet.getString(2));
                            headerDTO.setUserName(resultSet.getString(3));
                            headerDTO.setControl1(resultSet.getString(4));
                            headerDTO.setAchFileNumber(resultSet.getString(5));
                            headerDTO.setControl2(resultSet.getString(6));
                            headerDTO.setControl3(resultSet.getString(7));
                            headerDTO.setLedgerFolioNumber(resultSet.getString(8));
                            headerDTO.setUserDefLimit(resultSet.getString(9));
                            headerDTO.setTotalAmtInPaise(resultSet.getString(10));
                            headerDTO.setSettlementDate(resultSet.getString(11));
                            headerDTO.setReserved1(resultSet.getString(12));
                            headerDTO.setReserved2(resultSet.getString(13));
                            headerDTO.setFiller1(resultSet.getString(14));
                            headerDTO.setUserNumber(resultSet.getString(15));
                            headerDTO.setUserReference(resultSet.getString(16));
                            headerDTO.setSponsorBankIFSCMICRIIN(resultSet.getString(17));
                            headerDTO.setUserBankAccountNumber(resultSet.getString(18));
                            headerDTO.setTotalItems(resultSet.getString(19));
                            headerDTO.setSettlementCycle(resultSet.getString(20));
                            headerDTO.setFiller2(resultSet.getString(21));
                        }
                        fileWrapperDTO.setAchFileHeaderDTO(headerDTO);
                    }
                    resultSet = (ResultSet) statement.getObject(6);
                    if (resultSet != null) {
                        List<BondACHFileDetailsDTO> listOfACHDetailsDTO = new LinkedList<>();
                        while (resultSet.next()) {
                            BondACHFileDetailsDTO detailsDTO = new BondACHFileDetailsDTO();
                            detailsDTO.setAchTransactionCode(resultSet.getString(1));
                            detailsDTO.setControl(resultSet.getString(2));
                            detailsDTO.setDestinationAccountType(resultSet.getString(3));
                            detailsDTO.setLedgerFolioNumber(resultSet.getString(4));
                            detailsDTO.setControl1(resultSet.getString(5));
                            detailsDTO.setBeneficiaryAccHolderName(resultSet.getString(6));
                            detailsDTO.setControl2(resultSet.getString(7));
                            detailsDTO.setControl3(resultSet.getString(8));
                            detailsDTO.setUserName(resultSet.getString(9));
                            detailsDTO.setControl4(resultSet.getString(10));
                            detailsDTO.setAmt(resultSet.getString(11));
                            detailsDTO.setReserved1(resultSet.getString(12));
                            detailsDTO.setReserved2(resultSet.getString(13));
                            detailsDTO.setReserved3(resultSet.getString(14));
                            detailsDTO.setReserved4(resultSet.getString(15));
                            detailsDTO.setDestinationBankIFSCMICRIIN(resultSet.getString(16));
                            detailsDTO.setBeneficiaryBankAccNo(resultSet.getString(17));
                            detailsDTO.setSponsorBankIFSCMICRIIN(resultSet.getString(18));
                            detailsDTO.setUserNo(resultSet.getString(19));
                            detailsDTO.setTxnRef(resultSet.getString(20));
                            detailsDTO.setPdtType(resultSet.getString(21));
                            detailsDTO.setBeneficiaryArNo(resultSet.getString(22));
                            detailsDTO.setUMRN(resultSet.getString(23));
                            detailsDTO.setFiller(resultSet.getString(24));
                            listOfACHDetailsDTO.add(detailsDTO);
                        }
                        fileWrapperDTO.setAchFileDetailsList(listOfACHDetailsDTO);
                    }
                    fileWrapperDTO.setFileName(statement.getString(7));
                    responseDataDTO.setResult(fileWrapperDTO);
                    responseDataDTO.setMessage(resultMsg);
                }
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private Array generateArrayForVerifySubAccClosure(Connection connection, List<VerifyArrayObject> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PRE_CL_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PRE_CL_DTLS", connection);
        Struct[] struct = list != null && !list.isEmpty() ?
                list.stream().map(item -> {
                            try {
                                return new STRUCT(genericTableStruct, connection,
                                        new Object[]{item.getBlaNo(), item.getSubId(),
                                                item.getPanNo(), item.getPaymentDate()}
                                );
                            } catch (SQLException e) {
                                e.printStackTrace();
                                throw new IllegalArgumentException(ERROR_CREATION_TYPE, e);
                            }
                        }
                ).toArray(Struct[]::new) : new Struct[0];
        return new ARRAY(genericTableDescriptor, connection, struct);
    }


    private static ARRAY generateArrayForPanValid(Connection connection, List<RbiBondClosureOnMaturDeathNomiineeDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_ADD_DET_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_ADD_DET", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RbiBondClosureOnMaturDeathNomiineeDTO rbiBondClosureBeforeMaturDeathLegalHierDTO = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[3];
            rbiBondClosureBeforeMaturDeathLegalHierDTO = list.get(index);
            paramArray[0] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getPanNo();
            paramArray[1] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getNomineeName();
            paramArray[2] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getDateOfBirthOfMinor();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    @Override
    public ResponseDataDTO verifyTranResponse(TransactionReponseDto transactionReponseDto) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATHSP_VER_RES.getProcName());
                stmt.setString("p_verifier_id_in", UserAccountUtils.getCurrentUsername());
                stmt.setString("p_verifier_sol_id_in", UserAccountUtils.getCurrentSolId());
                stmt.setString("p_gbm_tran_id_in", transactionReponseDto.getGbmTranId());
                stmt.setString("p_cbs_tran_id_in", transactionReponseDto.getCbsTranId());
                stmt.setString("p_cbs_tran_stat_in", transactionReponseDto.getCbsTranStatus());
                stmt.setString("p_cbs_tran_statdesc_in",transactionReponseDto.getCbsTranStatusDesc() );
                stmt.registerOutParameter("p_gbm_tran_id_out", Types.VARCHAR);
                stmt.registerOutParameter("p_cbs_tran_id_out", Types.VARCHAR);
                stmt.registerOutParameter("p_cbs_tran_stat_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_desc_out", Types.VARCHAR);
                stmt.execute();

                responseDataDTO.setMessage(stmt.getString("p_msg_desc_out"));

            }
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;

    }

    private ResponseDataDTO officeAccountValidation(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO){


        String finacleValidationReqString = "TDS" + "^" + rbiBondClosureBeforeMaturDTO.getCredBondTdsAcc() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + rbiBondClosureBeforeMaturDTO.getCredBondTdsAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getCredBondTdsAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
        finacleValidationReqString += "!" + "SUS" + "^" + rbiBondClosureBeforeMaturDTO.getDebBondSusAcc() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + rbiBondClosureBeforeMaturDTO.getDebBondSusAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getDebBondSusAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
        finacleValidationReqString += "!" + "SUN" + "^" + rbiBondClosureBeforeMaturDTO.getDebBondSundryAcc() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + rbiBondClosureBeforeMaturDTO.getDebBondSundryAccFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getDebBondSundryAccFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
        finacleValidationReqString += "!" + "DDPO" + "^" + rbiBondClosureBeforeMaturDTO.getDdPoAccNum() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + rbiBondClosureBeforeMaturDTO.getDdPoAccNumFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getDdPoAccNumFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));


        ResponseDataDTO responseDataDTO = new ResponseDataDTO();


        ValidatorWrapperDTO validatorWrapperDTO = transactionValidator.validateBacidForAcidDetails(finacleValidationReqString);
        if (CommonConstants.SUCCESS.equalsIgnoreCase(validatorWrapperDTO.getStatus())) {
            List<TranDetailsDTO> transactionList = validatorWrapperDTO.getTranDetailsDTOList();
            for (TranDetailsDTO dto : transactionList) {
                if (CommonConstants.SUCCESS.equalsIgnoreCase(dto.getValidityStatus())) {
                    String[] accNo = dto.getAccountNumber().split("\\^");
                    if ("TDS".equalsIgnoreCase(accNo[0])) {
                        rbiBondClosureBeforeMaturDTO.setCredBondTdsAcc(accNo[1]);
                        rbiBondClosureBeforeMaturDTO.setCredBondTdsAccFlag(dto.getIsSysAcct());
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    }
                    else if ("SUS".equalsIgnoreCase(accNo[0])) {
                        rbiBondClosureBeforeMaturDTO.setDebBondSusAcc(accNo[1]);
                        rbiBondClosureBeforeMaturDTO.setDebBondSusAccFlag(dto.getIsSysAcct());
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    }
                    else if ("SUN".equalsIgnoreCase(accNo[0])) {
                        rbiBondClosureBeforeMaturDTO.setDebBondSundryAcc(accNo[1]);
                        rbiBondClosureBeforeMaturDTO.setDebBondSundryAccFlag(dto.getIsSysAcct());
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    }
                    else if ("DDPO".equalsIgnoreCase(accNo[0])) {
                        rbiBondClosureBeforeMaturDTO.setDdPoAccNum(accNo[1]);
                        rbiBondClosureBeforeMaturDTO.setDdPoAccNumFlag(dto.getIsSysAcct());
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    }
                    else {
                        responseDataDTO.setMessage("INVALID ACCOUNT NAME");
                    }
                } else {
                    responseDataDTO.setMessage(dto.getReason());
                }
            }
        } else {
            responseDataDTO.setMessage(validatorWrapperDTO.getReason());
        }
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATHSP_FILENAME.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getPaymentDate());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    responseDataDTO.setMessage(resultMsg);
                    responseDataDTO.setResult(statement.getString(5));
                    return responseDataDTO;
                }
            } else
                resultMsg = CONNECTION_EMPTY;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO, String
            schemeCode, String paymentDate) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATHSP_UPL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, schemeCode);
                statement.setString(index++, paymentDate);
                statement.setArray(index++, generateArrayForUploadPaymentFile(connection, bondInterestPaymentFileDTO.getFileDetailsDTO()));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, resultMsg);
            } else
                resultMsg = CONNECTION_EMPTY;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private Array generateArrayForUploadPaymentFile(Connection connection, List<BondInterestPaymentFileDetailsDTO> list) throws
            SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PMNT_RES_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PMNT_RES", connection); //
        Struct[] struct = list != null && !list.isEmpty() ?
                list.stream().map(
                        item -> {
                            try {
                                return new STRUCT(genericTableStruct, connection,
                                        new Object[]{
                                                item.getAchItemSeqNo(), item.getChecksum(),
                                                item.getFlagForSuccess(), item.getReasonCode(),
                                                item.getTransRef()
                                        }
                                );
                            } catch (SQLException e) {
                                e.printStackTrace();
                                throw new IllegalArgumentException(ERROR_CREATION_TYPE, e);
                            }
                        }
                ).toArray(Struct[]::new) : new Struct[0];
        return new ARRAY(genericTableDescriptor, connection, struct);
    }
    @Override
    public ResponseDataDTO processPaymentFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_AC_CL_ON_DEATHSP_VER_RES.getProcName());
                statement.setArray(index++, generateArrayForUploadPaymentFile(connection, bondInterestPaymentFileDTO.getFileDetailsDTO()));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, statement.getString(index));
                responseDataDTO.setMessage(statement.getString(index));
                return responseDataDTO;
            } else
                log.info(CONNECTION_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        return responseDataDTO;
    }

}
