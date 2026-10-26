package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.TrailRunBrokerageDAO;
import com.isl.gbm.rbibond.dto.TrailRunBrokerageDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static org.apache.poi.ss.util.CellUtil.createCell;


@Repository
public class TrailRunBrokerageDAOimpl implements TrailRunBrokerageDAO {

    private static final Logger log = LoggerFactory.getLogger(TrailRunBrokerageDAOimpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO getBrokerDetails(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return null;
    }

    @Override
    public ResponseDataDTO add(TrailRunBrokerageDTO bondBrokeragePaymentDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_PAY_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getBrokerId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getSchemeCode());
                stmt.setString(index++, bondBrokeragePaymentDTO.getFromDate());

                stmt.setString(index++, bondBrokeragePaymentDTO.getToDate());
                if (bondBrokeragePaymentDTO.getBrokerDetailsList() != null && bondBrokeragePaymentDTO.getBrokerDetailsList().size() > 0) {
                    stmt.setArray(index++, generateArrayForBrokerDetailsAdd(connection, bondBrokeragePaymentDTO.getBrokerDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, "TYP_GBM_RBI_BOND_BROK_DTLS");
                }

                stmt.setString(index++, bondBrokeragePaymentDTO.getTotalAmount());
                stmt.setString(index++, bondBrokeragePaymentDTO.getCreditAccType());
                stmt.setString(index++, bondBrokeragePaymentDTO.getCreditAccNo());
                stmt.setString(index++, bondBrokeragePaymentDTO.getDebitAccNo());
                stmt.setString(index++, bondBrokeragePaymentDTO.getDebitAccType());

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                //15
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    bondBrokeragePaymentDTO.setGbsTranId(stmt.getString(13));
                    responseDataDTO.setResult(bondBrokeragePaymentDTO);
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
        return responseDataDTO;
    }

    private Array generateArrayForBrokerDetailsAdd(Connection connection, List<TrailRunBrokerageDTO.BrokerDetails> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_BROK_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_BROK_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[7];
            TrailRunBrokerageDTO.BrokerDetails item = list.get(index);
//            paramArray[0] = item.getBlaNo();
//            paramArray[1] = item.getSubscriptionDate();
//            paramArray[2] = item.getSubscriptionId();
//            paramArray[3] = item.getSubscriptionAmount();
//            paramArray[4] = item.getBrokerAmount();
//            paramArray[5] = item.getPaidAmount();
//            paramArray[6] = item.getNarration();

            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    private Array generateArrayForBlaDetailsAdd(Connection connection, List<TrailRunBrokerageDTO.BlaDetails> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[1];
            TrailRunBrokerageDTO.BlaDetails item = list.get(index);
            paramArray[0] = item.getBranchRefNo();
//            paramArray[1] = item.getSubscriptionDate();
//            paramArray[2] = item.getSubscriptionId();
//            paramArray[3] = item.getSubscriptionAmount();
//            paramArray[4] = item.getBrokerAmount();
//            paramArray[5] = item.getPaidAmount();
//            paramArray[6] = item.getNarration();

            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


    @Override
    public ResponseDataDTO getTrailRun(TrailRunBrokerageDTO bondBrokeragePaymentDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TRAIL_RUN_BRK_SCH_NAME.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getSchemeCode());
                stmt.setString(index++, bondBrokeragePaymentDTO.getAgencyCode());
                stmt.setString(index++, bondBrokeragePaymentDTO.getFromDate());
                stmt.setString(index++, bondBrokeragePaymentDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(bondBrokeragePaymentDTO.getSchemeCode() == null){
                    ResultSet resultSet = (ResultSet) stmt.getObject(7);


                        if(resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("schemeName", resultSet.getString(2));
                                item.put("schemeStatus", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }

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
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getTrailRunVerDetail(TrailRunBrokerageDTO bondBrokeragePaymentDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        TrailRunBrokerageDTO trailRunBrokerageDTO = new TrailRunBrokerageDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TRAIL_RUN_BRK_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getSchemeCode());
                stmt.setString(index++, bondBrokeragePaymentDTO.getAgencyCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(bondBrokeragePaymentDTO.getSchemeCode() == null){
                        ResultSet resultSet = (ResultSet) stmt.getObject(5);


                        if(resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("schemeName", resultSet.getString(2));
                                item.put("schemeStatus", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else {

                        ResultSet resultSet = (ResultSet) stmt.getObject(5);


                        if(resultSet != null) {
                            List<Map<String, String>> blaList = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();
                                item.put("blaNo",resultSet.getString(1));
                                item.put("investAmt",resultSet.getString(2));
                                item.put("casTransferDate",resultSet.getString(3));
                                item.put("maturityDate",resultSet.getString(4));
                                item.put("scheme",resultSet.getString(5));
                                item.put("cfd",resultSet.getString(6));
                                item.put("branchRefNo",resultSet.getString(7));
                                item.put("panNo",resultSet.getString(8));
                                item.put("bankName",resultSet.getString(9));
                                item.put("applicantName",resultSet.getString(10));
                                item.put("adviceNo",resultSet.getString(11));
                                item.put("applAdd1",resultSet.getString(12));
                                item.put("applAdd2",resultSet.getString(13));
                                item.put("city",resultSet.getString(14));
                                item.put("state",resultSet.getString(15));
                                item.put("pincode",resultSet.getString(16));
                                item.put("branchName",resultSet.getString(17));
                                item.put("rcptDate",resultSet.getString(18));
                                item.put("groupCode",resultSet.getString(19));
                                item.put("hdfcBrokerCode",resultSet.getString(20));
                                item.put("brokerName",resultSet.getString(21));
                                item.put("brokerAdd1",resultSet.getString(22));
                                item.put("brokerAdd2",resultSet.getString(23));
                                item.put("brokerCity",resultSet.getString(24));
                                item.put("state1",resultSet.getString(25));
                                item.put("brokerPinCode",resultSet.getString(26));
                                item.put("bPhone",resultSet.getString(27));
                                item.put("brokerEmailId",resultSet.getString(28));
                                item.put("brokerPan",resultSet.getString(29));
                                item.put("paymentMode",resultSet.getString(30));
                                item.put("accountTypeCode",resultSet.getString(31));
                                item.put("bankAccount",resultSet.getString(32));
                                item.put("micrCode",resultSet.getString(33));
                                item.put("applNo",resultSet.getString(34));
                                item.put("status",resultSet.getString(35));
                                item.put("deStatus",resultSet.getString(36));
                                item.put("grossBrok",resultSet.getString(37));
                                item.put("gst",resultSet.getString(38));
                                item.put("netBrok",resultSet.getString(39));
                                item.put("applPanNo1",resultSet.getString(40));
                                item.put("applPanNo2",resultSet.getString(41));
                                blaList.add(item);
                            }
                            trailRunBrokerageDTO.setBlaList(blaList);
                        }


                        ResultSet resultSet1 = (ResultSet) stmt.getObject(6);


                        if(resultSet1 != null) {
                            List<Map<String, String>> brokerList = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet1.next()){
                                item = new HashMap<>();
                                item.put("brokerName",resultSet1.getString(1));
                                item.put("brokerCode",resultSet1.getString(2));
                                item.put("brokerPan",resultSet1.getString(3));
                                item.put("brokerEmailId",resultSet1.getString(4));
                                item.put("totalInvstAmt",resultSet1.getString(5));

                                item.put("grossBrokerage",resultSet1.getString(6));

                                item.put("netBrokerage",resultSet1.getString(7));
                                item.put("gst",resultSet1.getString(8));
                                item.put("gstinNo",resultSet1.getString(9));

                                brokerList.add(item);
                            }
                            trailRunBrokerageDTO.setBrokerList(brokerList);
                        }

                        responseDataDTO.setResult(trailRunBrokerageDTO);
                    }

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
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAgencyCodeAddTor(TrailRunBrokerageDTO bondBrokeragePaymentDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TRAIL_RUN_BRK_AGENCY_CODE_ADD_TOR.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(4);


                        if(resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();
                                item.put("agencyCode", resultSet.getString(1));
                                item.put("brokerName", resultSet.getString(2));
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
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAgencyCodeVerTor(TrailRunBrokerageDTO bondBrokeragePaymentDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TRAIL_RUN_BRK_AGENCY_CODE_VER_TOR.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(4);


                    if(resultSet != null) {
                        List<Map<String, String>> list = new ArrayList<>();

                        Map<String, String> item = null;
                        while (resultSet.next()){
                            item = new HashMap<>();
                            item.put("brokerName", resultSet.getString(1));
                            item.put("agencyCode", resultSet.getString(2));
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
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySelect(TrailRunBrokerageDTO bondBrokeragePaymentDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_PAY_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getBrokerId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getGbsTranId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getSchemeCode());

                stmt.setString(index++, bondBrokeragePaymentDTO.getFromDate());
                stmt.setString(index++, bondBrokeragePaymentDTO.getToDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
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
                //10
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(15);
                    if(bondBrokeragePaymentDTO.getBrokerId() == null && bondBrokeragePaymentDTO.getGbsTranId() == null){

                        if(resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();
                                item.put("brokerAgencyCode", resultSet.getString(1));
                                item.put("brokerName", resultSet.getString(2));
                                item.put("branchId", resultSet.getString(3));
                                item.put("verifiedStatus", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }
                    if(bondBrokeragePaymentDTO.getBrokerId() != null && bondBrokeragePaymentDTO.getGbsTranId() == null){
                        if(resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()){
                                item = new HashMap<>();
                                item.put("brokerAgencyCode", resultSet.getString(1));
                                item.put("gbsTranId", resultSet.getString(2));
                                item.put("brokerAmount", resultSet.getString(3));
                                item.put("verifiedStatus", resultSet.getString(4));
                                item.put("schemeCode", resultSet.getString(5));
                                item.put("fromDate", resultSet.getString(6));
                                item.put("toDate", resultSet.getString(7));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    if(bondBrokeragePaymentDTO.getBrokerId() != null && bondBrokeragePaymentDTO.getGbsTranId() != null){
                        if(resultSet != null) {
                            List<TrailRunBrokerageDTO.BrokerDetails> list = new ArrayList<>();
                            TrailRunBrokerageDTO.BrokerDetails item = null;
                            while (resultSet.next()){
//                                item = new TrailRunBrokerageDTO.BrokerDetails();
//                                item.setBlaNo(resultSet.getString(1));
//                                item.setSubscriptionDate( resultSet.getString(2));
//                                item.setSubscriptionId(resultSet.getString(3));
//                                item.setSubscriptionAmount( resultSet.getString(4));
//                                item.setBrokerAmount(resultSet.getString(5));
//                                item.setPaidAmount( resultSet.getString(6));
//                                list.add(item);
                            }
                            bondBrokeragePaymentDTO.setBrokerDetailsList(list);

                        }

                        bondBrokeragePaymentDTO.setCreatedBy(stmt.getString(8));
                        bondBrokeragePaymentDTO.setCreatedDate(stmt.getString(9));
                        bondBrokeragePaymentDTO.setVerifiedBy(stmt.getString(12));
                        bondBrokeragePaymentDTO.setVerifiedDate(stmt.getString(13));
                        bondBrokeragePaymentDTO.setVerifiedStatus(stmt.getString(14));
                        responseDataDTO.setResult(bondBrokeragePaymentDTO);
                    }

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
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyResponse(TrailRunBrokerageDTO bondBrokeragePaymentDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_PAY_VER_RES.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getGbsTranId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getCbsTranId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getCbsTranStatus());

                stmt.setString(index++, bondBrokeragePaymentDTO.getCbsTranStatusDescription());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    bondBrokeragePaymentDTO.setCbsTranId(stmt.getString(7));
                    bondBrokeragePaymentDTO.setCbsTranStatus(stmt.getString(8));
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
        responseDataDTO.setResult(bondBrokeragePaymentDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(TrailRunBrokerageDTO bondBrokeragePaymentDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TRAIL_RUN_BRK_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getSchemeCode());
                stmt.setArray(index++, generateArrayForBlaDetailsAdd(connection, bondBrokeragePaymentDTO.getAgencyCodeList()));

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO delete(TrailRunBrokerageDTO bondBrokeragePaymentDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TRAIL_RUN_BRK_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getSchemeCode());
                stmt.setArray(index++, generateArrayForBlaDetailsAdd(connection, bondBrokeragePaymentDTO.getAgencyCodeList()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO download(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        TrailRunBrokerageDTO.BlaDetails blaDetailsDto = null;
        TrailRunBrokerageDTO.BrokerDetails brokerDetailsDto = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        List<TrailRunBrokerageDTO.BlaDetails> blaResponseDTOList = null;
        List<TrailRunBrokerageDTO.BrokerDetails> brokerResponseDTOList = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if(trailRunBrokerageDTO.getFlag().equalsIgnoreCase("A")) {
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TRAIL_RUN_BRK_DOWNLOAD.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, trailRunBrokerageDTO.getSchemeCode());
                    stmt.setString(index++, trailRunBrokerageDTO.getAgencyCode());
                    stmt.setString(index++, trailRunBrokerageDTO.getFromDate());
                    stmt.setString(index++, trailRunBrokerageDTO.getToDate());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                } else if (trailRunBrokerageDTO.getFlag().equalsIgnoreCase("V")) {
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TRAIL_RUN_BRK_VER_SEL.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, trailRunBrokerageDTO.getSchemeCode());
                    stmt.setString(index++, trailRunBrokerageDTO.getAgencyCode());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                }
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    if (trailRunBrokerageDTO.getFlag().equalsIgnoreCase("A")) {
                        resultSet1 = (ResultSet) stmt.getObject(7);

                    } else if (trailRunBrokerageDTO.getFlag().equalsIgnoreCase("V")){
                         resultSet1 = (ResultSet) stmt.getObject(5);
                        }
                    blaResponseDTOList = new ArrayList<>();
                        if (resultSet1 != null) {
                            while (resultSet1.next()) {
                                blaDetailsDto= new TrailRunBrokerageDTO.BlaDetails();

                                blaDetailsDto.setBlaNo(resultSet1.getString(1));
                                blaDetailsDto.setInvestAmt(resultSet1.getString(2));
                                blaDetailsDto.setCasTransferDate(resultSet1.getString(3));
                                blaDetailsDto.setMaturityDate(resultSet1.getString(4));
                                blaDetailsDto.setScheme(resultSet1.getString(5));
                                blaDetailsDto.setCfd(resultSet1.getString(6));
                                blaDetailsDto.setBranchRefNo(resultSet1.getString(7));
                                blaDetailsDto.setPanNo(resultSet1.getString(8));
                                blaDetailsDto.setBankName(resultSet1.getString(9));
                                blaDetailsDto.setApplicantName(resultSet1.getString(10));
                                blaDetailsDto.setAdviceNo(resultSet1.getString(11));
                                blaDetailsDto.setApplAdd1(resultSet1.getString(12));
                                blaDetailsDto.setApplAdd2(resultSet1.getString(13));
                                blaDetailsDto.setCity(resultSet1.getString(14));
                                blaDetailsDto.setState(resultSet1.getString(15));
                                blaDetailsDto.setPincode(resultSet1.getString(16));
                                blaDetailsDto.setBranchName(resultSet1.getString(17));
                                blaDetailsDto.setRcptDate(resultSet1.getString(18));
                                blaDetailsDto.setGroupCode(resultSet1.getString(19));
                                blaDetailsDto.setHdfcBrokerCode(resultSet1.getString(20));
                                blaDetailsDto.setBrokerName(resultSet1.getString(21));
                                blaDetailsDto.setBrokerAdd1(resultSet1.getString(22));
                                blaDetailsDto.setBrokerAdd2(resultSet1.getString(23));
                                blaDetailsDto.setBrokerCity(resultSet1.getString(24));
                                blaDetailsDto.setState1(resultSet1.getString(25));
                                blaDetailsDto.setBrokerPinCode(resultSet1.getString(26));
                                blaDetailsDto.setbPhone(resultSet1.getString(27));
                                blaDetailsDto.setBrokerEmailId(resultSet1.getString(28));
                                blaDetailsDto.setBrokerPan(resultSet1.getString(29));
                                blaDetailsDto.setPaymentMode(resultSet1.getString(30));
                                blaDetailsDto.setAccountTypeCode(resultSet1.getString(31));
                                blaDetailsDto.setBankAccount(resultSet1.getString(32));
                                blaDetailsDto.setMicrCode(resultSet1.getString(33));
                                blaDetailsDto.setApplNo(resultSet1.getString(34));
                                blaDetailsDto.setStatus(resultSet1.getString(35));
                                blaDetailsDto.setDeStatus(resultSet1.getString(36));
                                blaDetailsDto.setGrossBrok(resultSet1.getString(37));
                                blaDetailsDto.setGst(resultSet1.getString(38));
                                blaDetailsDto.setNetBrok(resultSet1.getString(39));
                                blaDetailsDto.setApplPanNo1(resultSet1.getString(40));
                                blaDetailsDto.setApplPanNo2(resultSet1.getString(41));

                                blaResponseDTOList.add(blaDetailsDto);

                            }
                            /*resultMap = new HashMap<>();
                            resultMap.put("totalAmount", totalAmount);
                            resultMap.put("detailResponseDTOList", detailResponseDTOList);
                            responseDataDTO.setResult(resultMap);*/

                            trailRunBrokerageDTO.setBlaResponseList(blaResponseDTOList);
                        }

                    if (trailRunBrokerageDTO.getFlag().equalsIgnoreCase("A")) {
                         resultSet2 = (ResultSet) stmt.getObject(8);

                    } else if (trailRunBrokerageDTO.getFlag().equalsIgnoreCase("V")){
                        resultSet2 = (ResultSet) stmt.getObject(6);
                    }
                    brokerResponseDTOList = new ArrayList<>();
                        if (resultSet2 != null) {
                            while (resultSet2.next()) {
                                brokerDetailsDto= new TrailRunBrokerageDTO.BrokerDetails();

                                brokerDetailsDto.setBrokerName(resultSet2.getString(1));
                                brokerDetailsDto.setBrokerCode(resultSet2.getString(2));
                                brokerDetailsDto.setBrokerPan(resultSet2.getString(3));
                                brokerDetailsDto.setBrokerEmailId(resultSet2.getString(4));
                                brokerDetailsDto.setTotalInvstAmt(resultSet2.getString(5));
                                brokerDetailsDto.setGrossBrokerage(resultSet2.getString(6));
                                brokerDetailsDto.setNetBrokerage(resultSet2.getString(7));
                                brokerDetailsDto.setGst(resultSet2.getString(8));
                                brokerDetailsDto.setGstinNo(resultSet2.getString(9));


                                brokerResponseDTOList.add(brokerDetailsDto);

                            }
                          /*  resultMap.put("totalAmount", totalAmount);
                            resultMap.put("totalNoOfPaymentRefId", totalNoOfPaymentRefId);
                            resultMap.put("summaryResponseDTOList", summaryResponseDTOList);
                            responseDataDTO.setResult(resultMap);*/

                            trailRunBrokerageDTO.setBrokerResponseList(brokerResponseDTOList);

                        }



                    log.info("=-=-=-=-=-=-=----> fileResponseDto.getBlaResponseList " , trailRunBrokerageDTO.getBlaResponseList().size());
                    log.info("=-=-=-=-=-=-=----> fileResponseDto.getBrokerResponseList " , trailRunBrokerageDTO.getBrokerResponseList().size());
                    responseDataDTO.setResult(trailRunBrokerageDTO);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                   /* if (trailRunBrokerageDTO.getBlaResponseList().size() > 0 || trailRunBrokerageDTO.getBrokerResponseList().size() > 0) {
                        FileResponseDto fileResponseDto = new FileResponseDto();
                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(excelFileGeneratorReport(blaResponseDTOList, brokerResponseDTOList, trailRunBrokerageDTO, String.valueOf(totalAmount))));
                        log.info("=-=-=-=-=-=-=----> fileResponseDto.getFileData " + fileResponseDto.getFileData());
                        fileResponseDto.setFileName("TRAIL RUN BROKERAGE.xlsx");
                        fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                        responseDataDTO.setResult(fileResponseDto);
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                    }*/
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

    public byte[] excelFileGeneratorReport(List<TrailRunBrokerageDTO.BlaDetails> blaDetailList,List<TrailRunBrokerageDTO.BrokerDetails> brokerDetailList, TrailRunBrokerageDTO trailRunBrokerageDTO , String totalAmount) throws IOException {


        XSSFWorkbook workbook = new XSSFWorkbook();
        final String sheet1="sheet1";
        XSSFSheet sheet = workbook.createSheet(sheet1);

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

//            CellStyle centerStyle = workbook.createCellStyle();
//            centerStyle.setAlignment(HorizontalAlignment.CENTER);

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row,6, "Trial run for Brokerage", style);


        row = sheet.createRow(1);
        createCell(row, 6, "Branch Id :",style);
        createCell(row,7, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(2);
        createCell(row, 6, "From Date :",style);
        createCell(row,7, trailRunBrokerageDTO.getFromDate());

        row = sheet.createRow(3);
        createCell(row, 6, "To Date :",style);
        createCell(row,7, trailRunBrokerageDTO.getToDate());


        int n = 9;
        if(trailRunBrokerageDTO.getBlaResponseList().size() > 0) {
            row = sheet.createRow(5);
            createCell(row, 6, "BLA DETAILS :",style);
            row = sheet.createRow(7);
            createCell(row, 0, "S.No", style);
            createCell(row, 1, "BLA No", style);
            createCell(row, 2, "Investment Amount", style);
            createCell(row, 3, "CAS Transfer Date.", style);
            createCell(row, 4, "Maturity Date", style);
            createCell(row, 5, "Scheme", style);
            createCell(row, 6, "CFD", style);
            createCell(row, 7, "SUBSCRIPTION ID", style);
            createCell(row, 8, "APPLICANT PAN NO 1", style);
            createCell(row, 9, "APPLICANT PAN NO 2", style);
            createCell(row, 10, "APPLICANT PAN NO 3", style);
            createCell(row, 11, "Bank Name.", style);
            createCell(row, 12, "Applicant Name", style);
            createCell(row, 13, "Advice No", style);
            createCell(row, 14, "Applicant Address1", style);
            createCell(row, 15, "Applicant Adddress2", style);
            createCell(row, 16, "City", style);
            createCell(row, 17, "State", style);
            createCell(row, 18, "Pin Code", style);
            createCell(row, 19, "Branch Name", style);
            createCell(row, 20, "Reciept Date", style);
            createCell(row, 21, "Group Code", style);
            createCell(row, 22, "HDFC Broker Code", style);
            createCell(row, 23, "Broker Name", style);
            createCell(row, 24, "Broker Address1", style);
            createCell(row, 25, "Broker Address2", style);
            createCell(row, 26, "Broker City", style);
            createCell(row, 27, "State1", style);
            createCell(row, 28, "Broker Pin Code", style);
            createCell(row, 29, "Broker Phone", style);
            createCell(row, 30, "Broker Email Id", style);
            createCell(row, 31, "Broker Pan", style);
            createCell(row, 32, "Payment Mode", style);
            createCell(row, 33, "Account Type Code", style);
            createCell(row, 34, "Bank Account", style);
            createCell(row, 35, "MICR CODE", style);
            createCell(row, 36, "Applicant No", style);
            createCell(row, 37, "Status", style);
            createCell(row, 38, "DE Status", style);
            createCell(row, 39, "Gross Broker", style);
            createCell(row, 40, "GST", style);
            createCell(row, 41, "Net Broker", style);


            int index = 1;


            for (TrailRunBrokerageDTO.BlaDetails item : blaDetailList) {
                row = sheet.createRow(n++);

                createCell(row, 0, String.valueOf(index++));
                createCell(row, 1, (String) item.getBlaNo());
                createCell(row, 2, (String) item.getInvestAmt());
                createCell(row, 3, (String) item.getCasTransferDate());
                createCell(row, 4, (String) item.getMaturityDate());
                createCell(row, 5, (String) item.getScheme());
                createCell(row, 6, (String) item.getCfd());
                createCell(row, 7, (String) item.getBranchRefNo());
                createCell(row, 8, (String) item.getPanNo());
                createCell(row, 9, (String) item.getApplPanNo1());
                createCell(row, 10, (String) item.getApplPanNo2());
                createCell(row, 11, (String) item.getBankName());
                createCell(row, 12, (String) item.getApplicantName());
                createCell(row, 13, (String) item.getAdviceNo());
                createCell(row, 14, (String) item.getApplAdd1());
                createCell(row, 15, (String) item.getApplAdd2());
                createCell(row, 16, (String) item.getCity());
                createCell(row, 17, (String) item.getState());
                createCell(row, 18, (String) item.getPincode());
                createCell(row, 19, (String) item.getBranchName());
                createCell(row, 20, (String) item.getRcptDate());
                createCell(row, 21, (String) item.getGroupCode());
                createCell(row, 22, (String) item.getHdfcBrokerCode());
                createCell(row, 23, (String) item.getBrokerName());
                createCell(row, 24, (String) item.getBrokerAdd1());
                createCell(row, 25, (String) item.getBrokerAdd2());
                createCell(row, 26, (String) item.getBrokerCity());
                createCell(row, 27, (String) item.getState1());
                createCell(row, 28, (String) item.getBrokerPinCode());
                createCell(row, 29, (String) item.getbPhone());
                createCell(row, 30, (String) item.getBrokerEmailId());
                createCell(row, 31, (String) item.getBrokerPan());
                createCell(row, 32, (String) item.getPaymentMode());
                createCell(row, 33, (String) item.getAccountTypeCode());
                createCell(row, 34, (String) item.getBankAccount());
                createCell(row, 35, (String) item.getMicrCode());
                createCell(row, 36, (String) item.getApplNo());
                createCell(row, 37, (String) item.getStatus());
                createCell(row, 38, (String) item.getDeStatus());
                createCell(row, 39, (String) item.getGrossBrok());
                createCell(row, 40, (String) item.getGst());
                createCell(row, 41, (String) item.getNetBrok());
            }



            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);
            sheet.autoSizeColumn(16);
            sheet.autoSizeColumn(17);
            sheet.autoSizeColumn(18);
            sheet.autoSizeColumn(19);
            sheet.autoSizeColumn(20);
            sheet.autoSizeColumn(21);
            sheet.autoSizeColumn(22);
            sheet.autoSizeColumn(23);
            sheet.autoSizeColumn(24);
            sheet.autoSizeColumn(25);
            sheet.autoSizeColumn(26);
            sheet.autoSizeColumn(27);
            sheet.autoSizeColumn(28);
            sheet.autoSizeColumn(29);
            sheet.autoSizeColumn(30);
            sheet.autoSizeColumn(31);
            sheet.autoSizeColumn(32);
            sheet.autoSizeColumn(33);
            sheet.autoSizeColumn(34);
            sheet.autoSizeColumn(35);
            sheet.autoSizeColumn(36);
            sheet.autoSizeColumn(37);
            sheet.autoSizeColumn(38);
            sheet.autoSizeColumn(39);
            sheet.autoSizeColumn(40);
            sheet.autoSizeColumn(41);


        }
        n++;
        if(trailRunBrokerageDTO.getBrokerResponseList().size() > 0){
            row = sheet.createRow(n++);
            createCell(row, 6, "BROKER DETAILS :",style);
            row = sheet.createRow(n++);
            createCell(row, 0, "S.No", style);
            createCell(row, 1, "Broker Name", style);
            createCell(row, 2, "Broker Code", style);
            createCell(row, 3, "GSTIN Number", style);
            createCell(row, 4, "Broker PAN", style);
            createCell(row, 5, "Broker Email ID", style);
            createCell(row, 6, "Total Investment Amount", style);
            createCell(row, 7, "Gross Brokerage", style);
            createCell(row, 8, "Net Brokerage", style);
            createCell(row, 9, "GST", style);


            int index = 1;


            for (TrailRunBrokerageDTO.BrokerDetails item : brokerDetailList) {
                row = sheet.createRow(n++);

                createCell(row, 0, String.valueOf(index++));
                createCell(row, 1, (String) item.getBrokerName());
                createCell(row, 2, (String) item.getBrokerCode());
                createCell(row, 3, (String) item.getGstinNo());
                createCell(row, 4, (String) item.getBrokerPan());
                createCell(row, 5, (String) item.getBrokerEmailId());
                createCell(row, 6, (String) item.getTotalInvstAmt());
                createCell(row, 7, (String) item.getGrossBrokerage());
                createCell(row, 8, (String) item.getNetBrokerage());
                createCell(row, 9, (String) item.getGst());


            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);


        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        log.info(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }



}
