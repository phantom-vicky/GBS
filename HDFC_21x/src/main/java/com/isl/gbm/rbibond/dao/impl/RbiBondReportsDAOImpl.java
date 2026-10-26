package com.isl.gbm.rbibond.dao.impl;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.JdbcConnectionFactory;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.dao.RbiBondReportsDAO;
import com.isl.gbm.rbibond.dto.reports.*;
import com.isl.gbm.rbibond.util.*;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import jakarta.annotation.PostConstruct;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.isl.gbm.rbibond.util.CommonReportsConstants.*;

@Repository
public class RbiBondReportsDAOImpl extends HibernateDao implements RbiBondReportsDAO {


    private static final Logger log = LoggerFactory.getLogger(RbiBondReportsDAOImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private JdbcConnectionFactory jdbcConnectionFactory;
    @Autowired
    private final RbiBondReportsUtil rbiBondReportsUtil;

    public RbiBondReportsDAOImpl(RbiBondReportsUtil rbiBondReportsUtil) {
        this.rbiBondReportsUtil = rbiBondReportsUtil;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JDBCTemplateUtil jDBCTemplateUtil;

    @PostConstruct
    public void init() {
        jDBCTemplateUtil = new JDBCTemplateUtil(jdbcTemplate);
    }

    @Override
    public ResponseDataDTO getBondAckNominiReport(BondAckNominationDTO bondAckNominationDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACK_NOMINEE.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondAckNominationDTO.getSchemeCode());
                stmt.setString(index++, bondAckNominationDTO.getBlaNo());
                stmt.setString(index++, bondAckNominationDTO.getBondSubscriptionId());
                stmt.setString(index++, bondAckNominationDTO.getNomineeId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (bondAckNominationDTO.getBlaNo() == null && bondAckNominationDTO.getBondSubscriptionId() == null
                            && bondAckNominationDTO.getNomineeId() == null) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<BondAckNominationDTO> list = new ArrayList<>();
                            BondAckNominationDTO item = null;
                            while (resultSet.next()) {
                                item = new BondAckNominationDTO();

                                item.setBlaNo(resultSet.getString(1));
                                item.setBondHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));
                                item.setHolderType(resultSet.getString(4));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else if (bondAckNominationDTO.getBlaNo() != null
                            && bondAckNominationDTO.getBondSubscriptionId() == null
                            && bondAckNominationDTO.getNomineeId() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<BondAckNominationDTO> list = new ArrayList<>();
                            BondAckNominationDTO item = null;
                            while (resultSet.next()) {
                                item = new BondAckNominationDTO();

                                item.setBondSubscriptionId(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setSubscriptionAmt(resultSet.getString(3));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondAckNominationDTO.getBlaNo() != null && bondAckNominationDTO.getBondSubscriptionId() != null && bondAckNominationDTO.getNomineeId() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<BondAckNominationDTO> list = new ArrayList<>();
                            BondAckNominationDTO item = null;
                            while (resultSet.next()) {
                                item = new BondAckNominationDTO();

                                item.setNomineeId(resultSet.getString(1));
                                item.setNomineeDate(resultSet.getString(2));
                                item.setNomineeName(resultSet.getString(3));
                                item.setBondSubscriptionId(resultSet.getString(4));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondAckNominationDTO.getBlaNo() != null && bondAckNominationDTO.getBondSubscriptionId() != null && bondAckNominationDTO.getNomineeId() != null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<BondAckNominationDTO> list = new ArrayList<>();
                            BondAckNominationDTO item = null;
                            while (resultSet.next()) {
                                item = new BondAckNominationDTO();

                                item.setBlaNo(resultSet.getString(1));
                                item.setBondHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));
                                item.setHolderType(resultSet.getString(4));
                                item.setBondSubscriptionId(resultSet.getString(5));
                                item.setHolderName(resultSet.getString(6));
                                item.setSubscriptionAmt(resultSet.getString(7));
                                item.setNomineeId(resultSet.getString(8));
                                item.setNomineeDate(resultSet.getString(9));
                                item.setNomineeName(resultSet.getString(10));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getminorToMajorIntimationRepValues(RbiBondMinorToMajorIntimateRepDTO rbiBondMinorToMajorIntimateRepDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MINR_TO_MAJR_INTI_REP.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondMinorToMajorIntimateRepDTO.getBlaFlag());
                stmt.setString(index++, rbiBondMinorToMajorIntimateRepDTO.getModuleId());
                stmt.setString(index++, rbiBondMinorToMajorIntimateRepDTO.getName());
                stmt.setString(index++, rbiBondMinorToMajorIntimateRepDTO.getPan());
                stmt.setString(index++, rbiBondMinorToMajorIntimateRepDTO.getBlaNo());
                stmt.setString(index++, rbiBondMinorToMajorIntimateRepDTO.getFromDate());
                stmt.setString(index++, rbiBondMinorToMajorIntimateRepDTO.getToDate());
//                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    ResultSet resultSet = (ResultSet) stmt.getObject(index - 2);



                        if (rbiBondMinorToMajorIntimateRepDTO.getBlaFlag().equalsIgnoreCase("S")) {
                            if (resultSet != null) {
                                List<RbiBondMinorToMajorIntimateRepDTO> list = new ArrayList<>();
                                RbiBondMinorToMajorIntimateRepDTO item = null;
                                while (resultSet.next()) {
                                    item = new RbiBondMinorToMajorIntimateRepDTO();
                                    item.setSchmeCode(resultSet.getString(1));
                                    item.setSchemeName(resultSet.getString(2));
                                    item.setSchemeStatus(resultSet.getString(3));
                                    list.add(item);
                                }
                                responseDataDTO.setResult(list);
                            }
                        }


                       else if (rbiBondMinorToMajorIntimateRepDTO.getModuleId()!=null&&rbiBondMinorToMajorIntimateRepDTO.getBlaFlag().equalsIgnoreCase("P")) {
                            if (resultSet != null) {
                                List<RbiBondMinorToMajorIntimateRepDTO> list = new ArrayList<>();
                                RbiBondMinorToMajorIntimateRepDTO item = null;
                                while (resultSet.next()) {
                                    item = new RbiBondMinorToMajorIntimateRepDTO();
                                    item.setBondHolderName(resultSet.getString(1));
                                    item.setPageNo(resultSet.getString(2));

                                    list.add(item);
                                }
                                responseDataDTO.setResult(list);
                            }
                        }

                    else if (rbiBondMinorToMajorIntimateRepDTO.getModuleId()!=null&&rbiBondMinorToMajorIntimateRepDTO.getBlaFlag().equalsIgnoreCase("B")) {
                            if (resultSet != null) {
                                List<RbiBondMinorToMajorIntimateRepDTO> list = new ArrayList<>();
                                RbiBondMinorToMajorIntimateRepDTO item = null;
                                while (resultSet.next()) {
                                    item = new RbiBondMinorToMajorIntimateRepDTO();

                                    item.setModeOfHolding(resultSet.getString(1));
                                    item.setBondHolderName(resultSet.getString(2));
                                    item.setPan(resultSet.getString(3));
                                    item.setBlaNo(resultSet.getString(4));


                                    list.add(item);
                                }
                                responseDataDTO.setResult(list);
                            }
                        }   else if (rbiBondMinorToMajorIntimateRepDTO.getModuleId()!=null&&rbiBondMinorToMajorIntimateRepDTO.getFromDate()!=null&&rbiBondMinorToMajorIntimateRepDTO.getToDate()!=null&&rbiBondMinorToMajorIntimateRepDTO.getBlaFlag().equalsIgnoreCase("D")) {
                        if (resultSet != null) {
                            List<RbiBondMinorToMajorIntimateRepDTO> list = new ArrayList<>();
                            RbiBondMinorToMajorIntimateRepDTO item = null;
                            while (resultSet.next()) {
                                item = new RbiBondMinorToMajorIntimateRepDTO();
//                                item.setBondHolderName(resultSet.getString("holder_name"));
//                                item.setAdd1(resultSet.getString("address1"));
//                                item.setAdd2(resultSet.getString("address2"));
//                                item.setAdd3(resultSet.getString("address3"));
//                                item.setAdd4(resultSet.getString("address4"));
//                                item.setAdd5(resultSet.getString("address5"));
//                                item.setAdd6(resultSet.getString("address6"));
//                                item.setBlaNo(resultSet.getString("bla_no"));
//                                item.setGuarName(resultSet.getString("guardian_name"));
                                item.setBlaNo(resultSet.getString(1));
                                item.setBondHolderDOB(resultSet.getString(2));
                                item.setName(resultSet.getString(3));
                                item.setAdd1(resultSet.getString(4));
                                item.setAdd2(resultSet.getString(5));
                                item.setAdd3(resultSet.getString(6));
                                item.setCity(resultSet.getString(7));
                                item.setState(resultSet.getString(8));
                                item.setPinCode(resultSet.getString(9));
                                item.setMobileNum(resultSet.getString(10));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO getBondNominiRegsReport(BondNominiRegsReportDTO bondNominiRegsReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<BondNominiRegsReportDTO> list = Collections.emptyList();
        String totalRecords = null;
        String resultMsg = "SUCCESS";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, bondNominiRegsReportDTO.getSchemeCode());
            inParams.put(flag, bondNominiRegsReportDTO.getFlag());
            inParams.put(name, bondNominiRegsReportDTO.getHolderName());
            inParams.put(panNumber, bondNominiRegsReportDTO.getPanNumber());
            inParams.put(blaNumber, bondNominiRegsReportDTO.getBlaNumber());
            inParams.put(subscriptionID, bondNominiRegsReportDTO.getSubscriptionID());
            inParams.put(p_page_no_in, bondNominiRegsReportDTO.getPageNo());
            inParams.put(p_page_size_in, bondNominiRegsReportDTO.getPageSize());
            inParams.put(p_search_value_in, bondNominiRegsReportDTO.getSearchValue());
            inParams.put(fromDate, bondNominiRegsReportDTO.getFromDate());
            inParams.put(toDate, bondNominiRegsReportDTO.getToDate());


            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(subscriptionID, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR)

            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BondNominiRegsReportDTO item = new BondNominiRegsReportDTO();
                if (bondNominiRegsReportDTO.getFlag().equals("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (bondNominiRegsReportDTO.getFlag().equals("P")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNumber(rs.getString(2));
                } else if (bondNominiRegsReportDTO.getFlag().equals("B")) {
                    item.setModeOfHolding(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setPanNumber(rs.getString(3));
                    item.setBlaNumber(rs.getString(4));
                } else if (bondNominiRegsReportDTO.getFlag().equals("BR")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNumber(rs.getString(2));
                    item.setModeOfHolding(rs.getString(3));
                    item.setSubscriptionID(rs.getString(4));
                } else if (bondNominiRegsReportDTO.getFlag().equals("D")) {
                    item.setDateOfIssue(rs.getString(1));
                    item.setAmount(rs.getString(2));
                    item.setDateOfRepayment(rs.getString(3));
                    item.setNomineeName(rs.getString(4));
                    item.setDob(rs.getString(5));
                    item.setRelationshipHolder(rs.getString(6));
                    item.setStatus(rs.getString(7));
                    item.setParticularsOfBankAccount(rs.getString(8));
                    item.setNomineeAddress(rs.getString(9));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_rep_nominee_reg",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<BondNominiRegsReportDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBondInterestPayMisReportValues(RbiBondInterestPaymentMisReportDto rbiBondInterestPaymentMisReportDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, Object> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_INT_MIS_REP.getProcName());
                stmt.setString("p_enterer_id_in", UserAccountUtils.getCurrentUsername());
                stmt.setString("p_enterer_sol_id_in", UserAccountUtils.getCurrentSolId());
                stmt.setString("p_module_id_in", rbiBondInterestPaymentMisReportDto.getSchemeCode());
                stmt.setString("p_interest_paid_in", rbiBondInterestPaymentMisReportDto.getPaidMonth());
                stmt.setString("p_finacial_year_in", rbiBondInterestPaymentMisReportDto.getFinancialYear());
                stmt.setString("p_page_number", rbiBondInterestPaymentMisReportDto.getPageNo());
                stmt.setString("p_page_size", rbiBondInterestPaymentMisReportDto.getPageSize());
                stmt.setString("P_SEARCH_VALUE_IN", rbiBondInterestPaymentMisReportDto.getSearchValue());
                stmt.setString("P_SORT_COLUMN", rbiBondInterestPaymentMisReportDto.getSortField());
                stmt.setString("p_sort_order_in", rbiBondInterestPaymentMisReportDto.getSortOrder());
                stmt.registerOutParameter("P_CURSOR_OUT", OracleTypes.CURSOR);
                stmt.registerOutParameter("P_MAX_PAGES_OUT", Types.VARCHAR);
                stmt.registerOutParameter("p_tot_grossIntAmt", Types.VARCHAR);
                stmt.registerOutParameter("p_tot_tdsAmt", Types.VARCHAR);
                stmt.registerOutParameter("p_tot_netIntAmt", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_desc_out", Types.VARCHAR);

                stmt.execute();


                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString("p_msg_desc_out"));
                resultMsg = stmt.getString("p_msg_desc_out");
                if (stmt.getString("p_msg_out") != null && stmt.getString("p_msg_out").equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject("P_CURSOR_OUT");


                    if (resultSet != null) {
                        List<RbiBondInterestPaymentMisReportDto> list = new ArrayList<>();
                        RbiBondInterestPaymentMisReportDto rbiBondInterestPaymentMisReportDto1;
                        while (resultSet.next()) {
                            rbiBondInterestPaymentMisReportDto1 = new RbiBondInterestPaymentMisReportDto();
                            rbiBondInterestPaymentMisReportDto1.setsNo(resultSet.getString("rn"));
                            rbiBondInterestPaymentMisReportDto1.setBranchId(resultSet.getString("branchId"));
                            rbiBondInterestPaymentMisReportDto1.setGrossIntAmt(resultSet.getString("grossIntAmt"));
                            rbiBondInterestPaymentMisReportDto1.setTdsAmt(resultSet.getString("tdsAmt"));
                            rbiBondInterestPaymentMisReportDto1.setNetIntAmt(resultSet.getString("netIntAmt"));
                            list.add(rbiBondInterestPaymentMisReportDto1);
                        }
                        resultMap = new HashMap<>();
                        resultMap.put("dataList", list);
                        resultMap.put("totGrossIntAmt", stmt.getString("p_tot_grossIntAmt"));
                        resultMap.put("totTdsAmt", stmt.getString("p_tot_tdsAmt"));
                        resultMap.put("totNetIntAmt", stmt.getString("p_tot_netIntAmt"));
                        resultMap.put("maxPagesOut", stmt.getString("P_MAX_PAGES_OUT"));
                        resultMap.put("solDesc", rbiBondInterestPaymentMisReportDto.getSolDesc());


                        if (rbiBondInterestPaymentMisReportDto.getDownloadType() != null && rbiBondInterestPaymentMisReportDto.getDownloadType().length() > 0) {
                            if (rbiBondInterestPaymentMisReportDto.getDownloadType().equalsIgnoreCase("excel")) {
                                FileResponseDto fileResponseDto = new FileResponseDto();
                                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil.excelFileGeneratorForInterestPayMisReport(resultMap)));
                                fileResponseDto.setFileName("BOND INTEREST PAYMENT MIS REPORT - SOL WISE.xlsx");
                                fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                                responseDataDTO.setResult(fileResponseDto);
                                responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                            }

                            if (rbiBondInterestPaymentMisReportDto.getDownloadType().equalsIgnoreCase("pdf")) {
                                FileResponseDto fileResponseDto = new FileResponseDto();
                                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil.pdfFileGeneratorForInterestPayMisReport(resultMap)));
                                fileResponseDto.setFileName("BOND INTEREST PAYMENT MIS REPORT - SOL WISE.pdf");
                                fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                                responseDataDTO.setResult(fileResponseDto);
                                responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                                return responseDataDTO;

                            }


                        } else {
                            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                            responseDataDTO.setResult(resultMap);

                        }
                    }
                }
                responseDataDTO.setMessage(stmt.getString("p_msg_desc_out"));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(CommonConstants.EXCEPTION_MESSAGE, ex);
            responseDataDTO.setMessage(ex.getMessage());

        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO getNoticeCancelReport(BondNoticeCancelReportDTO bondNoticeCancelReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<BondNoticeCancelReportDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;


        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, bondNoticeCancelReportDTO.getSchemeCode());
            inParams.put(flag, bondNoticeCancelReportDTO.getFlag());
            inParams.put(fromDate, bondNoticeCancelReportDTO.getFromDate());
            inParams.put(toDate, bondNoticeCancelReportDTO.getToDate());
            inParams.put(p_page_no_in, bondNoticeCancelReportDTO.getPageNo());
            inParams.put(p_page_size_in, bondNoticeCancelReportDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=bondNoticeCancelReportDTO.getSearchValue()&&!bondNoticeCancelReportDTO.getSearchValue().trim().isEmpty())?bondNoticeCancelReportDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR)
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BondNoticeCancelReportDTO item = new BondNoticeCancelReportDTO();
                if (bondNoticeCancelReportDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (bondNoticeCancelReportDTO.getFlag() != null && bondNoticeCancelReportDTO.getFlag().equalsIgnoreCase("A")) {
                    item.setSchemeName(rs.getString(1));
                    item.setDateOfIssue(rs.getString(2));
                    item.setBlaNumber(rs.getString(3));
                    item.setSubscriptionId(rs.getString(4));
                    item.setNomineeName(rs.getString(5));
                    item.setAmount(rs.getString(6));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_notice_cancel_nomination",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<BondNoticeCancelReportDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAckCancelNomineReport(BondCancelNomineeReportDTO bondCancelNomineeReportDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACK_CAN_NOMINEE.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondCancelNomineeReportDTO.getSchemeCode());
                stmt.setString(index++, bondCancelNomineeReportDTO.getBlaNo());
                stmt.setString(index++, bondCancelNomineeReportDTO.getBondSubscriptionId());
                stmt.setString(index++, bondCancelNomineeReportDTO.getNomineeId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (bondCancelNomineeReportDTO.getBlaNo() == null && bondCancelNomineeReportDTO.getBondSubscriptionId() == null && bondCancelNomineeReportDTO.getNomineeId() == null) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<BondCancelNomineeReportDTO> list = new ArrayList<>();
                            BondCancelNomineeReportDTO item = null;
                            while (resultSet.next()) {
                                item = new BondCancelNomineeReportDTO();

                                item.setBlaNo(resultSet.getString(1));
                                item.setBondHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));
                                item.setHolderType(resultSet.getString(4));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else if (bondCancelNomineeReportDTO.getBlaNo() != null
                            && bondCancelNomineeReportDTO.getBondSubscriptionId() == null
                            && bondCancelNomineeReportDTO.getNomineeId() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<BondCancelNomineeReportDTO> list = new ArrayList<>();
                            BondCancelNomineeReportDTO item = null;
                            while (resultSet.next()) {
                                item = new BondCancelNomineeReportDTO();

                                item.setBondSubscriptionId(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setSubscriptionAmt(resultSet.getString(3));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondCancelNomineeReportDTO.getBlaNo() != null
                            && bondCancelNomineeReportDTO.getBondSubscriptionId() != null
                            && bondCancelNomineeReportDTO.getNomineeId() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<BondCancelNomineeReportDTO> list = new ArrayList<>();
                            BondCancelNomineeReportDTO item = null;
                            while (resultSet.next()) {
                                item = new BondCancelNomineeReportDTO();
                                item.setNomineeDate(resultSet.getString(1));
                                item.setDateOfCancel(resultSet.getString(2));
                                item.setNomineeName(resultSet.getString(3));
                                item.setBondSubscriptionId(resultSet.getString(4));
                                item.setNomineeId(resultSet.getString(5));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondCancelNomineeReportDTO.getBlaNo() != null && bondCancelNomineeReportDTO.getBondSubscriptionId() != null && bondCancelNomineeReportDTO.getNomineeId() != null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<BondCancelNomineeReportDTO> list = new ArrayList<>();
                            BondCancelNomineeReportDTO item = null;
                            while (resultSet.next()) {
                                item = new BondCancelNomineeReportDTO();

                                item.setBlaNo(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));
                                item.setHolderType(resultSet.getString(4));
                                item.setBondNomineDate(resultSet.getString(5));
                                item.setBondSubscriptionId(resultSet.getString(6));
                                item.setSubscriptionAmt(resultSet.getString(7));
                                item.setNomineeName(resultSet.getString(8));
                                item.setDateOfCancel(resultSet.getString(9));
                                item.setNomineeId(resultSet.getString(10));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getCertificateInvtReport(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, Object> resultMap = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<BondCertificateInvtReportDTO> bondCertificateInvtReportDTOList = null;
        List<BondCertificateInvtReportDTO> bondCertificateInvtReportDTOList1 = null;
        BondCertificateInvtReportDTO bondCertificateInvtReportDTO1 = null;

        double totalInvestmentAmt = 0;
        double totalRepaymentAmt = 0;
        double totaltTransferAmt = 0;
        double totalBalance = 0;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_COI.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondCertificateInvtReportDTO.getSchemeCode());
                stmt.setString(index++, bondCertificateInvtReportDTO.getName());
                stmt.setString(index++, bondCertificateInvtReportDTO.getPanNo());
                stmt.setString(index++, bondCertificateInvtReportDTO.getBlaNo());
                stmt.setString(index++, bondCertificateInvtReportDTO.getSubscriptionId());
                stmt.setString(index++, bondCertificateInvtReportDTO.getFromDate());
                stmt.setString(index++, bondCertificateInvtReportDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    String schemeFlag = stmt.getString(11);
                    ResultSet rs = (ResultSet) stmt.getObject(10);
                    if (bondCertificateInvtReportDTO.getSchemeCode() == null) {

                        if (rs != null) {
                            bondCertificateInvtReportDTOList = new ArrayList<>();
                            while (rs.next()) {
                                bondCertificateInvtReportDTO1 = new BondCertificateInvtReportDTO();
                                bondCertificateInvtReportDTO1.setSchemeCode(rs.getString("sub_code"));
                                bondCertificateInvtReportDTO1.setSchemeName(rs.getString("sub_code_desc"));
                                bondCertificateInvtReportDTO1.setSchemeStatus(rs.getString("scheme_status"));
                                bondCertificateInvtReportDTOList.add(bondCertificateInvtReportDTO1);
                            }
                            resultMap = new HashMap<>();
                            resultMap.put("bondCertificateInvtReportDTOList", bondCertificateInvtReportDTOList);
                            responseDataDTO.setResult(resultMap);
                        }

                    } else if (bondCertificateInvtReportDTO.getSchemeCode() != null) {
                        if (schemeFlag.equalsIgnoreCase("Y")) {
                            if (rs != null) {
                                bondCertificateInvtReportDTOList = new ArrayList<>();

                                while (rs.next()) {

                                    bondCertificateInvtReportDTO1 = new BondCertificateInvtReportDTO();
                                    bondCertificateInvtReportDTO1.setBlaNo(rs.getString("bla_no"));
                                    bondCertificateInvtReportDTO1.setMaturityAmt(rs.getString("maturity_amt"));
                                    bondCertificateInvtReportDTO1.setMaturityDate(rs.getString("due_Date"));
                                    bondCertificateInvtReportDTO1.setNomineeId(rs.getString("nomination"));
                                    bondCertificateInvtReportDTO1.setBondType(rs.getString("bond_type"));
                                    bondCertificateInvtReportDTO1.setBondAddress(rs.getString("address"));
                                    bondCertificateInvtReportDTO1.setDepositeDate(rs.getString("deposit_date"));
                                    bondCertificateInvtReportDTO1.setModeOfHolding(rs.getString("mode_of_operation"));
                                    bondCertificateInvtReportDTO1.setHolderName(rs.getString("name_of_the_hldr"));
                                    bondCertificateInvtReportDTO1.setNasikDate(rs.getString("nasik_date"));
                                    bondCertificateInvtReportDTO1.setCertificateNo(rs.getString("certi_no"));
                                    bondCertificateInvtReportDTO1.setIntrestRate(rs.getString("int_rate"));
                                    bondCertificateInvtReportDTO1.setCity(rs.getString("city"));
                                    bondCertificateInvtReportDTO1.setState(rs.getString("state"));
                                    bondCertificateInvtReportDTO1.setPinCode(rs.getString("pin_code"));
                                    bondCertificateInvtReportDTOList.add(bondCertificateInvtReportDTO1);
                                }
                                resultMap = new HashMap<>();
                                resultMap.put("bondCertificateInvtReportDTOList", bondCertificateInvtReportDTOList);
                                resultMap.put("schemeFlag", schemeFlag);
                                responseDataDTO.setResult(resultMap);
                            }

                        } else if (schemeFlag.equalsIgnoreCase("N")) {
                            if (rs != null) {
                                bondCertificateInvtReportDTOList1 = new ArrayList<>();

                                while (rs.next()) {
                                    bondCertificateInvtReportDTO1 = new BondCertificateInvtReportDTO();
                                    bondCertificateInvtReportDTO1.setBlaNo(rs.getString("bla_no"));
                                    bondCertificateInvtReportDTO1.setModeOfHolding(rs.getString("mode_of_operation"));
                                    bondCertificateInvtReportDTO1.setHolderName(rs.getString("name_of_the_hldr"));
                                    bondCertificateInvtReportDTO1.setCertificateNo(rs.getString("certi_no"));
                                    bondCertificateInvtReportDTO1.setBondAddress(rs.getString("address"));
                                    bondCertificateInvtReportDTO1.setCity(rs.getString("city"));
                                    bondCertificateInvtReportDTO1.setState(rs.getString("state"));
                                    bondCertificateInvtReportDTO1.setNotificationDate(rs.getString("govt_not_date"));
                                    bondCertificateInvtReportDTO1.setNotificationNo(rs.getString("govt_not_no"));
                                    bondCertificateInvtReportDTO1.setNomineeId(rs.getString("nomination_id"));
                                    bondCertificateInvtReportDTO1.setPinCode(rs.getString("pin_code"));
                                    bondCertificateInvtReportDTO1.setIntPaymtDate1(rs.getString("int_paym_date1"));
                                    bondCertificateInvtReportDTO1.setIntPaymtDate2(rs.getString("int_paym_date2"));
                                    bondCertificateInvtReportDTO1.setRepaymentAmt(rs.getString("repayment_amt"));
                                    bondCertificateInvtReportDTO1.setSubscriptionId(rs.getString("subscription_id"));
                                    bondCertificateInvtReportDTO1.setReceivingOffice(rs.getString("Name_of_receiving_office"));
                                    totalRepaymentAmt = totalRepaymentAmt + Double.parseDouble(
                                            (rs.getString("repayment_amt") != null) ? (rs.getString("repayment_amt"))
                                                    : ("0"));
                                    bondCertificateInvtReportDTO1.setRepaymentDate(rs.getString("repayment_date"));
                                    bondCertificateInvtReportDTO1.setTransferAmt(rs.getString("transfer_amt"));
                                    totaltTransferAmt = totaltTransferAmt + Double.parseDouble(
                                            (rs.getString("transfer_amt") != null) ? (rs.getString("transfer_amt"))
                                                    : ("0"));
                                    bondCertificateInvtReportDTO1.setTransferDate(rs.getString("transfer_date"));
                                    bondCertificateInvtReportDTO1.setBranchCode(rs.getString("branch_Code"));
                                    bondCertificateInvtReportDTO1.setBalance(rs.getString("balance"));
                                    totalBalance = totalBalance + Double.parseDouble(
                                            (rs.getString("balance") != null) ? (rs.getString("balance")) : ("0"));
                                    bondCertificateInvtReportDTO1
                                            .setInitialUndrBankSeal(rs.getString("initials_under_banl_Seal"));
                                    bondCertificateInvtReportDTO1.setInvestmentAmt(rs.getString("investment_amt"));
                                    totalInvestmentAmt = totalInvestmentAmt + Double.parseDouble(
                                            (rs.getString("investment_amt") != null) ? (rs.getString("investment_amt"))
                                                    : ("0"));
                                    bondCertificateInvtReportDTO1.setInvestmentDate(rs.getString("investment_date"));

                                    bondCertificateInvtReportDTOList1.add(bondCertificateInvtReportDTO1);

                                }
                                // responseDataDTO.setResult(bondCertificateInvtReportDTOList);
                                resultMap = new HashMap<>();
                                resultMap.put("bondCertificateInvtReportDTOList1", bondCertificateInvtReportDTOList1);
                                resultMap.put("totalInvestmentAmt", totalInvestmentAmt);
                                resultMap.put("totaltTransferAmt", totaltTransferAmt);
                                resultMap.put("totalRepaymentAmt", totalRepaymentAmt);
                                resultMap.put("totalBalance", totalBalance);
                                resultMap.put("schemeFlag", schemeFlag);
                                responseDataDTO.setResult(resultMap);

                            }

                        }
                    }

                }

            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }



    @Override
    public ResponseDataDTO getPanValidationBondSchemeReport(PANValidationReportDTO panValidationReportDTO) {
        List<PANValidationReportDTO> list = new ArrayList<>();

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, Object> finalData = new HashMap<>();
        List<PANValidationReportDTO> panValidationReportDTOList = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords=null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put("p_user_id_in", UserAccountUtils.getCurrentUsername());
            inParams.put("p_user_sol_id_in", UserAccountUtils.getCurrentSolId());
            inParams.put("p_sch_code_in",panValidationReportDTO.getSchemeCode() );
            inParams.put("p_bond_scheme", panValidationReportDTO.getBondScheme());
            inParams.put("p_pan_tri", panValidationReportDTO.getPanTriggered());
            inParams.put("p_status_in", panValidationReportDTO.getStatus());
            inParams.put("p_from_date_in", panValidationReportDTO.getFromDate());
            inParams.put("p_to_date_in", panValidationReportDTO.getToDate());
            inParams.put("p_flag_in", panValidationReportDTO.getFlagIn());
            inParams.put("p_page_no_in", panValidationReportDTO.getPageNoIn());
            inParams.put("p_page_size_in", panValidationReportDTO.getPageSizeIn());
            inParams.put("p_search_value_in", (null!=panValidationReportDTO.getSearchValIn()
                    &&!panValidationReportDTO.getSearchValIn().trim().isEmpty())?
                    panValidationReportDTO.getSearchValIn():null);

            // Define SQL parameters
            SqlParameter[] sqlParameters = {
                    new SqlParameter("p_user_id_in", Types.VARCHAR),
                    new SqlParameter("p_user_sol_id_in", Types.VARCHAR),
                    new SqlParameter("p_sch_code_in", Types.VARCHAR),
                    new SqlParameter("p_bond_scheme",Types.VARCHAR),
                    new SqlParameter("p_pan_tri",Types.VARCHAR),
                    new SqlParameter("p_status_in",Types.VARCHAR),
                    new SqlParameter("p_from_date_in", Types.VARCHAR),
                    new SqlParameter("p_to_date_in",Types.VARCHAR),
                    new SqlParameter("p_flag_in",Types.VARCHAR),
                    new SqlParameter("p_page_no_in", Types.VARCHAR),
                    new SqlParameter("p_page_size_in", Types.VARCHAR),
                    new SqlParameter("p_search_value_in", Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter("p_total_records_out", OracleTypes.VARCHAR),
                    new SqlOutParameter("p_cursor_val_out", OracleTypes.CURSOR),
                    new SqlOutParameter("p_msg_out", OracleTypes.VARCHAR),
                    new SqlOutParameter("p_msg_desc_out", OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            //rowMappers.put(, this::mapListRowToExcelData);

            rowMappers.put("p_cursor_val_out", (rs, rowNum) -> {

                PANValidationReportDTO item = new PANValidationReportDTO();

                if (panValidationReportDTO.getSchemeCode() == null && panValidationReportDTO.getFromDate() == null && panValidationReportDTO.getToDate() == null) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                    list.add(item);
                }
                else if (panValidationReportDTO.getSchemeCode() != null && panValidationReportDTO.getFromDate() != null && panValidationReportDTO.getToDate() != null) {
                    item.setPanTriggeredFrom(rs.getString(1));
                    item.setTriggedDate(rs.getString(2));
                    item.setTriggedCheckerID(rs.getString(3));
                    item.setPanNo(rs.getString(4));
                    item.setNewPANNO(rs.getString(5));
                    item.setName(rs.getString(6));
                    item.setNewName(rs.getString(7));
                    item.setDOB(rs.getString(8));
                    item.setNewDOB(rs.getString(9));
                    item.setAadharSeeding(rs.getString(10));
                    item.setIsSendNSDL(rs.getString(11));
                    item.setSendNSDLDate(rs.getString(12));
                    item.setStatus(rs.getString(13));
                    item.setDOBStatus(rs.getString(14));
                    item.setPanNoStatus(rs.getString(15));
                    item.setNameStatus(rs.getString(16));
                    item.setAadharStatus(rs.getString(17));
                    item.setFileType(rs.getString(18));
                    item.setRemark(rs.getString(19));
                    item.setUploadBy(rs.getString(20));
                    item.setUploadDate(rs.getString(21));
                    item.setModifyBy(rs.getString(22));
                    item.setModifyDate(rs.getString(23));
                    item.setReSendNSDL(rs.getString(24));
                    item.setReSendNSDLDate(rs.getString(25));
                    item.setNetBankingDate(rs.getString(26));
                    item.setAuditDate(rs.getString(27));
                    item.setUploadFileName(rs.getString(28));

                    list.add(item);
                }
                return item;

            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_BOND_PANVAL_REPORT",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            panValidationReportDTOList = (List<PANValidationReportDTO>) result.get("p_cursor_val_out");
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            if (CollectionUtils.isEmpty(panValidationReportDTOList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("panValidationReportDTOList", panValidationReportDTOList);
                responseDataDTO.setResult(finalData);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e);
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setTotal(Double.parseDouble(totalRecords));
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getTaxWaiverDocumentsReport(TaxWaiverDocumentsReportDTO taxWaiverDocumentsReportDTO) {
        List<TaxWaiverDocumentsReportDTO> list = new ArrayList<>();

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, Object> finalData = new HashMap<>();
        List<TaxWaiverDocumentsReportDTO> taxWaiverDocumentsReportDTOList = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords=null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put("p_user_id_in", UserAccountUtils.getCurrentUsername());
            inParams.put("p_user_sol_id_in", UserAccountUtils.getCurrentSolId());
            inParams.put("p_sch_code_in",taxWaiverDocumentsReportDTO.getSchemeCode() );
            inParams.put("p_from_date_in", taxWaiverDocumentsReportDTO.getFromDate());
            inParams.put("p_to_date_in", taxWaiverDocumentsReportDTO.getToDate());
            inParams.put("p_flag_in", taxWaiverDocumentsReportDTO.getFlagIn());
            inParams.put("p_page_no_in", taxWaiverDocumentsReportDTO.getPageNoIn());
            inParams.put("p_page_size_in", taxWaiverDocumentsReportDTO.getPageSizeIn());
            inParams.put("p_search_value_in", (null!=taxWaiverDocumentsReportDTO.getSearchValIn()
                    &&!taxWaiverDocumentsReportDTO.getSearchValIn().trim().isEmpty())?
                    taxWaiverDocumentsReportDTO.getSearchValIn():null);

            // Define SQL parameters
            SqlParameter[] sqlParameters = {
                    new SqlParameter("p_user_id_in", Types.VARCHAR),
                    new SqlParameter("p_user_sol_id_in", Types.VARCHAR),
                    new SqlParameter("p_sch_code_in", Types.VARCHAR),
                    new SqlParameter("p_from_date_in", Types.VARCHAR),
                    new SqlParameter("p_to_date_in",Types.VARCHAR),
                    new SqlParameter("p_flag_in",Types.VARCHAR),
                    new SqlParameter("p_page_no_in", Types.VARCHAR),
                    new SqlParameter("p_page_size_in", Types.VARCHAR),
                    new SqlParameter("p_search_value_in", Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter("p_total_records_out", OracleTypes.VARCHAR),
                    new SqlOutParameter("p_cursor_val_out", OracleTypes.CURSOR),
                    new SqlOutParameter("p_msg_out", OracleTypes.VARCHAR),
                    new SqlOutParameter("p_msg_desc_out", OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();

            rowMappers.put("p_cursor_val_out", (rs, rowNum) -> {

                TaxWaiverDocumentsReportDTO item = new TaxWaiverDocumentsReportDTO();

                if (taxWaiverDocumentsReportDTO.getSchemeCode() == null && taxWaiverDocumentsReportDTO.getFromDate() == null && taxWaiverDocumentsReportDTO.getToDate() == null) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                    list.add(item);
                }
                else if (taxWaiverDocumentsReportDTO.getSchemeCode() != null && taxWaiverDocumentsReportDTO.getFromDate() != null && taxWaiverDocumentsReportDTO.getToDate() != null) {
                  /*  item.setPanTriggeredFrom(rs.getString(1));
                    item.setTriggedDate(rs.getString(2));
                    item.setTriggedMakerID(rs.getString(3));
                    item.setTriggedCheckerID(rs.getString(4));
                    item.setPanNo(rs.getString(5));
                    item.setNewPANNO(rs.getString(6));
                    item.setName(rs.getString(7));
                    item.setNewName(rs.getString(8));
                    item.setDOB(rs.getString(9));
                    item.setNewDOB(rs.getString(10));
                    item.setAadharSeeding(rs.getString(11));
                    item.setIsSendNSDL(rs.getString(12));
                    item.setSendNSDLDate(rs.getString(13));
                    item.setStatus(rs.getString(14));
                    item.setDOBStatus(rs.getString(15));
                    item.setPanNoStatus(rs.getString(16));
                    item.setNameStatus(rs.getString(17));
                    item.setAadharStatus(rs.getString(18));
                    item.setFileType(rs.getString(19));
                    item.setRemark(rs.getString(20));
                    item.setUploadBy(rs.getString(21));
                    item.setUploadDate(rs.getString(22));
                    item.setModifyBy(rs.getString(23));
                    item.setModifyDate(rs.getString(24));
                    item.setReSendNSDL(rs.getString(25));
                    item.setReSendNSDLDate(rs.getString(26));
                    item.setNetBankingDate(rs.getString(27));
                    item.setAuditDate(rs.getString(28));
                    item.setUploadFileName(rs.getString(29));*/
                    list.add(item);
                }
                return item;

            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_BOND_PANVAL_REPORT",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            taxWaiverDocumentsReportDTOList = (List<TaxWaiverDocumentsReportDTO>) result.get("p_cursor_val_out");
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            if (CollectionUtils.isEmpty(taxWaiverDocumentsReportDTOList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("taxWaiverDocumentsReportDTOList",taxWaiverDocumentsReportDTOList);
                responseDataDTO.setResult(finalData);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e);
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setTotal(Double.parseDouble(totalRecords));
        return responseDataDTO;

    }



    @Override
    public ResponseDataDTO getPreMaturityMarkingReport(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO) {
        List<PreMaturityMarkingReportDTO> list = new ArrayList<>();

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, Object> finalData = new HashMap<>();
        List<PreMaturityMarkingReportDTO> preMaturityMarkingReportDTOList = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords=null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put("p_user_id_in", UserAccountUtils.getCurrentUsername());
            inParams.put("p_user_sol_id_in", UserAccountUtils.getCurrentSolId());
            inParams.put("p_sch_code_in",preMaturityMarkingReportDTO.getSchemeCode() );
            inParams.put("p_flag_in", preMaturityMarkingReportDTO.getFlagIn());
            inParams.put("p_pan_no_in", preMaturityMarkingReportDTO.getPanNo());
            inParams.put("p_bla_no_in", preMaturityMarkingReportDTO.getBlaNo());
            inParams.put("p_subs_id_in", preMaturityMarkingReportDTO.getSubscriptionId());
            inParams.put("p_from_date_in", preMaturityMarkingReportDTO.getFromDate());
            inParams.put("p_to_date_in", preMaturityMarkingReportDTO.getToDate());
            inParams.put("p_page_no_in", preMaturityMarkingReportDTO.getPageNoIn());
            inParams.put("p_page_size_in", preMaturityMarkingReportDTO.getPageSizeIn());
            inParams.put("p_search_value_in", (null!=preMaturityMarkingReportDTO.getSearchValIn()
                    &&!preMaturityMarkingReportDTO.getSearchValIn().trim().isEmpty())?
                    preMaturityMarkingReportDTO.getSearchValIn():null);

            // Define SQL parameters
            SqlParameter[] sqlParameters = {
                    new SqlParameter("p_user_id_in", Types.VARCHAR),
                    new SqlParameter("p_user_sol_id_in", Types.VARCHAR),
                    new SqlParameter("p_sch_code_in", Types.VARCHAR),
                    new SqlParameter("p_flag_in",Types.VARCHAR),
                    new SqlParameter("p_pan_no_in",Types.VARCHAR),
                    new SqlParameter("p_bla_no_in", Types.VARCHAR),
                    new SqlParameter("p_subs_id_in",Types.VARCHAR),
                    new SqlParameter("p_from_date_in",Types.VARCHAR),
                    new SqlParameter("p_to_date_in", Types.VARCHAR),
                    new SqlParameter("p_page_no_in", Types.VARCHAR),
                    new SqlParameter("p_page_size_in", Types.VARCHAR),
                    new SqlParameter("p_search_value_in", Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter("p_total_records_out", OracleTypes.VARCHAR),
                    new SqlOutParameter("p_cur_val_out", OracleTypes.CURSOR),
                    new SqlOutParameter("p_msg_out", OracleTypes.VARCHAR),
                    new SqlOutParameter("p_msg_desc_out", OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            //rowMappers.put(, this::mapListRowToExcelData);

            rowMappers.put("p_cur_val_out", (rs, rowNum) -> {

                PreMaturityMarkingReportDTO item = new PreMaturityMarkingReportDTO();

                if (preMaturityMarkingReportDTO.getSchemeCode() == null ) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                    list.add(item);
                }
                else if (preMaturityMarkingReportDTO.getFlagIn().equals("P") && preMaturityMarkingReportDTO.getSchemeCode() != null ) {
                            item.setPanNo(rs.getString(1));
                            item.setHolderName(rs.getString(2));
                            list.add(item);
                }
                else if (preMaturityMarkingReportDTO.getFlagIn().equals("B")) {
                            item.setPanNo(rs.getString(1));
                            item.setHolderName(rs.getString(2));
                            item.setModeOfHolding(rs.getString(3));
                            item.setBlaNo(rs.getString(4));
                            list.add(item);
                }
                else if (preMaturityMarkingReportDTO.getFlagIn().equals("S")) {
                            item.setPanNo(rs.getString(1));
                            item.setHolderName(rs.getString(2));
                            item.setModeOfHolding(rs.getString(3));
                            item.setBlaNo(rs.getString(4));
                            item.setSubscriptionId(rs.getString(5));
                            list.add(item);
                }

                else if (preMaturityMarkingReportDTO.getSchemeCode() != null && preMaturityMarkingReportDTO.getFlagIn().equals("T")) {
                    item.setPanNo(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setAge(rs.getString(3));
                    item.setBlaNo(rs.getString(4));
                    item.setModeOfHolding(rs.getString(5));
                    item.setSubscriptionId(rs.getString(6));
                    item.setPreMaturityMarkedDate(rs.getString(7));
                    item.setAccOpenDate(rs.getString(8));
                    item.setMaturityDate(rs.getString(9));
                    item.setSubscriptionAmt(rs.getString(10));
                    item.setStatus(rs.getString(11));


                    list.add(item);
                }
                else if (preMaturityMarkingReportDTO.getSchemeCode() != null && preMaturityMarkingReportDTO.getFlagIn().equals("D")) {
                    item.setPanNo(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setAge(rs.getString(3));
                    item.setBlaNo(rs.getString(4));
                    item.setModeOfHolding(rs.getString(5));
                    item.setSubscriptionId(rs.getString(6));
                    item.setPreMaturityMarkedDate(rs.getString(7));
                    item.setAccOpenDate(rs.getString(8));
                    item.setMaturityDate(rs.getString(9));
                    item.setSubscriptionAmt(rs.getString(10));
                    item.setStatus(rs.getString(11));


                    list.add(item);
                }


                return item;

            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_bond_pre_Mat_Mark_Report",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            preMaturityMarkingReportDTOList = (List<PreMaturityMarkingReportDTO>) result.get("p_cur_val_out");
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            if (CollectionUtils.isEmpty(preMaturityMarkingReportDTOList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("preMaturityMarkingReportDTOList", preMaturityMarkingReportDTOList);
                responseDataDTO.setResult(finalData);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e);
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setTotal(Double.parseDouble(totalRecords));
        return responseDataDTO;

    }





    @Override
    public ResponseDataDTO getPanNo(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CER_INV_PAN_NO.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondCertificateInvtReportDTO.getSchemeCode());
                stmt.setString(index++, bondCertificateInvtReportDTO.getName());
                stmt.setString(index++, bondCertificateInvtReportDTO.getPanNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    List<HashMap<String, String>> torchOut = null;

                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            temp = new HashMap<>();

                            temp.put("panHldName", resultSet.getString(1));
                            temp.put("panNo", resultSet.getString(2));

                            torchOut.add(temp);
                        }
                        bondCertificateInvtReportDTO.setTorchValue(torchOut);
                    }

                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(bondCertificateInvtReportDTO);

        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBlaAcNo(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CER_INV_BLA_NO.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondCertificateInvtReportDTO.getSchemeCode());
                stmt.setString(index++, bondCertificateInvtReportDTO.getName());
                stmt.setString(index++, bondCertificateInvtReportDTO.getPanNo());
                stmt.setString(index++, bondCertificateInvtReportDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (bondCertificateInvtReportDTO.getSchemeCode() != null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        List<HashMap<String, String>> torchOut = null;

                        ResultSetMetaData metaData = resultSet.getMetaData();
                        int columnCount = metaData.getColumnCount();

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("name", resultSet.getString(1));
                                temp.put("panNo", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));
                                temp.put("blaNo", resultSet.getString(4));

                                for (int i = 1; i <= columnCount; i++) {
                                    String columnValue = resultSet.getString(i);
                                    String columnName = metaData.getColumnName(i);
                                    System.out.println("Index: " + i + ", Column: " + columnName + ", Value: " + columnValue);

                                    temp.put(columnName, columnValue);
                                }

                                torchOut.add(temp);
                            }
                            bondCertificateInvtReportDTO.setTorchValue(torchOut);
                        }
                    }
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(bondCertificateInvtReportDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getSubscripID(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CER_INV_SUBSCR_ID.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondCertificateInvtReportDTO.getSchemeCode());
                stmt.setString(index++, bondCertificateInvtReportDTO.getBondHolderName());
                stmt.setString(index++, bondCertificateInvtReportDTO.getPanNo());
                stmt.setString(index++, bondCertificateInvtReportDTO.getBlaNo());
                stmt.setString(index++, bondCertificateInvtReportDTO.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (bondCertificateInvtReportDTO.getSchemeCode() != null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("name", resultSet.getString(1));
                                temp.put("panNo", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));
                                temp.put("subscriptionId", resultSet.getString(4));

                                torchOut.add(temp);
                            }
                            bondCertificateInvtReportDTO.setTorchValue(torchOut);
                        }
                    }
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(bondCertificateInvtReportDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getCertificateOfHolding(BondCertificateInvtHoldReportDTO bondCertificateInvtHoldReportDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        List<BondCertificateInvtHoldReportDTO> list = new ArrayList<>();
        BondCertificateInvtHoldReportDTO item = null;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            connection = jdbcConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_REP_COH.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondCertificateInvtHoldReportDTO.getSchemeCode());
                stmt.setString(index++, bondCertificateInvtHoldReportDTO.getFlag());
                stmt.setString(index++, bondCertificateInvtHoldReportDTO.getHolderName());
                stmt.setString(index++, bondCertificateInvtHoldReportDTO.getPanNumber());
                stmt.setString(index++, bondCertificateInvtHoldReportDTO.getBlaNo());
                stmt.setString(index++, bondCertificateInvtHoldReportDTO.getBondSubscriptionId());
                stmt.setString(index++, bondCertificateInvtHoldReportDTO.getFromDate());
                stmt.setString(index++, bondCertificateInvtHoldReportDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (bondCertificateInvtHoldReportDTO.getSchemeCode().isEmpty()) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(11);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                item = new BondCertificateInvtHoldReportDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            resultMap.put("list", list);
                            responseDataDTO.setResult(resultMap);
                        }
                    } else if (bondCertificateInvtHoldReportDTO.getFlag().equals("P")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(11);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                item = new BondCertificateInvtHoldReportDTO();
                                item.setPanNumber(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                list.add(item);
                            }
                            resultMap.put("list", list);
                            responseDataDTO.setResult(resultMap);
                        }
                    } else if (bondCertificateInvtHoldReportDTO.getFlag().equals("B")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(11);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                item = new BondCertificateInvtHoldReportDTO();
                                item.setPanNumber(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));
                                item.setBlaNo(resultSet.getString(4));
                                list.add(item);
                            }
                            resultMap.put("list", list);
                            responseDataDTO.setResult(resultMap);
                        }
                    } else if (bondCertificateInvtHoldReportDTO.getFlag().equals("S")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(11);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                item = new BondCertificateInvtHoldReportDTO();
                                item.setPanNumber(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));
                                item.setBlaNo(resultSet.getString(4));
                                item.setBondSubscriptionId(resultSet.getString(5));
                                list.add(item);
                            }
                            resultMap.put("list", list);
                            responseDataDTO.setResult(resultMap);
                        }
                    } else if (bondCertificateInvtHoldReportDTO.getFlag().equals("T")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(11);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                item = new BondCertificateInvtHoldReportDTO();
                                item.setBlaNo(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setPanNumber(resultSet.getString(3));
                                item.setModeOfHolding(resultSet.getString(4));
                                list.add(item);
                            }
                            resultMap.put("list", list);
                            responseDataDTO.setResult(resultMap);
                        }
                    }
                    else if (bondCertificateInvtHoldReportDTO.getFlag().equals("D")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(11);
                        String notificationNo = stmt.getString(12);
                        String notificationDate = stmt.getString(13);
                        String intPaymentDate1 = stmt.getString(14);
                        String intPaymentDate2 = stmt.getString(15);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                item = new BondCertificateInvtHoldReportDTO();
                                item.setBlaNo(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setPanNumber(resultSet.getString(3));
                                item.setAddress(resultSet.getString(4));
                                item.setAddress1(resultSet.getString(5));
                                item.setAddress2(resultSet.getString(6));
                                item.setPinCode(resultSet.getString(7));
                                item.setCity(resultSet.getString(8));
                                item.setState(resultSet.getString(9));
                                item.setInvestDate(resultSet.getString(10));
                                item.setInvestAmount(resultSet.getString(11));
                                item.setCertificateNo(resultSet.getString(12));
                                item.setTransferDate(resultSet.getString(13));
                                item.setTransferAmount(resultSet.getString(14));
                                item.setDueDateOfRepayment(resultSet.getString(15));
                                item.setRepaymentDate(resultSet.getString(16));
                                item.setRepaymentAmount(resultSet.getString(17));
                                item.setBranchCode(resultSet.getString(18));
                                item.setBondSubscriptionId(resultSet.getString(19));
                                item.setModeOfHolding(resultSet.getString(20));
                                item.setFirstJointBlaName(resultSet.getString(21));
                                item.setSecondJointBlaName(resultSet.getString(22));
                                item.setThirdJointBlaName(resultSet.getString(23));
                                item.setNomineeID(resultSet.getString(24));
                                item.setBalance(resultSet.getString(25));
                                item.setWboOffice(resultSet.getString(26));
                                item.setGuardianName(resultSet.getString(27));
                                item.setGovtNotNo(notificationNo);
                                item.setGovtNotDate(notificationDate);
                                item.setIntPaymentDate1(intPaymentDate1);
                                item.setIntPaymentDate2(intPaymentDate2);
                                list.add(item);
                            }
                            resultMap.put("list", list);
                            responseDataDTO.setResult(resultMap);
                        }
                    }
                    else if (bondCertificateInvtHoldReportDTO.getFlag().equals("F")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(11);
                        String notificationNo = stmt.getString(12);
                        String notificationDate = stmt.getString(13);
                        String intPaymentDate1 = stmt.getString(14);
                        String intPaymentDate2 = stmt.getString(15);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                item = new BondCertificateInvtHoldReportDTO();
                                item.setBlaNo(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setPanNumber(resultSet.getString(3));
                                item.setAddress(resultSet.getString(4));
                                item.setAddress1(resultSet.getString(5));
                                item.setAddress2(resultSet.getString(6));
                                item.setPinCode(resultSet.getString(7));
                                item.setCity(resultSet.getString(8));
                                item.setState(resultSet.getString(9));
                                item.setInvestDate(resultSet.getString(10));
                                item.setInvestAmount(resultSet.getString(11));
                                item.setCertificateNo(resultSet.getString(12));
                                item.setTransferDate(resultSet.getString(13));
                                item.setTransferAmount(resultSet.getString(14));
                                item.setDueDateOfRepayment(resultSet.getString(15));
                                item.setRepaymentDate(resultSet.getString(16));
                                item.setRepaymentAmount(resultSet.getString(17));
                                item.setBranchCode(resultSet.getString(18));
                                item.setBondSubscriptionId(resultSet.getString(19));
                                item.setModeOfHolding(resultSet.getString(20));
                                item.setFirstJointBlaName(resultSet.getString(21));
                                item.setSecondJointBlaName(resultSet.getString(22));
                                item.setThirdJointBlaName(resultSet.getString(23));
                                item.setNomineeID(resultSet.getString(24));
                                item.setBalance(resultSet.getString(25));
                                item.setWboOffice(resultSet.getString(26));
                                item.setGuardianName(resultSet.getString(27));
                                item.setGovtNotNo(notificationNo);
                                item.setGovtNotDate(notificationDate);
                                item.setIntPaymentDate1(intPaymentDate1);
                                item.setIntPaymentDate2(intPaymentDate2);
                                list.add(item);
                            }
                            resultMap.put("list", list);
                            responseDataDTO.setResult(resultMap);
                        }
                    }

                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            jdbcConnectionFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getTranSameBankReport(BondTransSameBankReportDTO bondTransSameBankReportDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        double totalInterestAmt = 0;
        double totalTdsAmt = 0;
        double totalInterPaidAmt = 0;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TRAN_SAME_BANK.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTransSameBankReportDTO.getSchemeCode());
                stmt.setString(index++, bondTransSameBankReportDTO.getAccTransBranchId());
                stmt.setString(index++, bondTransSameBankReportDTO.getBlaNo());
                stmt.setString(index++, bondTransSameBankReportDTO.getBondSubscriptionId());
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (bondTransSameBankReportDTO.getAccTransBranchId() == null
                            && bondTransSameBankReportDTO.getBlaNo() == null
                            && bondTransSameBankReportDTO.getBondSubscriptionId() == null) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(14);
                        if (resultSet != null) {
                            List<BondTransSameBankReportDTO> list = new ArrayList<>();
                            BondTransSameBankReportDTO item = null;
                            while (resultSet.next()) {
                                item = new BondTransSameBankReportDTO();

                                item.setBranchId(resultSet.getString(1));
                                item.setBranchName(resultSet.getString(2));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else if (bondTransSameBankReportDTO.getAccTransBranchId() != null
                            && bondTransSameBankReportDTO.getBlaNo() == null
                            && bondTransSameBankReportDTO.getBondSubscriptionId() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(14);
                        if (resultSet != null) {
                            List<BondTransSameBankReportDTO> list = new ArrayList<>();
                            BondTransSameBankReportDTO item = null;
                            while (resultSet.next()) {
                                item = new BondTransSameBankReportDTO();

                                item.setBlaNo(resultSet.getString(1));
                                item.setBondHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondTransSameBankReportDTO.getAccTransBranchId() != null
                            && bondTransSameBankReportDTO.getBlaNo() != null
                            && bondTransSameBankReportDTO.getBondSubscriptionId() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(14);
                        if (resultSet != null) {
                            List<BondTransSameBankReportDTO> list = new ArrayList<>();
                            BondTransSameBankReportDTO item = null;
                            while (resultSet.next()) {
                                item = new BondTransSameBankReportDTO();

                                item.setBondSubscriptionId(resultSet.getString(1));
                                item.setSubscriptionAmt(resultSet.getString(2));
                                item.setDateOfTran(resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondTransSameBankReportDTO.getAccTransBranchId() != null
                            && bondTransSameBankReportDTO.getBlaNo() != null
                            && bondTransSameBankReportDTO.getBondSubscriptionId() != null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(14);
                        if (resultSet != null) {
                            List<BondTransSameBankReportDTO> list = new ArrayList<>();
                            BondTransSameBankReportDTO item = null;
                            while (resultSet.next()) {
                                item = new BondTransSameBankReportDTO();

                                item.setBlaNo(resultSet.getString(1));
                                item.setBondHolderName(resultSet.getString(2));
                                item.setBondSubscriptionId(resultSet.getString(3));
                                item.setSubscriptionAmt(resultSet.getString(4));
                                item.setDateOfTran(resultSet.getString(5));
                                item.setTransToName(resultSet.getString(6));
                                item.setTransToId(resultSet.getString(7));
                                item.setBondType(resultSet.getString(8));
                                item.setMaturityDate(resultSet.getString(9));
                                item.setOperativeAccNo(resultSet.getString(10));
                                item.setInterPaidUp(resultSet.getString(11));
                                item.setAccOpenDate(resultSet.getString(12));
                                list.add(item);
                            }
                            bondTransSameBankReportDTO.setTorchOne(list);
                        }

                        ResultSet resultSet1 = (ResultSet) stmt.getObject(15);
                        if (resultSet1 != null) {
                            List<BondTransSameBankReportDTO> list = new ArrayList<>();
                            BondTransSameBankReportDTO item = null;
                            while (resultSet1.next()) {
                                item = new BondTransSameBankReportDTO();
                                item.setInterestFromPeriod(resultSet1.getString(1));
                                item.setInterestToPeriod(resultSet1.getString(2));
                                item.setInterestAmt(resultSet1.getString(3));
                                item.setTdsAmt(resultSet1.getString(4));
                                item.setInterestPaidAmt(resultSet1.getString(5));
                                item.setInterestPaidDate(resultSet1.getString(6));
                                item.setRateOfInterest(resultSet1.getString(7));
                                item.setPaidByBranch(resultSet1.getString(8));
                                totalInterestAmt = totalInterestAmt + Double.parseDouble(resultSet1.getString(3));
                                // totalTdsAmt = totalTdsAmt + Double.parseDouble(resultSet1.getString(4));
                                totalInterPaidAmt = totalInterPaidAmt + Double.parseDouble((resultSet1.getString(5)));

                                list.add(item);
                            }
                            bondTransSameBankReportDTO.setTorchTwo(list);
                            bondTransSameBankReportDTO.setCreatedBy(stmt.getString(7));
                            bondTransSameBankReportDTO.setCreatedDate(stmt.getString(8));
                            bondTransSameBankReportDTO.setModifiedBy(stmt.getString(9));
                            bondTransSameBankReportDTO.setModifiedDate(stmt.getString(10));
                            bondTransSameBankReportDTO.setVerifiedBy(stmt.getString(11));
                            bondTransSameBankReportDTO.setVerifiedDate(stmt.getString(12));
                            bondTransSameBankReportDTO.setVerifiedStatus(stmt.getString(13));
                            bondTransSameBankReportDTO.setTotalInterestAmt(totalInterestAmt);
                            bondTransSameBankReportDTO.setTotalTdsAmt(totalTdsAmt);
                            bondTransSameBankReportDTO.setTotalInterPaidAmt(totalInterPaidAmt);

                        }
                        responseDataDTO.setResult(bondTransSameBankReportDTO);

                    }

                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getUnverifiedReportRbi(UnverifiedReportDto unverifiedReportDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        UnverifiedReportDto unverifiedReportDto1 = null;
        List<UnverifiedReportDto> outputList = new ArrayList<>();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_REP_UNVER_REC.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, unverifiedReportDto.getBranchId());
                stmt.setString(index++, unverifiedReportDto.getCreatedOrModifiedBy());
                stmt.setString(index++, unverifiedReportDto.getOperationFlag());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                // ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (unverifiedReportDto.getBranchId() == null
                            && unverifiedReportDto.getCreatedOrModifiedBy() == null
                            && org.apache.commons.lang3.StringUtils
                            .equalsIgnoreCase(unverifiedReportDto.getOperationFlag(), "B")) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    unverifiedReportDto1 = new UnverifiedReportDto();
                                    unverifiedReportDto1.setBranchId(resultSet.getString(1));
                                    unverifiedReportDto1.setBranchDescription(resultSet.getString(2));
                                    outputList.add(unverifiedReportDto1);
                                }
                            }
                        }

                    } else if (unverifiedReportDto.getBranchId() != null
                            && unverifiedReportDto.getCreatedOrModifiedBy() == null
                            && org.apache.commons.lang3.StringUtils
                            .equalsIgnoreCase(unverifiedReportDto.getOperationFlag(), "C")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    unverifiedReportDto1 = new UnverifiedReportDto();
                                    unverifiedReportDto1.setCreatedBy(resultSet.getString(1));
                                    unverifiedReportDto1.setModifiedBy(resultSet.getString(2));
                                    outputList.add(unverifiedReportDto1);
                                }
                            }
                        }
                    } else if (unverifiedReportDto.getCreatedOrModifiedBy() == null
                            && org.apache.commons.lang3.StringUtils
                            .equalsIgnoreCase(unverifiedReportDto.getOperationFlag(), "C")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    unverifiedReportDto1 = new UnverifiedReportDto();
                                    unverifiedReportDto1.setCreatedBy(resultSet.getString(1));
                                    unverifiedReportDto1.setModifiedBy(resultSet.getString(2));
                                    outputList.add(unverifiedReportDto1);
                                }
                            }
                        }

                    } else if (unverifiedReportDto.getBranchId() != null
                            && unverifiedReportDto.getCreatedOrModifiedBy() != null
                            && org.apache.commons.lang3.StringUtils
                            .equalsIgnoreCase(unverifiedReportDto.getOperationFlag(), "R")) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    unverifiedReportDto1 = new UnverifiedReportDto();
                                    unverifiedReportDto1.setBlaNo(resultSet.getString(1));
                                    unverifiedReportDto1.setSubsId(resultSet.getString("Subscription_ID"));
                                    unverifiedReportDto1.setHolderName(resultSet.getString("name"));
                                    unverifiedReportDto1.setScreenName(resultSet.getString("screen_name"));
                                    unverifiedReportDto1.setDateOfCreation(resultSet.getString("created_date"));
                                    unverifiedReportDto1.setCreatedOrModifiedBy(resultSet.getString("created_by"));
                                    unverifiedReportDto1.setBranchId(resultSet.getString("branch_id"));
                                    outputList.add(unverifiedReportDto1);
                                }
                            }
                        }

                    } else if (unverifiedReportDto.getBranchId() == null
                            && unverifiedReportDto.getCreatedOrModifiedBy() != null
                            && org.apache.commons.lang3.StringUtils
                            .equalsIgnoreCase(unverifiedReportDto.getOperationFlag(), "R")) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                unverifiedReportDto1 = new UnverifiedReportDto();
                                unverifiedReportDto1.setBlaNo(resultSet.getString(1));
                                unverifiedReportDto1.setSubsId(resultSet.getString(2));
                                unverifiedReportDto1.setHolderName(resultSet.getString(3));
                                unverifiedReportDto1.setScreenName(resultSet.getString(4));
                                unverifiedReportDto1.setDateOfCreation(resultSet.getString(5));
                                unverifiedReportDto1.setCreatedOrModifiedBy(resultSet.getString(6));
                                unverifiedReportDto1.setBranchId(resultSet.getString(7));
                                outputList.add(unverifiedReportDto1);
                            }
                        }
                    } else if (unverifiedReportDto.getBranchId() != null
                            && unverifiedReportDto.getCreatedOrModifiedBy() == null
                            && org.apache.commons.lang3.StringUtils
                            .equalsIgnoreCase(unverifiedReportDto.getOperationFlag(), "R")) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    unverifiedReportDto1 = new UnverifiedReportDto();
                                    unverifiedReportDto1.setBlaNo(resultSet.getString(1));
                                    unverifiedReportDto1.setSubsId(resultSet.getString(2));
                                    unverifiedReportDto1.setHolderName(resultSet.getString(3));
                                    unverifiedReportDto1.setScreenName(resultSet.getString(4));
                                    unverifiedReportDto1.setDateOfCreation(resultSet.getString(5));
                                    unverifiedReportDto1.setCreatedBy(resultSet.getString(6));
                                    unverifiedReportDto1.setBranchId(resultSet.getString(7));
                                    outputList.add(unverifiedReportDto1);

                                }
                            }
                        }

                    } else if (unverifiedReportDto.getCreatedOrModifiedBy() == null
                            && org.apache.commons.lang3.StringUtils
                            .equalsIgnoreCase(unverifiedReportDto.getOperationFlag(), "R")) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                if (resultSet.getString(1) != null) {
                                    unverifiedReportDto1 = new UnverifiedReportDto();
                                    unverifiedReportDto1.setBlaNo(resultSet.getString(1));
                                    unverifiedReportDto1.setSubsId(resultSet.getString(2));
                                    unverifiedReportDto1.setHolderName(resultSet.getString(3));
                                    unverifiedReportDto1.setScreenName(resultSet.getString(4));
                                    unverifiedReportDto1.setDateOfCreation(resultSet.getString(5));
                                    unverifiedReportDto1.setCreatedBy(resultSet.getString(6));
                                    unverifiedReportDto1.setBranchId(resultSet.getString(7));
                                    outputList.add(unverifiedReportDto1);

                                }
                            }
                        }

                    }

                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(outputList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getListOfBondReport(ListOfBondInvestmentsDTO listOfBondInvestmentsDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, Object> finalData = new HashMap<>();
        List<ListOfBondInvestmentsDTO> listOfBondInvestmentsDTOList = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords=null;

        try {
            // Input parameters
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(p_page_no_in, listOfBondInvestmentsDTO.getPageNo());
            inParams.put(p_page_size_in, listOfBondInvestmentsDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=listOfBondInvestmentsDTO.getSearchValue()&&!listOfBondInvestmentsDTO.getSearchValue().trim().isEmpty())?listOfBondInvestmentsDTO.getSearchValue():null);

            // Define SQL parameters
            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_cursor_val_out, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(p_cursor_val_out, this::mapListRowToExcelData);
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_CAS_PEN_REC",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            listOfBondInvestmentsDTOList = (List<ListOfBondInvestmentsDTO>) result.get(p_cursor_val_out);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(listOfBondInvestmentsDTOList)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("listOfBondInvestmentsDTOList", listOfBondInvestmentsDTOList);
                responseDataDTO.setResult(finalData);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private ListOfBondInvestmentsDTO mapListRowToExcelData(ResultSet resultSet, int rowNum) throws SQLException {
        ListOfBondInvestmentsDTO dto = new ListOfBondInvestmentsDTO();
        dto.setBlaNumber(resultSet.getString("bla_no"));
        dto.setSchemeName(resultSet.getString("schm_name"));
        dto.setSubsDate(resultSet.getString("subs_date"));
        dto.setSubsAmount(resultSet.getString("subs_amt"));
        return dto;
    }

    @Override
    public ResponseDataDTO getBlaStatementReport(BlaStatementForBondHoldersDTO blaStatementForBondHoldersDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> resultMap = new HashMap<>();
        List<BlaStatementForBondHoldersDTO> blaStatementForBondHoldersDTOList = Collections.emptyList();
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, blaStatementForBondHoldersDTO.getSchemeCode());
            inParams.put(flag, blaStatementForBondHoldersDTO.getFlag());
            inParams.put(fromDate, blaStatementForBondHoldersDTO.getFromDate());
            inParams.put(toDate, blaStatementForBondHoldersDTO.getToDate());
            inParams.put(name, blaStatementForBondHoldersDTO.getBondholdername());
            inParams.put(panNumber, blaStatementForBondHoldersDTO.getPanNo());
            inParams.put(blaNumber, blaStatementForBondHoldersDTO.getBlaNumber());
            inParams.put(p_page_no_in, blaStatementForBondHoldersDTO.getPageNo());
            inParams.put(p_page_size_in, blaStatementForBondHoldersDTO.getPageSize());
            inParams.put(p_search_value_in, blaStatementForBondHoldersDTO.getSearchValue());


            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),

            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();

            rowMappers.put(curValOut, (rs, rowNum) -> {
                BlaStatementForBondHoldersDTO item = new BlaStatementForBondHoldersDTO();
                if (blaStatementForBondHoldersDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                    return item;
                } else if (blaStatementForBondHoldersDTO.getFlag().equalsIgnoreCase("P")) {
                    item.setBondholdername(rs.getString(1));
                    item.setPanNo(rs.getString(2));
                    item.setBlaNumber(rs.getString(3));
                    return item;
                } else if (blaStatementForBondHoldersDTO.getFlag().equalsIgnoreCase("C")) {
                    item.setBlaNumber(rs.getString(1));
                    item.setBondholdername(rs.getString(2));
                    item.setModeofholding(rs.getString(3));
                    item.setPanNo(rs.getString(4));
                    return item;
                } else if (blaStatementForBondHoldersDTO.getFlag().equalsIgnoreCase("T")) {
                    item.setPanNo(rs.getString(1));
                    item.setBlaNumber(rs.getString(2));
                    item.setBondholdername(rs.getString(3));
                    item.setAddress(rs.getString(4));
                    item.setDateOfSubscription(rs.getString(5));
                    item.setSubcriptionId(rs.getString(6));
                    item.setBondtype(rs.getString(7));
                    item.setMaturitydate(rs.getString(8));
                    item.setSubcriptionamount(rs.getString(9));
                    item.setSubsStatus(rs.getString(10));
                    return item;
                }
                throw new IllegalArgumentException("Invalid flag: " + blaStatementForBondHoldersDTO.getFlag());
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_bla_statement_Bondholders",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);
            blaStatementForBondHoldersDTOList = (List<BlaStatementForBondHoldersDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            if (CollectionUtils.isEmpty(blaStatementForBondHoldersDTOList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", blaStatementForBondHoldersDTOList);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO bondSubscriptionwithoutnomination(bondSubscriptionwithoutnominationDTO bondSubscriptionwithoutnominationDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_gbm_rbi_bond_sta_bond_hold.getProcName());

                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondSubscriptionwithoutnominationDTO.getSchemeCode());
                stmt.setString(index++, bondSubscriptionwithoutnominationDTO.getName());
                stmt.setString(index++, bondSubscriptionwithoutnominationDTO.getPanNo());
                stmt.setString(index++, bondSubscriptionwithoutnominationDTO.getBlaNumber());
                stmt.setString(index++, bondSubscriptionwithoutnominationDTO.getSubcriptionId());
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));

                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(13);
                    if (resultSet != null) {
                        List<bondSubscriptionwithoutnominationDTO> list = new ArrayList<>();
                        bondSubscriptionwithoutnominationDTO item = null;
                        while (resultSet.next()) {
                            item = new bondSubscriptionwithoutnominationDTO();

                            item.setBlaNumber(resultSet.getString("BLA_NO"));
                            item.setBondholdername(resultSet.getString("bond_holder_name"));
                            item.setModeofholding(resultSet.getString("mode_of_holding"));
                            item.setHolderType(resultSet.getString("holder_type"));
                            item.setSubcriptionId(resultSet.getString("Subcription_Id"));
                            item.setDateofsubscription(resultSet.getString("Date_of_subscription"));
                            item.setSubcriptionamount(resultSet.getString("Subcription_amount"));
                            item.setSubcriptionstatus(resultSet.getString("Subcription_status"));
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
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getTdsDeductReport(BondTdsDeductionReportDTO bondTdsDeductionReportDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondTdsDeductionReportDTO output = new BondTdsDeductionReportDTO();
        if (bondTdsDeductionReportDTO.getBranchId() == null && bondTdsDeductionReportDTO.getBlaNo() == null
                && bondTdsDeductionReportDTO.getTdsDeductFrom() == null
                && bondTdsDeductionReportDTO.getTdsDeductTo() == null) {
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TDS_DECT_DATA.getProcName());
                    stmt.setString(index++, "SUPERADMIN1");
                    stmt.setString(index++, "101");
                    stmt.setString(index++, bondTdsDeductionReportDTO.getSchemeCode());
                    stmt.setString(index++, bondTdsDeductionReportDTO.getBranchId());
                    stmt.setString(index++, bondTdsDeductionReportDTO.getBlaNo());
                    stmt.setString(index++, bondTdsDeductionReportDTO.getTdsDeductFrom());
                    stmt.setString(index++, bondTdsDeductionReportDTO.getTdsDeductTo());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;
                    if (stmt.getString(index - 1) != null
                            && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                        {
                            List<HashMap<String, String>> torchOut = null;
                            resultSet = (ResultSet) stmt.getObject(8);
                            if (resultSet != null) {
                                HashMap<String, String> temp = null;
                                torchOut = new ArrayList<>();

                                while (resultSet.next()) {
                                    temp = new HashMap<>();

                                    temp.put("branchId", resultSet.getString(1));
                                    temp.put("branchDescription", resultSet.getString(2));
                                    torchOut.add(temp);
                                }
                            }
                            output.setBranchIdTorchOut(torchOut);
                            responseDataDTO.setResult(output);
                        }

                    }

                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }
            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }
        } else if (bondTdsDeductionReportDTO.getBranchId() != null && bondTdsDeductionReportDTO.getBlaNo() == null
                && bondTdsDeductionReportDTO.getTdsDeductFrom() == null
                && bondTdsDeductionReportDTO.getTdsDeductTo() == null) {
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TDS_DECT_DATA.getProcName());
                    stmt.setString(index++, "SUPERADMIN1");
                    stmt.setString(index++, "101");
                    stmt.setString(index++, bondTdsDeductionReportDTO.getSchemeCode());
                    stmt.setString(index++, bondTdsDeductionReportDTO.getBranchId());
                    stmt.setString(index++, bondTdsDeductionReportDTO.getBlaNo());
                    stmt.setString(index++, bondTdsDeductionReportDTO.getTdsDeductFrom());
                    stmt.setString(index++, bondTdsDeductionReportDTO.getTdsDeductTo());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;
                    if (stmt.getString(index - 1) != null
                            && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                        {
                            List<HashMap<String, String>> torchOut = null;
                            resultSet = (ResultSet) stmt.getObject(8);
                            if (resultSet != null) {
                                HashMap<String, String> temp = null;
                                torchOut = new ArrayList<>();
                                while (resultSet.next()) {
                                    temp = new HashMap<>();
                                    temp.put("blaNo", resultSet.getString(1));
                                    temp.put("modeOfHolding", resultSet.getString(2));
                                    temp.put("bondHolderName", resultSet.getString(3));
                                    torchOut.add(temp);
                                }
                            }
                            output.setBlaNumberTorchOut(torchOut);
                            responseDataDTO.setResult(output);
                        }

                    }

                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }
            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getTdsDeductDetails(BondTdsDeductionReportDTO bondTdsDeductionReportDTO)
            throws IOException {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, Object> tdsDeductionList = new HashMap<>();
        final Session session = getSession();
        Transaction transaction = null;
        boolean isTransactionActive = session.getTransaction().isActive();
        if (!isTransactionActive) {
            transaction = session.beginTransaction();
        }

        try {

            String hqlQuery = "select DISTINCT \n" +
                    "            T1.INTERESTAMOUNT as INTEREST_AMT,\n" +
                    "           NVL(T1.CURRENTTDSAMOUNT,0)+NVL(T1.ARREARTDSAMOUNT,0)  as CURRENT_TDS_AMOUNT,\n" +
                    "            T1.SUBSCRIPTIONAMOUNT as SUBSCRIPTION_AMOUNT,\n" +
                    "            T1.NETINTERESTAMOUNT as NET_INTEREST_AMOUNT,\n" +
                    "            T2.BONDLEDGERACCOUNTNUMBER as BOND_LEDGER_ACCOUNT_NUMBER,\n" +
                    "            T1.BRANCHID as BRANCH_ID,\n" +
                    "            T2.PANNO as PAN_NO,\n" +
                    "            T1.BONDSUBSCRIPTIONID as BOND_SUBSCRIPTION_ID,\n" +
                    "            T2.BONDHLDFIRSTNAME|| ' ' ||T2.BONDHLDMIDNAME|| ' ' ||T2.BONDHLDLASTNAME as Holder_Name,\n"
                    +
                    "            TO_CHAR(T1.INTERESTPAIDDATE,'DD/MM/YYYY') AS Date_of_Deduction_of_TDS\n" +
                    "            from\n" +
                    "            InterestPaymentDetails T1\n" +
                    "            inner join \n" +
                    "            BondLedgerAccNo T2\n" +
                    "            on T2.BONDLEDGERACCOUNTNUMBER = T1.BONDLEDGERACCOUNTNUMBER\n" +
                    "            WHERE T1.SCHCODE = :pSchemeCode AND T1.ISTDSCALCULATED = 'Y' AND  T1.BONDLEDGERACCOUNTNUMBER = NVL(:pBlaNo,T1.BONDLEDGERACCOUNTNUMBER)AND\n"
                    +
                    "             T1.BRANCHID = NVL(:pBranchId,T1.BRANCHID) AND\n" +
                    "             T1.ISPAYMENTDONE = 'Y' AND\n" +
                    "              T1.INTERESTPAIDDATE BETWEEN TO_DATE(:pTdsDeductionFrom,'DD/MM/YYYY') AND TO_DATE(:pTdsDeductionTo,'DD/MM/YYYY') AND \n"
                    +
                    "            T2.SCHCODE = :pSchemeCode AND T2.verifiedstatus = 'Y' AND T2.isdeleted = 'N' and T2.ISACTIVE = 'Y'\n"
                    +
                    "            AND T1.BRANCHID LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n" +
                    "            OR T2.BONDLEDGERACCOUNTNUMBER LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T2.BONDHLDFIRSTNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T2.BONDHLDMIDNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T2.BONDHLDLASTNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T2.PANNO LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.BONDSUBSCRIPTIONID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.SUBSCRIPTIONAMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR TO_CHAR(T1.INTERESTPAIDDATE, 'DD/MM/YYYY')  LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n" +
                    "            OR T1.INTERESTAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.CURRENTTDSAMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.NETINTERESTAMOUNT LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'";

            if (bondTdsDeductionReportDTO.getSortField() != null && !bondTdsDeductionReportDTO.getSortField().isEmpty()
                    && bondTdsDeductionReportDTO.getSortOrder() != null
                    && !bondTdsDeductionReportDTO.getSortOrder().isEmpty()) {
                hqlQuery += " ORDER BY " + bondTdsDeductionReportDTO.getSortField() + " "
                        + bondTdsDeductionReportDTO.getSortOrder();
            }

            Query<Map<String, Object>> query = session.createQuery(hqlQuery);

            query.setParameter("pBlaNo", bondTdsDeductionReportDTO.getBlaNo());
            query.setParameter("pBranchId", bondTdsDeductionReportDTO.getBranchId());
            query.setParameter("pTdsDeductionFrom", bondTdsDeductionReportDTO.getTdsDeductFrom());
            query.setParameter("pTdsDeductionTo", bondTdsDeductionReportDTO.getTdsDeductTo());
            query.setParameter("pSchemeCode", bondTdsDeductionReportDTO.getSchemeCode());
            query.setParameter("P_SEARCH_VALUE_IN", bondTdsDeductionReportDTO.getSearchValue());
            query.setFirstResult((bondTdsDeductionReportDTO.getPageNo() - 1) * bondTdsDeductionReportDTO.getPageSize());
            query.setMaxResults(bondTdsDeductionReportDTO.getPageSize());

            query.setResultTransformer(new TrailRunDetailsResultTransformer());
            List<Map<String, Object>> results = query.getResultList();
            bondTdsDeductionReportDTO.setData(results);
            tdsDeductionList.put("tdsDeductionList", bondTdsDeductionReportDTO.getData());

            Query<Long> countQuery = session.createQuery("select count(1)\n" +
                    "            from\n" +
                    "            InterestPaymentDetails T1\n" +
                    "            inner join \n" +
                    "            BondLedgerAccNo T2\n" +
                    "            on T2.BONDLEDGERACCOUNTNUMBER = T1.BONDLEDGERACCOUNTNUMBER\n" +
                    "            WHERE T1.SCHCODE = :pSchemeCode AND T1.ISTDSCALCULATED = 'Y' AND  T1.BONDLEDGERACCOUNTNUMBER = NVL(:pBlaNo,T1.BONDLEDGERACCOUNTNUMBER)AND\n"
                    +
                    "             T1.BRANCHID = NVL(:pBranchId,T1.BRANCHID) AND\n" +
                    "             T1.ISPAYMENTDONE = 'Y' AND\n" +
                    "              T1.INTERESTPAIDDATE BETWEEN TO_DATE(:pTdsDeductionFrom,'DD/MM/YYYY') AND TO_DATE(:pTdsDeductionTo,'DD/MM/YYYY') AND \n"
                    +
                    "            T2.SCHCODE = :pSchemeCode AND T2.verifiedstatus = 'Y' AND T2.isdeleted = 'N' and T2.ISACTIVE = 'Y'\n"
                    +
                    "            AND T1.BRANCHID LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n" +
                    "            OR T2.BONDLEDGERACCOUNTNUMBER LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T2.BONDHLDFIRSTNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T2.BONDHLDMIDNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T2.BONDHLDLASTNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T2.PANNO LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.BONDSUBSCRIPTIONID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.SUBSCRIPTIONAMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR TO_CHAR(T1.INTERESTPAIDDATE, 'DD/MM/YYYY')  LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n" +
                    "            OR T1.INTERESTAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.CURRENTTDSAMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.NETINTERESTAMOUNT LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'");

            countQuery.setParameter("pBlaNo", bondTdsDeductionReportDTO.getBlaNo());
            countQuery.setParameter("pBranchId", bondTdsDeductionReportDTO.getBranchId());
            countQuery.setParameter("pTdsDeductionFrom", bondTdsDeductionReportDTO.getTdsDeductFrom());
            countQuery.setParameter("pTdsDeductionTo", bondTdsDeductionReportDTO.getTdsDeductTo());
            countQuery.setParameter("pSchemeCode", bondTdsDeductionReportDTO.getSchemeCode());
            countQuery.setParameter("P_SEARCH_VALUE_IN", bondTdsDeductionReportDTO.getSearchValue());

            Long totalCount = countQuery.getSingleResult();

            int maxPages = (int) Math.ceil((double) totalCount / bondTdsDeductionReportDTO.getPageSize());
            bondTdsDeductionReportDTO.setMaxPages(maxPages);

            tdsDeductionList.put("PageDetails", bondTdsDeductionReportDTO);

            if (bondTdsDeductionReportDTO.getIsDownload() != null
                    && bondTdsDeductionReportDTO.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS)
                    && bondTdsDeductionReportDTO.getDownloadType() != null) {

                if (bondTdsDeductionReportDTO.getDownloadType().equalsIgnoreCase("excel")) {

                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(
                            rbiBondReportsUtil.excelFileGeneratorForTDSReport(results, bondTdsDeductionReportDTO)));
                    fileResponseDto.setFileName("TDS Deduction – Report.xlsx");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                    return responseDataDTO;
                }

                if (bondTdsDeductionReportDTO.getDownloadType().equalsIgnoreCase("pdf")) {
                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(
                            rbiBondReportsUtil.pdfFileGeneratorForTDSReport(results, bondTdsDeductionReportDTO)));
                    fileResponseDto.setFileName("TDS Deduction – Report.pdf");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    return responseDataDTO;
                } else {
                    responseDataDTO.setMessage("Invalid File Type");
                    return responseDataDTO;
                }

            }

        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        responseDataDTO.setResult(tdsDeductionList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getTdsDeductionReportCb(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, Object> tdsDeductionCbList = new HashMap<>();
        final Session session = getSession();
        Transaction transaction = null;
        boolean isTransactionActive = session.getTransaction().isActive();
        if (!isTransactionActive) {
            transaction = session.beginTransaction();
        }

        try {

            String hqlQuery = "select DISTINCT\n" +
                    "                               T1.INTEREST_AMT as INTEREST_AMT,\n" +
                    "                               NVL(T1.CURRENT_TDS_AMOUNT,0)+NVL(T1.ARREAR_TDS_AMOUNT,0)  as CURRENT_TDS_AMOUNT,\n"
                    +
                    "                               T1.SUBSCRIPTION_AMOUNT as SUBSCRIPTION_AMOUNT,\n" +
                    "                               T1.NET_INTEREST_AMOUNT as NET_INTEREST_AMOUNT,\n" +
                    "                               T2.BOND_LEDGER_ACCOUNT_NUMBER as BOND_LEDGER_ACCOUNT_NUMBER,\n" +
                    "                               T2.BRANCH_ID as BRANCH_ID,\n" +
                    "                               T2.PAN_NO as PAN_NO,\n" +
                    "                               T1.BOND_SUBSCRIPTION_ID as BOND_SUBSCRIPTION_ID,\n" +
                    "                               T2.BOND_HLD_FIRST_NAME|| ' ' ||T2.BOND_HLD_MID_NAME|| ' ' ||T2.BOND_HLD_LAST_NAME as Holder_Name,\n"
                    +
                    "                               TO_CHAR(T1.INTEREST_PAID_DATE,'DD/MM/YYYY') AS Date_of_Deduction_of_TDS\n"
                    +
                    "                   \n" +
                    "                               from\n" +
                    "                               gbm_rbi_bond_int_pay_dtls T1\n" +
                    "                               inner join \n" +
                    "                               GBM_RBI_BOND_LEDGER_ACC_MASTER T2\n" +
                    "                               on T2.BOND_LEDGER_ACCOUNT_NUMBER = T1.BLA_NO\n" +
                    "                               WHERE T1.SCH_CODE = :pSchemeCode AND T1.IS_TDS_CALC = 'Y' AND  T1.BLA_NO = NVL(:pBlaNo,T1.BLA_NO)AND\n"
                    +
                    "                                T1.BRANCH_ID = :pBranchId AND\n" +
                    "                                T1.IS_PAYMENT_DONE = 'Y' AND\n" +
                    "                   T1.INTEREST_PAID_DATE BETWEEN TO_DATE(:pTdsDeductionFrom,'DD/MM/YYYY') AND TO_DATE(:pTdsDeductionTo,'DD/MM/YYYY') AND\n"
                    +
                    "                               T2.SCH_CODE = :pSchemeCode AND T2.verified_status = 'Y' AND T2.is_deleted = 'N' and T2.IS_ACTIVE = 'Y'\n"
                    +
                    "                               AND (T1.BRANCH_ID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                  OR T2.BOND_LEDGER_ACCOUNT_NUMBER LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T2.BOND_HLD_FIRST_NAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                  OR T2.BOND_HLD_MID_NAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T2.BOND_HLD_LAST_NAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T2.PAN_NO LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T1.BOND_SUBSCRIPTION_ID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                  OR T1.SUBSCRIPTION_AMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR TO_CHAR(T1.INTEREST_PAID_DATE, 'DD/MM/YYYY')  LIKE '%'||:P_SEARCH_VALUE_IN ||'%'\n"
                    +
                    "                                                   OR T1.INTEREST_AMT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T1.CURRENT_TDS_AMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                  OR T1.NET_INTEREST_AMOUNT LIKE '%'|| :P_SEARCH_VALUE_IN||'%')";

            if (bondTdsCollDeductionReportDTO.getSortField() != null
                    && !bondTdsCollDeductionReportDTO.getSortField().isEmpty()
                    && bondTdsCollDeductionReportDTO.getSortOrder() != null
                    && !bondTdsCollDeductionReportDTO.getSortOrder().isEmpty()) {
                hqlQuery += " ORDER BY " + bondTdsCollDeductionReportDTO.getSortField() + " "
                        + bondTdsCollDeductionReportDTO.getSortOrder();
            }

            Query<Map<String, Object>> query = session.createNativeQuery(hqlQuery);

            query.setParameter("pBlaNo", bondTdsCollDeductionReportDTO.getBlaNo());
            query.setParameter("pBranchId", UserAccountUtils.getCurrentSolId());
            query.setParameter("pTdsDeductionFrom", bondTdsCollDeductionReportDTO.getTdsDeductFrom());
            query.setParameter("pTdsDeductionTo", bondTdsCollDeductionReportDTO.getTdsDeductTo());
            query.setParameter("pSchemeCode", bondTdsCollDeductionReportDTO.getSchemeCode());
            query.setParameter("P_SEARCH_VALUE_IN", bondTdsCollDeductionReportDTO.getSearchValue());
            query.setFirstResult(
                    (bondTdsCollDeductionReportDTO.getPageNo() - 1) * bondTdsCollDeductionReportDTO.getPageSize());
            query.setMaxResults(bondTdsCollDeductionReportDTO.getPageSize());

            query.setResultTransformer(new TrailRunDetailsResultTransformer());
            List<Map<String, Object>> results = query.getResultList();
            bondTdsCollDeductionReportDTO.setData(results);
            tdsDeductionCbList.put("tdsDeductionList", bondTdsCollDeductionReportDTO.getData());

            Query<BigDecimal> countQuery = session.createNativeQuery("select count(1)\n" +
                    "                               from\n" +
                    "                               gbm_rbi_bond_int_pay_dtls T1\n" +
                    "                               inner join \n" +
                    "                               GBM_RBI_BOND_LEDGER_ACC_MASTER T2\n" +
                    "                               on T2.BOND_LEDGER_ACCOUNT_NUMBER = T1.BLA_NO\n" +
                    "                               WHERE T1.SCH_CODE = :pSchemeCode AND T1.IS_TDS_CALC = 'Y' AND  T1.BLA_NO = NVL(:pBlaNo,T1.BLA_NO)AND\n"
                    +
                    "                                T1.BRANCH_ID = :pBranchId AND\n" +
                    "                                T1.IS_PAYMENT_DONE = 'Y' AND\n" +
                    "                   T1.INTEREST_PAID_DATE BETWEEN TO_DATE(:pTdsDeductionFrom,'DD/MM/YYYY') AND TO_DATE(:pTdsDeductionTo,'DD/MM/YYYY') AND\n"
                    +
                    "                               T2.SCH_CODE = :pSchemeCode AND T2.verified_status = 'Y' AND T2.is_deleted = 'N' and T2.IS_ACTIVE = 'Y'\n"
                    +
                    "                               AND (T1.BRANCH_ID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                                                  OR T2.BOND_LEDGER_ACCOUNT_NUMBER LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T2.BOND_HLD_FIRST_NAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                  OR T2.BOND_HLD_MID_NAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T2.BOND_HLD_LAST_NAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T2.PAN_NO LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T1.BOND_SUBSCRIPTION_ID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                  OR T1.SUBSCRIPTION_AMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR TO_CHAR(T1.INTEREST_PAID_DATE, 'DD/MM/YYYY')  LIKE '%'||:P_SEARCH_VALUE_IN ||'%'\n"
                    +
                    "                                                   OR T1.INTEREST_AMT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T1.CURRENT_TDS_AMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                  OR T1.NET_INTEREST_AMOUNT LIKE '%'|| :P_SEARCH_VALUE_IN||'%')");

            countQuery.setParameter("pBlaNo", bondTdsCollDeductionReportDTO.getBlaNo());
            countQuery.setParameter("pBranchId", UserAccountUtils.getCurrentSolId());
            countQuery.setParameter("pTdsDeductionFrom", bondTdsCollDeductionReportDTO.getTdsDeductFrom());
            countQuery.setParameter("pTdsDeductionTo", bondTdsCollDeductionReportDTO.getTdsDeductTo());
            countQuery.setParameter("pSchemeCode", bondTdsCollDeductionReportDTO.getSchemeCode());
            countQuery.setParameter("P_SEARCH_VALUE_IN", bondTdsCollDeductionReportDTO.getSearchValue());

            // BigDecimal countResult = BigDecimal.valueOf(countQuery.getSingleResult());
            // Long totalCount = countResult.longValue();
            // BigDecimal countResult = (BigDecimal) countQuery.getSingleResult();
            Long totalCount = Long.parseLong(countQuery.getSingleResult().toString());

            // Long totalCount = countQuery.getSingleResult();

            int maxPages = (int) Math.ceil((double) totalCount / bondTdsCollDeductionReportDTO.getPageSize());
            bondTdsCollDeductionReportDTO.setMaxPages(maxPages);

            tdsDeductionCbList.put("PageDetails", bondTdsCollDeductionReportDTO);

            if (bondTdsCollDeductionReportDTO.getIsDownload() != null
                    && bondTdsCollDeductionReportDTO.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS)
                    && bondTdsCollDeductionReportDTO.getDownloadType() != null) {

                if (bondTdsCollDeductionReportDTO.getDownloadType().equalsIgnoreCase("excel")) {

                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(
                            rbiBondReportsUtil.excelFileGeneratorForTdsCb(results, bondTdsCollDeductionReportDTO)));
                    fileResponseDto.setFileName("TDS Deduction – Report.xlsx");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                    return responseDataDTO;
                }

                if (bondTdsCollDeductionReportDTO.getDownloadType().equalsIgnoreCase("pdf")) {
                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(
                            rbiBondReportsUtil.pdfFileGeneratorForTdsCb(results, bondTdsCollDeductionReportDTO)));
                    fileResponseDto.setFileName("TDS Deduction – Report.pdf");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    return responseDataDTO;
                } else {
                    responseDataDTO.setMessage("Invalid File Type");
                    return responseDataDTO;
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        responseDataDTO.setResult(tdsDeductionCbList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO financialTransactionInquiry(FinancialTransactionInquiryDTO financialTransactionInquiryDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> resultMap = new HashMap<>();
        List<FinancialTransactionInquiryDTO> list = Collections.emptyList();
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, financialTransactionInquiryDTO.getSchemeCode());
            inParams.put(flag, financialTransactionInquiryDTO.getFlag());
            inParams.put(fromDate, financialTransactionInquiryDTO.getFromDate());
            inParams.put(toDate, financialTransactionInquiryDTO.getToDate());
            inParams.put(p_page_no_in, financialTransactionInquiryDTO.getPageNo());
            inParams.put(p_page_size_in, financialTransactionInquiryDTO.getPageSize());
            inParams.put(p_search_value_in, financialTransactionInquiryDTO.getSearchValue());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();

            rowMappers.put(curValOut, (rs, rowNum) -> {
                FinancialTransactionInquiryDTO item = new FinancialTransactionInquiryDTO();
                if (financialTransactionInquiryDTO.getSchemeCode()== null &&financialTransactionInquiryDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                    return item;
                }
                 else if (financialTransactionInquiryDTO.getSchemeCode() != null && financialTransactionInquiryDTO.getFromDate() != null && financialTransactionInquiryDTO.getToDate() != null && financialTransactionInquiryDTO.getFlag().trim().equalsIgnoreCase("D")) {
                    item.setBla(rs.getString(1));
                    item.setSubscriptionID(rs.getString(2));
                    item.setBrokerId(rs.getString(3));
                    item.setTranDate(rs.getString(4));
                    item.setTranMode(rs.getString(5));
                    item.setAccountNumber(rs.getString(6));
                    item.setGbmTranID(rs.getString(7));
                    item.setGbmAmount(rs.getString(8));
                    item.setTranStatus(rs.getString(9));
                    item.setDeleted(rs.getString(10));
                    item.setTranType(rs.getString(11));
                    item.setCbsTranID(rs.getString(12));
                    item.setCbsAmount(rs.getString(13));
                    return item;
                }
                throw new IllegalArgumentException("Invalid flag: " + financialTransactionInquiryDTO.getFlag());
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_fin_tran_inq",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);
            list = (List<FinancialTransactionInquiryDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBlaStatusReport(BlaStatusReportDTO blaStatusReportDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        List<BlaStatusReportDTO> blaStatusReportDTOList = null;
        String resultMsg = "";
        ResultSet rs = null;
        HashMap<String, Object> resultMap = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        double total = 0;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_gbm_rbi_bond_status_rep.getProcName());

                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, blaStatusReportDTO.getSchemeCode());
                stmt.setString(index++, blaStatusReportDTO.getFromDate());
                stmt.setString(index++, blaStatusReportDTO.getToDate());
                stmt.setString(index++, blaStatusReportDTO.getP_sub_sts_in());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));

                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null
                        && CommonConstants.SUCCESS.equalsIgnoreCase(stmt.getString(index - 1))) {

                    rs = (ResultSet) stmt.getObject(7);

                    if (rs != null) {
                        blaStatusReportDTOList = new ArrayList<>();

                        while (rs.next()) {

                            blaStatusReportDTO = new BlaStatusReportDTO();
                            blaStatusReportDTO.setBlanumber(rs.getString(1));
                            blaStatusReportDTO.setHoldername(rs.getString(2));
                            blaStatusReportDTO.setModeofholding(rs.getString(3));
                            blaStatusReportDTO.setBondtype(rs.getString(4));
                            blaStatusReportDTO.setSubcriptionamount(rs.getString(5));
                            blaStatusReportDTO.setMaturitydate(rs.getString(6));
                            blaStatusReportDTO.setSubcriptionId(rs.getString(7));
                            blaStatusReportDTO.setDateofsubscription(rs.getString(8));
                            blaStatusReportDTO.setSubcriptionstatus(rs.getString(9));

                            blaStatusReportDTOList.add(blaStatusReportDTO);

                            total = total + Double.parseDouble(rs.getString(5) != null ? rs.getString(5) : ("0"));

                        }

                        resultMap = new HashMap<>();
                        resultMap.put("total", total);
                        resultMap.put("blaStatusReportDTOList", blaStatusReportDTOList);
                        responseDataDTO.setResult(resultMap);
                    } else {
                        log.info("Proc Status Description: {}" , resultMsg);
                    }
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        // responseDataDTO.setResult(blaStatusReportDTOList);
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBondBalanceReport(BondBalanceReportDTO bondBalanceReportDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        String totalRecords=null;
        HashMap<String, Object> finalData = new HashMap<>();
        List<BondBalanceReportDTO> bondBalanceReportDTOList = Collections.emptyList();

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, bondBalanceReportDTO.getSchemeCode());
            inParams.put(flag, bondBalanceReportDTO.getFlag());
            inParams.put(name, bondBalanceReportDTO.getName());
            inParams.put(panNumber, bondBalanceReportDTO.getPanNo());
            inParams.put(blaNumber, bondBalanceReportDTO.getBlaNo());
            inParams.put(P_SUBS_ID_IN, bondBalanceReportDTO.getSubsId());
            inParams.put(fromDate, bondBalanceReportDTO.getFromDate());
            inParams.put(toDate, bondBalanceReportDTO.getToDate());
            inParams.put(p_page_no_in, bondBalanceReportDTO.getPageNo());
            inParams.put(p_page_size_in, bondBalanceReportDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=bondBalanceReportDTO.getSearchValue()&&!bondBalanceReportDTO.getSearchValue().trim().isEmpty())?bondBalanceReportDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(P_SUBS_ID_IN, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, Types.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BondBalanceReportDTO item = new BondBalanceReportDTO();
                switch (bondBalanceReportDTO.getFlag()) {
                    case "SC":
                        item.setSchemeCode(rs.getString(1));
                        item.setSchemeName(rs.getString(2));
                        item.setSchemeStatus(rs.getString(3));
                        break;
                    case "P":
                        item.setPanNo(rs.getString(1));
                        item.setName(rs.getString(2));
                        break;
                    case "D":
                        item.setBlaNo(rs.getString(1));
                        item.setSubscriptionId(rs.getString(2));
                        item.setBondType(rs.getString(3));
                        item.setSubscriptionDate(rs.getString(4));
                        item.setSubscriptionMaturityDate(rs.getString(5));
                        item.setCurrentBalance(rs.getString(6));
                        item.setSubStatus(rs.getString(7));
                        break;
                    case "B":
                        item.setPanNo(rs.getString(1));
                        item.setName(rs.getString(2));
                        item.setBondType(rs.getString(3));
                        item.setBlaNo(rs.getString(4));
                        break;
                    case "S":
                        item.setPanNo(rs.getString(1));
                        item.setName(rs.getString(2));
                        item.setBondType(rs.getString(3));
                        item.setBlaNo(rs.getString(4));
                        item.setSubsId(rs.getString(5));
                        break;
                }
                return item;
            });

            // Execute stored procedure
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_BALANCE_REPORT",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            // Retrieve output data
            bondBalanceReportDTOList = (List<BondBalanceReportDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            if (CollectionUtils.isEmpty(bondBalanceReportDTOList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("bondBalanceReportDTOList", bondBalanceReportDTOList);
                responseDataDTO.setResult(finalData);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }

        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getReportOnTransactionDetails(ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> resultMap = new HashMap<>();
        List<ReportTransactionDetailsDTO> reportOnTransactionDTOList = Collections.emptyList();
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, reportTransactionDetailsDTO.getSchemeCode());
            inParams.put(flag, reportTransactionDetailsDTO.getFlag());
            inParams.put(name, reportTransactionDetailsDTO.getHolderName());
            inParams.put(panNumber, reportTransactionDetailsDTO.getPanNumber());
            inParams.put(blaNumber, reportTransactionDetailsDTO.getBlaNumber());
            inParams.put(P_SUBS_ID_IN, reportTransactionDetailsDTO.getSubscriptionID());
            inParams.put(fromDate, reportTransactionDetailsDTO.getFromDate());
            inParams.put(toDate, reportTransactionDetailsDTO.getToDate());
            inParams.put(p_page_no_in, reportTransactionDetailsDTO.getPageNo());
            inParams.put(p_page_size_in, reportTransactionDetailsDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=reportTransactionDetailsDTO.getSearchValue()&&!reportTransactionDetailsDTO.getSearchValue().trim().isEmpty())?reportTransactionDetailsDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(P_SUBS_ID_IN, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, Types.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();

            rowMappers.put(curValOut, (rs, rowNum) -> {
                ReportTransactionDetailsDTO item = new ReportTransactionDetailsDTO();
                if (reportTransactionDetailsDTO.getFlag().equalsIgnoreCase("SC")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                    return item;
                } else if (reportTransactionDetailsDTO.getFlag().equalsIgnoreCase("P")) {
                    item.setPanNumber(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    return item;
                } else if (reportTransactionDetailsDTO.getFlag().equalsIgnoreCase("B")) {
                    item.setPanNumber(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setModeOfHolding(rs.getString(3));
                    item.setBlaNumber(rs.getString(4));
                    return item;
                } else if (reportTransactionDetailsDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setPanNumber(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setModeOfHolding(rs.getString(3));
                    item.setBlaNumber(rs.getString(4));
                    item.setSubscriptionID(rs.getString(5));
                    return item;
                } else if (reportTransactionDetailsDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNumber(rs.getString(1));
                    item.setPanNumber(rs.getString(2));
                    item.setHolderName(rs.getString(3));
                    item.setSubscriptionID(rs.getString(4));
                    item.setBrokerCode(rs.getString(5));
                    item.setBrokerName(rs.getString(6));
                    item.setBrokerType(rs.getString(7));
                    item.setModeOfTransaction(rs.getString(8));
                    item.setSubscriptionAmount(rs.getString(9));
                    item.setTransactionDate(rs.getString(10));
                    return item;
                }
                throw new IllegalArgumentException("Invalid flag: " + reportTransactionDetailsDTO.getFlag());
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_TRAN_DETAILS",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);
            reportOnTransactionDTOList = (List<ReportTransactionDetailsDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";


            if (CollectionUtils.isEmpty(reportOnTransactionDTOList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", reportOnTransactionDTOList);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
                responseDataDTO.setResult(resultMap);
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO getBondInvestmentDueNoticeReport(BondInvestmentDueDateNoticeDTO bondInvestmentDueDateNoticeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<BondInvestmentDueDateNoticeDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String fromDate = "p_maturity_from_date";
        String toDate = "p_maturity_to_date";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, bondInvestmentDueDateNoticeDTO.getSchemeCode());
            inParams.put(flag, bondInvestmentDueDateNoticeDTO.getFlag());
            inParams.put(fromDate, bondInvestmentDueDateNoticeDTO.getFromDate());
            inParams.put(toDate, bondInvestmentDueDateNoticeDTO.getToDate());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR)
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BondInvestmentDueDateNoticeDTO item = new BondInvestmentDueDateNoticeDTO();
                if (bondInvestmentDueDateNoticeDTO.getFlag().equals("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (bondInvestmentDueDateNoticeDTO.getFlag().equals("T")) {
                    item.setSubscriptionID(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setAddress(rs.getString(3));
                    item.setBlaNumber(rs.getString(4));
                    item.setMaturityDate(rs.getString(5));
                    item.setSubscriptionDate(rs.getString(6));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_RBI_BOND_INVEST_DUE_DATE_NOTICE",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<BondInvestmentDueDateNoticeDTO>) result.get(curValOut);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBranchDetails() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";

        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareStatement(
                        "select distinct a.branch_id, b.sol_desc from gbm_rbi_bond_hirchy_lvl_master a, gbm_bank_branches b where a.branch_id=b.sol_id order by a.branch_id");
                resultSet = stmt.executeQuery();
                if (resultSet != null) {
                    List<HashMap<String, String>> resultList = new ArrayList<>();

                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("branchId", resultSet.getString("BRANCH_ID"));
                        resultMap.put("branchDescription", resultSet.getString("SOL_DESC"));
                        resultList.add(resultMap);
                    }
                    responseDataDTO.setResult(resultList);
                    resultMsg = CommonConstants.SUCCESS_CONST;
                } else {
                    resultMsg = CommonConstants.NDF;
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBondLedgerAccDet(BondLedgerAccDto bondLedgerAccDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> finalData = new HashMap<>();
        List<BondLedgerAccDto> bondLedgerAccDtoList = Collections.emptyList();
        String totalRecords=null;

        String p_bond_type_in = "p_bond_type_in";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, bondLedgerAccDto.getSchemeCode());
            inParams.put(p_bond_type_in, bondLedgerAccDto.getBondType());
            inParams.put(flag, bondLedgerAccDto.getFlag());
            inParams.put(name, bondLedgerAccDto.getHolderName());
            inParams.put(panNumber, bondLedgerAccDto.getPanNo());
            inParams.put(blaNumber, bondLedgerAccDto.getBlaNo());
            inParams.put(fromDate, bondLedgerAccDto.getFromDate());
            inParams.put(toDate, bondLedgerAccDto.getToDate());
            inParams.put(p_page_no_in, bondLedgerAccDto.getPageNo());
            inParams.put(p_page_size_in, bondLedgerAccDto.getPageSize());
            inParams.put(p_search_value_in, (null!=bondLedgerAccDto.getSearchValue()&&!bondLedgerAccDto.getSearchValue().trim().isEmpty())?bondLedgerAccDto.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(p_bond_type_in, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();

            rowMappers.put(curValOut, (rs, rowNum) -> {

//                ResultSetMetaData metaData = rs.getMetaData(); // Get metadata about the result set
//                int columnCount = metaData.getColumnCount(); // Number of columns in the result set
//
//                System.out.println("Row " + rowNum + ":"); // Print the row numbe
//                for (int i = 1; i <= columnCount; i++) { // Loop through each column (1-based index)
//                    String columnName = metaData.getColumnName(i); // Get column name
//                    String columnValue = rs.getString(i); // Get column value
//                    log.info("Column " + i + " (" + columnName + "): " + columnValue); // Print column details
//                }

                BondLedgerAccDto item = new BondLedgerAccDto();

                switch (bondLedgerAccDto.getFlag()) {
                    case "BT":
                        item.setBondType(rs.getString(1));
                        break;

                    case "S":
                        item.setSchemeCode(rs.getString(1));
                        item.setSchemeName(rs.getString(2));
                        item.setSchemeStatus(rs.getString(3));
                        break;
                    case "P":
                        item.setHolderName(rs.getString(1));
                        item.setPanNo(rs.getString(2));
                        break;
                    case "C":
                        item.setBlaNo(rs.getString(1));
                        item.setHolderName(rs.getString(2));
                        item.setModeOfHolding(rs.getString(3));
                        item.setPanNo(rs.getString(4));
                        break;
                    case "D":
                        item.setDateOfTransaction(rs.getString(1));
                        item.setAmtOfSubs(rs.getString(2));
                        item.setMaturityVal(rs.getString(3));
                        item.setCasAdviceNoDate(rs.getString(4));
                        item.setSubsId(rs.getString(5));
                        item.setTransferSaleBurchase(rs.getString(6));
                        item.setTransferNominalValue(rs.getString(7));
                        item.setTransferNominalAmt(rs.getString(8));
                        item.setDueDateOfRepayment(rs.getString(9));
                        item.setRepaymntNominalAmt(rs.getString(10));
                        item.setRepaymntMaturityValue(rs.getString(11));
                        item.setBalance(rs.getString(12));
                        item.setRemarks(rs.getString(13));
                        item.setInitials(rs.getString(14));
                        item.setHolderName(rs.getString(14));
                        item.setAddressOfTheHolder(rs.getString(15));
                        item.setNominationId(rs.getString(16));
                        break;
                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executeStoredProc(
                    "SP_GBM_RBI_BOND_REP_INDI_LEDGER_ACCT",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            bondLedgerAccDtoList = (List<BondLedgerAccDto>) result.get(curValOut);
            totalRecords = result.get(CommonReportsConstants.p_total_records_out) != null
                    ? result.get(CommonReportsConstants.p_total_records_out).toString()
                    : "0";

            if (CollectionUtils.isEmpty(bondLedgerAccDtoList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("bondLedgerAccDtoList", bondLedgerAccDtoList);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
                responseDataDTO.setResult(finalData);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getSuccessFailureDetails(SuccessFailureReportDto successFailureReportDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> finalData = new HashMap<>();
        List<SuccessFailureReportDto> successFailureReportDtoList = Collections.emptyList();

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, successFailureReportDto.getSchemeCode());
            inParams.put(status, successFailureReportDto.getStatus());
            inParams.put(fromDate, successFailureReportDto.getFromDate());
            inParams.put(toDate, successFailureReportDto.getToDate());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(status, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                SuccessFailureReportDto item = new SuccessFailureReportDto();


                switch (successFailureReportDto.getStatus()) {
                    case "A":

                        item.setInvoiceNumber(rs.getString("invoice_no"));
                        item.setInvoiceDate(rs.getString("invoice_date"));
                        item.setBrokerName(rs.getString("broker_name"));
                        item.setBrokerCode(rs.getString("broker_code"));
                        item.setBrokerPan(rs.getString("broker_pan"));
                        item.setBrokerEmailId(rs.getString("email_id"));
                        item.setGrossBrokerage(rs.getString("gross_brk_amt"));
                        item.setGst(rs.getString("gst_amt"));
                        item.setNetBrokerage(rs.getString("net_brk_amt"));
                        item.setVbsRefNumber(rs.getString("vbs_ref_no"));
                        item.setInvoiceReceiveDate(rs.getString("invoice_receive_date"));
                        item.setInvoiceRaisedOn(rs.getString("invoice_raised_on"));
                        item.setVbmsMaker(rs.getString("vbms_maker"));
                        item.setVbmsChecker(rs.getString("vbms_checker"));
                        item.setPaymentRefNumber(rs.getString("payment_ref_no"));
                        item.setPaidOnDate(rs.getString("paid_on"));
                        break;
                    case "S":

                        item.setInvoiceNumber(rs.getString("invoice_no"));
                        item.setInvoiceDate(rs.getString("invoice_date"));
                        item.setBrokerName(rs.getString("broker_name"));
                        item.setBrokerCode(rs.getString("broker_code"));
                        item.setBrokerPan(rs.getString("broker_pan"));
                        item.setBrokerEmailId(rs.getString("email_id"));
                        item.setGrossBrokerage(rs.getString("gross_brk_amt"));
                        item.setGst(rs.getString("gst_amt"));
                        item.setNetBrokerage(rs.getString("net_brk_amt"));
                        item.setVbsRefNumber(rs.getString("vbs_ref_no"));
                        item.setInvoiceReceiveDate(rs.getString("invoice_receive_date"));
                        item.setInvoiceRaisedOn(rs.getString("invoice_raised_on"));
                        item.setVbmsMaker(rs.getString("vbms_maker"));
                        item.setVbmsChecker(rs.getString("vbms_checker"));
                        item.setPaymentRefNumber(rs.getString("payment_ref_no"));
                        item.setPaidOnDate(rs.getString("paid_on"));
                        break;
                    case "F":

                        item.setInvoiceNumber(rs.getString("invoice_no"));
                        item.setInvoiceDate(rs.getString("invoice_date"));
                        item.setBrokerName(rs.getString("broker_name"));
                        item.setBrokerCode(rs.getString("broker_code"));
                        item.setBrokerPan(rs.getString("broker_pan"));
                        item.setBrokerEmailId(rs.getString("email_id"));
                        item.setGrossBrokerage(rs.getString("gross_brk_amt"));
                        item.setGst(rs.getString("gst_amt"));
                        item.setNetBrokerage(rs.getString("net_brk_amt"));
                        item.setVbsRefNumber(rs.getString("vbs_ref_no"));
                        item.setInvoiceReceiveDate(rs.getString("invoice_receive_date"));
                        item.setInvoiceRaisedOn(rs.getString("invoice_raised_on"));
                        item.setVbmsMaker(rs.getString("vbms_maker"));
                        item.setVbmsChecker(rs.getString("vbms_checker"));
                        item.setPaymentRefNumber(rs.getString("payment_ref_no"));
                        item.setPaidOnDate(rs.getString("paid_on"));
                        break;
                    case "I":

                        item.setInvoiceNumber(rs.getString("invoice_no"));
                        item.setInvoiceDate(rs.getString("invoice_date"));
                        item.setBrokerName(rs.getString("broker_name"));
                        item.setBrokerCode(rs.getString("broker_code"));
                        item.setBrokerPan(rs.getString("broker_pan"));
                        item.setBrokerEmailId(rs.getString("email_id"));
                        item.setGrossBrokerage(rs.getString("gross_brk_amt"));
                        item.setGst(rs.getString("gst_amt"));
                        item.setNetBrokerage(rs.getString("net_brk_amt"));
                        item.setVbsRefNumber(rs.getString("vbs_ref_no"));
                        item.setInvoiceReceiveDate(rs.getString("invoice_receive_date"));
                        item.setInvoiceRaisedOn(rs.getString("invoice_raised_on"));
                        item.setVbmsMaker(rs.getString("vbms_maker"));
                        item.setVbmsChecker(rs.getString("vbms_checker"));
                        item.setPaymentRefNumber(rs.getString("payment_ref_no"));
                        item.setPaidOnDate(rs.getString("paid_on"));
                        break;
                    default:
                        log.info("Unknown status: {}" , successFailureReportDto.getStatus());
                }

                return item;
            });

            // Execute stored procedure
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_BROKERAGE_PAY",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            // Retrieve output data
            successFailureReportDtoList = (List<SuccessFailureReportDto>) result.get(curValOut);

            if (CollectionUtils.isEmpty(successFailureReportDtoList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("successFailureReportDtoList", successFailureReportDtoList);
                responseDataDTO.setResult(finalData);
            }

        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getSchemeSuccessFailureDetails(SuccessFailureReportDto successFailureReportDto) {
        List<SuccessFailureReportDto> list = new ArrayList<>();
        SuccessFailureReportDto item = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_REP_BROKERAGE_PAY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, successFailureReportDto.getSchemeCode());
                stmt.setString(index++, successFailureReportDto.getStatus());
                stmt.setString(index++, successFailureReportDto.getFromDate());
                stmt.setString(index++, successFailureReportDto.getToDate());


                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(7);
                    if (resultSet != null) {

                        while (resultSet.next()) {
                            item = new SuccessFailureReportDto();
                            item.setSchemeCode(resultSet.getString("sub_code"));
                            item.setSchemeDescription(resultSet.getString("sub_code_desc"));
                            item.setSchemeStatus(resultSet.getString("scheme_status"));

                            list.add(item);
                        }
                        resultMap.put("list", list);
                        responseDataDTO.setResult(resultMap);
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getReportTransactionSubcriptionDeatails(ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        PreparedStatement stmt = null;
        String resultMsg = "";

        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;

        try {
            connection = connectionPoolFactory.getConnection();
            int index = 1;
            if (connection != null) {
                stmt = connection.prepareStatement(
                        "select t1.BOND_LEDGER_ACCOUNT_NUMBER as bond_ledger_acc_no, t1.BOND_HLD_FIRST_NAME || t1.BOND_HLD_MID_NAME || t1.BOND_HLD_LAST_NAME as holder_name, t2.BOND_SUBSCRIPTION_ID, t2.ACCOUNT_OPEN_DATE from gbm_rbi_bond_ledger_acc_master t1 inner join gbm_rbi_bond_subscription_flow t2 on (T1.BOND_LEDGER_ACCOUNT_NUMBER = T2.SINGLE_BOND_LEDGER_ACC_NUM) or (T1.BOND_LEDGER_ACCOUNT_NUMBER= T2.FIRST_JOINT_HLDR_BLA_NO) where  T2.ACCOUNT_OPEN_DATE BETWEEN TO_DATE('20/05/2021','dd/mm/yyyy') AND TO_DATE('05/09/2023','dd/mm/yyyy')AND t1.SCH_CODE = ? AND t1.verified_status = 'Y' AND  t1.is_deleted = 'N' AND t1.IS_ACTIVE = 'Y'");
                stmt.setString(index++, reportTransactionDetailsDTO.getSchemeCode());
                resultSet = stmt.executeQuery();
                if (resultSet != null) {
                    List<HashMap<String, String>> resultList = new ArrayList<>();

                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("bond_ledger_acc_no", resultSet.getString(1));
                        resultMap.put("holder_name", resultSet.getString(2));
                        resultMap.put("BOND_SUBSCRIPTION_ID", resultSet.getString(3));
                        resultMap.put("ACCOUNT_OPEN_DATE", resultSet.getString(4));
                        resultList.add(resultMap);
                    }
                    responseDataDTO.setResult(resultList);
                    resultMsg = CommonConstants.SUCCESS_CONST;
                } else {
                    resultMsg = CommonConstants.NDF;
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getInterestTrailRunReportPayment(InterestTrailRunPaymentDTO interestTrailRunPaymentDTO) {
        // ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        // String resultMsg = "";
        // ResultSet rs = null;
        //
        // final Session session = getSession();
        //
        // Transaction transaction = null;
        // boolean isTransactionActive = session.getTransaction().isActive();
        // if (!isTransactionActive) {
        // transaction = session.beginTransaction();
        // }
        //
        // try {
        //
        //
        // String hqlQuery =
        // " SELECT \n" +
        // " T1.BRANCH_ID AS BRACH_ID,\n" +
        // " T1.BLA_NO AS BOND_LEDGER_ACCOUNT_NUMBER,\n" +
        // " CASE WHEN T2.MODE_OF_HOLDING IN ('MH001','MH004','MH005') THEN
        // T2.BOND_HOLDER_NAME\n" +
        // " WHEN T2.MODE_OF_HOLDING IN('MH002','MH003') THEN T2.FIRST_JOINT_HLDR_NAME
        // END AS CUSTOMER_NAME,\n" +
        // " T1.BOND_SUBSCRIPTION_ID AS BOND_SUBSCRIPTION_ID,\n" +
        // " T1.SUBSCRIPTION_AMOUNT AS SUBSCRIPTION_AMOUNT,\n" +
        // " TO_CHAR(T1.ACCOUNT_OPEN_DATE,'DD/MM/YYYY') AS SUBCRIPTION_DATE,\n" +
        // " TO_CHAR(T1.INTEREST_PERIOD_FROM,'DD/MM/YYYY') AS INTEREST_FROM_PERIOD,\n" +
        // " TO_CHAR(T1.INTERST_PERIOD_TO,'DD/MM/YYYY') AS INTERST_TO_PERIOD,\n" +
        // " CASE WHEN T2.MODE_OF_INTEREST_PAYMENT = 'MP001' THEN 'CREDIT ACCOUNT' \n" +
        // " WHEN T2.MODE_OF_INTEREST_PAYMENT = 'MP002' THEN 'DD/PO' \n" +
        // " WHEN T2.MODE_OF_INTEREST_PAYMENT = 'MP003' THEN 'NEFT' END AS
        // INTEREST_PAYMENT_MODE,\n" +
        // " CASE WHEN T2.MODE_OF_INTEREST_PAYMENT = 'MP001' THEN
        // T2.OPERATIVE_ACC_NUMBER\n" +
        // " WHEN T2.MODE_OF_INTEREST_PAYMENT = 'MP002' THEN T2.DD_PO_ACC_NUMBER\n" +
        // " WHEN T2.MODE_OF_INTEREST_PAYMENT = 'MP003' THEN T2.CREDIT_ACCOUNT_NUMBER
        // END AS CREDIT_ACCOUNT_NUMBER,\n" +
        // " T1.INTEREST_AMT AS GROSS_INTEREST,\n" +
        // " nvl(T1.CURRENT_TDS_AMOUNT,0) + nvl(T1.ARREAR_TDS_AMOUNT,0) AS
        // CURRENT_TDS_AMOUNT,\n" +
        // " T1.ARREAR_TDS_AMOUNT AS ARREAR_TDS_AMOUNT,\n" +
        // " T1.NET_INTEREST_AMOUNT AS NET_INTEREST_AMOUNT\n" +
        // " FROM GBM_RBI_BOND_TRAIL_RUN_DTLS T1 INNER JOIN
        // GBM_RBI_BOND_SUBSCRIPTION_FLOW T2\n" +
        // " ON T1.BOND_SUBSCRIPTION_ID = T2.BOND_SUBSCRIPTION_ID\n" +
        // " WHERE TO_CHAR(T1.CREATED_DATE,'MM')= CASE WHEN :pInterestPaidIn = 'JAN-JUN'
        // THEN '07' WHEN :pInterestPaidIn = 'JUL-DEC' THEN '01' END \n" +
        // " AND TO_CHAR(T1.CREATED_DATE,'YYYY')= CASE WHEN :pInterestPaidIn = 'JAN-JUN'
        // THEN SUBSTR(:pFinacialYearIn,1,4) WHEN :pInterestPaidIn = 'JUL-DEC' THEN
        // SUBSTR(:pFinacialYearIn,6,4) END AND T1.IS_SUB_ID_CLOSED = 'N'\n" +
        // " AND T2.MODE_OF_INTEREST_PAYMENT = CASE WHEN :pModPayIn = 'CREDIT ACCOUNT'
        // THEN 'MP001'\n" +
        // " WHEN :pModPayIn = 'DD/PO' THEN 'MP002'\n" +
        // " WHEN :pModPayIn = 'NEFT' THEN 'MP003' END\n" +
        // " AND (T1.BRANCH_ID LIKE '%'|| :pSearchValue ||'%'\n" +
        // " OR T1.BLA_NO LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T2.FIRST_JOINT_HLDR_NAME LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T1.BOND_SUBSCRIPTION_ID LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T1.ACCOUNT_OPEN_DATE LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T1.SUBSCRIPTION_AMOUNT LIKE '%'||:pSearchValue||'%'\n" +
        // " OR TO_CHAR(T1.INTEREST_PERIOD_FROM, 'DD/MM/YYYY') LIKE
        // '%'||:pSearchValue||'%'\n" +
        // " OR TO_CHAR(T1.INTERST_PERIOD_TO, 'DD/MM/YYYY') LIKE
        // '%'||:pSearchValue||'%'\n" +
        // " OR T2.MODE_OF_INTEREST_PAYMENT LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T2.CREDIT_ACCOUNT_NUMBER LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T1.INTEREST_AMT LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T1.CURRENT_TDS_AMOUNT LIKE '%'|| :pSearchValue ||'%'\n" +
        // " OR T1.NET_INTEREST_AMOUNT LIKE '%'|| :pSearchValue ||'%')\n" +
        // " ";
        //
        // if (interestTrailRunPaymentDTO.getSortField() != null &&
        // !interestTrailRunPaymentDTO.getSortField().isEmpty() &&
        // interestTrailRunPaymentDTO.getSortOrder() != null &&
        // !interestTrailRunPaymentDTO.getSortOrder().isEmpty()) {
        // hqlQuery += " ORDER BY " + interestTrailRunPaymentDTO.getSortField() + " " +
        // interestTrailRunPaymentDTO.getSortOrder();
        // }
        //
        // Query<Map<String, Object>> query = session.createNativeQuery(hqlQuery);
        // query.setParameter("pInterestPaidIn",
        // interestTrailRunPaymentDTO.getIntertestPaymentFor());
        // query.setParameter("pFinacialYearIn",
        // interestTrailRunPaymentDTO.getFinacialYear());
        // query.setParameter("pModPayIn",
        // interestTrailRunPaymentDTO.getInterestPaymentMode());
        // query.setParameter("pSearchValue",
        // interestTrailRunPaymentDTO.getSearchValue());
        // query.setFirstResult((interestTrailRunPaymentDTO.getPageNo() - 1) *
        // interestTrailRunPaymentDTO.getPageSize());
        // query.setMaxResults(interestTrailRunPaymentDTO.getPageSize());
        //
        //
        // query.setResultTransformer(new TrailRunDetailsResultTransformer());
        // List<Map<String, Object>> results = query.getResultList();
        // interestTrailRunPaymentDTO.setData(results);
        //
        //
        // Query<BigDecimal> countQuery = session.createNativeQuery(" SELECT \n" +
        // " COUNT(1)\n" +
        // " FROM GBM_RBI_BOND_TRAIL_RUN_DTLS T1 INNER JOIN
        // GBM_RBI_BOND_SUBSCRIPTION_FLOW T2\n" +
        // " ON T1.BOND_SUBSCRIPTION_ID = T2.BOND_SUBSCRIPTION_ID\n" +
        // " WHERE TO_CHAR(T1.CREATED_DATE,'MM')= CASE WHEN :pInterestPaidIn = 'JAN-JUN'
        // THEN '07' WHEN :pInterestPaidIn = 'JUL-DEC' THEN '01' END \n" +
        // " AND TO_CHAR(T1.CREATED_DATE,'YYYY')= CASE WHEN :pInterestPaidIn = 'JAN-JUN'
        // THEN SUBSTR(:pFinacialYearIn,1,4) WHEN :pInterestPaidIn = 'JUL-DEC' THEN
        // SUBSTR(:pFinacialYearIn,6,4) END AND T1.IS_SUB_ID_CLOSED = 'N'\n" +
        // " AND T2.MODE_OF_INTEREST_PAYMENT = CASE WHEN :pModPayIn = 'CREDIT ACCOUNT'
        // THEN 'MP001'\n" +
        // " WHEN :pModPayIn = 'DD/PO' THEN 'MP002'\n" +
        // " WHEN :pModPayIn = 'NEFT' THEN 'MP003' END\n" +
        // " AND (T1.BRANCH_ID LIKE '%'|| :pSearchValue ||'%'\n" +
        // " OR T1.BLA_NO LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T2.FIRST_JOINT_HLDR_NAME LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T1.BOND_SUBSCRIPTION_ID LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T1.ACCOUNT_OPEN_DATE LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T1.SUBSCRIPTION_AMOUNT LIKE '%'||:pSearchValue||'%'\n" +
        // " OR TO_CHAR(T1.INTEREST_PERIOD_FROM, 'DD/MM/YYYY') LIKE
        // '%'||:pSearchValue||'%'\n" +
        // " OR TO_CHAR(T1.INTERST_PERIOD_TO, 'DD/MM/YYYY') LIKE
        // '%'||:pSearchValue||'%'\n" +
        // " OR T2.MODE_OF_INTEREST_PAYMENT LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T2.CREDIT_ACCOUNT_NUMBER LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T1.INTEREST_AMT LIKE '%'||:pSearchValue||'%'\n" +
        // " OR T1.CURRENT_TDS_AMOUNT LIKE '%'|| :pSearchValue ||'%'\n" +
        // " OR T1.NET_INTEREST_AMOUNT LIKE '%'|| :pSearchValue ||'%')\n" +
        // " ");
        //
        // countQuery.setParameter("pInterestPaidIn",
        // interestTrailRunPaymentDTO.getIntertestPaymentFor());
        // countQuery.setParameter("pFinacialYearIn",
        // interestTrailRunPaymentDTO.getFinacialYear());
        // countQuery.setParameter("pModPayIn",
        // interestTrailRunPaymentDTO.getInterestPaymentMode());
        // countQuery.setParameter("pSearchValue",
        // interestTrailRunPaymentDTO.getSearchValue());
        // Long totalCount = Long.parseLong(countQuery.getSingleResult().toString());
        //
        // int maxPages = (int) Math.ceil((double) totalCount /
        // interestTrailRunPaymentDTO.getPageSize());
        // interestTrailRunPaymentDTO.setMaxPages(maxPages);
        //
        //
        // if (interestTrailRunPaymentDTO.getIsDownload() != null &&
        // interestTrailRunPaymentDTO.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS)
        // && interestTrailRunPaymentDTO.getDownloadType() != null) {
        //
        // if (interestTrailRunPaymentDTO.getDownloadType().equalsIgnoreCase("excel")) {
        //
        // FileResponseDto fileResponseDto = new FileResponseDto();
        // fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil.excelFileGeneratorForInterRunRepaymentReport(interestTrailRunPaymentDTO)));
        // fileResponseDto.setFileName("Bond Interest Trial Run Report Mode of Payment
        // Wise.xlsx");
        // fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
        // responseDataDTO.setResult(fileResponseDto);
        // responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        //
        // return responseDataDTO;
        // }
        //
        // if (interestTrailRunPaymentDTO.getDownloadType().equalsIgnoreCase("pdf")) {
        // FileResponseDto fileResponseDto = new FileResponseDto();
        // fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil.pdfFileGeneratorForInterRunRepaymentReport(interestTrailRunPaymentDTO)));
        // fileResponseDto.setFileName("Bond Interest Trial Run Report Mode of Payment
        // Wise.pdf");
        // fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
        // responseDataDTO.setResult(fileResponseDto);
        // responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        // return responseDataDTO;
        // } else {
        // responseDataDTO.setMessage("Invalid File Type");
        // return responseDataDTO;
        // }
        //
        // }
        //
        //
        // } catch (Exception ex) {
        // ex.printStackTrace();
        // } finally {
        // session.close();
        // }
        //
        // responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        // responseDataDTO.setResult(interestTrailRunPaymentDTO);
        // return responseDataDTO;

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> resultMap = new HashMap<>();
        List<InterestTrailRunPaymentDTO> list = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            InterestTrailRunPaymentDTO item = new InterestTrailRunPaymentDTO();
            item.setCustomerName("Hema-" + i);
            item.setPanNumber("TTTTT111" + i + "A");
            item.setBondLedgerAccount("RBI339948492-" + i);
            list.add(item);
        }

        resultMap.put("list", list);
        responseDataDTO.setResult(resultMap);
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getInterRunCrdtCollBranReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO) {

        if (bondInterRunCrdtCollBranDTO.getIsTorch().equalsIgnoreCase("Y")
                && bondInterRunCrdtCollBranDTO.getBlaNo() == null
                && bondInterRunCrdtCollBranDTO.getSubscriptionId() == null) {

            Connection connection = null;
            PreparedStatement stmt = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "";

            ResultSet resultSet = null;
            HashMap<String, String> resultMap = null;
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareStatement(" SELECT  bond_ledger_account_number AS bla_no,\n" +
                            "            bond_hld_first_name||' '||bond_hld_mid_name||' '||bond_hld_last_name   AS name_of_the_bond_holder,\n"
                            +
                            "            CASE WHEN mode_of_holding = 'MH001' THEN 'SINGLE' \n" +
                            "            WHEN mode_of_holding = 'MH002' THEN 'Joint – Jointly'\n" +
                            "            WHEN mode_of_holding = 'MH003' THEN 'Joint- Anyone or Survivor'\n" +
                            "            WHEN mode_of_holding = 'MH004' THEN 'HUF'\n" +
                            "            WHEN mode_of_holding = 'MH005' THEN 'Legal Guardian' END AS mode_of_holding\n"
                            +
                            "            FROM gbm_rbi_bond_ledger_acc_master\n" +
                            "            WHERE  sch_code = ? AND \n" +
                            "            verified_status = 'Y' AND is_active = 'Y' AND is_deleted ='N'\n" +
                            "            AND (joint_holder_type = '1' OR joint_holder_type ='S')\n" +
                            "            and  BRANCH_ID  = ? \n" +
                            "            ");
                    stmt.setString(index++, bondInterRunCrdtCollBranDTO.getSchemeCode());
                    stmt.setString(index, UserAccountUtils.getCurrentSolId());
                    resultSet = stmt.executeQuery();
                    if (resultSet != null) {
                        List<HashMap<String, String>> resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("blaNo", resultSet.getString("BLA_NO"));
                            resultMap.put("bondHolderName", resultSet.getString("NAME_OF_THE_BOND_HOLDER"));
                            resultMap.put("modeOfHolding", resultSet.getString("MODE_OF_HOLDING"));
                            resultList.add(resultMap);
                        }
                        responseDataDTO.setResult(resultList);
                        resultMsg = CommonConstants.SUCCESS_CONST;
                    } else {
                        resultMsg = CommonConstants.NDF;
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
            return responseDataDTO;

        }
        if (bondInterRunCrdtCollBranDTO.getIsTorch().equalsIgnoreCase("Y")
                && bondInterRunCrdtCollBranDTO.getBlaNo() != null
                && bondInterRunCrdtCollBranDTO.getSubscriptionId() == null) {

            Connection connection = null;
            PreparedStatement stmt = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "";

            ResultSet resultSet = null;
            HashMap<String, String> resultMap = null;
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareStatement(" SELECT \n" +
                            "BOND_SUBSCRIPTION_ID  AS BOND_SUBSCRIPTION_ID ,\n" +
                            "SUBSCRIPTION_AMOUNT AS SUBSCRIPTION_AMOUNT,\n" +
                            "to_char(ACCOUNT_OPEN_DATE,'dd/mm/yyyy') AS SUBSCRIPTION_DATE\n" +
                            "FROM GBM_RBI_BOND_SUBSCRIPTION_FLOW\n" +
                            "WHERE  sch_code = ? AND \n" +
                            "verified_status = 'Y' AND is_active = 'Y' AND is_deleted ='N'\n" +
                            "and  BRANCH_ID  = ? \n" +
                            "AND ((single_bond_ledger_acc_num = ? or first_joint_hldr_bla_no = ? ))\n" +
                            "");
                    stmt.setString(index++, bondInterRunCrdtCollBranDTO.getSchemeCode());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, bondInterRunCrdtCollBranDTO.getBlaNo());
                    stmt.setString(index, bondInterRunCrdtCollBranDTO.getBlaNo());
                    resultSet = stmt.executeQuery();
                    if (resultSet != null) {
                        List<HashMap<String, String>> resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("subscriptionId", resultSet.getString("BOND_SUBSCRIPTION_ID"));
                            resultMap.put("subscriptionAmt", resultSet.getString("SUBSCRIPTION_AMOUNT"));
                            resultMap.put("subscriptionDate", resultSet.getString("SUBSCRIPTION_DATE"));
                            resultList.add(resultMap);
                        }
                        responseDataDTO.setResult(resultList);
                        resultMsg = CommonConstants.SUCCESS_CONST;
                    } else {
                        resultMsg = CommonConstants.NDF;
                    }
                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }

            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;

        }
        if (bondInterRunCrdtCollBranDTO.getIsTorch().equalsIgnoreCase("N")
                && bondInterRunCrdtCollBranDTO.getBlaNo() == null
                && bondInterRunCrdtCollBranDTO.getSubscriptionId() == null
                && bondInterRunCrdtCollBranDTO.getInterestPayFrom() != null
                && bondInterRunCrdtCollBranDTO.getInterestPayTo() != null) {

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();

            final Session session = getSession();

            Transaction transaction = null;
            boolean isTransactionActive = session.getTransaction().isActive();
            if (!isTransactionActive) {
                transaction = session.beginTransaction();
            }

            try {

                String hqlQuery = " SELECT \n" +
                        "                T1.BONDLEDGERACCOUNTNUMBER                               AS   BLA_NO,\n" +
                        "                T1.SUBSCRIPTIONAMOUNT                  AS   SUBSCRIPTION_AMOUNT,           \n"
                        +
                        "                T1.NETINTERESTAMOUNT                  AS   NET_INTEREST_AMOUNT,\n" +
                        "                T1.INTERESTAMOUNT                     as   Gross_Interest_Amount,\n" +
                        "                TO_CHAR(T1.INTERESTPAIDDATE,'DD/MM/YYYY')                   AS   INTREST_DATE,\n"
                        +
                        "                T1.BONDSUBSCRIPTIONID                 AS   BOND_SUBSCRIPTION_ID,\n" +
                        "                T1.CUSTOMERNAME                        AS   CUSTOMER_NAME,\n" +
                        "                TO_CHAR(T1.INTERESTPERIODFROM,'DD/MM/YYYY')   AS   INTEREST_PERIOD_FROM,\n" +
                        "                TO_CHAR(T1.INTERESTPERIODTO,'DD/MM/YYYY')    AS   INTERST_PERIOD_TO,\n" +
                        "                T1.CREDITACCOUNTNUMBER                AS   CREDIT_ACCOUNT_NUMBER,\n" +
                        "                T1.GBMTRANID                          AS   GBM_TRAN_ID,\n" +
                        "                TO_CHAR(T1.SUBSCRIPTIONDATE,'DD/MM/YYYY')  AS   SUBSCRIPTION_DATE,\n" +
                        "                T1.MODEOFINTERESTPAYMENT             as   MODE_OF_INTEREST_PAYMENT,\n" +
                        "                sum(nvl(T1.ARREARTDSAMOUNT,0)+nvl(T1.CURRENTTDSAMOUNT,0)) as TDS_AMT,            \n"
                        +
                        "                T2.FINACLETRANID                      as   CBS_Tran_Id\n" +
                        "                FROM\n" +
                        "                InterestPaymentDetails T1\n" +
                        "                INNER JOIN \n" +
                        "                TransactionTableDetails T2\n" +
                        "                ON    \n" +
                        "                T1.GBMTRANID = T2.GBMTRANID \n" +
                        "                WHERE (T1.VERIFIEDSTATUS = 'Y' AND T1.ISDELETED='N' AND T1.SCHCODE = :pSchemeCode) \n"
                        +
                        "                AND T2.VERIFIEDSTATUS = 'Y' AND T2.ISDELETED='N' AND \n" +
                        "                T2.SCHSUBCODE = :pSchemeCode and T1.BRANCHID = :pBranchId\n" +
                        "                AND  (T1.INTERESTPAIDDATE BETWEEN TO_DATE(:pInterestFrom,'DD/MM/YYYY') and TO_DATE(:pInterestTo,'DD/MM/YYYY'))\n"
                        +
                        "                AND T2.TRANLEG='D'\n" +
                        "                                                                   AND T1.BONDLEDGERACCOUNTNUMBER LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.CUSTOMERNAME LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.SUBSCRIPTIONDATE LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                      OR T1.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                      OR TO_CHAR(T1.INTERESTPAIDDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.CREDITACCOUNTNUMBER  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                         OR T1.INTERESTAMOUNT  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                       OR T1.NETINTERESTAMOUNT LIKE '%'|| :pSearchValue ||'%'\n"
                        +
                        "                                                                       OR T1.GBMTRANID LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                       OR T2.FINACLETRANID LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                GROUP BY  T1.BONDLEDGERACCOUNTNUMBER,                               \n" +
                        "                T1.SUBSCRIPTIONAMOUNT,                        \n" +
                        "                T1.NETINTERESTAMOUNT, \n" +
                        "                T1.INTERESTAMOUNT,\n" +
                        "                T1.INTERESTPAIDDATE,                 \n" +
                        "                T1.BONDSUBSCRIPTIONID,              \n" +
                        "                T1.CUSTOMERNAME,                      \n" +
                        "                T1.INTERESTPERIODFROM,               \n" +
                        "                T1.INTERESTPERIODTO,                   \n" +
                        "                T1.CREDITACCOUNTNUMBER,          \n" +
                        "                T1.GBMTRANID,               \n" +
                        "                T1.SUBSCRIPTIONDATE,        \n" +
                        "                T1.MODEOFINTERESTPAYMENT,\n" +
                        "                T2.FINACLETRANID";

                if (bondInterRunCrdtCollBranDTO.getSortField() != null
                        && !bondInterRunCrdtCollBranDTO.getSortField().isEmpty()
                        && bondInterRunCrdtCollBranDTO.getSortOrder() != null
                        && !bondInterRunCrdtCollBranDTO.getSortOrder().isEmpty()) {
                    hqlQuery += " ORDER BY " + bondInterRunCrdtCollBranDTO.getSortField() + " "
                            + bondInterRunCrdtCollBranDTO.getSortOrder();
                }

                Query<Map<String, Object>> query = session.createQuery(hqlQuery);
                query.setParameter("pSchemeCode", bondInterRunCrdtCollBranDTO.getSchemeCode());
                query.setParameter("pBranchId", UserAccountUtils.getCurrentSolId());
                query.setParameter("pInterestFrom", bondInterRunCrdtCollBranDTO.getInterestPayFrom());
                query.setParameter("pInterestTo", bondInterRunCrdtCollBranDTO.getInterestPayTo());
                query.setParameter("pSearchValue", bondInterRunCrdtCollBranDTO.getSearchValue());
                query.setFirstResult(
                        (bondInterRunCrdtCollBranDTO.getPageNo() - 1) * bondInterRunCrdtCollBranDTO.getPageSize());
                query.setMaxResults(bondInterRunCrdtCollBranDTO.getPageSize());

                query.setResultTransformer(new TrailRunDetailsResultTransformer());
                List<Map<String, Object>> results = query.getResultList();
                bondInterRunCrdtCollBranDTO.setData(results);

                Query<Long> countQuery = session.createQuery(" SELECT \n" +
                        "                COUNT(1)\n" +
                        "                FROM\n" +
                        "                InterestPaymentDetails T1\n" +
                        "                INNER JOIN \n" +
                        "                TransactionTableDetails T2\n" +
                        "                ON    \n" +
                        "                T1.GBMTRANID = T2.GBMTRANID \n" +
                        "                WHERE (T1.VERIFIEDSTATUS = 'Y' AND T1.ISDELETED='N' AND T1.SCHCODE = :pSchemeCode) \n"
                        +
                        "                AND T2.VERIFIEDSTATUS = 'Y' AND T2.ISDELETED='N' AND \n" +
                        "                T2.SCHSUBCODE = :pSchemeCode and T1.BRANCHID = :pBranchId\n" +
                        "                AND  (T1.INTERESTPAIDDATE BETWEEN TO_DATE(:pInterestFrom,'DD/MM/YYYY') and TO_DATE(:pInterestTo,'DD/MM/YYYY'))\n"
                        +
                        "                AND T2.TRANLEG='D'\n" +
                        "                                                                   AND T1.BONDLEDGERACCOUNTNUMBER LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.CUSTOMERNAME LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.SUBSCRIPTIONDATE LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                      OR T1.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                      OR TO_CHAR(T1.INTERESTPAIDDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.CREDITACCOUNTNUMBER  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                         OR T1.INTERESTAMOUNT  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                       OR T1.NETINTERESTAMOUNT LIKE '%'|| :pSearchValue ||'%'\n"
                        +
                        "                                                                       OR T1.GBMTRANID LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                       OR T2.FINACLETRANID LIKE '%'||:pSearchValue||'%'");
                // " GROUP BY T1.BONDLEDGERACCOUNTNUMBER, \n" +
                // " T1.SUBSCRIPTIONAMOUNT, \n" +
                // " T1.NETINTERESTAMOUNT, \n" +
                // " T1.INTERESTAMOUNT,\n" +
                // " T1.INTERESTPAIDDATE, \n" +
                // " T1.BONDSUBSCRIPTIONID, \n" +
                // " T1.CUSTOMERNAME, \n" +
                // " T1.INTERESTPERIODFROM, \n" +
                // " T1.INTERESTPERIODTO, \n" +
                // " T1.CREDITACCOUNTNUMBER, \n" +
                // " T1.GBMTRANID, \n" +
                // " T1.SUBSCRIPTIONDATE, \n" +
                // " T1.MODEOFINTERESTPAYMENT,\n" +
                // " T2.FINACLETRANID");

                countQuery.setParameter("pSchemeCode", bondInterRunCrdtCollBranDTO.getSchemeCode());
                countQuery.setParameter("pBranchId", UserAccountUtils.getCurrentSolId());
                countQuery.setParameter("pInterestFrom", bondInterRunCrdtCollBranDTO.getInterestPayFrom());
                countQuery.setParameter("pInterestTo", bondInterRunCrdtCollBranDTO.getInterestPayTo());
                countQuery.setParameter("pSearchValue", bondInterRunCrdtCollBranDTO.getSearchValue());
                Long totalCount = countQuery.getSingleResult();

                int maxPages = (int) Math.ceil((double) totalCount / bondInterRunCrdtCollBranDTO.getPageSize());
                bondInterRunCrdtCollBranDTO.setMaxPages(maxPages);

                if (bondInterRunCrdtCollBranDTO.getIsDownload() != null
                        && bondInterRunCrdtCollBranDTO.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS)
                        && bondInterRunCrdtCollBranDTO.getDownloadType() != null) {

                    if (bondInterRunCrdtCollBranDTO.getDownloadType().equalsIgnoreCase("excel")) {

                        FileResponseDto fileResponseDto = new FileResponseDto();
                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil
                                .excelFileGeneratorForInterRunCollBranchReport(bondInterRunCrdtCollBranDTO)));
                        fileResponseDto
                                .setFileName("Bond Actual Interest Run Credited Report – Collecting Branch.xlsx");
                        fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                        responseDataDTO.setResult(fileResponseDto);
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                        return responseDataDTO;
                    }

                    if (bondInterRunCrdtCollBranDTO.getDownloadType().equalsIgnoreCase("pdf")) {
                        FileResponseDto fileResponseDto = new FileResponseDto();
                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil
                                .pdfFileGeneratorForInterRunCollBranchReport(bondInterRunCrdtCollBranDTO)));
                        fileResponseDto.setFileName("Bond Actual Interest Run Credited Report – Collecting Branch.pdf");
                        fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                        responseDataDTO.setResult(fileResponseDto);
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                        return responseDataDTO;
                    } else {
                        responseDataDTO.setMessage("Invalid File Type");
                        return responseDataDTO;
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                session.close();
            }

            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
            responseDataDTO.setResult(bondInterRunCrdtCollBranDTO);
            return responseDataDTO;

        }
        if (bondInterRunCrdtCollBranDTO.getIsTorch().equalsIgnoreCase("N")
                && bondInterRunCrdtCollBranDTO.getBlaNo() != null
                && bondInterRunCrdtCollBranDTO.getSubscriptionId() != null
                && bondInterRunCrdtCollBranDTO.getInterestPayFrom() != null
                && bondInterRunCrdtCollBranDTO.getInterestPayTo() != null) {

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            final Session session = getSession();

            Transaction transaction = null;
            boolean isTransactionActive = session.getTransaction().isActive();
            if (!isTransactionActive) {
                transaction = session.beginTransaction();
            }

            try {

                String hqlQuery = "    SELECT \n" +
                        "                T1.BONDLEDGERACCOUNTNUMBER                               AS   BLA_NO,\n" +
                        "                T1.SUBSCRIPTIONAMOUNT                  AS   SUBSCRIPTION_AMOUNT,           \n"
                        +
                        "                T1.NETINTERESTAMOUNT                  AS   NET_INTEREST_AMOUNT,\n" +
                        "                T1.INTERESTAMOUNT                     as   Gross_Interest_Amount,\n" +
                        "                TO_CHAR(T1.INTERESTPAIDDATE,'DD/MM/YYYY')                   AS   INTREST_DATE,\n"
                        +
                        "                T1.BONDSUBSCRIPTIONID                 AS   BOND_SUBSCRIPTION_ID,\n" +
                        "                T1.CUSTOMERNAME                        AS   CUSTOMER_NAME,\n" +
                        "                TO_CHAR(T1.INTERESTPERIODFROM,'DD/MM/YYYY')   AS   INTEREST_PERIOD_FROM,\n" +
                        "                TO_CHAR(T1.INTERESTPERIODTO,'DD/MM/YYYY')    AS   INTERST_PERIOD_TO,\n" +
                        "                T1.CREDITACCOUNTNUMBER                AS   CREDIT_ACCOUNT_NUMBER,\n" +
                        "                T1.GBMTRANID                          AS   GBM_TRAN_ID,\n" +
                        "                TO_CHAR(T1.SUBSCRIPTIONDATE,'DD/MM/YYYY')  AS   SUBSCRIPTION_DATE,\n" +
                        "                T1.MODEOFINTERESTPAYMENT             as   MODE_OF_INTEREST_PAYMENT,\n" +
                        "                sum(nvl(T1.ARREARTDSAMOUNT,0)+nvl(T1.CURRENTTDSAMOUNT,0)) as TDS_AMT,            \n"
                        +
                        "                T2.FINACLETRANID                      as   CBS_Tran_Id\n" +
                        "                FROM\n" +
                        "                InterestPaymentDetails T1\n" +
                        "                INNER JOIN \n" +
                        "                TransactionTableDetails T2\n" +
                        "                ON    \n" +
                        "                T1.GBMTRANID = T2.GBMTRANID \n" +
                        "                WHERE (T1.VERIFIEDSTATUS = 'Y' AND T1.ISDELETED='N' AND T1.SCHCODE = :pSchemeCode) \n"
                        +
                        "                AND T2.VERIFIEDSTATUS = 'Y' AND T2.ISDELETED='N' AND \n" +
                        "                T2.SCHSUBCODE = :pSchemeCode and T1.BRANCHID = nvl(:pBranchId, T1.BRANCHID)\n"
                        +
                        "                  AND T1.BONDLEDGERACCOUNTNUMBER = nvl(:pBlaNo, T1.BONDLEDGERACCOUNTNUMBER)\n"
                        +
                        "                and T1.BONDSUBSCRIPTIONID = nvl(:pSubscriptionId,T1.BONDSUBSCRIPTIONID)\n" +
                        "                AND  (T1.INTERESTPAIDDATE BETWEEN TO_DATE(:pInterestFrom,'DD/MM/YYYY') and TO_DATE(:pInterestTo,'DD/MM/YYYY'))\n"
                        +
                        "                AND T2.TRANLEG='D'\n" +
                        "                                                                   AND (T1.BONDLEDGERACCOUNTNUMBER LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.CUSTOMERNAME LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.SUBSCRIPTIONDATE LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                      OR T1.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                      OR TO_CHAR(T1.INTERESTPAIDDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.CREDITACCOUNTNUMBER  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                         OR T1.INTERESTAMOUNT  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                       OR T1.NETINTERESTAMOUNT LIKE '%'|| :pSearchValue ||'%'\n"
                        +
                        "                                                                       OR T1.GBMTRANID LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                       OR T2.FINACLETRANID LIKE '%'||:pSearchValue||'%')\n"
                        +
                        "                GROUP BY  T1.BONDLEDGERACCOUNTNUMBER,                               \n" +
                        "                T1.SUBSCRIPTIONAMOUNT,                        \n" +
                        "                T1.NETINTERESTAMOUNT, \n" +
                        "                T1.INTERESTAMOUNT,\n" +
                        "                T1.INTERESTPAIDDATE,                 \n" +
                        "                T1.BONDSUBSCRIPTIONID,              \n" +
                        "                T1.CUSTOMERNAME,                      \n" +
                        "                T1.INTERESTPERIODFROM,               \n" +
                        "                T1.INTERESTPERIODTO,                   \n" +
                        "                T1.CREDITACCOUNTNUMBER,          \n" +
                        "                T1.GBMTRANID,               \n" +
                        "                T1.SUBSCRIPTIONDATE,        \n" +
                        "                T1.MODEOFINTERESTPAYMENT,\n" +
                        "                T2.FINACLETRANID";

                if (bondInterRunCrdtCollBranDTO.getSortField() != null
                        && !bondInterRunCrdtCollBranDTO.getSortField().isEmpty()
                        && bondInterRunCrdtCollBranDTO.getSortOrder() != null
                        && !bondInterRunCrdtCollBranDTO.getSortOrder().isEmpty()) {
                    hqlQuery += " ORDER BY " + bondInterRunCrdtCollBranDTO.getSortField() + " "
                            + bondInterRunCrdtCollBranDTO.getSortOrder();
                }

                Query<Map<String, Object>> query = session.createQuery(hqlQuery);
                query.setParameter("pSchemeCode", bondInterRunCrdtCollBranDTO.getSchemeCode());
                query.setParameter("pInterestFrom", bondInterRunCrdtCollBranDTO.getInterestPayFrom());
                query.setParameter("pInterestTo", bondInterRunCrdtCollBranDTO.getInterestPayTo());
                query.setParameter("pBlaNo", bondInterRunCrdtCollBranDTO.getBlaNo());
                query.setParameter("pSubscriptionId", bondInterRunCrdtCollBranDTO.getSubscriptionId());
                query.setParameter("pBranchId", UserAccountUtils.getCurrentSolId());

                query.setParameter("pSearchValue", bondInterRunCrdtCollBranDTO.getSearchValue());
                query.setFirstResult(
                        (bondInterRunCrdtCollBranDTO.getPageNo() - 1) * bondInterRunCrdtCollBranDTO.getPageSize());
                query.setMaxResults(bondInterRunCrdtCollBranDTO.getPageSize());

                query.setResultTransformer(new TrailRunDetailsResultTransformer());
                List<Map<String, Object>> results = query.getResultList();
                bondInterRunCrdtCollBranDTO.setData(results);

                Query<Long> countQuery = session.createQuery(" SELECT \n" +
                        "              COUNT(1)\n" +
                        "                FROM\n" +
                        "                InterestPaymentDetails T1\n" +
                        "                INNER JOIN \n" +
                        "                TransactionTableDetails T2\n" +
                        "                ON    \n" +
                        "                T1.GBMTRANID = T2.GBMTRANID \n" +
                        "                WHERE (T1.VERIFIEDSTATUS = 'Y' AND T1.ISDELETED='N' AND T1.SCHCODE = :pSchemeCode) \n"
                        +
                        "                AND T2.VERIFIEDSTATUS = 'Y' AND T2.ISDELETED='N' AND \n" +
                        "                T2.SCHSUBCODE = :pSchemeCode and T1.BRANCHID = nvl(:pBranchId, T1.BRANCHID)\n"
                        +
                        "                  AND T1.BONDLEDGERACCOUNTNUMBER = nvl(:pBlaNo, T1.BONDLEDGERACCOUNTNUMBER)\n"
                        +
                        "                and T1.BONDSUBSCRIPTIONID = nvl(:pSubscriptionId,T1.BONDSUBSCRIPTIONID)\n" +
                        "                AND  (T1.INTERESTPAIDDATE BETWEEN TO_DATE(:pInterestFrom,'DD/MM/YYYY') and TO_DATE(:pInterestTo,'DD/MM/YYYY'))\n"
                        +
                        "                AND T2.TRANLEG='D'\n" +
                        "                                                                   AND (T1.BONDLEDGERACCOUNTNUMBER LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.CUSTOMERNAME LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.SUBSCRIPTIONDATE LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                      OR T1.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                      OR TO_CHAR(T1.INTERESTPAIDDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                        OR T1.CREDITACCOUNTNUMBER  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                         OR T1.INTERESTAMOUNT  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                       OR T1.NETINTERESTAMOUNT LIKE '%'|| :pSearchValue ||'%'\n"
                        +
                        "                                                                       OR T1.GBMTRANID LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                       OR T2.FINACLETRANID LIKE '%'||:pSearchValue||'%')");

                // " GROUP BY T1.BONDLEDGERACCOUNTNUMBER, \n" +
                // " T1.SUBSCRIPTIONAMOUNT, \n" +
                // " T1.NETINTERESTAMOUNT, \n" +
                // " T1.INTERESTAMOUNT,\n" +
                // " T1.INTERESTPAIDDATE, \n" +
                // " T1.BONDSUBSCRIPTIONID, \n" +
                // " T1.CUSTOMERNAME, \n" +
                // " T1.INTERESTPERIODFROM, \n" +
                // " T1.INTERESTPERIODTO, \n" +
                // " T1.CREDITACCOUNTNUMBER, \n" +
                // " T1.GBMTRANID, \n" +
                // " T1.SUBSCRIPTIONDATE, \n" +
                // " T1.MODEOFINTERESTPAYMENT,\n" +
                // " T2.FINACLETRANID");
                countQuery.setParameter("pSchemeCode", bondInterRunCrdtCollBranDTO.getSchemeCode());
                countQuery.setParameter("pInterestFrom", bondInterRunCrdtCollBranDTO.getInterestPayFrom());
                countQuery.setParameter("pInterestTo", bondInterRunCrdtCollBranDTO.getInterestPayTo());
                countQuery.setParameter("pBlaNo", bondInterRunCrdtCollBranDTO.getBlaNo());
                countQuery.setParameter("pSubscriptionId", bondInterRunCrdtCollBranDTO.getSubscriptionId());
                countQuery.setParameter("pBranchId", UserAccountUtils.getCurrentSolId());
                countQuery.setParameter("pSearchValue", bondInterRunCrdtCollBranDTO.getSearchValue());
                Long totalCount = countQuery.getSingleResult();
                int maxPages = (int) Math.ceil((double) totalCount / bondInterRunCrdtCollBranDTO.getPageSize());
                bondInterRunCrdtCollBranDTO.setMaxPages(maxPages);

                if (bondInterRunCrdtCollBranDTO.getIsDownload() != null
                        && bondInterRunCrdtCollBranDTO.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS)
                        && bondInterRunCrdtCollBranDTO.getDownloadType() != null) {

                    if (bondInterRunCrdtCollBranDTO.getDownloadType().equalsIgnoreCase("excel")) {

                        FileResponseDto fileResponseDto = new FileResponseDto();
                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil
                                .excelFileGeneratorForInterRunCollBranchReport(bondInterRunCrdtCollBranDTO)));
                        fileResponseDto
                                .setFileName("Bond Actual Interest Run Credited Report – Collecting Branch.xlsx");
                        fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                        responseDataDTO.setResult(fileResponseDto);
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                        return responseDataDTO;
                    }

                    if (bondInterRunCrdtCollBranDTO.getDownloadType().equalsIgnoreCase("pdf")) {
                        FileResponseDto fileResponseDto = new FileResponseDto();
                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil
                                .pdfFileGeneratorForInterRunCollBranchReport(bondInterRunCrdtCollBranDTO)));
                        fileResponseDto.setFileName("Bond Actual Interest Run Credited Report – Collecting Branch.pdf");
                        fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                        responseDataDTO.setResult(fileResponseDto);
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                        return responseDataDTO;
                    } else {
                        responseDataDTO.setMessage("Invalid File Type");
                        return responseDataDTO;
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                session.close();
            }

            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
            responseDataDTO.setResult(bondInterRunCrdtCollBranDTO);
            return responseDataDTO;

        } else {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("No Options");
            return responseDataDTO;
        }
    }

    @Override
    public ResponseDataDTO getBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        PreparedStatement stmt = null;
        String resultMsg = "";

        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;

        try {
            connection = connectionPoolFactory.getConnection();
            int index = 1;
            if (connection != null) {
                stmt = connection.prepareStatement(
                        "SELECT DISTINCT T1.BRANCH_ID AS BRANCH_ID, T2.sol_desc as branch_desc FROM gbm_rbi_bond_hirchy_lvl_master t1 INNER JOIN gbm_bank_branches t2 ON t1.branch_id = t2.sol_id WHERE t1.SCH_CODE = ? AND t1.verified_status = 'Y' AND t1.is_deleted = 'N' AND t2.ver_flg = '1' AND t2.del_flg = '0'");
                stmt.setString(index, bondInterestCalculatedDTO.getSchemeCode());
                resultSet = stmt.executeQuery();
                if (resultSet != null) {
                    List<HashMap<String, String>> resultList = new ArrayList<>();

                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("BRANCH_ID", resultSet.getString(1));
                        resultMap.put("branch_desc", resultSet.getString(2));
                        resultList.add(resultMap);
                    }
                    responseDataDTO.setResult(resultList);
                    resultMsg = CommonConstants.SUCCESS_CONST;
                } else {
                    resultMsg = CommonConstants.NDF;
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBondInterestCalculatedReportDetails(BondInterestCalculatedDTO bondInterestCalculatedDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        final Session session = getSession();
        Transaction transaction = null;
        boolean isTransactionActive = session.getTransaction().isActive();
        if (!isTransactionActive) {
            transaction = session.beginTransaction();
        }
        try {
            String hqlQuery = "SELECT \n" +
                    "                            T1.BRANCHID   AS BRANCH_ID,\n" +
                    "                            T1.BLANO         AS BLANO,\n" +
                    "                            CASE WHEN T2.MODEOFHOLDING IN ('MH001','MH004','MH005') THEN T2.BONDHOLDERNAME\n"
                    +
                    "                            WHEN T2.MODEOFHOLDING IN('MH002','MH003') THEN T2.FIRSTJOINTHLDRNAME  END AS CUSTOMER_NAME,\n"
                    +
                    "                            T1.BONDSUBSCRIPTIONID     AS BOND_SUBSCRIPTION_ID,\n" +
                    "                             T2.BONDSUBCRIPTIONAMOUNT       AS  SUBSCRIPTION_AMOUNT,\n" +
                    "                            TO_CHAR(T2.ACCOUNTOPENDATE, 'DD/MM/YYYY')        AS SUBCRIPTION_DATE,\n"
                    +
                    "                            CASE WHEN T2.MODEOFINTERESTPAYMENT = 'MP001' THEN 'CREDIT ACCOUNT' \n"
                    +
                    "                            WHEN T2.MODEOFINTERESTPAYMENT = 'MP002' THEN 'DD/PO' \n" +
                    "                            WHEN T2.MODEOFINTERESTPAYMENT = 'MP003' THEN 'NEFT' END AS  INTEREST_PAYMENT_MODE,\n"
                    +
                    "                            T1.INTERESTAMT      AS GROSS_INTEREST_AMOUNT,\n" +
                    "                            T1.INELIGIBLEREASONS      AS REASON \n" +
                    "                            FROM BondCalculatedDTO T1 INNER JOIN \n" +
                    "                            SubscriptionFlowTableDTO T2 \n" +
                    "                            ON (T1.BONDSUBSCRIPTIONID=T2.BONDSUBSCRIPTIONID)\n" +
                    "                            where  T1.BRANCHID= NVL(:pBranchId,T1.BRANCHID)\n" +
                    "                            AND T2.SCHCODE= :pSchemeCode\n" +
                    "                            AND  TO_CHAR(T1.CREATEDDATE,'MM')= CASE WHEN :pInterestPaidIn = 'JAN-JUN' THEN '07' WHEN :pInterestPaidIn = 'JUL-DEC' THEN '01' END \n"
                    +
                    "                            AND TO_CHAR(T1.CREATEDDATE,'YYYY')= CASE WHEN :pInterestPaidIn = 'JAN-JUN' THEN SUBSTR(:pfinacialYear,1,4) WHEN :pInterestPaidIn = 'JUL-DEC' THEN SUBSTR(:pfinacialYear,6,4) END\n"
                    +
                    "                           AND T1.BRANCHID LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n" +
                    "                                             OR T1.BLANO LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n" +
                    "                                              OR T2.BONDHOLDERNAME LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n"
                    +
                    "                                                    OR T2.FIRSTJOINTHLDRNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR T1.BONDSUBSCRIPTIONID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR  T2.BONDSUBCRIPTIONAMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR TO_CHAR(T2.ACCOUNTOPENDATE, 'DD/MM/YYYY')LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T2.SINGLEBONDLEDGERACCOUNTNUMBER LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR T2.MODEOFINTERESTPAYMENT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR T1.INTERESTAMT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR T1.INELIGIBLEREASONS   LIKE '%'||:P_SEARCH_VALUE_IN||'%'";
            if (bondInterestCalculatedDTO.getSortField() != null && !bondInterestCalculatedDTO.getSortField().isEmpty()
                    && bondInterestCalculatedDTO.getSortOrder() != null
                    && !bondInterestCalculatedDTO.getSortOrder().isEmpty()) {
                hqlQuery += " ORDER BY " + bondInterestCalculatedDTO.getSortField() + " "
                        + bondInterestCalculatedDTO.getSortOrder();
            }

            Query<Map<String, Object>> query = session.createQuery(hqlQuery);

            query.setParameter("pSchemeCode", bondInterestCalculatedDTO.getSchemeCode());
            query.setParameter("pBranchId", bondInterestCalculatedDTO.getBranchId());
            query.setParameter("pInterestPaidIn", bondInterestCalculatedDTO.getInterestPaid());
            query.setParameter("pfinacialYear", bondInterestCalculatedDTO.getFinancialYear());
            // query.setParameter("pSchemeCode", bondInterestCalculatedDTO.getSchemeCode());
            query.setParameter("P_SEARCH_VALUE_IN", bondInterestCalculatedDTO.getSearchValue());
            query.setFirstResult((bondInterestCalculatedDTO.getPageNo() - 1) * bondInterestCalculatedDTO.getPageSize());
            query.setMaxResults(bondInterestCalculatedDTO.getPageSize());

            query.setResultTransformer(new TrailRunDetailsResultTransformer());
            List<Map<String, Object>> results = query.getResultList();
            bondInterestCalculatedDTO.setData(results);
            // bondInterestCalculatedList.put("bondInterestCalculatedList",
            // bondInterestCalculatedDTO.getData());

            Query<Long> countQuery = session.createQuery("SELECT \n" +
                    "                           count(1)\n" +
                    "                            FROM BondCalculatedDTO T1 INNER JOIN \n" +
                    "                            SubscriptionFlowTableDTO T2 \n" +
                    "                            ON (T1.BONDSUBSCRIPTIONID=T2.BONDSUBSCRIPTIONID)\n" +
                    "                            where  T1.BRANCHID= NVL(:pBranchId,T1.BRANCHID)\n" +
                    "                            AND T2.SCHCODE= :pSchemeCode\n" +
                    "                            AND  TO_CHAR(T1.CREATEDDATE,'MM')= CASE WHEN :pInterestPaidIn = 'JAN-JUN' THEN '07' WHEN :pInterestPaidIn = 'JUL-DEC' THEN '01' END \n"
                    +
                    "                            AND TO_CHAR(T1.CREATEDDATE,'YYYY')= CASE WHEN :pInterestPaidIn = 'JAN-JUN' THEN SUBSTR(:pfinacialYear,1,4) WHEN :pInterestPaidIn = 'JUL-DEC' THEN SUBSTR(:pfinacialYear,6,4) END\n"
                    +
                    "                           AND T1.BRANCHID LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n" +
                    "                                                   OR T1.BLANO LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n"
                    +
                    "                                                   OR T2.BONDHOLDERNAME LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n"
                    +
                    "                                                    OR T2.FIRSTJOINTHLDRNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR T1.BONDSUBSCRIPTIONID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR  T2.BONDSUBCRIPTIONAMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR TO_CHAR(T2.ACCOUNTOPENDATE, 'DD/MM/YYYY')LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                   OR T2.SINGLEBONDLEDGERACCOUNTNUMBER LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR T2.MODEOFINTERESTPAYMENT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR T1.INTERESTAMT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n"
                    +
                    "                                                    OR T1.INELIGIBLEREASONS   LIKE '%'||:P_SEARCH_VALUE_IN||'%'");
            countQuery.setParameter("pSchemeCode", bondInterestCalculatedDTO.getSchemeCode());
            countQuery.setParameter("pBranchId", bondInterestCalculatedDTO.getBranchId());
            countQuery.setParameter("pInterestPaidIn", bondInterestCalculatedDTO.getInterestPaid());
            countQuery.setParameter("pfinacialYear", bondInterestCalculatedDTO.getFinancialYear());
            countQuery.setParameter("P_SEARCH_VALUE_IN", bondInterestCalculatedDTO.getSearchValue());
            // countQuery.setParameter("pSchemeCode",
            // bondInterestCalculatedDTO.getSchemeCode());

            Long totalCount = countQuery.getSingleResult();

            int maxPages = (int) Math.ceil((double) totalCount / bondInterestCalculatedDTO.getPageSize());
            bondInterestCalculatedDTO.setMaxPages(maxPages);

            // bondInterestCalculatedList.put("PageDetails", bondInterestCalculatedDTO);

            if (bondInterestCalculatedDTO.getIsDownload() != null
                    && bondInterestCalculatedDTO.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS)
                    && bondInterestCalculatedDTO.getDownloadType() != null) {

                if (bondInterestCalculatedDTO.getDownloadType().equalsIgnoreCase("excel")) {

                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil
                            .excelFileGeneratorForBondInterestCalculatedReport(bondInterestCalculatedDTO)));
                    fileResponseDto.setFileName("Bond Interest NOT Calculated Report - Centralised.xlsx");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                    return responseDataDTO;
                }

                if (bondInterestCalculatedDTO.getDownloadType().equalsIgnoreCase("pdf")) {
                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil
                            .pdfFileGeneratorForBondInterestCalculatedReport(bondInterestCalculatedDTO)));
                    fileResponseDto.setFileName("Bond Interest NOT Calculated Report - Centralised.pdf");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    return responseDataDTO;
                } else {
                    responseDataDTO.setMessage("Invalid File Type");
                    return responseDataDTO;
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        responseDataDTO.setResult(bondInterestCalculatedDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getInterPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO) {
        if (bondInterestPayPendingDTO.getIsTorch().equalsIgnoreCase("Y")) {

            Connection connection = null;
            PreparedStatement stmt = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "";

            ResultSet resultSet = null;
            HashMap<String, String> resultMap = null;
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareStatement(
                            "SELECT DISTINCT T1.BRANCH_ID AS BRANCH_ID, T2.sol_desc as branch_desc\n" +
                                    "            FROM gbm_rbi_bond_hirchy_lvl_master t1 INNER JOIN gbm_bank_branches t2\n"
                                    +
                                    "            ON t1.branch_id = t2.sol_id \n" +
                                    "            WHERE t1.SCH_CODE = ? AND t1.verified_status = 'Y' \n" +
                                    "            AND t1.is_deleted = 'N' AND t2.ver_flg = '1' AND t2.del_flg = '0'");
                    stmt.setString(index, bondInterestPayPendingDTO.getSchemeCode());
                    resultSet = stmt.executeQuery();
                    if (resultSet != null) {
                        List<HashMap<String, String>> resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("branchId", resultSet.getString("BRANCH_ID"));
                            resultMap.put("branchName", resultSet.getString("BRANCH_DESC"));
                            resultList.add(resultMap);
                        }
                        responseDataDTO.setResult(resultList);
                        resultMsg = CommonConstants.SUCCESS_CONST;
                    } else {
                        resultMsg = CommonConstants.NDF;
                    }
                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }

            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;

        }

        if (bondInterestPayPendingDTO.getIsTorch().equalsIgnoreCase("N")) {

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();

            final Session session = getSession();

            Transaction transaction = null;
            boolean isTransactionActive = session.getTransaction().isActive();
            if (!isTransactionActive) {
                transaction = session.beginTransaction();
            }

            try {

                String hqlQuery = "SELECT DISTINCT\n" +
                        "        T1.BRANCHID   AS BRANCH_ID,\n" +
                        "        CASE WHEN T2.MODEOFHOLDING IN ('MH001','MH004','MH005')  THEN T2.SINGLEBONDLEDGERACCNUM \n"
                        +
                        "        WHEN T2.MODEOFHOLDING IN('MH002','MH003')  THEN T2.FIRSTJOINTHLDRBLANO END   AS bond_ledger_account_number,\n"
                        +
                        "        CASE WHEN T2.MODEOFHOLDING IN ('MH001','MH004','MH005') THEN T2.BONDHOLDERNAME\n" +
                        "        WHEN T2.MODEOFHOLDING IN('MH002','MH003') THEN T2.FIRSTJOINTHOLDERNAME  END AS CUSTOMER_NAME,\n"
                        +
                        "        T1.BONDSUBSCRIPTIONID     AS BOND_SUBSCRIPTION_ID,\n" +
                        "        T2.SUBSCRIPTIONAMOUNT      AS  SUBSCRIPTION_AMOUNT,\n" +
                        "        T2.ACCOUNTOPENDATE        AS SUBCRIPTION_DATE,\n" +
                        "        CASE WHEN T2.MODEOFINTERESTPAYMENT = 'MP001' THEN 'CREDIT ACCOUNT' \n" +
                        "        WHEN T2.MODEOFINTERESTPAYMENT = 'MP002' THEN 'DD/PO' \n" +
                        "        WHEN T2.MODEOFINTERESTPAYMENT = 'MP003' THEN 'NEFT' END AS  INTEREST_PAYMENT_MODE,\n" +
                        "        T1.INTERESTAMT      AS GROSS_INTEREST_AMOUNT,\n" +
                        "        T1.INELIGIBLEREASONS     AS REASON \n" +
                        "        FROM BondIntInEligibleAcc T1 INNER JOIN \n" +
                        "        SubscriptionFlow T2 \n" +
                        "        ON (T1.BONDSUBSCRIPTIONID=T2.BONDSUBSCRIPTIONID) \n" +
                        "        INNER JOIN \n" +
                        "        InterestPaymentDetails T4\n" +
                        "        ON T2.BONDSUBSCRIPTIONID = T4.BONDSUBSCRIPTIONID\n" +
                        "        where  T1.BRANCHID= NVL(:pbranchId,T1.BRANCHID)\n" +
                        "        AND T2.SCHCODE=:pschemeCode\n" +
                        "        AND TO_CHAR(T1.CREATEDDATE,'MM')= CASE WHEN  :pInterestPaidIn= 'JAN-JUN' THEN '07' WHEN :pInterestPaidIn= 'JUL-DEC' THEN '01' END \n"
                        +
                        "        AND TO_CHAR(T1.CREATEDDATE,'YYYY')= CASE WHEN :pInterestPaidIn= 'JAN-JUN' THEN SUBSTR(:pfinacialYear,1,4) WHEN :pInterestPaidIn= 'JUL-DEC' THEN SUBSTR(:pfinacialYear,6,4) END\n"
                        +
                        "        AND T4.ISPAYMENTDONE='N'\n" +
                        "          AND T1.BRANCHID LIKE '%'||:pSearchValue||'%'\n" +
                        "        OR T2.SINGLEBONDLEDGERACCNUM LIKE '%'||:pSearchValue||'%'\n" +
                        "        OR T2.FIRSTJOINTHLDRBLANO LIKE '%'||:pSearchValue||'%'\n" +
                        "        OR T2.BONDHOLDERNAME LIKE '%'||:pSearchValue||'%'\n" +
                        "      OR T2.FIRSTJOINTHOLDERNAME LIKE '%'||:pSearchValue||'%'\n" +
                        "      OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n" +
                        "      OR T2.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n" +
                        "      OR TO_CHAR(T2.ACCOUNTOPENDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n" +
                        "    OR T2.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n" +
                        "        OR T1.INTERESTAMT  LIKE '%'||:pSearchValue||'%'\n" +
                        "       OR T1.INELIGIBLEREASONS LIKE '%'|| :pSearchValue ||'%'";

                if (bondInterestPayPendingDTO.getSortField() != null
                        && !bondInterestPayPendingDTO.getSortField().isEmpty()
                        && bondInterestPayPendingDTO.getSortOrder() != null
                        && !bondInterestPayPendingDTO.getSortOrder().isEmpty()) {
                    hqlQuery += " ORDER BY " + bondInterestPayPendingDTO.getSortField() + " "
                            + bondInterestPayPendingDTO.getSortOrder();
                }

                Query<Map<String, Object>> query = session.createQuery(hqlQuery);
                query.setParameter("pschemeCode", bondInterestPayPendingDTO.getSchemeCode());
                query.setParameter("pbranchId", bondInterestPayPendingDTO.getBranchId());
                query.setParameter("pInterestPaidIn", bondInterestPayPendingDTO.getInterestPaymentFor());
                query.setParameter("pfinacialYear", bondInterestPayPendingDTO.getFinancialYear());
                query.setParameter("pSearchValue", bondInterestPayPendingDTO.getSearchValue());
                query.setFirstResult(
                        (bondInterestPayPendingDTO.getPageNo() - 1) * bondInterestPayPendingDTO.getPageSize());
                query.setMaxResults(bondInterestPayPendingDTO.getPageSize());

                query.setResultTransformer(new TrailRunDetailsResultTransformer());
                List<Map<String, Object>> results = query.getResultList();
                bondInterestPayPendingDTO.setData(results);

                Query<Long> countQuery = session.createQuery("SELECT COUNT(1) \n" +
                        "        FROM BondIntInEligibleAcc T1 INNER JOIN \n" +
                        "        SubscriptionFlow T2 \n" +
                        "        ON (T1.BONDSUBSCRIPTIONID=T2.BONDSUBSCRIPTIONID) \n" +
                        "        INNER JOIN \n" +
                        "        InterestPaymentDetails T4\n" +
                        "        ON T2.BONDSUBSCRIPTIONID = T4.BONDSUBSCRIPTIONID\n" +
                        "        where  T1.BRANCHID= NVL(:pbranchId,T1.BRANCHID)\n" +
                        "        AND T2.SCHCODE=:pschemeCode\n" +
                        "        AND TO_CHAR(T1.CREATEDDATE,'MM')= CASE WHEN  :pInterestPaidIn= 'JAN-JUN' THEN '07' WHEN :pInterestPaidIn= 'JUL-DEC' THEN '01' END \n"
                        +
                        "        AND TO_CHAR(T1.CREATEDDATE,'YYYY')= CASE WHEN :pInterestPaidIn= 'JAN-JUN' THEN SUBSTR(:pfinacialYear,1,4) WHEN :pInterestPaidIn= 'JUL-DEC' THEN SUBSTR(:pfinacialYear,6,4) END\n"
                        +
                        "        AND T4.ISPAYMENTDONE='N'\n" +
                        "          AND T1.BRANCHID LIKE '%'||:pSearchValue||'%'\n" +
                        "        OR T2.SINGLEBONDLEDGERACCNUM LIKE '%'||:pSearchValue||'%'\n" +
                        "        OR T2.FIRSTJOINTHLDRBLANO LIKE '%'||:pSearchValue||'%'\n" +
                        "        OR T2.BONDHOLDERNAME LIKE '%'||:pSearchValue||'%'\n" +
                        "      OR T2.FIRSTJOINTHOLDERNAME LIKE '%'||:pSearchValue||'%'\n" +
                        "      OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n" +
                        "      OR T2.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n" +
                        "      OR TO_CHAR(T2.ACCOUNTOPENDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n" +
                        "    OR T2.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n" +
                        "        OR T1.INTERESTAMT  LIKE '%'||:pSearchValue||'%'\n" +
                        "       OR T1.INELIGIBLEREASONS LIKE '%'|| :pSearchValue ||'%'");

                countQuery.setParameter("pschemeCode", bondInterestPayPendingDTO.getSchemeCode());
                countQuery.setParameter("pbranchId", bondInterestPayPendingDTO.getBranchId());
                countQuery.setParameter("pInterestPaidIn", bondInterestPayPendingDTO.getInterestPaymentFor());
                countQuery.setParameter("pfinacialYear", bondInterestPayPendingDTO.getFinancialYear());
                countQuery.setParameter("pSearchValue", bondInterestPayPendingDTO.getSearchValue());
                Long totalCount = countQuery.getSingleResult();

                int maxPages = (int) Math.ceil((double) totalCount / bondInterestPayPendingDTO.getPageSize());
                bondInterestPayPendingDTO.setMaxPages(maxPages);

                if (bondInterestPayPendingDTO.getIsDownload() != null
                        && bondInterestPayPendingDTO.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS)
                        && bondInterestPayPendingDTO.getDownloadType() != null) {

                    if (bondInterestPayPendingDTO.getDownloadType().equalsIgnoreCase("excel")) {

                        FileResponseDto fileResponseDto = new FileResponseDto();
                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil
                                .excelFileGeneratorForInterestPayPendingReport(bondInterestPayPendingDTO)));
                        fileResponseDto.setFileName("Bond Interest Payment Pending Report – Centralised.xlsx");
                        fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                        responseDataDTO.setResult(fileResponseDto);
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                        return responseDataDTO;
                    }

                    if (bondInterestPayPendingDTO.getDownloadType().equalsIgnoreCase("pdf")) {
                        FileResponseDto fileResponseDto = new FileResponseDto();
                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil
                                .pdfFileGeneratorForInterestPayPendingReport(bondInterestPayPendingDTO)));
                        fileResponseDto.setFileName("Bond Interest Payment Pending Report – Centralised.pdf");
                        fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                        responseDataDTO.setResult(fileResponseDto);
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                        return responseDataDTO;
                    } else {
                        responseDataDTO.setMessage("Invalid File Type");
                        return responseDataDTO;
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                session.close();
            }

            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
            responseDataDTO.setResult(bondInterestPayPendingDTO);
            return responseDataDTO;

        } else {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("No Options");
            return responseDataDTO;
        }
    }

    @Override
    public ResponseDataDTO getAccHolderInquiryReport(BondAccHolderInquiryReportDTO bondAccHolderInquiryReportDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        double total = 0;
        HashMap<String, Object> resultMap = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ACCOUNT_HOLDER_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondAccHolderInquiryReportDTO.getSchemeCode());
                stmt.setString(index++, bondAccHolderInquiryReportDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (bondAccHolderInquiryReportDTO.getBlaNo() == null) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(5);
                        if (resultSet != null) {
                            List<BondAccHolderInquiryReportDTO> list = new ArrayList<>();
                            BondAccHolderInquiryReportDTO item = null;
                            while (resultSet.next()) {
                                item = new BondAccHolderInquiryReportDTO();

                                item.setBlaNo(resultSet.getString(1));
                                item.setBondHolderName(resultSet.getString(2));
                                item.setModeOfHolding(resultSet.getString(3));
                                item.setHolderType(resultSet.getString(4));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else if (bondAccHolderInquiryReportDTO.getBlaNo() != null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(5);
                        if (resultSet != null) {
                            List<BondAccHolderInquiryReportDTO> list = new ArrayList<>();
                            BondAccHolderInquiryReportDTO item = null;
                            while (resultSet.next()) {
                                item = new BondAccHolderInquiryReportDTO();

                                item.setSubscriptionId(resultSet.getString(1));
                                item.setSubscriptionDate(resultSet.getString(2));
                                item.setSubscriptionAmt(resultSet.getString(3));
                                item.setMaturityDate(resultSet.getString(4));
                                item.setSubscriptionStatus(resultSet.getString(5));

                                list.add(item);
                                total = total + Double
                                        .parseDouble(resultSet.getString(3) != null ? resultSet.getString(3) : ("0"));
                            }
                            resultMap = new HashMap<>();
                            resultMap.put("item", list);
                            resultMap.put("total", total);

                            responseDataDTO.setResult(resultMap);
                        }
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBlaNameSearchReport(BondBlaNameSearchReportDTO bondBlaNameSearchReportDTO) {
        if (bondBlaNameSearchReportDTO.getIsTorch().equalsIgnoreCase("Y")) {

            Connection connection = null;
            PreparedStatement stmt = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "";

            ResultSet resultSet = null;
            HashMap<String, String> resultMap = null;
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareStatement("SELECT  BOND_LEDGER_ACCOUNT_NUMBER AS BLA_NO,\n" +
                            "        BOND_HLD_FIRST_NAME||' '||BOND_HLD_MID_NAME||' '||BOND_HLD_LAST_NAME   AS NAME_OF_THE_BOND_HOLDER,\n"
                            +
                            "        CASE WHEN JOINT_HOLDER_TYPE = '1' THEN 'First Joint Holder'  \n" +
                            "        WHEN JOINT_HOLDER_TYPE = '2' THEN 'Second Joint Holder'\n" +
                            "        WHEN JOINT_HOLDER_TYPE = '3' THEN 'Third Joint Holder'\n" +
                            "        ELSE 'Single Holder' END AS HOLDER_TYPE,\n" +
                            "        CASE WHEN MODE_OF_HOLDING = 'MH001' THEN 'Single' \n" +
                            "        WHEN MODE_OF_HOLDING = 'MH002' THEN 'Joint – Jointly'\n" +
                            "        WHEN MODE_OF_HOLDING = 'MH003' THEN 'Joint- Anyone or Survivor'\n" +
                            "        WHEN MODE_OF_HOLDING = 'MH004' THEN 'HUF'\n" +
                            "        WHEN MODE_OF_HOLDING = 'MH005' THEN 'Legal Guardian' END AS MODE_OF_HOLDING,\n" +
                            "        VERIFIED_STATUS AS VERIFIED_STATUS\n" +
                            "        FROM GBM_RBI_BOND_LEDGER_ACC_MASTER\n" +
                            "        WHERE  SCH_CODE = ? AND\n" +
                            "        VERIFIED_STATUS = 'Y' AND IS_ACTIVE = 'Y' AND IS_DELETED ='N'");
                    stmt.setString(index, bondBlaNameSearchReportDTO.getSchemeCode());
                    resultSet = stmt.executeQuery();
                    if (resultSet != null) {
                        List<HashMap<String, String>> resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("blaNo", resultSet.getString("BLA_NO"));
                            resultMap.put("holderName", resultSet.getString("NAME_OF_THE_BOND_HOLDER"));
                            resultMap.put("holderType", resultSet.getString("HOLDER_TYPE"));
                            resultMap.put("modeOfHolding", resultSet.getString("MODE_OF_HOLDING"));
                            resultList.add(resultMap);
                        }
                        responseDataDTO.setResult(resultList);
                        resultMsg = CommonConstants.SUCCESS_CONST;
                    } else {
                        resultMsg = CommonConstants.NDF;
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
            return responseDataDTO;

        }
        if (bondBlaNameSearchReportDTO.getIsTorch().equalsIgnoreCase("N")) {
            Connection connection = null;
            CallableStatement stmt = null;
            String resultMsg = "";
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_INQ_NS.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, bondBlaNameSearchReportDTO.getSchemeCode());
                    stmt.setString(index++, bondBlaNameSearchReportDTO.getBlaNo());
                    stmt.setString(index++, bondBlaNameSearchReportDTO.getFirstNameHolder());
                    stmt.setString(index++, bondBlaNameSearchReportDTO.getMiddleNameHolder());
                    stmt.setString(index++, bondBlaNameSearchReportDTO.getLastNameHolder());
                    stmt.setString(index++, bondBlaNameSearchReportDTO.getAddress());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                    resultMsg = stmt.getString(index);

                    if (stmt.getString(index - 1) != null
                            && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(9);
                        if (resultSet != null) {
                            List<BondBlaNameSearchReportDTO> list = new ArrayList<>();
                            BondBlaNameSearchReportDTO item = null;
                            while (resultSet.next()) {
                                item = new BondBlaNameSearchReportDTO();

                                item.setBlaNo(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setAddress(resultSet.getString(3));
                                item.setModeOfHolding(resultSet.getString(4));
                                item.setHolderType(resultSet.getString(5));
                                item.setSubscriptionId(resultSet.getString(6));
                                item.setSubscriptionDate(resultSet.getString(7));
                                item.setSubscriptionAmt(resultSet.getString(8));
                                item.setSubscriptionStatus(resultSet.getString(9));

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
                resultMsg = ErrorMessages.DATABASE_ERROR;
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }

            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;
        } else {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("No Options");
            return responseDataDTO;
        }
    }

    // Bond Interest Not Calculated Report
    @Override
    public ResponseDataDTO getBondCollectingdReportDetails(BondInteresDTO bondInteresDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> resultMap = new HashMap<>();
        List<BondInteresDTO> list = new ArrayList<>();
        String totalRecords = null;

        String interest = "p_int_code_in";

        try {
            // Input parameters
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, bondInteresDTO.getSchemeCode());
            inParams.put(flag, bondInteresDTO.getFlag());
            inParams.put(interest, bondInteresDTO.getMonth());
            inParams.put(financialYear, bondInteresDTO.getFinYear());
            inParams.put(p_page_no_in, bondInteresDTO.getPageNo());
            inParams.put(p_page_size_in, bondInteresDTO.getPageSize());
            inParams.put(p_search_value_in, bondInteresDTO.getSearchValue());

            // Define SQL parameters
            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(interest, Types.VARCHAR),
                    new SqlParameter(financialYear, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR),

            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BondInteresDTO item = new BondInteresDTO();
                if (bondInteresDTO.getFlag() != null) {
                    switch (bondInteresDTO.getFlag()) {
                        case "S":
                            item.setSchemeCode(rs.getString(1));
                            item.setSchemeName(rs.getString(2));
                            item.setSchemestatus(rs.getString(3));
                            break;
                        case "FY":
                            item.setFinYear(rs.getString(1));
                            break;
                        case "I":
                            item.setMonth(rs.getString(1));
                            break;
                        case "D":
                            item.setPanNo(rs.getString(1));
                            item.setBlaNo((rs.getString(2)));
                            item.setHolderName(rs.getString(3));
                            item.setSubscriptionDate(rs.getString(4));
                            item.setSubscriptionAccountNo(rs.getString(5));
                            item.setSubscriptionAmount(rs.getString(6));
                            item.setInterestPaymentmode(rs.getString(7));
                            item.setGrossInterestAmount(rs.getString(8));
                            item.setReasons(rs.getString(9));
                            break;
                    }
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_int_not_cal_rep",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            list = (List<BondInteresDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));

            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getPreClosureRepaymentReport(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        HashMap<String, Object> resultMap = new HashMap<>();
        List<BondPrecloserRepayReportDTO> list = new ArrayList<>();


        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, bondPrecloserRepayReportDTO.getSchemeCode());
            inParams.put(flag, bondPrecloserRepayReportDTO.getFlag());
            inParams.put(fromDate, bondPrecloserRepayReportDTO.getFromDate());
            inParams.put(toDate, bondPrecloserRepayReportDTO.getToDate());
            inParams.put(p_page_no_in, bondPrecloserRepayReportDTO.getPageNo());
            inParams.put(p_page_size_in, bondPrecloserRepayReportDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=bondPrecloserRepayReportDTO.getSearchValue()&&!bondPrecloserRepayReportDTO.getSearchValue().trim().isEmpty())?bondPrecloserRepayReportDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BondPrecloserRepayReportDTO item = new BondPrecloserRepayReportDTO();
                if (bondPrecloserRepayReportDTO.getSchemeCode().isEmpty()) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setStatus(rs.getString(3));
                } else if (bondPrecloserRepayReportDTO.getSchemeCode() != null && bondPrecloserRepayReportDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPanNo(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setBlaNo(rs.getString(3));
                    item.setHolderName(rs.getString(4));
                    item.setSubscriptionId(rs.getString(5));
                    item.setSubscriptionDate(rs.getString(6));
                    item.setSubscriptionAmt(rs.getString(7));
                    item.setDueDateRepay(rs.getString(8));
                    item.setDatePreclosRepay(rs.getString(9));
                    item.setGrossAmt(rs.getString(10));
                    item.setTdsAmt(rs.getString(11));
                    item.setTdsPercentage(rs.getString(12));
                    item.setRepayAmt(rs.getString(13));
                    item.setCreditAccNo(rs.getString(14));
                    item.setMakerId(rs.getString(15));

                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                    String makerDate = rs.getString(16);

                    if (makerDate != null) {
                        Date date = null;
                        try {
                            date = inputFormat.parse(makerDate);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        item.setMakerDate(outputFormat.format(date));
                    }

                    item.setCheckerId(rs.getString(17));

                    String checkerDate = rs.getString(18);

                    if (checkerDate != null) {
                        Date date = null;
                        try {
                            date = inputFormat.parse(checkerDate);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        item.setCheckerDate(outputFormat.format(date));
                    }



                   // item.setCasDate(rs.getString(19));

                    String cDate = rs.getString(19);

                    if (cDate != null) {
                        Date date = null;
                        try {
                            date = inputFormat.parse(cDate);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        item.setCasDate(outputFormat.format(date));
                    }


                    item.setOldBondSubscriptionId(rs.getString(20));
                    item.setOldBlaNo(rs.getString(21));
                    item.setInvestmentHoldingYear(rs.getString(22));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_rep_bond_preclosure_repayment_report",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            list = (List<BondPrecloserRepayReportDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);

        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getDailyRemittanceLcReport(BondDailyRemLcDTO bondDailyRemLcDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        Double totCul = 0d;
        Double totNonCul = 0d;
        Double tot = 0d;

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_DLY_REMIT_LC.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondDailyRemLcDTO.getSchemeCode());
                stmt.setString(index++, bondDailyRemLcDTO.getDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(5);
                    if (resultSet != null) {
                        List<BondDailyRemLcDTO> list = new ArrayList<>();
                        BondDailyRemLcDTO item = null;
                        while (resultSet.next()) {
                            item = new BondDailyRemLcDTO();

                            item.setBlaNo(resultSet.getString(1));
                            item.setHolderName(resultSet.getString(2));
                            item.setSubsId(resultSet.getString(3));
                            item.setCulAmount(resultSet.getString(4));
                            item.setNonCulAmount(resultSet.getString(5));
                            item.setTotal(resultSet.getString(6));
                            totCul += Double.parseDouble(item.getCulAmount());
                            totNonCul += Double.parseDouble(item.getNonCulAmount());
                            tot += Double.parseDouble(item.getTotal());
                            list.add(item);

                        }
                        Map<String, Object> resultMap = new HashMap<>();
                        resultMap.put("list", list);
                        resultMap.put("totCul", totCul);
                        resultMap.put("totNonCul", totNonCul);
                        resultMap.put("tot", tot);

                        responseDataDTO.setResult(resultMap);
                    }

                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getSubsAccDetails(SubscriptionAccountDTO subscriptionAccountDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<SubscriptionAccountDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, subscriptionAccountDTO.getSchemeCode());
            inParams.put(fromDate, subscriptionAccountDTO.getFromDate());
            inParams.put(toDate, subscriptionAccountDTO.getToDate());
            inParams.put(p_search_value_in, (null!=subscriptionAccountDTO.getSearchValue()&&!subscriptionAccountDTO.getSearchValue().trim().isEmpty())?subscriptionAccountDTO.getSearchValue():null);
            inParams.put(p_page_no_in, subscriptionAccountDTO.getPageNo());
            inParams.put(p_page_size_in, subscriptionAccountDTO.getPageSize());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (resultSet, rowNum) -> {
                SubscriptionAccountDTO subsAccountDTO = new SubscriptionAccountDTO();
                if (subscriptionAccountDTO.getSchemeCode().isEmpty()) {
                    subsAccountDTO.setSchemeCode(resultSet.getString(1));
                    subsAccountDTO.setSchemeName(resultSet.getString(2));
                    subsAccountDTO.setSchemeStatus(resultSet.getString(3));
                    return subsAccountDTO;
                } else if (subscriptionAccountDTO.getSchemeCode() != null && subscriptionAccountDTO.getFromDate() != null && subscriptionAccountDTO.getToDate() != null) {
                    subsAccountDTO.setModeOfInvestment(resultSet.getString(1));
                    subsAccountDTO.setHolderName(resultSet.getString(2));
                    subsAccountDTO.setHolderPanNo(resultSet.getString(3));
                    subsAccountDTO.setBlaNo(resultSet.getString(4));
                    subsAccountDTO.setSubsId(resultSet.getString(5));
                    subsAccountDTO.setDateOfSubs(resultSet.getString(6));
                    subsAccountDTO.setMaturityDate(resultSet.getString(7));
                    subsAccountDTO.setModeOfHolding(resultSet.getString(8));
                    subsAccountDTO.setSubsAmount(resultSet.getString(9));
                    subsAccountDTO.setSubsStatus(resultSet.getString(10));
                    return subsAccountDTO;
                }
                throw new IllegalArgumentException("Invalid flag:");
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_SUBS_ACCT_OPENED_DET",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<SubscriptionAccountDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
                responseDataDTO.setResult(resultMap);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO getSubsAmtDetails(BondInquiryDto bondInquiryDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        Double tot = 0d;

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INQ_SP_SUB_AMT_DET.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInquiryDto.getSchemeCode());
                stmt.setString(index++, bondInquiryDto.getSubsAmtFrom());
                stmt.setString(index++, bondInquiryDto.getSubsAmtTo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(index - 2);
                    if (resultSet != null) {
                        List<BondInquiryDto> list = new ArrayList<>();
                        BondInquiryDto item = null;
                        while (resultSet.next()) {
                            item = new BondInquiryDto();

                            item.setBlaNo(resultSet.getString(1));
                            item.setHolderName(resultSet.getString(2));
                            item.setSubsId(resultSet.getString(3));
                            item.setDateOfSubs(resultSet.getString(4));
                            item.setMaturityDate(resultSet.getString(5));
                            item.setModeOfHolding(resultSet.getString(6));
                            item.setSubsAmount(resultSet.getString(7));
                            tot += Double.parseDouble(item.getSubsAmount());
                            list.add(item);

                        }
                        Map<String, Object> resultMap = new HashMap<>();
                        resultMap.put("list", list);
                        resultMap.put("totSubsAmt", tot);

                        responseDataDTO.setResult(resultMap);
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAccClosedDetails(BondInquiryDto bondInquiryDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        Double tot = 0d;

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INQ_SP_SUB_CLS_DET.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInquiryDto.getSchemeCode());
                stmt.setString(index++, bondInquiryDto.getFromDate());
                stmt.setString(index++, bondInquiryDto.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(index - 2);
                    if (resultSet != null) {
                        List<BondInquiryDto> list = new ArrayList<>();
                        BondInquiryDto item = null;
                        while (resultSet.next()) {
                            item = new BondInquiryDto();

                            item.setBlaNo(resultSet.getString(1));
                            item.setHolderName(resultSet.getString(2));
                            item.setSubsId(resultSet.getString(3));
                            item.setDateOfSubs(resultSet.getString(4));
                            item.setMaturityDate(resultSet.getString(5));
                            item.setModeOfHolding(resultSet.getString(6));
                            item.setSubsAmount(resultSet.getString(7));
                            item.setAccClosedDate(resultSet.getString(8));
                            tot += Double.parseDouble(item.getSubsAmount());
                            list.add(item);

                        }
                        Map<String, Object> resultMap = new HashMap<>();
                        resultMap.put("list", list);
                        resultMap.put("totSubsAmt", tot);

                        responseDataDTO.setResult(resultMap);
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBlaAccNumDetails(BondInquiryDto bondInquiryDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INQ_SP_BLA_DET.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInquiryDto.getSchemeCode());
                stmt.setString(index++, bondInquiryDto.getBlaNoFrom());
                stmt.setString(index++, bondInquiryDto.getBlaNoTo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(index - 2);

                    if (resultSet != null
                            && (bondInquiryDto.getBlaNoFrom() == null || bondInquiryDto.getBlaNoFrom().equalsIgnoreCase("")
                            || bondInquiryDto.getBlaNoTo() == null || bondInquiryDto.getBlaNoTo().equalsIgnoreCase(""))) {
                        List<BondInquiryDto> list = new ArrayList<>();
                        BondInquiryDto item = null;
                        while (resultSet.next()) {
                            item = new BondInquiryDto();

                            item.setBlaNo(resultSet.getString(1));
                            item.setHolderName(resultSet.getString(2));
                            item.setModeOfHolding(resultSet.getString(3));

                            list.add(item);

                        }
                        responseDataDTO.setResult(list);
                    } else if (resultSet != null
                            && (bondInquiryDto.getBlaNoFrom() != null && bondInquiryDto.getBlaNoFrom() != ""
                            && bondInquiryDto.getBlaNoTo() != null && bondInquiryDto.getBlaNoTo() != "")) {
                        List<BondInquiryDto> list = new ArrayList<>();
                        BondInquiryDto item = null;
                        while (resultSet.next()) {
                            item = new BondInquiryDto();

                            item.setBlaNo(resultSet.getString(1));
                            item.setHolderName(resultSet.getString(2));
                            item.setAddress(resultSet.getString(3));
                            item.setIsNri(resultSet.getString(4));
                            item.setIsMinor(resultSet.getString(5));
                            item.setModeOfHolding(resultSet.getString(6));
                            item.setHolderType(resultSet.getString(7));
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
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getHolderTypeDetails(BondInquiryDto bondInquiryDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INQ_SP_HLD_TYP_DET.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInquiryDto.getSchemeCode());
                stmt.setString(index++, bondInquiryDto.getHolderType());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(index - 2);
                    if (resultSet != null) {
                        List<BondInquiryDto> list = new ArrayList<>();
                        BondInquiryDto item = null;
                        while (resultSet.next()) {
                            item = new BondInquiryDto();

                            item.setBlaNo(resultSet.getString(1));
                            item.setHolderName(resultSet.getString(2));
                            item.setSubsId(resultSet.getString(3));
                            item.setGuadId(resultSet.getString(4));
                            item.setGuadName(resultSet.getString(5));
                            item.setModeOfHolding(resultSet.getString(6));
                            item.setHolderType(resultSet.getString(7));
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
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getMaturedAccDetails(BondInquiryDto bondInquiryDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        Double tot = 0d;

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INQ_SP_SUB_MAT_DET.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInquiryDto.getSchemeCode());
                stmt.setString(index++, bondInquiryDto.getFromDate());
                stmt.setString(index++, bondInquiryDto.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(index - 2);
                    if (resultSet != null) {
                        List<BondInquiryDto> list = new ArrayList<>();
                        BondInquiryDto item = null;
                        while (resultSet.next()) {
                            item = new BondInquiryDto();

                            item.setBlaNo(resultSet.getString(1));
                            item.setHolderName(resultSet.getString(2));
                            item.setSubsId(resultSet.getString(3));
                            item.setDateOfSubs(resultSet.getString(4));
                            item.setMaturityDate(resultSet.getString(5));
                            item.setModeOfHolding(resultSet.getString(6));
                            item.setSubsAmount(resultSet.getString(7));
                            tot += Double.parseDouble(item.getSubsAmount());
                            list.add(item);

                        }
                        Map<String, Object> resultMap = new HashMap<>();
                        resultMap.put("list", list);
                        resultMap.put("totSubsAmt", tot);

                        responseDataDTO.setResult(resultMap);
                    }
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getMinorAccDetails(BondInquiryDto bondInquiryDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INQ_SP_MIN_DET.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInquiryDto.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(index - 2);
                    if (resultSet != null) {
                        List<BondInquiryDto> list = new ArrayList<>();
                        BondInquiryDto item = null;
                        while (resultSet.next()) {
                            item = new BondInquiryDto();

                            item.setBlaNo(resultSet.getString(1));
                            item.setHolderName(resultSet.getString(2));
                            item.setSubsId(resultSet.getString(3));
                            item.setGuadId(resultSet.getString(4));
                            item.setGuadName(resultSet.getString(5));
                            item.setHolderType(resultSet.getString(6));
                            item.setIsMinor(resultSet.getString(7));
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
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getNriAccDetails(BondInquiryDto bondInquiryDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INQ_SP_NRI_DET.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInquiryDto.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(index - 2);
                    if (resultSet != null) {
                        List<BondInquiryDto> list = new ArrayList<>();
                        BondInquiryDto item = null;
                        while (resultSet.next()) {
                            item = new BondInquiryDto();

                            item.setBlaNo(resultSet.getString(1));
                            item.setHolderName(resultSet.getString(2));
                            item.setSubsId(resultSet.getString(3));
                            item.setModeOfHolding(resultSet.getString(4));
                            item.setHolderType(resultSet.getString(5));
                            item.setIsNri(resultSet.getString(6));
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
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getTransferInOutDetails(TransferInOutDTO transferInOutDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<TransferInOutDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        String transferType = "p_tran_type_in";
        String transferDate = "P_TRAN_FROM_DATE_IN";
        String transferToDate = "P_TRAN_TO_DATE_IN";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, transferInOutDTO.getSchemeCode());
            inParams.put(flag, transferInOutDTO.getFlag());
            inParams.put(transferType, transferInOutDTO.getTransferType());
            inParams.put(transferDate, transferInOutDTO.getFromDate());
            inParams.put(transferToDate, transferInOutDTO.getToDate());
            inParams.put(p_page_no_in, transferInOutDTO.getPageNo());
            inParams.put(p_page_size_in, transferInOutDTO.getPageSize());
            inParams.put(p_search_value_in, transferInOutDTO.getSearchValue());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(transferType, Types.VARCHAR),
                    new SqlParameter(transferDate, Types.VARCHAR),
                    new SqlParameter(transferToDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                TransferInOutDTO item = new TransferInOutDTO();
                if (transferInOutDTO.getFlag().equals("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (transferInOutDTO.getFlag().equals("D")) {
                    item.setBlaNumber(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setSubscriptionID(rs.getString(3));
                    item.setSubscriptionAmount(rs.getString(4));
                    item.setTransferInBankName(rs.getString(5));
                    item.setTransferInBranchName(rs.getString(6));
                    item.setTransferOutBankName(rs.getString(7));
                    item.setTransferOutBranchName(rs.getString(8));
                    item.setTransferDate(rs.getString(9));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_tran_rep_in_out_dtls",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<TransferInOutDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
                responseDataDTO.setResult(resultMap);
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getBondRepaymentDetails(BondRepaymentReportDto bondRepaymentReportDto)
            throws IOException {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, Object> bondRepaymentList = new HashMap<>();
        final Session session = getSession();
        Transaction transaction = null;
        boolean isTransactionActive = session.getTransaction().isActive();
        if (!isTransactionActive) {
            transaction = session.beginTransaction();
        }

        try {

            String hqlQuery = " select \n" +
                    "ROWNUM                                              AS SL_NO, \n" +
                    "CASE WHEN T1.MODEOFHOLDING IN ('MH001','MH004','MH005') THEN T1.SINGLEBONDLEDGERACCNUM WHEN T1.MODEOFHOLDING IN ('MH002','MH003') THEN T1.FIRSTJOINTHLDRBLANO ELSE NULL END                                  AS BLA_NO,\n"
                    +
                    "CASE WHEN T1.MODEOFHOLDING IN ('MH001','MH004','MH005') THEN T1.BONDHOLDERNAME WHEN T1.MODEOFHOLDING IN ('MH002','MH003') THEN T1.FIRSTJOINTHOLDERNAME ELSE NULL END                                   AS bond_holder_name,\n"
                    +
                    "T1.BONDSUBSCRIPTIONID                             AS bond_subscription_id,\n" +
                    "TO_CHAR(T1.ACCOUNTOPENDATE,'DD/MM/YYYY')          AS subscription_date,\n" +
                    "T1.SUBSCRIPTIONAMOUNT                              AS subscription_amount,\n" +
                    "T1.SUBSCRIPTIONAMOUNT                              AS Amount_of_Repayment,\n" +
                    "TO_CHAR(T1.MATURITYDATE,'DD/MM/YYYY')              AS MATURITY_DATE,\n" +
                    "TO_CHAR(T2.CLOSUREDATE,'DD/MM/YYYY')              AS DATE_OF_REPAYMENT \n" +
                    "FROM \n" +
                    "SubscriptionFlow T1\n" +
                    "INNER JOIN    RbiBondAccClosureDto T2 \n" +
                    "ON T1.BONDSUBSCRIPTIONID = T2.SUBSCRIPTIONID \n" +
                    "WHERE T1.SCHCODE =:pSchemeCode AND T1.verifiedstatus = 'Y' AND T1.ISACTIVE='Y' AND T1.isdeleted = 'N' \n"
                    +
                    "AND T2.SCHCODE = :pSchemeCode AND T2.verifiedstatus = 'Y' AND T2.ISACTIVE='Y' AND T2.isdeleted = 'N'\n"
                    +
                    "AND T1.branchId =:pBranchId \n" +
                    "AND T1.ISACCTCLOSURE = 'Y' \n" +
                    "AND T2.CLOSUREDATE BETWEEN TO_DATE(:pFromDate,'DD-MON-YYYY') AND TO_DATE(:pToDate,'DD-MON-YYYY')\n"
                    +
                    "            AND (ROWNUM LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n" +
                    "            OR T1.SINGLEBONDLEDGERACCNUM LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.FIRSTJOINTHLDRBLANO LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.BONDHOLDERNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.FIRSTJOINTHOLDERNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.BONDSUBSCRIPTIONID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.ACCOUNTOPENDATE LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.SUBSCRIPTIONAMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.SUBSCRIPTIONAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.MATURITYDATE  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T2.CLOSUREDATE LIKE '%'|| :P_SEARCH_VALUE_IN ||'%')";

            if (bondRepaymentReportDto.getSortField() != null && !bondRepaymentReportDto.getSortField().isEmpty()
                    && bondRepaymentReportDto.getSortOrder() != null
                    && !bondRepaymentReportDto.getSortOrder().isEmpty()) {
                hqlQuery += " ORDER BY " + bondRepaymentReportDto.getSortField() + " "
                        + bondRepaymentReportDto.getSortOrder();
            }

            Query<Map<String, Object>> query = session.createQuery(hqlQuery);

            query.setParameter("pFromDate", bondRepaymentReportDto.getRepaymentFromDate());
            query.setParameter("pToDate", bondRepaymentReportDto.getRepaymentToDate());
            query.setParameter("pSchemeCode", bondRepaymentReportDto.getSchemeName());
            query.setParameter("pBranchId", UserAccountUtils.getCurrentSolId());
            query.setParameter("P_SEARCH_VALUE_IN", bondRepaymentReportDto.getSearchValue());
            query.setFirstResult((bondRepaymentReportDto.getPageNo() - 1) * bondRepaymentReportDto.getPageSize());
            query.setMaxResults(bondRepaymentReportDto.getPageSize());

            query.setResultTransformer(new TrailRunDetailsResultTransformer());
            List<Map<String, Object>> results = query.getResultList();
            bondRepaymentReportDto.setData(results);
            bondRepaymentList.put("bondRepaymentList", bondRepaymentReportDto.getData());

            Query<Long> countQuery = session.createQuery("select count(1)\n" +
                    "FROM \n" +
                    "SubscriptionFlow T1\n" +
                    "INNER JOIN    RbiBondAccClosureDto T2 \n" +
                    "ON T1.BONDSUBSCRIPTIONID = T2.SUBSCRIPTIONID \n" +
                    "WHERE T1.SCHCODE =:pSchemeCode AND T1.verifiedstatus = 'Y' AND T1.ISACTIVE='Y' AND T1.isdeleted = 'N' \n"
                    +
                    "AND T2.SCHCODE = :pSchemeCode AND T2.verifiedstatus = 'Y' AND T2.ISACTIVE='Y' AND T2.isdeleted = 'N'"
                    +
                    "AND T1.branchId =:pBranchId \n" +
                    "AND T1.ISACCTCLOSURE = 'Y' \n" +
                    "AND T2.CLOSUREDATE BETWEEN TO_DATE(:pFromDate,'DD-MON-YYYY') AND TO_DATE(:pToDate,'DD-MON-YYYY')\n"
                    +
                    "            AND (ROWNUM LIKE '%'|| :P_SEARCH_VALUE_IN ||'%'\n" +
                    "            OR T1.SINGLEBONDLEDGERACCNUM LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.FIRSTJOINTHLDRBLANO LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.BONDHOLDERNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.FIRSTJOINTHOLDERNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.BONDSUBSCRIPTIONID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.ACCOUNTOPENDATE LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.SUBSCRIPTIONAMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.SUBSCRIPTIONAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T1.MATURITYDATE  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "            OR T2.CLOSUREDATE LIKE '%'|| :P_SEARCH_VALUE_IN ||'%')");

            countQuery.setParameter("pSchemeCode", bondRepaymentReportDto.getSchemeName());
            countQuery.setParameter("pFromDate", bondRepaymentReportDto.getRepaymentFromDate());
            countQuery.setParameter("pToDate", bondRepaymentReportDto.getRepaymentToDate());
            countQuery.setParameter("pBranchId", UserAccountUtils.getCurrentSolId());
            countQuery.setParameter("P_SEARCH_VALUE_IN", bondRepaymentReportDto.getSearchValue());

            Long totalCount = countQuery.getSingleResult();

            int maxPages = (int) Math.ceil((double) totalCount / bondRepaymentReportDto.getPageSize());
            bondRepaymentReportDto.setMaxPages(maxPages);

            bondRepaymentList.put("PageDetails", bondRepaymentReportDto);

            if (bondRepaymentReportDto.getIsDownload() != null
                    && bondRepaymentReportDto.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS)
                    && bondRepaymentReportDto.getDownloadType() != null) {

                if (bondRepaymentReportDto.getDownloadType().equalsIgnoreCase("excel")) {

                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(
                            rbiBondReportsUtil.excelFileGeneratorForBondRepay(results, bondRepaymentReportDto)));
                    fileResponseDto.setFileName("Bond Repayment – Report.xlsx");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                    return responseDataDTO;
                }

                if (bondRepaymentReportDto.getDownloadType().equalsIgnoreCase("pdf")) {
                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(
                            rbiBondReportsUtil.pdfFileGeneratorForBondRepay(results, bondRepaymentReportDto)));
                    fileResponseDto.setFileName("Bond Repayment – Report.pdf");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    return responseDataDTO;
                } else {
                    responseDataDTO.setMessage("Invalid File Type");
                    return responseDataDTO;
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        responseDataDTO.setResult(bondRepaymentList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getBodEodStatusInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<BodEodStatusInqDTO> list = Collections.emptyList();

        String totalRecords=null;

        String resultMsg = "SUCCESS";

        // IN PARAMS
        String statusInquiry = "p_status_inquiry_in";
        String levelId = "p_level_id_in";
        String levelName = "p_level_name_in";
        String parentLevelId = "p_parent_level_id_in";
        String parentBranchId = "p_parent_branch_id_in";
        String levelBranchId = "p_level_branch_id_in";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(flag, bodEodStatusInqDTO.getFlag());
            inParams.put(statusInquiry, bodEodStatusInqDTO.getStatusInquiry());
            inParams.put(levelId, bodEodStatusInqDTO.getLevelId());
            inParams.put(levelName, bodEodStatusInqDTO.getLevelName());
            inParams.put(parentLevelId, bodEodStatusInqDTO.getParentLevelId());
            inParams.put(parentBranchId, bodEodStatusInqDTO.getParentBranchId());
            inParams.put(levelBranchId, bodEodStatusInqDTO.getLevelBranchId());
            inParams.put(p_search_value_in, bodEodStatusInqDTO.getSearchValue());
            inParams.put(p_page_no_in, bodEodStatusInqDTO.getPageNo());
            inParams.put(p_page_size_in, bodEodStatusInqDTO.getPageSize());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(statusInquiry, Types.VARCHAR),
                    new SqlParameter(levelId, Types.VARCHAR),
                    new SqlParameter(levelName, Types.VARCHAR),
                    new SqlParameter(parentLevelId, Types.VARCHAR),
                    new SqlParameter(parentBranchId, Types.VARCHAR),
                    new SqlParameter(levelBranchId, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };


            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BodEodStatusInqDTO item = new BodEodStatusInqDTO();
                if (bodEodStatusInqDTO.getFlag().equalsIgnoreCase("L")) {
                    item.setLevelId(rs.getString(1));
                    item.setLevelName(rs.getString(2));
                    item.setParentLevelId(rs.getString(3));
                    item.setParentLevelName(rs.getString(4));
                } else if (bodEodStatusInqDTO.getFlag().equalsIgnoreCase("LB")) {
                    item.setLevelBranchId(rs.getString(1));
                    item.setBranchDesc(rs.getString(2));
                } else if (bodEodStatusInqDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBranchId(rs.getString(1));
                    item.setBranchDesc(rs.getString(2));
                    item.setLevelName(rs.getString(3));
                }
                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_rep_eod_bod_status",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<BodEodStatusInqDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            responseDataDTO.setTotal(Double.parseDouble(totalRecords));

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }





    @Override
    public ResponseDataDTO getModuleName() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareStatement(
                        "select code,code_desc from GBM_CODE_MASTER a where a.code in('001','002','003','004','005','006','007','008','009','012','ALL')");
                resultSet = stmt.executeQuery();
                List<HashMap<String, String>> resultList = new ArrayList<>();
                if (resultSet != null)
                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("subCode", resultSet.getString("code"));
                        resultMap.put("subCodeDescription", resultSet.getString("code_desc"));
                        resultList.add(resultMap);
                    }
                responseDataDTO.setResult(resultList);
                resultMsg = CommonConstants.SUCCESS_CONST;
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getInterPayPendingCollReport(BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        final Session session = getSession();

        Transaction transaction = null;
        boolean isTransactionActive = session.getTransaction().isActive();
        if (!isTransactionActive) {
            transaction = session.beginTransaction();
        }

        try {
            String hqlQuery = "SELECT DISTINCT\n" +
                    "        T1.BRANCHID   AS BRANCH_ID,\n" +
                    "        CASE WHEN T2.MODEOFHOLDING IN ('MH001','MH004','MH005')  THEN T2.SINGLEBONDLEDGERACCNUM \n"
                    +
                    "        WHEN T2.MODEOFHOLDING IN('MH002','MH003')  THEN T2.FIRSTJOINTHLDRBLANO END   AS bond_ledger_account_number,\n"
                    +
                    "        CASE WHEN T2.MODEOFHOLDING IN ('MH001','MH004','MH005') THEN T2.BONDHOLDERNAME\n" +
                    "        WHEN T2.MODEOFHOLDING IN('MH002','MH003') THEN T2.FIRSTJOINTHOLDERNAME  END AS CUSTOMER_NAME,\n"
                    +
                    "        T1.BONDSUBSCRIPTIONID     AS BOND_SUBSCRIPTION_ID,\n" +
                    "        T2.SUBSCRIPTIONAMOUNT      AS  SUBSCRIPTION_AMOUNT,\n" +
                    "        T2.ACCOUNTOPENDATE        AS SUBCRIPTION_DATE,\n" +
                    "        CASE WHEN T2.MODEOFINTERESTPAYMENT = 'MP001' THEN 'CREDIT ACCOUNT' \n" +
                    "        WHEN T2.MODEOFINTERESTPAYMENT = 'MP002' THEN 'DD/PO' \n" +
                    "        WHEN T2.MODEOFINTERESTPAYMENT = 'MP003' THEN 'NEFT' END AS  INTEREST_PAYMENT_MODE,\n" +
                    "        T1.INTERESTAMT      AS GROSS_INTEREST_AMOUNT,\n" +
                    "        T1.INELIGIBLEREASONS     AS REASON \n" +
                    "        FROM BondIntInEligibleAcc T1 INNER JOIN \n" +
                    "        SubscriptionFlow T2 \n" +
                    "        ON (T1.BONDSUBSCRIPTIONID=T2.BONDSUBSCRIPTIONID) \n" +
                    "        INNER JOIN \n" +
                    "        InterestPaymentDetails T4\n" +
                    "        ON T2.BONDSUBSCRIPTIONID = T4.BONDSUBSCRIPTIONID\n" +
                    "        where  T1.BRANCHID= :pSolId\n" +
                    "        AND T2.SCHCODE=:pSchemeCode\n" +
                    "        AND TO_CHAR(T1.CREATEDDATE,'MM')= CASE WHEN  :pInterestPaidIn= 'JAN-JUN' THEN '07' WHEN :pInterestPaidIn= 'JUL-DEC' THEN '01' END \n"
                    +
                    "        AND TO_CHAR(T1.CREATEDDATE,'YYYY')= CASE WHEN :pInterestPaidIn= 'JAN-JUN' THEN SUBSTR(:pfinacialYear,1,4) WHEN :pInterestPaidIn= 'JUL-DEC' THEN SUBSTR(:pfinacialYear,6,4) END\n"
                    +
                    "        AND T4.ISPAYMENTDONE='N'\n" +
                    "          AND T1.BRANCHID LIKE '%'||:pSearchValue||'%'\n" +
                    "        OR T2.SINGLEBONDLEDGERACCNUM LIKE '%'||:pSearchValue||'%'\n" +
                    "        OR T2.FIRSTJOINTHLDRBLANO LIKE '%'||:pSearchValue||'%'\n" +
                    "        OR T2.BONDHOLDERNAME LIKE '%'||:pSearchValue||'%'\n" +
                    "      OR T2.FIRSTJOINTHOLDERNAME LIKE '%'||:pSearchValue||'%'\n" +
                    "      OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n" +
                    "      OR T2.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n" +
                    "      OR TO_CHAR(T2.ACCOUNTOPENDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n" +
                    "    OR T2.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n" +
                    "        OR T1.INTERESTAMT  LIKE '%'||:pSearchValue||'%'\n" +
                    "       OR T1.INELIGIBLEREASONS LIKE '%'|| :pSearchValue ||'%'";
            if (bondInterestPayPendingCollDTO.getSortField() != null
                    && !bondInterestPayPendingCollDTO.getSortField().isEmpty()
                    && bondInterestPayPendingCollDTO.getSortOrder() != null
                    && !bondInterestPayPendingCollDTO.getSortOrder().isEmpty()) {
                hqlQuery += " ORDER BY " + bondInterestPayPendingCollDTO.getSortField() + " "
                        + bondInterestPayPendingCollDTO.getSortOrder();
            }

            Query<Map<String, Object>> query = session.createQuery(hqlQuery);

            query.setParameter("pSolId", bondInterestPayPendingCollDTO.getSolId());
            query.setParameter("pSchemeCode", bondInterestPayPendingCollDTO.getSchemeCode());
            query.setParameter("pInterestPaidIn", bondInterestPayPendingCollDTO.getInterestPaymentFor());
            query.setParameter("pfinacialYear", bondInterestPayPendingCollDTO.getFinancialYear());
            query.setParameter("pSearchValue", bondInterestPayPendingCollDTO.getSearchValue());
            query.setFirstResult(
                    (bondInterestPayPendingCollDTO.getPageNo() - 1) * bondInterestPayPendingCollDTO.getPageSize());
            query.setMaxResults(bondInterestPayPendingCollDTO.getPageSize());

            query.setResultTransformer(new TrailRunDetailsResultTransformer());
            List<Map<String, Object>> results = query.getResultList();
            bondInterestPayPendingCollDTO.setData(results);

            Query<Long> countQuery = session.createQuery("SELECT COUNT(1) \n" +
                    "        FROM BondIntInEligibleAcc T1 INNER JOIN \n" +
                    "        SubscriptionFlow T2 \n" +
                    "        ON (T1.BONDSUBSCRIPTIONID=T2.BONDSUBSCRIPTIONID) \n" +
                    "        INNER JOIN \n" +
                    "        InterestPaymentDetails T4\n" +
                    "        ON T2.BONDSUBSCRIPTIONID = T4.BONDSUBSCRIPTIONID\n" +
                    "        where  T1.BRANCHID= :pSolId\n" +
                    "        AND T2.SCHCODE=:pSchemeCode\n" +
                    "        AND TO_CHAR(T1.CREATEDDATE,'MM')= CASE WHEN  :pInterestPaidIn= 'JAN-JUN' THEN '07' WHEN :pInterestPaidIn= 'JUL-DEC' THEN '01' END \n"
                    +
                    "        AND TO_CHAR(T1.CREATEDDATE,'YYYY')= CASE WHEN :pInterestPaidIn= 'JAN-JUN' THEN SUBSTR(:pfinacialYear,1,4) WHEN :pInterestPaidIn= 'JUL-DEC' THEN SUBSTR(:pfinacialYear,6,4) END\n"
                    +
                    "        AND T4.ISPAYMENTDONE='N'\n" +
                    "          AND T1.BRANCHID LIKE '%'||:pSearchValue||'%'\n" +
                    "        OR T2.SINGLEBONDLEDGERACCNUM LIKE '%'||:pSearchValue||'%'\n" +
                    "        OR T2.FIRSTJOINTHLDRBLANO LIKE '%'||:pSearchValue||'%'\n" +
                    "        OR T2.BONDHOLDERNAME LIKE '%'||:pSearchValue||'%'\n" +
                    "      OR T2.FIRSTJOINTHOLDERNAME LIKE '%'||:pSearchValue||'%'\n" +
                    "      OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n" +
                    "      OR T2.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n" +
                    "      OR TO_CHAR(T2.ACCOUNTOPENDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n" +
                    "    OR T2.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n" +
                    "        OR T1.INTERESTAMT  LIKE '%'||:pSearchValue||'%'\n" +
                    "       OR T1.INELIGIBLEREASONS LIKE '%'|| :pSearchValue ||'%'");
            countQuery.setParameter("pSolId", bondInterestPayPendingCollDTO.getSolId());
            countQuery.setParameter("pSchemeCode", bondInterestPayPendingCollDTO.getSchemeCode());
            countQuery.setParameter("pInterestPaidIn", bondInterestPayPendingCollDTO.getInterestPaymentFor());
            countQuery.setParameter("pfinacialYear", bondInterestPayPendingCollDTO.getFinancialYear());
            countQuery.setParameter("pSearchValue", bondInterestPayPendingCollDTO.getSearchValue());

            Long totalCount = countQuery.getSingleResult();

            int maxPages = (int) Math.ceil((double) totalCount / bondInterestPayPendingCollDTO.getPageSize());
            bondInterestPayPendingCollDTO.setMaxPages(maxPages);

            // bondInterestCalculatedList.put("PageDetails", bondInterestCalculatedDTO);

            if (bondInterestPayPendingCollDTO.getIsDownload() != null
                    && bondInterestPayPendingCollDTO.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS)
                    && bondInterestPayPendingCollDTO.getDownloadType() != null) {

                if (bondInterestPayPendingCollDTO.getDownloadType().equalsIgnoreCase("excel")) {

                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil
                            .excelFileGeneratorForBondInterestPendingCollReport(bondInterestPayPendingCollDTO)));
                    fileResponseDto.setFileName("Bond Interest Payment Pending Report – Collecting Branch.xlsx");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                    return responseDataDTO;
                }

                if (bondInterestPayPendingCollDTO.getDownloadType().equalsIgnoreCase("pdf")) {
                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondReportsUtil
                            .pdfFileGeneratorForBondInterestPendingCollReport(bondInterestPayPendingCollDTO)));
                    fileResponseDto.setFileName("Bond Interest Payment Pending Report – Collecting Branch.pdf");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    return responseDataDTO;
                } else {
                    responseDataDTO.setMessage("Invalid File Type");
                    return responseDataDTO;
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        responseDataDTO.setResult(bondInterestPayPendingCollDTO);
        return responseDataDTO;
    }

    // @Override
    public ResponseDataDTO getStatusEnquiry(RbiBondMetaDataRequestDto metaDataRequestDto) {
        // Connection connection = null;
        // PreparedStatement stmt = null;
        // ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        // String resultMsg = "";
        //
        // ResultSet resultSet = null;
        // HashMap<String, String> resultMap = null;
        // try {
        // connection = connectionFactory.getConnection();
        // if (connection != null) {
        // int index = 1;
        // stmt = connection.prepareStatement("SELECT sub_code as subCode,sub_code_desc
        // as subCodeDescription FROM GBM_SUB_CODE_MASTER WHERE CODE = (SELECT code FROM
        // GBM_CODE_MASTER WHERE CODE_NAME = ? and is_metadata='Y') AND IS_DELETED =
        // 'N'");
        // stmt.setString(index, metaDataRequestDto.getReferenceId());
        // resultSet = stmt.executeQuery();
        // if (resultSet != null) {
        // List<HashMap<String, String>> resultList = new ArrayList<>();
        //
        // while (resultSet.next()) {
        // resultMap = new HashMap<>();
        // resultMap.put("subCode", resultSet.getString("subCode"));
        // resultMap.put("subCodeDescription",
        // resultSet.getString("subCodeDescription"));
        // resultList.add(resultMap);
        // }
        // responseDataDTO.setResult(resultList);
        // resultMsg = CommonConstants.SUCCESS_CONST;
        // } else {
        // resultMsg = CommonConstants.NDF;
        // }
        // } else {
        // log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
        // resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        // }
        //
        // } catch (Exception e) {
        // log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        // resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        // } finally {
        // connectionFactory.closeConnection(connection, stmt);
        // }
        //
        // responseDataDTO.setMessage(resultMsg);
        return null;
    }

    @Override
    public ResponseDataDTO getStatementBrokerageDetails(StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<StatementOfBrokeragePaidDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, statementOfBrokeragePaidDTO.getSchemeCode());
            inParams.put(flag, statementOfBrokeragePaidDTO.getFlag());
            inParams.put(fromDate, statementOfBrokeragePaidDTO.getFromDate());
            inParams.put(toDate, statementOfBrokeragePaidDTO.getToDate());
            inParams.put(status, statementOfBrokeragePaidDTO.getStatus());
            inParams.put(reportType, statementOfBrokeragePaidDTO.getReportType());
            inParams.put(p_page_no_in, statementOfBrokeragePaidDTO.getPageNo());
            inParams.put(p_page_size_in, statementOfBrokeragePaidDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=statementOfBrokeragePaidDTO.getSearchValue()&&!statementOfBrokeragePaidDTO.getSearchValue().trim().isEmpty())?statementOfBrokeragePaidDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(status, Types.VARCHAR),
                    new SqlParameter(reportType, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                StatementOfBrokeragePaidDTO item = new StatementOfBrokeragePaidDTO();
                if (statementOfBrokeragePaidDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (statementOfBrokeragePaidDTO.getFlag() != null && statementOfBrokeragePaidDTO.getFlag().equalsIgnoreCase("D") && statementOfBrokeragePaidDTO.getReportType().equalsIgnoreCase("IW")) {
                    item.setBlaNumber(rs.getString(1));
                    item.setCustomerName(rs.getString(2));
                    item.setSubscriptionID(rs.getString(3));
                    item.setNonCumulative(rs.getString(4));
                    item.setCumulative(rs.getString(5));
                    item.setCustomerStatus(rs.getString(6));
                    item.setInvoiceNumber(rs.getString(7));
                    item.setInvoiceDate(rs.getString(8));
                    item.setBrokerID(rs.getString(9));
                    item.setBrokerName(rs.getString(10));
                    item.setBrokerPAN(rs.getString(11));
                    item.setBrokerEmailId(rs.getString(12));
                    item.setGrossBrokerage(rs.getString(13));
                    item.setGST(rs.getString(14));
                    item.setNetBrokeragePaid(rs.getString(15));
                    item.setBrokeragePaidDate(rs.getString(16));
                    item.setBrokerageTransactionInitiatedDate(rs.getString(17));
                    item.setReasonForUnpaid(rs.getString(18));
                } else if (statementOfBrokeragePaidDTO.getReportType().equalsIgnoreCase("BW")) {
                    item.setInvoiceNumber(rs.getString(1));
                    item.setInvoiceDate(rs.getString(2));
                    item.setBrokerName(rs.getString(3));
                    item.setBrokerCode(rs.getString(4));
                    item.setBrokerPAN(rs.getString(5));
                    item.setBrokerEmailId(rs.getString(6));
                    item.setGrossBrokerage(rs.getString(7));
                    item.setGstPercent(rs.getString(8));
                    item.setGstAmount(rs.getString(9));
                    item.setNetBrokeragePaid(rs.getString(10));
                    item.setBrokeragePaidDate(rs.getString(11));
                    item.setCustomerStatus(rs.getString(12));
                    item.setRemarks(rs.getString(13));
                }
                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_stmt_brk_paid_unpaid",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<StatementOfBrokeragePaidDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
                responseDataDTO.setResult(resultMap);
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getReportTorchBla(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";

        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareStatement(" SELECT  BOND_LEDGER_ACCOUNT_NUMBER ,\n" +
                        "MODE_OF_HOLDING,\n" +
                        "BOND_HLD_FIRST_NAME|| ' ' ||BOND_HLD_MID_NAME|| ' ' ||BOND_HLD_LAST_NAME as Holder_Name\n" +
                        "FROM  GBM_RBI_BOND_LEDGER_ACC_MASTER\n" +
                        "WHERE BRANCH_ID = ? \n" +
                        "and verified_status = 'Y' AND is_deleted = 'N' AND IS_ACTIVE = 'Y'");
                stmt.setString(index, UserAccountUtils.getCurrentSolId());
                resultSet = stmt.executeQuery();
                if (resultSet != null) {
                    List<HashMap<String, String>> resultList = new ArrayList<>();

                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("blaNo", resultSet.getString("BOND_LEDGER_ACCOUNT_NUMBER"));
                        resultMap.put("bondHolderName", resultSet.getString("Holder_Name"));
                        resultMap.put("modeOfHolding", resultSet.getString("MODE_OF_HOLDING"));
                        resultList.add(resultMap);
                    }
                    responseDataDTO.setResult(resultList);
                    resultMsg = CommonConstants.SUCCESS_CONST;
                } else {
                    resultMsg = CommonConstants.NDF;
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getInterestRunCreditReport(BondInterestRunCreditDTO bondInterestRunCreditDTO) {
        if (bondInterestRunCreditDTO.getIsTorch().equalsIgnoreCase("Y")
                && bondInterestRunCreditDTO.getBranchId() == null && bondInterestRunCreditDTO.getBlaNo() == null
                && bondInterestRunCreditDTO.getSubscriptionId() == null) {

            Connection connection = null;
            PreparedStatement stmt = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "";

            ResultSet resultSet = null;
            HashMap<String, String> resultMap = null;
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareStatement(
                            "SELECT DISTINCT T1.BRANCH_ID AS BRANCH_ID, T2.sol_desc as branch_desc\n" +
                                    "            FROM gbm_rbi_bond_hirchy_lvl_master t1 INNER JOIN gbm_bank_branches t2\n"
                                    +
                                    "            ON t1.branch_id = t2.sol_id \n" +
                                    "            WHERE t1.SCH_CODE = ? AND t1.verified_status = 'Y' \n" +
                                    "            AND t1.is_deleted = 'N' AND t2.ver_flg = '1' AND t2.del_flg = '0'");
                    stmt.setString(index, bondInterestRunCreditDTO.getSchemeCode());
                    resultSet = stmt.executeQuery();
                    if (resultSet != null) {
                        List<HashMap<String, String>> resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("branchId", resultSet.getString("BRANCH_ID"));
                            resultMap.put("branchName", resultSet.getString("BRANCH_DESC"));
                            resultList.add(resultMap);
                        }
                        responseDataDTO.setResult(resultList);
                        resultMsg = CommonConstants.SUCCESS_CONST;
                    } else {
                        resultMsg = CommonConstants.NDF;
                    }
                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }

            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;

        }
        if (bondInterestRunCreditDTO.getIsTorch().equalsIgnoreCase("Y")
                && bondInterestRunCreditDTO.getBranchId() != null && bondInterestRunCreditDTO.getBlaNo() == null
                && bondInterestRunCreditDTO.getSubscriptionId() == null) {

            Connection connection = null;
            PreparedStatement stmt = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "";

            ResultSet resultSet = null;
            HashMap<String, String> resultMap = null;
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareStatement(" SELECT  bond_ledger_account_number AS bla_no,\n" +
                            "            bond_hld_first_name||' '||bond_hld_mid_name||' '||bond_hld_last_name   AS name_of_the_bond_holder,\n"
                            +
                            "            CASE WHEN mode_of_holding = 'MH001' THEN 'SINGLE' \n" +
                            "            WHEN mode_of_holding = 'MH002' THEN 'Joint – Jointly'\n" +
                            "            WHEN mode_of_holding =  'MH003' THEN 'Joint- Anyone or Survivor'\n" +
                            "            WHEN mode_of_holding = 'MH004' THEN 'HUF'\n" +
                            "            WHEN mode_of_holding = 'MH005' THEN 'Legal Guardian' END AS mode_of_holding\n"
                            +
                            "            FROM gbm_rbi_bond_ledger_acc_master\n" +
                            "            WHERE  sch_code = ? AND \n" +
                            "            verified_status = 'Y' AND is_active = 'Y' AND is_deleted ='N'\n" +
                            "            AND (joint_holder_type = '1' OR joint_holder_type ='S')\n" +
                            "            and  BRANCH_ID  = ?");
                    stmt.setString(index++, bondInterestRunCreditDTO.getSchemeCode());
                    stmt.setString(index, bondInterestRunCreditDTO.getBranchId());
                    resultSet = stmt.executeQuery();
                    if (resultSet != null) {
                        List<HashMap<String, String>> resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("blaNo", resultSet.getString("BLA_NO"));
                            resultMap.put("bondHolderName", resultSet.getString("NAME_OF_THE_BOND_HOLDER"));
                            resultMap.put("modeOfHolding", resultSet.getString("MODE_OF_HOLDING"));
                            resultList.add(resultMap);
                        }
                        responseDataDTO.setResult(resultList);
                        resultMsg = CommonConstants.SUCCESS_CONST;
                    } else {
                        resultMsg = CommonConstants.NDF;
                    }
                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }

            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;

        }
        if (bondInterestRunCreditDTO.getIsTorch().equalsIgnoreCase("Y")
                && bondInterestRunCreditDTO.getBranchId() != null && bondInterestRunCreditDTO.getBlaNo() != null
                && bondInterestRunCreditDTO.getSubscriptionId() == null) {

            Connection connection = null;
            PreparedStatement stmt = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "";

            ResultSet resultSet = null;
            HashMap<String, String> resultMap = null;
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareStatement("SELECT\n" +
                            "BOND_SUBSCRIPTION_ID  AS BOND_SUBSCRIPTION_ID ,\n" +
                            "SUBSCRIPTION_AMOUNT AS SUBSCRIPTION_AMOUNT,\n" +
                            "ACCOUNT_OPEN_DATE AS SUBSCRIPTION_DATE\n" +
                            "FROM GBM_RBI_BOND_SUBSCRIPTION_FLOW\n" +
                            "WHERE  sch_code = ? AND \n" +
                            "verified_status = 'Y' AND is_active = 'Y' AND is_deleted ='N'\n" +
                            "and  BRANCH_ID  = ? \n" +
                            "AND ((single_bond_ledger_acc_num = ? or first_joint_hldr_bla_no = ?))\n" +
                            "");
                    stmt.setString(index++, bondInterestRunCreditDTO.getSchemeCode());
                    stmt.setString(index++, bondInterestRunCreditDTO.getBranchId());
                    stmt.setString(index++, bondInterestRunCreditDTO.getBlaNo());
                    stmt.setString(index, bondInterestRunCreditDTO.getBlaNo());
                    resultSet = stmt.executeQuery();
                    if (resultSet != null) {
                        List<HashMap<String, String>> resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("subscriptionId", resultSet.getString("BOND_SUBSCRIPTION_ID"));
                            resultMap.put("subscriptionAmt", resultSet.getString("SUBSCRIPTION_AMOUNT"));
                            resultMap.put("subscriptionDate", resultSet.getString("SUBSCRIPTION_DATE"));
                            resultList.add(resultMap);
                        }
                        responseDataDTO.setResult(resultList);
                        resultMsg = CommonConstants.SUCCESS_CONST;
                    } else {
                        resultMsg = CommonConstants.NDF;
                    }
                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }

            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;

        }
        if (bondInterestRunCreditDTO.getIsTorch().equalsIgnoreCase("N")
                && bondInterestRunCreditDTO.getBranchId() == null && bondInterestRunCreditDTO.getBlaNo() == null
                && bondInterestRunCreditDTO.getSubscriptionId() == null
                && bondInterestRunCreditDTO.getInterestPayFrom() != null
                && bondInterestRunCreditDTO.getInterestPayTo() != null) {

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();

            final Session session = getSession();

            Transaction transaction = null;
            boolean isTransactionActive = session.getTransaction().isActive();
            if (!isTransactionActive) {
                transaction = session.beginTransaction();
            }

            try {

                String hqlQuery = "SELECT\n" +
                        "                T1.BONDLEDGERACCOUNTNUMBER                               AS   BLA_NO,\n" +
                        "                T1.SUBSCRIPTIONAMOUNT                  AS   SUBSCRIPTION_AMOUNT,           \n"
                        +
                        "                T1.NETINTERESTAMOUNT                  AS   NET_INTEREST_AMOUNT ,\n" +
                        "                T1.INTERESTAMOUNT                         as   Gross_Interest_Amount,\n" +
                        "                TO_CHAR(T1.INTERESTPAIDDATE,'DD/MM/YYYY')                   AS   INTREST_DATE,\n"
                        +
                        "                T1.BONDSUBSCRIPTIONID                 AS   BOND_SUBSCRIPTION_ID,\n" +
                        "                T1.CUSTOMERNAME                        AS   CUSTOMER_NAME,\n" +
                        "                TO_CHAR(T1.INTERESTPERIODFROM,'DD/MM/YYYY')  AS   INTEREST_PERIOD_FROM,\n" +
                        "                TO_CHAR(T1.INTERESTPERIODTO,'DD/MM/YYYY')    AS   INTERST_PERIOD_TO,\n" +
                        "                T1.CREDITACCOUNTNUMBER                AS   CREDIT_ACCOUNT_NUMBER,\n" +
                        "                T1.GBMTRANID                          AS   GBS_TRAN_ID,\n" +
                        "                 TO_CHAR(T1.SUBSCRIPTIONDATE,'DD/MM/YYYY')  AS   SUBSCRIPTION_DATE,\n" +
                        "                T1.BRANCHID                            AS   BRANCH_ID,\n" +
                        "                T1.MODEOFINTERESTPAYMENT             as   MODE_OF_INTEREST_PAYMENT,\n" +
                        "                sum(nvl(T1.ARREARTDSAMOUNT,0)+nvl(T1.CURRENTTDSAMOUNT,0)) as TDS_AMT,            \n"
                        +
                        "                T2.FINACLETRANID                      as   CBS_Tran_Id\n" +
                        "                FROM\n" +
                        "                InterestPaymentDetails T1\n" +
                        "                INNER JOIN \n" +
                        "                TransactionTableDetails T2\n" +
                        "                ON    \n" +
                        "                T1.GBMTRANID = T2.GBMTRANID \n" +
                        "                WHERE (T1.VERIFIEDSTATUS = 'Y' AND T1.ISDELETED='N' AND T1.SCHCODE = :pSchemeCode) \n"
                        +
                        "                AND T2.VERIFIEDSTATUS = 'Y' AND T2.ISDELETED='N' AND \n" +
                        "                T2.SCHSUBCODE = :pSchemeCode \n" +
                        "                AND  (T1.INTERESTPAIDDATE BETWEEN TO_DATE(:pInterestFrom,'DD/MM/YYYY') and TO_DATE(:pInterestTo,'DD/MM/YYYY'))\n"
                        +
                        "                AND T2.TRANLEG='D' AND  T1.BRANCHID LIKE '%'|| :pSearchValue ||'%'\n" +
                        "                                    OR T1.BONDLEDGERACCOUNTNUMBER LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                    OR T1.CUSTOMERNAME LIKE '%'||:pSearchValue||'%'\n" +
                        "                                    OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n" +
                        "                                    OR T1.SUBSCRIPTIONDATE LIKE '%'||:pSearchValue||'%'\n" +
                        "                                   OR T1.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n" +
                        "                                   OR TO_CHAR(T1.INTERESTPAIDDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                    OR T1.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                    OR T1.CREDITACCOUNTNUMBER  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                     OR T1.INTERESTAMOUNT  LIKE '%'||:pSearchValue||'%'\n" +
                        "                                   OR T1.NETINTERESTAMOUNT LIKE '%'|| :pSearchValue ||'%'\n" +
                        "                                   OR T1.GBMTRANID LIKE '%'|| :pSearchValue ||'%'\n" +
                        "                                   OR T2.FINACLETRANID LIKE '%'|| :pSearchValue ||'%'\n" +
                        "                GROUP BY  T1.BONDLEDGERACCOUNTNUMBER,                               \n" +
                        "                T1.SUBSCRIPTIONAMOUNT,                        \n" +
                        "                T1.NETINTERESTAMOUNT,  \n" +
                        "                T1.INTERESTAMOUNT,\n" +
                        "                T1.INTERESTPAIDDATE,                               \n" +
                        "                T1.BONDSUBSCRIPTIONID,              \n" +
                        "                T1.CUSTOMERNAME,                      \n" +
                        "                T1.INTERESTPERIODFROM,               \n" +
                        "                T1.INTERESTPERIODTO,                   \n" +
                        "                T1.CREDITACCOUNTNUMBER,          \n" +
                        "                T1.GBMTRANID,               \n" +
                        "                T1.SUBSCRIPTIONDATE,        \n" +
                        "                T1.SUBSCRIPTIONAMOUNT,  \n" +
                        "                T1.BRANCHID ,\n" +
                        "                T1.MODEOFINTERESTPAYMENT,\n" +
                        "                T2.FINACLETRANID\n" +
                        "                ";

                if (bondInterestRunCreditDTO.getSortField() != null
                        && !bondInterestRunCreditDTO.getSortField().isEmpty()
                        && bondInterestRunCreditDTO.getSortOrder() != null
                        && !bondInterestRunCreditDTO.getSortOrder().isEmpty()) {
                    hqlQuery += " ORDER BY " + bondInterestRunCreditDTO.getSortField() + " "
                            + bondInterestRunCreditDTO.getSortOrder();
                }

                Query<Map<String, Object>> query = session.createQuery(hqlQuery);
                query.setParameter("pSchemeCode", bondInterestRunCreditDTO.getSchemeCode());
                query.setParameter("pInterestFrom", bondInterestRunCreditDTO.getInterestPayFrom());
                query.setParameter("pInterestTo", bondInterestRunCreditDTO.getInterestPayTo());
                query.setParameter("pSearchValue", bondInterestRunCreditDTO.getSearchValue());
                query.setFirstResult(
                        (bondInterestRunCreditDTO.getPageNo() - 1) * bondInterestRunCreditDTO.getPageSize());
                query.setMaxResults(bondInterestRunCreditDTO.getPageSize());

                query.setResultTransformer(new TrailRunDetailsResultTransformer());
                List<Map<String, Object>> results = query.getResultList();
                bondInterestRunCreditDTO.setData(results);

                Query<Long> countQuery = session.createQuery("SELECT \n" +
                        "    COUNT(1) \n" +
                        "    FROM\n" +
                        "    InterestPaymentDetails T1\n" +
                        "    INNER JOIN \n" +
                        "    TransactionTableDetails T2\n" +
                        "    ON    \n" +
                        "    T1.GBMTRANID = T2.GBMTRANID \n" +
                        "    WHERE (T1.VERIFIEDSTATUS = 'Y' AND T1.ISDELETED='N' AND T1.SCHCODE = :pSchemeCode) \n" +
                        "    AND T2.VERIFIEDSTATUS = 'Y' AND T2.ISDELETED='N' AND \n" +
                        "    T2.SCHSUBCODE = :pSchemeCode \n" +
                        "    AND  (T1.INTERESTPAIDDATE BETWEEN TO_DATE(:pInterestFrom,'DD/MM/YYYY') and TO_DATE(:pInterestTo,'DD/MM/YYYY'))\n"
                        +
                        "    AND T2.TRANLEG='D'\n" +
                        "     AND T1.BRANCHID LIKE '%'|| :pSearchValue ||'%'\n" +
                        "                                                                   OR T1.BONDLEDGERACCOUNTNUMBER LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                   OR T1.CUSTOMERNAME LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.SUBSCRIPTIONDATE LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                   OR T1.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                   OR TO_CHAR(T1.INTERESTPAIDDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.CREDITACCOUNTNUMBER  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                     OR T1.INTERESTAMOUNT  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                   OR T1.NETINTERESTAMOUNT LIKE '%'|| :pSearchValue ||'%'\n"
                        +
                        "                                                                   OR T1.GBMTRANID LIKE '%'|| :pSearchValue ||'%'\n"
                        +
                        "                                                                   OR T2.FINACLETRANID LIKE '%'|| :pSearchValue ||'%'");

                countQuery.setParameter("pSchemeCode", bondInterestRunCreditDTO.getSchemeCode());
                countQuery.setParameter("pInterestFrom", bondInterestRunCreditDTO.getInterestPayFrom());
                countQuery.setParameter("pInterestTo", bondInterestRunCreditDTO.getInterestPayTo());
                countQuery.setParameter("pSearchValue", bondInterestRunCreditDTO.getSearchValue());
                Long totalCount = countQuery.getSingleResult();

                int maxPages = (int) Math.ceil((double) totalCount / bondInterestRunCreditDTO.getPageSize());
                bondInterestRunCreditDTO.setMaxPages(maxPages);

                if (bondInterestRunCreditDTO.getIsDownload() != null
                        && bondInterestRunCreditDTO.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS)
                        && bondInterestRunCreditDTO.getDownloadType() != null) {

                    if (bondInterestRunCreditDTO.getDownloadType().equalsIgnoreCase("excel")) {

                        FileResponseDto fileResponseDto = new FileResponseDto();
                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(
                                rbiBondReportsUtil.excelFileGeneratorForInterestRunReport(bondInterestRunCreditDTO)));
                        fileResponseDto.setFileName("Bond Actual Interest Run Credited Report – centralized.xlsx");
                        fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                        responseDataDTO.setResult(fileResponseDto);
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                        return responseDataDTO;
                    }

                    if (bondInterestRunCreditDTO.getDownloadType().equalsIgnoreCase("pdf")) {
                        FileResponseDto fileResponseDto = new FileResponseDto();
                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(
                                rbiBondReportsUtil.pdfFileGeneratorForInterestRunReport(bondInterestRunCreditDTO)));
                        fileResponseDto.setFileName("Bond Actual Interest Run Credited Report – centralized.pdf");
                        fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                        responseDataDTO.setResult(fileResponseDto);
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                        return responseDataDTO;
                    } else {
                        responseDataDTO.setMessage("Invalid File Type");
                        return responseDataDTO;
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                session.close();
            }

            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
            responseDataDTO.setResult(bondInterestRunCreditDTO);
            return responseDataDTO;

        }
        if (bondInterestRunCreditDTO.getIsTorch().equalsIgnoreCase("N")
                && bondInterestRunCreditDTO.getBranchId() != null && bondInterestRunCreditDTO.getBlaNo() != null
                && bondInterestRunCreditDTO.getSubscriptionId() != null
                && bondInterestRunCreditDTO.getInterestPayFrom() != null
                && bondInterestRunCreditDTO.getInterestPayTo() != null) {

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();

            final Session session = getSession();

            Transaction transaction = null;
            boolean isTransactionActive = session.getTransaction().isActive();
            if (!isTransactionActive) {
                transaction = session.beginTransaction();
            }

            try {

                String hqlQuery = "SELECT \n" +
                        "                T1.BONDLEDGERACCOUNTNUMBER                               AS   BLA_NO,\n" +
                        "                T1.NETINTERESTAMOUNT                  AS   NET_INTEREST_AMOUNT ,\n" +
                        "                T1.INTERESTAMOUNT                         as   Gross_Interest_Amount,\n" +
                        "                TO_CHAR(T1.INTERESTPAIDDATE,'DD/MM/YYYY')                   AS   INTREST_DATE,\n"
                        +
                        "                T1.BONDSUBSCRIPTIONID                 AS   BOND_SUBSCRIPTION_ID,\n" +
                        "                T1.CUSTOMERNAME                        AS   CUSTOMER_NAME,\n" +
                        "               TO_CHAR(T1.INTERESTPERIODFROM,'DD/MM/YYYY')  AS   INTEREST_PERIOD_FROM,\n" +
                        "               TO_CHAR(T1.INTERESTPERIODTO,'DD/MM/YYYY')                 AS   INTERST_PERIOD_TO,\n"
                        +
                        "                T1.CREDITACCOUNTNUMBER                AS   CREDIT_ACCOUNT_NUMBER,\n" +
                        "                T1.GBMTRANID                          AS   GBM_TRAN_ID,\n" +
                        "                 TO_CHAR(T1.SUBSCRIPTIONDATE,'DD/MM/YYYY')  AS   SUBSCRIPTION_DATE,\n" +
                        "                T1.SUBSCRIPTIONAMOUNT                  AS   SUBSCRIPTION_AMOUNT,\n" +
                        "                T1.BRANCHID                            AS   BRANCH_ID,\n" +
                        "                T1.MODEOFINTERESTPAYMENT             as   MODE_OF_INTEREST_PAYMENT,\n" +
                        "                sum(nvl(T1.ARREARTDSAMOUNT,0)+nvl(T1.CURRENTTDSAMOUNT,0)) as TDS_AMT,            \n"
                        +
                        "                T2.FINACLETRANID                      as   CBS_Tran_Id\n" +
                        "                FROM\n" +
                        "                InterestPaymentDetails T1\n" +
                        "                INNER JOIN \n" +
                        "                TransactionTableDetails T2\n" +
                        "                ON    \n" +
                        "                T1.GBMTRANID = T2.GBMTRANID \n" +
                        "                WHERE (T1.VERIFIEDSTATUS = 'Y' AND T1.ISDELETED='N' AND T1.SCHCODE = :pSchemeCode) \n"
                        +
                        "                AND T2.VERIFIEDSTATUS = 'Y' AND T2.ISDELETED='N' AND \n" +
                        "                T2.SCHSUBCODE = :pSchemeCode \n" +
                        "                and  T1.BRANCHID  = nvl(:pBranchId, T1.BRANCHID)\n" +
                        "                AND T1.BONDLEDGERACCOUNTNUMBER = nvl(:pBlaNo, T1.BONDLEDGERACCOUNTNUMBER)\n" +
                        "                and T1.BONDSUBSCRIPTIONID = nvl(:pSubscriptionId, T1.BONDSUBSCRIPTIONID)\n" +
                        "                AND  (T1.INTERESTPAIDDATE BETWEEN TO_DATE(:pInterestFrom,'DD/MM/YYYY') and TO_DATE(:pInterestTo,'DD/MM/YYYY'))\n"
                        +
                        "                AND T2.TRANLEG='D' AND  (T1.BRANCHID LIKE '%'|| :pSearchValue ||'%'\n" +
                        "                                    OR T1.BONDLEDGERACCOUNTNUMBER LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                    OR T1.CUSTOMERNAME LIKE '%'||:pSearchValue||'%'\n" +
                        "                                    OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n" +
                        "                                    OR T1.SUBSCRIPTIONDATE LIKE '%'||:pSearchValue||'%'\n" +
                        "                                   OR T1.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n" +
                        "                                   OR TO_CHAR(T1.INTERESTPAIDDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                    OR T1.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                    OR T1.CREDITACCOUNTNUMBER  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                     OR T1.INTERESTAMOUNT  LIKE '%'||:pSearchValue||'%'\n" +
                        "\n" +
                        "                                   OR T1.NETINTERESTAMOUNT LIKE '%'|| :pSearchValue ||'%'\n" +
                        "                                   OR T1.GBMTRANID LIKE '%'|| :pSearchValue ||'%'\n" +
                        "                                   OR T2.FINACLETRANID LIKE '%'|| :pSearchValue ||'%')\n" +
                        "                GROUP BY  T1.BONDLEDGERACCOUNTNUMBER,                               \n" +
                        "                T1.SUBSCRIPTIONAMOUNT,                        \n" +
                        "                T1.NETINTERESTAMOUNT, \n" +
                        "                T1.INTERESTAMOUNT,\n" +
                        "                T1.INTERESTPAIDDATE,                                     \n" +
                        "                T1.BONDSUBSCRIPTIONID,              \n" +
                        "                T1.CUSTOMERNAME,                      \n" +
                        "                T1.INTERESTPERIODFROM,               \n" +
                        "                T1.INTERESTPERIODTO,                   \n" +
                        "                T1.CREDITACCOUNTNUMBER,          \n" +
                        "                T1.GBMTRANID,               \n" +
                        "                T1.SUBSCRIPTIONDATE,        \n" +
                        "                T1.SUBSCRIPTIONAMOUNT,     \n" +
                        "                T1.MODEOFINTERESTPAYMENT,\n" +
                        "                T1.BRANCHID,\n" +
                        "                T2.FINACLETRANID";

                if (bondInterestRunCreditDTO.getSortField() != null
                        && !bondInterestRunCreditDTO.getSortField().isEmpty()
                        && bondInterestRunCreditDTO.getSortOrder() != null
                        && !bondInterestRunCreditDTO.getSortOrder().isEmpty()) {
                    hqlQuery += " ORDER BY " + bondInterestRunCreditDTO.getSortField() + " "
                            + bondInterestRunCreditDTO.getSortOrder();
                }

                Query<Map<String, Object>> query = session.createQuery(hqlQuery);
                query.setParameter("pSchemeCode", bondInterestRunCreditDTO.getSchemeCode());
                query.setParameter("pInterestFrom", bondInterestRunCreditDTO.getInterestPayFrom());
                query.setParameter("pInterestTo", bondInterestRunCreditDTO.getInterestPayTo());
                query.setParameter("pBlaNo", bondInterestRunCreditDTO.getBlaNo());
                query.setParameter("pSubscriptionId", bondInterestRunCreditDTO.getSubscriptionId());
                query.setParameter("pBranchId", bondInterestRunCreditDTO.getBranchId());
                query.setParameter("pSearchValue", bondInterestRunCreditDTO.getSearchValue());
                query.setFirstResult(
                        (bondInterestRunCreditDTO.getPageNo() - 1) * bondInterestRunCreditDTO.getPageSize());
                query.setMaxResults(bondInterestRunCreditDTO.getPageSize());

                query.setResultTransformer(new TrailRunDetailsResultTransformer());
                List<Map<String, Object>> results = query.getResultList();
                bondInterestRunCreditDTO.setData(results);

                Query<Long> countQuery = session.createQuery("SELECT \n" +
                        "        COUNT(1) \n" +
                        "        FROM\n" +
                        "        InterestPaymentDetails T1\n" +
                        "        INNER JOIN \n" +
                        "        TransactionTableDetails T2\n" +
                        "        ON    \n" +
                        "        T1.GBMTRANID = T2.GBMTRANID \n" +
                        "        WHERE (T1.VERIFIEDSTATUS = 'Y' AND T1.ISDELETED='N' AND T1.SCHCODE = :pSchemeCode) \n"
                        +
                        "        AND T2.VERIFIEDSTATUS = 'Y' AND T2.ISDELETED='N' AND \n" +
                        "        T2.SCHSUBCODE = :pSchemeCode \n" +
                        "       and  T1.BRANCHID  = nvl(:pBranchId, T1.BRANCHID)\n" +
                        "                AND T1.BONDLEDGERACCOUNTNUMBER = nvl(:pBlaNo, T1.BONDLEDGERACCOUNTNUMBER)\n" +
                        "                and T1.BONDSUBSCRIPTIONID = nvl(:pSubscriptionId, T1.BONDSUBSCRIPTIONID)\n" +
                        "        AND  (T1.INTERESTPAIDDATE BETWEEN TO_DATE(:pInterestFrom,'DD/MM/YYYY') and TO_DATE(:pInterestTo,'DD/MM/YYYY'))\n"
                        +
                        "        AND T2.TRANLEG='D'\n" +
                        "         AND (T1.BRANCHID LIKE '%'|| :pSearchValue ||'%'\n" +
                        "                                                                   OR T1.BONDLEDGERACCOUNTNUMBER LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                   OR T1.CUSTOMERNAME LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.BONDSUBSCRIPTIONID LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.SUBSCRIPTIONDATE LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                   OR T1.SUBSCRIPTIONAMOUNT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                   OR TO_CHAR(T1.INTERESTPAIDDATE, 'DD/MM/YYYY') LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.MODEOFINTERESTPAYMENT LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                    OR T1.CREDITACCOUNTNUMBER  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                     OR T1.INTERESTAMOUNT  LIKE '%'||:pSearchValue||'%'\n"
                        +
                        "                                                                   OR T1.NETINTERESTAMOUNT LIKE '%'|| :pSearchValue ||'%'\n"
                        +
                        "                                                                   OR T1.GBMTRANID LIKE '%'|| :pSearchValue ||'%'\n"
                        +
                        "                                                                   OR T2.FINACLETRANID LIKE '%'|| :pSearchValue ||'%')");
                countQuery.setParameter("pSchemeCode", bondInterestRunCreditDTO.getSchemeCode());
                countQuery.setParameter("pInterestFrom", bondInterestRunCreditDTO.getInterestPayFrom());
                countQuery.setParameter("pInterestTo", bondInterestRunCreditDTO.getInterestPayTo());
                countQuery.setParameter("pBlaNo", bondInterestRunCreditDTO.getBlaNo());
                countQuery.setParameter("pSubscriptionId", bondInterestRunCreditDTO.getSubscriptionId());
                countQuery.setParameter("pBranchId", bondInterestRunCreditDTO.getBranchId());
                countQuery.setParameter("pSearchValue", bondInterestRunCreditDTO.getSearchValue());

                Long totalCount = countQuery.getSingleResult();

                int maxPages = (int) Math.ceil((double) totalCount / bondInterestRunCreditDTO.getPageSize());
                bondInterestRunCreditDTO.setMaxPages(maxPages);

                if (bondInterestRunCreditDTO.getIsDownload() != null
                        && bondInterestRunCreditDTO.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS)
                        && bondInterestRunCreditDTO.getDownloadType() != null) {

                    if (bondInterestRunCreditDTO.getDownloadType().equalsIgnoreCase("excel")) {

                        FileResponseDto fileResponseDto = new FileResponseDto();
                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(
                                rbiBondReportsUtil.excelFileGeneratorForInterestRunReport(bondInterestRunCreditDTO)));
                        fileResponseDto.setFileName("Bond Actual Interest Run Credited Report – centralized.xlsx");
                        fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                        responseDataDTO.setResult(fileResponseDto);
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                        return responseDataDTO;
                    }

                    if (bondInterestRunCreditDTO.getDownloadType().equalsIgnoreCase("pdf")) {
                        FileResponseDto fileResponseDto = new FileResponseDto();
                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(
                                rbiBondReportsUtil.pdfFileGeneratorForInterestRunReport(bondInterestRunCreditDTO)));
                        fileResponseDto.setFileName("Bond Actual Interest Run Credited Report – centralized.pdf");
                        fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                        responseDataDTO.setResult(fileResponseDto);
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                        return responseDataDTO;
                    } else {
                        responseDataDTO.setMessage("Invalid File Type");
                        return responseDataDTO;
                    }

                }

            } catch (Exception ex) {

                ex.printStackTrace();
            } finally {
                session.close();
            }

            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
            responseDataDTO.setResult(bondInterestRunCreditDTO);
            return responseDataDTO;

        } else {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("No Options");
            return responseDataDTO;
        }
    }

    @Override
    public ResponseDataDTO getBrokerageInvoiceBondScheme(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = new ArrayList<>();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_brk_invoice.getProcName());
                stmt.setString("p_user_id_in", UserAccountUtils.getCurrentUsername());
                stmt.setString("p_user_sol_id_in", UserAccountUtils.getCurrentSolId());
                stmt.setString("p_sch_code_in", brokerageInvoiceDownloadDTO.getSchemeCode());
                stmt.setString("p_subs_from_date_in", brokerageInvoiceDownloadDTO.getFromDate());
                stmt.setString("p_subs_to_date_in", brokerageInvoiceDownloadDTO.getToDate());
                stmt.setString("p_broker_code_in", brokerageInvoiceDownloadDTO.getBrokerCode());
                stmt.registerOutParameter("p_email_body_count_out", Types.VARCHAR);
                stmt.registerOutParameter("p_invoice_count_out", Types.VARCHAR);
                stmt.registerOutParameter("p_cursor_val_out", OracleTypes.CURSOR);
                stmt.registerOutParameter("p_msg_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_desc_out", Types.VARCHAR);
                stmt.execute();
                log.info(stmt.getString("p_msg_out"));
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt
                        .getString(CommonConstants.PROC_PARAM_MSG_OUT).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                    if (brokerageInvoiceDownloadDTO.getSchemeCode() == null) {
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("schemeCode", resultSet.getString(1));
                                resultMap.put("subDesc", resultSet.getString(2));
                                resultMap.put("schemeStatus", resultSet.getString(3));
                                resultList.add(resultMap);
                            }
                        } else {
                            resultMsg = ErrorMessages.DATABASE_ERROR;
                            log.error("No result set returned for scheme code null");
                        }
                    } else {
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("broker_name", resultSet.getString(1));
                                resultMap.put("broker_agency_code", resultSet.getString(2));
                                resultMap.put("bank_name", resultSet.getString(3));
                                resultMap.put("account_number", resultSet.getString(4));
                                resultMap.put("hsn_code", resultSet.getString(5));
                                resultMap.put("address", resultSet.getString(6));
                                resultMap.put("PHONE", resultSet.getString(7));
                                resultMap.put("EMAIL_ID", resultSet.getString(8));
                                resultMap.put("GST_NO", resultSet.getString(9));
                                resultMap.put("PAN", resultSet.getString(10));
                                resultMap.put("invoice_no", resultSet.getString(11));
                                resultMap.put("invoice_date", resultSet.getString(12));
                                resultMap.put("Brokerage_Commission", resultSet.getString(13));
                                resultMap.put("flag", resultSet.getString(14));
                                resultMap.put("grossBrokerAmt", resultSet.getString(15));
                                resultMap.put("brokerGstAmt", resultSet.getString(16));
                                resultMap.put("igstRate", resultSet.getString(17));
                                resultMap.put("investmentAmount", resultSet.getString(18));
                                resultMap.put("netBrokerRate", resultSet.getString(19));
                                resultList.add(resultMap);
                            }
                        } else {
                            resultMsg = ErrorMessages.DATABASE_ERROR;
                            log.error("No result set returned for scheme code: {}"
                                    , brokerageInvoiceDownloadDTO.getSchemeCode());
                        }
                        if(null!=stmt.getString("p_email_body_count_out")){
                            responseDataDTO.setTotal(Double.parseDouble(stmt.getString("p_email_body_count_out")));}
                        if(null!=stmt.getString("p_invoice_count_out")){
                            responseDataDTO.setResult1(Double.parseDouble(stmt.getString("p_invoice_count_out")));}
                    }
                } else {
                    resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);
                    log.error("Stored procedure execution failed: {}" , resultMsg);
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                log.error("Database connection is null");
            }
        } catch (SQLException sqlEx) {
            log.error("SQL Exception occurred while executing stored procedure: ", sqlEx);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(resultList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBrokerageAgencyCode(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<Map<String, String>> resultList = new ArrayList<>();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_brk_code_tor.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, brokerageInvoiceDownloadDTO.getSchemeCode());

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);

                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (CommonConstants.SUCCESS.equals(stmt.getString(index - 1))) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(4);

                    if (brokerageInvoiceDownloadDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            Map<String, String> item = new HashMap<>();
                            item.put("brokerAgencyCode", resultSet.getString(1));
                            item.put("brokerName", resultSet.getString(2));
                            resultList.add(item);

                        }
                        responseDataDTO.setResult(resultList);
                    }
                } else {

                    resultMsg = stmt.getString(index);
                    log.error("Stored procedure execution failed: {}" , resultMsg);
                }
            } else {

                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                log.error("Connection is null");
            }
        } catch (SQLException sqlEx) {
            log.error("SQL Exception occurred while executing stored procedure: ", sqlEx);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {

            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO generateBrokerRes(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_brk_code_res.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, brokerageInvoiceDownloadDTO.getSchemeCode());
                stmt.setArray(index++,
                        generateArrayForGenerateBrok(connection, brokerageInvoiceDownloadDTO.getBrokerResList()));
                stmt.setString(index++, brokerageInvoiceDownloadDTO.getFromDate());
                stmt.setString(index++, brokerageInvoiceDownloadDTO.getToDate());

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.info(CommonConstants.EXCEPTION_MESSAGE, e.getMessage());
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private Array generateArrayForGenerateBrok(Connection connection, List<BrokerageInvoiceDownloadDTO> list)
            throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor
                .createDescriptor(CommonConstants.DATABASE_OBJECT_TY_GBM_PP_COL_DETAILS_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor
                .createDescriptor(CommonConstants.DATABASE_TY_GBM_PP_COL_DETAILS, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[2];
            BrokerageInvoiceDownloadDTO item = list.get(index);
            paramArray[0] = item.getBrokerCode();
            paramArray[1] = item.getInvoiceNum();
            // paramArray[1] = item.getSubscriptionDate();
            // paramArray[2] = item.getSubscriptionId();
            // paramArray[3] = item.getSubscriptionAmount();
            // paramArray[4] = item.getBrokerAmount();
            // paramArray[5] = item.getPaidAmount();
            // paramArray[6] = item.getNarration();

            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    @Override
    public ResponseDataDTO unlockBla(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet rs = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_gbm_rbi_bond_bla_cum_sub_unlock.getProcName());

                int index = 1;
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));

                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null
                        && CommonConstants.SUCCESS.equalsIgnoreCase(stmt.getString(index - 1))) {

                    rs = (ResultSet) stmt.getObject(4);

                    if (bondLedgerAccountMaintenanceDTO.getSchemeCode() == null) {
                        if (rs != null) {
                            List<BondLedgerAccountMaintenanceDTO> list = new ArrayList<>();
                            BondLedgerAccountMaintenanceDTO item = null;

                            while (rs.next()) {
                                item = new BondLedgerAccountMaintenanceDTO();

                                item = new BondLedgerAccountMaintenanceDTO();
                                item.setSubCode(rs.getString(1));
                                item.setSubCodeDescription(rs.getString(2));
                                item.setStatus(rs.getString(3));

                                list.add(item);

                            }
                            responseDataDTO.setResult(list);
                        }
                    } else if (bondLedgerAccountMaintenanceDTO.getSchemeCode() != null) {
                        if (rs != null) {
                            List<BondLedgerAccountMaintenanceDTO> list = new ArrayList<>();
                            BondLedgerAccountMaintenanceDTO item = null;
                            while (rs.next()) {
                                item = new BondLedgerAccountMaintenanceDTO();
                                item.setPanNum(rs.getString("pan_no"));
                                item.setBondHolderName(rs.getString("customer_name"));
                                item.setLockedStatus(rs.getString("locked_status"));
                                item.setMakerCheckerId(rs.getString("maker_checker_id"));

                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else {
                        log.info("Proc Status Description: {}" , resultMsg);
                    }
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO unlockBlaSubmit(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(bondLedgerAccountMaintenanceDTO.getSchemeCode() == null || bondLedgerAccountMaintenanceDTO.getSchemeCode().trim().isEmpty()){
            responseDataDTO.setMessage("Scheme code is must and not empty");
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
                stmt = connection
                        .prepareCall(RbiBondProcNameEnum.sp_gbm_rbi_bond_bla_cum_sub_unlock_submit.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setArray(index++,
                        generateTYP_SINGLE_COL_OBJ(connection, bondLedgerAccountMaintenanceDTO.getPanDet()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
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
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private static ARRAY generateTYP_SINGLE_COL_OBJ(Connection connection, List<String> panDet) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);

        STRUCT[] genericTableInsertStructs = null;
        if (panDet == null) {
            genericTableInsertStructs = new STRUCT[1];
            Object[] paramArray = null;
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[0] = struct;
        } else {
            genericTableInsertStructs = new STRUCT[panDet.size()];
            for (int index = 0; index < panDet.size();
                 index++) {
                Object[] paramArray = new Object[1];
                paramArray[0] = panDet.get(index);

                STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
                genericTableInsertStructs[index] = struct;
            }
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    @Override
    public ResponseDataDTO getPendingBrokeragePaymentClaimDetails(
            PendingBrokeragePaymentClaimStatusDTO pendingBrokeragePaymentClaimStatusDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> finalData = new HashMap<>();
        List<PendingBrokeragePaymentClaimStatusDTO> pendingBrokeragePaymentClaimStatusDTOList = Collections.emptyList();

        String p_user_id_in = "p_user_id_in";
        String p_user_sol_id_in = "p_user_sol_id_in";
        String p_broker_code_in = "p_broker_code_in";
        String p_sch_code_in = "p_sch_code_in";
        String p_type_in = "p_type_in";
        String p_from_date_in = "p_from_date_in";
        String p_to_date_in = "p_to_date_in";

        String p_cur_val_out = "p_cur_val_out";
        String p_count_out = "p_count_out";
        String p_sum_of_invest_amt_out = "p_sum_of_invest_amt_out";
        String p_sum_of_gross_amt_out = "p_sum_of_gross_amt_out";
        String p_msg_out = "p_msg_out";
        String p_msg_desc_out = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername());
            inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId());
            inParams.put(p_broker_code_in, pendingBrokeragePaymentClaimStatusDTO.getBrokerId());
            inParams.put(p_sch_code_in, pendingBrokeragePaymentClaimStatusDTO.getSchemeCode());
            inParams.put(p_type_in, pendingBrokeragePaymentClaimStatusDTO.getStatus());
            inParams.put(p_from_date_in, pendingBrokeragePaymentClaimStatusDTO.getFromDate());
            inParams.put(p_to_date_in, pendingBrokeragePaymentClaimStatusDTO.getToDate());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(p_user_id_in, Types.VARCHAR),
                    new SqlParameter(p_user_sol_id_in, Types.VARCHAR),
                    new SqlParameter(p_broker_code_in, Types.VARCHAR),
                    new SqlParameter(p_sch_code_in, Types.VARCHAR),
                    new SqlParameter(p_type_in, Types.VARCHAR),
                    new SqlParameter(p_from_date_in, Types.VARCHAR),
                    new SqlParameter(p_to_date_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_cur_val_out, OracleTypes.CURSOR),
                    new SqlOutParameter(p_count_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_sum_of_invest_amt_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_sum_of_gross_amt_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_desc_out, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(p_cur_val_out, (rs, rowNum) -> {

                PendingBrokeragePaymentClaimStatusDTO item = new PendingBrokeragePaymentClaimStatusDTO();

                switch (pendingBrokeragePaymentClaimStatusDTO.getStatus()) {
                    case "P":
                        item.setBrokerCode(rs.getString("broker_code"));
                        item.setBrokerName(rs.getString("broker_name"));
                        item.setSumOfInvestmentAmountHeader1(rs.getString("sum_of_invest_amt"));
                        item.setGrossBrokerage(rs.getString("gross_brk_amt"));
                        item.setAddress1(rs.getString("address"));
                        item.setAddress2(rs.getString("address_1"));
                        item.setCity(rs.getString("city"));
                        item.setPinCode(rs.getString("pin_code"));
                        item.setState(rs.getString("state"));
                        item.setNetBrokerage(rs.getString("net_brk_amt"));
                        item.setGst18Percent(rs.getString("gst_amt"));
                        item.setVendorCodes(rs.getString("vendor_codes"));
                        item.setHsnCodes(rs.getString("hsn_codes"));
                        item.setStatus(rs.getString("status"));
                        item.setRcmStatus(rs.getString("rcm_status"));
                        item.setInvoiceNumber(rs.getString("invoice_no"));
                        item.setInvoiceDate(rs.getString("invoice_date"));
                        item.setVbms(rs.getString("vbms"));
                        item.setDate(rs.getString("vbms_date"));
                        item.setMaker(rs.getString("maker"));
                        item.setCreditDate(rs.getString("credit_date"));
                        item.setAmount(rs.getString("amount"));
                        item.setDifference(rs.getString("difference"));
                        item.setReceivedOn(rs.getString("received_on"));
                        item.setReceivedOn(rs.getString("rejected_on"));
                        item.setPod(rs.getString("pod"));
                        item.setRcm(rs.getString("rcm"));
                        item.setRemarks(rs.getString("remarks"));
                        item.setTat(rs.getString("tat"));
                        item.setCpuRemarks(rs.getString("cpu_remarks"));
                        item.setRemarks1(rs.getString("remarks1"));
                        item.setReason(rs.getString("reason"));
                        item.setPhone(rs.getString("phone"));
                        item.setEmailId(rs.getString("email_id"));
                        item.setStatus(rs.getString("broker_status"));
                        item.setQueryRemarks(rs.getString("query_remark"));
                        break;

                    case "C":
                        item.setBlaNo(rs.getString("bla_no"));
                        item.setInvestmentAmount(rs.getString("invest_amt"));
                        item.setCasTransferDate(rs.getString("cas_transfer_date"));
                        item.setMaturityDate(rs.getString("maturity_date"));
                        item.setSchemeName(rs.getString("scheme"));
                        item.setCfd(rs.getString("cfd"));
                        item.setBranchRefNo(rs.getString("branch_ref_no"));
                        item.setAdviceNo(rs.getString("advice_no"));
                        item.setApplicantName(rs.getString("applicant_name"));
                        item.setBlaAddress(rs.getString("bla_address"));
                        item.setBlaAddress1(rs.getString("bla_address_1"));
                        item.setBlaCity(rs.getString("bla_city"));
                        item.setBlaState(rs.getString("bla_state"));
                        item.setBlaPincode(rs.getString("bla_pin_code"));
                        item.setBranchName(rs.getString("branch_name"));
                        item.setReceiptDate(rs.getString("rcpt_date"));
                        item.setGroupCode(rs.getString("group_code"));
                        item.setHdfcBrokerCode(rs.getString("hdfc_broker_code"));
                        item.setBrokerName(rs.getString("broker_name"));
                        item.setAddress1(rs.getString("address"));
                        item.setAddress2(rs.getString("address_1"));
                        item.setCity(rs.getString("city"));
                        item.setState(rs.getString("state"));
                        item.setPinCode(rs.getString("pin_code"));
                        item.setPhone(rs.getString("phone"));
                        item.setPaymentMode(rs.getString("payment_mode"));
                        item.setAccountTypeCode(rs.getString("account_type_code"));
                        item.setBankAccount(rs.getString("bank_account"));
                        item.setMicrCode(rs.getString("micr_code"));
                        item.setApplicantNO(rs.getString("appl_no"));
                        item.setStatus(rs.getString("status"));
                        item.setDeStatus(rs.getString("de_status"));
                        item.setBrokerAmount(rs.getString("brk_amt"));
                        item.setHdfcBranchCode(rs.getString("hdfc_branch_code"));
                        break;
                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_BROKER_STATUS_CLAIM",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            pendingBrokeragePaymentClaimStatusDTOList = (List<PendingBrokeragePaymentClaimStatusDTO>) result
                    .get(p_cur_val_out);

            if (CollectionUtils.isEmpty(pendingBrokeragePaymentClaimStatusDTOList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("pendingBrokeragePaymentStatusDtoList", pendingBrokeragePaymentClaimStatusDTOList);
                responseDataDTO.setResult(finalData);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBrokerageInvoiceEmailBodyLetterDetails(BrokerageInvoiceEmailBodyDTO brokerageInvoiceEmailBodyDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        List<BrokerageInvoiceEmailBodyDTO> invoiceList = Collections.emptyList();
        HashMap<String, Object> resultMap = new HashMap();
        String totalRecords = null;


        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, brokerageInvoiceEmailBodyDTO.getSchemeCode());
            inParams.put(flag, brokerageInvoiceEmailBodyDTO.getFlag());
            inParams.put(fromDate, brokerageInvoiceEmailBodyDTO.getFromDate());
            inParams.put(toDate, brokerageInvoiceEmailBodyDTO.getToDate());
            inParams.put(P_TYPE_INVOICE_IN, brokerageInvoiceEmailBodyDTO.getInvoiceType());
            inParams.put(P_BRK_AGENCY_CODE_IN, brokerageInvoiceEmailBodyDTO.getBrokerCode());
            inParams.put(p_search_value_in, brokerageInvoiceEmailBodyDTO.getSearchValue());
            inParams.put(p_page_no_in, brokerageInvoiceEmailBodyDTO.getPageNo());
            inParams.put(p_page_size_in, brokerageInvoiceEmailBodyDTO.getPageSize());

            // Define SQL parameters
            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(P_TYPE_INVOICE_IN, Types.VARCHAR),
                    new SqlParameter(P_BRK_AGENCY_CODE_IN, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),

            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BrokerageInvoiceEmailBodyDTO item = new BrokerageInvoiceEmailBodyDTO();
                if (brokerageInvoiceEmailBodyDTO.getFlag() != null) {
                    switch (brokerageInvoiceEmailBodyDTO.getFlag()) {
                        case "S":
                            item.setSchemeCode(rs.getString(1));
                            item.setSchemeName(rs.getString(2));
                            item.setSchemestatus(rs.getString(3));
                            break;
                        case "I":
                            item.setInvoiceType(rs.getString(1));
                            break;
                        case "AC":
                            item.setBrokerCode(rs.getString(1));
                            item.setBrokerName(rs.getString(2));
                            item.setBranchId(rs.getString(3));
                            item.setVerifyStatus(rs.getString(4));
                            break;
                        case "D":
                            item.setInvoiceNo(rs.getString(1));
                            item.setInvoiceDate(rs.getString(2));
                            item.setBrokerName(rs.getString(3));
                            item.setBrokerCode(rs.getString(4));
                            item.setBrokerPanNo(rs.getString(5));
                            item.setBrokerEmailId(rs.getString(6));
                            item.setGrossBrokerage(rs.getString(7));
                            item.setGst(rs.getString(8));
                            item.setNetBrokerage(rs.getString(9));
                            break;
                    }
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_rep_brokerage_invoice",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            invoiceList = (List<BrokerageInvoiceEmailBodyDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(invoiceList)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", invoiceList);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getCallBackReportDetails(CallBackReportDTO callBackReportDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        List<CallBackReportDTO> list = new ArrayList<>();
        HashMap<String, Object> finalData = new HashMap<>();
        List<CallBackReportDTO> callBackReportDTOList = Collections.emptyList();


        String p_user_id_in = "p_user_id_in";
        String p_user_sol_id_in = "p_user_sol_id_in";
        String p_sch_code_in = "p_sch_code_in";
        String p_flag_in = "p_flag_in";
        String p_from_date_in = "p_from_date_in";
        String p_to_date_in = "p_to_date_in";
        String p_page_no_in = "p_page_no_in";
        String p_page_size_in = "p_page_size_in";
        String p_search_value_in = "p_search_value_in";

        String p_total_records_out = "p_total_records_out";
        String p_cur_val_out = "p_cur_val_out";
        String p_msg_out = "p_msg_out";
        String p_msg_desc_out = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername());
            inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId());
            inParams.put(p_sch_code_in, callBackReportDTO.getSchemeCode());
            inParams.put(p_flag_in, callBackReportDTO.getFlag());
            inParams.put(p_from_date_in, callBackReportDTO.getFromDate());
            inParams.put(p_to_date_in, callBackReportDTO.getToDate());
            inParams.put(p_page_no_in, callBackReportDTO.getPageNo());
            inParams.put(p_page_size_in, callBackReportDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=callBackReportDTO.getSearchValue()&&!callBackReportDTO.getSearchValue().trim().isEmpty())?callBackReportDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(p_user_id_in, Types.VARCHAR),
                    new SqlParameter(p_user_sol_id_in, Types.VARCHAR),
                    new SqlParameter(p_sch_code_in, Types.VARCHAR),
                    new SqlParameter(p_flag_in, Types.VARCHAR),
                    new SqlParameter(p_from_date_in, Types.VARCHAR),
                    new SqlParameter(p_to_date_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_cur_val_out, OracleTypes.CURSOR),
                    new SqlOutParameter(p_msg_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_desc_out, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(p_cur_val_out, (rs, rowNum) -> {

                CallBackReportDTO item = new CallBackReportDTO();

                if (callBackReportDTO.getSchemeCode() == null && callBackReportDTO.getFromDate() == null && callBackReportDTO.getToDate() == null) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                    list.add(item);
                }
                else if (callBackReportDTO.getSchemeCode() != null && callBackReportDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setModeOfInvestment(rs.getString(171));
                    item.setSchemeName(rs.getString(5));
                    item.setBranchName(rs.getString(3));
                    item.setApplicationNo(rs.getString(4));
                    item.setInvestmentDate(rs.getString(6));
                    item.setCASDate(rs.getString(7));
                    item.setInvestmentAmount(rs.getString(31));
                    item.setBranchRefNoSubsId(rs.getString(1));
                    item.setBlaNo(rs.getString(2));
                    item.setApplicantName1(rs.getString(8));
                    item.setGender(rs.getString(14));
                    item.setDOB(rs.getString(15));
                    item.setDOB2(rs.getString(16));
                    item.setDOB3(rs.getString(17));
                    item.setPanNo(rs.getString(12));
                    item.setApplicantName2(rs.getString(9));
                    item.setPanNo2(rs.getString(13));
                    item.setApplicantName3(rs.getString(10));
                    item.setPanNo3(rs.getString(11));
                    item.setAddress1(rs.getString(18));
                    item.setAddress2(rs.getString(19));
                    item.setCity(rs.getString(20));
                    item.setState1(rs.getString(21));
                    item.setPincode(rs.getString(22));
                    item.setTelephoneNo(rs.getString(23));
                    item.setMobile_Number(rs.getString(24));
                    item.setEmailId(rs.getString(25));
                    item.setApplicantStatus(rs.getString(26));
                    item.setModeOfHolding(rs.getString(27));
                    item.setGuardianName(rs.getString(28));
                    item.setModeOfPayment(rs.getString(29));
                    item.setChequeNumber(rs.getString(30));
                    item.setAccountNo(rs.getString(32));
                    item.setIfscCode(rs.getString(34));
                    item.setMicrCode(rs.getString(33));
                    item.setBankName(rs.getString(35));
                    item.setNominee1(rs.getString(41));
                    item.setNominee_DOB1(rs.getString(45));
                    item.setNominee_Relationship(rs.getString(57));
                    item.setNominee_Sharing_Percent1(rs.getString(49));
                 item.setIf_Nominee_minor_Guardian_details(rs.getString(53));
                    item.setGuardianName1(rs.getString(61));
                    item.setGuardianRelationship1(rs.getString(65));
                    item.setNominee2(rs.getString(42));
                    item.setNominee_DOB2(rs.getString(46));
                    item.setGuardianName2(rs.getString(62));
                    item.setGuardianRelationship2(rs.getString(66));
                    item.setNominee3(rs.getString(43));
                    item.setNominee_DOB3(rs.getString(47));
                    item.setGuardianName3(rs.getString(63));
                    item.setGuardianRelationship3(rs.getString(67));
                    item.setNominee4(rs.getString(44));
                    item.setNominee_DOB4(rs.getString(48));
                    item.setGuardianName4(rs.getString(64));
                    item.setGuardianRelationship4(rs.getString(68));
                    item.setNominationRefNo(rs.getString(93));
                    item.setStatus(rs.getString(94));
                    item.setMaturityDate(rs.getString(95));
                    item.setActualPaidDate(rs.getString(96));
                    item.setMaturityAmount(rs.getString(97));
                    item.setHalfYearlyInterestAmount(rs.getString(98));
                    item.setLastIntrPaidDateBrokenInterest(rs.getString(99));
                    item.setNetPaidGrossTDSAcutalPaid(rs.getString(100));
                    item.setTdsAt10Percent(rs.getString(101));
                    item.setTDS_amt(rs.getString(102));
                    item.setExempt_Doc_Status_Yes_No(rs.getString(103));
                    item.setExempt_Doc_Type(rs.getString(104));
                    item.setLimit(rs.getString(105));
                    item.setFY(rs.getString(106));
                    item.setAge(rs.getString(118));
                    item.setWhether_assessed_to_tax(rs.getString(107));
                    item.setDate_on_which_Declaration_is_received(rs.getString(108));
                    item.setEstimated_income_for_which_this_declaration_is_ma(rs.getString(109));
                    item.setEstimated_total_income_of_the_P_Y(rs.getString(110));
                    item.setTotal_No__of_Forms(rs.getString(111));
                    item.setAggregate_amount_of_income(rs.getString(112));
                  item.setCertificateNoLowTaxNoTax(rs.getString(113));
                    item.setExem_Doc_Maker_Id(rs.getString(114));
                    item.setExem_Doc_Checker_Id(rs.getString(115));
                    item.setLast_Update_Date_And_Time(rs.getString(116));
                    item.setCurrent_Status(rs.getString(117));
                    item.setCIF(rs.getString(38));
                    item.setAccount_Type_Code(rs.getString(119));
                    item.setApplication_Data_Entry_Maker_id(rs.getString(120));
                    item.setApplication_Data_Entry_Checker_ID(rs.getString(121));
                    item.setAppl_status(rs.getString(149));
                    item.setDataEntry_By_HO(rs.getString(122));
                    item.setCertificateNo(rs.getString(162));
                    item.setCertificate_Type_COH__COI(rs.getString(123));
                    item.setSection_Code_193(rs.getString(124));
                    item.setReason_for_Non_deduction_Lower_Deduction(rs.getString(125));
                    item.setInteret_Payout_of_the_Year(rs.getString(126));
                    item.setDeductee_Code_02(rs.getString(127));
                    item.setPan_Status_Active_Inactive(rs.getString(170));
                    item.setFiler_Non_Filer(rs.getString(169));
                    item.setTransfer_cases_Old_Branch_Ref_Number(rs.getString(150));
                    item.setTransfer_cases_Old_Blas(rs.getString(151));
                    item.setTransfer_cases_Old_Customer_Name(rs.getString(152));
                    item.setTransfer_cases_Old_Pan_Number(rs.getString(153));
                    item.setBond_Certificate_Dispatch_Date(rs.getString(163));
                    item.setBond_Certificate_Email_trigger_date(rs.getString(164));
                    item.setBond_Certificate_Status(rs.getString(165));
                    item.setHalf_Yearly_Interest_Advice_Email_trigger_date(rs.getString(166));
                    item.setHalf_Yearly_Interest_Advice_Status(rs.getString(167));
                    item.setCo_parceners_details_1(rs.getString(128));
                    item.setCo_parceners_details_2(rs.getString(129));
                    item.setCo_parceners_details_3(rs.getString(130));
                    item.setCo_parceners_1_DOB(rs.getString(131));
                    item.setCo_parceners_1_Relationship(rs.getString(134));
                    item.setCo_parceners_2_DOB(rs.getString(132));
                    item.setCo_parceners_2_Relationship(rs.getString(135));
                    item.setCo_parceners_3_DOB(rs.getString(133));
                    item.setCo_parceners_3_Relationship(rs.getString(136));
                    item.setBroker_code(rs.getString(137));
                    item.setBroker_Name(rs.getString(138));
                    item.setAddress_1(rs.getString(139));
                    item.setAddress_2(rs.getString(140));
                    item.setLocation(rs.getString(141));
                    item.setState2(rs.getString(142));
                    item.setPin_Code(rs.getString(143));
                    item.setMobileNo(rs.getString(144));
                    item.setPan_Number(rs.getString(145));
                    item.setGST_Number(rs.getString(146));
                    item.setAccount_Number(rs.getString(147));
                    item.setGross_Brokerage(rs.getString(154));
                    item.setNET_Brokerage(rs.getString(155));
                    item.setGST_At_18Percent(rs.getString(156));
                    item.setBroker_Code_Status_Active_Inactive(rs.getString(157));
                   item.setBrokerage_letter_Email_triggered_status(rs.getString(159));
                   item.setBrokerage_Payment_status(rs.getString(158));
                    item.setPOA(rs.getString(168));
                    item.setIf_Nominee_minor_Guardian_details2(rs.getString(54));
                    item.setIf_Nominee_minor_Guardian_details3(rs.getString(55));
                    item.setIf_Nominee_minor_Guardian_details4(rs.getString(56));
                    item.setNominationRefNo2(rs.getString(172));
                    item.setNominationRefNo3(rs.getString(173));
                    item.setNominationRefNo4(rs.getString(174));
                    item.setNominationStatus1(rs.getString(175));
                    item.setNominationStatus2(rs.getString(176));
                    item.setNominationStatus3(rs.getString(177));
                    item.setNominationStatus4(rs.getString(178));
                    item.setSchemeType(rs.getString(179));
                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_CALLBACK_REPORT",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            callBackReportDTOList = (List<CallBackReportDTO>) result.get(p_cur_val_out);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            if (CollectionUtils.isEmpty(callBackReportDTOList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("callbackreportDtoList", callBackReportDTOList);
                responseDataDTO.setResult(finalData);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e);
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setTotal(Double.parseDouble(totalRecords));
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBondActualInterestRunCreditedDetails(BondActualInterestRunCreditedDTO bondActualInterestRunCreditedDTO) {
        List<BondActualInterestRunCreditedDTO> list = new ArrayList<>();
        HashMap<String, Object> finalData = new HashMap<>();
        String resultMsg = "SUCCESS";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String totalRecords = null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(flag, bondActualInterestRunCreditedDTO.getFlag());
            inParams.put(schemeCode, bondActualInterestRunCreditedDTO.getSchemeCode());
            inParams.put(intFromDate, bondActualInterestRunCreditedDTO.getFromDate());
            inParams.put(intToDate, bondActualInterestRunCreditedDTO.getToDate());
            inParams.put(panNumber, bondActualInterestRunCreditedDTO.getPanNo());
            inParams.put(blaNumber, bondActualInterestRunCreditedDTO.getBlaNo());
            inParams.put(subsId, bondActualInterestRunCreditedDTO.getSubscriptionId());
            inParams.put(p_page_no_in, bondActualInterestRunCreditedDTO.getPageNo());
            inParams.put(p_page_size_in, bondActualInterestRunCreditedDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=bondActualInterestRunCreditedDTO.getSearchValue()&&!bondActualInterestRunCreditedDTO.getSearchValue().trim().isEmpty())?bondActualInterestRunCreditedDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(intFromDate, Types.VARCHAR),
                    new SqlParameter(intToDate, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(subsId, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BondActualInterestRunCreditedDTO item = new BondActualInterestRunCreditedDTO();
                if (bondActualInterestRunCreditedDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                    return item;
                } else if (bondActualInterestRunCreditedDTO.getFlag().equalsIgnoreCase("P")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNo((rs.getString(2)));
                    return item;
                } else if (bondActualInterestRunCreditedDTO.getFlag().equalsIgnoreCase("B")) {
                    item.setModeOfHolding(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setPanNo((rs.getString(3)));
                    item.setBlaNo((rs.getString(4)));
                    return item;
                } else if (bondActualInterestRunCreditedDTO.getFlag().equalsIgnoreCase("BR")) {
                    item.setSubscriptionAmt((rs.getString(1)));
                    item.setSubscriptionDate((rs.getString(2)));
                    item.setSubscriptionId((rs.getString(3)));
                    return item;
                } else if (bondActualInterestRunCreditedDTO.getFlag().equalsIgnoreCase("D") || bondActualInterestRunCreditedDTO.getFlag().equalsIgnoreCase("UM") ) {
                    item.setModeOfPayment(rs.getString(1));
                    item.setBranchRefNo((rs.getString(2)));
                    item.setBlaNo((rs.getString(3)));
                    item.setRefNo((rs.getString(4)));
                    item.setAppName1(rs.getString(5));
                    item.setAppName2(rs.getString(6));
                    item.setAppName3(rs.getString(7));
                    item.setAddress1(rs.getString(8));
                    item.setAddress2(rs.getString(9));
                    item.setRecName(rs.getString(10));
                    item.setRecParentName(rs.getString(11));
                    item.setPinCode(rs.getString(12));
                    item.setPhoneNo(rs.getString(13));
                    item.setAppStatus(rs.getString(14));
                    item.setInvestmentAmount(rs.getString(15));
                    item.setCfd(rs.getString(16));
                    item.setMaturityDate(rs.getString(17));
                    item.setAccountNo(rs.getString(18));
                    item.setSubscriptionCount(rs.getString(19));
                    item.setBankSortCode(rs.getString(20));
                    item.setBankName(rs.getString(21));
                    item.setPanNo(rs.getString(22));
                    item.setInterestPayOut8Per(rs.getString(23));
                    item.setNonCumulativeInt(rs.getString(24));
                    item.setCumulativeInt(rs.getString(25));
                    item.setInterestPayOut7Per(rs.getString(26));
                    item.setNonCumulativeIntFor7(rs.getString(27));
                    item.setCumulativeIntFor7(rs.getString(28));
                    item.setTotal(rs.getString(29));
                    item.setInOperativePAN(rs.getString(30));
                    item.setNonFilers(rs.getString(31));
                    item.setAge(rs.getString(32));
                    item.setTaxableLimit(rs.getString(33));
                    item.setWaiverDocument(rs.getString(34));
                    item.setWaiverDocumentRefNo(rs.getString(35));
                    item.setRrbTDSRate(rs.getString(36));
                    item.setThresholdExceedAmount(rs.getString(37));
                    item.setThresholdExceedTDSAmount(rs.getString(38));
                    item.setExemptionRate(rs.getString(39));
                    item.setExemptionAmount(rs.getString(40));
                    item.setExemptionTDSAmount(rs.getString(41));
                    item.setSurcharge(rs.getString(42));
                    item.setEduCess(rs.getString(43));
                    item.setBaseRateAmount(rs.getString(44));
                    item.setSurchargeAmount(rs.getString(45));
                    item.setEduCessAmount(rs.getString(46));
                    item.setTdsAmount(rs.getString(47));
                    item.setTdsRecoveryAmount(rs.getString(48));
                    item.setTdsRefundAmount(rs.getString(49));
                    item.setIntRate(rs.getString(50));
                    item.setLastIntPaid(rs.getString(51));
                    item.setPendingInterest(rs.getString(52));
                    item.setCurrentHalfYearlyInterest(rs.getString(53));
                    item.setFutureInterest(rs.getString(54));
                    item.setGrossInterestAmount(rs.getString(55));
                    item.setIntPaid(rs.getString(56));
                    item.setPaymentDate(rs.getString(57));
                    item.setReturnReason(rs.getString(58));
                    item.setReturnAmt(rs.getString(59));
                    item.setReInitiatedAmt(rs.getString(60));
                    item.setReInitiatedDate(rs.getString(61));
                    item.setPaymentDate(rs.getString(62));
                    item.setReInitiatedPaymentMode(rs.getString(63));
                    return item;
                }



                else if (bondActualInterestRunCreditedDTO.getFlag().equalsIgnoreCase("M")) {
                    item.setModeOfPayment(rs.getString(1));
                    //  item.setBranchRefNo((rs.getString(2)));
                    item.setBlaNo((rs.getString(2)));
                    //  item.setRefNo((rs.getString(4)));
                    item.setAppName1(rs.getString(3));
                    item.setAppName2(rs.getString(4));
                    item.setAppName3(rs.getString(5));
                    item.setAddress1(rs.getString(6));
                    item.setAddress2(rs.getString(7));
                    item.setRecName(rs.getString(8));
                    item.setRecParentName(rs.getString(9));
                    item.setPinCode(rs.getString(10));
                    item.setPhoneNo(rs.getString(11));
                    item.setAppStatus(rs.getString(12));
                    item.setInvestmentAmount(rs.getString(13));
//                item.setCfd(rs.getString(16));
//                item.setMaturityDate(rs.getString(17));
                    item.setAccountNo(rs.getString(14));
                    item.setSubscriptionCount(rs.getString(15));
                    item.setBankSortCode(rs.getString(16));
                    //  item.setBankName(rs.getString(21));
                    item.setPanNo(rs.getString(17));
                    item.setInterestPayOut8Per(rs.getString(18));
                    item.setNonCumulativeInt(rs.getString(19));
                    item.setCumulativeInt(rs.getString(20));
                    item.setInterestPayOut7Per(rs.getString(21));
                    item.setNonCumulativeIntFor7(rs.getString(22));
                    item.setCumulativeIntFor7(rs.getString(23));
                    item.setTotal(rs.getString(24));
                    item.setInOperativePAN(rs.getString(25));
                    item.setNonFilers(rs.getString(26));
                    item.setAge(rs.getString(27));
                    item.setTaxableLimit(rs.getString(28));
                    item.setWaiverDocument(rs.getString(29));
                    item.setWaiverDocumentRefNo(rs.getString(30));
                    item.setRrbTDSRate(rs.getString(31));
                    item.setThresholdExceedAmount(rs.getString(32));
                    item.setThresholdExceedTDSAmount(rs.getString(33));
                    item.setExemptionRate(rs.getString(34));
                    item.setExemptionAmount(rs.getString(35));
                    item.setExemptionTDSAmount(rs.getString(36));
                    item.setSurcharge(rs.getString(37));
                    item.setEduCess(rs.getString(38));
                    item.setBaseRateAmount(rs.getString(39));
                    item.setSurchargeAmount(rs.getString(40));
                    item.setEduCessAmount(rs.getString(41));
                    item.setTdsAmount(rs.getString(42));
                    item.setTdsRecoveryAmount(rs.getString(43));
                    item.setTdsRefundAmount(rs.getString(44));
                    item.setIntRate(rs.getString(45));
                    item.setLastIntPaid(rs.getString(46));
                    item.setPendingInterest(rs.getString(47));
                    item.setCurrentHalfYearlyInterest(rs.getString(48));
                    item.setFutureInterest(rs.getString(49));
                    item.setGrossInterestAmount(rs.getString(50));
                    item.setIntPaid(rs.getString(51));
                    item.setPaymentDate(rs.getString(52));
//                item.setReturnReason(rs.getString(58));
//                item.setReturnAmt(rs.getString(59));
//                item.setReInitiatedAmt(rs.getString(60));
//                item.setReInitiatedDate(rs.getString(61));
//                item.setPaymentDate(rs.getString(62));
//                item.setReInitiatedPaymentMode(rs.getString(63));
                    return item;


                }




                throw new IllegalArgumentException("Invalid flag: " + bondActualInterestRunCreditedDTO.getFlag());
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_rep_actual_int_credit",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            list = (List<BondActualInterestRunCreditedDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("list", list);
                responseDataDTO.setResult(finalData);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getTdsRemittanceFinconFileDetails(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO) {
        List<TDSRemittanceFinconFiledDTO> list = new ArrayList<>();
        HashMap<String, Object> finalData = new HashMap<>();
        String resultMsg = "SUCCESS";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, tdsRemittanceFinconFileDTO.getSchemeCode());
            inParams.put(flag, tdsRemittanceFinconFileDTO.getFlag());
            inParams.put(name, tdsRemittanceFinconFileDTO.getHolderName());
            inParams.put(panNumber, tdsRemittanceFinconFileDTO.getPanNo());
            inParams.put(fromDate, tdsRemittanceFinconFileDTO.getFromDate());
            inParams.put(toDate, tdsRemittanceFinconFileDTO.getToDate());
            inParams.put(p_page_no_in, tdsRemittanceFinconFileDTO.getPageNo());
            inParams.put(p_page_size_in, tdsRemittanceFinconFileDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=tdsRemittanceFinconFileDTO.getSearchValue()&&!tdsRemittanceFinconFileDTO.getSearchValue().trim().isEmpty())?tdsRemittanceFinconFileDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                TDSRemittanceFinconFiledDTO item = new TDSRemittanceFinconFiledDTO();
                if (tdsRemittanceFinconFileDTO.getSchemeCode() != null && tdsRemittanceFinconFileDTO.getSchemeCode().isEmpty() && tdsRemittanceFinconFileDTO.getFlag() == null) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (tdsRemittanceFinconFileDTO.getFlag() != null) {
                    switch (tdsRemittanceFinconFileDTO.getFlag()) {
                        case "P":
                            item.setPanNo(rs.getString(1));
                            item.setHolderName(rs.getString(2));
                            break;
                        case "D":
                            item.setCoCode(rs.getString(1));
                            item.setRefNo(rs.getString(2));
                            item.setBla(rs.getString(3));
                            item.setHolderName(rs.getString(4));
                            item.setAddress1(rs.getString(5));
                            item.setAddress2(rs.getString(6));
                            item.setCity(rs.getString(7));
                            item.setPin(rs.getString(8));
                            item.setState(rs.getString(9));
                            item.setCountry(rs.getString(10));
                            item.setSection(rs.getString(11));
                            item.setPanNo(rs.getString(12));
                            item.setGrossAmount(rs.getString(13));
                            item.setTaxRate(rs.getString(14) + "%");
                            item.setTotalTaxAmount(rs.getString(15));

                            Timestamp ts = rs.getTimestamp(16);

                            if (ts != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                                sdf.setLenient(false);
                                item.setPaymentDate(sdf.format(ts).toUpperCase());
                            } else {
                                item.setPaymentDate("");
                            }
                            item.setBasicTax(rs.getString(17));
                            item.setSurchargeAmount(rs.getString(18));
                            item.setCessAmount(rs.getString(19));
                            Timestamp ts1 = rs.getTimestamp(20);

                            if (ts1 != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                                sdf.setLenient(false);
                                item.setTaxDepDate(sdf.format(ts1).toUpperCase());
                            } else {
                                item.setPaymentDate("");
                            }
                           // item.setTaxDepDate(rs.getString(20));
                            item.setLowerDeductionStatus(rs.getString(21));
                            item.setCategory(rs.getString(22));
                            item.setInterestPayoutfortheYear(rs.getString(23));
                            item.setWaiverDocument(rs.getString(24));
                            break;

                    }
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_TDS_REMIT_FINCON_FILE",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            list = (List<TDSRemittanceFinconFiledDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("list", list);
                responseDataDTO.setResult(finalData);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBondInterestTrialRunEligibleAccounts(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccountsDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<BondInterestTrialRunEligibleAccountsDto> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;


        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, bondInterestTrialRunEligibleAccountsDto.getSchemeCode());
            inParams.put(flag, bondInterestTrialRunEligibleAccountsDto.getFlag());
            inParams.put(year, bondInterestTrialRunEligibleAccountsDto.getYear());
            inParams.put(interestHalfMonth, bondInterestTrialRunEligibleAccountsDto.getInterestHalfYear());
            inParams.put(name, bondInterestTrialRunEligibleAccountsDto.getName());
            inParams.put(panNumber, bondInterestTrialRunEligibleAccountsDto.getPanNo());
            inParams.put(p_page_no_in, bondInterestTrialRunEligibleAccountsDto.getPageNo());
            inParams.put(p_page_size_in, bondInterestTrialRunEligibleAccountsDto.getPageSize());
            inParams.put(p_search_value_in, bondInterestTrialRunEligibleAccountsDto.getSearchValue());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(year, Types.VARCHAR),
                    new SqlParameter(interestHalfMonth, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BondInterestTrialRunEligibleAccountsDto item = new BondInterestTrialRunEligibleAccountsDto();
                if (bondInterestTrialRunEligibleAccountsDto.getSchemeCode().isEmpty()) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                } else if (bondInterestTrialRunEligibleAccountsDto.getFlag() != null && bondInterestTrialRunEligibleAccountsDto.getFlag().equalsIgnoreCase("P")) {
                    item.setName(rs.getString(1));
                    item.setPanNo(rs.getString(2));
                } else if (bondInterestTrialRunEligibleAccountsDto.getFlag() != null && bondInterestTrialRunEligibleAccountsDto.getFlag().equalsIgnoreCase("T")) {
                    item.setModeOfPayment(rs.getString(1));
                    item.setBranchRefNo(rs.getString(2));
                    item.setBlaNo(rs.getString(3));
                    item.setRefNo(rs.getString(4));
                    item.setApplName1(rs.getString(5));
                    item.setApplName2(rs.getString(6));
                    item.setApplName3(rs.getString(7));
                    item.setAddr1(rs.getString(8));
                    item.setAddr2(rs.getString(9));
                    item.setRecName(rs.getString(10));
                    item.setRecParentName(rs.getString(11));
                    item.setPinCode(rs.getString(12));
                    item.setPhoneNo(rs.getString(13));
                    item.setApplStatus(rs.getString(14));
                    item.setInvestmentAmt(rs.getString(15));
                    item.setCfd(rs.getString(16));
                    item.setMatDate(rs.getString(17));
                    item.setAccountNo(rs.getString(18));
                    item.setSubscriptionCount(rs.getString(19));
                    item.setBankSortCode(rs.getString(20));
                    item.setBankName(rs.getString(21));
                    item.setPanNo(rs.getString(22));
                    item.setInterestPayout8(rs.getString(23));
                    item.setNonCumulativeInt8(rs.getString(24));
                    item.setCumulatinveInt8(rs.getString(25));
                    item.setInterestPayout7(rs.getString(26));
                    item.setNonCumulativeInt7(rs.getString(27));
                    item.setCumulativeInt7(rs.getString(28));
                    item.setTotal(rs.getString(29));
                    item.setInOperativePan(rs.getString(30));
                    item.setNonFilers(rs.getString(31));
                    item.setAge(rs.getString(32));
                    item.setTaxableLimit(rs.getString(33));
                    item.setWaiverDocument(rs.getString(34));
                    item.setWaiverDocumentRefNo(rs.getString(35));
                    item.setRrbTdsRate(rs.getString(36));
                    item.setThresholdExceedAmount(rs.getString(37));
                    item.setThresholdExceedTdsAmount(rs.getString(38));
                    item.setExemptionRatePercent(rs.getString(39));
                    item.setExemptionAmount(rs.getString(40));
                    item.setExemptionTdsAmount(rs.getString(41));
                    item.setSurchargePercent(rs.getString(42));
                    item.setEduCessPercent(rs.getString(43));
                    item.setBaseRateAmt(rs.getString(44));
                    item.setSurchargeAmt(rs.getString(45));
                    item.setEduCessAmt(rs.getString(46));
                    item.setTdsAmount(rs.getString(47));
                    item.setTdsRecoveryAmount(rs.getString(48));
                    item.setTdsRefundAmount(rs.getString(49));
                    item.setIntRate(rs.getString(50));
                    item.setLastIntPaid(rs.getString(51));
                    item.setPendingInterest(rs.getString(52));
                    item.setCurrentHalfYearlyInterest(rs.getString(53));
                    item.setFutureInterest(rs.getString(54));
                    item.setGrossInterestAmount(rs.getString(55));
                    item.setIntPaidAmt(rs.getString(56));
                }

                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_TRAIL_RUN_ELIGIBLE_ACC",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<BondInterestTrialRunEligibleAccountsDto>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBlaModificationReport(BlaModificationReportDto blaModificationReportDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<BlaModificationReportDto> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;


        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, blaModificationReportDto.getSchemeCode());
            inParams.put(flag, blaModificationReportDto.getFlag());
            inParams.put(fromDate, blaModificationReportDto.getFromDate());
            inParams.put(toDate, blaModificationReportDto.getToDate());
            inParams.put(p_page_no_in, blaModificationReportDto.getPageNo());
            inParams.put(p_page_size_in, blaModificationReportDto.getPageSize());
            inParams.put(p_search_value_in, (null!=blaModificationReportDto.getSearchValue()&&!blaModificationReportDto.getSearchValue().trim().isEmpty())?blaModificationReportDto.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BlaModificationReportDto item = new BlaModificationReportDto();
                if (blaModificationReportDto.getSchemeCode().isEmpty()) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                }
                else if (blaModificationReportDto.getFlag().equalsIgnoreCase("D")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setInwardRefNo(rs.getString(3));
                    item.setInwardRefDate(rs.getString(4));
                    item.setBlaNo(rs.getString(5));
                    item.setApplicant(rs.getString(6));
                    item.setFirstApplicant(rs.getString(7));
                    item.setSecondApplicant(rs.getString(8));
                    item.setThirdApplicant(rs.getString(9));
                    item.setOldApplicant(rs.getString(10));
                    item.setOldFirstApplicant(rs.getString(11));
                    item.setOldSecondApplicant(rs.getString(12));
                    item.setOldThirdApplicant(rs.getString(13));
                    item.setAddress(rs.getString(14));
                    item.setAddressOne(rs.getString(15));
                    item.setAddressTwo(rs.getString(16));
                    item.setAddressThree(rs.getString(17));
                    item.setOldAddress(rs.getString(18));
                    item.setOldAddressOne(rs.getString(19));
                    item.setOldAddressTwo(rs.getString(20));
                    item.setOldAddressThree(rs.getString(21));
                    item.setModeOfHolding(rs.getString(22));
                    item.setOldModeOfHolding(rs.getString(23));
                    item.setOldDob(rs.getString(24));
                    item.setOldDobOne(rs.getString(25));
                    item.setOldDobTwo(rs.getString(26));
                    item.setOldDobThree(rs.getString(27));
                    item.setDob(rs.getString(28));
                    item.setDobOne(rs.getString(29));
                    item.setDobTwo(rs.getString(30));
                    item.setDobThree(rs.getString(31));
                    item.setModeOfPayment(rs.getString(32));
                    item.setOldModeOfPayment(rs.getString(33));
                    item.setAccountType(rs.getString(34));
                    item.setOldAccountType(rs.getString(35));
                    item.setAccountNo(rs.getString(36));
                    item.setOldAccountNo(rs.getString(37));
                    item.setMicr(rs.getString(38));
                    item.setOldMicr(rs.getString(39));
                    item.setApplStatus(rs.getString(40));
                    item.setOldApplStatus(rs.getString(41));
                    item.setPanNo(rs.getString(42));
                    item.setPanOne(rs.getString(43));
                    item.setPanTwo(rs.getString(44));
                    item.setPanThree(rs.getString(45));
                    item.setOldPanNo(rs.getString(46));
                    item.setPanOneOld(rs.getString(47));
                    item.setPanTwoOld(rs.getString(48));
                    item.setPanThreeOld(rs.getString(49));
                    item.setMakerId(rs.getString(50));
                    item.setMakerDate(rs.getString(51));
                    item.setCheckerId(rs.getString(52));
                    item.setCheckerDate(rs.getString(53));
                    item.setAuthorizedStatus(rs.getString(54));
                    item.setHdfcBranchCode(rs.getString(55));
                    item.setOldHdfcBranchCode(rs.getString(56));
                    item.setRemarks(rs.getString(57));
                    item.setEmail(rs.getString(58));
                    item.setEmailOne(rs.getString(59));
                    item.setEmailTwo(rs.getString(60));
                    item.setEmailThree(rs.getString(61));
                    item.setMobileNo(rs.getString(62));
                    item.setMobileNoOne(rs.getString(63));
                    item.setMobileNoTwo(rs.getString(64));
                    item.setMobileNoThree(rs.getString(65));
                    item.setOldEmail(rs.getString(66));
                    item.setOldEmailOne(rs.getString(67));
                    item.setOldEmailTwo(rs.getString(68));
                    item.setOldEmailThree(rs.getString(69));
                    item.setOldMobileNo(rs.getString(70));
                    item.setOldMobileNoOne(rs.getString(71));
                    item.setOldMobileNoTwo(rs.getString(72));
                    item.setOldMobileNoThree(rs.getString(73));
                    item.setNomineeOne(rs.getString(74));
                    item.setNomineeOneOld(rs.getString(75));
                    item.setNomineeTwo(rs.getString(76));
                    item.setNomineeTwoOld(rs.getString(77));
                    item.setNomineeThree(rs.getString(78));
                    item.setNomineeThreeOld(rs.getString(79));
                    item.setNomineeFour(rs.getString(80));
                    item.setNomineeFourOld(rs.getString(81));

                }
                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_rep_bla_modification_report",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<BlaModificationReportDto>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getInterestPaymentReport(InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<InterestPaymentToOtherBankDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, interestPaymentToOtherBankDTO.getSchemeCode());
            inParams.put(fromDate, interestPaymentToOtherBankDTO.getFromDate());
            inParams.put(toDate, interestPaymentToOtherBankDTO.getToDate());
            inParams.put(p_page_no_in, interestPaymentToOtherBankDTO.getPageNo());
            inParams.put(p_page_size_in, interestPaymentToOtherBankDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=interestPaymentToOtherBankDTO.getSearchValue()&&!interestPaymentToOtherBankDTO.getSearchValue().trim().isEmpty())?interestPaymentToOtherBankDTO.getSearchValue():null);


            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                InterestPaymentToOtherBankDTO item = new InterestPaymentToOtherBankDTO();
                if (interestPaymentToOtherBankDTO.getSchemeCode().isEmpty()) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                } else if (interestPaymentToOtherBankDTO.getSchemeCode() != null && interestPaymentToOtherBankDTO.getFromDate() != null && interestPaymentToOtherBankDTO.getToDate() != null) {
                    item.setPanNo(rs.getString(1));
                    item.setBondLedgerAccountNumber(rs.getString(2));
                    item.setBondSubscriptionId(rs.getString(3));
                    item.setSubscriptionAmount(rs.getString(4));
                    item.setSubscriptionDate(rs.getString(5));
                    item.setInterestPaidDate(rs.getString(6));
                    item.setInterestFromDate(rs.getString(7));
                    item.setInterestToDate(rs.getString(8));
                    item.setInterestPaymentMode(rs.getString(9));
                    item.setGrossInterest(rs.getString(10));
                    item.setTdsAmount(rs.getString(11));
                    item.setNetAmount(rs.getString(12));
                    item.setTdsCertificate(rs.getString(13));
                    item.setTdsExemptionLimit(rs.getString(14));
                    item.setCreditAccountNumber(rs.getString(15));
                    item.setLowTaxPercentage(rs.getString(16));
                    item.setCustomerName(rs.getString(17));
                    item.setMicrCode(rs.getString(18));
                    item.setIfcCode(rs.getString(19));
                    item.setPanStatus(rs.getString(20));
                    item.setItrStatus(rs.getString(21));
                    item.setBankName(rs.getString(22));

                }
                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_interest_payment_non_hdfc",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<InterestPaymentToOtherBankDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";


            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getTDSExemptionSubmitNonSubmitReport(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<TDSExemptionSubmitNonSubmitReportDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(panNumber, tdsExemptionSubmitNonSubmitReportDTO.getPanNumber());
            inParams.put(status, tdsExemptionSubmitNonSubmitReportDTO.getStatus());
            inParams.put(fromDate, tdsExemptionSubmitNonSubmitReportDTO.getFromDate());
            inParams.put(toDate, tdsExemptionSubmitNonSubmitReportDTO.getToDate());
            inParams.put(p_search_value_in, (null!=tdsExemptionSubmitNonSubmitReportDTO.getSearchValue()&&!tdsExemptionSubmitNonSubmitReportDTO.getSearchValue().trim().isEmpty())?tdsExemptionSubmitNonSubmitReportDTO.getSearchValue():null);
            inParams.put(p_page_no_in, tdsExemptionSubmitNonSubmitReportDTO.getPageNo());
            inParams.put(p_page_size_in, tdsExemptionSubmitNonSubmitReportDTO.getPageSize());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(status, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                TDSExemptionSubmitNonSubmitReportDTO item = new TDSExemptionSubmitNonSubmitReportDTO();
                if (tdsExemptionSubmitNonSubmitReportDTO.getStatus() == null) {
                    item.setPanNumber(rs.getString(1));
                    item.setCustomerName(rs.getString(2));

                } else if (tdsExemptionSubmitNonSubmitReportDTO.getStatus() != null) {
                    item.setCustomerName(rs.getString(1));
                    item.setPanNumber(rs.getString(2));
                    item.setTypeOfExemption(rs.getString(3));
                    item.setCertificateNo(rs.getString(4));
                    item.setTdsLimit(rs.getString(5));
                    item.setLowTaxPercentage(rs.getString(6));
                    item.setValidFromDate(rs.getString(7));
                    item.setValidToDate(rs.getString(8));
                    item.setFinancialYear(rs.getString(9));
                    item.setCertificateSubStatus(rs.getString(10));

                }
                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_EXE_SUB_NON_SUB",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<TDSExemptionSubmitNonSubmitReportDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getEtdsDetails(EtdsDTO etdsDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<EtdsDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords=null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(flag, etdsDTO.getFlag());
            inParams.put(p_page_no_in, etdsDTO.getPageNo());
            inParams.put(p_page_size_in, etdsDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=etdsDTO.getSearchValue()&&!etdsDTO.getSearchValue().trim().isEmpty())?etdsDTO.getSearchValue():null);
            inParams.put(fromDate, etdsDTO.getFromDate());
            inParams.put(toDate, etdsDTO.getToDate());
            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                EtdsDTO item = new EtdsDTO();
                if (etdsDTO.getFlag().equalsIgnoreCase("FY")) {
                    item.setFinancialYear(rs.getString(1));
                } else if (etdsDTO.getFlag().equalsIgnoreCase("M")) {
                    item.setMonthIn(rs.getString(1));
                } else if (etdsDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setChallanNo(rs.getString(1));
                    item.setDeducteeSrNo(rs.getString(2));
                    item.setPan(rs.getString(3));
                    item.setNameOfTheDeductee(rs.getString(4));
                    item.setSectionCode(rs.getString(5));
                    item.setDateOfPaymentOrCredit(rs.getString(6));
                    item.setAmountPaidCredited(rs.getString(7));
                    item.setAmountOfCashWithdrawalExceedingRs1Crore(rs.getString(8));
                    item.setAmountOfCashWithdrawalExceedingRs3Crore(rs.getString(9));
                    item.setTotalTaxDeducted(rs.getString(10));
                    item.setTotalTaxDeposited(rs.getString(11));
                    item.setRateAtWhichTaxDeducted(rs.getString(12));
                    item.setDateOfDeduction(rs.getString(13));
                    item.setReasonForNonDeductionLowerDeductionHigherDeductionThresholdTransporter(rs.getString(14));
                    item.setCertificateNo(rs.getString(15));
                    item.setUniqueIdentificationNumberOfForm121(rs.getString(16));
                    item.setTaxDeductedDeposited(rs.getString(17));
                    item.setReferenceNo(rs.getString(18));
                    item.setAddressLine1(rs.getString(19));
                    item.setAddressLine2(rs.getString(20));
                    item.setAddressLine3(rs.getString(21));
                    item.setCity(rs.getString(22));
                    item.setState(rs.getString(23));
                    item.setCountry(rs.getString(24));
                    item.setPinCode(rs.getString(25));
                    item.setEmailId(rs.getString(26));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_rep_etds_report",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<EtdsDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getSubsAmtBLAMaturedAccClosed(SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<SubsAmtBLAMaturedAccClosedDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, subsAmtBLAMaturedAccClosedDTO.getSchemeCode());
            inParams.put(flag, subsAmtBLAMaturedAccClosedDTO.getFlag());
            inParams.put(fromDate, subsAmtBLAMaturedAccClosedDTO.getFromDate());
            inParams.put(toDate, subsAmtBLAMaturedAccClosedDTO.getToDate());
            inParams.put(P_FROM_AMT_IN, subsAmtBLAMaturedAccClosedDTO.getFromAmount());
            inParams.put(P_TO_AMT_IN, subsAmtBLAMaturedAccClosedDTO.getToAmount());
            inParams.put(p_search_value_in, (null!=subsAmtBLAMaturedAccClosedDTO.getSearchValue()&&!subsAmtBLAMaturedAccClosedDTO.getSearchValue().trim().isEmpty())?subsAmtBLAMaturedAccClosedDTO.getSearchValue():null);
            inParams.put(p_page_no_in, subsAmtBLAMaturedAccClosedDTO.getPageNo());
            inParams.put(p_page_size_in, subsAmtBLAMaturedAccClosedDTO.getPageSize());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(P_FROM_AMT_IN, Types.VARCHAR),
                    new SqlParameter(P_TO_AMT_IN, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                SubsAmtBLAMaturedAccClosedDTO item = new SubsAmtBLAMaturedAccClosedDTO();
                if (subsAmtBLAMaturedAccClosedDTO.getSchemeCode().isEmpty() && subsAmtBLAMaturedAccClosedDTO.getFlag() == null) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (subsAmtBLAMaturedAccClosedDTO.getFlag() != null) {
                    if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("B")) {
                        if (subsAmtBLAMaturedAccClosedDTO.getFromAmount() != null && subsAmtBLAMaturedAccClosedDTO.getToAmount() != null) {
                            item.setBlaNumber(rs.getString(1));
                            item.setHolderName(rs.getString(2));
                            item.setAddress(rs.getString(3));
                            item.setIsNRI(rs.getString(4));
                            item.setIsMinor(rs.getString(5));
                            item.setModeOfHolding(rs.getString(6));
                            item.setHolderType(rs.getString(7));
                        } else if (subsAmtBLAMaturedAccClosedDTO.getFromAmount() == null || subsAmtBLAMaturedAccClosedDTO.getToAmount() == null) {
                            item.setBlaNumber(rs.getString(1));
                            item.setHolderName(rs.getString(2));
                            item.setModeOfHolding(rs.getString(3));
                        }
                    } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("S")) {
                        item.setPanNumber(rs.getString(1));
                        item.setHolderName(rs.getString(2));
                        item.setModeOfHolding(rs.getString(3));
                        item.setBlaNumber(rs.getString(4));
                        item.setSubscriptionID(rs.getString(5));
                        item.setDateOfSubscription(rs.getString(6));
                        item.setMaturityDate(rs.getString(7));
                        item.setSubscriptionAmount(rs.getString(8));
                    } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("M")) {
                        item.setPanNumber(rs.getString(1));
                        item.setHolderName(rs.getString(2));
                        item.setModeOfHolding(rs.getString(3));
                        item.setBlaNumber(rs.getString(4));
                        item.setSubscriptionID(rs.getString(5));
                        item.setDateOfSubscription(rs.getString(6));
                        item.setMaturityDate(rs.getString(7));
                        item.setSubscriptionAmount(rs.getString(8));
                    } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("A")) {
                        item.setPanNumber(rs.getString(1));
                        item.setHolderName(rs.getString(2));
                        item.setModeOfHolding(rs.getString(3));
                        item.setBlaNumber(rs.getString(4));
                        item.setSubscriptionID(rs.getString(5));
                        item.setDateOfSubscription(rs.getString(6));
                        item.setMaturityDate(rs.getString(7));
                        item.setSubscriptionAmount(rs.getString(8));
                        item.setAccountClosedDate(rs.getString(9));
                    }
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_BLA_MAT_CLS_SUB",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<SubsAmtBLAMaturedAccClosedDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
                responseDataDTO.setResult(resultMap);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getReportCancellationSubsCatCbt(ReportCancellationSubsCatCbtDTO reportCancellationSubsCatCbtDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> resultMap = new HashMap<>();
        List<ReportCancellationSubsCatCbtDTO> cancellationList = Collections.emptyList();

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, reportCancellationSubsCatCbtDTO.getSchemeCode());
            inParams.put(flag, reportCancellationSubsCatCbtDTO.getFlag());
            inParams.put(fromDate, reportCancellationSubsCatCbtDTO.getFromDate());
            inParams.put(toDate, reportCancellationSubsCatCbtDTO.getToDate());
            inParams.put(BOND_SUB_ID_IN, reportCancellationSubsCatCbtDTO.getBranchRefno());
            inParams.put(name, reportCancellationSubsCatCbtDTO.getHolderName());
            inParams.put(panNumber, reportCancellationSubsCatCbtDTO.getPanNo());
            inParams.put(blaNumber, reportCancellationSubsCatCbtDTO.getBla());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR)
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                ReportCancellationSubsCatCbtDTO item = new ReportCancellationSubsCatCbtDTO();
                if (reportCancellationSubsCatCbtDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (reportCancellationSubsCatCbtDTO.getSchemeCode() != null && reportCancellationSubsCatCbtDTO.getFlag() != null) {
                    switch (reportCancellationSubsCatCbtDTO.getFlag()) {
                        case "BR":
                            item.setHolderName(rs.getString(1));
                            item.setPanNo(rs.getString(2));
                            item.setModeofHolding(rs.getString(3));
                            item.setBranchRefno(rs.getString(4));
                            break;
                        case "P":
                            item.setHolderName(rs.getString(1));
                            item.setPanNo(rs.getString(2));
                            break;
                        case "C":
                            item.setBla(rs.getString(1));
                            item.setHolderName(rs.getString(2));
                            item.setModeofHolding(rs.getString(3));
                            item.setPanNo(rs.getString(4));
                            break;
                        case "A":
                            item.setSchemeName(rs.getString(1));
                            item.setModeofHolding(rs.getString(2));
                            item.setBla(rs.getString(3));
                            item.setHolderName(rs.getString(4));
                            item.setSubsamt(rs.getString(5));
                            item.setBondtype(rs.getString(6));
                            item.setAccOpenDate(rs.getString(7));
                            item.setValueDate(rs.getString(8));
                            item.setMaturityDate(rs.getString(9));
                            item.setRateofInterest(rs.getString(10));
                            item.setTransactionMode(rs.getString(11));
                            item.setRemarks(rs.getString(12));

                            item.setMakerId(rs.getString(13));
                            item.setMakerDate(rs.getString(14));
                            item.setCheckerId(rs.getString(15));
                            item.setCheckerDate(rs.getString(16));
                            item.setRefNo(rs.getString(17));
                            break;
                        case "B":
                            item.setSchemeName(rs.getString(1));
                            item.setModeofHolding(rs.getString(2));
                            item.setBla(rs.getString(3));
                            item.setHolderName(rs.getString(4));
                            item.setSubsamt(rs.getString(5));
                            item.setBondtype(rs.getString(6));
                            item.setAccOpenDate(rs.getString(7));
                            item.setValueDate(rs.getString(8));
                            item.setMaturityDate(rs.getString(9));
                            item.setRateofInterest(rs.getString(10));
                            item.setTransactionMode(rs.getString(11));
                            item.setRemarks(rs.getString(12));
                            item.setMakerId(rs.getString(13));
                            item.setMakerDate(rs.getString(14));
                            item.setCheckerId(rs.getString(15));
                            item.setCheckerDate(rs.getString(16));
                            item.setRefNo(rs.getString(17));
                            break;
                    }
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_cancel_subscription_cat_cbt",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            cancellationList = (List<ReportCancellationSubsCatCbtDTO>) result.get(curValOut);
            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(cancellationList)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", cancellationList);
                responseDataDTO.setResult(resultMap);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }


    @Override
    public ResponseDataDTO getBrokerageClaimMonthly(BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> resultMap = new HashMap<>();
        List<BrokerageClaimMonthlyDTO> list = Collections.emptyList();
        List<BrokerageClaimMonthlyDTO> list1 = Collections.emptyList();
        String totalRecords=null;
        final Double[] dirTot = {0.0d};
        final Double[] othersTot = {0.0d};
        final Double[] commissionTot = {0.0d};

        try {
            // Input parameters
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, brokerageClaimMonthlyDTO.getSchemeCode());
            inParams.put(flag, brokerageClaimMonthlyDTO.getFlag());
            inParams.put(agencyCode, brokerageClaimMonthlyDTO.getBrokerAgencyCode());
            inParams.put(fromDate, brokerageClaimMonthlyDTO.getFromDate());
            inParams.put(toDate, brokerageClaimMonthlyDTO.getToDate());
            inParams.put(p_page_no_in, brokerageClaimMonthlyDTO.getPageNo());
            inParams.put(p_page_size_in, brokerageClaimMonthlyDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=brokerageClaimMonthlyDTO.getSearchValue()&&!brokerageClaimMonthlyDTO.getSearchValue().trim().isEmpty())?brokerageClaimMonthlyDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(agencyCode, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(currVal1, OracleTypes.CURSOR),
                    new SqlOutParameter(currVal2, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BrokerageClaimMonthlyDTO item = new BrokerageClaimMonthlyDTO();
                if (brokerageClaimMonthlyDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                } else if (brokerageClaimMonthlyDTO.getFlag().equalsIgnoreCase("A")) {
                    item.setBrokerAgencyCode(rs.getString(1));
                    item.setBrokerName(rs.getString(2));
                    item.setVerifystatus(rs.getString(3));
                } else if (brokerageClaimMonthlyDTO.getFlag().equalsIgnoreCase("Y")) {
                    item.setDirGrossAmt(rs.getString(2));
                    item.setDirNetAmt(rs.getString(3));
                    item.setDirGst(rs.getString(4));
                    item.setOthersGrossAmt(rs.getString(5));
                    item.setOthersNetAmt(rs.getString(6));
                    item.setOthersGst(rs.getString(7));
                    item.setCommissionGross(rs.getString(8));
                    item.setCommissionNet(rs.getString(9));
                    item.setCommissionGst(rs.getString(10));

                    Double brokerGross = rs.getDouble(2);
                    Double brokerNet = rs.getDouble(5);
                    Double brokerGsd = rs.getDouble(8);
                    dirTot[0] = brokerGross + brokerNet + brokerGsd;


                    Double brokerGross1 = rs.getDouble(3);
                    Double brokerNet1 = rs.getDouble(6);
                    Double brokerGsd1 = rs.getDouble(9);
                    othersTot[0] = brokerGross1 + brokerNet1 + brokerGsd1;


                    Double brokerGross2 = rs.getDouble(4);
                    Double brokerNet2 = rs.getDouble(7);
                    Double brokerGsd2 = rs.getDouble(10);
                    commissionTot[0] = brokerGross2 + brokerNet2 + brokerGsd2;

                }
                return item;

            });

            rowMappers.put(currVal2, (rs, rowNum) -> {
                BrokerageClaimMonthlyDTO item = new BrokerageClaimMonthlyDTO();
                if (brokerageClaimMonthlyDTO.getFlag().equalsIgnoreCase("Y")) {
                    item.setMaturityDate(rs.getString(1));
                    item.setBlaNo(rs.getString(2));
                    item.setInvestAmt(rs.getString(3));
                    item.setCasTransferDate(rs.getString(4));
                    item.setScheme(rs.getString(5));
                    item.setPanNo(rs.getString(6));
                    item.setBankName(rs.getString(7));
                    item.setAppName(rs.getString(8));
                    item.setAddrs1(rs.getString(9));
                    item.setAddrs2(rs.getString(10));
                    item.setCfd(rs.getString(11));
                    item.setCity(rs.getString(12));
                    item.setState(rs.getString(13));
                    item.setPincode(rs.getString(14));
                    item.setRcptDate(rs.getString(15));
                    item.setBranchrefno(rs.getString(16));
                    item.setBranchName(rs.getString(17));
                    item.setGroupDate(rs.getString(18));
                    item.setHdfcBrokerCode(rs.getString(19));
                    item.setBrokerName(rs.getString(20));
                    item.setAddr1(rs.getString(21));
                    item.setAccTypeCode(rs.getString(22));
                    item.setAppNo(rs.getString(23));
                    item.setAddr2(rs.getString(24));
                    item.setCity1(rs.getString(25));
                    item.setState1(rs.getString(26));
                    item.setAdviceNo(rs.getString(27));
                    item.setPincode1(rs.getString(28));
                    item.setPhone(rs.getString(29));
                    item.setPaymentMode(rs.getString(30));
                    item.setBankAcc(rs.getString(31));
                    item.setMicrCode(rs.getString(32));
                    item.setStatus(rs.getString(33));
                    item.setDeStatus(rs.getString(34));
                    item.setGrossBrok(rs.getString(35));
                    item.setNetBrok(rs.getString(36));
                    item.setGst1(rs.getString(37));
                    item.setBrokerAmt(rs.getString(38));
                }
                return item;

            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_brk_inc_exp",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            list = (List<BrokerageClaimMonthlyDTO>) result.get(curValOut);
            list1 = (List<BrokerageClaimMonthlyDTO>) result.get(currVal2);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list) && CollectionUtils.isEmpty(list1)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                resultMap.put("list1", list1);
                resultMap.put("total", dirTot[0]);
                resultMap.put("otherstotal", othersTot[0]);
                resultMap.put("commissiontotal", commissionTot[0]);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }

        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getMinorAccountDetails(MinorAccountDetailsDTO minorAccountDetailsDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> resultMap = new HashMap<>();
        List<MinorAccountDetailsDTO> list = Collections.emptyList();
        String totalRecords = null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, minorAccountDetailsDTO.getSchemeCode());
            inParams.put(flag, minorAccountDetailsDTO.getFlag());
            inParams.put(p_page_no_in, minorAccountDetailsDTO.getPageNo());
            inParams.put(p_page_size_in, minorAccountDetailsDTO.getPageSize());
            inParams.put(p_search_value_in, minorAccountDetailsDTO.getSearchValue());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                MinorAccountDetailsDTO item = new MinorAccountDetailsDTO();
                if (minorAccountDetailsDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                } else if (minorAccountDetailsDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNo(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setSubsId(rs.getString(3));
                    item.setGuadId(rs.getString(4));
                    item.setGuadName(rs.getString(5));
                    item.setHolderType(rs.getString(6));
                    Date date = rs.getDate(7);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    item.setInvestmentDate(sdf.format(date));
item.setAmount(rs.getString(8));
Date mdate = rs.getDate(9);
item.setMinorDOB(sdf.format(mdate));
item.setMinorAge(rs.getString(10));

                }
                return item;

            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_MIN_ACC_DET",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            list = (List<MinorAccountDetailsDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }

        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBLASubscriptionNominationDetails(BLASubscriptionNominationDTO blaSubscriptionNominationDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<BLASubscriptionNominationDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, blaSubscriptionNominationDTO.getSchemeCode());
            inParams.put(flag, blaSubscriptionNominationDTO.getFlag());
            inParams.put(fromDate, blaSubscriptionNominationDTO.getFromDate());
            inParams.put(toDate, blaSubscriptionNominationDTO.getToDate());
            inParams.put(name, blaSubscriptionNominationDTO.getHolderName());
            inParams.put(panNumber, blaSubscriptionNominationDTO.getPanNumber());
            inParams.put(blaNumber, blaSubscriptionNominationDTO.getBlaNumber());
            inParams.put(P_SUBSCRIPTION_ID_IN, blaSubscriptionNominationDTO.getSubscriptionID());
            inParams.put(p_search_value_in, (null!=blaSubscriptionNominationDTO.getSearchValue()&&!blaSubscriptionNominationDTO.getSearchValue().isEmpty())?blaSubscriptionNominationDTO.getSearchValue():null);
            inParams.put(p_page_no_in, blaSubscriptionNominationDTO.getPageNo());
            inParams.put(p_page_size_in, blaSubscriptionNominationDTO.getPageSize());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(P_SUBSCRIPTION_ID_IN, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),

            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BLASubscriptionNominationDTO item = new BLASubscriptionNominationDTO();
                if (blaSubscriptionNominationDTO.getFlag().equals("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (blaSubscriptionNominationDTO.getFlag().equals("P")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNumber(rs.getString(2));
                } else if (blaSubscriptionNominationDTO.getFlag().equals("B")) {
                    item.setModeOfHolding(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setPanNumber(rs.getString(3));
                    item.setBlaNumber(rs.getString(4));
                } else if (blaSubscriptionNominationDTO.getFlag().equals("BR")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNumber(rs.getString(2));
                    item.setModeOfHolding(rs.getString(3));
                    item.setSubscriptionID(rs.getString(4));
                } else if (blaSubscriptionNominationDTO.getFlag().equals("D")) {
                    item.setBondHolderAadhaar(rs.getString(1));// AADHAR_NO - 1
                    item.setBondHolderAccountTypeCode(rs.getString(2));
                    item.setBondHolderCommunicationAddress1(rs.getString(3));
                    item.setBondHolderAddress2(rs.getString(4));
                    item.setBondHolderAddress3(rs.getString(5));
                    // AUDITABLE  - 6
                    item.setBankCode(rs.getString(7));
                    item.setBondHolderBirthCertificate(rs.getString(8));
                    // BLA_GROUP_ID - 9
                    item.setBondHolderBlaNumber(rs.getString(10));
                    item.setBondHolderDateOfBirth(rs.getString(11));
                    item.setBondHolderFirstName(rs.getString(12));
                    item.setBondHolderLastName(rs.getString(13));
                    item.setBondHolderMiddleName(rs.getString(14));
                    item.setBranchCode(rs.getString(15));
                    // CBS_CIF_ID - 16
                    item.setBondHolderCity(rs.getString(17));
                    // CREATED_BY - 18
                    // CREATED_DATE - 19
                    item.setBondHolderDetailForOthers(rs.getString(20));
                    item.setBondHolderDetailsOfBirthCertificate(rs.getString(21));
                    item.setBondHolderDrivingLicence(rs.getString(22));
                    item.setBondHolderEmailID(rs.getString(23));
                    item.setBondHolderFatherName(rs.getString(24));
                    item.setBondHolderFTSApplicationNumber(rs.getString(25));// FTS_APP_NUM
                    item.setBondHolderGender(rs.getString(26));
                    item.setBondHolderHolderType(rs.getString(27));
                    item.setBondHolderHUF(rs.getString(28));
                    item.setBondHolderIFSCCode(rs.getString(29));
                    //IS_ACTIVE - 30
                    //IS_DELETED -31
                    item.setBondHolderIsMinor(rs.getString(32));
                    //JOINT_HLDR_TYPE-33
                    //LAST_MOD_BY-34
                    //LAST_MOD_DATE-35
                    item.setBondHolderMICR(rs.getString(36));//MICR_CODE
                    item.setBondHolderMobileNumber(rs.getString(37));
                    item.setModeOfHolding(rs.getString(38));
                    item.setBondHolderModeOfInterestPayment(rs.getString(39));
                    item.setBondHolderMotherName(rs.getString(40)); //Guardian details
                    item.setBondHolderNameOfPayee(rs.getString(41));//NAME_OF_THE_PAYEE
                    //NO_OF_JOINT_HOLDERS-42
                    item.setBondHolderOperativeAccountNumber(rs.getString(43));
                    //OPER_ACC_NO_INT-44
                    item.setBondHolderOthers(rs.getString(45));
                    item.setBondHolderPANCard(rs.getString(46));
                    item.setThirdPANNo(rs.getString(47));
                    item.setBondHolderPANNo(rs.getString(48));
                    item.setSecondPANNo(rs.getString(49));
                    item.setBondHolderPassport(rs.getString(50));
                    item.setBondHolderPhoneNumber(rs.getString(51));
                    item.setBondHolderPinCode(rs.getString(52));//Other details
                    item.setBondHolderPowerOfAttorney(rs.getString(53));
                    item.setBondHolderRationCard(rs.getString(54));
                    item.setBondHolderRemarks(rs.getString(55));
                    item.setSchemeCode(rs.getString(56));
                    item.setBondHolderSeniorCitizenCard(rs.getString(57));
                    item.setBondHolderState(rs.getString(58));//Witness details
                    //VERIFIED_BY--59
                    //VERIFIED_DATE-60
                    //VERIFIED_STATUS-61
                    item.setBondHolderVoterIDCard(rs.getString(62));
                    item.setBondHolderWitnessAddress1(rs.getString(63));
                    item.setBondHolderWitnessAddress2(rs.getString(64));
                    item.setBondHolderWhetherHDFCCustomer(rs.getString(65));//documents details
                    item.setBondHolderWitnessName1(rs.getString(66));
                    item.setBondHolderWitnessName2(rs.getString(67));
                    // TB010.AUDITABLE AS CO_AUDITABLE,-68
                    // TB010.BLA_GROUP_ID AS CO_BLA_GROUP_ID,-69
                    item.setBondHolderCoName(rs.getString(70));// TB010.COPARCENERS_NAME AS CO_COPARCENERS_NAME,
                    // TB010.CREATED_BY AS CO_CREATED_BY,-71
                    // TB010.CREATED_DATE AS CO_CREATED_DATE,-72
                    item.setBondHolderCoDOB(rs.getString(73));//TB010.DOB AS CO_DOB,
                    // TB010.IS_ACTIVE AS CO_IS_ACTIVE,-74
                    // TB010.IS_DELETED AS CO_IS_DELETED,-75
                    // TB010.LAST_MOD_BY AS CO_LAST_MOD_BY,-76
                    // TB010.LAST_MOD_DATE AS CO_LAST_MOD_DATE,-77
                    item.setBondHolderCoRelationshipWithKarta(rs.getString(78));// TB010.RELATION_WITH_KARTA AS CO_RELATION_WITH_KARTA,
                    // TB010.SCH_CODE AS CO_SCH_CODE,-79
                    // TB010.VERIFIED_BY AS CO_VERIFIED_BY,-80
                    // TB010.VERIFIED_DATE AS CO_VERIFIED_DATE,-81
                    // TB010.VERIFIED_STATUS AS CO_VERIFIED_STATUS,-82
                    // TB011.AUDITABLE AS GUARD_AUDITABLE,-83
                    // TB011.BLA_GROUP_ID AS GUARD_BLA_GROUP_ID,-84
                    // TB011.CREATED_BY AS GUARD_CREATED_BY,-85
                    // TB011.CREATED_DATE AS GUARD_CREATED_DATE,-86
                    item.setBondHolderGIfRequestFromCurrentGuardian(rs.getString(87)); //TB011.CUR_GUARD_REQ AS GUARD_CUR_GUARD_REQ,
                    item.setBondHolderGDateOfDeath(rs.getString(88));//TB011.DATE_OF_DEATH_GUARD AS GUARD_DATE_OF_DEATH_GUARD,
                    item.setBondHolderGDateOfReceipt(rs.getString(89));//TB011.DATE_OF_RECEI_DEATH_CERTI AS GUARD_DATE_OF_RECEI_DEATH_CERTI,
                    item.setBondHolderGID(rs.getString(90));//TB011.FETCH_GUARD_ID AS GUARD_FETCH_GUARD_ID,
                    item.setBondHolderGAddress(rs.getString(91));//TB011.GUARD_COM_ADDR AS GUARD_GUARD_COM_ADDR,
                    item.setBondHolderGDateOfBirth(rs.getString(92));//TB011.GUARD_DATE_OF_BIRTH AS GUARD_GUARD_DATE_OF_BIRTH,
                    item.setBondHolderGEmailID(rs.getString(93));//TB011.GUARD_EMAIL_ID AS GUARD_GUARD_EMAIL_ID,
                    item.setBondHolderGFirstName(rs.getString(94));//TB011.GUARD_FIRST_NAME AS GUARD_GUARD_FIRST_NAME,
                    item.setBondHolderGID(rs.getString(95)); //TB011.GUARD_ID AS GUARD_GUARD_ID,
                    item.setBondHolderGLastName(rs.getString(96));//TB011.GUARD_LAST_NAME AS GUARD_GUARD_LAST_NAME,
                    item.setBondHolderGMiddleName(rs.getString(97));//TB011.GUARD_MIDDLE_NAME AS GUARD_GUARD_MIDDLE_NAME,
                    item.setBondHolderGMobile(rs.getString(98));//TB011.GUARD_MOBILE_NO AS GUARD_GUARD_MOBILE_NO,
                    // TB011.IS_ACTIVE AS GUARD_IS_ACTIVE,-99
                    // TB011.IS_DELETED AS GUARD_IS_DELETED,-100
                    // TB011.JOINT_HLDR_TYPE AS GUARD_JOINT_HLDR_TYPE,-101
                    // TB011.LAST_MOD_BY AS GUARD_LAST_MOD_BY,-102
                    // TB011.LAST_MOD_DATE AS GUARD_LAST_MOD_DATE,-103
                    item.setBondHolderGRelationWithMinor(rs.getString(104));//TB011.RELATION_WITH_MINOR AS GUARD_RELATION_WITH_MINOR,
                    item.setBondHolderGRemarks(rs.getString(105));//TB011.REMARKS AS GUARD_REMARKS,
                    // TB011.SCH_CODE AS GUARD_SCH_CODE,-106
                    // TB011.VERIFIED_BY AS GUARD_VERIFIED_BY,-107
                    // TB011.VERIFIED_DATE AS GUARD_VERIFIED_DATE,-108
                    // TB011.VERIFIED_STATUS AS GUARD_VERIFIED_STATUS,-109
                    item.setAccountOpenDate(rs.getString(110));//TB012.ACCOUNT_OPEN_DATE,
                    item.setAmountInWords(rs.getString(111));//TB012.AMOUNT_IN_WORDS,
                    // TB012.AUDITABLE,-112
                    item.setBondHolderBankCode(rs.getString(113));//                   TB012.BANK_CODE_NON_MICR,
                    item.setSubscriptionID(rs.getString(114));//TB012.BOND_SUBSCRIPTION_ID,
                    item.setBondType(rs.getString(115));//TB012.BOND_TYPE,
                    item.setBankCode(rs.getString(116));//                  TB012.BRANCH_CODE_NON_MICR,
                    item.setBranchCode(rs.getString(117));//TB012.BRANCH_CODE_WBO,
                    item.setBranchName(rs.getString(118)); //TB012.BRANCH_NAME_WBO,
                    item.setBrokerID(rs.getString(119)); //TB012.BROKER_ID,
                    //  TB012.CREATED_BY,-120
                    //  TB012.CREATED_DATE,-121
                    item.setFirstJointHolderName(rs.getString(122));//TB012.FIRST_JOINT_BLA_NAME,
                    item.setFirstJointBlaNumber(rs.getString(123));//TB012.FIRST_JOINT_BLA_NO,
                    item.setInstrumentAmount(rs.getString(124));//TB012.INSTRUMENT_AMT,
                    item.setInstrumentDate(rs.getString(125));//TB012.INSTRUMENT_DATE,
                    item.setInstrumentNumber(rs.getString(126));//TB012.INSTRUMENT_NUMBER,
                    item.setInstrumentType(rs.getString(127));//TB012.INSTRUMENT_TYPE,
                    // TB012.IS_ACTIVE,-128
                    // TB012.IS_DELETED,-129
                    item.setDoYouWantToAddNominee(rs.getString(130));//TB012.IS_NOMINEE,
                    // TB012.LAST_MOD_BY,-131
                    // TB012.LAST_MOD_DATE,-132
                    item.setMaturityAmount(rs.getString(133));//                            TB012.MATURITY_AMT,
                    item.setMaturityDate(rs.getString(134));//TB012.MATURITY_DATE,
                    item.setModeOfHolding(rs.getString(135));//TB012.MODE_OF_HOLDING,
                    item.setPeriodOfDeposit(rs.getString(136));//TB012.PERIOD_OF_DEPOSIT,
                    item.setRateOfInterest(rs.getString(137));//TB012.RATE_OF_INTEREST,
                    item.setRemarks(rs.getString(138));  //TB012.REMARKS,
                    // TB012.SCH_CODE,-139
                    item.setSecondJointBlaNumber(rs.getString(140));// TB012.SEC_JOINT_BA_NO,
                    item.setSecondJointHolderName(rs.getString(141));//TB012.SEC_JOINT_BLA_NAME,
                    item.setSegment(rs.getString(142)); //TB012.SEGMENT,
                    // TB012.SINGLE_BLA_NAME,-143
                    // TB012.SINGLE_BLA_NO,-144
                    item.setSortCode(rs.getString(145));//TB012.SORT_CODE,
                    item.setSubscriptionAmount(rs.getString(146));//TB012.SUBSCRIPTION_AMOUNT,
                    item.setThirdJointHolderName(rs.getString(147));//TB012.THIRD_JOINT_BLA_NAME,
                    item.setThirdJointBlaNumber(rs.getString(148));//TB012.THIRD_JOINT_BLA_NO,
                    item.setDebitAccountNumber(rs.getString(149));//TB012.TNF_DEC_ACC_NUM,
                    item.setTranCode(rs.getString(150));//TB012.TRAN_CODE,
                    item.setTransactionMode(rs.getString(151));//TB012.TRAN_MODE,
                    item.setValueDate(rs.getString(152));//TB012.VALUE_DATE,
                    //TB012.VERIFIED_BY,-153
                    //TB012.VERIFIED_DATE,-154
                    //TB012.VERIFIED_STATUS,-155
                    item.setZoneCode(rs.getString(156));//TB012.ZONE_CODE,
                    item.setZoneDate(rs.getString(157));//TB012.ZONE_DATE,
                    item.setNomineeAddress1(rs.getString(158));//TB013.ADDRESS1 AS NOM_ADDRESS1,
                    item.setNomineeAddress2(rs.getString(159));//TB013.ADDRESS2 AS NOM_ADDRESS2,
//                            TB013.AUDITABLE AS NOM_AUDITABLE,-160
                    item.setSubscriptionID(rs.getString(161));//TB013.BOND_SUBSCRIPTION_ID AS NOM_BOND_SUBSCRIPTION_ID,
                    item.setNomineeCity(rs.getString(162));//TB013.CITY AS NOM_CITY,
//                    TB013.CREATED_BY AS NOM_CREATED_BY,-163
//                            TB013.CREATED_DATE AS NOM_CREATED_DATE,-164
                    item.setNomineeDateOfBirth(rs.getString(165));//TB013.DATE_OF_BIRTH_OF_MINOR AS NOM_DATE_OF_BIRTH_OF_MINOR,
                    item.setNomineeGFirstName(rs.getString(166));//TB013.GUARDIAN_FIRST_NAME AS NOM_GUARDIAN_FIRST_NAME,
                    item.setNomineeGLastName(rs.getString(167));//TB013.GUARDIAN_LAST_NAME AS NOM_GUARDIAN_LAST_NAME,
                    item.setNomineeGMiddleName(rs.getString(168));//TB013.GUARDIAN_MIDDLE_NAME AS NOM_GUARDIAN_MIDDLE_NAME,
                    item.setNomineeGAddress1(rs.getString(169));//TB013.GUARD_ADDRESS1 AS NOM_GUARD_ADDRESS1,
                    item.setNomineeGAddress2(rs.getString(170));//TB013.GUARD_ADDRESS2 AS NOM_GUARD_ADDRESS2,
                    item.setNomineeGCity(rs.getString(171));//TB013.GUARD_CITY AS NOM_GUARD_CITY,
                    item.setNomineeGEmailID(rs.getString(172));//TB013.GUARD_EMAIL_ID AS NOM_GUARD_EMAIL_ID,
                    item.setNomineeGMobileNo(rs.getString(173));//TB013.GUARD_MOB_NO AS NOM_GUARD_MOB_NO,
                    item.setNomineeGPINCode(rs.getString(174));//TB013.GUARD_PIN_CODE AS NOM_GUARD_PIN_CODE,
                    item.setNomineeGState(rs.getString(175));//TB013.GUARD_STATE AS NOM_GUARD_STATE,
//TB013.IS_ACTIVE AS NOM_IS_ACTIVE, 176
//TB013.IS_DELETED AS NOM_IS_DELETED,177
//TB013.IS_MOD_DEL AS NOM_IS_MOD_DEL,178
                    item.setNomineeIsNomineeMinor(rs.getString(179));//TB013.IS_NOMINEE_MINOR AS NOM_IS_NOMINEE_MINOR,
//TB013.LAST_MOD_BY AS NOM_LAST_MOD_BY,180
//TB013.LAST_MOD_DATE AS NOM_LAST_MOD_DATE,181
                    //TB013.NOMINATION_ID AS NOM_NOMINATION_ID,182
                    item.setNomineeFirstName(rs.getString(183));//TB013.NOMINEE_FIRST_NAME AS NOM_NOMINEE_FIRST_NAME,
                    item.setNomineeLastName(rs.getString(184));//TB013.NOMINEE_LAST_NAME AS NOM_NOMINEE_LAST_NAME,
                    item.setNomineeMiddleName(rs.getString(185));//TB013.NOMINEE_MID_NAME AS NOM_NOMINEE_MID_NAME,
                    item.setNomineeOperativeAccountNumber(rs.getString(186));//TB013.OPERATIVE_ACCOUNT_NO AS NOM_OPERATIVE_ACCOUNT_NO,
                    item.setNomineePhoneNo(rs.getString(187));//TB013.PHONE_NO AS NOM_PHONE_NO,
                    item.setNomineePinCode(rs.getString(188));//TB013.PIN_CODE AS NOM_PIN_CODE,
                    item.setNomineeRelationWithHolder(rs.getString(189));//TB013.RELATION_WITH_HOLDER AS NOM_RELATION_WITH_HOLDER,
                    // TB013.SCH_CODE AS NOM_SCH_CODE,190
                    item.setNomineeSharePercentage(rs.getString(191));//TB013.SHARE_PERCENTAGE AS NOM_SHARE_PERCENTAGE,
                    item.setNomineeState(rs.getString(192));//TB013.STATE AS NOM_STATE,
                    // TB013.STATUS AS NOM_STATUS,193
                    // TB013.VERIFIED_BY AS NOM_VERIFIED_BY,194
                    // TB013.VERIFIED_DATE AS NOM_VERIFIED_DATE,195
                    // TB013.VERIFIED_STATUS AS NOM_VERIFIED_STATUS -196
                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_bla_sub_nom_det_rep",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<BLASubscriptionNominationDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAcknowledgementconfirmation(AcknowledgementconfirmationDTO acknowledgementconfirmationDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        List<AcknowledgementconfirmationDTO> list = Collections.emptyList();
        String totalRecords = null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, acknowledgementconfirmationDTO.getSchemeCode());
            inParams.put(inout, acknowledgementconfirmationDTO.getStatusIO());
            inParams.put(flag, acknowledgementconfirmationDTO.getFlag());
            inParams.put(fromDate, acknowledgementconfirmationDTO.getFromDate());
            inParams.put(toDate, acknowledgementconfirmationDTO.getToDate());
            inParams.put(blaNumber, acknowledgementconfirmationDTO.getBlaNo());
            inParams.put(inward, acknowledgementconfirmationDTO.getInwardRefNo());
            inParams.put(p_page_no_in, acknowledgementconfirmationDTO.getPageNo());
            inParams.put(p_page_size_in, acknowledgementconfirmationDTO.getPageSize());
            inParams.put(p_search_value_in, acknowledgementconfirmationDTO.getSearchValue());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(inout, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(inward, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                AcknowledgementconfirmationDTO item = new AcknowledgementconfirmationDTO();
                if (acknowledgementconfirmationDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                    return item;

                } else if (acknowledgementconfirmationDTO.getFlag().equalsIgnoreCase("AR")) {
                    item.setBlaNo(rs.getString(1));
                    item.setFts(rs.getString(2));
                    item.setSchemeName(rs.getString(3));
                    item.setInwardRefNo(rs.getString(4));
                    item.setInwardDate(rs.getString(5));
                    item.setOutwardRefNo(rs.getString(6));
                    item.setOutwardDate(rs.getString(7));
                    item.setOutwardReason(rs.getString(8));
                    return item;
                }else if (acknowledgementconfirmationDTO.getFlag().equalsIgnoreCase("A")) {
                    item.setBlaNo(rs.getString(1));
                    item.setInwardRefNo(rs.getString(2));
                    item.setInwardDate(rs.getString(3));
                    item.setBranchRefNo(rs.getString(4));
                    item.setPanNo(rs.getString(5));
                    item.setAccNo(rs.getString(6));
                    item.setMicrCode(rs.getString(7));
                    item.setHolderName(rs.getString(8));
                    item.setBankName(rs.getString(9));
                    item.setDocumentSubmitted(rs.getString(10));
                    item.setValidUp(rs.getString(11));
                    item.setAddResssOne(rs.getString(12));
                    item.setAddResssTwo(rs.getString(13));
                    item.setAddResssThree(rs.getString(14));
                    item.setCity(rs.getString(15));
                    item.setState(rs.getString(16));
                    item.setPinCode(rs.getString(17));
                    item.setNominationRefNo(rs.getString(18));
                    item.setNominationRefNoTwo(rs.getString(19));
                    item.setNominationRefNoThree(rs.getString(20));
                    item.setNominationRefNoFour(rs.getString(21));
                    item.setNomineeName(rs.getString(22));
                    item.setNomineeNameTwo(rs.getString(23));
                    item.setNomineeNameThree(rs.getString(24));
                    item.setNomineeNameFour(rs.getString(25));







                    return item;
                } else if (acknowledgementconfirmationDTO.getBlaNo() != null && acknowledgementconfirmationDTO.getFlag().equalsIgnoreCase("R")) {
                    item.setBlaNo(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setAddResssOne(rs.getString(3));
                    item.setAddResssTwo(rs.getString(4));
                    item.setAddResssThree(rs.getString(5));
                    item.setCity(rs.getString(6));
                    item.setState(rs.getString(7));
                    item.setPinCode(rs.getString(8));

                    return item;
                }
                throw new IllegalArgumentException("Invalid flag: " + acknowledgementconfirmationDTO.getFlag());

            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_Ack_Updation_Rejection",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            list = (List<AcknowledgementconfirmationDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            responseDataDTO.setResult(list);
            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {
                responseDataDTO.setResult(list);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAcknowledgement(AcknowledgementDTO acknowledgementDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> resultMap = new HashMap<>();
        List<BrokerageClaimMonthlyDTO> list = Collections.emptyList();

        try {
            // Input parameters
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, acknowledgementDTO.getSchemeCode());
            inParams.put(flag, acknowledgementDTO.getFlag());
            inParams.put(fromDate, acknowledgementDTO.getFromDate());
            inParams.put(toDate, acknowledgementDTO.getToDate());


            // Define SQL parameters
            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR)
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                AcknowledgementDTO item = new AcknowledgementDTO();
                if (acknowledgementDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                } else if (acknowledgementDTO.getFlag().equalsIgnoreCase("T")) {
                    item.setBlaNo(rs.getString(1));
                    item.setBondType(rs.getString(2));
                    item.setName(rs.getString(3));
                    item.setBankName(rs.getString(4));
                    item.setAccNo(rs.getString(5));
                    item.setMicrCode(rs.getString(6));
                    item.setCohNo(rs.getString(7));
                    item.setNominationUnder(rs.getString(8));
                    item.setTransactionDate(rs.getString(9));
                    item.setHolderName(rs.getString(10));
                    item.setAddress(rs.getString(11));
                    item.setAddressTwo(rs.getString(12));
                    item.setAddressThree(rs.getString(13));
                    item.setState(rs.getString(14));
                    item.setCity(rs.getString(15));
                    item.setPincode(rs.getString(16));
                    item.setGuardianName(rs.getString(17));
                    item.setBranchRefno(rs.getString(18));
                    item.setStatus(rs.getString(19));
                }
                return item;

            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_Rep_Ack_of_App_Nom",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            list = (List<BrokerageClaimMonthlyDTO>) result.get(curValOut);
            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);

                responseDataDTO.setResult(resultMap);
            }

        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getFreezeUnfreezeDetails(FreezeUnFreezeDTO freezeUnFreezeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<FreezeUnFreezeDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, freezeUnFreezeDTO.getSchemeCode());
            inParams.put(flag, freezeUnFreezeDTO.getFlag());
            inParams.put(fromDate, freezeUnFreezeDTO.getFromDate());
            inParams.put(toDate, freezeUnFreezeDTO.getToDate());
            inParams.put(name, freezeUnFreezeDTO.getHolderName());
            inParams.put(panNumber, freezeUnFreezeDTO.getPanNumber());
            inParams.put(blaNumber, freezeUnFreezeDTO.getBlaNumber());
            inParams.put(p_search_value_in, freezeUnFreezeDTO.getSearchValue());
            inParams.put(p_page_no_in, freezeUnFreezeDTO.getPageNo());
            inParams.put(p_page_size_in, freezeUnFreezeDTO.getPageSize());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),

            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                FreezeUnFreezeDTO item = new FreezeUnFreezeDTO();
                if (freezeUnFreezeDTO.getFlag().equals("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (freezeUnFreezeDTO.getFlag().equals("P")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNumber(rs.getString(2));
                } else if (freezeUnFreezeDTO.getFlag().equals("B")) {
                    item.setModeOfHolding(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setPanNumber(rs.getString(3));
                    item.setBlaNumber(rs.getString(4));
                } else if (freezeUnFreezeDTO.getFlag().equals("D")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNumber(rs.getString(2));
                    item.setBlaNumber(rs.getString(3));
                    item.setFreeze(rs.getString(4));
                    item.setFreezeDate(rs.getString(5));
                    item.setUnFreeze(rs.getString(6));
                    item.setUnFreezeDate(rs.getString(7));
                    item.setModeOfHolding(rs.getString(8));
                    item.setPrincipalAmountFrozen(rs.getString(9));
                    item.setInterestPaymentFrozen(rs.getString(10));
                    item.setIsTransferringOutAllowed(rs.getString(11));
                    item.setReasonFromMaker(rs.getString(12));
                    item.setReasonFromChecker(rs.getString(13));
                    item.setSubscriptionId(rs.getString(14));
                    item.setInvestmentAmount(rs.getString(15));
                    item.setInvestmentDate(rs.getString(16));
                    item.setMakerId(rs.getString(17));
                    item.setMakerDate(rs.getString(18));
                    item.setCheckerId(rs.getString(19));
                    item.setCheckerDate(rs.getString(20));
                    item.setFreezeAuth(rs.getString(21));


                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_FRZ_UNFRZ_REPORT",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<FreezeUnFreezeDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getReportOnUnverifiedDetails(ReportOnUnverifiedDTO reportOnUnverifiedDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<ReportOnUnverifiedDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, reportOnUnverifiedDTO.getSchemeCode());
            inParams.put(flag, reportOnUnverifiedDTO.getFlag());
            inParams.put(P_MOD_USER_ID_IN, reportOnUnverifiedDTO.getUserID());
            inParams.put(p_search_value_in, reportOnUnverifiedDTO.getSearchValue());
            inParams.put(p_page_no_in, reportOnUnverifiedDTO.getPageNo());
            inParams.put(p_page_size_in, reportOnUnverifiedDTO.getPageSize());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(P_MOD_USER_ID_IN, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                ReportOnUnverifiedDTO item = new ReportOnUnverifiedDTO();
                if (reportOnUnverifiedDTO.getFlag().equals("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (reportOnUnverifiedDTO.getFlag().equals("D")) {
                    item.setBlaNumber(rs.getString(1));
                    item.setSubscriptionID(rs.getString(2));
                    item.setBondType(rs.getString(3));
                    item.setHolderName(rs.getString(4));
                    item.setScreenName(rs.getString(5));
                    item.setDateOfCreation(rs.getString(6));
                    item.setCreatedModifiedBy(rs.getString(7));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_unver_rec_rep",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<ReportOnUnverifiedDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getTdsDeductionTdsrate(TdsDeductionDTO tdsDeductionDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<TdsDeductionDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, tdsDeductionDTO.getSchemeCode());
            inParams.put(flag, tdsDeductionDTO.getFlag());
            inParams.put(name, tdsDeductionDTO.getHolderName());
            inParams.put(panNumber, tdsDeductionDTO.getPanNo());
            inParams.put(blaNumber, tdsDeductionDTO.getBlaNo());
            inParams.put(financialYear, tdsDeductionDTO.getFinancialYear());
            inParams.put(p_page_no_in, tdsDeductionDTO.getPageNo());
            inParams.put(p_page_size_in, tdsDeductionDTO.getPageSize());
            inParams.put(p_search_value_in, tdsDeductionDTO.getSearchValue());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(financialYear, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                TdsDeductionDTO item = new TdsDeductionDTO();
                if (tdsDeductionDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemestatus(rs.getString(3));

                } else if (tdsDeductionDTO.getFlag().equalsIgnoreCase("P")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNo(rs.getString(2));
                } else if (tdsDeductionDTO.getFlag().equalsIgnoreCase("B")) {
                    item.setModeofHolding(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setPanNo(rs.getString(3));
                    item.setBlaNo(rs.getString(4));
                } else if (tdsDeductionDTO.getFlag().equalsIgnoreCase("FY")) {
                    item.setFinancialYear(rs.getString(1));
                } else if (tdsDeductionDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPanNo(rs.getString(1));
                    item.setBlaNo(rs.getString(2));
                    item.setHolderName(rs.getString(3));
                    item.setSubsId(rs.getString(4));
                    item.setFinancialYear(rs.getString(5));
                    item.setTypeofCertificate(rs.getString(6));
                    item.setTaxExcemlimit(rs.getString(7));
                    item.setLownotaxper(rs.getString(8));
                    item.setPanStatus(rs.getString(9));
                    item.setInterestPaymentDate(rs.getString(10));
                    item.setTotalInterest(rs.getString(11));
                    item.setTdsAmt(rs.getString(12));
                    item.setTdsDeductionper(rs.getString(13));
                    item.setTdsDeductionAmt(rs.getString(14));
                    item.setNetInterestAmt(rs.getString(15));
                }
                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_rep_tds_deduction_rate",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<TdsDeductionDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getPreIntimationAdviceNoticeDetails(PreIntimationAdviceNoticeDTO preIntimationAdviceNoticeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<PreIntimationAdviceNoticeDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, preIntimationAdviceNoticeDTO.getSchemeCode());
            inParams.put(flag, preIntimationAdviceNoticeDTO.getFlag());
            inParams.put(fromDate, preIntimationAdviceNoticeDTO.getFromDate());
            inParams.put(toDate, preIntimationAdviceNoticeDTO.getToDate());
            inParams.put(blaNumber, preIntimationAdviceNoticeDTO.getBlaNumber());
            inParams.put(panNumber, preIntimationAdviceNoticeDTO.getPanNumber());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                PreIntimationAdviceNoticeDTO item = new PreIntimationAdviceNoticeDTO();
                if (preIntimationAdviceNoticeDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                } else if (preIntimationAdviceNoticeDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNumber(rs.getString(1));
                    item.setNameOfHolder(rs.getString(2));
                    item.setPanNumber(rs.getString(3));
                }
                else if (preIntimationAdviceNoticeDTO.getFlag().equalsIgnoreCase("T")) {
                    item.setNameOfHolder(rs.getString(1));
                    item.setAddress(rs.getString(2));
                    item.setAddress1(rs.getString(3));
                    item.setAddress2(rs.getString(4));
                    item.setCity(rs.getString(5));
                    item.setState(rs.getString(6));
                    item.setPinCode(rs.getString(7));
                    item.setPhoneNumber(rs.getString(8));
                    item.setBlaNumber(rs.getString(9));
                    item.setBranchRefNo(rs.getString(10));
                    item.setInterset(rs.getString(11));
                    item.setInvestmentdate(rs.getString(12));
                    item.setInvestedAmount(rs.getString(13));
                    item.setMaturityDate(rs.getString(14));
                    item.setInterestAmount(rs.getString(15));
                    item.setTotalAmount(rs.getString(16));
                }
                else if (preIntimationAdviceNoticeDTO.getFlag().equalsIgnoreCase("D1")) {
                    item.setNameOfHolder(rs.getString(1));
                    item.setAddress(rs.getString(2));
                    item.setAddress1(rs.getString(3));
                    item.setAddress2(rs.getString(4));
                    item.setCity(rs.getString(5));
                    item.setState(rs.getString(6));
                    item.setPinCode(rs.getString(7));
                    item.setPhoneNumber(rs.getString(8));
                    item.setBlaNumber(rs.getString(9));
                    item.setBranchRefNo(rs.getString(10));
                    item.setInterset(rs.getString(11));
                    item.setInvestmentdate(rs.getString(12));
                    item.setInvestedAmount(rs.getString(13));
                    item.setMaturityDate(rs.getString(14));
                    item.setInterestAmount(rs.getString(15));
                    item.setTotalAmount(rs.getString(16));
                }

                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_rep_pre_intim_adv_on_maturity",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<PreIntimationAdviceNoticeDTO>) result.get(curValOut);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getPostIntimationAdviceNoticeDetails(PostIntimationAdviceNoticeDTO postIntimationAdviceNoticeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<PostIntimationAdviceNoticeDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, postIntimationAdviceNoticeDTO.getSchemeCode());
            inParams.put(flag, postIntimationAdviceNoticeDTO.getFlag());
            inParams.put(fromDate, postIntimationAdviceNoticeDTO.getFromDate());
            inParams.put(toDate, postIntimationAdviceNoticeDTO.getToDate());
            inParams.put(blaNumber, postIntimationAdviceNoticeDTO.getBlaNumber());
            inParams.put(panNumber, postIntimationAdviceNoticeDTO.getPanNumber());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                PostIntimationAdviceNoticeDTO item = new PostIntimationAdviceNoticeDTO();
                if (postIntimationAdviceNoticeDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                }
              else if (postIntimationAdviceNoticeDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNumber(rs.getString(1));
                    item.setNameOfHolder(rs.getString(2));
                    item.setPanNumber(rs.getString(3));
                }
                else if (postIntimationAdviceNoticeDTO.getFlag().equalsIgnoreCase("T")) {
                    item.setBlaNumber(rs.getString(1));
                    item.setNameOfHolder(rs.getString(2));
                    item.setPanNumber(rs.getString(3));
                    item.setAddress(rs.getString(4));
                    item.setAddress1(rs.getString(5));
                    item.setAddress2(rs.getString(6));
                    item.setPinCode(rs.getString(7));
                    item.setCity(rs.getString(8));
                    item.setState(rs.getString(9));
                    item.setPhoneNumber(rs.getString(10));
                    item.setMaturityDate(rs.getString(11));
                    item.setInvestedAmount(rs.getString(12));
                    item.setInterestAmount(rs.getString(13));
item.setTdsRate(rs.getString(14));
item.setTdsAmount(rs.getString(15));
                    item.setTotalAmount(rs.getString(16));
                    item.setAccountNo(rs.getString(17));
                }
                  else if (postIntimationAdviceNoticeDTO.getFlag().equalsIgnoreCase("D1")) {
                    item.setBlaNumber(rs.getString(1));
                    item.setNameOfHolder(rs.getString(2));
                    item.setPanNumber(rs.getString(3));
                    item.setAddress(rs.getString(4));
                    item.setAddress1(rs.getString(5));
                    item.setAddress2(rs.getString(6));
                    item.setPinCode(rs.getString(7));
                    item.setCity(rs.getString(8));
                    item.setState(rs.getString(9));
                    item.setPhoneNumber(rs.getString(10));
                    item.setMaturityDate(rs.getString(11));
                    item.setInvestedAmount(rs.getString(12));
                    item.setInterestAmount(rs.getString(13));
                    item.setTdsRate(rs.getString(14));
                    item.setTdsAmount(rs.getString(15));
                    item.setTotalAmount(rs.getString(16));
//                    item.setAccountNo(rs.getString(17));
                }

                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_POST_MAT_LTR_REP",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<PostIntimationAdviceNoticeDTO>) result.get(curValOut);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }



    @Override
    public ResponseDataDTO getPanAadharDetails(PanAadhaarDTO panAadhaarDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<PanAadhaarDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(flag, panAadhaarDTO.getFlag());
            inParams.put(financialYear, panAadhaarDTO.getFinancialYear());
            inParams.put(p_page_no_in, panAadhaarDTO.getPageNo());
            inParams.put(p_page_size_in, panAadhaarDTO.getPageSize());
            inParams.put(p_search_value_in, panAadhaarDTO.getSearchValue());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(financialYear, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                PanAadhaarDTO item = new PanAadhaarDTO();
                if (panAadhaarDTO.getFlag().equalsIgnoreCase("FY")) {
                    item.setFinancialYear(rs.getString(1));
                } else if (panAadhaarDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPanNo(rs.getString(1));
                    item.setLastStatusDate(rs.getString(2));
                    item.setHolderName(rs.getString(3));
                    item.setPanStatus(rs.getString(4));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_PAN_AADHAAR_INVALID_PAN",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<PanAadhaarDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getHalfYearlyInterestAdvice(HalfYearlyInterestAdviceDTO halfYearlyInterestAdviceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<HalfYearlyInterestAdviceDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, halfYearlyInterestAdviceDTO.getSchemeCode());
            inParams.put(flag, halfYearlyInterestAdviceDTO.getFlag());
            inParams.put(name, halfYearlyInterestAdviceDTO.getHolderName());
            inParams.put(panNumber, halfYearlyInterestAdviceDTO.getPanNumber());
            inParams.put(blaNumber, halfYearlyInterestAdviceDTO.getBlaNumber());
            inParams.put(fromDate, halfYearlyInterestAdviceDTO.getFromDate());
            inParams.put(toDate, halfYearlyInterestAdviceDTO.getToDate());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                HalfYearlyInterestAdviceDTO item = new HalfYearlyInterestAdviceDTO();
                if (halfYearlyInterestAdviceDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (halfYearlyInterestAdviceDTO.getFlag().equalsIgnoreCase("P")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNumber(rs.getString(2));
                } else if (halfYearlyInterestAdviceDTO.getFlag().equalsIgnoreCase("B")) {
                    item.setModeOfHolding(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setPanNumber(rs.getString(3));
                    item.setBlaNumber(rs.getString(4));
                } else if (halfYearlyInterestAdviceDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setCourNumber(rs.getString(1));
                    item.setBlaNumber(rs.getString(2));
                    item.setHolderName(rs.getString(3));
                    item.setAddress1(rs.getString(4));
                    item.setAddress2(rs.getString(5));
                    item.setCity(rs.getString(6));
                    item.setState(rs.getString(7));
                    item.setPinCode(rs.getString(8));
                    item.setInvAmount(rs.getString(9));
                    item.setInvDate(rs.getString(10));
                    item.setAccountNumber(rs.getString(11));
                    item.setMicrCode(rs.getString(12));
                    item.setBankName(rs.getString(13));
                    item.setIntAmount(rs.getString(14));
                    item.setTdsRate(rs.getString(15));
                    item.setTdsAmount(rs.getString(16));
                    item.setIntPaid(rs.getString(17));
                    item.setCreditDate(rs.getString(18));
                    item.setHyIntDate(rs.getString(19));
                    item.setCategory(rs.getString(20));
                    item.setEmailID(rs.getString(21));
                    item.setPanNumber(rs.getString(22));
                    item.setRemarks(rs.getString(23));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_HY_INT_ADV",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<HalfYearlyInterestAdviceDTO>) result.get(curValOut);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getTdsEligibility(TdsEligibilityDTO tdsEligibilityDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<TdsEligibilityDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, tdsEligibilityDTO.getSchemeCode());
            inParams.put(panNumber, tdsEligibilityDTO.getPanNo());
            inParams.put(flag, tdsEligibilityDTO.getFlag());
            inParams.put(fromDate, tdsEligibilityDTO.getFromDate());
            inParams.put(toDate, tdsEligibilityDTO.getToDate());
            inParams.put(p_page_no_in, tdsEligibilityDTO.getPageNo());
            inParams.put(p_page_size_in, tdsEligibilityDTO.getPageSize());
            inParams.put(p_search_value_in, tdsEligibilityDTO.getSearchValue());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR),
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                TdsEligibilityDTO item = new TdsEligibilityDTO();
                if (tdsEligibilityDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (tdsEligibilityDTO.getFlag().equalsIgnoreCase("P")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNo(rs.getString(2));
                    item.setBlaNo(rs.getString(3));
                    item.setModeOfHolding(rs.getString(4));
                } else if (tdsEligibilityDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNo(rs.getString(2));
                    item.setBlaNo(rs.getString(3));
                    item.setSubsId(rs.getString(4));
                    item.setSubsDate(rs.getString(5));
                    item.setSubsAmt(rs.getString(6));
                    item.setInterestRate(rs.getString(7));
                    item.setTdsDeductionFrom(rs.getString(8));
                    item.setTdsDeductionTo(rs.getString(9));
                    item.setInterestPaidDate(rs.getString(10));
                    item.setInterestPaidMode(rs.getString(11));
                    item.setCreditAccNo(rs.getString(12));
                    item.setTypeOfCertificate(rs.getString(13));
                    item.setTdsExemptionLimit(rs.getString(14));
                    item.setLowTAxPercentage(rs.getString(15));
                    item.setPanStatus(rs.getString(16));
                    item.setGrosstotalInterestAmt(rs.getString(17));
                    item.setTdsAmt(rs.getString(18));
                    item.setTdsDeductionPercentage(rs.getString(19));
                    item.setNewInterestAmt(rs.getString(20));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_TDS_ELIG_REP",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<TdsEligibilityDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getModeOfHoldingChangeDetails(ModeOfHoldingReportDto modeOfHoldingReportDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<ModeOfHoldingReportDto> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, modeOfHoldingReportDto.getSchemeCode());
            inParams.put(flag, modeOfHoldingReportDto.getFlag());
            inParams.put(fromDate, modeOfHoldingReportDto.getFromDate());
            inParams.put(toDate, modeOfHoldingReportDto.getToDate());
            inParams.put(p_page_no_in, modeOfHoldingReportDto.getPageNo());
            inParams.put(p_page_size_in, modeOfHoldingReportDto.getPageSize());
            inParams.put(p_search_value_in, modeOfHoldingReportDto.getSearchValue());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                ModeOfHoldingReportDto item = new ModeOfHoldingReportDto();
                if (modeOfHoldingReportDto.getFlag().equals("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (modeOfHoldingReportDto.getFlag().equals("D")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNo(rs.getString(2));
                    item.setModeOfHolding(rs.getString(3));
                    item.setFromBlaAccountNumber(rs.getString(4));
                    item.setActiveSubscriptionId(rs.getString(5));
                    item.setActiveSubscriptionCount(rs.getString(6));
                    item.setToBlaAccountNumber(rs.getString(7));
                    item.setDateOfHoldingChange(rs.getString(8));
                    item.setRemarks(rs.getString(9));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_rbi_bond_rep_mod_hold_change",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<ModeOfHoldingReportDto>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBrokerEnrolledDetails(BrokerEnrolledDTO brokerEnrolledDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<BrokerEnrolledDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, brokerEnrolledDTO.getSchemeCode());
            inParams.put(flag, brokerEnrolledDTO.getFlag());
            inParams.put(fromDate, brokerEnrolledDTO.getFromDate());
            inParams.put(toDate, brokerEnrolledDTO.getToDate());
            inParams.put(brokerAgencyCode, brokerEnrolledDTO.getBrokerAgencyCode());
            inParams.put(p_page_no_in, brokerEnrolledDTO.getPageNo());
            inParams.put(p_page_size_in, brokerEnrolledDTO.getPageSize());
            inParams.put(p_search_value_in, brokerEnrolledDTO.getSearchValue());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(brokerAgencyCode, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BrokerEnrolledDTO item = new BrokerEnrolledDTO();
                if (brokerEnrolledDTO.getFlag().equalsIgnoreCase("B")) {
                    item.setBrokerAgencyCode(rs.getString(1));
                    item.setBrokerName(rs.getString(2));
                    item.setVerifyStatus(rs.getString(3));
                    item.setBranchID(rs.getString(4));
                } else if (brokerEnrolledDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (brokerEnrolledDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBrokerName(rs.getString(1));
                    item.setBrokerPanNumber(rs.getString(2));
                    item.setBrokerAddress1(rs.getString(3));
                    item.setBrokerAddress2(rs.getString(4));
                    item.setBrokerCity(rs.getString(5));
                    item.setBrokerState(rs.getString(6));
                    item.setBrokerPinCode(rs.getString(7));
                    item.setPhone(rs.getString(8));
                    item.setPaymentMode(rs.getString(9));
                    item.setAccountTypeCode(rs.getString(10));
                    item.setBankAccount(rs.getString(11));
                    item.setMicrCode(rs.getString(12));
                    item.setApplNo(rs.getString(13));
                    item.setStatus(rs.getString(14));
                    item.setDeStatus(rs.getString(15));
                    item.setHdfcBranchCode(rs.getString(16));
                    item.setBrokerAmount(rs.getString(17));
                    item.setGroupCode(rs.getString(18));
                    item.setBlaNumber(rs.getString(19));
                    item.setInvestAmount(rs.getString(20));
                    item.setCasTransferDate(rs.getString(21));
                    item.setMaturityDate(rs.getString(22));
                    item.setScheme(rs.getString(23));
                    item.setCfd(rs.getString(24));
                    item.setBranchRefNo(rs.getString(25));
                    item.setAdviceNo(rs.getString(26));
                    item.setApplicantName(rs.getString(27));
                    item.setAddress1(rs.getString(28));
                    item.setAddress2(rs.getString(29));
                    item.setCity(rs.getString(30));
                    item.setState(rs.getString(31));
                    item.setPinCode(rs.getString(32));
                    item.setHdfcBrokerCode(rs.getString(33));
                    item.setBranchName(rs.getString(34));
                    item.setRcptDate(rs.getString(35));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_BROKER_APPOINTMENT_ENROLL",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<BrokerEnrolledDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        }catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getBondTransferIn(BondTransferInDTO bondTransferInDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<BondTransferInDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        String interestCurValOut = "p_int_dtls_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(flag, bondTransferInDTO.getFlag());
            inParams.put(schemeCode, bondTransferInDTO.getSchemeCode());
            inParams.put(accountTransferFrom, bondTransferInDTO.getAccTranFrom());
            inParams.put(P_HOLDER_NAME_IN, bondTransferInDTO.getHolderName());
            inParams.put(panNumber, bondTransferInDTO.getPanNo());
            inParams.put(blaNumber, bondTransferInDTO.getBlaNo());
            inParams.put(P_SUB_ID_IN, bondTransferInDTO.getSubsId());
            inParams.put(p_page_no_in, bondTransferInDTO.getPanNo());
            inParams.put(p_page_size_in, bondTransferInDTO.getBlaNo());
            inParams.put(p_search_value_in, bondTransferInDTO.getSubsId());


            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(accountTransferFrom, Types.VARCHAR),
                    new SqlParameter(P_HOLDER_NAME_IN, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(P_SUB_ID_IN, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(interestCurValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BondTransferInDTO item = new BondTransferInDTO();
                if (bondTransferInDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (bondTransferInDTO.getFlag().equalsIgnoreCase("T")) {
                    item.setBranchId(rs.getString(1));
                    item.setBranchName(rs.getString(2));
                } else if (bondTransferInDTO.getFlag().equalsIgnoreCase("P")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNo(rs.getString(2));
                    item.setBlaNo(rs.getString(3));
                } else if (bondTransferInDTO.getFlag().equalsIgnoreCase("B")) {
                    item.setAccNumber(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setBlaNo(rs.getString(3));
                } else if (bondTransferInDTO.getFlag().equalsIgnoreCase("BR")) {
                    item.setSubsAmt(rs.getString(1));
                    item.setDateOfTransfer(rs.getString(2));
                    item.setSubsId(rs.getString(3));
                } else if (bondTransferInDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setTranFromBranchId(rs.getString(1));
                    item.setTranFromBranchName(rs.getString(2));
                    item.setTranToBranchId(rs.getString(3));
                    item.setTranToBranchName(rs.getString(4));
                    item.setBlaNo(rs.getString(5));
                    item.setHolderName(rs.getString(6));
                    item.setSubsId(rs.getString(7));
                    item.setBondType(rs.getString(8));
                    item.setDepositAmt(rs.getString(9));
                    item.setAccOpenedDate(rs.getString(10));
                    item.setMaturityDate(rs.getString(11));
                    item.setInterestPaidUpTo(rs.getString(12));
                    item.setOperativeAccNo(rs.getString(13));
                    item.setDateOfTransfer(rs.getString(14));
                }
                return item;
            });
            rowMappers.put(interestCurValOut, (rs, rowNum) -> {
                BondTransferInDTO item = new BondTransferInDTO();
                if (bondTransferInDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setInterestFromPeriod(rs.getString(1));
                    item.setInterestToPeriod(rs.getString(2));
                    item.setInterestAmt(rs.getString(3));
                    item.setTdsAmt(rs.getString(4));
                    item.setInterestPaidDate(rs.getString(5));
                    item.setInterestPaidAmt(rs.getString(6));
                    item.setRateOfOInterest(rs.getString(7));
                    item.setPaidByBranch(rs.getString(8));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_rbi_bond_rep_tran_in_same_bank",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<BondTransferInDTO>) result.get(curValOut);
            List<BondTransferInDTO> interestList = (List<BondTransferInDTO>) result.get(interestCurValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                resultMap.put("interestList", interestList);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO getDashBoardReportForInwardOutward(DashBoardReportForInwardOutwardDTO dto) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        String totalRecords = "0";
        String inwardsCount = "0";
        String outwardsCount = "0";
        String pendingForOutwardsCount = "0";
        HashMap<String, Object> finalData = new HashMap<>();
        List<DashBoardReportForInwardOutwardDTO> resultList = Collections.emptyList();

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put("p_user_id_in", UserAccountUtils.getCurrentUsername());
            inParams.put("p_user_sol_id_in", UserAccountUtils.getCurrentSolId());
            inParams.put("p_sch_code_in", dto.getSchemeCode());
            inParams.put("p_flag_in", dto.getFlag());
            inParams.put("p_name_in", dto.getName());
            inParams.put("p_pan_no_in", dto.getPanNo());
            inParams.put("p_bla_no_in", dto.getBlaNo());
            inParams.put("p_subs_id_in", dto.getSubscriptionID());
            inParams.put("p_from_date_in", dto.getFromDate());
            inParams.put("p_to_date_in", dto.getToDate());
            inParams.put("p_page_no_in", dto.getPageNo());
            inParams.put("p_page_size_in", dto.getPageSize());
            inParams.put("p_search_value_in", (dto.getSearchValue() != null && !dto.getSearchValue().trim().isEmpty()) ? dto.getSearchValue() : null);
            inParams.put("p_status_in",dto.getStatus());

            SqlParameter[] sqlParameters = {
                    new SqlParameter("p_user_id_in", Types.VARCHAR),
                    new SqlParameter("p_user_sol_id_in", Types.VARCHAR),
                    new SqlParameter("p_sch_code_in", Types.VARCHAR),
                    new SqlParameter("p_flag_in", Types.VARCHAR),
                    new SqlParameter("p_name_in", Types.VARCHAR),
                    new SqlParameter("p_pan_no_in", Types.VARCHAR),
                    new SqlParameter("p_bla_no_in", Types.VARCHAR),
                    new SqlParameter("p_subs_id_in", Types.VARCHAR),
                    new SqlParameter("p_from_date_in", Types.VARCHAR),
                    new SqlParameter("p_to_date_in", Types.VARCHAR),
                    new SqlParameter("p_page_no_in", Types.VARCHAR),
                    new SqlParameter("p_page_size_in", Types.VARCHAR),
                    new SqlParameter("p_search_value_in", Types.VARCHAR),
                    new SqlParameter("p_status_in", Types.VARCHAR)

            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter("p_total_records_out", Types.VARCHAR),
                    new SqlOutParameter("p_cur_val_out", OracleTypes.CURSOR),
                    new SqlOutParameter("p_msg_out", Types.VARCHAR),
                    new SqlOutParameter("p_msg_desc_out", Types.VARCHAR),
                    new SqlOutParameter("p_inward_out",Types.VARCHAR),
                    new SqlOutParameter("p_outward_out",Types.VARCHAR),
                    new SqlOutParameter("p_pending_out",Types.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put("p_cur_val_out", (rs, rowNum) -> {
                DashBoardReportForInwardOutwardDTO item = new DashBoardReportForInwardOutwardDTO();
                switch (dto.getFlag()) {
                    case "SC":
                        item.setSchemeCode(rs.getString("SUB_CODE"));
                        item.setSchemeName(rs.getString("SUB_CODE_DESC"));
                        item.setSchemeStatus(rs.getString("SCHEME_STATUS"));
                        break;
                    case "P":
                        item.setPanNo(rs.getString("PAN_NO"));
                        item.setName(rs.getString("NAME_OF_THE_HLDR"));
                        break;
                    case "B":
                        item.setPanNo(rs.getString("PAN_NO"));
                        item.setName(rs.getString("NAME_OF_THE_HLDR"));
                        item.setModeOfHolding(rs.getString("MODE_OF_HOLDING"));
                        item.setBlaNo(rs.getString("BLA_NO"));
                        item.setSubscriptionID(rs.getString("SUBS_ID"));
                        break;
                    case "D":
                        item.setFts(rs.getString("FTS_APP_NUM"));
                        item.setBlaNo(rs.getString("BLA_NO"));
                        item.setSchemeCode(rs.getString("SCHEME_CODE"));
                        item.setRequestCode(rs.getString("REQ_CODE"));
                        item.setReqDescription(rs.getString("REQ_DESC"));
                        item.setUserName(rs.getString("USER_NAME"));
                        item.setNoOfDays(rs.getString("NO_OF_DAYS"));
                        item.setInwardDate(rs.getString("INWARD_DATE"));
                        item.setOutwardDate(rs.getString("OUTWARD_DATE"));
                        item.setInwardRefNo(rs.getString("INWARD_REF_NUM"));
                        item.setOutwardRefNo(rs.getString("OUTWARD_REF_NUM"));
                        item.setBondType(rs.getString("BOND_TYPE"));
                        item.setMaturityDate(rs.getString("MATURITY_DATE"));
                        item.setOutwardReason(rs.getString("OUTWARD_REASON"));
                        item.setStatus(rs.getString("STATUS"));
                        item.setSchemeName(rs.getString("SCHEME_NAME"));
                        item.setPanNo(rs.getString("PAN_NO"));
                        item.setPodNumber(rs.getString("POD_CODE"));
                        break;
                }
                return item;
            });

            // Execute stored procedure
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_INWARD_OUTWARD_DASHBOARD",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            // Retrieve output data
            resultList = (List<DashBoardReportForInwardOutwardDTO>) result.get("p_cur_val_out");
            totalRecords = result.get("p_total_records_out") != null ? result.get("p_total_records_out").toString() : "0";
            inwardsCount = result.get("p_inward_out") != null ? result.get("p_inward_out").toString() : "0";
            outwardsCount = result.get("p_outward_out") != null ? result.get("p_outward_out").toString() : "0";
            pendingForOutwardsCount = result.get("p_pending_out") != null ? result.get("p_pending_out").toString() : "0";



            if (CollectionUtils.isEmpty(resultList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                finalData.put("dashBoardReportList", resultList);
                finalData.put("total", totalRecords);
                finalData.put("inwardsCount", inwardsCount);
                finalData.put("outwardsCount", outwardsCount);
                finalData.put("pendingForOutwardsCount", pendingForOutwardsCount);
                responseDataDTO.setResult(finalData);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
//                responseDataDTO.setInwardsCount(Double.parseDouble(inwardsCount));
//                responseDataDTO.setOutwardsCount(Double.parseDouble(outwardsCount));
//                        responseDataDTO.setPendingForOutwardsCount(Double.parseDouble(pendingForOutwardsCount));


            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO getDisablingDeactivating(DisablingDeactivatingDTO disablingDeactivatingDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<DisablingDeactivatingDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords=null;

        // IN PARAMS
        String brokerAgencyIn = "P_broker_agency_in";
        String brokerStatusIn = "P_broker_status_in";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
//            inParams.put(schemeCode, disablingDeactivatingDTO.getSchemeCode());
            inParams.put(flag, disablingDeactivatingDTO.getFlag());
            inParams.put(fromDate, disablingDeactivatingDTO.getFromDate());
            inParams.put(toDate, disablingDeactivatingDTO.getToDate());
            inParams.put(brokerAgencyIn, disablingDeactivatingDTO.getBrokerAgencyCode());
            inParams.put(brokerStatusIn, disablingDeactivatingDTO.getBrokerStatus());
            inParams.put(p_page_no_in, disablingDeactivatingDTO.getPageNo());
            inParams.put(p_page_size_in, disablingDeactivatingDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=disablingDeactivatingDTO.getSearchValue()&&!disablingDeactivatingDTO.getSearchValue().trim().isEmpty())?disablingDeactivatingDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
//                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(brokerAgencyIn, Types.VARCHAR),
                    new SqlParameter(brokerStatusIn, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                DisablingDeactivatingDTO item = new DisablingDeactivatingDTO();
//                if (disablingDeactivatingDTO.getFlag().equalsIgnoreCase("S")) {
//                    item.setSchemeCode(rs.getString(1));
//                    item.setSchemeName(rs.getString(2));
//                    item.setSchemeStatus(rs.getString(3));
//                } else
                    if (disablingDeactivatingDTO.getFlag().equalsIgnoreCase("BA")) {
                    item.setBrokerAgencyCode(rs.getString(1));
                    item.setBrokerName(rs.getString(2));
                    item.setVerifyStatus(rs.getString(3));
                    item.setBranchId(rs.getString(4));
                } else if (disablingDeactivatingDTO.getFlag().equalsIgnoreCase("T") ||
                        disablingDeactivatingDTO.getFlag().equalsIgnoreCase("T1") ||
                        disablingDeactivatingDTO.getFlag().equalsIgnoreCase("T2") ||
                        disablingDeactivatingDTO.getFlag().equalsIgnoreCase("T3")) {
                    item.setHdfcBrokercode(rs.getString(1));
                    item.setBrokerSortCode(rs.getString(2));
                    item.setBrokerName(rs.getString(3));
                    item.setEnableBroker(rs.getString(4));
                    item.setBankAccount(rs.getString(5));
                    item.setpAN(rs.getString(6));
                    item.setGstNo(rs.getString(7));
                    item.setIfscCode(rs.getString(8));
                    item.setBankName(rs.getString(9));
                    item.setStatus(rs.getString(10));
                    item.setNoOfMonth(rs.getString(11));
                    item.setAddress(rs.getString(12));
                    item.setAddress2(rs.getString(13));
                    item.setDistrict(rs.getString(14));
                    item.setState(rs.getString(15));
                    item.setPinCode(rs.getString(16));
                    item.setPhone(rs.getString(17));
                    item.setEmailId(rs.getString(18));
                    item.setMobileNo(rs.getString(19));
                    item.setMicrCode(rs.getString(20));
                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_Disabling_Deactivating_of_Brks",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<DisablingDeactivatingDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
                responseDataDTO.setResult(resultMap);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getFutureTdsDetails(FutureTdsDto futureTdsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_gbm_rbi_bond_fut_tds_calc.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, futureTdsDto.getSchemeCode());
                stmt.setString(index++, futureTdsDto.getMenu());
                stmt.setString(index++, futureTdsDto.getFlag());
                stmt.setString(index++, futureTdsDto.getFromDate());
                stmt.setString(index++, futureTdsDto.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(8);
                    if (resultSet != null) {
                        List<FutureTdsDto> list = new ArrayList<>();
                        FutureTdsDto item = null;
                        while (resultSet.next()) {
                            if (futureTdsDto.getFlag().equalsIgnoreCase("S")) {
                                item = new FutureTdsDto();

                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            if (futureTdsDto.getFlag().equalsIgnoreCase("D")) {
                                item = new FutureTdsDto();
                                item.setSchemeId(resultSet.getString(1));
                                item.setBranchRefNo(resultSet.getString(2));
                                item.setBlaNo(resultSet.getString(3));
                                item.setApplNameWithHuf(resultSet.getString(4));
                                item.setApplName1(resultSet.getString(5));
                                item.setApplName2(resultSet.getString(6));
                                item.setApplName3(resultSet.getString(7));
                                item.setCasTransferDate(resultSet.getString(8));
                                item.setApplStatus(resultSet.getString(9));
                                item.setRcptDate(resultSet.getString(10));
                                item.setInvestAmt(resultSet.getString(11));
                                item.setScheme(resultSet.getString(12));
                                item.setCfd(resultSet.getString(13));
                                item.setMaturityDate(resultSet.getString(14));
                                item.setIntRate(resultSet.getString(15));
                                item.setLastIntPaidAmt(resultSet.getString(16));
                                item.setPanNo(resultSet.getString(17));
                                item.setFutureInterestStatus(resultSet.getString(18));
                                item.setNew1(resultSet.getString(19));
                                item.setNew2(resultSet.getString(20));
                                item.setNew3(resultSet.getString(21));
                                item.setNew4(resultSet.getString(22));
                                item.setNew5(resultSet.getString(23));
                                list.add(item);
                            }

                        }
                        responseDataDTO.setResult(list);
                    }
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBondInterestNotCredited(BondInterestNotCreditedDTO bondInterestNotCreditedDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<BondInterestNotCreditedDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, bondInterestNotCreditedDTO.getSchemeCode());
            inParams.put(interestPaymentFor, bondInterestNotCreditedDTO.getInterestPaymentFor());
            inParams.put(flag, bondInterestNotCreditedDTO.getFlag());
            inParams.put(name, bondInterestNotCreditedDTO.getHolderName());
            inParams.put(panNumber, bondInterestNotCreditedDTO.getPanNumber());
            inParams.put(blaNumber, bondInterestNotCreditedDTO.getBlaNumber());
            inParams.put(financialYear, bondInterestNotCreditedDTO.getFinancialYear());
            inParams.put(p_page_no_in, bondInterestNotCreditedDTO.getPageNo());
            inParams.put(p_page_size_in, bondInterestNotCreditedDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=bondInterestNotCreditedDTO.getSearchValue()&&!bondInterestNotCreditedDTO.getSearchValue().trim().isEmpty())?bondInterestNotCreditedDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(interestPaymentFor, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(financialYear, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BondInterestNotCreditedDTO item = new BondInterestNotCreditedDTO();
                if (bondInterestNotCreditedDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (bondInterestNotCreditedDTO.getFlag().equalsIgnoreCase("P")) {
                    item.setHolderName(rs.getString(1));
                    item.setPanNumber(rs.getString(2));
                } else if (bondInterestNotCreditedDTO.getFlag().equalsIgnoreCase("B")) {
                    item.setModeOfHolding(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setPanNumber(rs.getString(3));
                    item.setBlaNumber(rs.getString(4));
                } else if (bondInterestNotCreditedDTO.getFlag().equalsIgnoreCase("FY")) {
                    item.setFinancialYear(rs.getString(1));
                } else if (bondInterestNotCreditedDTO.getFlag().equalsIgnoreCase("SB")) {
                    item.setPanNumber(rs.getString(1));
                    item.setBlaNumber(rs.getString(2));
                    item.setHolderName(rs.getString(3));
                    item.setSubscriptionID(rs.getString(4));
                    item.setSubscriptionDate(rs.getString(5));
                    item.setSubscriptionAmount(rs.getString(6));
                    item.setInterestFromPeriod(rs.getString(7));
                    item.setInterestToPeriod(rs.getString(8));
                    item.setInterestPaymentMode(rs.getString(9));
                    item.setCreditAccountNumber(rs.getString(10));
                    item.setTypeOfCertificate(rs.getString(11));
                    item.setTaxExemptionLimit(rs.getString(12));
                    item.setLowTaxPercentage(rs.getString(13));
                    item.setPanStatus(rs.getString(14));
                    item.setTotalInterest(rs.getString(15));
                    item.setTdsAmount(rs.getString(16));
                    item.setNetInterestAmount(rs.getString(17));
                    item.setReason(rs.getString(18));
                }





                else if (bondInterestNotCreditedDTO.getFlag().equalsIgnoreCase("M")) {
                    item.setModeOfPayment(rs.getString(1));
                    //  item.setBranchRefNo((rs.getString(2)));
                    item.setBlaNo((rs.getString(2)));
                    //  item.setRefNo((rs.getString(4)));
                    item.setAppName1(rs.getString(3));
                    item.setAppName2(rs.getString(4));
                    item.setAppName3(rs.getString(5));
                    item.setAddress1(rs.getString(6));
                    item.setAddress2(rs.getString(7));
                    item.setRecName(rs.getString(8));
                    item.setRecParentName(rs.getString(9));
                    item.setPinCode(rs.getString(10));
                    item.setPhoneNo(rs.getString(11));
                    item.setAppStatus(rs.getString(12));
                    item.setInvestmentAmount(rs.getString(13));
//                item.setCfd(rs.getString(16));
//                item.setMaturityDate(rs.getString(17));
                    item.setAccountNo(rs.getString(14));
                    item.setSubscriptionCount(rs.getString(15));
                    item.setBankSortCode(rs.getString(16));
                    //  item.setBankName(rs.getString(21));
                    item.setPanNo(rs.getString(17));
                    item.setInterestPayOut8Per(rs.getString(18));
                    item.setNonCumulativeInt(rs.getString(19));
                    item.setCumulativeInt(rs.getString(20));
                    item.setInterestPayOut7Per(rs.getString(21));
                    item.setNonCumulativeIntFor7(rs.getString(22));
                    item.setCumulativeIntFor7(rs.getString(23));
                    item.setTotal(rs.getString(24));
                    item.setInOperativePAN(rs.getString(25));
                    item.setNonFilers(rs.getString(26));
                    item.setAge(rs.getString(27));
                    item.setTaxableLimit(rs.getString(28));
                    item.setWaiverDocument(rs.getString(29));
                    item.setWaiverDocumentRefNo(rs.getString(30));
                    item.setRrbTDSRate(rs.getString(31));
                    item.setThresholdExceedAmount(rs.getString(32));
                    item.setThresholdExceedTDSAmount(rs.getString(33));
                    item.setExemptionRate(rs.getString(34));
                    item.setExemptionAmount(rs.getString(35));
                    item.setExemptionTDSAmount(rs.getString(36));
                    item.setSurcharge(rs.getString(37));
                    item.setEduCess(rs.getString(38));
                    item.setBaseRateAmount(rs.getString(39));
                    item.setSurchargeAmount(rs.getString(40));
                    item.setEduCessAmount(rs.getString(41));
                    item.setTdsAmount(rs.getString(42));
                    item.setTdsRecoveryAmount(rs.getString(43));
                    item.setTdsRefundAmount(rs.getString(44));
                    item.setIntRate(rs.getString(45));
                    item.setLastIntPaid(rs.getString(46));
                    item.setPendingInterest(rs.getString(47));
                    item.setCurrentHalfYearlyInterest(rs.getString(48));
                    item.setFutureInterest(rs.getString(49));
                    item.setGrossInterestAmount(rs.getString(50));
                    item.setIntPaid(rs.getString(51));
                    item.setPaymentDate(rs.getString(52));
//                item.setReturnReason(rs.getString(58));
//                item.setReturnAmt(rs.getString(59));
//                item.setReInitiatedAmt(rs.getString(60));
//                item.setReInitiatedDate(rs.getString(61));
//                item.setPaymentDate(rs.getString(62));
//                item.setReInitiatedPaymentMode(rs.getString(63));



                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_Int_not_credit_Rep",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<BondInterestNotCreditedDTO>) result.get(curValOut);

            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";


            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));

            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBondRepaymentReport(BondRepaymentReportDto bondRepaymentReportDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<BondRepaymentReportDto> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, bondRepaymentReportDto.getSchemeCode());
            inParams.put(flag, bondRepaymentReportDto.getFlag());
            inParams.put(fromDate, bondRepaymentReportDto.getFromDate());
            inParams.put(toDate, bondRepaymentReportDto.getToDate());
            inParams.put(p_page_no_in, bondRepaymentReportDto.getPageNo());
            inParams.put(p_page_size_in, bondRepaymentReportDto.getPageSize());
            inParams.put(p_search_value_in, (null!=bondRepaymentReportDto.getSearchValue()&&!bondRepaymentReportDto.getSearchValue().trim().isEmpty())?bondRepaymentReportDto.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                BondRepaymentReportDto item = new BondRepaymentReportDto();
                if (bondRepaymentReportDto.getSchemeCode().isEmpty() && bondRepaymentReportDto.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                } else if (bondRepaymentReportDto.getFlag() != null && bondRepaymentReportDto.getFlag().equalsIgnoreCase("A")) {
                    item.setDdOrPoAccountNumber(rs.getString(1));
                    item.setPanNo(rs.getString(2));
                    item.setBlaNo(rs.getString(3));
                    item.setHolderName(rs.getString(4));
                    item.setSubsId(rs.getString(5));
                    item.setDateOfSubscription(rs.getString(6));
                    item.setSubsAmt(rs.getString(7));
                    item.setGrossAmount(rs.getString(8));
                    item.setTdsPercentage(rs.getString(9));
                    item.setTdsAmount(rs.getString(10));
                    item.setMaturityDate(rs.getString(11));
                    item.setAmtRepaid(rs.getString(12));
                    item.setDateOfRepayment(rs.getString(13));
                    item.setPrincipalRepayment(rs.getString(14));
                    item.setBankCode(rs.getString(15));
                    item.setBranchCode(rs.getString(16));
                    item.setIfscCodeOfCreditBank(rs.getString(17));
                    item.setNameOfPayee(rs.getString(18));
                    item.setCreditAccountNumber(rs.getString(19));
                    item.setOperativeAccountNumber(rs.getString(20));
                    item.setIfscCodeSecondary(rs.getString(21));
                    item.setMicrCode(rs.getString(22));
                    item.setStatus(rs.getString(23));
                    item.setReturnDescription(rs.getString(24));
                }
                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REPLAY_REP",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<BondRepaymentReportDto>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getTdsSummaryForTaxAudit(TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> finalData = new HashMap<>();
        List<TdsSummaryForTaxAuditDTO> tdsSummaryForTaxAuditDTOList = Collections.emptyList();
        String totalRecords = null;
        try {
            // Input parameters
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(flag, tdsSummaryForTaxAuditDTO.getFlag());
            inParams.put(financialYear, tdsSummaryForTaxAuditDTO.getFinancialYear());
            inParams.put(Month, tdsSummaryForTaxAuditDTO.getMonth());
            inParams.put(p_page_no_in, tdsSummaryForTaxAuditDTO.getPageNo());
            inParams.put(p_page_size_in, tdsSummaryForTaxAuditDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=tdsSummaryForTaxAuditDTO.getSearchValue()&&!tdsSummaryForTaxAuditDTO.getSearchValue().trim().isEmpty())?tdsSummaryForTaxAuditDTO.getSearchValue():null);

            // Define SQL parameters
            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(financialYear, Types.VARCHAR),
                    new SqlParameter(Month, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),

            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (resultSet, rowNum) ->
            {
                TdsSummaryForTaxAuditDTO item = new TdsSummaryForTaxAuditDTO();
                if (tdsSummaryForTaxAuditDTO.getFlag().equalsIgnoreCase("FY")) {
                    item.setFinancialYear(resultSet.getString(1));
                } else if (tdsSummaryForTaxAuditDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setChallanSerialNo(resultSet.getString(1));
                    item.setForTheMonthOf(resultSet.getString(2));
                    item.setQuater(resultSet.getString(3));
                    item.setDateOfPayment(resultSet.getString(4));
                    item.setNatureOfPayment(resultSet.getString(5));
                    item.setQuaterlyForm(resultSet.getString(6));
                    item.setTotalAmountPaid(resultSet.getString(7));
                    item.setTotalTaxDeducted(resultSet.getString(8));
                    item.setTaxDeposited(resultSet.getString(9));
                    item.setTeam(resultSet.getString(10));
                    item.setDifference(resultSet.getString(11));
                    item.setTeamsRevertForDifference(resultSet.getString(12));

                }
                return item;


            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_TDS_SUMM_TAX_AUDIT",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            tdsSummaryForTaxAuditDTOList = (List<TdsSummaryForTaxAuditDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(tdsSummaryForTaxAuditDTOList)) {
                log.info("No Data Found in Bond Investments");
                resultMsg = CommonConstants.NDF;
            } else {

                // Add data to response
                finalData.put("tdsSummaryForTaxAuditDTOList", tdsSummaryForTaxAuditDTOList);
                responseDataDTO.setResult(finalData);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));

            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getNomineeReportDownload(NomineeContinuationReportDTO nomineeContinuationReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        String resultMsg = "SUCCESS";
        String totalRecords = "0";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, nomineeContinuationReportDTO.getSchemeCode());
            inParams.put(flag, nomineeContinuationReportDTO.getFlag());
            inParams.put(fromDate, nomineeContinuationReportDTO.getFromDate());
            inParams.put(toDate, nomineeContinuationReportDTO.getToDate());
            inParams.put(p_page_no_in, nomineeContinuationReportDTO.getPageNo());
            inParams.put(p_page_size_in, nomineeContinuationReportDTO.getPageSize());
            inParams.put(p_search_value_in,
                    nomineeContinuationReportDTO.getSearchValue() != null &&
                            !nomineeContinuationReportDTO.getSearchValue().trim().isEmpty()
                            ? nomineeContinuationReportDTO.getSearchValue() : null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                NomineeContinuationReportDTO item = new NomineeContinuationReportDTO();
                if (nomineeContinuationReportDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (nomineeContinuationReportDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setSchemeName(rs.getString(1));
                    item.setDeathMarkedHolderPan(rs.getString(2));
                    item.setDeathHolderName(rs.getString(3));
                    item.setDeathMarkedHolderBlaNo(rs.getString(4));
                    item.setDeathMarkedHolderSubscriptionId(rs.getString(5));
                    item.setDateOfDeathOfBondHolder(rs.getString(6));
                    item.setDateOfReceiptOfDeathCertificate(rs.getString(7));
                    item.setDeathMarkingDate(rs.getString(8));
                    item.setMainSubscriptionAmount(rs.getString(9));
                    item.setNomineeSharableAmount(rs.getString(10));
                    item.setNomineeID(rs.getString(11));

                    ListOfNomineeDTO nominee = new ListOfNomineeDTO();
                    nominee.setNomineePanNumber(rs.getString(12));
                    nominee.setNomineeName(rs.getString(13));
                    nominee.setNomineeDOB(rs.getString(14));
                    nominee.setNomineeBlaNumber(rs.getString(15));
                    nominee.setNomineeSubscriptionID(rs.getString(16));

                    List<ListOfNomineeDTO> nomineeList = new ArrayList<>();
                    nomineeList.add(nominee);
                    item.setNominees(nomineeList);
                    item.setNomineeSubscriptionAmount(rs.getString(17));
                    item.setBondType(rs.getString(18));
                    item.setAccountOpenDate(rs.getString(19));
                    item.setSubscriptionTransferredToNominee(rs.getString(20));
                    item.setPeriodOfDeposits(rs.getString(21));
                    item.setMaturityDate(rs.getString(22));
                    item.setModeOfInterestPayment(rs.getString(23));
                    item.setPrincipalRepayment(rs.getString(24));
                    item.setNomineeSharablePercentage(rs.getString(24));
                }
                return item;
            });

            long startTime = System.currentTimeMillis();

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "", "sp_gbm_rbi_bond_nom_con_rep",
                    inParams, sqlParameters, sqlOutParameters, rowMappers);

            List<NomineeContinuationReportDTO> rawList =
                    (List<NomineeContinuationReportDTO>) result.get(curValOut);

            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(rawList)) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            } else {
                // Step 1: Group by nomineeID and merge nominees
                Map<String, NomineeContinuationReportDTO> groupedMap = new LinkedHashMap<>();
                for (NomineeContinuationReportDTO item : rawList) {
                    String key = item.getNomineeID();
                    groupedMap.putIfAbsent(key, item);
                    NomineeContinuationReportDTO groupedItem = groupedMap.get(key);

                    if (groupedItem.getNominees() == null) {
                        groupedItem.setNominees(new ArrayList<>());
                    }else if (!(groupedItem.getNominees() instanceof ArrayList)) {
                        groupedItem.setNominees(new ArrayList<>(groupedItem.getNominees()));
                    }

                    List<ListOfNomineeDTO> currentNominees = item.getNominees();
                    if (currentNominees != null) {
                        for (ListOfNomineeDTO nom : currentNominees) {
                            boolean exists = groupedItem.getNominees().stream().anyMatch(n -> n.getNomineeSubscriptionID().equals(nom.getNomineeSubscriptionID()));
                            if (!exists) {
                                groupedItem.getNominees().add(nom);
                            }
                        }
                    }
                }

                // Step 2: Flatten nominees
                List<NomineeContinuationReportDTO> groupedList = new ArrayList<>(groupedMap.values());
                int maxNominees = 0;
                for (NomineeContinuationReportDTO dto : groupedList) {
                    List<ListOfNomineeDTO> nominees = dto.getNominees();
                    if (nominees != null) {
                        maxNominees = Math.max(maxNominees, nominees.size());
                        Map<String, String> nomineeFlat = new LinkedHashMap<>();
                        for (int i = 0; i < nominees.size(); i++) {
                            ListOfNomineeDTO n = nominees.get(i);
                            nomineeFlat.put("Nominee "  + (i+1) + " Name", n.getNomineeName());
                            nomineeFlat.put("Nominee "  + (i+1) + " PAN" , n.getNomineePanNumber());
                            nomineeFlat.put("Nominee "  + (i+1) + " DOB", n.getNomineeDOB());
                            nomineeFlat.put("Nominee "  + (i+1) + " BLA", n.getNomineeBlaNumber());
                            nomineeFlat.put("Nominee "  + (i+1) + " Subscription ID", n.getNomineeSubscriptionID());
                        }
                        dto.setNomineeFlat(nomineeFlat);
                    }
                }

                // Optional: Generate dynamic headers
//                List<Map<String, String>> nomineeDynamicHeaders = new ArrayList<>();
//                for (int i = 0; i < maxNominees; i++) {
//                    nomineeDynamicHeaders.add(Map.of("label", "Nominee " + (i + 1) + " PAN", "key", "nomineePanNumber_" + i));
//                    nomineeDynamicHeaders.add(Map.of("label", "Nominee " + (i + 1) + " Name", "key", "nomineeName_" + i));
//                    nomineeDynamicHeaders.add(Map.of("label", "Nominee " + (i + 1) + " DOB", "key", "nomineeDOB_" + i));
//                    nomineeDynamicHeaders.add(Map.of("label", "Nominee " + (i + 1) + " BLA", "key", "nomineeBlaNumber_" + i));
//                    nomineeDynamicHeaders.add(Map.of("label", "Nominee " + (i + 1) + " Subscription ID", "key", "nomineeSubscriptionID_" + i));
//                }

                resultMap.put("list", groupedList);
//                resultMap.put("headers", nomineeDynamicHeaders);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }

        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }



    @Override
    public ResponseDataDTO getNomineeReport(NomineeContinuationReportDTO nomineeContinuationReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<NomineeContinuationReportDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, nomineeContinuationReportDTO.getSchemeCode());
            inParams.put(flag, nomineeContinuationReportDTO.getFlag());
            inParams.put(fromDate, nomineeContinuationReportDTO.getFromDate());
            inParams.put(toDate, nomineeContinuationReportDTO.getToDate());
            inParams.put(p_page_no_in, nomineeContinuationReportDTO.getPageNo());
            inParams.put(p_page_size_in, nomineeContinuationReportDTO.getPageSize());
            inParams.put(p_search_value_in, (null!=nomineeContinuationReportDTO.getSearchValue()&&!nomineeContinuationReportDTO.getSearchValue().trim().isEmpty())?nomineeContinuationReportDTO.getSearchValue():null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                NomineeContinuationReportDTO item = new NomineeContinuationReportDTO();
                if (nomineeContinuationReportDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (nomineeContinuationReportDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setSchemeName(rs.getString(1));
                    item.setDeathMarkedHolderPan(rs.getString(2));
                    item.setDeathHolderName(rs.getString(3));
                    item.setDeathMarkedHolderBlaNo(rs.getString(4));
                    item.setDeathMarkedHolderSubscriptionId(rs.getString(5));
                    item.setDateOfDeathOfBondHolder(rs.getString(6));
                    item.setDateOfReceiptOfDeathCertificate(rs.getString(7));
                    item.setDeathMarkingDate(rs.getString(8));
                    item.setMainSubscriptionAmount(rs.getString(9));

                    item.setNominee1Id(rs.getString(10));
                    item.setNominee1Name(rs.getString(11));
                    item.setNominee1PanNo(rs.getString(12));
                    item.setNominee1Dob(rs.getString(13));
                    item.setNominee1SharePercentage(rs.getString(14));
                    item.setNominee1BlaNo(rs.getString(15));
                    item.setNominee1SubscriptionId(rs.getString(16));
                    item.setNominee1SubscriptionAmount(rs.getString(17));
                    item.setNominee1ModeOfInterestPayment(rs.getString(18));

                    item.setNominee2Id(rs.getString(19));
                    item.setNominee2Name(rs.getString(20));
                    item.setNominee2PanNo(rs.getString(21));
                    item.setNominee2Dob(rs.getString(22));
                    item.setNominee2SharePercentage(rs.getString(23));
                    item.setNominee2BlaNo(rs.getString(24));
                    item.setNominee2SubscriptionId(rs.getString(25));
                    item.setNominee2SubscriptionAmount(rs.getString(26));
                    item.setNominee2ModeOfInterestPayment(rs.getString(27));

                    item.setNominee3Id(rs.getString(28));
                    item.setNominee3Name(rs.getString(29));
                    item.setNominee3PanNo(rs.getString(30));
                    item.setNominee3Dob(rs.getString(31));
                    item.setNominee3SharePercentage(rs.getString(32));
                    item.setNominee3BlaNo(rs.getString(33));
                    item.setNominee3SubscriptionId(rs.getString(34));
                    item.setNominee3SubscriptionAmount(rs.getString(35));
                    item.setNominee3ModeOfInterestPayment(rs.getString(36));

                    item.setNominee4Id(rs.getString(37));
                    item.setNominee4Name(rs.getString(38));
                    item.setNominee4PanNo(rs.getString(39));
                    item.setNominee4Dob(rs.getString(40));
                    item.setNominee4SharePercentage(rs.getString(41));
                    item.setNominee4BlaNo(rs.getString(42));
                    item.setNominee4SubscriptionId(rs.getString(43));
                    item.setNominee4SubscriptionAmount(rs.getString(44));
                    item.setNominee4ModeOfInterestPayment(rs.getString(45));

                    item.setBondType(rs.getString(46));
// Add more fields below if there are additional columns beyond 42

// If there are fields like:
                    item.setAccountOpenDate(rs.getString(47));
                    item.setSubscriptionTransferredToNominee(rs.getString(48));
                    item.setPeriodOfDeposits(rs.getString(49));
                    item.setMaturityDate(rs.getString(50));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_nom_con_rep",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<NomineeContinuationReportDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO getDetailsOfPdf(NetBankingTransectionPdfDTO netBankingTransectionPdfDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_gbm_rbi_bond_rep_net_bank_tran_holders.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, netBankingTransectionPdfDTO.getFlag());
                stmt.setString(index++, netBankingTransectionPdfDTO.getSchemeCode());
                stmt.setString(index++, netBankingTransectionPdfDTO.getBondHolderName());
                stmt.setString(index++, netBankingTransectionPdfDTO.getPanNum());
                stmt.setString(index++, netBankingTransectionPdfDTO.getBlaNo());
                stmt.setString(index++, netBankingTransectionPdfDTO.getSubscriptionId());
                stmt.setString(index++, netBankingTransectionPdfDTO.getFromDate());
                stmt.setString(index++, netBankingTransectionPdfDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {


                    if (netBankingTransectionPdfDTO.getFlag().equalsIgnoreCase("S")) {


                        ResultSet resultSet = (ResultSet) stmt.getObject(17);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();


                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("subCode", resultSet.getString(1));
                                temp.put("subCodeDescription", resultSet.getString(2));
                                temp.put("status", resultSet.getString(3));
                                torchOut.add(temp);

                            }
                            netBankingTransectionPdfDTO.setTorchValue(torchOut);
                        }


                    } else if (netBankingTransectionPdfDTO.getFlag().equalsIgnoreCase("P")) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(17);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("panHldName", resultSet.getString(1));
                                temp.put("panNum", resultSet.getString(2));
                                torchOut.add(temp);
                            }
                            netBankingTransectionPdfDTO.setTorchValue(torchOut);
                        }

                    } else if (netBankingTransectionPdfDTO.getFlag().equalsIgnoreCase("B")) {


                        ResultSet resultSet = (ResultSet) stmt.getObject(17);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();


                                temp.put("modeOfHolding", resultSet.getString(1));
                                temp.put("nameOfTheBondHolder", resultSet.getString(2));
                                temp.put("panNo", resultSet.getString(3));
                                temp.put("blaNo", resultSet.getString(4));

                                torchOut.add(temp);
                            }
                            netBankingTransectionPdfDTO.setTorchValue(torchOut);
                        }
                    } else if (netBankingTransectionPdfDTO.getFlag().equalsIgnoreCase("BR")) {


                        ResultSet resultSet = (ResultSet) stmt.getObject(17);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();


                                temp.put("bondHolderName", resultSet.getString(1));
                                temp.put("panNo", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));
                                temp.put("subscriptionId", resultSet.getString(4));


                                torchOut.add(temp);
                            }
                            netBankingTransectionPdfDTO.setTorchValue(torchOut);
                        }

                    } else if (netBankingTransectionPdfDTO.getFlag().equalsIgnoreCase("D")) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(17);
                        List<HashMap<String, String>> torchOut = null;
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("pan", resultSet.getString(1));
                                temp.put("holderName", resultSet.getString(2));
                                temp.put("bla", resultSet.getString(3));
                                temp.put("modeOfHolding", resultSet.getString(4));
                                temp.put("noOfJointHolders", resultSet.getString(5));
                                temp.put("subscriptionId", resultSet.getString(6));
                                temp.put("subscriptionAmount", resultSet.getString(7));

                                torchOut.add(temp);
                            }
                            netBankingTransectionPdfDTO.setTorchValue(torchOut);
                        }


                    } else if (netBankingTransectionPdfDTO.getFlag().equalsIgnoreCase("Z")) {


                        ResultSet resultSet = (ResultSet) stmt.getObject(11);
                        List<HashMap<String, String>> branhDetailsOut = null;
                        if (resultSet != null) {
                            HashMap<String, String> branchDetails = null;
                            branhDetailsOut = new ArrayList<>();
                            while (resultSet.next()) {
                                branchDetails = new HashMap<>();


                                branchDetails.put("brokerCode", resultSet.getString(1));
                                branchDetails.put("branchCode", resultSet.getString(2));
                                branchDetails.put("bankCode", resultSet.getString(3));
                                branchDetails.put("bankName", resultSet.getString(4));
                                branchDetails.put("addressOfBranch", resultSet.getString(5));
                                branchDetails.put("bankBranchStamp", resultSet.getString(6));
                                branchDetails.put("bondDate", resultSet.getString(7));
                                branhDetailsOut.add(branchDetails);
                            }
                            netBankingTransectionPdfDTO.setBranchDetails(branhDetailsOut);
                        }
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(12);
                        List<HashMap<String, String>> otherDetailsOut = null;
                        if (resultSet1 != null) {
                            HashMap<String, String> otherDetails = null;
                            otherDetailsOut = new ArrayList<>();
                            while (resultSet1.next()) {
                                otherDetails = new HashMap<>();
                                otherDetails.put("accountHolderName", resultSet1.getString(1));
                                otherDetails.put("bankName", resultSet1.getString(2));
                                otherDetails.put("branchAddress", resultSet1.getString(3));
                                otherDetails.put("bankMicrCode", resultSet1.getString(4));
                                otherDetails.put("branchMicrCode", resultSet1.getString(5));
                                otherDetails.put("accountNumber", resultSet1.getString(6));
                                otherDetails.put("ifscCode", resultSet1.getString(7));
                                otherDetails.put("accountType", resultSet1.getString(8));
                                otherDetailsOut.add(otherDetails);
                            }
                            netBankingTransectionPdfDTO.setHolderOtherDetails(otherDetailsOut);
                        }
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(13);
                        List<HashMap<String, String>> gaurdianDetailsOut = null;
                        if (resultSet2 != null) {
                            HashMap<String, String> gaurdianDetails = null;
                            gaurdianDetailsOut = new ArrayList<>();
                            while (resultSet2.next()) {
                                gaurdianDetails = new HashMap<>();

                                gaurdianDetails.put("nameInFull", resultSet2.getString(1));
                                gaurdianDetails.put("dob", resultSet2.getString(2));
                                gaurdianDetails.put("relationWithMinor", resultSet2.getString(3));
                                gaurdianDetails.put("gaurdAddress", resultSet2.getString(4));
                                gaurdianDetails.put("gaurdMobileNumber", resultSet2.getString(5));
                                gaurdianDetails.put("gaurdEmailId", resultSet2.getString(6));


                                gaurdianDetailsOut.add(gaurdianDetails);
                            }
                            netBankingTransectionPdfDTO.setGaurdianDetails(gaurdianDetailsOut);
                        }


                        ResultSet resultSet3 = (ResultSet) stmt.getObject(15);
                        List<HashMap<String, String>> particulerDetailsOut = null;
                        if (resultSet3 != null) {
                            HashMap<String, String> holderParticulerDetails = null;
                            particulerDetailsOut = new ArrayList<>();
                            while (resultSet3.next()) {
                                holderParticulerDetails = new HashMap<>();

                                holderParticulerDetails.put("applicationStatus", resultSet3.getString(1));
                                holderParticulerDetails.put("modeOfHolding", resultSet3.getString(2));
                                holderParticulerDetails.put("modeOfInvest", resultSet3.getString(3));
                                holderParticulerDetails.put("investMentAmount", resultSet3.getString(4));
                                holderParticulerDetails.put("ddAccNo", resultSet3.getString(5));


                                particulerDetailsOut.add(holderParticulerDetails);
                            }
                            netBankingTransectionPdfDTO.setParticulerBankDetails(particulerDetailsOut);
                        }


                        ResultSet resultSet4 = (ResultSet) stmt.getObject(16);
                        List<HashMap<String, String>> nomineeDetailsOut = null;
                        if (resultSet4 != null) {
                            HashMap<String, String> holderNomineeDetails = null;
                            nomineeDetailsOut = new ArrayList<>();
                            while (resultSet4.next()) {
                                holderNomineeDetails = new HashMap<>();

                                holderNomineeDetails.put("amount", resultSet4.getString(1));
                                holderNomineeDetails.put("name", resultSet4.getString(2));
                                holderNomineeDetails.put("addressOfNominee", resultSet4.getString(3));
                                holderNomineeDetails.put("relationWithHolder", resultSet4.getString(4));
                                holderNomineeDetails.put("statusResident", resultSet4.getString(5));
                                holderNomineeDetails.put("dateOfIssue", resultSet4.getString(6));


                                nomineeDetailsOut.add(holderNomineeDetails);
                            }
                            netBankingTransectionPdfDTO.setNomineeDetails(nomineeDetailsOut);
                        }


                        ResultSet resultSet5 = (ResultSet) stmt.getObject(14);
                        List<HashMap<String, String>> applicationDetailsOut = null;
                        if (resultSet5 != null) {
                            HashMap<String, String> holderApplicationDetails = null;
                            applicationDetailsOut = new ArrayList<>();
                            while (resultSet5.next()) {
                                holderApplicationDetails = new HashMap<>();

                                holderApplicationDetails.put("sNo", resultSet5.getString(1));
                                holderApplicationDetails.put("nameInFull", resultSet5.getString(2));
                                holderApplicationDetails.put("gender", resultSet5.getString(3));
                                holderApplicationDetails.put("dob", resultSet5.getString(4));
                                holderApplicationDetails.put("pan", resultSet5.getString(5));
                                holderApplicationDetails.put("motherName", resultSet5.getString(6));
                                holderApplicationDetails.put("communicationAddress", resultSet5.getString(7));
                                holderApplicationDetails.put("mobileNumber", resultSet5.getString(8));
                                holderApplicationDetails.put("email", resultSet5.getString(9));
                                holderApplicationDetails.put("applicationNumber", resultSet5.getString(10));


                                applicationDetailsOut.add(holderApplicationDetails);
                            }
                            netBankingTransectionPdfDTO.setApplicationDetails(applicationDetailsOut);
                        }


//                        ResultSet resultSet5 = (ResultSet) stmt.getObject(13);
//                        List<HashMap<String, String>> applicationDetailsOut = null;
//                        if (resultSet5 != null) {
//                            HashMap<String, String> gaurdianDetails = null;
//                            gaurdianDetailsOut = new ArrayList<>();
//                            while (resultSet.next()) {
//                                gaurdianDetails = new HashMap<>();
//
//                                gaurdianDetails.put("accountHolderName", resultSet5.getString(1));
//                                gaurdianDetails.put("bankName", resultSet5.getString(2));
//                                gaurdianDetails.put("branchAddress", resultSet5.getString(3));
//                                gaurdianDetails.put("bankMicrCode", resultSet5.getString(4));
//                                gaurdianDetails.put("branchMicrCode", resultSet5.getString(5));
//                                gaurdianDetails.put("accountNumber", resultSet5.getString(6));
//                                gaurdianDetails.put("ifscCode", resultSet5.getString(7));
//                                gaurdianDetails.put("accountType", resultSet5.getString(8));
//
//
//                                gaurdianDetailsOut.add(gaurdianDetails);
//                            }
//                            netBankingTransectionPdfDTO.setGaurdianDetails(gaurdianDetailsOut);
//                        }


                    }


                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(netBankingTransectionPdfDTO);
        return responseDataDTO;
    }








    @Override
    public ResponseDataDTO getJointTypeDetails(JointTypeThreeToTwoReportDTO jointTypeThreeToTwoReportDTO) {


        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<JointTypeThreeToTwoReportDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(flag, jointTypeThreeToTwoReportDTO.getFlag());
            inParams.put(schemeCode, jointTypeThreeToTwoReportDTO.getSchemeCode());
            inParams.put(fromDate, jointTypeThreeToTwoReportDTO.getFromDate());
            inParams.put(toDate, jointTypeThreeToTwoReportDTO.getToDate());
            inParams.put(p_page_no_in, jointTypeThreeToTwoReportDTO.getPageNo());
            inParams.put(p_page_size_in, jointTypeThreeToTwoReportDTO.getPageSize());
            inParams.put(p_search_value_in, (null != jointTypeThreeToTwoReportDTO.getSearchValue() && !jointTypeThreeToTwoReportDTO.getSearchValue().trim().isEmpty()) ? jointTypeThreeToTwoReportDTO.getSearchValue() : null);

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                JointTypeThreeToTwoReportDTO item = new JointTypeThreeToTwoReportDTO();
                if (jointTypeThreeToTwoReportDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (jointTypeThreeToTwoReportDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setFirstHolderName(rs.getString(1));              // 2
                    item.setFirstHolderPan(rs.getString(2));               // 5
                    item.setSecondHolderName(rs.getString(3));             // 3
                    item.setSecondHolderPan(rs.getString(4));              // 6
                    item.setThirdHolderName(rs.getString(5));              // 4
                    item.setThirdHolderPan(rs.getString(6));               // 7
                    item.setModeOfHolding(rs.getString(7));                // 8
                    item.setFromBlaAccountNumber(rs.getString(8));         // 9
                    item.setActiveSubscriptionId(rs.getString(9));        // 10
                    item.setActiveSubscriptionCount(rs.getString(10));     // 11
                    item.setToBlaAccountNumber(rs.getString(11));          // 12
                    item.setToFirstHolderName(rs.getString(12));           // 13
                    item.setToFirstHolderPan(rs.getString(13));            // 14
                    item.setToSecondHolderName(rs.getString(14));          // 15
                    item.setToSecondHolderPan(rs.getString(15));           // 16
                    item.setDateOfChangeInJointType(rs.getString(16));     // 17 (Or use rs.getDate(17) if it's a Date)
                    item.setRemarks(rs.getString(17));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_RBI_BOND_REP_JOINT_CHANGE",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<JointTypeThreeToTwoReportDTO>) result.get(curValOut);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal(Double.parseDouble(totalRecords));
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO getCpTotalDetails(CertificatePrintingDTO certificatePrintingDTO){



        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        double totalB=0.0;
        List<CertificatePrintingDTO> list=null;
        List<CertificatePrintingDTO> listAck=null;
        List<CertificatePrintingDTO> listMailF=null;
        List<CertificatePrintingDTO> listMailNF=null;
        Map<String,List>CertificatePdetails=null;
        ResultSet resultSetCoh =null;
        ResultSet resultSetAck = null;
        ResultSet resultSetMailF = null;
        ResultSet resultSetMailNF = null;

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_CERT_PRI.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, certificatePrintingDTO.getSchemeCode());
                stmt.setString(index++, certificatePrintingDTO.getFlag());
                stmt.setString(index++, certificatePrintingDTO.getValueDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();


                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);



                String value = stmt.getString(6);
                System.out.println("total "+stmt.getString(6));
                totalB = (value != null && !value.isBlank())
                        ? Double.parseDouble(value)
                        : 0.0;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (certificatePrintingDTO.getSchemeCode() == null && certificatePrintingDTO.getFlag().equalsIgnoreCase("C")) {

                        ResultSet resultSet = (ResultSet) stmt.getObject(7);

                        if (resultSet != null) {
                            list = new ArrayList<>();
                            CertificatePrintingDTO item = null;
                            while (resultSet.next()) {
                                item = new CertificatePrintingDTO();

                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));


                                list.add(item);

                            }

                            responseDataDTO.setResult(list);
                        }


                    } else if (certificatePrintingDTO.getSchemeCode() != null && certificatePrintingDTO.getValueDate() != null&&certificatePrintingDTO.getFlag().equalsIgnoreCase("D")) {

                         resultSetCoh = (ResultSet) stmt.getObject(7);
                         resultSetAck = (ResultSet) stmt.getObject(8);
                         resultSetMailF = (ResultSet) stmt.getObject(9);
                         resultSetMailNF = (ResultSet) stmt.getObject(10);


                        String notificationNo = stmt.getString(11);
                        String notificationDate = stmt.getString(12);
                        String intPaymentDate1 = stmt.getString(13);
                        String intPaymentDate2 = stmt.getString(14);


                        if (resultSetCoh != null) {
                            CertificatePrintingDTO item = null;
                            list = new ArrayList<>();
                            while (resultSetCoh.next()) {
                                item = new CertificatePrintingDTO();
                                item.setBlaNo(resultSetCoh.getString(1));
                                item.setHolderName(resultSetCoh.getString(2));
                                item.setPanNumber(resultSetCoh.getString(3));
                                item.setAddress(resultSetCoh.getString(4));
                                item.setAddressTwo(resultSetCoh.getString(5));
                                item.setAddressThree(resultSetCoh.getString(6));
                                item.setPinCode(resultSetCoh.getString(7));
                                item.setCity(resultSetCoh.getString(8));
                                item.setState(resultSetCoh.getString(9));
                                item.setInvestDate(resultSetCoh.getString(10));
                                item.setInvestAmount(resultSetCoh.getString(11));
                                item.setCertificateNo(resultSetCoh.getString(12));
                                item.setTransferDate(resultSetCoh.getString(13));
                                item.setTransferAmount(resultSetCoh.getString(14));
                                item.setDueDateOfRepayment(resultSetCoh.getString(15));
                                item.setRepaymentDate(resultSetCoh.getString(16));
                                item.setRepaymentAmount(resultSetCoh.getString(17));
                                item.setBranchCode(resultSetCoh.getString(18));
                                item.setBondSubscriptionId(resultSetCoh.getString(19));
                                item.setModeOfHolding(resultSetCoh.getString(20));
                                item.setFirstJointBlaName(resultSetCoh.getString(21));
                                item.setSecondJointBlaName(resultSetCoh.getString(22));
                                item.setThirdJointBlaName(resultSetCoh.getString(23));
                                item.setNomineeID(resultSetCoh.getString(24));
                                item.setBalance(resultSetCoh.getString(25));
                                item.setWboOffice(resultSetCoh.getString(26));
                                item.setGuardianName(resultSetCoh.getString(27));
                                item.setSchemeName(resultSetCoh.getString(28));
                                item.setGovtNotNo(notificationNo);
                                item.setGovtNotDate(notificationDate);
                                item.setIntPaymentDate1(intPaymentDate1);
                                item.setIntPaymentDate2(intPaymentDate2);

                                list.add(item);
                            }
                        }

                        if (resultSetAck != null) {
                            CertificatePrintingDTO item = null;
                            listAck = new ArrayList<>();
                            while (resultSetAck.next()) {
                                item = new CertificatePrintingDTO();
                                item.setBlaNo(resultSetAck.getString(1));
                                item.setBondType(resultSetAck.getString(2));
                                item.setName(resultSetAck.getString(3));
                                item.setBankName(resultSetAck.getString(4));
                                item.setAccNo(resultSetAck.getString(5));
                                item.setMicrCode(resultSetAck.getString(6));
                                item.setCohNo(resultSetAck.getString(7));
                                item.setNominationUnder(resultSetAck.getString(8));
                                item.setTransactionDate(resultSetAck.getString(9));
                                item.setHolderName(resultSetAck.getString(10));
                                item.setAddress(resultSetAck.getString(11));
                                item.setAddressTwo(resultSetAck.getString(12));
                                item.setAddressThree(resultSetAck.getString(13));
                                item.setState(resultSetAck.getString(14));
                                item.setCity(resultSetAck.getString(15));
                                item.setPincode(resultSetAck.getString(16));
                                item.setActualTranDate(resultSetAck.getString(17));
                                item.setBranchRefNo(resultSetAck.getString(18));
                                item.setGuardianName(resultSetAck.getString(19));
                                item.setSchemeName(resultSetAck.getString(20));
                                item.setStatus(resultSetAck.getString(21));

                                listAck.add(item);

                            }
                        }



                        if (resultSetMailF != null) {
                            CertificatePrintingDTO item = null;
                            listMailF = new ArrayList<>();
                            while (resultSetMailF.next()) {
                                item = new CertificatePrintingDTO();
                                item.setMailId(resultSetMailF.getString(1));
                                item.setMailCc(resultSetMailF.getString(2));
                                item.setMailBcc(resultSetMailF.getString(3));
                                item.setSubject(resultSetMailF.getString(4));
                                item.setAttachment(resultSetMailF.getString(5));
                                item.setSmsTo(resultSetMailF.getString(6));
                                item.setBarcode(resultSetMailF.getString(7));
                                item.setBlaNo(resultSetMailF.getString(8));
                                item.setCfd(resultSetMailF.getString(9));
                                item.setApplName(resultSetMailF.getString(10));
                                item.setInvestAmt(resultSetMailF.getString(11));
                                item.setMaturityDate(resultSetMailF.getString(12));

                                listMailF.add(item);



                            }
                        }







                        if (resultSetMailNF != null) {
                            CertificatePrintingDTO item = null;
                            listMailNF = new ArrayList<>();
                            while (resultSetMailNF.next()) {
                                item = new CertificatePrintingDTO();
                                item.setMailId(resultSetMailNF.getString(1));
                                item.setMailCc(resultSetMailNF.getString(2));
                                item.setMailBcc(resultSetMailNF.getString(3));
                                item.setSubject(resultSetMailNF.getString(4));
                                item.setAttachment(resultSetMailNF.getString(5));
                                item.setSmsTo(resultSetMailNF.getString(6));
                                item.setBarcode(resultSetMailNF.getString(7));
                                item.setBlaNo(resultSetMailNF.getString(8));
                                item.setCfd(resultSetMailNF.getString(9));
                                item.setApplName(resultSetMailNF.getString(10));
                                item.setInvestAmt(resultSetMailNF.getString(11));
                                item.setMaturityDate(resultSetMailNF.getString(12));
//                                item.setC(resultSetMailNF.getString(9));
//                                item.setApplication(resultSetMailNF.getString(10));
//                                item.setAddress(resultSetMailNF.getString(11));
                                listMailNF.add(item);

                            }
                        }

//                        resultSetAck = (ResultSet) stmt.getObject(8);

                        CertificatePdetails=new HashMap<>();
                        CertificatePdetails.put("cohDet",list);
                        CertificatePdetails.put("ackDet",listAck);
                        CertificatePdetails.put("mailfDet",listMailF);
                        CertificatePdetails.put("mailNfDet",listMailNF);
                        responseDataDTO.setResult(CertificatePdetails);
                    }
                }
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setTotal(totalB);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getHufDeathMarkingDetails(HUFDeathMarkingReportDTO hufDeathMarkingReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<HUFDeathMarkingReportDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(flag, hufDeathMarkingReportDTO.getFlag());
            inParams.put(panNumber, hufDeathMarkingReportDTO.getPanNo());
            inParams.put(blaNumber, hufDeathMarkingReportDTO.getBlaNo());
            inParams.put(fromDate, hufDeathMarkingReportDTO.getFromDate());
            inParams.put(toDate, hufDeathMarkingReportDTO.getToDate());
            inParams.put(p_page_no_in, hufDeathMarkingReportDTO.getPageNo());
            inParams.put(p_page_size_in, hufDeathMarkingReportDTO.getPageSize());
            inParams.put(p_search_value_in, hufDeathMarkingReportDTO.getSearchValue());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            String requestFlag = hufDeathMarkingReportDTO.getFlag();

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                HUFDeathMarkingReportDTO item = new HUFDeathMarkingReportDTO();
                item.setFlag(requestFlag);

                if ("P".equalsIgnoreCase(requestFlag)) {
                    item.setPanNo(rs.getString(1));
                    item.setBondHolderName(rs.getString(2));

                } else if ("B".equalsIgnoreCase(requestFlag) && hufDeathMarkingReportDTO.getPageNo()!= null) {
                    item.setPanNo(rs.getString(1));
                    item.setBondHolderName(rs.getString(2));
                    item.setBlaNo(rs.getString(3));

                } else if ("D".equalsIgnoreCase(requestFlag)) {
                    item.setPanNo(rs.getString(1));
                    item.setBondHolderName(rs.getString(2));
                    item.setDateOfDeathOfKarta(rs.getString(3));
                    item.setKartaDeathMarking(rs.getString(4));
                    item.setKartaDeathMarkingRemarks(rs.getString(5));
                    item.setOldKartaName(rs.getString(6));
                    item.setNextEligibleCoParcenerName(rs.getString(7));
                    item.setDobOfNewKarta(rs.getString(8));
                    item.setRelationshipWithKarta(rs.getString(9));
                    item.setKartaChangeReferenceNo(rs.getString(10));
                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_HUF_DEATH",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<HUFDeathMarkingReportDTO>) result.get(curValOut);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
                responseDataDTO.setTotal((double) list.size());
            }
        } catch (DataAccessException e) {
            log.error("Spring JDBC error while fetching HUF death marking report", e);
            Throwable root = e.getRootCause();
            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.error("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }
            responseDataDTO.setMessage(ErrorMessages.DATABASE_ERROR);
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

//    @Override
//    public ResponseDataDTO uploadDataPatch(MultipartFile file, DataPatchUploadRequestDTO requestDTO) {
//        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
//        Connection connection = null;
//        CallableStatement stmt = null;
//        String resultMsg = "";
//
//        try {
//            connection = connectionPoolFactory.getConnection();
//            if (connection == null) {
//                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
//                responseDataDTO.setMessage(ErrorMessages.INTERNAL_SERVER_ERROR);
//                return responseDataDTO;
//            }
//
//            int index = 1;
//            stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_DATA_PATCH_UPLOAD.getProcName());
//            stmt.setString(index++, UserAccountUtils.getCurrentUsername());
//            stmt.setString(index++, UserAccountUtils.getCurrentSolId());
//            stmt.setString(index++, requestDTO.getFromDate());
//            stmt.setString(index++, requestDTO.getToDate());
//            stmt.setString(index++, requestDTO.getFileName());
//
//            if (requestDTO.getUploadList() != null && !requestDTO.getUploadList().isEmpty()) {
//                stmt.setArray(index++, generateDataPatchUploadArray(connection, requestDTO.getUploadList()));
//            } else {
//                stmt.setNull(index++, Types.ARRAY, "TYP_RBI_BOND_DATA_PATCH");
//            }
//
//            int cursorIndex = index++;
//            stmt.registerOutParameter(cursorIndex, OracleTypes.CURSOR);
//            int outStatusIndex = index++;
//            int outMsgIndex = index;
//            stmt.registerOutParameter(outStatusIndex, Types.VARCHAR);
//            stmt.registerOutParameter(outMsgIndex, Types.VARCHAR);
//            stmt.execute();
//
//            log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
//            resultMsg = stmt.getString(outMsgIndex);
//            log.info("{}: status={}, message={}", CommonConstants.STATUS_MESSAGE,
//                    stmt.getString(outStatusIndex), resultMsg);
//
//            if (stmt.getString(outStatusIndex) != null
//                    && stmt.getString(outStatusIndex).equalsIgnoreCase(CommonConstants.SUCCESS)) {
//                ResultSet resultSet = (ResultSet) stmt.getObject(cursorIndex);
//                if (resultSet != null && resultSet.next()) {
//                    Map<String, String> uploadSummary = new HashMap<>();
//                    uploadSummary.put("uploadedCount", resultSet.getString(1));
//                    responseDataDTO.setResult(uploadSummary);
//                }
//            } else {
//                responseDataDTO.setStatusCode("0");
//            }
//        } catch (Exception e) {
//            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
//            responseDataDTO.setMessage(ErrorMessages.DATABASE_ERROR);
//            return responseDataDTO;
//        } finally {
//            connectionPoolFactory.closeConnection(connection, stmt);
//        }
//
//        responseDataDTO.setMessage(resultMsg);
//        return responseDataDTO;
//    }

   /* private ARRAY generateDataPatchUploadArray(Connection connection, List<DataPatchUploadRowDTO> uploadList)
            throws Exception {
        StructDescriptor structDescriptor = StructDescriptor.createDescriptor("TYP_RBI_BOND_DATA_PATCH_OBJ", connection);
        ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_DATA_PATCH", connection);
        STRUCT[] structArray = new STRUCT[uploadList.size()];

        for (int i = 0; i < uploadList.size(); i++) {
            DataPatchUploadRowDTO rowDTO = uploadList.get(i);
            Object[] paramArray = new Object[2];
            paramArray[0] = rowDTO.getSerialNo();
            paramArray[1] = rowDTO.getRowData();
            structArray[i] = new STRUCT(structDescriptor, connection, paramArray);
        }

        return new ARRAY(arrayDescriptor, connection, structArray);
    }
*/
}
