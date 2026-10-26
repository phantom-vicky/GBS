/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao.impl;


import com.isl.gbm.cbs.integration.common.ProcNameEnum;

import com.isl.gbm.cbs.integration.dao.BankBranchDao;
import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BankBranchDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
//import com.isl.gbm.stateTax.util.ProcNameEnum;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The Class BankBranchDaoImpl.
 */
@Repository
public class BankBranchDaoImpl extends HibernateDao implements BankBranchDao {

    /**
     * The Constant FILE_NAME.
     */
    private static final String FILE_NAME = "fileName";

    /**
     * The Constant VERIFIED.
     */
    private static final String VERIFIED = "verifiedFlag";

    /**
     * The Constant DELETED.
     */
    private static final String DELETED = "deleteFlag";

    private static final Logger log = LoggerFactory.getLogger(BankBranchDaoImpl.class);

    final String emailPattern = "^(?!\\s)[a-zA-Z0-9_%+-][a-zA-Z0-9._%+-]*@(hdfcbank\\.com|hdfcbank\\.co\\.in)$";
    Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);

    /**
     * The session factory.
     */
    private SessionFactory sessionFactory;

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;


    /**
     * The Entity Manager
     */
    @PersistenceContext
    EntityManager em;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BankBranchDao#deleteBank(int)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void delete(final int id) {
        final Session session = getSession();
        final Query query = session.createQuery("update BankBranchMaster set deleteFlag = :deleteFlag where id = :id");
        query.setParameter(DELETED, Boolean.TRUE);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteId(int id) {
        final Session session = this.getSession();
        final Query query = session.createQuery("delete from BankBranchMaster where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BranchBankDao#deleteBanks(java.lang.
     * String)
     */
    @Override
    public void deleteBanks(final String bankFileName) {
        final Session session = this.getSession();
        final Query query = session.createQuery("delete from BankBranchMaster where fileName = :fileName");
        query.setParameter(FILE_NAME, bankFileName);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BranchBankDao#fileNameExists(java.lang.
     * String)
     */
    @Override
    public boolean fileNameExists(final String bankFileName) {
        final Session session = this.getSession();
        final Query<String> query = session.createQuery(
                "select fileName from BankBranchMaster where fileName = :fileName group by fileName", String.class);
        query.setParameter(FILE_NAME, bankFileName);
        final List<String> fileNameList = query.list();
        return CollectionUtils.size(fileNameList) > 0;
    }


    @Override
    public boolean unVerifiedDetails() {
        try {

//            final Session session = this.getSession();
//            final Query<String> query = session
//                    .createNativeQuery("select * from GBM_BANK_BRANCHES where SOL_ID = :solId and DEL_FLG =:deleteFlag ");
//            query.setParameter("solId", solId);
//            query.setParameter(DELETED, 0);
//            final List<String> solIdList = query.getResultList();
//            return CollectionUtils.size(solIdList) > 0;

            final Session session = getSession();
            Query<String> query = session.createNativeQuery("SELECT FILENAME FROM GBM_BANK_BRANCHES WHERE DEL_FLG = :delStatus AND VER_FLG = :verStatus AND AUDITABLE = :auditable");
            query.setParameter("delStatus", 0);
            query.setParameter("verStatus", 0);
            query.setParameter("auditable", 0);
            final List<String> solIdList = query.getResultList();
//            BigDecimal totalCount = countQuery.getSingleResult();
            if(CollectionUtils.size(solIdList) > 1){
                return true;
            }
            else{
                return false;
            }

        } catch (Exception e) {
            return true;
        }


    }





    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BankBranchDao#getAllBankDetails()
     */
    @Override
    public List<BankBranchMaster> getAllBankDetails() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<BankBranchMaster> criteriaQuery = builder.createQuery(BankBranchMaster.class);
        final Root<BankBranchMaster> branchBankRoot = criteriaQuery.from(BankBranchMaster.class);
        criteriaQuery.select(branchBankRoot).where(builder.equal(branchBankRoot.get(DELETED), Boolean.FALSE),
                builder.equal(branchBankRoot.get(VERIFIED), Boolean.FALSE));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BankBranchDao#getAllBankDetailsinquiry()
     */
    @Override
    public List<BankBranchMaster> getAllBankDetailsForInquiry() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<BankBranchMaster> criteriaQuery = builder.createQuery(BankBranchMaster.class);
        final Root<BankBranchMaster> branchBankRoot = criteriaQuery.from(BankBranchMaster.class);
        criteriaQuery.select(branchBankRoot).where(builder.equal(branchBankRoot.get(DELETED), Boolean.FALSE));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BankBranchDao#getBank(java.lang.String)
     */
    @Override
    public BankBranchMaster getBankBranchDetails(final int id) {
        final Session session = this.getSession();
        final BankBranchMaster master = session.get(BankBranchMaster.class, id);
        if (null != master) {
            session.evict(master);
        }
        return master;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BranchBankDao#getBranchBankList()
     */
    @Override
    public List<BankBranchMaster> getBranchBankList() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<BankBranchMaster> criteriaQuery = builder.createQuery(BankBranchMaster.class);
        final Root<BankBranchMaster> branchBankRoot = criteriaQuery.from(BankBranchMaster.class);
        criteriaQuery.select(branchBankRoot);
        return em.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.BankBranchDao#getSolDetails(java.lang.String)
     */
    @Override
    public List<BankBranchMaster> getSolDetails(final String solId) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<BankBranchMaster> criteriaQuery = builder.createQuery(BankBranchMaster.class);
        final Root<BankBranchMaster> root = criteriaQuery.from(BankBranchMaster.class);
        final List<Predicate> predicates = new ArrayList<>(1);
        // adding the and condition for incoming sol id
        if (StringUtils.isNotBlank(solId)) {
            final Predicate solIdPredicate = builder.equal(root.get("solId"), solId);
            predicates.add(solIdPredicate);
        }
        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[]{})));
        // Use criteria to query with session to fetch all accounts
        return em.createQuery(criteriaQuery).getResultList();

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BankBranchDao#getSolIdandDescription()
     */
    @Override
    public List<BankBranchMaster> getSolIdandDescription() {
        final Session session = this.getSession();
        final Query query = session
                .createQuery("select solId, solDesc from BankBranchMaster where verifiedFlag = :verifiedFlag");
        query.setParameter(VERIFIED, Boolean.TRUE);
        final List<Object[]> rows = query.list();
        final List<BankBranchMaster> resultList = new ArrayList<>();
        for (final Object[] objArray : rows) {
            final BankBranchMaster bankBranchMaster = new BankBranchMaster();
            bankBranchMaster.setSolId((String) objArray[0]);
            bankBranchMaster.setSolDesc((String) objArray[1]);
            resultList.add(bankBranchMaster);
        }
        return resultList;
    }
    @Override
    public List<BankBranchMaster> getAllBranchId(BankBranchDto dto) {
        final Session session = this.getSession();
//        if(dto.getTypeFlag().equalsIgnoreCase("I")){
            final Query query = session
                    .createQuery("select solId, solDesc from BankBranchMaster where verifiedFlag = :verifiedFlag");
            query.setParameter(VERIFIED, Boolean.TRUE);
//        }

        final List<Object[]> rows = query.list();
        final List<BankBranchMaster> resultList = new ArrayList<>();
        for (final Object[] objArray : rows) {
            final BankBranchMaster bankBranchMaster = new BankBranchMaster();
            bankBranchMaster.setSolId((String) objArray[0]);
            bankBranchMaster.setSolDesc((String) objArray[1]);
            resultList.add(bankBranchMaster);
        }
        return resultList;
    }


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BranchBankDao#getUnverifiedBankDetails(
     * java.lang.String)
     */
    @Override
    public List<BankBranchMaster> getUnverifiedBankDetails(final String fileName) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        int delFlag = 0;
        final CriteriaQuery<BankBranchMaster> criteriaQuery = builder.createQuery(BankBranchMaster.class);
        final Root<BankBranchMaster> root = criteriaQuery.from(BankBranchMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(FILE_NAME), fileName), builder.equal(root.get(DELETED), delFlag));
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<BankBranchMaster> getInquiryBankDetails(final String fileName) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<BankBranchMaster> criteriaQuery = builder.createQuery(BankBranchMaster.class);
        final Root<BankBranchMaster> root = criteriaQuery.from(BankBranchMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(FILE_NAME), fileName), builder.equal(root.get(DELETED), Boolean.FALSE));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BranchBankDao#getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from BankBranchMaster where verifiedFlag = :verifiedFlag and fileName is not null group by fileName",
                String.class);
        query.setParameter(VERIFIED, Boolean.FALSE);
        return query.list();
    }

    @Override
    public List<String> getInquiryFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from BankBranchMaster where fileName is not null group by fileName",
                String.class);
        return query.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BankBranchDao#getUnverifiedBanks()
     */
    @Override
    public List<BankBranchMaster> getUnverifiedRecords() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<BankBranchMaster> criteriaQuery = builder.createQuery(BankBranchMaster.class);
        final Root<BankBranchMaster> root = criteriaQuery.from(BankBranchMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(VERIFIED), Boolean.FALSE),
                builder.equal(root.get(DELETED), Boolean.FALSE));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BankBranchDao#getAllBankDetails()
     */
    @Override
    public List<BankBranchMaster> getVerifiedRecords() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<BankBranchMaster> criteriaQuery = builder.createQuery(BankBranchMaster.class);
        final Root<BankBranchMaster> root = criteriaQuery.from(BankBranchMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(VERIFIED), Boolean.TRUE),
                builder.equal(root.get(DELETED), Boolean.FALSE));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BankBranchDao#isSolIdExists(java.lang.
     * String)
     */
    @Override
    public Boolean isSolIdExists(final String solId) {
        final Session session = this.getSession();
        final Query<String> query = session
                .createNativeQuery("select SOL_ID from GBM_BANK_BRANCHES where SOL_ID = :solId and DEL_FLG =:deleteFlag ");
        query.setParameter("solId", solId);
        query.setParameter(DELETED, 0);
        final List<String> solIdList = query.getResultList();
        return CollectionUtils.size(solIdList) > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.BranchBankDao#saveDetails(java.util.List)
     */
    @Override
    public void saveDetails(final List<BankBranchMaster> branchBankList) {
        final Session session = getSession();
        for (final BankBranchMaster branchBankMaster : branchBankList) {
            session.saveOrUpdate(branchBankMaster);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BankBranchDao#saveBank(com.isl.gbm.cbs.
     * integration.model.BankBranchMaster)
     */
    @Override
    public void saveOrUpdate(final BankBranchMaster branchBankMaster) {
        this.getSession().saveOrUpdate(branchBankMaster);
    }

    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BankBranchDao#verify(com.isl.gbm.cbs.
     * integration.model.BankBranchMaster)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final BankBranchMaster master) {
        final Session session = this.getSession();
        final String HQL = "update BankBranchMaster set verifiedFlag = :verified, verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where id = :id";
        final Query query = session.createQuery(HQL);
        query.setParameter("verified", master.getVerifiedFlag());
        query.setParameter("verifiedBy", master.getVerifiedBy());
        query.setParameter("verifiedDate", master.getVerifiedDate());
        query.setParameter("auditable", master.getAuditable());
        query.setParameter("id", master.getId());
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.BranchBankDao#verifyBanks(java.lang.
     * String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verifyBanks(final String fileName) {
        final Session session = this.getSession();
        final Query query = session.createQuery(
                "update BankBranchMaster set verifiedFlag = :verifiedFlag , verifiedBy = :verifiedBy , verifiedDate = :verifiedDate where fileName = :fileName");
        query.setParameter(VERIFIED, Boolean.TRUE);
        query.setParameter("verifiedDate", new Date());
        query.setParameter("verifiedBy", null);
        query.setParameter(FILE_NAME, fileName);
        query.executeUpdate();
    }


    public List<BankBranchDtoAuditLogWrapper> getAllDetails() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT t1.*, t2.* FROM gbm_bank_branches t1 left join gbm_audit_log t2 on t1.id = t2.TABLE_PK_COL_VALUE and upper(table_name) = 'GBM_BANK_BRANCHES' and t1.DEL_FLG = '0' and lower(MODIFIED_FIELDS_DATA) not like '%record added%' and substr(lower(MODIFIED_FIELDS_DATA), instr(lower(MODIFIED_FIELDS_DATA), '^', 1, 3)+1, instr(lower(MODIFIED_FIELDS_DATA), '^', 1, 4)-instr(lower(MODIFIED_FIELDS_DATA), '^', 1, 3)-1)  not like '%record verified%' and substr(lower(MODIFIED_FIELDS_DATA), instr(lower(MODIFIED_FIELDS_DATA), '^', 1, 3)+1, instr(lower(MODIFIED_FIELDS_DATA), '^', 1, 4)-instr(lower(MODIFIED_FIELDS_DATA), '^', 1, 3)-1) not like '%record modified%' and substr(lower(MODIFIED_FIELDS_DATA), instr(lower(MODIFIED_FIELDS_DATA), '^', 1, 3)+1, instr(lower(MODIFIED_FIELDS_DATA), '^', 1, 4)-instr(lower(MODIFIED_FIELDS_DATA), '^', 1, 3)-1) not like '%record deleted%%' ");
        BankBranchDto bankBranchMaster = null;
        List<BankBranchDtoAuditLogWrapper> wrapperList = new ArrayList<>();
        BankBranchDtoAuditLogWrapper wrapper = null;
        final Session session = this.getSession();
        final Query<Object[]> query = session.createNativeQuery(sql.toString());
        final List<Object[]> rows = query.getResultList();
        for (final Object[] objArray : rows) {
            wrapper = new BankBranchDtoAuditLogWrapper();
            AuditLogDto auditLogDto = null;
            bankBranchMaster = new BankBranchDto();
            bankBranchMaster.setId(((BigDecimal) objArray[0]).intValue());
            bankBranchMaster.setSolId((String) objArray[1]);
            bankBranchMaster.setSolDesc((String) objArray[2]);
            bankBranchMaster.setBankCode((String) objArray[3]);
            bankBranchMaster.setBranchCode((String) objArray[4]);
            bankBranchMaster.setMicrCertreCode((String) objArray[5]);
            bankBranchMaster.setMicrBankCode((String) objArray[6]);
            bankBranchMaster.setMicrBranchCode((String) objArray[7]);
            bankBranchMaster.setAddr1((String) objArray[8]);
            bankBranchMaster.setAddr2((String) objArray[9]);
            bankBranchMaster.setCityCode((String) objArray[10]);
            bankBranchMaster.setStateCode((String) objArray[11]);
            bankBranchMaster.setPinCode((String) objArray[12]);
            bankBranchMaster.setPanGirNumber((String) objArray[13]);
            bankBranchMaster.setTaxDednDetails((String) objArray[14]);
            bankBranchMaster.setTdsCircleName((String) objArray[15]);
            bankBranchMaster.setDivisionName((String) objArray[16]);
            bankBranchMaster.setCirclOfficeName((String) objArray[17]);
            bankBranchMaster.setUniformBrCode((String) objArray[18]);
            bankBranchMaster.setDeleteFlag(((BigDecimal) objArray[19]).intValue() > 0 ? true : false);
            bankBranchMaster.setVerifiedFlag(((BigDecimal) objArray[20]).intValue() > 0 ? true : false);
            bankBranchMaster.setFileName((String) objArray[21]);
            bankBranchMaster.setCreatedBy((String) objArray[22]);
            bankBranchMaster.setCreatedDate((Date) objArray[23]);
            bankBranchMaster.setLastModifiedBy((String) objArray[24]);
            bankBranchMaster.setLastModifiedDate((Date) objArray[25]);
            bankBranchMaster.setVerifiedBy((String) objArray[26]);
            bankBranchMaster.setVerifiedDate((Date) objArray[27]);
            bankBranchMaster.setAdditionalText1((String) objArray[28]);
            bankBranchMaster.setAdditionalText2((String) objArray[29]);
            bankBranchMaster.setAdditionalText3((String) objArray[30]);
            bankBranchMaster.setIfscCode((String) objArray[44]);
            //bankBranchMaster.setStatus((int) objArray[31]);
            //bankBranchMaster.setAuditable((boolean) objArray[32]);
            if ((BigDecimal) objArray[38] != null) {
                auditLogDto = new AuditLogDto();
                auditLogDto.setAuditId((BigDecimal) objArray[38]);
            }
            wrapper.setDto(bankBranchMaster);
            wrapper.setAuditLogDto(auditLogDto);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

    @Override
    public ResponseDataDTO addDetails(BankBranchDto dto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BRNCHS_BANKS_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getSolId());
                stmt.setString(index++, dto.getSolDesc());
                stmt.setString(index++, dto.getBankCode());
                stmt.setString(index++, dto.getBranchCode());
                stmt.setString(index++, dto.getMicrCertreCode());
                stmt.setString(index++, dto.getMicrBankCode());
                stmt.setString(index++, dto.getMicrBranchCode());
                stmt.setString(index++, dto.getAddr1());
                stmt.setString(index++, dto.getAddr2());
                stmt.setString(index++, dto.getBranchLocation());
                stmt.setString(index++, dto.getCityCode());
                stmt.setString(index++, dto.getStateCode());
                stmt.setString(index++, dto.getPinCode());
                stmt.setString(index++, dto.getPanGirNumber());
                stmt.setString(index++, dto.getTaxDednDetails());
                stmt.setString(index++, dto.getTdsCircleName());
                stmt.setString(index++, dto.getDivisionName());
                stmt.setString(index++, dto.getCirclOfficeName());
                stmt.setString(index++, dto.getUniformBrCode());
                stmt.setInt(index++, dto.getBranchStatus());
                stmt.setString(index++, dto.getAdditionalText1());
                stmt.setString(index++, dto.getAdditionalText2());
                stmt.setString(index++, dto.getAdditionalText3());
                stmt.setString(index++, dto.getZoneCode());
                stmt.setString(index++, dto.getZoneName());
                stmt.setString(index++, dto.getPdoCode());
                stmt.setString(index++, dto.getShortName());
                stmt.setString(index++, dto.getBranchEmailId());
                stmt.setString(index++, dto.getIfscCode());
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
    public ResponseDataDTO inquiry(BankBranchDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BankBranchDto output = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BRNCHS_BANKS_INQ.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getSolId());
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

                    if (dto.getSolId() != null) {
                        output = new BankBranchDto();
                        output.setCreatedBy(stmt.getString(3));
                        output.setCreatedDate1(stmt.getString(4));
                        output.setLastModifiedBy(stmt.getString(5));
                        output.setModifiedDate1(stmt.getString(6));
                        output.setVerifiedBy(stmt.getString(7));
                        output.setVerifiedDate1(stmt.getString(8));
                        output.setPrevOperationType(stmt.getString(9));
                        output.setVerifiedStatus(stmt.getString(10));
                        output.setRemarks(stmt.getString(11));
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(12)));
                        responseDataDTO.setResult(output);

                    }
                    else{
                        rs = (ResultSet) stmt.getObject(12);
                        if(rs != null){
                            HashMap<String, String> resultMap = null;
                            List<HashMap> resultList = new ArrayList<>();
                            while (rs.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("solId", rs.getString(1));
                                resultMap.put("solDesc", rs.getString(2));
                                resultList.add(resultMap);
                            }
                            responseDataDTO.setResult(resultList);
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
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO verDelDetails(BankBranchDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BankBranchDto output = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum. PKG_GBM_CBS_BRNCHS_BANKS_VER_SEL.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getSolId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
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

                        if (dto.getSolId() != null ){

                        output = new BankBranchDto();
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(14)));
                        output.setPkColVal(stmt.getString(4));
                        output.setCreatedBy(stmt.getString(5));
                        output.setCreatedDate1(stmt.getString(6));
                        output.setLastModifiedBy(stmt.getString(7));
                        output.setModifiedDate1(stmt.getString(8));
                        output.setVerifiedBy(stmt.getString(9));
                        output.setVerifiedDate1(stmt.getString(10));
                        output.setPrevOperationType(stmt.getString(11));
                        output.setVerifiedStatus(stmt.getString(12));
                        output.setRemarks(stmt.getString(13));
                        responseDataDTO.setResult(output);

                    }

                        else{
                            rs = (ResultSet) stmt.getObject(14);
                            if(rs != null){
                                HashMap<String, String> resultMap = null;
                                List<HashMap> resultList = new ArrayList<>();
                                while (rs.next()) {
                                    resultMap = new HashMap<>();
                                    resultMap.put("solId", rs.getString(1));
                                    resultMap.put("solDesc", rs.getString(2));
                                    resultList.add(resultMap);
                                }
                                responseDataDTO.setResult(resultList);
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
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO modifyDetails(BankBranchDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BankBranchDto output = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BRNCHS_BANKS_MOD_SEL.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getSolId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if ((stmt.getString(index - 1)) != null && (CommonConstants.SUCCESS).equalsIgnoreCase(stmt.getString(index - 1))) {

                   if (dto.getSolId() != null) {

                        rs = (ResultSet) stmt.getObject(5);

                        if (rs != null) {

                            while (rs.next()) {
                                output = new BankBranchDto();
                                output.setRemarks(stmt.getString(4));
                                output.setSolId(rs.getString(1));
                                output.setSolDesc(rs.getString(2));
                                output.setBankCode(rs.getString(3));
                                output.setBranchCode(rs.getString(4));
                                output.setMicrCertreCode(rs.getString(5));
                                output.setMicrBankCode(rs.getString(6));
                                output.setMicrBranchCode(rs.getString(7));
                                output.setAddr1(rs.getString(8));
                                output.setAddr2(rs.getString(9));
                                output.setBranchLocation(rs.getString(10));
                                output.setCityCode(rs.getString(11));
                                output.setStateCode(rs.getString(12));
                                output.setPinCode(rs.getString(13));
                                output.setPanGirNumber(rs.getString(14));
                                output.setTaxDednDetails(rs.getString(15));
                                output.setTdsCircleName(rs.getString(16));
                                output.setDivisionName(rs.getString(17));
                                output.setCirclOfficeName(rs.getString(18));
                                output.setUniformBrCode(rs.getString(19));
                                output.setBranchStatus(rs.getInt(20));
                                output.setCreatedBy(rs.getString(21));
                                output.setCreatedDate1(rs.getString(22));
                                output.setLastModifiedBy(rs.getString(23));
                                output.setModifiedDate1(rs.getString(24));
                                output.setVerifiedStatus(rs.getString(25));
                                output.setVerifiedBy(rs.getString(26));
                                output.setVerifiedDate1(rs.getString(27));
                                output.setAdditionalText1(rs.getString(29));
                                output.setAdditionalText2(rs.getString(30));
                                output.setAdditionalText3(rs.getString(31));
                                output.setId1(rs.getString(32));
                                output.setZoneCode(rs.getString(33));
                                output.setZoneName(rs.getString(34));
                                output.setPdoCode(rs.getString(35));
                                output.setBranchEmailId(rs.getString(36));
                                output.setShortName(rs.getString(37));
                                output.setIfscCode(rs.getString(38));
                                responseDataDTO.setResult(output);
                            }

                        }
                    }
                   else{

                       rs = (ResultSet) stmt.getObject(5);
                       if(rs != null){
                           HashMap<String, String> resultMap = null;
                           List<HashMap> resultList = new ArrayList<>();
                           while (rs.next()) {
                               resultMap = new HashMap<>();
                               resultMap.put("solId", rs.getString(1));
                               resultMap.put("solDesc", rs.getString(2));
                               resultList.add(resultMap);
                           }
                           responseDataDTO.setResult(resultList);
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
        return responseDataDTO;

    }


    @Override
    public ResponseDataDTO modify(BankBranchDto dto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BRNCHS_BANKS_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getId1());
                stmt.setString(index++, dto.getSolDesc());
                stmt.setString(index++, dto.getBankCode());
                stmt.setString(index++, dto.getBranchCode());
                stmt.setString(index++, dto.getMicrCertreCode());
                stmt.setString(index++, dto.getMicrBankCode());
                stmt.setString(index++, dto.getMicrBranchCode());
                stmt.setString(index++, dto.getAddr1());
                stmt.setString(index++, dto.getAddr2());
                stmt.setString(index++, dto.getBranchLocation());
                stmt.setString(index++, dto.getCityCode());
                stmt.setString(index++, dto.getStateCode());
                stmt.setString(index++, dto.getPinCode());
                stmt.setString(index++, dto.getPanGirNumber());
                stmt.setString(index++, dto.getTaxDednDetails());
                stmt.setString(index++, dto.getTdsCircleName());
                stmt.setString(index++, dto.getDivisionName());
                stmt.setString(index++, dto.getCirclOfficeName());
                stmt.setString(index++, dto.getUniformBrCode());
                stmt.setInt(index++, dto.getBranchStatus());
                stmt.setString(index++, dto.getAdditionalText1());
                stmt.setString(index++, dto.getAdditionalText2());
                stmt.setString(index++, dto.getAdditionalText3());
                stmt.setString(index++, dto.getZoneCode());
                stmt.setString(index++, dto.getZoneName());
                stmt.setString(index++, dto.getPdoCode());
                stmt.setString(index++, dto.getShortName());
                stmt.setString(index++, dto.getBranchEmailId());
                stmt.setString(index++, dto.getIfscCode());
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
    public ResponseDataDTO verifySubmit(BankBranchDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum. PKG_GBM_CBS_BRNCHS_BANKS_VER.getProcName());
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
    public ResponseDataDTO deleteSubmit(BankBranchDto dto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (dto.getRemarks() == null
                || dto.getRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(dto.getRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
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
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BRNCHS_BANKS_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getId1());
                stmt.setString(index++, dto.getRemarks());
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
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

}
