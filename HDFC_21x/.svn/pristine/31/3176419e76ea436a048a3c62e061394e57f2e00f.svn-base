package com.isl.gbm.user.management.manager;

import com.isl.gbm.common.DateUtils;
import com.isl.gbm.user.management.dao.AdminLogDao;
import com.isl.gbm.user.management.dao.UserDao;
import com.isl.gbm.user.management.dao.UserLogDao;
import com.isl.gbm.user.management.dao.UserParamDao;
import com.isl.gbm.user.management.dto.AdminActivityReportDto;
import com.isl.gbm.user.management.dto.LoginReportDto;
import com.isl.gbm.user.management.dto.ReportSearchCriteriaDto;
import com.isl.gbm.user.management.model.AdminLog;
import com.isl.gbm.user.management.model.User;
import com.isl.gbm.user.management.model.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The Class ReportsManager.
 */
@Component
public class ReportsManager {

    /**
     * The Constant MAXIMUM_LOGIN_ATTEMPTS.
     */
    private static final int MAXIMUM_LOGIN_ATTEMPTS = 3;

    /**
     * The admin log dao.
     */
    @Autowired
    private AdminLogDao adminLogDao;

    /**
     * The user log dao.
     */
    @Autowired
    private UserLogDao userLogDao;

    /**
     * The user dao.
     */
    @Autowired
    private UserDao userDao;

    /**
     * The user param dao.
     */
    @Autowired
    private UserParamDao userParamDao;

    /**
     * Sets the admin log dao.
     *
     * @param adminLogDao the new admin log dao
     */
    public void setAdminLogDao(AdminLogDao adminLogDao) {
        this.adminLogDao = adminLogDao;
    }

    /**
     * Sets the user dao.
     *
     * @param userDao the new user dao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Sets the user log dao.
     *
     * @param userLogDao the new user log dao
     */
    public void setUserLogDao(UserLogDao userLogDao) {
        this.userLogDao = userLogDao;
    }

    /**
     * Sets the user param dao.
     *
     * @param userParamDao the new user param dao
     */
    public void setUserParamDao(UserParamDao userParamDao) {
        this.userParamDao = userParamDao;
    }

    /**
     * Gets the unsuccessful login atempts.
     *
     * @param fromDate the from date
     * @param toDate   the to date
     * @return the unsuccessful login atempts
     */
    public List<LoginReportDto> getUnsuccessfulLoginAtempts(Date fromDate, Date toDate) {
        List<LoginReportDto> loginReportDtos = new ArrayList<>();
        List<UserLog> userLogs = this.userLogDao.getUnsuccessfulLoginAttempts(fromDate, toDate);
        for (UserLog userLog : userLogs) {
            loginReportDtos.add(this.convertUserLogModelToDto(userLog));
        }

        return this.getMultipleFailedLoginAttempts(loginReportDtos);
    }


    /**
     * Gets the multiple failed login attempts.
     *
     * @param loginReportDtos the login report dtos
     * @return the multiple failed login attempts
     */
    private List<LoginReportDto> getMultipleFailedLoginAttempts(List<LoginReportDto> loginReportDtos) {
        List<LoginReportDto> mutlipleLogins = new ArrayList<>();
        Integer tempId = null;
        for (LoginReportDto loginReportDto : loginReportDtos) {
            if (!loginReportDto.getUserId().equals(tempId) && loginReportDto.getUserId() != null) {

                final Integer tempUserId = loginReportDto.getUserId();
                List<LoginReportDto> tempList = loginReportDtos.stream().filter(obj -> obj.getUserId().equals(tempUserId)).collect(Collectors.toList());
                if (tempList.size() >= MAXIMUM_LOGIN_ATTEMPTS) {
                    mutlipleLogins.addAll(tempList);
                }
                tempId = tempUserId;
            }
        }
        mutlipleLogins.sort((o1, o2) -> o1.getLoginDate().compareTo(o2.getLoginDate()));
        return mutlipleLogins;
    }

    /**
     * Convert user log model to dto.
     *
     * @param userLog the user log
     * @return the unsuccessful login report dto
     */
    private LoginReportDto convertUserLogModelToDto(UserLog userLog) {
        LoginReportDto loginReportDto = new LoginReportDto();
        loginReportDto.setIp(userLog.getIpAddress());
        User user = this.userDao.getUser(userLog.getUserId());
        if (null != user) {
            loginReportDto.setUsername(user.getUsername());
            loginReportDto.setSolId(user.getSolId());
            loginReportDto.setUserId(user.getId());
            loginReportDto.setEmployeeCode(user.getEmployeeCode());
            loginReportDto.setFirstName(user.getFirstName());
            loginReportDto.setLastName(user.getLastName());
            loginReportDto.setLoginDate(userLog.getCreatedTime());
            loginReportDto.setLogoutDate(userLog.getLogoutTime());
            loginReportDto.setId(userLog.getId());
        }
        return loginReportDto;
    }

