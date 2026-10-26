package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.RbiBondLedgerKartaDAO;
import com.isl.gbm.rbibond.dto.RbiBondLedgerKartaDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RbiBondLedgerKartaDAOImpl implements RbiBondLedgerKartaDAO {
    private static final Logger log = LoggerFactory.getLogger(RbiBondLedgerKartaDAOImpl.class);
    @Autowired
    ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO getTorchPan(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = new HashMap<>();
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_PAN_TOR.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getNameOfTheBondHolder());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getPanNo());
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

                        resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("nameOfTheBondHolder", resultSet.getString(1));
                            resultMap.put("panNo", resultSet.getString(2));


                            resultList.add(resultMap);
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

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(resultList);
        return responseDataDTO;



    }

    @Override
    public ResponseDataDTO getModifyTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
       List<Map<String, String>> resultList = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getNameOfTheBondHolder());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getPanNo());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                String remarks = stmt.getString(8);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet resultSet = (ResultSet) stmt.getObject(7);
                    if (rbiBondLedgerKartaDTO.getSchemeCode() == null) {


                        if (resultSet != null) {

                            resultList = new ArrayList<>();

                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("schemeCode", resultSet.getString(1));
                                resultMap.put("subDesc", resultSet.getString(2));
                                resultMap.put("schemeStatus", resultSet.getString(3));
                                resultList.add(resultMap);
                            }
                        }
                    }

                    else if (rbiBondLedgerKartaDTO.getBlaNo() != null && rbiBondLedgerKartaDTO.getSchemeCode() != null) {

                        if (resultSet != null) {

                            resultList = new ArrayList<>();

                            while (resultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("coparcenersName", resultSet.getString(1));
                                resultMap.put("coparcenersRelation", resultSet.getString(2));
                                resultMap.put("coparcenersDob", resultSet.getString(3));
                                resultMap.put("id", resultSet.getString(4));
                                resultMap.put("remarks", remarks);


                                resultList.add(resultMap);
                            }
                        }



                    }
                }
            }else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }


        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(resultList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getInquiryTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_INQ_BLA.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getNameOfTheBondHolder());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getPanNo());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getBlaNo());
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

                        resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("panNo", resultSet.getString("pan_no"));
                            resultMap.put("blaNo", resultSet.getString("BLA_NO"));
                            resultMap.put("nameOfTheBondHolder", resultSet.getString("name_of_the_hldr"));
                            resultMap.put("modeOfHolding", resultSet.getString("mode_of_holding"));

                            resultList.add(resultMap);
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

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(resultList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getModifyBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_MOD_BLA.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getNameOfTheBondHolder());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getPanNo());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getBlaNo());
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

                        resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("nameOfTheBondHolder", resultSet.getString(1));
                            resultMap.put("panNo", resultSet.getString(2));
                           resultMap.put("blaNo", resultSet.getString(3));
                            resultMap.put("modeOfHolding", resultSet.getString(4));

                            resultList.add(resultMap);
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

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(resultList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getVerDelTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
       HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getBlaNumber());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++,OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(14);
                    if (resultSet != null) {

                        resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("blaNo", resultSet.getString(1));
                            resultMap.put("nameOfTheBondHolder", resultSet.getString(2));
                            resultMap.put("modeOfHolding", resultSet.getString(3));
                            resultList.add(resultMap);
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


        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(resultList);
        return responseDataDTO;



    }

    @Override
    public ResponseDataDTO modifySelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getBlaNumber());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++,OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet1 = (ResultSet) stmt.getObject(14);
                    if (resultSet1 != null) {



                        while (resultSet1.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("blaNo", resultSet1.getString(1));
                            resultMap.put("nameOfTheBondHolder", resultSet1.getString(2));
                            resultMap.put("modeOfHolding", resultSet1.getString(3));
                            resultMap.put("schemeCode", resultSet1.getString(4));
                            resultMap.put("schemeName", resultSet1.getString(5));
                            rbiBondLedgerKartaDTO.setSubscriberDtlsMap(resultMap);

                        }

                    }

                    ResultSet resultSet = (ResultSet) stmt.getObject(15);
                    if (resultSet != null) {

                        resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("id", resultSet.getString(1));
                            resultMap.put("coparcenersName", resultSet.getString(2));
                            resultMap.put("relationWithKarta", resultSet.getString(3));
                            resultMap.put("dob", resultSet.getString(4));
                            resultList.add(resultMap);

                        }
                        rbiBondLedgerKartaDTO.setKartaList(resultList);
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



            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage(resultMsg);
            responseDataDTO.setResult(rbiBondLedgerKartaDTO);
            return responseDataDTO;
        }


    @Override
    public ResponseDataDTO addModify(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getBlaNo());


                if (CollectionUtils.isNotEmpty(rbiBondLedgerKartaDTO.getKartaModifyDtoList()))
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, rbiBondLedgerKartaDTO.getKartaModifyDtoList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                if (CollectionUtils.isNotEmpty(rbiBondLedgerKartaDTO.getKartaAddDtoList()))
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, rbiBondLedgerKartaDTO.getKartaAddDtoList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                if (CollectionUtils.isNotEmpty(rbiBondLedgerKartaDTO.getKartaDelDtoList()) && CollectionUtils.isNotEmpty(rbiBondLedgerKartaDTO.getKartaDelDtoList().get(0)))
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, rbiBondLedgerKartaDTO.getKartaDelDtoList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);

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

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiry(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        RbiBondLedgerKartaDTO bondLedgerKartaDTO=new RbiBondLedgerKartaDTO();
        List<Map<String,Object>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getNameOfTheBondHolder());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getPanNo());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet1 = (ResultSet) stmt.getObject(7);
                    if (rbiBondLedgerKartaDTO.getSchemeCode() == null) {
                        if (resultSet1 != null) {
                            List<HashMap<String, String>> torchOut = null;
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet1.next()) {
                                temp = new HashMap<>();
                                temp.put("schemeCode", resultSet1.getString(1));
                                temp.put("subDesc", resultSet1.getString(2));
                                temp.put("schemeStatus", resultSet1.getString(3));

                                torchOut.add(temp);

                            }
                            bondLedgerKartaDTO.setTorchValue(torchOut);

                        }
                    } else if (rbiBondLedgerKartaDTO.getSchemeCode() != null) {
                        if (resultSet1 != null) {

                            resultList = DataBaseUtils.generateOldValueNewValueMapList(resultSet1);
                            bondLedgerKartaDTO.setOldNewValueMap(resultList);

                        }


                        bondLedgerKartaDTO.setCreatedBy(stmt.getString(8));
                        bondLedgerKartaDTO.setCreatedDate(stmt.getString(9));
                        bondLedgerKartaDTO.setModifiedBy(stmt.getString(10));
                        bondLedgerKartaDTO.setModifiedDate(stmt.getString(11));
                        bondLedgerKartaDTO.setVerifiedBy(stmt.getString(12));
                        bondLedgerKartaDTO.setVerifiedDate(stmt.getString(13));
                        bondLedgerKartaDTO.setPreOprType(stmt.getString(14));
                        bondLedgerKartaDTO.setVerifiedStatus(stmt.getString(15));
                        bondLedgerKartaDTO.setRemarks(stmt.getString(16));

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

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(bondLedgerKartaDTO);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO inquirySelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getNameOfTheBondHolder());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getPanNo());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet1 = (ResultSet) stmt.getObject(7);
                    if (resultSet1 != null) {



                        while (resultSet1.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("blaNo", resultSet1.getString(1));
                            resultMap.put("nameOfTheBondHolder", resultSet1.getString(2));
                            resultMap.put("modeOfHolding", resultSet1.getString(3));
                            resultMap.put("schemeCode", resultSet1.getString(4));
                            resultMap.put("schemeName", resultSet1.getString(5));
                            rbiBondLedgerKartaDTO.setSubscriberDtlsMap(resultMap);

                        }

                    }

                    rbiBondLedgerKartaDTO.setPkColValOut(stmt.getString(5));
                    rbiBondLedgerKartaDTO.setCreatedBy(stmt.getString(6));
                    rbiBondLedgerKartaDTO.setCreatedDate(stmt.getString(7));
                    rbiBondLedgerKartaDTO.setModifiedBy(stmt.getString(8));
                    rbiBondLedgerKartaDTO.setModifiedDate(stmt.getString(9));
                    rbiBondLedgerKartaDTO.setVerifiedBy(stmt.getString(10));
                    rbiBondLedgerKartaDTO.setVerifiedDate(stmt.getString(11));
                    rbiBondLedgerKartaDTO.setPreOprType(stmt.getString(12));
                    rbiBondLedgerKartaDTO.setVerifiedStatus(stmt.getString(13));

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

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(rbiBondLedgerKartaDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiryPan(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = new HashMap<>();
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_INQ_KARTA_MASTER_PAN_TOR.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getNameOfTheBondHolder());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getPanNo());
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

                        resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("nameOfTheBondHolder", resultSet.getString(1));
                            resultMap.put("panNo", resultSet.getString(2));



                            resultList.add(resultMap);
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

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(resultList);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verDelSelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<Map<String,Object>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getBlaNo());
                stmt.registerOutParameter(index++,OracleTypes.CURSOR);
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet1 = (ResultSet) stmt.getObject(5);
                    if(rbiBondLedgerKartaDTO.getSchemeCode() == null) {
                        if (resultSet1 != null) {
                            List<HashMap<String, String>> torchOut = null;
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet1.next()) {
                                temp = new HashMap<>();
                                temp.put("schemeCode", resultSet1.getString(1));
                                temp.put("subDesc", resultSet1.getString(2));
                                temp.put("schemeStatus", resultSet1.getString(3));

                                torchOut.add(temp);

                            }
                            rbiBondLedgerKartaDTO.setTorchValue(torchOut);

                        }
                    }


                     else if(rbiBondLedgerKartaDTO.getBlaNo() == null) {
                        if (resultSet1 != null) {
                            List<HashMap<String, String>> torchOut = null;
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet1.next()) {
                                temp = new HashMap<>();
                                temp.put("nameOfTheBondHolder", resultSet1.getString(1));
                                temp.put("panNo", resultSet1.getString(2));
                                temp.put("blaNo", resultSet1.getString(3));
                                temp.put("modeOfHolding", resultSet1.getString(4));

                                torchOut.add(temp);

                            }
                            rbiBondLedgerKartaDTO.setTorchValue(torchOut);

                        }
                    }
                    else if(rbiBondLedgerKartaDTO.getBlaNo() != null && rbiBondLedgerKartaDTO.getSchemeCode() != null) {

                        if (resultSet1 != null) {
                                resultList=DataBaseUtils.generateOldValueNewValueMapList(resultSet1);
                                rbiBondLedgerKartaDTO.setOldNewValueMap(resultList);
                            }
                        rbiBondLedgerKartaDTO.setCreatedBy(stmt.getString(6));
                        rbiBondLedgerKartaDTO.setCreatedDate(stmt.getString(7));
                        rbiBondLedgerKartaDTO.setModifiedBy(stmt.getString(8));
                        rbiBondLedgerKartaDTO.setModifiedDate(stmt.getString(9));
                        rbiBondLedgerKartaDTO.setVerifiedBy(stmt.getString(10));
                        rbiBondLedgerKartaDTO.setVerifiedDate(stmt.getString(11));
                        rbiBondLedgerKartaDTO.setPreOprType(stmt.getString(12));
                        rbiBondLedgerKartaDTO.setVerifiedStatus(stmt.getString(13));
                        rbiBondLedgerKartaDTO.setRemarks(stmt.getString(14));
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


        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(rbiBondLedgerKartaDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getBlaNo());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                responseDataDTO.setMessage(resultMsg);


            }
        }
        catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondLedgerKartaDTO.getRemarks() == null
                || rbiBondLedgerKartaDTO.getRemarks() .trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_KARTA_MASTER_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getSchemeCode());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getBlaNo());
                stmt.setString(index++, rbiBondLedgerKartaDTO.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);


            }
        }
        catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.DATABASE_ERROR;
        }
        finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
}
