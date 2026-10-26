package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.CumToNonCumChangeOfBondTypeDAO;
import com.isl.gbm.rbibond.dto.CumToNonCumChangeOfBondTypeDTO;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class CumToNonCumChangeOfBondTypeDAOImpl implements CumToNonCumChangeOfBondTypeDAO {
    private static final Logger log = LoggerFactory.getLogger(CumToNonCumChangeOfBondTypeDAOImpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;
    @Value("${profile.type}")
    private String stubProcess;
    @Override
    public ResponseDataDTO getRbiBondModuleNameDetails(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                if("PROD".equalsIgnoreCase(stubProcess)) {
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
                        resultMap.put("subCode",resultSet.getString(1));
                        resultMap.put("subCodeDescription", resultSet.getString(2));
                        resultMap.put("status", resultSet.getString(3));
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
    public ResponseDataDTO getRbiBondModuleNameDetailsVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_VER_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "S");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet=(ResultSet) stmt.getObject(9);
                    List<HashMap<String, String>> resultList = new ArrayList<>();
                    if (resultSet != null)
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("subCode",resultSet.getString(1));
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
    public ResponseDataDTO getRbiBondModuleNameDetailsInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_INQ_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "S");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                ResultSet resultSet=(ResultSet) stmt.getObject(9);
                    List<HashMap<String, String>> resultList = new ArrayList<>();
                if (resultSet != null)
                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("subCode",resultSet.getString(1));
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
    public ResponseDataDTO getBrokerTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {

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
                        cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }


    @Override

    public ResponseDataDTO getGuardianDetails(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
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
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getGuardianId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(3);

                    if (cumToNonCumChangeOfBondTypeDTO.getGuardianId() == null) {

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

    public ResponseDataDTO getAddDashBoard(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
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
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(4);

                    if (cumToNonCumChangeOfBondTypeDTO.getId() == null) {

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
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }

                    else if (cumToNonCumChangeOfBondTypeDTO.getId() != null) {

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
    public ResponseDataDTO add(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO1=new CumToNonCumChangeOfBondTypeDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_ADD_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "T");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(cumToNonCumChangeOfBondTypeDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(9);

                        if (resultSet != null) {
                            while (resultSet.next()) {
                                cumToNonCumChangeOfBondTypeDTO1.setBlaNo(resultSet.getString(2));
                                cumToNonCumChangeOfBondTypeDTO1.setSubscriptionId(resultSet.getString(3));
                                cumToNonCumChangeOfBondTypeDTO1.setSubscriptionAmount(resultSet.getString(11));
                                cumToNonCumChangeOfBondTypeDTO1.setAccountOpenDate(resultSet.getString(5));
                                cumToNonCumChangeOfBondTypeDTO1.setValueDate(resultSet.getString(6));
                                cumToNonCumChangeOfBondTypeDTO1.setPeriodOfDeposit(resultSet.getString(7));
                                cumToNonCumChangeOfBondTypeDTO1.setMaturityDate(resultSet.getString(8));
                                cumToNonCumChangeOfBondTypeDTO1.setRateOfInterest(resultSet.getString(13));
                                cumToNonCumChangeOfBondTypeDTO1.setMaturityAmt(resultSet.getString(12));
                                cumToNonCumChangeOfBondTypeDTO1.setPrincipleAmount(resultSet.getString(9));
                                cumToNonCumChangeOfBondTypeDTO1.setInterestAmount(resultSet.getString(10));
                                cumToNonCumChangeOfBondTypeDTO1.setSchemeName(resultSet.getString(1));
                            }
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO1);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO addSubmit(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {

        log.info("ADD input >>>>>>: {}" , cumToNonCumChangeOfBondTypeDTO.getDataList());
        log.info("ADD Coperson input >>>>>>: {}" , cumToNonCumChangeOfBondTypeDTO.getDataCopersondetailsList());
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CUM_SUBS_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getModeOfHolding());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getJointHolderNo());

                if (cumToNonCumChangeOfBondTypeDTO.getDataList() != null) {
                    stmt.setArray(index++, generateTYP_ty_gbm_bond_holder_dtls(connection, cumToNonCumChangeOfBondTypeDTO.getDataList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_BLA_ARRAY);
                }

                if (cumToNonCumChangeOfBondTypeDTO.getDataCopersondetailsList() != null) {
                    stmt.setArray(index++, generateTYP_gbm_rbi_bond_copar(connection, cumToNonCumChangeOfBondTypeDTO.getDataCopersondetailsList()));

//                    stmt.setArray(index++, generateTYP_gbm_rbi_bond_copar(connection, cumToNonCumChangeOfBondTypeDTO.getCoParson()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COPARS_ARRAY);
                }
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaFlag());


                //subscription

                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSingleBondAccNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getFirstJointHolderBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getFirstJointHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSecondJointHolderBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSecondJointHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getThirdJointHolderBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getThirdJointHolderName());

                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBrokerAgencyCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionAmount());

                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondType());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getAccountOpenDate());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getValueDate());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPeriodOfDeposit());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getMaturityDate());

                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getRateOfInterest());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getMaturityAmt());
