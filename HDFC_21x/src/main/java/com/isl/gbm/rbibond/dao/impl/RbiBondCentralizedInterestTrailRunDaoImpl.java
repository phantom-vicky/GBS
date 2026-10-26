package com.isl.gbm.rbibond.dao.impl;


import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TranStatusDto;
import com.isl.gbm.common.exception.BusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DatabaseConnectionFactory;
import com.isl.gbm.rbibond.bo.RbiBondCentralizedInterestTrailRunBo;
import com.isl.gbm.rbibond.dao.RbiBondCentralizedInterestTrailRunDao;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.util.JDBCTemplateUtil;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.rbibond.util.TrailRunDetailsResultTransformer;
import com.isl.gbm.common.dto.AccountValidationDto;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import jakarta.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;

import static org.apache.poi.ss.util.CellUtil.createCell;


import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Repository
public class RbiBondCentralizedInterestTrailRunDaoImpl extends HibernateDao implements RbiBondCentralizedInterestTrailRunDao {


    private static final Logger log = LoggerFactory.getLogger(RbiBondCentralizedInterestTrailRunDaoImpl.class);


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    RbiBondCentralizedInterestTrailRunBo rbiBondCentralizedInterestTrailRunBo;

    private SessionFactory sessionFactory;

    @Autowired
    DatabaseConnectionFactory databaseConnectionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JDBCTemplateUtil jDBCTemplateUtil;

    @PostConstruct
    public void init() {
        jDBCTemplateUtil = new JDBCTemplateUtil(jdbcTemplate);
    }
    @Override
    public ResponseDataDTO getTrialRunDetailsReport(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) throws IOException {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, Object> trialRunEligibleList = new HashMap<>();
        List<RbiBondTrailRunReportDto> rbiBondTrailRunReportDtos = new ArrayList<>();
        final Session session = getSession();

        Transaction transaction = null;
        boolean isTransactionActive = session.getTransaction().isActive();
        if (!isTransactionActive) {
            transaction = session.beginTransaction();
        }

        try {


            String hqlQuery = "SELECT T1.BRANCHID AS branchId, T1.BONDLEDGERACCOUNTNUMBER AS bondLedgerAccountNumber,  \n" +
                    "                    CASE WHEN T2.MODEOFHOLDING IN ('MH001','MH004','MH005') THEN T2.BONDHOLDERNAME  \n" +
                    "                    WHEN T2.MODEOFHOLDING IN ('MH002','MH003') THEN T2.FIRSTJOINTHOLDERNAME END AS firstJointHolderName,  \n" +
                    "                    T1.BONDSUBSCRIPTIONID AS bondSubscriptionId, T1.SUBSCRIPTIONAMOUNT AS subscriptionAmount,  \n" +
                    "                    T1.ACCOUNTOPENDATE AS accountOpenDate, T1.INTERESTPERIODFROM AS interestPeriodFrom,  \n" +
                    "                    T1.INTERESTPERIODTO AS interestPeriodTo, T2.MODEOFINTERESTPAYMENT AS modeOfInterestPayment,  \n" +
                    "                    CASE WHEN T2.MODEOFINTERESTPAYMENT = 'MP001' THEN T2.OPERATIVEACCNUMBER  \n" +
                    "                    WHEN T2.MODEOFINTERESTPAYMENT = 'MP002' THEN T2.DDPOACCNUMBER  \n" +
                    "                    WHEN T2.MODEOFINTERESTPAYMENT = 'MP003' THEN T2.CREDITACCOUNTNUMBER END AS creditAccountNumber,  \n" +
                    "                    T1.INTERESTAMOUNT AS interestAmount, T1.CURRENTTDSAMOUNT AS currentTdsAmount,  \n" +
                    "                    T1.ARREARTDSAMOUNT AS arrearTdsAmount, T1.NETINTERESTAMOUNT AS netInterestAmount  \n" +
                    "                    FROM TrailRunDetails T1  \n" +
                    "                    INNER JOIN SubscriptionFlow T2 ON T1.BONDSUBSCRIPTIONID = T2.BONDSUBSCRIPTIONID  \n" +
                    "                    WHERE T1.BRANCHID = COALESCE(:pBranchId, T1.BRANCHID)  \n" +
                    "                    AND T1.BONDLEDGERACCOUNTNUMBER = COALESCE(:pBlaNo, T1.BONDLEDGERACCOUNTNUMBER)  \n" +
                    "                    AND T1.BONDSUBSCRIPTIONID = COALESCE(:pSubId, T1.BONDSUBSCRIPTIONID)  \n" +
                    "                    AND (TO_CHAR(T1.CREATEDDATE, 'MM') = CASE WHEN :pIntMonth = 'JANUARY' THEN '01' WHEN :pIntMonth = 'JULY' THEN '07' END)  \n" +
                    "                    AND TO_CHAR(T1.CREATEDDATE, 'YYYY') = :pIntYear  \n" +
                    "                    AND T1.BRANCHID LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T1.BONDLEDGERACCOUNTNUMBER LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T2.BONDHOLDERNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T2.FIRSTJOINTHOLDERNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T1.BONDSUBSCRIPTIONID LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T1.SUBSCRIPTIONAMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T1.ACCOUNTOPENDATE  LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T1.INTERESTPERIODFROM LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T1.INTERESTPERIODTO  LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T2.MODEOFINTERESTPAYMENT LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T2.OPERATIVEACCNUMBER LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T2.DDPOACCNUMBER  LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T2.CREDITACCOUNTNUMBER  LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T1.INTERESTAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T1.CURRENTTDSAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T1.ARREARTDSAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%' \n" +
                    "                            OR T1.NETINTERESTAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%' ";

            if (rbiBondCentralizedInterestTrailRunDto.getSortField() != null && !rbiBondCentralizedInterestTrailRunDto.getSortField().isEmpty() && rbiBondCentralizedInterestTrailRunDto.getSortOrder() != null && !rbiBondCentralizedInterestTrailRunDto.getSortOrder().isEmpty()) {
                hqlQuery += " ORDER BY " + rbiBondCentralizedInterestTrailRunDto.getSortField().toUpperCase() + " " + rbiBondCentralizedInterestTrailRunDto.getSortOrder().toUpperCase();
            }

            Query<Map<String, Object>> query = session.createQuery(hqlQuery);
            query.setParameter("pBranchId", rbiBondCentralizedInterestTrailRunDto.getBranchId());
            query.setParameter("pBlaNo", rbiBondCentralizedInterestTrailRunDto.getBlaNo());
            query.setParameter("pSubId", rbiBondCentralizedInterestTrailRunDto.getBondSubsId());
            query.setParameter("pIntMonth", rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
            query.setParameter("pIntYear", rbiBondCentralizedInterestTrailRunDto.getYear());
            query.setParameter("P_SEARCH_VALUE_IN", rbiBondCentralizedInterestTrailRunDto.getSearchValue());
            query.setFirstResult((rbiBondCentralizedInterestTrailRunDto.getPageNo() - 1) * rbiBondCentralizedInterestTrailRunDto.getPageSize());
            query.setMaxResults(rbiBondCentralizedInterestTrailRunDto.getPageSize());


            query.setResultTransformer(new TrailRunDetailsResultTransformer());
            List<Map<String, Object>> results = query.getResultList();



            for( Map<String, Object> result  : results ){
                RbiBondTrailRunReportDto rbiBondTrailRunReportDto = new RbiBondTrailRunReportDto();
                rbiBondTrailRunReportDto.setBranchId((String) result.get("branchId"));
                rbiBondTrailRunReportDto.setBondLedgerAccountNumber((String) result.get("bondLedgerAccountNumber"));
                rbiBondTrailRunReportDto.setFirstJointHolderName((String) result.get("firstJointHolderName"));
                rbiBondTrailRunReportDto.setBondSubscriptionId((String) result.get("bondSubscriptionId"));
                rbiBondTrailRunReportDto.setSubscriptionAmount((String) result.get("subscriptionAmount"));
                rbiBondTrailRunReportDto.setAccountOpenDate((String) result.get("accountOpenDate"));
                rbiBondTrailRunReportDto.setInterestPeriodFrom((String) result.get("interestPeriodFrom"));
                rbiBondTrailRunReportDto.setInterestPeriodTo((String) result.get("interestPeriodTo"));
                rbiBondTrailRunReportDto.setModeOfInterestPayment((String) result.get("modeOfInterestPayment"));
                rbiBondTrailRunReportDto.setCreditAccountNumber((String) result.get("creditAccountNumber"));
                rbiBondTrailRunReportDto.setInterestAmount((String) result.get("interestAmount"));
                rbiBondTrailRunReportDto.setCurrentTdsAmount((String) result.get("currentTdsAmount"));
                rbiBondTrailRunReportDto.setArrearTdsAmount((String) result.get("arrearTdsAmount"));
                rbiBondTrailRunReportDto.setNetInterestAmount((String) result.get("netInterestAmount"));

                rbiBondTrailRunReportDtos.add(rbiBondTrailRunReportDto);

            }


            Query<Long> countQuery = session.createQuery("SELECT count(1) \n" +
                    "                    FROM TrailRunDetails T1 \n" +
                    "                    INNER JOIN SubscriptionFlow T2 ON T1.BONDSUBSCRIPTIONID = T2.BONDSUBSCRIPTIONID \n" +
                    "                    WHERE T1.BRANCHID = COALESCE(:pBranchId, T1.BRANCHID) \n" +
                    "                    AND T1.BONDLEDGERACCOUNTNUMBER = COALESCE(:pBlaNo, T1.BONDLEDGERACCOUNTNUMBER) \n" +
                    "                    AND T1.BONDSUBSCRIPTIONID = COALESCE(:pSubId, T1.BONDSUBSCRIPTIONID) \n" +
                    "                    AND (TO_CHAR(T1.CREATEDDATE, 'MM') = CASE WHEN :pIntMonth = 'JANUARY' THEN '01' WHEN :pIntMonth = 'JULY' THEN '07' END) \n" +
                    "                    AND TO_CHAR(T1.CREATEDDATE, 'YYYY') = :pIntYear \n" +
                    "                    AND T1.BRANCHID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T1.BONDLEDGERACCOUNTNUMBER LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T2.BONDHOLDERNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T2.FIRSTJOINTHOLDERNAME LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T1.BONDSUBSCRIPTIONID LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T1.SUBSCRIPTIONAMOUNT  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T1.ACCOUNTOPENDATE  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T1.INTERESTPERIODFROM LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T1.INTERESTPERIODTO  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T2.MODEOFINTERESTPAYMENT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T2.OPERATIVEACCNUMBER LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T2.DDPOACCNUMBER  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T2.CREDITACCOUNTNUMBER  LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T1.INTERESTAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T1.CURRENTTDSAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T1.ARREARTDSAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            OR T1.NETINTERESTAMOUNT LIKE '%'||:P_SEARCH_VALUE_IN||'%'\n" +
                    "                            ");

            countQuery.setParameter("pBranchId", rbiBondCentralizedInterestTrailRunDto.getBranchId());
            countQuery.setParameter("pBlaNo", rbiBondCentralizedInterestTrailRunDto.getBlaNo());
            countQuery.setParameter("pSubId", rbiBondCentralizedInterestTrailRunDto.getBondSubsId());
            countQuery.setParameter("pIntMonth", rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
            countQuery.setParameter("pIntYear", rbiBondCentralizedInterestTrailRunDto.getYear());
            countQuery.setParameter("P_SEARCH_VALUE_IN", rbiBondCentralizedInterestTrailRunDto.getSearchValue());

            Long totalCount = countQuery.getSingleResult();


            int maxPages = (int) Math.ceil((double) totalCount / rbiBondCentralizedInterestTrailRunDto.getPageSize());
            rbiBondCentralizedInterestTrailRunDto.setMaxPages(maxPages);



            trialRunEligibleList.put("trialRunEligibleList", rbiBondTrailRunReportDtos);
            trialRunEligibleList.put("PageDetails", rbiBondCentralizedInterestTrailRunDto);

            if(rbiBondCentralizedInterestTrailRunDto.getIsDownload() != null && rbiBondCentralizedInterestTrailRunDto.getIsDownload().equalsIgnoreCase(CommonConstants.YES_STATUS) && rbiBondCentralizedInterestTrailRunDto.getDownloadType() !=null ){

                if(rbiBondCentralizedInterestTrailRunDto.getDownloadType().equalsIgnoreCase("excel")) {

                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondCentralizedInterestTrailRunBo.excelFileGeneratorTrailRunRep(rbiBondTrailRunReportDtos, rbiBondCentralizedInterestTrailRunDto)));
                    fileResponseDto.setFileName("BOND Interest Trial Run – Eligible Accounts Report.xlsx");
                    fileResponseDto.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                    return responseDataDTO;
                }

                if(rbiBondCentralizedInterestTrailRunDto.getDownloadType().equalsIgnoreCase("pdf")){
                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(rbiBondCentralizedInterestTrailRunBo.pdfFileGeneratorTrailRunRep(rbiBondTrailRunReportDtos, rbiBondCentralizedInterestTrailRunDto)));
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
    public ResponseDataDTO add(RbiBondCentralizedInterestTrailRunDto input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        Map<String, Object> rbiBondUnverifiedList = new HashMap<>();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRAIL_RUN_UNVER_REC.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getSchemeCode());
                stmt.setString(index++, input.getInterestForHalfYear());
                stmt.setString(index++, input.getYear());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                resultMsg = stmt.getString(index);


                ResultSet rs1 = null;
                ResultSet rs2 = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {

                    List<HashMap<String, String>> torchOut = null;
                    rs1 = (ResultSet) stmt.getObject(6);
                    rs2 = (ResultSet) stmt.getObject(7);
                    if (rs1 != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (rs1.next()) {

                            temp = new HashMap<>();
                            temp.put("blaNo", rs1.getString(1));
                            temp.put("holderName", rs1.getString(2));
                            temp.put("panNo", rs1.getString(3));

                            torchOut.add(temp);

                        }
                        rbiBondUnverifiedList.put("bondLedgerOut", torchOut);
                    }
                    if (rs2 != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (rs2.next()) {

                            temp = new HashMap<>();
                            temp.put("blaNo", rs2.getString(1));
                            temp.put("bondSubsId", rs2.getString(2));
                            temp.put("holderName", rs2.getString(3));
                            temp.put("panNo", rs2.getString(4));

                            torchOut.add(temp);

                        }
                        rbiBondUnverifiedList.put("bondSubsIdOut", torchOut);
                    }

                } else {
                    resultMsg = stmt.getString(index);

                }
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(rbiBondUnverifiedList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addTorch(RbiBondCentralizedInterestTrailRunDto input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondCentralizedInterestTrailRunDto output = new RbiBondCentralizedInterestTrailRunDto();
        String resultMsg = "";

            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;

                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRAIL_RUN_SP_ADD_VAL.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, input.getSchemeCode());
                    stmt.setString(index++, input.getInterestForHalfYear());
                    stmt.setString(index++, input.getYear());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;

                    if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {
                        if (input.getOperationType().equalsIgnoreCase("scheme")) {
                            List<HashMap<String, String>> torchOut = null;
                            resultSet = (ResultSet) stmt.getObject(6);
                            if (resultSet != null) {
                                HashMap<String, String> temp = null;
                                torchOut = new ArrayList<>();
                                while (resultSet.next()) {

                                    temp = new HashMap<>();
                                    temp.put("schemeCode", resultSet.getString(1));
                                    temp.put("schemeName", resultSet.getString(2));
                                    temp.put("schemeStatus", resultSet.getString(3));
                                    torchOut.add(temp);

                                }
                            }
                            output.setSchemeCodeOut(torchOut);
                            responseDataDTO.setResult(output);
                        } else if(input.getOperationType().equalsIgnoreCase("INTERESTHALFYEAR")){
                            List<HashMap<String, String>> torchOut = null;
                            resultSet = (ResultSet) stmt.getObject(6);
                            if (resultSet != null) {
                                HashMap<String, String> temp = null;
                                torchOut = new ArrayList<>();
                                while (resultSet.next()) {

                                    temp = new HashMap<>();
                                    temp.put("interestForHalfYear", resultSet.getString(1));
                                      torchOut.add(temp);

                                }
                            }
                            output.setInterestHalfYearTorchOut(torchOut);
                            responseDataDTO.setResult(output);

                        }

                    }

                }
                else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }
            }   catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }


        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private static final int SQL_QUERY_TIMEOUT_SECONDS = 1200; // 20 minutes

