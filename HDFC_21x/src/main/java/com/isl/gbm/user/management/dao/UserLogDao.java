package com.isl.gbm.user.management.dao;

import com.isl.gbm.user.management.dto.ActiveUserDto;
import com.isl.gbm.user.management.dto.ReportSearchCriteriaDto;
import com.isl.gbm.user.management.model.UserLog;

import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserLogDao.
 */

/**
 * @author Anoop.Kumar
 */
public interface UserLogDao {

    /**
     * Save.
     *
     * @param userLog the user log
     */
    void save(UserLog userLog);


    /**
     * Gets the last success login info.
     *
     * @param userId the user id
     * @return the last success login info
     */
    UserLog getLastSuccessLoginInfo(Integer userId);

    /**
     * Gets the unsuccessful login attempts.
     *
     * @param fromDate the from date
     * @param toDate   the to date
     * @return the unsuccessful login attempts
     */
    List<UserLog> getUnsuccessfulLoginAttempts(Date fromDate, Date toDate);


    /**
     * Gets the login reports.
     *
     * @param reportSearchCriteriaDto the report search criteria dto
     * @return the login reports
     */
    List<UserLog> getLoginReports(ReportSearchCriteriaDto reportSearchCriteriaDto);


    /**
     * Gets the multiple device login.
     *
     * @param solId the sol id
     * @param date  the date
     * @return the multiple device login
     */
    List<UserLog> getMultipleDeviceLogin(String solId, Date date);


    /**
     * Gets the dormant users report.
     *
     * @param from the from
     * @param to   the to
     * @return the dormant users report
     */
    List<UserLog> getDormantUsersReport(Date from, Date to);

    /**
     * Gets the user log by session id.
     *
     * @param sessionId the session id
     * @return the user log by session id
     */
    UserLog getUserLogBySessionId(String sessionId);


    void updateUserLogAfterLogOut(List<ActiveUserDto> activeUserDtoList, String logOutReason, String logOutReasonDesc);

}
