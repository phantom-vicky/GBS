package com.isl.gbm.user.management.manager;

import com.isl.gbm.common.DateUtils;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.user.management.dao.*;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.model.*;
import com.isl.gbm.user.management.service.UserParameterService;
import com.isl.gbm.user.management.service.UserService;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


/**
 * The Class UserManager.
 */
@Component
public class UserManager {

    /**
     * The user dao.
     */
    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    private UserDao userDao;

    /**
     * The user log dao.
     */
    @Autowired
    private UserLogDao userLogDao;

    /**
     * The user param dao.
     */
    @Autowired
    private UserParamDao userParamDao;

    /**
     * The password history dao.
     */
    @Autowired
    private PasswordHistoryDao passwordHistoryDao;

    /**
     * The permission dao.
     */
    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private UserParameterService parameterService;

    private static final Logger log = LoggerFactory.getLogger(UserManager.class);

    /**
     * Sets the user dao.
     *
     * @param userDao the new user dao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Sets the permission dao.
     *
     * @param permissionDao the new permission dao
     */
    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    /**
     * Sets the password history dao.
     *
     * @param passwordHistoryDao the new password history dao
     */
    public void setPasswordHistoryDao(PasswordHistoryDao passwordHistoryDao) {
        this.passwordHistoryDao = passwordHistoryDao;
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
     * Sets the user log dao.
     *
     * @param userLogDao the new user log dao
     */
    public void setUserLogDao(UserLogDao userLogDao) {
        this.userLogDao = userLogDao;
    }

    /**
     * Update login retry count.
     *
     * @param retryCount the retry count
     */
    public void updateLoginRetryCount(Integer retryCount) {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        if(retryCount==0){
            user.setInvalidLoginCount(0);
        }
        else {
            try{
                ResponseDataDTO data = parameterService.getUserParams();
                int MaxOfRetriesConfigured;
                // Null check for data and its result
                if (data != null && data.getResult() != null) {
                    // Safely cast the result to List<UserParamDto>
                    List<UserParamDto> dto = (List<UserParamDto>) data.getResult();

                    // Null check for the list and check if the list is not empty
                    if (!dto.isEmpty()) {
                        // Safely access the first element and get the noOfRetries1 value
                        MaxOfRetriesConfigured = dto.get(0) != null ? dto.get(0).getNoOfRetries1() : 3;
                        // Use MaxOfRetriesConfigured here...
                    } else {
                        // Handle case when dto is empty
                        // e.g., log a warning or set a default value
                        MaxOfRetriesConfigured = 3;
                    }
                } else {
                    // Handle case when data or data.getResult() is null
                    // e.g., log a warning or set a default value
                    MaxOfRetriesConfigured = 3;
                }

                if (null!=user&&null!=user.getInvalidLoginCount()&&user.getInvalidLoginCount() >= MaxOfRetriesConfigured) {
                    this.userService.lockUser();
                    throw new ExcessiveAttemptsException();
                }
                user.setInvalidLoginCount(user.getInvalidLoginCount() + 1);
            }
            catch (ExcessiveAttemptsException ex) {
                throw ex;
            }
            catch (Exception e) {
                log.error("Exception occurred while updating login retry count: ", e);
                throw new RuntimeException("Internal error while updating retry count", e);
            }
        }
        this.userDao.save(user);
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public UserDto getUser() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        return this.mapUserModelToDto(user);
    }

    /**
     * Update password.
     *
     * @param password the password
     */
    public void updatePassword(String password,String id) {
        User user;
        if(id != null) {
            user = this.userDao.getUser(Integer.parseInt(id));
        } else {
            user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        }
        user.setPassword(password);
        user.setPasswordModifiedDate(new Date());
        user.setPasswordExpiryDate(DateUtils.addDays(new Date(), this.userParamDao.getUserParam().getPasswordAge()));
        if (user.getStatus().equals(UserStatus.INACTIVE.getId())) {
            user.setActivationTime(new Date());
            user.setStatus(UserStatus.ACTIVE.getId());
        }
        userDao.save(user);
        savePasswordHistory(user);
    }

    /**
     * Update user last login attempt.
     */
    public void updateUserLastLoginAttempt() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        user.setLastLoginAttempt(new Date());
        this.userDao.save(user);
    }

