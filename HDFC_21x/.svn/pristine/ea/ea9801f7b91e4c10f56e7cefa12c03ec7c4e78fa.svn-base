/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao.impl;

import com.isl.gbm.cbs.integration.common.ProcNameEnum;
import com.isl.gbm.cbs.integration.dao.BranchIfscDetailsDao;
import com.isl.gbm.cbs.integration.dto.BranchIfscDetailsDto;
import com.isl.gbm.cbs.integration.dto.CommonGenericTableDto;
import com.isl.gbm.cbs.integration.model.BranchIfscDetails;
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
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * The Class BranchIfscDetailsDaoImpl.
 */
@Repository
public class BranchIfscDetailsDaoImpl extends HibernateDao implements BranchIfscDetailsDao {


    private static final Logger log = LoggerFactory.getLogger(BranchIfscDetailsDaoImpl.class);
    /**
     * The Constant FILE_NAME.
     */
    private static final String FILE_NAME = "fileName";

    /**
     * The Constant VERIFIED.
     */
    private static final String VERIFIED = "verified";

    /**
     * The Constant DELETED.
     */
    private static final String DELETED = "deleted";

    /**
     * The Constant ID.
     */
    private static final String ID = "id";

    /**
     * The Constant BANK_CODE.
     */
    private static final String BANK_CODE = "bankCode";

