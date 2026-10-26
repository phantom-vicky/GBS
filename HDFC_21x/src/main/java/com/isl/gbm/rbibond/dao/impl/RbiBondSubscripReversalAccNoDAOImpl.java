package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.RbiBondSubscripReversalAccNoDAO;
import com.isl.gbm.rbibond.dto.BondSubscripReversalAccNoDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RbiBondSubscripReversalAccNoDAOImpl implements RbiBondSubscripReversalAccNoDAO {

    private static final Logger Log = LoggerFactory.getLogger(RbiBondSubscripReversalAccNoDAOImpl.class);
    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO add(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SUB_REV_ACC_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getSchemeCode());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getFromBlaNo());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getToBlaNo());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getSubscriptionId());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getSubscriptionDate());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getSubscriptionAmt());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                Log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                Log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    bondSubscripReversalAccNoDTO.setReversalId(stmt.getString(10));

                }
            }
        } catch (Exception e) {
            Log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(bondSubscripReversalAccNoDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addValidation(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SUB_REV_ACC_ADD_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getSchemeCode());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getFlag());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getFromBlaNo());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getSubscriptionId());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getToBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();


                Log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                Log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(bondSubscripReversalAccNoDTO.getSchemeCode() == null) {
                        Log.info("in scheme");
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();

                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    else if (bondSubscripReversalAccNoDTO.getFlag() != null && bondSubscripReversalAccNoDTO.getFlag().equalsIgnoreCase("B")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();
                                item.setFromBlaNo(resultSet.getString(1));
                                item.setBondHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    else if (bondSubscripReversalAccNoDTO.getSchemeCode()!=null && bondSubscripReversalAccNoDTO.getFlag() != null && bondSubscripReversalAccNoDTO.getFlag().equalsIgnoreCase("S")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();
                                item.setSubscriptionId(resultSet.getString(1));
                                item.setSubscriptionDate(resultSet.getString(2));
                                item.setSubscriptionAmt(resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondSubscripReversalAccNoDTO.getSchemeCode()!=null && bondSubscripReversalAccNoDTO.getFlag() != null && bondSubscripReversalAccNoDTO.getFlag().equalsIgnoreCase("FB")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();

                                item.setToBlaNo(resultSet.getString(1));
                                item.setBondHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));
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
            Log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }


        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SUB_REV_ACC_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getSchemeCode());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getReversalId());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                Log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                Log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    bondSubscripReversalAccNoDTO.setReversalId(stmt.getString(6));
                }
            } else {
                Log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            Log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(bondSubscripReversalAccNoDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (bondSubscripReversalAccNoDTO.getRemarks() == null
                || bondSubscripReversalAccNoDTO.getRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SUB_REV_ACC_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getSchemeCode());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getReversalId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                Log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                Log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    bondSubscripReversalAccNoDTO.setReversalId(stmt.getString(5));
                }
            } else {
                Log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            Log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(bondSubscripReversalAccNoDTO);
        return responseDataDTO;
    }

