package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.JdbcConnectionFactory;
import com.isl.gbm.rbibond.dao.RbiBondReportDAOLatest;
import com.isl.gbm.rbibond.dto.reports.*;
import com.isl.gbm.rbibond.util.JDBCTemplateUtil;
import com.isl.gbm.rbibond.util.RbiBondReportsUtil;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import jakarta.annotation.PostConstruct;
import oracle.jdbc.OracleTypes;
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

import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.isl.gbm.rbibond.util.CommonReportsConstants.*;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.blaNumber;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.curValOut;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.flag;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.fromDate;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.msgDescOut;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.msgOut;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.panNumber;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.schemeCode;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.solID;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.recordCountOut;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.toDate;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.userID;

@Repository
public class RbiBondReportDAOImplLatest  extends HibernateDao implements RbiBondReportDAOLatest {
    private static final Logger log = LoggerFactory.getLogger(RbiBondReportsDAOImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private JdbcConnectionFactory jdbcConnectionFactory;
    @Autowired
    private final RbiBondReportsUtil rbiBondReportsUtil;

    public RbiBondReportDAOImplLatest(RbiBondReportsUtil rbiBondReportsUtil) {
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
    public ResponseDataDTO getPortfolio(PortfolioDTO portfoliodto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<PortfolioDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode,portfoliodto.getSchemeCode());
            inParams.put(fromDate,portfoliodto.getFromDate());
            inParams.put(toDate,portfoliodto.getToDate());




            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
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
                PortfolioDTO item = new PortfolioDTO();
                if (portfoliodto.getSchemeCode()==null) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                }else if(portfoliodto.getSchemeCode()!=null){
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setBranchRefNo(rs.getString(3));
                    item.setBlaNo(rs.getString(4));
                    item.setApplName1(rs.getString(5));
                    item.setApplName2(rs.getString(6));
                    item.setApplName3(rs.getString(7));
                    item.setPanNumber(rs.getString(8));
                    item.setInvstAmount(rs.getString(9));
                    item.setMaturityAmount(rs.getString(10));
                    item.setScheme(rs.getString(11));
                    item.setCfd(rs.getString(12));
                    item.setCasDate(rs.getString(13));
                    item.setMaturityDate(rs.getString(14));
                    item.setAccountNo(rs.getString(15));
                    item.setMicrCode(rs.getString(16));
                    item.setIfscCode(rs.getString(17));
                    item.setBankName(rs.getString(18));
                    item.setBrokerCode(rs.getString(19));
                    item.setStatus(rs.getString(20));
                    item.setEmail(rs.getString(21));
                    item.setMobileNo(rs.getString(22));
                    item.setPhoneNo(rs.getString(23));
                    item.setAddress1(rs.getString(24));
                    item.setAddress2(rs.getString(25));
                    item.setCity(rs.getString(26));
                    item.setState(rs.getString(27));
                    item.setPinCode(rs.getString(28));
                    item.setPaymentMode(rs.getString(29));
                   item.setGsp_BLA_NO(rs.getString(30));

                }


                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_REP_PORTFOLIO_REPORTS",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<PortfolioDTO>) result.get(curValOut);

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
    public ResponseDataDTO getInetPortfolioDetails(InetPortfolioRequestDTO portfoliodto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<InetPortfolioResponseDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(fromDate, portfoliodto.getFromDate());
            inParams.put(toDate, portfoliodto.getToDate());
            inParams.put(p_page_no_in, portfoliodto.getPageNo() != null ? Integer.valueOf(portfoliodto.getPageNo()) : 1);
            inParams.put(p_page_size_in, portfoliodto.getPageSize() != null ? Integer.valueOf(portfoliodto.getPageSize()) : 5);
            inParams.put(p_search_value_in, portfoliodto.getSearchValue() != null ? portfoliodto.getSearchValue().trim() : "");

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.NUMERIC),
                    new SqlParameter(p_page_size_in, Types.NUMERIC),
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
                InetPortfolioResponseDTO item = new InetPortfolioResponseDTO();

                item.setRequestId(rs.getString(1));
                item.setPanNo(rs.getString(2));
                item.setReqDateTime(rs.getString(3));
                item.setResDateTime(rs.getString(4));
                item.setResStatus(rs.getString(5));

                return item;

            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_INET_PORTFOLIO_REPORT_DETAILS",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<InetPortfolioResponseDTO>) result.get(curValOut);
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
            log.info("Spring JDBC error = {}", e.getMessage());
            Throwable root = e.getRootCause();

            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }

