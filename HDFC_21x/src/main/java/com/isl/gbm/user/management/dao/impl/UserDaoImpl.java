package com.isl.gbm.user.management.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dto.FileResponseDto;
import com.isl.gbm.rbibond.util.JDBCTemplateUtil;
import com.isl.gbm.rbibond.util.TrailRunDetailsResultTransformer;
import com.isl.gbm.user.management.constants.UserLogOutReasons;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.dao.UserDao;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.model.User;
import com.isl.gbm.user.management.model.UserStatus;
import com.isl.gbm.user.management.service.ReportsService;
import com.isl.gbm.user.management.util.ProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import static org.apache.poi.ss.util.CellUtil.createCell;


/**
 * The Class UserDaoImpl.
 */



@Repository
public class UserDaoImpl extends HibernateDao implements UserDao {
    /**
     * The Constant USERNAME.
     */
    private static final String USERNAME = "username";

    /**
     * The Constant USER_TYPE.
     */
    private static final String USER_TYPE = "userType";

    /**
     * The Constant STATUS.
     */
    private static final String STATUS = "status";

    private static final String VERIFIED_STATUS = "verifiedStatus";

    /**
     * The Constant ID.
     */
    private static final String ID = "id";

    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;


    ReportsService reportsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JDBCTemplateUtil jDBCTemplateUtil;

    @PostConstruct
    public void init() {
        jDBCTemplateUtil = new JDBCTemplateUtil(jdbcTemplate);
    }


    @PersistenceContext
    EntityManager em;



    /* (non-Javadoc)
     * @see com.afl.gbm.user.management.dao.UserDao#save(com.afl.gbm.user.management.model.User)
     */
    @Override
    public void save(User user) {
        Session session = getSession();
        session.saveOrUpdate(user);
    }
    @Value("${gbm.bankName}")
    private String bankName;

