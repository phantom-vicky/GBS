package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.RbiBondBodEodDao;
import com.isl.gbm.rbibond.dto.AccountValidationDto;
import com.isl.gbm.rbibond.dto.TransactionReponseDto;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.rbibond.dto.BodEodDto;
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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class RbiBondBodEodDaoImpl implements RbiBondBodEodDao {
    private static final Logger log = LoggerFactory.getLogger(RbiBondBodEodDaoImpl.class);


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;
    @Override
    public ResponseDataDTO startBodProcess(BodEodDto requestDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_gbm_rbi_bond_bod.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString(CommonConstants.PROC_PARAM_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString("P_MODULE_CODE_IN", CommonConstants.RBI_BOND_MODULE_ID);
                stmt.setString("P_LEVEL_CODE_IN", requestDto.getLevelCode());
                stmt.setString("P_REQ_TYPE_IN", requestDto.getProcTypeIn());
                stmt.registerOutParameter("P_FIN_ENTRY_FLG_OUT", Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);
                if (CommonConstants.SUCCESS.equalsIgnoreCase(stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT))) {
                    BodEodDto responseDto = new BodEodDto();
                    responseDto.setCbsEntryFlag(stmt.getString("P_FIN_ENTRY_FLG_OUT"));
                    responseDataDTO.setResult(responseDto);
                }
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
        return responseDataDTO;    }

    @Override
    public ResponseDataDTO startEodProcess(BodEodDto bodEodDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();


        List<GbmTransactionMasterDto> gbmTransactionMasterDtos = new ArrayList<>();
        GbmTransactionMasterDto gbmTransactionMasterDto = null;

        String resultMsg = "";

        BodEodDto rbiBondEodBodDTO = null;
        List<BodEodDto> rbiBondEodBodDTOList = new ArrayList<>();

        BodEodDto responseDto = new BodEodDto();


        BodEodDto amountCursor = null;
        List<BodEodDto> amountCursorList = new ArrayList();

        TransactionReponseDto transactionReponseDto = new TransactionReponseDto();
            if(bodEodDto.getValueFlag() != null && bodEodDto.getValueFlag().equalsIgnoreCase("TRAN")) {
                transactionReponseDto = (TransactionReponseDto) bodEodDto.getTransactionReponseDto();
            }
        try {
            log.info("BOD EOD DTO {}",bodEodDto);
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;

                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_EOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, CommonConstants.RBI_BOND_MODULE_ID);
                stmt.setString(index++, bodEodDto.getLevelCode());
                stmt.setString(index++, bodEodDto.getCashDepCheckFlg());
                stmt.setString(index++, bodEodDto.getProcTypeIn());

                if (bodEodDto.getCollectionAccountDet() != null && !bodEodDto.getCollectionAccountDet().isEmpty()){
                    stmt.setArray(index++, generateArrayCol(connection, bodEodDto.getCollectionAccountDet()));
                } else{
                    stmt.setNull(index++, Types.ARRAY, "TYP_RBI_BOND_EOD");
                }

                if (bodEodDto.getConsolidatedAccountDet() !=null && !bodEodDto.getConsolidatedAccountDet().isEmpty()){
                    stmt.setArray(index++, generateArrayCon(connection, bodEodDto.getConsolidatedAccountDet()));
                } else{
                    stmt.setNull(index++, Types.ARRAY, "TYP_RBI_BOND_EOD_AC_DET");
                }
                stmt.setString(index++, transactionReponseDto.getGbmTranId());
                stmt.setString(index++, transactionReponseDto.getCbsTranId());
                stmt.setString(index++, transactionReponseDto.getCbsTranStatus());
                stmt.setString(index++, transactionReponseDto.getCbsTranStatusDesc());

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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);



                if(stmt.getString(index-1) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {


                    responseDto.setEodStatus(stmt.getString(19));
                    responseDto.setEodFailedReason(stmt.getString(21));
                    responseDto.setEodStatusDesc(stmt.getString(20));


                    if (CommonConstants.FAILURE.equals(responseDto.getEodStatus())) {
                        ResultSet rs = null;
                        if (CommonConstants.EOD_FLAG_DEPOSIT_AMT_VALIDATION.equalsIgnoreCase(responseDto.getEodFailedReason())) {
                            AccountValidationDto tempAccValidationDto = new AccountValidationDto();
//                            tempAccValidationDto.setAccountName("DEPOSIT");
//                            tempAccValidationDto.setIsCentralized(stmt.getString("P_DEP_AC_CENT_FLG_OUT"));
//                            String[] solAndAccountDetails = stmt.getString("P_DEP_AC_DET_OUT").split("\\^");
//                            tempAccValidationDto.setBranchId(solAndAccountDetails[0]);
//                            tempAccValidationDto.setAccNo(solAndAccountDetails[1]);
                            responseDto.setDepositAccountDet(tempAccValidationDto);
                        } else if (CommonConstants.EOD_FLAG_NON_FINANCIAL_TRAN_PENDING.equalsIgnoreCase(responseDto.getEodFailedReason())) {
                            rs = (ResultSet) stmt.getObject(23);
                            List<BodEodDto.ErrorDescDto> tempList = new ArrayList<>();
                            if (rs != null) {
                                BodEodDto.ErrorDescDto tempDto;
                                while (rs.next()) {
                                    tempDto = new BodEodDto.ErrorDescDto();
                                    tempDto.setModuleName(rs.getString("MODULE"));
                                    tempDto.setLinkName(rs.getString("LINK_NAME"));
                                    tempDto.setDesc(rs.getString("RECORD_COUNT"));
                                    tempList.add(tempDto);
                                }
                            }
                            rs.close();
                            if (!org.springframework.util.CollectionUtils.isEmpty(tempList)) {
                                responseDto.setNonFinancialUnverifiedTranList(tempList);
                            } else {
                                throw new GenericBusinessException("NO NON-FINANCIAL UNVERIFIED RECORDS FOUND");
                            }
                        } else if (CommonConstants.EOD_FLAG_UNVERIFIED_TRAN_PENDING.equalsIgnoreCase(responseDto.getEodFailedReason())) {
                            rs = (ResultSet) stmt.getObject(23);
                            List<BodEodDto.ErrorDescDto> tempList = new ArrayList<>();
                            if (rs != null) {
                                BodEodDto.ErrorDescDto tempDto;
                                while (rs.next()) {
                                    tempDto = new BodEodDto.ErrorDescDto();
                                    tempDto.setModuleName(rs.getString("MODULE"));
                                    tempDto.setLinkName(rs.getString("LINK_NAME"));
                                    tempDto.setDesc(rs.getString(CommonConstants.GBM_TRAN_ID_STRING));
                                    tempList.add(tempDto);
                                }
                            }
                            rs.close();
                            if (!org.springframework.util.CollectionUtils.isEmpty(tempList)) {
                                responseDto.setFinancialUnverifiedTranList(tempList);
                            } else {
                                throw new GenericBusinessException("NO FINANCIAL UNVERIFIED RECORDS FOUND");
                            }
                        }
                    }
                    else {
                        AccountValidationDto tempAccValidationDto = null;
                        List<AccountValidationDto> accountValidationDtoList = new ArrayList<>();

                        if (CommonConstants.EOD_FLAG_CASH_BAL_VALIDATION.equalsIgnoreCase(bodEodDto.getCashDepCheckFlg())) {


                            //cash account details
                            ResultSet rs3 = (ResultSet) stmt.getObject(22);

                            while(rs3.next()) {
                                tempAccValidationDto = new AccountValidationDto();
                                tempAccValidationDto.setAccountName("CASH" + rs3.getString(3));
                                tempAccValidationDto.setIsCentralized(rs3.getString(1));
//                                tempAccValidationDto.setBranchId(rs3.getString(4));
                                tempAccValidationDto.setAccNo(rs3.getString(2));
                                accountValidationDtoList.add(tempAccValidationDto);
                            }
                            rs3.close();
                        } else if (CommonConstants.PROC_TYPE_IN_REQUEST.equals(bodEodDto.getProcTypeIn())) {



                            //gives amount for each sol id
                            ResultSet rs4 = (ResultSet) stmt.getObject(26);
                            while(rs4.next()){

                                amountCursor = new BodEodDto();
                                amountCursor.setSchemeCode(rs4.getString(1));
                                amountCursor.setAmount(rs4.getString(2));
                                amountCursor.setBranchId(rs4.getString(3));
                                amountCursorList.add(amountCursor);

                            }
                            responseDto.setAmtValidationList(amountCursorList);
                            rs4.close();

                            // collection branch account numbers to be validated
                            ResultSet rs6 = (ResultSet) stmt.getObject(28);
                            while(rs6.next()){

                                rbiBondEodBodDTO = new BodEodDto();
                                rbiBondEodBodDTO.setBondColAccCenFlag(rs6.getString(1));
                                rbiBondEodBodDTO.setBondColAcc(rs6.getString(2));
                                rbiBondEodBodDTO.setBondConAccCenFlag(rs6.getString(3));
                                rbiBondEodBodDTO.setBondConAcc(rs6.getString(4));
                                rbiBondEodBodDTO.setBondPayAccCenFlag(rs6.getString(5));
                                rbiBondEodBodDTO.setBondPayAcc(rs6.getString(6));
                                rbiBondEodBodDTO.setBondRecAccCenFlag(rs6.getString(7));
                                rbiBondEodBodDTO.setBondRecAcc(rs6.getString(8));
                                rbiBondEodBodDTO.setSchemeCode(rs6.getString(9));
                                rbiBondEodBodDTOList.add(rbiBondEodBodDTO);
                            }
                            responseDto.setAccValidationList(rbiBondEodBodDTOList);
                            rs6.close();

//                            ResultSet rs7 = (ResultSet) stmt.getObject(26);
//                            while(rs7.next()){
//
//                                rbiBondEodBodDTO = new BodEodDto();
//
//                                rbiBondEodBodDTO.setBranchId(rs7.getString(3));
//                                rbiBondEodBodDTO.setSchemeCode(rs7.getString(4));
//                                rbiBondEodBodDTOList.add(rbiBondEodBodDTO);
//                            }
//                            responseDto.setAccValidationList(rbiBondEodBodDTOList);
//                            rs7.close();


                        } else if (CommonConstants.PROC_TYPE_IN_RESPONSE.equals(bodEodDto.getProcTypeIn())) {

                            Date tranDate = null;
                            try {
                                tranDate = new SimpleDateFormat("dd/MM/yyyy").parse(stmt.getString(16));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            ResultSet rs1 = (ResultSet) stmt.getObject(24);

                            //details to proceed with transaction
                            while(rs1.next()){

                                gbmTransactionMasterDto = new GbmTransactionMasterDto();
                                gbmTransactionMasterDto.setAcctNum(rs1.getString(1));
                                gbmTransactionMasterDto.setTranLeg(rs1.getString(2).charAt(0));
                                gbmTransactionMasterDto.setAmount(Double.parseDouble(rs1.getString(3)));
                                gbmTransactionMasterDto.setSolId(stmt.getString(15));
                                gbmTransactionMasterDto.setTransactionChannel(stmt.getString(13));
                                gbmTransactionMasterDto.setTranDate(tranDate);
                                gbmTransactionMasterDto.setSchemeCode(stmt.getString(17));
                                gbmTransactionMasterDto.setScreenTranCode(stmt.getString(18));
                                gbmTransactionMasterDto.setTranId(stmt.getString(14));
                                gbmTransactionMasterDto.setGbmTranId(stmt.getString(14));
                                gbmTransactionMasterDtos.add(gbmTransactionMasterDto);

                            }
                            responseDto.setTranList(gbmTransactionMasterDtos);
                            rs1.close();

                        } else if ("2".equals(bodEodDto.getProcTypeIn())) {
//                            Successful EOD
                        } else if ("3".equals(bodEodDto.getProcTypeIn())) {
//                            Successful EOD
                        }
                    }
                }
                responseDataDTO.setResult(responseDto);

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

    private ARRAY generateArrayCol(Connection connection, List<AccountValidationDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_EOD_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_EOD", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        AccountValidationDto dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[4];
            dto = list.get(index);
            paramArray[0] = dto.getAccNo();
            paramArray[1] = dto.getSchemeCode();
            paramArray[2] = dto.getCbsAccType();
            paramArray[3] = dto.getBranchId();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private ARRAY generateArrayCon(Connection connection, List<AccountValidationDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_EOD_AC_DET_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_EOD_AC_DET", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        AccountValidationDto dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[4];
            dto = list.get(index);
            paramArray[0] = dto.getAccNo();
            paramArray[1] = dto.getSchemeCode();
            paramArray[2] = dto.getCbsAccType();
            paramArray[3] = dto.getBranchId();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
}
