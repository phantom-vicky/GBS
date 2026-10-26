/**
 *
 */
package com.isl.gbm.cbs.integration.dao.impl;

import com.isl.gbm.cbs.integration.dao.CalendarEntriesDao;
import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.model.CalendarEntries;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.common.utils.ProcNameEnum;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.internal.OracleTypes;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
 * The Class CalendarEntriesDaoImpl.
 *
 * @author harishbabu.t
 */
@Repository
public class CalendarEntriesDaoImpl extends HibernateDao implements CalendarEntriesDao {

    /** The Constant FILE_NAME. */
    private static final String FILE_NAME = "fileName";
    /** The Constant VERIFIED. */
    private static final String VERIFIED = "verifiedFlag";
    private static final String DELETE_FLAG = "deleteFlag";
    /** The session factory. */
    private SessionFactory sessionFactory;

    private static final Logger log = LoggerFactory.getLogger(CalendarEntriesDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#create(com.isl.gbm.cbs
     * .integration.model.CalendarEntries)
     */
    @Override
    public void create(final CalendarEntries calendarEntries) {
        final Session session = getSession();
        session.save(calendarEntries);
    }

    @Override
    public void update(final CalendarEntries calendarEntries) {
        final Session session = getSession();
        session.saveOrUpdate(calendarEntries);
    }

