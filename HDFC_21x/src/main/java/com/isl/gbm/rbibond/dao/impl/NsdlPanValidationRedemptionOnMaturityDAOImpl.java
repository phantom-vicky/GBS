package com.isl.gbm.rbibond.dao.impl;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DatabaseConnectionFactory;
import com.isl.gbm.rbibond.dao.NsdlPanValidationRedemptionOnMaturityDAO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.util.JDBCTemplateUtil;
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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import jakarta.annotation.PostConstruct;
import java.sql.*;
import java.util.*;

@Repository
public class NsdlPanValidationRedemptionOnMaturityDAOImpl implements NsdlPanValidationRedemptionOnMaturityDAO {

    private static final Logger log = LoggerFactory.getLogger(NsdlPanValidationRedemptionOnMaturityDAOImpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;
    @Autowired
    DatabaseConnectionFactory databaseConnectionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JDBCTemplateUtil jDBCTemplateUtil;

    @PostConstruct
    public void init() {
        jDBCTemplateUtil = new JDBCTemplateUtil(jdbcTemplate);
    }
    @Override
    public ResponseDataDTO verifyApiRequest(
            NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        PanDetailsDTO panDetailsDTO=null;
        List<PanDetailsDTO> panDetailsList=null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(
                        RbiBondProcNameEnum.PKG_GBM_RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_VERIFY_API_REQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getType());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getFileType());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            panDetailsList = new ArrayList<>();
                            while (resultSet.next()) {
                                panDetailsDTO=new PanDetailsDTO();
                                panDetailsDTO.setPan(resultSet.getString(1));
                                panDetailsDTO.setName(resultSet.getString(2));
                                panDetailsDTO.setDob(resultSet.getString(3));
                                panDetailsList.add(panDetailsDTO);
                            }
                        }
                        responseDataDTO.setResult(panDetailsList);
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
    public ResponseDataDTO verifyApiResponse(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(
                        RbiBondProcNameEnum.PKG_GBM_RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_VERIFY_API_RES.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, panValidationRetriggerResponseDTO.getSchemeCode());
                stmt.setString(index++, panValidationRetriggerResponseDTO.getType());
                stmt.setString(index++, panValidationRetriggerResponseDTO.getFileMode());
                stmt.setArray(index++, generateTYP_typ_rbi_bond_nsdl_res_OBJ(
                        connection, panValidationRetriggerResponseDTO));
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
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        }finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO api(
        NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        NsdlPanValidationRequestAndResponseDTO dto=null;
        PanDetailsDTO panDetailsDTO=null;
        List<NsdlPanValidationRequestAndResponseDTO> nsdlList=null;
        List<PanDetailsDTO> panDetailsList=null;
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(
                RbiBondProcNameEnum.PKG_GBM_RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_API_REQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getType());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getFromDate());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (nsdlPanValidationRequestAndResponseDTO.getSchemeCode() == null) {
                        resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            nsdlList = new ArrayList<>();
                            while (resultSet.next()) {
                                dto=new NsdlPanValidationRequestAndResponseDTO();
                                dto.setSchemeCode(resultSet.getString(1));
                                dto.setSchemeName(resultSet.getString(2));
                                dto.setSchemeStatus(resultSet.getString(3));
                                nsdlList.add(dto);
                            }
                        }
                        responseDataDTO.setResult(nsdlList);
                    } else if(null!=nsdlPanValidationRequestAndResponseDTO
                            &&null!=nsdlPanValidationRequestAndResponseDTO.getType()){
                        resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            panDetailsList = new ArrayList<>();
                            while (resultSet.next()) {
                                panDetailsDTO=new PanDetailsDTO();
                                panDetailsDTO.setPan(resultSet.getString(1));
                                panDetailsDTO.setName(resultSet.getString(2));
                                panDetailsDTO.setDob(resultSet.getString(3));
                                panDetailsList.add(panDetailsDTO);
                            }
                        }
                        log.info("Pan Count: {}" , (panDetailsList != null ? panDetailsList.size() : 0));
                        responseDataDTO.setResult(panDetailsList);
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
            databaseConnectionFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO apiResponse(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO, String userName,String solId){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(
                RbiBondProcNameEnum.PKG_GBM_RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_API_RES.getProcName());
                stmt.setString(index++, userName);
                stmt.setString(index++, solId);
                stmt.setString(index++, panValidationRetriggerResponseDTO.getSchemeCode());
                stmt.setString(index++, panValidationRetriggerResponseDTO.getType());
                stmt.setArray(index++, generateTYP_typ_rbi_bond_nsdl_res_OBJ(
                        connection, panValidationRetriggerResponseDTO));
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
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        }finally {
            databaseConnectionFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO verify(
            NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<NsdlPanValidationRedemptionOnMaturityReqAndResDTO> nsdlPanValidationRequestAndResponseList=null;
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(
                RbiBondProcNameEnum.PKG_GBM_RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_VERIFY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getType());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getFileType());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(null==nsdlPanValidationRequestAndResponseDTO.getSchemeCode()){
      resultSet = (ResultSet) stmt.getObject(6);
    if (resultSet != null) {
        nsdlPanValidationRequestAndResponseList = new ArrayList<>();
            while (resultSet.next()) {
                nsdlPanValidationRequestAndResponseDTO = new NsdlPanValidationRedemptionOnMaturityReqAndResDTO();
                nsdlPanValidationRequestAndResponseDTO.setSubCode(resultSet.getString(1));
                nsdlPanValidationRequestAndResponseDTO.setSubCodeDesc(resultSet.getString(2));
                nsdlPanValidationRequestAndResponseDTO.setSchemeStatus(resultSet.getString(3));
                nsdlPanValidationRequestAndResponseList.add(nsdlPanValidationRequestAndResponseDTO);
            }
         responseDataDTO.setResult(nsdlPanValidationRequestAndResponseList);
            }
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
        databaseConnectionFactory.closeResources(connection, stmt);
    }
    responseDataDTO.setMessage(resultMsg);
    return responseDataDTO;
    }
    @Override
    public ResponseDataDTO verifyDownload(
    NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        NsdlPanStatusDTO dto=null;
        IncomeTaxPanStatusDTO incomeTaxPanStatus=null;
        List<IncomeTaxPanStatusDTO> incomeTaxPanStatusList=null;
        List<NsdlPanStatusDTO> nsdlList=null;
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
      stmt = connection.prepareCall(
      RbiBondProcNameEnum.PKG_GBM_RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SP_VERIFY_DOWNLOAD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getType());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getFileType());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(null!=nsdlPanValidationRequestAndResponseDTO
                            &&nsdlPanValidationRequestAndResponseDTO.getFileType().equalsIgnoreCase("P001")) {
                        resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            nsdlList = new ArrayList<>();
                            while (resultSet.next()) {
                                dto = new NsdlPanStatusDTO();
                                dto.setPan(resultSet.getString(1));
                                dto.setPanStatus(resultSet.getString(2));
                                dto.setPanAsPerNsdl(resultSet.getString(3));
                                dto.setName(resultSet.getString(4));
                                dto.setNameStatus(resultSet.getString(5));
                                dto.setNameAsPerNsdl(resultSet.getString(6));
                                dto.setDob(resultSet.getString(7));
                                dto.setDobStatus(resultSet.getString(8));
                                dto.setDobAsPerNsdl(resultSet.getString(9));
                                dto.setRemarks(resultSet.getString(10));
                                nsdlList.add(dto);
                            }
                            responseDataDTO.setResult(nsdlList);
                        } }else if (null != nsdlPanValidationRequestAndResponseDTO && nsdlPanValidationRequestAndResponseDTO.getFileType().equalsIgnoreCase("P002")) {
                            resultSet = (ResultSet) stmt.getObject(6);
                            if (resultSet != null) {
                                incomeTaxPanStatusList = new ArrayList<>();
                                while (resultSet.next()) {
                                    incomeTaxPanStatus = new IncomeTaxPanStatusDTO();
                                    incomeTaxPanStatus.setPanNo(resultSet.getString(1));
                                    incomeTaxPanStatus.setItrFlagStatus(resultSet.getString(2));
                                    incomeTaxPanStatus.setIncomeTaxDate(resultSet.getString(3));
                                    incomeTaxPanStatusList.add(incomeTaxPanStatus);
                                }
                            }
                            responseDataDTO.setResult(incomeTaxPanStatusList);
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
            databaseConnectionFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO download(
            NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        NsdlPanStatusDTO dto=null;
        IncomeTaxDownloadDTO incomeTaxDownloadDTO=null;
        List<IncomeTaxDownloadDTO> incomeTaxDownloadList=null;
        List<NsdlPanStatusDTO> nsdlList=null;
        List<NsdlPanValidationRedemptionOnMaturityReqAndResDTO> nsdlPanValidationRequestAndResponseList=null;
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(
                RbiBondProcNameEnum.PKG_GBM_RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_DOWNLOAD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getType());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getFileType());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
           if(null==nsdlPanValidationRequestAndResponseDTO.getSchemeCode()
              && null==nsdlPanValidationRequestAndResponseDTO.getFileType()){
               resultSet = (ResultSet) stmt.getObject(6);
           if (resultSet != null) {
               nsdlPanValidationRequestAndResponseList = new ArrayList<>();
               while (resultSet.next()) {
                   nsdlPanValidationRequestAndResponseDTO = new NsdlPanValidationRedemptionOnMaturityReqAndResDTO();
                   nsdlPanValidationRequestAndResponseDTO.setSubCode(resultSet.getString(1));
                   nsdlPanValidationRequestAndResponseDTO.setSubCodeDesc(resultSet.getString(2));
                   nsdlPanValidationRequestAndResponseDTO.setSchemeStatus(resultSet.getString(3));
                   nsdlPanValidationRequestAndResponseList.add(nsdlPanValidationRequestAndResponseDTO);
               }
               responseDataDTO.setResult(nsdlPanValidationRequestAndResponseList);
       }
                           }
          else if(null!=nsdlPanValidationRequestAndResponseDTO
                   &&nsdlPanValidationRequestAndResponseDTO.getFileType().equalsIgnoreCase("P001")) {
                           resultSet = (ResultSet) stmt.getObject(6);
                           if (resultSet != null) {
                               nsdlList = new ArrayList<>();
                               while (resultSet.next()) {
                                   dto = new NsdlPanStatusDTO();
                                   dto.setPan(resultSet.getString(1));
                                   dto.setPanStatus(resultSet.getString(2));
                                   dto.setPanAsPerNsdl(resultSet.getString(3));
                                   dto.setName(resultSet.getString(4));
                                   dto.setNameStatus(resultSet.getString(5));
                                   dto.setNameAsPerNsdl(resultSet.getString(6));
                                   dto.setDob(resultSet.getString(7));
                                   dto.setDobStatus(resultSet.getString(8));
                                   dto.setDobAsPerNsdl(resultSet.getString(9));
                                   dto.setRemarks(resultSet.getString(10));
                                   nsdlList.add(dto);
                               }
                               responseDataDTO.setResult(nsdlList);
                           }
          }else if (null != nsdlPanValidationRequestAndResponseDTO
                   && nsdlPanValidationRequestAndResponseDTO.getFileType().equalsIgnoreCase("P002")) {
                               resultSet = (ResultSet) stmt.getObject(6);
                               if (resultSet != null) {
                                   incomeTaxDownloadList = new ArrayList<>();
                                   while (resultSet.next()) {
                                       incomeTaxDownloadDTO = new IncomeTaxDownloadDTO();
                                       incomeTaxDownloadDTO.setPanNo(resultSet.getString(1));
                                       incomeTaxDownloadDTO.setCustomerName(resultSet.getString(2));
                                       incomeTaxDownloadList.add(incomeTaxDownloadDTO);
                                   }
                               }
                               responseDataDTO.setResult(incomeTaxDownloadList);
                       }
                }else{
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
            databaseConnectionFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO inquiryDetails(
            NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        NsdlPanValidationRequestAndResponseDTO dto=null;
        List<NsdlPanValidationRequestAndResponseDTO> nsdlList=null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(
                RbiBondProcNameEnum.PKG_GBM_RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_INQUIRY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getType());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getFileStatus());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (nsdlPanValidationRequestAndResponseDTO.getSchemeCode() == null) {
                        resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            nsdlList = new ArrayList<>();
                            while (resultSet.next()) {
                                dto=new NsdlPanValidationRequestAndResponseDTO();
                                dto.setSchemeCode(resultSet.getString(1));
                                dto.setSchemeName(resultSet.getString(2));
                                dto.setSchemeStatus(resultSet.getString(3));
                                nsdlList.add(dto);
                            }
                        }
                        responseDataDTO.setResult(nsdlList);
                    }
                }else{
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
    public ResponseDataDTO inquiry(
            NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, Object> finalData = new HashMap<>();
        List<PanValidationRetriggerResponseDTO> gstAckFileDownloadDTOList = Collections.emptyList();
        String resultMsg = "SUCCESS";
        // IN PARAMS
        String p_user_id_in = "p_user_id_in";
        String p_user_sol_id_in = "p_user_sol_id_in";
        String p_sch_code_in = "p_sch_code_in";
        String p_type_in = "p_type_in";
        String p_status_in = "p_status_in";

        // OUT PARAMS
        String p_cursor_val_out = "p_cursor_val_out";
        String p_msg_out = "p_msg_out";
        String p_msg_desc_out = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername());
            inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId());
            inParams.put(p_sch_code_in, nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
            inParams.put(p_type_in, nsdlPanValidationRequestAndResponseDTO.getType());
            inParams.put(p_status_in, nsdlPanValidationRequestAndResponseDTO.getFileStatus());
            SqlParameter[] sqlParameters = {
                    new SqlParameter(p_user_id_in, Types.VARCHAR),
                    new SqlParameter(p_user_sol_id_in, Types.VARCHAR),
                    new SqlParameter(p_sch_code_in, Types.VARCHAR),
                    new SqlParameter(p_type_in, Types.VARCHAR),
                    new SqlParameter(p_status_in, Types.VARCHAR)
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_cursor_val_out, OracleTypes.CURSOR),
                    new SqlOutParameter(p_msg_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_desc_out, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(p_cursor_val_out, (rs, rowNum) -> mapGSTAckEkuberRowToExcelData(rs));
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc("PKG_GBM_RBI_BOND_NSDL_PAN_VAL_RDM_OM",
                    "sp_inq",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );
            gstAckFileDownloadDTOList = (List<PanValidationRetriggerResponseDTO>) result.get(p_cursor_val_out);
            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));
            if (CollectionUtils.isEmpty(gstAckFileDownloadDTOList)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            }
            else{
                finalData.put("gstAckFileDownloadDTOList", gstAckFileDownloadDTOList);
                responseDataDTO.setResult(finalData);
            }
        } catch (DataAccessException e) {
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
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    private PanValidationRetriggerResponseDTO mapGSTAckEkuberRowToExcelData(ResultSet resultSet) throws SQLException {
        PanValidationRetriggerResponseDTO dto=new PanValidationRetriggerResponseDTO();
        dto.setName(resultSet.getString(1));
        dto.setNewName(resultSet.getString(2));
        dto.setNameStatus(resultSet.getString(3));
        dto.setDob(resultSet.getString(4));
        dto.setNewDob(resultSet.getString(5));
        dto.setDobStatus(resultSet.getString(6));
        dto.setOldPan(resultSet.getString(7));
        dto.setNewPan(resultSet.getString(8));
        dto.setPanStatus(resultSet.getString(9));
        dto.setTypeOfMatch(resultSet.getString(10));
        dto.setPanStatusValidateDate(resultSet.getString(11));
        dto.setUserId(resultSet.getString(12));
        dto.setRemarks(resultSet.getString(13));
        return dto;
    }
@Override
public ResponseDataDTO upload(NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<NsdlPanValidationRedemptionOnMaturityReqAndResDTO> nsdlPanValidationRequestAndResponseList;
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(
                RbiBondProcNameEnum.PKG_GBM_RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_UPLOAD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getType());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getFileType());
                if(null!=nsdlPanValidationRequestAndResponseDTO&&
                        null!=nsdlPanValidationRequestAndResponseDTO.getNsdlPanStatusList()
                        &&nsdlPanValidationRequestAndResponseDTO.getNsdlPanStatusList().size()>0) {
                    stmt.setArray(index++, generateTYP_typ_rbi_bond_nsdl_OBJ(connection,
                            nsdlPanValidationRequestAndResponseDTO.getNsdlPanStatusList()));
                }else{
                    stmt.setNull(index++, Types.ARRAY, "TYP_RBI_BOND_NSDL");
                }
                if(null!=nsdlPanValidationRequestAndResponseDTO&&
                        null!=nsdlPanValidationRequestAndResponseDTO.getIncomeTaxStatusList()
                        &&nsdlPanValidationRequestAndResponseDTO.getIncomeTaxStatusList().size()>0) {
                    stmt.setArray(index++, generateTYP_typ_rbi_bond_income_OBJ(
                            connection, nsdlPanValidationRequestAndResponseDTO.getIncomeTaxStatusList()));
                }else{
                    stmt.setNull(index++, Types.ARRAY, "TYP_RBI_BOND_INCOME");
                }
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(null==nsdlPanValidationRequestAndResponseDTO.getSchemeCode()
                            && null==nsdlPanValidationRequestAndResponseDTO.getFileType()){
                    resultSet = (ResultSet) stmt.getObject(8);
    if (resultSet != null) {
        nsdlPanValidationRequestAndResponseList = new ArrayList<>();
            while (resultSet.next()) {
                nsdlPanValidationRequestAndResponseDTO = new NsdlPanValidationRedemptionOnMaturityReqAndResDTO();
                nsdlPanValidationRequestAndResponseDTO.setSubCode(resultSet.getString(1));
                nsdlPanValidationRequestAndResponseDTO.setSubCodeDesc(resultSet.getString(2));
                nsdlPanValidationRequestAndResponseDTO.setSchemeStatus(resultSet.getString(3));
                nsdlPanValidationRequestAndResponseList.add(nsdlPanValidationRequestAndResponseDTO);
            }
             responseDataDTO.setResult(nsdlPanValidationRequestAndResponseList);
                    }
                }
                }else{
                    responseDataDTO.setStatusCode("0");
                }
                }else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        } finally {
            databaseConnectionFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    private ARRAY generateTYP_typ_rbi_bond_nsdl_OBJ(Connection connection,List<NsdlPanStatusDTO> list)throws Exception{
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_NSDL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_NSDL", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        NsdlPanStatusDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            dto = list.get(index);
            log.info("NSDL Loop:- {}",dto);
            Object[] paramArray = new Object[10];
            paramArray[0] = dto.getPan();
            paramArray[1] = dto.getPanStatus();
            paramArray[2] = dto.getPanAsPerNsdl();
            paramArray[3] = dto.getName();
            paramArray[4] = dto.getNameStatus();
            paramArray[5] = dto.getNameAsPerNsdl();
            paramArray[6] = dto.getDob();
            paramArray[7] = dto.getDobStatus();
            paramArray[8] = dto.getDobAsPerNsdl();
            paramArray[9] = dto.getRemarks();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    private ARRAY generateTYP_typ_rbi_bond_nsdl_res_OBJ(Connection connection,
                                                        PanValidationRetriggerResponseDTO dto) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_PAN_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_PAN", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[1];
        Object[] paramArray = new Object[14];
        log.info("Processing NSDL: {}" , dto);
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

    private ARRAY generateTYP_typ_rbi_bond_income_OBJ(Connection connection,
                                                      List<IncomeTaxPanStatusDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_INCOME_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_INCOME", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        IncomeTaxPanStatusDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            dto = list.get(index);
            Object[] paramArray = new Object[3];
            paramArray[0] = dto.getPanNo();
            paramArray[1] = dto.getItrFlagStatus();
            paramArray[2] = dto.getIncomeTaxDate();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    @Override
public ResponseDataDTO delete(NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (nsdlPanValidationRequestAndResponseDTO.getDeleteRemarks() == null
                || nsdlPanValidationRequestAndResponseDTO.getDeleteRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(
                RbiBondProcNameEnum.PKG_GBM_RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_DELETE.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getType());
                stmt.setString(index++, nsdlPanValidationRequestAndResponseDTO.getFileType());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            }else {
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
}
