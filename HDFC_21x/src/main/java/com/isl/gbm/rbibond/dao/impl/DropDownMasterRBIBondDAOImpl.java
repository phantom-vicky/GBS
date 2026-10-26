package com.isl.gbm.rbibond.dao.impl;


import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.DropDownMasterDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;

import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.DropDownMasterRBIBondDAO;

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
public class DropDownMasterRBIBondDAOImpl implements DropDownMasterRBIBondDAO {


    private static final Logger log = LoggerFactory.getLogger(DropDownMasterRBIBondDAOImpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO addDetails(DropDownMasterDTO dropDownMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_DROP_DOWN_MASTER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dropDownMasterDTO.getRelationshipCode());
                stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, dropDownMasterDTO.getAddedList()));
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
    public ResponseDataDTO modifyDetails(DropDownMasterDTO dropDownMasterDTO) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_DROP_DOWN_MASTER_MODIFY_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dropDownMasterDTO.getRelationshipCode());
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
                        dropDownMasterDTO.setSchemeDetailList(resultList);

                    }
                }

                dropDownMasterDTO.setCreatedBy(stmt.getString(5));
                dropDownMasterDTO.setCreatedDate(stmt.getString(6));
                dropDownMasterDTO.setModifiedBy(stmt.getString(7));
                dropDownMasterDTO.setModifiedDate(stmt.getString(8));
                dropDownMasterDTO.setVerifiedBy(stmt.getString(9));
                dropDownMasterDTO.setVerifiedDate(stmt.getString(10));
                dropDownMasterDTO.setVerifiedStatus(stmt.getString(11));
                dropDownMasterDTO.setRejectionRemarks(stmt.getString(12));
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setResult(dropDownMasterDTO);
        responseDataDTO.setMessage(resultMsg);

        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySubmit(DropDownMasterDTO dropDownMasterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_DROP_DOWN_MASTER_MODIFY_SUBMIT.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dropDownMasterDTO.getRelationshipCode());
                if (CollectionUtils.isNotEmpty(dropDownMasterDTO.getAddedList()))
                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, dropDownMasterDTO.getAddedList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);


                if (CollectionUtils.isNotEmpty(dropDownMasterDTO.getDeletedList()))

                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, dropDownMasterDTO.getDeletedList()));
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
    public ResponseDataDTO verifyDetails(DropDownMasterDTO dropDownMasterDTO) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_DROP_DOWN_MASTER_VERIFY_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dropDownMasterDTO.getRelationshipCode());
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
                        dropDownMasterDTO.setSchemeDetailList(resultList);
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
                        dropDownMasterDTO.setAddedDetailList(resultList);
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
                        dropDownMasterDTO.setDeleteDetailList(resultList);
                    }
                }

                dropDownMasterDTO.setCreatedBy(stmt.getString(7));
                dropDownMasterDTO.setCreatedDate(stmt.getString(8));
                dropDownMasterDTO.setModifiedBy(stmt.getString(9));
                dropDownMasterDTO.setModifiedDate(stmt.getString(10));
                dropDownMasterDTO.setVerifiedBy(stmt.getString(11));
                dropDownMasterDTO.setVerifiedDate(stmt.getString(12));
                dropDownMasterDTO.setVerifiedStatus(stmt.getString(13));
                dropDownMasterDTO.setRejectionRemarks(stmt.getString(14));
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setResult(dropDownMasterDTO);
        responseDataDTO.setMessage(resultMsg);

        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySubmit(DropDownMasterDTO dropDownMasterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_DROP_DOWN_MASTER_VERIFY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dropDownMasterDTO.getRelationshipCode());
                if (CollectionUtils.isNotEmpty(dropDownMasterDTO.getAddedList()))
                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, dropDownMasterDTO.getAddedList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);


                if (CollectionUtils.isNotEmpty(dropDownMasterDTO.getDeletedList()))

                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, dropDownMasterDTO.getDeletedList()));
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
    public ResponseDataDTO inquiry(DropDownMasterDTO dropDownMasterDTO) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_DROP_DOWN_MASTER_INQUIRY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dropDownMasterDTO.getRelationshipCode());
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
                            resultMap.put("fieldName", resultSet.getString(1));
                            resultList.add(resultMap);

                        }
                        dropDownMasterDTO.setSchemeDetailList(resultList);
                    }

                    ResultSet resultSet1 = (ResultSet) stmt.getObject(5);
                    if (resultSet1 != null) {
                        resultList = new ArrayList<>();
                        while (resultSet1.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("fieldName", resultSet1.getString(1));
                            resultList.add(resultMap);
                        }
                        dropDownMasterDTO.setAddedDetailList(resultList);
                    }
                    ResultSet resultSet2 = (ResultSet) stmt.getObject(6);
                    if (resultSet2 != null) {
                        resultList = new ArrayList<>();
                        while (resultSet2.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("fieldName", resultSet2.getString(1));

                            resultList.add(resultMap);
                        }
                        dropDownMasterDTO.setDeleteDetailList(resultList);
                    }
                }


                dropDownMasterDTO.setCreatedBy(stmt.getString(7));
                dropDownMasterDTO.setCreatedDate(stmt.getString(8));
                dropDownMasterDTO.setModifiedBy(stmt.getString(9));
                dropDownMasterDTO.setModifiedDate(stmt.getString(10));
                dropDownMasterDTO.setVerifiedBy(stmt.getString(11));
                dropDownMasterDTO.setVerifiedDate(stmt.getString(12));
                dropDownMasterDTO.setVerifiedStatus(stmt.getString(13));
                dropDownMasterDTO.setRejectionRemarks(stmt.getString(14));


            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setResult(dropDownMasterDTO);
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO reject(DropDownMasterDTO dropDownMasterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (dropDownMasterDTO.getRejectionRemarks() == null
                || dropDownMasterDTO.getRejectionRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_DROP_DOWN_MASTER_REJECT.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dropDownMasterDTO.getRelationshipCode());
                if (CollectionUtils.isNotEmpty(dropDownMasterDTO.getAddedList()))
                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, dropDownMasterDTO.getAddedList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);


                if (CollectionUtils.isNotEmpty(dropDownMasterDTO.getDeletedList()))

                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, dropDownMasterDTO.getDeletedList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);
                stmt.setString(index++, dropDownMasterDTO.getRejectionRemarks());
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


    private ARRAY generate_typ_gbm_rbi_bond_drop_down_master(Connection connection, List<DropDownMasterDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_RBIBOND_DRP_DWN_MST_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_RBIBOND_DRP_DWN_MST", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        DropDownMasterDTO dropDownResponseDTO = null;
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
