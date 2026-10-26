package com.isl.gbm.user.management.manager;

import com.isl.gbm.user.management.dao.AdminLogDao;
import com.isl.gbm.user.management.dao.UserLogDao;
import com.isl.gbm.user.management.dto.AdminLogDto;
import com.isl.gbm.user.management.dto.UserLogDto;
import com.isl.gbm.user.management.model.AdminLog;
import com.isl.gbm.user.management.model.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The Class LogManager.
 */
@Component
public class LogManager {

    /**
     * The user log dao.
     */
    @Autowired
    private UserLogDao userLogDao;

    /**
     * The admin log dao.
     */
    @Autowired
    private AdminLogDao adminLogDao;


    /**
     * Save admin log.
     *
     * @param adminLogDto the admin log dto
     */
    public void saveAdminLog(AdminLogDto adminLogDto) {
        this.adminLogDao.save(this.convertAdminLogDtoToModel(adminLogDto));
    }

    /**
     * Convert admin log dto to model.
     *
     * @param adminLogDto the admin log dto
     * @return the admin log
     */
    private AdminLog convertAdminLogDtoToModel(AdminLogDto adminLogDto) {
        AdminLog adminLog = new AdminLog();
        adminLog.setUserAgent(adminLogDto.getUserAgent());
        adminLog.setCreatedTime(new Date());
        adminLog.setId(adminLogDto.getId());
        adminLog.setIpAddress(adminLogDto.getIpAddress());
        adminLog.setLogType(adminLogDto.getLogType());
        adminLog.setSuccess(adminLogDto.getSuccess() ? 1 : 0);
        adminLog.setUserId(adminLogDto.getUserId());
        adminLog.setUsername(adminLogDto.getUsername());
        adminLog.setRequestPayload(adminLogDto.getRequestPayload());
        adminLog.setResponsePayload(adminLogDto.getResponsePayload());
        return adminLog;
    }

    /**
     * Save user log.
     *
     * @param userLogDto the user log dto
     */
    public void saveUserLog(UserLogDto userLogDto) {
        this.userLogDao.save(this.convertUserLogDtoToModel(userLogDto));
    }

    /**
     * Convert user log dto to model.
     *
     * @param userLogDto the user log dto
     * @return the user log
     */
    private UserLog convertUserLogDtoToModel(UserLogDto userLogDto) {
        UserLog userLog = new UserLog();
        userLog.setUserAgent(userLogDto.getUserAgent());
        userLog.setCreatedTime(new Date());
        userLog.setId(userLogDto.getId());
        userLog.setIpAddress(userLogDto.getIpAddress());
        userLog.setLogType(userLogDto.getLogType());
        userLog.setSuccess(userLogDto.getSuccess() ? 1 : 0);
        userLog.setUserId(userLogDto.getUserId());
        userLog.setUsername(userLogDto.getUsername());
        userLog.setResponsePayload(userLogDto.getResponsePayload());
        userLog.setSolId(userLogDto.getSolId());
        userLog.setSessionId(userLogDto.getSessionId());
        return userLog;
    }

    /**
     * Save logout time.
     *
     * @param sessionId the session id
     * @param logOutReason the logOut Reason
     * @param logOutReasonDesc the logOut Reason Desc
     */
    public void saveLogoutTime(String sessionId, String logOutReason, String logOutReasonDesc) {
        UserLog userLog = this.userLogDao.getUserLogBySessionId(sessionId);
        if (userLog != null) {
            userLog.setLogoutTime(new Date());
            userLog.setLogoutReason(logOutReason);
            userLog.setLogoutReasonDesc(logOutReasonDesc);
            this.userLogDao.save(userLog);
        }
    }

}
