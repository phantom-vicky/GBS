package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.BondLedgerAccountMaintenancDAO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.dto.CustomerData;
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
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
public class BondLedgerAccountMaintenancDAOimpl implements BondLedgerAccountMaintenancDAO {

    private static final Logger log = LoggerFactory.getLogger(BondLedgerAccountMaintenancDAOimpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO getBlaNumDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_GET_BLA_DETAILS.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getModeOfHolding());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNum());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getJointHolderNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {


                    ResultSet resultSet = (ResultSet) stmt.getObject(7);

                    if(bondLedgerAccountMaintenanceDTO.getBlaNum() == null){


                        if(bondLedgerAccountMaintenanceDTO.getModeOfHolding().equalsIgnoreCase("MH002")  ||
                                bondLedgerAccountMaintenanceDTO.getModeOfHolding().equalsIgnoreCase("MH003")){

                            if(resultSet != null) {
                                list = new ArrayList<>();
                                HashMap<String, String> item = null;
                                while (resultSet.next()) {
                                    item = new HashMap<>();
                                    item.put("blaNo", resultSet.getString(1));
                                    item.put("nameOfTheBondHolder", resultSet.getString(2));
                                    item.put("jointHolderType", resultSet.getString(3));
                                    item.put("modeOfHolding", resultSet.getString(4));
                                    item.put("jointHolderTypeCode", resultSet.getString(5));
                                    list.add(item);
                                }
                                responseDataDTO.setResult(list);
                            }

                        }
                        if(bondLedgerAccountMaintenanceDTO.getModeOfHolding().equalsIgnoreCase("MH001")  ||
                                bondLedgerAccountMaintenanceDTO.getModeOfHolding().equalsIgnoreCase("MH004") ||
                                bondLedgerAccountMaintenanceDTO.getModeOfHolding().equalsIgnoreCase("MH005")){

                            if(resultSet != null) {
                                list = new ArrayList<>();
                                HashMap<String, String> item = null;
                                while (resultSet.next()) {
                                    item = new HashMap<>();
                                    item.put("blaNo", resultSet.getString(1));
                                    item.put("nameOfBondHolder", resultSet.getString(2));
                                    item.put("modeOfHolding", resultSet.getString(3));
                                    item.put("jointHolderType", resultSet.getString(4));
                                    item.put("jointHolderTypeCode", resultSet.getString(5));
                                    list.add(item);
                                }
                                responseDataDTO.setResult(list);
                            }
                        }


                    }
                    else {
                        if(resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", bondLedgerAccountMaintenanceDTO.getBlaNum());
                                item.put("bondHolderFirstName", resultSet.getString(1));
                                item.put("bondHolderMiddleName", resultSet.getString(2));
                                item.put("bondHolderLastName", resultSet.getString(3));
                                item.put("fatherName", resultSet.getString(4));
                                item.put("motherName", resultSet.getString(5));
                                item.put("gender", resultSet.getString(6));
                                item.put("dateOfBirth", resultSet.getString(7));
                                item.put("isMinor", resultSet.getString(8));
                                item.put("addressOne", resultSet.getString(9));
                                item.put("addressTwo", resultSet.getString(10));
                                item.put("addressThree", resultSet.getString(11));
                                item.put("city", resultSet.getString(12));
                                item.put("pinCode", resultSet.getString(13));
                                item.put("phoneNumber", resultSet.getString(14));
                                item.put("mobileNumber", resultSet.getString(15));
                                item.put("email", resultSet.getString(16));
                                item.put("pan", resultSet.getString(17));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
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
    public ResponseDataDTO add(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {

        log.info("ADD input : {}" , bondLedgerAccountMaintenanceDTO.getDataList().toString());
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getModeOfHolding());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getIsNri());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getJointHolderNo());
                stmt.setArray(index++, generateTYP_ty_gbm_bond_holder_dtls(connection, bondLedgerAccountMaintenanceDTO.getDataList()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);

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


                if (stmt.getString(index) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    bondLedgerAccountMaintenanceDTO.setSingleBlaNo(stmt.getString(8));
                    bondLedgerAccountMaintenanceDTO.setSingleGuardianId(stmt.getString(12));

                    bondLedgerAccountMaintenanceDTO.setFirstJointBlaNo(stmt.getString(9));
                    bondLedgerAccountMaintenanceDTO.setSecondJointBlaNo(stmt.getString( 10));
                    bondLedgerAccountMaintenanceDTO.setThirdJointBlaNo(stmt.getString(11));

                    bondLedgerAccountMaintenanceDTO.setFirstGuardianId(stmt.getString(13));
                    bondLedgerAccountMaintenanceDTO.setSecondGuardianId(stmt.getString(14));
                    bondLedgerAccountMaintenanceDTO.setThirdGuardianId(stmt.getString(15));

                    responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
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
    public ResponseDataDTO getGuardianDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_GET_GUARDIAN_DETAILS.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getGuardianId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(5);

                    if(bondLedgerAccountMaintenanceDTO.getGuardianId() == null){

                        if(resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("modeOfHolding", resultSet.getString(3));
                                item.put("guardianId", resultSet.getString(4));
                                item.put("guardianName", resultSet.getString(5));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }

                    }
                    else {
                        if(resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("guardianFirstName", resultSet.getString(1));
                                item.put("guardianMiddleName", resultSet.getString(2));
                                item.put("guardianLastName", resultSet.getString(3));
                                item.put("dateOfBirth", resultSet.getString(4));
                                item.put("address", resultSet.getString(5));
                                item.put("mobileNo", resultSet.getString(6));
                                item.put("email", resultSet.getString(7));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
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
    public ResponseDataDTO inquiry(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_INQUIRY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getModeOfHolding());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNum());
                stmt.registerOutParameter(index++, Types.VARCHAR);
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(15);

                    if(bondLedgerAccountMaintenanceDTO.getBlaNum() == null){


                        if(resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("jointHolderType", resultSet.getString(3));
                                item.put("modeOfHolding", resultSet.getString(4));
                                item.put("verifiedStatus", resultSet.getString(5));
                                item.put("blaGroupId", resultSet.getString(6));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }


                    } else {

                        bondLedgerAccountMaintenanceDTO.setPkColumnValue(stmt.getString(6));
                        bondLedgerAccountMaintenanceDTO.setCreatedBy(stmt.getString(7));
                        bondLedgerAccountMaintenanceDTO.setCreatedDate(stmt.getString(8));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedBy(stmt.getString(9));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedDate(stmt.getString(10));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedBy(stmt.getString(11));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedDate(stmt.getString(12));
                        bondLedgerAccountMaintenanceDTO.setPreviousOperationType(stmt.getString(13));
                        bondLedgerAccountMaintenanceDTO.setVerifiedStatus(stmt.getString(14));
                        bondLedgerAccountMaintenanceDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
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
    public ResponseDataDTO getTdsRateCode(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_GET_TDS_RATE_CODE.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    bondLedgerAccountMaintenanceDTO.setTdsRateWithPan(stmt.getString(4));
                    bondLedgerAccountMaintenanceDTO.setTdsRateWithoutPan(stmt.getString(5));
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
        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getModeOfHolding());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNum());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(16);

                    if(bondLedgerAccountMaintenanceDTO.getBlaNum() == null){


                        if(resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("jointHolderType", resultSet.getString(3));
                                item.put("modeOfHolding", resultSet.getString(4));
                                item.put("verifiedStatus", resultSet.getString(5));
                                //item.put("blaGroupId", resultSet.getString(6));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }


                    } else {

                        bondLedgerAccountMaintenanceDTO.setPkColumnValue(stmt.getString(7));
                        bondLedgerAccountMaintenanceDTO.setCreatedBy(stmt.getString(8));
                        bondLedgerAccountMaintenanceDTO.setCreatedDate(stmt.getString(9));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedBy(stmt.getString(10));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedDate(stmt.getString(11));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedBy(stmt.getString(12));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedDate(stmt.getString(13));
                        bondLedgerAccountMaintenanceDTO.setPreviousOperationType(stmt.getString(14));
                        bondLedgerAccountMaintenanceDTO.setVerifiedStatus(stmt.getString(15));
                        bondLedgerAccountMaintenanceDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
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
    public ResponseDataDTO modify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, bondLedgerAccountMaintenanceDTO.getColumnValueDetailsList()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO verifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<HashMap<String, String>> list = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getModeOfHolding());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaNum());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(16);

                    if(bondLedgerAccountMaintenanceDTO.getBlaNum() == null){


                        if(resultSet != null) {
                            list = new ArrayList<>();
                            HashMap<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("blaNo", resultSet.getString(1));
                                item.put("nameOfTheBondHolder", resultSet.getString(2));
                                item.put("jointHolderType", resultSet.getString(3));
                                item.put("modeOfHolding", resultSet.getString(4));
                                item.put("verifiedStatus", resultSet.getString(5));
                                //item.put("blaGroupId", resultSet.getString(6));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }


                    } else {

                        bondLedgerAccountMaintenanceDTO.setBlaGroupId(stmt.getString(6));
                        bondLedgerAccountMaintenanceDTO.setPkColumnValue(stmt.getString(7));
                        bondLedgerAccountMaintenanceDTO.setCreatedBy(stmt.getString(8));
                        bondLedgerAccountMaintenanceDTO.setCreatedDate(stmt.getString(9));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedBy(stmt.getString(10));
                        bondLedgerAccountMaintenanceDTO.setLastModifiedDate(stmt.getString(11));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedBy(stmt.getString(12));
                        bondLedgerAccountMaintenanceDTO.setLastVerifiedDate(stmt.getString(13));
                        bondLedgerAccountMaintenanceDTO.setPreviousOperationType(stmt.getString(14));
                        bondLedgerAccountMaintenanceDTO.setVerifiedStatus(stmt.getString(15));
                        bondLedgerAccountMaintenanceDTO.setMasterDetailsValueModel(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        responseDataDTO.setResult(bondLedgerAccountMaintenanceDTO);
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
    public ResponseDataDTO verify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaGroupId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO delete(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BLA_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, bondLedgerAccountMaintenanceDTO.getBlaGroupId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info("Executed the sql query :");
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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


    private ARRAY generateTYP_ty_gbm_bond_holder_dtls(Connection connection, List<CustomerData> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_BOND_HOLDER_DTLS_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_BOND_HOLDER_DTLS", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        CustomerData dto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[55];
            dto = list.get(index);
            paramArray[0] = dto.getCbsCustId();
            paramArray[1] = dto.getJointHolderType();
            paramArray[2] = dto.getBondHldFirstName();
            paramArray[3] = dto.getBondHldMidName();
            paramArray[4] = dto.getBondHldLastName();
            paramArray[5] = dto.getHolderType();
            paramArray[6] = dto.getFatherName();
            paramArray[7] = dto.getMotherName();
            paramArray[8] = dto.getGender();
            paramArray[9] = dto.getBondHldDob();
            paramArray[10] = dto.getIsMinor();
            paramArray[11] = dto.getDetailsOfBirthCertificate();
            paramArray[12] = dto.getBondHldAddOne();
            paramArray[13] = dto.getBondHldAddTwo();
            paramArray[14] = dto.getBondHldAddThree();
            paramArray[15] = dto.getBondHldCity();
            paramArray[16] = dto.getBondHldState();
            paramArray[17] = dto.getBondHldPinCode();
            paramArray[18] = dto.getBondHolderPhoneNumber();
            paramArray[19] = dto.getBondHldMobNum();
            paramArray[20] = dto.getBondHldEmail();
            paramArray[21] = dto.getHufRegPowerOfAttorney();
            paramArray[22] = dto.getPowerOfAttorneyKartaName();
            paramArray[23] = dto.getGuardianFirstName();
            paramArray[24] = dto.getGuardianMiddleName();
            paramArray[25] = dto.getGuardianLastName();
            paramArray[26] = dto.getRelationWithMinor();
            paramArray[27] = dto.getGuardianDateOfBirth();
            paramArray[28] = dto.getGuardianCommunicationAddress();
            paramArray[29] = dto.getGuardianMobileNo();
            paramArray[30] = dto.getGuardianEmailId();
            paramArray[31] = dto.getOperativeAccNum();
            paramArray[32] = dto.getOperativeAccBranchId();
            paramArray[33] = dto.getPanNo();
            paramArray[34] = dto.getAadhaarNo();
            paramArray[35] = dto.getForm15g15htaxExeCerti();
            paramArray[36] = dto.getDateSubmission15g15htax();
            paramArray[37] = dto.getFinYear();
            paramArray[38] = dto.getOtherDetails();
            paramArray[39] = dto.getRemarks();
            paramArray[40] = dto.getWitnessNameOne();
            paramArray[41] = dto.getWitnessAddressOne();
            paramArray[42] = dto.getWitnessNameTwo();
            paramArray[43] = dto.getWitnessAddressTwo();
            paramArray[44] = dto.getBirthCertificate();
            paramArray[45] = dto.getPassport();
            paramArray[46] = dto.getSeniorCitizenCard();
            paramArray[47] = dto.getVoterIdCard();
            paramArray[48] = dto.getPanCard();
            paramArray[49] = dto.getDrivingLicence();
            paramArray[50] = dto.getRationCard();
            paramArray[51] = dto.getOtherDocuments();

            if(dto.getTdsRateApplicable() != null){
                if(dto.getTdsRateApplicable().length() > 0){
                    paramArray[52] = dto.getTdsRateApplicable();
                }else{
                    paramArray[52] = 0;
                }
            }else{
                paramArray[52] = 0;
            }

            paramArray[53] = dto.getBlaAccNo();
            paramArray[54] = dto.getGuardianId();

//            if(dto.getTdsRateApplicable() == "" || dto.getTdsRateApplicable().equalsIgnoreCase(null)){
//                paramArray[52] = 0;
//            }
//            else{
//                paramArray[52] = dto.getTdsRateApplicable();
//            }
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
            System.out.println(dto.toString());
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


//    private Array generate_ty_gbm_bond_holder_dtls_obj(Connection connection, List<BondLedgerAccountMaintenanceDTO.Data> list) throws SQLException {
//        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TY_GBM_BOND_HOLDER_DTLS_OBJ", connection);
//        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TY_GBM_BOND_HOLDER_DTLS", connection);
//        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
//        BondLedgerAccountMaintenanceDTO.Data dto = null;
//        for (int index = 0; index < list.size(); index++) {
//            dto = list.get(index);
//            Map<String, Object> paramMap = new HashMap<>();
//
//            paramMap.put("cbs_cust_id",  dto.getCbsCustId());
//            paramMap.put("joint_holder_type",  dto.getJointHolderType());
//            paramMap.put("bond_hld_first_name",  dto.getBondHldFirstName());
//            paramMap.put("bond_hld_mid_name",  dto.getBondHldMidName());
//            paramMap.put("bond_hld_last_name",  dto.getBondHldLastName());
//            paramMap.put("holder_type",  dto.getHolderType());
//            paramMap.put("father_name",  dto.getFatherName());
//            paramMap.put("mother_name",  dto.getMotherName());
//            paramMap.put("gender",  dto.getGender());
//            paramMap.put("bond_hld_dob",  dto.getBondHldDob());
//            paramMap.put("is_minor",  dto.getIsMinor());
//            paramMap.put("details_of_birth_certificate",  dto.getDetailsOfBirthCertificate());
//            paramMap.put("bond_hld_add_1",  dto.getBondHldAddOne());
//            paramMap.put("bond_hld_add_2",  dto.getBondHldAddTwo());
//            paramMap.put("bond_hld_add_3",  dto.getBondHldAddThree());
//            paramMap.put("bond_hld_city",  dto.getBondHldCity());
//            paramMap.put("bond_hld_state",  dto.getBondHldState());
//            paramMap.put("bond_hld_pin_code",  dto.getBondHldPinCode());
//            paramMap.put("bond_holder_phone_number",  dto.getBondHolderPhoneNumber());
//            paramMap.put("bond_hld_mob_num",  dto.getBondHldMobNum());
//            paramMap.put("bond_hld_email",  dto.getBondHldEmail());
//            paramMap.put("huf_reg_power_of_attorney",  dto.getHufRegPowerOfAttorney());
//            paramMap.put("power_of_attorney_karta_name",  dto.getPowerOfAttorneyKartaName());
//            paramMap.put("guardian_first_name",  dto.getGuardianFirstName());
//            paramMap.put("guardian_middle_name",  dto.getGuardianMiddleName());
//            paramMap.put("guardian_last_name",  dto.getGuardianLastName());
//            paramMap.put("relation_with_minor",  dto.getRelationWithMinor());
//            paramMap.put("guardian_date_of_birth",  dto.getGuardianDateOfBirth());
//            paramMap.put("guardian_communication_address",  dto.getGuardianCommunicationAddress());
//            paramMap.put("guardian_mobile_no",  dto.getGuardianMobileNo());
//            paramMap.put("guardian_email_id",  dto.getGuardianEmailId());
//            paramMap.put("operative_acc_num",  dto.getOperativeAccNum());
//            paramMap.put("operative_acc_branch_id",  dto.getOperativeAccBranchId());
//            paramMap.put("pan_no",  dto.getPanNo());
//            paramMap.put("aadhaar_no",  dto.getAadhaarNo());
//            paramMap.put("form_15g_15h_tax_exe_certi",  dto.getForm15g15htaxExeCerti());
//            paramMap.put("date_submission_15g_15h_tax",  dto.getDateSubmission15g15htax());
//            paramMap.put("fin_year",  dto.getFinYear());
//            paramMap.put("other_details",  dto.getOtherDetails());
//            paramMap.put("remarks",  dto.getRemarks());
//            paramMap.put("witness_name_1",  dto.getWitnessNameOne());
//            paramMap.put("witness_address_1",  dto.getWitnessAddressOne());
//            paramMap.put("witness_name_2",  dto.getWitnessNameTwo());
//            paramMap.put("witness_address_2",  dto.getWitnessAddressTwo());
//            paramMap.put("birth_certificate",  dto.getBirthCertificate());
//            paramMap.put("passport",  dto.getPassport());
//            paramMap.put("senior_citizen_card",  dto.getSeniorCitizenCard());
//            paramMap.put("voter_id_card",  dto.getVoterIdCard());
//            paramMap.put("pan_card",  dto.getPanCard());
//            paramMap.put("driving_licence",  dto.getDrivingLicence());
//            paramMap.put("ration_card",  dto.getRationCard());
//            paramMap.put("other_documents",  dto.getOtherDocuments());
//
//            STRUCT struct = new STRUCT(genericTableStruct, connection, paramMap);
//            genericTableInsertStructs[index] = struct;
//        }
//        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
//    }
//




}
