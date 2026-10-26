package com.isl.gbm.rbibond.dao.impl;


import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.AgeBseLkIPdDetlPmteEncasmetDAO;
import com.isl.gbm.rbibond.dto.AgeBseLkIPdDetlPmteEncasmetDTO;
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
import java.util.List;

@Repository
public class AgeBseLkIPdDetlPmteEncasmetDAOimpl implements AgeBseLkIPdDetlPmteEncasmetDAO {


    private static final Logger log = LoggerFactory.getLogger(AgeBseLkIPdDetlPmteEncasmetDAOimpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;



    @Override
    public ResponseDataDTO addValidation(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_AGE_BSD_DET_PMTE_ADD_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() == null) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(4);
                        if (resultSet != null) {
                            List<AgeBseLkIPdDetlPmteEncasmetDTO> list = new ArrayList<>();
                            AgeBseLkIPdDetlPmteEncasmetDTO item = null;
                            while (resultSet.next()) {
                                item = new AgeBseLkIPdDetlPmteEncasmetDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSubCodeDescription(resultSet.getString(2));
                                item.setStatus(resultSet.getString(3));

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
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }


        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO add(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_AGE_BSD_DET_PMTE_ADD.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate());
                stmt.setArray(index++, this.generate_typ_gbm_rbi_bond_age_base_obj(connection, ageBseLkIPdDetlPmteEncasmetDTO.getValues()));
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

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySelect(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_AGE_BSD_DET_PMTE_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
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
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {


                    if(ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo() == null && ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate() == null && ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() != null){

                        ResultSet resultSet = (ResultSet) stmt.getObject(15);
                        if(resultSet != null) {
                            List<AgeBseLkIPdDetlPmteEncasmetDTO> list = new ArrayList<>();
                            AgeBseLkIPdDetlPmteEncasmetDTO item = null;
                            while (resultSet.next()) {
                                item = new AgeBseLkIPdDetlPmteEncasmetDTO();
                                item.setVersionNo(resultSet.getString(1));
                                item.setEffectiveFromDate(resultSet.getString(2));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    else if(ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() == null){
                        ResultSet resultSet = (ResultSet) stmt.getObject(15);
                        if (resultSet != null) {
                            List<AgeBseLkIPdDetlPmteEncasmetDTO> list = new ArrayList<>();
                            AgeBseLkIPdDetlPmteEncasmetDTO item = null;
                            while (resultSet.next()) {
                                item = new AgeBseLkIPdDetlPmteEncasmetDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSubCodeDescription(resultSet.getString(2));
                                item.setStatus(resultSet.getString(3));
                                item.setVersionNo(resultSet.getString(4));
                                item.setEffectiveFromDate(resultSet.getString(5));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    else if(ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo() != null && ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate() != null){
                        ageBseLkIPdDetlPmteEncasmetDTO.setCreatedBy(stmt.getString(6));
                        ageBseLkIPdDetlPmteEncasmetDTO.setCreatedDate(stmt.getString(7));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastModifiedBy(stmt.getString(8));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastModifiedDate(stmt.getString(9));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastVerifiedBy(stmt.getString(10));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastVerifiedDate(stmt.getString(11));
                        ageBseLkIPdDetlPmteEncasmetDTO.setPreviousOperationType(stmt.getString(12));
                        ageBseLkIPdDetlPmteEncasmetDTO.setVerifiedStatus(stmt.getString(13));
                        ageBseLkIPdDetlPmteEncasmetDTO.setRejectionRemarks(stmt.getString(14));

                        ageBseLkIPdDetlPmteEncasmetDTO.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(15)));
//                        ageBseLkIPdDetlPmteEncasmetDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));
                        responseDataDTO.setResult(ageBseLkIPdDetlPmteEncasmetDTO);
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
    public ResponseDataDTO verify(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_AGE_BSD_DET_PMTE_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo());
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
    public ResponseDataDTO modifySelect(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_AGE_BSD_DET_PMTE_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
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
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(14);
                        if (resultSet != null) {
                            List<AgeBseLkIPdDetlPmteEncasmetDTO> list = new ArrayList<>();
                            AgeBseLkIPdDetlPmteEncasmetDTO item = null;
                            while (resultSet.next()) {
                                item = new AgeBseLkIPdDetlPmteEncasmetDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSubCodeDescription(resultSet.getString(2));
                                item.setStatus(resultSet.getString(3));
                                item.setVersionNo(resultSet.getString(4));
                                item.setEffectiveFromDate(resultSet.getString(5));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else {
                        ageBseLkIPdDetlPmteEncasmetDTO.setCreatedBy(stmt.getString(5));
                        ageBseLkIPdDetlPmteEncasmetDTO.setCreatedDate(stmt.getString(6));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastModifiedBy(stmt.getString(7));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastModifiedDate(stmt.getString(8));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastVerifiedBy(stmt.getString(9));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastVerifiedDate(stmt.getString(10));
                        ageBseLkIPdDetlPmteEncasmetDTO.setPreviousOperationType(stmt.getString(11));
                        ageBseLkIPdDetlPmteEncasmetDTO.setVerifiedStatus(stmt.getString(12));
                        ageBseLkIPdDetlPmteEncasmetDTO.setRejectionRemarks(stmt.getString(13));
                        ageBseLkIPdDetlPmteEncasmetDTO.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(14)));
                        responseDataDTO.setResult(ageBseLkIPdDetlPmteEncasmetDTO);

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
    public ResponseDataDTO modify(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_AGE_BSD_DET_PMTE_MOD.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode());
                if (CollectionUtils.isNotEmpty(ageBseLkIPdDetlPmteEncasmetDTO.getColumnValueDetailsList()))
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, ageBseLkIPdDetlPmteEncasmetDTO.getColumnValueDetailsList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);

                if (CollectionUtils.isNotEmpty(ageBseLkIPdDetlPmteEncasmetDTO.getColumnValueDetailsAddList()))
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, ageBseLkIPdDetlPmteEncasmetDTO.getColumnValueDetailsAddList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                if (CollectionUtils.isNotEmpty(ageBseLkIPdDetlPmteEncasmetDTO.getColumnValueDetailsDelList()))

                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, ageBseLkIPdDetlPmteEncasmetDTO.getColumnValueDetailsDelList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);




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
    public ResponseDataDTO inquiry(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_AGE_BSD_DET_PMTE_INQ.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
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

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    if(ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo() == null && ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate() == null &&  ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() != null){

                        ResultSet resultSet = (ResultSet) stmt.getObject(15);
                        if(resultSet != null) {
                            List<AgeBseLkIPdDetlPmteEncasmetDTO> list = new ArrayList<>();
                            AgeBseLkIPdDetlPmteEncasmetDTO item = null;
                            while (resultSet.next()) {
                                item = new AgeBseLkIPdDetlPmteEncasmetDTO();
                                item.setVersionNo(resultSet.getString(1));
                                item.setEffectiveFromDate(resultSet.getString(2));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    else if(ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode() == null){
                        ResultSet resultSet = (ResultSet) stmt.getObject(15);
                        if (resultSet != null) {
                            List<AgeBseLkIPdDetlPmteEncasmetDTO> list = new ArrayList<>();
                            AgeBseLkIPdDetlPmteEncasmetDTO item = null;
                            while (resultSet.next()) {
                                item = new AgeBseLkIPdDetlPmteEncasmetDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSubCodeDescription(resultSet.getString(2));
                                item.setStatus(resultSet.getString(3));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                    else if(ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo() != null && ageBseLkIPdDetlPmteEncasmetDTO.getEffectiveFromDate() != null){
                        ageBseLkIPdDetlPmteEncasmetDTO.setCreatedBy(stmt.getString(6));
                        ageBseLkIPdDetlPmteEncasmetDTO.setCreatedDate(stmt.getString(7));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastModifiedBy(stmt.getString(8));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastModifiedDate(stmt.getString(9));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastVerifiedBy(stmt.getString(10));
                        ageBseLkIPdDetlPmteEncasmetDTO.setLastVerifiedDate(stmt.getString(11));
                        ageBseLkIPdDetlPmteEncasmetDTO.setPreviousOperationType(stmt.getString(12));
                        ageBseLkIPdDetlPmteEncasmetDTO.setVerifiedStatus(stmt.getString(13));
                        ageBseLkIPdDetlPmteEncasmetDTO.setRejectionRemarks(stmt.getString(14));

                        ageBseLkIPdDetlPmteEncasmetDTO.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(15)));
//                        ageBseLkIPdDetlPmteEncasmetDTO.setMasterOldNewValueModel(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));
                        responseDataDTO.setResult(ageBseLkIPdDetlPmteEncasmetDTO);
                    }
                }
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
    public ResponseDataDTO delete(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (ageBseLkIPdDetlPmteEncasmetDTO.getRejectionRemarks() == null
                || ageBseLkIPdDetlPmteEncasmetDTO.getRejectionRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBIBOND_AGE_BSD_DET_PMTE_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getSchemeCode());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getVersionNo());
                stmt.setString(index++, ageBseLkIPdDetlPmteEncasmetDTO.getRejectionRemarks());
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


    private ARRAY generate_typ_gbm_rbi_bond_age_base_obj(Connection connection, List<AgeBseLkIPdDetlPmteEncasmetDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_AGE_BASE_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_AGE_BASE", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        AgeBseLkIPdDetlPmteEncasmetDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[3];
            dto = list.get(index);
            paramArray[0] = dto.getSubscriberAgeBracketFrom();
            paramArray[1] = dto.getSubscriberAgeBracketTo();
            paramArray[2] = dto.getLockInPeriod();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


}
