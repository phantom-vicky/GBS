package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.BrokerMasterMaintainanceDao;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.BrokerMasterMainDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.internal.OracleTypes;
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


@Repository
public class BrokerMasterMaintananceDaoimpl implements BrokerMasterMaintainanceDao {


    private static final Logger log = LoggerFactory.getLogger(BrokerMasterMaintananceDaoimpl.class);

    
    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Override
    public ResponseDataDTO add(BrokerMasterMainDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_MAS_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBrokerAgencyCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBrokerName());
                stmt.setString(index++, rbiBondTdsParameterDTO.getDateOfBirth());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBrokerGroup());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBrokerSortCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getHdfcOtherBank());
                stmt.setString(index++, rbiBondTdsParameterDTO.getOperativeAccNo());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBankName());
                stmt.setString(index++, rbiBondTdsParameterDTO.getHdfcBranchCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getTypeOfBroker());
                stmt.setString(index++, rbiBondTdsParameterDTO.getIfsc());
                stmt.setString(index++, rbiBondTdsParameterDTO.getGst());
                stmt.setString(index++, rbiBondTdsParameterDTO.getPan() );
                stmt.setString(index++, rbiBondTdsParameterDTO.getIsBrokerReg());
                stmt.setString(index++, rbiBondTdsParameterDTO.getAddress());
                stmt.setString(index++, rbiBondTdsParameterDTO.getMobileNo());
                stmt.setString(index++, rbiBondTdsParameterDTO.getEmail());
                stmt.setString(index++, rbiBondTdsParameterDTO.getRemarks());
                stmt.setString(index++, rbiBondTdsParameterDTO.getState());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBrokerageCreateDate());
                stmt.setString(index++, rbiBondTdsParameterDTO.getPaymentMode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getNonBankName());
                stmt.setString(index++, rbiBondTdsParameterDTO.getInstIfscCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getMicrCodeLoc());
                stmt.setString(index++, rbiBondTdsParameterDTO.getMicrBankCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getMicrBranchCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getAddress1());
                stmt.setString(index++, rbiBondTdsParameterDTO.getAddress2());
                stmt.setString(index++, rbiBondTdsParameterDTO.getPinCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getCity());
                stmt.setString(index++, rbiBondTdsParameterDTO.getPhoneNumber());
                stmt.setString(index++, rbiBondTdsParameterDTO.getFax());
                stmt.setString(index++, rbiBondTdsParameterDTO.getNameOfthePayee());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBankCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBranchCode());
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
    public ResponseDataDTO addValidation(BrokerMasterMainDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_MAS_ADD_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBrokerAgencyCode());
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
    public ResponseDataDTO inquiryDetails(BrokerMasterMainDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_MAS_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBrokerAgencyCode());
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (rbiBondTdsParameterDTO.getBrokerAgencyCode() == null) {
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(14);

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("brokerAgencyCode", resultSet.getString(1));
                                temp.put("brokerName", resultSet.getString(2));
//                                temp.put("operativeBranchId", resultSet.getString(3));
                                temp.put("verifiedStatus", resultSet.getString(3));
                                tdsCode.add(temp);
                            }
                            rbiBondTdsParameterDTO.setTorchValue(tdsCode);
                        }
                    }

                    else {
                        rbiBondTdsParameterDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));
                        rbiBondTdsParameterDTO.setPkColumnValue(stmt.getString(4));
                        rbiBondTdsParameterDTO.setCreatedBy(stmt.getString(5));
                        rbiBondTdsParameterDTO.setCreatedDate(stmt.getString(6));
                        rbiBondTdsParameterDTO.setModifiedBy(stmt.getString(7));
                        rbiBondTdsParameterDTO.setModifiedDate(stmt.getString(8));
                        rbiBondTdsParameterDTO.setVerifiedBy(stmt.getString(9));
                        rbiBondTdsParameterDTO.setVerifiedDate(stmt.getString(10));
                        rbiBondTdsParameterDTO.setPrevOperType(stmt.getString(11));
                        rbiBondTdsParameterDTO.setVerifiedStatus(stmt.getString(12));
                        rbiBondTdsParameterDTO.setRemarks(stmt.getString(13));
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
        responseDataDTO.setResult(rbiBondTdsParameterDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyDeleteDetails(BrokerMasterMainDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_MAS_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBrokerAgencyCode());
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (rbiBondTdsParameterDTO.getBrokerAgencyCode() == null) {
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(14);

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {

                                temp = new HashMap<>();
                                temp.put("brokerAgencyCode", resultSet.getString(1));
                                temp.put("brokerName", resultSet.getString(2));
                                //temp.put("operativeBranchId", resultSet.getString(3));
                                temp.put("verifiedStatus", resultSet.getString(3));
                                tdsCode.add(temp);

                            }
                            rbiBondTdsParameterDTO.setTorchValue(tdsCode);
                        }
                    }
                    else {

                        rbiBondTdsParameterDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));
                        rbiBondTdsParameterDTO.setPkColumnValue(stmt.getString(4));
                        rbiBondTdsParameterDTO.setCreatedBy(stmt.getString(5));
                        rbiBondTdsParameterDTO.setCreatedDate(stmt.getString(6));
                        rbiBondTdsParameterDTO.setModifiedBy(stmt.getString(7));
                        rbiBondTdsParameterDTO.setModifiedDate(stmt.getString(8));
                        rbiBondTdsParameterDTO.setVerifiedBy(stmt.getString(9));
                        rbiBondTdsParameterDTO.setVerifiedDate(stmt.getString(10));
                        rbiBondTdsParameterDTO.setPrevOperType(stmt.getString(11));
                        rbiBondTdsParameterDTO.setVerifiedStatus(stmt.getString(12));
                        rbiBondTdsParameterDTO.setRemarks(stmt.getString(13));
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
        responseDataDTO.setResult(rbiBondTdsParameterDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(BrokerMasterMainDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_MAS_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getId());
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
    public ResponseDataDTO delete(BrokerMasterMainDTO rbiBondTdsParameterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondTdsParameterDTO.getRemarks() == null
                || rbiBondTdsParameterDTO.getRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(rbiBondTdsParameterDTO.getRemarks())) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_MAS_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getRemarks());
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
    public ResponseDataDTO modifyDetails(BrokerMasterMainDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_MAS_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBrokerAgencyCode());
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    if(rbiBondTdsParameterDTO.getBrokerAgencyCode() == null){
                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(14);

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {

                                temp = new HashMap<>();
                                temp.put("brokerAgencyCode", resultSet.getString(1));
                                temp.put("brokerName", resultSet.getString(2));
                            //    temp.put("operativeBranchId", resultSet.getString(3));
                                temp.put("verifiedStatus", resultSet.getString(3));
                                tdsCode.add(temp);

                            }
                            rbiBondTdsParameterDTO.setTorchValue(tdsCode);
                        }
                    }else{
                        rbiBondTdsParameterDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));
                        rbiBondTdsParameterDTO.setPkColumnValue(stmt.getString(4));
                        rbiBondTdsParameterDTO.setCreatedBy(stmt.getString(5));
                        rbiBondTdsParameterDTO.setCreatedDate(stmt.getString(6));
                        rbiBondTdsParameterDTO.setModifiedBy(stmt.getString(7));
                        rbiBondTdsParameterDTO.setModifiedDate(stmt.getString(8));
                        rbiBondTdsParameterDTO.setVerifiedBy(stmt.getString(9));
                        rbiBondTdsParameterDTO.setVerifiedDate(stmt.getString(10));
                        rbiBondTdsParameterDTO.setPrevOperType(stmt.getString(11));
                        rbiBondTdsParameterDTO.setVerifiedStatus(stmt.getString(12));
                        rbiBondTdsParameterDTO.setRemarks(stmt.getString(13));
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
        responseDataDTO.setResult(rbiBondTdsParameterDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySubmit(BrokerMasterMainDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_MAS_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTdsParameterDTO.getId());
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, rbiBondTdsParameterDTO.getColumnValues()));
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
    public ResponseDataDTO getBranchCode(BrokerMasterMainDTO rbiBondTdsParameterDTO) {
        return null;
    }

    @Override
    public ResponseDataDTO getNonHdfcTorch(BrokerMasterMainDTO rbiBondTdsParameterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_MAS_TOR.getProcName());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBankCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getMicrBankCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getMicrBranchCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getInstIfscCode());
                stmt.setString(index++, rbiBondTdsParameterDTO.getBankFlag());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();


                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    if (rbiBondTdsParameterDTO.getBankFlag() != null && rbiBondTdsParameterDTO.getBankFlag().equalsIgnoreCase("B")) {

                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(6);

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {

                                temp = new HashMap<>();
                                temp.put("bankName", resultSet.getString(1));
                                temp.put("bankCode", resultSet.getString(2));
                                tdsCode.add(temp);

                            }
                            rbiBondTdsParameterDTO.setTorchValue(tdsCode);
                        }



                    }

                    else {


                        List<HashMap<String, String>> tdsCode = null;
                        resultSet = (ResultSet) stmt.getObject(6);

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            tdsCode = new ArrayList<>();
                            while (resultSet.next()) {

                                temp = new HashMap<>();
                                temp.put("bankName", resultSet.getString(1));
                                temp.put("instIfscCode", resultSet.getString(2));
                                temp.put("micrCodeLoc", resultSet.getString(3));
                                temp.put("micrBankCode", resultSet.getString(4));
                                temp.put("micrBranchCode", resultSet.getString(5));
                                temp.put("bankCode", resultSet.getString(6));
                                tdsCode.add(temp);



                            }
                            rbiBondTdsParameterDTO.setTorchValue(tdsCode);
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
        responseDataDTO.setResult(rbiBondTdsParameterDTO);
        return responseDataDTO;
    }


}
