package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.dao.RbiBondNomineeContinuationDao;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureOnMaturDeathDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureOnMaturDeathNomiineeDTO;
import com.isl.gbm.rbibond.dto.RbiBondNomineeContinuationDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Repository for handling RBI Bond Nominee Continuation operations.
 *
 * @author Sneha Sampath
 * @date 2025-02-05
 */
@Repository
public class RbiBondNomineeContinuationDaoImpl implements RbiBondNomineeContinuationDao {

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;
    private static final String ERROR_CREATION_TYPE = "Error creating STRUCT";

    private static final Logger log = LoggerFactory.getLogger(RbiBondNomineeContinuationDaoImpl.class);

    @Autowired
    TransactionValidator transactionValidator;

    private static final String RESULT_SET_EMPTY = "Result Set is Empty";
    private static final String CONNECTION_EMPTY = "Connection is Empty";



    @Override
    public ResponseDataDTO getAddDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
        RbiBondNomineeContinuationDTO rbiBondNomineeContinuationDTO = new RbiBondNomineeContinuationDTO();
        List<RbiBondNomineeContinuationDTO> rbiBondBLAList = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> rbiBondNomineeList = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> rbiBondBondSubsList = new ArrayList<>();



        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_NOMINEE_CONTINUATION_SP_ADD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getSchemeCode());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getFlag());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getFtsApplication());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getModeOfHolding());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getPanNum());

                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));

                resultMsg = stmt.getString(index);
                responseDataDTO.setMessage(resultMsg);


                if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {



                    if (ObjectUtils.isEmpty(rbiBondClosureBeforeMaturDTO.getFlag())) {

                        ResultSet rs = (ResultSet) stmt.getObject(10);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                            rbiBondClosureBeforeMaturDTO1.setSchemeCode(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setSchemeName(rs.getString(2));
                            rbiBondClosureBeforeMaturDTO1.setSchemeStatus(rs.getString(3));

                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }



                    else
                    if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("P")) {

                        ResultSet rs = (ResultSet) stmt.getObject(10);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setPanNum(rs.getString(2));

                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }






                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("B")) {

                        ResultSet rs = (ResultSet) stmt.getObject(10);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(2));
                            rbiBondClosureBeforeMaturDTO1.setPanNum(rs.getString(3));
                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }
                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("S")) {

                        ResultSet rs = (ResultSet) stmt.getObject(10);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(2));
                            rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString(3));
                            rbiBondClosureBeforeMaturDTO1.setSubscriptionAmount(rs.getString(4));
                            rbiBondClosureBeforeMaturDTO1.setValueDate(rs.getString(5));
                            rbiBondClosureBeforeMaturDTO1.setMaturityDate(rs.getString(6));
                            rbiBondBondSubsList.add(rbiBondClosureBeforeMaturDTO1);
                        }

                        rbiBondNomineeContinuationDTO.setSubsDetailsList(rbiBondBondSubsList);
                        ResultSet rs1 = (ResultSet) stmt.getObject(11);

                        if(rs1 != null) {
                            while (rs1.next()) {
                                rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                                rbiBondClosureBeforeMaturDTO1.setNomineeId(rs1.getString(1));
                                rbiBondClosureBeforeMaturDTO1.setNomineeName(rs1.getString(2));
                                rbiBondClosureBeforeMaturDTO1.setNomineeDob(rs1.getString(3));
                                rbiBondClosureBeforeMaturDTO1.setSharePercentage(rs1.getString(4));
                                rbiBondClosureBeforeMaturDTO1.setShareAmountSettled(rs1.getString(5));
                                rbiBondNomineeList.add(rbiBondClosureBeforeMaturDTO1);

                            }
                            rbiBondNomineeContinuationDTO.setNomineeDetList(rbiBondNomineeList);

                            responseDataDTO.setResult(rbiBondNomineeContinuationDTO);

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
    public ResponseDataDTO getAddValidation(RbiBondNomineeContinuationDTO paymentRequestDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        RbiBondNomineeContinuationDTO finalResponse =new RbiBondNomineeContinuationDTO();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<RbiBondNomineeContinuationDTO> listOfBLAs = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> listOfSub = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> listOfNominee = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_NOMINEE_CONTINUATION_SP_ADD_VAL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setString(index++, paymentRequestDTO.getModeOfHolding());
                statement.setArray(index++, generateArrayForPanValid(connection, paymentRequestDTO.getNomineeDetList()));
                statement.setString(index++, paymentRequestDTO.getBoSubsId());
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
                    resultSet = (ResultSet) statement.getObject(7);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondNomineeContinuationDTO bondTrailRunDTO = new RbiBondNomineeContinuationDTO();
                            bondTrailRunDTO.setNomineeId(resultSet.getString(1));
                            bondTrailRunDTO.setSchemeCode(resultSet.getString(2));
                            bondTrailRunDTO.setPanNum(resultSet.getString(3));
                            bondTrailRunDTO.setModeOfHolding(resultSet.getString(4));
                            bondTrailRunDTO.setNoOfJointAcc(resultSet.getString(5));
                            bondTrailRunDTO.setBlaNo(resultSet.getString(6));
                            bondTrailRunDTO.setHolderFirstName(resultSet.getString(7));
                            bondTrailRunDTO.setHolderMiddleName(resultSet.getString(8));
                            bondTrailRunDTO.setHolderLastName(resultSet.getString(9));
                            bondTrailRunDTO.setBondHolderDob(resultSet.getString(10));
                            bondTrailRunDTO.setCifId(resultSet.getString(11));
                            bondTrailRunDTO.setFtsApplication(resultSet.getString(12));
                            bondTrailRunDTO.setBondHolderType(resultSet.getString(13));
                            bondTrailRunDTO.setFatherName(resultSet.getString(14));
                            bondTrailRunDTO.setMotherName(resultSet.getString(15));
                            bondTrailRunDTO.setGender(resultSet.getString(16));
                            bondTrailRunDTO.setIsMinor(resultSet.getString(17));
                            bondTrailRunDTO.setDateOfBirthOfMinor(resultSet.getString(18));
                            bondTrailRunDTO.setBondHldAddOne(resultSet.getString(19));
                            bondTrailRunDTO.setBondHldAddTwo(resultSet.getString(20));
                            bondTrailRunDTO.setBondHldAddThree(resultSet.getString(21));
                            bondTrailRunDTO.setBondHldPinCode(resultSet.getString(22));
                            bondTrailRunDTO.setBondHldCity(resultSet.getString(23));
                            bondTrailRunDTO.setBondHldState(resultSet.getString(24));
                            bondTrailRunDTO.setBondHolderPhoneNumber(resultSet.getString(25));
                            bondTrailRunDTO.setBondHldMobNum(resultSet.getString(26));
                            bondTrailRunDTO.setBondHldEmail(resultSet.getString(27));
                            bondTrailRunDTO.setHufRegPowerOfAttorney(resultSet.getString(28));
                            bondTrailRunDTO.setPowerOfAttorneyKartaName(resultSet.getString(29));
                            bondTrailRunDTO.setWhetherCustomerBelongHdf(resultSet.getString(30));
                            bondTrailRunDTO.setOperativeAccountNumber(resultSet.getString(31));
                            bondTrailRunDTO.setIfscCode(resultSet.getString(32));
                            bondTrailRunDTO.setAadharCapture(resultSet.getString(33));
                            bondTrailRunDTO.setWitnessNameOne(resultSet.getString(34));
                            bondTrailRunDTO.setWitnessAddressOne(resultSet.getString(35));
                            bondTrailRunDTO.setWitnessNameTwo(resultSet.getString(36));
                            bondTrailRunDTO.setWitnessAddressTwo(resultSet.getString(37));
                            bondTrailRunDTO.setBirthCertificate(resultSet.getString(38));
                            bondTrailRunDTO.setPassport(resultSet.getString(39));
                            bondTrailRunDTO.setSeniorCitizenCard(resultSet.getString(40));
                            bondTrailRunDTO.setVoterIdCard(resultSet.getString(41));
                            bondTrailRunDTO.setPanCard(resultSet.getString(42));
                            bondTrailRunDTO.setDrivingLicence(resultSet.getString(43));
                            bondTrailRunDTO.setRationCard(resultSet.getString(44));
                            bondTrailRunDTO.setOtherDocuments(resultSet.getString(45));
                            bondTrailRunDTO.setOtherDetails(resultSet.getString(46));
                            bondTrailRunDTO.setRemarks(resultSet.getString(47));
                            bondTrailRunDTO.setBlaGroupId(resultSet.getString(48));
                            bondTrailRunDTO.setJointHolderType(resultSet.getString(49));
                            bondTrailRunDTO.setModeOfInterestPayment(resultSet.getString(50));
                            bondTrailRunDTO.setOperativeAccNo(resultSet.getString(51));
                            bondTrailRunDTO.setMicrCode(resultSet.getString(52));
                            bondTrailRunDTO.setBankCode(resultSet.getString(53));
                            bondTrailRunDTO.setBranchCode(resultSet.getString(54));
                            bondTrailRunDTO.setAccountType(resultSet.getString(55));
                            bondTrailRunDTO.setFlag(resultSet.getString(56));
                            bondTrailRunDTO.setBlaFlagCur(statement.getString(10));
                            listOfBLAs.add(bondTrailRunDTO);
                        }
                        finalResponse.setBlaDetailsList(listOfBLAs);
                    } else {
                        resultMsg = RESULT_SET_EMPTY;
                    }
                    resultSet = (ResultSet) statement.getObject(9);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondNomineeContinuationDTO bondSubs = new RbiBondNomineeContinuationDTO();
                            bondSubs.setNomineeId(resultSet.getString(1));
                            bondSubs.setSubscriptionAmount(resultSet.getString(2));
                            bondSubs.setBondType(resultSet.getString(3));
                            bondSubs.setAccountOpenDate(resultSet.getString(4));
                            bondSubs.setValueDate(resultSet.getString(5));
                            bondSubs.setPeriodOfDeposit(resultSet.getString(6));
                            bondSubs.setMaturityDate(resultSet.getString(7));
                            bondSubs.setRateOfInterest(resultSet.getString(8));
                            bondSubs.setMaturityAmt(resultSet.getString(9));
                            bondSubs.setWboBranchCode(resultSet.getString(10));
                            bondSubs.setWboBranchName(resultSet.getString(11));
                            bondSubs.setSegment(resultSet.getString(12));
                            bondSubs.setTranMode(resultSet.getString(13));
                            bondSubs.setInstrumentType(resultSet.getString(14));
                            bondSubs.setInstrumentNumber(resultSet.getString(15));
                            bondSubs.setInstrumentDate(resultSet.getString(16));
                            bondSubs.setInstrumentAmt(resultSet.getString(17));
                            bondSubs.setTranCode(resultSet.getString(18));
                            bondSubs.setZoneCode(resultSet.getString(19));
                            bondSubs.setZoneDate(resultSet.getString(20));
                            bondSubs.setDebitAccNumber(resultSet.getString(21));
                            listOfSub.add(bondSubs);
                        }
                        finalResponse.setSubsDetailsList(listOfSub);
                    } else {
                        resultMsg = RESULT_SET_EMPTY;
                    }
                    resultSet = (ResultSet) statement.getObject(8);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondNomineeContinuationDTO bondNom = new RbiBondNomineeContinuationDTO();
                            bondNom.setNomineeId(resultSet.getString(1));
                            bondNom.setBlaNo(resultSet.getString(2));
                            bondNom.setBlaGroupId(resultSet.getString(3));
                            bondNom.setJointHolderType(resultSet.getString(4));
                            bondNom.setSchemeCode(resultSet.getString(5));
                            bondNom.setGuardianId(resultSet.getString(6));
                            bondNom.setGuardFName(resultSet.getString(7));
                            bondNom.setGuardMName(resultSet.getString(8));
                            bondNom.setGuardLName(resultSet.getString(9));
                            bondNom.setRelationWithMinor(resultSet.getString(10));
                            bondNom.setGuardianDob(resultSet.getString(11));
                            bondNom.setGuardAddress1(resultSet.getString(12));
                            bondNom.setGuardMobileNo(resultSet.getString(13));
                            bondNom.setGuardEmailId(resultSet.getString(14));
                            bondNom.setGuardianDeathDate(resultSet.getString(15));
                            bondNom.setGuardianDeathRecieptCert(resultSet.getString(16));
                            bondNom.setGuardianCurr(resultSet.getString(17));
                            bondNom.setRemarks(resultSet.getString(18));
                            bondNom.setFetchGuardId(resultSet.getString(19));
                            listOfNominee.add(bondNom);
                        }
                        finalResponse.setGuardianDetailList(listOfNominee);
                    }
                    finalResponse.setBlaFlag(statement.getString(10));
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


    private static ARRAY generateArrayForPanValid(Connection connection, List<RbiBondNomineeContinuationDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_RBIBOND_NOM_CONT_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_RBIBOND_NOM_CONT", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDeathLegalHierDTO = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[4];
            rbiBondClosureBeforeMaturDeathLegalHierDTO = list.get(index);
            paramArray[0] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getNomineeId();
            paramArray[1] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getPanNum();
            paramArray[2] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getNomineeName();
            paramArray[3] = rbiBondClosureBeforeMaturDeathLegalHierDTO.getNomineeDob();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }



    @Override
    public ResponseDataDTO add(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO1) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondNomineeContinuationDTO dto=new RbiBondNomineeContinuationDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_NOMINEE_CONTINUATION_SP_ADD.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO1.getSchemeCode());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO1.getModeOfHolding());
                statement.setString(index++, String.valueOf(rbiBondClosureBeforeMaturDTO1.getBlaDetailsList() != null ? rbiBondClosureBeforeMaturDTO1.getBlaDetailsList().size() : 0));
                statement.setString(index++, rbiBondClosureBeforeMaturDTO1.getBlaNo());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO1.getBoSubsId());
                statement.setArray(index++, generateArrayForBla(connection, rbiBondClosureBeforeMaturDTO1.getBlaDetailsList()));
                statement.setArray(index++, generateArrayForSubsId(connection, rbiBondClosureBeforeMaturDTO1.getSubsDetailsList()));
                if (rbiBondClosureBeforeMaturDTO1.getNomineeDetList() != null) {
                    statement.setArray(index++, generateArrayOfNominee(connection, rbiBondClosureBeforeMaturDTO1.getNomineeDetList()));
                }else {
                    statement.setNull(index++, Types.ARRAY, "TYP_GBM_RBI_BOND_NOM_CONT_DTLS");
                }
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                dto.setBlaNo(statement.getString(11));
                dto.setBoSubsId(statement.getString(12));
                responseDataDTO.setResult(dto);
            } else
                resultMsg = CONNECTION_EMPTY;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
            responseDataDTO.setMessage(resultMsg);
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private static ARRAY generateArrayForBla(Connection connection, List<RbiBondNomineeContinuationDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_RBI_BOND_BLA_DTLS_NOM_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_RBI_BOND_BLA_DTLS_NOM", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDeathNomiineeDTO = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[62];
            rbiBondClosureBeforeMaturDeathNomiineeDTO = list.get(index);
            paramArray[0] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getNomineeId();
            paramArray[1] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBlaFlag();
            paramArray[2] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBlaNo();
            paramArray[3] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getPanNum();
            paramArray[4] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getHolderFirstName();
            paramArray[5] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getHolderMiddleName();
            paramArray[6] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getHolderLastName();
            paramArray[7] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHolderDob();
            paramArray[8] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getCifId();
            paramArray[9] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getFtsApplication();
            paramArray[10] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHolderType();
            paramArray[11] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getFatherName();
            paramArray[12] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getMotherName();
            paramArray[13] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGender();
            paramArray[14] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getIsMinor();
            paramArray[15] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getDateOfBirthOfMinor();
            paramArray[16] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHldAddOne();
            paramArray[17] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHldAddTwo();
            paramArray[18] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHldAddThree();
            paramArray[19] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHldPinCode();
            paramArray[20] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHldCity();
            paramArray[21] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHldState();
            paramArray[22] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHolderPhoneNumber();
            paramArray[23] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHldMobNum();
            paramArray[24] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHldEmail();
            paramArray[25] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getHufRegPowerOfAttorney();
            paramArray[26] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getPowerOfAttorneyKartaName();
            paramArray[27] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getWhetherCustomerBelongHdf();
            paramArray[28] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getOperativeAccountNumber();
            paramArray[29] = (rbiBondClosureBeforeMaturDeathNomiineeDTO.getCreditBankIfscCode() != null && !rbiBondClosureBeforeMaturDeathNomiineeDTO.getCreditBankIfscCode().trim().isEmpty()) ? rbiBondClosureBeforeMaturDeathNomiineeDTO.getCreditBankIfscCode() : rbiBondClosureBeforeMaturDeathNomiineeDTO.getIfscCode();;
            paramArray[30] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getAadharCapture();
            paramArray[31] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getWitnessNameOne();
            paramArray[32] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getWitnessAddressOne();
            paramArray[33] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getWitnessNameTwo();
            paramArray[34] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getWitnessAddressTwo();
            paramArray[35] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBirthCertificate();
            paramArray[36] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getPassport();
            paramArray[37] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getSeniorCitizenCard();
            paramArray[38] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getVoterIdCard();
            paramArray[39] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getPanCard();
            paramArray[40] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getDrivingLicence();
            paramArray[41] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getRationCard();
            paramArray[42] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getOtherDocuments();
            paramArray[43] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getOtherDetails();
            paramArray[44] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getRemarks();
            paramArray[45] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardianId();
            paramArray[46] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardFName();
            paramArray[47] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardMName();
            paramArray[48] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardLName();
            paramArray[49] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getRelationWithMinor();
            paramArray[50] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardianDob();
            paramArray[51] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardianCommunicationAddress();
            paramArray[52] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardMobileNo();
            paramArray[53] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardEmailId();
            paramArray[54] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getJointHolderType();
            paramArray[55] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getModeOfInterestPayment();
            paramArray[56] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBankCode();
            paramArray[57] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBranchCode();
            paramArray[58] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getNameOfThePayee();
            paramArray[59] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getOperativeAccNo();
            paramArray[60] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getMicrCode();
            paramArray[61] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getAccountType();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


    private static ARRAY generateArrayForSubsId(Connection connection, List<RbiBondNomineeContinuationDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_SUBS_DTLS_NOM_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_SUBS_DTLS_NOM", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDeathNomiineeDTO = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[25];
            rbiBondClosureBeforeMaturDeathNomiineeDTO = list.get(index);
            paramArray[0] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getNomineeId();
            paramArray[1] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getSchemeCode();
            paramArray[2] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBlaNo();
            paramArray[3] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondHolderName();
            paramArray[4] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getSubscriptionAmount();
            paramArray[5] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getBondType();
            paramArray[6] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getAccountOpenDate();
            paramArray[7] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getValueDate();
            paramArray[8] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getPeriodOfDeposit();
            paramArray[9] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getMaturityDate();
            paramArray[10] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getRateOfInterest();
            paramArray[11] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getMaturityAmt();
            paramArray[12] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getWboBranchCode();
            paramArray[13] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getWboBranchName();
            paramArray[14] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getSegment();
            paramArray[15] = (rbiBondClosureBeforeMaturDeathNomiineeDTO.getIsNominee() == null
                    || rbiBondClosureBeforeMaturDeathNomiineeDTO.getIsNominee().trim().isEmpty())
                    ? "N"
                    : rbiBondClosureBeforeMaturDeathNomiineeDTO.getIsNominee();
            paramArray[16] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getTranMode();
            paramArray[17] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getInstrumentType();
            paramArray[18] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getInstrumentNumber();
            paramArray[19] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getInstrumentDate();
            paramArray[20] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getInstrumentAmt();
            paramArray[21] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getTranCode();
            paramArray[22] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getZoneCode();
            paramArray[23] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getZoneDate();
            paramArray[24] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getDebitAccNumber();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private static ARRAY generateArrayOfNominee(Connection connection, List<RbiBondNomineeContinuationDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_CONT_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_CONT_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDeathNomiineeDTO = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[26];
            rbiBondClosureBeforeMaturDeathNomiineeDTO = list.get(index);
            paramArray[0] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getNomineeId();
            paramArray[1] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getSchemeCode();
            paramArray[2] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getNomineeFName();
            paramArray[3] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getNomineeMName();
            paramArray[4] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getNomineeLName();
            paramArray[5] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getAddr1();
            paramArray[6] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getAddr2();
            paramArray[7] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getCity();
            paramArray[8] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getState();
            paramArray[9] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getPinCode();
            paramArray[10] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getPhoneNum();
            paramArray[11] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getSharePercentage();
            paramArray[12] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getOperativeAccountNumber();
            paramArray[13] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getIsNomineeMinor();
            paramArray[14] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getRelationWithMinor();
            paramArray[15] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getMinorDob();
            paramArray[16] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardFName();
            paramArray[17] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardMName();
            paramArray[18] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardLName();
            paramArray[19] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardAddress1();
            paramArray[20] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardAddress2();
            paramArray[21] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardCity();
            paramArray[22] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardState();
            paramArray[23] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardPinCode();
            paramArray[24] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardMobileNo();
            paramArray[25] = rbiBondClosureBeforeMaturDeathNomiineeDTO.getGuardEmailId();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    @Override
    public ResponseDataDTO getInqDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondNomineeContinuationDTO finalResponse =new RbiBondNomineeContinuationDTO();
        RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
        List<RbiBondNomineeContinuationDTO> rbiBondBLAList = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> listOfBLAs = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> listOfBLAGuards = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> listOfSub = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> rbiBondBondSubsList = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> listOfNominee = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_NOMINEE_CONTINUATION_SP_INQ.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getSchemeCode());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getFlag());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getPanNum());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                statement.setArray(index++, generateArrayForBlaSubs(connection, rbiBondClosureBeforeMaturDTO.getBlaDetSingle()));
                statement.setArray(index++, generateArrayForBlaSubs(connection, rbiBondClosureBeforeMaturDTO.getSubsDetSingle()));
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
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


                    if (ObjectUtils.isEmpty(rbiBondClosureBeforeMaturDTO.getFlag())) {

                        ResultSet rs = (ResultSet) statement.getObject(14);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                            rbiBondClosureBeforeMaturDTO1.setSchemeCode(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setSchemeName(rs.getString(2));
                            rbiBondClosureBeforeMaturDTO1.setSchemeStatus(rs.getString(3));

                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }



                    else
                    if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("P")) {

                        ResultSet rs = (ResultSet) statement.getObject(14);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setPanNum(rs.getString(2));

                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }






                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("B")) {

                        ResultSet rs = (ResultSet) statement.getObject(14);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(2));
                            rbiBondClosureBeforeMaturDTO1.setPanNum(rs.getString(3));
                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }
                    else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("S")) {

                        ResultSet rs = (ResultSet) statement.getObject(14);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                            rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(2));
                            rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString(3));
                            rbiBondClosureBeforeMaturDTO1.setSubscriptionAmount(rs.getString(4));
                             rbiBondBondSubsList.add(rbiBondClosureBeforeMaturDTO1);
                        }

                        responseDataDTO.setResult(rbiBondBondSubsList);



                    }else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("N")) {

                        ResultSet rs = (ResultSet) statement.getObject(14);

                        while (rs.next()) {
                            rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                            rbiBondClosureBeforeMaturDTO1.setNomineeBlaNo(rs.getString(1));
                            rbiBondClosureBeforeMaturDTO1.setNomineeBoSubsId(rs.getString(2));
                            rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                        }
                        responseDataDTO.setResult(rbiBondBLAList);

                    }

                    else if (rbiBondClosureBeforeMaturDTO.getSchemeCode() != null && "D".equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getFlag())) {
                        resultSet = (ResultSet) statement.getObject(10);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                RbiBondNomineeContinuationDTO bondTrailRunDTO = new RbiBondNomineeContinuationDTO();
                                bondTrailRunDTO.setBlaNo(resultSet.getString(1));
                                bondTrailRunDTO.setHolderFirstName(resultSet.getString(2));
                                bondTrailRunDTO.setHolderMiddleName(resultSet.getString(3));
                                bondTrailRunDTO.setHolderLastName(resultSet.getString(4));
                                bondTrailRunDTO.setBondHolderDob(resultSet.getString(5));
                                bondTrailRunDTO.setBondHolderType(resultSet.getString(6));
                                bondTrailRunDTO.setFatherName(resultSet.getString(7));
                                bondTrailRunDTO.setMotherName(resultSet.getString(8));
                                bondTrailRunDTO.setGender(resultSet.getString(9));
                                bondTrailRunDTO.setIsMinor(resultSet.getString(10));
                                bondTrailRunDTO.setDateOfBirthOfMinor(resultSet.getString(11));
                                bondTrailRunDTO.setBondHldAddOne(resultSet.getString(12));
                                bondTrailRunDTO.setBondHldAddTwo(resultSet.getString(13));
                                bondTrailRunDTO.setBondHldAddThree(resultSet.getString(14));
                                bondTrailRunDTO.setBondHldPinCode(resultSet.getString(15));
                                bondTrailRunDTO.setBondHldCity(resultSet.getString(16));
                                bondTrailRunDTO.setBondHldState(resultSet.getString(17));
                                bondTrailRunDTO.setBondHolderPhoneNumber(resultSet.getString(18));
                                bondTrailRunDTO.setBondHldMobNum(resultSet.getString(19));
                                bondTrailRunDTO.setBondHldEmail(resultSet.getString(20));
                                bondTrailRunDTO.setHufRegPowerOfAttorney(resultSet.getString(21));
                                bondTrailRunDTO.setPowerOfAttorneyKartaName(resultSet.getString(22));
                                bondTrailRunDTO.setWhetherCustomerBelongHdf(resultSet.getString(23));
                                bondTrailRunDTO.setOperativeAccountNumber(resultSet.getString(24));
                                bondTrailRunDTO.setIfscCode(resultSet.getString(25));
                                bondTrailRunDTO.setAadharCapture(resultSet.getString(26));
                                bondTrailRunDTO.setWitnessNameOne(resultSet.getString(27));
                                bondTrailRunDTO.setWitnessAddressOne(resultSet.getString(28));
                                bondTrailRunDTO.setWitnessNameTwo(resultSet.getString(29));
                                bondTrailRunDTO.setWitnessAddressTwo(resultSet.getString(30));
                                bondTrailRunDTO.setBirthCertificate(resultSet.getString(31));
                                bondTrailRunDTO.setPassport(resultSet.getString(32));
                                bondTrailRunDTO.setSeniorCitizenCard(resultSet.getString(33));
                                bondTrailRunDTO.setVoterIdCard(resultSet.getString(34));
                                bondTrailRunDTO.setPanCard(resultSet.getString(35));
                                bondTrailRunDTO.setDrivingLicence(resultSet.getString(36));
                                bondTrailRunDTO.setRationCard(resultSet.getString(37));
                                bondTrailRunDTO.setOtherDocuments(resultSet.getString(38));
                                bondTrailRunDTO.setOtherDetails(resultSet.getString(39));
                                bondTrailRunDTO.setRemarks(resultSet.getString(40));
                                bondTrailRunDTO.setModeOfInterestPayment(resultSet.getString(41));
                                bondTrailRunDTO.setBankCode(resultSet.getString(42));
                                bondTrailRunDTO.setBranchCode(resultSet.getString(43));
                                bondTrailRunDTO.setNameOfThePayee(resultSet.getString(44));
                                bondTrailRunDTO.setOperativeAccNo(resultSet.getString(45));
                                bondTrailRunDTO.setMicrCode(resultSet.getString(46));
                                bondTrailRunDTO.setAccountType(resultSet.getString(47));
                                bondTrailRunDTO.setPanNum(resultSet.getString(48));


                                listOfBLAs.add(bondTrailRunDTO);
                            }

                            finalResponse.setBlaDetailsList(listOfBLAs);

                        } else {
                            resultMsg = RESULT_SET_EMPTY;
                        }
                        resultSet = (ResultSet) statement.getObject(11);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                RbiBondNomineeContinuationDTO bondTrailRunDTO = new RbiBondNomineeContinuationDTO();
                                bondTrailRunDTO.setBlaNo(resultSet.getString(1));
                                bondTrailRunDTO.setBlaGroupId(resultSet.getString(2));
                                bondTrailRunDTO.setJointHolderType(resultSet.getString(3));
                                bondTrailRunDTO.setSchemeCode(resultSet.getString(4));
                                bondTrailRunDTO.setGuardianId(resultSet.getString(5));
                                bondTrailRunDTO.setGuardFName(resultSet.getString(6));
                                bondTrailRunDTO.setGuardMName(resultSet.getString(7));
                                bondTrailRunDTO.setGuardLName(resultSet.getString(8));
                                bondTrailRunDTO.setRelationWithMinor(resultSet.getString(9));
                                bondTrailRunDTO.setGuardianDob(resultSet.getString(10));
                                bondTrailRunDTO.setGuardianCommunicationAddress(resultSet.getString(11));
                                bondTrailRunDTO.setGuardMobileNo(resultSet.getString(12));
                                bondTrailRunDTO.setGuardEmailId(resultSet.getString(13));
                                bondTrailRunDTO.setCreatedBy(resultSet.getString(14));
                                bondTrailRunDTO.setCreatedDate(resultSet.getString(15));
                                bondTrailRunDTO.setFetchGuardId(resultSet.getString(16));


                                listOfBLAGuards.add(bondTrailRunDTO);
                            }

                            finalResponse.setBlaGuardianDetailList(listOfBLAGuards);

                        } else {
                            resultMsg = RESULT_SET_EMPTY;
                        }
                        resultSet = (ResultSet) statement.getObject(12);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                RbiBondNomineeContinuationDTO bondSubs = new RbiBondNomineeContinuationDTO();

                                bondSubs.setBlaNo(resultSet.getString(1));
                                bondSubs.setBondHolderName(resultSet.getString(2));
                                bondSubs.setSubscriptionAmount(resultSet.getString(3));
                                bondSubs.setBondType(resultSet.getString(4));
                                bondSubs.setBoSubsId(resultSet.getString(5));
                                bondSubs.setAccountOpenDate(resultSet.getString(6));
                                bondSubs.setValueDate(resultSet.getString(7));
                                bondSubs.setPeriodOfDeposit(resultSet.getString(8));
                                bondSubs.setMaturityDate(resultSet.getString(9));
                                bondSubs.setRateOfInterest(resultSet.getString(10));
                                bondSubs.setMaturityAmt(resultSet.getString(11));
                                bondSubs.setTranMode(resultSet.getString(12));
                                bondSubs.setCreatedBy(resultSet.getString(13));
                                bondSubs.setCreatedDate(resultSet.getString(14));
                                bondSubs.setIsNominee(resultSet.getString(15));
                                bondSubs.setWboBranchCode(resultSet.getString(16));
                                bondSubs.setWboBranchName(resultSet.getString(17));
                                bondSubs.setSegment(resultSet.getString(18));
                                listOfSub.add(bondSubs);
                            }
                            finalResponse.setSubsDetailsList(listOfSub);

                        } else {
                            resultMsg = RESULT_SET_EMPTY;
                        }
                        resultSet = (ResultSet) statement.getObject(13);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                RbiBondNomineeContinuationDTO bondNom = new RbiBondNomineeContinuationDTO();
                                bondNom.setNomineeId(resultSet.getString(1));
                                bondNom.setNomineeFName(resultSet.getString(2));
                                bondNom.setNomineeMName(resultSet.getString(3));
                                bondNom.setNomineeLName(resultSet.getString(4));
                                bondNom.setAddr1(resultSet.getString(5));
                                bondNom.setAddr2(resultSet.getString(6));
                                bondNom.setCity(resultSet.getString(7));
                                bondNom.setState(resultSet.getString(8));
                                bondNom.setPinCode(resultSet.getString(9));
                                bondNom.setPhoneNo(resultSet.getString(10));
                                bondNom.setSharePercentage(resultSet.getString(11));
                                bondNom.setOperativeAccNo(resultSet.getString(12));
                                bondNom.setIsNomineeMinor(resultSet.getString(13));
                                bondNom.setRelationWithHolder(resultSet.getString(14));
                                bondNom.setDateOfBirthOfMinor(resultSet.getString(15));
                                bondNom.setGuardFName(resultSet.getString(16));
                                bondNom.setGuardMName(resultSet.getString(17));
                                bondNom.setGuardLName(resultSet.getString(18));
                                bondNom.setGuardAddress1(resultSet.getString(19));
                                bondNom.setGuardAddress2(resultSet.getString(20));
                                bondNom.setGuardCity(resultSet.getString(21));
                                bondNom.setGuardState(resultSet.getString(22));
                                bondNom.setGuardPinCode(resultSet.getString(23));
                                bondNom.setGuardMobileNo(resultSet.getString(24));
                                bondNom.setGuardEmailId(resultSet.getString(25));

                                listOfNominee.add(bondNom);

                            }
                            finalResponse.setNomineeDetList(listOfNominee);
                        }
