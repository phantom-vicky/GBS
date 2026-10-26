package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.RbiBondNominationDao;
import com.isl.gbm.rbibond.dto.RbiBondNominationDto;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class RbiBondNominationDaoImpl implements RbiBondNominationDao {

    private static final Logger log = LoggerFactory.getLogger(RbiBondNominationDaoImpl.class);


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO inquiry(RbiBondNominationDto rbiBondNominationDto) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet;
        Map<String, Object> rbiBondNominationDtoNomDetMap = new HashMap<>();
        List<Map<String, Object>> nomList = null;

        RbiBondNominationDto rbiBondNominationDto1 = null;
        List<RbiBondNominationDto> rbiBondNominationDtoBLAList = new ArrayList<>();
        List<RbiBondNominationDto> rbiBondNominationDtoSubDetList = new ArrayList<>();
        RbiBondNominationDto rbiBondNominationDto2 = new RbiBondNominationDto();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_inq.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                stmt.setString(index++, rbiBondNominationDto.getBondHolderName());
                stmt.setString(index++,rbiBondNominationDto.getPanNum());
                stmt.setString(index++, rbiBondNominationDto.getBlaNo());
                stmt.setString(index++, rbiBondNominationDto.getBondSubsId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                rbiBondNominationDto2.setCreatedBy(stmt.getString(12));
                rbiBondNominationDto2.setCreatedDate(stmt.getString(13));
                rbiBondNominationDto2.setVerifiedBy(stmt.getString(16));
                rbiBondNominationDto2.setVerifiedDate(stmt.getString(17));
                rbiBondNominationDto2.setVerifiedStatus(stmt.getString(19));
                rbiBondNominationDto2.setModifiedBy(stmt.getString(14));
                rbiBondNominationDto2.setModifiedDate(stmt.getString(15));
                rbiBondNominationDto2.setPkColValue(stmt.getString(11));
                rbiBondNominationDto2.setPreviousOperationType(stmt.getString(18));
                rbiBondNominationDto2.setVerifiedStatus(stmt.getString(19));
                rbiBondNominationDto2.setDeleteRemark(stmt.getString(20));
                rbiBondNominationDto2.setAccOpenDate(stmt.getString(21));
                rbiBondNominationDto2.setMaturityDate(stmt.getString(22));




                if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                    if (rbiBondNominationDto.getSchemeCode() == null ) {

                        ResultSet rs = (ResultSet) stmt.getObject(8);

                        while (rs.next()) {
                            rbiBondNominationDto1 = new RbiBondNominationDto();
                            rbiBondNominationDto1.setSchemeCode(rs.getString(1));
                            rbiBondNominationDto1.setSubDesc(rs.getString(2));
                            rbiBondNominationDto1.setSchemeStatus(rs.getString(3));
                            rbiBondNominationDtoBLAList.add(rbiBondNominationDto1);
                        }
                        responseDataDTO.setResult(rbiBondNominationDtoBLAList);

                    }

                    if (rbiBondNominationDto.getBlaNo() != null && rbiBondNominationDto.getBondSubsId() != null) {

                        ResultSet rs1 = (ResultSet) stmt.getObject(8);

                        while (rs1.next()) {
                            rbiBondNominationDto1 = new RbiBondNominationDto();
                            rbiBondNominationDto1.setBlaNo(rs1.getString("bla_no"));
                            rbiBondNominationDto1.setBondHolderName(rs1.getString("name_of_the_hldr"));
                            rbiBondNominationDto1.setModeOfHolding(rs1.getString("mode_of_holding"));
                            rbiBondNominationDto1.setJointHolderType(rs1.getString("joint_hldr_type"));
                            rbiBondNominationDto1.setModeOfHolding1(rs1.getString("mode_of_holding1"));
                            rbiBondNominationDtoSubDetList.add(rbiBondNominationDto1);
                        }

                        //GET NOMINATION DETAILS
                        resultSet = (ResultSet) stmt.getObject(9);

                        nomList = DataBaseUtils.generateOldValueNewValueMapList(resultSet);

                        rbiBondNominationDtoNomDetMap.put("rbiBondNominationDtoSubDetList",rbiBondNominationDtoSubDetList);
                        rbiBondNominationDtoNomDetMap.put("rbiBondNominationDtoNomDetList",nomList);
                        rbiBondNominationDtoNomDetMap.put("auditDetails",rbiBondNominationDto2);

                        responseDataDTO.setResult(rbiBondNominationDtoNomDetMap);
                    }

                }
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

    @Override
    public ResponseDataDTO getVerifyDeleteDetails(RbiBondNominationDto rbiBondNominationDto) {
         ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        List<RbiBondNominationDto> rbiBondNominationDtoSubDetList = new ArrayList<>();
        RbiBondNominationDto rbiBondNominationDto1 = null;
        List<Map<String,Object>> resultList = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_ver_sel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                stmt.setString(index++, rbiBondNominationDto.getBlaNo());
                stmt.setString(index++, rbiBondNominationDto.getBondSubsId());
                stmt.setString(index++, rbiBondNominationDto.getFlag());
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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                    ResultSet resultSet1 = (ResultSet) stmt.getObject(16);
                    if (rbiBondNominationDto.getSchemeCode() == null) {
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
                            rbiBondNominationDto.setTorchValue(torchOut);
                        }
                    } else if (!ObjectUtils.isEmpty(rbiBondNominationDto.getFlag()) && rbiBondNominationDto.getFlag().equalsIgnoreCase("B")) {
                        if (resultSet1 != null) {
                            List<HashMap<String, String>> torchOut = null;
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet1.next()) {
                                temp = new HashMap<>();
                                temp.put("blaNo", resultSet1.getString(1));
                                temp.put("bondHolderName", resultSet1.getString(2));
                                temp.put("modeOfHolding", resultSet1.getString(3));
                                torchOut.add(temp);
                            }
                            rbiBondNominationDto.setTorchValue(torchOut);
                        }
                    } else if (!ObjectUtils.isEmpty(rbiBondNominationDto.getFlag()) && rbiBondNominationDto.getFlag().equalsIgnoreCase("S")) {
                        if (resultSet1 != null) {
                            List<HashMap<String, String>> torchOut = null;
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet1.next()) {
                                temp = new HashMap<>();
                                temp.put("subscriptionId", resultSet1.getString(1));
                                temp.put("bondHolderName", resultSet1.getString(2));
                                temp.put("subsAmount", resultSet1.getString(3));
                                temp.put("verifiedStatus", resultSet1.getString(4));
                                temp.put("valueDate", resultSet1.getString(5));
                                torchOut.add(temp);
                            }
                            rbiBondNominationDto.setTorchValue(torchOut);
                        }
                    } else if (!ObjectUtils.isEmpty(rbiBondNominationDto.getFlag()) && rbiBondNominationDto.getFlag().equalsIgnoreCase("D")) {
                        ResultSet rs2 = (ResultSet) stmt.getObject(16);// bond holder detials
                        while (rs2.next()) {
                            rbiBondNominationDto1 = new RbiBondNominationDto();
                            rbiBondNominationDto1.setBlaNo(rs2.getString("bla_no"));
                            rbiBondNominationDto1.setBondHolderName(rs2.getString("name_of_the_hldr"));
                            rbiBondNominationDto1.setModeOfHolding(rs2.getString("mode_of_holding"));
                            rbiBondNominationDto1.setJointHolderType(rs2.getString("joint_hldr_type"));
                            rbiBondNominationDto1.setModeOfHolding1(rs2.getString("mode_of_holding1"));
                            rbiBondNominationDtoSubDetList.add(rbiBondNominationDto1);
                        }
                        ResultSet resultSet2 = (ResultSet) stmt.getObject(17); // nominee details cursor
                        if (resultSet1 != null) {
                            resultList = DataBaseUtils.generateOldValueNewValueMapList(resultSet2);
                            rbiBondNominationDto.setOldNewValueMap(resultList);
                        }
                        rbiBondNominationDto.setPkColValue(stmt.getString(7));
                        rbiBondNominationDto.setCreatedBy(stmt.getString(8));
                        rbiBondNominationDto.setCreatedDate(stmt.getString(9));
                        rbiBondNominationDto.setModifiedBy(stmt.getString(10));
                        rbiBondNominationDto.setModifiedDate(stmt.getString(11));
                        rbiBondNominationDto.setVerifiedBy(stmt.getString(12));
                        rbiBondNominationDto.setVerifiedDate(stmt.getString(13));
                        rbiBondNominationDto.setPreOrpTyp(stmt.getString(14));
                        rbiBondNominationDto.setVerifiedStatus(stmt.getString(15));
                        rbiBondNominationDto.setAccOpenDate(stmt.getString(18));
                        rbiBondNominationDto.setMaturityDate(stmt.getString(19));
                        rbiBondNominationDto.setRbiBondNominationDtoSubsIdList(rbiBondNominationDtoSubDetList);
                    }
                }
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
        responseDataDTO.setResult(rbiBondNominationDto);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(RbiBondNominationDto rbiBondNominationDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondNominationDto.getDeleteRemark() == null
                || rbiBondNominationDto.getDeleteRemark().trim().isEmpty()) {
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_del.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                stmt.setString(index++, rbiBondNominationDto.getBondSubsId());
                stmt.setString(index++, rbiBondNominationDto.getDeleteRemark());
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

    @Override
    public ResponseDataDTO verify(RbiBondNominationDto rbiBondNominationDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_ver.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                stmt.setString(index++, rbiBondNominationDto.getBondSubsId());
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

    @Override
    public ResponseDataDTO addModify(RbiBondNominationDto rbiBondNominationDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_mod.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                stmt.setString(index++, rbiBondNominationDto.getBondSubsId());

                if (CollectionUtils.isNotEmpty(rbiBondNominationDto.getNominationAddDtoList()))
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, rbiBondNominationDto.getNominationAddDtoList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                if (CollectionUtils.isNotEmpty(rbiBondNominationDto.getNominationModifyDtoList()))
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, rbiBondNominationDto.getNominationModifyDtoList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                if (CollectionUtils.isNotEmpty(rbiBondNominationDto.getNominationDelDtoList()) && CollectionUtils.isNotEmpty(rbiBondNominationDto.getNominationDelDtoList()))
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, rbiBondNominationDto.getNominationDelDtoList()));
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
    public ResponseDataDTO modifySelect(RbiBondNominationDto rbiBondNominationDto) {


            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            Connection connection = null;
            CallableStatement stmt = null;
            String resultMsg = "";
            ResultSet resultSet;
            ResultSet resultSet1;
            ResultSet rs;
            RbiBondNominationDto rbiBondNominationDto1 = null;
            List<RbiBondNominationDto> rbiBondNominationDtoBLAList = new ArrayList<>();
            List<RbiBondNominationDto> rbiBondNominationDtoSubsIdList = new ArrayList<>();
            List<RbiBondNominationDto> rbiBondNominationDtoNomDetList = new ArrayList<>();
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_mod_sel.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                    stmt.setString(index++, rbiBondNominationDto.getBondHolderName());
                    stmt.setString(index++,rbiBondNominationDto.getPanNum());
                    stmt.setString(index++, rbiBondNominationDto.getBlaNo());
                    stmt.setString(index++, rbiBondNominationDto.getBondSubsId());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();
                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                    resultMsg = stmt.getString(index);
                    if (stmt.getString(index) != null && stmt.getString(index).equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                        if (rbiBondNominationDto.getSchemeCode()==null) {
                            rs = (ResultSet) stmt.getObject(8);
                            while (rs.next()) {
                                rbiBondNominationDto1 = new RbiBondNominationDto();
                                rbiBondNominationDto1.setSchemeCode(rs.getString(1));
                                rbiBondNominationDto1.setSubDesc(rs.getString(2));
                                rbiBondNominationDto1.setSchemeStatus(rs.getString(3));
                                rbiBondNominationDtoBLAList.add(rbiBondNominationDto1);
                            }
                            rbiBondNominationDto.setRbiSchemeList(rbiBondNominationDtoBLAList);
//                            rbiBondNominationDtoNomDetMap.put("rbiBondNominationsss", rbiBondNominationDtoBLAList);
//                            responseDataDTO.setResult(rbiBondNominationDto);
                        }
                        if (rbiBondNominationDto.getBlaNo() != null && rbiBondNominationDto.getSchemeCode()!=null && rbiBondNominationDto.getBondSubsId() != null) {
                            resultSet1 = (ResultSet) stmt.getObject(8);
                            while (resultSet1.next()) {
                                rbiBondNominationDto1 = new RbiBondNominationDto();
                                rbiBondNominationDto1.setBlaNo(resultSet1.getString(1));
                                rbiBondNominationDto1.setJointHolderName(resultSet1.getString(2));
                                rbiBondNominationDto1.setModeOfHolding(resultSet1.getString(3));
                                rbiBondNominationDto1.setJointHolderType(resultSet1.getString(4));
                                rbiBondNominationDto1.setModeOfHolding1(resultSet1.getString(5));
                                rbiBondNominationDtoSubsIdList.add(rbiBondNominationDto1);
                            }
                            rbiBondNominationDto.setRbiBondNominationDtoList(rbiBondNominationDtoSubsIdList);
                            rbiBondNominationDto.setMaturityDate(stmt.getString(11));
                            rbiBondNominationDto.setAccOpenDate(stmt.getString(10));
//                            responseDataDTO.setResult(rbiBondNominationDto1);
//                            rbiBondNominationDtoNomDetLists.add(rbiBondNominationDto1);
//                            rbiBondNominationDto.setRbiBondNominationDtoSubsIdList(rbiBondNominationDtoNomDetLists);
//                            rbiBondNominationDtoNomDetMap.put("rbiBondNomination", rbiBondNominationDtoNomDetLists);
                        }

//                        responseDataDTO.setResult(rbiBondNominationDto);




                        if (rbiBondNominationDto.getBlaNo() != null && rbiBondNominationDto.getBondSubsId() != null && rbiBondNominationDto.getSchemeCode() != null) {
                            resultSet = (ResultSet) stmt.getObject(9);
                            //GET NOMINATION DETAILS
                            // Old manual column mapping - commented out (NOM_SHARE_PERCENT_RS / SHARABLE_AMOUNT not in sp_mod_sel caused ORA-17006)
                            //while (resultSet.next()) {
                            //    rbiBondNominationDto1 = new RbiBondNominationDto();
                            //
                            //    Map<String, Object> resultMap = new HashMap<>();
                            //    Map<String, Object> tempMap = new HashMap<>();
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("NOMINATION_ID"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C002", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("NOMINEE_FIRST_NAME"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C003", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("NOMINEE_MID_NAME"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C004", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("NOMINEE_LAST_NAME"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C005", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("ADDRESS1"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C006", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("ADDRESS2"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C007", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("CITY"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C008", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("STATE"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C009", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("PIN_CODE"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C010", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("PHONE_NO"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C011", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("SHARE_PERCENTAGE"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C012", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    try {
                            //        tempMap.put("oldValue", resultSet.getString("SHARABLE_AMOUNT"));
                            //    } catch (Exception ignore) {
                            //        tempMap.put("oldValue", null);
                            //    }
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("OPERATIVE_ACCOUNT_NO"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C013", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("IS_NOMINEE_MINOR"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C014", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("RELATION_WITH_HOLDER"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C015", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("DATE_OF_BIRTH_OF_MINOR"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C016", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("GUARDIAN_FIRST_NAME"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C017", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("GUARDIAN_MIDDLE_NAME"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C018", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("GUARDIAN_LAST_NAME"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C019", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("GUARD_ADDRESS1"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C020", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("GUARD_ADDRESS2"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C021", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("GUARD_CITY"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C022", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("GUARD_STATE"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C023", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("GUARD_PIN_CODE"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C024", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("GUARD_MOB_NO"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C025", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("GUARD_EMAIL_ID"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C026", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("NOMINEE_STATUS"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C029", tempMap);
                            //
                            //    tempMap.put("oldValue", resultSet.getString("ID"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("ID", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("RELATION_OF_GUARD_WITH_NOMI"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C030", tempMap);
                            //
                            //    tempMap = new HashMap();
                            //    tempMap.put("oldValue", resultSet.getString("NOM_SHARE_PERCENT_RS"));
                            //    tempMap.put("newValue", null);
                            //    resultMap.put("C031", tempMap);
                            //
                            //    rbiBondNominationDto1.setMasterOldNewValueModel(resultMap);
                            //    rbiBondNominationDto1.setAccOpenDate(stmt.getString(10));
                            //    rbiBondNominationDto1.setMaturityDate(stmt.getString(11));
                            //    rbiBondNominationDtoNomDetList.add(rbiBondNominationDto1);
                            //}
                            //rbiBondNominationDto.setRbiBondNominationDtoNomDetList(rbiBondNominationDtoNomDetList);

                            // New code - mapped per sp_mod_sel p_nominee_dtls_out cursor columns
                            while (resultSet.next()) {
                                rbiBondNominationDto1 = new RbiBondNominationDto();

                                Map<String, Object> resultMap = new HashMap<>();
                                Map<String, Object> tempMap;

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("NOMINATION_ID"));
                                tempMap.put("newValue", null);
                                resultMap.put("C002", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("NOMINEE_FIRST_NAME"));
                                tempMap.put("newValue", null);
                                resultMap.put("C003", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("NOMINEE_MID_NAME"));
                                tempMap.put("newValue", null);
                                resultMap.put("C004", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("NOMINEE_LAST_NAME"));
                                tempMap.put("newValue", null);
                                resultMap.put("C005", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("ADDRESS1"));
                                tempMap.put("newValue", null);
                                resultMap.put("C006", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("ADDRESS2"));
                                tempMap.put("newValue", null);
                                resultMap.put("C007", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("CITY"));
                                tempMap.put("newValue", null);
                                resultMap.put("C008", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("STATE"));
                                tempMap.put("newValue", null);
                                resultMap.put("C009", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("PIN_CODE"));
                                tempMap.put("newValue", null);
                                resultMap.put("C010", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("PHONE_NO"));
                                tempMap.put("newValue", null);
                                resultMap.put("C011", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("SHARE_PERCENTAGE"));
                                tempMap.put("newValue", null);
                                resultMap.put("C012", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("OPERATIVE_ACCOUNT_NO"));
                                tempMap.put("newValue", null);
                                resultMap.put("C013", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("IS_NOMINEE_MINOR"));
                                tempMap.put("newValue", null);
                                resultMap.put("C014", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("RELATION_WITH_HOLDER"));
                                tempMap.put("newValue", null);
                                resultMap.put("C015", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("DATE_OF_BIRTH_OF_MINOR"));
                                tempMap.put("newValue", null);
                                resultMap.put("C016", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("GUARDIAN_FIRST_NAME"));
                                tempMap.put("newValue", null);
                                resultMap.put("C017", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("GUARDIAN_MIDDLE_NAME"));
                                tempMap.put("newValue", null);
                                resultMap.put("C018", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("GUARDIAN_LAST_NAME"));
                                tempMap.put("newValue", null);
                                resultMap.put("C019", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("GUARD_ADDRESS1"));
                                tempMap.put("newValue", null);
                                resultMap.put("C020", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("GUARD_ADDRESS2"));
                                tempMap.put("newValue", null);
                                resultMap.put("C021", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("GUARD_CITY"));
                                tempMap.put("newValue", null);
                                resultMap.put("C022", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("GUARD_STATE"));
                                tempMap.put("newValue", null);
                                resultMap.put("C023", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("GUARD_PIN_CODE"));
                                tempMap.put("newValue", null);
                                resultMap.put("C024", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("GUARD_MOB_NO"));
                                tempMap.put("newValue", null);
                                resultMap.put("C025", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("GUARD_EMAIL_ID"));
                                tempMap.put("newValue", null);
                                resultMap.put("C026", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("NOMINEE_STATUS"));
                                tempMap.put("newValue", null);
                                resultMap.put("C029", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("ID"));
                                tempMap.put("newValue", null);
                                resultMap.put("ID", tempMap);

                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("RELATION_OF_GUARD_WITH_NOMI"));
                                tempMap.put("newValue", null);
                                resultMap.put("C030", tempMap);

                                // C031 - Nominee Sharable Amount: not returned by sp_mod_sel; UI recalculates from share %
                                tempMap = new HashMap<>();
                                tempMap.put("oldValue", resultSet.getString("NOM_SHARE_PERCENT_RS"));
                                tempMap.put("newValue", null);
                                resultMap.put("C031", tempMap);

                                rbiBondNominationDto1.setMasterOldNewValueModel(resultMap);
                                rbiBondNominationDto1.setAccOpenDate(stmt.getString(10));
                                rbiBondNominationDto1.setMaturityDate(stmt.getString(11));
                                rbiBondNominationDtoNomDetList.add(rbiBondNominationDto1);
                            }
                            rbiBondNominationDto.setDeleteRemark(stmt.getString(22));
                            rbiBondNominationDto.setRbiBondNominationDtoNomDetList(rbiBondNominationDtoNomDetList);

                            // Wrong approach for sp_mod_sel - procedure returns flat columns, not COL_NO/OLD_VAL/NEW_VAL/ROW_NO
                            //List<Map<String, Object>> nomList = DataBaseUtils.generateOldValueNewValueMapList(resultSet);
                            //for (Map<String, Object> nomMap : nomList) {
                            //    rbiBondNominationDto1 = new RbiBondNominationDto();
                            //    rbiBondNominationDto1.setMasterOldNewValueModel(nomMap);
                            //    rbiBondNominationDto1.setAccOpenDate(stmt.getString(10));
                            //    rbiBondNominationDto1.setMaturityDate(stmt.getString(11));
                            //    rbiBondNominationDtoNomDetList.add(rbiBondNominationDto1);
                            //}
                            //rbiBondNominationDto.setRbiBondNominationDtoNomDetList(rbiBondNominationDtoNomDetList);
                        }
                        responseDataDTO.setResult(rbiBondNominationDto);

                    }
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
            // responseDataDTO.setResult(rbiBondNominationDto2);
            return responseDataDTO;

        }


    @Override
    public ResponseDataDTO modifyPanTorch(RbiBondNominationDto rbiBondNominationDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_mod_pan_tor.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                stmt.setString(index++, rbiBondNominationDto.getBondHolderName());
                stmt.setString(index++, rbiBondNominationDto.getPanNum());
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
                            temp.put("panNum", resultSet.getString(2));

                            torchOut.add(temp);
                        }
                        rbiBondNominationDto.setTorchValue(torchOut);
                        responseDataDTO.setResult(torchOut);
                    }

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
        responseDataDTO.setMessage(resultMsg);
       // responseDataDTO.setResult(rbiBondNominationDto);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiryPanTorch(RbiBondNominationDto rbiBondNominationDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_inq_pan_tor.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                stmt.setString(index++, rbiBondNominationDto.getBondHolderName());
                stmt.setString(index++, rbiBondNominationDto.getPanNum());
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
                            temp.put("panNum", resultSet.getString(2));
                            torchOut.add(temp);
                        }
                        rbiBondNominationDto.setTorchValue(torchOut);
                        responseDataDTO.setResult(torchOut);
                    }
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
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO blaNo(RbiBondNominationDto rbiBondNominationDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_mod_blaNo_tor.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                stmt.setString(index++, rbiBondNominationDto.getBondHolderName());
                stmt.setString(index++, rbiBondNominationDto.getPanNum());
                stmt.setString(index++, rbiBondNominationDto.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(rbiBondNominationDto.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("nameOfTheBondHolder", resultSet.getString(1));
                                temp.put("panNum", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));
                                temp.put("blaNo", resultSet.getString(4));

                                torchOut.add(temp);
                            }
                            rbiBondNominationDto.setTorchValue(torchOut);
                        }
                    }
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
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(rbiBondNominationDto);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO getInqBlaNo(RbiBondNominationDto rbiBondNominationDto) {

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_inq_blaNo_tor.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                stmt.setString(index++, rbiBondNominationDto.getBondHolderName());
                stmt.setString(index++, rbiBondNominationDto.getPanNum());
                stmt.setString(index++, rbiBondNominationDto.getBlaNo());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(rbiBondNominationDto.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(7);
                        List<HashMap<String, String>> torchOut = null;

                        if (resultSet != null) {
                            HashMap<String, String> temp = null;
                            torchOut = new ArrayList<>();
                            while (resultSet.next()) {
                                temp = new HashMap<>();

                                temp.put("nameOfTheBondHolder", resultSet.getString(1));
                                temp.put("panNum", resultSet.getString(2));
                                temp.put("modeOfHolding", resultSet.getString(3));
                                temp.put("blaNo", resultSet.getString(4));

                                torchOut.add(temp);
                            }
                            rbiBondNominationDto.setTorchValue(torchOut);
                        }
                    }
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
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(rbiBondNominationDto);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getsubscriptionIdMod(RbiBondNominationDto rbiBondNominationDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_mod_subId_tor.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                stmt.setString(index++, rbiBondNominationDto.getBondHolderName());
                stmt.setString(index++, rbiBondNominationDto.getPanNum());
                stmt.setString(index++, rbiBondNominationDto.getBlaNo());
                stmt.setString(index++, rbiBondNominationDto.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(rbiBondNominationDto.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
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
                                temp.put("subscriptionAmt", resultSet.getString(5));
                                temp.put("valueDate", resultSet.getString(6));

                                torchOut.add(temp);
                            }
                            rbiBondNominationDto.setTorchValue(torchOut);
                        }
                    }
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
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(rbiBondNominationDto);
        return responseDataDTO;
    }



    @Override
    public ResponseDataDTO getsubscriptionIdInq(RbiBondNominationDto rbiBondNominationDto) {

        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.pkg_gbm_rbi_bond_nominaton_inq_subId_tor.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondNominationDto.getSchemeCode());
                stmt.setString(index++, rbiBondNominationDto.getBondHolderName());
                stmt.setString(index++, rbiBondNominationDto.getPanNum());
                stmt.setString(index++, rbiBondNominationDto.getBlaNo());
                stmt.setString(index++, rbiBondNominationDto.getSubscriptionId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(rbiBondNominationDto.getBlaNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(8);
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
                                temp.put("subscriptionAmt", resultSet.getString(5));
                                temp.put("valueDate", resultSet.getString(6));

                                torchOut.add(temp);
                            }
                            rbiBondNominationDto.setTorchValue(torchOut);
                        }
                    }
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
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(rbiBondNominationDto);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO nomineeValidation(RbiBondNominationDto rbiBondNominationDto) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        List<HashMap<String, String>> resultList = new ArrayList<>();


        ResultSet resultSet = null;
        HashMap<String, String> resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareStatement("select MAX_NO_OF_NOMINEE from GBM_RBI_BOND_SCHEME_DETAILS  where VERIFIED_STATUS='Y' and IS_ACTIVE ='Y' and IS_DELETED ='N' and AUDIT_CRE_FLG = '1' and SCH_CODE = ?");
                stmt.setString(index, rbiBondNominationDto.getSchemeCode());
                resultSet = stmt.executeQuery();
                if (resultSet != null) {
                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("maxNoOfNominee", resultSet.getString("MAX_NO_OF_NOMINEE"));
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
        responseDataDTO.setResult(resultList);
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO fetchState(RbiBondNominationDto rbiBondNominationDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "Success"; // Default success message

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            // Get connection from the connection pool
            connection = connectionPoolFactory.getConnection();

            if (connection != null) {
                // SQL query to fetch city and state based on the pin code
                String sql = "SELECT city AS city, " +
                        "( " +
                        "    SELECT sub_code " +
                        "    FROM gbm_rbi_bond_sub_code_master " +
//                        "    WHERE code='037' AND sub_code_desc = state_name AND verified_status = 'Y' " +
                        "    WHERE code='037' AND sub_code_desc = state_name " +
                        "    AND is_deleted = 'N' " +
                        ") AS state_name " +
                        "FROM GBM_RBI_BOND_STATE_MASTER " +
                        "WHERE verified_status = 'Y' AND is_active = 'Y' " +
                        "AND is_deleted = 'N' AND audit_cre_flg = '1' " +
                        "AND pin_code = ?";

                // Prepare the statement and set the pin code parameter
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, rbiBondNominationDto.getPinCode());

                resultSet = stmt.executeQuery();

                List<HashMap<String, String>> tdsCode = new ArrayList<>();

                if (resultSet.next()) {
                    HashMap<String, String> temp = new HashMap<>();
                    temp.put("bondHldCity", resultSet.getString("city"));
                    temp.put("bondHldState", resultSet.getString("state_name"));
                    tdsCode.add(temp);
                } else {
                    resultMsg = "No data found for the given pin code Enter Manually.";
                    responseDataDTO.setResult(new ArrayList<>());
                }
                if (!tdsCode.isEmpty()) {
                    responseDataDTO.setResult(tdsCode);
                }

            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (SQLException e) {
            log.error("Database error occurred while fetching state info", e);
            resultMsg = "An error occurred while fetching the state information.";
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            resultMsg = "Unexpected error occurred while fetching the state information.";
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


}
