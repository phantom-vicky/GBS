package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.RejectionReasonDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.RejectionReasonMasterDAO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.commons.collections.CollectionUtils;
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
import java.util.Map;

@Repository
public class RejectionReasonMasterDAOImpl implements RejectionReasonMasterDAO {


    private static final Logger log = LoggerFactory.getLogger(RejectionReasonMasterDAOImpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO addDetails(RejectionReasonDTO rejectionReasonDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_REJECTION_REASON_MASTER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rejectionReasonDTO.getFieldCode());
                stmt.setString(index++, rejectionReasonDTO.getFieldName());
                stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, rejectionReasonDTO.getValues()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
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
    public ResponseDataDTO modifyDetails(RejectionReasonDTO rejectionReasonDTO) {
        Connection connection = null;
        String resultMsg = "";
        CallableStatement stmt = null;
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_REJECTION_REASON_MASTER_MODIFY_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rejectionReasonDTO.getFieldCode());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(4);
                    if (resultSet != null) {
                        resultList = new ArrayList<>();
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("fieldCode", resultSet.getString(1));
                            resultMap.put("fieldName", resultSet.getString(2));
                            resultList.add(resultMap);

                        }
                        rejectionReasonDTO.setSchemeDetailList(resultList);

                    }
                }

                rejectionReasonDTO.setCreatedBy(stmt.getString(5));
                rejectionReasonDTO.setCreatedDate(stmt.getString(6));
                rejectionReasonDTO.setModifiedBy(stmt.getString(7));
                rejectionReasonDTO.setModifiedDate(stmt.getString(8));
                rejectionReasonDTO.setVerifiedBy(stmt.getString(9));
                rejectionReasonDTO.setVerifiedDate(stmt.getString(10));
                rejectionReasonDTO.setVerifiedStatus(stmt.getString(11));
                rejectionReasonDTO.setRejectionRemarks(stmt.getString(12));
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setResult(rejectionReasonDTO);
        responseDataDTO.setMessage(resultMsg);

        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySubmit(RejectionReasonDTO rejectionReasonDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_REJECTION_REASON_MASTER_MODIFY_SUBMIT.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rejectionReasonDTO.getFieldCode());
                stmt.setString(index++, rejectionReasonDTO.getFieldName());
                if (CollectionUtils.isNotEmpty(rejectionReasonDTO.getAddedList()))
                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, rejectionReasonDTO.getAddedList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);


                if (CollectionUtils.isNotEmpty(rejectionReasonDTO.getDeletedList()))

                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, rejectionReasonDTO.getDeletedList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);


                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
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
    public ResponseDataDTO verifyDetails(RejectionReasonDTO rejectionReasonDTO) {
        Connection connection = null;
        String resultMsg = "";
        CallableStatement stmt = null;
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_REJECTION_REASON_MASTER_VERIFY_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rejectionReasonDTO.getFieldCode());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(4);
                    if (resultSet != null) {
                        resultList = new ArrayList<>();
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("fieldCode", resultSet.getString(1));
                            resultMap.put("fieldName", resultSet.getString(2));
                            resultList.add(resultMap);

                        }
                        rejectionReasonDTO.setSchemeDetailList(resultList);
                    }

                    ResultSet resultSet1 = (ResultSet) stmt.getObject(5);
                    if (resultSet1 != null) {
                        resultList = new ArrayList<>();
                        while (resultSet1.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("fieldCode", resultSet1.getString(1));
                            resultMap.put("fieldName", resultSet1.getString(2));

                            resultList.add(resultMap);
                        }
                        rejectionReasonDTO.setAddedDetailList(resultList);
                    }
                    ResultSet resultSet2 = (ResultSet) stmt.getObject(6);
                    if (resultSet2 != null) {
                        resultList = new ArrayList<>();
                        while (resultSet2.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("fieldCode", resultSet2.getString(1));
                            resultMap.put("fieldName", resultSet2.getString(2));

                            resultList.add(resultMap);
                        }
                        rejectionReasonDTO.setDeleteDetailList(resultList);
                    }
                }

                rejectionReasonDTO.setCreatedBy(stmt.getString(7));
                rejectionReasonDTO.setCreatedDate(stmt.getString(8));
                rejectionReasonDTO.setModifiedBy(stmt.getString(9));
                rejectionReasonDTO.setModifiedDate(stmt.getString(10));
                rejectionReasonDTO.setVerifiedBy(stmt.getString(11));
                rejectionReasonDTO.setVerifiedDate(stmt.getString(12));
                rejectionReasonDTO.setVerifiedStatus(stmt.getString(13));
                rejectionReasonDTO.setRejectionRemarks(stmt.getString(14));
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setResult(rejectionReasonDTO);
        responseDataDTO.setMessage(resultMsg);

        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySubmit(RejectionReasonDTO rejectionReasonDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_REJECTION_REASON_MASTER_VERIFY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rejectionReasonDTO.getFieldCode());
                if (CollectionUtils.isNotEmpty(rejectionReasonDTO.getAddedList()))
                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, rejectionReasonDTO.getAddedList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);


                if (CollectionUtils.isNotEmpty(rejectionReasonDTO.getDeletedList()))

                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, rejectionReasonDTO.getDeletedList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);
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
    public ResponseDataDTO inquiry(RejectionReasonDTO rejectionReasonDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_REJECTION_REASON_MASTER_INQUIRY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rejectionReasonDTO.getFieldCode());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(4);
                    if (resultSet != null) {
                        resultList = new ArrayList<>();
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("rejectionReason", resultSet.getString(1));
                            resultList.add(resultMap);

                        }
                        rejectionReasonDTO.setSchemeDetailList(resultList);
                    }

                    ResultSet resultSet1 = (ResultSet) stmt.getObject(5);
                    if (resultSet1 != null) {
                        resultList = new ArrayList<>();
                        while (resultSet1.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("rejectionReason", resultSet1.getString(1));
                            resultList.add(resultMap);
                        }
                        rejectionReasonDTO.setAddedDetailList(resultList);
                    }
                    ResultSet resultSet2 = (ResultSet) stmt.getObject(6);
                    if (resultSet2 != null) {
                        resultList = new ArrayList<>();
                        while (resultSet2.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("rejectionReason", resultSet2.getString(1));

                            resultList.add(resultMap);
                        }
                        rejectionReasonDTO.setDeleteDetailList(resultList);
                    }
                }


                rejectionReasonDTO.setCreatedBy(stmt.getString(7));
                rejectionReasonDTO.setCreatedDate(stmt.getString(8));
                rejectionReasonDTO.setModifiedBy(stmt.getString(9));
                rejectionReasonDTO.setModifiedDate(stmt.getString(10));
                rejectionReasonDTO.setVerifiedBy(stmt.getString(11));
                rejectionReasonDTO.setVerifiedDate(stmt.getString(12));
                rejectionReasonDTO.setVerifiedStatus(stmt.getString(13));
                rejectionReasonDTO.setRejectionRemarks(stmt.getString(14));


            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setResult(rejectionReasonDTO);
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO reject(RejectionReasonDTO rejectionReasonDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rejectionReasonDTO.getRejectionRemarks() == null
                || rejectionReasonDTO.getRejectionRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_REJECTION_REASON_MASTER_REJECT.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rejectionReasonDTO.getFieldCode());
                if (CollectionUtils.isNotEmpty(rejectionReasonDTO.getAddedList()))
                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, rejectionReasonDTO.getAddedList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);


                if (CollectionUtils.isNotEmpty(rejectionReasonDTO.getDeletedList()))

                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, rejectionReasonDTO.getDeletedList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);
                stmt.setString(index++, rejectionReasonDTO.getRejectionRemarks());
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


    private ARRAY generate_typ_gbm_rbi_bond_drop_down_master(Connection connection, List<RejectionReasonDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_RBIBOND_DRP_DWN_MST_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_RBIBOND_DRP_DWN_MST", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        RejectionReasonDTO dropDownResponseDTO = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[1];
            dropDownResponseDTO = list.get(index);
            paramArray[0] = dropDownResponseDTO.getFieldName();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private ARRAY generate_typ_gbm_rbi_bond_drop_down_master1(Connection connection, List<String> list) throws Exception {
        // Create the SQL StructDescriptor and ArrayDescriptor for the Oracle object types
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_RBIBOND_DRP_DWN_MST_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_RBIBOND_DRP_DWN_MST", connection);

        // Create an array of STRUCTs to hold the values
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];

        // Loop through the list and create STRUCTs for each value
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[1]; // Adjust size if needed
            paramArray[0] = list.get(index); // Get the field code

            // Create the STRUCT using the descriptor and parameter array
            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }

        // Return the ARRAY containing all the STRUCTs
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

}

