package com.isl.gbm.cbs.integration.dao.impl;

import com.isl.gbm.cbs.integration.common.ProcNameEnum;
import com.isl.gbm.cbs.integration.dao.StateMasterDao;
import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.StateMasterDTO;
import com.isl.gbm.cbs.integration.dto.StateMasterResponseDTO;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.common.util.DatabaseConnectionFactory;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.reports.CallBackReportDTO;
import com.isl.gbm.rbibond.util.JDBCTemplateUtil;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

@Repository
public class StateMasterDaoImpl extends HibernateDao implements StateMasterDao {

    private static final Logger log = LoggerFactory.getLogger(StateMasterDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;
    @Autowired
    DatabaseConnectionFactory databaseConnectionFactory;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JDBCTemplateUtil jDBCTemplateUtil;

    @PostConstruct
    public void init() {
        jDBCTemplateUtil = new JDBCTemplateUtil(jdbcTemplate);
    }

    @Override
    public ResponseDataDTO save(List<StateMasterDTO> stateMasterDTOList) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_STATE_MASTER_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                if (stateMasterDTOList != null) {
                    stmt.setArray(index++, generateArrayOfStateMasterList(connection, stateMasterDTOList));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_STATE_MASTER_ARRAY);
                }
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            databaseConnectionFactory.closeResources(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getInquiryDetails(StateMasterDTO stateMasterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        HashMap<String, Object> finalData = new HashMap<>();
        List<StateMasterDTO> stateReportDTOList = Collections.emptyList();
//        StateMasterDTO stateMasterDTO = null;

        String p_user_id_in = "p_user_id_in";
        String p_user_sol_id_in = "p_user_sol_id_in";
        String p_page_no_in = "p_page_no_in";
        String p_page_size_in = "p_page_size_in";
        String p_search_value_in = "p_search_value_in";
        String p_created_by_out = "p_created_by_out";
        String p_created_date_out = "p_created_date_out";
        String p_modified_by_out = "p_modified_by_out";
        String p_modified_date_out = "p_modified_date_out";
        String p_verified_by_out = "p_verified_by_out";
        String p_verified_date_out = "p_verified_date_out";
        String p_prev_opr_type_out = "p_prev_opr_type_out";
        String p_verified_status_out = "p_verified_status_out";
        String p_delete_remarks_out = "p_delete_remarks_out";
        String p_total_records_out = "p_total_records_out";
        String p_cur_val_out = "p_cursor_val_out";
        String p_msg_out = "p_msg_out";
        String p_msg_desc_out = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername());
            inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId());
            inParams.put(p_page_no_in, stateMasterDTO.getPageNo());
            inParams.put(p_page_size_in, stateMasterDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=stateMasterDTO.getSearchValue()&&!stateMasterDTO.getSearchValue().trim().isEmpty())?stateMasterDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(p_user_id_in, Types.VARCHAR),
                    new SqlParameter(p_user_sol_id_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_created_by_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_created_date_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_modified_by_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_modified_date_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_verified_by_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_verified_date_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_prev_opr_type_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_verified_status_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_delete_remarks_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_cur_val_out, OracleTypes.CURSOR),
                    new SqlOutParameter(p_msg_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_desc_out, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(p_cur_val_out, (rs, rowNum) -> {

                StateMasterDTO item = new StateMasterDTO();

                item.setState(rs.getString(1));
                item.setCity(rs.getString(2));
                item.setPinCode(rs.getString(3));
                item.setPostOfficeName(rs.getString(4));


                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "pkg_gbm_rbi_bond_state_master",
                    "sp_inq",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            stateReportDTOList = (List<StateMasterDTO>) result.get(p_cur_val_out);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";


            if (CollectionUtils.isEmpty(stateReportDTOList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("stateReportDTOList", stateReportDTOList);
                finalData.put("createdBy",result.get(p_created_by_out));
                finalData.put("createdDate",result.get(p_created_date_out));
                finalData.put("modifiedBy",result.get(p_modified_by_out));
                finalData.put("modifiedDate",result.get(p_modified_date_out));
                finalData.put("verifiedBy",result.get(p_verified_by_out));
                finalData.put("verifiedDate",result.get(p_verified_date_out));
                finalData.put("prevOp",result.get(p_prev_opr_type_out));
                finalData.put("verifiedStatus",result.get(p_verified_status_out));
                finalData.put("deleteRemarks",result.get(p_delete_remarks_out));
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
        responseDataDTO.setTotal(Double.parseDouble(totalRecords));
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getVerifyDetails(StateMasterDTO stateMasterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        HashMap<String, Object> finalData = new HashMap<>();
        List<StateMasterDTO> stateReportDTOList = Collections.emptyList();
//        StateMasterDTO stateMasterDTO = null;

        String p_user_id_in = "p_verifier_id_in";
        String p_user_sol_id_in = "p_verifier_sol_id_in";
        String p_page_no_in = "p_page_no_in";
        String p_page_size_in = "p_page_size_in";
        String p_search_value_in = "p_search_value_in";
        String p_created_by_out = "p_created_by_out";
        String p_created_date_out = "p_created_date_out";
        String p_modified_by_out = "p_modified_by_out";
        String p_modified_date_out = "p_modified_date_out";
        String p_verified_by_out = "p_verified_by_out";
        String p_verified_date_out = "p_verified_date_out";
        String p_prev_opr_type_out = "p_prev_opr_type_out";
        String p_verified_status_out = "p_verified_status_out";
        String p_delete_remarks_out = "p_delete_remarks_out";
        String p_total_records_out = "p_total_records_out";
        String p_cur_val_out = "p_cursor_val_out";
        String p_msg_out = "p_msg_out";
        String p_msg_desc_out = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername());
            inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId());
            inParams.put(p_page_no_in, stateMasterDTO.getPageNo());
            inParams.put(p_page_size_in, stateMasterDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=stateMasterDTO.getSearchValue()&&!stateMasterDTO.getSearchValue().trim().isEmpty())?stateMasterDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(p_user_id_in, Types.VARCHAR),
                    new SqlParameter(p_user_sol_id_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_created_by_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_created_date_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_modified_by_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_modified_date_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_verified_by_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_verified_date_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_prev_opr_type_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_verified_status_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_delete_remarks_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_cur_val_out, OracleTypes.CURSOR),
                    new SqlOutParameter(p_msg_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_desc_out, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(p_cur_val_out, (rs, rowNum) -> {

                StateMasterDTO item = new StateMasterDTO();

                item.setState(rs.getString(1));
                item.setCity(rs.getString(2));
                item.setPinCode(rs.getString(3));
                item.setPostOfficeName(rs.getString(4));


                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "pkg_gbm_rbi_bond_state_master",
                    "sp_ver_Sel",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            stateReportDTOList = (List<StateMasterDTO>) result.get(p_cur_val_out);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";


            if (CollectionUtils.isEmpty(stateReportDTOList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("stateReportDTOList", stateReportDTOList);
                finalData.put("createdBy",result.get(p_created_by_out));
                finalData.put("createdDate",result.get(p_created_date_out));
                finalData.put("modifiedBy",result.get(p_modified_by_out));
                finalData.put("modifiedDate",result.get(p_modified_date_out));
                finalData.put("verifiedBy",result.get(p_verified_by_out));
                finalData.put("verifiedDate",result.get(p_verified_date_out));
                finalData.put("prevOp",result.get(p_prev_opr_type_out));
                finalData.put("verifiedStatus",result.get(p_verified_status_out));
                finalData.put("deleteRemarks",result.get(p_delete_remarks_out));
                responseDataDTO.setResult(finalData);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        }
        responseDataDTO.setTotal(Double.parseDouble(totalRecords));
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO verifySubmit() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_STATE_MASTER_VERIFY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            databaseConnectionFactory.closeResources(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO deleteSubmit(StateMasterDTO stateMasterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (stateMasterDTO.getRemarks() == null
                || stateMasterDTO.getRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        //validation for vulnerability
        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(stateMasterDTO.getRemarks())) {
            responseDataDTO.setMessage("Invalid Rejection Remarks");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_STATE_MASTER_DELETE.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, stateMasterDTO.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            databaseConnectionFactory.closeResources(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO dataExistsCheck() {
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, String> resultMap = new HashMap<>();
        try {

            final Session session = getSession();
            Query<BigDecimal> countQuery = session.createNativeQuery("SELECT COUNT(1) FROM GBM_RBI_BOND_STATE_MASTER WHERE VERIFIED_STATUS = :verifyStatus AND IS_ACTIVE = :isActive AND IS_DELETED = :deleteFlag");
            countQuery.setParameter("verifyStatus", "Y");
            countQuery.setParameter("isActive", "Y");
            countQuery.setParameter("deleteFlag", "N");
            BigDecimal totalCount = countQuery.getSingleResult();
            resultMsg = CommonConstants.SUCCESS_CONST;
            if(totalCount.compareTo(BigDecimal.ZERO) > 0){
                resultMap.put("flag","Y");
            }
            else{
                resultMap.put("flag","N");
            }
          responseDataDTO.setResult(resultMap);
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }



    private static ARRAY generateArrayOfStateMasterList(Connection connection, List<StateMasterDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_STATE_MASTER_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_STATE_MASTER_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        StateMasterDTO stateMasterDTO = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[4];
            stateMasterDTO = list.get(index);
            paramArray[0] = stateMasterDTO.getState();
            paramArray[1] = stateMasterDTO.getCity();
            paramArray[2] = stateMasterDTO.getPinCode();
            paramArray[3] = stateMasterDTO.getPostOfficeName();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
}
