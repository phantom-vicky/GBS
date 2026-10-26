/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao.impl;

import com.isl.gbm.cbs.integration.common.ProcNameEnum;

import com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao;
import com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto;
import com.isl.gbm.cbs.integration.model.OfficeAccountPlaceHolderMaster;
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
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
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
 * The Class OfficeAccountPlaceHolderDaoImpl.
 */
@Repository
public class OfficeAccountPlaceHolderDaoImpl extends HibernateDao implements OfficeAccountPlaceHolderDao {

    /**
     * The Constant DEL_FLG.
     */
    private static final String DEL_FLG = "delFlg";

    /**
     * The Constant FILE_NAME.
     */
    private static final String FILE_NAME = "filename";

    private static String bacId = "bacId";
    private static String bacIdDescription = "bacIdDescription";
    private static String schemeType = "schemeType";


    /**
     * The Constant IS_VERIFIED.
     */
    private static final String IS_VERIFIED = "isVerified";

    private static final Logger log = LoggerFactory.getLogger(OfficeAccountPlaceHolderDaoImpl.class);


    @PersistenceContext
    EntityManager em;


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#
     * createOfficeAccountPlaceHolder(java.util.List)
     */
    @Override
    public void createOfficeAccountPlaceHolder(final List<OfficeAccountPlaceHolderMaster> officeAccountPlaceHolder) {


        final Session session = getSession();
        for (final OfficeAccountPlaceHolderMaster offAccPlaceHolderMaster : officeAccountPlaceHolder) {
            session.saveOrUpdate(offAccPlaceHolderMaster);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#delete(com.
     * isl.gbm.cbs.integration.model.OfficeAccountPlaceHolderMaster)
     */
    @Override
    public void delete(final Integer id) {

        final Session session = getSession();
        final Query query = session
                .createQuery("update OfficeAccountPlaceHolderMaster set delFlg = :delFlg where id = :id");
        query.setParameter(DEL_FLG, Boolean.TRUE);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#delete(java.
     * lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void delete(final String officeFileName) {
        final Session session = getSession();
        final Query query = session
                .createQuery("delete from OfficeAccountPlaceHolderMaster where filename = :filename");
        query.setParameter(FILE_NAME, officeFileName);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#
     * fileNameExists(java.lang.String)
     */
    @Override
    public boolean fileNameExists(final String officeFileName) {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select filename from OfficeAccountPlaceHolderMaster where filename = :filename group by filename",
                String.class);
        query.setParameter(FILE_NAME, officeFileName);
        final List<String> fileNameList = query.list();
        return CollectionUtils.size(fileNameList) > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#
     * getOfficeAccountPlaceHolderDetails(java.lang.Integer)
     */
    @Override
    public OfficeAccountPlaceHolderMaster getOfficeAccountPlaceHolderDetails(final Integer officeId) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<OfficeAccountPlaceHolderMaster> criteriaQuery = builder
                .createQuery(OfficeAccountPlaceHolderMaster.class);
        final Root<OfficeAccountPlaceHolderMaster> root = criteriaQuery.from(OfficeAccountPlaceHolderMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get("id"), officeId),
                builder.equal(root.get(DEL_FLG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        final OfficeAccountPlaceHolderMaster master = em.createQuery(criteriaQuery).getResultList()
                .stream().findFirst().orElse(null);
        if (null != master) {
            em.detach(master);
        }
        return master;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#
     * getOfficeAccountPlaceHolderDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<OfficeAccountPlaceHolderMaster> getOfficeAccountPlaceHolderDetails(final String bacid) {

        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<OfficeAccountPlaceHolderMaster> criteriaQuery = builder
                .createQuery(OfficeAccountPlaceHolderMaster.class);
        final Root<OfficeAccountPlaceHolderMaster> root = criteriaQuery.from(OfficeAccountPlaceHolderMaster.class);
        criteriaQuery.select(root).where(builder.like(root.get("bacid"), bacid + "%"),
                builder.equal(root.get(DEL_FLG), Boolean.FALSE));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#
     * getOfficeAccountPlaceHolderDetailsForList(ArrayList)
     */
    @Override
    public List<OfficeAccountPlaceHolderMaster> getOfficeAccountPlaceHolderDetailsForList(
            final List<String> bacidList) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<OfficeAccountPlaceHolderMaster> criteriaQuery = builder
                .createQuery(OfficeAccountPlaceHolderMaster.class);
        final Root<OfficeAccountPlaceHolderMaster> root = criteriaQuery.from(OfficeAccountPlaceHolderMaster.class);

        final Expression<String> tranIdExpression = root.get("bacid");
        final Predicate tranIdPredicate = tranIdExpression.in(bacidList);
        criteriaQuery.where(tranIdPredicate).where(builder.equal(root.get(DEL_FLG), Boolean.FALSE));

        return em.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Gets the records.
     *
     * @param verified the verified
     * @return the records
     */
    private List<OfficeAccountPlaceHolderMaster> getRecords(final Boolean verified) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<OfficeAccountPlaceHolderMaster> criteriaQuery = builder
                .createQuery(OfficeAccountPlaceHolderMaster.class);
        final Root<OfficeAccountPlaceHolderMaster> root = criteriaQuery.from(OfficeAccountPlaceHolderMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(IS_VERIFIED), verified),
                builder.equal(root.get(DEL_FLG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        return em.createQuery(criteriaQuery).getResultList();

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#
     * getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select filename from OfficeAccountPlaceHolderMaster where isVerified = :isVerified and filename is not null group by filename",
                String.class);
        query.setParameter(IS_VERIFIED, Boolean.FALSE);
        return query.list();

    }

    @Override
    public List<String> getInquiryFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select filename from OfficeAccountPlaceHolderMaster where filename is not null group by filename",
                String.class);
        return query.list();

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#
     * getUnverifiedPlaceHolders(java.lang.String)
     */
    @Override
    public List<OfficeAccountPlaceHolderMaster> getUnverifiedPlaceHolders(final String fileName) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<OfficeAccountPlaceHolderMaster> criteriaQuery = builder
                .createQuery(OfficeAccountPlaceHolderMaster.class);
        final Root<OfficeAccountPlaceHolderMaster> root = criteriaQuery.from(OfficeAccountPlaceHolderMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(FILE_NAME), fileName));
        return em.createQuery(criteriaQuery).getResultList();

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#
     * getUnverifiedRecords()
     */
    @Override
    public List<OfficeAccountPlaceHolderMaster> getUnverifiedRecords() {
        return this.getRecords(Boolean.FALSE);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#
     * getVerifiedRecords()
     */
    @Override
    public List<OfficeAccountPlaceHolderMaster> getVerifiedRecords() {
        return this.getRecords(Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#list()
     */
    @Override
    public List<OfficeAccountPlaceHolderMaster> list() {

        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<OfficeAccountPlaceHolderMaster> criteriaQuery = builder
                .createQuery(OfficeAccountPlaceHolderMaster.class);
        final Root<OfficeAccountPlaceHolderMaster> root = criteriaQuery.from(OfficeAccountPlaceHolderMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(DEL_FLG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        return em.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#recordList()
     */
    @Override
    public List<OfficeAccountPlaceHolderMaster> recordList() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<OfficeAccountPlaceHolderMaster> criteriaQuery = builder
                .createQuery(OfficeAccountPlaceHolderMaster.class);
        final Root<OfficeAccountPlaceHolderMaster> root = criteriaQuery.from(OfficeAccountPlaceHolderMaster.class);
        criteriaQuery.select(root);
        return em.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#saveOrUpdate(
     * com.isl.gbm.cbs.integration.model.OfficeAccountPlaceHolderMaster)
     */
    @Override
    public void saveOrUpdate(final OfficeAccountPlaceHolderMaster masterDetails) {
        final Session session = getSession();
        session.saveOrUpdate(masterDetails);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#verify(com.
     * isl.gbm.cbs.integration.model.OfficeAccountPlaceHolderMaster)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final OfficeAccountPlaceHolderMaster master) {
        final Session session = getSession();
        final String HQL = "update OfficeAccountPlaceHolderMaster set isVerified = :verified, verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where id = :id";
        final Query query = session.createQuery(HQL);
        query.setParameter("verified", master.isVerified());
        query.setParameter("verifiedBy", master.getVerifiedBy());
        query.setParameter("verifiedDate", master.getVerifiedDate());
        query.setParameter("auditable", master.isAuditable());
        query.setParameter("id", master.getId());
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao#verify(java.
     * lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final String fileName) {
        final Session session = getSession();
        final Query query = session.createQuery(
                "update OfficeAccountPlaceHolderMaster set isVerified = :isVerified ,verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where fileName = :fileName");
        query.setParameter(IS_VERIFIED, Boolean.TRUE);
        query.setParameter("fileName", fileName);
        query.setParameter("verifiedBy", UserAccountUtils.getCurrentUsername());
        query.setParameter("verifiedDate", new Date());
        query.setParameter("auditable", Boolean.TRUE);
        query.executeUpdate();
    }

    @Override
    public ResponseDataDTO create(OfficeAccountPlaceHolderDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_cbs_ofyc_acct_pl_hldr_add.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getBacid());
                stmt.setString(index++, dto.getBacidDesc());
                stmt.setString(index++, dto.getSchmType());
                stmt.setString(index++, dto.getAdditionalText1());
                stmt.setString(index++, dto.getAdditionalText2());
                stmt.setString(index++, dto.getAdditionalText3());
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
    public ResponseDataDTO inquiry(OfficeAccountPlaceHolderDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        OfficeAccountPlaceHolderDto output = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum. pkg_gbm_cbs_ofyc_acct_pl_hldr_inq.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getBacid());
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

                    if (dto.getBacid() == null) {

                        rs = (ResultSet) stmt.getObject(11);
                        output = new OfficeAccountPlaceHolderDto();
                        List<HashMap<String, String>> taxCode = null;
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            taxCode = new ArrayList<>();
                            while (rs.next()) {
                                temp = new HashMap<>();
                                temp.put(bacId, rs.getString(1));
                                temp.put(bacIdDescription, rs.getString(2));
                                temp.put(schemeType, rs.getString(3));
                                taxCode.add(temp);
                            }
                            output.setSchemeName(taxCode);

                        }
                    } else if (dto.getBacid() != null ){
                        output = new OfficeAccountPlaceHolderDto();
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(11)));
                        output.setCreatedBy(stmt.getString(3));
                        output.setCreatedDate1(stmt.getString(4));
                        output.setLastModifiedBy(stmt.getString(5));
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
    public ResponseDataDTO verDelDetails(OfficeAccountPlaceHolderDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        OfficeAccountPlaceHolderDto output = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum. pkg_gbm_cbs_ofyc_acct_pl_hldr_ver_sel.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getBacid());
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

                    if (dto.getBacid() == null) {

                        rs = (ResultSet) stmt.getObject(13);
                        output = new OfficeAccountPlaceHolderDto();
                        List<HashMap<String, String>> taxCode = null;
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            taxCode = new ArrayList<>();
                            while (rs.next()) {
                                temp = new HashMap<>();
                                temp.put(bacId, rs.getString(1));
                                temp.put(bacIdDescription, rs.getString(2));
                                temp.put(schemeType, rs.getString(3));
                                taxCode.add(temp);
                            }
                            output.setSchemeName(taxCode);

                        }
                    } else if (dto.getBacid() != null ){
                        output = new OfficeAccountPlaceHolderDto();
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(13)));
                        output.setId1(stmt.getString(4));
                        output.setCreatedBy(stmt.getString(5));
                        output.setCreatedDate1(stmt.getString(6));
                        output.setLastModifiedBy(stmt.getString(7));
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
    public ResponseDataDTO modifyDetails(OfficeAccountPlaceHolderDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        OfficeAccountPlaceHolderDto output = null;
        List<OfficeAccountPlaceHolderDto> modifyList = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_cbs_ofyc_acct_pl_hldr_mod_sel.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getBacid());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if ((stmt.getString(index - 1)) != null && (CommonConstants.SUCCESS).equalsIgnoreCase(stmt.getString(index - 1))) {

                    if (dto.getBacid() == null) {

                        rs = (ResultSet) stmt.getObject(4);
                        output = new OfficeAccountPlaceHolderDto();
                        List<HashMap<String, String>> taxCode = null;
                        if (rs != null) {
                            HashMap<String, String> temp = null;
                            taxCode = new ArrayList<>();
                            while (rs.next()) {
                                temp = new HashMap<>();
                                temp.put(bacId, rs.getString(1));
                                temp.put(bacIdDescription, rs.getString(2));
                                temp.put(schemeType, rs.getString(3));
                                taxCode.add(temp);

                            }
                            output.setSchemeName(taxCode);

                        }
                    }else if (dto.getBacid() != null) {

                        rs = (ResultSet) stmt.getObject(4);

                        if (rs != null) {
                            modifyList = new ArrayList<>();

                            while (rs.next()) {

                                output = new OfficeAccountPlaceHolderDto();
                                output.setId1(rs.getString(1));
                                output.setCreatedBy(rs.getString(5));
                                output.setCreatedDate1(rs.getString(6));
                                output.setLastModifiedBy(rs.getString(7));
                                output.setModifiedDate1(rs.getString(8));
                                output.setVerifiedStatus(rs.getString(9));
                                output.setVerifiedBy(rs.getString(10));
                                output.setVerifiedDate1(rs.getString(11));
                                output.setAdditionalText1(rs.getString(13));
                                output.setAdditionalText2(rs.getString(14));
                                output.setAdditionalText3(rs.getString(15));




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
    public ResponseDataDTO modify(OfficeAccountPlaceHolderDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum. pkg_gbm_cbs_ofyc_acct_pl_hldr_mod.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getId1());
                stmt.setString(index++, dto.getAdditionalText1());
                stmt.setString(index++, dto.getAdditionalText2());
                stmt.setString(index++, dto.getAdditionalText3());
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
    public ResponseDataDTO verifySubmit(OfficeAccountPlaceHolderDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum. pkg_gbm_cbs_ofyc_acct_pl_hldr_ver.getProcName());
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
    public ResponseDataDTO deleteSubmit(OfficeAccountPlaceHolderDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_cbs_ofyc_acct_pl_hldr_del.getProcName());
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
