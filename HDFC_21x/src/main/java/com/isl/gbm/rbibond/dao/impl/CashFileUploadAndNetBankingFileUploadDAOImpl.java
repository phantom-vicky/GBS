package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.DatabaseConnectionFactory;
import com.isl.gbm.rbibond.dao.CashFileUploadAndNetBankingFileUploadDAO;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.*;
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

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CashFileUploadAndNetBankingFileUploadDAOImpl implements CashFileUploadAndNetBankingFileUploadDAO {

    private static final Logger log = LoggerFactory.getLogger(CashFileUploadAndNetBankingFileUploadDAOImpl.class);

    @Autowired
    DatabaseConnectionFactory connectionPoolFactory;
    String incomingValue="Incoming value: ";
    String returnValue="Returning value: ";

    @Override
    public ResponseDataDTO add(CashFileUploadAndNetBankingFileUploadRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        CashFileUploadAndNetBankingFileUploadRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO1=null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CASH_INET_FILE_UPLOAD_sp_add.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getSubCode());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getFileMode());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getLiquidationDate());
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&
                        null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList3()&&
                        !cashFileUploadAndNetBankingFileUploadRequestDTO.getList3().isEmpty()&&
                        cashFileUploadAndNetBankingFileUploadRequestDTO.getList3().size()>0){
                    stmt.setArray(index++, generateTYP_GBM_RRBNB_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getList3(),cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName3()));}
                else{
                    stmt.setNull(index++, Types.ARRAY, "GBM_RBI_BOND_RRBNB");
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList1()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList1().size()>0){
                    stmt.setArray(index++, generateTYP_GBM_RRBNB_BB_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getList1(),cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName1()));}
                else{
                    stmt.setNull(index++, Types.ARRAY, "GBM_RBI_BOND_RRBNB_BB");
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList4()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList4().size()>0){
                    stmt.setArray(index++, generateTYP_GBM_TBNB_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getList4(),cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName4()));}
                else{
                    stmt.setNull(index++, Types.ARRAY, "GBM_RBI_BOND_TBNB");
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList2()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList2().size()>0){
                    stmt.setArray(index++, generateTYP_GBM_TBNB_BB_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getList2(),cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName2()));}
                else{
                    stmt.setNull(index++, Types.ARRAY, "GBM_TY_RBI_BOND_TBNB_BB");
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList5()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList5().size()>0){
                    stmt.setArray(index++, generateTYP_GBM_TAXBOND_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getList5(),cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName5()));}
                else{
                    stmt.setNull(index++, Types.ARRAY, "TYP_GBM_RBI_BOND_TAXBOND");
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&
                        null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList6()&&
                        !cashFileUploadAndNetBankingFileUploadRequestDTO.getList6().isEmpty()&&
                        cashFileUploadAndNetBankingFileUploadRequestDTO.getList6().size()>0){
                    stmt.setArray(index++, generateTYP_GBM_RRBNB_NOVOPAY_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getList6(),cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName6()));}
                else{
                    stmt.setNull(index++, Types.ARRAY, "GBM_RBI_BOND_RRBNB_NOVOPAY");
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList7()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList7().size()>0){
                    stmt.setArray(index++, generateTYP_GBM_TBNB_BB_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getList7(),cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName7()));}
                else{
                    stmt.setNull(index++, Types.ARRAY, "GBM_TY_RBI_BOND_TBNB_BB");
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&
                        null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList8()&&
                        !cashFileUploadAndNetBankingFileUploadRequestDTO.getList8().isEmpty()&&
                        cashFileUploadAndNetBankingFileUploadRequestDTO.getList8().size()>0){
                    stmt.setArray(index++, generateTYP_GBM_RRBNB_NOVOPAY_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getList8(),cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName8()));}
                else{
                    stmt.setNull(index++, Types.ARRAY, "GBM_RBI_BOND_RRBNB_NOVOPAY");
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList9()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList9().size()>0){
                    stmt.setArray(index++, generateTYP_GBM_TBNB_BB_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getList9(),cashFileUploadAndNetBankingFileUploadRequestDTO.getFileName9()));}
                else{
                    stmt.setNull(index++, Types.ARRAY, "GBM_TY_RBI_BOND_TBNB_BB");
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&
                        null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList3()&&
                        !cashFileUploadAndNetBankingFileUploadRequestDTO.getList3().isEmpty()&&
                        cashFileUploadAndNetBankingFileUploadRequestDTO.getList3().size()>0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(cashFileUploadAndNetBankingFileUploadRequestDTO.getFile3());
                    stmt.setBlob(index++,bis,cashFileUploadAndNetBankingFileUploadRequestDTO.getFile3().length);
                }
                else{
                    stmt.setNull(index++, Types.BLOB);
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList1()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList1().size()>0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(cashFileUploadAndNetBankingFileUploadRequestDTO.getFile1());
                    stmt.setBlob(index++,bis,cashFileUploadAndNetBankingFileUploadRequestDTO.getFile1().length);
                }
                else{
                    stmt.setNull(index++, Types.BLOB);
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList4()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList4().size()>0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(cashFileUploadAndNetBankingFileUploadRequestDTO.getFile4());
                    stmt.setBlob(index++,bis,cashFileUploadAndNetBankingFileUploadRequestDTO.getFile4().length);
                }
                else{
                    stmt.setNull(index++, Types.BLOB);
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList2()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList2().size()>0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(cashFileUploadAndNetBankingFileUploadRequestDTO.getFile2());
                    stmt.setBlob(index++,bis,cashFileUploadAndNetBankingFileUploadRequestDTO.getFile2().length);
                }
                else{
                    stmt.setNull(index++, Types.BLOB);
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&
                        null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList5()&&
                        !cashFileUploadAndNetBankingFileUploadRequestDTO.getList5().isEmpty()&&
                        cashFileUploadAndNetBankingFileUploadRequestDTO.getList5().size()>0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(cashFileUploadAndNetBankingFileUploadRequestDTO.getFile5());
                    stmt.setBlob(index++,bis,cashFileUploadAndNetBankingFileUploadRequestDTO.getFile5().length);
                }
                else{
                    stmt.setNull(index++, Types.BLOB);
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList6()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList6().size()>0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(cashFileUploadAndNetBankingFileUploadRequestDTO.getFile6());
                    stmt.setBlob(index++,bis,cashFileUploadAndNetBankingFileUploadRequestDTO.getFile6().length);
                }
                else{
                    stmt.setNull(index++, Types.BLOB);
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList7()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList7().size()>0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(cashFileUploadAndNetBankingFileUploadRequestDTO.getFile7());
                    stmt.setBlob(index++,bis,cashFileUploadAndNetBankingFileUploadRequestDTO.getFile7().length);
                }
                else{
                    stmt.setNull(index++, Types.BLOB);
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList8()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList8().size()>0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(cashFileUploadAndNetBankingFileUploadRequestDTO.getFile8());
                    stmt.setBlob(index++,bis,cashFileUploadAndNetBankingFileUploadRequestDTO.getFile8().length);
                }
                else{
                    stmt.setNull(index++, Types.BLOB);
                }
                if(null!=cashFileUploadAndNetBankingFileUploadRequestDTO&&null!=cashFileUploadAndNetBankingFileUploadRequestDTO.getList9()&&cashFileUploadAndNetBankingFileUploadRequestDTO.getList9().size()>0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(cashFileUploadAndNetBankingFileUploadRequestDTO.getFile9());
                    stmt.setBlob(index++,bis,cashFileUploadAndNetBankingFileUploadRequestDTO.getFile9().length);
                }
                else{
                    stmt.setNull(index++, Types.BLOB);
                }
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getValueDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    cashFileUploadAndNetBankingFileUploadRequestDTO1=new CashFileUploadAndNetBankingFileUploadRequestDTO();
                    cashFileUploadAndNetBankingFileUploadRequestDTO1.setFileName1(stmt.getString(25));
                    cashFileUploadAndNetBankingFileUploadRequestDTO1.setCountOfRecord(stmt.getString(26));
                    cashFileUploadAndNetBankingFileUploadRequestDTO1.setTotalAmt(stmt.getString(27));
                }else{
                    responseDataDTO.setStatusCode("0");
                }
                responseDataDTO.setResult(cashFileUploadAndNetBankingFileUploadRequestDTO1);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } finally {
            connectionPoolFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    private ARRAY generateTYP_GBM_RRBNB_NOVOPAY_ARRAY_OBJ(Connection connection, List<RRBNBNOVOPAYRequestDTO> list,String fileName) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("GBM_RBI_BOND_RRBNB_NOVOPAY_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("GBM_RBI_BOND_RRBNB_NOVOPAY", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RRBNBNOVOPAYRequestDTO dto = null;
        for (int indexA = 0; indexA < list.size(); indexA++) {
            dto = list.get(indexA);
            Object[] paramArray = new Object[83];
            log.info("Loop RRBNB_NOVOPAY:- ");
            paramArray[0] = utility(dto.getScheme());
            paramArray[1] = utility(dto.getPickupPoint());
            paramArray[2] = convertZeroToNull(parseBigDecimal(utility(dto.getApplicationNumber())));
            paramArray[3] = utility(dto.getDepositDate());
            paramArray[4] = convertZeroToNull(parseBigDecimal(utility(dto.getDepositSlipNumber())));
            paramArray[5] = utility(dto.getInstrumentNumber());
            paramArray[6] = utility(dto.getDraweeBankDescription());
            paramArray[7] = utility(dto.getInstrumentAmount());
            paramArray[8] = utility(dto.getProductCode());
            paramArray[9] = utility(dto.getDepositRemarks());
            paramArray[10] = utility(dto.getFirstNameApplicant());
            paramArray[11] = utility(dto.getFirstApplicantCustId());
            paramArray[12] = utility(dto.getModeOfHolding());
            paramArray[13] = utility(dto.getCohCoi());
            paramArray[14] = utility(dto.getApplicantStatus());
            paramArray[15] = utility(dto.getPanNo());
            paramArray[16] = utility(dto.getMotherMaidenName());
            paramArray[17] = utility(dto.getEmailAddress());
            paramArray[18] = utility(dto.getBankName());
            paramArray[19] = convertZeroToNull(parseBigDecimal(utility(dto.getAccountNo())));
            paramArray[20] = utility(dto.getIfscCode());
            paramArray[21] = convertZeroToNull(parseBigDecimal(utility(dto.getTelephones())));
            paramArray[22] = utility(dto.getCollectionBranch());
            paramArray[23] = utility(dto.getGender());
            paramArray[24] = utility(dto.getDateOfBirth());
            paramArray[25] = utility(dto.getCommunicationAddress1());
            paramArray[26] = utility(dto.getCommunicationAddress2());
            paramArray[27] = utility(dto.getCity());
            paramArray[28] = utility(dto.getState());
            paramArray[29] = utility(dto.getPincode());
            paramArray[30] = utility(dto.getGuardianName());
            paramArray[31] = utility(dto.getRelationshipWithMinor());
            paramArray[32] = utility(dto.getCommunicationAddress());
            paramArray[33] = utility(dto.getGuardianEmailAddress());
            paramArray[34] = utility(dto.getModeOfInvestment());
            paramArray[35] = utility(dto.getChequeDd());
            paramArray[36] = utility(dto.getBranchCode());
            paramArray[37] = utility(dto.getBrokersNameAndCode());
            paramArray[38] = utility(dto.getNomineeOneFirstName());
            paramArray[39] = utility(dto.getNomineeOneSecondName());
            paramArray[40] = utility(dto.getNomineeOneThirdName());
            paramArray[41] = utility(dto.getNomineeOneAddress1());
            paramArray[42] = utility(dto.getNomineeOneAddress2());
            paramArray[43] = utility(dto.getNomineeOneCity());
            paramArray[44] = utility(dto.getNomineeOneState());
            paramArray[45] = utility(dto.getNomineeOnePincode());
            paramArray[46] = utility(dto.getNomineeOneCountry());
            paramArray[47] = convertZeroToNull(parseBigDecimal(utility(dto.getNomineeOnePhoneNo())));
            paramArray[48] = utility(dto.getNomineeOnePercentage());
            paramArray[49] = utility(dto.getNomineeOneAmount());
            paramArray[50] = utility(dto.getNomineeOneRelationshipToHolder());
            paramArray[51] = utility(dto.getNomineeOneMinorFlagging());
            paramArray[52] = utility(dto.getNomineeOneMinorGender());
            paramArray[53] = utility(dto.getNomineeOneDob());
            paramArray[54] = utility(dto.getNomineeOneGuardianName());
            paramArray[55] = utility(dto.getRelationshipWithMinor());
            paramArray[56] = utility(dto.getStatusResidentNri());
            paramArray[57] = utility(dto.getSecondApplicantName());
            paramArray[58] = utility(dto.getSecondApplicantCustId());
            paramArray[59] = utility(dto.getSecondApplicantGender());
            paramArray[60] = utility(dto.getSecondApplicantDob());
            paramArray[61] = utility(dto.getSecondApplicantPanNo());
            paramArray[62] = utility(dto.getThirdApplicantName());
            paramArray[63] = utility(dto.getThirdApplicantCustId());
            paramArray[64] = utility(dto.getThirdApplicantGender());
            paramArray[65] = utility(dto.getThirdApplicantDob());
            paramArray[66] = utility(dto.getThirdApplicantPanNo());
            paramArray[67] = utility(dto.getAccountType());
            paramArray[68] = utility(dto.getNomineeTwoFirstName());
            paramArray[69] = utility(dto.getNomineeTwoSecondName());
            paramArray[70] = utility(dto.getNomineeTwoThirdName());
            paramArray[71] = utility(dto.getNomineeTwoAddress1());
            paramArray[72] = utility(dto.getNomineeTwoAddress2());
            paramArray[73] = utility(dto.getNomineeTwoCity());
            paramArray[74] = utility(dto.getNomineeTwoState());
            paramArray[75] = utility(dto.getNomineeTwoPincode());
            paramArray[76] = utility(dto.getNomineeTwoCountry());
            paramArray[77] = convertZeroToNull(parseBigDecimal(utility(dto.getNomineeTwoPhoneNo())));
            paramArray[78] = utility(dto.getNomineeTwoPercentage());
            paramArray[79] = utility(dto.getNomineeTwoAmount());
            paramArray[80] = utility(dto.getNomineeTwoRelationshipToHolder());
            paramArray[81] = utility(dto.getNomineeTwoMinorFlagging());
            paramArray[82] = fileName;
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[indexA] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    private ARRAY generateTYP_GBM_RRBNB_BB_ARRAY_OBJ(Connection connection, List<RRBNBBBRequestDTO> list, String fileName) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("GBM_RBI_BOND_RRBNB_BB_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("GBM_RBI_BOND_RRBNB_BB", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RRBNBBBRequestDTO dto = null;
        for (int indexA = 0; indexA < list.size(); indexA++) {
            dto = list.get(indexA);
            Object[] paramArray = new Object[81];
            paramArray[0] = utility(dto.getScheme());
            paramArray[1] = utility(dto.getPickupPoint());
            paramArray[2] = convertZeroToNull(parseBigDecimal(utility(dto.getApplicationNumber())));
            paramArray[3] = utility(dto.getDepositDate());
            paramArray[4] = convertZeroToNull(parseBigDecimal(utility(dto.getDepositSlipNumber())));
            paramArray[5] = utility(dto.getInstrumentNumber());
            paramArray[6] = utility(dto.getDraweeBankDescription());
            paramArray[7] = utility(dto.getInstrumentAmount());
            paramArray[8] = utility(dto.getProductCode());
            paramArray[9] = utility(dto.getDepositRemarks());
            paramArray[10] = utility(dto.getFirstNameApplicant());
            paramArray[11] = utility(dto.getModeOfHolding());
            paramArray[12] = utility(dto.getCohCoi());
            paramArray[13] = utility(dto.getApplicantStatus());
            paramArray[14] = handlePanValidations(utility(dto.getPanNo()));
            paramArray[15] = utility(dto.getMotherMaidenName());
            paramArray[16] = utility(dto.getEmailAddress());
            paramArray[17] = utility(dto.getBankName());
            paramArray[18] = convertZeroToNull(parseBigDecimal(utility(dto.getAccountNo())));
            paramArray[19] = utility(dto.getIfscCode());
            paramArray[20] = convertZeroToNull(parseBigDecimal(utility(dto.getTelephones())));
            paramArray[21] = utility(dto.getCollectionBranch());
            paramArray[22] = utility(dto.getGender());
            paramArray[23] = utility(dto.getDateOfBirth());
            paramArray[24] = utility(dto.getCommunicationAddress1());
            paramArray[25] = utility(dto.getCommunicationAddress2());
            paramArray[26] = utility(dto.getCity());
            paramArray[27] = utility(dto.getState());
            paramArray[28] = utility(dto.getPincode());
            paramArray[29] = utility(dto.getGuardianName());
            paramArray[30] = utility(dto.getRelationshipWithMinor());
            paramArray[31] = utility(dto.getCommunicationAddress());
            paramArray[32] = utility(dto.getGuardianEmailAddress());
            paramArray[33] = utility(dto.getModeOfInvestment());
            paramArray[34] = utility(dto.getChequeDd());
            paramArray[35] = utility(dto.getBranchCode());
            paramArray[36] = utility(dto.getBrokersNameAndCode());
            paramArray[37] = utility(dto.getNomineeOneFirstName());
            paramArray[38] = utility(dto.getNomineeOneSecondName());
            paramArray[39] = utility(dto.getNomineeOneThirdName());
            paramArray[40] = utility(dto.getNomineeOneAddress1());
            paramArray[41] = utility(dto.getNomineeOneAddress2());
            paramArray[42] = utility(dto.getNomineeOneCity());
            paramArray[43] = utility(dto.getNomineeOneState());
            paramArray[44] = utility(dto.getNomineeOnePincode());
            paramArray[45] = utility(dto.getNomineeOneCountry());
            paramArray[46] = convertZeroToNull(parseBigDecimal(utility(dto.getNomineeOnePhoneNo())));
            paramArray[47] = utility(dto.getNomineeOnePercentage());
            paramArray[48] = utility(dto.getNomineeOneAmount());
            paramArray[49] = utility(dto.getNomineeOneRelationshipToHolder());
            paramArray[50] = utility(dto.getNomineeOneMinorFlagging());
            paramArray[51] = utility(dto.getNomineeOneMinorGender());
            paramArray[52] = utility(dto.getNomineeOneDob());
            paramArray[53] = utility(dto.getNomineeOneGuardianName());
            paramArray[54] = utility(dto.getNomineeOneRelationshipWithMinor());
            paramArray[55] = utility(dto.getStatusResidentNri());
            paramArray[56] = utility(dto.getSecondApplicantName());
            paramArray[57] = utility(dto.getSecondApplicantCustId());
            paramArray[58] = utility(dto.getSecondApplicantGender());
            paramArray[59] = utility(dto.getSecondApplicantDob());
            paramArray[60] = handlePanValidations(utility(dto.getSecondApplicantPanNo()));
            paramArray[61] = utility(dto.getThirdApplicantName());
            paramArray[62] = utility(dto.getThirdApplicantGender());
            paramArray[63] = utility(dto.getThirdApplicantDob());
            paramArray[64] = handlePanValidations(utility(dto.getThirdApplicantPanNo()));
            paramArray[65] = utility(dto.getAccountType());
            paramArray[66] = utility(dto.getNomineeTwoFirstName());
            paramArray[67] = utility(dto.getNomineeTwoSecondName());
            paramArray[68] = utility(dto.getNomineeTwoThirdName());
            paramArray[69] = utility(dto.getNomineeTwoAddress1());
            paramArray[70] = utility(dto.getNomineeTwoAddress2());
            paramArray[71] = utility(dto.getNomineeTwoCity());
            paramArray[72] = utility(dto.getNomineeTwoState());
            paramArray[73] = utility(dto.getNomineeTwoPincode());
            paramArray[74] = utility(dto.getNomineeTwoCountry());
            paramArray[75] = convertZeroToNull(parseBigDecimal(utility(dto.getNomineeTwoPhoneNo())));
            paramArray[76] = utility(dto.getNomineeTwoPercentage());
            paramArray[77] = utility(dto.getNomineeTwoAmount());
            paramArray[78] = utility(dto.getNomineeTwoRelationshipToHolder());
            paramArray[79] = utility(dto.getNomineeTwoMinorFlagging());
            paramArray[80] = fileName;
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[indexA] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    private ARRAY generateTYP_GBM_RRBNB_ARRAY_OBJ(Connection connection, List<RRBNBRequestDTO> list, String fileName) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("GBM_RBI_BOND_RRBNB_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("GBM_RBI_BOND_RRBNB", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RRBNBRequestDTO dto = null;
        for (int indexC = 0; indexC < list.size(); indexC++) {
            dto = list.get(indexC);
            Object[] paramArray = new Object[66];
            paramArray[0] = utility(dto.getTranType());
            paramArray[1] = utility(dto.getEntityId());
            paramArray[2] = utility(dto.getTypeOfEntry());
            paramArray[3] = utility(dto.getDebitCreditFlag());
            paramArray[4] = convertZeroToNull(parseBigDecimal(utility(dto.getInvestmentAmount())));
            paramArray[5] = utility(dto.getValueDate());
            paramArray[6] = utility(dto.getPostingDate());
            paramArray[7] = utility(dto.getProductCode());
            paramArray[8] = utility(dto.getPickupLocation());
            paramArray[9] = utility(dto.getPickupPoint());
            paramArray[10] = utility(dto.getPickupDate());
            paramArray[11] = convertZeroToNull(parseBigDecimal(utility(dto.getDepositSlipNumber())));
            paramArray[12] = utility(dto.getDepositDate());
            paramArray[13] = convertZeroToNull(parseBigDecimal(utility(dto.getDepositAmount())));
            paramArray[14] = utility(dto.getNumberOfInstruments());
            paramArray[15] = utility(dto.getDepositRemarks());
            paramArray[16] = utility(dto.getInstrumentNumber());
            paramArray[17] = utility(dto.getDraweeBankDescription());
            paramArray[18] = utility(dto.getLocDescription());
            paramArray[19] = convertZeroToNull(parseBigDecimal(utility(dto.getInvestmentAmount())));
            paramArray[20] = utility(dto.getInstrumentDate());
            paramArray[21] = utility(dto.getDrawerName());
            paramArray[22] = utility(dto.getApplicantName());
            paramArray[23] = utility(dto.getOperatingInstructions());
            paramArray[24] = convertZeroToNull(parseBigDecimal(utility(dto.getCheckListNumber())));
            paramArray[25] = utility(dto.getCohCoi());
            paramArray[26] = utility(dto.getScheme());
            paramArray[27] = utility(dto.getBla());
            paramArray[28] = utility(dto.getType());
            paramArray[29] = handlePanValidations(utility(dto.getPan()));
            paramArray[30] = utility(dto.getEmail());
            paramArray[31] = utility(dto.getBankName());
            paramArray[32] = convertZeroToNull(parseBigDecimal(utility(dto.getAccountNumber())));
            paramArray[33] = utility(dto.getIfsc());
            paramArray[34] = convertZeroToNull(parseBigDecimal(utility(dto.getMobile())));
            paramArray[35] = utility(dto.getCollectionBranch());
            paramArray[36] = utility(dto.getGender());
            paramArray[37] = utility(dto.getDateOfBirth());
            paramArray[38] = utility(dto.getAddress1());
            paramArray[39] = utility(dto.getAddress2());
            paramArray[40] = utility(dto.getCity());
            paramArray[41] = utility(dto.getState());
            paramArray[42] = utility(dto.getPincode());
            paramArray[43] = convertZeroToNull(parseBigDecimal(utility(dto.getTelephone())));
            paramArray[44] = utility(dto.getModeOfInvestment());
            paramArray[45] = utility(dto.getBranchCode());
            paramArray[46] = utility(dto.getBrokerCode());
            paramArray[47] = utility(dto.getNomineeFirstName());
            paramArray[48] = utility(dto.getNomineeMiddleName());
            paramArray[49] = utility(dto.getNomineeLastName());
            paramArray[50] = utility(dto.getNomineeAddress1());
            paramArray[51] = utility(dto.getNomineeAddress2());
            paramArray[52] = utility(dto.getNomineeCity());
            paramArray[53] = utility(dto.getNomineeState());
            paramArray[54] = utility(dto.getNomineeCountry());
            paramArray[55] = utility(dto.getNomineePincode());
            paramArray[56] = convertZeroToNull(parseBigDecimal(utility(dto.getNomineePhoneNo())));
            paramArray[57] = utility(dto.getNomineePercentage());
            paramArray[58] = convertZeroToNull(parseBigDecimal(utility(dto.getNomineeAmount())));
            paramArray[59] = utility(dto.getApplicantRelation());
            paramArray[60] = utility(dto.getMinorFlagging());
            paramArray[61] = utility(dto.getMinorGender());
            paramArray[62] = utility(dto.getNomineeDob());
            paramArray[63] = utility(dto.getGuardiansName());
            paramArray[64] = utility(dto.getRelationshipWithGuardian());
            paramArray[65] = fileName;
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[indexC] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    public String parseBigDecimal(String var) {
        log.info("Incoming value: {}", var);
        if (var == null) {
            return null;  // better than forcing ZERO
        }
        var = var.trim();
        if (var.isEmpty()) {
            return null;  // or return "0" if business requires
        }
        try {
            // If value contains scientific notation OR only digits
            if (var.contains("E") || var.contains("e")) {
                BigDecimal bigDecimal = new BigDecimal(var);
                String result = bigDecimal.toPlainString();
                log.info("Converted scientific to plain: {}", result);
                return result;
            }
            // If pure numeric (may contain leading zeros)
            else if (var.matches("\\d+")) {
                log.info("Dont Converted decimal: {}", var);
                return var;   // DO NOT convert → preserves leading zeros
            }
            // If decimal number
            else if (var.matches("\\d+\\.\\d+")) {
                BigDecimal bigDecimal = new BigDecimal(var);
                String result = bigDecimal.toPlainString();
                log.info("Converted decimal: {}", result);
                return result;
            }
            // If not numeric, return as it is
            return var;
        } catch (Exception e) {
            log.error("Error converting value: {}", var, e);
            throw new RuntimeException("Error converting value: " + var, e);
        }
    }
    public String handlePanValidations(String value){
        if(null!=value && !value.trim().isEmpty()){
            RbiBondServerSideRegexUtility.validatePAN(value.trim());
        }return value;
    }

    private ARRAY generateTYP_GBM_TBNB_ARRAY_OBJ(Connection connection, List<TBNBRequestDTO> list, String fileName) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("GBM_RBI_BOND_TBNB_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("GBM_RBI_BOND_TBNB", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        TBNBRequestDTO dto = null;
        for (int indexD = 0; indexD < list.size(); indexD++) {
            dto = list.get(indexD);
            Object[] paramArray = new Object[31];
            paramArray[0] = convertZeroToNull(parseBigDecimal(utility(dto.getDepositSlipNumber())));
            paramArray[1] = utility(dto.getPickupPointCode());
            paramArray[2] = convertZeroToNull(parseBigDecimal(utility(dto.getInstrumentAmount())));
            paramArray[3] = utility(dto.getInstrumentNumber());
            paramArray[4] = utility(dto.getInstrumentDate());
            paramArray[5] = utility(dto.getDraweeBankCode());
            paramArray[6] =  utility(dto.getMicrCode());
            paramArray[7] =  utility(dto.getClientCode());
            paramArray[8] =  utility(dto.getDepositDate());
            paramArray[9] =  utility(dto.getDrawerCode());
            paramArray[10] = utility(dto.getClearingLocationCode());
            paramArray[11] = utility(dto.getDispatchBankCode());
            paramArray[12] = utility(dto.getClientArrangement());
            paramArray[13] = utility(dto.getProductCode());
            paramArray[14] = utility(dto.getDispatchBankArrangement());
            paramArray[15] = utility(dto.getActivationDate());
            paramArray[16] = utility(dto.getClientType());
            paramArray[17] = utility(dto.getApplicantName());
            paramArray[18] = utility(dto.getOperatingInstructions());
            paramArray[19] = convertZeroToNull(parseBigDecimal(utility(dto.getCheckListNumber())));
            paramArray[20] = utility(dto.getCohCoi());
            paramArray[21] = utility(dto.getScheme());
            paramArray[22] = utility(dto.getExistingBlaNumber());
            paramArray[23] = utility(dto.getTypeOfApplicant());
            paramArray[24] = handlePanValidations(utility(dto.getPanNo()));
            paramArray[25] = utility(dto.getEmailAddressOf1stApplicant());
            paramArray[26] = utility(dto.getBankNameInterestPayment());
            paramArray[27] = utility(dto.getAccountNoInterestPayment());
            paramArray[28] = utility(dto.getIfscCodeInterestPayment());
            paramArray[29] = convertZeroToNull(parseBigDecimal(utility(dto.getMobileNo())));
            paramArray[30] = fileName;
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[indexD] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    private ARRAY generateTYP_GBM_TBNB_BB_ARRAY_OBJ(Connection connection, List<TBNBBBRequestDTO> list, String fileName) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("GBM_RBI_BOND_TBNB_BB_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("GBM_TY_RBI_BOND_TBNB_BB", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        TBNBBBRequestDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            dto = list.get(index);
            Object[] paramArray = new Object[30];
            paramArray[0] = utility(dto.getScheme());
            paramArray[1] = utility(dto.getPickupPointCode());
            paramArray[2] = convertZeroToNull(parseBigDecimal(utility(dto.getCheckListNumber())));
            paramArray[3] = utility(dto.getDepositDate());
            paramArray[4] = utility(dto.getActivationDate());
            paramArray[5] = utility(dto.getDispatchBankArrangement());
            paramArray[6] = utility(dto.getClientType());
            paramArray[7] = convertZeroToNull(parseBigDecimal(utility(dto.getDepositSlipNumber())));
            paramArray[8] = utility(dto.getInstrumentNumber());
            paramArray[9] = utility(dto.getDraweeBankCode());
            paramArray[10] = utility(dto.getMicrCode());
            paramArray[11] = utility(dto.getClientCode());
            paramArray[12] = utility(dto.getClearingLocationCode());
            paramArray[13] = utility(dto.getDispatchBankCode());
            paramArray[14] = utility(dto.getClientArrangement());
            paramArray[15] = convertZeroToNull(parseBigDecimal(utility(dto.getInstrumentAmount())));
            paramArray[16] = utility(dto.getProductCode());
            paramArray[17] = utility(dto.getBlank());
            paramArray[18] = utility(dto.getApplicantName());
            paramArray[19] = utility(dto.getOperatingInstructions());
            paramArray[20] = utility(dto.getCohCoi());
            paramArray[21] = utility(dto.getExistingBlaNumber());
            paramArray[22] = utility(dto.getTypeOfApplicant());
            paramArray[23] = handlePanValidations(utility(dto.getPanNo()));
            paramArray[24] = utility(dto.getEmailAddressOfFirstApplicant());
            paramArray[25] = utility(dto.getBankNameInterestPayment());
            paramArray[26] = utility(dto.getAccountNoInterestPayment());
            paramArray[27] = utility(dto.getIfscCodeInterestPayment());
            paramArray[28] = convertZeroToNull(parseBigDecimal(utility(dto.getMobileNo())));
            paramArray[29] =fileName;
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    public String utility(String value){
        if((null!=value)&&(!value.isEmpty())){
            return value.trim();
        }else{
            return value;
        }
    }
    private ARRAY generateTYP_GBM_TAXBOND_ARRAY_OBJ(Connection connection, List<TaxBondTextFileDTO> list, String fileName) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_TAXBOND_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_TAXBOND", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        TaxBondTextFileDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            dto = list.get(index);
            Object[] paramArray = new Object[52];
            log.info("Loop TAXBOND:- ");
            paramArray[0] = dto.getTranType();
            paramArray[1] = dto.getEntityId();
            paramArray[2] = dto.getTypeOfEntry();
            paramArray[3] = dto.getDebitCreditFlag();
            paramArray[4] = dto.getAccEntryAmount();
            paramArray[5] = dto.getValueDate();
            paramArray[6] = dto.getPostingDate();
            paramArray[7] = dto.getProductCode();
            paramArray[8] = dto.getPickupLocation();
            paramArray[9] = dto.getPickupPoint();
            paramArray[10] = dto.getPickupDate();
            paramArray[11] = dto.getDepositSlipNmbr();
            paramArray[12] = dto.getDepositDate();
            paramArray[13] = dto.getDepositAmnt();
            paramArray[14] = dto.getNoOfInst();
            paramArray[15] = dto.getDepositRemarks();
            paramArray[16] = dto.getInstNmbr();
            paramArray[17] = dto.getDraweeBankDescription();
            paramArray[18] = dto.getLocDescription();
            paramArray[19] = dto.getInstrumentAmnt();
            paramArray[20] = dto.getInstrumentDate();
            paramArray[21] = dto.getDrawerName();
            paramArray[22] = dto.getApplicantName();
            paramArray[23] = dto.getOperatingInstructions();
            paramArray[24] = dto.getCheckListNumber();
            paramArray[25] = dto.getCoh();
            paramArray[26] = dto.getScheme();
            paramArray[27] = dto.getBla();
            paramArray[28] = dto.getType();
            paramArray[29] = dto.getPan();
            paramArray[30] = dto.getEmail();
            paramArray[31] = dto.getBankName();
            paramArray[32] = dto.getAcctNo();
            paramArray[33] = dto.getIfsc();
            paramArray[34] = dto.getMobile();
            paramArray[35] = dto.getBrokerCode();
            paramArray[36] = dto.getGender();
            paramArray[37] = dto.getCollectionBranch();
            paramArray[38] = dto.getCommunicationAddress();
            paramArray[39] = dto.getDateOfBirth();
            paramArray[40] = dto.getApplicantStatus();
            paramArray[41] = dto.getModeOfInvestment();
            paramArray[42] = dto.getFullName();
            paramArray[43] = dto.getDob();
            paramArray[44] = dto.getNameInFullSecondApplicants();
            paramArray[45] = dto.getSecondApplicantsGender();
            paramArray[46] = dto.getSecondApplicantsPan();
            paramArray[47] = dto.getNameInFullGuardianDetails();
            paramArray[48] = dto.getGuardianGender();
            paramArray[49] = dto.getRelationshipWithMinor();
            paramArray[50] = dto.getGuardianCommunicationAddress();
            paramArray[51] = fileName;
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    public static String convertZeroToNull(String number) {
        return "0".equals(number) ? null : number;
    }

    @Override
    public ResponseDataDTO verifySelect(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO1=new CashFileUploadVerifySelectRequestDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CASH_INET_FILE_UPLOAD_sp_ver_sel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getSubCode());
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getFileMode());
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getFileType());
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getLiquidationDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                ResultSet resultSet = null;
                ResultSet resultSet1 = null;
                List<CashFileUploadVerifySelectRequestDTO> torchOut = new ArrayList<>();
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(7);
                    CashFileUploadVerifySelectRequestDTO output = null;
                    while (resultSet.next()) {
                        output = new CashFileUploadVerifySelectRequestDTO();
                        output.setFileName(resultSet.getString(1));
                        output.setCountOfRecord(resultSet.getString(2));
                        output.setTotalAmt(resultSet.getString(3));
                        torchOut.add(output);
                    }
                    resultSet1 = (ResultSet) stmt.getObject(8);
                    while (resultSet1.next()) {
                        Blob fileData = null;
                        String fileName=resultSet1.getString(1);
                        fileData=resultSet1.getBlob(2);
                        for (int i = 0; i < torchOut.size(); i++) {
                            if (fileData != null && fileName.equalsIgnoreCase(torchOut.get(i).getFileName())) {
                                byte[] bytes = fileData.getBytes(1, (int) fileData.length());
                                log.info("bytes-"+ bytes);
                                torchOut.get(i).setFile(bytes);
                            }
                        }
                    }
                    if(null!=torchOut&&torchOut.size()>8){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") && null != torchOut.get(2) &&
                                torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB") && null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")&&
                                null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")&&null != torchOut.get(8) && torchOut.get(8).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6, 8);
                        }
                    }else if(null!=torchOut&&torchOut.size()>7){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") && null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB")&&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") && null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6);
                        }
                    }else if(null!=torchOut&&torchOut.size()>6){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")&&
                                null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")&&
                                null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")&&
                                null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")&&
                                null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6);
                        }
                    }else if(null!=torchOut&&torchOut.size()>5){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB")&&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB")&&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")&&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")&&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }
                    }else if(null!=torchOut&&torchOut.size()>4){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }
                    }else if(null!=torchOut&&torchOut.size()>3){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }
                    }else if(null!=torchOut&&torchOut.size()>2){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        } else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }
                    }else if(null!=torchOut&&torchOut.size()>1){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0);
                        }else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB")){
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0);
                        }else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")){
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0);
                        }else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")){
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0);
                        }
                    }else if(null!=torchOut&&torchOut.size()>0){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0);
                        }
                    }
                    cashFileUploadVerifySelectRequestDTO1.setCreatedBy(stmt.getString(9));
                    cashFileUploadVerifySelectRequestDTO1.setCreatedDate(stmt.getString(10));
                    cashFileUploadVerifySelectRequestDTO1.setVerifiedStatus(stmt.getString(11));
                    cashFileUploadVerifySelectRequestDTO1.setRejectionRemarks(stmt.getString(12));
                    if(null!=stmt.getString(13)){
                    cashFileUploadVerifySelectRequestDTO1.setValueDate(stmt.getString(13));}
                    cashFileUploadVerifySelectRequestDTO1.setTorchOut(torchOut);
                    responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO1);
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    private void calculateOverall(List<CashFileUploadVerifySelectRequestDTO> torchOut,
                                  CashFileUploadVerifySelectRequestDTO dto,
                                  int... indexes) {
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal count = BigDecimal.ZERO;
        for (int index : indexes) {
            amount = amount.add(new BigDecimal(torchOut.get(index).getTotalAmt()));
            count = count.add(new BigDecimal(torchOut.get(index).getCountOfRecord()));
            log.info("Index: {}, File Name: {}, Amount: {}, Count: {}", index, torchOut.get(index).getFileName(), torchOut.get(index).getTotalAmt(), torchOut.get(index).getCountOfRecord());
        }
        dto.setOverAllAmt(amount.toString());
        dto.setOverAllCount(count.toString());
    }
    private ARRAY generateTYP_GBM_VERIFY_ARRAY_OBJ(Connection connection, List<CashFileUploadVerifySelectRequestDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        CashFileUploadVerifySelectRequestDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            dto = list.get(index);
            Object[] paramArray = new Object[1];
            paramArray[0] = dto.getFileName();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    @Override
    public ResponseDataDTO verify(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CASH_INET_FILE_UPLOAD_sp_ver.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getSubCode());
                stmt.setArray(index++, generateTYP_GBM_VERIFY_ARRAY_OBJ(connection, cashFileUploadVerifySelectRequestDTO.getTorchOut()));
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getLiquidationDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO delete(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (cashFileUploadVerifySelectRequestDTO.getRejectionRemarks() == null
                || cashFileUploadVerifySelectRequestDTO.getRejectionRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CASH_INET_FILE_UPLOAD_sp_del.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getSubCode());
                stmt.setArray(index++, generateTYP_GBM_VERIFY_ARRAY_OBJ(connection,cashFileUploadVerifySelectRequestDTO.getTorchOut()));
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getLiquidationDate());
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiry(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO1=new CashFileUploadVerifySelectRequestDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CASH_INET_FILE_UPLOAD_sp_inq.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getSubCode());
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getFileMode());
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getFileType());
                stmt.setString(index++,cashFileUploadVerifySelectRequestDTO.getLiquidationDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                ResultSet resultSet1 = null;
                List<CashFileUploadVerifySelectRequestDTO> torchOut = new ArrayList<>();
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(7);
                    CashFileUploadVerifySelectRequestDTO output = null;
                    while (resultSet.next()) {
                        output = new CashFileUploadVerifySelectRequestDTO();
                        output.setFileName(resultSet.getString(1));
                        output.setCountOfRecord(resultSet.getString(2));
                        output.setTotalAmt(resultSet.getString(3));
                        torchOut.add(output);
                    }
                    resultSet1 = (ResultSet) stmt.getObject(8);
                    while (resultSet1.next()) {
                        Blob fileData = null;
                        String fileName = resultSet1.getString(1);
                        fileData = resultSet1.getBlob(2);
                        for (int i = 0; i < torchOut.size(); i++) {
                            if (fileData != null && fileName.equalsIgnoreCase(torchOut.get(i).getFileName())) {
                                byte[] bytes = fileData.getBytes(1, (int) fileData.length());
                                log.info("bytes-" + bytes);
                                torchOut.get(i).setFile(bytes);
                            }
                        }
                    }
                    if(null!=torchOut&&torchOut.size()>8){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") && null != torchOut.get(2) &&
                                torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB") && null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")&&
                                null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")&&null != torchOut.get(8) && torchOut.get(8).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6, 8);
                        }
                    }else if(null!=torchOut&&torchOut.size()>7){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") && null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB")&&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") && null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6);
                        }
                    }else if(null!=torchOut&&torchOut.size()>6){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")&&
                                null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")&&
                                null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")&&
                                null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")&&
                                null != torchOut.get(6) && torchOut.get(6).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4, 6);
                        }
                    }else if(null!=torchOut&&torchOut.size()>5){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB")&&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB")&&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")&&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")&&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }
                    }else if(null!=torchOut&&torchOut.size()>4){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY") &&
                                null != torchOut.get(4) && torchOut.get(4).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2, 4);
                        }
                    }else if(null!=torchOut&&torchOut.size()>3){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_BB")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }
                    }else if(null!=torchOut&&torchOut.size()>2){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        } else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY") &&
                                null != torchOut.get(2) && torchOut.get(2).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0, 2);
                        }
                    }else if(null!=torchOut&&torchOut.size()>1){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0);
                        }else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_BB")){
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0);
                        }else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_SAKSHAM")){
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0);
                        }else if(null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("RRBNB_NOVOPAY")){
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0);
                        }
                    }else if(null!=torchOut&&torchOut.size()>0){
                        if (null != torchOut.get(0) && torchOut.get(0).getFileName().equalsIgnoreCase("TAXBOND")) {
                            calculateOverall(torchOut, cashFileUploadVerifySelectRequestDTO1, 0);
                        }
                    }
                    if(null!=stmt.getString(9)){
                        cashFileUploadVerifySelectRequestDTO1.setValueDate(stmt.getString(9));}
                        cashFileUploadVerifySelectRequestDTO1.setTorchOut(torchOut);
                        responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO1);
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    /*
     Input  :4-1-1984
     Output :04-01-1984

     or
     Input  :04-01-1984
     Output :04-01-1984

     */
    public static String  formatDate(String dateStr) {
        log.info("Incoming value: {}", dateStr);

        // Define the required date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false); // Ensures strict parsing

        try {
            if((null!=dateStr)&&(!dateStr.isEmpty())) {
                // Parse the input date string to a Date object to check validity
                Date date = dateFormat.parse(dateStr);
                // Format the Date object back to String to ensure it's always in the correct format
                String formattedDate = dateFormat.format(date);
                log.info("Returning value: {}", formattedDate);
                return formattedDate;
            }
        } catch (Exception e) {
            // Log the error
            log.error("Invalid date format: {}", dateStr, e);
            log.info("Returning value: null");
            return "";
        }
        return "";
    }

}