            responseDataDTO.setMessage(e.getMessage());
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
    public ResponseDataDTO getTransferReport(TransferReportDTO transferReportDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<TransferReportDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";

        try {
            final String requestFlag = transferReportDTO.getFlag() != null
                    ? transferReportDTO.getFlag().trim()
                    : "";

            Map<String, Object> inParams = new HashMap<>();

            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, transferReportDTO.getSchemeCode());
            inParams.put(flag, transferReportDTO.getFlag());
            inParams.put(tranFlag, transferReportDTO.getFlag());
            inParams.put(name, transferReportDTO.getName());
            inParams.put(panNumber, transferReportDTO.getPanNo());
            inParams.put(blaNumber, transferReportDTO.getBlaNo());
            inParams.put(fromDate, transferReportDTO.getFromDate());
            inParams.put(toDate, transferReportDTO.getToDate());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(tranFlag, Types.VARCHAR),
                    new SqlParameter(name, Types.VARCHAR),
                    new SqlParameter(panNumber, Types.VARCHAR),
                    new SqlParameter(blaNumber, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(recordCountOut, OracleTypes.NUMBER),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();

            rowMappers.put(curValOut, (rs, rowNum) -> {
                TransferReportDTO item = new TransferReportDTO();

                if ("S".equalsIgnoreCase(requestFlag)) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                } else if ("P".equalsIgnoreCase(requestFlag)) {
                    item.setPanNo(rs.getString(1));
                    item.setHolderName(rs.getString(2));

                }
                 else if ("B".equalsIgnoreCase(requestFlag)) {
                    item.setPanNo(rs.getString(1));
                    item.setHolderName(rs.getString(2));
                    item.setModeOfHolding(rs.getString(3));
                    item.setBlaNo(rs.getString(4));

                } else if ("T".equalsIgnoreCase(requestFlag)) {
                    item.setFromBlaModeOfHolding(rs.getString(1));
                    item.setFromBlaAccountNumber(rs.getString(2));
                    item.setTransferorHolderName1(rs.getString(3));
                    item.setTransferorHolder1PanNo(rs.getString(4));
                    item.setTransferorHolderName2(rs.getString(5));
                    item.setTransferorHolder2PanNo(rs.getString(6));
                    item.setTransferorHolderName3(rs.getString(7));
                    item.setTransferorHolder3PanNo(rs.getString(8));
                    item.setToBlaModeOfHolding(rs.getString(9));
                    item.setToBlaAccountNumber(rs.getString(10));
                    item.setTransfereeHolderName1(rs.getString(11));
                    item.setTransfereeHolder1PanNo(rs.getString(12));
                    item.setTransfereeHolderName2(rs.getString(13));
                    item.setTransfereeHolder2PanNo(rs.getString(14));
                    item.setTransfereeHolderName3(rs.getString(15));
                    item.setTransfereeHolder3PanNo(rs.getString(16));
                    item.setTransferType(
                            rs.getString(18) != null ? rs.getString(18) : rs.getString(17));
                    item.setBondSchemeName(rs.getString(19));
                    item.setDateOfTransfer(rs.getString(20));
                    item.setRemarks(rs.getString(21));
                    item.setTransferorSubscriptionId(rs.getString(22));
                    item.setTransferredSubscriptionId(rs.getString(23));
                    item.setTransferredSubscriptionAmount(rs.getString(24));
                    item.setSubscriptionAmount(rs.getString(25));
                    item.setTransferredMaturityDate(rs.getString(26));
                    item.setMaturityDate(rs.getString(27));
                    item.setTransferredCfdDate(rs.getString(28));
                    item.setCfdDate(rs.getString(29));

                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_rep_transfer",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<TransferReportDTO>) result.get(curValOut);
            Object procMsgOut = result.get(msgOut);
            Object procCountOut = result.get(recordCountOut);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (procMsgOut != null && "F".equalsIgnoreCase(String.valueOf(procMsgOut))) {
                Object procMsgDesc = result.get(msgDescOut);
                String procDesc = procMsgDesc != null ? String.valueOf(procMsgDesc) : "";

                if (procDesc.toLowerCase().contains("no record")) {
                    resultMsg = CommonConstants.NDF;
                } else {
                    resultMsg = procDesc.isEmpty() ? CommonConstants.NDF : procDesc;
                }
            } else if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;

            } else {
                resultMap.put("list", list);
                if (procCountOut != null) {
                    resultMap.put("count", procCountOut);
                }

                responseDataDTO.setResult(resultMap);
            }
        } catch (DataAccessException e) {
            log.info("Spring JDBC error", e.getMessage());
            Throwable root = e.getRootCause();

            if (root instanceof SQLException) {
                SQLException sqlEx = (SQLException) root;
                log.info("Oracle Exception - ErrorCode: {}, SQLState: {}, Message: {}",
                        sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
            }

            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");

            return responseDataDTO;
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
}