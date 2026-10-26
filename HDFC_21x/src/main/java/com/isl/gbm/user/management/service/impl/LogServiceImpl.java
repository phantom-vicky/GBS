package com.isl.gbm.user.management.service.impl;


import com.isl.gbm.user.management.dto.AdminLogDto;
import com.isl.gbm.user.management.dto.UserLogDto;
import com.isl.gbm.user.management.manager.LogManager;
import com.isl.gbm.user.management.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class LogServiceImpl.
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {

    /**
     * The log manager.
     */
    @Autowired
    private LogManager logManager;

    /**
     * Sets the log manager.
     *
     * @paramlogManager the new log manager
     */


    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.LogService#saveUserLog(com.isl.gbm.user.management.dto.UserLogDto)
     */
    @Override
    public void saveUserLog(UserLogDto userLogDto) {
        this.logManager.saveUserLog(userLogDto);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.LogService#saveAdminLog(com.isl.gbm.user.management.dto.AdminLogDto)
     */
    @Override
    public void saveAdminLog(AdminLogDto adminLogDto) {
        this.logManager.saveAdminLog(adminLogDto);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.LogService#saveLogoutTime(java.lang.String)
     */
    @Override
    public void saveLogoutTime(String sessionId, String logOutReason, String logOutReasonDesc) {
        this.logManager.saveLogoutTime(sessionId, logOutReason, logOutReasonDesc);
    }
}
