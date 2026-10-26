package com.isl.gbm.rbibond.dao.impl;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.DatabaseConnectionFactory;
import com.isl.gbm.rbibond.dao.RbiBondPanStatusUpdateForTaxDao;
import com.isl.gbm.rbibond.dto.*;
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
import java.util.List;

@Repository
public class RbiBondPanStatusUpdateForTaxDaoImpl implements RbiBondPanStatusUpdateForTaxDao {

    private static final Logger log = LoggerFactory.getLogger(RbiBondPanStatusUpdateForTaxDaoImpl.class);

    @Autowired
    DatabaseConnectionFactory databaseConnectionFactory;

    @Override
    public ResponseDataDTO upload(PanStatusUploadForTdsRequestAndResponseDTO panStatusUploadForTdsRequestDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<PanStatusUploadForTdsRequestAndResponseDTO> responseList;
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(
                        RbiBondProcNameEnum.PKG_GBM_RBIBOND_PAN_STATUS_UPDATE_FOR_TAX_UPLOAD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, panStatusUploadForTdsRequestDTO.getSchemeCode());
                stmt.setString(index++, panStatusUploadForTdsRequestDTO.getFileType());
                //stmt.setString(index++, panStatusUploadForTdsRequestDTO.getFromDate());
                if(null!=panStatusUploadForTdsRequestDTO&&null!=panStatusUploadForTdsRequestDTO.getList()
                        &&panStatusUploadForTdsRequestDTO.getList().size()>0) {
                    stmt.setArray(index++, generateTYP_typ_rbi_bond_pan_status_update_OBJ(connection,panStatusUploadForTdsRequestDTO.getList()));
                }else{
                    stmt.setNull(index++, Types.ARRAY, "TYP_RBI_BOND_PAN_TDS_UPLD");
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
                    if(null==panStatusUploadForTdsRequestDTO.getSchemeCode() &&null==panStatusUploadForTdsRequestDTO.getFileType()){
                        resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            responseList = new ArrayList<>();
                            while (resultSet.next()) {
                                panStatusUploadForTdsRequestDTO = new PanStatusUploadForTdsRequestAndResponseDTO();
                                panStatusUploadForTdsRequestDTO.setSubCode(resultSet.getString(1));
                                panStatusUploadForTdsRequestDTO.setSubCodeDesc(resultSet.getString(2));
                                panStatusUploadForTdsRequestDTO.setSchemeStatus(resultSet.getString(3));
                                responseList.add(panStatusUploadForTdsRequestDTO);
                            }
                            responseDataDTO.setResult(responseList);
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
    private ARRAY generateTYP_typ_rbi_bond_pan_status_update_OBJ(Connection connection,List<PanStatusUploadForTdsFileUploadDTO> list)throws Exception{
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_RBI_BOND_PAN_TDS_UPLD_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_PAN_TDS_UPLD", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        PanStatusUploadForTdsFileUploadDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            dto = list.get(index);
            log.info("PAN STATUS UPDATE LOOP: ");
            Object[] paramArray = new Object[4];
            paramArray[0] = dto.getOldPan();
            paramArray[1] = dto.getNewPanStatus();
            paramArray[2] = dto.getNewAadharSeedingStatus();
            paramArray[3] = getSeedingStatus(dto.getNewAadharSeedingStatus());
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    public static String getSeedingStatus(String flag) {
        if (flag == null) {
            return "Unknown flag"; // Handle null input gracefully
        }
        switch (flag) {
            case "Y":
                return "Operative PAN";
            case "R":
                return "Inoperative PAN";
            case "NA":
                return "For all non-Individual PANs";
            default:
                return "Unknown flag";
        }
    }
    @Override
    public ResponseDataDTO download(PanStatusUploadForTdsRequestAndResponseDTO panStatusUploadForTdsRequestAndResponseDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        PanStatusUploadForTdsRequestAndResponseDTO dto=null;
        List<PanStatusUploadForTdsRequestAndResponseDTO> list=null;
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_PAN_STATUS_UPDATE_FOR_TAX_DOWNLOAD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, panStatusUploadForTdsRequestAndResponseDTO.getSchemeCode());
                stmt.setString(index++, panStatusUploadForTdsRequestAndResponseDTO.getFileType());
                stmt.setString(index++, panStatusUploadForTdsRequestAndResponseDTO.getFromDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXE_SQL_QUERY);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if(null==panStatusUploadForTdsRequestAndResponseDTO.getSchemeCode()
                            && null==panStatusUploadForTdsRequestAndResponseDTO.getFileType()){
                        resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            while (resultSet.next()) {
                                panStatusUploadForTdsRequestAndResponseDTO = new PanStatusUploadForTdsRequestAndResponseDTO();
                                panStatusUploadForTdsRequestAndResponseDTO.setSubCode(resultSet.getString(1));
                                panStatusUploadForTdsRequestAndResponseDTO.setSubCodeDesc(resultSet.getString(2));
                                panStatusUploadForTdsRequestAndResponseDTO.setSchemeStatus(resultSet.getString(3));
                                list.add(panStatusUploadForTdsRequestAndResponseDTO);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else {
                        resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            list = new ArrayList<>();
                            while (resultSet.next()) {
                                dto = new PanStatusUploadForTdsRequestAndResponseDTO();
                                dto.setOldPan(resultSet.getString(1));
                                dto.setOldPanStatus(resultSet.getString(2));
                                dto.setOldName(resultSet.getString(3));
                                dto.setOldDob(resultSet.getString(4));
                                dto.setOldAadharSeedingStatus(resultSet.getString(5));
                                list.add(dto);
                            }
                            responseDataDTO.setResult(list);
                        }
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
}
