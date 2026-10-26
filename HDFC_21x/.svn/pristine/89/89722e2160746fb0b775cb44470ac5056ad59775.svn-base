/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao.impl;

import com.isl.gbm.cbs.integration.common.ProcNameEnum;

import com.isl.gbm.cbs.integration.dao.SchemeCodeDao;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.model.SchemeCodes;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
//import com.isl.gbm.stateTax.util.ProcNameEnum;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * The Class SchemeCodeDaoImpl.
 */
@Repository

public class SchemeCodeDaoImpl extends HibernateDao implements SchemeCodeDao {

    /**
     * The Constant FILE_NAME.
     */
    private static final String FILE_NAME = "fileName";

    /**
     * The Constant VERIFIED.
     */
    private static final String VERIFIED = "isVerified";

    /**
     * The Constant DELETE_FLAG.
     */
    private static final String DELETE_FLAG = "deleteFlag";

    /**
     * The Constant ID.
     */
    private static final String ID = "id";

    /**
     * The Constant SCHEME_CODE.
     */
    private static final String SCHEME_CODE = "schemeCode";

    /**
     * The Constant SCHEME_TYPE.
     */
    private static final String SCHEME_TYPE = "schemeType";

    private static String schemeCode = "schemCode";
    private static String schemeDescription = "schemeDescription";
    private static String glSubHeadCode = "glSubHeadCode";
    private static String schemeType = "schemeType";

    private static final Logger log = LoggerFactory.getLogger(SolDateDaoImpl.class);