//                        finalResponse.setDdPayAccountNumber(null);
                        finalResponse.setCreatedBy(statement.getString(15));
                        finalResponse.setCreatedDate(statement.getString(16));
                        finalResponse.setVerifiedBy(statement.getString(19));
                        finalResponse.setVerifiedDate(statement.getString(20));
                        finalResponse.setVerifiedStatus(statement.getString(22));
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

    private Array generateArrayForBlaSubs(Connection connection, List<String> list) throws
            SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);
        Struct[] struct = list != null && !list.isEmpty() ?
                list.stream().map(
                        item -> {
                            try {
                                return new STRUCT(genericTableStruct, connection, new Object[]{item});
                            } catch (SQLException e) {
                                e.printStackTrace();
                                throw new IllegalArgumentException(ERROR_CREATION_TYPE, e);
                            }
                        }
                ).toArray(Struct[]::new) : new Struct[0];
        return new ARRAY(genericTableDescriptor, connection, struct);
    }
    @Override
    public ResponseDataDTO getWboBranchDetails(RbiBondNomineeContinuationDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareStatement("SELECT sol_id AS branch_code_wbo,sol_desc  AS branch_name_wbo FROM gbm_bank_branches WHERE \n" +
                        "del_flg='0' AND ver_flg='1' AND status ='1' AND auditable ='1'");
                resultSet = stmt.executeQuery();
                List<HashMap<String, String>> resultList = new ArrayList<>();
                if (resultSet != null)
                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("wboBranchCode",resultSet.getString(1));
                        resultMap.put("wboBranchName", resultSet.getString(2));
                        resultList.add(resultMap);
                    }
                responseDataDTO.setResult(resultList);
                resultMsg = CommonConstants.SUCCESS_CONST;
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
        ResultSet resultSet = null;
        List<RbiBondNomineeContinuationDTO> rbiBondBLAList = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> rbiBondBondSubsList = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> listOfBLAGuards = new ArrayList<>();
        RbiBondNomineeContinuationDTO finalResponse =new RbiBondNomineeContinuationDTO();
        List<RbiBondNomineeContinuationDTO> listOfBLAs = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> listOfSub = new ArrayList<>();
        List<RbiBondNomineeContinuationDTO> listOfNominee = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_NOMINEE_CONTINUATION_SP_VER_DEL_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getSchemeCode());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getFlag());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getPanNum());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                stmt.setArray(index++, generateArrayForBlaSubs(connection, rbiBondClosureBeforeMaturDTO.getBlaDetSingle()));
                stmt.setArray(index++, generateArrayForBlaSubs(connection, rbiBondClosureBeforeMaturDTO.getSubsDetSingle()));
                stmt.registerOutParameter(index++, OracleTypes.CURSOR); //cursorval out
                stmt.registerOutParameter(index++, OracleTypes.CURSOR); //cursorval out
                stmt.registerOutParameter(index++, OracleTypes.CURSOR); //cursorval out
                stmt.registerOutParameter(index++, OracleTypes.CURSOR); //nominee details
                stmt.registerOutParameter(index++, OracleTypes.CURSOR); //nominee details
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
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


                if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) { if (ObjectUtils.isEmpty(rbiBondClosureBeforeMaturDTO.getFlag())) {

                    ResultSet rs = (ResultSet) stmt.getObject(14);

                    while (rs.next()) {
                        rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                        rbiBondClosureBeforeMaturDTO1.setSchemeCode(rs.getString(1));
                        rbiBondClosureBeforeMaturDTO1.setSchemeName(rs.getString(2));
                        rbiBondClosureBeforeMaturDTO1.setSchemeStatus(rs.getString(3));

                        rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                    }
                    responseDataDTO.setResult(rbiBondBLAList);

                }



                else
                if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("P")) {

                    ResultSet rs = (ResultSet) stmt.getObject(14);

                    while (rs.next()) {
                        rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                        rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(1));
                        rbiBondClosureBeforeMaturDTO1.setPanNum(rs.getString(2));

                        rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                    }
                    responseDataDTO.setResult(rbiBondBLAList);

                }






                else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("B")) {

                    ResultSet rs = (ResultSet) stmt.getObject(14);

                    while (rs.next()) {
                        rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                        rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString(1));
                        rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(2));
                        rbiBondClosureBeforeMaturDTO1.setPanNum(rs.getString(3));
                        rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                    }
                    responseDataDTO.setResult(rbiBondBLAList);

                }
                else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("S")) {

                    ResultSet rs = (ResultSet) stmt.getObject(14);

                    while (rs.next()) {
                        rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                        rbiBondClosureBeforeMaturDTO1.setBlaNo(rs.getString(1));
                        rbiBondClosureBeforeMaturDTO1.setBondHolderName(rs.getString(2));
                        rbiBondClosureBeforeMaturDTO1.setBoSubsId(rs.getString(3));
                        rbiBondClosureBeforeMaturDTO1.setSubscriptionAmount(rs.getString(4));
                        rbiBondBondSubsList.add(rbiBondClosureBeforeMaturDTO1);
                    }

                    responseDataDTO.setResult(rbiBondBondSubsList);



                }else if (rbiBondClosureBeforeMaturDTO.getFlag().equalsIgnoreCase("N")) {

                    ResultSet rs = (ResultSet) stmt.getObject(14);

                    while (rs.next()) {
                        rbiBondClosureBeforeMaturDTO1 = new RbiBondNomineeContinuationDTO();
                        rbiBondClosureBeforeMaturDTO1.setNomineeBlaNo(rs.getString(1));
                        rbiBondClosureBeforeMaturDTO1.setNomineeBoSubsId(rs.getString(2));
                        rbiBondBLAList.add(rbiBondClosureBeforeMaturDTO1);
                    }
                    responseDataDTO.setResult(rbiBondBLAList);

                }

                else if (rbiBondClosureBeforeMaturDTO.getSchemeCode() != null && "D".equalsIgnoreCase(rbiBondClosureBeforeMaturDTO.getFlag())) {
                    resultSet = (ResultSet) stmt.getObject(10);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondNomineeContinuationDTO bondTrailRunDTO = new RbiBondNomineeContinuationDTO();
                            bondTrailRunDTO.setBlaNo(resultSet.getString(1));
                            bondTrailRunDTO.setHolderFirstName(resultSet.getString(2));
                            bondTrailRunDTO.setHolderMiddleName(resultSet.getString(3));
                            bondTrailRunDTO.setHolderLastName(resultSet.getString(4));
                            bondTrailRunDTO.setBondHolderDob(resultSet.getString(5));
                            bondTrailRunDTO.setBondHolderType(resultSet.getString(6));
                            bondTrailRunDTO.setFatherName(resultSet.getString(7));
                            bondTrailRunDTO.setMotherName(resultSet.getString(8));
                            bondTrailRunDTO.setGender(resultSet.getString(9));
                            bondTrailRunDTO.setIsMinor(resultSet.getString(10));
                            bondTrailRunDTO.setDateOfBirthOfMinor(resultSet.getString(11));
                            bondTrailRunDTO.setBondHldAddOne(resultSet.getString(12));
                            bondTrailRunDTO.setBondHldAddTwo(resultSet.getString(13));
                            bondTrailRunDTO.setBondHldAddThree(resultSet.getString(14));
                            bondTrailRunDTO.setBondHldPinCode(resultSet.getString(15));
                            bondTrailRunDTO.setBondHldCity(resultSet.getString(16));
                            bondTrailRunDTO.setBondHldState(resultSet.getString(17));
                            bondTrailRunDTO.setBondHolderPhoneNumber(resultSet.getString(18));
                            bondTrailRunDTO.setBondHldMobNum(resultSet.getString(19));
                            bondTrailRunDTO.setBondHldEmail(resultSet.getString(20));
                            bondTrailRunDTO.setHufRegPowerOfAttorney(resultSet.getString(21));
                            bondTrailRunDTO.setPowerOfAttorneyKartaName(resultSet.getString(22));
                            bondTrailRunDTO.setWhetherCustomerBelongHdf(resultSet.getString(23));
                            bondTrailRunDTO.setOperativeAccountNumber(resultSet.getString(24));
                            bondTrailRunDTO.setIfscCode(resultSet.getString(25));
                            bondTrailRunDTO.setAadharCapture(resultSet.getString(26));
                            bondTrailRunDTO.setWitnessNameOne(resultSet.getString(27));
                            bondTrailRunDTO.setWitnessAddressOne(resultSet.getString(28));
                            bondTrailRunDTO.setWitnessNameTwo(resultSet.getString(29));
                            bondTrailRunDTO.setWitnessAddressTwo(resultSet.getString(30));
                            bondTrailRunDTO.setBirthCertificate(resultSet.getString(31));
                            bondTrailRunDTO.setPassport(resultSet.getString(32));
                            bondTrailRunDTO.setSeniorCitizenCard(resultSet.getString(33));
                            bondTrailRunDTO.setVoterIdCard(resultSet.getString(34));
                            bondTrailRunDTO.setPanCard(resultSet.getString(35));
                            bondTrailRunDTO.setDrivingLicence(resultSet.getString(36));
                            bondTrailRunDTO.setRationCard(resultSet.getString(37));
                            bondTrailRunDTO.setOtherDocuments(resultSet.getString(38));
                            bondTrailRunDTO.setOtherDetails(resultSet.getString(39));
                            bondTrailRunDTO.setRemarks(resultSet.getString(40));
                            bondTrailRunDTO.setModeOfInterestPayment(resultSet.getString(41));
                            bondTrailRunDTO.setBankCode(resultSet.getString(42));
                            bondTrailRunDTO.setBranchCode(resultSet.getString(43));
                            bondTrailRunDTO.setNameOfThePayee(resultSet.getString(44));
                            bondTrailRunDTO.setOperativeAccNo(resultSet.getString(45));
                            bondTrailRunDTO.setMicrCode(resultSet.getString(46));
                            bondTrailRunDTO.setAccountType(resultSet.getString(47));
                            bondTrailRunDTO.setPanNum(resultSet.getString(48));


                            listOfBLAs.add(bondTrailRunDTO);
                        }

                        finalResponse.setBlaDetailsList(listOfBLAs);

                    } else {
                        resultMsg = RESULT_SET_EMPTY;
                    }
                    resultSet = (ResultSet) stmt.getObject(11);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondNomineeContinuationDTO bondTrailRunDTO = new RbiBondNomineeContinuationDTO();
                            bondTrailRunDTO.setBlaNo(resultSet.getString(1));
                            bondTrailRunDTO.setBlaGroupId(resultSet.getString(2));
                            bondTrailRunDTO.setJointHolderType(resultSet.getString(3));
                            bondTrailRunDTO.setSchemeCode(resultSet.getString(4));
                            bondTrailRunDTO.setGuardianId(resultSet.getString(5));
                            bondTrailRunDTO.setGuardFName(resultSet.getString(6));
                            bondTrailRunDTO.setGuardMName(resultSet.getString(7));
                            bondTrailRunDTO.setGuardLName(resultSet.getString(8));
                            bondTrailRunDTO.setRelationWithMinor(resultSet.getString(9));
                            bondTrailRunDTO.setGuardianDob(resultSet.getString(10));
                            bondTrailRunDTO.setGuardianCommunicationAddress(resultSet.getString(11));
                            bondTrailRunDTO.setGuardMobileNo(resultSet.getString(12));
                            bondTrailRunDTO.setGuardEmailId(resultSet.getString(13));
                            bondTrailRunDTO.setCreatedBy(resultSet.getString(14));
                            bondTrailRunDTO.setCreatedDate(resultSet.getString(15));
                            bondTrailRunDTO.setFetchGuardId(resultSet.getString(16));


                            listOfBLAGuards.add(bondTrailRunDTO);
                        }

                        finalResponse.setBlaGuardianDetailList(listOfBLAGuards);

                    } else {
                        resultMsg = RESULT_SET_EMPTY;
                    }
                    resultSet = (ResultSet) stmt.getObject(12);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondNomineeContinuationDTO bondSubs = new RbiBondNomineeContinuationDTO();

                            bondSubs.setBlaNo(resultSet.getString(1));
                            bondSubs.setBondHolderName(resultSet.getString(2));
                            bondSubs.setSubscriptionAmount(resultSet.getString(3));
                            bondSubs.setBondType(resultSet.getString(4));
                            bondSubs.setBoSubsId(resultSet.getString(5));
                            bondSubs.setAccountOpenDate(resultSet.getString(6));
                            bondSubs.setValueDate(resultSet.getString(7));
                            bondSubs.setPeriodOfDeposit(resultSet.getString(8));
                            bondSubs.setMaturityDate(resultSet.getString(9));
                            bondSubs.setRateOfInterest(resultSet.getString(10));
                            bondSubs.setMaturityAmt(resultSet.getString(11));
                            bondSubs.setTranMode(resultSet.getString(12));
                            bondSubs.setCreatedBy(resultSet.getString(13));
                            bondSubs.setCreatedDate(resultSet.getString(14));
                            bondSubs.setIsNominee(resultSet.getString(15));
                            bondSubs.setWboBranchCode(resultSet.getString(16));
                            bondSubs.setWboBranchName(resultSet.getString(17));
                            bondSubs.setSegment(resultSet.getString(18));
                            listOfSub.add(bondSubs);
                        }
                        finalResponse.setSubsDetailsList(listOfSub);

                    } else {
                        resultMsg = RESULT_SET_EMPTY;
                    }
                    resultSet = (ResultSet) stmt.getObject(13);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            RbiBondNomineeContinuationDTO bondNom = new RbiBondNomineeContinuationDTO();
                            bondNom.setNomineeId(resultSet.getString(1));
                            bondNom.setNomineeFName(resultSet.getString(2));
                            bondNom.setNomineeMName(resultSet.getString(3));
                            bondNom.setNomineeLName(resultSet.getString(4));
                            bondNom.setAddr1(resultSet.getString(5));
                            bondNom.setAddr2(resultSet.getString(6));
                            bondNom.setCity(resultSet.getString(7));
                            bondNom.setState(resultSet.getString(8));
                            bondNom.setPinCode(resultSet.getString(9));
                            bondNom.setPhoneNo(resultSet.getString(10));
                            bondNom.setSharePercentage(resultSet.getString(11));
                            bondNom.setOperativeAccNo(resultSet.getString(12));
                            bondNom.setIsNomineeMinor(resultSet.getString(13));
                            bondNom.setRelationWithHolder(resultSet.getString(14));
                            bondNom.setDateOfBirthOfMinor(resultSet.getString(15));
                            bondNom.setGuardFName(resultSet.getString(16));
                            bondNom.setGuardMName(resultSet.getString(17));
                            bondNom.setGuardLName(resultSet.getString(18));
                            bondNom.setGuardAddress1(resultSet.getString(19));
                            bondNom.setGuardAddress2(resultSet.getString(20));
                            bondNom.setGuardCity(resultSet.getString(21));
                            bondNom.setGuardState(resultSet.getString(22));
                            bondNom.setGuardPinCode(resultSet.getString(23));
                            bondNom.setGuardMobileNo(resultSet.getString(24));
                            bondNom.setGuardEmailId(resultSet.getString(25));

                            listOfNominee.add(bondNom);

                        }
                        finalResponse.setNomineeDetList(listOfNominee);
                    }