    @Override
    public ResponseDataDTO saveUser(User user) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_MGMT_ADD.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, user.getFirstName());
                stmt.setString(index++, user.getLastName());
                stmt.setString(index++, user.getSolId());
                stmt.setString(index++, user.getEmployeeCode());
                stmt.setString(index++, user.getApplicationName());
                stmt.setString(index++, user.getDepartmentCode());
                stmt.setString(index++, user.getSupervisorCode());
                stmt.setString(index++, user.getLocationCode());
                stmt.setString(index++, user.getUserType());
                stmt.setString(index++, user.getUsername());
                stmt.setString(index++, user.getUserDomain());
                stmt.setString(index++, user.getPassword());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
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
    public ResponseDataDTO verifyUser(UserDetailsDto user) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_MGMT_VER.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, user.getId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
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
    public EodSessionDto getCurrentLoggedInBranchActiveUser(UserAdminSackDTO userAdminSackDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ActiveUserDto activeUserDto = new ActiveUserDto();
        EodSessionDto eodSessionDto = new EodSessionDto();
        List<EodSessionDto> activeUserDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_LOGGIN_DETAIL.getProcName());
                stmt.setString(CommonConstants.P_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString("P_SOL_ID_IN", userAdminSackDTO.getBranchId());
                stmt.registerOutParameter("P_CURSOR_OUT", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject("P_CURSOR_OUT");

                    if (resultSet != null && userAdminSackDTO.getBranchId() != null) {
                        HashMap<String, ActiveUserDto> activeUsersMap = new HashMap<>();
                        ActiveUserDto activeUser =null;
                        while (resultSet.next()) {
                            activeUser = new ActiveUserDto();
                            activeUser.setUserId(resultSet.getInt(1));
                            activeUser.setUsername(resultSet.getString(2));
                            activeUser.setLoggedInSince(resultSet.getTimestamp(3));
                            activeUsersMap.put(resultSet.getString(2),activeUser);
                        }
                        eodSessionDto.setActiveUsersMap(activeUsersMap);
//                        responseDataDTO.setResult(activeUsersMap);
                    } else if(resultSet != null) {
                        HashMap<String, ActiveUserDto> branchIdList = new HashMap<>();
                        ActiveUserDto activeUser =null;
                        while (resultSet.next()) {
                            activeUser = new ActiveUserDto();
                            activeUser.setBranchId(resultSet.getString(1));
                            activeUser.setBranchDescription(resultSet.getString(2));
                            branchIdList.put(resultSet.getString(1),activeUser);
                        }
                        eodSessionDto.setBranchIdList(branchIdList);
//                        responseDataDTO.setResult(branchIdList);
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
//        responseDataDTO.setMessage(resultMsg);
        return eodSessionDto;
    }


    @Override
    public ResponseDataDTO deleteUser(UserDetailsDto user) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_MGMT_DEL.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, user.getId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
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
    public ResponseDataDTO modifyUser(UserDetailsDto user) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String formatDate = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_MGMT_MOD.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, user.getId());
                stmt.setString(index++, user.getFirstName());
                stmt.setString(index++, user.getLastName());
                stmt.setString(index++, user.getSolId());
                stmt.setString(index++, user.getEmployeeCode());
                stmt.setString(index++, user.getApplicationName());
                stmt.setString(index++, user.getDepartmentCode());
                stmt.setString(index++, user.getSupervisorCode());
                stmt.setString(index++, user.getLocationCode());
                stmt.setString(index++, user.getUserType());
                stmt.setString(index++, user.getUsername());
                stmt.setString(index++, user.getUserDomain());
                stmt.setString(index++, user.getPassword());
                stmt.setString(index++, user.getUserStatus());
                if (user.getUserDisabledTill() != null) {
                    SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
                    formatDate = f.format(user.getUserDisabledTill());
                }

                stmt.setString(index++, formatDate);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
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
    public ResponseDataDTO modifyUserDetails(String userId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        Date formatDate = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_MGMT_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, userId);
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(12);
                    if (StringUtils.isEmpty(userId)) {
                        List<UserDetailsDto> userList = new ArrayList<>();
                        UserDetailsDto userDto = null;

                        while (resultSet.next()) {
                            userDto = new UserDetailsDto();
                            userDto.setId(resultSet.getString(1));
                            userDto.setFirstName(resultSet.getString(2));
                            userDto.setLastName(resultSet.getString(3));
                            userDto.setSolId(resultSet.getString(4));
                            userDto.setEmployeeCode(resultSet.getString(5));
                            userDto.setApplicationName(resultSet.getString(6));
                            userDto.setDepartmentCode(resultSet.getString(7));
                            userDto.setSupervisorCode(resultSet.getString(8));
                            userDto.setLocationCode(resultSet.getString(9));
                            userDto.setUserType(resultSet.getString(10));
                            userDto.setUsername(resultSet.getString(11));
                            userDto.setUserDomain(resultSet.getString(12));
                            userDto.setUserStatus(resultSet.getString(14));

                            userList.add(userDto);
                        }
                        responseDataDTO.setResult(userList);
                    } else {
                        UserDetailsDto userDto = null;

                        if (resultSet.next()) {
                            userDto = new UserDetailsDto();
                            userDto.setId(resultSet.getString(1));
                            userDto.setFirstName(resultSet.getString(2));
                            userDto.setLastName(resultSet.getString(3));
                            userDto.setSolId(resultSet.getString(4));
                            userDto.setEmployeeCode(resultSet.getString(5));
                            userDto.setApplicationName(resultSet.getString(6));
                            userDto.setDepartmentCode(resultSet.getString(7));
                            userDto.setSupervisorCode(resultSet.getString(8));
                            userDto.setLocationCode(resultSet.getString(9));
                            userDto.setUserType(resultSet.getString(10));
                            userDto.setUsername(resultSet.getString(11));
                            userDto.setUserDomain(resultSet.getString(12));
                            userDto.setPassword(resultSet.getString(13));
                            userDto.setUserStatus(resultSet.getString(14));
                            if ("N".equalsIgnoreCase(userDto.getUserStatus()) && resultSet.getString(15) != null) {
                                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                                formatDate = f.parse(resultSet.getString(15));
                            }
                            userDto.setUserDisabledTill(formatDate);
                            userDto.setCreatedByUser(stmt.getString(4));
                            userDto.setCreatedDate(stmt.getString(5));
                            userDto.setModifiedByUser(stmt.getString(6));
                            userDto.setModifiedDate(stmt.getString(7));
                            userDto.setVerifiedByUser(stmt.getString(8));
                            userDto.setVerifiedDate(stmt.getString(9));
                            userDto.setPreOpr(stmt.getString(10));
                            userDto.setVerifyStatus(stmt.getString(11));
                        }
                        responseDataDTO.setResult(userDto);
                    }

                }
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
    public ClientDetailsDTO getClientInfo() {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Session session = getSession();
        NativeQuery nativeQuery = null;

        nativeQuery = session.createNativeQuery("SELECT CLIENT_ID as clientId,CLIENT_SECRETE as clientSecret,CHECKSUM_KEY as checkSumKey FROM ULTRAGBM_CLIENTINFO");
        final List<Object[]> list = nativeQuery.getResultList();
        ClientDetailsDTO clientDetailsDTO = null;
        for (Object obj[] : list) {
            clientDetailsDTO = new ClientDetailsDTO();
            clientDetailsDTO.setClientId((String) obj[0]);
            clientDetailsDTO.setClientSecret((String) obj[1]);
        }
        return clientDetailsDTO;
    }


    @Override
    public ResponseDataDTO inquiryUserDetails(String userId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        Date formatDate = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_MGMT_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, userId);
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(12);
                    if (StringUtils.isEmpty(userId)) {
                        List<UserDetailsDto> userList = new ArrayList<>();
                        UserDetailsDto userDto = null;

                        while (resultSet.next()) {
                            userDto = new UserDetailsDto();
                            userDto.setId(resultSet.getString(1));
                            userDto.setFirstName(resultSet.getString(2));
                            userDto.setLastName(resultSet.getString(3));
                            userDto.setSolId(resultSet.getString(4));
                            userDto.setEmployeeCode(resultSet.getString(5));
                            userDto.setApplicationName(resultSet.getString(6));
                            userDto.setDepartmentCode(resultSet.getString(7));
                            userDto.setSupervisorCode(resultSet.getString(8));
                            userDto.setLocationCode(resultSet.getString(9));
                            userDto.setUserType(resultSet.getString(10));
                            userDto.setUsername(resultSet.getString(11));
                            userDto.setUserDomain(resultSet.getString(12));
                            userDto.setUserStatus(resultSet.getString(14));
                            userList.add(userDto);
                        }
                        responseDataDTO.setResult(userList);
                    } else {
                        UserDetailsDto userDto = null;

                        if (resultSet.next()) {
                            userDto = new UserDetailsDto();
                            userDto.setId(resultSet.getString(1));
                            userDto.setFirstName(resultSet.getString(2));
                            userDto.setLastName(resultSet.getString(3));
                            userDto.setSolId(resultSet.getString(4));
                            userDto.setEmployeeCode(resultSet.getString(5));
                            userDto.setApplicationName(resultSet.getString(6));
                            userDto.setDepartmentCode(resultSet.getString(7));
                            userDto.setSupervisorCode(resultSet.getString(8));
                            userDto.setLocationCode(resultSet.getString(9));
                            userDto.setUserType(resultSet.getString(10));
                            userDto.setUsername(resultSet.getString(11));
                            userDto.setUserDomain(resultSet.getString(12));
                            userDto.setPassword(resultSet.getString(13));
                            userDto.setUserStatus(resultSet.getString(14));
                            if ("N".equalsIgnoreCase(userDto.getUserStatus()) && resultSet.getString(15) != null) {
                                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                                formatDate = f.parse(resultSet.getString(15));
                            }
                            userDto.setUserDisabledTill(formatDate);
                            userDto.setFirstNameNew(resultSet.getString(16));
                            userDto.setLastNameNew(resultSet.getString(17));
                            userDto.setSolIdNew(resultSet.getString(18));
                            userDto.setEmployeeCodeNew(resultSet.getString(19));
                            userDto.setApplicationNameNew(resultSet.getString(20));
                            userDto.setDepartmentCodeNew(resultSet.getString(21));
                            userDto.setSupervisorCodeNew(resultSet.getString(22));
                            userDto.setLocationCodeNew(resultSet.getString(23));
                            userDto.setUserTypeNew(resultSet.getString(24));
                            userDto.setUsernameNew(resultSet.getString(25));
                            userDto.setUserDomainNew(resultSet.getString(26));
                            userDto.setPasswordNew(resultSet.getString(27));
                            userDto.setUserStatusNew(resultSet.getString(28));
                            userDto.setUserDisabledTillNew(resultSet.getString(29));

                            userDto.setCreatedByUser(stmt.getString(4));
                            userDto.setCreatedDate(stmt.getString(5));
                            userDto.setModifiedByUser(stmt.getString(6));
                            userDto.setModifiedDate(stmt.getString(7));
                            userDto.setVerifiedByUser(stmt.getString(8));
                            userDto.setVerifiedDate(stmt.getString(9));
                            userDto.setPreOpr(stmt.getString(10));
                            userDto.setVerifyStatus(stmt.getString(11));
                        }
                        responseDataDTO.setResult(userDto);
                    }
                }
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
    public ResponseDataDTO verifyUserDetails(String userId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        Date formatDate = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_USER_MGMT_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, userId);
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(12);
                    if (StringUtils.isEmpty(userId)) {
                        List<UserDetailsDto> userList = new ArrayList<>();
                        UserDetailsDto userDto = null;

                        while (resultSet.next()) {
                            userDto = new UserDetailsDto();
                            userDto.setId(resultSet.getString(1));
                            userDto.setFirstName(resultSet.getString(2));
                            userDto.setLastName(resultSet.getString(3));
                            userDto.setSolId(resultSet.getString(4));
                            userDto.setEmployeeCode(resultSet.getString(5));
                            userDto.setApplicationName(resultSet.getString(6));
                            userDto.setDepartmentCode(resultSet.getString(7));
                            userDto.setSupervisorCode(resultSet.getString(8));
                            userDto.setLocationCode(resultSet.getString(9));
                            userDto.setUserType(resultSet.getString(10));
                            userDto.setUsername(resultSet.getString(11));
                            userDto.setUserDomain(resultSet.getString(12));
                            userDto.setUserStatus(resultSet.getString(14));
                            userList.add(userDto);
                        }
                        responseDataDTO.setResult(userList);
                    } else {
                        UserDetailsDto userDto = null;

                        if (resultSet.next()) {
                            userDto = new UserDetailsDto();
                            userDto.setId(resultSet.getString(1));
                            userDto.setFirstName(resultSet.getString(2));
                            userDto.setLastName(resultSet.getString(3));
                            userDto.setSolId(resultSet.getString(4));
                            userDto.setEmployeeCode(resultSet.getString(5));
                            userDto.setApplicationName(resultSet.getString(6));
                            userDto.setDepartmentCode(resultSet.getString(7));
                            userDto.setSupervisorCode(resultSet.getString(8));
                            userDto.setLocationCode(resultSet.getString(9));
                            userDto.setUserType(resultSet.getString(10));
                            userDto.setUsername(resultSet.getString(11));
                            userDto.setUserDomain(resultSet.getString(12));
                            userDto.setPassword(resultSet.getString(13));
                            userDto.setUserStatus(resultSet.getString(14));
                            if ("N".equalsIgnoreCase(userDto.getUserStatus()) && resultSet.getString(15) != null) {
                                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                                formatDate = f.parse(resultSet.getString(15));
                            }
                            userDto.setUserDisabledTill(formatDate);
                            userDto.setFirstNameNew(resultSet.getString(16));
                            userDto.setLastNameNew(resultSet.getString(17));
                            userDto.setSolIdNew(resultSet.getString(18));
                            userDto.setEmployeeCodeNew(resultSet.getString(19));
                            userDto.setApplicationNameNew(resultSet.getString(20));
                            userDto.setDepartmentCodeNew(resultSet.getString(21));
                            userDto.setSupervisorCodeNew(resultSet.getString(22));
                            userDto.setLocationCodeNew(resultSet.getString(23));
                            userDto.setUserTypeNew(resultSet.getString(24));
                            userDto.setUsernameNew(resultSet.getString(25));
                            userDto.setUserDomainNew(resultSet.getString(26));
                            userDto.setPasswordNew(resultSet.getString(27));
                            userDto.setUserStatusNew(resultSet.getString(28));
                            userDto.setUserDisabledTillNew(resultSet.getString(29));

                            userDto.setCreatedByUser(stmt.getString(4));
                            userDto.setCreatedDate(stmt.getString(5));
                            userDto.setModifiedByUser(stmt.getString(6));
                            userDto.setModifiedDate(stmt.getString(7));
                            userDto.setVerifiedByUser(stmt.getString(8));
                            userDto.setVerifiedDate(stmt.getString(9));
                            userDto.setPreOpr(stmt.getString(10));
                            userDto.setVerifyStatus(stmt.getString(11));
                        }
                        responseDataDTO.setResult(userDto);
                    }

                }
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
    public void update(User user) {
        Session session = getSession();
        session.saveOrUpdate(user);
        //this.em.merge(user);
    }

    @Override
    public Set<String> getRoleSet() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        //ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Set<String> roleDataSet = new HashSet<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_ROLES.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.registerOutParameter("P_ROLES_OUT", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject("P_ROLES_OUT");

                    if (resultSet != null) {

                        while (resultSet.next()) {
                            roleDataSet.add(resultSet.getString("NAME"));
                        }
                        //responseDataDTO.setResult(linksDataSet);
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        //responseDataDTO.setMessage(resultMsg);
        return roleDataSet;
    }

    /* (non-Javadoc)
     * @see com.afl.gbm.user.management.dao.UserDao#getUserByUsername(java.lang.String)
     */
    @Override
    public User getUserByUsername(String username) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        final Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(builder.and(builder.equal(root.get(USERNAME), username)), builder.equal(root.get(VERIFIED_STATUS), CommonConstants.YES));
        return em.createQuery(criteriaQuery).getResultList().stream().filter(item -> item != null).findFirst().orElse(null);
    }

    /* (non-Javadoc)
     * @see com.afl.gbm.user.management.dao.UserDao#getUsers()
     */
    @Override
    public List<User> getUsers() {
        /*final Session session =sessionFactory.getCurrentSession();*/
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        final Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(builder.notEqual(root.get(USER_TYPE), CommonConstants.SUPER_ADMIN), builder.notEqual(root.get(STATUS), UserStatus.DELETED.getId()));
        return em.createQuery(criteriaQuery).getResultList();
    }

    /* (non-Javadoc)
     * @see com.afl.gbm.user.management.dao.UserDao#getUser(java.lang.Integer)
     */
    @Override
    public User getUser(Integer id) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        final Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), id));
        return session.createQuery(criteriaQuery).uniqueResult();
    }

    /* (non-Javadoc)
     * @see com.afl.gbm.user.management.dao.UserDao#getUsersByRole(java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUsersByRole(Integer id) {
        final Session session = getSession();
        Query<User> query = session.createQuery("SELECT u FROM User u JOIN u.roles r WHERE r.id=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    /* (non-Javadoc)
     * @see com.afl.gbm.user.management.dao.UserDao#getLockedUsers()
     */
    @Override
    public List<User> getLockedUsers() {
        /*final Session session =sessionFactory.getCurrentSession();*/
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        final Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(builder.notEqual(root.get(USER_TYPE), CommonConstants.SUPER_ADMIN), builder.equal(root.get(STATUS), UserStatus.LOCKED.getId()));
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void updateLogoutStatusForUsersById(User user){
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        String result = "";
        ResultSet resultSet = null;
        try {
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_UPDATE_LOGOUT_USERID.getProcName());
                stmt.setString(index++,user.getId().toString());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                result = stmt.getString(index);
                log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<LOGOUT STATUS>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
                responseDataDTO.setMessage(result);
            } else {
                responseDataDTO.setMessage(ErrorMessages.INTERNAL_SERVER_ERROR);
                log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<LOGOUT STATUS>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

    }

    @Override
    public ResponseDataDTO getUserActivityDetails(UserActivityReportDTO userActivityReportDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        ResultSet rs = null;
        UserActivityReportDTO userActivityReportDTO1 = null;
        List<UserActivityReportDTO> userActivityReportDTOList = new ArrayList<>();
        HashMap<String, Object> trialRunEligibleList = new HashMap<>();
//        List<RbiBondTrailRunReportDto> rbiBondTrailRunReportDtos = new ArrayList<>();

        HashMap<String, Object> userActivityList = new HashMap<>();


        final Session session = getSession();

        Transaction transaction = null;
        boolean isTransactionActive = session.getTransaction().isActive();
        if (!isTransactionActive) {
            transaction = session.beginTransaction();
        }

        try {


            String hqlQuery = "SELECT \n" +
            " moduleId AS module_id, \n" +
                    " linkName AS link_name, \n" +
                    " auditRefNum AS audit_ref_num, \n" +
                    " tableName AS table_name, \n" +
                    " keyValue AS key_value, \n" +
                    " substr(dat, 1, instr(dat, '|', 1, 1)-1) AS col_name, \n" +
                    " substr(dat, instr(dat, '|', 1, 1)+1, (instr(dat, '|', 1, 2))-(instr(dat, '|', 1, 1))-1) AS OLD_VAL,\n" +
                    " substr(dat, instr(dat, '|', 1, 2)+1, LENGTH(dat)- instr(dat, '|',-1, 1)) AS NEW_VAL,\n" +
                    " remarks AS remarks,\n" +
                    " createdBy AS created_by,\n" +
                    " createdOn AS created_on,\n" +
                    " lastModifiedBy AS modified_by,\n" +
                    " lastModifiedDate AS modified_on,\n" +
                    " NULL AS authorized_by,\n" +
                    " NULL AS authorized_on,\n" +
                    " NULL AS deleted_by,\n" +
                    " NULL AS deleted_on,\n" +
                    " entity_id AS entity_id,\n" +
                    " NULL AS entity_account_no\n" +
                    "FROM ( \n" +
            "SELECT \n" +
            " moduleId AS module_id, \n" +
                    " linkName AS link_name, \n" +
                    " auditRefNum AS audit_ref_num, \n" +
                    " tableName AS table_name, \n" +
                    " keyValue AS key_value, \n" +
                    " regexp_substr(modified_fields_data,'[^~]+', 1, LEVEL) AS dat, \n" +
                    " remarks AS remarks, \n" +
                    " createdBy AS created_by, \n" +
                    " createdOn AS created_on, \n" +
                    " lastModifiedBy AS modified_by, \n" +
                    " lastModifiedDate AS modified_on, \n" +
                    " NULL AS authorized_by, \n" +
                    " NULL AS authorized_on, \n" +
                    " NULL AS deleted_by, \n" +
                    "NULL AS deleted_on, \n" +
                    " entity_id AS entity_id, \n" +
                    " NULL AS entity_account_no \n" +
                    "FROM ( \n" +
            "SELECT \n" +
            " UPPER(T2.moduleId) AS module_id, \n" +
                    " UPPER(t1.linkName) AS link_name, \n" +
                    " T2.auditRefNum AS audit_ref_num, \n" +
                    " T2.tableName AS table_name, \n" +
                    " T2.keyValue AS key_value, \n" +
                    " T2.modifiedFieldsData AS modified_fields_data, \n" +
                    " T2.remarks AS remarks, \n" +
                    " T2.createdBy AS created_by, \n" +
                    " to_char(T2.createdOn,'DD/MM/YYYY') AS created_on, \n" +
                    " T2.lastModifiedBy AS modified_by, \n" +
                    " to_char(T2.lastModifiedDate,'DD/MM/YYYY') AS modified_on, \n" +
                    " NULL AS authorized_by, \n" +
                    " NULL AS authorized_on, \n" +
                    " NULL AS deleted_by,  \n" +
                    " NULL AS deleted_on, \n" +
                    " T2.ref_col_val AS entity_id, \n" +
                    " NULL AS entity_account_no \n" +
                    " FROM AuditLog T2 \n" +
                    " LEFT JOIN AuditLogCode t1 ON T2.featureDisplayName = t1.link_code \n" +
                    " WHERE \n" +
                    " T2.authId = '!' \n" +
                    " AND T2.deleted = '0'  \n" +
                    " AND (T2.entererSolId = :p_user_sol_id_in OR T2.modifierSolId = :p_user_sol_id_in) \n" +
                    " AND (T2.entererId = :p_user_name_in OR T2.lastModifiedBy = :p_user_name_in) \n" +
                    " AND (T2.operationMode IN ('ADD', 'MODIFY')) \n" +
                    " AND TRIM(T2.moduleId) = NVL(:p_module_id_in, T2.moduleId) \n" +
                    " AND ( \n" +
                    "(enterer_date BETWEEN TO_DATE(:p_from_date_in, 'DD-MON-YYYY') AND TO_DATE(:p_to_date_in, 'DD-MON-YYYY')) OR \n" +
                    "(last_modified_date BETWEEN TO_DATE(:p_from_date_in, 'DD-MON-YYYY') AND TO_DATE(:p_to_date_in, 'DD-MON-YYYY')) \n" +
                    " ) \n" +
                    " ) rr \n" +
                    " CONNECT BY LEVEL <= regexp_count(rr.modified_fields_data,'~') \n" +
                    " ) mr \n" +
                    " UNION ALL \n" +
                    "SELECT UPPER(T2.moduleId)                AS module_id, --FOR VERIFY RECORD   UPPER(t1.linkName)     AS link_name, \n" +
            " T2.auditRefNum                                 AS audit_ref_num, T2.tableName    AS table_name, \n" +
                    " T2.keyValue                       AS key_value, NULL    AS col_name, \n" +
                    " NULL                                        AS OLD_VAL,    NULL    AS NEW_VAL, \n" +
                    " T2.remarks                                  AS remarks,         T2.createdBy      AS created_by, \n" +
                    " to_char(T2.createdOn,'DD/MM/YYYY')       AS created_on,         T2.lastModifiedBy   AS modified_by, \n" +
                    " to_char(T2.lastModifiedDate,'DD/MM/YYYY') AS modified_on,         T2.authId     AS authorized_by, \n" +
                    " to_char(T2.authDate,'DD/MM/YYYY')          AS authorized_on,         NULL      AS deleted_by, \n" +
                    " NULL                                        AS deleted_on,         T2.ref_col_val    AS entity_id, \n" +
                    " NULL                                        AS entity_account_no \n" +
                    " FROM AuditLog T2 LEFT JOIN AuditLogCode t1 \n" +
                    " ON T2.featureDisplayName = t1.link_code \n" +
                    " WHERE T2.authId = :p_user_name_in AND T2.deleted = '0'  \n" +
                    " AND T2.verifierSolId = :p_user_sol_id_in \n" +
                    " AND T2.operationMode IN ('VERIFY') \n" +
                    " AND TRIM(T2.moduleId) = nvl(:p_module_id_in,T2.moduleId) \n" +
                    " AND T2.authDate BETWEEN TO_DATE(:p_from_date_in, 'DD-MON-YYYY') AND TO_DATE(:p_to_date_in,'DD-MON-YYYY') \n" +
                    "    UNION ALL    \n" +
                    " SELECT UPPER(T2.moduleId)                AS module_id, --FOR Delete RECORD ,\n" +
                    " UPPER(t1.linkName)             AS link_name, \n" +
                    " T2.auditRefNum                                 AS audit_ref_num, \n" +
            " T2.tableName                               AS table_name,\n"+
                    " T2.keyValue                       AS key_value, \n"+
                    " NULL                                        AS col_name,  NULL   AS OLD_VAL,          NULL        AS NEW_VAL,   \n"+
                    " T2.remarks                                  AS remarks, T2.createdBy     AS created_by,  to_char(T2.createdOn,'DD/MM/YYYY')       AS created_on,\n"+
                    " T2.lastModifiedBy                         AS modified_by,         to_char(T2.lastModifiedDate,'DD/MM/YYYY') AS modified_on, \n"+
                    " NULL                                        AS authorized_by,         NULL                                        AS authorized_on, \n"+
                    " CASE WHEN T2.operationMode = 'DELETE' THEN T2.deletedBy \n"+
                    " WHEN T2.operationMode = 'REVERT' THEN T2.revertedBy END AS deleted_by, \n"+
                    "  CASE WHEN T2.operationMode = 'DELETE' THEN to_char(T2.deletedDate,'DD/MM/YYYY') \n"+
                    " WHEN T2.operationMode = 'REVERT' THEN to_char(T2.revertedDate,'DD/MM/YYYY') END  AS deleted_on, \n"+
                    " T2.ref_col_val                              AS entity_id, \n"+
                    " NULL                                        AS entity_account_no \n"+
                    " FROM AuditLog T2 LEFT JOIN AuditLogCode t1 \n"+
                    " ON T2.featureDisplayName = t1.link_code \n"+
                    " WHERE ((T2.deletedBy = :p_user_name_in)OR (T2.revertedBy = :p_user_name_in)) AND T2.deleted = '0'  \n"+
                    " AND ((T2.deleterSolId = :p_user_sol_id_in) OR (T2.reverterSolId =:p_user_sol_id_in)) \n"+
                    " AND T2.operationMode IN ('DELETE','REVERT') \n"+
                    " AND TRIM(T2.moduleId) = nvl(:p_module_id_in,T2.moduleId)  \n"+
                    " AND \n"+
                    " ( (T2.deletedDate BETWEEN TO_DATE(:p_from_date_in,'DD-MON-YYYY') AND TO_DATE(:p_to_date_in,'DD-MON-YYYY')) OR \n"+
            " (T2.revertedDate BETWEEN TO_DATE(:p_from_date_in,'DD-MON-YYYY') AND TO_DATE(:p_to_date_in,'DD-MON-YYYY'))) \n"+
                    "                       AND (T2.moduleId LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                  OR t1.linkName LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.auditId LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                  OR T2.NULL LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.NULL LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.NULL LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.remarks LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.createdBy LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                  OR to_char(T2.createdOn,'DD/MM/YYYY')  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.lastModifiedBy   LIKE '%'||:P_SEARCH_VALUE_IN ||'%'\n" +
                    "                                                   OR to_char(T2.lastModifiedDate,'DD/MM/YYYY') LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.authId  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                  OR T2.authDate LIKE '%'|| :P_SEARCH_VALUE_IN||'%')\n" +
                    "                                                OR T2.deletedBy	  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                OR to_char(T2.deletedDate,'DD/MM/YYYY')	  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR to_char(T2.revertedDate,'DD/MM/YYYY')  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR NULL  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR NULL  LIKE '%'||:P_SEARCH_VALUE_IN||'%'" ;
            if (userActivityReportDTO.getSortField() != null && !userActivityReportDTO.getSortField().isEmpty() && userActivityReportDTO.getSortOrder() != null && !userActivityReportDTO.getSortOrder().isEmpty()) {
                hqlQuery += " ORDER BY " + userActivityReportDTO.getSortField().toUpperCase() + " " + userActivityReportDTO.getSortOrder().toUpperCase();
            }

            Query<Map<String, Object>> query = session.createQuery(hqlQuery);
            query.setParameter("p_user_sol_id_in", userActivityReportDTO.getBranchId());
            query.setParameter("p_user_name_in", userActivityReportDTO.getBranchName());
            query.setParameter("p_from_date_in", userActivityReportDTO.getFromDate());
            query.setParameter("p_to_date_in", userActivityReportDTO.getToDate());
            query.setParameter("p_user_name_in", userActivityReportDTO.getUserName());
            query.setParameter("p_module_id_in", userActivityReportDTO.getModuleId());
            query.setParameter("P_SEARCH_VALUE_IN", userActivityReportDTO.getSearchValue());
            query.setFirstResult((userActivityReportDTO.getPageNo() - 1) * userActivityReportDTO.getPageSize());
            query.setMaxResults(userActivityReportDTO.getPageSize());

            query.setResultTransformer(new TrailRunDetailsResultTransformer());
            List<Map<String, Object>> results = query.getResultList();
            userActivityReportDTO.setData(results);
            userActivityList.put("userActivityList", userActivityReportDTO.getData());

            Query<Long> countQuery = session.createQuery(" SELECT \\n\" +\n" +
                    "                    \"                                    COUNT(1)\\n\" +" +
                    " FROM AuditLog T2 LEFT JOIN AuditLogCode t1 \n" +
                    " ON T2.featureDisplayName = t1.link_code \n" +
                    " WHERE T2.authId = '!' AND T2.deleted = '0'  \n" +
                    " AND (T2.entererSolId = :p_user_sol_id_in OR T2.modifierSolId = :p_user_sol_id_in) \n" +
                    " AND (T2.entererId = :p_user_name_in OR T2.lastModifiedBy = :p_user_name_in) \n" +
                    " AND (T2.operationMode IN ('ADD','MODIFY')) \n" +
                    " AND TRIM(T2.moduleId) = nvl(:p_module_id_in,T2.moduleId) \n" +
                    " AND ( \n" +
                    "(enterer_date BETWEEN TO_DATE(:p_from_date_in,'DD-MON-YYYY') AND TO_DATE(:p_to_date_in,'DD-MON-YYYY')) OR \n" +
                    "(last_modified_date BETWEEN TO_DATE(:p_from_date_in,'DD-MON-YYYY') AND TO_DATE(:p_to_date_in,'DD-MON-YYYY'))" +
                    ") \n"+
                    ")rr \n" +
                    " CONNECT BY LEVEL <= regexp_count(rr.modified_fields_data,'~') \n"+
                    ")mr  \n" +
                    " UNION ALL \n" +
                    " FROM AuditLog T2 LEFT JOIN AuditLogCode t1 \n" +
                    " ON T2.featureDisplayName = t1.link_code \n" +
                    " WHERE T2.authId = :p_user_name_in AND T2.deleted = '0'  \n" +
                    " AND T2.verifierSolId = :p_user_sol_id_in \n" +
                    " AND T2.operationMode IN ('VERIFY') \n" +
                    " AND TRIM(T2.moduleId) = nvl(:p_module_id_in,T2.moduleId) \n" +
                    " AND T2.authDate BETWEEN TO_DATE(:p_from_date_in, 'DD-MON-YYYY') AND TO_DATE(:p_to_date_in,'DD-MON-YYYY') \n" +
                            " UNION ALL \n" +
                    " FROM AuditLog T2 LEFT JOIN AuditLogCode t1 \n"+
                    " ON T2.featureDisplayName = t1.link_code \n"+
                    " WHERE ((T2.deletedBy = :p_user_name_in)OR (T2.revertedBy = :p_user_name_in)) AND T2.deleted = '0'  \n"+
                    " AND ((T2.deleterSolId = :p_user_sol_id_in) OR (T2.reverterSolId = :p_user_sol_id_in)) \n"+
                    " AND T2.operationMode IN ('DELETE','REVERT') \n"+
                    " AND TRIM(T2.moduleId) = nvl(:p_module_id_in,T2.moduleId)  \n"+
                    " AND \n"+
                    " ( (T2.deletedDate BETWEEN TO_DATE(:p_from_date_in,'DD-MON-YYYY') AND TO_DATE(:p_to_date_in,'DD-MON-YYYY')) OR \n"+
                    " (T2.revertedDate BETWEEN TO_DATE(:p_from_date_in,'DD-MON-YYYY') AND TO_DATE(:p_to_date_in,'DD-MON-YYYY'))) \n"+
                    "                       AND (T2.moduleId LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                  OR t1.linkName LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.auditId LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                  OR T2.NULL LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.NULL LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.NULL LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.remarks LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.createdBy LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                  OR to_char(T2.createdOn,'DD/MM/YYYY')  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.lastModifiedBy   LIKE '%'||:P_SEARCH_VALUE_IN ||'%'\n" +
                    "                                                   OR to_char(T2.lastModifiedDate,'DD/MM/YYYY') LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR T2.authId  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                  OR T2.authDate LIKE '%'|| :P_SEARCH_VALUE_IN||'%')\n" +
                    "                                                OR T2.deletedBy	  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                OR to_char(T2.deletedDate,'DD/MM/YYYY')	  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR to_char(T2.revertedDate,'DD/MM/YYYY')  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                   OR NULL  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                            "                                                   OR NULL  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n");

            countQuery.setParameter("p_user_sol_id_in", userActivityReportDTO.getBranchId());
            countQuery.setParameter("p_user_name_in", userActivityReportDTO.getBranchName());
            countQuery.setParameter("p_from_date_in", userActivityReportDTO.getFromDate());
            countQuery.setParameter("p_to_date_in", userActivityReportDTO.getToDate());
            countQuery.setParameter("p_user_name_in", userActivityReportDTO.getUserName());
            countQuery.setParameter("p_module_id_in", userActivityReportDTO.getModuleId());
            countQuery.setParameter("P_SEARCH_VALUE_IN", userActivityReportDTO.getSearchValue());

            Long totalCount = countQuery.getSingleResult();


            int maxPages = (int) Math.ceil((double) totalCount / userActivityReportDTO.getPageSize());
            userActivityReportDTO.setMaxPages(maxPages);



            userActivityList.put("userActivityList", userActivityReportDTO);
            userActivityList.put("PageDetails", userActivityReportDTO);

            if(userActivityReportDTO.getIsDownload() != null && userActivityReportDTO.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS) && userActivityReportDTO.getDownloadType() !=null ){

                if(userActivityReportDTO.getDownloadType().equalsIgnoreCase("excel")) {

                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(reportsService.excelFileGeneratorForUserReport(results, userActivityReportDTO)));
                    fileResponseDto.setFileName("BOND Interest Trial Run – Eligible Accounts Report.xlsx");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                    return responseDataDTO;
                }

                if(userActivityReportDTO.getDownloadType().equalsIgnoreCase("pdf")){
                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(reportsService.pdfFileGeneratorForUserReport(results, userActivityReportDTO)));
                    fileResponseDto.setFileName("BOND Interest Trial Run – Eligible Accounts Report.pdf");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    return responseDataDTO;
                }
                else{
                    responseDataDTO.setMessage("Invalid File Type");
                    return responseDataDTO;
                }

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//            transaction.commit();
            session.close();
        }

        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        responseDataDTO.setResult(trialRunEligibleList);
        return responseDataDTO;
    }


    @Override
    public void updateLogoutForUsersById(String id) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String name = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_user_session_id .getProcName());
                stmt.setString(index++,id);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                resultMsg = stmt.getString(4);
                System.out.println("UserLogoutStatus"+resultMsg);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    name = stmt.getString(2);
                    log.info(name);
                    this.updateLogforSession(name, id);
                }
            }else {
                responseDataDTO.setMessage(ErrorMessages.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
    }

    @Override
    public ResponseDataDTO adminActivityReport(NewAdminActivityReportDTO dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        List<NewAdminActivityReportDTO> list = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if(!dto.isSftpFlag()){
                    stmt = connection.prepareCall(ProcNameEnum.SP_GBM_NEW_ADMIN_ACTIVITY_REPORT.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                }else{
                    stmt = connection.prepareCall(ProcNameEnum.SP_GBM_NEW_ADMIN_ACTIVITY_REPORT_SCH.getProcName());
                    stmt.setString(index++, "SUPERADMIN1");
                    stmt.setString(index++, "101");
                }
                stmt.setString(index++, dto.getFromDate());
                stmt.setString(index++, dto.getToDate());
                stmt.setString(index++, dto.getPageNo());
                stmt.setString(index++, dto.getPageSize());
                stmt.setString(index++, dto.getSearchValue());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, oracle.jdbc.internal.OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{} {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(9);
                    list = new ArrayList<>();
                    while(resultSet.next()){
                        NewAdminActivityReportDTO item = new NewAdminActivityReportDTO();
                        item.setId(resultSet.getString(1));
                        item.setUsername(resultSet.getString(2));
                        item.setBranchCode(resultSet.getString(3));
                        item.setBranchName(resultSet.getString(4));
                        item.setProfileName(resultSet.getString(5));
//                        item.setActivity(resultSet.getString(6));
//                        item.setMakerId(resultSet.getString(7));
//                        item.setMakerDateTime(resultSet.getString(8));
//                        item.setLastCheckerId(resultSet.getString(9));
//                        item.setLastCheckerDateTime(resultSet.getString(10));
//                        item.setOldEmployeeName(resultSet.getString(11));
//                        item.setNewEmployeeName(resultSet.getString(12));
//                        item.setOldProfileName(resultSet.getString(13));
//                        item.setNewProfileName(resultSet.getString(14));
//                        item.setOldBranchName(resultSet.getString(15));
//                        item.setNewBranchName(resultSet.getString(16));
//                        item.setCreationFlag(resultSet.getString(17));
//                        item.setEnabledFlag(resultSet.getString(18));
//                        item.setDisabledFlag(resultSet.getString(19));
//                        item.setDeletionFlag(resultSet.getString(20));

                        item.setMakerId(resultSet.getString(6));
                        item.setMakerDateTime(resultSet.getString(7));
                        item.setLastCheckerId(resultSet.getString(8));
                        item.setLastCheckerDateTime(resultSet.getString(9));
                        item.setOldEmployeeName(resultSet.getString(10));
                        item.setNewEmployeeName(resultSet.getString(11));
                        item.setOldProfileName(resultSet.getString(12));
                        item.setNewProfileName(resultSet.getString(13));
                        item.setOldBranchName(resultSet.getString(14));
                        item.setNewBranchName(resultSet.getString(15));
                        item.setCreationFlag(resultSet.getString(16));
                        /*item.setEnabledFlag(resultSet.getString(17));
                        item.setDisabledFlag(resultSet.getString(18));
                        item.setDeletionFlag(resultSet.getString(19));*/
                        list.add(item);
                    }
                    if(null!=stmt.getString(8)) {
                        responseDataDTO.setTotal(Long.parseLong(stmt.getString(8)));
                    }
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(list);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO loginLogoutReport(LoginLogoutReportDTO dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        List<LoginLogoutReportDTO> list = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_LOGIN_LOGOUT_REPORT.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getFromDate());
                stmt.setString(index++, dto.getToDate());
                stmt.registerOutParameter(index++, oracle.jdbc.internal.OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(5);
                    list = new ArrayList<>();
                    while(resultSet.next()){
                        LoginLogoutReportDTO item = new LoginLogoutReportDTO();
                        item.setUserId(resultSet.getString(1));
                        item.setEmployeeCode(resultSet.getString(2));
                        item.setEmployeeName(resultSet.getString(3));
                        item.setLoginDate(resultSet.getString(4));
                        item.setLoginTime(resultSet.getString(5));
                        item.setLogoutTime(resultSet.getString(6));
                        item.setApplicationName(resultSet.getString(7));
                        item.setIpAddress(resultSet.getString(8));
                        item.setHostDetails(resultSet.getString(9));

                        list.add(item);
                    }

                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(list);
        return responseDataDTO;
    }


    private void updateLogforSession(String name, String id) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                int index= 1;
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_user_session_name.getProcName());
                stmt.setString(index++,name);
                stmt.setString(index++,id);
                stmt.setString(index++, UserLogOutReasons.SESSION_EXPIRED.getReasonName());
                stmt.setString(index++,UserLogOutReasons.SESSION_EXPIRED.getReasonDesc());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                resultMsg = stmt.getString(6);

                System.out.println("UserLogoutStatus"+resultMsg);
            }else {
                responseDataDTO.setMessage(ErrorMessages.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        }
        finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
    }


    @Override
    public ResponseDataDTO getModuleName(UserActivityReportDTO userActivityReportDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.sp_gbm_module_name_tor.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    ResultSet resultSet = (ResultSet) stmt.getObject(3);

                        if (resultSet != null) {
                            List<UserActivityReportDTO> list = new ArrayList<>();
                            UserActivityReportDTO item = null;
                            while (resultSet.next()) {
                                item = new UserActivityReportDTO();
                                item.setModuleName(resultSet.getString("module_name"));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                }

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
    public void updateLogoutStatusForUsersById(List<Integer> userIdList) {
//        final Session session = getSession();
//        Query<User> query = session.createQuery("UPDATE User u SET u.loggedInFlag='N' WHERE u.id in (:userIdList)");
//        query.setParameterList("userIdList", userIdList);
//        query.executeUpdate();
        Connection connection = null;
//        PreparedStatement stmt = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        String result = "";
        ResultSet resultSet = null;
        try {
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_UPDATE_LOGOUT_USERID.getProcName());
                if (userIdList != null && !userIdList.isEmpty() && userIdList.get(0) != null) {
                    stmt.setString(index++, userIdList.get(0).toString());
                } else {
                    stmt.setNull(index++, java.sql.Types.VARCHAR);
                }
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                result = stmt.getString(index);
                log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<LOGOUT STATUS>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
                responseDataDTO.setMessage(result);
            } else {
                responseDataDTO.setMessage(ErrorMessages.INTERNAL_SERVER_ERROR);
                log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<LOGOUT STATUS>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
    }


    @Override
    public ResponseDataDTO postLoginUserDetails() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Set<String> linksDataSet = new HashSet<>();
        try {
            connection = connectionPoolFactory.getConnection();
            connection.setAutoCommit(false);
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_POST_USER_LOGIN_DETAILS.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                log.info("user Name " + UserAccountUtils.getCurrentUsername());
                log.info("SOL ID " + UserAccountUtils.getCurrentSolId());
                stmt.setString("P_SOL_ID_IN", UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_CURSOR_OUT, OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.P_USER_MENU_OUT, OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.P_SOL_DATE_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.P_SOL_DESC_OUT, Types.VARCHAR);
                stmt.registerOutParameter("P_CREATED_TIME_OUT", Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    PostLoginUserDetailsDTO postLoginUserDetailsDTO = new PostLoginUserDetailsDTO();
                    postLoginUserDetailsDTO.setSolDate(stmt.getString(CommonConstants.P_SOL_DATE_OUT));
                    postLoginUserDetailsDTO.setSolDesc(stmt.getString(CommonConstants.P_SOL_DESC_OUT));
                    postLoginUserDetailsDTO.setLastLoginSuccessTime(stmt.getString("P_CREATED_TIME_OUT"));
                    String solDesc =  postLoginUserDetailsDTO.getSolDesc();

                    SecurityUtils.getSubject().getSession()
                            .setAttribute("SOL_DESC", solDesc);
                    resultSet = (ResultSet) stmt.getObject(CommonConstants.PROC_PARAM_CURSOR_OUT);
                    StopWatch stopWatch = new StopWatch();
                    stopWatch.start();
                    if (resultSet != null) {
                        resultSet.setFetchSize(4000);
                        while (resultSet.next()) {
                            linksDataSet.add(resultSet.getString(1));
                        }
                        postLoginUserDetailsDTO.setPermissionList(linksDataSet);
                    }
                    stopWatch.stop();
                    System.out.println("Permisions time :  " + stopWatch.getTotalTimeMillis());

                    resultSet = (ResultSet) stmt.getObject(CommonConstants.P_USER_MENU_OUT);
                    StopWatch stopWatch1 = new StopWatch();
                    stopWatch1.start();
                    if (resultSet != null) {
                        resultSet.setFetchSize(4000);
                        //PermissionMenuLinkDTO permissionMenuLinkDTO = null;
                        //Map<String, PermissionMenuLinkDTO> linksDataMap = new LinkedHashMap<>();
                        PostLoginUserDetailsDTO.ColumnWiseMenuList columnWiseMenuList = null;
                        List<PostLoginUserDetailsDTO.ColumnWiseMenuList> columnWiseMenuLists = new ArrayList<>();

                        while (resultSet.next()) {

                            /*if (!linksDataMap.containsKey(resultSet.getString("ID"))) {
                                permissionMenuLinkDTO = new PermissionMenuLinkDTO();
                                permissionMenuLinkDTO.setLevel(resultSet.getString("DISPLY_LEVEL"));
                                permissionMenuLinkDTO.setLinkId(resultSet.getString("ID"));
                                permissionMenuLinkDTO.setLinkName(resultSet.getString("MENU_NAME"));
                                permissionMenuLinkDTO.setParentLinkId(resultSet.getString("PARENT_ID"));
                                permissionMenuLinkDTO.setMenuUrl(resultSet.getString("URL"));
                                permissionMenuLinkDTO.setIsLink(resultSet.getString("IS_LINK"));
                                permissionMenuLinkDTO.setOrderId(resultSet.getString("ORDER_ID"));
                                linksDataMap.put(resultSet.getString("ID"), permissionMenuLinkDTO);
                            }*/

                            columnWiseMenuList = new PostLoginUserDetailsDTO.ColumnWiseMenuList();
                            columnWiseMenuList.setColumnWiseMenu(resultSet.getString(1));
                            columnWiseMenuLists.add(columnWiseMenuList);
                        }
                        stopWatch1.stop();
                        System.out.println("Menu time :  " + stopWatch1.getTotalTimeMillis());
                        /*for (PermissionMenuLinkDTO item : linksDataMap.values()) {
                            if (Integer.valueOf(item.getParentLinkId()) > 0) {
                                if (linksDataMap.get(item.getParentLinkId()).getChildren() == null)
                                    linksDataMap.get(item.getParentLinkId()).setChildren(new ArrayList<>());
                                linksDataMap.get(item.getParentLinkId()).getChildren().add(item);
                            }
                        }

                        Map<String, Object> resMap = new HashMap<>();
                        resMap.put("menuItemList", linksDataMap.values().stream().filter(item -> item.getLevel().equals("1")).collect(Collectors.toList()));*/
                        //postLoginUserDetailsDTO.setLinksDataMap(linksDataMap);
                        postLoginUserDetailsDTO.setColumnWiseMenuList(columnWiseMenuLists);

                    }

                    responseDataDTO.setResult(postLoginUserDetailsDTO);
                }


            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO getCurrentLoggedInBranchActiveUsers(UserAdminSackDTO userAdminSackDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_LOGGIN_DETAIL.getProcName());
                stmt.setString("P_SOL_ID_IN", userAdminSackDTO.getBranchId());
                stmt.registerOutParameter("P_CURSOR_OUT", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                ResultSet resultSet = null;
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject("P_CURSOR_OUT");

                    if (resultSet != null && userAdminSackDTO.getBranchId() != null) {
                        HashMap<String, ActiveUserDto> activeUsersMap = new HashMap<>();
                        ActiveUserDto activeUser = null;
                        while (resultSet.next()) {
                            activeUser = new ActiveUserDto();
                            activeUser.setUserId(resultSet.getInt(1));
                            activeUser.setUsername(resultSet.getString(2));
                            activeUser.setLoggedInSince(resultSet.getTimestamp(3));
                            activeUsersMap.put(resultSet.getString(2), activeUser);
                        }
                        responseDataDTO.setResult(activeUsersMap);
                    } else if (resultSet != null) {
                        HashMap<String, ActiveUserDto> branchIdList = new HashMap<>();
                        ActiveUserDto activeUser = null;
                        while (resultSet.next()) {
                            activeUser = new ActiveUserDto();
                            activeUser.setBranchId(resultSet.getString(1));
                            activeUser.setBranchDescription(resultSet.getString(2));
                            branchIdList.put(resultSet.getString(1), activeUser);
                        }
                        responseDataDTO.setResult(branchIdList);
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getUserReports(ReportSearchCriteriaDto reportSearchCriteriaDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        List<HashMap<String, String>> pran = null;
        List<HashMap<String, String>> resultBranchIdList = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<ReportSearchCriteriaDto> reportSearchCriteriaDtoDTOList = null;
        ReportSearchCriteriaDto reportSearchCriteriaDto1 = new ReportSearchCriteriaDto();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_REPORT.getProcName());
                stmt.setString(CommonConstants.P_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString(CommonConstants.P_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString(CommonConstants.PROC_P_BR_DET_REQ_FLG_IN_IN, reportSearchCriteriaDto.getBranchFlag());
                stmt.setString(CommonConstants.PROC_P_BRANCH_ID_IN_IN, reportSearchCriteriaDto.getBranchID());
//                stmt.setString("P_STATUS_IN", reportSearchCriteriaDto.getStatus());
                stmt.registerOutParameter(CommonConstants.PROC_BR_DET_OUT, OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_DET_OUT, OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);

                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    resultSet = (ResultSet) stmt.getObject(CommonConstants.PROC_DET_OUT);
                    ResultSet resultSet1 = (ResultSet) stmt.getObject(CommonConstants.PROC_BR_DET_OUT);

                    if (reportSearchCriteriaDto.getBranchFlag() == null && reportSearchCriteriaDto.getBranchID() != null) {
                        if (resultSet != null) {

                            reportSearchCriteriaDtoDTOList = new ArrayList<>();

                            while (resultSet.next()) {
                                reportSearchCriteriaDto1 = new ReportSearchCriteriaDto();
                                reportSearchCriteriaDto1.setBranchID(resultSet.getString("Branch_id"));
                                reportSearchCriteriaDto1.setUsername(resultSet.getString("UserName"));
                                reportSearchCriteriaDto1.setFirstName(resultSet.getString("first_name"));
                                reportSearchCriteriaDto1.setLastName(resultSet.getString("last_name"));
                                reportSearchCriteriaDto1.setUserType(resultSet.getString("user_type"));
                                reportSearchCriteriaDto1.setEntityRole(resultSet.getString("Entity_Role"));
                                reportSearchCriteriaDto1.setExpiryDate(resultSet.getString("expiry_date"));
                                reportSearchCriteriaDto1.setStatus(resultSet.getString("status"));
                                reportSearchCriteriaDto1.setLocationCode(resultSet.getString("location_code"));
                                reportSearchCriteriaDto1.setUserDomain(resultSet.getString("user_domain"));
                                reportSearchCriteriaDto1.setApplicationName(resultSet.getString("application_name"));
                                reportSearchCriteriaDto1.setCreated(resultSet.getString("created"));
                                reportSearchCriteriaDto1.setCreatedDate(resultSet.getString("created_date"));
                                reportSearchCriteriaDto1.setModifiedBy(resultSet.getString("modified_by"));
                                reportSearchCriteriaDto1.setModifiedDate(resultSet.getString("modified_date"));
                                reportSearchCriteriaDto1.setVerifiedBy(resultSet.getString("verified_by"));
                                reportSearchCriteriaDto1.setAuthorizedDateTime(resultSet.getString("Authorized_Date_Time"));
                                reportSearchCriteriaDto1.setLastLoginAttempt(resultSet.getString("last_login_attempt"));
                                reportSearchCriteriaDtoDTOList.add(reportSearchCriteriaDto1);
                            }
                            responseDataDTO.setResult(reportSearchCriteriaDtoDTOList);
                        }

                    } else if (reportSearchCriteriaDto.getBranchID() == null && reportSearchCriteriaDto.getBranchFlag() != null) {
                        if (resultSet1 != null) {
                            List<HashMap<String, String>> branchList = new ArrayList<>();
                            while (resultSet1.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("branchId", resultSet1.getString(1));
                                resultMap.put("branchDescription", resultSet1.getString(2));
                                branchList.add(resultMap);
                            }
                            responseDataDTO.setResult(branchList);
                        }
                    } else {
                        if (resultSet != null) {
                            reportSearchCriteriaDtoDTOList = new ArrayList<>();
                            while (resultSet.next()) {
                                reportSearchCriteriaDto1 = new ReportSearchCriteriaDto();
                                reportSearchCriteriaDto1.setBranchID(resultSet.getString("Branch_id"));
                                reportSearchCriteriaDto1.setUsername(resultSet.getString("UserName"));
                                reportSearchCriteriaDto1.setFirstName(resultSet.getString("first_name"));
                                reportSearchCriteriaDto1.setLastName(resultSet.getString("last_name"));
                                reportSearchCriteriaDto1.setUserType(resultSet.getString("user_type"));
                                reportSearchCriteriaDto1.setEntityRole(resultSet.getString("Entity_Role"));
                                reportSearchCriteriaDto1.setExpiryDate(resultSet.getString("expiry_date"));
                                reportSearchCriteriaDto1.setStatus(resultSet.getString("status"));
                                reportSearchCriteriaDto1.setLocationCode(resultSet.getString("location_code"));
                                reportSearchCriteriaDto1.setUserDomain(resultSet.getString("user_domain"));
                                reportSearchCriteriaDto1.setApplicationName(resultSet.getString("application_name"));
                                reportSearchCriteriaDto1.setCreated(resultSet.getString("created"));
                                reportSearchCriteriaDto1.setCreatedDate(resultSet.getString("created_date"));
                                reportSearchCriteriaDto1.setModifiedBy(resultSet.getString("modified_by"));
                                reportSearchCriteriaDto1.setModifiedDate(resultSet.getString("modified_date"));
                                reportSearchCriteriaDto1.setVerifiedBy(resultSet.getString("verified_by"));
                                reportSearchCriteriaDto1.setAuthorizedDateTime(resultSet.getString("Authorized_Date_Time"));
                                reportSearchCriteriaDto1.setLastLoginAttempt(resultSet.getString("last_login_attempt"));
                                reportSearchCriteriaDtoDTOList.add(reportSearchCriteriaDto1);
                            }

                            responseDataDTO.setResult(reportSearchCriteriaDtoDTOList);
                        }
                    }

                }

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
    public ResponseDataDTO getPopulationData(PopulationReportDTO populationReportDTO) {
//        Connection connection = null;
//        CallableStatement stmt = null;
//        ResponseDataDTO responseDataDTO = null;
//        String resultMsg = "";
//        List<PopulationReportDTO> populationReportDTOList = null;
//        Map<String, Object> resultMap = null;
//        PopulationReportDTO populationReportDTO1 = null;
//
//
//        try {
//            connection = connectionPoolFactory.getConnection();
//            if (connection != null) {
//                int index = 1;
//                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_POPULATION_REP.getProcName());
//                if(!populationReportDTO.isSftpFlag()){
//                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
//                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
//                }else{
//                    stmt.setString(index++, "RAJA1");
//                    stmt.setString(index++, "101");
//                }
//                stmt.setString(index++, populationReportDTO.getDate());
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index, Types.VARCHAR);
//                stmt.execute();
//                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
//                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
//                resultMsg = stmt.getString(index);
//
//                ResultSet resultSet = null;
//                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
//                    resultSet = (ResultSet) stmt.getObject(4);
//                    if (resultSet != null) {
//                        populationReportDTOList = new ArrayList<>();
//                        while (resultSet.next()) {
//                            populationReportDTO1 = new PopulationReportDTO();
//                            populationReportDTO1.setId(resultSet.getString(1));
//                            populationReportDTO1.setUserId(resultSet.getString(2));
//                            populationReportDTO1.setUserName(resultSet.getString(3));
//                            populationReportDTO1.setEmployeeCode(resultSet.getString(4));
//                            populationReportDTO1.setBranchCode(resultSet.getString(5));
//                            populationReportDTO1.setBranchName(resultSet.getString(6));
//                            populationReportDTO1.setDepartmentCode(resultSet.getString(7));
//                            populationReportDTO1.setDepartmentName(resultSet.getString(8));
//                            populationReportDTO1.setRoleName(resultSet.getString(9));
//                            populationReportDTO1.setStatus(resultSet.getString(10));
//                            populationReportDTO1.setCreationDateTime(resultSet.getString(11));
//                            populationReportDTO1.setLastPwdChangedDateTime(resultSet.getString(12));
//                            populationReportDTO1.setLastLoginDateTime(resultSet.getString(13));
//                            populationReportDTO1.setLastModifiedIdName(resultSet.getString(14));
//                            populationReportDTO1.setLastModifiedDateTime(resultSet.getString(15));
//                            populationReportDTO1.setLastCheckerId(resultSet.getString(16));
//                            populationReportDTO1.setLastCheckerDateTime(resultSet.getString(17));
//                            populationReportDTO1.setDisabledDateTime(resultSet.getString(18));
//                            populationReportDTO1.setDeletionDateTime(resultSet.getString(19));
//                            populationReportDTO1.setCheckerDateTime(resultSet.getString(20));
//                            populationReportDTOList.add(populationReportDTO1);
//                        }
//                    }
//                }
//            } else {
//                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
//                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
//            }
//
//        } catch (Exception e) {
//            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
//            resultMsg = e.getMessage();
//        } finally {
//            connectionPoolFactory.closeConnection(connection, stmt);
//        }
//        responseDataDTO = new ResponseDataDTO();
//        responseDataDTO.setMessage(resultMsg);
//        responseDataDTO.setResult(populationReportDTOList);
//        return responseDataDTO;















        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        HashMap<String, Object> finalData = new HashMap<>();
        List<PopulationReportDTO> populationReportDTOo = Collections.emptyList();
        String p_user_id_in = "p_user_id_in";
        String p_user_sol_id_in = "p_user_sol_id_in";
        String p_date_in = "p_date_in";
        String p_page_no_in = "p_page_no_in";
        String p_page_size_in = "p_page_size_in";
        String p_search_value_in = "p_search_value_in";

        String p_total_records_out = "p_total_records_out";
        String p_cursor_val_out = "p_cursor_val_out";
        String p_msg_out = "p_msg_out";
        String p_msg_desc_out = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            if(!populationReportDTO.isSftpFlag()){
                inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername());
                inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId());
            }else{
                inParams.put(p_user_id_in, "SUPERADMIN1");
                inParams.put(p_user_sol_id_in, "101");
            }
            //inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername() != null ? UserAccountUtils.getCurrentUsername() : "SUPERADMIN1");
            //inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId() != null ? UserAccountUtils.getCurrentSolId() : "101");
            inParams.put(p_date_in, populationReportDTO.getDate());
            inParams.put(p_page_no_in, populationReportDTO.getPageNo());
            inParams.put(p_page_size_in, populationReportDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=populationReportDTO.getSearchValue()&&!populationReportDTO.getSearchValue().trim().isEmpty())?populationReportDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {

                    new SqlParameter(p_user_id_in, Types.VARCHAR),
                    new SqlParameter(p_user_sol_id_in, Types.VARCHAR),
                    new SqlParameter(p_date_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_cursor_val_out, OracleTypes.CURSOR),
                    new SqlOutParameter(p_msg_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_desc_out, OracleTypes.VARCHAR)
            };


            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(p_cursor_val_out, (rs, rowNum) -> {
                PopulationReportDTO item = new PopulationReportDTO();
                if (populationReportDTO.getDate() != null) {

                    item.setId(rs.getString(1));
                    item.setUserId(rs.getString(2));
                    item.setUserName(rs.getString(3));
                    item.setEmployeeCode(rs.getString(4));
                    item.setBranchCode(rs.getString(5));
                    item.setBranchName(rs.getString(6));
                    item.setDepartmentCode(rs.getString(7));
                    item.setDepartmentName(rs.getString(8));
                    item.setRoleName(rs.getString(9));
                    item.setStatus(rs.getString(10));
                    item.setCreationDateTime(rs.getString(11));
                    item.setLastPwdChangedDateTime(rs.getString(12));
                    item.setLastLoginDateTime(rs.getString(13));
                    item.setLastModifiedIdName(rs.getString(14));
                    item.setLastModifiedDateTime(rs.getString(15));
                    item.setLastCheckerId(rs.getString(16));
                    item.setLastCheckerDateTime(rs.getString(17));
                    item.setDisabledDateTime(rs.getString(18));
                    item.setDeletionDateTime(rs.getString(19));
//                    item.setCheckerDateTime(rs.getString(20));
//                    item.setDisabledDateTime(rs.getString(21));
//                    item.setDeletionDateTime(rs.getString(22));





                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_USER_POPULATION_REP",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            populationReportDTOo = (List<PopulationReportDTO>) result.get(p_cursor_val_out);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            if (CollectionUtils.isEmpty(populationReportDTOo)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("populationDTOList", populationReportDTOo);
                responseDataDTO.setResult(finalData);
            }
        }catch (DataAccessException e) {
            log.error("Spring JDBC error", e);
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.error("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setTotal(Double.parseDouble(totalRecords));
        return responseDataDTO;

    }
    public ResponseDataDTO getRoleRightsReport(RoleRightsReportDTO roleRightsReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        HashMap<String, Object> finalData = new HashMap<>();
        List<RoleRightsReportDTO> roleRightsReportDTOList = Collections.emptyList();
        String p_user_id_in = "p_user_id_in";
        String p_user_sol_id_in = "p_user_sol_id_in";
        String p_date_in = "p_date_in";
        String p_page_no_in = "p_page_no_in";
        String p_page_size_in = "p_page_size_in";
        String p_search_value_in = "p_search_value_in";

        String p_total_records_out = "p_total_records_out";
        String p_cursor_val_out = "p_cursor_val_out";
        String p_msg_out = "p_msg_out";
        String p_msg_desc_out = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername());
            inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId());
            inParams.put(p_date_in, roleRightsReportDTO.getDate());
            inParams.put(p_page_no_in, roleRightsReportDTO.getPageNo());
            inParams.put(p_page_size_in, roleRightsReportDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=roleRightsReportDTO.getSearchValue()&&!roleRightsReportDTO.getSearchValue().trim().isEmpty())?roleRightsReportDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(p_user_id_in, Types.VARCHAR),
                    new SqlParameter(p_user_sol_id_in, Types.VARCHAR),
                    new SqlParameter(p_date_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_cursor_val_out, OracleTypes.CURSOR),
                    new SqlOutParameter(p_msg_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_desc_out, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(p_cursor_val_out, (rs, rowNum) -> {
                RoleRightsReportDTO item = new RoleRightsReportDTO();
                if (roleRightsReportDTO.getDate() != null) {
                    item.setRoleName(rs.getString(1));
                    item.setRoleDesc(rs.getString(2));
                    item.setRightsAssigned(rs.getString(3));
                    item.setRoleDescription(rs.getString(4));
                    item.setRightsAssignedFlg(rs.getString(5));
                    item.setRoleStatus(rs.getString(6));
                    item.setDepartmentCode(rs.getString(7));
                    item.setTypeAccess(rs.getString(8));
                    item.setLastCheckerDate(rs.getString(9));
                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_user_role_rights",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            roleRightsReportDTOList = (List<RoleRightsReportDTO>) result.get(p_cursor_val_out);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            if (CollectionUtils.isEmpty(roleRightsReportDTOList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("roleRightsReportDTOList", roleRightsReportDTOList);
                responseDataDTO.setResult(finalData);
            }
        }catch (DataAccessException e) {
            log.error("Spring JDBC error", e);
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.error("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setTotal(Double.parseDouble(totalRecords));
        return responseDataDTO;
    }

       /* @Override
        public ResponseDataDTO getRoleRightsReport(RoleRightsReportDTO roleRightsReportDTO) {
            Connection connection = null;
            CallableStatement stmt = null;
            ResponseDataDTO responseDataDTO = null;
            String resultMsg = "";
            List<RoleRightsReportDTO> roleRightsReportDTOList = null;

            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_ROLE_RIGHT_REPORT.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, roleRightsReportDTO.getDate());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                    resultMsg = stmt.getString(index);

                    ResultSet resultSet = null;
                    if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                        resultSet = (ResultSet) stmt.getObject(4);
                        if (resultSet != null) {
                            roleRightsReportDTOList = new ArrayList<>();
                            Set<String> distinctRoleDescriptions = new HashSet<>();
                            while (resultSet.next()) {
                                RoleRightsReportDTO roleRightsReportDTO1 = new RoleRightsReportDTO();
                                roleRightsReportDTO1.setRoleName(resultSet.getString(1));
                                roleRightsReportDTO1.setRoleDesc(resultSet.getString(2));
                                String rightsAssigned = resultSet.getString(3);

                                String[] rightsAssignedArray = rightsAssigned.split(",");
                                StringBuilder rightsAssignedLines = new StringBuilder();
                                for (String right : rightsAssignedArray) {
                                    rightsAssignedLines.append(right).append("\n");
                                }
                                roleRightsReportDTO1.setRightsAssigned(rightsAssignedLines.toString().trim());
                                String roleDescription = resultSet.getString(4);
                                String[] actionsArray = roleDescription.split(",");
                                List<String> actionsList = Arrays.asList(actionsArray);
                                List<String> distinctActionsList = actionsList.stream()
                                        .distinct()
                                        .collect(Collectors.toList());
                                String distinctActions = String.join(",", distinctActionsList);

                                roleRightsReportDTO1.setRoleDescription(distinctActions);

                                roleRightsReportDTO1.setRightsAssignedFlg(resultSet.getString(5));
                                    roleRightsReportDTO1.setRoleStatus(resultSet.getString(6));
                                    roleRightsReportDTO1.setDepartmentCode(resultSet.getString(7));
                                    roleRightsReportDTO1.setTypeAccess(resultSet.getString(8));
                                    roleRightsReportDTO1.setLastCheckerDate(resultSet.getString(9));
                                    roleRightsReportDTOList.add(roleRightsReportDTO1);

                            }
                        }
                    }
                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = e.getMessage();
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }
            responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage(resultMsg);
            responseDataDTO.setResult(roleRightsReportDTOList);
            return responseDataDTO;
        }*/


//    @Override
//    public ResponseDataDTO getRoleRightsReport(RoleRightsReportDTO roleRightsReportDTO) {
//        Connection connection = null;
//        CallableStatement stmt = null;
//        ResponseDataDTO responseDataDTO = null;
//        String resultMsg = "";
//        List<RoleRightsReportDTO> roleRightsReportDTOList = null;
//        Map<String, Object> resultMap = null;
//        RoleRightsReportDTO roleRightsReportDTO1 = null;
//
//
//        try {
//            connection = connectionPoolFactory.getConnection();
//            if (connection != null) {
//                int index = 1;
//                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_ROLE_RIGHT_REPORT.getProcName());
//                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
//                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
//                stmt.setString(index++, roleRightsReportDTO.getDate());
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index, Types.VARCHAR);
//                stmt.execute();
//                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
//                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
//                resultMsg = stmt.getString(index);
//
//                ResultSet resultSet = null;
//                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
//                    resultSet = (ResultSet) stmt.getObject(4);
//                    if (resultSet != null) {
//                        roleRightsReportDTOList = new ArrayList<>();
////                        while (resultSet.next()) {
////                            roleRightsReportDTO1 = new RoleRightsReportDTO();
////                            roleRightsReportDTO1.setRoleName(resultSet.getString(1));
////                            roleRightsReportDTO1.setRoleDesc(resultSet.getString(2));
////                            roleRightsReportDTO1.setRightsAssigned(resultSet.getString(3));
////                            roleRightsReportDTO1.setRoleDescription(resultSet.getString(4));
////                            roleRightsReportDTO1.setRightsAssignedFlg(resultSet.getString(5));
////                            roleRightsReportDTO1.setRoleStatus(resultSet.getString(6));
////                            roleRightsReportDTO1.setDepartmentCode(resultSet.getString(7));
////                            roleRightsReportDTO1.setTypeAccess(resultSet.getString(8));
////                            roleRightsReportDTO1.setLastCheckerDate(resultSet.getString(9));
////                            roleRightsReportDTOList.add(roleRightsReportDTO1);
////                        }
//                        while (resultSet.next()) {
//                            roleRightsReportDTO1 = new RoleRightsReportDTO();
//                            roleRightsReportDTO1.setRoleName(resultSet.getString(1));
//                            roleRightsReportDTO1.setRoleDesc(resultSet.getString(2));
//                            String rightsAssigned = resultSet.getString(3);
//                            // Split the rightsAssigned field into multiple lines
//                            String[] rightsAssignedArray = rightsAssigned.split(",");
//                            StringBuilder rightsAssignedLines = new StringBuilder();
//                            for (String right : rightsAssignedArray) {
//                                rightsAssignedLines.append(right).append("\n");
//                            }
//                            roleRightsReportDTO1.setRightsAssigned(rightsAssignedLines.toString().trim()); // Set the formatted rightsAssigned
//                            roleRightsReportDTO1.setRoleDescription(resultSet.getString(4));
//                            roleRightsReportDTO1.setRightsAssignedFlg(resultSet.getString(5));
//                            roleRightsReportDTO1.setRoleStatus(resultSet.getString(6));
//                            roleRightsReportDTO1.setDepartmentCode(resultSet.getString(7));
//                            roleRightsReportDTO1.setTypeAccess(resultSet.getString(8));
//                            roleRightsReportDTO1.setLastCheckerDate(resultSet.getString(9));
//                            roleRightsReportDTOList.add(roleRightsReportDTO1);
//                        }
//
//                    }
//                }
//            } else {
//                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
//                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
//            }
//
//        } catch (Exception e) {
//            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
//            resultMsg = e.getMessage();
//        } finally {
//            connectionPoolFactory.closeConnection(connection, stmt);
//        }
//        responseDataDTO = new ResponseDataDTO();
//        responseDataDTO.setMessage(resultMsg);
//        responseDataDTO.setResult(roleRightsReportDTOList);
//        return responseDataDTO;
//
//    }



    @Override
    public ResponseDataDTO getAccessControlMatrixreport(AccessControlMatrixReportDTO accessControlMatrixReportDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = null;
        String resultMsg = "";
        List<AccessControlMatrixReportDTO.Details> detailList = null;
//        List<Object> detailList = null;
        Map<String, Object> resultMap = null;

        List<Map<String, String>> branchResultSet = null;
        List<Map<String, String>> roleResultSet = null;


        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_CBDT_REP_ACM.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, accessControlMatrixReportDTO.getRoleFlag());
                stmt.setString(index++, accessControlMatrixReportDTO.getRoleName());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    if (accessControlMatrixReportDTO.getRoleFlag().equalsIgnoreCase("Y")) {
                        resultSet = (ResultSet) stmt.getObject(5);
                        if (resultSet != null) {
                            roleResultSet = new ArrayList<>();
                            Map<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("roleName", resultSet.getString(1));
//                                item.put("branchDecs", resultSet.getString(2));
                                roleResultSet.add(item);
                            }
                            accessControlMatrixReportDTO.setRoleList(roleResultSet);

                        }
                    } else if (accessControlMatrixReportDTO.getRoleFlag().equalsIgnoreCase("N")) {
                        resultSet = (ResultSet) stmt.getObject(5);
                        if (resultSet != null) {
                            detailList = new ArrayList<>();
                            AccessControlMatrixReportDTO.Details item = null;
                            while (resultSet.next()) {
                                item = new AccessControlMatrixReportDTO.Details();

                                item.setRoles(resultSet.getString("ROLES"));
                                item.setPermissions(resultSet.getString("PERMISSION"));
                                item.setCreatedByCreatedDate(resultSet.getString("CREATED_BYCREATED_DATE"));
                                item.setModifiedByModifiedDate(resultSet.getString("MODIFIED_BYMODIFIED_DATE"));
                                item.setVerifiedByVerifiedDate(resultSet.getString("VERIFIED_BYVERIFIED_DATE"));

                                detailList.add(item);

                            }
                            accessControlMatrixReportDTO.setDetailList(detailList);
//
                        }
                    }
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(accessControlMatrixReportDTO);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getUserLogin(String username) {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        ResultSet resultSet = null;
        try {
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                int index = 1;
                stmt = connection.prepareStatement("select logged_in_flg from gbm_user_mgmt_users where username = ?");
                stmt.setString(index, username);
                resultSet = stmt.executeQuery();
                String resultList = " ";
                if (resultSet != null) {
                    while (resultSet.next()) {
                        resultList = resultSet.getString("logged_in_flg");
                    }
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(resultList);

                }
            } else {
                responseDataDTO.setMessage(ErrorMessages.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAgreementStatus() {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        ResultSet resultSet = null;
        try {
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                int index = 1;
                stmt = connection.prepareStatement("select count(*) as agrStatus from gbm_user_mngt_agreement");
                resultSet = stmt.executeQuery();
                String resultList = " ";
                if (resultSet != null) {
                    while (resultSet.next()) {
                        resultList = resultSet.getString("agrStatus");
                    }
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(resultList);

                }
            } else {
                responseDataDTO.setMessage(ErrorMessages.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO saveAgreement(AgreementDTO agreementDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            StringBuffer sbQuery = new StringBuffer();
            sbQuery.append(" INSERT INTO gbm_user_mngt_agreement (cin,sol_id, accepted_by,accepted_date,bank_name) VALUES ");
            sbQuery.append(" (:cin, :solId, :acceptedBy, TO_DATE(:acceptedDate,'dd/mm/yyyy'), :bankName ");
            final Session session = this.getSession();

            final Query<Object[]> query = session.createNativeQuery(sbQuery.toString());
            query.setParameter("cin", agreementDTO.getCIN());
            query.setParameter("solId", UserAccountUtils.getCurrentSolId());
            query.setParameter("acceptedBy", UserAccountUtils.getCurrentUsername());
            query.setParameter("acceptedDate", agreementDTO.getAcceptedDate());
            query.setParameter("bankName", agreementDTO.getBankName());
            int responseCount = query.executeUpdate();

            if(responseCount > 0){
                responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

            }else{
                //failed
                responseDataDTO.setMessage(CommonConstants.FAILURE_CONST);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        }
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO forceLogoutSingleUser(String username) {

        Connection connection = null;
//        PreparedStatement stmt = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        String result = "";
        ResultSet resultSet = null;
        try {
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_LOGOUT_USER.getProcName());
                stmt.setString(index++, username);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                result = stmt.getString(index);
                log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<LOGOUT STATUS>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
                responseDataDTO.setMessage(result);
//                String resultList = "";
//                if (resultSet != null) {
//                    while (resultSet.next()) {
//                        resultList = resultSet.getString("logged_in_flg");
//                    }
//                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
//                    responseDataDTO.setResult(resultList);
//
//                }
            } else {
                responseDataDTO.setMessage(ErrorMessages.INTERNAL_SERVER_ERROR);
                log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<LOGOUT STATUS>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO activateUserReport(ReportSearchCriteriaDto reportSearchCriteriaDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_ACTIVATE_USER.getProcName());
                stmt.setString(CommonConstants.P_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString(CommonConstants.P_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString(CommonConstants.P_USERNAME_IN, reportSearchCriteriaDto.getUsername());
                stmt.registerOutParameter(CommonConstants.P_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.P_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.P_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.P_MSG_DESC_OUT);

                if (stmt.getString(CommonConstants.P_MSG_DESC_OUT) != null) {
                    resultMsg = stmt.getString(CommonConstants.P_MSG_DESC_OUT);
                    responseDataDTO.setMessage(resultMsg);
                }

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


}
