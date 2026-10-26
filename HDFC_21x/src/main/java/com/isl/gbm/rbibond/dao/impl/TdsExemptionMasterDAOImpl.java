package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.TdsExemptionMasterDAO;
import com.isl.gbm.rbibond.dto.BondTaxExcemptionMasterDTO;
import com.isl.gbm.rbibond.dto.reports.BondTdsITRFiledTwoYearsDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.*;

@Repository
public class TdsExemptionMasterDAOImpl extends HibernateDao implements TdsExemptionMasterDAO {
    private static final Logger log = LoggerFactory.getLogger(TdsExemptionMasterDAOImpl.class);
    @Autowired
    ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO getAddPanNoDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_ADD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getFlag());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {



                    if ("N".equals(bondTaxExcemptionMasterDTO.getFlag()) && bondTaxExcemptionMasterDTO.getPanNo()!= null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(5);
                        if (resultSet != null) {

                            resultList = new ArrayList<>();

                            while (resultSet.next()) {
                                resultMap = new HashMap<>();

                                resultMap.put("panNo", resultSet.getString(1));
                                resultMap.put("bondHolderName", resultSet.getString(2));
                                resultMap.put("exemptionDocumentType", resultSet.getString(3));
                                resultMap.put("declarationReceivedDate", resultSet.getString(4));
                                resultList.add(resultMap);
                            }
                        }
                    }

                    else if ("Y".equals(bondTaxExcemptionMasterDTO.getFlag())) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(5);
                        if (resultSet != null) {

                            resultList = new ArrayList<>();

                            while (resultSet.next()) {
                                resultMap = new HashMap<>();


                                resultMap.put("bondHolderName", resultSet.getString(1));
                                resultMap.put("panNo", resultSet.getString(2));
                                resultMap.put("dob", resultSet.getString(3));
                                resultMap.put("applicationStatus", resultSet.getString(4));
                                resultMap.put("communicationAddress", resultSet.getString(5));
                                resultMap.put("phoneNo", resultSet.getString(6));
                                resultMap.put("emailId", resultSet.getString(7));
                                resultMap.put("schemeName", resultSet.getString(8));
                                resultMap.put("modeOfHolding", resultSet.getString(9));
                                resultMap.put("blaNo", resultSet.getString(10));
                                resultMap.put("subscriptionId", resultSet.getString(11));
                                resultMap.put("mobileNo", resultSet.getString(13));
                                resultList.add(resultMap);

                            }
                        }
                    }

                }

            }
            else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        }catch (Exception e) {
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

//    @Override
//    public ResponseDataDTO getAddDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
//
//        Connection connection = null;
//        CallableStatement stmt = null;
//        String resultMsg = "";
//        HashMap<String, String> resultMap = null;
//        List<Map<String, String>> resultList = null;
//
//        try {
//            connection = connectionPoolFactory.getConnection();
//            if (connection != null) {
//                int index = 1;
//                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_ADD_SEL.getProcName());
//                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
//                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
//                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
//                stmt.setString(index++, "Y");
//                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
//                stmt.registerOutParameter(index++, Types.VARCHAR);
//                stmt.registerOutParameter(index, Types.VARCHAR);
//                stmt.execute();
//                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
//                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
//                resultMsg = stmt.getString(index);
//
//                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
//
//                    ResultSet resultSet = (ResultSet) stmt.getObject(5);
//                    log.info("resultset64" + resultSet);
//                    if (resultSet != null) {
//
//                        resultList = new ArrayList<>();
//
//                        while (resultSet.next()) {
//                            resultMap = new HashMap<>();
//                            resultMap.put("panNo", resultSet.getString(1));
//                            resultMap.put("bondHolderName", resultSet.getString(2));
//                            resultMap.put("dob", resultSet.getString(3));
//                            resultMap.put("effFromDate", resultSet.getString(4));
//                            resultMap.put("finYear", resultSet.getString(5));
//                            resultMap.put("schemeName", resultSet.getString(6));
//                            resultMap.put("modeOfHolding", resultSet.getString(7));
//                            resultMap.put("blaCount", resultSet.getString(8));
//                            resultMap.put("subScriptionCount", resultSet.getString(9));
//                            resultList.add(resultMap);
//                        }
//                    }
//                }
//
//            } else {
//                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
//            }
//
//        } catch (Exception e) {
//            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
//            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
//        } finally {
//            connectionPoolFactory.closeConnection(connection, stmt);
//        }
//
//        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
//        responseDataDTO.setMessage(resultMsg);
//        responseDataDTO.setResult(resultList);
//        return responseDataDTO;
//    }



    @Override
    public ResponseDataDTO getCertNumber(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_CERTI_NUMBER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getFinYear());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getCertificateType());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {


                    bondTaxExcemptionMasterDTO.setCertificateNumber(stmt.getString(6));
                    responseDataDTO.setResult(bondTaxExcemptionMasterDTO);
                }

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        }  catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getEffFromDate());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getCertificateType());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getCertificateNumber());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getNoTax());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getLowTax());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getNoTaxLimit());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getLowTaxPercentage());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getCertificateValidFromDate());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getCertificateValidToDate());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getFinYear());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getIncomeTaxAccount());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getEstimatedIncomeTaxDed());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getEstimatedTotalIncomeTaxDed());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getTotalNoOfForm());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getAggAmtIncome());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getDedReceivedDate());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getResidentialStatus());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getResidentIndividual());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getNatureOfIncome());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getOtherThanTaxYear());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getEstimateTotalTaxYearAmt());

                if(null!=bondTaxExcemptionMasterDTO && null!=bondTaxExcemptionMasterDTO.getTwoTaxYearsList() &&
                !bondTaxExcemptionMasterDTO.getTwoTaxYearsList().isEmpty() && bondTaxExcemptionMasterDTO.getTwoTaxYearsList().size()>0 &&
                null!=bondTaxExcemptionMasterDTO.getCertificateType() && ("TF006").equalsIgnoreCase(bondTaxExcemptionMasterDTO.getCertificateType()))
                {
                    stmt.setArray(index++, generateTDS_EXCEMPTION_MASTER_ARRAY_OBJ(connection, bondTaxExcemptionMasterDTO.getTwoTaxYearsList()));
                }
                else{
                    stmt.setNull(index++, Types.ARRAY, "GBM_TY_RBI_BOND_TDS_MASTER");
                }
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.DATABASE_ERROR;
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

    private ARRAY generateTDS_EXCEMPTION_MASTER_ARRAY_OBJ(Connection connection, List<BondTdsITRFiledTwoYearsDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("GBM_RBI_BOND_TDS_MASTER_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("GBM_TY_RBI_BOND_TDS_MASTER", connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        BondTdsITRFiledTwoYearsDTO dto = null;
        for (int indexC = 0; indexC < list.size(); indexC++) {
            dto = list.get(indexC);
            Object[] paramArray = new Object[3];
            paramArray[0] = dto.getAssessmentYear();
            paramArray[1] = dto.getAckNumber();
            String returnIncome = normalizeReturnIncomeValue(dto.getReturnIncome());
            if (returnIncome != null && !returnIncome.isEmpty()) {
                paramArray[2] = returnIncome;
            } else {
                paramArray[2] = null;
            }
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[indexC] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private String normalizeReturnIncomeValue(String returnIncome) {
        if (returnIncome == null) {
            return null;
        }
        return returnIncome.replace(",", "").trim();
    }

    @Override
    public ResponseDataDTO getHolderAge(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDT) {
        bondTaxExcemptionMasterDT.setAge("60");
        ResponseDataDTO responseDataDTO= new ResponseDataDTO();
        responseDataDTO.setMessage("SUCCESS");
        responseDataDTO.setResult(bondTaxExcemptionMasterDT);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO getModifyPanNoDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
                stmt.setString(index++,"");
                stmt.setString(index++, "");
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
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet  resultSet = (ResultSet) stmt.getObject(15);
                    if (resultSet!= null) {
                        resultList = new ArrayList<>();
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("panNo", resultSet.getString(1));
                            resultMap.put("bondHolderName", resultSet.getString(2));
                            resultMap.put("effFromDate", resultSet.getString(4));
                            resultMap.put("panId", resultSet.getString(5));
                            resultList.add(resultMap);
                        }
                    }

                }
            }

            else {
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
    public ResponseDataDTO getModifyDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getFlag());

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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    if("Y".equals(bondTaxExcemptionMasterDTO.getFlag())) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(16);

                        Map<String, Object> tempMap = null;

                        if (resultSet != null) {
                            tempMap = DataBaseUtils.generateOldValueNewValueMap(resultSet);
                        }
                        bondTaxExcemptionMasterDTO.setOldNewValueMap(tempMap);

                        ResultSet customerResultSet = (ResultSet) stmt.getObject(15);
                        if (customerResultSet != null) {
                            resultList = new ArrayList<>();

                            while (customerResultSet.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("bondHolderName", customerResultSet.getString(1));
                                resultMap.put("panNo", customerResultSet.getString(2));
                                resultMap.put("dob", customerResultSet.getString(3));
                                resultMap.put("applicationStatus", customerResultSet.getString(4));
                                resultMap.put("communicationAddress", customerResultSet.getString(5));
                                resultMap.put("phoneNo", customerResultSet.getString(6));
                                resultMap.put("emailId", customerResultSet.getString(7));
                                resultMap.put("schemeName", customerResultSet.getString(8));
                                resultMap.put("modeOfHolding", customerResultSet.getString(9));
                                resultMap.put("blaNo", customerResultSet.getString(10));
                                resultMap.put("subscriptionId", customerResultSet.getString(11));
                                resultMap.put("mobileNo", customerResultSet.getString(13));

                                resultList.add(resultMap);
                            }
                            bondTaxExcemptionMasterDTO.setSchemeDetailMap(resultList);
                        }
                    }
                    if ("N".equals(bondTaxExcemptionMasterDTO.getFlag())) {
                        ResultSet  resultSet1 = (ResultSet) stmt.getObject(16);
                        if (resultSet1 != null) {

                            resultList = new ArrayList<>();

                            while (resultSet1.next()) {
                                resultMap = new HashMap<>();

                                resultMap.put("panNo", resultSet1.getString(1));
                                resultMap.put("bondHolderName", resultSet1.getString(2));
                                resultMap.put("exemptionDocumentType", resultSet1.getString(3));
                                resultMap.put("declarationReceivedDate", resultSet1.getString(4));
                                resultList.add(resultMap);

                            }
                            bondTaxExcemptionMasterDTO.setSchemeDetailMap(resultList);
                        }
                    }else if(bondTaxExcemptionMasterDTO.getPanNo() != null){
                        ResultSet  resultSet1 = (ResultSet) stmt.getObject(15);
                        if (resultSet1 != null) {

                            resultList = new ArrayList<>();

                            while (resultSet1.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("bondHolderName", resultSet1.getString(1));
                                resultMap.put("panNo", resultSet1.getString(2));
                                resultMap.put("dob", resultSet1.getString(3));
                                resultMap.put("applicationStatus", resultSet1.getString(4));
                                resultMap.put("communicationAddress", resultSet1.getString(5));
                                resultMap.put("phoneNo", resultSet1.getString(6));
                                resultMap.put("emailId", resultSet1.getString(7));
                                resultMap.put("schemeName", resultSet1.getString(8));
                                resultMap.put("modeOfHolding", resultSet1.getString(9));
                                resultMap.put("blaNo", resultSet1.getString(10));
                                resultMap.put("subscriptionId", resultSet1.getString(11));
                                resultMap.put("mobileNo", resultSet1.getString(13));

                                resultList.add(resultMap);

                            }
                            bondTaxExcemptionMasterDTO.setSchemeDetailMap(resultList);
                        }
                    }
                }


                bondTaxExcemptionMasterDTO.setPkColValOut(stmt.getString(5));
                bondTaxExcemptionMasterDTO.setCreatedBy(stmt.getString(6));
                bondTaxExcemptionMasterDTO.setCreatedDate(stmt.getString(7));
                bondTaxExcemptionMasterDTO.setModifiedBy(stmt.getString(8));
                bondTaxExcemptionMasterDTO.setModifiedDate(stmt.getString(9));
                bondTaxExcemptionMasterDTO.setVerifiedBy(stmt.getString(10));
                bondTaxExcemptionMasterDTO.setVerifiedDate(stmt.getString(11));
                bondTaxExcemptionMasterDTO.setPreOprType(stmt.getString(12));
                bondTaxExcemptionMasterDTO.setVerifyStatus(stmt.getString(13));
                bondTaxExcemptionMasterDTO.setRejectionRemarks(stmt.getString(14));
            }

            else {
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
        responseDataDTO.setResult(bondTaxExcemptionMasterDTO);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO modifyCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanId());
                log.info("array details {}",DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, bondTaxExcemptionMasterDTO.getTaxExcemptionModifyDtoList()));
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, bondTaxExcemptionMasterDTO.getTaxExcemptionModifyDtoList()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO getVerDelPanDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
                stmt.setString(index++, "N");
                stmt.setString(index++, "");
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(15);
                    if (resultSet != null) {

                        resultList = new ArrayList<>();

                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("panNo", resultSet.getString(1));
                            resultMap.put("bondHolderName", resultSet.getString(2));
                            resultMap.put("id", resultSet.getString(3));
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
    public ResponseDataDTO getVerifyDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        log.info("getVerifyDetails");
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {


    if(bondTaxExcemptionMasterDTO.getPanNo() != null && !bondTaxExcemptionMasterDTO.getPanNo().isEmpty())
    {
        ResultSet resultSet = (ResultSet) stmt.getObject(15);

        Map<String, Object> tempMap = null;

        if (resultSet != null) {
            tempMap = DataBaseUtils.generateOldValueNewValueMap(resultSet);
        }

        bondTaxExcemptionMasterDTO.setOldNewValueMap(tempMap);
    }

    if((bondTaxExcemptionMasterDTO.getPanNo()==null || bondTaxExcemptionMasterDTO.getPanNo().isEmpty())){
                    ResultSet  resultSet1 = (ResultSet) stmt.getObject(15);

                    if (resultSet1!= null) {

                        resultList =new ArrayList<>();

                        while (resultSet1.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("panNo", resultSet1.getString(1));
                            resultMap.put("bondHolderName", resultSet1.getString(2));
                            resultMap.put("exemptionDocumentType", resultSet1.getString(3));
                            resultMap.put("declarationReceivedDate", resultSet1.getString(4));
                            resultList.add(resultMap);
                        }
                        bondTaxExcemptionMasterDTO.setSchemeDetailMap(resultList);
                    }

                }
    else if((bondTaxExcemptionMasterDTO.getPanNo() != null && !bondTaxExcemptionMasterDTO.getPanNo().isEmpty())){
        ResultSet resultSet1 = (ResultSet) stmt.getObject(14);
                    if (resultSet1 != null) {
                        resultList = new ArrayList<>();
                        while (resultSet1.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("bondHolderName", resultSet1.getString(1));
                            resultMap.put("panNo", resultSet1.getString(2));
                            resultMap.put("dob", resultSet1.getString(3));
                            resultMap.put("applicationStatus", resultSet1.getString(4));
                            resultMap.put("communicationAddress", resultSet1.getString(5));
                            resultMap.put("phoneNo", resultSet1.getString(6));
                            resultMap.put("emailId", resultSet1.getString(7));
                            resultMap.put("schemeName", resultSet1.getString(8));
                            resultMap.put("modeOfHolding", resultSet1.getString(9));
                            resultMap.put("blaNo", resultSet1.getString(10));
                            resultMap.put("subscriptionId", resultSet1.getString(11));
                            resultMap.put("mobileNo", resultSet1.getString(12));

                            resultList.add(resultMap);

                        }
                        bondTaxExcemptionMasterDTO.setSchemeDetailMap(resultList);
                    }
                }
            }



            bondTaxExcemptionMasterDTO.setPkColValOut(stmt.getString(4));
                bondTaxExcemptionMasterDTO.setCreatedBy(stmt.getString(5));
                bondTaxExcemptionMasterDTO.setCreatedDate(stmt.getString(6));
                bondTaxExcemptionMasterDTO.setModifiedBy(stmt.getString(7));
                bondTaxExcemptionMasterDTO.setModifiedDate(stmt.getString(8));
                bondTaxExcemptionMasterDTO.setVerifiedBy(stmt.getString(9));
                bondTaxExcemptionMasterDTO.setVerifiedDate(stmt.getString(10));
                bondTaxExcemptionMasterDTO.setPreOprType(stmt.getString(11));
                bondTaxExcemptionMasterDTO.setVerifyStatus(stmt.getString(12));
                bondTaxExcemptionMasterDTO.setRejectionRemarks(stmt.getString(13));


            }

            else {
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
        responseDataDTO.setResult(bondTaxExcemptionMasterDTO);
        log.info("Verification Details {}", bondTaxExcemptionMasterDTO);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO getDeleteDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
                stmt.setString(index++, "Y");
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanId());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

                    ResultSet  resultSet = (ResultSet) stmt.getObject(15);

                    Map<String, Object> tempMap = null;


                    if(resultSet!=null) {
                        tempMap = DataBaseUtils.generateOldValueNewValueMap(resultSet);
                    }

                    bondTaxExcemptionMasterDTO.setOldNewValueMap(tempMap);

                    ResultSet  resultSet1 = (ResultSet) stmt.getObject(16);

                    if (resultSet1!= null) {

                        resultList =new ArrayList<>();

                        while (resultSet1.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("schemeName", resultSet1.getString(1));
                            resultMap.put("modeOfHolding", resultSet1.getString(2));
                            resultMap.put("blaCount", resultSet1.getString(3));
                            resultMap.put("SubscriptionCount", resultSet1.getString(4));
                            resultList.add(resultMap);
                        }
                        bondTaxExcemptionMasterDTO.setSchemeDetailMap(resultList);
                    }

                }


                bondTaxExcemptionMasterDTO.setPkColValOut(stmt.getString(6));
                bondTaxExcemptionMasterDTO.setCreatedBy(stmt.getString(7));
                bondTaxExcemptionMasterDTO.setCreatedDate(stmt.getString(8));
                bondTaxExcemptionMasterDTO.setModifiedBy(stmt.getString(9));
                bondTaxExcemptionMasterDTO.setModifiedDate(stmt.getString(10));
                bondTaxExcemptionMasterDTO.setVerifiedBy(stmt.getString(11));
                bondTaxExcemptionMasterDTO.setVerifiedDate(stmt.getString(12));
                bondTaxExcemptionMasterDTO.setPreOprType(stmt.getString(13));
                bondTaxExcemptionMasterDTO.setVerifyStatus(stmt.getString(14));


            }

            else {
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
        responseDataDTO.setResult(bondTaxExcemptionMasterDTO);
        log.info("Verification Details {}" , bondTaxExcemptionMasterDTO);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO deleteCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));
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
    public ResponseDataDTO getInquiryPanDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
                stmt.setString(index++,"");
                stmt.setString(index++, "");
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet  resultSet = (ResultSet) stmt.getObject(15);
                    if (resultSet!= null) {
                        resultList = new ArrayList<>();
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("panNo", resultSet.getString(1));
                            resultMap.put("bondHolderName", resultSet.getString(2));
                            resultList.add(resultMap);
                        }
                    }

                }
            }

            else {
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
    public ResponseDataDTO getInquiryDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getFlag());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getVersionNumber());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getTableId());
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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {

              if(("Y".equals(bondTaxExcemptionMasterDTO.getFlag()))) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(18);

                        Map<String, Object> tempMap = null;


                        if (resultSet != null) {
                            tempMap = DataBaseUtils.generateOldValueNewValueMap(resultSet);
                        }

                        bondTaxExcemptionMasterDTO.setOldNewValueMap(tempMap);

                    }
              if("N".equals(bondTaxExcemptionMasterDTO.getFlag())&& bondTaxExcemptionMasterDTO.getPanNo()!=null) {
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(18);
                        if (resultSet1 != null) {
                            resultList = new ArrayList<>();
                            while (resultSet1.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("panNo", resultSet1.getString(1));
                                resultMap.put("bondHolderName", resultSet1.getString(2));

                                resultList.add(resultMap);

                            }
                            bondTaxExcemptionMasterDTO.setSchemeDetailMap(resultList);
                        }
                    }
             else if(bondTaxExcemptionMasterDTO.getPanNo()!=null && "S".equals(bondTaxExcemptionMasterDTO.getFlag())) {
                        ResultSet resultSet1 = (ResultSet) stmt.getObject(18);
                        if (resultSet1 != null) {
                            resultList = new ArrayList<>();
                            while (resultSet1.next()) {
                                resultMap = new HashMap<>();
                                resultMap.put("exemptionDocumentType", resultSet1.getString(1));
                                resultMap.put("declarationReceivedDate", resultSet1.getString(2));
                                resultMap.put("status", resultSet1.getString(3));
                                resultMap.put("tableId", resultSet1.getString(4));

                                resultList.add(resultMap);

                            }
                            bondTaxExcemptionMasterDTO.setSchemeDetailMap(resultList);
                        }
                    }


              else if(bondTaxExcemptionMasterDTO.getPanNo()!=null && "Y".equals(bondTaxExcemptionMasterDTO.getFlag()) &&
                      bondTaxExcemptionMasterDTO.getVersionNumber()==null && bondTaxExcemptionMasterDTO.getTableId()!= null) {
                  ResultSet resultSet1 = (ResultSet) stmt.getObject(17);
                  if (resultSet1 != null) {
                      resultList = new ArrayList<>();
                      while (resultSet1.next()) {
                          resultMap = new HashMap<>();
                          resultMap.put("bondHolderName", resultSet1.getString(1));
                          resultMap.put("panNo", resultSet1.getString(2));
                          resultMap.put("dob", resultSet1.getString(3));
                          resultMap.put("applicationStatus", resultSet1.getString(4));
                          resultMap.put("communicationAddress", resultSet1.getString(5));
                          resultMap.put("phoneNo", resultSet1.getString(6));
                          resultMap.put("emailId", resultSet1.getString(7));
                          resultMap.put("schemeName", resultSet1.getString(8));
                          resultMap.put("modeOfHolding", resultSet1.getString(9));
                          resultMap.put("blaNo", resultSet1.getString(10));
                          resultMap.put("subscriptionId", resultSet1.getString(11));
                          resultMap.put("mobileNo", resultSet1.getString(12));

                          resultList.add(resultMap);

                      }
                      bondTaxExcemptionMasterDTO.setSchemeDetailMap(resultList);
                  }
              }
                }


                bondTaxExcemptionMasterDTO.setPkColValOut(stmt.getString(7));
                bondTaxExcemptionMasterDTO.setCreatedBy(stmt.getString(8));
                bondTaxExcemptionMasterDTO.setCreatedDate(stmt.getString(9));
                bondTaxExcemptionMasterDTO.setModifiedBy(stmt.getString(10));
                bondTaxExcemptionMasterDTO.setModifiedDate(stmt.getString(11));
                bondTaxExcemptionMasterDTO.setVerifiedBy(stmt.getString(12));
                bondTaxExcemptionMasterDTO.setVerifiedDate(stmt.getString(13));
                bondTaxExcemptionMasterDTO.setPreOprType(stmt.getString(14));
                bondTaxExcemptionMasterDTO.setVerifyStatus(stmt.getString(15));
                bondTaxExcemptionMasterDTO.setRejectionRemarks(stmt.getString(16));
                bondTaxExcemptionMasterDTO.setTableId(stmt.getString(17));



            }

            else {
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
        responseDataDTO.setResult(bondTaxExcemptionMasterDTO);
        return responseDataDTO;

    }


    @Override
    public ResponseDataDTO getVersionNoDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        HashMap<String, String> resultMap = null;
        List<Map<String, String>> resultList = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TAX_EXP_MAS_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getPanNo());
                stmt.setString(index++, bondTaxExcemptionMasterDTO.getVersionNumber());
                stmt.setString(index++, "V");
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}",CommonConstants.STATUS_MESSAGE , stmt.getString(index));

                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equalsIgnoreCase(CommonConstants.SUCCESS)) {
                    ResultSet  resultSet = (ResultSet) stmt.getObject(15);
                    if (resultSet!= null) {
                        resultList = new ArrayList<>();
                        while (resultSet.next()) {
                            resultMap = new HashMap<>();
                            resultMap.put("versionNumber", resultSet.getString(1));
                            resultMap.put("effFromDate", resultSet.getString(2));
                            resultList.add(resultMap);
                        }
                    }

                }
            }

            else {
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
    public String getFinancialYear(final Date solDate) {
        final Session session =this.getSession();
        final java.sql.Date date = new java.sql.Date(solDate.getTime());
        final NativeQuery nativeQuery = session.createNativeQuery(
                "select FN_GET_FIN_START_YR(:solDate)||'-'||substr(FN_GET_FIN_END_YR(:solDate),3,2) from dual");
        nativeQuery.setParameter("solDate", date);
        return (String) nativeQuery.getResultList()
                .stream().findFirst().orElse("");
    }
}