    /**
     * Save password history.
     *
     * @param user the user
     */
    private void savePasswordHistory(User user) {
        PasswordHistory passwordHistory = new PasswordHistory();
        passwordHistory.setUsername(UserAccountUtils.getCurrentUsername());
        passwordHistory.setCreatedDate(user.getPasswordModifiedDate());
        passwordHistory.setPassword(user.getPassword());
        passwordHistoryDao.save(passwordHistory);
    }

    /**
     * Checks if is password recently used.
     *
     * @param password the password
     * @param oldPasswordExclusionCount
     * @return the boolean
     */
    public Boolean isPasswordRecentlyUsed(String password, Integer oldPasswordExclusionCount) {
        return this.passwordHistoryDao
                .getPasswordHistory(UserAccountUtils.getCurrentUsername(),
                        oldPasswordExclusionCount)
                .stream().anyMatch(passwordHistory -> BCrypt.checkpw(password, passwordHistory.getPassword()));
    }


    public UserDetailsValidationDTO getUserValidationDetailsForLogin(){

        UserDetailsValidationDTO userDetailsValidationDTO = new UserDetailsValidationDTO();
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());

        UserParam userParam =this.userParamDao.getUserParam();

        userDetailsValidationDTO.setActivationPeriodExpired( new Date().after(DateUtils.addDays(user.getCreatedDate(),
                userParam.getActivationPeriod())));


        //checking is dormant
        if (user.getLastSuccessLogin() != null) {
            userDetailsValidationDTO.setDormant(new Date().after(DateUtils.addDays(user.getLastSuccessLogin(), userParam.getDormancyPeriod())));
        } else {
            userDetailsValidationDTO.setDormant(false);
            //here checking inactivity of user who still login for single time
            boolean isTimeReached = new Date().after(DateUtils.addDays(user.getVerifiedDate(), userParam.getMaxAcceptPeriod()));
            boolean isVerified = user.getVerifiedStatus().equals('Y') && user.getIsActive().equals('Y');
            if(isTimeReached && isVerified){
                userDetailsValidationDTO.setUserInactiveNoLoginAttemptEver(true);
            }

        }

        //checking is user expired
        userDetailsValidationDTO.setUserExpied( UserType.CONTRACT.getId().equals(user.getUserType()) && new Date().after(user.getUserExpiryDate()));


        //checking user block time over
        userDetailsValidationDTO.setUserBlockTimeOver(user.getUserDisabledTill() != null && new Date().after(user.getUserDisabledTill()));