    /**
     * Gets the admin activities.
     *
     * @param date the date
     * @return the admin activities
     */
    public List<AdminActivityReportDto> getAdminActivities(Date date) {
        List<AdminActivityReportDto> adminActivityReportDtos = new ArrayList<>();
        for (AdminLog adminLog : this.adminLogDao.getAdminActivityLogs(date)) {
            adminActivityReportDtos.add(this.convertAdminLogModelToDto(adminLog));
        }
        return adminActivityReportDtos;
    }

    /**
     * Convert admin log model to dto.
     *
     * @param adminLog the admin log
     * @return the admin activity report dto
     */
    private AdminActivityReportDto convertAdminLogModelToDto(AdminLog adminLog) {
        AdminActivityReportDto adminActivityReportDto = new AdminActivityReportDto();
        adminActivityReportDto.setActivity(adminLog.getLogType());
        User user = this.userDao.getUser(adminLog.getUserId());
        adminActivityReportDto.setEmployeeCode(user.getEmployeeCode());
        adminActivityReportDto.setUsername(user.getUsername());
        adminActivityReportDto.setUserId(user.getId());
        adminActivityReportDto.setFirstName(user.getFirstName());
        adminActivityReportDto.setLastName(user.getLastName());
        return adminActivityReportDto;
    }

    /**
     * Gets the login reports.
     *
     * @param reportSearchCriteriaDto the report search criteria dto
     * @return the login reports
     */
    public List<LoginReportDto> getLoginReports(ReportSearchCriteriaDto reportSearchCriteriaDto) {
        List<LoginReportDto> loginReportDtos = new ArrayList<>();
        for (UserLog userLog : this.userLogDao.getLoginReports(reportSearchCriteriaDto)) {
            loginReportDtos.add(this.convertUserLogModelToDto(userLog));
        }
        loginReportDtos.sort((o1, o2) -> o1.getLoginDate().compareTo(o2.getLoginDate()));
        return loginReportDtos;
    }

    /**
     * Gets the multiple device login.
     *
     * @param solId the sol id
     * @param date  the date
     * @return the multiple device login
     */
    public List<LoginReportDto> getMultipleDeviceLogin(String solId, Date date) {
        List<LoginReportDto> loginReportDtos = new ArrayList<>();
        List<UserLog> userLogs = this.userLogDao.getMultipleDeviceLogin(solId, date);
        for (UserLog userLog : userLogs) {
            loginReportDtos.add(this.convertUserLogModelToDto(userLog));
        }

        return this.getMultipleLogins(loginReportDtos);

    }

    /**
     * Gets the multiple logins.
     *
     * @param loginReportDtos the login report dtos
     * @return the multiple logins
     */

    private List<LoginReportDto> getMultipleLogins(List<LoginReportDto> loginReportDtos) {
        List<LoginReportDto> mutlipleLogins = new ArrayList<>();
        Integer tempId = null;
        for (LoginReportDto loginReportDto : loginReportDtos) {
            if (!loginReportDto.getUserId().equals(tempId)) {

                final Integer tempUserId = loginReportDto.getUserId();
                List<LoginReportDto> tempList = loginReportDtos.stream().filter(obj -> obj.getUserId().equals(tempUserId)).collect(Collectors.toList());
                if (tempList.stream().filter(distinctByKey(LoginReportDto::getIp)).collect(Collectors.toList()).size() > 1) {
                    mutlipleLogins.addAll(tempList);
                }
                tempId = tempUserId;
            }
        }
        loginReportDtos.sort((o1, o2) -> o1.getLoginDate().compareTo(o2.getLoginDate()));
        return loginReportDtos;
    }

    /**
     * Distinct by key.
     *
     * @param <T>          the generic type
     * @param keyExtractor the key extractor
     * @return the predicate
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    /**
     * Gets the dormant users report.
     *
     * @param from the from
     * @param to   the to
     * @return the dormant users report
     */
    public List<LoginReportDto> getDormantUsersReport(Date from, Date to) {
        List<LoginReportDto> loginReportDtos = new ArrayList<>();
        int dormancyPeriod = this.userParamDao.getUserParam().getDormancyPeriod();
        List<UserLog> result = this.userLogDao.getDormantUsersReport(from, to);

        List<UserLog> userLogs = result.stream().filter(userLog -> new Date().after(DateUtils.addDays(userLog.getCreatedTime(), dormancyPeriod))).collect(Collectors.toList());
        for (UserLog userLog : userLogs) {
            loginReportDtos.add(this.convertUserLogModelToDto(userLog));
        }
        loginReportDtos.sort((o1, o2) -> o1.getLoginDate().compareTo(o2.getLoginDate()));
        return loginReportDtos;
    }

}
