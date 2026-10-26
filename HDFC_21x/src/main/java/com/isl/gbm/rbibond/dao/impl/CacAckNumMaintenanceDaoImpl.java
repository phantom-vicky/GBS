package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.CacAckNumMaintenanceDao;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.reports.CacAckNumMaintenanceDto;
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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CacAckNumMaintenanceDaoImpl implements CacAckNumMaintenanceDao {

    private static final Logger log = LoggerFactory.getLogger(CacAckNumMaintenanceDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;


    @Override
    public ResponseDataDTO add(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CAC_ACK_NUM_sp_add.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cacAckNumMaintenanceDto.getSchemeCode());
                stmt.setString(index++,cacAckNumMaintenanceDto.getSubsDate());
                stmt.setString(index++,cacAckNumMaintenanceDto.getCasAckNum());
                stmt.setString(index++,cacAckNumMaintenanceDto.getCasAckDate());
                stmt.setString(index++,cacAckNumMaintenanceDto.getCasTranDate());
                stmt.setString(index++,cacAckNumMaintenanceDto.getBrkAdviceNumber());
                stmt.setArray(index++, generateTYP_GBM_PP_COL_DET_ARRAY_OBJJ(connection, cacAckNumMaintenanceDto.getCacAckNumList()));
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
    public ResponseDataDTO verifyDeleteSelect(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        Connection connection = null;
        CallableStatement stmt = null;

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<HashMap<String, String>> cacAckNumMaintenanceListt = null;
        List<HashMap<String, String>> cacAckNumMaintenanceList = null;
        double subScriptionAmt = 0;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CAC_ACK_NUM_sp_ver_sel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cacAckNumMaintenanceDto.getSchemeCode());
                stmt.setString(index++, cacAckNumMaintenanceDto.getSubsDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                if (stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    if (cacAckNumMaintenanceDto.getSubsDate() == null || cacAckNumMaintenanceDto.getSubsDate().trim().isEmpty()) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(12);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            cacAckNumMaintenanceListt = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("subsDate", resultSet.getString(1));
                                cacAckNumMaintenanceListt.add(temp);
                            }
                            cacAckNumMaintenanceDto.setCacAckNumMaintenanceListt(cacAckNumMaintenanceListt);
                        }
                    }
                    else if (null!=cacAckNumMaintenanceDto.getSubsDate()&&!cacAckNumMaintenanceDto.getSubsDate().trim().isEmpty()) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(12);
                        HashMap<String, String> temp = null;
                        cacAckNumMaintenanceList = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();
                            temp.put("bondLedgeAccNum", resultSet.getString("bla_no"));
                            temp.put("holderName", resultSet.getString("holder_name"));
                            temp.put("subsAmt", resultSet.getString("subscription_amt"));
                            temp.put("subsId", resultSet.getString("subscription_id"));
                            temp.put("bondType", resultSet.getString("bond_type"));
                            temp.put("casAckNum", resultSet.getString("cas_ack_num"));
                            temp.put("casAckDate", resultSet.getString("cas_ack_date"));
                            temp.put("casTranDate", resultSet.getString("cas_transfer_date"));

                            subScriptionAmt = subScriptionAmt + Double.parseDouble(
                                    (resultSet.getString("subscription_amt") != null) ? (resultSet.getString("subscription_amt"))
                                            : ("0"));
                            cacAckNumMaintenanceList.add(temp);
                            cacAckNumMaintenanceDto.setCasTranDate(resultSet.getString("cas_transfer_date"));
                        }
                        cacAckNumMaintenanceDto.setCacAckNumMaintenanceList(cacAckNumMaintenanceList);
                        cacAckNumMaintenanceDto.setTotalSubScriptionAmt(String.format("%.2f", subScriptionAmt));

                    }
                    cacAckNumMaintenanceDto.setCreatedBy(stmt.getString(5));
                    cacAckNumMaintenanceDto.setCreatedDate(stmt.getString(6));
                    cacAckNumMaintenanceDto.setLastModifiedBy(stmt.getString(7));
                    cacAckNumMaintenanceDto.setLastModifiedDate(stmt.getString(8));
                    cacAckNumMaintenanceDto.setVerifiedBy(stmt.getString(9));
                    cacAckNumMaintenanceDto.setVerifiedDate(stmt.getString(10));
                    cacAckNumMaintenanceDto.setVerifiedStatus(stmt.getString(11));
                    cacAckNumMaintenanceDto.setDeleteRemarks(stmt.getString(13));
                    cacAckNumMaintenanceDto.setCasAckNum(stmt.getString(14));
                    cacAckNumMaintenanceDto.setCasAckDate(stmt.getString(15));
                    cacAckNumMaintenanceDto.setBrkAdviceNumber(stmt.getString(16));
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
        responseDataDTO.setResult(cacAckNumMaintenanceDto);
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiry(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        double subScriptionAmt = 0;
        List<HashMap<String, String>> cacAckNumMaintenanceListt = null;
        List<HashMap<String, String>> cacAckNumMaintenanceList = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CAC_ACK_NUM_sp_inq.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cacAckNumMaintenanceDto.getSchemeCode());
                stmt.setString(index++, cacAckNumMaintenanceDto.getSubsDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                    if (cacAckNumMaintenanceDto.getSubsDate() == null || cacAckNumMaintenanceDto.getSubsDate().trim().isEmpty()) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(12);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            cacAckNumMaintenanceListt = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("subsDate", resultSet.getString(1));
                                cacAckNumMaintenanceListt.add(temp);
                            }
                            cacAckNumMaintenanceDto.setCacAckNumMaintenanceListt(cacAckNumMaintenanceListt);
                        }
                    }
                    else if (null!=cacAckNumMaintenanceDto.getSubsDate()&&!cacAckNumMaintenanceDto.getSubsDate().trim().isEmpty()) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(12);
                        HashMap<String, String> temp = null;
                        cacAckNumMaintenanceList = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();
                            temp.put("bondLedgeAccNum", resultSet.getString("bla_no"));
                            temp.put("holderName", resultSet.getString("holder_name"));
                            temp.put("subsAmt", resultSet.getString("subscription_amt"));
                            temp.put("subsId", resultSet.getString("subscription_id"));
                            temp.put("bondType", resultSet.getString("bond_type"));
                            temp.put("casAckNum", resultSet.getString("cas_ack_num"));
                            temp.put("casAckDate", resultSet.getString("cas_ack_date"));
                            temp.put("casTranDate", resultSet.getString("cas_transfer_date"));

                            subScriptionAmt = subScriptionAmt + Double.parseDouble(
                                    (resultSet.getString("subscription_amt") != null) ? (resultSet.getString("subscription_amt"))
                                            : ("0"));
                            cacAckNumMaintenanceList.add(temp);
                            cacAckNumMaintenanceDto.setCasTranDate(resultSet.getString("cas_transfer_date"));
                            cacAckNumMaintenanceDto.setTotalSubScriptionAmt(String.format("%.2f", subScriptionAmt));
                        }
                        cacAckNumMaintenanceDto.setCacAckNumMaintenanceList(cacAckNumMaintenanceList);
                    }
                    cacAckNumMaintenanceDto.setCreatedBy(stmt.getString(5));
                    cacAckNumMaintenanceDto.setCreatedDate(stmt.getString(6));
                    cacAckNumMaintenanceDto.setLastModifiedBy(stmt.getString(7));
                    cacAckNumMaintenanceDto.setLastModifiedDate(stmt.getString(8));
                    cacAckNumMaintenanceDto.setVerifiedBy(stmt.getString(9));
                    cacAckNumMaintenanceDto.setVerifiedDate(stmt.getString(10));
                    cacAckNumMaintenanceDto.setVerifiedStatus(stmt.getString(11));
                    cacAckNumMaintenanceDto.setDeleteRemarks(stmt.getString(13));
                    cacAckNumMaintenanceDto.setCasAckNum(stmt.getString(14));
                    cacAckNumMaintenanceDto.setCasAckDate(stmt.getString(15));
                    cacAckNumMaintenanceDto.setBrkAdviceNumber(stmt.getString(16));
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
        responseDataDTO.setResult(cacAckNumMaintenanceDto);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CAC_ACK_NUM_sp_ver.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cacAckNumMaintenanceDto.getSchemeCode());
                stmt.setString(index++,cacAckNumMaintenanceDto.getSubsDate());
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
    public ResponseDataDTO delete(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (cacAckNumMaintenanceDto.getDeleteRemarks() == null
                || cacAckNumMaintenanceDto.getDeleteRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(cacAckNumMaintenanceDto.getDeleteRemarks())) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CAC_ACK_NUM_sp_del.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cacAckNumMaintenanceDto.getSchemeCode());
                stmt.setString(index++,cacAckNumMaintenanceDto.getSubsDate());
                stmt.setString(index++,cacAckNumMaintenanceDto.getDeleteRemarks());
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
        return responseDataDTO;    }

    @Override
    public ResponseDataDTO modifySelect(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<HashMap<String, String>> cacAckNumMaintenanceListt = null;
        List<HashMap<String, String>> cacAckNumMaintenanceList = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CAC_ACK_NUM_sp_mod_sel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cacAckNumMaintenanceDto.getSchemeName());
                stmt.setString(index++, cacAckNumMaintenanceDto.getSubsFlg());
                stmt.setString(index++, cacAckNumMaintenanceDto.getSubsDate());
                stmt.setString(index++, cacAckNumMaintenanceDto.getSubsId());
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    if (cacAckNumMaintenanceDto.getSubsId() == null && cacAckNumMaintenanceDto.getSubsFlg()!=null && cacAckNumMaintenanceDto.getFlgg()!=null) {


                        ResultSet resultSet = (ResultSet) stmt.getObject(16);

                        HashMap<String, String> temp = null;
                        cacAckNumMaintenanceList = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();
                            temp.put("subsDate", resultSet.getString("subscription_date"));
                            temp.put("bondLedgeAccNum", resultSet.getString("bla_no"));
                            temp.put("holderName", resultSet.getString("holder_name"));
                            temp.put("subsAmt", resultSet.getString("subscription_amt"));
                            temp.put("subsId", resultSet.getString("subscription_id"));

                            cacAckNumMaintenanceList.add(temp);
                        }
                        cacAckNumMaintenanceDto.setCacAckNumMaintenanceList(cacAckNumMaintenanceList);

                    }