        return userDetailsValidationDTO;

    }


    /**
     * Checks if is activation period expired.
     *
     * @return the boolean
     */
    public Boolean isActivationPeriodExpired()
    {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        Date expiryDate = DateUtils.addDays(user.getCreatedDate(),
                this.userParamDao.getUserParam().getActivationPeriod());
        return new Date().after(expiryDate);
    }
    /**
     * Checks if is dormant.
     *
     * @return the boolean
     */
    public Boolean isDormant() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        Date expiryDate = null;
        if (user.getLastSuccessLogin() != null) {
            expiryDate = DateUtils.addDays(user.getLastSuccessLogin(), this.userParamDao.getUserParam().getDormancyPeriod());
            return new Date().after(expiryDate);
        } else {
            return false;
        }

    }

    /**
     * Checks if is password expired.
     *
     * @return the boolean
     */
    public Boolean isPasswordExpired() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        return new Date().after(user.getPasswordExpiryDate());
    }

    /**
     * Map user model to dto.
     *
     * @param user the user
     * @return the user
     */
    private UserDto mapUserModelToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setApplicationName(user.getApplicationName());
        userDto.setDepartmentCode(user.getDepartmentCode());
        userDto.setEmployeeCode(user.getEmployeeCode());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setLocationCode(user.getLocationCode());
        userDto.setSolId(user.getSolId());
        userDto.setSupervisorCode(user.getSupervisorCode());
        userDto.setUserType(user.getUserType());
        userDto.setUserExpiryDate(user.getUserExpiryDate());
        userDto.setUsername(user.getUsername());
        userDto.setActivationTime(user.getActivationTime());
        userDto.setCreatedByUser(user.getCreatedByUser());
        userDto.setCreatedDate(user.getCreatedDate());
        userDto.setDeleted(user.getDeleted());
        userDto.setModifiedByUser(user.getModifiedByUser());
        userDto.setModifiedDate(user.getModifiedDate());
        userDto.setPasswordExpiryDate(user.getPasswordExpiryDate());
        userDto.setPasswordModifiedDate(user.getPasswordModifiedDate());
        userDto.setStatus(UserStatus.findById(user.getStatus()).getStatus());
        userDto.setUserDisabledTill(user.getUserDisabledTill());
        userDto.setUserExpiryDate(user.getUserExpiryDate());
        userDto.setVerifiedByUser(user.getVerifiedByUser());
        userDto.setVerifiedDate(user.getVerifiedDate());
        userDto.setLastLoginAttempt(user.getLastLoginAttempt());
        userDto.setInvalidLoginCount(user.getInvalidLoginCount());
        userDto.setUserDomain(UserDomain.findById(user.getUserDomain()).getDomain());
        return userDto;
    }

    /**
     * Allow old passwords.
     *
     * @return the boolean
     */
    public Boolean allowOldPasswords() {
        return this.userParamDao.getUserParam().getAllowOldPassword() == 1 ? true : false;
    }

    /**
     * Checks if is user expied.
     *
     * @return the boolean
     */
    public Boolean isUserExpied() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        return UserType.CONTRACT.getId().equals(user.getUserType()) && new Date().after(user.getUserExpiryDate());
    }

    /**
     * Checks if is user block time over.
     *
     * @return the boolean
     */
    public Boolean isUserBlockTimeOver() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        return user.getUserDisabledTill() != null && new Date().after(user.getUserDisabledTill());
    }

    /**
     * Gets the permissions.
     *
     * @return the permissions
     */
    public Set<String> getPermissions() {
        return new HashSet<>(permissionDao.getPermissionName());
    }

    /**
     * Gets the permissions.
     *
     * @param permissions the permissions
     * @return the permissions
     */
    private Set<String> getPermissions(Collection<Permission> permissions) {
        Set<String> permissionSet = new HashSet<>();
        for (Permission permission : permissions) {
            permissionSet.add(permission.getDisplayName());
        }
        return permissionSet;
    }

    /**
     * Gets the last success login info.
     *
     * @return the last success login info
     */
    public Date getLastSuccessLoginInfo() {
        UserLog userLog = this.userLogDao.getLastSuccessLoginInfo(UserAccountUtils.getCurrentUserID());
        if (userLog == null) {
            return new Date();
        }
        return userLog.getCreatedTime();
    }

    /**
     * Lock user.
     */
    public void lockUser() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        user.setStatus(UserStatus.LOCKED.getId());
        this.userDao.save(user);
    }

    /**
     * Sets the account expired.
     */
    public void setAccountExpired() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        user.setStatus(UserStatus.EXPIRED.getId());
        this.userDao.save(user);
    }

    /**
     * Enable account.
     */
    public void enableAccount() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        user.setStatus(UserStatus.ACTIVE.getId());
        user.setUserDisabledTill(null);
        this.userDao.save(user);
    }

    /**
     * Disable account.
     */
    public void disableAccount() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        user.setStatus(UserStatus.DISABLED.getId());
        user.setUserDisabledTill(null);
        this.userDao.save(user);
    }

    /**
     * Update user last success login.
     */
    public void updateUserLastSuccessLogin() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        user.setLastSuccessLogin(new Date());
        user.setLoggedInFlag(CommonConstants.YES);
        user.setInvalidLoginCount(0);
        this.userDao.save(user);
    }

    //    public void logOutUser() {