    @Override
    public ResponseDataDTO getTrialRunProcess(RbiBondCentralizedInterestTrailRunDto input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRAIL_RUN_SP_TRAIL_RUN.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getSchemeCode());
                stmt.setString(index++, input.getCentralizedFlag());
                stmt.setString(index++, input.getInterestForHalfYear());
                stmt.setString(index++, input.getYear());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.setQueryTimeout(SQL_QUERY_TIMEOUT_SECONDS);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {
                    responseDataDTO.setResult(stmt.getString(7));
                }
            }
            else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            databaseConnectionFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getReportTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondCentralizedInterestTrailRunDto output = new RbiBondCentralizedInterestTrailRunDto();
        String resultMsg = "";

        if(rbiBondCentralizedInterestTrailRunDto.getOperationType().equalsIgnoreCase("sol")){
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;

                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRAIL_RUN_REP_BRANCH_ID.getProcName());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getModuleId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getYear());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;
                    if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {
                        List<HashMap<String, String>> torchOut = null;
                        resultSet = (ResultSet) stmt.getObject(4);
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
                else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }
            }   catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }
        }
        else if(rbiBondCentralizedInterestTrailRunDto.getOperationType().equalsIgnoreCase("blaNo")){
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;

                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRAIL_RUN_REP_BLA_NUM.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getModuleId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getBranchId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getYear());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;

                    if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {

                        List<HashMap<String, String>> torchOut = null;
                        resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {

                                temp = new HashMap<>();
                                temp.put("blaNo", resultSet.getString("bla_no"));
                                temp.put("nameOfTheBondHolder", resultSet.getString("name_of_the_bond_holder"));
                                temp.put("jointHolderType", resultSet.getString("HOLDER_TYPE"));
                                temp.put("modeOfHolding", resultSet.getString("mode_of_holding"));
                                temp.put("verifiedStatus", resultSet.getString("verified_status"));
                                torchOut.add(temp);

                            }
                        }
                        output.setBlaNumberTorchOut(torchOut);

                        responseDataDTO.setResult(output);
                    }

                }
                else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }
            }   catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }
        }
        else if(rbiBondCentralizedInterestTrailRunDto.getOperationType().equalsIgnoreCase("bondSubsId") ){
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRAIL_RUN_REP_SUBS_ID.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getModuleId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getBlaNo());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getBranchId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getYear());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;


                    if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {

                        List<HashMap<String, String>> torchOut = null;
                        resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {

                                temp = new HashMap<>();
                                temp.put("bondSubsId", resultSet.getString("BOND_SUBSCRIPTION_ID"));
                                temp.put("accountOpenDate", resultSet.getString("ACCOUNT_OPEN_DATE"));
                                temp.put("subsAmount", resultSet.getString("SUBSCRIPTION_AMOUNT"));
                                torchOut.add(temp);

                            }
                        }
                        output.setBondSubsIdTorchOut(torchOut);
                        responseDataDTO.setResult(output);
                    }
                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }

        }
        else if(rbiBondCentralizedInterestTrailRunDto.getOperationType().equalsIgnoreCase("date") ) {
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRAIL_RUN_REP_DATE.getProcName());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getModuleId());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;


                    if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {

                        resultSet = (ResultSet) stmt.getObject(2);

                        if(resultSet != null) {

                            while (resultSet.next()) {

                                output.setInterestForHalfYear(resultSet.getString("INTEREST_DATE"));
                                output.setYear(resultSet.getString("INTEREST_YEAR"));

                            }
                        }
                        responseDataDTO.setResult(output);
                    }
                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                e.printStackTrace();
                log.info(CommonConstants.EXCEPTION_MESSAGE, e.getMessage());
                resultMsg=e.getMessage();
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }



    @Override
    public ResponseDataDTO interestPaymentAddTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondCentralizedInterestTrailRunDto output = new RbiBondCentralizedInterestTrailRunDto();
        String resultMsg = "";

        if(rbiBondCentralizedInterestTrailRunDto.getOperationType().equalsIgnoreCase("sol")){
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;

                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INT_PAY_BRANCH_ID.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getModuleId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getYear());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;

                    if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {
                        List<HashMap<String, String>> torchOut = null;
                        resultSet = (ResultSet) stmt.getObject(6);
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
                else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            }   catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }
        }
        else if(rbiBondCentralizedInterestTrailRunDto.getOperationType().equalsIgnoreCase("blaNo")){
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;

                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INT_PAY_BLA_NUM.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getModuleId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getBranchId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getYear());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;

                    if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {

                        List<HashMap<String, String>> torchOut = null;
                        resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {

                                temp = new HashMap<>();
                                temp.put("blaNo", resultSet.getString("bla_no"));
                                temp.put("nameOfTheBondHolder", resultSet.getString("name_of_the_bond_holder"));
                                temp.put("jointHolderType", resultSet.getString("HOLDER_TYPE"));
                                temp.put("modeOfHolding", resultSet.getString("mode_of_holding"));
                                temp.put("verifiedStatus", resultSet.getString("verified_status"));
                                torchOut.add(temp);

                            }
                        }
                        output.setBlaNumberTorchOut(torchOut);

                        responseDataDTO.setResult(output);
                    }

                }
                else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }
            }   catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }
        }
        else if(rbiBondCentralizedInterestTrailRunDto.getOperationType().equalsIgnoreCase("bondSubsId") ){
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INT_PAY_SUBS_ID.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getModuleId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getBlaNo());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getBranchId());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getYear());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;


                    if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {

                        List<HashMap<String, String>> torchOut = null;
                        resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {

                                temp = new HashMap<>();
                                temp.put("bondSubsId", resultSet.getString("BOND_SUBSCRIPTION_ID"));
                                temp.put("accountOpenDate", resultSet.getString("ACCOUNT_OPEN_DATE"));
                                temp.put("subsAmount", resultSet.getString("SUBSCRIPTION_AMOUNT"));
                                torchOut.add(temp);

                            }
                        }
                        output.setBondSubsIdTorchOut(torchOut);
                        responseDataDTO.setResult(output);
                    }
                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }

        }
        else if(rbiBondCentralizedInterestTrailRunDto.getOperationType().equalsIgnoreCase("date") ) {
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INT_PAY_DATE.getProcName());
                    stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getModuleId());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    ResultSet resultSet = null;


                    if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {

                        resultSet = (ResultSet) stmt.getObject(2);

                        if(resultSet != null) {

                            while (resultSet.next()) {

                                output.setInterestForHalfYear(resultSet.getString("INTEREST_DATE"));
                                output.setYear(resultSet.getString("INTEREST_YEAR"));

                            }
                        }
                        responseDataDTO.setResult(output);
                    }
                } else {
                    log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                    resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
                }

            } catch (Exception e) {
                log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            } finally {
                connectionPoolFactory.closeConnection(connection, stmt);
            }
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO intPayUnverifiedRecords(RbiBondCentralizedInterestTrailRunDto input) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        Map<String, Object> rbiBondUnverifiedList = new HashMap<>();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_INT_PAY_UNVER_REC.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, input.getModuleId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                resultMsg = stmt.getString(index);


                ResultSet rs1 = null;
                ResultSet rs2 = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {

                    List<HashMap<String, String>> torchOut = null;
                    rs1 = (ResultSet) stmt.getObject(4);
                    rs2 = (ResultSet) stmt.getObject(5);
                    if (rs1 != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (rs1.next()) {

                            temp = new HashMap<>();
                            temp.put("branchId", rs1.getString("BRANCH_ID"));
                            temp.put("blaNo", rs1.getString("BOND_LEDGER_ACCOUNT_NUMBER"));
                            temp.put("cusName", rs1.getString("CUSTOMER_NAME"));

                            torchOut.add(temp);

                        }
                        rbiBondUnverifiedList.put("blaNumberTorchOut", torchOut);
                    }
                    if (rs2 != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (rs2.next()) {

                            temp = new HashMap<>();
                            temp.put("branchId", rs2.getString("BRANCH_ID"));
                            temp.put("blaNo", rs2.getString("BOND_LEDGER_ACCOUNT_NUMBER"));
                            temp.put("bondSubsId", rs2.getString("BOND_SUBSCRIPTION_ID"));
                            temp.put("cusName", rs2.getString("hldr_name"));

                            torchOut.add(temp);

                        }
                        rbiBondUnverifiedList.put("bondSubsIdTorchOut", torchOut);
                    }

                }
            }
            else {
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
        responseDataDTO.setResult(rbiBondUnverifiedList);
        return responseDataDTO;
    }