//                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getModeOfInterestPayment());
//                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getOperativeAccNo());
//
//                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBankCode());
//                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBranchCode());
//                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getIfscCodeOfCreditBank());
//                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getNameOfThePayee());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getTranMode());

                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getDebitAccNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getInstrumentType());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getInstrumentNo());

                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getInstrumentDate());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getInstrumentAmount());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getTranCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getZoneCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getZoneDate());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBankCodeNonMicr());

                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBranchCodeNonMicr());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSortCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getRemarks());
                if (cumToNonCumChangeOfBondTypeDTO.getNomineeDetailsList() != null) {
                    stmt.setArray(index++, generateTYP_gbm_rbi_bond_nom_dtls(connection, cumToNonCumChangeOfBondTypeDTO.getNomineeDetailsList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_NOMINATION_ARRAY);
                }
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getIsNominee());

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
//
                    cumToNonCumChangeOfBondTypeDTO.setSingleBlaNo(stmt.getString(42));
                    cumToNonCumChangeOfBondTypeDTO.setFirstJointBlaNo(stmt.getString(43));
                    cumToNonCumChangeOfBondTypeDTO.setSecondJointBlaNo(stmt.getString(44));
                    cumToNonCumChangeOfBondTypeDTO.setThirdJointBlaNo(stmt.getString(45));
                    cumToNonCumChangeOfBondTypeDTO.setSingleGuardianId(stmt.getString(46));


                    cumToNonCumChangeOfBondTypeDTO.setFirstGuardianId(stmt.getString(47));
                    cumToNonCumChangeOfBondTypeDTO.setSecondGuardianId(stmt.getString(48));
                    cumToNonCumChangeOfBondTypeDTO.setThirdGuardianId(stmt.getString(49));
                    cumToNonCumChangeOfBondTypeDTO.setSubscriptionId(stmt.getString(50));
                    cumToNonCumChangeOfBondTypeDTO.setBlaNo(stmt.getString(51));
                    responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
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
    public ResponseDataDTO inquiry(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_INQ_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "T");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(9);

                    if (cumToNonCumChangeOfBondTypeDTO.getBlaNo() == null) {


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
                    } else if (cumToNonCumChangeOfBondTypeDTO.getSubscriptionId() == null) {


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

//                        ResultSet resultSet1 = (ResultSet) stmt.getObject(9);
//                        ResultSet resultSet2 = (ResultSet) stmt.getObject(10);
//                        ResultSet resultSet3 = (ResultSet) stmt.getObject(11);
//                        ResultSet resultSet4 = (ResultSet) stmt.getObject(12);
                        resultSet.next();
                        cumToNonCumChangeOfBondTypeDTO.setBlaNo(resultSet.getString(2));
                        cumToNonCumChangeOfBondTypeDTO.setSubscriptionId(resultSet.getString(3));
                        cumToNonCumChangeOfBondTypeDTO.setSubscriptionAmount(resultSet.getString(11));
                        cumToNonCumChangeOfBondTypeDTO.setAccountOpenDate(resultSet.getString(5));
                        cumToNonCumChangeOfBondTypeDTO.setValueDate(resultSet.getString(6));
                        cumToNonCumChangeOfBondTypeDTO.setPeriodOfDeposit(resultSet.getString(7));
                        cumToNonCumChangeOfBondTypeDTO.setMaturityDate(resultSet.getString(8));
                        cumToNonCumChangeOfBondTypeDTO.setRateOfInterest(resultSet.getString(13));
                        cumToNonCumChangeOfBondTypeDTO.setMaturityAmt(resultSet.getString(12));
                        cumToNonCumChangeOfBondTypeDTO.setPrincipleAmount(resultSet.getString(9));
                        cumToNonCumChangeOfBondTypeDTO.setInterestAmount(resultSet.getString(10));
                        cumToNonCumChangeOfBondTypeDTO.setSchemeName(resultSet.getString(1));
                        cumToNonCumChangeOfBondTypeDTO.setCreatedBy(resultSet.getString(15));
                        cumToNonCumChangeOfBondTypeDTO.setCreatedDate(resultSet.getString(16));
                        cumToNonCumChangeOfBondTypeDTO.setLastModifiedBy(resultSet.getString(17));
                        cumToNonCumChangeOfBondTypeDTO.setLastModifiedDate(resultSet.getString(18));
                        cumToNonCumChangeOfBondTypeDTO.setLastVerifiedBy(resultSet.getString(19));
                        cumToNonCumChangeOfBondTypeDTO.setLastVerifiedDate(resultSet.getString(20));
                        cumToNonCumChangeOfBondTypeDTO.setVerifiedStatus(resultSet.getString(14));
                        cumToNonCumChangeOfBondTypeDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
//                        cumToNonCumChangeOfBondTypeDTO.setCoparsDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet1));
//                        cumToNonCumChangeOfBondTypeDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
//                        cumToNonCumChangeOfBondTypeDTO.setSubscriptionDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet3));
//                        cumToNonCumChangeOfBondTypeDTO.setNomineeDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet4));
                        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
                        resultSet.close();
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
    public ResponseDataDTO modifySelect(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
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
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());

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

                    ResultSet resultSet = (ResultSet) stmt.getObject(8);

                    if (cumToNonCumChangeOfBondTypeDTO.getBlaNo() == null) {


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
                    } else if (cumToNonCumChangeOfBondTypeDTO.getSubscriptionId() == null) {


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
                        cumToNonCumChangeOfBondTypeDTO.setBlaGroupId(stmt.getString(13));
                        cumToNonCumChangeOfBondTypeDTO.setPkColumnValue(stmt.getString(14));
                        cumToNonCumChangeOfBondTypeDTO.setCreatedBy(stmt.getString(15));
                        cumToNonCumChangeOfBondTypeDTO.setCreatedDate(stmt.getString(16));
                        cumToNonCumChangeOfBondTypeDTO.setLastModifiedBy(stmt.getString(17));
                        cumToNonCumChangeOfBondTypeDTO.setLastModifiedDate(stmt.getString(18));
                        cumToNonCumChangeOfBondTypeDTO.setLastVerifiedBy(stmt.getString(19));
                        cumToNonCumChangeOfBondTypeDTO.setLastVerifiedDate(stmt.getString(20));
                        cumToNonCumChangeOfBondTypeDTO.setPreviousOperationType(stmt.getString(21));
                        cumToNonCumChangeOfBondTypeDTO.setVerifiedStatus(stmt.getString(22));
                        cumToNonCumChangeOfBondTypeDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        cumToNonCumChangeOfBondTypeDTO.setCoparsDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet1));
                        cumToNonCumChangeOfBondTypeDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
                        cumToNonCumChangeOfBondTypeDTO.setSubscriptionDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet3));
                        cumToNonCumChangeOfBondTypeDTO.setNomineeDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet4));
                        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
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
    public ResponseDataDTO modify(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultMsg = stmt.getString(index);
                }
                else{
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
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

        public ResponseDataDTO verifySelect(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_VER_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "T");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(9);

                    if (cumToNonCumChangeOfBondTypeDTO.getBlaNo() == null) {


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
                    } else if (cumToNonCumChangeOfBondTypeDTO.getSubscriptionId() == null) {


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

//                        ResultSet resultSet1 = (ResultSet) stmt.getObject(9);
//                        ResultSet resultSet2 = (ResultSet) stmt.getObject(10);
//                        ResultSet resultSet3 = (ResultSet) stmt.getObject(11);
//                        ResultSet resultSet4 = (ResultSet) stmt.getObject(12);
                        resultSet.next();
                        cumToNonCumChangeOfBondTypeDTO.setBlaNo(resultSet.getString(2));
                        cumToNonCumChangeOfBondTypeDTO.setSubscriptionId(resultSet.getString(3));
                        cumToNonCumChangeOfBondTypeDTO.setSubscriptionAmount(resultSet.getString(11));
                        cumToNonCumChangeOfBondTypeDTO.setAccountOpenDate(resultSet.getString(5));
                        cumToNonCumChangeOfBondTypeDTO.setValueDate(resultSet.getString(6));
                        cumToNonCumChangeOfBondTypeDTO.setPeriodOfDeposit(resultSet.getString(7));
                        cumToNonCumChangeOfBondTypeDTO.setMaturityDate(resultSet.getString(8));
                        cumToNonCumChangeOfBondTypeDTO.setRateOfInterest(resultSet.getString(13));
                        cumToNonCumChangeOfBondTypeDTO.setMaturityAmt(resultSet.getString(12));
                        cumToNonCumChangeOfBondTypeDTO.setPrincipleAmount(resultSet.getString(9));
                        cumToNonCumChangeOfBondTypeDTO.setInterestAmount(resultSet.getString(10));
                        cumToNonCumChangeOfBondTypeDTO.setSchemeName(resultSet.getString(1));
                        cumToNonCumChangeOfBondTypeDTO.setCreatedBy(resultSet.getString(15));
                        cumToNonCumChangeOfBondTypeDTO.setCreatedDate(resultSet.getString(16));
                        cumToNonCumChangeOfBondTypeDTO.setLastModifiedBy(resultSet.getString(17));
                        cumToNonCumChangeOfBondTypeDTO.setLastModifiedDate(resultSet.getString(18));
                        cumToNonCumChangeOfBondTypeDTO.setLastVerifiedBy(resultSet.getString(19));
                        cumToNonCumChangeOfBondTypeDTO.setLastVerifiedDate(resultSet.getString(20));
                        cumToNonCumChangeOfBondTypeDTO.setVerifiedStatus(resultSet.getString(14));
                        cumToNonCumChangeOfBondTypeDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
//                        cumToNonCumChangeOfBondTypeDTO.setCoparsDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet1));
//                        cumToNonCumChangeOfBondTypeDTO.setGuardianDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet2));
//                        cumToNonCumChangeOfBondTypeDTO.setSubscriptionDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet3));
//                        cumToNonCumChangeOfBondTypeDTO.setNomineeDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet4));
                        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
                        resultSet.close();
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
    public ResponseDataDTO verify(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
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
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaGroupId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO deleteSubmit(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (cumToNonCumChangeOfBondTypeDTO.getRejectionRemarks() == null
                || cumToNonCumChangeOfBondTypeDTO.getRejectionRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, "");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO submitVerifyForm(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, "");
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
    public ResponseDataDTO blaNo(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_ADD_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "B");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

//                    if(cumToNonCumChangeOfBondTypeDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(9);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("nameOfTheBondHolder", resultSet.getString(2));
                                temp.put("blaNo", resultSet.getString(4));
                                temp.put("modeOfHolding", resultSet.getString(1));
                                temp.put("panNum", resultSet.getString(3));
                                torchOut.add(temp);
                            }
                            cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
                        }
                    }
                }
