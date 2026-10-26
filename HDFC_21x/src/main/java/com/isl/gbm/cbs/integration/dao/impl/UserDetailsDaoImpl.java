/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao.impl;

import com.isl.gbm.cbs.integration.common.ProcNameEnum;
import com.isl.gbm.cbs.integration.dao.UserDetailsDao;
import com.isl.gbm.cbs.integration.dto.GbmUserDetailsDto;
import com.isl.gbm.cbs.integration.model.GbmUserDetails;
import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.*;

/**
 * The Class UserDetailsDaoImpl.
 */
@Repository
public class UserDetailsDaoImpl extends HibernateDao implements UserDetailsDao {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsDaoImpl.class);
    /**
     * The Constant FILE_NAME.
     */
    private static final String FILE_NAME = "fileName";

    /**
     * The Constant VERIFIED.
     */
    private static final String VERIFIED = "verified";

    /**
     * The Constant DEL_FLG.
     */
    private static final String DEL_FLG = "deleted";

    /**
     * The Constant ID.
     */
    private static final String ID = "id";

    /**
     * The Constant USER_ID.
     */
    private static final String USER_ID = "userId";

    /**
     * The session factory.
     */
    private SessionFactory sessionFactory;


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO add(GbmUserDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_USER_DTLS_SP_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setArray(index++, generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection,dto.getColumnValues()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }
    @Override
    public ResponseDataDTO modifySelect(final GbmUserDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        GbmUserDetailsDto gbmUserDetailsDto = null;
        List<GbmUserDetailsDto> gbmUserDetailsDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_USER_DTLS_SP_MOD_SEL.getProcName());
                stmt.setString("p_modifier_id_in", UserAccountUtils.getCurrentUsername());
                stmt.setString("p_modifier_sol_id_in", UserAccountUtils.getCurrentSolId());
                stmt.setString("p_user_id_in", dto.getUserId());
                stmt.registerOutParameter("p_cursor_val_out", OracleTypes.CURSOR);
                stmt.registerOutParameter("p_msg_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_desc_out", Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString("p_msg_desc_out"));
                resultMsg = stmt.getString("p_msg_desc_out");
                ResultSet resultSet = null;
                if (stmt.getString("p_msg_desc_out")  != null && stmt.getString("p_msg_desc_out").equals("SUCCESS")) {

                    if(dto.getUserId() == null) {
                        resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                gbmUserDetailsDto = new GbmUserDetailsDto();
                                gbmUserDetailsDto.setUserId(resultSet.getString("USER_ID"));
                                gbmUserDetailsDtoList.add(gbmUserDetailsDto);
                            }
                        }
                    }
                    else {
                        resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                gbmUserDetailsDto = new GbmUserDetailsDto();
                                gbmUserDetailsDto.setId(Integer.parseInt(resultSet.getString("ID")));
                                gbmUserDetailsDto.setUserId(resultSet.getString("USER_ID"));
                                gbmUserDetailsDto.setUserPassword(resultSet.getString("USER_PW"));
                                gbmUserDetailsDto.setUserWorkClass(resultSet.getString("USER_WORK_CLASS"));
                                gbmUserDetailsDto.setSolId(resultSet.getString("SOL_ID"));
                                gbmUserDetailsDto.setUserSolTenorOut(resultSet.getString("USER_SOL_TENOR"));
                                gbmUserDetailsDto.setUserMaxInactiveTime(Integer.valueOf(resultSet.getString("USER_MAX_INACTIVE_TIME")));
                                gbmUserDetailsDto.setUserAccountExpiryDateOut(resultSet.getString("USER_ACCT_EXPY_DATE"));
                                gbmUserDetailsDto.setUserPasswordExpiryDateOut(resultSet.getString("USER_PW_EXPY_DATE"));
                                gbmUserDetailsDto.setUserApplicationName(resultSet.getString("USER_APPL_NAME"));
                                gbmUserDetailsDto.setNewUserFlagOut(resultSet.getString("NEW_USER_FLG"));
                                gbmUserDetailsDto.setDefaultTermId(resultSet.getString("DEFAULT_TERM_ID"));
                                gbmUserDetailsDto.setVirtualFlagOut(resultSet.getString("VIRTUAL_FLG"));
                                gbmUserDetailsDto.setFileName(resultSet.getString("FILE_NAME"));
                                gbmUserDetailsDto.setAdditionalText1(resultSet.getString("ADDL_TEXT1"));
                                gbmUserDetailsDto.setAdditionalText2(resultSet.getString("ADDL_TEXT2"));
                                gbmUserDetailsDto.setAdditionalText3(resultSet.getString("ADDL_TEXT3"));
                                gbmUserDetailsDto.setCreatedBy(resultSet.getString("CREATED_BY"));
                                gbmUserDetailsDto.setCreatDate(resultSet.getString("CREATED_DATE"));
                                gbmUserDetailsDto.setModifiedBy(resultSet.getString("MODIFIED_BY"));
                                gbmUserDetailsDto.setModDate(resultSet.getString("LAST_MOD_DATE"));
                                gbmUserDetailsDto.setVerifiedFlag(resultSet.getString("IS_VERIFIED"));
                                gbmUserDetailsDto.setVerifiedBy(resultSet.getString("VERIFIED_BY"));
                                gbmUserDetailsDto.setVerDate(resultSet.getString("VERIFIED_DATE"));
                                gbmUserDetailsDtoList.add(gbmUserDetailsDto);
                            }
                        }


                    }
                }


            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(gbmUserDetailsDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO modifySubmit(GbmUserDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_USER_DTLS_SP_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, String.valueOf(dto.getId()));
                stmt.setArray(index++, generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection,dto.getColumnValues()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }


    public static ARRAY generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(Connection connection, List<ColumnValueDTO> columnValueDTOList) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COLUMN_DETAILS, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_COLUMN_DETAILS, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[columnValueDTOList.size()];
        ColumnValueDTO columnValueDTO = null;
        for (int index = 0; index < columnValueDTOList.size(); index++) {
            columnValueDTO = columnValueDTOList.get(index);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("COLUMN_NAME", columnValueDTO.getColName());
            paramMap.put("COL_VALUE", columnValueDTO.getValue());

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramMap);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


    @Override
    public ResponseDataDTO inquiry(final GbmUserDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        GbmUserDetailsDto gbmUserDetailsDto = null;
        List<GbmUserDetailsDto> gbmUserDetailsDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_USER_DTLS_SP_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getUserId());
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index)  != null && stmt.getString(index).equalsIgnoreCase("SUCCESS")) {

                    if(dto.getUserId() == null) {
                        resultSet = (ResultSet) stmt.getObject(11);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                gbmUserDetailsDto = new GbmUserDetailsDto();
                                gbmUserDetailsDto.setUserId(resultSet.getString(1));
                                gbmUserDetailsDtoList.add(gbmUserDetailsDto);
                            }
                        }
                    }
                    else {
                        gbmUserDetailsDto = new GbmUserDetailsDto();
                        gbmUserDetailsDto.setCbsUserDetailsOldNewValue(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(11)));
                        gbmUserDetailsDto.setCreatedBy(stmt.getString(3));
                        gbmUserDetailsDto.setCreatDate(stmt.getString(4));
                        gbmUserDetailsDto.setModifiedBy(stmt.getString(5));
                        gbmUserDetailsDto.setModDate(stmt.getString(6));
                        gbmUserDetailsDto.setVerifiedBy(stmt.getString(7));
                        gbmUserDetailsDto.setVerDate(stmt.getString(8));
                        gbmUserDetailsDto.setPreviousOperationType(stmt.getString(9));
                        gbmUserDetailsDto.setVerifiedFlag(stmt.getString(10));
                        gbmUserDetailsDtoList.add(gbmUserDetailsDto);
                    }
                }


            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(gbmUserDetailsDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(final GbmUserDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        GbmUserDetailsDto gbmUserDetailsDto = null;
        List<GbmUserDetailsDto> gbmUserDetailsDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_USER_DTLS_SP_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getUserId());
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index)  != null && stmt.getString(index).equalsIgnoreCase("SUCCESS")) {

                    if(dto.getUserId() == null) {
                        resultSet = (ResultSet) stmt.getObject(13);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                gbmUserDetailsDto = new GbmUserDetailsDto();
                                gbmUserDetailsDto.setUserId(resultSet.getString(1));
                                gbmUserDetailsDtoList.add(gbmUserDetailsDto);
                            }
                        }
                    }
                    else {
                        gbmUserDetailsDto = new GbmUserDetailsDto();
                        gbmUserDetailsDto.setCbsUserDetailsOldNewValue(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(13)));
                        gbmUserDetailsDto.setId(Integer.parseInt(stmt.getString(4)));
                        gbmUserDetailsDto.setCreatedBy(stmt.getString(5));
                        gbmUserDetailsDto.setCreatDate(stmt.getString(6));
                        gbmUserDetailsDto.setModifiedBy(stmt.getString(7));
                        gbmUserDetailsDto.setModDate(stmt.getString(8));
                        gbmUserDetailsDto.setVerifiedBy(stmt.getString(9));
                        gbmUserDetailsDto.setVerDate(stmt.getString(10));
                        gbmUserDetailsDto.setPreviousOperationType(stmt.getString(11));
                        gbmUserDetailsDto.setVerifiedFlag(stmt.getString(12));
                        gbmUserDetailsDtoList.add(gbmUserDetailsDto);
                    }
                }


            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(gbmUserDetailsDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO verifySubmit(final GbmUserDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_USER_DTLS_SP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, String.valueOf(dto.getId()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }
    @Override
    public ResponseDataDTO deleteSubmit(final  GbmUserDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_USER_DTLS_SP_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, String.valueOf(dto.getId()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }







    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.UserDetailsDao#delete(java.lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void delete(final String userFileName) {
        final Session session = getSession();
        final Query query = session.createQuery("delete from GbmUserDetails where fileName = :fileName");
        query.setParameter(FILE_NAME, userFileName);
        query.executeUpdate();
    }


    @Override
    public void deleteUsingId(Integer id) {
        final Session session = getSession();
        final Query query = session.createQuery("delete from GbmUserDetails where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.UserDetailsDao#fileNameExists(java.lang.
     * String)
     */
    @Override
    public boolean fileNameExists(final String userFileName) {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from GbmUserDetails where fileName = :fileName group by fileName", String.class);
        query.setParameter(FILE_NAME, userFileName);
        final List<String> fileNameList = query.list();
        return CollectionUtils.size(fileNameList) > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.UserDetailsDao#getAllRecords()
     */
    @Override
    public List<GbmUserDetails> getAllRecords() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<GbmUserDetails> criteriaQuery = builder.createQuery(GbmUserDetails.class);
        final Root<GbmUserDetails> root = criteriaQuery.from(GbmUserDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(DEL_FLG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        final List<GbmUserDetails> otherBankList = session.createQuery(criteriaQuery).getResultList();

        return otherBankList;
    }

    /**
     * Gets the records.
     *
     * @param verified the verified
     * @return the records
     */
    private List<GbmUserDetails> getRecords(final Boolean verified) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<GbmUserDetails> criteriaQuery = builder.createQuery(GbmUserDetails.class);
        final Root<GbmUserDetails> root = criteriaQuery.from(GbmUserDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(VERIFIED), verified),
                builder.equal(root.get(DEL_FLG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        final List<GbmUserDetails> otherBankDetailsList = session.createQuery(criteriaQuery).getResultList();
        return otherBankDetailsList;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.UserDetailsDao#getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from GbmUserDetails where verified = :verified and fileName is not null group by fileName",
                String.class);
        query.setParameter(VERIFIED, Boolean.FALSE);
        final List<String> fileNameList = query.list();
        return fileNameList;
    }

    @Override
    public List<String> getInquiryFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from GbmUserDetails where  fileName is not null group by fileName",
                String.class);
        //query.setParameter(VERIFIED, Boolean.FALSE);
        final List<String> fileNameList = query.list();
        return fileNameList;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.UserDetailsDao#getUnverifiedRecords()
     */
    @Override
    public List<GbmUserDetails> getUnverifiedRecords() {
        return this.getRecords(Boolean.FALSE);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.UserDetailsDao#getUnverifiedUserDetails(java.
     * lang.String)
     */
    @Override
    public List<GbmUserDetails> getUnverifiedUserDetails(final String fileName) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<GbmUserDetails> criteriaQuery = builder.createQuery(GbmUserDetails.class);
        final Root<GbmUserDetails> root = criteriaQuery.from(GbmUserDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(FILE_NAME), fileName));
        // Use criteria to query with session to fetch all accounts
        final List<GbmUserDetails> userDetailsList = session.createQuery(criteriaQuery).getResultList();
        return userDetailsList;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.UserDetailsDao#getUser(java.lang.String)
     */
    @Override
    public List<GbmUserDetails> getUser(final String userId) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<GbmUserDetails> criteriaQuery = builder.createQuery(GbmUserDetails.class);
        final Root<GbmUserDetails> root = criteriaQuery.from(GbmUserDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(USER_ID), userId));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).list();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.UserDetailsDao#getUserWorkDetails(java.lang.
     * String)
     */
    @Override
    public GbmUserDetails getUserWorkDetails(final Integer gbmUserDetailsId) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<GbmUserDetails> criteriaQuery = builder.createQuery(GbmUserDetails.class);
        final Root<GbmUserDetails> root = criteriaQuery.from(GbmUserDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), gbmUserDetailsId));
        // Use criteria to query with session to fetch all accounts
        final GbmUserDetails details = session.createQuery(criteriaQuery).getResultList()
                .stream().findFirst().orElse(null);
        return details;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.UserDetailsDao#getVerifiedRecords()
     */
    @Override
    public List<GbmUserDetails> getVerifiedRecords() {
        return this.getRecords(Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.UserDetailsDao#list()
     */
    @Override
    public List<GbmUserDetails> list() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<GbmUserDetails> criteriaQuery = builder.createQuery(GbmUserDetails.class);
        final Root<GbmUserDetails> root = criteriaQuery.from(GbmUserDetails.class);
        criteriaQuery.select(root);
        // Use criteria to query with session to fetch all accounts
        final List<GbmUserDetails> userDetailsList = session.createQuery(criteriaQuery).getResultList();

        return userDetailsList;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.UserDetailsDao#saveOrUpdate(com.isl.gbm.cbs.
     * integration.model.GbmUserDetails)
     */
    @Override
    public int saveOrUpdate(final GbmUserDetails userDetails) {
        final Session session = getSession();
        return (int) session.save(userDetails);
    }


    @Override
    public void update(final GbmUserDetails userDetails) {
        final Session session = getSession();
        session.merge(userDetails);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.UserDetailsDao#upload(java.util.List)
     */
    @Override
    public void upload(final List<GbmUserDetails> userDetailsList) {
        final Session session = getSession();
        for (final GbmUserDetails userDetails : userDetailsList) {
            session.saveOrUpdate(userDetails);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.UserDetailsDao#verify(java.lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final String fileName) {
        final Session session = getSession();
        final Query query = session
                .createQuery("update GbmUserDetails set verified = :verified ,verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where fileName = :fileName");
        query.setParameter(VERIFIED, Boolean.TRUE);
        query.setParameter(FILE_NAME, fileName);
        query.setParameter("verifiedBy", UserAccountUtils.getCurrentUsername());
        query.setParameter("verifiedDate", new Date());
        query.setParameter("auditable", Boolean.TRUE);
        query.executeUpdate();
    }

}
