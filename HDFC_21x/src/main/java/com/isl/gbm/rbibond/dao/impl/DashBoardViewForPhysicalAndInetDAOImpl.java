package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.DashBoardViewForPhysicalAndInetDAO;
import com.isl.gbm.rbibond.dto.*;
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
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class DashBoardViewForPhysicalAndInetDAOImpl implements DashBoardViewForPhysicalAndInetDAO {

    private static final Logger log = LoggerFactory.getLogger(DashBoardViewForPhysicalAndInetDAOImpl.class);


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO getFileTypeList(){
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareStatement("SELECT SUB_CODE AS SUB_CODE,SUB_CODE_DESC  AS SUB_CODE_DESC\n" +
                        "FROM GBM_RBI_BOND_SUB_CODE_MASTER WHERE CODE ='043'");
                resultSet = stmt.executeQuery();
                List<HashMap<String, String>> resultList = new ArrayList<>();
                if (resultSet != null)
                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("subCode", resultSet.getString("SUB_CODE"));
                        resultMap.put("subCodeDescription", resultSet.getString("SUB_CODE_DESC"));
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
    public ResponseDataDTO add(CashFileUploadVerifySelectRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_DASH_BOARD_VIEW_sp_add.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getSubCode());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getFileMode());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getLiquidationDate());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getValueDate());
                stmt.setArray(index++, generateTYP_GBM_VERIFY_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getTorchOut()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
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
    private ARRAY generateTYP_GBM_VERIFY_ARRAY_OBJ(Connection connection,List<CashFileUploadVerifySelectRequestDTO> list) throws Exception {
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
    public ResponseDataDTO verifySelect(CashFileUploadVerifySelectRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO1=new CashFileUploadVerifySelectRequestDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_DASH_BOARD_VIEW_sp_ver_sel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getSubCode());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getLiquidationDate());
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
                    resultSet = (ResultSet) stmt.getObject(5);
                    CashFileUploadVerifySelectRequestDTO output = null;
                    while (resultSet.next()) {
                        output = new CashFileUploadVerifySelectRequestDTO();
                        output.setFileName(resultSet.getString(1));
                        output.setCountOfRecord(resultSet.getString(2));
                        output.setTotalAmt(resultSet.getString(3));
                        torchOut.add(output);
                    }
                    resultSet1 = (ResultSet) stmt.getObject(6);
                    while (resultSet1.next()) {
                        Blob fileData = null;
                        String fileName = resultSet1.getString(1);
                        fileData = resultSet1.getBlob(2);
                        for (int i = 0; i < torchOut.size(); i++) {
                            if (fileData != null && fileName.equalsIgnoreCase(torchOut.get(i).getFileName())) {
                                byte[] bytes = fileData.getBytes(1, (int) fileData.length());
                                log.info("bytes- {}" , bytes);
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
                    cashFileUploadVerifySelectRequestDTO1.setTorchOut(torchOut);
                    if(null!=stmt.getString(11)){
                        cashFileUploadVerifySelectRequestDTO1.setValueDate(stmt.getString(11));}
                    responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO1);
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
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO addValidation(CashFileUploadVerifySelectRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO1=new CashFileUploadVerifySelectRequestDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_DASH_BOARD_VIEW_sp_add_val.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getSubCode());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getFileMode());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getLiquidationDate());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getValueDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                    cashFileUploadVerifySelectRequestDTO1.setTorchOut(torchOut);
                    responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO1);
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
        return responseDataDTO;    }
    @Override
    public ResponseDataDTO addRejection(CashFileUploadVerifySelectRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_DASH_BOARD_VIEW_sp_add_rejection.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getSubCode());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getFileMode());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getLiquidationDate());
                stmt.setArray(index++, generateTYP_GBM_VERIFY_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getTorchOut()));
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
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
    public ResponseDataDTO verify(CashFileUploadVerifySelectRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_DASH_BOARD_VIEW_sp_ver.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getSubCode());
                stmt.setArray(index++, generateTYP_GBM_VERIFY_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getTorchOut()));
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getLiquidationDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
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
    public ResponseDataDTO delete(CashFileUploadVerifySelectRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (cashFileUploadAndNetBankingFileUploadRequestDTO.getRejectionRemarks() == null
                || cashFileUploadAndNetBankingFileUploadRequestDTO.getRejectionRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_DASH_BOARD_VIEW_sp_del.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getSubCode());
                stmt.setArray(index++, generateTYP_GBM_VERIFY_ARRAY_OBJ(connection, cashFileUploadAndNetBankingFileUploadRequestDTO.getTorchOut()));
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getLiquidationDate());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
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
    public ResponseDataDTO inquiry(CashFileUploadVerifySelectRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO1=new CashFileUploadVerifySelectRequestDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_DASH_BOARD_VIEW_sp_inq.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getSubCode());
                stmt.setString(index++,cashFileUploadAndNetBankingFileUploadRequestDTO.getLiquidationDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                    resultSet = (ResultSet) stmt.getObject(5);
                    CashFileUploadVerifySelectRequestDTO output = null;
                    while (resultSet.next()) {
                        output = new CashFileUploadVerifySelectRequestDTO();
                        output.setFileName(resultSet.getString(1));
                        output.setCountOfRecord(resultSet.getString(2));
                        output.setTotalAmt(resultSet.getString(3));
                        torchOut.add(output);
                    }
                    resultSet1 = (ResultSet) stmt.getObject(6);
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
                    cashFileUploadVerifySelectRequestDTO1.setTorchOut(torchOut);
                    if(null!=stmt.getString(7)){
                    cashFileUploadVerifySelectRequestDTO1.setValueDate(stmt.getString(7));}
                    responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO1);
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
    public String utility(String value){
        if((null!=value)&&(!value.isEmpty())){
            return value.trim();
        }else{
            return value;
        }
    }
}
