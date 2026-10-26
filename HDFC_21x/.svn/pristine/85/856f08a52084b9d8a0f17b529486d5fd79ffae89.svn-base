package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.CancellationSubscriptionCatCbtDao;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CancellationSubscriptionCatCbtDaoImpl implements CancellationSubscriptionCatCbtDao {
    private static final Logger log = LoggerFactory.getLogger(CancellationSubscriptionCatCbtDaoImpl.class);
    private static final String PAN_REGEX =
            "^[A-Z]{5}[0-9]{4}[A-Z]$";
    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Override
    public ResponseDataDTO addCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (bondLedgerAccountMaintenanceDTO.getFlag() != null &&bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("D")) {
            String closerType = bondLedgerAccountMaintenanceDTO.getCloserType();
            if(closerType ==null || closerType.trim().isEmpty()  || !(closerType.equalsIgnoreCase("CAT") || closerType.equalsIgnoreCase("CBT"))){
                responseDataDTO.setMessage("Closer Type is mandatory and must be CAT or CBT");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String pan = bondLedgerAccountMaintenanceDTO.getPanNum();
            if (pan != null
                    && !pan.trim().isEmpty()
                    && !pan.replaceAll("\\s+", "").toUpperCase().matches(PAN_REGEX)) {
                responseDataDTO.setMessage("PAN must be valid (e.g., ABCDE1234F)");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String bla = bondLedgerAccountMaintenanceDTO.getBlaNo();
            if(bla ==null || bla.trim().isEmpty()){
                responseDataDTO.setMessage("BLA Number is mandatory");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String subId= bondLedgerAccountMaintenanceDTO.getSubscriptionId();
            if(subId ==null || subId.trim().isEmpty()){
                responseDataDTO.setMessage("Subscription ID is mandatory");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet resultSet = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CANCEL_SUB_CAT_CBT_ADD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getFlag());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getCloserType());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (bondLedgerAccountMaintenanceDTO.getFlag() == null) {
                        resultSet = (ResultSet) stmt.getObject(10);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("subCode", resultSet.getString(1));
                                item.put("subCodeDescription", resultSet.getString(2));
                                item.put("status", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getFlag() != null && bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("P")) {
                        resultSet = (ResultSet) stmt.getObject(10);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getFlag() != null && bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("B")) {
                        resultSet = (ResultSet) stmt.getObject(10);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("blaNo", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getFlag() != null && bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("S")) {
                        resultSet = (ResultSet) stmt.getObject(10);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("blaNo", resultSet.getString(4));
                                item.put("subscriptionId", resultSet.getString(5));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getFlag() != null &&bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("D")) {
                        resultSet = (ResultSet) stmt.getObject(10);
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(11);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(12);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(13);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(14);
                        List<ColumnValueDTO> columnValueDTOS = DataBaseUtils.getColumnNamePropertyList(resultSet);
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet1));
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet2));
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet3));
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet4));
                        responseDataDTO.setResult(columnValueDTOS);
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
    public ResponseDataDTO verifyRejectCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(bondLedgerAccountMaintenanceDTO.getTorch() == null){
            String schemeCode = bondLedgerAccountMaintenanceDTO.getSchemeCode();
            if(schemeCode ==null || schemeCode.trim().isEmpty()){
                responseDataDTO.setMessage("Scheme code is mandatory");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String closerType = bondLedgerAccountMaintenanceDTO.getCloserType();
            if(closerType == null || closerType.trim().isEmpty()  || !(closerType.equalsIgnoreCase("CAT") || closerType.equalsIgnoreCase("CBT"))){
                responseDataDTO.setMessage("Closer Type is mandatory and must be CAT or CBT");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String bla = bondLedgerAccountMaintenanceDTO.getBlaNo();
            if(bla ==null || bla.trim().isEmpty()){
                responseDataDTO.setMessage("BLA Number is mandatory");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String subId= bondLedgerAccountMaintenanceDTO.getSubscriptionId();
            if(subId ==null || subId.trim().isEmpty()){
                responseDataDTO.setMessage("Subscription ID is mandatory");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet resultSet=null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CANCEL_SUB_CAT_CBT_VER_DEL_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getCloserType());
                /*stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());*/
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (bondLedgerAccountMaintenanceDTO.getTorch() != null && bondLedgerAccountMaintenanceDTO.getTorch().equalsIgnoreCase("scheme")) {
                        resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("subCode", resultSet.getString(1));
                                item.put("subCodeDescription", resultSet.getString(2));
                                item.put("status", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                   /* } else if (bondLedgerAccountMaintenanceDTO.getTorch() != null && bondLedgerAccountMaintenanceDTO.getTorch().equalsIgnoreCase("pan")) {
                        resultSet = (ResultSet) stmt.getObject(9);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }*/
                    } else if (bondLedgerAccountMaintenanceDTO.getTorch() != null && bondLedgerAccountMaintenanceDTO.getTorch().equalsIgnoreCase("bla")) {
                        resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("bondHolderName", resultSet.getString(1));
                                item.put("modeOfHolding", resultSet.getString(2));
                                item.put("blaNo", resultSet.getString(3));
                                item.put("subscriptionId", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getTorch() != null && bondLedgerAccountMaintenanceDTO.getTorch().equalsIgnoreCase("sub")) {
                        resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("bondHolderName", resultSet.getString(1));
                                item.put("modeOfHolding", resultSet.getString(2));
                                item.put("blaNo", resultSet.getString(3));
                                item.put("subscriptionId", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getTorch() == null){
                        resultSet = (ResultSet) stmt.getObject(7);
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(8);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(9);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(10);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(11);
                        List<ColumnValueDTO> columnValueDTOS=DataBaseUtils.getColumnNamePropertyList(resultSet);
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet1));
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet2));
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet3));
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet4));
                        responseDataDTO.setResult(columnValueDTOS);
                        Map<String, String> map=new HashMap();
                        map.put("p_court_order_no_out",stmt.getString(12));
                        map.put("p_court_order_date_out",stmt.getString(13));
                        map.put("p_remarks_out",stmt.getString(14));
                        map.put("p_created_by_out",stmt.getString(15));
                        map.put("p_created_date_out",stmt.getString(16));
                        map.put("p_verified_by_out",stmt.getString(17));
                        map.put("p_verified_date_out",stmt.getString(18));
                        map.put("p_verified_status_out",stmt.getString(19));
                        responseDataDTO.setResult1(map);

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
    public ResponseDataDTO addSubCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (bondLedgerAccountMaintenanceDTO.getCatCbtRemarks() == null
                || bondLedgerAccountMaintenanceDTO.getCatCbtRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("Remarks is Mandatory");
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CANCEL_SUB_CAT_CBT_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getCloserType());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getCourtOrder());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getCourtOrderDate());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getCatCbtRemarks()
                );
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
    public ResponseDataDTO verifyCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CANCEL_SUB_CAT_CBT_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getCloserType());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
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
    public ResponseDataDTO rejectCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (bondLedgerAccountMaintenanceDTO.getRejectionRemarks() == null
                || bondLedgerAccountMaintenanceDTO.getRejectionRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(bondLedgerAccountMaintenanceDTO.getRejectionRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CANCEL_SUB_CAT_CBT_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getCloserType());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
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
    public ResponseDataDTO inquiryCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet resultSet = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CANCEL_SUB_CAT_CBT_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getCloserType());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getFlag());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (bondLedgerAccountMaintenanceDTO.getFlag() == null) {
                        resultSet = (ResultSet) stmt.getObject(10);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("subCode", resultSet.getString(1));
                                item.put("subCodeDescription", resultSet.getString(2));
                                item.put("status", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getFlag() != null && bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("P")) {
                        resultSet = (ResultSet) stmt.getObject(10);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getFlag() != null && bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("B")) {
                        resultSet = (ResultSet) stmt.getObject(10);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("blaNo", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getFlag() != null && bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("S")) {
                        resultSet = (ResultSet) stmt.getObject(10);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("panNum", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("blaNo", resultSet.getString(4));
                                item.put("subscriptionId", resultSet.getString(5));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getFlag() != null&& bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("D")){
                        resultSet = (ResultSet) stmt.getObject(10);
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(11);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(12);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(13);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(14);
                        List<ColumnValueDTO> columnValueDTOS=DataBaseUtils.getColumnNamePropertyList(resultSet);
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet1));
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet2));
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet3));
                        columnValueDTOS.addAll(DataBaseUtils.getColumnNamePropertyList(resultSet4));
                        responseDataDTO.setResult(columnValueDTOS);
                        Map<String, String> map=new HashMap();
                        map.put("p_court_order_no_out",stmt.getString(15));
                        map.put("p_court_order_date_out",stmt.getString(16));
                        map.put("p_remarks_out",stmt.getString(17));
                        map.put("p_final_remarks_out",stmt.getString(18));
                        map.put("p_created_by_out",stmt.getString(19));
                        map.put("p_created_date_out",stmt.getString(20));
                        map.put("p_verified_by_out",stmt.getString(21));
                        map.put("p_verified_date_out",stmt.getString(22));
                        map.put("p_verified_status_out",stmt.getString(23));
                        responseDataDTO.setResult1(map);
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