//
                    else if (cacAckNumMaintenanceDto.getSubsDate() == null && cacAckNumMaintenanceDto.getDateFlg()!=null) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(16);

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            cacAckNumMaintenanceListt = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("subsDate", resultSet.getString(1));
                                cacAckNumMaintenanceListt.add(temp);
                            }
                            cacAckNumMaintenanceDto.setCacAckNumMaintenanceListt(cacAckNumMaintenanceListt);
                        }
                    }

                    else if (cacAckNumMaintenanceDto.getSubsDate() != null && cacAckNumMaintenanceDto.getSubsId()!=null && cacAckNumMaintenanceDto.getFlg()!=null) {

                        cacAckNumMaintenanceDto.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(16)));

//                            cacAckNumMaintenanceList.add(cacAckNumMaintenanceDto);
//                        cacAckNumMaintenanceDto.setCacAckNumMaintenanceList(cacAckNumMaintenanceList);

                    }
                    else if (cacAckNumMaintenanceDto.getSubsDate() != null && cacAckNumMaintenanceDto.getSubsId()==null && cacAckNumMaintenanceDto.getFlg()!=null) {

                        cacAckNumMaintenanceDto.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(16)));

//                        cacAckNumMaintenanceList.add(cacAckNumMaintenanceDto);
//                        cacAckNumMaintenanceDto.setCacAckNumMaintenanceList(cacAckNumMaintenanceList);

                    }
