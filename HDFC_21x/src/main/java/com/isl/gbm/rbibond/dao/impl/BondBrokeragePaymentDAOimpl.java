package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.BondBrokeragePaymentDAO;
import com.isl.gbm.rbibond.dto.BondBrokeragePaymentDTO;
import com.isl.gbm.rbibond.util.JDBCTemplateUtil;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import jakarta.annotation.PostConstruct;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


@Repository
public class BondBrokeragePaymentDAOimpl implements BondBrokeragePaymentDAO {

    private static final Logger log = LoggerFactory.getLogger(BondBrokeragePaymentDAOimpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JDBCTemplateUtil jDBCTemplateUtil;

    @PostConstruct
    public void init() {
        jDBCTemplateUtil = new JDBCTemplateUtil(jdbcTemplate);
    }


    @Override
    public ResponseDataDTO getBrokerDetails(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return null;
    }

    @Override
    public ResponseDataDTO add(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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

    private Array generateArrayForBrokerDetailsAdd(Connection connection, List<BondBrokeragePaymentDTO.BrokerDetails> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_BROK_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_BROK_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[7];
            BondBrokeragePaymentDTO.BrokerDetails item = list.get(index);
            paramArray[0] = item.getBlaNo();
            paramArray[1] = item.getSubscriptionDate();
            paramArray[2] = item.getSubscriptionId();
            paramArray[3] = item.getSubscriptionAmount();
            paramArray[4] = item.getBrokerAmount();
            paramArray[5] = item.getPaidAmount();
            paramArray[6] = item.getNarration();

            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


    @Override
    public ResponseDataDTO addValidation(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            HashMap<String, Object> finalData = new HashMap<>();
            List<BondBrokeragePaymentDTO> bondBrokeragePaymentDTOList = Collections.emptyList();

            String p_user_id_in = "p_user_id_in";
            String p_user_sol_id_in = "p_user_sol_id_in";
            String p_broker_code_in  = "p_broker_code_in";
            String p_sch_code_in = "p_sch_code_in";
            String p_type_in = "p_type_in";
            String p_from_date_in = "p_from_date_in";
            String p_to_date_in = "p_to_date_in";

            String p_cur_val_out = "p_cur_val_out";
            String p_count_out = "p_count_out";
            String p_sum_of_invest_amt_out="p_sum_of_invest_amt_out";
            String p_sum_of_gross_amt_out = "p_sum_of_gross_amt_out";
            String p_msg_out = "p_msg_out";
            String p_msg_desc_out = "p_msg_desc_out";

            try {
                Map<String, Object> inParams = new HashMap<>();
                inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername());
                inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId());
                inParams.put(p_broker_code_in, bondBrokeragePaymentDTO.getBrokerId());
                inParams.put(p_sch_code_in, bondBrokeragePaymentDTO.getSchemeCode());
                inParams.put(p_type_in, bondBrokeragePaymentDTO.getChannelType());
                inParams.put(p_from_date_in, bondBrokeragePaymentDTO.getFromDate());
                inParams.put(p_to_date_in, bondBrokeragePaymentDTO.getToDate());

                SqlParameter[] sqlParameters = {
                        new SqlParameter(p_user_id_in, Types.VARCHAR),
                        new SqlParameter(p_user_sol_id_in, Types.VARCHAR),
                        new SqlParameter(p_broker_code_in, Types.VARCHAR),
                        new SqlParameter(p_sch_code_in,Types.VARCHAR),
                        new SqlParameter(p_type_in, Types.VARCHAR),
                        new SqlParameter(p_from_date_in, Types.VARCHAR),
                        new SqlParameter(p_to_date_in, Types.VARCHAR),
                };

                SqlOutParameter[] sqlOutParameters = {
                        new SqlOutParameter(p_cur_val_out, OracleTypes.CURSOR),
                        new SqlOutParameter(p_count_out, OracleTypes.VARCHAR),
                        new SqlOutParameter(p_sum_of_invest_amt_out, OracleTypes.VARCHAR),
                        new SqlOutParameter(p_sum_of_gross_amt_out, OracleTypes.VARCHAR),
                        new SqlOutParameter(p_msg_out, OracleTypes.VARCHAR),
                        new SqlOutParameter(p_msg_desc_out, OracleTypes.VARCHAR)
                };

                Map<String, RowMapper<?>> rowMappers = new HashMap<>();

                rowMappers.put(p_cur_val_out, (rs, rowNum) -> {

                    BondBrokeragePaymentDTO item = new BondBrokeragePaymentDTO();

                    String channelType = bondBrokeragePaymentDTO.getChannelType();
                    if ("B".equals(channelType)) {
                        item.setBrokerCode(rs.getString("broker_code"));
                        item.setBrokerName(rs.getString("broker_name"));
                        item.setVerifiedStatus(rs.getString("verified_status"));
                    } else {
                        log.info("No values available");
                    }
                    return item;
                });

                Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                        "",
                        "SP_GBM_RBI_BOND_REP_BROKER_STATUS_CLAIM",
                        inParams,
                        sqlParameters,
                        sqlOutParameters,
                        rowMappers
                );

                bondBrokeragePaymentDTOList = (List<BondBrokeragePaymentDTO>) result.get(p_cur_val_out);

                if (CollectionUtils.isEmpty(bondBrokeragePaymentDTOList)) {
                    log.info("No Data Found");
                    resultMsg = CommonConstants.NDF;
                } else {
                    finalData.put("bondBrokeragePaymentDtoList", bondBrokeragePaymentDTOList);
                    responseDataDTO.setResult(finalData);
                }
            }catch (DataAccessException e) {
                log.error("Spring JDBC error", e);
                Throwable root = e.getRootCause();
                if (root instanceof SQLException) {
                    SQLException sqlEx = (SQLException) root;
                    log.error("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                            sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
                }
                responseDataDTO.setMessage(e.getMessage());
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;
        }

    @Override
    public ResponseDataDTO inquiry(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_PAY_INQ.getProcName());
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
                            List<BondBrokeragePaymentDTO.BrokerDetails> list = new ArrayList<>();
                            BondBrokeragePaymentDTO.BrokerDetails item = null;
                            while (resultSet.next()){
                                item = new BondBrokeragePaymentDTO.BrokerDetails();
                                item.setBlaNo(resultSet.getString(1));
                                item.setSubscriptionDate( resultSet.getString(2));
                                item.setSubscriptionId(resultSet.getString(3));
                                item.setSubscriptionAmount( resultSet.getString(4));
                                item.setBrokerAmount(resultSet.getString(5));
                                item.setPaidAmount( resultSet.getString(6));
                                list.add(item);
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
    public ResponseDataDTO verifySelect(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
                            List<BondBrokeragePaymentDTO.BrokerDetails> list = new ArrayList<>();
                            BondBrokeragePaymentDTO.BrokerDetails item = null;
                            while (resultSet.next()){
                                item = new BondBrokeragePaymentDTO.BrokerDetails();
                                item.setBlaNo(resultSet.getString(1));
                                item.setSubscriptionDate( resultSet.getString(2));
                                item.setSubscriptionId(resultSet.getString(3));
                                item.setSubscriptionAmount( resultSet.getString(4));
                                item.setBrokerAmount(resultSet.getString(5));
                                item.setPaidAmount( resultSet.getString(6));
                                list.add(item);
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
    public ResponseDataDTO verifyResponse(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO verify(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<com.isl.gbm.transaction.channel.GbmTransactionMasterDto> gbmTransactionMasterDtoList = null;
        com.isl.gbm.transaction.channel.GbmTransactionMasterDto ddto = null;
        Date tranDate = null;

        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_PAY_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getGbsTranId());
                stmt.registerOutParameter(index++, Types.VARCHAR);//gbm train id
                stmt.registerOutParameter(index++, Types.VARCHAR);//channel type

                stmt.registerOutParameter(index++, Types.VARCHAR);//sol id
                stmt.registerOutParameter(index++, Types.VARCHAR);//tran date
                stmt.registerOutParameter(index++, Types.VARCHAR);//scheme code
                stmt.registerOutParameter(index++, Types.VARCHAR);//screen tran code
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    tranDate = (new SimpleDateFormat("dd/MM/yyyy").parse(stmt.getString(7)));

                    resultSet = (ResultSet) stmt.getObject(10);
                    gbmTransactionMasterDtoList = new ArrayList<>();
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            ddto = new GbmTransactionMasterDto();
                            ddto.setAcctNum(resultSet.getString(1));
                            ddto.setTranLeg(resultSet.getString(2).charAt(0));
                            ddto.setAmount(resultSet.getBigDecimal(3).doubleValue());


                            ddto.setTransactionChannel(stmt.getString(5));
                            ddto.setTranId(bondBrokeragePaymentDTO.getGbsTranId());
                            ddto.setSolId(stmt.getString(6));
                            ddto.setTranDate(tranDate);
                            ddto.setSchemeCode(stmt.getString(8));
                            ddto.setGbmTranId(bondBrokeragePaymentDTO.getGbsTranId());

                            gbmTransactionMasterDtoList.add(ddto);

                        }
                    }

                    bondBrokeragePaymentDTO.setGbsTranId(bondBrokeragePaymentDTO.getGbsTranId());
                    bondBrokeragePaymentDTO.setChannelType(stmt.getString(5));
                    bondBrokeragePaymentDTO.setGbmTransactionMasterDtoList(gbmTransactionMasterDtoList);
                    responseDataDTO.setResult(bondBrokeragePaymentDTO);
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
    public ResponseDataDTO delete(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BRK_PAY_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokeragePaymentDTO.getGbsTranId());
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



}
