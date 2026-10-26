package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.PreMaturityClosureMarkingDAO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.util.JDBCTemplateUtil;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class PreMaturityClosureMarkingDAOImpl implements PreMaturityClosureMarkingDAO {

    private static final Logger log = LoggerFactory.getLogger(PreMaturityClosureMarkingDAOImpl.class);
    private static final String PAN_REGEX =
            "^[A-Z]{5}[0-9]{4}[A-Z]$";

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JDBCTemplateUtil jDBCTemplateUtil;

    @PostConstruct
    public void init() {
        jDBCTemplateUtil = new JDBCTemplateUtil(jdbcTemplate);
    }

    @Override
    public ResponseDataDTO addDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(preMaturityRequestDTO.getSchemeCode() != null) {
                String pan = preMaturityRequestDTO.getPanNo();
                if (pan != null
                        && !pan.trim().isEmpty()
                        && !pan.replaceAll("\\s+", "").toUpperCase().matches(PAN_REGEX)) {
                    responseDataDTO.setMessage("PAN must be valid (e.g., ABCDE1234F)");
                    responseDataDTO.setStatusCode("0");
                    return responseDataDTO;
                }
                String bla = preMaturityRequestDTO.getBlaNo();
                if (bla == null || bla.trim().isEmpty()) {
                    responseDataDTO.setMessage("BLA Number is mandatory");
                    responseDataDTO.setStatusCode("0");
                    return responseDataDTO;
                }

        }
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        BigDecimal totalAmt=BigDecimal.ZERO;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_add_val.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setString(index++,preMaturityRequestDTO.getBondHolderName());
                stmt.setString(index++,preMaturityRequestDTO.getPanNo());
                stmt.setString(index++,preMaturityRequestDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (preMaturityRequestDTO.getSchemeCode() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                            RateCodeMaintenanceDTO item = null;
                            while (resultSet.next()) {
                                item = new RateCodeMaintenanceDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSubCodeDescription(resultSet.getString(2));
                                item.setStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else{
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            PreMaturityListDTO item=null;
                            List<PreMaturityListDTO> list = new ArrayList<>();
                            while (resultSet.next()) {
                                item=new PreMaturityListDTO();
                                item.setPanNo(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setAge(resultSet.getString(3));
                                item.setBlaNo(resultSet.getString(4));
                                item.setModeOfHolding(resultSet.getString(5));
                                item.setSubsId(resultSet.getString(6));
                                item.setAccOpenDate(resultSet.getString(7));
                                item.setMaturityDate(resultSet.getString(8));
                                item.setSubsAmt(resultSet.getString(9));
                                totalAmt=totalAmt.add(new BigDecimal(resultSet.getString(9)));
                                list.add(item);
                            }
                            preMaturityRequestDTO.setPreMaturityList(list);
                            preMaturityRequestDTO.setTotalAmt(String.valueOf(totalAmt));
                            responseDataDTO.setResult(preMaturityRequestDTO);
                        }
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
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO=new BondLedgerAccountMaintenanceDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("P")) {
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_add_pan_tor.getProcName());
                }else{
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_add_bla_tor.getProcName());
                }
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setString(index++,preMaturityRequestDTO.getBondHolderName());
                stmt.setString(index++,preMaturityRequestDTO.getPanNo());
                if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("B")) {
                    stmt.setString(index++, preMaturityRequestDTO.getBlaNo());
                }
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("P")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(6);
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
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
                        }
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
                    }else if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("B")){
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        List<HashMap<String, String>> torchOut = null;
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("panNum", resultSet.getString(1));
                                temp.put("blaNo", resultSet.getString(2));
                                temp.put("bondHldName", resultSet.getString(3));
                                temp.put("modeOfHolding", resultSet.getString(4));
                                torchOut.add(temp);
                            }
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
                        }
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
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
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifyDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(preMaturityRequestDTO.getSchemeCode() != null) {
            String pan = preMaturityRequestDTO.getPanNo();
            if (pan != null
                    && !pan.trim().isEmpty()
                    && !pan.replaceAll("\\s+", "").toUpperCase().matches(PAN_REGEX)) {
                responseDataDTO.setMessage("PAN must be valid (e.g., ABCDE1234F)");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String bla = preMaturityRequestDTO.getBlaNo();
            if (bla == null || bla.trim().isEmpty()) {
                responseDataDTO.setMessage("BLA Number is mandatory");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }

        }
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        BigDecimal totalAmt=BigDecimal.ZERO;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_mod_sel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setString(index++,preMaturityRequestDTO.getBondHolderName());
                stmt.setString(index++,preMaturityRequestDTO.getPanNo());
                stmt.setString(index++,preMaturityRequestDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (preMaturityRequestDTO.getSchemeCode() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                            RateCodeMaintenanceDTO item = null;
                            while (resultSet.next()) {
                                item = new RateCodeMaintenanceDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSubCodeDescription(resultSet.getString(2));
                                item.setStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            PreMaturityListDTO item=null;
                            List<PreMaturityListDTO> list = new ArrayList<>();
                            while (resultSet.next()) {
                                item=new PreMaturityListDTO();
                                item.setPanNo(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setAge(resultSet.getString(3));
                                item.setBlaNo(resultSet.getString(4));
                                item.setModeOfHolding(resultSet.getString(5));
                                item.setSubsId(resultSet.getString(6));
                                item.setAccOpenDate(resultSet.getString(7));
                                item.setMaturityDate(resultSet.getString(8));
                                item.setSubsAmt(resultSet.getString(9));
                                totalAmt=totalAmt.add(new BigDecimal(resultSet.getString(9)));
                                list.add(item);
                            }
                            preMaturityRequestDTO.setPreMaturityList(list);
                            preMaturityRequestDTO.setTotalAmt(String.valueOf(totalAmt));
                            responseDataDTO.setResult(preMaturityRequestDTO);
                        }
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
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO modifyTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO=new BondLedgerAccountMaintenanceDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("P")) {
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_mod_pan_tor.getProcName());
                }else{
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_mod_bla_tor.getProcName());
                }
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setString(index++,preMaturityRequestDTO.getBondHolderName());
                stmt.setString(index++,preMaturityRequestDTO.getPanNo());
                if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("B")) {
                    stmt.setString(index++, preMaturityRequestDTO.getBlaNo());
                }
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("P")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(6);
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
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
                        }
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
                    }else if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("B")){
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        List<HashMap<String, String>> torchOut = null;
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("panNum", resultSet.getString(1));
                                temp.put("blaNo", resultSet.getString(2));
                                temp.put("bondHldName", resultSet.getString(3));
                                temp.put("modeOfHolding", resultSet.getString(4));
                                torchOut.add(temp);
                            }
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
                        }
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
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
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO verifyDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(preMaturityRequestDTO.getSchemeCode() != null) {
            String pan = preMaturityRequestDTO.getPanNo();
            if (pan != null
                    && !pan.trim().isEmpty()
                    && !pan.replaceAll("\\s+", "").toUpperCase().matches(PAN_REGEX)) {
                responseDataDTO.setMessage("PAN must be valid (e.g., ABCDE1234F)");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String bla = preMaturityRequestDTO.getBlaNo();
            if (bla == null || bla.trim().isEmpty()) {
                responseDataDTO.setMessage("BLA Number is mandatory");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }

        }
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        BigDecimal totalAmt=BigDecimal.ZERO;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_ver_sel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setString(index++,preMaturityRequestDTO.getBondHolderName());
                stmt.setString(index++,preMaturityRequestDTO.getPanNo());
                stmt.setString(index++,preMaturityRequestDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (preMaturityRequestDTO.getSchemeCode() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                            RateCodeMaintenanceDTO item = null;
                            while (resultSet.next()) {
                                item = new RateCodeMaintenanceDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSubCodeDescription(resultSet.getString(2));
                                item.setStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else{
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            PreMaturityListDTO item=null;
                            List<PreMaturityListDTO> list = new ArrayList<>();
                            while (resultSet.next()) {
                                item=new PreMaturityListDTO();
                                item.setPanNo(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setAge(resultSet.getString(3));
                                item.setBlaNo(resultSet.getString(4));
                                item.setModeOfHolding(resultSet.getString(5));
                                item.setSubsId(resultSet.getString(6));
                                item.setAccOpenDate(resultSet.getString(7));
                                item.setMaturityDate(resultSet.getString(8));
                                item.setSubsAmt(resultSet.getString(9));
                                totalAmt=totalAmt.add(new BigDecimal(resultSet.getString(9)));
                                list.add(item);
                            }
                            preMaturityRequestDTO.setPreMaturityList(list);
                            preMaturityRequestDTO.setTotalAmt(String.valueOf(totalAmt));
                            responseDataDTO.setResult(preMaturityRequestDTO);
                        }
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
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO verifyTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO=new BondLedgerAccountMaintenanceDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("P")) {
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_ver_pan_tor.getProcName());
                }else{
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_ver_bla_tor.getProcName());
                }
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setString(index++,preMaturityRequestDTO.getBondHolderName());
                stmt.setString(index++,preMaturityRequestDTO.getPanNo());
                if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("B")) {
                    stmt.setString(index++, preMaturityRequestDTO.getBlaNo());
                }
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("P")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(6);
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
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
                        }
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
                    }else if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("B")){
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        List<HashMap<String, String>> torchOut = null;
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("panNum", resultSet.getString(1));
                                temp.put("blaNo", resultSet.getString(2));
                                temp.put("bondHldName", resultSet.getString(3));
                                temp.put("modeOfHolding", resultSet.getString(4));
                                torchOut.add(temp);
                            }
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
                        }
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
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
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO inquiryDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(preMaturityRequestDTO.getSchemeCode() != null) {
            String pan = preMaturityRequestDTO.getPanNo();
            if (pan != null
                    && !pan.trim().isEmpty()
                    && !pan.replaceAll("\\s+", "").toUpperCase().matches(PAN_REGEX)) {
                responseDataDTO.setMessage("PAN must be valid (e.g., ABCDE1234F)");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String bla = preMaturityRequestDTO.getBlaNo();
            if (bla == null || bla.trim().isEmpty()) {
                responseDataDTO.setMessage("BLA Number is mandatory");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }

        }
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        BigDecimal totalAmt=BigDecimal.ZERO;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_inq.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setString(index++,preMaturityRequestDTO.getBondHolderName());
                stmt.setString(index++,preMaturityRequestDTO.getPanNo());
                stmt.setString(index++,preMaturityRequestDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (preMaturityRequestDTO.getSchemeCode() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            List<RateCodeMaintenanceDTO> list = new ArrayList<>();
                            RateCodeMaintenanceDTO item = null;
                            while (resultSet.next()) {
                                item = new RateCodeMaintenanceDTO();
                                item.setSchemeCode(resultSet.getString(1));
                                item.setSubCodeDescription(resultSet.getString(2));
                                item.setStatus(resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    }else {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        if (resultSet != null) {
                            PreMaturityListDTO item = null;
                            List<PreMaturityListDTO> list = new ArrayList<>();
                            while (resultSet.next()) {
                                item = new PreMaturityListDTO();
                                item.setPanNo(resultSet.getString(1));
                                item.setHolderName(resultSet.getString(2));
                                item.setAge(resultSet.getString(3));
                                item.setBlaNo(resultSet.getString(4));
                                item.setModeOfHolding(resultSet.getString(5));
                                item.setSubsId(resultSet.getString(6));
                                item.setAccOpenDate(resultSet.getString(7));
                                item.setMaturityDate(resultSet.getString(8));
                                item.setSubsAmt(resultSet.getString(9));
                                totalAmt = totalAmt.add(new BigDecimal(resultSet.getString(9)));
                                list.add(item);
                            }
                            preMaturityRequestDTO.setPreMaturityList(list);
                            preMaturityRequestDTO.setTotalAmt(String.valueOf(totalAmt));
                            responseDataDTO.setResult(preMaturityRequestDTO);
                        }
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
        return responseDataDTO;
    }
    @Override
public ResponseDataDTO inquiryTorch(PreMaturityRequestDTO preMaturityRequestDTO){
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO=new BondLedgerAccountMaintenanceDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("P")) {
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_inq_pan_tor.getProcName());
                }else{
                    stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_inq_bla_tor.getProcName());
                }
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setString(index++,preMaturityRequestDTO.getBondHolderName());
                stmt.setString(index++,preMaturityRequestDTO.getPanNo());
                if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("B")) {
                    stmt.setString(index++, preMaturityRequestDTO.getBlaNo());
                }
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("P")) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(6);
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
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
                        }
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
                    }else if(null!=preMaturityRequestDTO&&preMaturityRequestDTO.getFlag().equalsIgnoreCase("B")){
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        List<HashMap<String, String>> torchOut = null;
                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("panNum", resultSet.getString(1));
                                temp.put("blaNo", resultSet.getString(2));
                                temp.put("bondHldName", resultSet.getString(3));
                                temp.put("modeOfHolding", resultSet.getString(4));
                                torchOut.add(temp);
                            }
                            bondLedgerAccountMaintenanceDTO.setTorchValue(torchOut);
                        }
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
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
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO add(PreMaturityRequestDTO preMaturityRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_add.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setString(index++,preMaturityRequestDTO.getBlaNo());
                stmt.setArray(index++, generateTY_GBM_RBI_BOND_PRE_MATURITY_LIST_ARRAY(connection, preMaturityRequestDTO.getListOfString()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO delete(PreMaturityRequestDTO preMaturityRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (preMaturityRequestDTO.getRejectionRemarks() == null
                || preMaturityRequestDTO.getRejectionRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_del.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setArray(index++, generateTY_GBM_RBI_BOND_PRE_MATURITY_LIST_VERIFY_ARRAY(connection, preMaturityRequestDTO.getListOfString()));
                stmt.setString(index++,preMaturityRequestDTO.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO modify(PreMaturityRequestDTO preMaturityRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_mod.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setArray(index++, generateTY_GBM_RBI_BOND_PRE_MATURITY_LIST_ARRAY(connection, preMaturityRequestDTO.getListOfString()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO verify(PreMaturityRequestDTO preMaturityRequestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.PKG_GBM_RBI_BOND_PRE_MATURITY_sp_ver.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,preMaturityRequestDTO.getSchemeCode());
                stmt.setArray(index++, generateTY_GBM_RBI_BOND_PRE_MATURITY_LIST_VERIFY_ARRAY(connection, preMaturityRequestDTO.getListOfString()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.DATABASE_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    private ARRAY generateTY_GBM_RBI_BOND_PRE_MATURITY_LIST_ARRAY(Connection connection, List<String> list) throws Exception {
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
    private ARRAY generateTY_GBM_RBI_BOND_PRE_MATURITY_LIST_VERIFY_ARRAY(Connection connection, List<String> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
       // PreMaturityListDTO dto=null;
        for (int index = 0; index < list.size(); index++) {
           // dto=list.get(index);
            Object[] paramArray = new Object[1];
            paramArray[0] = list.get(index);
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }
}