    @Override
    public ResponseDataDTO addDetails(CalendarEntriesDto calendarEntriesDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_calender_entries_Add.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, calendarEntriesDto.getCalendarType());
                stmt.setString(index++, calendarEntriesDto.getCalendarCode());
                stmt.setString(index++, calendarEntriesDto.getMonth());
                stmt.setString(index++, calendarEntriesDto.getYear());
                stmt.setString(index++, calendarEntriesDto.getHolidayString());
                stmt.setString(index++, calendarEntriesDto.getExistingFlag());
                stmt.setString(index++, calendarEntriesDto.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {

                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiry(CalendarEntriesDto calendarEntriesDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_calender_entries_inquiry.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, calendarEntriesDto.getCalendarType());
                stmt.setString(index++, calendarEntriesDto.getCalendarCode());
                stmt.setString(index++, calendarEntriesDto.getMonthYear());
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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    if (StringUtils.isNotEmpty(calendarEntriesDto.getCalendarType()) && StringUtils.isNotEmpty(calendarEntriesDto.getCalendarCode()) && StringUtils.isEmpty(calendarEntriesDto.getMonthYear())) {

                        resultSet = (ResultSet) stmt.getObject(17);

                        if (resultSet != null) {
                            HashMap<String, String> resultMap = null;
                            List<HashMap> resultList = new ArrayList<>();
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("MMYYYY", resultSet.getString("MMYYYY"));
                                resultList.add(resultMap);
                            }
                            responseDataDTO.setResult(resultList);
                        }
                    } else {
                        HashMap<String, Object> resultMap = null;
                        List<HashMap> resultList = new ArrayList<>();
                        resultMap = new HashMap<>();
                        resultMap.put("pkColVal", stmt.getString(6));
                        resultMap.put("createdBy", stmt.getString(7));
                        resultMap.put("createdDate", stmt.getString(8));
                        resultMap.put("modifiedBy", stmt.getString(9));
                        resultMap.put("modifiedDate", stmt.getString(10));
                        resultMap.put("verifiedBy", stmt.getString(11));
                        resultMap.put("verifiedDate", stmt.getString(12));
                        resultMap.put("prevOprType", stmt.getString(13));
                        resultMap.put("verfiedStatus", stmt.getString(14));
                        resultMap.put("rejectionRemarks", stmt.getString(15));
                        resultMap.put("model", DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(16)));
                        resultList.add(resultMap);
                        responseDataDTO.setResult(resultList);
                    }
                }
            } else {
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyDetails(CalendarEntriesDto calendarEntriesDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_calender_entries_verify.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, calendarEntriesDto.getCalendarType());
                stmt.setString(index++, calendarEntriesDto.getCalendarCode());
                stmt.setString(index++, calendarEntriesDto.getMonthYear());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO deleteDetails(CalendarEntriesDto calendarEntriesDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (calendarEntriesDto.getRejectionRemarks() == null
                || calendarEntriesDto.getRejectionRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(calendarEntriesDto.getRemarks())) {
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
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_calender_entries_delete.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, calendarEntriesDto.getCalendarType());
                stmt.setString(index++, calendarEntriesDto.getCalendarCode());
                stmt.setString(index++, calendarEntriesDto.getMonthYear());
                stmt.setString(index++, calendarEntriesDto.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getVerifyDetails(CalendarEntriesDto calendarEntriesDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_calender_entries_verify_sel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, calendarEntriesDto.getCalendarType());
                stmt.setString(index++, calendarEntriesDto.getCalendarCode());
                stmt.setString(index++, calendarEntriesDto.getMonthYear());
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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    if (StringUtils.isNotEmpty(calendarEntriesDto.getCalendarType()) && StringUtils.isNotEmpty(calendarEntriesDto.getCalendarCode()) && StringUtils.isEmpty(calendarEntriesDto.getMonthYear())) {

                        resultSet = (ResultSet) stmt.getObject(17);

                        if (resultSet != null) {
                            HashMap<String, String> resultMap = null;
                            List<HashMap> resultList = new ArrayList<>();

                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("MMYYYY", resultSet.getString("MMYYYY"));
                                resultList.add(resultMap);
                            }
                            responseDataDTO.setResult(resultList);
                        }
                    } else {
                        HashMap<String, Object> resultMap = null;
                        List<HashMap> resultList = new ArrayList<>();
                        resultMap = new HashMap<>();
                        resultMap.put("pkColVal", stmt.getString(6));
                        resultMap.put("createdBy", stmt.getString(7));
                        resultMap.put("createdDate", stmt.getString(8));
                        resultMap.put("modifiedBy", stmt.getString(9));
                        resultMap.put("modifiedDate", stmt.getString(10));
                        resultMap.put("verifiedBy", stmt.getString(11));
                        resultMap.put("verifiedDate", stmt.getString(12));
                        resultMap.put("prevOprType", stmt.getString(13));
                        resultMap.put("verfiedStatus", stmt.getString(14));
                        resultMap.put("RejectionRemarks", stmt.getString(15));
                        resultMap.put("model", DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(16)));
                        resultList.add(resultMap);
                        responseDataDTO.setResult(resultList);

                    }
                }
            } else {
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getModifyDetails(CalendarEntriesDto calendarEntriesDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_calender_entries_modify_sel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, calendarEntriesDto.getCalendarType());
                stmt.setString(index++, calendarEntriesDto.getCalendarCode());
                stmt.setString(index++, calendarEntriesDto.getMonthYear());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    if (StringUtils.isNotEmpty(calendarEntriesDto.getCalendarType()) && StringUtils.isNotEmpty(calendarEntriesDto.getCalendarCode()) && StringUtils.isEmpty(calendarEntriesDto.getMonthYear())) {

                        resultSet = (ResultSet) stmt.getObject(7);

                        if (resultSet != null) {
                            HashMap<String, String> resultMap = null;
                            List<HashMap> resultList = new ArrayList<>();
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("MMYYYY", resultSet.getString("MMYYYY"));
                                resultList.add(resultMap);
                            }
                            responseDataDTO.setResult(resultList);
                        }
                    } else if (StringUtils.isNotEmpty(calendarEntriesDto.getCalendarType()) && StringUtils.isNotEmpty(calendarEntriesDto.getCalendarCode()) && StringUtils.isNotEmpty(calendarEntriesDto.getMonthYear())) {
                        resultSet = (ResultSet) stmt.getObject(7);

                        if (resultSet != null) {
                            HashMap<String, String> resultMap = null;
                            List<HashMap> resultList = new ArrayList<>();
                            ;
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("type", resultSet.getString(1));
                                resultMap.put("monthYear", resultSet.getString(2));
                                resultMap.put("typeCode", resultSet.getString(3));
                                resultMap.put("holidayString", resultSet.getString(4));
                                resultMap.put("createdBy", resultSet.getString(5));
                                resultMap.put("createdDate", resultSet.getString(6));
                                resultMap.put("modifiedBy", resultSet.getString(7));
                                resultMap.put("modifiedDate", resultSet.getString(8));
                                resultMap.put("verifiedBy", resultSet.getString(9));
                                resultMap.put("verifiedDate", resultSet.getString(10));
                                resultMap.put("verfiedStatus", resultSet.getString(11));
                                resultMap.put("remarks", resultSet.getString(12));
                                resultMap.put("rejectionRemarks", stmt.getString(6));
                                resultList.add(resultMap);
                            }
                            responseDataDTO.setResult(resultList);
                        }
                    }
                }
            } else {
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifyDetails(CalendarEntriesDto calendarEntriesDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_calender_entries_modify.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, calendarEntriesDto.getCalendarType());
                stmt.setString(index++, calendarEntriesDto.getCalendarCode());
                stmt.setString(index++, calendarEntriesDto.getMonthYear());
                stmt.setString(index++, calendarEntriesDto.getHolidayString());
                stmt.setString(index++, calendarEntriesDto.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#delete(int)
     */
    @Override
    public void delete(final int id) {

        final Session session = getSession();
        final Query query = session.createQuery("update CalendarEntries set deleteFlag = :deleteFlag where Id = :Id");
        query.setParameter(DELETE_FLAG, Boolean.TRUE);
        query.setParameter("Id", id);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#delete(java.lang.
     * String)
     */
    @Override
    public void delete(final String fileName) {
        final Session session = getSession();
        final Query query = session.createQuery("delete from CalendarEntries where fileName = :fileName");
        query.setParameter(FILE_NAME, fileName);
        query.executeUpdate();
    }


    @Override
    public void deleteId(Integer id) {
        final Session session = getSession();
        final Query query = session.createQuery("delete from CalendarEntries where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#fileNameExists(java.
     * lang.String)
     */
    @Override
    public boolean fileNameExists(final String calendarFileName) {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from CalendarEntries where fileName = :fileName group by fileName", String.class);
        query.setParameter(FILE_NAME, calendarFileName);
        final List<String> fileNameList = query.list();
        return CollectionUtils.size(fileNameList) > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#getAllEntries()
     */
    @Override
    public List<CalendarEntries> getAllEntries() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<CalendarEntries> criteriaQuery = builder.createQuery(CalendarEntries.class);
        final Root<CalendarEntries> calendarEntriesRoot = criteriaQuery.from(CalendarEntries.class);
        criteriaQuery.select(calendarEntriesRoot)
                .where(builder.equal(calendarEntriesRoot.get(DELETE_FLAG), Boolean.FALSE));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#getCalendarEntries()
     */
    @Override
    public List<CalendarEntries> getCalendarEntries() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<CalendarEntries> criteriaQuery = builder.createQuery(CalendarEntries.class);
        final Root<CalendarEntries> calendarEntriesRoot = criteriaQuery.from(CalendarEntries.class);
        criteriaQuery.select(calendarEntriesRoot);
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#getCalendarEntry(java.
     * lang.String)
     */
    @Override
    public CalendarEntries getCalendarEntry(final Integer id) {
        final Session session = getSession();
        CalendarEntries calendarEntries = session.get(CalendarEntries.class, id);
        session.evict(calendarEntries);
        return calendarEntries;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#getUnverifiedEntries()
     */
    @Override
    public List<CalendarEntries> getUnverifiedEntries() {

        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<CalendarEntries> criteriaQuery = builder.createQuery(CalendarEntries.class);
        final Root<CalendarEntries> calendarEntriesRoot = criteriaQuery.from(CalendarEntries.class);
        criteriaQuery.select(calendarEntriesRoot).where(builder.equal(calendarEntriesRoot.get(VERIFIED), Boolean.FALSE),
                builder.equal(calendarEntriesRoot.get(DELETE_FLAG), Boolean.FALSE));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#getUnverifiedEntries(
     * java.lang.String)
     */
    @Override
    public List<CalendarEntries> getUnverifiedEntries(final String fileName) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<CalendarEntries> criteriaQuery = builder.createQuery(CalendarEntries.class);
        final Root<CalendarEntries> calendarEntriesRoot = criteriaQuery.from(CalendarEntries.class);
        criteriaQuery.select(calendarEntriesRoot).where(builder.equal(calendarEntriesRoot.get(FILE_NAME), fileName));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from CalendarEntries where verifiedFlag = :verifiedFlag and fileName is not null group by fileName",
                String.class);
        query.setParameter(VERIFIED, Boolean.FALSE);
        return query.list();
    }

    @Override
    public List<String> getInquiryFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from CalendarEntries where fileName is not null group by fileName",
                String.class);
        return query.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#iscalendarEntryExists(
     * java.lang.String, java.lang.String)
     */
    @Override
    public boolean iscalendarEntryExists(final String calendarCode, final String monthYear) {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select id from CalendarEntries where calendarCode = :calendarCode and monthYear = :monthYear");
        query.setParameter("calendarCode", calendarCode);
        query.setParameter("monthYear", monthYear);
        final List<String> solIdList = query.list();
        return CollectionUtils.size(solIdList) > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#saveEntries(java.util.
     * List)
     */
    @Override
    public void saveEntries(final List<CalendarEntries> calendarEntriesList) {
        final Session session = getSession();
        for (final CalendarEntries calendarEntries : calendarEntriesList) {
            session.save(calendarEntries);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#verify(int)
     */
    @Override
    public void verify(final int id) {

        final Session session = getSession();
        final Query query = session.createQuery(
                "update CalendarEntries set verifiedFlag = :verifiedFlag , verifiedBy = :verifiedBy , verifiedDate = :verifiedDate, auditable = :auditable where Id = :Id");
        query.setParameter(VERIFIED, Boolean.TRUE);
        query.setParameter("verifiedBy", null);
        query.setParameter("verifiedDate", new Date());
        query.setParameter("auditable", Boolean.TRUE);
        query.setParameter("Id", id);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.CalendarEntriesDao#verify(java.lang.
     * String)
     */
    @Override
    public void verify(final String fileName) {
        final Session session = getSession();
        final Query query = session
                .createQuery("update CalendarEntries set verifiedFlag = :verifiedFlag where fileName = :fileName");
        query.setParameter(VERIFIED, Boolean.TRUE);
        query.setParameter(FILE_NAME, fileName);
        query.executeUpdate();
    }

}