//        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
//        user.setLoggedInFlag(CommonConstants.NO);
//        this.userDao.save(user);
//    }
    public void logOutUser() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        user.setLoggedInFlag(CommonConstants.NO);
        if(UserAccountUtils.getCurrentUserID()!=null) {
            user.setId(UserAccountUtils.getCurrentUserID());
        } else{
            user.setId(0);
        }
        this.userDao.updateLogoutStatusForUsersById(user);
    }
    /**
     * Returns old password exclusion count from User Parameter Table
     *
     * @return oldPasswordExclusionCount
     */
    public Integer getUserOldPasswordExclusionCount() {
        return this.userParamDao.getUserParam().getOldPasswordExclusionCount();
    }

    public ClientDetailsDTO getClientInfo() {
        return this.userDao.getClientInfo();
    }

    public ResponseDataDTO postLoginUserDetails(){
        ResponseDataDTO responseDataDTO = userDao.postLoginUserDetails();
//        PostLoginUserDetailsDTO postLoginUserDetailsDTO = (PostLoginUserDetailsDTO) responseDataDTO.getResult();
        PostLoginUserDetailsDTO postLoginUserDetailsDTO= null;
        if(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
            postLoginUserDetailsDTO = (PostLoginUserDetailsDTO) responseDataDTO.getResult();
        } else {
            return responseDataDTO;
        }
        Map<String, PermissionMenuLinkDTO> linksDataMap = new LinkedHashMap<>();
        try {
            for (PostLoginUserDetailsDTO.ColumnWiseMenuList item : postLoginUserDetailsDTO.getColumnWiseMenuList()) {
                String[] records = item.getColumnWiseMenu().split("\\^");

                if (!linksDataMap.containsKey(records[0])) {
                    PermissionMenuLinkDTO permissionMenuLinkDTO = new PermissionMenuLinkDTO();
                    permissionMenuLinkDTO.setLevel(records[2]);
                    permissionMenuLinkDTO.setLinkId(records[0]);
                    permissionMenuLinkDTO.setLinkName(records[3]);
                    permissionMenuLinkDTO.setParentLinkId(records[1]);
                    permissionMenuLinkDTO.setMenuUrl(records[4]);
                    permissionMenuLinkDTO.setIsLink(records[5]);
                    permissionMenuLinkDTO.setOrderId(records[6]);
                    linksDataMap.put(records[0], permissionMenuLinkDTO);
                }

            }

            for (PermissionMenuLinkDTO item : linksDataMap.values()) {
                if (Integer.valueOf(item.getParentLinkId()) > 0) {
                    if (linksDataMap.get(item.getParentLinkId()).getChildren() == null)
                        linksDataMap.get(item.getParentLinkId()).setChildren(new ArrayList<>());
                    linksDataMap.get(item.getParentLinkId()).getChildren().add(item);
                }
            }

            Map<String, Object> resMap = new HashMap<>();
            resMap.put("menuItemList", linksDataMap.values().stream().filter(item -> item.getLevel().equals("1")).collect(Collectors.toList()));
            postLoginUserDetailsDTO.setMenuLinksList(resMap);
        }catch (Exception e){
            log.info("Exception"+e.getMessage());
        }
        responseDataDTO.setResult(postLoginUserDetailsDTO);
        return responseDataDTO;
    }



    public ResponseDataDTO getAgreementStatus() {
        return this.userDao.getAgreementStatus();
    }

    public ResponseDataDTO saveAgreement(AgreementDTO agreementDTO) {
        return this.userDao.saveAgreement(agreementDTO);
    }

    public void inActiveUser() {
        User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
        user.setStatus(UserStatus.INACTIVE.getId());
        this.userDao.save(user);
    }
}
