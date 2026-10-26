package com.isl.gbm.common.dao.impl;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dao.BodEodStatusInqDAO;
import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.*;
import com.isl.gbm.common.utils.ProcNameEnum;
import com.isl.gbm.common.utils.ProcNamesEnum;
import com.isl.gbm.user.management.util.CbdtProcNameEnum;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class BodEodStatusInqDAOImpl implements BodEodStatusInqDAO {


    private static final Logger log = LoggerFactory.getLogger(BodEodStatusInqDAOImpl.class);
    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO getStatusInquiry() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        java.lang.String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareStatement("select sub_code, sub_code_desc from GBM_SUB_CODE_MASTER  where code ='020' and is_deleted='N'");
                resultSet = stmt.executeQuery();
                List<HashMap<String, String>> resultList = new ArrayList<>();
                if (resultSet != null)
                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("subCode", resultSet.getString("sub_code"));
                        resultMap.put("subCodeDescription", resultSet.getString("sub_code_desc"));
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
    public ResponseDataDTO getModuleName() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        java.lang.String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareStatement("select code,code_desc from GBM_CODE_MASTER a where a.code in('001','002','003','004','005','006','007','008','009','ALL')");
                resultSet = stmt.executeQuery();
                List<HashMap<String, String>> resultList = new ArrayList<>();
                if (resultSet != null)
                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("subCode", resultSet.getString("code"));
                        resultMap.put("subCodeDescription", resultSet.getString("code_desc"));
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
    public ResponseDataDTO getInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        BodEodStatusInqDTO output = new BodEodStatusInqDTO();
        List<HashMap<String, String>> torchOut = null;
        ResponseDataDTO responseDataDTO = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if(CommonConstants.APY_MODULE_CODE.equalsIgnoreCase(bodEodStatusInqDTO.getModuleId())) {
                    stmt = connection.prepareCall(ApyProcEnum.gbm_bod_eod_status_inq_apy.getProcName());
                }else if(CommonConstants.CBDT_MODULE_ID.equalsIgnoreCase(bodEodStatusInqDTO.getModuleId())) {
                    stmt = connection.prepareCall(CbdtProcNameEnum.gbm_bod_eod_status_inq_cbdt.getProcName());
                }else if(CommonConstants.SCSS_MODULE_CODE.equalsIgnoreCase(bodEodStatusInqDTO.getModuleId())) {
                    stmt = connection.prepareCall(ScssProcNameEnum.gbm_bod_eod_status_inq_scss.getProcName());
                }else if(CommonConstants.KVP_MODULE_CODE.equalsIgnoreCase(bodEodStatusInqDTO.getModuleId())) {
                    stmt = connection.prepareCall(ProcNameEnum.gbm_bod_eod_status_inq_kvp.getProcName());
                }else if(CommonConstants.GST_MODULE_CODE.equalsIgnoreCase(bodEodStatusInqDTO.getModuleId())) {
                    stmt = connection.prepareCall(CbdtProcNameEnum.gbm_bod_eod_status_inq_gst.getProcName());
                }else if(CommonConstants.SSA_MODULE_CODE.equalsIgnoreCase(bodEodStatusInqDTO.getModuleId())) {
//                    stmt = connection.prepareCall(SsaProcNameEnum.gbm_bod_eod_status_inq_ssa.getProcName());
                    stmt = connection.prepareCall("");
                }else{
                    stmt = connection.prepareCall(PpfProcNamesEnum.gbm_bod_eod_status_inq_ppf.getProcName());
                }
                stmt.setString(index++, bodEodStatusInqDTO.getStatusInquiry());
                stmt.setString(index++, bodEodStatusInqDTO.getModuleId());
                stmt.setString(index++, bodEodStatusInqDTO.getTreasuryCode());
                stmt.setString(index++, bodEodStatusInqDTO.getLevelOrBranchFlag());
                stmt.setString(index++, bodEodStatusInqDTO.getLevelId());
                stmt.setString(index++, bodEodStatusInqDTO.getLevelBranchId());
                stmt.setString(index++, bodEodStatusInqDTO.getBranchId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if ((stmt.getString(index - 1)) != null && (CommonConstants.SUCCESS).equalsIgnoreCase(stmt.getString(index - 1))) {

                    if (bodEodStatusInqDTO.getStatusInquiry() != null && "Y".equalsIgnoreCase(bodEodStatusInqDTO.getLevelOrBranchFlag())) {
                        rs = (ResultSet) stmt.getObject(8);
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (rs.next()) {
                                if (rs.getString(1) != null) {
                                    temp = new HashMap<>();
                                    temp.put("levelId", rs.getString(1));
                                    temp.put("levelName", rs.getString(2));
                                    torchOut.add(temp);
                                }
                            }
                            output.setLevelNameList(torchOut);
                        }
                    } else if (bodEodStatusInqDTO.getStatusInquiry() != null && "N".equalsIgnoreCase(bodEodStatusInqDTO.getLevelOrBranchFlag())) {
                        rs = (ResultSet) stmt.getObject(8);
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (rs.next()) {
                                if (rs.getString(1) != null) {
                                    temp = new HashMap<>();
                                    temp.put("levelBranchId", rs.getString(1));
                                    temp.put("levelBranchName", rs.getString(2));
                                    torchOut.add(temp);
                                }
                            }
                            output.setLevelBranchIdList(torchOut);
                        }
                    } else if (bodEodStatusInqDTO.getStatusInquiry() != null && "B".equalsIgnoreCase(bodEodStatusInqDTO.getLevelOrBranchFlag())) {
                        rs = (ResultSet) stmt.getObject(8);
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (rs.next()) {
                                if (rs.getString(1) != null) {
                                    temp = new HashMap<>();
                                    temp.put("branchId", rs.getString(1));
                                    temp.put("branchName", rs.getString(2));
                                    torchOut.add(temp);
                                }
                            }
                            output.setBranchIdList(torchOut);
                        }
                    } else if (bodEodStatusInqDTO.getStatusInquiry() != null && "C".equalsIgnoreCase(bodEodStatusInqDTO.getLevelOrBranchFlag())) {
                        rs = (ResultSet) stmt.getObject(8);
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (rs.next()) {
                                if (rs.getString(1) != null) {
                                    temp = new HashMap<>();
                                    temp.put("solId", rs.getString(1));
                                    temp.put("solDesc", rs.getString(2));
                                    temp.put("levelName", rs.getString(3));
                                    temp.put("lastEodDate", rs.getString(4));
                                    torchOut.add(temp);
                                }
                            }
                            output.setBodEodStatusDetailsList(torchOut);
                        }
                    }

                } else {
                    log.info("Proc Status Description " + resultMsg);
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
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(output);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getStateTaxInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        BodEodStatusInqDTO output = new BodEodStatusInqDTO();
        List<HashMap<String, String>> torchOut = null;
        ResponseDataDTO responseDataDTO = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNamesEnum.gbm_st_bod_eod_status_inq.getProcName());
                stmt.setString(index++, bodEodStatusInqDTO.getStatusInquiry());
                stmt.setString(index++, bodEodStatusInqDTO.getModuleId());
                stmt.setString(index++, bodEodStatusInqDTO.getTreasuryCode());
                stmt.setString(index++, bodEodStatusInqDTO.getLevelOrBranchFlag());
                stmt.setString(index++, bodEodStatusInqDTO.getLevelId());
                stmt.setString(index++, bodEodStatusInqDTO.getLevelBranchId());
                stmt.setString(index++, bodEodStatusInqDTO.getBranchId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if ((stmt.getString(index - 1)) != null && (CommonConstants.SUCCESS).equalsIgnoreCase(stmt.getString(index - 1))) {

                    if (bodEodStatusInqDTO.getStatusInquiry() != null && "Y".equalsIgnoreCase(bodEodStatusInqDTO.getLevelOrBranchFlag())) {
                        rs = (ResultSet) stmt.getObject(8);
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (rs.next()) {
                                if (rs.getString(1) != null) {
                                    temp = new HashMap<>();
                                    temp.put("levelId", rs.getString(1));
                                    temp.put("levelName", rs.getString(2));
                                    torchOut.add(temp);
                                }
                            }
                            output.setLevelNameList(torchOut);
                        }
                    } else if (bodEodStatusInqDTO.getStatusInquiry() != null && "N".equalsIgnoreCase(bodEodStatusInqDTO.getLevelOrBranchFlag())) {
                        rs = (ResultSet) stmt.getObject(8);
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (rs.next()) {
                                if (rs.getString(1) != null) {
                                    temp = new HashMap<>();
                                    temp.put("levelBranchId", rs.getString(1));
                                    temp.put("levelBranchName", rs.getString(2));
                                    torchOut.add(temp);
                                }
                            }
                            output.setLevelBranchIdList(torchOut);
                        }
                    } else if (bodEodStatusInqDTO.getStatusInquiry() != null && "B".equalsIgnoreCase(bodEodStatusInqDTO.getLevelOrBranchFlag())) {
                        rs = (ResultSet) stmt.getObject(8);
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (rs.next()) {
                                if (rs.getString(1) != null) {
                                    temp = new HashMap<>();
                                    temp.put("branchId", rs.getString(1));
                                    temp.put("branchName", rs.getString(2));
                                    torchOut.add(temp);
                                }
                            }
                            output.setBranchIdList(torchOut);
                        }
                    } else if (bodEodStatusInqDTO.getStatusInquiry() != null && "C".equalsIgnoreCase(bodEodStatusInqDTO.getLevelOrBranchFlag())) {
                        rs = (ResultSet) stmt.getObject(8);
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (rs.next()) {
                                if (rs.getString(1) != null) {
                                    temp = new HashMap<>();
                                    temp.put("solId", rs.getString(1));
                                    temp.put("solDesc", rs.getString(2));
                                    temp.put("levelName", rs.getString(3));
                                    temp.put("lastEodDate", rs.getString(4));
                                    torchOut.add(temp);
                                }
                            }
                            output.setBodEodStatusDetailsList(torchOut);
                        }
                    }

                } else {
                    log.info("Proc Status Description " + resultMsg);
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
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(output);
        return responseDataDTO;
    }

}
