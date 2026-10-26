package com.isl.gbm.user.management.service;


import com.isl.gbm.user.management.dto.AdminLogDto;
import com.isl.gbm.user.management.dto.UserLogDto;

/**
 * The Interface LogService.
 */
public interface LogService {

    /**
     * Save user log.
     *
     * @param userLogDto the user log dto
     */
    void saveUserLog(UserLogDto userLogDto);

    /**
     * Save admin log.
     *
     * @param adminLogDto the admin log dto
     */
    void saveAdminLog(AdminLogDto adminLogDto);


    /**
     * Save logout time.
     *
     * @param sessionId the session id
     * @param logOutReason
     * @param logOutReasonDesc
     */
    void saveLogoutTime(String sessionId, String logOutReason, String logOutReasonDesc);


}