//    @Override
//    public ResponseDataDTO validateOfficeAccountDetails(RbiBondCentralizedInterestTrailRunDto requestDto) throws Exception {
//        CallableStatement stmt = null;
//        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
//        String resultMsg = "";
//        Connection connection = null;
//        try {
//                connection = connectionFactory.getConnection();
//            if (connection != null) {
//                ARRAY accDetailsArray = null;
//                if (CommonConstants.PROC_TYPE_IN_RESPONSE.equals(requestDto.getTypeIn())) {
//                    StructDescriptor accDetStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_SCSS_CBS_ACCT_DETAILS, connection);
//                    ArrayDescriptor accDetDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_SCSS_CBS_ACCT_DETAILS, connection);
//
//                    STRUCT[] accDetStructs = new STRUCT[requestDto.getCbsAcctDet().size()];
//                    Map<String, Object> parameterMap = null;
//                    for (int index = 0; index < requestDto.getCbsAcctDet().size(); index++) {
//                        parameterMap = new HashMap<>();
//                        parameterMap.put("CR_DR_AC_TYPE", requestDto.getCbsAcctDet().get(index).getAccountName());
//                        parameterMap.put(CommonConstants.CURSOR_PARAM_TRAN_ACCT_NUM, requestDto.getCbsAcctDet().get(index).getAccNo());
//                        parameterMap.put("BRANCH_ID", requestDto.getCbsAcctDet().get(index).getBranchId());
//                        parameterMap.put("CBS_ACCT_NUM", requestDto.getCbsAcctDet().get(index).getCbsAccNo());
//                        parameterMap.put("CBS_ACCT_TYPE", requestDto.getCbsAcctDet().get(index).getCbsAccType());
//                        accDetStructs[index] = new STRUCT(accDetStruct, connection, parameterMap);
//                    }
//                    accDetailsArray = new ARRAY(accDetDescriptor, connection, accDetStructs);
//                }
//
//
//                stmt = connection.prepareCall(ScssProcNameEnum.PKG_GBM_SCSS_PAYMENT_PROCESS_SP_GBM_SCSS_CBS_ACCT_VAL.getProcName());
//                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
//                stmt.setString(CommonConstants.PROC_PARAM_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
//                stmt.setString("P_IS_CENT_FLG_IN", requestDto.getCentralizedFlag());
//                stmt.setString(CommonConstants.PROC_PARAM_BRANCH_ID_IN, requestDto.getBranchId());
//                stmt.setString("P_SCSS_CUST_ID_IN", requestDto.getScssCustomerId());
//                stmt.setString("P_SCSS_ACCT_NUM_IN", requestDto.getScssAccountNumber());
//                stmt.setString(CommonConstants.PROC_PARAM_PROCESS_ID_IN, requestDto.getProcessId());
//                stmt.setString(CommonConstants.PROC_PARAM_TYPE_IN, requestDto.getTypeIn());
//                if (CommonConstants.PROC_TYPE_IN_RESPONSE.equals(requestDto.getTypeIn())) {
//                    stmt.setObject("P_CBS_ACCT_DET_IN", accDetailsArray, Types.ARRAY);
//                } else {
//                    stmt.setNull("P_CBS_ACCT_DET_IN", Types.ARRAY, CommonConstants.DATABASE_COLLECTION_SCSS_CBS_ACCT_DETAILS);
//                }
//                stmt.registerOutParameter("P_ACCT_DET_OUT", OracleTypes.CURSOR);
//                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
//                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
//                stmt.execute();
//                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
//                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
//                resultMsg = stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT);
//
//                if (CommonConstants.SUCCESS.equalsIgnoreCase(stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT))) {
//                    if (requestDto.getTypeIn().equals("0")) {
//
//                        ResultSet resultSet = (ResultSet) stmt.getObject("P_ACCT_DET_OUT");
//                        if (resultSet != null) {
//                            List<AccountValidationDto> accountValidationDtoList = new ArrayList<>();
//                            AccountValidationDto accountValidationDto = null;
//                            while (resultSet.next()) {
//                                accountValidationDto = new AccountValidationDto();
//                                accountValidationDto.setAccountName(resultSet.getString("CR_DR_AC_TYPE"));
//                                accountValidationDto.setAccNo(resultSet.getString(CommonConstants.CURSOR_PARAM_TRAN_ACCT_NUM));
//                                accountValidationDto.setBranchId(resultSet.getString("BRANCH_ID"));
//                                accountValidationDto.setIsCentralized(resultSet.getString("ACCT_CENT_FLG"));
//                                accountValidationDtoList.add(accountValidationDto);
//                            }
//
//                            responseDataDTO.setResult(accountValidationDtoList);
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
//            throw e;
//        }
//        finally{
//
//            connectionFactory.closeConnection(connection, stmt);
//        }
//        responseDataDTO.setMessage(resultMsg);
//        return responseDataDTO;
//    }

    @Override
    public List<String> getValidationBatchList(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws Exception {
        List<String> validationBatchList = null;
        CallableStatement stmt = null;
        Connection connection = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_BULK_PAY_BATCH_VAL.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString(CommonConstants.PROC_PARAM_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString("P_MODULE_ID_IN", rbiBondCentralizedInterestTrailRunDtoRequest.getModuleId());
                stmt.setString("P_BLA_NO_IN", rbiBondCentralizedInterestTrailRunDtoRequest.getBlaNo());
                stmt.setString("P_BRANCH_ID_IN", rbiBondCentralizedInterestTrailRunDtoRequest.getBranchId());
                stmt.setString("P_SUB_ID_IN", rbiBondCentralizedInterestTrailRunDtoRequest.getBondSubsId());
                stmt.setString("P_IS_CENT_FLG_IN", rbiBondCentralizedInterestTrailRunDtoRequest.getCentralizedFlag());
                stmt.setString("P_PROCESS_ID_IN", rbiBondCentralizedInterestTrailRunDtoRequest.getProcessId());
                stmt.setString("P_INT_MONTH_IN", rbiBondCentralizedInterestTrailRunDtoRequest.getInterestForHalfYear());
                stmt.setString("P_INT_YEAR_IN", rbiBondCentralizedInterestTrailRunDtoRequest.getYear());
                stmt.registerOutParameter("P_VAL_BATCH_DET_OUT", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));

                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject("P_VAL_BATCH_DET_OUT");
                    if (resultSet != null) {
                        validationBatchList = new ArrayList<>();
                        while (resultSet.next())
                            validationBatchList.add(resultSet.getString(1));
                    }
                } /*else {
                    throw new BusinessException(stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                }*/
            } else {
                throw new BusinessException(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            throw e;
        }
        finally{

            connectionPoolFactory.closeConnection(connection, stmt);
        }

        return validationBatchList;

    }

    @Override
    public  ResponseDataDTO getInterestPayBatchTranDetails(RbiBondCentralizedInterestTrailRunDto  rbiBondCentralizedInterestTrailRunDto){

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

//try{
////creating process id for batch process
//    String processId = "rbi_bond" + UserAccountUtils.getCurrentUsername() + "_" + UserAccountUtils.getCurrentSolId() + (StringUtils.isEmpty(rbiBondCentralizedInterestTrailRunDto.getBranchId()) ? ("") : ("_" + rbiBondCentralizedInterestTrailRunDto.getBranchId())) + (StringUtils.isEmpty(rbiBondCentralizedInterestTrailRunDto.getBlaNo()) ? ("") : ("_" + rbiBondCentralizedInterestTrailRunDto.getBlaNo())) + (StringUtils.isEmpty(rbiBondCentralizedInterestTrailRunDto.getBondSubsId()) ? ("") : ("_" + rbiBondCentralizedInterestTrailRunDto.getBondSubsId())) + ("_" + System.currentTimeMillis());
//
////setting the process id to  paymentprogress dto
//    PaymentProgressDto.









//} catch (Exception e) {
//    e.printStackTrace();
//}

        return null;
    }


    //--**   bulk payment trail run **--
    @Override
    public  ResponseDataDTO trailrun(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest){

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        CallableStatement stmt = null;
        Connection connection = null;

        try{
            connection = databaseConnectionFactory.getConnection();
            if(connection != null){
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRAIL_RUN.getProcName());
                stmt.setString("P_USER_ID_IN", UserAccountUtils.getCurrentUsername());
                stmt.setString("P_USER_SOL_ID_IN",UserAccountUtils.getCurrentSolId());
                stmt.setString("P_MODULE_ID_IN",rbiBondCentralizedInterestTrailRunDtoRequest.getModuleId());
                stmt.setString("P_INT_MONTH_IN", rbiBondCentralizedInterestTrailRunDtoRequest.getInterestForHalfYear());
                stmt.setString("P_INT_YEAR_IN",rbiBondCentralizedInterestTrailRunDtoRequest.getYear());
                stmt.registerOutParameter("P_MSG_OUT",Types.VARCHAR);
                stmt.registerOutParameter("P_MSG_DESC_OUT",Types.VARCHAR);
//                log.info(stmt.toString());
                stmt.execute();

                if(stmt.getString("P_MSG_OUT").equalsIgnoreCase(CommonConstants.SUCCESS)){
                    log.info("TRIAL RUN COMPLETED SUCCESSFULLY");
                    responseDataDTO.setMessage(stmt.getString("P_MSG_DESC_OUT"));
                }
                else{
                    log.info("TRIAL RUN FAILED FROM DB"+ "msg : "+stmt.getString("p_msg_desc_out"));
                    responseDataDTO.setMessage(stmt.getString("P_MSG_DESC_OUT"));
                }


            }else{
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                responseDataDTO.setMessage( ErrorMessages.INTERNAL_SERVER_ERROR);
            }


        }catch (Exception e){
            log.info(e.getMessage());
            responseDataDTO.setMessage(e.getMessage());
        }
        finally {
            databaseConnectionFactory.closeResources(connection,stmt);
        }

        return responseDataDTO;
    }




    //--**   bulk payment get det **--
    @Override
    public ResponseDataDTO bulkPaymentGetDet(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest){

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        CallableStatement stmt = null;
        Connection connection = null;

        try{
            connection = connectionPoolFactory.getConnection();
            if(connection != null){
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_GET_DET.getProcName());
                stmt.setString("P_USER_ID_IN", UserAccountUtils.getCurrentUsername());
                stmt.setString("P_USER_SOL_ID_IN",UserAccountUtils.getCurrentSolId());
                stmt.setString("P_MODULE_ID_IN",rbiBondCentralizedInterestTrailRunDtoRequest.getModuleId());
                stmt.setString("P_BLA_NO_IN",rbiBondCentralizedInterestTrailRunDtoRequest.getBlaNo());
                stmt.setString("P_BRANCH_ID_IN",rbiBondCentralizedInterestTrailRunDtoRequest.getBranchId());
                stmt.setString("P_SUB_ID_IN",rbiBondCentralizedInterestTrailRunDtoRequest.getBondSubsId());
                stmt.setString("P_INT_MONTH_IN", rbiBondCentralizedInterestTrailRunDtoRequest.getInterestForHalfYear());
                stmt.setString("P_INT_YEAR_IN",rbiBondCentralizedInterestTrailRunDtoRequest.getYear());
                stmt.registerOutParameter("P_MSG_OUT",Types.VARCHAR);
                stmt.registerOutParameter("P_MSG_DESC_OUT",Types.VARCHAR);
//                log.info(stmt.toString());
                stmt.execute();

                if(stmt.getString("P_MSG_OUT").equalsIgnoreCase(CommonConstants.SUCCESS)){
                    log.info("BULKPAYMENT GET-DET COMPLETED SUCCESSFULLY");
                    responseDataDTO.setMessage(stmt.getString("P_MSG_DESC_OUT"));
                }
                else{
                    log.info("BULKPAYMENT GET-DET FROM DB"+ "msg : "+stmt.getString("p_msg_desc_out"));
                    responseDataDTO.setMessage(stmt.getString("P_MSG_DESC_OUT"));
                }


            }else{
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                responseDataDTO.setMessage( ErrorMessages.INTERNAL_SERVER_ERROR);

            }


        }catch (Exception e){
            log.info(e.getMessage());
            responseDataDTO.setMessage(e.getMessage());
        }
        finally {
            connectionPoolFactory.closeConnection(connection,stmt);
        }

        return responseDataDTO;
    }







    @Override
    public ResponseDataDTO cbsAccValidation(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        CallableStatement stmt = null;
        Connection connection = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_BULK_PAY_CBS_ACC_VAL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDtoRequest.getModuleId());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDtoRequest.getBlaNo());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDtoRequest.getBranchId());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDtoRequest.getBondSubsId());
                stmt.setString(index++, "Y"); //centralized flag
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDtoRequest.getProcessId());
                if(CollectionUtils.isEmpty(rbiBondCentralizedInterestTrailRunDtoRequest.getCbsAcctDet())) {
                    stmt.setString(index++, "0"); //0 -request ; 1 - request
                }else{
                    stmt.setString(index++, "1"); //0 -request ; 1 - request
                }
                if(CollectionUtils.isEmpty(rbiBondCentralizedInterestTrailRunDtoRequest.getCbsAcctDet())){
                    stmt.setNull(index++, Types.ARRAY, "TYP_GBM_RBI_BOND_CBS_AC_DET");

                }else{
                    stmt.setObject(index++, cbsAccValidationList(connection, rbiBondCentralizedInterestTrailRunDtoRequest.getCbsAcctDet()));
                }
                stmt.registerOutParameter(index++,OracleTypes.CURSOR );
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                if(stmt.getString(index-1).equalsIgnoreCase(CommonConstants.SUCCESS)){
//
                    List<AccountValidationDto> cbsAccValidationList =  new ArrayList<>();
                    ResultSet rs = (ResultSet) stmt.getObject(index-2);

                    while(rs.next()){
                        AccountValidationDto cbsAccValidationValues = new AccountValidationDto();

                        cbsAccValidationValues.setAccountName(rs.getString("CR_DR_AC_TYPE"));
                        cbsAccValidationValues.setAccNo(rs.getString("ACCT_NUM"));
                        cbsAccValidationValues.setBranchId(rs.getString("BRANCH_ID"));
                        cbsAccValidationValues.setIsCentralized(rs.getString("ACCT_CENT_FLG"));
                        cbsAccValidationList.add(cbsAccValidationValues);

                    }
                    responseDataDTO.setMessage(stmt.getString(index));
                    responseDataDTO.setResult(cbsAccValidationList);

                }
                else{
                    log.info("cbs acc validations database exception {}",stmt.getString(index));
                    responseDataDTO.setMessage(stmt.getString(index));
                }


            }
            else{
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                responseDataDTO.setMessage( ErrorMessages.INTERNAL_SERVER_ERROR);

            }
        }
        catch (Exception e){
            log.info(e.getMessage());
            responseDataDTO.setMessage(e.getMessage());
        }
        finally {
            connectionPoolFactory.closeConnection(connection,stmt);
        }
        return responseDataDTO;
    }


    private static ARRAY cbsAccValidationList(Connection connection, List<AccountValidationDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_CBS_AC_DET_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_CBS_AC_DET", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        AccountValidationDto accountValidationDto = new AccountValidationDto();

        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[5];
            accountValidationDto = list.get(index);

            paramArray[0] = accountValidationDto.getCbsAccType();
            paramArray[1] = accountValidationDto.getAccNo();
            paramArray[2] = accountValidationDto.getBranchId();
            paramArray[3] = accountValidationDto.getCbsAccNo();
            paramArray[4] = accountValidationDto.getCbsAccType();
//for testing pupose
//            paramArray[4] = accountValidationDto.getCbsAccType().substring(2,4);

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }



    @Override
    public RbiBondCentralizedInterestTrailRunDtoRequest requestBatchForRbiBondIntValidation(String processId, String centralizedFlag, String branchId, String bondSubsId, String blaNo, String validationBatchId) throws Exception {
        log.info("Validation Batch Id : {}" , validationBatchId);
        CallableStatement stmt = null;
        RbiBondCentralizedInterestTrailRunDtoRequest output = null;
        Connection connection = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_BULK_PAY_PAYMET_VAL.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString(CommonConstants.PROC_PARAM_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString("P_IS_CENT_FLG_IN", centralizedFlag);
                stmt.setString(CommonConstants.PROC_PARAM_BRANCH_ID_IN, branchId);
                stmt.setString("P_SUB_ID_IN", bondSubsId);
                stmt.setString("P_BLA_NO_IN", blaNo);
                stmt.setString(CommonConstants.PROC_PARAM_TYPE_IN, CommonConstants.PROC_TYPE_IN_REQUEST);
                stmt.setNull(CommonConstants.PROC_PARAM_VALIDATED_ACCT_DET_IN, Types.ARRAY, "TYP_GBM_RBI_BOND_TRAN_AC_VAL");
                stmt.setString(CommonConstants.PROC_PARAM_PROCESS_ID_IN, processId);
                stmt.setString("P_VAL_BATCH_ID_IN", validationBatchId);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_TRAN_ACCT_DET_OUT, OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {
                    GbmTransactionMasterDto acctDetailDto = null;
                    List<GbmTransactionMasterDto> acctDetailDtoList = new ArrayList<>();
                    ResultSet resultSet = (ResultSet) stmt.getObject(CommonConstants.PROC_PARAM_TRAN_ACCT_DET_OUT);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            acctDetailDto = new GbmTransactionMasterDto();
                            acctDetailDto.setAcctNum(resultSet.getString(2));
                            acctDetailDto.setTranLeg(resultSet.getString(3).charAt(0));
                            acctDetailDtoList.add(acctDetailDto);
                        }
                    }
                    if (CollectionUtils.isEmpty(acctDetailDtoList))
                        return null;
                    output = new RbiBondCentralizedInterestTrailRunDtoRequest();
                    output.setProcessId(processId);
                    output.setCentralizedFlag(centralizedFlag);
                    output.setBranchId(branchId);
                    output.setBondSubsId(bondSubsId);
                    output.setBlaNo(blaNo);
                    output.setValidationBatchId(validationBatchId);
                    output.setAcctDetailsList(acctDetailDtoList);
                } else {
                    throw new BusinessException(stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                }
            } else {
                throw new BusinessException(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            throw e;
        }
        finally{

            connectionPoolFactory.closeConnection(connection, stmt);
        }

        return output;
    }

    @Override
    public void responseBatchForRbiBondIntValidation(RbiBondCentralizedInterestTrailRunDtoRequest input) throws Exception {
        CallableStatement stmt = null;
        RbiBondCentralizedInterestTrailRunDtoRequest output = new RbiBondCentralizedInterestTrailRunDtoRequest();
        Connection connection = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                //Construct SQL Array
                StructDescriptor accDetStruct = StructDescriptor.createDescriptor("TYP_GBM_RBI_BOND_TRAN_AC_VAL_OBJ", connection);
                ArrayDescriptor accDetDescriptor = ArrayDescriptor.createDescriptor("TYP_GBM_RBI_BOND_TRAN_AC_VAL", connection);

                STRUCT[] accDetStructs = new STRUCT[input.getAccDetailsIn().size()];
                Map<String, Object> parameterMap = null;
                for (int index = 0; index < input.getAccDetailsIn().size(); index++) {
                    parameterMap = new HashMap<>();
                    parameterMap.put(CommonConstants.CURSOR_PARAM_OPR_ACCT_NUM, input.getAccDetailsIn().get(index).getAccountNumber());
                    parameterMap.put(CommonConstants.STATUS_STRING, input.getAccDetailsIn().get(index).getIsSysAcct());
                    parameterMap.put(CommonConstants.REMARKS_CONST, input.getAccDetailsIn().get(index).getReason());
                    accDetStructs[index] = new STRUCT(accDetStruct, connection, parameterMap);
                }
                ARRAY accDetailsArray = new ARRAY(accDetDescriptor, connection, accDetStructs);


                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_BULK_PAY_PAYMET_VAL.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString(CommonConstants.PROC_PARAM_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString("P_IS_CENT_FLG_IN", input.getCentralizedFlag());
                stmt.setString(CommonConstants.PROC_PARAM_BRANCH_ID_IN, input.getBranchId());
                stmt.setString("P_SUB_ID_IN", input.getBondSubsId());
                stmt.setString("P_BLA_NO_IN", input.getBlaNo());
                stmt.setString(CommonConstants.PROC_PARAM_TYPE_IN, CommonConstants.PROC_TYPE_IN_RESPONSE);
                stmt.setObject(CommonConstants.PROC_PARAM_VALIDATED_ACCT_DET_IN, accDetailsArray);
                stmt.setString(CommonConstants.PROC_PARAM_PROCESS_ID_IN, input.getProcessId());
                stmt.setString("P_VAL_BATCH_ID_IN", input.getValidationBatchId());
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_TRAN_ACCT_DET_OUT, OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {
                    if (CommonConstants.PROC_TYPE_IN_REQUEST.equals(input.getTypeIn())) {
                        GbmTransactionMasterDto acctDetailDto = null;
                        List<GbmTransactionMasterDto> acctDetailDtoList = new ArrayList<>();
                        ResultSet resultSet = (ResultSet) stmt.getObject(CommonConstants.PROC_PARAM_TRAN_ACCT_DET_OUT);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                acctDetailDto = new GbmTransactionMasterDto();
                                acctDetailDto.setAcctNum(resultSet.getString(2));
                                acctDetailDto.setTranLeg(resultSet.getString(3).charAt(0));
                                acctDetailDtoList.add(acctDetailDto);
                            }
                        }
                        output.setAcctDetailsList(acctDetailDtoList);
                    }
                }
            } else {
                throw new BusinessException(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            throw e;
        }
        finally{

            connectionPoolFactory.closeConnection(connection, stmt);
        }
    }

    @Override
    public List<String> getTransactionBatchList(String processId, String centralizedFlag, String branchId, String bondSubsId, String blaNo, String interestForHalfYear, String year, String moduleId) throws Exception {
        List<String> transactionBatchList = null;
        CallableStatement stmt = null;
        Connection connection = null;
        try {

            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_BULK_PAY_PAYMET_TRAN_BATCH.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString(CommonConstants.PROC_PARAM_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString("P_BLA_NO_IN", blaNo);
                stmt.setString(CommonConstants.PROC_PARAM_BRANCH_ID_IN, branchId);
                stmt.setString("P_SUB_ID_IN", bondSubsId);
                stmt.setString("P_INT_MONTH_IN", interestForHalfYear);
                stmt.setString("P_INT_YEAR_IN", year);
                stmt.setString(CommonConstants.PROC_PARAM_PROCESS_ID_IN, processId);
                stmt.setString("P_IS_CENT_FLG_IN", centralizedFlag);
                stmt.setString("p_module_id_in", moduleId);
                stmt.registerOutParameter("P_TRAN_BATCH_DET_OUT", OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));

                if (stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT) != null && stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject("P_TRAN_BATCH_DET_OUT");

                    if (resultSet != null) {
                        transactionBatchList = new ArrayList<>();
                        while (resultSet.next())
                            transactionBatchList.add(resultSet.getString(1));
                    }
                    System.out.println("****batch id list*** : = > "+ transactionBatchList);
                    if (CollectionUtils.isEmpty(transactionBatchList))
                        throw new BusinessException("Transaction Batch List Could Not Be Empty");
                } else {
                    throw new BusinessException(stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                }
            } else {
                throw new BusinessException(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            throw e;
        }
        finally{

            connectionPoolFactory.closeConnection(connection, stmt);
        }

        return transactionBatchList;
    }

    @Override
    public RbiBondCentralizedInterestTrailRunDtoRequest requestBatchForRbiBondIntRun(String processId, String centralizedFlag, String branchId, String bondSubsId, String blaNo, String tranBatchId, String moduleId)
    {
        CallableStatement stmt = null;
        RbiBondCentralizedInterestTrailRunDtoRequest output = null;
        Connection connection = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_BULK_PAY_PAYMET_TRAN_REQ.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString(CommonConstants.PROC_PARAM_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString("P_MODULE_ID_IN", moduleId);
                stmt.setString("P_IS_CENT_FLG_IN", centralizedFlag);

//                stmt.setString("P_SCSS_CUST_ID_IN", scssCustomerId);
//                stmt.setString("P_SCSS_ACCT_NUM_IN", scssAccountNumber);
                stmt.setString("P_TRAN_BATCH_ID_IN", tranBatchId);
                System.out.println("=====tran batch id ===="+tranBatchId);
                stmt.setString(CommonConstants.PROC_PARAM_PROCESS_ID_IN, processId);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_TRAN_CHANNEL_TYPE_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_GBM_TRAN_ID_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_TRAN_SOL_ID_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_TRAN_DATE_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_TRAN_SCHEME_CODE_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_TRAN_SCREEN_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_TRAN_DET_OUT, OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));

                if (CommonConstants.SUCCESS.equals(stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT))) {
                    GbmTransactionMasterDto tempDto = null;
                    List<GbmTransactionMasterDto> acctDetailDtoList = new ArrayList<>();

                    Date tranDate = null;
                    try {
                        tranDate = new SimpleDateFormat("dd/MM/yyyy").parse(stmt.getString(CommonConstants.PROC_PARAM_TRAN_DATE_OUT));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    ResultSet resultSet = (ResultSet) stmt.getObject(CommonConstants.PROC_PARAM_TRAN_DET_OUT);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            tempDto = new GbmTransactionMasterDto();
                            tempDto.setTransactionChannel(stmt.getString(CommonConstants.PROC_PARAM_TRAN_CHANNEL_TYPE_OUT));
                            tempDto.setTranId(stmt.getString(CommonConstants.PROC_PARAM_GBM_TRAN_ID_OUT));
                            System.out.println("$$$$ GBM TRAN ID $$$$ = > "+stmt.getString(CommonConstants.PROC_PARAM_GBM_TRAN_ID_OUT));
                            tempDto.setSolId(stmt.getString(CommonConstants.PROC_PARAM_TRAN_SOL_ID_OUT));
                            tempDto.setTranDate(tranDate);
                            tempDto.setSchemeCode(stmt.getString(CommonConstants.PROC_PARAM_TRAN_SCHEME_CODE_OUT));
                            tempDto.setAcctNum(resultSet.getString(CommonConstants.CURSOR_PARAM_TRAN_ACCT_NUM));
                            tempDto.setTranLeg(resultSet.getString(CommonConstants.CURSOR_PARAM_TRAN_TYPE).charAt(0));
                            tempDto.setAmount(resultSet.getDouble(CommonConstants.CURSOR_PARAM_AMT));
                            acctDetailDtoList.add(tempDto);
                        }
                    }

                    if (CollectionUtils.isEmpty(acctDetailDtoList))
                        return null;                    //stop batch process

                    output = new RbiBondCentralizedInterestTrailRunDtoRequest();
                    output.setProcessId(processId);
                    output.setCentralizedFlag(centralizedFlag);
                    output.setBranchId(branchId);
                    output.setBondSubsId(bondSubsId);
                    output.setBlaNo(blaNo);
                    output.setAcctDetailsList(acctDetailDtoList);

                } else if (CommonConstants.NO_STATUS.equals(stmt.getString(CommonConstants.PROC_PARAM_BATCH_REMAIN_FLAG_OUT))) {
                    return null;                        //stop batch process
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                output = null;
            }

        } catch (Exception e) {
            output = null;
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        }
        finally{

            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return output;
    }

    @Override
    public void responseBatchForRbiBondIntRun(RbiBondCentralizedInterestTrailRunDtoRequest input) {
        CallableStatement stmt = null;
        Connection connection = null;
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_BULK_PAY_PAYMET_TRAN_RES.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString(CommonConstants.PROC_PARAM_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString("P_IS_CENT_FLG_IN", input.getCentralizedFlag());
                stmt.setString(CommonConstants.PROC_PARAM_GBM_TRAN_ID_IN, input.getGbmTranIdOut());
                stmt.setString(CommonConstants.PROC_PARAM_CBS_TRAN_ID_IN, input.getCbsTranIdOut());
                stmt.setString(CommonConstants.PROC_PARAM_CBS_TRAN_STAT_IN, input.getCbsTranStatusOut());
                stmt.setString(CommonConstants.PROC_PARAM_CBS_TRAN_STAT_DESC_IN, input.getCbsTranStatusDesc());
                stmt.setString(CommonConstants.PROC_PARAM_PROCESS_ID_IN, input.getProcessId());
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_CBS_TRAN_STAT_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));

                if (!CommonConstants.SUCCESS.equalsIgnoreCase(stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT))) {
                    throw new GenericBusinessException(stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        }
        finally{

            databaseConnectionFactory.closeResources(connection, stmt);
        }
    }

    @Override
    public RbiBondCentralizedInterestTrailRunDtoRequest interestPaymentStatus(RbiBondCentralizedInterestTrailRunDtoRequest input) throws Exception {
        Connection connection = null;
        CallableStatement stmt = null;
        RbiBondCentralizedInterestTrailRunDtoRequest output = new RbiBondCentralizedInterestTrailRunDtoRequest();
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_BULK_PAY_PAYMET_STATUS.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString(CommonConstants.PROC_PARAM_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString("P_IS_CENT_FLG_IN", input.getCentralizedFlag());
                stmt.setString(CommonConstants.PROC_PARAM_PROCESS_ID_IN, input.getProcessId());
                stmt.registerOutParameter("P_COMPLETED_FLG_OUT", Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_TRAN_DET_OUT, OracleTypes.CURSOR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                if (CommonConstants.SUCCESS.equalsIgnoreCase(stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT))) {
                    output.setCompletedFlag(stmt.getString("P_COMPLETED_FLG_OUT"));
                    ResultSet resultSet = (ResultSet) stmt.getObject(CommonConstants.PROC_PARAM_TRAN_DET_OUT);
                    if (resultSet != null) {
                        List<TranStatusDto> torchOut = new ArrayList<>();
                        TranStatusDto temp = null;
                        while (resultSet.next()) {
                            log.info("pay status values found");
                            temp = new TranStatusDto();
                            temp.setGbmTranId(resultSet.getString(1));
                            temp.setCbsTranId(resultSet.getString(2));
                            temp.setTranStatus(resultSet.getString(3));
                            temp.setTranRemarks(resultSet.getString(4));
                            torchOut.add(temp);
                        }
                        output.setPaymentStatusOut(torchOut);
                    }
                } else {
                    throw new GenericBusinessException(stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            throw e;
        } finally {
            databaseConnectionFactory.closeResources(connection, stmt);
        }
        return output;
    }

    @Override
    public void updateCompletionOfBatchExecutionRbiBulkInterestPayment(String processId, String status, String statusRemarks) {
        CallableStatement stmt = null;
        Connection connection = null;
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_BULK_PROCESS_MIDDLEWARE_STAT.getProcName());
                stmt.setString(CommonConstants.PROC_PARAM_USER_ID_IN, UserAccountUtils.getCurrentUsername());
                stmt.setString(CommonConstants.PROC_PARAM_USER_SOL_ID_IN, UserAccountUtils.getCurrentSolId());
                stmt.setString(CommonConstants.PROC_PARAM_PROCESS_ID_IN, processId);
                stmt.setString("P_STATUS_IN", status);
                stmt.setString("P_STATUS_REMARKS_IN", statusRemarks);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_OUT, Types.VARCHAR);
                stmt.registerOutParameter(CommonConstants.PROC_PARAM_MSG_DESC_OUT, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(CommonConstants.PROC_PARAM_MSG_DESC_OUT));

                if (!CommonConstants.SUCCESS.equalsIgnoreCase(stmt.getString(CommonConstants.PROC_PARAM_MSG_OUT))) {
                    throw new GenericBusinessException(CommonConstants.PROC_PARAM_MSG_DESC_OUT);
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                throw new GenericBusinessException(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        }
        finally {
            databaseConnectionFactory.closeResources(connection, stmt);
        }

    }
    @Override
    public ResponseDataDTO download(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto) {
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {

          /*  PROCEDURE sp_download
            (
                    p_user_id_in                IN VARCHAR2,
            p_user_sol_id_in            IN VARCHAR2,
                    p_sch_code_in               IN VARCHAR2,
            p_int_month_in              IN VARCHAR2,
                    p_int_year_in               IN VARCHAR2,
            p_cursor_val_out            OUT SYS_REFCURSOR,
                    p_msg_out                   OUT VARCHAR2,
            p_msg_desc_out              OUT VARCHAR2
    );*/
            HashMap<String, Object> finalData = new HashMap<>();
            List<RbiBondCentralizedInterestTrailRunDownloadDto> downloadResponseDTOList = Collections.emptyList();
             resultMsg = "SUCCESS";

            // IN PARAMS
            String p_user_id_in = "p_user_id_in";
            String p_user_sol_id_in = "p_user_sol_id_in";
            String p_sch_code_in = "p_sch_code_in";
            String P_int_half_month_in = "p_int_month_in";
            String p_int_year_in = "p_int_year_in";

            // OUT PARAMS
            String p_cursor_val_out = "p_cursor_val_out";
            String p_msg_out = "p_msg_out";
            String p_msg_desc_out = "p_msg_desc_out";
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername());
            inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId());
            inParams.put(p_sch_code_in,  rbiBondCentralizedInterestTrailRunDto.getSchemeCode());
            inParams.put(P_int_half_month_in, rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
            inParams.put(p_int_year_in, rbiBondCentralizedInterestTrailRunDto.getYear());


            SqlParameter[] sqlParameters = {
                    new SqlParameter(p_user_id_in, Types.VARCHAR),
                    new SqlParameter(p_user_sol_id_in, Types.VARCHAR),
                    new SqlParameter(p_sch_code_in, Types.VARCHAR),
                    new SqlParameter(P_int_half_month_in, Types.VARCHAR),
                    new SqlParameter(p_int_year_in, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_cursor_val_out, OracleTypes.CURSOR),
                    new SqlOutParameter(p_msg_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_desc_out, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(p_cursor_val_out, (rs, rowNum) -> mapDownloadRowMapper(rs, rowNum));


            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(

                    "PKG_GBM_RBI_BOND_TRAIL_RUN",
                    "sp_download",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );
            downloadResponseDTOList = (List<RbiBondCentralizedInterestTrailRunDownloadDto>) result.get(p_cursor_val_out);


            if (!downloadResponseDTOList.isEmpty()) {
                finalData.put("gstAckFileDownloadDTOList", downloadResponseDTOList);
                responseDataDTO.setResult(finalData);
               responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
            } else {
                log.error(CommonConstants.NDF);
                responseDataDTO.setMessage("No data to download");
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        }
        return responseDataDTO;
    }

    private RbiBondCentralizedInterestTrailRunDownloadDto mapDownloadRowMapper(ResultSet resultSet, int rowNum) throws SQLException {

            RbiBondCentralizedInterestTrailRunDownloadDto downloadDto = new RbiBondCentralizedInterestTrailRunDownloadDto();
            downloadDto.setModeOfPayment(resultSet.getString(1));
            downloadDto.setBranchRefNo(resultSet.getString(2));
            downloadDto.setBlaNo(resultSet.getString(3));
            downloadDto.setRefNo(resultSet.getString(4));
            downloadDto.setApplName1(resultSet.getString(5));
            downloadDto.setApplName2(resultSet.getString(6));
            downloadDto.setApplName3(resultSet.getString(7));
            downloadDto.setAddr1(resultSet.getString(8));
            downloadDto.setAddr2(resultSet.getString(9));
            downloadDto.setRecName(resultSet.getString(10));
            downloadDto.setRecParentName(resultSet.getString(11));
            downloadDto.setPinCode(resultSet.getString(12));
            downloadDto.setPhoneNo(resultSet.getString(13));
            downloadDto.setApplStatus(resultSet.getString(14));
            downloadDto.setInvestmentAmt(resultSet.getString(15));
            downloadDto.setCfd(resultSet.getString(16));
            downloadDto.setMatDate(resultSet.getString(17));
            downloadDto.setAccountNo(resultSet.getString(18));
            downloadDto.setSubscriptionCount(resultSet.getString(19));
            downloadDto.setBankSortCode(resultSet.getString(20));
            downloadDto.setBankName(resultSet.getString(21));
            downloadDto.setPanNo(resultSet.getString(22));
            downloadDto.setInterestPayout8Percent(resultSet.getString(23));
            downloadDto.setNonCumulativeInt8(resultSet.getString(24));
            downloadDto.setCumulativeInt8(resultSet.getString(25));
            downloadDto.setInterestPayout775Percent(resultSet.getString(26));
            downloadDto.setNonCumulativeInt775(resultSet.getString(27));
            downloadDto.setCumulativeInt775(resultSet.getString(28));
           downloadDto.setInterestPayout805Percent(resultSet.getString(29));
            downloadDto.setNonCumulativeInt805(resultSet.getString(30));
            downloadDto.setCumulativeInt805(resultSet.getString(31));
            downloadDto.setTotalInterestPayout(resultSet.getString(32));

// PAN Status
            downloadDto.setInOperativePan(resultSet.getString(33));
            downloadDto.setNonFilers(resultSet.getString(34));

// TDS Exemption Details
            downloadDto.setAge(resultSet.getString(35));
            downloadDto.setTaxableLimit(resultSet.getString(36));
            downloadDto.setWaiverDocument(resultSet.getString(37));
            downloadDto.setWaiverDocumentRefNo(resultSet.getString(38));
            downloadDto.setRrbTdsRate(resultSet.getString(39));
            downloadDto.setThresholdExceedAmount(resultSet.getString(40));
            downloadDto.setThresholdExceedTdsAmount(resultSet.getString(41));
            downloadDto.setExemptionRate(resultSet.getString(42));
            downloadDto.setExemptionAmount(resultSet.getString(43));
            downloadDto.setExemptionTdsAmount(resultSet.getString(44));
            downloadDto.setSurchargePercent(resultSet.getString(45));
            downloadDto.setEduCessPercent(resultSet.getString(46));
            downloadDto.setBaseRateAmt(resultSet.getString(47));
            downloadDto.setSurchargeAmt(resultSet.getString(48));
            downloadDto.setEduCessAmt(resultSet.getString(49));
            downloadDto.setTdsAmount(resultSet.getString(50));
            downloadDto.setTdsRecoveryAmount(resultSet.getString(51));
            downloadDto.setTdsRefundAmount(resultSet.getString(52));

// FRSB Interest Payout Details
            downloadDto.setIntRate(resultSet.getString(53));
            downloadDto.setLastIntPaid(resultSet.getString(54));
            downloadDto.setPendingInterest(resultSet.getString(55));
            downloadDto.setCurrentHalfYearlyInterest(resultSet.getString(56));
            downloadDto.setFutureInterest(resultSet.getString(57));
            downloadDto.setGrossInterestAmount(resultSet.getString(58));
            downloadDto.setIntPaidNet(resultSet.getString(59));
            downloadDto.setPaymentDate(resultSet.getString(60));
            downloadDto.setTotalAmountToBePaid(resultSet.getString(61));
            return downloadDto;

    }

    private ARRAY generateArray(Connection connection, List<String> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_RBI_BOND_SUB_ID_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_RBI_BOND_SUB_ID", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        String bondSubsId = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[1];
            bondSubsId = list.get(index);
            paramArray[0] = bondSubsId;
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


    public byte[] excelFileGeneratorReport(List<RbiBondCentralizedInterestTrailRunDownloadDto> downloadResponseDTOList , RbiBondCentralizedInterestTrailRunDtoRequest downloadDto) throws IOException {


        SXSSFWorkbook workbook = new SXSSFWorkbook();
        final String sheet1="sheet1";
        SXSSFSheet sheet = workbook.createSheet(sheet1);

        CellStyle style = workbook.createCellStyle();
        Font headerFont = workbook.createFont();

//            CellStyle centerStyle = workbook.createCellStyle();
//            centerStyle.setAlignment(HorizontalAlignment.CENTER);

        headerFont.setBold(true);
        headerFont.setFontHeight((short) 14);
        style.setFont(headerFont);

        SXSSFRow row = sheet.createRow(0);
        createCell(row,6, "Trial run for Brokerage", style);


        row = sheet.createRow(1);
        createCell(row, 6, "Branch Id :",style);
        createCell(row,7, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(2);
        createCell(row, 6, "Interest for the Half Year :",style);
        createCell(row,7, downloadDto.getInterestForHalfYear());

        row = sheet.createRow(3);
        createCell(row, 6, "Year :",style);
        createCell(row,7, downloadDto.getYear());


        int n = 9;
        if (downloadResponseDTOList.size() > 0) {
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont1 = workbook.createFont();
            headerFont1.setBold(true);
            headerStyle.setFont(headerFont1);

            // Add the main header row
            SXSSFRow mainHeaderRow = sheet.createRow(0);
            createCell(mainHeaderRow, 0, "Basic Details From BLA cum Subscription", headerStyle);
            createCell(mainHeaderRow, 22, "OTHER SCHEMES INTEREST RELATED INFO", headerStyle);
            createCell(mainHeaderRow, 30, "PAN STATUS", headerStyle);
            createCell(mainHeaderRow, 33, "TDS EXEMPTION DETAILS", headerStyle);
            createCell(mainHeaderRow, 41, "FRSB INTEREST PAYOUT DETAILS", headerStyle);

            // Add sub-header row
            SXSSFRow subHeaderRow = sheet.createRow(1);
            String[] subHeaders = {
                    "Sl.No", "Mode of Payment", "Branch Ref No", "Bla_no", "Ref No",
                    "Appl_Name1", "Appl_Name2", "Appl_Name3", "Addr1", "Addr2",
                    "Rec_Name", "Rec_Parent_Name", "Pin_Code", "Phone_no", "appl_status",
                    "Investment Amt", "CFD", "Mat Date", "Account_No", "Subscription Count",
                    "BankSortCode", "Bank_name", "Pan_No", "8% interest payout",
                    "Non Cumulative Int", "Cumulative Int", "7.75% interest payout",
                    "Non Cumulative Int 775", "Cumulative Int 775", "Total",
                    "In-operative PAN", "Non-filers", "Age", "Taxable Limit",
                    "Waiver Document", "Waiver Document Ref No.", "RRB-TDS rate",
                    "Threshold_Exceed_Amount", "Threshold_Exceed_TDS_Amount", "Exemption Rate %",
                    "Exemption_Amount", "Exemption_TDS_Amount", "Surcharge %", "Edu Cess %",
                    "Base Rate Amt", "Surcharge Amt", "Edu Cess Amt", "TDS Amount",
                    "TDS Recovery Amount", "TDS Refund Amount", "Int_Rate", "Last Int Paid",
                    "Pending Interest", "Current Half Yearly Interest", "Future Interest",
                    "Gross Interest Amount", "Int Paid (Net Interest Amount)"
            };
            for (int i = 0; i < subHeaders.length; i++) {
                createCell(subHeaderRow, i, subHeaders[i], headerStyle);
            }

            // Populate data rows based on DTO
            int rowIndex = 2;
            int index = 1;

            for (RbiBondCentralizedInterestTrailRunDownloadDto item : downloadResponseDTOList) {
                SXSSFRow dataRow = sheet.createRow(rowIndex++);
                int column = 0;
                createCell(dataRow, column++, String.valueOf(index++));
                createCell(dataRow, column++, item.getModeOfPayment());
                createCell(dataRow, column++, item.getBranchRefNo());
                createCell(dataRow, column++, item.getBlaNo());
                createCell(dataRow, column++, item.getRefNo());
                createCell(dataRow, column++, item.getApplName1());
                createCell(dataRow, column++, item.getApplName2());
                createCell(dataRow, column++, item.getApplName3());
                createCell(dataRow, column++, item.getAddr1());
                createCell(dataRow, column++, item.getAddr2());
                createCell(dataRow, column++, item.getRecName());
                createCell(dataRow, column++, item.getRecParentName());
                createCell(dataRow, column++, item.getPinCode());
                createCell(dataRow, column++, item.getPhoneNo());
                createCell(dataRow, column++, item.getApplStatus());
                createCell(dataRow, column++, item.getInvestmentAmt());
                createCell(dataRow, column++, item.getCfd());
                createCell(dataRow, column++, item.getMatDate());
                createCell(dataRow, column++, item.getAccountNo());
                createCell(dataRow, column++, item.getSubscriptionCount());
                createCell(dataRow, column++, item.getBankSortCode());
                createCell(dataRow, column++, item.getBankName());
                createCell(dataRow, column++, item.getPanNo());
                createCell(dataRow, column++, item.getInterestPayout8Percent());
                createCell(dataRow, column++, item.getNonCumulativeInt8());
                createCell(dataRow, column++, item.getCumulativeInt8());
                createCell(dataRow, column++, item.getInterestPayout8Percent());
                createCell(dataRow, column++, item.getNonCumulativeInt775());
                createCell(dataRow, column++, item.getCumulativeInt775());
                createCell(dataRow, column++, item.getTotalInterestPayout());
                createCell(dataRow, column++, item.getInOperativePan());
                createCell(dataRow, column++, item.getNonFilers());
                createCell(dataRow, column++, item.getAge());
                createCell(dataRow, column++, item.getTaxableLimit());
                createCell(dataRow, column++, item.getWaiverDocument());
                createCell(dataRow, column++, item.getWaiverDocumentRefNo());
                createCell(dataRow, column++, item.getRrbTdsRate());
                createCell(dataRow, column++, item.getThresholdExceedAmount());
                createCell(dataRow, column++, item.getThresholdExceedTdsAmount());
                createCell(dataRow, column++, item.getExemptionRate());
                createCell(dataRow, column++, item.getExemptionAmount());
                createCell(dataRow, column++, item.getExemptionTdsAmount());
                createCell(dataRow, column++, item.getSurchargePercent());
                createCell(dataRow, column++, item.getEduCessPercent());
                createCell(dataRow, column++, item.getBaseRateAmt());
                createCell(dataRow, column++, item.getSurchargeAmt());
                createCell(dataRow, column++, item.getEduCessAmt());
                createCell(dataRow, column++, item.getTdsAmount());
                createCell(dataRow, column++, item.getTdsRecoveryAmount());
                createCell(dataRow, column++, item.getTdsRefundAmount());
                createCell(dataRow, column++, item.getIntRate());
                createCell(dataRow, column++, item.getLastIntPaid());
                createCell(dataRow, column++, item.getPendingInterest());
                createCell(dataRow, column++, item.getCurrentHalfYearlyInterest());
                createCell(dataRow, column++, item.getFutureInterest());
                createCell(dataRow, column++, item.getGrossInterestAmount());
                createCell(dataRow, column, item.getTotalInterestPayout());

                // Optional: Clear rows if you are sure they are out of scope and can be removed
                if (rowIndex % 10000 == 0) {  // Every 10,000 rows
                    ((SXSSFSheet) sheet).flushRows(100); // Keep only the last 100 rows in memory
                }
            }

            // Auto-size columns based on content
            for (int i = 0; i < subHeaders.length; i++) {
                sheet.setColumnWidth(i, 5000);  // Set a reasonable width, adjust as needed
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        log.info(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }

   /* public List<RbiBondCentralizedInterestTrailRunDownloadDto> fetchData(RbiBondCentralizedInterestTrailRunDtoRequest downloadDto, int offset, int limit) {
        // Construct the SQL query with placeholders

            String sql =  "SELECT * FROM (\n" +
                    "    SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY Branch_Ref_No) AS row_num\n" +
                    "    FROM (" +"SELECT  \n" +
                "                (SELECT T4.sub_code_desc   FROM gbm_rbi_bond_sub_code_master T4 WHERE T4.code ='034' AND t4.sub_code =t2.mode_of_interest AND t4.is_deleted ='N')  AS mode_of_payment,\n" +
                "                t1.bond_subscription_id                                 AS Branch_Ref_No,\n" +
                "                t2.bla_no                                               AS bla_no,\n" +
                "                NULL                                                    AS ref_no,\n" +
                "                t2.bond_hld_first_name                                  AS Appl_Name1,\n" +
                "                T2.bond_hld_mid_name                                    AS Appl_Name2,\n" +
                "                T2.bond_hld_last_name                                   AS Appl_Name3,\n" +
                "                T2.add_1                                                AS addr1,\n" +
                "                T2.add_2                                                AS addr2,\n" +
                "                T2.CITY                                                 AS rec_name,\n" +
                "                (SELECT T5.SUB_CODE_DESC FROM GBM_RBI_BOND_SUB_CODE_MASTER T5 WHERE T5.CODE ='037' AND T5.SUB_CODE =T2.STATE AND T2.IS_DELETED ='N') AS rec_parent_name,\n" +
                "                T2.Pin_Code                                             AS pin_code,\n" +
                "                T2.phone_number                                         AS phone_no,\n" +
                "                (SELECT t5.sub_code_desc FROM gbm_rbi_bond_sub_code_master t5 WHERE t5.code ='029' AND t5.sub_code =T2.holder_type\n" +
                "                AND T5.is_deleted ='N')                                 AS appl_status,\n" +
                "                t1.subscription_amount                                  AS investment_amt,\n" +
                "                TO_CHAR(T1.value_date,'DD/MM/YYYY')                     AS CFD,\n" +
                "                TO_CHAR(T1.MATURITY_dATE,'DD/MM/YYYY')                  AS Mat_Date,\n" +
                "                t2.oper_acc_no_int                                      AS Account_No,\n" +
                "                (SELECT COUNT(1) FROM gbm_rbi_bond_int_trail_run T6 WHERE T6.sch_code = :schemeCode AND T6.verified_status ='N' \n" +
                "                AND T6.bond_subscription_id=t3.bond_subscription_id)    AS subscription_count,\n" +
                "                t1.SORT_CODE                                            AS bank_sort_code,\n" +
                "                T1.bank_code_non_micr                                   AS bank_name,\n" +
                "                T2.pan_no                                               AS pan_no,\n" +
                "                '0.00'                                                  AS interest_payout_8,\n" +
                "                '0.00'                                                  AS non_cumulative_int_8,\n" +
                "                '0.00'                                                  AS cumulative_int_8,\n" +
                "                '0'                                                     AS interest_payout_7,\n" +
                "                '0'                                                     AS NON_CUMULATIVE_INT_7,\n" +
                "                '0'                                                     AS cumulative_int_7,\n" +
                "                '0'                                                     AS total_amount,\n" +
                "                NULL                                                    AS In_operative_PAN,\n" +
                "                NULL                                                    AS Non_filers,\n" +
                "                TRUNC(months_between(SYSDATE,T2.BOND_HLD_DOB)/12)       AS age,\n" +
                "                NULL                                                    AS taxable_limit,\n" +
                "                null                                                    AS waiver_documnet,\n" +
                "                NULL                                                    AS WAIVER_DOCUMNET_REF_NO,\n" +
                "                null                                                    AS RRB_TDS_RATE,\n" +
                "                NULL                                                    AS THRESHOLD_eXCEED_AMT,\n" +
                "                NULL                                                    AS threshold_exceed_tds_Amt,\n" +
                "                NULL                                                    AS exemption_rate,\n" +
                "                NULL                                                    AS EXEMPTION_AMT,\n" +
                "                NULL                                                    AS EXEMPTION_TDS_AMOUNT,\n" +
                "                NULL                                                    AS surcharge,\n" +
                "                '0'                                                     AS educess,\n" +
                "                '0'                                                     AS base_rate_amt,\n" +
                "                '0'                                                     AS surchagre_Amt,\n" +
                "                '0'                                                     AS edu_Cess_amt,\n" +
                "                '0'                                                     AS tds_Amount,\n" +
                "                '0'                                                     AS tds_rec_amt,\n" +
                "                '0'                                                     AS tds_refund_amt,\n" +
                "                (SELECT int_rate_code FROM gbm_rbi_bond_scheme_details T7 WHERE t7.sch_Code = :schemeCode AND t7.verified_status ='Y' AND t7.is_Active ='Y' AND T7.is_deleted='N')   AS int_rate,\n" +
                "                NULL                                                    AS Last_Int_Paid,\n" +
                "                (SELECT SUM(INTEREST_AMT) FROM gbm_rbi_bond_int_trail_run T6 WHERE T6.sch_code = :schemeCode AND T6.verified_status ='N' \n" +
                "                AND T6.bond_subscription_id=t3.bond_subscription_id\n" +
                "                AND T6.INTERST_PERIOD_TO < ADD_MONTHS(TO_DATE('01-'||:interestForHalfYear||'-'||:year,'DD-MON-YYYY'),-6)\n" +
                "                )                                                    \n" +
                "                                                                        AS PENDING_INT_PAID,\n" +
                "                (SELECT INTEREST_AMT FROM gbm_rbi_bond_int_trail_run T6 WHERE T6.sch_code = :schemeCode AND T6.verified_status ='N' \n" +
                "                AND T6.bond_subscription_id=t3.bond_subscription_id\n" +
                "                AND T6.INTERST_PERIOD_TO BETWEEN ADD_MONTHS(TO_DATE('01-'||:interestForHalfYear||'-'||:year,'DD-MON-YYYY'),-6)\n" +
                "                AND TO_DATE('01-'||:interestForHalfYear||'-'||:year,'DD-MON-YYYY')-1\n" +
                "                )                                                    \n" +
                "                                                                        AS Current_Half_Yearly_Interest,\n" +
                "                NULL                                                    AS future_interest,\n" +
                "                ((SELECT SUM(INTEREST_AMT) FROM gbm_rbi_bond_int_trail_run T6 WHERE T6.sch_code = :schemeCode AND T6.verified_status ='N' \n" +
                "                AND T6.bond_subscription_id=t3.bond_subscription_id) ) \n" +
                "                                                                        AS gross_int_amt,\n" +
                "                NULL                                                    AS int_paid_amt\n" +
                "                FROM  gbm_rbi_bond_subscription_flow t1 INNER JOIN gbm_rbi_bond_ledger_acc_master T2\n" +
                "                ON \n" +
                "                ((t1.single_bla_no = t2.bla_no)\n" +
                "                or\n" +
                "                (t1.first_joint_bla_no = t2.bla_no))\n" +
                "                INNER JOIN\n" +
                "                gbm_rbi_bond_int_trail_run T3\n" +
                "                ON T3.bond_subscription_id =T1.bond_subscription_id\n" +
                "                WHERE \n" +
                "                t1.sch_code = :schemeCode AND t1.verified_status = 'Y' \n" +
                "                AND t1.is_active = 'Y'  AND t1.is_deleted ='N' -- AND t1.is_trnsf_out_flg = 'N'\n" +
                "                AND T2.sch_code = :schemeCode AND T2.verified_status = 'Y' \n" +
                "                AND T2.is_active = 'Y' AND T2.is_deleted ='N' AND t1.is_acct_closure = 'N' \n" +
                "                AND t1.account_open_date < TO_DATE('01-'||:interestForHalfYear||'-'|| :year,'DD-MON-YYYY') \n" +
                "                AND T3.verified_status ='N'" +
                " ) inner_query\n" +
                    ") WHERE row_num BETWEEN 0 + 1 AND 0 + :limit";

        // Create parameters map
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("schemeCode", downloadDto.getSchemeCode());
        params.addValue("interestForHalfYear", downloadDto.getInterestForHalfYear());
        params.addValue("year", downloadDto.getYear());
        params.addValue("limit", limit);
        params.addValue("offset", offset);

        // Execute query and return results
        return null;

    }*/

    @Override
    public ResponseDataDTO verifySelect(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondCentralizedInterestTrailRunDto output = new RbiBondCentralizedInterestTrailRunDto();
        RbiBondCentralizedInterestTrailRunDownloadDto verDetails = new RbiBondCentralizedInterestTrailRunDownloadDto();
        String resultMsg = "";
        String totalRecords = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRAIL_RUN_SP_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDtoRequest.getSchemeCode());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDtoRequest.getInterestForHalfYear());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDtoRequest.getYear());
                stmt.setString(index++, String.valueOf(rbiBondCentralizedInterestTrailRunDtoRequest.getPageNo()));
                stmt.setString(index++, String.valueOf(rbiBondCentralizedInterestTrailRunDtoRequest.getPageSize()));
                stmt.setString(index++, (null!=rbiBondCentralizedInterestTrailRunDtoRequest.getSearchValue()&&!rbiBondCentralizedInterestTrailRunDtoRequest.getSearchValue().trim().isEmpty())?rbiBondCentralizedInterestTrailRunDtoRequest.getSearchValue():null);
                if(null!=rbiBondCentralizedInterestTrailRunDtoRequest.getCheckedList()&&rbiBondCentralizedInterestTrailRunDtoRequest.getCheckedList().size()>0) {
                    stmt.setArray(index++, generateArrayForUncheckedVerify(connection, rbiBondCentralizedInterestTrailRunDtoRequest.getCheckedList()));
                }else{
                    stmt.setNull(index++, Types.ARRAY, "TYP_SINGLE_COL");
                }if(null!=rbiBondCentralizedInterestTrailRunDtoRequest.getUncheckedList()&&rbiBondCentralizedInterestTrailRunDtoRequest.getUncheckedList().size()>0) {
                    stmt.setArray(index++, generateArrayForUncheckedVerify(connection, rbiBondCentralizedInterestTrailRunDtoRequest.getUncheckedList()));
                }else{
                    stmt.setNull(index++, Types.ARRAY, "TYP_SINGLE_COL");
                }
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index).equals(CommonConstants.SUCCESS_CONST)) {
                    if (null!=rbiBondCentralizedInterestTrailRunDtoRequest.getOperationType()&&rbiBondCentralizedInterestTrailRunDtoRequest.getOperationType().equalsIgnoreCase("scheme")) {
                        List<HashMap<String, String>> torchOut = null;
                        resultSet = (ResultSet) stmt.getObject(12);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("schemeCode", resultSet.getString(1));
                                temp.put("schemeName", resultSet.getString(2));
                                temp.put("schemeStatus", resultSet.getString(3));
                                torchOut.add(temp);
                            }
                        }
                        output.setSchemeCodeOut(torchOut);
                        responseDataDTO.setResult(output);
                    } else if(null!=rbiBondCentralizedInterestTrailRunDtoRequest.getOperationType()&&rbiBondCentralizedInterestTrailRunDtoRequest.getOperationType().equalsIgnoreCase("INTERESTHALFYEAR")){
                        List<HashMap<String, String>> torchOut = null;
                        resultSet = (ResultSet) stmt.getObject(12);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("interestForHalfYear", resultSet.getString(1));
                                torchOut.add(temp);
                            }
                        }
                        output.setInterestHalfYearTorchOut(torchOut);
                        responseDataDTO.setResult(output);
                    }else {
                        List<HashMap<String, String>> torchOut = null;
                        resultSet = (ResultSet) stmt.getObject(12);
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
// Assuming you know the order of the columns in your SQL query
                                temp.put("modeOfPayment", resultSet.getString(1)); // mode_of_payment
                                temp.put("branchRefNo", resultSet.getString(2)); // Branch_Ref_No
                                temp.put("blaNo", resultSet.getString(3)); // bla_no
                                temp.put("refNo", resultSet.getString(4)); // ref_no
                                temp.put("applName1", resultSet.getString(5)); // Appl_Name1
                                temp.put("applName2", resultSet.getString(6)); // Appl_Name2
                                temp.put("applName3", resultSet.getString(7)); // Appl_Name3
                                temp.put("addr1", resultSet.getString(8)); // addr1
                                temp.put("addr2", resultSet.getString(9)); // addr2
                                temp.put("recName", resultSet.getString(10)); // rec_name
                                temp.put("recParentName", resultSet.getString(11)); // rec_parent_name
                                temp.put("pinCode", resultSet.getString(12)); // pin_code
                                temp.put("phoneNo", resultSet.getString(13)); // phone_no
                                temp.put("applStatus", resultSet.getString(14)); // appl_status
                                temp.put("investmentAmt", resultSet.getString(15)); // investment_amt
                                temp.put("cfd", resultSet.getString(16)); // CFD
                                temp.put("matDate", resultSet.getString(17)); // Mat_Date
                                temp.put("accountNo", resultSet.getString(18)); // Account_No
                                temp.put("subscriptionCount", resultSet.getString(19)); // subscription_count
                                temp.put("bankSortCode", resultSet.getString(20)); // bank_sort_code
                                temp.put("bankName", resultSet.getString(21)); // bank_name
                                temp.put("panNo", resultSet.getString(22)); // pan_no
                                temp.put("interestPayout8Percent", resultSet.getString(23)); // interest_payout_8
                                temp.put("nonCumulativeInt8", resultSet.getString(24)); // non_cumulative_int_8
                                temp.put("cumulativeInt8", resultSet.getString(25)); // cumulative_int_8
                                temp.put("interestPayout775Percent", resultSet.getString(26)); // interest_payout_7
                                temp.put("nonCumulativeInt775", resultSet.getString(27)); // NON_CUMULATIVE_INT_7
                                temp.put("cumulativeInt775", resultSet.getString(28)); // cumulative_int_7
                                temp.put("totalInterestPayout", resultSet.getString(29)); // total_amount
// PAN Status
                                temp.put("inOperativePan", resultSet.getString(30)); // In_operative_PAN
                                temp.put("nonFilers", resultSet.getString(31)); // Non_filers
// TDS Exemption Details
                                temp.put("age", resultSet.getString(32)); // age
                                temp.put("taxableLimit", resultSet.getString(33)); // taxable_limit
                                temp.put("waiverDocument", resultSet.getString(34)); // waiver_documnet
                                temp.put("waiverDocumentRefNo", resultSet.getString(35)); // WAIVER_DOCUMNET_REF_NO
                                temp.put("rrbTdsRate", resultSet.getString(36)); // RRB_TDS_RATE
                                temp.put("thresholdExceedAmount", resultSet.getString(37)); // THRESHOLD_eXCEED_AMT
                                temp.put("thresholdExceedTdsAmount", resultSet.getString(38)); // threshold_exceed_tds_Amt
                                temp.put("exemptionRate", resultSet.getString(39)); // exemption_rate
                                temp.put("exemptionAmount", resultSet.getString(40)); // EXEMPTION_AMT
                                temp.put("exemptionTdsAmount", resultSet.getString(41)); // EXEMPTION_TDS_AMOUNT
                                temp.put("surchargePercent", resultSet.getString(42)); // surcharge
                                temp.put("eduCessPercent", resultSet.getString(43)); // educess
                                temp.put("baseRateAmt", resultSet.getString(44)); // base_rate_amt
                                temp.put("surchargeAmt", resultSet.getString(45)); // surchagre_Amt
                                temp.put("eduCessAmt", resultSet.getString(46)); // edu_Cess_amt
                                temp.put("tdsAmount", resultSet.getString(47)); // tds_Amount
                                temp.put("tdsRecoveryAmount", resultSet.getString(48)); // tds_rec_amt
                                temp.put("tdsRefundAmount", resultSet.getString(49)); // tds_refund_amt

// FRSB Interest Payout Details
                                temp.put("intRate", resultSet.getString(50)); // int_rate
                                temp.put("lastIntPaid", resultSet.getString(51)); // Last_Int_Paid
                                temp.put("pendingInterest", resultSet.getString(52)); // PENDING_INT_PAID
                                temp.put("currentHalfYearlyInterest", resultSet.getString(53)); // Current_Half_Yearly_Interest
                                temp.put("futureInterest", resultSet.getString(54)); // future_interest
                                temp.put("grossInterestAmount", resultSet.getString(55)); // gross_int_amt
                                temp.put("intPaidNet", resultSet.getString(56)); // int_paid_amt                                torchOut.add(temp);
                                temp.put("verifyStatus", resultSet.getString(62)); // verify_status                                torchOut.add(temp);
                                torchOut.add(temp);
                            }
                        }
                        verDetails.setInterestTrailRunVerDetailList(torchOut);
                        verDetails.setCreatedBy(stmt.getString(13));
                        verDetails.setCreatedDate(stmt.getString(14));
                        verDetails.setVerifiedBy(stmt.getString(15));
                        responseDataDTO.setResult(verDetails);
                        totalRecords = stmt.getString(11) != null
                                ? stmt.getString(11)
                                : "0";
                        responseDataDTO.setTotal(Double.parseDouble(totalRecords));
                    }
                }
            }
            else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        }   catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getInterestTrailRunVerify(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        HashMap<String, Object> finalData = new HashMap<>();
        List<RbiBondCentralizedInterestTrailRunDownloadDto> gstAckFileDownloadDTOList = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;
        Connection conn = null;
        OracleConnection oracleConn = null;
        // IN PARAMS
        String p_user_id_in = "p_user_id_in";
        String p_user_sol_id_in = "p_user_sol_id_in";
        String p_sch_code_in = "p_sch_code_in";
        String P_int_half_month_in = "P_int_half_month_in";
        String p_int_year_in = "p_int_year_in";
        String p_page_no_in = "p_page_no_in";
        String p_page_size_in = "p_page_size_in";
        String p_search_value_in = "p_search_value_in";
        String P_MARKED_DET_IN = "P_MARKED_DET_IN";
        String p_unmarked_det_in = "p_unmarked_det_in";

        // OUT PARAMS
        String p_total_records_out = "p_total_records_out";
        String p_cursor_val_out = "p_cursor_val_out";
        String p_created_by_out = "p_created_by_out";
        String p_created_date_out = "p_created_date_out";
        String p_verified_status_out = "p_verified_status_out";
        String p_msg_out = "p_msg_out";
        String p_msg_desc_out = "p_msg_desc_out";
        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            oracleConn = conn.unwrap(OracleConnection.class);  // Unwrap to OracleConnection
            Array markedArray = null;
            Array unmarkedArray = null;
            if (rbiBondCentralizedInterestTrailRunDtoRequest.getCheckedList() != null &&
                    !rbiBondCentralizedInterestTrailRunDtoRequest.getCheckedList().isEmpty()) {
                markedArray = generateArrayForOracleConnectionVerify(oracleConn, rbiBondCentralizedInterestTrailRunDtoRequest.getCheckedList());
            }
            if (rbiBondCentralizedInterestTrailRunDtoRequest.getUncheckedList() != null &&
                    !rbiBondCentralizedInterestTrailRunDtoRequest.getUncheckedList().isEmpty()) {
                unmarkedArray = generateArrayForOracleConnectionVerify(oracleConn, rbiBondCentralizedInterestTrailRunDtoRequest.getUncheckedList());
            }
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername());
            inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId());
            inParams.put(p_sch_code_in, rbiBondCentralizedInterestTrailRunDtoRequest.getSchemeCode());
            inParams.put(P_int_half_month_in, rbiBondCentralizedInterestTrailRunDtoRequest.getInterestForHalfYear());
            inParams.put(p_int_year_in, rbiBondCentralizedInterestTrailRunDtoRequest.getYear());
            inParams.put(p_page_no_in, rbiBondCentralizedInterestTrailRunDtoRequest.getPageNo());
            inParams.put(p_page_size_in, rbiBondCentralizedInterestTrailRunDtoRequest.getPageSize());
            inParams.put(p_search_value_in, (null!=rbiBondCentralizedInterestTrailRunDtoRequest.getSearchValue()&&!rbiBondCentralizedInterestTrailRunDtoRequest.getSearchValue().trim().isEmpty())?rbiBondCentralizedInterestTrailRunDtoRequest.getSearchValue():null);
            inParams.put(P_MARKED_DET_IN, markedArray);
            inParams.put(p_unmarked_det_in,unmarkedArray);
            SqlParameter[] sqlParameters = {
                    new SqlParameter(p_user_id_in, Types.VARCHAR),
                    new SqlParameter(p_user_sol_id_in, Types.VARCHAR),
                    new SqlParameter(p_sch_code_in, Types.VARCHAR),
                    new SqlParameter(P_int_half_month_in, Types.VARCHAR),
                    new SqlParameter(p_int_year_in, Types.VARCHAR),
                    new SqlParameter(p_page_no_in, Types.VARCHAR),
                    new SqlParameter(p_page_size_in, Types.VARCHAR),
                    new SqlParameter(p_search_value_in, Types.VARCHAR),
                    new SqlParameter(P_MARKED_DET_IN, OracleTypes.ARRAY, "TYP_SINGLE_COL"),
                    new SqlParameter(p_unmarked_det_in, OracleTypes.ARRAY, "TYP_SINGLE_COL")
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_total_records_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_cursor_val_out, OracleTypes.CURSOR),
                    new SqlOutParameter(p_created_by_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_created_date_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_verified_status_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_desc_out, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(p_cursor_val_out, (rs, rowNum) -> mapGSTAckEkuberRowToExcelData(rs, rowNum));

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    //ProcNameEnum.pkg_gbm_gst_inet_rep_excel.getProcName(),
                    "pkg_gbm_rbi_bond_trail_run",
                    "sp_ver_sel",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );

            gstAckFileDownloadDTOList = (List<RbiBondCentralizedInterestTrailRunDownloadDto>) result.get(p_cursor_val_out);
            totalRecords = result.get(p_total_records_out) != null
                    ? result.get(p_total_records_out).toString()
                    : "0";
            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(gstAckFileDownloadDTOList)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            }
            else{
                finalData.put("gstAckFileDownloadDTOList", gstAckFileDownloadDTOList);
                finalData.put("createdBy", result.get(p_created_by_out));
                finalData.put("createdDate", result.get(p_created_date_out));
                finalData.put("verifiedBy", result.get(p_verified_status_out));
                responseDataDTO.setResult(finalData);
            }
        } catch (SQLException e) {
            log.error("Database error while creating Oracle ARRAYs", e);
            throw new RuntimeException("Error creating Oracle ARRAYs", e);
        }catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setTotal(Double.parseDouble(totalRecords));
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySubmit(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = databaseConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRAIL_RUN_SP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getSchemeCode());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getYear());
                stmt.setArray(index++, generateArrayForUncheckedVerify(connection, rbiBondCentralizedInterestTrailRunDto.getCheckedList()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            databaseConnectionFactory.closeResources(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_TRAIL_RUN_SP_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getSchemeCode());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
                stmt.setString(index++, rbiBondCentralizedInterestTrailRunDto.getYear());
                stmt.setArray(index++, generateArrayForUncheckedVerify(connection, rbiBondCentralizedInterestTrailRunDto.getUncheckedList()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private Array generateArrayForOracleConnectionVerify(OracleConnection connection, List<String> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[1];
            paramArray[0] = list.get(index);
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }

        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
    private Array generateArrayForUncheckedVerify(Connection connection, List<String> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[1];
            paramArray[0] = list.get(index);
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }

        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private RbiBondCentralizedInterestTrailRunDownloadDto mapGSTAckEkuberRowToExcelData(ResultSet rs, int rowNum) throws SQLException {
        RbiBondCentralizedInterestTrailRunDownloadDto oGSTAckFileDownloadDTO = new RbiBondCentralizedInterestTrailRunDownloadDto();
        oGSTAckFileDownloadDTO.setModeOfPayment(rs.getString(1)); // mode_of_payment
        oGSTAckFileDownloadDTO.setBranchRefNo(rs.getString(2)); // Branch_Ref_No
        oGSTAckFileDownloadDTO.setBlaNo(rs.getString(3)); // bla_no
        oGSTAckFileDownloadDTO.setRefNo(rs.getString(4)); // ref_no
        oGSTAckFileDownloadDTO.setApplName1(rs.getString(5)); // Appl_Name1
        oGSTAckFileDownloadDTO.setApplName2(rs.getString(6)); // Appl_Name2
        oGSTAckFileDownloadDTO.setApplName3(rs.getString(7)); // Appl_Name3
        oGSTAckFileDownloadDTO.setAddr1(rs.getString(8)); // addr1
        oGSTAckFileDownloadDTO.setAddr2(rs.getString(9)); // addr2
        oGSTAckFileDownloadDTO.setRecName(rs.getString(10)); // rec_name
        oGSTAckFileDownloadDTO.setRecParentName(rs.getString(11)); // rec_parent_name
        oGSTAckFileDownloadDTO.setPinCode(rs.getString(12)); // pin_code
        oGSTAckFileDownloadDTO.setPhoneNo(rs.getString(13)); // phone_no
        oGSTAckFileDownloadDTO.setApplStatus(rs.getString(14)); // appl_status
        oGSTAckFileDownloadDTO.setInvestmentAmt(rs.getString(15)); // investment_amt
        oGSTAckFileDownloadDTO.setCfd(rs.getString(16)); // CFD
        oGSTAckFileDownloadDTO.setMatDate(rs.getString(17)); // Mat_Date
        oGSTAckFileDownloadDTO.setAccountNo(rs.getString(18)); // Account_No
        oGSTAckFileDownloadDTO.setSubscriptionCount(rs.getString(19)); // subscription_count
        oGSTAckFileDownloadDTO.setBankSortCode(rs.getString(20)); // bank_sort_code
        oGSTAckFileDownloadDTO.setBankName(rs.getString(21)); // bank_name
        oGSTAckFileDownloadDTO.setPanNo(rs.getString(22)); // pan_no
        oGSTAckFileDownloadDTO.setInterestPayout8Percent(rs.getString(23)); // interest_payout_8
        oGSTAckFileDownloadDTO.setNonCumulativeInt8(rs.getString(24)); // non_cumulative_int_8
        oGSTAckFileDownloadDTO.setCumulativeInt8(rs.getString(25)); // cumulative_int_8
        oGSTAckFileDownloadDTO.setInterestPayout775Percent(rs.getString(26)); // interest_payout_7
        oGSTAckFileDownloadDTO.setNonCumulativeInt775(rs.getString(27)); // NON_CUMULATIVE_INT_7
        oGSTAckFileDownloadDTO.setCumulativeInt775(rs.getString(28)); // cumulative_int_7
        oGSTAckFileDownloadDTO.setInterestPayout805Percent(rs.getString(29)); // cumulative_int_7
        oGSTAckFileDownloadDTO.setNonCumulativeInt805(rs.getString(30)); // cumulative_int_7
        oGSTAckFileDownloadDTO.setCumulativeInt805(rs.getString(31)); // cumulative_int_7
        oGSTAckFileDownloadDTO.setTotalInterestPayout(rs.getString(32)); // total_amount
        oGSTAckFileDownloadDTO.setInOperativePan(rs.getString(33)); // In_operative_PAN
        oGSTAckFileDownloadDTO.setNonFilers(rs.getString(34)); // Non_filers
        oGSTAckFileDownloadDTO.setAge(rs.getString(35)); // age
        oGSTAckFileDownloadDTO.setTaxableLimit(rs.getString(36)); // taxable_limit
        oGSTAckFileDownloadDTO.setWaiverDocument(rs.getString(37)); // waiver_documnet
        oGSTAckFileDownloadDTO.setWaiverDocumentRefNo(rs.getString(38)); // WAIVER_DOCUMNET_REF_NO
        oGSTAckFileDownloadDTO.setRrbTdsRate(rs.getString(39)); // RRB_TDS_RATE
        oGSTAckFileDownloadDTO.setThresholdExceedAmount(rs.getString(40)); // THRESHOLD_eXCEED_AMT
        oGSTAckFileDownloadDTO.setThresholdExceedTdsAmount(rs.getString(41)); // threshold_exceed_tds_Amt
        oGSTAckFileDownloadDTO.setExemptionRate(rs.getString(42)); // exemption_rate
        oGSTAckFileDownloadDTO.setExemptionAmount(rs.getString(43)); // EXEMPTION_AMT
        oGSTAckFileDownloadDTO.setExemptionTdsAmount(rs.getString(44)); // EXEMPTION_TDS_AMOUNT
        oGSTAckFileDownloadDTO.setSurchargePercent(rs.getString(45)); // surcharge
        oGSTAckFileDownloadDTO.setEduCessPercent(rs.getString(46)); // educess
        oGSTAckFileDownloadDTO.setBaseRateAmt(rs.getString(47)); // base_rate_amt
        oGSTAckFileDownloadDTO.setSurchargeAmt(rs.getString(48)); // surchagre_Amt
        oGSTAckFileDownloadDTO.setEduCessAmt(rs.getString(49)); // edu_Cess_amt
        oGSTAckFileDownloadDTO.setTdsAmount(rs.getString(50)); // tds_Amount
        oGSTAckFileDownloadDTO.setTdsRecoveryAmount(rs.getString(51)); // tds_rec_amt
        oGSTAckFileDownloadDTO.setTdsRefundAmount(rs.getString(52)); // tds_refund_amt
        oGSTAckFileDownloadDTO.setIntRate(rs.getString(55)); // int_rate
        oGSTAckFileDownloadDTO.setLastIntPaid(rs.getString(54)); // Last_Int_Paid
        oGSTAckFileDownloadDTO.setPendingInterest(rs.getString(55)); // PENDING_INT_PAID
        oGSTAckFileDownloadDTO.setCurrentHalfYearlyInterest(rs.getString(56)); // Current_Half_Yearly_Interest
        oGSTAckFileDownloadDTO.setFutureInterest(rs.getString(57)); // future_interest
        oGSTAckFileDownloadDTO.setGrossInterestAmount(rs.getString(58)); // gross_int_amt
        oGSTAckFileDownloadDTO.setIntPaidNet(rs.getString(59)); // int_paid_amt                                torchOut.add(temp);
        oGSTAckFileDownloadDTO.setPaymentDate(rs.getString(60)); // int_paid_amt                                torchOut.add(temp);
        oGSTAckFileDownloadDTO.setTotalAmountToBePaid(rs.getString(61)); // int_paid_amt                                torchOut.add(temp);
        oGSTAckFileDownloadDTO.setVerifyStatus(rs.getString(62)); // verified_status                                torchOut.add(temp);
        return oGSTAckFileDownloadDTO;
    }

    @Override
    public ResponseDataDTO downloadMerged(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto) {
        String resultMsg = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            HashMap<String, Object> finalData = new HashMap<>();
            List<RbiBondCentralizedInterestTrailRunDownloadDto> downloadResponseDTOList = Collections.emptyList();
            resultMsg = "SUCCESS";

            // IN PARAMS
            String p_user_id_in = "p_user_id_in";
            String p_user_sol_id_in = "p_user_sol_id_in";
            String p_sch_code_in = "p_sch_code_in";
            String P_int_half_month_in = "p_int_month_in";
            String p_int_year_in = "p_int_year_in";

            // OUT PARAMS
            String p_cursor_val_out = "p_cursor_val_out";
            String p_msg_out = "p_msg_out";
            String p_msg_desc_out = "p_msg_desc_out";
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(p_user_id_in, UserAccountUtils.getCurrentUsername());
            inParams.put(p_user_sol_id_in, UserAccountUtils.getCurrentSolId());
            inParams.put(p_sch_code_in,  rbiBondCentralizedInterestTrailRunDto.getSchemeCode());
            inParams.put(P_int_half_month_in, rbiBondCentralizedInterestTrailRunDto.getInterestForHalfYear());
            inParams.put(p_int_year_in, rbiBondCentralizedInterestTrailRunDto.getYear());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(p_user_id_in, Types.VARCHAR),
                    new SqlParameter(p_user_sol_id_in, Types.VARCHAR),
                    new SqlParameter(p_sch_code_in, Types.VARCHAR),
                    new SqlParameter(P_int_half_month_in, Types.VARCHAR),
                    new SqlParameter(p_int_year_in, Types.VARCHAR)
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(p_cursor_val_out, OracleTypes.CURSOR),
                    new SqlOutParameter(p_msg_out, OracleTypes.VARCHAR),
                    new SqlOutParameter(p_msg_desc_out, OracleTypes.VARCHAR)
            };
            long startTime = System.currentTimeMillis();

            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(p_cursor_val_out, (rs, rowNum) -> mapDownloadMergedRowMapper(rs, rowNum));


            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(

                    "PKG_GBM_RBI_BOND_TRAIL_RUN",
                    "sp_download_merger",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers
            );
            downloadResponseDTOList = (List<RbiBondCentralizedInterestTrailRunDownloadDto>) result.get(p_cursor_val_out);


            if (!downloadResponseDTOList.isEmpty()) {
                finalData.put("gstAckFileDownloadDTOList", downloadResponseDTOList);
                responseDataDTO.setResult(finalData);
                responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
            } else {
                log.error(CommonConstants.NDF);
                responseDataDTO.setMessage("No data to download");
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        }
        return responseDataDTO;
    }

    private RbiBondCentralizedInterestTrailRunDownloadDto mapDownloadMergedRowMapper(ResultSet resultSet, int rowNum) throws SQLException {

        RbiBondCentralizedInterestTrailRunDownloadDto downloadDto = new RbiBondCentralizedInterestTrailRunDownloadDto();
        downloadDto.setModeOfPayment(resultSet.getString(1));
//        downloadDto.setBranchRefNo(resultSet.getString(2));
        downloadDto.setBlaNo(resultSet.getString(2));
//        downloadDto.setRefNo(resultSet.getString(4));
        downloadDto.setApplName1(resultSet.getString(3));
        downloadDto.setApplName2(resultSet.getString(4));
        downloadDto.setApplName3(resultSet.getString(5));
        downloadDto.setAddr1(resultSet.getString(6));
        downloadDto.setAddr2(resultSet.getString(7));
        downloadDto.setRecName(resultSet.getString(8));
        downloadDto.setRecParentName(resultSet.getString(9));
        downloadDto.setPinCode(resultSet.getString(10));
        downloadDto.setPhoneNo(resultSet.getString(11));
        downloadDto.setApplStatus(resultSet.getString(12));
        downloadDto.setInvestmentAmt(resultSet.getString(13));
//        downloadDto.setCfd(resultSet.getString(16));
//        downloadDto.setMatDate(resultSet.getString(17));
        downloadDto.setAccountNo(resultSet.getString(14));
        downloadDto.setSubscriptionCount(resultSet.getString(15));
        downloadDto.setBankSortCode(resultSet.getString(16));
        downloadDto.setBankName(resultSet.getString(17));
        downloadDto.setPanNo(resultSet.getString(18));
        downloadDto.setInterestPayout8Percent(resultSet.getString(19));
        downloadDto.setNonCumulativeInt8(resultSet.getString(20));
        downloadDto.setCumulativeInt8(resultSet.getString(21));
        downloadDto.setInterestPayout775Percent(resultSet.getString(22));
        downloadDto.setNonCumulativeInt775(resultSet.getString(23));
        downloadDto.setCumulativeInt775(resultSet.getString(24));
        downloadDto.setInterestPayout805Percent(resultSet.getString(25));
        downloadDto.setNonCumulativeInt805(resultSet.getString(26));
        downloadDto.setCumulativeInt805(resultSet.getString(27));
        downloadDto.setTotalInterestPayout(resultSet.getString(28));

// PAN Status
        downloadDto.setInOperativePan(resultSet.getString(29));
        downloadDto.setNonFilers(resultSet.getString(30));

// TDS Exemption Details
        downloadDto.setAge(resultSet.getString(31));
        downloadDto.setTaxableLimit(resultSet.getString(32));
        downloadDto.setWaiverDocument(resultSet.getString(33));
        downloadDto.setWaiverDocumentRefNo(resultSet.getString(34));
        downloadDto.setRrbTdsRate(resultSet.getString(35));
        downloadDto.setThresholdExceedAmount(resultSet.getString(36));
        downloadDto.setThresholdExceedTdsAmount(resultSet.getString(37));
        downloadDto.setExemptionRate(resultSet.getString(38));
        downloadDto.setExemptionAmount(resultSet.getString(39));
        downloadDto.setExemptionTdsAmount(resultSet.getString(40));
        downloadDto.setSurchargePercent(resultSet.getString(41));
        downloadDto.setEduCessPercent(resultSet.getString(42));
        downloadDto.setBaseRateAmt(resultSet.getString(43));
        downloadDto.setSurchargeAmt(resultSet.getString(44));
        downloadDto.setEduCessAmt(resultSet.getString(45));
        downloadDto.setTdsAmount(resultSet.getString(46));
        downloadDto.setTdsRecoveryAmount(resultSet.getString(47));
        downloadDto.setTdsRefundAmount(resultSet.getString(48));

// FRSB Interest Payout Details
        downloadDto.setIntRate(resultSet.getString(49));
        downloadDto.setLastIntPaid(resultSet.getString(50));
        downloadDto.setPendingInterest(resultSet.getString(51));
        downloadDto.setCurrentHalfYearlyInterest(resultSet.getString(52));
        downloadDto.setFutureInterest(resultSet.getString(53));
        downloadDto.setGrossInterestAmount(resultSet.getString(54));
        downloadDto.setIntPaidNet(resultSet.getString(55));
        downloadDto.setPaymentDate(resultSet.getString(56));
//        downloadDto.setTotalAmountToBePaid(resultSet.getString(61));
        return downloadDto;

    }


}