    /**
     * The Constant BRANCH_CODE.
     */
    private static final String BRANCH_CODE = "branchCode";


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;
    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#delete(com.afl.gbm.
     * cbs. integration.model.BranchIfscDetails)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void delete(final BranchIfscDetails branchIfscDetails) {
        final Session session = getSession();
        final Query query = session.createQuery("update BranchIfscDetails set deleted = :deleted where id = :id");
        query.setParameter(DELETED, Boolean.TRUE);
        query.setParameter(ID, branchIfscDetails.getId());
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#delete(java.lang.
     * String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void delete(final String ifscFileName) {
        final Session session = getSession();
        final Query query = session.createQuery("delete from BranchIfscDetails where fileName = :fileName");
        query.setParameter(FILE_NAME, ifscFileName);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#fileNameExists(java.
     * lang .String)
     */
    @Override
    public boolean fileNameExists(final String ifscFileName) {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from BranchIfscDetails where fileName = :fileName group by fileName", String.class);
        query.setParameter(FILE_NAME, ifscFileName);
        final List<String> fileNameList = query.list();
        return CollectionUtils.size(fileNameList) > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#
     * getBranchIfscDetails( java.lang.Integer)
     */
    @Override
    public BranchIfscDetails getBranchIfscDetails(final Integer branchIfscDetailsId) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<BranchIfscDetails> criteriaQuery = builder.createQuery(BranchIfscDetails.class);
        final Root<BranchIfscDetails> root = criteriaQuery.from(BranchIfscDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), branchIfscDetailsId));
        // Use criteria to query with session to fetch all accounts
        final BranchIfscDetails details = session.createQuery(criteriaQuery).getResultList()
                .stream().findFirst().orElse(null);
        if (null != details) {
            session.evict(details);
        }
        return details;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#
     * getBranchIfscDetails( java.lang.String, java.lang.String)
     */
    @Override
    public List<BranchIfscDetails> getBranchIfscDetails(final String bankCode, final String branchCode) {

        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<BranchIfscDetails> criteriaQuery = builder.createQuery(BranchIfscDetails.class);
        final Root<BranchIfscDetails> root = criteriaQuery.from(BranchIfscDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(BANK_CODE), bankCode),
                builder.equal(root.get(BRANCH_CODE), branchCode), builder.equal(root.get(DELETED), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();

    }

    /**
     * Gets the records.
     *
     * @param verified the verified
     * @return the records
     */
    private List<BranchIfscDetails> getRecords(final Boolean verified) {

        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<BranchIfscDetails> criteriaQuery = builder.createQuery(BranchIfscDetails.class);
        final Root<BranchIfscDetails> root = criteriaQuery.from(BranchIfscDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(VERIFIED), verified),
                builder.equal(root.get(DELETED), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();

    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#
     * getUnverifiedBranchIfscDetails(java.lang.String)
     */
    @Override
    public List<BranchIfscDetails> getUnverifiedBranchIfscDetails(final String fileName) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<BranchIfscDetails> criteriaQuery = builder.createQuery(BranchIfscDetails.class);
        final Root<BranchIfscDetails> root = criteriaQuery.from(BranchIfscDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(FILE_NAME), fileName));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from BranchIfscDetails where verified = :verified and fileName is not null group by fileName",
                String.class);
        query.setParameter(VERIFIED, Boolean.FALSE);
        return query.list();
    }

    @Override
    public List<String> getInquiryFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from BranchIfscDetails where fileName is not null group by fileName",
                String.class);
        return query.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#getUnverifiedRecords
     * ()
     */
    @Override
    public List<BranchIfscDetails> getUnverifiedRecords() {
        return this.getRecords(Boolean.FALSE);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#getVerifiedRecords()
     */
    @Override
    public List<BranchIfscDetails> getVerifiedRecords() {
        return this.getRecords(Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#list()
     */
    @Override
    public List<BranchIfscDetails> list() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<BranchIfscDetails> criteriaQuery = builder.createQuery(BranchIfscDetails.class);
        final Root<BranchIfscDetails> root = criteriaQuery.from(BranchIfscDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(DELETED), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#saveOrUpdate(com.afl
     * .gbm .cbs.integration.model.BranchIfscDetails)
     */
    @Override
    public void saveOrUpdate(final BranchIfscDetails branchIfscDetails) {
        getSession().saveOrUpdate(branchIfscDetails);
    }
    @Override
    public ResponseDataDTO add(final BranchIfscDetailsDto branchIfscDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_IFSC_SP_ADD.getProcName());
                stmt.setString("p_enterer_id_in", UserAccountUtils.getCurrentUsername());
                stmt.setString("p_enterer_sol_id_in", UserAccountUtils.getCurrentSolId());
                stmt.setString("p_BANK_CODE_in", branchIfscDetailsDto.getBankCode());
                stmt.setString("p_BRANCH_CODE_in", branchIfscDetailsDto.getBranchCode());
                stmt.setString("p_BANK_IDENTIFIER_in", branchIfscDetailsDto.getBankIdentifier());
                stmt.setString("p_PAYSYS_ID_in", branchIfscDetailsDto.getPaysysId());
                stmt.setString("p_ADDL_TEXT1_in", branchIfscDetailsDto.getAdditionalText1());
                stmt.setString("p_ADDL_TEXT2_in", branchIfscDetailsDto.getAdditionalText2());
                stmt.setString("p_ADDL_TEXT3_in", branchIfscDetailsDto.getAdditionalText3());
                stmt.registerOutParameter("p_msg_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_desc_out", Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString("p_msg_desc_out"));
                resultMsg = stmt.getString("p_msg_desc_out");
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
    public ResponseDataDTO modify(final BranchIfscDetailsDto branchIfscDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        BranchIfscDetailsDto branchIfscDetailsDto1 = null;
        List<BranchIfscDetailsDto> branchIfscDetailsDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_IFSC_SP_MOD_SEL.getProcName());
                stmt.setString("p_modifier_id_in", UserAccountUtils.getCurrentUsername());
                stmt.setString("p_modifier_sol_id_in", UserAccountUtils.getCurrentSolId());
                stmt.setString("p_bank_code_in", branchIfscDetailsDto.getBankCode());
                stmt.setString("p_branch_code_in", branchIfscDetailsDto.getBranchCode());
                stmt.registerOutParameter("p_cursor_val_out", OracleTypes.CURSOR);
                stmt.registerOutParameter("p_msg_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_desc_out", Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString("p_msg_desc_out"));
                resultMsg = stmt.getString("p_msg_desc_out");
                ResultSet resultSet = null;
                if (stmt.getString("p_msg_desc_out")  != null && stmt.getString("p_msg_desc_out").equals("SUCCESS")) {

                    if(branchIfscDetailsDto.getBankCode() == null) {
                        resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                branchIfscDetailsDto1 = new BranchIfscDetailsDto();
                                branchIfscDetailsDto1.setBankCode(resultSet.getString("BANK_CODE"));
                                branchIfscDetailsDtoList.add(branchIfscDetailsDto1);
                            }
                            }
                        }
                    else if(branchIfscDetailsDto.getBranchCode() ==null){
                        resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                branchIfscDetailsDto1 = new BranchIfscDetailsDto();
                                branchIfscDetailsDto1.setBranchCode(resultSet.getString("BRANCH_CODE"));
                                branchIfscDetailsDtoList.add(branchIfscDetailsDto1);
                            }
                        }
                    }
                    else {
                        resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                branchIfscDetailsDto1 = new BranchIfscDetailsDto();
                                branchIfscDetailsDto1.setBranchDetailsId(Integer.valueOf(resultSet.getString("ID")));
                                branchIfscDetailsDto1.setBankCode(resultSet.getString("BANK_CODE"));
                                branchIfscDetailsDto1.setBranchCode(resultSet.getString("BRANCH_CODE"));
                                branchIfscDetailsDto1.setBankIdentifier(resultSet.getString("BANK_IDENTIFIER"));
                                branchIfscDetailsDto1.setPaysysId(resultSet.getString("PAYSYS_ID"));
                                branchIfscDetailsDto1.setCreatedBy(resultSet.getString("CREATED_BY"));
                                branchIfscDetailsDto1.setCreatDate(resultSet.getString("CREATED_DATE"));
                                branchIfscDetailsDto1.setModifiedBy(resultSet.getString("MODIFIED_BY"));
                                branchIfscDetailsDto1.setModDate(resultSet.getString("MODIFIED_DATE"));
                                branchIfscDetailsDto1.setIsVerified(resultSet.getString("IS_VERIFIED"));
                                branchIfscDetailsDto1.setVerifiedBy(resultSet.getString("VERIFIED_BY"));
                                branchIfscDetailsDto1.setVerDate(resultSet.getString("VERIFIED_DATE"));
                                branchIfscDetailsDto1.setFileName(resultSet.getString("FILE_NAME"));
                                branchIfscDetailsDto1.setAdditionalText1(resultSet.getString("ADDL_TEXT1"));
                                branchIfscDetailsDto1.setAdditionalText2(resultSet.getString("ADDL_TEXT2"));
                                branchIfscDetailsDto1.setAdditionalText3(resultSet.getString("ADDL_TEXT3"));
                                branchIfscDetailsDtoList.add(branchIfscDetailsDto1);
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
        responseDataDTO.setResult(branchIfscDetailsDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO modifySubmit(final CommonGenericTableDto commonGenericTableDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_IFSC_SP_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, String.valueOf(commonGenericTableDto.getPkColumnValue()));
                stmt.setArray(index++, generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection,commonGenericTableDto.getColumnValues()));
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
    public ResponseDataDTO inquiry(final BranchIfscDetailsDto branchIfscDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        BranchIfscDetailsDto branchIfscDetailsDto1 = null;
        List<BranchIfscDetailsDto> branchIfscDetailsDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_IFSC_SP_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, branchIfscDetailsDto.getBankCode());
                stmt.setString(index++, branchIfscDetailsDto.getBranchCode());
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

                    if(branchIfscDetailsDto.getBankCode() == null) {
                        resultSet = (ResultSet) stmt.getObject(12);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                branchIfscDetailsDto1 = new BranchIfscDetailsDto();
                                branchIfscDetailsDto1.setBankCode(resultSet.getString(1));
                                branchIfscDetailsDtoList.add(branchIfscDetailsDto1);
                            }
                        }
                    }
                    else if(branchIfscDetailsDto.getBranchCode() ==null){
                        resultSet = (ResultSet) stmt.getObject(12);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                branchIfscDetailsDto1 = new BranchIfscDetailsDto();
                                branchIfscDetailsDto1.setBranchCode(resultSet.getString(1));
                                branchIfscDetailsDtoList.add(branchIfscDetailsDto1);
                            }
                        }
                    }
                    else {
                        branchIfscDetailsDto1 = new BranchIfscDetailsDto();
                        branchIfscDetailsDto1.setBranchIfscDetailsOldNewValue(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(12)));
                        branchIfscDetailsDto1.setCreatedBy(stmt.getString(4));
                        branchIfscDetailsDto1.setCreatDate(stmt.getString(5));
                        branchIfscDetailsDto1.setModifiedBy(stmt.getString(6));
                        branchIfscDetailsDto1.setModDate(stmt.getString(7));
                        branchIfscDetailsDto1.setVerifiedBy(stmt.getString(8));
                        branchIfscDetailsDto1.setVerDate(stmt.getString(9));
                        branchIfscDetailsDto1.setPreviousOperationType(stmt.getString(10));
                        branchIfscDetailsDto1.setIsVerified(stmt.getString(11));
                        branchIfscDetailsDtoList.add(branchIfscDetailsDto1);
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
        responseDataDTO.setResult(branchIfscDetailsDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(final BranchIfscDetailsDto branchIfscDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        BranchIfscDetailsDto branchIfscDetailsDto1 = null;
        List<BranchIfscDetailsDto> branchIfscDetailsDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_IFSC_SP_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, branchIfscDetailsDto.getBankCode());
                stmt.setString(index++, branchIfscDetailsDto.getBranchCode());
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

                    if(branchIfscDetailsDto.getBankCode() == null) {
                        resultSet = (ResultSet) stmt.getObject(14);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                branchIfscDetailsDto1 = new BranchIfscDetailsDto();
                                branchIfscDetailsDto1.setBankCode(resultSet.getString(1));
                                branchIfscDetailsDtoList.add(branchIfscDetailsDto1);
                            }
                        }
                    }
                    else if(branchIfscDetailsDto.getBranchCode() ==null){
                        resultSet = (ResultSet) stmt.getObject(14);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                branchIfscDetailsDto1 = new BranchIfscDetailsDto();
                                branchIfscDetailsDto1.setBranchCode(resultSet.getString(1));
                                branchIfscDetailsDtoList.add(branchIfscDetailsDto1);
                            }
                        }
                    }
                    else {
                        branchIfscDetailsDto1 = new BranchIfscDetailsDto();
                        branchIfscDetailsDto1.setBranchIfscDetailsOldNewValue(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));
                        branchIfscDetailsDto1.setBranchDetailsId(Integer.valueOf(stmt.getString(5)));
                        branchIfscDetailsDto1.setCreatedBy(stmt.getString(6));
                        branchIfscDetailsDto1.setCreatDate(stmt.getString(7));
                        branchIfscDetailsDto1.setModifiedBy(stmt.getString(8));
                        branchIfscDetailsDto1.setModDate(stmt.getString(9));
                        branchIfscDetailsDto1.setVerifiedBy(stmt.getString(10));
                        branchIfscDetailsDto1.setVerDate(stmt.getString(11));
                        branchIfscDetailsDto1.setPreviousOperationType(stmt.getString(12));
                        branchIfscDetailsDto1.setIsVerified(stmt.getString(13));
                        branchIfscDetailsDtoList.add(branchIfscDetailsDto1);
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
        responseDataDTO.setResult(branchIfscDetailsDtoList);
        return responseDataDTO;

    }
    @Override
    public ResponseDataDTO verifySubmit(final CommonGenericTableDto commonGenericTableDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
           try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_IFSC_SP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, String.valueOf(commonGenericTableDto.getPkColumnValue()));
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
    public ResponseDataDTO deleteSubmit(final CommonGenericTableDto commonGenericTableDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_IFSC_SP_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, String.valueOf(commonGenericTableDto.getPkColumnValue()));
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
    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#upload(java.util.
     * List)
     */
    @Override
    public void upload(final List<BranchIfscDetails> branchIfscDetailsList) {
        final Session session = getSession();
        for (final BranchIfscDetails ifscDetails : branchIfscDetailsList) {
            session.save(ifscDetails);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#verify(com.afl.gbm.
     * cbs. integration.model.BranchIfscDetails)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final BranchIfscDetails branchIfscDetails) {
        final Session session = getSession();
        final String HQL = "update BranchIfscDetails set verified = :verified, verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where id = :id";
        final Query query = session.createQuery(HQL);
        query.setParameter(VERIFIED, Boolean.TRUE);
        query.setParameter("verifiedBy", branchIfscDetails.getVerifiedBy());
        query.setParameter("verifiedDate", branchIfscDetails.getVerifiedDate());
        query.setParameter("auditable", Boolean.TRUE);
        query.setParameter("id", branchIfscDetails.getId());
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.dao.BranchIfscDetailsDao#verify(java.lang.
     * String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final String fileName) {
        final Session session = getSession();
        final Query query = session
                .createQuery("update BranchIfscDetails set verified = :verified ,verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where fileName = :fileName");
        query.setParameter(VERIFIED, Boolean.TRUE);
        query.setParameter(FILE_NAME, fileName);
        query.setParameter("verifiedBy", UserAccountUtils.getCurrentUsername());
        query.setParameter("verifiedDate", new Date());
        query.setParameter("auditable", Boolean.TRUE);
        query.executeUpdate();
    }





}