    /**
     * The session factory.
     */
    private SessionFactory sessionFactory;

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.SchemeCodeDao#buildDetails(java.util.
     * List)
     */
    @Override
    public void buildDetails(final List<SchemeCodes> schemeCodesList) {
        final Session session = getSession();
        for (final SchemeCodes schemeCodes : schemeCodesList) {
            session.saveOrUpdate(schemeCodes);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.SchemeCodesDao#delete(com.isl.gbm.cbs.
     * integration.model.SchemeCodes)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void delete(final SchemeCodes codes) {
        final Session session = getSession();
        final Query query = session.createQuery("update SchemeCodes set deleteFlag = :deleteFlag where id = :id");
        query.setParameter(DELETE_FLAG, Boolean.TRUE);
        query.setParameter(ID, codes.getId());
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.SchemeCodeDao#deleteSchemeCodes(java.
     * lang. String)
     */
    @Override
    public void deleteSchemeCodes(final String fileName) {
        final Session session = getSession();
        final Query query = session.createQuery("delete from SchemeCodes where fileName = :fileName");
        query.setParameter(FILE_NAME, fileName);
        query.executeUpdate();

    }


    @Override
    public void deleteSchemeCodesId(Integer id) {
        final Session session = getSession();
        final Query query = session.createQuery("delete from SchemeCodes where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.SchemeCodeDao#fileNameExists(java.lang.
     * String)
     */
    @Override
    public boolean fileNameExists(final String schemeFileName) {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from SchemeCodes where fileName = :fileName group by fileName", String.class);
        query.setParameter(FILE_NAME, schemeFileName);
        final List<String> fileNameList = query.list();
        return CollectionUtils.size(fileNameList) > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.SchemeCodesDao#getAll()
     */
    @Override
    public List<SchemeCodes> getAllList() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<SchemeCodes> criteriaQuery = builder.createQuery(SchemeCodes.class);
        final Root<SchemeCodes> root = criteriaQuery.from(SchemeCodes.class);
        criteriaQuery.select(root).where(builder.equal(root.get(DELETE_FLAG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.SchemeCodeDao#getGlSubHeadList(java.lang.
     * String, java.lang.String, java.lang.String)
     */
    @Override
    public List<SchemeCodes> getGlSubHeadList(final String schemeType, final String schemeCode,
                                              final String glSubHeadCode) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<SchemeCodes> criteriaQuery = builder.createQuery(SchemeCodes.class);
        final Root<SchemeCodes> root = criteriaQuery.from(SchemeCodes.class);
        criteriaQuery.select(root).where(builder.equal(root.get(SCHEME_TYPE), schemeType),
                builder.equal(root.get(SCHEME_CODE), schemeCode), builder.equal(root.get(DELETE_FLAG), Boolean.FALSE),
                builder.like(root.get("glSubHeadCode"), glSubHeadCode + "%"));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Gets the records.
     *
     * @param verified the verified
     * @return the records
     */
    private List<SchemeCodes> getRecords(final Boolean verified) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<SchemeCodes> criteriaQuery = builder.createQuery(SchemeCodes.class);
        final Root<SchemeCodes> root = criteriaQuery.from(SchemeCodes.class);
        criteriaQuery.select(root).where(builder.equal(root.get(VERIFIED), verified),
                builder.equal(root.get(DELETE_FLAG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.SchemeCodesDao#getSchemeCodes(
     * java.lang.Integer)
     */
    @Override
    public SchemeCodes getSchemeCodes(final Integer id) {
        final Session session = getSession();
        final SchemeCodes codes = session.get(SchemeCodes.class, id);
        if (null != codes) {
            session.evict(codes);
        }
        return codes;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.SchemeCodesDao#getSchemeCodes(
     * java.lang.String, java.lang.String)
     */
    @Override
    public List<SchemeCodes> getSchemeCodes(final String schemeType, final String schemeCode) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<SchemeCodes> criteriaQuery = builder.createQuery(SchemeCodes.class);
        final Root<SchemeCodes> root = criteriaQuery.from(SchemeCodes.class);
        criteriaQuery.select(root).where(builder.equal(root.get(SCHEME_TYPE), schemeType),
                builder.equal(root.get(SCHEME_CODE), schemeCode));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.SchemeCodeDao#getSchemeCodesList()
     */
    @Override
    public List<SchemeCodes> getSchemeCodesList() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<SchemeCodes> criteriaQuery = builder.createQuery(SchemeCodes.class);
        final Root<SchemeCodes> schemeCodesRoot = criteriaQuery.from(SchemeCodes.class);
        criteriaQuery.select(schemeCodesRoot);
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.SchemeCodeDao#getSchemeCodesList(java.
     * lang. String, java.lang.String)
     */
    @Override
    public List<SchemeCodes> getSchemeCodesList(final String schemeType, final String schemeCode) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<SchemeCodes> criteriaQuery = builder.createQuery(SchemeCodes.class);
        final Root<SchemeCodes> root = criteriaQuery.from(SchemeCodes.class);
        criteriaQuery.select(root).where(builder.equal(root.get(SCHEME_TYPE), schemeType),
                builder.equal(root.get(DELETE_FLAG), Boolean.FALSE),
                builder.like(root.get(SCHEME_CODE), schemeCode + "%"));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.SchemeCodeDao#getSchemeTypeList()
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<String> getSchemeTypeList() {
        final String HQL = " select distinct schemeType from SchemeCodes where deleteFlag= :deleteFlag ";
        final Session session = getSession();
        final Query query = session.createQuery(HQL);
        query.setParameter(DELETE_FLAG, Boolean.FALSE);
        return query.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.SchemeCodeDao#getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from SchemeCodes where isVerified = :isVerified and fileName is not null group by fileName",
                String.class);
        query.setParameter(VERIFIED, Boolean.FALSE);
        return query.list();
    }

    @Override
    public List<String> getInquiryFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from SchemeCodes where fileName is not null group by fileName",
                String.class);
        return query.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.SchemeCodesDao#getUnverifiedRecords()
     */
    @Override
    public List<SchemeCodes> getUnverifiedRecords() {
        return this.getRecords(Boolean.FALSE);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.SchemeCodeDao#
     * getUnverifiedSchemeCodesDetails (java.lang.String)
     */
    @Override
    public List<SchemeCodes> getUnverifiedSchemeCodesDetails(final String fileName) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<SchemeCodes> criteriaQuery = builder.createQuery(SchemeCodes.class);
        final Root<SchemeCodes> root = criteriaQuery.from(SchemeCodes.class);
        criteriaQuery.select(root).where(builder.equal(root.get(FILE_NAME), fileName));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.SchemeCodesDao#getVerifiedRecords()
     */
    @Override
    public List<SchemeCodes> getVerifiedRecords() {
        return this.getRecords(Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.SchemeCodesDao#saveOrUpdate(com.isl.gbm
     * .cbs.integration.model.SchemeCodes)
     */
    @Override

    public void saveOrUpdate(final SchemeCodes schemeCodes) {
        final Session session = getSession();
        session.saveOrUpdate(schemeCodes);
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.SchemeCodeDao#verify(com.isl.gbm.cbs.
     * integration.model.SchemeCodes)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final SchemeCodes schemeCodes) {
        final Session session = getSession();
        final String HQL = "update SchemeCodes set isVerified = :verified, verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where id = :id";
        final Query query = session.createQuery(HQL);
        query.setParameter("verified", schemeCodes.isVerified());
        query.setParameter("verifiedBy", schemeCodes.getVerifiedBy());
        query.setParameter("verifiedDate", schemeCodes.getVerifiedDate());
        query.setParameter("auditable", schemeCodes.getAuditable());
        query.setParameter(ID, schemeCodes.getId());
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.SchemeCodeDao#verifySchemeCodes(java.lang
     * .String)
     */
    @Override
    public void verifySchemeCodes(final String fileName) {
        final Session session = getSession();
        final Query query = session
                .createQuery("update SchemeCodes set isVerified = :isVerified,verifiedBy=:verifiedBy,verifiedDate=:verifiedDate,auditable=:auditable where fileName = :fileName");
        query.setParameter(VERIFIED, Boolean.TRUE);
        query.setParameter(FILE_NAME, fileName);
        query.setParameter("verifiedBy", UserAccountUtils.getCurrentUsername());
        query.setParameter("verifiedDate", new Date());
        query.setParameter("auditable", Boolean.TRUE);
        query.executeUpdate();

    }

    @Override
    public ResponseDataDTO saveDetailsSchemeCodes(SchemeCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_cbs_schm_codes_add.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getSchemeCode());
                stmt.setString(index++, dto.getDescription());
                stmt.setString(index++, dto.getGlSubHeadCode());
                stmt.setString(index++, dto.getSchemeType());
                stmt.setString(index++, dto.getAddText1());
                stmt.setString(index++, dto.getAddText2());
                stmt.setString(index++, dto.getAddText3());
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
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiry(SchemeCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        SchemeCodesDto output = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_cbs_schm_codes_inq.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getSchemeCode());
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
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if ((stmt.getString(index - 1)) != null && (CommonConstants.SUCCESS).equalsIgnoreCase(stmt.getString(index - 1))) {

                    if (dto.getSchemeCode() == null) {

                        rs = (ResultSet) stmt.getObject(11);
                        output = new SchemeCodesDto();
                        List<HashMap<String, String>> taxCode = null;
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            taxCode = new ArrayList<>();
                            while (rs.next()) {
                                temp = new HashMap<>();
                                temp.put(schemeCode, rs.getString(1));
                                temp.put(schemeDescription, rs.getString(2));
                                temp.put(glSubHeadCode, rs.getString(3));
                                temp.put(schemeType, rs.getString(4));
                                taxCode.add(temp);
                            }
                            output.setSchemeName(taxCode);

                        }
                    } else if (dto.getSchemeCode() != null ){
                        output = new SchemeCodesDto();
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(11)));
                        output.setCreatedBy(stmt.getString(3));
                        output.setCreatedDate1(stmt.getString(4));
                        output.setModifiedBy(stmt.getString(5));
                        output.setModifiedDate1(stmt.getString(6));
                        output.setVerifiedBy(stmt.getString(7));
                        output.setVerifiedDate1(stmt.getString(8));
                        output.setPrevOperationType(stmt.getString(9));
                        output.setVerifiedStatus(stmt.getString(10));

                    }
                }



            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(output);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO verDelDetails(SchemeCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        SchemeCodesDto output = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_cbs_schm_codes_ver_sel.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getSchemeCode());
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
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if ((stmt.getString(index - 1)) != null && (CommonConstants.SUCCESS).equalsIgnoreCase(stmt.getString(index - 1))) {

                    if (dto.getSchemeCode() == null) {

                        rs = (ResultSet) stmt.getObject(13);
                        output = new SchemeCodesDto();
                        List<HashMap<String, String>> taxCode = null;
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            taxCode = new ArrayList<>();
                            while (rs.next()) {
                                temp = new HashMap<>();
                                temp.put(schemeCode, rs.getString(1));
                                temp.put(schemeDescription, rs.getString(2));
                                temp.put(glSubHeadCode, rs.getString(3));
                                temp.put(schemeType, rs.getString(4));
                                taxCode.add(temp);
                            }
                            output.setSchemeName(taxCode);

                        }
                    } else if (dto.getSchemeCode() != null ){
                        output = new SchemeCodesDto();
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(13)));
                        output.setId1(stmt.getString(4));
                        output.setCreatedBy(stmt.getString(5));
                        output.setCreatedDate1(stmt.getString(6));
                        output.setModifiedBy(stmt.getString(7));
                        output.setModifiedDate1(stmt.getString(8));
                        output.setVerifiedBy(stmt.getString(9));
                        output.setVerifiedDate1(stmt.getString(10));
                        output.setPrevOperationType(stmt.getString(11));
                        output.setVerifiedStatus(stmt.getString(12));

                    }
                }



            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(output);
        return responseDataDTO;

    }


    @Override
    public ResponseDataDTO modifyDetails(SchemeCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        SchemeCodesDto output = null;
        List<SchemeCodesDto> modifyList = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_cbs_schm_codes_mod_sel.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if ((stmt.getString(index - 1)) != null && (CommonConstants.SUCCESS).equalsIgnoreCase(stmt.getString(index - 1))) {

                    if (dto.getSchemeCode() == null) {

                        rs = (ResultSet) stmt.getObject(4);
                        output = new SchemeCodesDto();
                        List<HashMap<String, String>> taxCode = null;
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            taxCode = new ArrayList<>();
                            while (rs.next()) {
                                temp = new HashMap<>();
                                temp.put(schemeCode, rs.getString(1));
                                temp.put(schemeDescription, rs.getString(2));
                                temp.put(glSubHeadCode, rs.getString(3));
                                temp.put(schemeType, rs.getString(4));
                                taxCode.add(temp);
                            }
                            output.setSchemeName(taxCode);

                        }
                    }else if (dto.getSchemeCode() != null) {

                        rs = (ResultSet) stmt.getObject(4);

                        if (rs != null) {
                            modifyList = new ArrayList<>();

                            while (rs.next()) {

                                output = new SchemeCodesDto();
                                output.setId1(rs.getString(1));
                                output.setCreatedBy(rs.getString(6));
                                output.setCreatedDate1(rs.getString(7));
                                output.setModifiedBy(rs.getString(8));
                                output.setModifiedDate1(rs.getString(9));
                                output.setVerifiedStatus(rs.getString(10));
                                output.setVerifiedBy(rs.getString(11));
                                output.setVerifiedDate1(rs.getString(12));
                                output.setAddText1(rs.getString(14));
                                output.setAddText2(rs.getString(15));
                                output.setAddText3(rs.getString(16));




                            }
                            modifyList.add(output);
                        }
                    }


                }

            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(output);
        return responseDataDTO;

    }



    @Override
    public ResponseDataDTO modify(SchemeCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_cbs_schm_codes_mod.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getId1());
                stmt.setString(index++, dto.getAddText1());
                stmt.setString(index++, dto.getAddText2());
                stmt.setString(index++, dto.getAddText3());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO verifySubmit(SchemeCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_cbs_schm_codes_ver.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getId1());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO deleteSubmit(SchemeCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_cbs_schm_codes_del.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getId1());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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

}