//    @Override
    public ResponseDataDTO x(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SUB_REV_ACC_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getSchemeCode());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getReversalId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                Log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                Log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    bondSubscripReversalAccNoDTO.setReversalId(stmt.getString(5));

                }
            } else {
                Log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            Log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(bondSubscripReversalAccNoDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiry(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SUB_REV_ACC_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getSchemeCode());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getReversalId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                Log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                Log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(10);
                    if (bondSubscripReversalAccNoDTO.getSchemeCode() != null && bondSubscripReversalAccNoDTO.getReversalId() == null) {
                        if (resultSet != null) {
                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();
                                item.setReversalId(resultSet.getString(1));
                                item.setSubscriptionId(resultSet.getString(2));
                                item.setFromBlaNo(resultSet.getString(3));
                                item.setToBlaNo(resultSet.getString(4));
                                item.setVerifyStatus(resultSet.getString(5));
//                                item.setFromBlaNo(resultSet.getString(6));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else if (bondSubscripReversalAccNoDTO.getSchemeCode() != null && bondSubscripReversalAccNoDTO.getReversalId() != null) {
                        if (resultSet != null) {
                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();
                                item.setId(resultSet.getString(1));
                                item.setSchemeCode(resultSet.getString(2));
                                item.setReversalId(resultSet.getString(3));
                                item.setBranchId(resultSet.getString(4));
                                item.setSubscriptionId(resultSet.getString(5));
                                item.setSubscriptionDate(resultSet.getString(6));
                                item.setSubscriptionAmt(resultSet.getString(7));
                                item.setFromBlaNo(resultSet.getString(8));
                                item.setToBlaNo(resultSet.getString(9));
                                item.setRemarks(resultSet.getString(10));
                                item.setVerifyStatus(resultSet.getString(11));
                                list.add(item);

                            }
                            bondSubscripReversalAccNoDTO.setCursorOut(list);
                            bondSubscripReversalAccNoDTO.setCreatedBy(stmt.getString(5));
                            bondSubscripReversalAccNoDTO.setCreateDate(stmt.getString(6));
                            bondSubscripReversalAccNoDTO.setVerifyBy(stmt.getString(7));
                            bondSubscripReversalAccNoDTO.setVerifyDate(stmt.getString(8));
                            bondSubscripReversalAccNoDTO.setVerifyStatus(stmt.getString(9));
                        }


                        responseDataDTO.setResult(bondSubscripReversalAccNoDTO);
                    }

                }


            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            Log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySelect(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_SUB_REV_ACC_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getSchemeCode());
                stmt.setString(index++, bondSubscripReversalAccNoDTO.getReversalId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                Log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                Log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(10);
                    if (bondSubscripReversalAccNoDTO.getSchemeCode() != null && bondSubscripReversalAccNoDTO.getReversalId() == null) {

                        if (resultSet != null) {

                            ResultSetMetaData metaData = resultSet.getMetaData();
                            int columnCount = metaData.getColumnCount();
                            // Print the column names
                            Log.info("Cursor Column Details:");
                            for (int i = 1; i <= columnCount; i++) {
                                Log.info("Column Index: " + i + ", Column Name: " + metaData.getColumnName(i));
                            }

                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();
                                item.setReversalId(resultSet.getString(1));
                                item.setSubscriptionId(resultSet.getString(2));
                                item.setFromBlaNo(resultSet.getString(3));
                                item.setToBlaNo(resultSet.getString(4));
                                item.setVerifyStatus(resultSet.getString(5));
//                                item.setFromBlaNo(resultSet.getString(6));


                                list.add(item);

                                for (int i = 1; i <= columnCount; i++) {
                                    Log.info("Column Name: " + metaData.getColumnName(i) + ", Value: " + resultSet.getString(i));
                                    Log.info("end");
                                }
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else if (bondSubscripReversalAccNoDTO.getSchemeCode() != null && bondSubscripReversalAccNoDTO.getReversalId() != null) {
                        if (resultSet != null) {


                            ResultSetMetaData metaData = resultSet.getMetaData();
                            int columnCount = metaData.getColumnCount();

                            // Print the column names
                            Log.info("Cursor Column Details:");
                            for (int i = 1; i <= columnCount; i++) {
                                Log.info("Column Index: " + i + ", Column Name: " + metaData.getColumnName(i));
                                Log.info("end");
                            }
                            List<BondSubscripReversalAccNoDTO> list = new ArrayList<>();
                            BondSubscripReversalAccNoDTO item = null;
                            while (resultSet.next()) {
                                item = new BondSubscripReversalAccNoDTO();
                                item.setId(resultSet.getString(1));
                                item.setSchemeCode(resultSet.getString(2));
                                item.setReversalId(resultSet.getString(3));
                                item.setBranchId(resultSet.getString(4));
                                item.setSubscriptionId(resultSet.getString(5));
                                item.setSubscriptionDate(resultSet.getString(6));
                                item.setSubscriptionAmt(resultSet.getString(7));
                                item.setFromBlaNo(resultSet.getString(8));
                                item.setToBlaNo(resultSet.getString(9));
                                item.setRemarks(resultSet.getString(10));
                                item.setVerifyStatus(resultSet.getString(11));
                                list.add(item);

                                for (int i = 1; i <= columnCount; i++) {
                                    Log.info("Column Name: " + metaData.getColumnName(i) + ", Value: " + resultSet.getString(i));
                                    Log.info("end");
                                }
                            }
                            bondSubscripReversalAccNoDTO.setCursorOut(list);
                            bondSubscripReversalAccNoDTO.setCreatedBy(stmt.getString(5));
                            bondSubscripReversalAccNoDTO.setCreateDate(stmt.getString(6));
                            bondSubscripReversalAccNoDTO.setVerifyBy(stmt.getString(7));
                            bondSubscripReversalAccNoDTO.setVerifyDate(stmt.getString(8));
                            bondSubscripReversalAccNoDTO.setVerifyStatus(stmt.getString(9));
                        }
                        responseDataDTO.setResult(bondSubscripReversalAccNoDTO);
                    }
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            Log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
}