//                    else if(cacAckNumMaintenanceDto.getFlg()!=null){
                    cacAckNumMaintenanceDto.setPkColumnValue(stmt.getString(7));
                    cacAckNumMaintenanceDto.setCreatedBy(stmt.getString(8));
                    cacAckNumMaintenanceDto.setCreatedDate(stmt.getString(9));
                    cacAckNumMaintenanceDto.setLastModifiedBy(stmt.getString(10));
                    cacAckNumMaintenanceDto.setLastModifiedDate(stmt.getString(11));
                    cacAckNumMaintenanceDto.setVerifiedBy(stmt.getString(12));
                    cacAckNumMaintenanceDto.setVerifiedDate(stmt.getString(13));
                    cacAckNumMaintenanceDto.setPreviousOperationType(stmt.getString(14));
                    cacAckNumMaintenanceDto.setVerifiedStatus(stmt.getString(15));
//                        responseDataDTO.setResult(cacAckNumMaintenanceList);
//                    }

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
        responseDataDTO.setResult(cacAckNumMaintenanceDto);
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;    }

    @Override
    public ResponseDataDTO modify(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CAC_ACK_NUM_sp_mod.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,cacAckNumMaintenanceDto.getSchemeName());
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, cacAckNumMaintenanceDto.getColumnValueDetailsList()));
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
    public ResponseDataDTO addDet(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<CacAckNumMaintenanceDto> cacAckNumMaintenanceList = new ArrayList<>();
        String resultMsg = "";

        double subScriptionAmt = 0;
        CacAckNumMaintenanceDto cacAckNumMaintenanceDto2=new CacAckNumMaintenanceDto();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CAC_ACK_NUM_sp_add_val.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cacAckNumMaintenanceDto.getSchemeCode());
                stmt.setString(index++, cacAckNumMaintenanceDto.getSubsDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    if (null!=cacAckNumMaintenanceDto.getSubsDate()&&!cacAckNumMaintenanceDto.getSubsDate().trim().isEmpty()) {
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(5);
                        while (resultSet1.next()) {
                            CacAckNumMaintenanceDto cacAckNumMaintenanceDto1 = new CacAckNumMaintenanceDto();
                            cacAckNumMaintenanceDto1.setSubsDate(resultSet1.getString("subscription_date"));
                            cacAckNumMaintenanceDto1.setBondLedgeAccNum(resultSet1.getString("bla_no"));
                            cacAckNumMaintenanceDto1.setHolderName(resultSet1.getString("HOLDER_NAME"));
                            cacAckNumMaintenanceDto1.setSubsId(resultSet1.getString("bond_subscription_id"));
                            cacAckNumMaintenanceDto1.setSubsAmt(resultSet1.getString("SUBSCRIPTION_AMOUNT"));
                            cacAckNumMaintenanceDto1.setBondType(resultSet1.getString("bond_type"));
                            subScriptionAmt = subScriptionAmt + Double.parseDouble(
                                    (resultSet1.getString("SUBSCRIPTION_AMOUNT") != null) ? (resultSet1.getString("SUBSCRIPTION_AMOUNT"))
                                            : ("0"));
                            cacAckNumMaintenanceList.add(cacAckNumMaintenanceDto1);
                            cacAckNumMaintenanceDto2.setCasTranDate(resultSet1.getString("cas_transfer_date"));
                        }
                        cacAckNumMaintenanceDto2.setCasAckNum(stmt.getString(6));
                        cacAckNumMaintenanceDto2.setBrkAdviceNumber(stmt.getString(7));
                        cacAckNumMaintenanceDto2.setTotalSubScriptionAmt(String.format("%.2f", subScriptionAmt));
                        cacAckNumMaintenanceDto2.setList(cacAckNumMaintenanceList);
                        responseDataDTO.setResult(cacAckNumMaintenanceDto2);
                    }
                    if (cacAckNumMaintenanceDto.getSubsDate() == null || cacAckNumMaintenanceDto.getSubsDate().trim().isEmpty()) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(5);
                        while (resultSet.next()) {
                            CacAckNumMaintenanceDto cacAckNumMaintenanceDto1 = new CacAckNumMaintenanceDto();
                            cacAckNumMaintenanceDto1.setSubsDate(resultSet.getString("subscription_date"));
                            cacAckNumMaintenanceList.add(cacAckNumMaintenanceDto1);
                        }
                        cacAckNumMaintenanceDto2.setList(cacAckNumMaintenanceList);
                        responseDataDTO.setResult(cacAckNumMaintenanceDto2);
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

    private ARRAY generateTYP_GBM_PP_COL_DET_ARRAY_OBJJ(Connection connection, List<CacAckNumMaintenanceDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_SUBS_CAC_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_SUBS_CAC", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        CacAckNumMaintenanceDto dto = null;
        for (int index = 0; index < list.size(); index++) {
            dto = list.get(index);
            Object[] paramArray = new Object[5];
            paramArray[0] = dto.getBondLedgeAccNum();
            paramArray[1] = dto.getHolderName();
            paramArray[2] = dto.getSubsId();
            paramArray[3] = dto.getSubsAmt();
            paramArray[4] = dto.getBondType();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    public static ARRAY generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(Connection connection, List<ColumnValueDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COLUMN_DETAILS, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_COLUMN_DETAILS, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        ColumnValueDTO columnValueDTO = null;
        for (int index = 0; index < list.size(); index++) {
            columnValueDTO = list.get(index);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("COLUMN_NAME", columnValueDTO.getColName());
            paramMap.put("COL_VALUE", columnValueDTO.getValue());

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramMap);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
}
