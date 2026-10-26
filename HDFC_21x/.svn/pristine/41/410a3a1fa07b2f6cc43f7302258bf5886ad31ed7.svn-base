package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.common.util.JdbcConnectionFactory;
import com.isl.gbm.rbibond.dao.RbiBondScrollsDAO;
import com.isl.gbm.rbibond.dto.scrolls.*;
import com.isl.gbm.rbibond.util.JDBCTemplateUtil;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.util.CollectionUtils;

import jakarta.annotation.PostConstruct;

import static com.isl.gbm.rbibond.util.CommonReportsConstants.*;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.curValOut;
import static com.isl.gbm.rbibond.util.CommonReportsConstants.msgOut;


@Repository
public class RbiBondScrollsDAOImpl implements RbiBondScrollsDAO {
    private static final Logger log = LoggerFactory.getLogger(RbiBondScrollsDAOImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;
    @Autowired
    private JdbcConnectionFactory jdbcConnectionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JDBCTemplateUtil jDBCTemplateUtil;

    @PostConstruct
    public void init() {
        jDBCTemplateUtil = new JDBCTemplateUtil(jdbcTemplate);
    }

    @Override
    public ResponseDataDTO getBondPrincipalRepay(BondPrincipalRepayDTO bondPrincipalRepayDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        BondPrincipalRepayDTO bondPrincipalRepayDTO1 = null;
        List<BondPrincipalRepayDTO> bondPrincipalRepayDTOList = null;
        Map<String, Object> resultMap = null;
        double grandTotal = 0;

        try {
            connection = jdbcConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_PRIN_REPAY_REP.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondPrincipalRepayDTO.getSchemeCode());
                stmt.setString(index++, bondPrincipalRepayDTO.getFromDate());
                stmt.setString(index++, bondPrincipalRepayDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    if (resultSet != null) {
                        bondPrincipalRepayDTOList = new ArrayList<>();

                        while (resultSet.next()) {

                            bondPrincipalRepayDTO1 = new BondPrincipalRepayDTO();
                            bondPrincipalRepayDTO1.setBlaNo(resultSet.getString(1));
                            bondPrincipalRepayDTO1.setDueDateRepay(resultSet.getString(2));
                            bondPrincipalRepayDTO1.setDateOfRepayment(resultSet.getString(3));
                            bondPrincipalRepayDTO1.setAmtOfRepay(resultSet.getString(4));
                            bondPrincipalRepayDTO1.setCreditBankAcc(resultSet.getString(5));
                            bondPrincipalRepayDTO1.setRemarks(resultSet.getString(6));
                            bondPrincipalRepayDTO1.setInitials(resultSet.getString(7));

                            bondPrincipalRepayDTOList.add(bondPrincipalRepayDTO1);
                            grandTotal = grandTotal + Double.parseDouble((resultSet.getString(4) != null) ? (resultSet.getString(4)) : ("0"));

                        }

                        resultMap = new HashMap<>();
                        resultMap.put("grandTotal", grandTotal);
                        resultMap.put("bondPrincipalRepayDTOList", bondPrincipalRepayDTOList);
                        responseDataDTO.setResult(resultMap);
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
            jdbcConnectionFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBondInterestRepay(BondInterestRepayDTO bondInterestRepayDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        BondInterestRepayDTO bondInterestRepayDTO1 = null;
        List<BondInterestRepayDTO> bondInterestRepayDTOList = null;
        Map<String, Object> resultMap = null;
        double total = 0;

        try {
            connection = jdbcConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_INTEREST_PAY.getProcName());
                stmt.setString(index++, "NIM");
                stmt.setString(index++, "101");
//                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
//                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondInterestRepayDTO.getSchemeCode());
                stmt.setString(index++, bondInterestRepayDTO.getIntertestPaidFor());
                stmt.setString(index++, bondInterestRepayDTO.getFinacialYear());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    if (resultSet != null) {
                        bondInterestRepayDTOList = new ArrayList<>();
                        while (resultSet.next()) {
                            bondInterestRepayDTO1 = new BondInterestRepayDTO();
                            bondInterestRepayDTO1.setBlaNo(resultSet.getString(1));
                            bondInterestRepayDTO1.setPrincipalAmt(resultSet.getString(2));
                            bondInterestRepayDTO1.setInterestAmt(resultSet.getString(3));
                            bondInterestRepayDTO1.setDateOfInterCredit(resultSet.getString(4));
                            bondInterestRepayDTO1.setCreditAccNo(resultSet.getString(5));
                            bondInterestRepayDTO1.setRemarks(resultSet.getString(6));
                            bondInterestRepayDTO1.setInitials(resultSet.getString(7));
                            bondInterestRepayDTO1.setDueDateRepay(resultSet.getString(8));
                            bondInterestRepayDTOList.add(bondInterestRepayDTO1);
                            total = total + Double.parseDouble((resultSet.getString(3) != null) ? (resultSet.getString(3)) : ("0"));
                        }
                        resultMap = new HashMap<>();
                        resultMap.put("Total", total);
                        resultMap.put("bondInterestRepayDTOList", bondInterestRepayDTOList);
                        responseDataDTO.setResult(resultMap);
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
            jdbcConnectionFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBondRegisterLedgerAcc(BondRegisterLedgerAccDTO bondRegisterLedgerAccDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_LEDGE_ACC_COLL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondRegisterLedgerAccDTO.getSchemeCode());
                stmt.setString(index++, bondRegisterLedgerAccDTO.getFromDate());
                stmt.setString(index++, bondRegisterLedgerAccDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    if (resultSet != null) {
                        List<BondRegisterLedgerAccDTO> list = new ArrayList<>();
                        BondRegisterLedgerAccDTO item = null;
                        while (resultSet.next()) {
                            item = new BondRegisterLedgerAccDTO();
                            item.setDateOfTransaction(resultSet.getString(1));
                            item.setNoOfInvestmentAcc(resultSet.getString(2));
                            item.setTotInvestmentAmt(resultSet.getString(3));
                            item.setNoOfTnsfInAcc(resultSet.getString(4));
                            item.setNoOfTnsfInAmt(resultSet.getString(5));
                            item.setNoOfTnsfOutAcc(resultSet.getString(6));
                            item.setNoOfTnsfOutAmt(resultSet.getString(7));
                            item.setNoOfRepayAcc(resultSet.getString(8));
                            item.setNoOfRepayAmt(resultSet.getString(9));
                            item.setTotAcc(resultSet.getString(10));
                            item.setTotAmt(resultSet.getString(11));
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
    public ResponseDataDTO getBondRegLedgerLinkCell(BondRegisterLedgerLinkDTO bondRegisterLedgerLinkDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_LEDGE_ACC_CEN.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondRegisterLedgerLinkDTO.getSchemeCode());
                stmt.setString(index++, bondRegisterLedgerLinkDTO.getBranchId());
                stmt.setString(index++, bondRegisterLedgerLinkDTO.getFromDate());
                stmt.setString(index++, bondRegisterLedgerLinkDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (bondRegisterLedgerLinkDTO.getBranchId() == null && bondRegisterLedgerLinkDTO.getFromDate() == null && bondRegisterLedgerLinkDTO.getToDate() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<BondRegisterLedgerLinkDTO> list = new ArrayList<>();
                            BondRegisterLedgerLinkDTO item = null;
                            while (resultSet.next()) {
                                item = new BondRegisterLedgerLinkDTO();
                                item.setBranchId(resultSet.getString(1));
                                item.setBranchName(resultSet.getString(2));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    } else if (bondRegisterLedgerLinkDTO.getBranchId() != null && bondRegisterLedgerLinkDTO.getFromDate() != null && bondRegisterLedgerLinkDTO.getToDate() != null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<BondRegisterLedgerLinkDTO> list = new ArrayList<>();
                            BondRegisterLedgerLinkDTO item = null;
                            while (resultSet.next()) {
                                item = new BondRegisterLedgerLinkDTO();

                                item.setDateOfTransaction(resultSet.getString(1));
                                item.setNoOfInvestmentAcc(resultSet.getString(2));
                                item.setTotInvestmentAmt(resultSet.getString(3));
                                item.setNoOfTnsfInAcc(resultSet.getString(4));
                                item.setNoOfTnsfInAmt(resultSet.getString(5));
                                item.setNoOfTnsfOutAcc(resultSet.getString(6));
                                item.setNoOfTnsfOutAmt(resultSet.getString(7));
                                item.setNoOfRepayAcc(resultSet.getString(8));
                                item.setNoOfRepayAmt(resultSet.getString(9));
                                item.setTotAcc(resultSet.getString(10));
                                item.setTotAmt(resultSet.getString(11));
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
    public ResponseDataDTO getBrokerageHandlingReport(BondBrokerageHandleDTO bondBrokerageHandleDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        double brokeAmtAlPaidTotal = 0;
        double handlingCommTotal = 0;
        double brokeAmtPaidTotal = 0;
        double totalAmt = 0;
        Map<String, Object> resultMap = null;

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_APPEND_VII.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondBrokerageHandleDTO.getSchemeCode());
                stmt.setString(index++, bondBrokerageHandleDTO.getFromDate());
                stmt.setString(index++, bondBrokerageHandleDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    if (resultSet != null) {
                        List<BondBrokerageHandleDTO> list = new ArrayList<>();
                        BondBrokerageHandleDTO item = null;
                        while (resultSet.next()) {
                            item = new BondBrokerageHandleDTO();
                            item.setBranchId(resultSet.getString(1));
                            item.setBrokerageAmtAlPaid(resultSet.getDouble(2));
                            item.setHandlingComm(resultSet.getDouble(3));
                            item.setBrokerageFrom(resultSet.getString(4));
                            item.setBrokerageTo(resultSet.getString(5));
                            item.setBrokerageAmt(resultSet.getDouble(6));
                            item.setBrokerageAmtPaid(resultSet.getDouble(7));
                            item.setTotal(resultSet.getDouble(8));
                            list.add(item);
                            brokeAmtAlPaidTotal = brokeAmtAlPaidTotal + Double.parseDouble((resultSet.getString(2) != null) ? (resultSet.getString(2)) : ("0"));
                            brokeAmtPaidTotal = brokeAmtPaidTotal + Double.parseDouble((resultSet.getString(7) != null) ? (resultSet.getString(7)) : ("0"));
                            handlingCommTotal = handlingCommTotal + Double.parseDouble((resultSet.getString(3) != null) ? (resultSet.getString(3)) : ("0"));
                            totalAmt = totalAmt + Double.parseDouble((resultSet.getString(8) != null) ? (resultSet.getString(8)) : ("0"));
                        }
                        resultMap = new HashMap<>();
                        resultMap.put("TotalAmt", totalAmt);
                        resultMap.put("HandlingCommTotal", handlingCommTotal);
                        resultMap.put("BrokeAmtPaidTotal", brokeAmtPaidTotal);
                        resultMap.put("BrokeAmtAlPaidTotal", brokeAmtAlPaidTotal);
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
    public ResponseDataDTO statementOfBondsDetails(StatementofBondsIssuedDTO statementofBondsIssuedDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_rbi_bond_appendix_vi.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, statementofBondsIssuedDTO.getSchemeCode());
                stmt.setString(index++, statementofBondsIssuedDTO.getFromdate());
                stmt.setString(index++, statementofBondsIssuedDTO.getTodate());
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


                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    if (resultSet != null) {
                        List<StatementofBondsIssuedDTO> list = new ArrayList<>();
                        StatementofBondsIssuedDTO item = null;
                        while (resultSet.next()) {
                            item = new StatementofBondsIssuedDTO();

                            item.setBondLedgerAccountNumber(resultSet.getString(1));
                            item.setInvestmentNo(resultSet.getString(2));
                            item.setAmount(resultSet.getString(3));
                            item.setDateofInvestment(resultSet.getString(4));
                            item.setDueDateofRepayment(resultSet.getString(5));
                            item.setCASAdviceNo(resultSet.getString(6));
                            item.setCASAdviceDate(resultSet.getString(7));


                            list.add(item);
                        }
                        statementofBondsIssuedDTO.setCursorOne(list);
                    }


                    ResultSet resultSet1 = (ResultSet) stmt.getObject(7);
                    if (resultSet1 != null) {
                        List<StatementofBondsIssuedDTO> list = new ArrayList<>();
                        StatementofBondsIssuedDTO item = null;
                        while (resultSet1.next()) {
                            item = new StatementofBondsIssuedDTO();

                            item.setBondLedgerAccountNumber(resultSet1.getString(1));
                            item.setAmount(resultSet1.getString(2));
                            item.setDueDateofRepayment(resultSet1.getString(3));
                            item.setRepaidon(resultSet1.getString(4));
                            item.setInvestmentNo(resultSet1.getString(5));


                            list.add(item);
                        }
                        statementofBondsIssuedDTO.setCursorTwo(list);
                    }

                    ResultSet resultSet2 = (ResultSet) stmt.getObject(8);
                    if (resultSet2 != null) {
                        List<StatementofBondsIssuedDTO> list = new ArrayList<>();
                        StatementofBondsIssuedDTO item = null;
                        while (resultSet2.next()) {
                            item = new StatementofBondsIssuedDTO();

                            item.setBalanceacc(resultSet2.getString(1));
                            item.setBalanceamt(resultSet2.getString(2));
                            item.setVnoofsubscriptions(resultSet2.getString(3));
                            item.setVtotsubamt(resultSet2.getString(4));
                            item.setVnoofsubclosed(resultSet2.getString(5));
                            item.setVtotclosedamt(resultSet2.getString(6));
                            item.setTotalacc(resultSet2.getString(7));
                            item.setTotalamt(resultSet2.getString(8));
                            item.setNettotalacc(resultSet2.getString(9));
                            item.setNettotalam(resultSet2.getString(10));


                            list.add(item);
                        }
                        statementofBondsIssuedDTO.setCursorThree(list);
                    }

                    responseDataDTO.setResult(statementofBondsIssuedDTO);


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
    public ResponseDataDTO statementOfChargesServicingDetails(SatatementOfChargesBlaDTO satatementOfChargesBlaDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, Object> resultMap = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        double total = 0;
        double total2 = 0;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_gbm_rbi_bond_annexure_ix.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, satatementOfChargesBlaDTO.getSchemeCode());
                stmt.setString(index++, satatementOfChargesBlaDTO.getFromdate());
                stmt.setString(index++, satatementOfChargesBlaDTO.getTodate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(6);
                    if (resultSet != null) {
                        List<SatatementOfChargesBlaDTO> list = new ArrayList<>();
                        SatatementOfChargesBlaDTO item = null;
                        while (resultSet.next()) {
                            item = new SatatementOfChargesBlaDTO();

                            item.setBranch(resultSet.getString(1));
                            item.setBranchname(resultSet.getString(2));
                            item.setNewblaamt(resultSet.getString(3));
                            item.setExistingblaamt(resultSet.getString(4));
//                            total = total + Double.parseDouble(resultSet.getString(3) != null ? resultSet.getString(3) : ("0"));

                            list.add(item);
                            total = total + Double.parseDouble((resultSet.getString(3) != null) ? (resultSet.getString(3)) : ("0"));
                            total2 = total2 + Double.parseDouble((resultSet.getString(4) != null) ? (resultSet.getString(4)) : ("0"));

//                            total = total + Double.parseDouble(resultSet.getString(3) != null ? resultSet.getString(3) : ("0"));

                        }
                        resultMap = new HashMap<>();
                        resultMap.put("item", list);
                        resultMap.put("total", total);
                        resultMap.put("total2", total2);
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
    public ResponseDataDTO statementOfChargesTurnoverCommissionDetails(RbiBondTurnoverCommissionDTO rbiBondTurnoverCommissionDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        RbiBondTurnoverCommissionDTO rbiBondTurnoverCommissionDTO1 = null;
        List<RbiBondTurnoverCommissionDTO> rbiBondTurnoverCommissionDTOList = null;
        Map<String, Object> resultMap = null;
        double grandTotal = 0;
        double grandTotal2 = 0;
        double grandTotal3 = 0;
        double totalAmt = 0;


        try {
            connection = jdbcConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_BOND_TOC_SCROLL.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, null);
                stmt.setString(index++, rbiBondTurnoverCommissionDTO.getFlag());
                stmt.setString(index++, rbiBondTurnoverCommissionDTO.getFinancialYear());
                stmt.setString(index++, rbiBondTurnoverCommissionDTO.getQuarter());
//                stmt.setString(index++, rbiBondTurnoverCommissionDTO.getTotalClaim());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(8);
                    if (resultSet != null) {
                        rbiBondTurnoverCommissionDTOList = new ArrayList<>();

                        while (resultSet.next()) {

                            rbiBondTurnoverCommissionDTO1 = new RbiBondTurnoverCommissionDTO();
                           rbiBondTurnoverCommissionDTO1.setsNo(resultSet.getString(1));
                            rbiBondTurnoverCommissionDTO1.setTotalClaim(resultSet.getString(2));
                            rbiBondTurnoverCommissionDTO1.setNoOfTransections(resultSet.getString(3));
                            rbiBondTurnoverCommissionDTO1.setAmount(resultSet.getString(4));

                            rbiBondTurnoverCommissionDTOList.add(rbiBondTurnoverCommissionDTO1);
                        }

//                        resultMap = new HashMap<>();
//                        resultMap.put("grandTotal", grandTotal);
//                        resultMap.put("grandTotal2", grandTotal2);
//                        resultMap.put("grandTotal3", grandTotal3);
//                        resultMap.put("total", totalAmt);
//
//
//                        resultMap.put("bondPrincipalRepayDTOList", rbiBondTurnoverCommissionDTOList);
                        responseDataDTO.setResult(rbiBondTurnoverCommissionDTOList);
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
            jdbcConnectionFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO principleScrollreportAppendixXII(PrincipleScrollreportAppendixXIIDto scrollreportAppendixXIIDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = jdbcConnectionFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.sp_rbi_bond_prinscrrep.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, scrollreportAppendixXIIDto.getFlag());
                stmt.setString(index++, scrollreportAppendixXIIDto.getSchemeCode());
                stmt.setString(index++, scrollreportAppendixXIIDto.getFromDate());
                stmt.setString(index++, scrollreportAppendixXIIDto.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(7);
                    if (resultSet != null) {
                        List<PrincipleScrollreportAppendixXIIDto> list = new ArrayList<>();
                        PrincipleScrollreportAppendixXIIDto item = null;
                        while (resultSet.next()) {

                            if (scrollreportAppendixXIIDto.getFlag().equalsIgnoreCase("S")) {
                                item = new PrincipleScrollreportAppendixXIIDto();

                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            if (scrollreportAppendixXIIDto.getFlag().equalsIgnoreCase("D")) {
                                item = new PrincipleScrollreportAppendixXIIDto();


                                item.setBla(resultSet.getString(1));
                                item.setDueDateRepay(resultSet.getString(2));
                                item.setAmountOfRepayment(resultSet.getString(3));
                                item.setNomineeValue(resultSet.getString(4));
                                item.setCreditAccNo(resultSet.getString(5));
                                item.setAmountPaidCheque(resultSet.getString(6));
                                item.setRemarks(resultSet.getString(7));



                                list.add(item);
                            }
                            responseDataDTO.setResult(list);

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
            jdbcConnectionFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO estimateOfExpenses(EstimateOfExpensesDto estimateOfExpensesDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        HashMap<String, Object> resultMap = new HashMap<>();
        List<EstimateOfExpensesDto> list = new ArrayList<>();
        if (estimateOfExpensesDto.getFlag().equalsIgnoreCase("D")) {
            for (int i = 0; i <= 100; i++) {
                EstimateOfExpensesDto item = new EstimateOfExpensesDto();
                item.setSchemeName("APSRK4569B");
                item.setActualRecipt("45687" + i);
                item.setEstimatedRecipt("AP");
                item.setTotal("64387" + i);
                item.setBudgetEstimates("JAN");
                item.setActualRepayment("Single");
                item.setEstimatedRepayment("456" + i);
                item.setTotal1("45" + i);
                item.setBudgetEstimates1("Active");
                item.setActualPaymentInterest("18-APR-2025");
                item.setEstimatedPaymentInterest("1" + i);
                item.setTotal2("1" + i);
                item.setBudgetEstimates2("1" + i);
                list.add(item);
            }
        }
        resultMap.put("list", list);
        responseDataDTO.setResult(resultMap);
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getSubsTextFileDetails(SusbscriptionTextFileDto susbscriptionTextFileDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBTXTRBI.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, susbscriptionTextFileDto.getSchemeCode());
                stmt.setString(index++, susbscriptionTextFileDto.getFlag());
                stmt.setString(index++, susbscriptionTextFileDto.getFromDate());
                stmt.setString(index++, susbscriptionTextFileDto.getToDate());
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
                        List<SusbscriptionTextFileDto> list = new ArrayList<>();
                        SusbscriptionTextFileDto item = null;
                        while (resultSet.next()) {
                            if (susbscriptionTextFileDto.getFlag().equalsIgnoreCase("S")) {
                                item = new SusbscriptionTextFileDto();

                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            if (susbscriptionTextFileDto.getFlag().equalsIgnoreCase("D")) {
                                item = new SusbscriptionTextFileDto();

                                item.setNumber(resultSet.getString(1));
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
    public ResponseDataDTO getRedemptionTextFileDetails(RedemptionTextFileDto redemptionTextFileDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_REDMTXTRBI.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, redemptionTextFileDto.getSchemeCode());
                stmt.setString(index++, redemptionTextFileDto.getFlag());
                stmt.setString(index++, redemptionTextFileDto.getFromDate());
                stmt.setString(index++, redemptionTextFileDto.getToDate());
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
                        List<RedemptionTextFileDto> list = new ArrayList<>();
                        RedemptionTextFileDto item = null;
                        while (resultSet.next()) {
                            if (redemptionTextFileDto.getFlag().equalsIgnoreCase("S")) {
                                item = new RedemptionTextFileDto();

                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            if (redemptionTextFileDto.getFlag().equalsIgnoreCase("D")) {
                                item = new RedemptionTextFileDto();

                                item.setNumber(resultSet.getString(1));
                                list.add(item);
                            }
                            if (redemptionTextFileDto.getFlag().equalsIgnoreCase("274")) {
                                item = new RedemptionTextFileDto();

                                item.setNumber(resultSet.getString(1));
                                list.add(item);
                            }
                            if (redemptionTextFileDto.getFlag().equalsIgnoreCase("275")) {
                                item = new RedemptionTextFileDto();

                                item.setNumber(resultSet.getString(1));
                                list.add(item);
                            }
                            if (redemptionTextFileDto.getFlag().equalsIgnoreCase("276")) {
                                item = new RedemptionTextFileDto();

                                item.setNumber(resultSet.getString(1));
                                list.add(item);
                            }
                            if (redemptionTextFileDto.getFlag().equalsIgnoreCase("277")) {
                                item = new RedemptionTextFileDto();

                                item.setNumber(resultSet.getString(1));
                                list.add(item);
                            }
                            if (redemptionTextFileDto.getFlag().equalsIgnoreCase("CL")) {
                                item = new RedemptionTextFileDto();
                                String bondType = resultSet.getString(1);
                                item.setBondType(bondType);
                                item.setInterestAmount(resultSet.getString(2));
                                item.setInvestmentAmount(resultSet.getString(3));
                                list.add(item);
                            }

                            if (redemptionTextFileDto.getFlag().equalsIgnoreCase("PCL")) {
                                item = new RedemptionTextFileDto();
                                String bondType = resultSet.getString(1);
                                item.setBondType(bondType);
                                item.setInterestAmount(resultSet.getString(2));
                                item.setPrincipleAmount(resultSet.getString(3));
                                item.setPaymentDate(resultSet.getString(4));
                                item.setBlaNo(resultSet.getString(5));
                                item.setTotalAmount(resultSet.getString(6));
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
    public ResponseDataDTO getMonthBrokerageDetails(MonthlyBrokerageClaimDto monthlyBrokerageClaimDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_MONBRKCLM.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, monthlyBrokerageClaimDto.getFlag());
                stmt.setString(index++, monthlyBrokerageClaimDto.getSchemeCode());
                stmt.setString(index++, monthlyBrokerageClaimDto.getFromDate());
                stmt.setString(index++, monthlyBrokerageClaimDto.getToDate());
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
                        List<MonthlyBrokerageClaimDto> list = new ArrayList<>();
                        MonthlyBrokerageClaimDto item = null;
                        while (resultSet.next()) {
                            if (monthlyBrokerageClaimDto.getFlag().equalsIgnoreCase("S")) {
                                item = new MonthlyBrokerageClaimDto();

                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            if (monthlyBrokerageClaimDto.getFlag().equalsIgnoreCase("D")) {
                                item = new MonthlyBrokerageClaimDto();

                                item.setNumber(resultSet.getString(1));
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
    public ResponseDataDTO getInterestScrollReport5106Details(InterestScrollReportDTO interestScrollReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<InterestScrollReportDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";

        // IN PARAMS
        String userID = "p_user_id_in";
        String solID = "p_user_sol_id_in";
        String schemeCode = "p_sch_code_in";
        String flag = "p_flag_in";
        String fromDate = "p_from_date_in";
        String toDate = "p_to_date_in";
        // OUT PARAMS
        String curValOut = "p_cur_val_out";
        String msgOut = "p_msg_out";
        String msgDescOut = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, interestScrollReportDTO.getSchemeCode());
            inParams.put(flag, interestScrollReportDTO.getFlag());
            inParams.put(fromDate, interestScrollReportDTO.getFromDate());
            inParams.put(toDate, interestScrollReportDTO.getToDate());

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
                InterestScrollReportDTO item = new InterestScrollReportDTO();
                if (interestScrollReportDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (interestScrollReportDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNumber(rs.getString(1));
                    item.setPrincipalAmount(rs.getString(2));
                    item.setDueDate(rs.getString(3));
                    item.setGrossAmount(rs.getString(4));
                    item.setInterestPaidForFirstBrokenPeriod(rs.getString(5));
                    item.setInterestPaidForLastBrokenPeriod(rs.getString(6));
                    item.setCreditedToBankAccount(rs.getString(7));
                    item.setAmountPaid(rs.getString(8));
                    item.setRemarks(rs.getString(9));
                    item.setInitials(rs.getString(10));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_intscrrep",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<InterestScrollReportDTO>) result.get(curValOut);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAnnualReport(AnnualReportDto annualReportDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<InterestScrollReportDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";

        // IN PARAMS
        String userID = "p_user_id_in";
        String solID = "p_user_sol_id_in";
        String schemeCode = "p_sch_code_in";
        String flag = "p_flag_in";
        String financialYear = "p_fin_year_in";

        // OUT PARAMS
        String curValOut = "p_cur_val_out";
        String msgOut = "p_msg_out";
        String msgDescOut = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, annualReportDto.getSchemeCode());
            inParams.put(flag, annualReportDto.getFlag());
            inParams.put(financialYear, annualReportDto.getFinancialYear());


            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(financialYear, Types.VARCHAR),

            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                AnnualReportDto item = new AnnualReportDto();
                if (annualReportDto.getFlag().equalsIgnoreCase("FY")) {
                    item.setFinancialYear(rs.getString(1));

                }
                if (annualReportDto.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (annualReportDto.getFlag().equalsIgnoreCase("T")) {

                    item.setOrginalSerialNo(rs.getString(1));
                    item.setPersonName(rs.getString(2));
                    item.setPersonType(rs.getString(3));
                    item.setCustomerIdentity(rs.getString(4));
                    item.setGender(rs.getString(5));
                    item.setFatherName(rs.getString(6));
                    item.setPan(rs.getString(7));
                    item.setAadharNo(rs.getString(8));
                    item.setForm60Acknowledgment(rs.getString(9));
                    item.setIdentificationType(rs.getString(10));
                    item.setIdentificationNo(rs.getString(11));
                    item.setDateOfBirth(rs.getString(12));
                    item.setNationality(rs.getString(13));
                    item.setAddressType(rs.getString(14));
                    item.setFixedAddress(rs.getString(15));
                    item.setCity(rs.getString(16));
                    item.setPostCode(rs.getString(17));
                    item.setState(rs.getString(18));
                    item.setCountryCode(rs.getString(19));
                    item.setBusiness(rs.getString(20));
                    item.setPrimaryStdCode(rs.getString(21));
                    item.setPrimaryPhoneNo(rs.getString(22));
                    item.setPrimaryMobileNo(rs.getString(23));
                    item.setSecondaryStdCode(rs.getString(24));
                    item.setSecondaryPhoneNo(rs.getString(25));
                    item.setSecondaryMobileNo(rs.getString(26));
                    item.setEmail(rs.getString(27));
                    item.setRemarks(rs.getString(28));
                    item.setProductType(rs.getString(29));
                    item.setAggregateGrossAmountRecieved(rs.getString(30));
                    item.setAggregateGrossAmountCash(rs.getString(31));
                    item.setAggregateGrossAmountPaid(rs.getString(32));
                    item.setTransactionSummaryRemarks(rs.getString(33));
                    item.setProductIdentifier(rs.getString(34));
                    item.setLastDateOfTransaction(rs.getString(35));
                    item.setAmountRecieved(rs.getString(36));
                    item.setCashAmountRecieved(rs.getString(37));
                    item.setAmountPaidToPerson(rs.getString(38));
                    item.setRelatedAccountNumber(rs.getString(39));
                    item.setRelatedInstituteName(rs.getString(40));
                    item.setRelatedRefNo(rs.getString(41));
                    item.setFinancialTransactionDtlsRemarks(rs.getString(42));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_SCROLL_AIR_REPORTING",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<InterestScrollReportDTO>) result.get(curValOut);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getSft(SftScrollDTO sftScrollDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<SftScrollDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";

        // IN PARAMS
        String userID = "p_user_id_in";
        String solID = "p_user_sol_id_in";
        String flag = "p_flag_in";
        String schemeCode = "p_sch_code_in";
        String financialYear = "p_fin_year_in";

        // OUT PARAMS
        String curValOut = "p_cur_val_out";
        String msgOut = "p_msg_out";
        String msgDescOut = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(flag, sftScrollDTO.getFlag());
            inParams.put(schemeCode, sftScrollDTO.getSchemeCode());
            inParams.put(financialYear, sftScrollDTO.getFinancialYear());


            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(financialYear, Types.VARCHAR),

            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                SftScrollDTO item = new SftScrollDTO();
                if (sftScrollDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (sftScrollDTO.getFlag().equalsIgnoreCase("FY")) {
                    item.setFinancialYear(rs.getString(1));
                } else if (sftScrollDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPersonName(rs.getString(1));
                    item.setPanNo(rs.getString(2));
                    item.setAadhar(rs.getString(3));
                    item.setMobileNo(rs.getString(4));
                    item.setEmailId(rs.getString(5));
                    item.setAccNo(rs.getString(6));
                    item.setAccType(rs.getString(7));
                    item.setTotalIntPaid(rs.getString(8));
                    item.setBankIndicator(rs.getString(9));
                    item.setBlaRef(rs.getString(10));
                    item.setRemarks(rs.getString(11));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_scroll_sft",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<SftScrollDTO>) result.get(curValOut);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAppendixIV8Details(AppendixIV8DTO appendixIV8DTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<AppendixIV8DTO> schemeList = Collections.emptyList();
        List<AppendixIV8DTO> partANonCumulativeList = Collections.emptyList();
        List<AppendixIV8DTO> partACumulativeList = Collections.emptyList();
        List<AppendixIV8DTO> partBNonCumulativeList = Collections.emptyList();
        List<AppendixIV8DTO> partBCumulativeList = Collections.emptyList();
        List<AppendixIV8DTO> partCNonCumulativeList = Collections.emptyList();
        List<AppendixIV8DTO> partCCumulativeList = Collections.emptyList();
        List<AppendixIV8DTO> partDNonCumulativeList = Collections.emptyList();
        List<AppendixIV8DTO> partDCumulativeList = Collections.emptyList();
        String resultMsg = "SUCCESS";

        // IN PARAMS
        String userID = "p_user_id_in";
        String solID = "p_user_sol_id_in";
        String schemeCode = "p_sch_code_in";
        String flag = "p_flag_in";
        String fromDate = "p_from_date_in";
        String toDate = "p_to_date_in";

        // OUT PARAMS
        String curValOut = "p_cur_val_out";
        String partANonCumulative = "p_acc_ope_non_cum_out";
        String partACumulative = "p_acc_ope_cum_out";
        String partBNonCumulative = "p_tran_inf_non_cum_out";
        String partBCumulative = "p_tran_inf_cum_out";
        String partCNonCumulative = "p_tran_outf_non_cum_out";
        String partCCumulative = "p_tran_outf_cum_out";
        String partDNonCumulative = "p_repay_non_cum_out";
        String partDCumulative = "p_repay_cum_out";
        String msgOut = "p_msg_out";
        String msgDescOut = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, appendixIV8DTO.getSchemeCode());
            inParams.put(flag, appendixIV8DTO.getFlag());
            inParams.put(fromDate, appendixIV8DTO.getFromDate());
            inParams.put(toDate, appendixIV8DTO.getToDate());


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
                    new SqlOutParameter(partANonCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partACumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partBNonCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partBCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partCNonCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partCCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partDNonCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partDCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                AppendixIV8DTO item = new AppendixIV8DTO();
                if (appendixIV8DTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                }
                return item;
            });
            rowMappers.put(partANonCumulative, (rs, rowNum) -> {
                AppendixIV8DTO item = new AppendixIV8DTO();
                if (appendixIV8DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setaNcBlaNumber(rs.getString(1));
                    item.setaNcDueDate(rs.getString(2));
                    item.setaNcSch(rs.getString(3));
                    item.setaNcAmount(rs.getString(4));
                    item.setaNcCas(rs.getString(5));
                    log.info("The part a non cumulative: {}" , rs.getString(1));
                }
                return item;
            });
            rowMappers.put(partACumulative, (rs, rowNum) -> {
                AppendixIV8DTO item = new AppendixIV8DTO();
                if (appendixIV8DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setaCBlaNumber(rs.getString(1));
                    item.setaCAmount(rs.getString(2));
                    item.setaCCas(rs.getString(3));
                    item.setaCDueDate(rs.getString(4));
                    log.info("The part a cumulative: {}" , rs.getString(1));
                }
                return item;
            });

            rowMappers.put(partBNonCumulative, (rs, rowNum) -> {
                AppendixIV8DTO item = new AppendixIV8DTO();
                if (appendixIV8DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setbNcBlaNumber(rs.getString(1));
                    item.setbNcDueDate(rs.getString(2));
                    item.setbNcAmount(rs.getString(3));
                }
                return item;
            });
            rowMappers.put(partBCumulative, (rs, rowNum) -> {
                AppendixIV8DTO item = new AppendixIV8DTO();
                if (appendixIV8DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setbCBlaNumber(rs.getString(1));
                    item.setbCAmount(rs.getString(2));
                    item.setbCDueDate(rs.getString(3));
                    item.setbCTransferorBranchName(rs.getString(4));
                }
                return item;
            });
            rowMappers.put(partCNonCumulative, (rs, rowNum) -> {
                AppendixIV8DTO item = new AppendixIV8DTO();
                if (appendixIV8DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setcNcBlaNumber(rs.getString(1));
                    item.setcNcDueDate(rs.getString(2));
                    item.setcNcAmount(rs.getString(3));
                }
                return item;
            });
            rowMappers.put(partCCumulative, (rs, rowNum) -> {
                AppendixIV8DTO item = new AppendixIV8DTO();
                if (appendixIV8DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setcCBlaNumber(rs.getString(1));
                    item.setcCAmount(rs.getString(2));
                    item.setcCDueDate(rs.getString(3));
                    item.setcCTransferorBranchName(rs.getString(4));
                }
                return item;
            });
            rowMappers.put(partDNonCumulative, (rs, rowNum) -> {
                AppendixIV8DTO item = new AppendixIV8DTO();
                if (appendixIV8DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setdNcBlaNumber(rs.getString(1));
                    item.setdNcAmount(rs.getString(2));
                    item.setdNcDueDate(rs.getString(3));
                    item.setdNcRepaymentOn(rs.getString(4));
                }
                return item;
            });
            rowMappers.put(partDCumulative, (rs, rowNum) -> {
                AppendixIV8DTO item = new AppendixIV8DTO();
                if (appendixIV8DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setdCBlaNumber(rs.getString(1));
                    item.setdCAmount(rs.getString(2));
                    item.setdCDueDate(rs.getString(3));
                    item.setdCRepaymentOn(rs.getString(4));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_rbi_bond_appivper8",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            schemeList = (List<AppendixIV8DTO>) result.get(curValOut);
            partANonCumulativeList = (List<AppendixIV8DTO>) result.get(partANonCumulative);
            partACumulativeList = (List<AppendixIV8DTO>) result.get(partACumulative);
            partBNonCumulativeList = (List<AppendixIV8DTO>) result.get(partBNonCumulative);
            partBCumulativeList = (List<AppendixIV8DTO>) result.get(partBCumulative);
            partCNonCumulativeList = (List<AppendixIV8DTO>) result.get(partCNonCumulative);
            partCCumulativeList = (List<AppendixIV8DTO>) result.get(partCCumulative);
            partDNonCumulativeList = (List<AppendixIV8DTO>) result.get(partDNonCumulative);
            partDCumulativeList = (List<AppendixIV8DTO>) result.get(partDCumulative);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(schemeList) &&
                    (CollectionUtils.isEmpty(partANonCumulativeList) && CollectionUtils.isEmpty(partACumulativeList) &&
                            CollectionUtils.isEmpty(partBNonCumulativeList) && CollectionUtils.isEmpty(partBCumulativeList) &&
                            CollectionUtils.isEmpty(partCNonCumulativeList) && CollectionUtils.isEmpty(partCCumulativeList) &&
                            CollectionUtils.isEmpty(partDNonCumulativeList) && CollectionUtils.isEmpty(partDCumulativeList))) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("schemeList", schemeList);
                resultMap.put("partANonCumulativeList", partANonCumulativeList);
                resultMap.put("partACumulativeList", partACumulativeList);
                resultMap.put("partBNonCumulativeList", partBNonCumulativeList);
                resultMap.put("partBCumulativeList", partBCumulativeList);
                resultMap.put("partCNonCumulativeList", partCNonCumulativeList);
                resultMap.put("partCCumulativeList", partCCumulativeList);
                resultMap.put("partDNonCumulativeList", partDNonCumulativeList);
                resultMap.put("partDCumulativeList", partDCumulativeList);
                responseDataDTO.setResult(resultMap);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAppendixIV85Details(AppendixIV85DTO appendixIV85DTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<AppendixIV85DTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";

        // IN PARAMS
        String userID = "p_user_id_in";
        String solID = "p_user_sol_id_in";
        String flag = "p_flag_in";
        String schemeCode = "p_sch_code_in";
        String fromDate = "p_from_date_in";
        String toDate = "p_to_date_in";

        // OUT PARAMS
        String curValOut = "p_cur_val_out";
        String partANonCumulative = "p_acc_ope_non_cum_out";
        String partACumulative = "p_acc_ope_cum_out";
        String partBNonCumulative = "p_tran_inf_non_cum_out";
        String partBCumulative = "p_tran_inf_cum_out";
        String partCNonCumulative = "p_tran_outf_non_cum_out";
        String partCCumulative = "p_tran_outf_cum_out";
        String partDNonCumulative = "p_repay_non_cum_out";
        String partDCumulative = "p_repay_cum_out";
        String msgOut = "p_msg_out";
        String msgDescOut = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(flag, appendixIV85DTO.getFlag());
            inParams.put(schemeCode, appendixIV85DTO.getSchemeCode());
            inParams.put(fromDate, appendixIV85DTO.getFromDate());
            inParams.put(toDate, appendixIV85DTO.getToDate());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
            };

            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(partANonCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partACumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partBNonCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partBCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partCNonCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partCCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partDNonCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(partDCumulative, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                AppendixIV85DTO item = new AppendixIV85DTO();
                if (appendixIV85DTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                }
                return item;
            });
            rowMappers.put(partANonCumulative, (rs, rowNum) -> {
                AppendixIV85DTO item = new AppendixIV85DTO();
                if (appendixIV85DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNoNonCumA(rs.getString(1));
                    item.setAmountNonCumA(rs.getString(2));
                    item.setCASANonCumA(rs.getString(3));
                    item.setDueDateNonCumA(rs.getString(4));
                    item.setSchemeCode(rs.getString(5));
                }
                return item;
            });

            rowMappers.put(partACumulative, (rs, rowNum) -> {
                AppendixIV85DTO item = new AppendixIV85DTO();
                if (appendixIV85DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNoCumA(rs.getString(1));
                    item.setAmountCumA(rs.getString(2));
                    item.setCASCumA(rs.getString(3));
                    item.setDueDateCumA(rs.getString(4));
                }
                return item;
            });
            rowMappers.put(partBNonCumulative, (rs, rowNum) -> {
                AppendixIV85DTO item = new AppendixIV85DTO();
                if (appendixIV85DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNoNonCumB(rs.getString(1));
                    item.setAmountNonCumB(rs.getString(2));
                    item.setDueDateNonCumB(rs.getString(3));
                }
                return item;
            });
            rowMappers.put(partBCumulative, (rs, rowNum) -> {
                AppendixIV85DTO item = new AppendixIV85DTO();
                if (appendixIV85DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNoCumB(rs.getString(1));
                    item.setAmountCumB(rs.getString(2));
                    item.setDueDateCumB(rs.getString(3));
                    item.setTransferorBranchNameB(rs.getString(4));

                }
                return item;
            });
            rowMappers.put(partCNonCumulative, (rs, rowNum) -> {
                AppendixIV85DTO item = new AppendixIV85DTO();
                if (appendixIV85DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNoNonCumC(rs.getString(1));
                    item.setAmountNonCumC(rs.getString(2));
                    item.setDueDateNonCumC(rs.getString(3));

                }
                return item;
            });
            rowMappers.put(partCCumulative, (rs, rowNum) -> {
                AppendixIV85DTO item = new AppendixIV85DTO();
                if (appendixIV85DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNoCumC(rs.getString(1));
                    item.setAmountCumC(rs.getString(2));
                    item.setDueDateCumC(rs.getString(3));
                    item.setTransferorBranchNameC(rs.getString(4));

                }
                return item;
            });
            rowMappers.put(partDNonCumulative, (rs, rowNum) -> {
                AppendixIV85DTO item = new AppendixIV85DTO();
                if (appendixIV85DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNoNonCumD(rs.getString(1));
                    item.setAmountNonCumD(rs.getString(2));
                    item.setDueDateNonCumD(rs.getString(3));
                    item.setRepaymentOnNonCum(rs.getString(4));

                }
                return item;
            });
            rowMappers.put(partDCumulative, (rs, rowNum) -> {
                AppendixIV85DTO item = new AppendixIV85DTO();
                if (appendixIV85DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setBlaNoCumD(rs.getString(1));
                    item.setAmountCumD(rs.getString(2));
                    item.setDueDateCumD(rs.getString(3));
                    item.setRepaymentOnNonCum(rs.getString(4));

                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_rbi_bond_appivper8_5",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            log.info("Stored procedure raw result: {}", result);

            list = (List<AppendixIV85DTO>) result.get(curValOut);
            List<AppendixIV85DTO> partANonCumList = (List<AppendixIV85DTO>) result.get(partANonCumulative);
            List<AppendixIV85DTO> partACumList = (List<AppendixIV85DTO>) result.get(partACumulative);
            List<AppendixIV85DTO> partBNonCumList = (List<AppendixIV85DTO>) result.get(partBNonCumulative);
            List<AppendixIV85DTO> partBCumList = (List<AppendixIV85DTO>) result.get(partBCumulative);
            List<AppendixIV85DTO> partCNonCumList = (List<AppendixIV85DTO>) result.get(partCNonCumulative);
            List<AppendixIV85DTO> partCCumList = (List<AppendixIV85DTO>) result.get(partCCumulative);
            List<AppendixIV85DTO> partDNonCumList = (List<AppendixIV85DTO>) result.get(partDNonCumulative);
            List<AppendixIV85DTO> partDCumList = (List<AppendixIV85DTO>) result.get(partDCumulative);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list) && (
                    CollectionUtils.isEmpty(partANonCumList) &&
                            CollectionUtils.isEmpty(partACumList) &&
                            CollectionUtils.isEmpty(partBNonCumList) &&
                            CollectionUtils.isEmpty(partBCumList) &&
                            CollectionUtils.isEmpty(partCNonCumList) &&
                            CollectionUtils.isEmpty(partCCumList) &&
                            CollectionUtils.isEmpty(partDNonCumList) &&
                            CollectionUtils.isEmpty(partDCumList))
            ) {
                log.info("No Data Found");
                resultMsg = CommonConstants.NDF;
            }  else {
                resultMap.put("list", list);
                resultMap.put("partANonCumList", partANonCumList);
                resultMap.put("partACumList", partACumList);
                resultMap.put("partBNonCumList", partBNonCumList);
                resultMap.put("partBCumList", partBCumList);
                resultMap.put("partCNonCumList", partCNonCumList);
                resultMap.put("partCCumList", partCCumList);
                resultMap.put("partDNonCumList", partDNonCumList);
                resultMap.put("partDCumList", partDCumList);
                responseDataDTO.setResult(resultMap);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAIRDetails(AirDTO airDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<AirDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";

        // IN PARAMS
        String userID = "p_user_id_in";
        String solID = "p_user_sol_id_in";
        String schemeCode = "p_sch_code_in";
        String flag = "p_flag_in";
        String financialYear = "p_fin_year_in";

        // OUT PARAMS
        String curValOut = "p_cur_val_out";
        String msgOut = "p_msg_out";
        String msgDescOut = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, airDTO.getSchemeCode());
            inParams.put(flag, airDTO.getFlag());
            inParams.put(financialYear, airDTO.getFinancialYear());


            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(financialYear, Types.VARCHAR)

            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                AirDTO item = new AirDTO();
                if (airDTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                } else if (airDTO.getFlag().equalsIgnoreCase("FY")) {
                    item.setFinancialYear(rs.getString(1));
                } else if (airDTO.getFlag().equalsIgnoreCase("T")) {
                    item.setOriginalReportSerialNumber(rs.getString(1));
                    item.setPersonName(rs.getString(2));
                    item.setPersonType(rs.getString(3));
                    item.setCustomerIdentity(rs.getString(4));
                    item.setGender(rs.getString(5));
                    item.setFatherName(rs.getString(6));
                    item.setPanNumber(rs.getString(7));
                    item.setAadhaarNumber(rs.getString(8));
                    item.setForm60Acknowledgment(rs.getString(9));
                    item.setIdentificationType(rs.getString(10));
                    item.setIdentificationNumber(rs.getString(11));
                    item.setDateOfBirth(rs.getString(12));
                    item.setNationality(rs.getString(13));
                    item.setAddressType(rs.getString(14));
                    item.setFixAddress(rs.getString(15));
                    item.setCity(rs.getString(16));
                    item.setPostcode(rs.getString(17));
                    item.setState(rs.getString(18));
                    item.setCountryCode(rs.getString(19));
                    item.setBusiness(rs.getString(20));
                    item.setPrimarySTDCode(rs.getString(21));
                    item.setPrimaryPhoneNo(rs.getString(22));
                    item.setPrimaryMobileNo(rs.getString(23));
                    item.setSecondarySTDCode(rs.getString(24));
                    item.setSecondaryPhoneNo(rs.getString(25));
                    item.setSecondaryMobileNo(rs.getString(26));
                    item.setEmail(rs.getString(27));
                    item.setRemarks(rs.getString(28));
                    item.setProductType(rs.getString(29));
                    item.setGrossAmountReceivedPerson(rs.getString(30));
                    item.setGrossAmountReceivedPersonInCash(rs.getString(31));
                    item.setGrossAmountPaidToPerson(rs.getString(32));
                    item.setTransactionSummaryRemarks(rs.getString(33));
                    item.setProductIdentifier(rs.getString(34));
                    item.setLastDateOfTransaction(rs.getString(35));
                    item.setAmountReceivedFromPerson(rs.getString(36));
                    item.setCashAmountReceivedFromPerson(rs.getString(37));
                    item.setAmountPaidToPerson(rs.getString(38));
                    item.setRelatedAccountNumber(rs.getString(39));
                    item.setRelatedInstituteName(rs.getString(40));
                    item.setRelatedInstituteRefNumber(rs.getString(41));
                    item.setFinancialTransactionDtlsRemarks(rs.getString(42));
                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_SCROLL_AIR",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<AirDTO>) result.get(curValOut);

            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAppendixIV805Details(AppendixIV805DTO appendixIV805DTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<AppendixIV805DTO> list = Collections.emptyList();
        List<AppendixIV805DTO> partANonCumulativeList = Collections.emptyList();
        List<AppendixIV805DTO> partACumulativeList = Collections.emptyList();

        List<AppendixIV805DTO> partBNonCumulativeList = Collections.emptyList();
        List<AppendixIV805DTO> partBCumulativeList = Collections.emptyList();

        List<AppendixIV805DTO> partCNonCumulativeList = Collections.emptyList();
        List<AppendixIV805DTO> partCCumulativeList = Collections.emptyList();

        List<AppendixIV805DTO> partDNonCumulativeList = Collections.emptyList();
        List<AppendixIV805DTO> partDCumulativeList = Collections.emptyList();

        List<AppendixIV805DTO> partTotalAmtAcc = Collections.emptyList();

        String resultMsg = "SUCCESS";
        String P_CUR_VAL_OUT = "p_cur_val_out";
        String P_ACC_OPE_NON_CUM_OUT = "p_acc_ope_non_cum_out";
        String P_ACC_OPE_CUM_OUT = "p_acc_ope_cum_out";
        String P_TRAN_INF_NON_CUM_OUT = "p_tran_inf_non_cum_out";
        String P_TRAN_INF_CUM_OUT = "p_tran_inf_cum_out";
        String P_TRAN_OUTF_NON_CUM_OUT = "p_tran_outf_non_cum_out";
        String P_TRAN_OUTF_CUM_OUT = "p_tran_outf_cum_out";
        String P_REPAY_NON_CUM_OUT = "p_repay_non_cum_out";
        String P_REPAY_CUM_OUT = "p_repay_cum_out";
        String P_PARTICULARS_OUT = "p_particulars_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
            inParams.put(schemeCode, appendixIV805DTO.getSchemeCode());
            inParams.put(flag, appendixIV805DTO.getFlag());
            inParams.put(fromDate, appendixIV805DTO.getFromDate());
            inParams.put(toDate, appendixIV805DTO.getToDate());


            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR)

            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(P_CUR_VAL_OUT, OracleTypes.CURSOR),
                    new SqlOutParameter(P_ACC_OPE_NON_CUM_OUT, OracleTypes.CURSOR),
                    new SqlOutParameter(P_ACC_OPE_CUM_OUT, OracleTypes.CURSOR),
                    new SqlOutParameter(P_TRAN_INF_NON_CUM_OUT, OracleTypes.CURSOR),
                    new SqlOutParameter(P_TRAN_INF_CUM_OUT, OracleTypes.CURSOR),
                    new SqlOutParameter(P_TRAN_OUTF_NON_CUM_OUT, OracleTypes.CURSOR),
                    new SqlOutParameter(P_TRAN_OUTF_CUM_OUT, OracleTypes.CURSOR),
                    new SqlOutParameter(P_REPAY_NON_CUM_OUT, OracleTypes.CURSOR),
                    new SqlOutParameter(P_REPAY_CUM_OUT, OracleTypes.CURSOR),
                    new SqlOutParameter(P_PARTICULARS_OUT, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(P_CUR_VAL_OUT, (rs, rowNum) -> {
                AppendixIV805DTO item = new AppendixIV805DTO();
                if (appendixIV805DTO.getFlag().equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));
                }
                return item;
            });
//            Part A Non Cumulative
            rowMappers.put(P_ACC_OPE_NON_CUM_OUT, (rs, rowNum) -> {
                AppendixIV805DTO item = new AppendixIV805DTO();
                if (appendixIV805DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPartANcBla(rs.getString(1));
                    item.setPartANcAmount(rs.getString(2));
                    item.setPartANcCasAdviseNo(rs.getString(3));
                    item.setPartANcCasDate(rs.getString(4));
                    item.setPartANcDueDate(rs.getString(5));
                    item.setSch(rs.getString(6));
                }
                return item;
            });
//          Part A Cumulative
            rowMappers.put(P_ACC_OPE_CUM_OUT, (rs, rowNum) -> {
                AppendixIV805DTO item = new AppendixIV805DTO();
                if (appendixIV805DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPartACBla(rs.getString(1));
                    item.setPartACAmount(rs.getString(2));
                    item.setPartACCasAdviseNo(rs.getString(3));
                    item.setPartACCasDate(rs.getString(4));
                    item.setPartACDueDate(rs.getString(5));
                }
                return item;
            });

//            Part B Non Cumulative
            rowMappers.put(P_TRAN_INF_NON_CUM_OUT, (rs, rowNum) -> {
                AppendixIV805DTO item = new AppendixIV805DTO();
                if (appendixIV805DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPartBNcBla(rs.getString(1));
                    item.setPartBNcAmount(rs.getString(2));
                    item.setPartBNcDueDate(rs.getString(3));
                }
                return item;
            });
//            Part B Cumulative
            rowMappers.put(P_TRAN_INF_CUM_OUT, (rs, rowNum) -> {
                AppendixIV805DTO item = new AppendixIV805DTO();
                if (appendixIV805DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPartBCBla(rs.getString(1));
                    item.setPartBCAmount(rs.getString(2));
                    item.setPartBCDueDate(rs.getString(3));
                    item.setPartBCTransferorBranchName(rs.getString(4));
                }
                return item;
            });
//            Part C Non Cumulative
            rowMappers.put(P_TRAN_OUTF_NON_CUM_OUT, (rs, rowNum) -> {
                AppendixIV805DTO item = new AppendixIV805DTO();
                if (appendixIV805DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPartCNcBla(rs.getString(1));
                    item.setPartCNcAmount(rs.getString(2));
                    item.setPartCNcDueDate(rs.getString(3));
                }
                return item;
            });
//          Part C Cumulative
            rowMappers.put(P_TRAN_OUTF_CUM_OUT, (rs, rowNum) -> {
                AppendixIV805DTO item = new AppendixIV805DTO();
                if (appendixIV805DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPartCCBla(rs.getString(1));
                    item.setPartCCAmount(rs.getString(2));
                    item.setPartCCDueDate(rs.getString(3));
                    item.setPartCCTransferorBranchName(rs.getString(4));
                }
                return item;
            });

//          Part D Non Cumulative
            rowMappers.put(P_REPAY_NON_CUM_OUT, (rs, rowNum) -> {
                AppendixIV805DTO item = new AppendixIV805DTO();
                if (appendixIV805DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPartDNcBla(rs.getString(1));
                    item.setPartDNcAmount(rs.getString(2));
                    item.setPartDNcDueDate(rs.getString(3));
                    item.setPartDNcRepaymentOn(rs.getString(4));
                }
                return item;
            });
//          Part D Cumulative
            rowMappers.put(P_REPAY_CUM_OUT, (rs, rowNum) -> {
                AppendixIV805DTO item = new AppendixIV805DTO();
                if (appendixIV805DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setPartDCBla(rs.getString(1));
                    item.setPartDCAmount(rs.getString(2));
                    item.setPartDCDueDate(rs.getString(3));
                    item.setPartDCRepaymentOn(rs.getString(4));
                }
                return item;
            });

            rowMappers.put(P_PARTICULARS_OUT, (rs, rowNum) -> {
                AppendixIV805DTO item = new AppendixIV805DTO();
                if (appendixIV805DTO.getFlag().equalsIgnoreCase("D")) {
                    item.setTotalaPartiAccounts(rs.getString(1));
                    item.setTotalaPartiAmount(rs.getString(2));


                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_scroll_appendix_8_05",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<AppendixIV805DTO>) result.get(curValOut);

            partANonCumulativeList = (List<AppendixIV805DTO>) result.get(P_ACC_OPE_NON_CUM_OUT);
            partACumulativeList = (List<AppendixIV805DTO>) result.get(P_ACC_OPE_CUM_OUT);

            partBNonCumulativeList = (List<AppendixIV805DTO>) result.get(P_TRAN_INF_NON_CUM_OUT);
            partBCumulativeList = (List<AppendixIV805DTO>) result.get(P_TRAN_INF_CUM_OUT);

            partCNonCumulativeList = (List<AppendixIV805DTO>) result.get(P_TRAN_OUTF_NON_CUM_OUT);
            partCCumulativeList = (List<AppendixIV805DTO>) result.get(P_TRAN_OUTF_CUM_OUT);

            partDNonCumulativeList = (List<AppendixIV805DTO>) result.get(P_REPAY_NON_CUM_OUT);
            partDCumulativeList = (List<AppendixIV805DTO>) result.get(P_REPAY_CUM_OUT);
            partTotalAmtAcc= (List<AppendixIV805DTO>) result.get(P_PARTICULARS_OUT);


            long endTime = System.currentTimeMillis();
            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list) &&
                    (CollectionUtils.isEmpty(partANonCumulativeList) && CollectionUtils.isEmpty(partACumulativeList) &&
                            CollectionUtils.isEmpty(partBNonCumulativeList) && CollectionUtils.isEmpty(partBCumulativeList) &&
                            CollectionUtils.isEmpty(partCNonCumulativeList) && CollectionUtils.isEmpty(partCCumulativeList) &&
                            CollectionUtils.isEmpty(partDNonCumulativeList) && CollectionUtils.isEmpty(partDCumulativeList)&&CollectionUtils.isEmpty(partTotalAmtAcc))) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                resultMap.put("partANonCumulativeList", partANonCumulativeList);
                resultMap.put("partACumulativeList", partACumulativeList);
                resultMap.put("partBNonCumulativeList", partBNonCumulativeList);
                resultMap.put("partBCumulativeList", partBCumulativeList);
                resultMap.put("partCNonCumulativeList", partCNonCumulativeList);
                resultMap.put("partCCumulativeList", partCCumulativeList);
                resultMap.put("partDNonCumulativeList", partDNonCumulativeList);
                resultMap.put("partDCumulativeList", partDCumulativeList);
                resultMap.put("partTotalAmtAcc", partTotalAmtAcc);
                responseDataDTO.setResult(resultMap);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getAppendixDetails(AppendixFourMarchDTO appendixFourMarchDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_APP75MAR2024.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, appendixFourMarchDTO.getFlag());
                stmt.setString(index++, appendixFourMarchDTO.getSchemeCode());
                stmt.setString(index++, appendixFourMarchDTO.getFromDate());
                stmt.setString(index++, appendixFourMarchDTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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

                List<AppendixFourMarchDTO> list = new ArrayList<>();
                List<AppendixFourMarchDTO> partANonCumulativeList = new ArrayList<>();
                List<AppendixFourMarchDTO> partACumulativeList = new ArrayList<>();


                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (appendixFourMarchDTO.getFlag().equalsIgnoreCase("S")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            AppendixFourMarchDTO item = null;
                            while (resultSet.next()) {
                                item = new AppendixFourMarchDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));
                                list.add(item);
                            }
                        }
                        resultMap.put("schemeList", list);
                        responseDataDTO.setResult(resultMap);
                    }
                    if (appendixFourMarchDTO.getFlag().equalsIgnoreCase("T")) {
                        try (ResultSet rs1 = (ResultSet) stmt.getObject(8)) {
                            while (rs1 != null && rs1.next()) {
                                AppendixFourMarchDTO item = new AppendixFourMarchDTO();

                                item.setPartANCblano(rs1.getString(1));
                                item.setPartANCamount(rs1.getString(2));
                                item.setPartANCcasAdvNoCasDate(rs1.getString(3));
                                item.setPartANCDueDate(rs1.getString(4));
                                partANonCumulativeList.add(item);
                            }
                            resultMap.put("partANonCumulativeList", partANonCumulativeList);
                        }

                        try (ResultSet rs2 = (ResultSet) stmt.getObject(9)) {
                            while (rs2 != null && rs2.next()) {
                                AppendixFourMarchDTO item = new AppendixFourMarchDTO();

                                item.setPartACblano(rs2.getString(1));
                                item.setPartACamount(rs2.getString(2));
                                item.setPartACcasAdvNoCasDate(rs2.getString(3));
                                item.setPartACDueDate(rs2.getString(4));
                                partACumulativeList.add(item);
                            }
                            resultMap.put("partACumulativeList", partACumulativeList);
                        }
                    }
                    responseDataDTO.setResult(resultMap);
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



    public ResponseDataDTO getAppendix65Details(AppendixFourMar65DTO apendixFourMar65DTO){



        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap= new HashMap<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_APP65MAR2024.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, apendixFourMar65DTO.getFlag());
                stmt.setString(index++, apendixFourMar65DTO.getSchemeCode());
                stmt.setString(index++, apendixFourMar65DTO.getFromDate());
                stmt.setString(index++, apendixFourMar65DTO.getToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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

                List<AppendixFourMar65DTO> list = new ArrayList<>();

                List<AppendixFourMar65DTO> partAnonCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partACumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partBNonCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partBCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partCNonCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partCCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partDNonCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partDCumulativeList = new ArrayList<>();

                List<AppendixFourMar65DTO> partAallnonCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partAallCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partBallNonCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partBallCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partCallNonCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partCallCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partDallNonCumulativeList = new ArrayList<>();
                List<AppendixFourMar65DTO> partDallCumulativeList = new ArrayList<>();









                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (apendixFourMar65DTO.getFlag().equalsIgnoreCase("S")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            AppendixFourMar65DTO item = null;
                            while (resultSet.next()) {
                                item = new AppendixFourMar65DTO();

                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));
                                list.add(item);
                            }
                        }
                        resultMap.put("schemeList", list);
                        responseDataDTO.setResult(resultMap);
                    }
                    if (apendixFourMar65DTO.getFlag().equalsIgnoreCase("T")) {
                        ResultSet rs1 = (ResultSet) stmt.getObject(8);
                            while (rs1 != null && rs1.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();

                                item.setBlaNo(rs1.getString(1));
                                item.setAmount(rs1.getString(2));
                                item.setCas(rs1.getString(3));
                                item.setDueDate(rs1.getString(4));
                                partAnonCumulativeList.add(item);
                            }
                       //     resultMap.put("partANonCumulativeList", nonCumulativeCumulativeList);


                        ResultSet rs2 = (ResultSet) stmt.getObject(9);
                            while (rs2 != null && rs2.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();

                                item.setCumBlaNo(rs2.getString(1));
                                item.setCumAmount(rs2.getString(2));
                                item.setCumCas(rs2.getString(3));
                                item.setCumDueDate(rs2.getString(4));
                                partACumulativeList.add(item);




                        }


                        ResultSet rs3 = (ResultSet) stmt.getObject(10);
                            while (rs3 != null && rs3.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();

                                item.setBlaNo(rs3.getString(1));
                                item.setAmount(rs3.getString(2));
                                item.setDueDate(rs3.getString(3));
                                item.setTransferBranchName(rs3.getString(4));

                                partBNonCumulativeList.add(item);

                        }


                        ResultSet rs4 = (ResultSet) stmt.getObject(11);
                            while (rs4 != null && rs2.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();

                                item.setCumBlaNo(rs4.getString(1));
                                item.setCumAmount(rs4.getString(2));
                                item.setCumDueDate(rs4.getString(3));
                                item.setCumTransferBranch(rs4.getString(4));
                                partBCumulativeList.add(item);
                            }


                        ResultSet rs5 = (ResultSet) stmt.getObject(12);
                            while (rs5 != null && rs5.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();

                                item.setBlaNo(rs5.getString(1));
                                item.setAmount(rs5.getString(2));
                                item.setDueDate(rs5.getString(3));
                                item.setTransferBranchName(rs5.getString(4));
                                partCNonCumulativeList.add(item);
                            }



                        ResultSet rs6 = (ResultSet) stmt.getObject(13);
                            while (rs6 != null && rs6.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();

                                item.setCumBlaNo(rs6.getString(1));
                                item.setCumAmount(rs6.getString(2));
                                item.setCumDueDate(rs6.getString(3));
                                item.setCumTransferBranch(rs6.getString(4));
                                partCCumulativeList.add(item);

                        }

                        ResultSet rs7 = (ResultSet) stmt.getObject(14);
                            while (rs7 != null && rs7.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();

                                item.setBlaNo(rs7.getString(1));
                                item.setAmount(rs7.getString(2));
                                item.setDueDate(rs7.getString(3));
                                item.setRepaymentOn(rs7.getString(4));
                                partDNonCumulativeList.add(item);
                            }



                        ResultSet rs8 = (ResultSet) stmt.getObject(15);
                            while (rs8 != null && rs8.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();

                                item.setCumBlaNo(rs8.getString(1));
                                item.setCumAmount(rs8.getString(2));
                                item.setCumDueDate(rs8.getString(3));
                                item.setCumRepayMentOn(rs8.getString(4));
                                partDCumulativeList.add(item);
                            }






                            ResultSet rs9 = (ResultSet) stmt.getObject(16);
                            while (rs9 != null && rs9.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();


                                item.setCount(rs9.getString(1));
                                item.setSum(rs9.getString(2));

                                partAallnonCumulativeList.add(item);
                            }

                            ResultSet rs10 = (ResultSet) stmt.getObject(17);
                            while (rs10 != null && rs10.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();


                                item.setCumCount(rs10.getString(1));
                                item.setCumSum(rs10.getString(2));

                                partAallCumulativeList.add(item);
                            }



                            ResultSet rs11 = (ResultSet) stmt.getObject(18);
                            while (rs11 != null && rs11.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();


                                item.setCount(rs11.getString(1));
                                item.setSum(rs11.getString(2));


                                partBallNonCumulativeList.add(item);
                            }



                            ResultSet rs12 = (ResultSet) stmt.getObject(19);
                            while (rs12 != null && rs12.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();




                                item.setCumCount(rs12.getString(1));
                                item.setCumSum(rs12.getString(2));


                                partBallCumulativeList.add(item);
                            }



                            ResultSet rs13 = (ResultSet) stmt.getObject(20);
                            while (rs13 != null && rs13.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();



                                item.setCount(rs13.getString(1));
                                item.setSum(rs13.getString(2));


                                partCallNonCumulativeList.add(item);
                            }




                            ResultSet rs14 = (ResultSet) stmt.getObject(21);
                            while (rs14 != null && rs14.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();


                                item.setCumCount(rs14.getString(1));
                                item.setCumSum(rs14.getString(2));

                                partCallCumulativeList.add(item);
                            }



                            ResultSet rs15 = (ResultSet) stmt.getObject(22);
                            while (rs15 != null && rs15.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();



                                item.setCount(rs15.getString(1));
                                item.setSum(rs15.getString(2));


                                partDallNonCumulativeList.add(item);
                            }



                            ResultSet rs16 = (ResultSet) stmt.getObject(23);
                            while (rs16 != null && rs16.next()) {
                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();


                                item.setCumCount(rs16.getString(1));
                                item.setCumSum(rs16.getString(2));

                                partDallCumulativeList.add(item);
                            }




//                            ResultSet rs17 = (ResultSet) stmt.getObject(18);
//                            while (rs17 != null && rs17.next()) {
//                                AppendixFourMar65DTO item = new AppendixFourMar65DTO();
//
//                                item.setSum(rs17.getString(1));
//                                item.setCount(rs17.getString(2));
//
//                                nonCumulativeCumulativeList.add(item);
//                            }









                        resultMap.put("partANonCumulativeList", partAnonCumulativeList);
                        resultMap.put("partACumulativeList", partACumulativeList);
                        resultMap.put("partBNonCumulativeList", partBNonCumulativeList);
                        resultMap.put("partBCumulativeList", partBCumulativeList);
                        resultMap.put("partCNonCumulativeList", partCNonCumulativeList);
                        resultMap.put("partCCumulativeList", partCCumulativeList);
                        resultMap.put("partDNonCumulativeList", partDNonCumulativeList);
                        resultMap.put("partDCumulativeList", partDCumulativeList);






                        partAnonCumulativeList.addAll(partACumulativeList);
                        partBNonCumulativeList.addAll(partBCumulativeList);
                        partCNonCumulativeList.addAll(partCCumulativeList);
                        partDNonCumulativeList.addAll(partDCumulativeList);

                        resultMap.put("partAallnonCumulativeList", partAallnonCumulativeList);
                        resultMap.put("partAallCumulativeList", partAallCumulativeList);
                        resultMap.put("partBallNonCumulativeList", partBallNonCumulativeList);
                        resultMap.put("partBallCumulativeList", partBallCumulativeList);
                        resultMap.put("partCallNonCumulativeList", partCallNonCumulativeList);
                        resultMap.put("partCallCumulativeList", partCallCumulativeList);
                        resultMap.put("partDallNonCumulativeList", partDallNonCumulativeList);
                        resultMap.put("partDallCumulativeList", partDallCumulativeList);




                    }








                responseDataDTO.setResult(resultMap);
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
    public ResponseDataDTO getEfillingDetails(EfillingDTO efillingDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<EfillingDTO> list = Collections.emptyList();
        String resultMsg = "SUCCESS";
        String totalRecords = null;

        // IN PARAMS
        String userID = "p_user_id_in";
        String solID = "p_user_sol_id_in";
//        String schemeCode = "p_sch_code_in";
        String flag = "p_flag_in";
        String fromDate= "p_from_date_in";
        String toDate= "p_to_date_in";
       String pageNo="p_page_no_in";
        String pagesize="p_page_size_in" ;
        String search= "p_search_value_in" ;

        // OUT PARAMS
        String total="p_total_records_out";
        String curValOut = "p_cur_val_out";
        String msgOut = "p_msg_out";
        String msgDescOut = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();
            inParams.put(userID, UserAccountUtils.getCurrentUsername());
            inParams.put(solID, UserAccountUtils.getCurrentSolId());
//            inParams.put(schemeCode, efillingDTO.getSchemeCode());
            inParams.put(flag, efillingDTO.getFlag());
            inParams.put(fromDate, efillingDTO.getFromDate());
            inParams.put(toDate, efillingDTO.getToDate());
            inParams.put(pageNo, efillingDTO.getPageNo());
            inParams.put(pagesize, efillingDTO.getPageSize());
            inParams.put(search, efillingDTO.getSearch());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(userID, Types.VARCHAR),
                    new SqlParameter(solID, Types.VARCHAR),
//                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(pageNo, Types.VARCHAR),
                    new SqlParameter(pagesize, Types.VARCHAR),
                    new SqlParameter(search, Types.VARCHAR),

            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(total, OracleTypes.VARCHAR),
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            long startTime = System.currentTimeMillis();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();
            rowMappers.put(curValOut, (rs, rowNum) -> {
                EfillingDTO item = new EfillingDTO();
//                if (efillingDTO.getFlag().equalsIgnoreCase("S")) {
//                    item.setSchemeCode(rs.getString(1));
//                    item.setSchemeName(rs.getString(2));
//                    item.setSchemeStatus(rs.getString(3));
//                }
                if (efillingDTO.getFlag().equalsIgnoreCase("D")) {
                    item.setAssesseeName(rs.getString(1));
                    item.setAssesseePan(rs.getString(2));
                    item.setUniqueIdNum(rs.getString(3));
                    item.setDOB(rs.getString(4));
                    item.setCountry(rs.getString(5));
                    item.setPleaseSpecify(rs.getString(6));
                    item.setFlatDoorBlockNum(rs.getString(7));
                    item.setRoadStreetLane(rs.getString(8));
                    item.setPin(rs.getString(9));
                    item.setPostOffice(rs.getString(10));
                    item.setAreaLocality(rs.getString(11));
                    item.setTownCityDistrict(rs.getString(12));
                    item.setState(rs.getString(13));
                    item.setEmail(rs.getString(14));
                    item.setMobileNum(rs.getString(15));
                    item.setEstimatedIncomeForWhichDeclarationIsMade(rs.getString(16 ));
                    item.setEstimatedTotalIncomeOfThePreviousYear(rs.getString(17));
                    item.setAggregateAmountOfIncomeForm15GFilled(rs.getString(18));
                    item.setDeclarationReceivedDate(rs.getString(19));

                }
                return item;
            });
            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "SP_GBM_RBI_BOND_SCROLL_EFILLING",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            list = (List<EfillingDTO>) result.get(curValOut);
            totalRecords = result.get(total) != null
                    ? result.get(total).toString()
                    : "0";
            long endTime = System.currentTimeMillis();

            log.info("Time taken to execute stored procedure: {} minutes", ((endTime - startTime) / 1000.0 / 60.0));

            if (CollectionUtils.isEmpty(list)) {
                log.info("No Data Found found");
                resultMsg = CommonConstants.NDF;
            } else {
                resultMap.put("list", list);
                responseDataDTO.setResult(resultMap);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setTotal(Double.parseDouble(totalRecords));

        return responseDataDTO;
    }

    public ResponseDataDTO getsubScriptionMandateDetails(SubscriptionMandateDTO subscriptionMandateDTO) {


        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";


        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_SUBSCRIPTION_MANDATE.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, subscriptionMandateDTO.getSchemeCode());
                stmt.setString(index++, subscriptionMandateDTO.getFlag());
                stmt.setString(index++, subscriptionMandateDTO.getFromDate());
                stmt.setString(index++, subscriptionMandateDTO.getToDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(8);


                    if (subscriptionMandateDTO.getFlag() != null && subscriptionMandateDTO.getFlag().equalsIgnoreCase("C")) {
                        if (resultSet != null) {
                            List<SubscriptionMandateDTO> list = new ArrayList<>();
                            SubscriptionMandateDTO item = null;
                            while (resultSet.next()) {
                                item = new SubscriptionMandateDTO();

                                item.setSchemeCode(resultSet.getString(1));
                                item.setSchemeName(resultSet.getString(2));
                                item.setSchemeStatus(resultSet.getString(3));

                                list.add(item);

                            }
                            responseDataDTO.setResult(list);
                        }


                    }

                    if (subscriptionMandateDTO.getFlag() != null && subscriptionMandateDTO.getFlag().equalsIgnoreCase("D")) {
                        if (resultSet != null) {
                            List<SubscriptionMandateDTO> list = new ArrayList<>();
                            SubscriptionMandateDTO item = null;
                            while (resultSet.next()) {
                                item = new SubscriptionMandateDTO();

                                item.setCumAmount(resultSet.getString(1));
                                item.setCumTotalTran(resultSet.getString(2));
                                item.setCumNonCumAmount(resultSet.getString(3));
                                item.setNonCumTotalTran(resultSet.getString(4));

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

    public ResponseDataDTO getBrokerageClaimHandlingCommissionDetails(BrokerageClaimAndHandlingCommissionDTO brokerageClaimDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet schemeResultSet = null;
        String resultMsg = "";

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                int index = 1;

                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BROKERAGE_HANDLING_SCROLL.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, brokerageClaimDTO.getSchemeCode());
                stmt.setString(index++, brokerageClaimDTO.getPidId());
                stmt.setString(index++, brokerageClaimDTO.getFromDate());
                stmt.setString(index++, brokerageClaimDTO.getToDate());
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
                    if (brokerageClaimDTO.getSchemeCode() == null || brokerageClaimDTO.getSchemeCode().trim().isEmpty()) {
                        List<Map<String, String>> schemeList = new ArrayList<>();
                        schemeResultSet = (ResultSet) stmt.getObject(7);
                        if (schemeResultSet != null) {
                            while (schemeResultSet.next()) {
                                Map<String, String> schemeMap = new HashMap<>();
                                schemeMap.put("schemeCode", schemeResultSet.getString(1));
                                schemeMap.put("schemeDescription", schemeResultSet.getString(2));
                                schemeMap.put("schemeStatus", schemeResultSet.getString(3));
                                schemeList.add(schemeMap);
                            }
                        }
                        responseDataDTO.setResult(schemeList);
                    } else {
                        Map<String, Object> resultMap = new HashMap<>();
                        resultMap.put("curValOut", readCursorAsMapList((ResultSet) stmt.getObject(7)));
                        resultMap.put("curValOut2", readCursorAsMapList((ResultSet) stmt.getObject(8)));
                        resultMap.put("curValOut3", readCursorAsMapList((ResultSet) stmt.getObject(9)));
                        resultMap.put("curValOut4", readCursorAsMapList((ResultSet) stmt.getObject(10)));
                        resultMap.put("curValOut5", readCursorAsMapList((ResultSet) stmt.getObject(11)));
                        resultMap.put("pidId", brokerageClaimDTO.getPidId());

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

    public ResponseDataDTO getSchemeWiseOutstandingBalanceDetails(SchemeWiseOutstandingBalanceDTO schemeWiseOutstandingBalanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet schemeResultSet = null;
        String resultMsg = "";

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
       /* try {
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                int index = 1;

                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BROKERAGE_HANDLING_SCROLL.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                 stmt.setString(index++, schemeWiseOutstandingBalanceDTO.getSchemeCode());
                stmt.setString(index++, schemeWiseOutstandingBalanceDTO.getPidId());
                stmt.setString(index++, schemeWiseOutstandingBalanceDTO.getFromDate());
                stmt.setString(index++, schemeWiseOutstandingBalanceDTO.getToDate());
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
                    if (schemeWiseOutstandingBalanceDTO.getSchemeCode() == null || schemeWiseOutstandingBalanceDTO.getSchemeCode().trim().isEmpty()) {
                        List<Map<String, String>> schemeList = new ArrayList<>();
                        schemeResultSet = (ResultSet) stmt.getObject(7);
                        if (schemeResultSet != null) {
                            while (schemeResultSet.next()) {
                                Map<String, String> schemeMap = new HashMap<>();
                                schemeMap.put("schemeCode", schemeResultSet.getString(1));
                                schemeMap.put("schemeDescription", schemeResultSet.getString(2));
                                schemeMap.put("schemeStatus", schemeResultSet.getString(3));
                                schemeList.add(schemeMap);
                            }
                        }
                        responseDataDTO.setResult(schemeList);
                    } else {
                        Map<String, Object> resultMap = new HashMap<>();
                        resultMap.put("curValOut", readCursorAsMapList((ResultSet) stmt.getObject(7)));
                        resultMap.put("curValOut2", readCursorAsMapList((ResultSet) stmt.getObject(8)));
                        resultMap.put("curValOut3", readCursorAsMapList((ResultSet) stmt.getObject(9)));
                        resultMap.put("curValOut4", readCursorAsMapList((ResultSet) stmt.getObject(10)));
                        resultMap.put("curValOut5", readCursorAsMapList((ResultSet) stmt.getObject(11)));
                        resultMap.put("pidId", brokerageClaimDTO.getPidId());

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
        }*/

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getHalfYearlyInterestScrollDetails(HalfYearlyInterestScrollDTO halfYearlyInterestScrollDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, Object> resultMap = new HashMap<>();
        List<HalfYearlyInterestScrollDTO> list = Collections.emptyList();
        String resultMsg = CommonConstants.SUCCESS_CONST;

        String flag = "p_flag_in";
        String schemeCode = "p_sch_code_in";
        String fromDate = "p_from_date_in";
        String toDate = "p_to_date_in";
        String financialYear = "p_financial_year_in";
        String halfYearlyMonth = "p_half_yearly_month_in";

        String curValOut = "p_cur_val_out";
        String msgOut = "p_msg_out";
        String msgDescOut = "p_msg_desc_out";

        try {
            Map<String, Object> inParams = new HashMap<>();

            inParams.put(flag, halfYearlyInterestScrollDTO.getFlag());
            inParams.put(schemeCode, halfYearlyInterestScrollDTO.getSchemeCode());
            inParams.put(fromDate, halfYearlyInterestScrollDTO.getFromDate());
            inParams.put(toDate, halfYearlyInterestScrollDTO.getToDate());
            inParams.put(financialYear, halfYearlyInterestScrollDTO.getFinancialYear());
            inParams.put(halfYearlyMonth, halfYearlyInterestScrollDTO.getHalfYearMonth());

            SqlParameter[] sqlParameters = {
                    new SqlParameter(flag, Types.VARCHAR),
                    new SqlParameter(schemeCode, Types.VARCHAR),
                    new SqlParameter(fromDate, Types.VARCHAR),
                    new SqlParameter(toDate, Types.VARCHAR),
                    new SqlParameter(financialYear, Types.VARCHAR),
                    new SqlParameter(halfYearlyMonth, Types.VARCHAR),
            };
            SqlOutParameter[] sqlOutParameters = {
                    new SqlOutParameter(curValOut, OracleTypes.CURSOR),
                    new SqlOutParameter(msgOut, OracleTypes.VARCHAR),
                    new SqlOutParameter(msgDescOut, OracleTypes.VARCHAR)
            };

            final String requestFlag = halfYearlyInterestScrollDTO.getFlag();
            Map<String, RowMapper<?>> rowMappers = new HashMap<>();

            rowMappers.put(curValOut, (rs, rowNum) -> {
                HalfYearlyInterestScrollDTO item = new HalfYearlyInterestScrollDTO();

                if (requestFlag != null && requestFlag.equalsIgnoreCase("S")) {
                    item.setSchemeCode(rs.getString(1));
                    item.setSchemeName(rs.getString(2));
                    item.setSchemeStatus(rs.getString(3));

                } else if (requestFlag != null && requestFlag.equalsIgnoreCase("A")) {
                    item.setTotalInterestAmount(rs.getString(1));
                    item.setBreakUpDetails(rs.getString(2));
                    item.setBreakUpAmount(rs.getString(3));
                    item.setBreakUpPaymentDate(rs.getString(4));

                }
                return item;
            });

            Map<String, Object> result = jDBCTemplateUtil.executePackageStoredProc(
                    "",
                    "sp_gbm_rbi_bond_half_intscrrep",
                    inParams,
                    sqlParameters,
                    sqlOutParameters,
                    rowMappers);

            Object procMsg = result.get(msgOut);
            if (procMsg != null && "F".equalsIgnoreCase(String.valueOf(procMsg))) {
                Object procDesc = result.get(msgDescOut);
                resultMsg = procDesc != null ? String.valueOf(procDesc) : CommonConstants.NDF;
            } else {
                list = (List<HalfYearlyInterestScrollDTO>) result.get(curValOut);
                if (CollectionUtils.isEmpty(list)) {
                    resultMsg = CommonConstants.NDF;
                } else {
                    resultMap.put("list", list);
                }
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }

        responseDataDTO.setResult(resultMap.isEmpty() ? null : resultMap);
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private String normalizeScrollDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }

        String trimmedDate = dateStr.trim();
        String[] patterns = {"dd/MMM/yyyy", "dd/MM/yyyy", "dd-MMM-yyyy", "dd-MMM-yyyy", "yyyy-MM-dd"};

        for (String pattern : patterns) {
            try {
                SimpleDateFormat parser = new SimpleDateFormat(pattern, Locale.ENGLISH);
                parser.setLenient(false);
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                return formatter.format(parser.parse(trimmedDate)).toUpperCase(Locale.ENGLISH);
            } catch (ParseException ignored) {
                // try next pattern
            }
        }

        return trimmedDate.toUpperCase(Locale.ENGLISH);
    }

    private List<Map<String, Object>> readCursorAsMapList(ResultSet resultSet) throws Exception {
        if (resultSet == null) {
            return Collections.emptyList();
        }
        return DataBaseUtils.getGenericColumnValueFromResultSet(resultSet);
    }
}