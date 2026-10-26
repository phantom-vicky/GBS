package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TransactionReponseDto;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dao.RbiBondTransferInDao;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
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

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
public class RbiBondTransferInDaoImpl implements RbiBondTransferInDao {

    private static final Logger log = LoggerFactory.getLogger(RbiBondTransferInDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO getblaNoInq(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaFlag());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(bondLedgerAccountMaintenanceDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(9);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("blaNo", resultSet.getString(1));
                                temp.put("bondHolderName", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));
                                temp.put("panNum", resultSet.getString(4));
                                temp.put("verifiedStatus", resultSet.getString(5));

                                torchOut.add(temp);
                            }
                            bondLedgerAccountMaintenanceDTO.setBlaNoOut(torchOut);
                        }
                    }
                }
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
        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getsubscriptionIdInq(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaFlag());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(bondLedgerAccountMaintenanceDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(9);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("blaNo", resultSet.getString(1));
                                temp.put("subscriptionId", resultSet.getString(2));
                                temp.put("bondHolderName", resultSet.getString(3));
                                temp.put("subscriptionAmount", resultSet.getString(4));
                                temp.put("modeOfHolding", resultSet.getString(5));
                                temp.put("panNum", resultSet.getString(6));
                                temp.put("verifiedStatus", resultSet.getString(7));

                                torchOut.add(temp);
                            }
                            bondLedgerAccountMaintenanceDTO.setBlaNoOut(torchOut);
                        }
                    }
                }
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
        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO panTorch(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<HashMap<String, String>> list = null;
        RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO1 = new RbiBondTransferInDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaFlag());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBoSubsId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                resultMsg = stmt.getString(index);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, resultMsg);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                        resultSet = (ResultSet) stmt.getObject(9);

                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("bondHolderName", resultSet.getString(1));
                                item.put("panNum", resultSet.getString(2));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setBlaNoOut(list);
                            responseDataDTO.setResult(list);
                        }



                    responseDataDTO.setMessage(stmt.getString(index));

                }
            }
            else {
                responseDataDTO.setMessage(stmt.getString("Contact DB-ADMIN"));
            }
        }
        catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO addTorch(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<HashMap<String, String>> list = null;
        RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO1 = new RbiBondTransferInDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_ADD_TRC.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getModeOfHolding());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getNoOfJointHolder());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum1());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum2());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum3());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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

                bondLedgerAccountMaintenanceDTO1.setBlaFlag(stmt.getString(12));
                bondLedgerAccountMaintenanceDTO1.setDdPayAccountNumber(stmt.getString(13));
                bondLedgerAccountMaintenanceDTO1.setPeriodOfDeposit(stmt.getString(14));
                bondLedgerAccountMaintenanceDTO1.setSchemeBondType(stmt.getString(15));
                bondLedgerAccountMaintenanceDTO1.setRateOfInterest(stmt.getString(16));
                bondLedgerAccountMaintenanceDTO1.setCashLimitForCash(stmt.getString(17));
                bondLedgerAccountMaintenanceDTO1.setCashLimitForClearingTransfer(stmt.getString(18));
                bondLedgerAccountMaintenanceDTO1.setDepositMultiples(stmt.getString(19));
                bondLedgerAccountMaintenanceDTO1.setMinCashLimit(stmt.getString(20));
                bondLedgerAccountMaintenanceDTO1.setAadharCapture(stmt.getString(21));
                bondLedgerAccountMaintenanceDTO1.setAadharMandatory(stmt.getString(22));
                bondLedgerAccountMaintenanceDTO1.setAadharDisplay(stmt.getString(23));
                bondLedgerAccountMaintenanceDTO1.setInterestPaidUpto1(stmt.getString(24));
                bondLedgerAccountMaintenanceDTO1.setInterestPaidUpto2(stmt.getString(25));
                bondLedgerAccountMaintenanceDTO1.setMaturityAmt(stmt.getString(26));
                String nomineeCountStr = stmt.getString(27);
                int nomineeCount = (nomineeCountStr != null && !nomineeCountStr.isEmpty()) ? Integer.parseInt(nomineeCountStr) : 0;
                bondLedgerAccountMaintenanceDTO1.setSchemeNomineeCount(nomineeCount);
                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if ("S".equals(bondLedgerAccountMaintenanceDTO.getBlaFlag())) {
                        resultSet = (ResultSet) stmt.getObject(9);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("schemeName", resultSet.getString(2));
                                item.put("schemeStatus", resultSet.getString(3));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setBlaNoOut(list);
                            responseDataDTO.setResult(list);
                        }
                    }
                    if ("Y".equals(stmt.getString(12))) {
                         resultSet = (ResultSet) stmt.getObject(9);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("jointHolderType", resultSet.getString(3));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setBlaNoOut(list);
                            responseDataDTO.setResult(list);
                        }
                    }  if ("Y".equals(stmt.getString(12))) {
                         resultSet = (ResultSet) stmt.getObject(11);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("blaGroupId", resultSet.getString(2));
                                item.put("jointHolderType", resultSet.getString(3));
                                item.put("schemeCode", resultSet.getString(4));
                                item.put("guardianId", resultSet.getString(5));
                                item.put("guardianFirstName", resultSet.getString(6));
                                item.put("guardianMiddleName", resultSet.getString(7));
                                item.put("guardianLastName", resultSet.getString(8));
                                item.put("relationWithMinor", resultSet.getString(9));
                                item.put("guardianDateOfBirth", resultSet.getString(10));
                                item.put("guardianCommunicationAddress", resultSet.getString(11));
                                item.put("guardianMobileNo", resultSet.getString(12));
                                item.put("guardianEmailId", resultSet.getString(13));
                                item.put("guardianDeathDate", resultSet.getString(14));
                                item.put("dateOfReceivedDeathcerti", resultSet.getString(15));
                                item.put("curGuardianReq", resultSet.getString(16));
                                item.put("remarks", resultSet.getString(17));
                                item.put("fetchGuardId", resultSet.getString(18));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setGuardianDetailsOut(list);
                            responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                        }

                    }  if ("Y".equals(stmt.getString(12))) {
                         resultSet = (ResultSet) stmt.getObject(10);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("panNo", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("noOfJointAcc", resultSet.getString(4));
                                item.put("blaNo", resultSet.getString(5));
                                item.put("bondHldFirstName", resultSet.getString(6));
                                item.put("bondHldMidName", resultSet.getString(7));
                                item.put("bondHldLastName", resultSet.getString(8));
                                item.put("bondHldDob", resultSet.getString(9));
                                item.put("cifId", resultSet.getString(10));
                                item.put("ftsAppNum", resultSet.getString(11));
                                item.put("holderType", resultSet.getString(12));
                                item.put("fatherName", resultSet.getString(13));
                                item.put("motherName", resultSet.getString(14));
                                item.put("gender", resultSet.getString(15));
                                item.put("isMinor", resultSet.getString(16));
                                item.put("detailsOfBirthCertificate", resultSet.getString(17));
                                item.put("bondHldAddOne", resultSet.getString(18));
                                item.put("bondHldAddTwo", resultSet.getString(19));
                                item.put("bondHldAddThree", resultSet.getString(20));
                                item.put("bondHldPinCode", resultSet.getString(21));
                                item.put("bondHldCity", resultSet.getString(22));
                                item.put("bondHldState", resultSet.getString(23));
                                item.put("bondHolderPhoneNumber", resultSet.getString(24));
                                item.put("bondHldMobNum", resultSet.getString(25));
                                item.put("bondHldEmail", resultSet.getString(26));
                                item.put("hufRegPowerOfAttorney", resultSet.getString(27));
                                item.put("powerOfAttorneyKartaName", resultSet.getString(28));
                                item.put("hdfcOtherBank", resultSet.getString(29));
                                item.put("operativeAccNum", resultSet.getString(30));
                                item.put("ifscCode", resultSet.getString(31));
                                item.put("aadhaarNo", resultSet.getString(32));
                                item.put("witnessNameOne", resultSet.getString(33));
                                item.put("witnessAddressOne", resultSet.getString(34));
                                item.put("witnessNameTwo", resultSet.getString(35));
                                item.put("witnessAddressTwo", resultSet.getString(36));
                                item.put("birthCertificate", resultSet.getString(37));
                                item.put("passport", resultSet.getString(38));
                                item.put("seniorCitizenCard", resultSet.getString(39));
                                item.put("voterIdCard", resultSet.getString(40));
                                item.put("panCard", resultSet.getString(41));
                                item.put("drivingLicence", resultSet.getString(42));
                                item.put("rationCard", resultSet.getString(43));
                                item.put("otherBankFlg", resultSet.getString(44));
                                item.put("otherDocuments", resultSet.getString(45));
                                item.put("remarks", resultSet.getString(46));
                                item.put("blaGroupId", resultSet.getString(47));
                                item.put("jointHolderType", resultSet.getString(48));
                                item.put("modeOfInterestPayment", resultSet.getString(49));
                                item.put("operativeAccNumber", resultSet.getString(50));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setCustomerMasterOut(list);
                            responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                        }
                    }else{
                        bondLedgerAccountMaintenanceDTO1.setBlaFlag(stmt.getString(12));
                        bondLedgerAccountMaintenanceDTO1.setDdPayAccountNumber(stmt.getString(13));
                        bondLedgerAccountMaintenanceDTO1.setPeriodOfDeposit(stmt.getString(14));
                        bondLedgerAccountMaintenanceDTO1.setSchemeBondType(stmt.getString(15));
                        bondLedgerAccountMaintenanceDTO1.setRateOfInterest(stmt.getString(16));
                        bondLedgerAccountMaintenanceDTO1.setCashLimitForCash(stmt.getString(17));
                        bondLedgerAccountMaintenanceDTO1.setCashLimitForClearingTransfer(stmt.getString(18));
                        bondLedgerAccountMaintenanceDTO1.setDepositMultiples(stmt.getString(19));
                        bondLedgerAccountMaintenanceDTO1.setMinCashLimit(stmt.getString(20));
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                    }
                }
            }
        }
        catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
            }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO verify(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_VERIFY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaGroupId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
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
    public ResponseDataDTO delete(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (bondLedgerAccountMaintenanceDTO.getRejectionRemarks() == null
                || bondLedgerAccountMaintenanceDTO.getRejectionRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaGroupId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
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
    public ResponseDataDTO verifyTranResponse(TransactionReponseDto transactionReponseDto) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_VER_TRAN_RES.getProcName());
                stmt.setString("p_verifier_id_in", UserAccountUtils.getCurrentUsername());
                stmt.setString("p_verifier_sol_id_in", UserAccountUtils.getCurrentSolId());
                stmt.setString("p_gbm_tran_id_in", transactionReponseDto.getGbmTranId());
                stmt.setString("p_cbs_tran_id_in", transactionReponseDto.getCbsTranId());
                stmt.setString("p_cbs_tran_stat_in", transactionReponseDto.getCbsTranStatus());
                stmt.setString("p_cbs_tran_statdesc_in",transactionReponseDto.getCbsTranStatusDesc() );
                stmt.registerOutParameter("p_cbs_tran_id_out", Types.VARCHAR);
                stmt.registerOutParameter("p_cbs_tran_stat_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_desc_out", Types.VARCHAR);
                stmt.execute();

                if(stmt.getString("p_msg_out").equalsIgnoreCase(CommonConstants.SUCCESS )
                        && transactionReponseDto.getCbsTranStatus().equalsIgnoreCase("Y")){

                    responseDataDTO.setMessage(stmt.getString("p_msg_desc_out"));
                    responseDataDTO.setResult(transactionReponseDto);
                }
                else if (stmt.getString("p_msg_out").equalsIgnoreCase(CommonConstants.SUCCESS )
                        && !transactionReponseDto.getCbsTranStatus().equalsIgnoreCase("Y")){
                    responseDataDTO.setMessage("CBS FAILED");
                    responseDataDTO.setResult(transactionReponseDto);
                }
                else{
                    responseDataDTO.setMessage(stmt.getString("p_msg_desc_out"));
                    responseDataDTO.setResult(transactionReponseDto);
                }
            }
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;

    }







    @Override
    public ResponseDataDTO verifySelect(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_VER_TRC.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(6);

                    if (bondLedgerAccountMaintenanceDTO.getSchemeCode() == null) {


                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("nameOfTheBondHolder", resultSet.getString(1));
                                item.put("PanNum", resultSet.getString(2));
                                item.put("schemeName", resultSet.getString(3));
                                item.put("modeOfHolding", resultSet.getString(4));
                                item.put("blaNo", resultSet.getString(5));
                                item.put("subscriptionId", resultSet.getString(6));
                                item.put("jointHolderType", resultSet.getString(7));
                                item.put("createModifyBy", resultSet.getString(8));
                                item.put("schemeCode", resultSet.getString(9));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else {
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(6);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(7);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(8);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(9);
                        ResultSet resultSet5 = (ResultSet) stmt.getObject(10);
                        ResultSet resultSet6 = (ResultSet) stmt.getObject(11);
                        bondLedgerAccountMaintenanceDTO.setBlaGroupId(stmt.getString(12));
                        bondLedgerAccountMaintenanceDTO.setPkColumnValue(stmt.getString(13));
                        bondLedgerAccountMaintenanceDTO.setCreatedBy(stmt.getString(14));
                        bondLedgerAccountMaintenanceDTO.setCreatedDate(stmt.getString(15));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedBy(stmt.getString(16));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedDate(stmt.getString(17));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedBy(stmt.getString(18));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedDate(stmt.getString(19));
                        bondLedgerAccountMaintenanceDTO.setPreviousOperationType(stmt.getString(20));
                        bondLedgerAccountMaintenanceDTO.setVerifiedStatus(stmt.getString(21));
                        bondLedgerAccountMaintenanceDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet1));
                        bondLedgerAccountMaintenanceDTO.setCoparsDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
                        bondLedgerAccountMaintenanceDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet3));
                        bondLedgerAccountMaintenanceDTO.setSubscriptionDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet4));
                        bondLedgerAccountMaintenanceDTO.setNomineeDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet5));
                        bondLedgerAccountMaintenanceDTO.setTransferDetailValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet6));
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
                    }

                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }




    @Override
    public ResponseDataDTO modifySelect(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_MOD_TRC.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaFlag());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getFtsAppNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(10);

                    if (bondLedgerAccountMaintenanceDTO.getBlaFlag().equalsIgnoreCase("B")) {


                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSet.getString(1));
                                item.put("blaNo", resultSet.getString(2));
                                item.put("bondHolderName", resultSet.getString(3));
                                item.put("modeOfHolding", resultSet.getString(4));
                                item.put("verifiedStatus", resultSet.getString(5));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getBlaFlag().equalsIgnoreCase("S")) {


                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSet.getString(1));
                                item.put("blaNo", resultSet.getString(2));
                                item.put("subscriptionId", resultSet.getString(3));
                                item.put("bondHolderName", resultSet.getString(4));
                                item.put("subscriptionAmount", resultSet.getString(5));
                                item.put("modeOfHolding", resultSet.getString(6));
                                item.put("verifiedStatus", resultSet.getString(7));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else {
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(10);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(11);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(12);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(13);
                        ResultSet resultSet5 = (ResultSet) stmt.getObject(14);
                        ResultSet resultSet6 = (ResultSet) stmt.getObject(15);
                        bondLedgerAccountMaintenanceDTO.setBlaGroupId(stmt.getString(16));
                        bondLedgerAccountMaintenanceDTO.setPkColumnValue(stmt.getString(17));
                        bondLedgerAccountMaintenanceDTO.setCreatedBy(stmt.getString(18));
                        bondLedgerAccountMaintenanceDTO.setCreatedDate(stmt.getString(19));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedBy(stmt.getString(20));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedDate(stmt.getString(21));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedBy(stmt.getString(22));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedDate(stmt.getString(23));
                        bondLedgerAccountMaintenanceDTO.setPreviousOperationType(stmt.getString(24));
                        bondLedgerAccountMaintenanceDTO.setVerifiedStatus(stmt.getString(25));
                        bondLedgerAccountMaintenanceDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet1));
                        bondLedgerAccountMaintenanceDTO.setCoparsDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
                        bondLedgerAccountMaintenanceDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet3));
                        bondLedgerAccountMaintenanceDTO.setSubscriptionDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet4));
                        bondLedgerAccountMaintenanceDTO.setNomineeDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet5));
                        bondLedgerAccountMaintenanceDTO.setTransferDetailValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet6));
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
                    }

                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO modifyPAN(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_MOD_PAN.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);


                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(6);


                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("bondHolderName", resultSet.getString(1));
                                item.put("panNum", resultSet.getString(2));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }


                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }



    @Override
    public ResponseDataDTO inquiry(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<HashMap<String, String>> list = null;
        RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO1 = new RbiBondTransferInDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaFlag());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBoSubsId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                resultMsg = stmt.getString(index);
                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if ("S".equals(bondLedgerAccountMaintenanceDTO.getBlaFlag())) {

                        resultSet = (ResultSet) stmt.getObject(9);

                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("schemeName", resultSet.getString(2));
                                item.put("schemeStatus", resultSet.getString(3));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setBlaNoOut(list);
                            responseDataDTO.setResult(list);
                        }


                    }
                    /*if ("Y".equals(stmt.getString(12))) {

                        resultSet = (ResultSet) stmt.getObject(8);

                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("jointHolderType", resultSet.getString(3));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setBlaNoOut(list);
                            responseDataDTO.setResult(list);
                        }


                    }
                    if ("Y".equals(stmt.getString(12))) {
                        resultSet = (ResultSet) stmt.getObject(11);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("blaGroupId", resultSet.getString(2));
                                item.put("jointHolderType", resultSet.getString(3));
                                item.put("schemeCode", resultSet.getString(4));
                                item.put("guardianId", resultSet.getString(5));
                                item.put("guardianFirstName", resultSet.getString(6));
                                item.put("guardianMiddleName", resultSet.getString(7));
                                item.put("guardianLastName", resultSet.getString(8));
                                item.put("relationWithMinor", resultSet.getString(9));
                                item.put("guardianDateOfBirth", resultSet.getString(10));
                                item.put("guardianCommunicationAddress", resultSet.getString(11));
                                item.put("guardianMobileNo", resultSet.getString(12));
                                item.put("guardianEmailId", resultSet.getString(13));
                                item.put("guardianDeathDate", resultSet.getString(14));
                                item.put("dateOfReceivedDeathcerti", resultSet.getString(15));
                                item.put("curGuardianReq", resultSet.getString(16));
                                item.put("remarks", resultSet.getString(17));
                                item.put("fetchGuardId", resultSet.getString(18));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setGuardianDetailsOut(list);
                            responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                        }

                    }
                    if ("Y".equals(stmt.getString(12))) {
                        resultSet = (ResultSet) stmt.getObject(10);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("panNo", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("noOfJointAcc", resultSet.getString(4));
                                item.put("blaNo", resultSet.getString(5));
                                item.put("bondHldFirstName", resultSet.getString(6));
                                item.put("bondHldMidName", resultSet.getString(7));
                                item.put("bondHldLastName", resultSet.getString(8));
                                item.put("bondHldDob", resultSet.getString(9));
                                item.put("cifId", resultSet.getString(10));
                                item.put("ftsAppNum", resultSet.getString(11));
                                item.put("holderType", resultSet.getString(12));
                                item.put("fatherName", resultSet.getString(13));
                                item.put("motherName", resultSet.getString(14));
                                item.put("gender", resultSet.getString(15));
                                item.put("isMinor", resultSet.getString(16));
                                item.put("detailsOfBirthCertificate", resultSet.getString(17));
                                item.put("bondHldAddOne", resultSet.getString(18));
                                item.put("bondHldAddTwo", resultSet.getString(19));
                                item.put("bondHldAddThree", resultSet.getString(20));
                                item.put("bondHldPinCode", resultSet.getString(21));
                                item.put("bondHldCity", resultSet.getString(22));
                                item.put("bondHldState", resultSet.getString(23));
                                item.put("bondHolderPhoneNumber", resultSet.getString(24));
                                item.put("bondHldMobNum", resultSet.getString(25));
                                item.put("bondHldEmail", resultSet.getString(26));
                                item.put("hufRegPowerOfAttorney", resultSet.getString(27));
                                item.put("powerOfAttorneyKartaName", resultSet.getString(28));
                                item.put("hdfcOtherBank", resultSet.getString(29));
                                item.put("operativeAccNum", resultSet.getString(30));
                                item.put("ifscCode", resultSet.getString(31));
                                item.put("aadhaarNo", resultSet.getString(32));
                                item.put("witnessNameOne", resultSet.getString(33));
                                item.put("witnessAddressOne", resultSet.getString(34));
                                item.put("witnessNameTwo", resultSet.getString(35));
                                item.put("witnessAddressTwo", resultSet.getString(36));
                                item.put("birthCertificate", resultSet.getString(37));
                                item.put("passport", resultSet.getString(38));
                                item.put("seniorCitizenCard", resultSet.getString(39));
                                item.put("voterIdCard", resultSet.getString(40));
                                item.put("panCard", resultSet.getString(41));
                                item.put("drivingLicence", resultSet.getString(42));
                                item.put("rationCard", resultSet.getString(43));
                                item.put("otherBankFlg", resultSet.getString(44));
                                item.put("otherDocuments", resultSet.getString(45));
                                item.put("remarks", resultSet.getString(46));
                                item.put("blaGroupId", resultSet.getString(47));
                                item.put("jointHolderType", resultSet.getString(48));
                                item.put("modeOfInterestPayment", resultSet.getString(49));
                                item.put("operativeAccNumber", resultSet.getString(50));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setCustomerMasterOut(list);
                            responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                        }

                    } else*/ if(bondLedgerAccountMaintenanceDTO.getSchemeCode() != null && bondLedgerAccountMaintenanceDTO.getBlaNo()  != null  && bondLedgerAccountMaintenanceDTO.getBoSubsId() != null ){
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(9);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(10);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(11);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(12);
                        ResultSet resultSet5 = (ResultSet) stmt.getObject(13);
                        ResultSet resultSet6 = (ResultSet) stmt.getObject(14);
                        bondLedgerAccountMaintenanceDTO.setBlaGroupId(stmt.getString(15));
                        bondLedgerAccountMaintenanceDTO.setPkColumnValue(stmt.getString(16));
                        bondLedgerAccountMaintenanceDTO.setCreatedBy(stmt.getString(17));
                        bondLedgerAccountMaintenanceDTO.setCreatedDate(stmt.getString(18));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedBy(stmt.getString(19));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedDate(stmt.getString(20));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedBy(stmt.getString(21));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedDate(stmt.getString(22));
                        bondLedgerAccountMaintenanceDTO.setPreviousOperationType(stmt.getString(23));
                        bondLedgerAccountMaintenanceDTO.setVerifiedStatus(stmt.getString(24));
                        bondLedgerAccountMaintenanceDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet1));
                        bondLedgerAccountMaintenanceDTO.setCoparsDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
                        bondLedgerAccountMaintenanceDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet3));
                        bondLedgerAccountMaintenanceDTO.setSubscriptionDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet4));
                        bondLedgerAccountMaintenanceDTO.setNomineeDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet5));
                        bondLedgerAccountMaintenanceDTO.setTransferDetailValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet6));
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
                    }
                    responseDataDTO.setMessage(stmt.getString(index));

                }
            }
            else {
                responseDataDTO.setMessage(stmt.getString("Contact DB-ADMIN"));
            }
        }
        catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);

        return responseDataDTO;

    }



    @Override
    public ResponseDataDTO otherBankDetails() throws SQLException {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        List<RbiBondTransferInOtherBankDetails> rbiBondTransferInOtherBankDetailsList = new ArrayList<>();


        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_OTHR_BNK_DETAILS.getProcName());

                stmt.registerOutParameter("p_other_bnk_dtls_out",OracleTypes.CURSOR);
                stmt.registerOutParameter("p_msg_out",Types.VARCHAR);
                stmt.registerOutParameter("p_msg_desc_out",Types.VARCHAR);
                stmt.execute();


                if (stmt.getString("p_msg_out").equalsIgnoreCase("S")){

                    ResultSet rs = (ResultSet) stmt.getObject("p_other_bnk_dtls_out");

                    if(stmt.getObject("p_other_bnk_dtls_out")!= null){

                        while(rs.next()) {
                            RbiBondTransferInOtherBankDetails rbiBondTransferInOtherBankDetails = new RbiBondTransferInOtherBankDetails();
                            rbiBondTransferInOtherBankDetails.setBankCode(rs.getString("bank_code"));
                            rbiBondTransferInOtherBankDetails.setBranchCode(rs.getString("branch_code"));

                            rbiBondTransferInOtherBankDetailsList.add(rbiBondTransferInOtherBankDetails);
                        }

                        responseDataDTO.setMessage(stmt.getString("p_msg_desc_out"));
                        responseDataDTO.setResult(rbiBondTransferInOtherBankDetailsList);
                    }


                }
                else
                {
                    responseDataDTO.setMessage(stmt.getString("p_msg_desc_out"));
                }

            }}
        catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);

        }
                return  responseDataDTO;
    }

    @Override
    public ResponseDataDTO bondTransferInAdd(RbiBondTransferInDTO rbiBondTransferInAddDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTransferInAddDto.getSchemeCode());
                stmt.setString(index++, rbiBondTransferInAddDto.getModeOfHolding());
                stmt.setString(index++, rbiBondTransferInAddDto.getNoOfJointHolder());
                if (rbiBondTransferInAddDto.getDataList() != null) {
                    stmt.setArray(index++, generateTYP_ty_gbm_bond_holder_dtls(connection, rbiBondTransferInAddDto.getDataList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_BLA_ARRAY);
                }  if (rbiBondTransferInAddDto.getDataCopersondetailsList() != null) {
                    stmt.setArray(index++, generateTYP_gbm_rbi_bond_copar(connection, rbiBondTransferInAddDto.getDataCopersondetailsList()));

                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COPARS_ARRAY);
                }
                stmt.setString(index++, rbiBondTransferInAddDto.getBlaFlag());
                stmt.setString(index++, rbiBondTransferInAddDto.getSingleBlaNo());
                stmt.setString(index++, rbiBondTransferInAddDto.getBondHolderName());
                stmt.setString(index++, rbiBondTransferInAddDto.getFirstJointBondHolderBLA());
                stmt.setString(index++, rbiBondTransferInAddDto.getFirstJointBondHolderName());
                stmt.setString(index++, rbiBondTransferInAddDto.getSecondJointBondHolderBLA());
                stmt.setString(index++, rbiBondTransferInAddDto.getSecondJointBondHolderName());
                stmt.setString(index++, rbiBondTransferInAddDto.getThirdJointBondHolderBLA());
                stmt.setString(index++, rbiBondTransferInAddDto.getThirdJointBondHolderName());
                stmt.setString(index++, null);
                stmt.setString(index++, rbiBondTransferInAddDto.getSubsAmt());
                stmt.setString(index++, rbiBondTransferInAddDto.getBondType());
                stmt.setString(index++, rbiBondTransferInAddDto.getAccOpenedDate());
                stmt.setString(index++, rbiBondTransferInAddDto.getValueDate());
                stmt.setString(index++, rbiBondTransferInAddDto.getPeriodOfDeposit());
                stmt.setString(index++, rbiBondTransferInAddDto.getMaturityDate());
                stmt.setString(index++, rbiBondTransferInAddDto.getRateOfInterest());
                stmt.setString(index++, rbiBondTransferInAddDto.getMaturityAmt());
                stmt.setString(index++, rbiBondTransferInAddDto.getTranMode());
                stmt.setString(index++, rbiBondTransferInAddDto.getDebitAccNum());
                stmt.setString(index++, null);
                stmt.setString(index++, rbiBondTransferInAddDto.getInstNum());
                stmt.setString(index++, rbiBondTransferInAddDto.getInstDate());
                stmt.setString(index++, rbiBondTransferInAddDto.getInstAmount());
                stmt.setString(index++, rbiBondTransferInAddDto.getTranCode());
                stmt.setString(index++, rbiBondTransferInAddDto.getZoneCode());
                stmt.setString(index++, rbiBondTransferInAddDto.getZoneDate());
                stmt.setString(index++, rbiBondTransferInAddDto.getNonMicrBankCode());
                stmt.setString(index++, rbiBondTransferInAddDto.getNonMicrBranchCode());
                stmt.setString(index++, rbiBondTransferInAddDto.getSortCode());
                stmt.setString(index++, rbiBondTransferInAddDto.getRemarks());
                if (rbiBondTransferInAddDto.getNomineeDetailsList() != null) {
                    stmt.setArray(index++, generateTYP_gbm_rbi_bond_nom_dtls(connection, rbiBondTransferInAddDto.getNomineeDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_NOMINATION_ARRAY);
                }
                stmt.setString(index++, rbiBondTransferInAddDto.getIsNominee());
                stmt.setString(index++, rbiBondTransferInAddDto.getWboBranchCode());
                stmt.setString(index++, rbiBondTransferInAddDto.getWboBranchName());
                stmt.setString(index++, null);
                stmt.setString(index++, rbiBondTransferInAddDto.getWordsInRupees());
                stmt.setString(index++, rbiBondTransferInAddDto.getTransfererBankName());
                stmt.setString(index++, rbiBondTransferInAddDto.getTransfererBranchName());
                stmt.setString(index++, rbiBondTransferInAddDto.getAccTransferedInDate());
                stmt.setString(index++, rbiBondTransferInAddDto.getOldBla());
                stmt.setString(index++, rbiBondTransferInAddDto.getRbiCode());
                stmt.setString(index++, rbiBondTransferInAddDto.getInterestPaidUpto());
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
//
                    rbiBondTransferInAddDto.setSingleBlaNo(stmt.getString(51));
                    rbiBondTransferInAddDto.setFirstJointBlaNo(stmt.getString(52));
                    rbiBondTransferInAddDto.setSecondJointBlaNo(stmt.getString(53));
                    rbiBondTransferInAddDto.setThirdJointBlaNo(stmt.getString(54));
                    rbiBondTransferInAddDto.setSingleGuardianId(stmt.getString(55));


                    rbiBondTransferInAddDto.setFirstGuardianId(stmt.getString(56));
                    rbiBondTransferInAddDto.setSecondGuardianId(stmt.getString(57));
                    rbiBondTransferInAddDto.setThirdGuardianId(stmt.getString(58));
                    rbiBondTransferInAddDto.setSubscriptionId(stmt.getString(59));
                    rbiBondTransferInAddDto.setBlaNo(stmt.getString(60));
                    responseDataDTO.setResult(rbiBondTransferInAddDto);
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private ARRAY generateTYP_ty_gbm_bond_holder_dtls(Connection connection, List<CustomerData> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_RBI_BOND_BLA_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_RBI_BOND_BLA_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        CustomerData dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[59];
            dto = list.get(index);
            paramArray[0] = dto.getPanNo();
            paramArray[1] = dto.getBondHldFirstName();
            paramArray[2] = dto.getBondHldMidName();
            paramArray[3] = dto.getBondHldLastName();
            paramArray[4] = dto.getBondHldDob();
            paramArray[5] = dto.getCbsCustId();
            paramArray[6] = dto.getFtsAppNum();
            paramArray[7] = dto.getHolderType();
            paramArray[8] = dto.getFatherName();
            paramArray[9] = dto.getMotherName();
            paramArray[10] = dto.getGender();
            paramArray[11] = dto.getIsMinor();
            paramArray[12] = dto.getDetailsOfBirthCertificate();
            paramArray[13] = dto.getBondHldAddOne();
            paramArray[14] = dto.getBondHldAddTwo();
            paramArray[15] = dto.getBondHldAddThree();
            paramArray[16] = dto.getBondHldPinCode();
            paramArray[17] = dto.getBondHldCity();
            paramArray[18] = dto.getBondHldState();
            paramArray[19] = dto.getBondHolderPhoneNumber();
            paramArray[20] = dto.getBondHldMobNum();
            paramArray[21] = dto.getBondHldEmail();
            paramArray[22] = dto.getHufRegPowerOfAttorney();
            paramArray[23] = dto.getPowerOfAttorneyKartaName();
            paramArray[24] = dto.getHdfcOtherBank();
            paramArray[25] = dto.getOperativeAccNum();
            paramArray[26] = dto.getIfscCode();
            paramArray[27] = dto.getAadhaarNo();
            paramArray[28] = dto.getWitnessNameOne();
            paramArray[29] = dto.getWitnessAddressOne();
            paramArray[30] = dto.getWitnessNameTwo();
            paramArray[31] = dto.getWitnessAddressTwo();
            paramArray[32] = dto.getBirthCertificate();
            paramArray[33] = dto.getPassport();
            paramArray[34] = dto.getSeniorCitizenCard();
            paramArray[35] = dto.getVoterIdCard();
            paramArray[36] = dto.getPanCard();
            paramArray[37] = dto.getDrivingLicence();
            paramArray[38] = dto.getRationCard();
            paramArray[39] = dto.getOtherDocuments();
            paramArray[40] = dto.getOtherDetails();
            paramArray[41] = dto.getRemarks();
            paramArray[42] = dto.getGuardianId();
            paramArray[43] = dto.getGuardianFirstName();
            paramArray[44] = dto.getGuardianMiddleName();
            paramArray[45] = dto.getGuardianLastName();
            paramArray[46] = dto.getRelationWithMinor();
            paramArray[47] = dto.getGuardianDateOfBirth1();
            paramArray[48] = dto.getGuardianCommunicationAddress();
            paramArray[49] = dto.getGuardianMobileNo();
            paramArray[50] = dto.getGuardianEmailId();
            paramArray[51] = dto.getJointHolderType();
            paramArray[52] = dto.getModeOfInterestPayment();
            paramArray[53] = dto.getBankCode();
            paramArray[54] = dto.getBranchCode();
            paramArray[55] = dto.getNameOfthePayee();
            paramArray[56] = dto.getOperativeAccNumber();
            paramArray[57] = dto.getMicrCode();
            paramArray[58] = dto.getAccountType();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    private ARRAY generateTYP_gbm_rbi_bond_copar(Connection connection, List<DataCopersondetails> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_COPAR_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_COPAR", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        DataCopersondetails dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[3];
            dto = list.get(index);
            paramArray[0] = dto.getCoparcenersName();
            paramArray[1] = dto.getCoparcenersRelation();
            paramArray[2] = dto.getCoparcenersDob();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    private ARRAY generateTYP_gbm_rbi_bond_nom_dtls(Connection connection, List<NomineeDetails> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        NomineeDetails dto = null;
        // Sharable amount is the 26th attribute; older DB types only declare 25
        int attributeCount = genericTableStruct.getMetaData().getColumnCount();
        if (attributeCount <= 26) {
            log.warn("TYP_GBM_RBI_BOND_NOM_DTLS_OBJ has {} attributes; nominee sharable amount will not be persisted", attributeCount);
        }
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[attributeCount];
            dto = list.get(index);
            paramArray[0] = dto.getFirstName();
            paramArray[1] = dto.getMiddleName();
            paramArray[2] = dto.getLastName();
            paramArray[3] = dto.getAddressOne();
            paramArray[4] = dto.getAddressTwo();
            paramArray[5] = dto.getCity();
            paramArray[6] = dto.getState();
            paramArray[7] = dto.getPinCode();
            paramArray[8] = dto.getPhoneNo();
            paramArray[9] = dto.getSharePercentage();
            paramArray[10] = handleAccountValidations(dto.getOperativeAccNo());
            paramArray[11] = dto.getIsNomineeMinor();
            paramArray[12] = dto.getRelationWithHolder();
            paramArray[13] = dto.getDateOfBirthOfMinor();
            paramArray[14] = dto.getGuardianFirstName();
            paramArray[15] = dto.getGuardianMiddleName();
            paramArray[16] = dto.getGuardianLastName();
            paramArray[17] = dto.getGuardianAddressOne();
            paramArray[18] = dto.getGuardianAddressTwo();
            paramArray[19] = dto.getGuardianCity();
            paramArray[20] = dto.getGuardianState();
            paramArray[21] = dto.getGuardianPinCode();
            paramArray[22] = handleMobileValidations(dto.getGuardianMobileNo());
            paramArray[23] = handleEmailValidations(dto.getGuardianEmail());
            paramArray[24] = dto.getRelationWithMinorNominee();
            paramArray[25] = dto.getNomineeStatus();
            if (attributeCount > 26) {
                paramArray[26] = dto.getNomineeSharableAmount();
            }

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
            log.info("DTO: {}", dto);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    public String handleEmailValidations(String value){
        if(null!=value && !value.trim().isEmpty()){
            RbiBondServerSideRegexUtility.validateEmail(value.trim());
        }return value;
    }
    public String handlePanValidations(String value){
        if(null!=value && !value.trim().isEmpty()){
            RbiBondServerSideRegexUtility.validatePAN(value.trim());
        }return value;
    }
    public String handleMobileValidations(String value){
        if(null!=value && !value.trim().isEmpty()){
            RbiBondServerSideRegexUtility.validateMobileNumber(value.trim());
        }return value;
    }
    public String handleAccountValidations(String value){
        if(null!=value && !value.trim().isEmpty()){
            RbiBondServerSideRegexUtility.validateAccountNumber(value.trim());
        }return value;
    }
    @Override
    public ResponseDataDTO modify(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                if (bondLedgerAccountMaintenanceDTO.getColumnValueDetailsList() != null) {
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, bondLedgerAccountMaintenanceDTO.getColumnValueDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                }
                if (bondLedgerAccountMaintenanceDTO.getGuardianModValueDetailsList() != null) {
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, bondLedgerAccountMaintenanceDTO.getGuardianModValueDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                }
                if (bondLedgerAccountMaintenanceDTO.getGuardianAddDetailsList() != null) {
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, bondLedgerAccountMaintenanceDTO.getGuardianAddDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                }
                if (bondLedgerAccountMaintenanceDTO.getSubscriptionDetailsList() != null) {
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, bondLedgerAccountMaintenanceDTO.getSubscriptionDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                }
                if (bondLedgerAccountMaintenanceDTO.getTransferDetailValueList() != null) {
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, bondLedgerAccountMaintenanceDTO.getTransferDetailValueList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                }
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
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
    private ARRAY generateTYP_typ_rbi_bond_bla_req_OBJ(Connection connection, List<PanValidationRequestDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PAN_DET_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_PAN_DET", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        PanValidationRequestDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[3];
            dto = list.get(index);
            paramArray[0] = dto.getPan();
            paramArray[1] = dto.getBondHolderName();
            paramArray[2] = dto.getDob();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    private ARRAY generateTYP_typ_rbi_bond_bla_res_OBJ(Connection connection, PanValidationRetriggerResponseDTO dto) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_PAN_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_PAN", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[1];
        Object[] paramArray = new Object[14];
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
        STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
        genericTableInsertStructs[0] = struct;
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    @Override
    public ResponseDataDTO validatePanRequest(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        PanDetailsDTO panDetailsDTO=null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_SP_PAN_TRIGGER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanValidation().get(0).getSchemeCode());
                stmt.setArray(index++, generateTYP_typ_rbi_bond_bla_req_OBJ(connection, bondLedgerAccountMaintenanceDTO.getPanValidation()));
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                List<PanDetailsDTO> list=new ArrayList<>();
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(null!=bondLedgerAccountMaintenanceDTO){
                        resultSet = (ResultSet) stmt.getObject(5);
                        if (resultSet != null){
                            while(resultSet.next()) {
                                panDetailsDTO=new PanDetailsDTO();
                                panDetailsDTO.setPan(resultSet.getString(1));
                                panDetailsDTO.setName(resultSet.getString(2));
                                panDetailsDTO.setDob(resultSet.getString(3));
                                list.add(panDetailsDTO);
                            }
                        }
                        responseDataDTO.setResult(list);
                    }
                }
                else{
                    responseDataDTO.setStatusCode("0");
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO apiResponse(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRANS_IN_SP_TRIGGER_RES.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, panValidationRetriggerResponseDTO.getSchemeCode());
                stmt.setArray(index++, generateTYP_typ_rbi_bond_bla_res_OBJ(connection, panValidationRetriggerResponseDTO));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override

    public ResponseDataDTO getGuardianDetails(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_NOMINEE_CONTINUATION_SP_GUARD_ID.getProcName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getGuardianId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(3);

                    if (bondLedgerAccountMaintenanceDTO.getGuardianId() == null) {

                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("guardianId", resultSet.getString(4));
                                item.put("guardianName", resultSet.getString(5));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else {
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("guardianFirstName", resultSet.getString(1));
                                item.put("guardianMiddleName", resultSet.getString(2));
                                item.put("guardianLastName", resultSet.getString(3));
                                item.put("relationWithMinor", resultSet.getString(4));
                                item.put("guardianDateOfBirth", resultSet.getString(5));
                                item.put("guardianCommunicationAddress", resultSet.getString(6));
                                item.put("mobileNo", resultSet.getString(7));
                                item.put("email", resultSet.getString(8));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                        responseDataDTO.setResult(list);
                    }

                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }









}
