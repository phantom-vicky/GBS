package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.BlaOpeningCumSubscriptionDao;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.service.RecordLockService;
import com.isl.gbm.rbibond.util.NumberToWordConverter;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Repository
public class BlaOpeningCumSubscriptionDaoImpl implements BlaOpeningCumSubscriptionDao {
    private static final Logger log = LoggerFactory.getLogger(BlaOpeningCumSubscriptionDaoImpl.class);

    // Single global lock to process requests one by one (Request 1 -> Request 2 -> Request 3...)
    private static final ReentrantLock globalAddSubmitLock = new ReentrantLock(true); // fair lock ensures FIFO order
    private static final int LOCK_TIMEOUT_SECONDS = 60; // 60 seconds timeout
    @Autowired
    RecordLockService lockService;
    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private NumberToWordConverter numberToWordConverter;
    @Value("${profile.type}")
    private String stubProcess;
    @Override
    public ResponseDataDTO getRbiBondModuleNameDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        PreparedStatement stmt = null;
        CallableStatement stmt1 = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        java.lang.String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if(null!=bondLedgerAccountMaintenanceDTO&&null!=bondLedgerAccountMaintenanceDTO.getReferenceId()&&bondLedgerAccountMaintenanceDTO.getReferenceId().equalsIgnoreCase("I")){
                    stmt1 = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD_INQUIRY.getProcName());
                    stmt1.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt1.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt1.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                    stmt1.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                    stmt1.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                    stmt1.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                    stmt1.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index, Types.VARCHAR);
                    stmt1.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt1.getString(index));
                    resultMsg = stmt1.getString(index);
                    if (stmt1.getString(index) != null && stmt1.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        List<HashMap<String, String>> resultList = new ArrayList<>();
                        resultSet = (ResultSet) stmt1.getObject(8);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("subCode", resultSet.getString(1));
                                resultMap.put("subCodeDescription", resultSet.getString(2));
                                resultMap.put("status", resultSet.getString(3));
                                resultList.add(resultMap);
                            }
                        }
                        responseDataDTO.setResult(resultList);
                    }
                }else if(null!=bondLedgerAccountMaintenanceDTO&&null!=bondLedgerAccountMaintenanceDTO.getReferenceId()&&bondLedgerAccountMaintenanceDTO.getReferenceId().equalsIgnoreCase("M")){
                    stmt1 = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_MODIFY_SEL.getProcName());
                    stmt1.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt1.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt1.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                    stmt1.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                    stmt1.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                    stmt1.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                    stmt1.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index++, Types.VARCHAR);
                    stmt1.registerOutParameter(index, Types.VARCHAR);
                    stmt1.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt1.getString(index));
                    resultMsg = stmt1.getString(index);
                    if (stmt1.getString(index) != null && stmt1.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        List<HashMap<String, String>> resultList = new ArrayList<>();
                        resultSet = (ResultSet) stmt1.getObject(8);
                        if (resultSet != null){
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("subCode", resultSet.getString(1));
                                resultMap.put("subCodeDescription", resultSet.getString(2));
                                resultMap.put("status", resultSet.getString(3));
                                resultList.add(resultMap);
                            }
                        }
                        responseDataDTO.setResult(resultList);
                    }
                }
                else {
                    if("PROD".equalsIgnoreCase(stubProcess)){
                        stmt = connection.prepareStatement("SELECT T2.sub_code AS sub_code,T2.sub_code_desc AS sub_code_desc,\n" +
                                "CASE WHEN T3.scheme_status ='C' THEN 'Current'\n" +
                                "WHEN T3.scheme_status ='A' THEN 'Active'\n" +
                                "WHEN T3.scheme_status ='CL' THEN 'Closed' ELSE NULL END AS scheme_status\n" +
                                "FROM gbm_code_master T1 INNER JOIN\n" +
                                "gbm_sub_code_master T2 ON T1.code =T2.code\n" +
                                "INNER JOIN gbm_rbi_bond_scheme_details T3 ON T3.sch_code =T2.sub_code\n" +
                                "WHERE T2.code='012' AND T2.verified_status ='Y' AND T2.is_Active ='Y' AND T2.is_deleted ='N' \n" +
                                "AND T3.verified_status ='Y' AND T3.is_active ='Y' AND T3.is_deleted ='N' AND T3.scheme_status IN ('C')");
                    }else {
                        stmt = connection.prepareStatement("SELECT T2.sub_code AS sub_code,T2.sub_code_desc AS sub_code_desc,\n" +
                                "CASE WHEN T3.scheme_status ='C' THEN 'Current'\n" +
                                "WHEN T3.scheme_status ='A' THEN 'Active'\n" +
                                "WHEN T3.scheme_status ='CL' THEN 'Closed' ELSE NULL END AS scheme_status\n" +
                                "FROM gbm_code_master T1 INNER JOIN\n" +
                                "gbm_sub_code_master T2 ON T1.code =T2.code\n" +
                                "INNER JOIN gbm_rbi_bond_scheme_details T3 ON T3.sch_code =T2.sub_code\n" +
                                "WHERE T2.code='012' AND T2.verified_status ='Y' AND T2.is_Active ='Y' AND T2.is_deleted ='N' \n" +
                                "AND T3.verified_status ='Y' AND T3.is_active ='Y' AND T3.is_deleted ='N' AND T3.scheme_status IN ('C','A','CL')");
                    }
                    resultSet = stmt.executeQuery();

                    List<HashMap<String, String>> resultList = new ArrayList<>();
                    if (resultSet != null)
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("subCode", resultSet.getString(1));
                            resultMap.put("subCodeDescription", resultSet.getString(2));
                            resultMap.put("status", resultSet.getString(3));
                            resultList.add(resultMap);
                        }
                    responseDataDTO.setResult(resultList);
                    resultMsg = CommonConstants.SUCCESS_CONST;
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
    public ResponseDataDTO getbankName(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        java.lang.String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareStatement("SELECT bank_name FROM gbm_other_bank_details WHERE is_verified = '1' AND delete_flag = '0' AND auditable = '1' AND bank_code = ?");
                stmt.setString(1, bondLedgerAccountMaintenanceDTO.getReferenceId());
                resultSet = stmt.executeQuery();

                    List<HashMap<String, String>> resultList = new ArrayList<>();
                    if (resultSet != null)
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("bank_name", resultSet.getString(1));
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
    public ResponseDataDTO getbranchName(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        java.lang.String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                stmt = connection.prepareStatement("SELECT SOL_DESC from gbm_bank_branches where sol_id=?");
                stmt.setString(1,bondLedgerAccountMaintenanceDTO.getReferenceId());
                resultSet = stmt.executeQuery();

                List<HashMap<String, String>> resultList = new ArrayList<>();
                if (resultSet != null)
                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("branchName", resultSet.getString(1));
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
    public ResponseDataDTO getMinorDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD_GET_MINOR_DETAILS_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPan_No1());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPan_No2());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPan_No3());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getModeOfHolding());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(8);
                    List<HashMap<String, String>> torchOut = null;

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();
                            temp.put("blaNo", resultSet.getString(1));
                            temp.put("customerName", resultSet.getString(2));
                            temp.put("modeOfHolding", resultSet.getString(3));
                            temp.put("panNo", resultSet.getString(4));
                            temp.put("isMinor", resultSet.getString(5));
                            torchOut.add(temp);
                        }
                        bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
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
    public ResponseDataDTO getWboBranchDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        java.lang.String resultMsg = "";
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
    public ResponseDataDTO getBrokerTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD_GET_BROKER_ID.getProcName());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(1);
                    List<HashMap<String, String>> torchOut = null;

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();
                            temp.put("brokerAgencyCode", resultSet.getString(1));
                            temp.put("brokerName", resultSet.getString(2));
                            temp.put("operativeAccBranchId", resultSet.getString(3));
                            torchOut.add(temp);
                        }
                        bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
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
    public ResponseDataDTO getGuardianDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD_GET_GUARD_ID.getProcName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getGuardianId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(4);

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

    @Override
    public ResponseDataDTO getAddDashBoard(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD_GET_DASHBOARD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(4);

                    if (bondLedgerAccountMaintenanceDTO.getId() == null) {

                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("id", resultSet.getString(1));
                                item.put("schemeCode", resultSet.getString(2));
                                item.put("liquidationDate", resultSet.getString(3));
                                item.put("bondHolderName", resultSet.getString(4));
                                item.put("pan_No1", resultSet.getString(5));
                                item.put("secondJointHolderName", resultSet.getString(6));
                                item.put("pan_No2", resultSet.getString(7));
                                item.put("thirdJointHolderName", resultSet.getString(8));
                                item.put("pan_No3", resultSet.getString(9));
                                item.put("modeOfHolding", resultSet.getString(10));
                                item.put("moduleCode", resultSet.getString(11));
                                item.put("schemeName", resultSet.getString(12));
                                item.put("bondHldDob", resultSet.getString(13));
                                item.put("isMinor", resultSet.getString(14));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }

                    else if (bondLedgerAccountMaintenanceDTO.getId() != null) {

                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("id", resultSet.getString(1));
                                item.put("schemeCode", resultSet.getString(2));
                                item.put("liquidationDate", resultSet.getString(3));
                                item.put("bondHolderName", resultSet.getString(4));
                                item.put("pan_No1", resultSet.getString(5));
                                item.put("secondJointHolderName", resultSet.getString(6));
                                item.put("pan_No2", resultSet.getString(7));
                                item.put("thirdJointHolderName", resultSet.getString(8));
                                item.put("pan_No3", resultSet.getString(9));
                                item.put("modeOfHolding", resultSet.getString(10));
                                item.put("moduleCode", resultSet.getString(11));
                                item.put("schemeName", resultSet.getString(12));
                                item.put("bondHldDob", resultSet.getString(13));
                                item.put("ftsAppNum", resultSet.getString(14));
                                item.put("noOfJointAcc", resultSet.getString(15));
                                item.put("bondHldDobTwo", resultSet.getString(16));
                                item.put("bondHldDobThree", resultSet.getString(17));
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
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO validatePanRequest(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        PanDetailsDTO panDetailsDTO=null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD_PAN_TRIGGER_REQ.getProcName());
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD_PAN_TRIGGER_RES.getProcName());
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
    private ARRAY generateTYP_typ_rbi_bond_bla_res_OBJ(Connection connection, PanValidationRetriggerResponseDTO dto) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_PAN_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_PAN", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[1];
        Object[] paramArray = new Object[14];
      //  log.info("Processing BLA: " + dto);
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
           // log.info(dto.toString());
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private static final String isMinor= "M";
    @Override
    public ResponseDataDTO add(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO1=new BondLedgerAccountMaintenanceDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getModeOfHolding());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPan_No1());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPan_No2());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPan_No3());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getId());
                if (bondLedgerAccountMaintenanceDTO.getMinorPan() && (bondLedgerAccountMaintenanceDTO.getBlaNo() == null || bondLedgerAccountMaintenanceDTO.getBlaNo().isEmpty())) {
                    // Your code here
                    stmt.setString(index++, isMinor);
                }else {
                    stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                }
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                bondLedgerAccountMaintenanceDTO1.setBlaFlag(stmt.getString(14));
                bondLedgerAccountMaintenanceDTO1.setDdPayAccountNumber(stmt.getString(15));
                bondLedgerAccountMaintenanceDTO1.setPeriodOfDeposit(stmt.getString(16));
                bondLedgerAccountMaintenanceDTO1.setSchemeBondType(stmt.getString(17));
                bondLedgerAccountMaintenanceDTO1.setRateOfInterest(stmt.getString(18));
                bondLedgerAccountMaintenanceDTO1.setCashLimitForCash(stmt.getString(19));
                bondLedgerAccountMaintenanceDTO1.setCashLimitForClearingTransfer(stmt.getString(20));
                bondLedgerAccountMaintenanceDTO1.setDepositMultiples(stmt.getString(21));
                bondLedgerAccountMaintenanceDTO1.setMinCashLimit(stmt.getString(22));
                bondLedgerAccountMaintenanceDTO1.setAadharCapture(stmt.getString(23));
                bondLedgerAccountMaintenanceDTO1.setAadharMandatory(stmt.getString(24));
                bondLedgerAccountMaintenanceDTO1.setAadharDisplay(stmt.getString(25));
                bondLedgerAccountMaintenanceDTO1.setBlaNo(stmt.getString(26));
                bondLedgerAccountMaintenanceDTO1.setMaturityAmt(stmt.getString(27));
                String nomineeCountStr = stmt.getString(28);
                int nomineeCount = (nomineeCountStr != null && !nomineeCountStr.isEmpty()) ? Integer.parseInt(nomineeCountStr) : 0;
                bondLedgerAccountMaintenanceDTO1.setSchemeNomineeCount(nomineeCount);
                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if ("Y".equals(bondLedgerAccountMaintenanceDTO1.getBlaFlag())) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(10);
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


                    }  if ("Y".equals(bondLedgerAccountMaintenanceDTO1.getBlaFlag())) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(12);
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

                    }  if ("Y".equals(bondLedgerAccountMaintenanceDTO1.getBlaFlag())) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(11);
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
                                item.put("bankCode", resultSet.getString(51));
                                item.put("accountType", resultSet.getString(53));
                                item.put("micrCode", resultSet.getString(54));
                                list.add(item);
                            }
                            bondLedgerAccountMaintenanceDTO1.setCustomerMasterOut(list);
                            bondLedgerAccountMaintenanceDTO1.setBlaNoOutNum(stmt.getString(26));
                            responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                        }

                    } if (bondLedgerAccountMaintenanceDTO.getId() != null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(13);

                        if ("Y".equals(bondLedgerAccountMaintenanceDTO1.getBlaFlag())) {
                            if (resultSet != null) {
                                list = new ArrayList<>();
                                HashMap<String, String> item; // Declare the item variable
                                while (resultSet.next()) {
                                    item = new HashMap<>();
                                    item.put("subscriptionAmount", resultSet.getString("SUBSCRIPTION_AMT"));
                                    item.put("bondType", resultSet.getString("BOND_TYPE"));
                                    item.put("accountOpeningDate", resultSet.getString("account_open_date"));
                                    item.put("modeOfInterestPayment", resultSet.getString("mode_of_interest"));
                                    item.put("operativeAccNum", resultSet.getString("operative_acc_number"));
                                    item.put("id", resultSet.getString("cashin_id"));
                                    String rsNumber = resultSet.getString("mobile");
                                    if (rsNumber != null) {
                                        rsNumber = rsNumber.replaceFirst("^000+", "").replaceFirst("^00+", "").replaceFirst("^0+", "");
                                    } else {
                                        rsNumber = "";  // Default to empty string if mobile number is null
                                    }
                                    item.put("bondHldMobNum", rsNumber);
                                    item.put("instrumentNo", resultSet.getString("instrument_number"));
                                    item.put("valueDate", resultSet.getString("value_date"));
                                    item.put("instrumentDate", resultSet.getString("INSTRUMENT_DATE"));
                                    item.put("transactionMode", resultSet.getString("TRAN_MODE"));
                                    item.put("brokerAgencyCode", resultSet.getString("broker_id"));
                                    item.put("brokerName", resultSet.getString("broker_name"));
                                    item.put("instrumentAmount", resultSet.getString("instrument_amnt"));
                                    item.put("ifscCode", resultSet.getString("ifsc_code"));
                                    item.put("drawerBank", resultSet.getString("DRAWEE_BANK_DESCRIPTION"));
                                    item.put("bankCodeNonMicr", resultSet.getString("BANK_NAME"));
                                    item.put("wboBranchName", resultSet.getString("BRANCH_NAME_WBO"));
                                    item.put("wboBranchCode", resultSet.getString("BRANCH_CODE_WBO"));
                                    list.add(item);
                                }
                                bondLedgerAccountMaintenanceDTO1.setDataCopersondetailsList1(list);
                                responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);

                            }


                        } else {
                            if (resultSet != null) {
                                list = new ArrayList<>();
                                HashMap<String, String> item; // Declare the item variable
                                while (resultSet.next()) {
                                    item = new HashMap<>(); // Initialize the item variable

                                    item.put("bondHldFirstName", resultSet.getString("FIRST_NAME"));
                                    item.put("bondHldDob", resultSet.getString("DOB"));
                                    item.put("holderType", resultSet.getString("holder_type"));
                                    item.put("gender", resultSet.getString("gender"));
                                    item.put("isMinor", resultSet.getString("minor_flg"));
                                    item.put("bondHldAddOne", resultSet.getString("ADD1"));
                                    item.put("bondHldAddTwo", resultSet.getString("ADD2"));
                                    item.put("bondHldPinCode", resultSet.getString("PINCODE"));
                                    item.put("bondHldState", resultSet.getString("STATE"));
                                    item.put("bondHldCity", resultSet.getString("CITY"));
                                    item.put("bondHldEmail", resultSet.getString("EMAIL_ADDRESS"));
                                    item.put("subscriptionAmount", resultSet.getString("SUBSCRIPTION_AMT"));
                                    item.put("bondType", resultSet.getString("BOND_TYPE"));
                                    item.put("accountOpeningDate", resultSet.getString("account_open_date"));
                                    item.put("modeOfInterestPayment", resultSet.getString("mode_of_interest"));
                                    item.put("operativeAccNumber", resultSet.getString("operative_acc_number"));
                                    item.put("firstName", resultSet.getString("nominee_first_name"));
                                    item.put("middleName", resultSet.getString("nominee_mid_name"));
                                    item.put("lastName", resultSet.getString("nominee_last_name"));
                                    item.put("addressOne", resultSet.getString("address1"));
                                    item.put("addressTwo", resultSet.getString("address2"));
                                    item.put("city", resultSet.getString("city"));
                                    item.put("state", resultSet.getString("STATE"));
                                    item.put("pinCode", resultSet.getString("pin_code"));
                                    item.put("phoneNo", resultSet.getString("phone_no"));
                                    item.put("sharePercentage", resultSet.getString("share_percentage"));
                                    item.put("isNomineeMinor", resultSet.getString("is_nominee_minor"));
                                    item.put("relationWithHolder", resultSet.getString("relation_with_holder"));
                                    item.put("dateOfBirthOfMinor", resultSet.getString("date_of_birth_of_minor"));
                                    item.put("id", resultSet.getString("cashin_id"));
                                    String rsNumber = resultSet.getString("mobile");
                                    if (rsNumber != null) {
                                        rsNumber = rsNumber.replaceFirst("^000+", "").replaceFirst("^00+", "").replaceFirst("^0+", "");
                                    } else {
                                        rsNumber = "";  // Default to empty string if mobile number is null
                                    }
                                    item.put("bondHldMobNum", rsNumber);
                                    item.put("instrumentNo", resultSet.getString("instrument_number"));
                                    item.put("valueDate", resultSet.getString("value_date"));
                                    item.put("instrumentDate", resultSet.getString("INSTRUMENT_DATE"));
                                    item.put("transactionMode", resultSet.getString("TRAN_MODE"));
                                    item.put("brokerAgencyCode", resultSet.getString("broker_id"));
                                    item.put("brokerName", resultSet.getString("broker_name"));
                                    item.put("instrumentAmount", resultSet.getString("instrument_amnt"));
                                    item.put("ifscCode", resultSet.getString("ifsc_code"));
                                    item.put("drawerBank", resultSet.getString("DRAWEE_BANK_DESCRIPTION"));
                                    item.put("bankCodeNonMicr", resultSet.getString("BANK_NAME"));
                                    item.put("wboBranchName", resultSet.getString("BRANCH_NAME_WBO"));
                                    item.put("wboBranchCode", resultSet.getString("BRANCH_CODE_WBO"));
                                    list.add(item);
                                }
                                bondLedgerAccountMaintenanceDTO1.setInstrumentDetailsOut(list);
                                responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                            }
                            if(null!=bondLedgerAccountMaintenanceDTO.getId()&&bondLedgerAccountMaintenanceDTO.getMinorPan() && (bondLedgerAccountMaintenanceDTO.getBlaNo() == null || bondLedgerAccountMaintenanceDTO.getBlaNo().isEmpty())) {
                                ResultSet resultSet2 = (ResultSet) stmt.getObject(12);
                                if (resultSet2 != null) {
                                    list = new ArrayList<>();
                                    HashMap<String, String> item = null;
                                    while (resultSet2.next()) {
                                        item = new HashMap<>();
                                        item.put("guardianFirstName", resultSet2.getString(1));
                                        item.put("guardianMiddleName", resultSet2.getString(2));
                                        item.put("guardianLastName", resultSet2.getString(3));
                                        item.put("relationWithMinor", resultSet2.getString(4));
                                        item.put("guardianCommunicationAddress", resultSet2.getString(5));
                                        item.put("guardianEmailId", resultSet2.getString(6));
                                        list.add(item);
                                    }
                                    bondLedgerAccountMaintenanceDTO1.setGuardianDetailsOut(list);
                                    responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
                                }
                            }
                        }
                    }else{
                        bondLedgerAccountMaintenanceDTO1.setBlaFlag(stmt.getString(14));
                        bondLedgerAccountMaintenanceDTO1.setDdPayAccountNumber(stmt.getString(15));
                        bondLedgerAccountMaintenanceDTO1.setPeriodOfDeposit(stmt.getString(16));
                        bondLedgerAccountMaintenanceDTO1.setSchemeBondType(stmt.getString(17));
                        bondLedgerAccountMaintenanceDTO1.setRateOfInterest(stmt.getString(18));
                        bondLedgerAccountMaintenanceDTO1.setCashLimitForCash(stmt.getString(19));
                        bondLedgerAccountMaintenanceDTO1.setCashLimitForClearingTransfer(stmt.getString(20));
                        bondLedgerAccountMaintenanceDTO1.setDepositMultiples(stmt.getString(21));
                        bondLedgerAccountMaintenanceDTO1.setMinCashLimit(stmt.getString(22));
                        bondLedgerAccountMaintenanceDTO1.setBlaNoOutNum(stmt.getString(26));
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO1);
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
    public ResponseDataDTO addSubmit(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<String> validModes = Arrays.asList("MH001", "MH004","MH005");
        List<String> jointModes = Arrays.asList("MH002", "MH003");
        
        boolean lockAcquired = false;
        
        try {
            // Acquire global lock - ensures requests are processed one by one (FIFO order)
            lockAcquired = globalAddSubmitLock.tryLock(LOCK_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            if (!lockAcquired) {
                log.warn("Could not acquire global lock. System busy processing other requests.");
                responseDataDTO.setMessage("System is busy. Please try again after some time.");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            
            log.info("Global lock acquired. Processing addSubmit request. Queue size: {}", globalAddSubmitLock.getQueueLength());
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getModeOfHolding());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getJointHolderNo());

                if (bondLedgerAccountMaintenanceDTO.getDataList() != null) {
                    stmt.setArray(index++, generateTYP_ty_gbm_bond_holder_dtls(connection, bondLedgerAccountMaintenanceDTO.getDataList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_BLA_ARRAY);
                }
                if (bondLedgerAccountMaintenanceDTO.getDataCopersondetailsList() != null) {
                    stmt.setArray(index++, generateTYP_gbm_rbi_bond_copar(connection, bondLedgerAccountMaintenanceDTO.getDataCopersondetailsList()));

//                    stmt.setArray(index++, generateTYP_gbm_rbi_bond_copar(connection, bondLedgerAccountMaintenanceDTO.getCoParson()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COPARS_ARRAY);
                }
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaFlag());

                //subscription
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSingleBondAccNo());
                if (((bondLedgerAccountMaintenanceDTO.getModeOfHolding() != null && validModes.stream().anyMatch(m -> m.equalsIgnoreCase(bondLedgerAccountMaintenanceDTO.getModeOfHolding())))
                        || (bondLedgerAccountMaintenanceDTO.getFlag() != null && bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("Y")))) {
                    String first  = (bondLedgerAccountMaintenanceDTO.getDataList().get(0).getBondHldFirstName() != null)
                            ? bondLedgerAccountMaintenanceDTO.getDataList().get(0).getBondHldFirstName()
                            : "";
                    String middle = (bondLedgerAccountMaintenanceDTO.getDataList().get(0).getBondHldMidName() != null)
                            ? bondLedgerAccountMaintenanceDTO.getDataList().get(0).getBondHldMidName()
                            : "";
                    String last   = (bondLedgerAccountMaintenanceDTO.getDataList().get(0).getBondHldLastName() != null)
                            ? bondLedgerAccountMaintenanceDTO.getDataList().get(0).getBondHldLastName()
                            : "";
                    StringBuilder fullName = new StringBuilder();
                    if (!first.isEmpty())
                        fullName.append(first);
                    if (!middle.isEmpty()) {
                        if (fullName.length() > 0)
                        fullName.append(" ");
                        fullName.append(middle);
                    }
                    if (!last.isEmpty()) {
                        if (fullName.length() > 0)
                        fullName.append(" ");
                        fullName.append(last);
                    }
                stmt.setString(index++, fullName.toString());
                }else {
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                }
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getFirstJointHolderBlaNo());
                if (((bondLedgerAccountMaintenanceDTO.getModeOfHolding() != null && jointModes.stream().anyMatch(m -> m.equalsIgnoreCase(bondLedgerAccountMaintenanceDTO.getModeOfHolding()))&&null!=bondLedgerAccountMaintenanceDTO.getDataList())
                        || (bondLedgerAccountMaintenanceDTO.getFlag() != null && bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("Y")))) {
                    String fullName = String.join(" ",
                            Optional.ofNullable(bondLedgerAccountMaintenanceDTO.getDataList().get(0).getBondHldFirstName()).orElse(""),
                            Optional.ofNullable(bondLedgerAccountMaintenanceDTO.getDataList().get(0).getBondHldMidName()).orElse(""),
                            Optional.ofNullable(bondLedgerAccountMaintenanceDTO.getDataList().get(0).getBondHldLastName()).orElse("")
                    ).trim();
                    stmt.setString(index++, fullName);
                }else {
                    stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getFirstJointHolderName());
                }
                //stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSecondJointHolderBlaNo());

                if (((bondLedgerAccountMaintenanceDTO.getModeOfHolding() != null && jointModes.stream().anyMatch(m -> m.equalsIgnoreCase(bondLedgerAccountMaintenanceDTO.getModeOfHolding()))&&null!=bondLedgerAccountMaintenanceDTO.getDataList()&&bondLedgerAccountMaintenanceDTO.getDataList().size()>1)
                        || (bondLedgerAccountMaintenanceDTO.getFlag() != null && bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("Y")))) {
                    stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getDataList().get(1).getBlaNo());
                    String fullName = String.join(" ",
                            Optional.ofNullable(bondLedgerAccountMaintenanceDTO.getDataList().get(1).getBondHldFirstName()).orElse(""),
                            Optional.ofNullable(bondLedgerAccountMaintenanceDTO.getDataList().get(1).getBondHldMidName()).orElse(""),
                            Optional.ofNullable(bondLedgerAccountMaintenanceDTO.getDataList().get(1).getBondHldLastName()).orElse("")
                    ).trim();
                    stmt.setString(index++, fullName);
                }else {
                    stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSecondJointHolderBlaNo());
                    stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSecondJointHolderName());
                }
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getThirdJointHolderBlaNo());
                if (((bondLedgerAccountMaintenanceDTO.getModeOfHolding() != null && jointModes.stream().anyMatch(m -> m.equalsIgnoreCase(bondLedgerAccountMaintenanceDTO.getModeOfHolding()))&&null!=bondLedgerAccountMaintenanceDTO.getDataList()&&bondLedgerAccountMaintenanceDTO.getDataList().size()>2)
                        || (bondLedgerAccountMaintenanceDTO.getFlag() != null && bondLedgerAccountMaintenanceDTO.getFlag().equalsIgnoreCase("Y")))) {
                    String fullName = String.join(" ",
                            Optional.ofNullable(bondLedgerAccountMaintenanceDTO.getDataList().get(2).getBondHldFirstName()).orElse(""),
                            Optional.ofNullable(bondLedgerAccountMaintenanceDTO.getDataList().get(2).getBondHldMidName()).orElse(""),
                            Optional.ofNullable(bondLedgerAccountMaintenanceDTO.getDataList().get(2).getBondHldLastName()).orElse("")
                    ).trim();
                    stmt.setString(index++, fullName);
                }else {
                    stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getThirdJointHolderName());
                }
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBrokerAgencyCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionAmount());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondType());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getAccountOpenDate());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getValueDate());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPeriodOfDeposit());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getMaturityDate());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getRateOfInterest());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getMaturityAmt());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getTranMode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getDebitAccNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getInstrumentType());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getInstrumentNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getInstrumentDate());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getInstrumentAmount());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getTranCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getZoneCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getZoneDate());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBankCodeNonMicr());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBranchCodeNonMicr());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSortCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getRemarks());
                if (bondLedgerAccountMaintenanceDTO.getNomineeDetailsList() != null) {
                    stmt.setArray(index++, generateTYP_gbm_rbi_bond_nom_dtls(connection, bondLedgerAccountMaintenanceDTO.getNomineeDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_NOMINATION_ARRAY);
                }
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getIsNominee());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getWboBranchCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getWboBranchName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSegment());
                //added utility for convert amount to words.
                stmt.setString(index++, numberToWordConverter.convertAmountToWordsString(bondLedgerAccountMaintenanceDTO.getSubscriptionAmount()).toUpperCase());
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
                    bondLedgerAccountMaintenanceDTO.setSingleBlaNo(stmt.getString(46));
                    bondLedgerAccountMaintenanceDTO.setFirstJointBlaNo(stmt.getString(47));
                    bondLedgerAccountMaintenanceDTO.setSecondJointBlaNo(stmt.getString(48));
                    bondLedgerAccountMaintenanceDTO.setThirdJointBlaNo(stmt.getString(49));
                    bondLedgerAccountMaintenanceDTO.setSingleGuardianId(stmt.getString(50));
                    bondLedgerAccountMaintenanceDTO.setFirstGuardianId(stmt.getString(51));
                    bondLedgerAccountMaintenanceDTO.setSecondGuardianId(stmt.getString(52));
                    bondLedgerAccountMaintenanceDTO.setThirdGuardianId(stmt.getString(53));
                    bondLedgerAccountMaintenanceDTO.setSubscriptionId(stmt.getString(54));
                    bondLedgerAccountMaintenanceDTO.setBlaNo(stmt.getString(55));
                    bondLedgerAccountMaintenanceDTO.setNomineeIdList(stmt.getString(56));
                    responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            log.error("Lock acquisition interrupted", ie);
            responseDataDTO.setMessage("Request interrupted. Please try again.");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in addSubmit", e);
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
            // Release the global lock
            if (lockAcquired) {
                globalAddSubmitLock.unlock();
                log.info("Global lock released. Remaining in queue: {}", globalAddSubmitLock.getQueueLength());
            }
            if (bondLedgerAccountMaintenanceDTO != null
                    && bondLedgerAccountMaintenanceDTO.getDataList() != null
                    && !bondLedgerAccountMaintenanceDTO.getDataList().isEmpty()
                    && bondLedgerAccountMaintenanceDTO.getDataList().get(0) != null
                    && bondLedgerAccountMaintenanceDTO.getDataList().get(0).getPanNo() != null
                    && !bondLedgerAccountMaintenanceDTO.getDataList().get(0).getPanNo().trim().isEmpty()) {
                lockService.unlockByPan(
                        bondLedgerAccountMaintenanceDTO.getDataList().get(0).getPanNo()
                );
            }
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO inquiry(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD_INQUIRY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(8);

                    if (bondLedgerAccountMaintenanceDTO.getBlaNo() == null) {


                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("verifiedStatus", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getSubscriptionId() == null) {


                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("subscriptionId", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                item.put("subscriptionAmount", resultSet.getString(3));
                                item.put("verifiedStatus", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else {
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(9);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(10);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(11);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(12);
                        bondLedgerAccountMaintenanceDTO.setBlaGroupId(stmt.getString(13));
                        bondLedgerAccountMaintenanceDTO.setPkColumnValue(stmt.getString(14));
                        bondLedgerAccountMaintenanceDTO.setCreatedBy(stmt.getString(15));
                        bondLedgerAccountMaintenanceDTO.setCreatedDate(stmt.getString(16));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedBy(stmt.getString(17));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedDate(stmt.getString(18));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedBy(stmt.getString(19));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedDate(stmt.getString(20));
                        bondLedgerAccountMaintenanceDTO.setPreviousOperationType(stmt.getString(21));
                        bondLedgerAccountMaintenanceDTO.setVerifiedStatus(stmt.getString(22));
                        bondLedgerAccountMaintenanceDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        bondLedgerAccountMaintenanceDTO.setCoparsDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet1));
                        bondLedgerAccountMaintenanceDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
                        bondLedgerAccountMaintenanceDTO.setSubscriptionDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet3));
                        bondLedgerAccountMaintenanceDTO.setNomineeDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet4));
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
    public ResponseDataDTO modifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_MODIFY_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
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

                    ResultSet resultSet = (ResultSet) stmt.getObject(8);

                    if (bondLedgerAccountMaintenanceDTO.getBlaNo() == null) {


                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("verifiedStatus", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getSubscriptionId() == null) {


                        if (resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("subscriptionId", resultSet.getString(1));
                                item.put("bondHolderName", resultSet.getString(2));
                                item.put("subscriptionAmount", resultSet.getString(3));
                                item.put("verifiedStatus", resultSet.getString(4));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else {
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(9);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(10);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(11);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(12);
                        bondLedgerAccountMaintenanceDTO.setBlaGroupId(stmt.getString(13));
                        bondLedgerAccountMaintenanceDTO.setPkColumnValue(stmt.getString(14));
                        bondLedgerAccountMaintenanceDTO.setCreatedBy(stmt.getString(15));
                        bondLedgerAccountMaintenanceDTO.setCreatedDate(stmt.getString(16));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedBy(stmt.getString(17));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedDate(stmt.getString(18));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedBy(stmt.getString(19));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedDate(stmt.getString(20));
                        bondLedgerAccountMaintenanceDTO.setPreviousOperationType(stmt.getString(21));
                        bondLedgerAccountMaintenanceDTO.setVerifiedStatus(stmt.getString(22));
                        bondLedgerAccountMaintenanceDTO.setInvoiceFlag(stmt.getString(25));
                        bondLedgerAccountMaintenanceDTO.setPaymentFlag(stmt.getString(26));
                        bondLedgerAccountMaintenanceDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        bondLedgerAccountMaintenanceDTO.setCoparsDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet1));
                        bondLedgerAccountMaintenanceDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
                        bondLedgerAccountMaintenanceDTO.setSubscriptionDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet3));
                        bondLedgerAccountMaintenanceDTO.setNomineeDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet4));
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
    public ResponseDataDTO modify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_MODIFY.getProcName());
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
                if (bondLedgerAccountMaintenanceDTO.getCoparsnersList() != null) {
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, bondLedgerAccountMaintenanceDTO.getCoparsnersList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                }
                /*if (bondLedgerAccountMaintenanceDTO.getNomineeDetails() != null) {
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, bondLedgerAccountMaintenanceDTO.getNomineeDetails()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                }*/
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



        @Override
        public ResponseDataDTO verifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        long startTime = System.currentTimeMillis();
        log.info("DB Execution Start Time: {}",new java.util.Date(startTime));
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_VERIFY_SEl.getProcName());
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

                    ResultSet resultSet = (ResultSet) stmt.getObject(6);

                    if (bondLedgerAccountMaintenanceDTO.getSchemeCode() == null) {


                        if (resultSet != null) {
                            long iterationStartTime = System.currentTimeMillis();
                            log.info("ResultSet Iteration Start Time: {}",new java.util.Date(iterationStartTime));
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
                                item.put("ftsAppNum", resultSet.getString(10));
                                list.add(item);
                            }
                            long iterationEndTime = System.currentTimeMillis();
                            long iterationTime = iterationEndTime - iterationStartTime;
                            log.info("ResultSet Iteration End Time: {}, Total Object Iteration Time: {} ms ({} sec)",new java.util.Date(iterationEndTime),iterationTime,iterationTime / 1000.0);
                            responseDataDTO.setResult(list);
                        }
                    } else {
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(7);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(8);
                        ResultSet resultSet3 = (ResultSet) stmt.getObject(9);
                        ResultSet resultSet4 = (ResultSet) stmt.getObject(10);
                        bondLedgerAccountMaintenanceDTO.setBlaGroupId(stmt.getString(11));
                        bondLedgerAccountMaintenanceDTO.setPkColumnValue(stmt.getString(12));
                        bondLedgerAccountMaintenanceDTO.setCreatedBy(stmt.getString(13));
                        bondLedgerAccountMaintenanceDTO.setCreatedDate(stmt.getString(14));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedBy(stmt.getString(15));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedDate(stmt.getString(16));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedBy(stmt.getString(17));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedDate(stmt.getString(18));
                        bondLedgerAccountMaintenanceDTO.setPreviousOperationType(stmt.getString(19));
                        bondLedgerAccountMaintenanceDTO.setVerifiedStatus(stmt.getString(20));
                        bondLedgerAccountMaintenanceDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        bondLedgerAccountMaintenanceDTO.setCoparsDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet1));
                        bondLedgerAccountMaintenanceDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
                        bondLedgerAccountMaintenanceDTO.setSubscriptionDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet3));
                        bondLedgerAccountMaintenanceDTO.setNomineeDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet4));
                        bondLedgerAccountMaintenanceDTO.setNomineeIdList(stmt.getString(22));
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
            long endTime = System.currentTimeMillis();
            log.info("DB Execution End Time: {}",new java.util.Date(endTime));
            log.info("Total DB Execution Time: {} ms ({} sec)",endTime - startTime,(endTime - startTime) / 1000.0);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }




    @Override
    public ResponseDataDTO verify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_VERIFY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaGroupId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getAuthorizationRemarks());
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
    public ResponseDataDTO delete(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_DELETE.getProcName());
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
    public ResponseDataDTO blaNo(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_INQ_BLA_TORCH_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(bondLedgerAccountMaintenanceDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("blaNo", resultSet.getString(1));
                                temp.put("nameOfTheBondHolder", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));
                                temp.put("verifiedStatus", resultSet.getString(4));

                                torchOut.add(temp);
                            }
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
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
    public ResponseDataDTO getblaNoInq(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_INQ_BLA_TORCH_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(bondLedgerAccountMaintenanceDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("blaNo", resultSet.getString(1));
                                temp.put("nameOfTheBondHolder", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));
                                temp.put("verifiedStatus", resultSet.getString(4));

                                torchOut.add(temp);
                            }
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
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
    public ResponseDataDTO getsubscriptionIdMod(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_INQ_SUB_TORCH_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(bondLedgerAccountMaintenanceDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("subscriptionId", resultSet.getString(1));
                                temp.put("modeOfHolding", resultSet.getString(2));
                                temp.put("bondHolderName", resultSet.getString(3));
                                temp.put("subscriptionAmount", resultSet.getString(4));
                                temp.put("verifiedStatus", resultSet.getString(5));
                                temp.put("valueDate", resultSet.getString(6));
                                temp.put("status", resultSet.getString(7));

                                torchOut.add(temp);
                            }
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
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
    public ResponseDataDTO getsubscriptionIdInq(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_INQ_SUB_TORCH_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBondHolderName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getPanNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNo());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(bondLedgerAccountMaintenanceDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("subscriptionId", resultSet.getString(1));
                                temp.put("modeOfHolding", resultSet.getString(2));
                                temp.put("bondHolderName", resultSet.getString(3));
                                temp.put("subscriptionAmount", resultSet.getString(4));
                                temp.put("verifiedStatus", resultSet.getString(5));
                                temp.put("valueDate", resultSet.getString(6));
                                temp.put("status", resultSet.getString(7));

                                torchOut.add(temp);
                            }
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
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
    public ResponseDataDTO blaTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_INQ_BLA_NO.getProcName());
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

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    List<HashMap<String, String>> torchOut = null;

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();

                            temp.put("panHldName", resultSet.getString(1));
                            temp.put("panNum", resultSet.getString(2));

                            torchOut.add(temp);
                        }
                        bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
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
    public ResponseDataDTO modifyPanTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_MOD_BLA_NO.getProcName());
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

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    List<HashMap<String, String>> torchOut = null;

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();

                            temp.put("panHldName", resultSet.getString(1));
                            temp.put("panNum", resultSet.getString(2));

                            torchOut.add(temp);
                        }
                        bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
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
    public ResponseDataDTO getNonHdfcTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD_GET_BANKCODE.getProcName());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    List<HashMap<String, String>> torchOut = null;
                    resultSet = (ResultSet) stmt.getObject(1);

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {

                            temp = new HashMap<>();
                            temp.put("bankCode", resultSet.getString(1));
                            temp.put("branchCode", resultSet.getString(2));
                            temp.put("bankName", resultSet.getString(3));
                            temp.put("branchName", resultSet.getString(4));
                            torchOut.add(temp);

                        }
                        bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
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
    public ResponseDataDTO getNonHdfcBranchTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD_GET_BRANCHCODE.getProcName());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBankCodeNonMicr());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    List<HashMap<String, String>> tdsCode = null;
                    resultSet = (ResultSet) stmt.getObject(2);

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        tdsCode = new ArrayList<>();
                        while (resultSet.next()) {

                            temp = new HashMap<>();
                            temp.put("bankCode", resultSet.getString(1));
                            temp.put("branchCode", resultSet.getString(2));

                            tdsCode.add(temp);

                        }
                        bondLedgerAccountMaintenanceDTO.setTorchValue(tdsCode);
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
    public ResponseDataDTO unlockBla(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = null; // Default message

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                // Using a native SQL query
                String sql = "UPDATE gbm_rbi_bond_inet_file_upd SET ACTION_FLG='N' WHERE ID=? AND ACTION_FLG='I'";
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, bondLedgerAccountMaintenanceDTO.getId()); // Assuming getId() exists

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 1) {
                    resultMsg = "Success";
                    responseDataDTO.setResult("unlocked Successfully");
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = "An error occurred while unlocking the account.";
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO fetchState(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "Success"; // Default success message

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            // Get connection from the connection pool
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                // SQL query to fetch city and state based on the pin code
                String sql = "SELECT city AS city, " +
                        "( " +
                        "    SELECT sub_code " +
                        "    FROM gbm_rbi_bond_sub_code_master " +
//                        "    WHERE code='037' AND sub_code_desc = state_name AND verified_status = 'Y' " +
                        "    WHERE code='037' AND upper(sub_code_desc) = upper(state_name) " +
                        "    AND is_deleted = 'N' " +
                        ") AS state_name " +
                        "FROM GBM_RBI_BOND_STATE_MASTER " +
                        "WHERE verified_status = 'Y' AND is_active = 'Y' " +
                        "AND is_deleted = 'N' AND audit_cre_flg = '1' " +
                        "AND pin_code = ?";

                // Prepare the statement and set the pin code parameter
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, bondLedgerAccountMaintenanceDTO.getPinCode());

                resultSet = stmt.executeQuery();

                List<HashMap<String, String>> tdsCode = new ArrayList<>();

                if (resultSet.next()) {
                    HashMap<String, String> temp = new HashMap<>();
                    temp.put("bondHldCity", resultSet.getString("city"));
                    temp.put("bondHldState", resultSet.getString("state_name"));
                    tdsCode.add(temp);
                } else {
                    resultMsg = "No data found for the given pin code Enter Manually.";
                    responseDataDTO.setResult(new ArrayList<>());
                }
                if (!tdsCode.isEmpty()) {
                    responseDataDTO.setResult(tdsCode);
                }

            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (SQLException e) {
            log.error("Database error occurred while fetching state info", e);
            resultMsg = "An error occurred while fetching the state information.";
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            resultMsg = "Unexpected error occurred while fetching the state information.";
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt, resultSet);
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
            paramArray[0] = handlePanValidations(dto.getPanNo());
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
            paramArray[20] = handleMobileValidations(dto.getBondHldMobNum());
            paramArray[21] = handleEmailValidations(dto.getBondHldEmail());
            paramArray[22] = dto.getHufRegPowerOfAttorney();
            paramArray[23] = dto.getPowerOfAttorneyKartaName();
            paramArray[24] = dto.getHdfcOtherBank();
            paramArray[25] = handleAccountValidations(dto.getOperativeAccNum());
            paramArray[26] = null!=dto.getIfscCode()?dto.getIfscCode():dto.getCreditBankIfscCode();
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
            paramArray[47] = dto.getGuardianDateOfBirth();
            paramArray[48] = dto.getGuardianCommunicationAddress();
            paramArray[49] = handleMobileValidations(dto.getGuardianMobileNo());
            paramArray[50] = handleEmailValidations(dto.getGuardianEmailId());
            paramArray[51] = dto.getJointHolderType();
            paramArray[52] = dto.getModeOfInterestPayment();
            paramArray[53] = (dto.getBankCode() != null && !dto.getBankCode().trim().isEmpty())?dto.getBankCode():dto.getBankName();
            paramArray[54] = dto.getBranchCode();
            paramArray[55] = dto.getNameOfthePayee();
            paramArray[56] = handleAccountValidations(dto.getOperativeAccNumber());
            paramArray[57] = dto.getMicrCode();
            paramArray[58] = dto.getAccountType();
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
    /*public String handleIfscValidations(String value){
        if(null!=value && !value.trim().isEmpty()){
            RbiBondServerSideRegexUtility.validateIFSC(value.trim());
        }return value;
    }*/
    public String handleAccountValidations(String value){
        if(null!=value && !value.trim().isEmpty()){
            RbiBondServerSideRegexUtility.validateAccountNumber(value.trim());
        }return value;
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
                log.info("DTO: {}", dto);
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
    }


