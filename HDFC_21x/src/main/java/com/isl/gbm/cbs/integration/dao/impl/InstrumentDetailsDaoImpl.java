/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao.impl;
import com.isl.gbm.cbs.integration.common.ProcNameEnum;
import com.isl.gbm.cbs.integration.common.InstrumentTypes;
import com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.model.InstrumentDetails;
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
import java.util.*;

/**
 * The Class InstrumentDetailsDaoImpl.
 */
@Repository
public class InstrumentDetailsDaoImpl extends HibernateDao implements InstrumentDetailsDao {

    /**
     * The Constant FILE_NAME.
     */
    private static final String FILE_NAME = "fileName";

    /**
     * The Constant VERIFIED.
     */
    private static final String VERIFIED = "verified";

    /**
     * The Constant ID.
     */
    private static final String ID = "id";

    /**
     * The Constant SCHEME_CODE.
     */
    private static final String SCHEME_CODE = "schemeCode";

    /**
     * The Constant INSTRUMENT_TYPE.
     */
    private static final String INSTRUMENT_TYPE = "instrumentType";
    private static final Logger log = LoggerFactory.getLogger(InstrumentDetailsDaoImpl.class);


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
     * com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#delete(com.isl.gbm.
     * cbs. integration.model.InstrumentDetails)
     */
    @Override
    public void delete(final InstrumentDetails details) {
        final Session session = getSession();
        session.delete(details);
    }


    @Override
    public void deleteInstrument(Integer id) {
        final Session session = getSession();
        final Query query = session.createQuery("delete from InstrumentDetails where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#delete(java.lang.
     * String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void delete(final String instrumentFileName) {
        final Session session = getSession();
        final Query query = session.createQuery("delete from InstrumentDetails where fileName = :fileName");
        query.setParameter(FILE_NAME, instrumentFileName);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#fileNameExists(java.
     * lang .String)
     */
    @Override
    public boolean fileNameExists(final String instrumentFileName) {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from InstrumentDetails where fileName = :fileName group by fileName", String.class);
        query.setParameter(FILE_NAME, instrumentFileName);
        final List<String> fileNameList = query.list();
        return CollectionUtils.size(fileNameList) > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#
     * getInstrumentDetails( java.lang.String)
     */
    @Override
    public InstrumentDetails getInstrumentDetails(final Integer instrumentId) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<InstrumentDetails> criteriaQuery = builder.createQuery(InstrumentDetails.class);
        final Root<InstrumentDetails> root = criteriaQuery.from(InstrumentDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), instrumentId));
        // Use criteria to query with session to fetch all accounts
        final InstrumentDetails details = (InstrumentDetails) session.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);

        if (null != details) {
            session.evict(details);
        }
        return details;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#
     * getInstrumentDetails( java.lang.String, java.lang.String)
     */
    @Override
    public List<InstrumentDetails> getInstrumentDetails(final String instrumentType, final String schemeCode) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<InstrumentDetails> criteriaQuery = builder.createQuery(InstrumentDetails.class);
        final Root<InstrumentDetails> root = criteriaQuery.from(InstrumentDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(INSTRUMENT_TYPE), instrumentType),
                builder.equal(root.get(SCHEME_CODE), schemeCode));
        // Use criteria to query with session to fetch all accounts
        final List<InstrumentDetails> instrumentList = session.createQuery(criteriaQuery).getResultList();
        return instrumentList;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#getInstrumentTypes()
     */
    @Override
    public Map<String, String> getInstrumentTypes() {
        final Map<String, String> instrumentTypeMap = new HashMap<>();
        for (final InstrumentTypes type : InstrumentTypes.values()) {
            instrumentTypeMap.put(type.getInstrumentType(), type.getDescription());
        }
        return instrumentTypeMap;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#
     * getInstrumentTypesList( java.lang.String)
     */
    @Override
    public List<InstrumentDetails> getInstrumentTypesList(final String schmCode) {

        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<InstrumentDetails> criteriaQuery = builder.createQuery(InstrumentDetails.class);
        final Root<InstrumentDetails> root = criteriaQuery.from(InstrumentDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(SCHEME_CODE), schmCode));
        // Use criteria to query with session to fetch all accounts
        final List<InstrumentDetails> instrumentList = session.createQuery(criteriaQuery).getResultList();
        return instrumentList;

    }

    /**
     * Gets the records.
     *
     * @param verified the verified
     * @return the records
     */
    private List<InstrumentDetails> getRecords(final Boolean verified) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<InstrumentDetails> criteriaQuery = builder.createQuery(InstrumentDetails.class);
        final Root<InstrumentDetails> root = criteriaQuery.from(InstrumentDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(VERIFIED), verified));
        // Use criteria to query with session to fetch all accounts
        final List<InstrumentDetails> instrumentList = session.createQuery(criteriaQuery).getResultList();
        return instrumentList;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from InstrumentDetails where verified = :verified and fileName is not null group by fileName",
                String.class);
        query.setParameter(VERIFIED, Boolean.FALSE);
        final List<String> fileNameList = query.list();
        return fileNameList;
    }

    @Override
    public List<String> getInquiryFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from InstrumentDetails where fileName is not null group by fileName",
                String.class);
        //query.setParameter(VERIFIED, Boolean.FALSE);
        final List<String> fileNameList = query.list();
        return fileNameList;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#
     * getUnverifiedInstruments (java.lang.String)
     */
    @Override
    public List<InstrumentDetails> getUnverifiedInstruments(final String fileName) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<InstrumentDetails> criteriaQuery = builder.createQuery(InstrumentDetails.class);
        final Root<InstrumentDetails> root = criteriaQuery.from(InstrumentDetails.class);
        criteriaQuery.select(root).where(builder.equal(root.get(FILE_NAME), fileName));
        // Use criteria to query with session to fetch all accounts
        final List<InstrumentDetails> instrumentList = session.createQuery(criteriaQuery).getResultList();
        return instrumentList;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#getUnverifiedRecords
     * ()
     */
    @Override
    public List<InstrumentDetails> getUnverifiedRecords() {
        return this.getRecords(Boolean.FALSE);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#getVerifiedRecords()
     */
    @Override
    public List<InstrumentDetails> getVerifiedRecords() {
        return this.getRecords(Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#list()
     */
    @Override
    public List<InstrumentDetails> list() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<InstrumentDetails> criteriaQuery = builder.createQuery(InstrumentDetails.class);
        final Root<InstrumentDetails> root = criteriaQuery.from(InstrumentDetails.class);
        criteriaQuery.select(root);
        // Use criteria to query with session to fetch all accounts
        final List<InstrumentDetails> instrumentList = session.createQuery(criteriaQuery).getResultList();

        return instrumentList;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#saveOrUpdate(com.isl
     * .gbm .cbs.integration.model.InstrumentDetails)
     */
    @Override
    public void saveOrUpdate(final InstrumentDetails instrumentDetails) {
        final Session session = getSession();
        session.saveOrUpdate(instrumentDetails);
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#upload(java.util.
     * List)
     */
    @Override
    public void upload(final List<InstrumentDetails> instrumentList) {
        final Session session = getSession();
        for (final InstrumentDetails instrument : instrumentList) {
            session.saveOrUpdate(instrument);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#verify(com.isl.gbm.
     * cbs. integration.model.InstrumentDetails)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final InstrumentDetails details) {
        final Session session = getSession();
        final String HQL = "update InstrumentDetails set verified = :verified, verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where id = :id";
        final Query query = session.createQuery(HQL);
        query.setParameter(VERIFIED, details.isVerified());
        query.setParameter("verifiedBy", details.getVerifiedBy());
        query.setParameter("verifiedDate", details.getVerifiedDate());
        query.setParameter("auditable", details.isAuditable());
        query.setParameter("id", details.getId());
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao#verify(java.lang.
     * String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final String fileName) {
        final Session session = getSession();
        final Query query = session
                .createQuery("update InstrumentDetails set verified = :verified ,verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where fileName = :fileName");
        query.setParameter(VERIFIED, Boolean.TRUE);
        query.setParameter(FILE_NAME, fileName);
        query.setParameter("verifiedBy", UserAccountUtils.getCurrentUsername());
        query.setParameter("verifiedDate", new Date());
        query.setParameter("auditable", Boolean.TRUE);
        query.executeUpdate();
    }

    @Override
    public ResponseDataDTO addDetails(InstrumentDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_INSTRUMENT_DET_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getDrCrIndicator1());
                stmt.setString(index++, dto.getInstrumentType());
                stmt.setString(index++, dto.getSchemeCode());
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
    public ResponseDataDTO inquiry(InstrumentDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        InstrumentDetailsDto output = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_INSTRUMENT_DET_INQ.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getDrCrIndicator1());
                stmt.setString(index++, dto.getInstrumentType());
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

                    if (dto.getDrCrIndicator1() != null && dto.getInstrumentType() != null) {

                        output = new InstrumentDetailsDto();
                        output.setSchemeCode(stmt.getString(4));
                        output.setCreatedBy(stmt.getString(5));
                        output.setCreatedDate1(stmt.getString(6));
                        output.setModifiedBy(stmt.getString(7));
                        output.setModifiedDate1(stmt.getString(8));
                        output.setVerifiedBy(stmt.getString(9));
                        output.setVerifiedDate1(stmt.getString(10));
                        output.setPrevOperationType(stmt.getString(11));
                        output.setVerifiedStatus(stmt.getString(12));
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(13)));



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
    public ResponseDataDTO verDelDetails(InstrumentDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        InstrumentDetailsDto output = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_INSTRUMENT_DET_VER_SEL.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getDrCrIndicator1());
                stmt.setString(index++, dto.getInstrumentType());
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

                    if (dto.getDrCrIndicator1() != null && dto.getInstrumentType() != null) {

                        output = new InstrumentDetailsDto();
                        output.setSchemeCode(stmt.getString(5));
                        output.setPkColVal(stmt.getString(6));
                        output.setCreatedBy(stmt.getString(7));
                        output.setCreatedDate1(stmt.getString(8));
                        output.setModifiedBy(stmt.getString(9));
                        output.setModifiedDate1(stmt.getString(10));
                        output.setVerifiedBy(stmt.getString(11));
                        output.setVerifiedDate1(stmt.getString(12));
                        output.setPrevOperationType(stmt.getString(13));
                        output.setVerifiedStatus(stmt.getString(14));
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(15)));



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
    public ResponseDataDTO modifyDetails(InstrumentDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        InstrumentDetailsDto output = null;
        List<InstrumentDetailsDto> modifyList = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_INSTRUMENT_DET_MOD_SEL.getProcName());
                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getDrCrIndicator1());
                stmt.setString(index++, dto.getInstrumentType());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if ((stmt.getString(index - 1)) != null && (CommonConstants.SUCCESS).equalsIgnoreCase(stmt.getString(index - 1))) {

                    if (dto.getDrCrIndicator1() != null && dto.getInstrumentType() != null) {

                        rs = (ResultSet) stmt.getObject(5);

                        if (rs != null) {
                            modifyList = new ArrayList<>();

                            while (rs.next()) {

                                output = new InstrumentDetailsDto();
                                output.setId(rs.getString(1));
                                output.setSchemeCode(rs.getString(4));
                                output.setCreatedBy(rs.getString(5));
                                output.setCreatedDate1(rs.getString(6));
                                output.setModifiedBy(rs.getString(7));
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
    public ResponseDataDTO modify(InstrumentDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_INSTRUMENT_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getId());
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
    public ResponseDataDTO verifySubmit(InstrumentDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_INSTRUMENT_DET_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getId());
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
    public ResponseDataDTO deleteSubmit(InstrumentDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_INSTRUMENT_DET_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getId());
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
