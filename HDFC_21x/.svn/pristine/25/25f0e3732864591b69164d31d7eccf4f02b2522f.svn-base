package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.RbiBondAckReceiptDAO;
import com.isl.gbm.rbibond.dto.RbiBondAckReceiptDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.dao.HibernateDao;
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
public class RbiBondAckReceiptDAOImpl extends HibernateDao implements RbiBondAckReceiptDAO {
    private static final Logger Log = LoggerFactory.getLogger(RbiBondAckReceiptDAOImpl.class);
    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO addTorch(RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACK_REC_ADD_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                Log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                Log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(3);
                    if (resultSet != null) {
                        List<RbiBondAckReceiptDTO> list = new ArrayList<>();
                        RbiBondAckReceiptDTO item = null;
                        while (resultSet.next()) {
                            item = new RbiBondAckReceiptDTO();
                            item.setId(resultSet.getString(1));
                            item.setBankAgencyCode(resultSet.getString(2));
                            item.setBorkerName(resultSet.getString(3));
                            item.setBranchId(resultSet.getString(4));
                            list.add(item);
                        }
                            responseDataDTO.setResult(list);
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
    public ResponseDataDTO detailAdd(RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACK_REC_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAckReceiptDTO.getSchemeCode());
                stmt.setString(index++, rbiBondAckReceiptDTO.getTransDate());
                stmt.setString(index++, rbiBondAckReceiptDTO.getBrokerId());
                stmt.setString(index++, rbiBondAckReceiptDTO.getBondHolderName());
                stmt.setString(index++, rbiBondAckReceiptDTO.getTransactionMode());
                stmt.setString(index++, rbiBondAckReceiptDTO.getDebitAccNum());
                stmt.setString(index++, rbiBondAckReceiptDTO.getBranchId());
                stmt.setString(index++, rbiBondAckReceiptDTO.getInstrumentType());
                stmt.setString(index++, rbiBondAckReceiptDTO.getInstrumentDate());
                stmt.setString(index++, rbiBondAckReceiptDTO.getInstrumentNum());
                stmt.setString(index++, rbiBondAckReceiptDTO.getAmount());
                stmt.setString(index++, rbiBondAckReceiptDTO.getDelDate());
                stmt.setString(index++, rbiBondAckReceiptDTO.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                Log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                Log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    rbiBondAckReceiptDTO.setApplicationNo(stmt.getString(16));

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
        responseDataDTO.setResult(rbiBondAckReceiptDTO);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO inquiry(RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_ACK_REC_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondAckReceiptDTO.getSchemeCode());
                stmt.setString(index++,rbiBondAckReceiptDTO.getApplicationNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                Log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                Log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index ));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(5);
                    if(rbiBondAckReceiptDTO.getApplicationNo() == null) {
                        if (resultSet != null) {
                            List<RbiBondAckReceiptDTO> list = new ArrayList<>();
                            RbiBondAckReceiptDTO item = null;
                            while (resultSet.next()) {
                                item = new RbiBondAckReceiptDTO();
                                item.setApplicationNo(resultSet.getString(1));
                                item.setBondHolderName(resultSet.getString(2));
                                item.setApplicationDate(resultSet.getString(3));
                                item.setAmount(resultSet.getString(4));
                                list.add(item);

                            }
                            responseDataDTO.setResult(list);
                        }
                    }else{


                        if (resultSet != null) {
                            List<RbiBondAckReceiptDTO> list = new ArrayList<>();
                            RbiBondAckReceiptDTO item = null;
                            while (resultSet.next()) {
                                item = new RbiBondAckReceiptDTO();
                                item.setId(resultSet.getString(1));
                                item.setSchemeCode(resultSet.getString(2));
                                item.setApplicationDate(resultSet.getString(3));
                                item.setTransDate(resultSet.getString(4));
                                item.setBrokerId(resultSet.getString(5));
                                item.setBondHolderName(resultSet.getString(6));
                                item.setTransactionMode(resultSet.getString(7));
                                item.setDebitAccNum(resultSet.getString(8));
                                item.setBranchId(resultSet.getString(9));
                                item.setInstrumentType(resultSet.getString(10));
                                item.setInstrumentNum(resultSet.getString(11));
                                item.setInstrumentDate(resultSet.getString(12));
                                item.setAmount(resultSet.getString(13));
                                item.setDelDate(resultSet.getString(14));
                                item.setRemarks(resultSet.getString(15));
                                item.setApplicationNo(resultSet.getString(16));
                                responseDataDTO.setResult(rbiBondAckReceiptDTO);
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


}
