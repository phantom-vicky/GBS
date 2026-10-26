package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.RbiBondCentalizedBodEodDAO;
import com.isl.gbm.rbibond.dto.RbiBondEodBodDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RbiBondCentalizedBodEodDAOImpl implements RbiBondCentalizedBodEodDAO {

    private static final Logger log = LoggerFactory.getLogger(RbiBondCentalizedBodEodDAOImpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;



    @Override
    public ResponseDataDTO add(RbiBondEodBodDTO centalizedEodBodDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CEN_BOD_CEN_BOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, centalizedEodBodDTO.getBodDate());
                stmt.setString(index++, CommonConstants.RBI_BOND_MODULE_ID);
                stmt.setString(index++, centalizedEodBodDTO.getLevelName());
                if (CollectionUtils.isNotEmpty(centalizedEodBodDTO.getCentalizedEodBodDTOList())){
                    stmt.setArray(index++, generateArray(connection, centalizedEodBodDTO.getCentalizedEodBodDTOList()));
                } else{
                    stmt.setNull(index++, Types.ARRAY, "TYP_SINGLE_COL");
                }
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(centalizedEodBodDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getDate(RbiBondEodBodDTO centalizedEodBodDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CEN_BOD_GET_BOD_STRT_DT.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    centalizedEodBodDTO.setBodDate(stmt.getString(3));
                }else {
                    resultMsg = stmt.getString(index);

                }

            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(centalizedEodBodDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getLevelBranch(RbiBondEodBodDTO centalizedEodBodDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        RbiBondEodBodDTO output = new RbiBondEodBodDTO();
        List < HashMap < String, String >> torchOut = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CEN_BOD_GET_LEV_BRN.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, centalizedEodBodDTO.getBodDate());
                stmt.setString(index++, CommonConstants.RBI_BOND_MODULE_ID);
                stmt.setString(index++, centalizedEodBodDTO.getLevelName());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (centalizedEodBodDTO.getLevelName() == null) {
                        resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            HashMap< String, String > temp = null;
                            torchOut = new ArrayList< >();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    temp = new HashMap < > ();
                                    temp.put("levelId", resultSet.getString(1));
                                    temp.put("levelName", resultSet.getString(2));
                                    torchOut.add(temp);
                                }
                            }
                            output.setLevelCodeList(torchOut);
                        }
                    } else {

                        resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            HashMap < String, String > temp = null;
                            torchOut = new ArrayList < > ();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    temp = new HashMap < > ();
                                    temp.put("branchId", resultSet.getString(1));
                                    temp.put("branchDescription", resultSet.getString(2));
                                    torchOut.add(temp);
                                }
                            }
                            output.setBranchCode(torchOut);
                        }




                    }

                }else {
                    resultMsg = stmt.getString(index);
                }

            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(output);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO startEodProcess(RbiBondEodBodDTO centalizedEodBodDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CEN_EOD_CEN_EOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, centalizedEodBodDTO.getEodDate());
                stmt.setString(index++, CommonConstants.RBI_BOND_MODULE_ID );
                stmt.setString(index++, centalizedEodBodDTO.getLevelName());
                stmt.setArray(index++, generateArray(connection, centalizedEodBodDTO.getCentalizedEodBodDTOList()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

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


//    @Override
//    public ResponseDataDTO startEodProcess(RbiBondEodBodDTO centalizedEodBodDTO) {
//        Connection connection = null;
//        CallableStatement stmt = null;
//        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
//
//
//        List<GbmTransactionMasterDto> gbmTransactionMasterDtos = new ArrayList<>();
//        GbmTransactionMasterDto gbmTransactionMasterDto = null;
//
//        String resultMsg = "";
//
//        RbiBondEodBodDTO rbiBondEodBodDTO = null;
//        List<RbiBondEodBodDTO> rbiBondEodBodDTOList = new ArrayList<>();
//
//        RbiBondEodBodDTO responseDto = new RbiBondEodBodDTO();
//
//
//        RbiBondEodBodDTO amountCursor = null;
//        List<RbiBondEodBodDTO> amountCursorList = new ArrayList();
//
//
//
//        List<String> gbmTranIdList = new ArrayList<>();
//        String gbmTranId = "";
//
//        String solId = "";
//        List<String> solIdList = new ArrayList<>();
//
//
//        try {
//            System.out.println("BOD EOD DTO >>>>"+centalizedEodBodDTO.toString());
//            connection = connectionPoolFactory.getConnection();
//            if (connection != null) {
//                int index = 1;
//
//                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CEN_EOD_CEN_EOD.getProcName());
//                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
//                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
//                stmt.setString(index++, centalizedEodBodDTO.getEodDate());
//                stmt.setString(index++, CommonConstants.RBI_BOND_MODULE_ID);
//                stmt.setString(index++, centalizedEodBodDTO.getLevelName());
//
//                if ((centalizedEodBodDTO.getTypeFlag().equalsIgnoreCase("ACC") || centalizedEodBodDTO.getTypeFlag().equalsIgnoreCase("TRAN")) && (centalizedEodBodDTO.getCentalizedEodBodDTOList() != null && !centalizedEodBodDTO.getCentalizedEodBodDTOList().isEmpty())){
//                    stmt.setArray(index++, generateArray(connection, centalizedEodBodDTO.getCentalizedEodBodDTOList()));
//                } else{
//                    stmt.setNull(index++, Types.ARRAY, "TYP_SINGLE_COL");
//                }
//
//                if (centalizedEodBodDTO.getTypeFlag().equalsIgnoreCase("ACC") && (centalizedEodBodDTO.getCollectionAccountDet() != null && !centalizedEodBodDTO.getCollectionAccountDet().isEmpty())){
//                    stmt.setArray(index++, generateArrayCol(connection, centalizedEodBodDTO.getCollectionAccountDet()));
//                } else{
//                    stmt.setNull(index++, Types.ARRAY, "TYP_RBI_BOND_EOD");
//                }
//
//                if (centalizedEodBodDTO.getTypeFlag().equalsIgnoreCase("ACC") && (centalizedEodBodDTO.getConsolidatedAccountDet() !=null && !centalizedEodBodDTO.getConsolidatedAccountDet().isEmpty())){
//                    stmt.setArray(index++, generateArrayCon(connection, centalizedEodBodDTO.getConsolidatedAccountDet()));
//                } else{
//                    stmt.setNull(index++, Types.ARRAY, "TYP_RBI_BOND_EOD_AC_DET");
//                }
//
//                if (centalizedEodBodDTO.getTypeFlag().equalsIgnoreCase("TRAN") && (centalizedEodBodDTO.getTransactionReponseDtos() != null && !centalizedEodBodDTO.getTransactionReponseDtos().isEmpty())){
//                    stmt.setArray(index++, generateArrayTran(connection, centalizedEodBodDTO.getTransactionReponseDtos()));
//                } else{
//                    stmt.setNull(index++, Types.ARRAY, "GBM_RBI_BOND_TRAN_EOD");
//                }
//
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index, Types.VARCHAR);
//                stmt.execute();
//
//                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
//                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
//                resultMsg = stmt.getString(index);
//
//
//
//                if(stmt.getString(28) != null && stmt.getString(29).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
//
//
//                    responseDto.setCbsEntryFlag(stmt.getString(16));
//                    responseDto.setEodStatus(stmt.getString(17));
//                    responseDto.setEodFailedReason(stmt.getString(18));
//                    responseDto.setEodStatusDesc(stmt.getString(19));
//
//
//                    if (CommonConstants.FAILURE.equals(responseDto.getEodStatus())) {
//                        ResultSet rs = null;
//                        if (CommonConstants.EOD_FLAG_DEPOSIT_AMT_VALIDATION.equalsIgnoreCase(responseDto.getEodFailedReason())) {
//                            AccountValidationDto tempAccValidationDto = new AccountValidationDto();
////                            tempAccValidationDto.setAccountName("DEPOSIT");
////                            tempAccValidationDto.setIsCentralized(stmt.getString("P_DEP_AC_CENT_FLG_OUT"));
////                            String[] solAndAccountDetails = stmt.getString("P_DEP_AC_DET_OUT").split("\\^");
////                            tempAccValidationDto.setBranchId(solAndAccountDetails[0]);
////                            tempAccValidationDto.setAccNo(solAndAccountDetails[1]);
//                            responseDto.setDepositAccountDet(tempAccValidationDto);
//                        } else if (CommonConstants.EOD_FLAG_NON_FINANCIAL_TRAN_PENDING.equalsIgnoreCase(responseDto.getEodFailedReason())) {
//                            rs = (ResultSet) stmt.getObject(21);
//                            List<RbiBondEodBodDTO.ErrorDescDto> tempList = new ArrayList<>();
//                            if (rs != null) {
//                                RbiBondEodBodDTO.ErrorDescDto tempDto;
//                                while (rs.next()) {
//                                    tempDto = new RbiBondEodBodDTO.ErrorDescDto();
//                                    tempDto.setModuleName(rs.getString("MODULE"));
//                                    tempDto.setLinkName(rs.getString("LINK_NAME"));
//                                    tempDto.setDesc(rs.getString("RECORD_COUNT"));
//                                    tempList.add(tempDto);
//                                }
//                            }
//                            rs.close();
//                            if (!org.springframework.util.CollectionUtils.isEmpty(tempList)) {
//                                responseDto.setNonFinancialUnverifiedTranList(tempList);
//                            } else {
//                                throw new GenericBusinessException("NO NON-FINANCIAL UNVERIFIED RECORDS FOUND");
//                            }
//                        } else if (CommonConstants.EOD_FLAG_UNVERIFIED_TRAN_PENDING.equalsIgnoreCase(responseDto.getEodFailedReason())) {
//                            rs = (ResultSet) stmt.getObject(2);
//                            List<RbiBondEodBodDTO.ErrorDescDto> tempList = new ArrayList<>();
//                            if (rs != null) {
//                                RbiBondEodBodDTO.ErrorDescDto tempDto;
//                                while (rs.next()) {
//                                    tempDto = new RbiBondEodBodDTO.ErrorDescDto();
//                                    tempDto.setModuleName(rs.getString("MODULE"));
//                                    tempDto.setLinkName(rs.getString("LINK_NAME"));
//                                    tempDto.setDesc(rs.getString(CommonConstants.GBM_TRAN_ID_STRING));
//                                    tempList.add(tempDto);
//                                }
//                            }
//                            rs.close();
//                            if (!org.springframework.util.CollectionUtils.isEmpty(tempList)) {
//                                responseDto.setFinancialUnverifiedTranList(tempList);
//                            } else {
//                                throw new GenericBusinessException("NO FINANCIAL UNVERIFIED RECORDS FOUND");
//                            }
//                        }
//                    }
//                    else {
//                        AccountValidationDto tempAccValidationDto = null;
//                        List<AccountValidationDto> accountValidationDtoList = new ArrayList<>();
//
//                        if (CommonConstants.EOD_FLAG_CASH_BAL_VALIDATION.equalsIgnoreCase(centalizedEodBodDTO.getCashDepCheckFlg())) {
//
//
//                            //cash account details
//                            ResultSet rs3 = (ResultSet) stmt.getObject(22);
//
//                            while(rs3.next()) {
//                                tempAccValidationDto = new AccountValidationDto();
//                                tempAccValidationDto.setAccountName("CASH" + rs3.getString(3));
//                                tempAccValidationDto.setIsCentralized(rs3.getString(1));
////                                tempAccValidationDto.setBranchId(rs3.getString(4));
//                                tempAccValidationDto.setAccNo(rs3.getString(2));
//                                accountValidationDtoList.add(tempAccValidationDto);
//                                responseDto.setCashAccountDet(accountValidationDtoList);
//                            }
//                            rs3.close();
//                        } else if (CommonConstants.PROC_TYPE_IN_REQUEST.equals(centalizedEodBodDTO.getProcTypeIn())) {
//
//
//
//                            //gives amount for each sol id
//                            ResultSet rs4 = (ResultSet) stmt.getObject(23);
//                            while(rs4.next()){
//
//                                amountCursor = new RbiBondEodBodDTO();
//                                amountCursor.setSchemeCode(rs4.getString(1));
//                                amountCursor.setAmount(rs4.getString(2));
//                                amountCursor.setBranchId(rs4.getString(3));
//                                amountCursorList.add(amountCursor);
//
//                            }
//                            responseDto.setAmtValidationList(amountCursorList);
//                            rs4.close();
//
////
////                            responseDto.setDepositAmount(stmt.getString("P_TOT_DEP_REC_AMT_OUT"));
////                            tempAccValidationDto = new AccountValidationDto();
////                            tempAccValidationDto.setAccountName("DEPOSIT");
////                            tempAccValidationDto.setIsCentralized(stmt.getString("P_DEP_AC_CENT_FLG_OUT"));
////                            tempAccValidationDto.setBranchId(solAndAccountDetails[0]);
////                            tempAccValidationDto.setAccNo(solAndAccountDetails[1]);
////                            responseDto.setDepositAccountDet(tempAccValidationDto);
//
//                            // account numbers to be validated
//                            ResultSet rs6 = (ResultSet) stmt.getObject(25);
//                            while(rs6.next()){
//
//                                rbiBondEodBodDTO = new RbiBondEodBodDTO();
//                                rbiBondEodBodDTO.setBondColAcc(rs6.getString(2));
//                                rbiBondEodBodDTO.setBondColAccCenFlag(rs6.getString(1));
//                                rbiBondEodBodDTO.setBondConAcc(rs6.getString(4));
//                                rbiBondEodBodDTO.setBondConAccCenFlag(rs6.getString(3));
//                                rbiBondEodBodDTO.setBondPayAcc(rs6.getString(6));
//                                rbiBondEodBodDTO.setBondPayAccCenFlag(rs6.getString(5));
//                                rbiBondEodBodDTO.setBondRecAcc(rs6.getString(8));
//                                rbiBondEodBodDTO.setBondRecAccCenFlag(rs6.getString(7));
//                                rbiBondEodBodDTO.setSchemeCode(rs6.getString(9));
//                                rbiBondEodBodDTOList.add(rbiBondEodBodDTO);
//                            }
//                            responseDto.setAccValidationList(rbiBondEodBodDTOList);
//                            rs6.close();
//
//
////                            tempAccValidationDto = new AccountValidationDto();
////                            tempAccValidationDto.setAccountName("COL");
////                            tempAccValidationDto.setIsCentralized(stmt.getString("P_COLLEC_AC_CENT_FLG_OUT"));
////                            tempAccValidationDto.setBranchId(solAndAccountDetails[0]);
////                            tempAccValidationDto.setAccNo(solAndAccountDetails[1]);
////                            responseDto.setCollectionAccountDet(tempAccValidationDto);
////
////                            tempAccValidationDto = new AccountValidationDto();
////                            tempAccValidationDto.setAccountName("CON");
////                            tempAccValidationDto.setIsCentralized(stmt.getString("P_CONS_AC_CENT_FLG_OUT"));
////                            tempAccValidationDto.setBranchId(solAndAccountDetails[0]);
////                            tempAccValidationDto.setAccNo(solAndAccountDetails[1]);
////                            responseDto.setConsolidatedAccountDet(tempAccValidationDto);
//
////                            tempAccValidationDto = new AccountValidationDto();
////                            tempAccValidationDto.setAccountName("PAY");
////                            tempAccValidationDto.setIsCentralized(stmt.getString("P_PAYABLE_AC_CENT_FLG_OUT"));
////                            tempAccValidationDto.setBranchId(solAndAccountDetails[0]);
////                            tempAccValidationDto.setAccNo(solAndAccountDetails[1]);
////                            responseDto.setPayableAccountDet(tempAccValidationDto);
////
////                            tempAccValidationDto = new AccountValidationDto();
////                            tempAccValidationDto.setAccountName("REC");
////                            tempAccValidationDto.setIsCentralized(stmt.getString("P_RECVABLE_AC_CENT_FLG_OUT"));
////                            tempAccValidationDto.setBranchId(solAndAccountDetails[0]);
////                            tempAccValidationDto.setAccNo(solAndAccountDetails[1]);
////                            responseDto.setReceivableAccountDet(tempAccValidationDto);
//
//                        } else if (CommonConstants.PROC_TYPE_IN_RESPONSE.equals(centalizedEodBodDTO.getProcTypeIn())) {
//
//                            Date tranDate = null;
//                            try {
//                                tranDate = new SimpleDateFormat("dd/MM/yyyy").parse(stmt.getString(13));
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//
//                            ResultSet rs1 = (ResultSet) stmt.getObject(20);
//
//                            //details to proceed with transaction
//                            while(rs1.next()){
//
//                                gbmTransactionMasterDto = new GbmTransactionMasterDto();
//                                gbmTransactionMasterDto.setAcctNum(rs1.getString(1));
//                                gbmTransactionMasterDto.setTranLeg(rs1.getString(2).charAt(0));
//                                gbmTransactionMasterDto.setAmount(Double.parseDouble(rs1.getString(3)));
//                                gbmTransactionMasterDto.setSolId(rs1.getString(4));
//                                gbmTransactionMasterDto.setTransactionChannel(stmt.getString(12));
//                                gbmTransactionMasterDto.setTranDate(tranDate);
//                                gbmTransactionMasterDto.setSchemeCode(rs1.getString(5));
//                                gbmTransactionMasterDto.setScreenTranCode(stmt.getString(15));
//                                gbmTransactionMasterDto.setTranId(rs1.getString(6));
//                                gbmTransactionMasterDto.setGbmTranId(rs1.getString(6));
//                                gbmTransactionMasterDtos.add(gbmTransactionMasterDto);
//
//                            }
//                            responseDto.setTranList(gbmTransactionMasterDtos);
//                            rs1.close();
//
//                        } else if ("2".equals(centalizedEodBodDTO.getProcTypeIn())) {
////                            Successful EOD
//                        } else if ("3".equals(centalizedEodBodDTO.getProcTypeIn())) {
////                            Successful EOD
//                        }
//                    }
//
//
//                    ResultSet rs2 = (ResultSet) stmt.getObject(21);
//                    rs2.close();
//                    ResultSet rs5 = (ResultSet) stmt.getObject(24);
//                    rs5.close();
//
//
//
//
//
//                    //list of gbm tran id
//
//                    ResultSet rs7 = (ResultSet) stmt.getObject(26);
//                    while(rs7.next()){
//
//                        gbmTranId = rs7.getString(1);
//                        gbmTranIdList.add(gbmTranId);
//
//                    }
//                    responseDto.setGbmTranIdList(gbmTranIdList);
//                    rs7.close();
//
//
//                    //list of sol id which initiated eod
//                    ResultSet rs8 = (ResultSet) stmt.getObject(27);
//                    while(rs8.next()){
//
//                        solId = rs8.getString(1);
//                        solIdList.add(solId);
//
//                    }
//                    rs8.close();
//
//
//
//
//
//
//                }
//
//
//
//
//
//                responseDataDTO.setResult(responseDto);
//
//
//            }
//        } catch (Exception e) {
//            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
//            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
//        } finally {
//            connectionPoolFactory.closeConnection(connection, stmt);
//        }
//        responseDataDTO.setMessage(resultMsg);
//        return responseDataDTO;
//    }

    @Override
    public ResponseDataDTO getEodDate(RbiBondEodBodDTO centalizedEodBodDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CEN_EOD_GET_EOD_STRT_DT.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    centalizedEodBodDTO.setEodDate(stmt.getString(3));
                }else {
                    resultMsg = stmt.getString(index);
                }

            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(centalizedEodBodDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getEodLevelBranch(RbiBondEodBodDTO centalizedEodBodDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        RbiBondEodBodDTO output = new RbiBondEodBodDTO();
        List < HashMap < String, String >> torchOut = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CEN_EOD_GET_LEV_BRN.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, centalizedEodBodDTO.getEodDate());
                stmt.setString(index++, CommonConstants.RBI_BOND_MODULE_ID);
                stmt.setString(index++, centalizedEodBodDTO.getLevelName());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (centalizedEodBodDTO.getLevelName() == null) {
                        resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            HashMap< String, String > temp = null;
                            torchOut = new ArrayList< >();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    temp = new HashMap < > ();
                                    temp.put("levelId", resultSet.getString(1));
                                    temp.put("levelName", resultSet.getString(2));
                                    torchOut.add(temp);
                                }
                            }
                            output.setLevelCodeList(torchOut);
                        }
                    } else {

                        resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            HashMap < String, String > temp = null;
                            torchOut = new ArrayList < > ();
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    temp = new HashMap < > ();
                                    temp.put("branchId", resultSet.getString(1));
                                    temp.put("branchDescription", resultSet.getString(2));
                                    torchOut.add(temp);
                                }
                            }
                            output.setBranchCode(torchOut);
                        }




                    }

                }else {
                    resultMsg = stmt.getString(index);

                }

            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(output);
        return responseDataDTO;
    }

    private ARRAY generateArray(Connection connection, List<RbiBondEodBodDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RbiBondEodBodDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[1];
            dto = list.get(index);
            paramArray[0] = dto.getBranchId();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

//    private ARRAY generateArrayCol(Connection connection, List<AccountValidationDto> list) throws Exception {
//        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_EOD_OBJ", connection);
//        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_EOD", connection);
//        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
//        AccountValidationDto dto = null;
//        for (int index = 0; index < list.size(); index++) {
//            Object[] paramArray = new Object[4];
//            dto = list.get(index);
//            paramArray[0] = dto.getAccNo();
//            paramArray[1] = dto.getSchemeCode();
//            paramArray[2] = dto.getCbsAccType();
//            paramArray[3] = dto.getBranchId();
//
//            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
//            genericTableInsertStructs[index] = struct;
//        }
//        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
//    }
//
//    private ARRAY generateArrayCon(Connection connection, List<AccountValidationDto> list) throws Exception {
//        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_EOD_AC_DET_OBJ", connection);
//        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_EOD_AC_DET", connection);
//        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
//        AccountValidationDto dto = null;
//        for (int index = 0; index < list.size(); index++) {
//            Object[] paramArray = new Object[4];
//            dto = list.get(index);
//            paramArray[0] = dto.getAccNo();
//            paramArray[1] = dto.getSchemeCode();
//            paramArray[2] = dto.getCbsAccType();
//            paramArray[3] = dto.getBranchId();
//
//            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
//            genericTableInsertStructs[index] = struct;
//        }
//        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
//    }
//
//    private ARRAY generateArrayTran(Connection connection, List<TransactionReponseDto> list) throws Exception {
//        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("GBM_RBI_BOND_TRAN_EOD_OBJ", connection);
//        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("GBM_RBI_BOND_TRAN_EOD", connection);
//        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
//        TransactionReponseDto dto = null;
//        for (int index = 0; index < list.size(); index++) {
//            Object[] paramArray = new Object[4];
//            dto = list.get(index);
//            paramArray[0] = dto.getGbmTranId();
//            paramArray[1] = dto.getCbsTranId();
//            paramArray[2] = dto.getCbsTranStatus();
//            paramArray[3] = dto.getCbsTranStatusDesc();
//            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
//            genericTableInsertStructs[index] = struct;
//        }
//        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
//    }


}
