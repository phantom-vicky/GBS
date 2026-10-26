package com.isl.gbm.user.management.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.model.User;

import java.util.List;
import java.util.Set;

/**
 * The Interface UserDao.
 */
public interface UserDao {

    /**
     * Save.
     *
     * @param user the user
     */
    void save(User user);

    /**
     * Gets the user.
     *
     * @param id the id
     * @return the user
     */
    User getUser(Integer id);

    /**
     * Gets the user by Username.
     *
     * @param username the username
     * @return the user byusername
     */
    User getUserByUsername(String username);

    /**
     * Gets the users.
     *
     * @return the users
     */
    List<User> getUsers();

    /**
     * Gets the users by role.
     *
     * @param id the id
     * @return the users by role
     */
    List<User> getUsersByRole(Integer id);

    /**
     * Gets the locked users.
     *
     * @return the locked users
     */
    List<User> getLockedUsers();

    ResponseDataDTO saveUser(User user);

    ResponseDataDTO inquiryUserDetails(String userId);

    ResponseDataDTO verifyUserDetails(String userId);

    void update(User user);

    Set<String> getRoleSet();

    void updateLogoutStatusForUsersById(List<Integer> userIdList);

   // List<Object[]> postLoginUserDetailsTest( );

    ResponseDataDTO verifyUser(UserDetailsDto user);

    ResponseDataDTO deleteUser(UserDetailsDto user);

    ResponseDataDTO modifyUser(UserDetailsDto user);

    ResponseDataDTO modifyUserDetails(String userId);

    ClientDetailsDTO getClientInfo();

    ResponseDataDTO postLoginUserDetails();
    ResponseDataDTO getAgreementStatus();

    ResponseDataDTO saveAgreement(AgreementDTO agreementDTO);
    ResponseDataDTO getCurrentLoggedInBranchActiveUsers(UserAdminSackDTO userAdminSackDTO);
    EodSessionDto getCurrentLoggedInBranchActiveUser(UserAdminSackDTO userAdminSackDTO);
    public ResponseDataDTO getUserReports(ReportSearchCriteriaDto reportSearchCriteriaDto);

    public ResponseDataDTO activateUserReport(ReportSearchCriteriaDto reportSearchCriteriaDto);

   public ResponseDataDTO getAccessControlMatrixreport(AccessControlMatrixReportDTO accessControlMatrixReportDTO);

    public ResponseDataDTO getPopulationData(PopulationReportDTO populationReportDTO);

    public ResponseDataDTO getRoleRightsReport(RoleRightsReportDTO roleRightsReportDTO);

   public ResponseDataDTO getUserLogin(String username);

    ResponseDataDTO forceLogoutSingleUser(String username);

    void updateLogoutStatusForUsersById(User user);

   public ResponseDataDTO getUserActivityDetails(UserActivityReportDTO userActivityReportDTO);


   public ResponseDataDTO getModuleName(UserActivityReportDTO userActivityReportDTO);

    void updateLogoutForUsersById(String id);

    ResponseDataDTO adminActivityReport(NewAdminActivityReportDTO newAdminActivityReportDTO);

    ResponseDataDTO loginLogoutReport(LoginLogoutReportDTO loginLogoutReportDTO);
}
