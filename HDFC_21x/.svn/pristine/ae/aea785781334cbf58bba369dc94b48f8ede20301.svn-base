package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;

import com.isl.gbm.common.dto.CommonFlagDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.CommonFlagDAO;
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
public class CommonFlagDAOImpl implements CommonFlagDAO {

    private static final Logger log = LoggerFactory.getLogger(CommonFlagDAOImpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO getModifyDetails(CommonFlagDTO commonFlagDTO) {
        Connection connection = null;
        String resultMsg = "";
        CallableStatement stmt = null;
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;
        CommonFlagDTO mappedCommonFlagDTO = null;
        CommonFlagDTO unmMppedCommonFlagDTO = null;
        CommonFlagDTO mapUnmMppedCommonFlagDTO = new CommonFlagDTO();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_COMMON_FLAG_MODIFY_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, commonFlagDTO.getSchemeCode());
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
                    if(commonFlagDTO.getSchemeCode()==null) {
                        if (resultSet != null) {
                            resultList = new ArrayList<>();
                            if (resultSet != null) {
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("schemeCode", resultSet.getString(1));
                                resultMap.put("schemeName", resultSet.getString(2));
                                resultList.add(resultMap);

                            }
                            }
                            mapUnmMppedCommonFlagDTO.setSchemeList(resultList);

                        }
                    }else if(commonFlagDTO.getSchemeCode()!=null) {

                        ResultSet resultSet1 = (ResultSet) stmt.getObject(4);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(5);
                        if (resultSet2 != null) {

                            List<CommonFlagDTO> mappedList = new ArrayList<>();
                            while (resultSet1.next()) {
                                mappedCommonFlagDTO = new CommonFlagDTO();
                                mappedCommonFlagDTO.setCode(resultSet1.getString(1));
                                mappedCommonFlagDTO.setCodeDescription(resultSet1.getString(2));
                                mappedList.add(mappedCommonFlagDTO);
                            }

                            mapUnmMppedCommonFlagDTO.setMappedDocumentsList(mappedList);
                        }


                        if (resultSet2 != null) {
                            List<CommonFlagDTO> unmappedList = new ArrayList<>();
                            while (resultSet2.next()) {
                                unmMppedCommonFlagDTO = new CommonFlagDTO();
                                unmMppedCommonFlagDTO.setCode(resultSet2.getString(1));
                                unmMppedCommonFlagDTO.setCodeDescription(resultSet2.getString(2));
                                unmappedList.add(unmMppedCommonFlagDTO);
                            }
                            mapUnmMppedCommonFlagDTO.setUnmappedDocumentsList(unmappedList);

                        }
                    }

                }

                mapUnmMppedCommonFlagDTO.setCreatedBy(stmt.getString(6));
                mapUnmMppedCommonFlagDTO.setCreatedDate(stmt.getString(7));
                mapUnmMppedCommonFlagDTO.setModifiedBy(stmt.getString(8));
                mapUnmMppedCommonFlagDTO.setModifiedDate(stmt.getString(9));
                mapUnmMppedCommonFlagDTO.setVerifiedBy(stmt.getString(10));
                mapUnmMppedCommonFlagDTO.setVerifiedDate(stmt.getString(11));
                mapUnmMppedCommonFlagDTO.setVerifiedStatus(stmt.getString(12));
                mapUnmMppedCommonFlagDTO.setRejectionRemarks(stmt.getString(13));
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

      responseDataDTO.setResult(mapUnmMppedCommonFlagDTO);
        responseDataDTO.setMessage(resultMsg);

        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modify(CommonFlagDTO commonFlagDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_COMMON_FLAG_MODIFY_SUBMIT.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, commonFlagDTO.getSchemeCode());
                if (CollectionUtils.isNotEmpty(commonFlagDTO.getModifiedManList())) {
                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master(connection, commonFlagDTO.getModifiedManList()));
                }
                else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);

                }
                if (CollectionUtils.isNotEmpty(commonFlagDTO.getModifiedNonManList())) {

                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master(connection, commonFlagDTO.getModifiedNonManList()));
                }
                else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);
                }

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
    public ResponseDataDTO getVerifyDetails(CommonFlagDTO commonFlagDTO) {
        Connection connection = null;
        String resultMsg = "";
        CallableStatement stmt = null;
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        CommonFlagDTO mappedCommonFlagDTO = null;
        CommonFlagDTO unmMppedCommonFlagDTO = null;
        CommonFlagDTO mapUnmMppedCommonFlagDTO = new CommonFlagDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_COMMON_FLAG_VERIFY_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, commonFlagDTO.getSchemeCode());
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
                    if (commonFlagDTO.getSchemeCode() == null) {
                        if (resultSet != null) {
                            resultList = new ArrayList<>();
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("schemeCode", resultSet.getString(1));
                                resultMap.put("schemeName", resultSet.getString(2));
                                resultList.add(resultMap);

                            }
                            mapUnmMppedCommonFlagDTO.setSchemeList(resultList);

                        }
                    } else if (commonFlagDTO.getSchemeCode() != null) {

                        ResultSet resultSet1 = (ResultSet) stmt.getObject(4);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(5);

                        if (resultSet1 != null) {
                            List<CommonFlagDTO> mappedList = new ArrayList<>();
                            while (resultSet1.next()) {
                                mappedCommonFlagDTO = new CommonFlagDTO();
                                mappedCommonFlagDTO.setCode(resultSet1.getString(1));
                                mappedCommonFlagDTO.setCodeDescription(resultSet1.getString(2));
                                mappedList.add(mappedCommonFlagDTO);
                            }

                            mapUnmMppedCommonFlagDTO.setMappedDocumentsList(mappedList);
                        }

                        // ResultSet resultSet1 = (ResultSet) stmt.getObject(5);
                        if (resultSet2 != null) {
                            List<CommonFlagDTO> unmappedList = new ArrayList<>();
                            while (resultSet2.next()) {
                                unmMppedCommonFlagDTO = new CommonFlagDTO();
                                unmMppedCommonFlagDTO.setCode(resultSet2.getString(1));
                                unmMppedCommonFlagDTO.setCodeDescription(resultSet2.getString(2));
                                unmappedList.add(unmMppedCommonFlagDTO);
                            }
                            mapUnmMppedCommonFlagDTO.setUnmappedDocumentsList(unmappedList);

                        }




                       // commonFlagDTO.setMasterMappedOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(4)));

                       // commonFlagDTO.setMasterUnMappedOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(5)));


                        mapUnmMppedCommonFlagDTO.setCreatedBy(stmt.getString(6));
                        mapUnmMppedCommonFlagDTO.setCreatedDate(stmt.getString(7));
                        mapUnmMppedCommonFlagDTO.setModifiedBy(stmt.getString(8));
                        mapUnmMppedCommonFlagDTO.setModifiedDate(stmt.getString(9));
                        mapUnmMppedCommonFlagDTO.setVerifiedBy(stmt.getString(10));
                        mapUnmMppedCommonFlagDTO.setVerifiedDate(stmt.getString(11));
                        mapUnmMppedCommonFlagDTO.setVerifiedStatus(stmt.getString(12));
                        mapUnmMppedCommonFlagDTO.setRejectionRemarks(stmt.getString(13));

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

      responseDataDTO.setResult(mapUnmMppedCommonFlagDTO);
        responseDataDTO.setMessage(resultMsg);

        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(CommonFlagDTO commonFlagDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_COMMON_FLAG_VERIFY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, commonFlagDTO.getSchemeCode());
//                stmt.setString(index++, dropDownMasterDTO.getRelationshipCode());
//                if (CollectionUtils.isNotEmpty(dropDownMasterDTO.getAddedList()))
//                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, dropDownMasterDTO.getAddedList()));
//                else
               //   stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);
//
//
//                if (CollectionUtils.isNotEmpty(dropDownMasterDTO.getDeletedList()))
//
//                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, dropDownMasterDTO.getDeletedList()));
//                else
                  //  stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);


                if (CollectionUtils.isNotEmpty(commonFlagDTO.getModifiedManList())) {
                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master(connection, commonFlagDTO.getModifiedManList()));
                }
                else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);

                }
                if (CollectionUtils.isNotEmpty(commonFlagDTO.getModifiedNonManList())) {

                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master(connection, commonFlagDTO.getModifiedNonManList()));
                }
                else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);
                }
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
    public ResponseDataDTO delete(CommonFlagDTO commonFlagDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (commonFlagDTO.getRejectionRemarks() == null
                || commonFlagDTO.getRejectionRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_COMMON_FLAG_REJECT.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, commonFlagDTO.getSchemeCode());
//                stmt.setString(index++, dropDownMasterDTO.getRelationshipCode());
//                if (CollectionUtils.isNotEmpty(dropDownMasterDTO.getAddedList()))
//                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, dropDownMasterDTO.getAddedList()));
//                else
//                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);
//
//
//                if (CollectionUtils.isNotEmpty(dropDownMasterDTO.getDeletedList()))
//
//                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master1(connection, dropDownMasterDTO.getDeletedList()));
//                else
//                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);
//                stmt.setString(index++, dropDownMasterDTO.getRejectionRemarks());

                if (CollectionUtils.isNotEmpty(commonFlagDTO.getModifiedNonManList())) {

                    stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_drop_down_master(connection, commonFlagDTO.getModifiedNonManList()));
                }
                else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_DROPDOWN);
                }

                stmt.setString(index++, commonFlagDTO.getRejectionRemarks());
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
    public ResponseDataDTO getInquiryDetails(CommonFlagDTO commonFlagDTO) {
        Connection connection = null;
        String resultMsg = "";
        CallableStatement stmt = null;
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        CommonFlagDTO mappedCommonFlagDTO = null;
        CommonFlagDTO unmMppedCommonFlagDTO = null;
        CommonFlagDTO mapUnmMppedCommonFlagDTO = new CommonFlagDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_COMMON_FLAG_INQUIRY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, commonFlagDTO.getSchemeCode());
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
                    if (commonFlagDTO.getSchemeCode() == null) {
                        if (resultSet != null) {
                            resultList = new ArrayList<>();
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("schemeCode", resultSet.getString(1));
                                resultMap.put("schemeName", resultSet.getString(2));
                                resultList.add(resultMap);

                            }
                            mapUnmMppedCommonFlagDTO.setSchemeList(resultList);

                        }
                    } else if (commonFlagDTO.getSchemeCode() != null) {

                        ResultSet resultSet1 = (ResultSet) stmt.getObject(4);
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(5);

                        if (resultSet1 != null) {
                            List<CommonFlagDTO> mappedList = new ArrayList<>();
                            while (resultSet1.next()) {
                                mappedCommonFlagDTO = new CommonFlagDTO();
                                mappedCommonFlagDTO.setCode(resultSet1.getString(1));
                                mappedCommonFlagDTO.setCodeDescription(resultSet1.getString(2));
                                mappedList.add(mappedCommonFlagDTO);
                            }

                            mapUnmMppedCommonFlagDTO.setMappedDocumentsList(mappedList);
                        }

                        if (resultSet2 != null) {
                            List<CommonFlagDTO> unmappedList = new ArrayList<>();
                            while (resultSet2.next()) {
                                unmMppedCommonFlagDTO = new CommonFlagDTO();
                                unmMppedCommonFlagDTO.setCode(resultSet2.getString(1));
                                unmMppedCommonFlagDTO.setCodeDescription(resultSet2.getString(2));
                                unmappedList.add(unmMppedCommonFlagDTO);
                            }
                            mapUnmMppedCommonFlagDTO.setUnmappedDocumentsList(unmappedList);

                        }




                        // commonFlagDTO.setMasterMappedOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(4)));

                        // commonFlagDTO.setMasterUnMappedOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(5)));


                        mapUnmMppedCommonFlagDTO.setCreatedBy(stmt.getString(6));
                        mapUnmMppedCommonFlagDTO.setCreatedDate(stmt.getString(7));
                        mapUnmMppedCommonFlagDTO.setModifiedBy(stmt.getString(8));
                        mapUnmMppedCommonFlagDTO.setModifiedDate(stmt.getString(9));
                        mapUnmMppedCommonFlagDTO.setVerifiedBy(stmt.getString(10));
                        mapUnmMppedCommonFlagDTO.setVerifiedDate(stmt.getString(11));
                        mapUnmMppedCommonFlagDTO.setVerifiedStatus(stmt.getString(12));
                        mapUnmMppedCommonFlagDTO.setRejectionRemarks(stmt.getString(13));

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

        responseDataDTO.setResult(mapUnmMppedCommonFlagDTO);
        responseDataDTO.setMessage(resultMsg);

        return responseDataDTO;
    }

    private ARRAY generate_typ_gbm_rbi_bond_drop_down_master(Connection connection, List<String> list) throws Exception {
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



