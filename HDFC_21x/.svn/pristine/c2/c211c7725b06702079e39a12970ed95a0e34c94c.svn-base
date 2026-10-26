package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.user.management.dao.AuditLogReportDAO;
import com.isl.gbm.user.management.dto.AuditReportDTO;
import com.isl.gbm.user.management.dto.ReportOnBranchWiseDeptWiseDTO;
import com.isl.gbm.user.management.dto.ReportsForDCParmaterizationDTO;
import com.isl.gbm.user.management.dto.ReportsForDCParmaterizationResponseDTO;
import com.isl.gbm.user.management.util.ProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Repository
public class AuditLogReportDAOImpl implements AuditLogReportDAO {

    private static final Logger log = LoggerFactory.getLogger(AuditLogReportDAOImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    private static final String screenTypeContant = "DC";

    @Override
    public ResponseDataDTO getReportsForDCParamertization(ReportsForDCParmaterizationDTO reportsForDCParmaterizationDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        ReportsForDCParmaterizationResponseDTO reportsForDCParmaterizationResponseDTO = null;
        List<ReportsForDCParmaterizationResponseDTO> reportsForDCParmaterizationList = null;
        ResultSet rs = null;

        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_PP_REP_AUDIT_LOG.getProcName());

                int index = 1;

                stmt.setString(index++, reportsForDCParmaterizationDTO.getTableShortName());
                stmt.setString(index++, reportsForDCParmaterizationDTO.getModuleId());
                stmt.setString(index++, reportsForDCParmaterizationDTO.getLinkName());
                stmt.setString(index++, ("ALL".equalsIgnoreCase(reportsForDCParmaterizationDTO.getOperation())) ? null : (reportsForDCParmaterizationDTO.getOperation()));
                stmt.setString(index++, ("B".equalsIgnoreCase(reportsForDCParmaterizationDTO.getRecordStatusl())) ? null : (reportsForDCParmaterizationDTO.getRecordStatusl()));
                stmt.setString(index++, reportsForDCParmaterizationDTO.getCreModVerBy());
                stmt.setString(index++, reportsForDCParmaterizationDTO.getFromDate());
                stmt.setString(index++, reportsForDCParmaterizationDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if ((stmt.getString(index - 1)) != null && (CommonConstants.SUCCESS).equalsIgnoreCase(stmt.getString(index - 1))) {

                    rs = (ResultSet) stmt.getObject(9);

                    if (rs != null) {

                        reportsForDCParmaterizationList = new ArrayList<>();

                        while (rs.next()) {

                            reportsForDCParmaterizationResponseDTO = new ReportsForDCParmaterizationResponseDTO();

                            reportsForDCParmaterizationResponseDTO.setAuditRefNum(rs.getString("AUDIT_ID"));
                            reportsForDCParmaterizationResponseDTO.setTableName(rs.getString("TABLE_NAME"));
                            reportsForDCParmaterizationResponseDTO.setKeyVal(rs.getString("TABLE_PK_COL_VALUE"));
                            reportsForDCParmaterizationResponseDTO.setRemarks(rs.getString(CommonConstants.REMARKS_CONST));
                            reportsForDCParmaterizationResponseDTO.setCreatedBy(rs.getString("ENTERER_ID"));
                            reportsForDCParmaterizationResponseDTO.setCreatedDate(rs.getString("ENTERER_DATE"));
                            reportsForDCParmaterizationResponseDTO.setModifiedBy(rs.getString("LAST_MODIFIED_BY"));
                            reportsForDCParmaterizationResponseDTO.setModifiedDate(rs.getString("LAST_MODIFIED_DATE"));
                            reportsForDCParmaterizationResponseDTO.setAuthorisedBy(rs.getString("AUTH_ID"));
                            reportsForDCParmaterizationResponseDTO.setAuthorisedDate(rs.getString("AUTH_DATE"));

                            if (StringUtils.isEmpty(rs.getString("MODIFIED_FIELDS_DATA"))) {
                                reportsForDCParmaterizationList.add(reportsForDCParmaterizationResponseDTO);
                            } else {
                                String rs1[] = rs.getString("MODIFIED_FIELDS_DATA").split("~");


                                for (String x : rs1) {

                                    String rs2[] = x.split("[|]");

                                    reportsForDCParmaterizationResponseDTO.setColName(rs2[0]);
                                    if (rs2.length > 1)
                                        reportsForDCParmaterizationResponseDTO.setOldName(rs2[1]);
                                    if (rs2.length > 2)
                                        reportsForDCParmaterizationResponseDTO.setNewName(rs2[2]);
                                    ReportsForDCParmaterizationResponseDTO kd = (ReportsForDCParmaterizationResponseDTO) reportsForDCParmaterizationResponseDTO.clone();
                                    reportsForDCParmaterizationList.add(kd);
                                }

                            }

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
        responseDataDTO.setResult(reportsForDCParmaterizationList);
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getLinkName(String moduleId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        List<LinkedHashMap<String, String>> linkList = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.sp_link_tor.getProcName());
                stmt.setString("p_module_id_in", moduleId);
                stmt.registerOutParameter("p_cursor_det_out", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject("p_cursor_det_out");

                    if (resultSet != null) {
                        linkList = new ArrayList<>();

                        while (resultSet.next()) {
                            LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
                            resultMap.put("linkCode", resultSet.getString("link_code"));
                            resultMap.put("linkDescription", resultSet.getString("link_desc"));

                            linkList.add(resultMap);
                        }

                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(linkList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getReportsAtDCLevelForAnySol(AuditReportDTO auditReportDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        AuditReportDTO auditReportDTO1 = null;
        List<AuditReportDTO.AuditReportResponseDTO> auditReportResponseDTOList = null;
        ResultSet rs = null;


        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_PP_REP_AUDIT_DC_SOL.getProcName());
                stmt.setString("p_table_name_in", auditReportDTO.getTableShortName());
                stmt.setString("p_module_id_in", auditReportDTO.getModuleId());
                stmt.setString("p_link_code_in", auditReportDTO.getLinkName());
                stmt.setString("p_trsy_code_in", auditReportDTO.getTreasuryCode());
                stmt.setString("p_st_tax_code_in", auditReportDTO.getStateCode());
                stmt.setString("p_operation_in", auditReportDTO.getOperation());
                stmt.setString(CommonConstants.PROC_PARAM_BRANCH_ID_IN, auditReportDTO.getBranchId());
                stmt.setString("p_record_status_in", auditReportDTO.getRecordStatus());
                stmt.setString("p_operation_by_in", auditReportDTO.getCreModVerBy());
                stmt.setString("p_from_date_in", auditReportDTO.getFromDate());
                stmt.setString("p_to_date_in", auditReportDTO.getToDate());
                stmt.registerOutParameter("p_cursor_det_out", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                if ((stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT)) != null && (CommonConstants.SUCCESS).equalsIgnoreCase(stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT))) {

                    rs = (ResultSet) stmt.getObject("p_cursor_det_out");

                    if (rs != null) {

                        auditReportResponseDTOList = new ArrayList<>();

                        while (rs.next()) {
                            AuditReportDTO.AuditReportResponseDTO auditReportResponseDTO = new AuditReportDTO.AuditReportResponseDTO();
                            auditReportResponseDTO.setAuditRefNum(rs.getString("audit_ref_num"));
                            auditReportResponseDTO.setTableName(rs.getString("table_name"));
                            auditReportResponseDTO.setKeyVal(rs.getString("key_value"));
                            auditReportResponseDTO.setRemarks(rs.getString("remarks"));
                            auditReportResponseDTO.setCreatedBy(rs.getString("created_by"));
                            auditReportResponseDTO.setCreatedDate(rs.getString("created_on"));
                            auditReportResponseDTO.setModifiedBy(rs.getString("modified_by"));
                            auditReportResponseDTO.setModifiedDate(rs.getString("modified_on"));
                            auditReportResponseDTO.setAuthorisedBy(rs.getString("authorized_by"));
                            auditReportResponseDTO.setAuthorisedDate(rs.getString("authorized_on"));
                            auditReportResponseDTO.setDeletedBy(rs.getString("deleted_by"));
                            auditReportResponseDTO.setDeletedDate(rs.getString("deleted_on"));
                            auditReportResponseDTO.setEntityId(rs.getString("entity_id"));
                            auditReportResponseDTO.setEntityAccNo(rs.getString("entity_acc_num"));
                            auditReportResponseDTO.setModuleId(rs.getString("module"));
                            auditReportResponseDTO.setLinkName(rs.getString("link_name"));

                            if (StringUtils.isEmpty(rs.getString("modified_fields_data"))) {
                                auditReportResponseDTOList.add(auditReportResponseDTO);
                            } else {
                                String rs1[] = rs.getString("MODIFIED_FIELDS_DATA").split("~");
                                List<AuditReportDTO.AuditReportResponseDTO.ModifiedValuesDTO> modifiedValuesDTOList = new ArrayList<>();
                                for (String x : rs1) {
                                    String rs2[] = x.split("[|]");
                                    if (!StringUtils.isEmpty(rs2[0])) {
                                        AuditReportDTO.AuditReportResponseDTO.ModifiedValuesDTO modifiedValuesDTO = new AuditReportDTO.AuditReportResponseDTO.ModifiedValuesDTO();
                                        modifiedValuesDTO.setColName(rs2[0]);
                                        if (rs2.length > 1)
                                            modifiedValuesDTO.setOldValue(rs2[1]);
                                        if (rs2.length > 2)
                                            modifiedValuesDTO.setNewValue(rs2[2]);
                                        modifiedValuesDTOList.add(modifiedValuesDTO);
                                    }
                                }
                                auditReportResponseDTO.setModifiedValuesDTOList(modifiedValuesDTOList);
                                auditReportResponseDTOList.add(auditReportResponseDTO);
                            }
                        }
                        auditReportDTO.setAuditReportResponseDTOS(auditReportResponseDTOList);
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

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setResult(auditReportDTO);
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getModuleId() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        List<LinkedHashMap<String, String>> moduleList = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.sp_module_tor.getProcName());
                stmt.registerOutParameter("p_cursor_det_out", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject("p_cursor_det_out");

                    if (resultSet != null) {
                        moduleList = new ArrayList<>();

                        while (resultSet.next()) {
                            LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
                            resultMap.put("moduleId", resultSet.getString("module_name"));

                            moduleList.add(resultMap);
                        }
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(moduleList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getOperations(String moduleId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        List<LinkedHashMap<String, String>> operationList = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.sp_operation_tor.getProcName());
                stmt.setString("p_module_id_in", moduleId);
                stmt.registerOutParameter("p_cursor_det_out", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject("p_cursor_det_out");

                    if (resultSet != null) {
                        operationList = new ArrayList<>();

                        while (resultSet.next()) {
                            LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
                            resultMap.put("operation", resultSet.getString("operation"));

                            operationList.add(resultMap);
                        }
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(operationList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getRecordStatus() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        List<LinkedHashMap<String, String>> recordStatusList = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.sp_record_status_tor.getProcName());
                stmt.registerOutParameter("p_cursor_det_out", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject("p_cursor_det_out");

                    if (resultSet != null) {
                        recordStatusList = new ArrayList<>();

                        while (resultSet.next()) {
                            LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
                            resultMap.put("recordCode", resultSet.getString("rec_code"));
                            resultMap.put("recordStatus", resultSet.getString("rec_status"));

                            recordStatusList.add(resultMap);
                        }
                        LinkedHashMap<String, String> deleteMap = new LinkedHashMap<>();
                        deleteMap.put("recordCode", "D");
                        deleteMap.put("recordStatus", "AUTHORIZED");
                        recordStatusList.add(deleteMap);
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(recordStatusList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBranchId(String moduleId, String screenType, String treasuryCode, String stateCode) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        List<LinkedHashMap<String, String>> recordStatusList = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.sp_branch_tor.getProcName());
                stmt.setString("p_module_id_in", moduleId);
                stmt.setString("p_trsy_code_in", treasuryCode);
                stmt.setString("p_st_tax_code_in", stateCode);
                stmt.setString(CommonConstants.PROC_PARAM_USER_SOL_ID_IN, (screenType.equalsIgnoreCase(screenTypeContant) ? ("ALL") : (UserAccountUtils.getCurrentSolId())));
                stmt.registerOutParameter("p_cursor_det_out", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject("p_cursor_det_out");

                    if (resultSet != null) {
                        recordStatusList = new ArrayList<>();

                        while (resultSet.next()) {
                            LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
                            resultMap.put("branchId", resultSet.getString("branch_id"));
                            resultMap.put("branchDescription", resultSet.getString("branch_desc"));

                            recordStatusList.add(resultMap);
                        }
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(recordStatusList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getStateTaxCode(String moduleId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        List<LinkedHashMap<String, String>> recordStatusList = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.sp_st_tax_tor.getProcName());
                stmt.setString("p_module_id_in", moduleId);
                stmt.registerOutParameter("p_cursor_det_out", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject("p_cursor_det_out");

                    if (resultSet != null) {
                        recordStatusList = new ArrayList<>();

                        while (resultSet.next()) {
                            LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
                            resultMap.put("stateCode", resultSet.getString("tax_code"));
                            resultMap.put("stateDescription", resultSet.getString("DESCRIPTION"));

                            recordStatusList.add(resultMap);
                        }
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(recordStatusList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getTreasuryCode(String moduleId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        List<LinkedHashMap<String, String>> recordStatusList = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.sp_trsy_tor.getProcName());
                stmt.setString("p_module_id_in", moduleId);
                stmt.registerOutParameter("p_cursor_det_out", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject("p_cursor_det_out");

                    if (resultSet != null) {
                        recordStatusList = new ArrayList<>();

                        while (resultSet.next()) {
                            LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
                            resultMap.put("treasuryCode", resultSet.getString("trsy_code"));
                            resultMap.put("treasuryDesc", resultSet.getString("trsy_name"));

                            recordStatusList.add(resultMap);
                        }
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(recordStatusList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getReportOnBranchWiseDeptWiseUserDetails(ReportOnBranchWiseDeptWiseDTO reportOnBranchWiseDeptWiseDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<LinkedHashMap<String, String>> recordStatusList = null;
        ReportOnBranchWiseDeptWiseDTO.ReportOnBranchWiseDeptWiseResponseDTO reportOnBranchWiseDeptWiseResponseDTO = null;
        List<ReportOnBranchWiseDeptWiseDTO.ReportOnBranchWiseDeptWiseResponseDTO> reportOnBranchWiseDeptWiseResponseDTOList = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_ROLES_PER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, reportOnBranchWiseDeptWiseDTO.getBranchId());
                stmt.setString(index++, reportOnBranchWiseDeptWiseDTO.getDeptCode());
                stmt.setString(index++, reportOnBranchWiseDeptWiseDTO.getUserId());
                stmt.setString(index++, reportOnBranchWiseDeptWiseDTO.getInputFlag());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if ("DC".equalsIgnoreCase(reportOnBranchWiseDeptWiseDTO.getInputFlag())) {
                        reportOnBranchWiseDeptWiseDTO.setDcId(stmt.getString(7));
                        reportOnBranchWiseDeptWiseDTO.setDcDescription(stmt.getString(8));

                        responseDataDTO.setResult(reportOnBranchWiseDeptWiseDTO);
                    } else if ("B".equalsIgnoreCase(reportOnBranchWiseDeptWiseDTO.getInputFlag())) {
                        resultSet = (ResultSet) stmt.getObject(6);

                        if (resultSet != null) {
                            recordStatusList = new ArrayList<>();

                            while (resultSet.next()) {
                                LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
                                resultMap.put("branchId", resultSet.getString(1));
                                resultMap.put("branchDescription", resultSet.getString(2));

                                recordStatusList.add(resultMap);
                            }
                            responseDataDTO.setResult(recordStatusList);
                        }
                    } else if ("D".equalsIgnoreCase(reportOnBranchWiseDeptWiseDTO.getInputFlag())) {
                        resultSet = (ResultSet) stmt.getObject(6);

                        if (resultSet != null) {
                            recordStatusList = new ArrayList<>();

                            while (resultSet.next()) {
                                LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
                                resultMap.put("deptCode", resultSet.getString(1));

                                recordStatusList.add(resultMap);
                            }
                            responseDataDTO.setResult(recordStatusList);
                        }
                    } else if ("U".equalsIgnoreCase(reportOnBranchWiseDeptWiseDTO.getInputFlag())) {
                        resultSet = (ResultSet) stmt.getObject(6);

                        if (resultSet != null) {
                            recordStatusList = new ArrayList<>();

                            while (resultSet.next()) {
                                LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
                                resultMap.put("userId", resultSet.getString(1));

                                recordStatusList.add(resultMap);
                            }
                            responseDataDTO.setResult(recordStatusList);
                        }
                    } else if ("O".equalsIgnoreCase(reportOnBranchWiseDeptWiseDTO.getInputFlag())) {
                        resultSet = (ResultSet) stmt.getObject(6);

                        if (resultSet != null) {
                            reportOnBranchWiseDeptWiseResponseDTOList = new ArrayList<>();

                            while (resultSet.next()) {
                                reportOnBranchWiseDeptWiseResponseDTO = new ReportOnBranchWiseDeptWiseDTO.ReportOnBranchWiseDeptWiseResponseDTO();
                                reportOnBranchWiseDeptWiseResponseDTO.setBranchId(resultSet.getString(1));
                                reportOnBranchWiseDeptWiseResponseDTO.setUserId(resultSet.getString(2));
                                reportOnBranchWiseDeptWiseResponseDTO.setEmployeeId(resultSet.getString(3));
                                reportOnBranchWiseDeptWiseResponseDTO.setEmployeeName(resultSet.getString(4));
                                reportOnBranchWiseDeptWiseResponseDTO.setDeptCode(resultSet.getString(5));
                                reportOnBranchWiseDeptWiseResponseDTO.setRoleId(resultSet.getString(6));
                                reportOnBranchWiseDeptWiseResponseDTO.setAccessPermissions(resultSet.getString(7));

                                reportOnBranchWiseDeptWiseResponseDTOList.add(reportOnBranchWiseDeptWiseResponseDTO);
                            }
                            responseDataDTO.setResult(reportOnBranchWiseDeptWiseResponseDTOList);
                        }
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
}