//            }
        else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getsubscriptionId(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_ADD_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "S");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(cumToNonCumChangeOfBondTypeDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(9);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("subscriptionId", resultSet.getString(4));
                                temp.put("bondHolderName", resultSet.getString(1));
                                temp.put("panNo", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));

                                torchOut.add(temp);
                            }
                            cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getsubscriptionIdInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_INQ_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "SI");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(cumToNonCumChangeOfBondTypeDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(9);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("subscriptionId", resultSet.getString(4));
                                temp.put("bondHolderName", resultSet.getString(1));
                                temp.put("panNo", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));

                                torchOut.add(temp);
                            }
                            cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO getsubscriptionIdVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_VER_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "SI");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(cumToNonCumChangeOfBondTypeDTO.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(9);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("subscriptionId", resultSet.getString(4));
                                temp.put("bondHolderName", resultSet.getString(1));
                                temp.put("panNo", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));

                                torchOut.add(temp);
                            }
                            cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getblaNoInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_INQ_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "B");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(9);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("nameOfTheBondHolder", resultSet.getString(2));
                                temp.put("blaNo", resultSet.getString(4));
                                temp.put("modeOfHolding", resultSet.getString(1));
                                temp.put("panNum", resultSet.getString(3));
                                torchOut.add(temp);
                            }
                            cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getblaNoVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_VER_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "B");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(9);
                    List<HashMap<String, String>> torchOut = null;

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();
                            temp.put("nameOfTheBondHolder", resultSet.getString(2));
                            temp.put("blaNo", resultSet.getString(4));
                            temp.put("modeOfHolding", resultSet.getString(1));
                            temp.put("panNum", resultSet.getString(3));
                            torchOut.add(temp);
                        }
                        cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO blaTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_ADD_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "B");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(9);
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
                        cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getPantorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_ADD_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "P");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(9);
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
                        cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getPantorchInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_INQ_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "P");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(9);
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
                        cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getPantorchVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO){

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_CUM_TO_NONCUM_VER_TORCH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, "P");
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBlaNo());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(9);
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
                        cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifyPanTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO){

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
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getSchemeCode());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getBondHolderName());
                stmt.setString(index++, cumToNonCumChangeOfBondTypeDTO.getPanNum());
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
                        cumToNonCumChangeOfBondTypeDTO.setTorchValue(torchOut);
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
        responseDataDTO.setResult(cumToNonCumChangeOfBondTypeDTO);
        return responseDataDTO;
    }

    private ARRAY generateTYP_ty_gbm_bond_holder_dtls(Connection connection, List<CumToNonCumChangeOfBondTypeDTO.Data> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_RBI_BOND_BLA_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_RBI_BOND_BLA_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        CumToNonCumChangeOfBondTypeDTO.Data dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[58];
            dto = list.get(index);
            paramArray[0] = dto.getPanNo();
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
            paramArray[20] = dto.getBondHldMobNum();
            paramArray[21] = dto.getBondHldEmail();
            paramArray[22] = dto.getHufRegPowerOfAttorney();
            paramArray[23] = dto.getPowerOfAttorneyKartaName();
            paramArray[24] = dto.getHdfcOtherBank();
            paramArray[25] = dto.getOperativeAccNum();
            paramArray[26] = dto.getIfscCode();
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
            paramArray[49] = dto.getGuardianMobileNo();
            paramArray[50] = dto.getGuardianEmailId();
            paramArray[51] = dto.getJointHolderType();
            paramArray[52] = dto.getModeOfInterestPayment();
            paramArray[53] = dto.getBankCode();
            paramArray[54] = dto.getBranchCode();
            paramArray[55] = dto.getNameOfThePayee();
            paramArray[56] = dto.getOperativeAccNumber();
            paramArray[57] = dto.getMicrCode();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }



        private ARRAY generateTYP_gbm_rbi_bond_copar(Connection connection, List<CumToNonCumChangeOfBondTypeDTO.DataCopersondetails> list) throws Exception {
            StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_COPAR_OBJ", connection);
            ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_COPAR", connection);
            STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
            CumToNonCumChangeOfBondTypeDTO.DataCopersondetails dto = null;
            for (int index = 0; index < list.size(); index++) {
                Object[] paramArray = new Object[3];
                dto = list.get(index);
                paramArray[0] = dto.getCoparcenersName();
                paramArray[1] = dto.getCoparcenersRelation();
                paramArray[2] = dto.getCoparcenersDob();
                STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
                genericTableInsertStructs[index] = struct;
            }
            return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
        }
    private ARRAY generateTYP_gbm_rbi_bond_nom_dtls(Connection connection, List<CumToNonCumChangeOfBondTypeDTO.NomineeDetails> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_NOM_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        CumToNonCumChangeOfBondTypeDTO.NomineeDetails dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[25];
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
            paramArray[10] = dto.getOperativeAccNo();
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
            paramArray[22] = dto.getGuardianMobileNo();
            paramArray[23] = dto.getGuardianEmail();
            paramArray[24] = dto.getTemp();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    }


