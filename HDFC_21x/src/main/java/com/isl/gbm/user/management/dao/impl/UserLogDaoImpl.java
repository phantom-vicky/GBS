package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.common.DateUtils;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.user.management.constants.UserMgmtOperations;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.dao.UserLogDao;
import com.isl.gbm.user.management.dto.ActiveUserDto;
import com.isl.gbm.user.management.dto.ReportSearchCriteriaDto;
import com.isl.gbm.user.management.model.UserLog;
import com.isl.gbm.user.management.util.ProcNameEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class UserLogDaoImpl.
 */
@Repository
public class UserLogDaoImpl extends HibernateDao implements UserLogDao {

    private static final String CREATED_TIME = "createdTime";

    private static final String LOG_TYPE = "logType";

    private static final String USER_ID = "userId";

    private static final String SOL_ID = "solId";

    private static final String SUCCESS = "success";

    private static final String START_DATE = "startDate";

    private static final String END_DATE = "endDate";

    private static final String SESSION_ID = "sessionId";

    private static final String USERNAME = "username";

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.user.management.dao.UserLogDao#save(com.isl.gbm.user.management.
     * model.UserLog)
     */
    @PersistenceContext
    EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(UserLogDaoImpl.class);


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;
    @Override
    public void save(UserLog userLog) {
        //getSession().saveOrUpdate(userLog);
        em.merge(userLog);
    }
  /*  @Override
    public void save(UserLog userLog) {
        //   getSession().saveOrUpdate(userLog);
        Connection connection = null;
//        PreparedStatement stmt = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        String result = " ";
        try {
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_INSERT_OR_UPDATE_USER_LOG.getProcName());
                stmt.setString(index++, userLog.getUserId().toString());
                stmt.setString(index++, userLog.getSessionId());
                stmt.setString(index++, userLog.getLogoutReason());
                stmt.setString(index++, userLog.getLogoutReasonDesc());
                stmt.setString(index++, userLog.getSolId());
                stmt.setString(index++, userLog.getIpAddress());
                stmt.setString(index++, userLog.getLogType());
                stmt.setString(index++, userLog.getResponsePayload());
                stmt.setString(index++, userLog.getSuccess().toString());
                stmt.setString(index++, userLog.getUserAgent());
//                stmt.setString(index++, userLog.getId().toString());
                stmt.setString(index++, userLog.getUsername());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                result = stmt.getString(index);
                log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<LOGOUT STATUS>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
//                responseDataDTO.setMessage(result);
            } else {
//                responseDataDTO.setMessage(ErrorMessages.INTERNAL_SERVER_ERROR);
                log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<LOGOUT STATUS ERROR>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
    }*/

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.user.management.dao.UserLogDao#getLastSuccessLoginInfo(java.lang.
     * Integer)
     */
    @Override
    public UserLog getLastSuccessLoginInfo(Integer userId) {
        final Session session = getSession();
        Query<UserLog> query = session.createQuery("from UserLog where logType= :logType and userId= :userId and success= :success and id = (Select MAX(id) from  UserLog where logType= :logType and userId= :userId and success= :success)",
                UserLog.class);
        query.setParameter(USER_ID, userId);
        query.setParameter(LOG_TYPE, UserMgmtOperations.LOGIN);
        query.setParameter(SUCCESS, 1);
        try {
            return query.getResultList()
                    .stream().findFirst().orElse(null);
        } catch (NoResultException e) {
            return null;
        }

    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.UserLogDao#getUnsuccessfulLoginAttempts(java.util.Date, java.util.Date)
     */
    @Override
    public List<UserLog> getUnsuccessfulLoginAttempts(Date fromDate, Date toDate) {
        //final Session session = getSession();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<UserLog> criteriaQuery = builder.createQuery(UserLog.class);
        final Root<UserLog> root = criteriaQuery.from(UserLog.class);
        criteriaQuery.select(root).where(builder.equal(root.get(LOG_TYPE), UserMgmtOperations.LOGIN), builder.equal(root.get(SUCCESS), 0), builder.notEqual(root.get(USER_ID), 0), builder.between(root.get(CREATED_TIME), DateUtils.getStartDate(fromDate),
                DateUtils.getEndDate(toDate))).orderBy(builder.asc(root.get(USER_ID)));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.UserLogDao#getLoginReports(com.isl.gbm.user.management.dto.ReportSearchCriteriaDto)
     */
    @Override
    public List<UserLog> getLoginReports(ReportSearchCriteriaDto reportSearchCriteriaDto) {
        final Session session = getSession();
        Query<UserLog> query = null;
        if (StringUtils.isNotBlank(reportSearchCriteriaDto.getUsername()) && StringUtils.isNotBlank(reportSearchCriteriaDto.getSolId())) {
            query = session.createQuery(
                    "from UserLog where logType= :logType and solId= :solId and success= :success and userName= :username and createdTime between :startDate and :endDate",
                    UserLog.class);
            query.setParameter(USERNAME, reportSearchCriteriaDto.getUsername());
            query.setParameter(SOL_ID, reportSearchCriteriaDto.getSolId());
        }
        else if(StringUtils.isNotBlank(reportSearchCriteriaDto.getUsername())){
            query = session.createQuery(
                    "from UserLog where logType= :logType and success= :success and userName= :username and createdTime between :startDate and :endDate",
                    UserLog.class);
            query.setParameter(USERNAME, reportSearchCriteriaDto.getUsername());
        }
        else if(StringUtils.isNotBlank(reportSearchCriteriaDto.getSolId())){
            query = session.createQuery(
                    "from UserLog where logType= :logType and solId= :solId and success= :success and createdTime between :startDate and :endDate",
                    UserLog.class);
            query.setParameter(SOL_ID, reportSearchCriteriaDto.getSolId());
        }
        else {
            query = session.createQuery(
                    "from UserLog where logType= :logType and success= :success and createdTime between :startDate and :endDate",
                    UserLog.class);
        }

        //query.setParameter(SOL_ID, reportSearchCriteriaDto.getSolId());
        query.setParameter(LOG_TYPE, "LOGIN");
        query.setParameter(SUCCESS, 1);
        query.setParameter(START_DATE, DateUtils.getStartDate(reportSearchCriteriaDto.getFromDate()));
        query.setParameter(END_DATE, DateUtils.getEndDate(reportSearchCriteriaDto.getToDate()));

        return query.getResultList();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.UserLogDao#getMultipleDeviceLogin(java.lang.String, java.util.Date)
     */
    @Override
    public List<UserLog> getMultipleDeviceLogin(String solId, Date date) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<UserLog> criteriaQuery = builder.createQuery(UserLog.class);
        final Root<UserLog> root = criteriaQuery.from(UserLog.class);
        criteriaQuery.select(root).where(builder.equal(root.get(LOG_TYPE), UserMgmtOperations.LOGIN), builder.equal(root.get(SOL_ID), solId), builder.equal(root.get(SUCCESS), 1), builder.between(root.get(CREATED_TIME), DateUtils.getStartDate(date),
                DateUtils.getEndDate(date))).orderBy(builder.asc(root.get(USER_ID)));

        return session.createQuery(criteriaQuery).getResultList();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.UserLogDao#getDormantUsersReport(java.util.Date, java.util.Date)
     */
    @Override
    public List<UserLog> getDormantUsersReport(Date from, Date to) {

        final Session session = getSession();
        NativeQuery nativeQuery = null;
        final Query<Object[]> query = session.createNativeQuery(
                "SELECT ID,USER_ID,LOG_TYPE,CREATED_TIME,IP_ADDRESS,SUCCESS,USERNAME,USER_AGENT,RESPONSE_PAYLOAD,SOL_ID,SESSION_ID,LOGOUT_TIME FROM GBM_USER_MGMT_USER_LOG a where a.id=(select MAX(id) from GBM_USER_MGMT_USER_LOG l where l.USER_ID = a.USER_ID) and a.LOG_TYPE= :logType and a.SUCCESS=:success and a.CREATED_TIME between :startDate and :endDate");

        query.setParameter(LOG_TYPE, UserMgmtOperations.LOGIN);
        query.setParameter(SUCCESS, 1);
        query.setParameter(START_DATE, DateUtils.getStartDate(from));
        query.setParameter(END_DATE, DateUtils.getEndDate(to));
        List<UserLog> gbmNpsPranErrorDtoList = new ArrayList<>();
        UserLog userLog = null;
        final List<Object[]> rows = query.getResultList();
        for (final Object[] objArray : rows) {
            userLog = new UserLog();
            userLog.setId(Integer.valueOf(String.valueOf((BigDecimal) objArray[0])));
            userLog.setUserId(Integer.valueOf(String.valueOf((BigDecimal) objArray[1])));
            userLog.setLogType((String) objArray[2]);
            userLog.setCreatedTime((Date) objArray[3]);
            userLog.setIpAddress((String) objArray[4]);
            userLog.setSuccess(Integer.valueOf(String.valueOf((BigDecimal) objArray[5])));
            userLog.setUsername((String) objArray[6]);
            userLog.setUserAgent((String) objArray[7]);
            //userLog.setResponsePayload((String) objArray[8]);
            userLog.setSolId((String) objArray[9]);
            userLog.setSessionId((String) objArray[10]);
            userLog.setLogoutTime((Date) objArray[11]);
            gbmNpsPranErrorDtoList.add(userLog);
        }
        return gbmNpsPranErrorDtoList;

    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.dao.UserLogDao#getUserLogBySessionId(java.lang.String)
     */
    @Override
    public UserLog getUserLogBySessionId(String sessionId) {

        final Session session = getSession();

        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<UserLog> criteriaQuery = builder.createQuery(UserLog.class);
        final Root<UserLog> root = criteriaQuery.from(UserLog.class);
        criteriaQuery.select(root).where(builder.equal(root.get(LOG_TYPE), UserMgmtOperations.LOGIN), builder.equal(root.get(SUCCESS), 1), builder.equal(root.get(SESSION_ID), sessionId));
        return session.createQuery(criteriaQuery).uniqueResult();
    }

    @Override
    public void updateUserLogAfterLogOut(List<ActiveUserDto> activeUserDtoList, String logOutReason, String logOutReasonDesc) {
//        final Session session = getSession();
        Subject currentUser = SecurityUtils.getSubject();
//        long millis=System.currentTimeMillis();
//        java.sql.Date date=new java.sql.Date(millis);
//        //Date date = new Date();
//        Query<User> query = session.createQuery("UPDATE UserLog ul SET ul.logoutTime=:logoutTime,ul.logoutReason=:logOutReason,ul.logoutReasonDesc=:logOutReasonDesc WHERE ul.userId in (:userIdAndSessionIdList) AND ul.sessionId =:sessionId");
//        query.setParameter("logOutReason", logOutReason);
//        query.setParameter("logOutReasonDesc", logOutReasonDesc);
//        query.setParameter("logoutTime", date);
//       query.setParameterList("userIdAndSessionIdList", activeUserDtoList.stream().map(item -> (item.getUserId())).collect(Collectors.toList()));
//        query.setParameterList("sessionId", Collections.singleton(currentUser.getSession().getId().toString()));
//        query.executeUpdate();

        Connection connection = null;
//        PreparedStatement stmt = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        String result = " ";
        try {
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_LOGOUT_USER_ID.getProcName());
                stmt.setString(index++, activeUserDtoList.get(0).getUserId().toString());
                stmt.setString(index++, currentUser.getSession().getId().toString());
                stmt.setString(index++, logOutReason);
                stmt.setString(index++, logOutReasonDesc);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                result = stmt.getString(index);
                log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<LOGOUT STATUS>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
//                responseDataDTO.setMessage(result);
            } else {
//                responseDataDTO.setMessage(ErrorMessages.INTERNAL_SERVER_ERROR);
                log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<LOGOUT STATUS ERROR>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

    }

}