//                        finalResponse.setDdPayAccountNumber(null);
                        finalResponse.setCreatedBy(stmt.getString(15));
                        finalResponse.setCreatedDate(stmt.getString(16));
                        finalResponse.setVerifiedBy(stmt.getString(19));
                        finalResponse.setVerifiedDate(stmt.getString(20));
                    finalResponse.setVerifiedStatus(stmt.getString(22));
                    finalResponse.setRemarks(stmt.getString(23));
                    responseDataDTO.setResult(finalResponse);
                    responseDataDTO.setMessage(resultMsg);
                    return responseDataDTO;
                }
                }
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
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
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_NOMINEE_CONTINUATION_SP_PAN_TRIGGER.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, paymentRequestDTO.getSchemeCode());
                statement.setArray(index++, generateArrayForPanValidManual(connection, paymentRequestDTO.getPanNumList()));
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

    private static ARRAY generateArrayForPanValidManual(Connection connection, List<RbiBondClosureOnMaturDeathNomiineeDTO> list) throws Exception {
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
    public ResponseDataDTO updatePANStatus(PanValidationRetriggerResponseDTO responseDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_NOMINEE_CONTINUATION_SP_TRIGGER_RES.getProcName());
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
    public ResponseDataDTO verify(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_NOMINEE_CONTINUATION_SP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getSchemeCode());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBlaNo());
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                stmt.setArray(index++, generateArrayForBlaSubs(connection, rbiBondClosureBeforeMaturDTO.getBlaDetSingle()));
                stmt.setArray(index++, generateArrayForBlaSubs(connection, rbiBondClosureBeforeMaturDTO.getSubsDetSingle()));
                stmt.setString(index++, rbiBondClosureBeforeMaturDTO.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                resultMsg = stmt.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);


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
    public ResponseDataDTO delete(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
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
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_NOMINEE_CONTINUATION_SP_DEL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getSchemeCode());
                statement.setString(index++,  rbiBondClosureBeforeMaturDTO.getBlaNo());
                statement.setString(index++, rbiBondClosureBeforeMaturDTO.getBoSubsId());
                statement.setArray(index++, generateArrayForBlaSubs(connection, rbiBondClosureBeforeMaturDTO.getBlaDetSingle()));
                statement.setArray(index++, generateArrayForBlaSubs(connection, rbiBondClosureBeforeMaturDTO.getSubsDetSingle()));
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

}


