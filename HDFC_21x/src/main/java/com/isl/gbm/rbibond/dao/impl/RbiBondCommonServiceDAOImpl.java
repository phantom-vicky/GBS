package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.CommonDataDTO;
import com.isl.gbm.common.dto.CommonFlagDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.common.utils.CbdtProcNamesEnum;
import com.isl.gbm.rbibond.dao.RbiBondCommonServiceDAO;
import com.isl.gbm.rbibond.dto.RbiBondCommonGenericTableDto;
import com.isl.gbm.rbibond.dto.RbiBondMetaDataRequestDto;
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
import org.springframework.util.CollectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RbiBondCommonServiceDAOImpl implements RbiBondCommonServiceDAO {

    private static final Logger log = LoggerFactory.getLogger(RbiBondCommonServiceDAOImpl.class);

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO getModuleDetails(RbiBondMetaDataRequestDto metaDataRequestDto) {
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
//                if(metaDataRequestDto.getReferenceId().equalsIgnoreCase("ACTSCH")){
//                    stmt = connection.prepareStatement("SELECT sub_code AS subcode,sub_code_desc AS subcodedescription FROM gbm_sub_code_master t1 INNER JOIN gbm_rbi_bond_scheme_details T2 ON t1.sub_code = T2.sch_code WHERE T2.is_deleted='N' AND T2.is_scheme_active='Y'");
//                }
//                else{
//                    stmt = connection.prepareStatement("SELECT T2.sub_code AS subCode,T2.sub_code_desc AS subCodeDescription FROM gbm_code_master T1 INNER JOIN gbm_sub_code_master T2 ON T1.code =T2.code WHERE T2.code='012' AND T2.verified_status ='Y' AND T2.is_Active ='Y' AND T2.is_deleted ='N'");
//                }
                if(metaDataRequestDto.getReferenceId().equalsIgnoreCase("ACTSCH")){
                    stmt = connection.prepareStatement("SELECT sub_code AS subcode, sub_code_desc AS subcodedescription, CASE WHEN t2.scheme_status = 'C' THEN 'Current' WHEN t2.scheme_status = 'A' THEN 'Active' WHEN t2.scheme_status = 'CL'  THEN 'Closed' ELSE NULL END AS scheme_status FROM gbm_sub_code_master  t1 INNER JOIN gbm_rbi_bond_scheme_details t2 ON t1.sub_code = t2.sch_code WHERE t2.is_deleted = 'N' AND t2.is_scheme_active = 'Y'");
                }
                else{
                    stmt = connection.prepareStatement("SELECT t2.sub_code AS subcode, t2.sub_code_desc AS subcodedescription, CASE  WHEN t3.scheme_status = 'C'   THEN   'Current'  WHEN t3.scheme_status = 'A'   THEN   'Active'  WHEN t3.scheme_status = 'CL'  THEN   'Closed'  ELSE   NULL  END AS scheme_status FROM gbm_code_master t1 INNER JOIN gbm_sub_code_master   t2 ON t1.code = t2.code INNER JOIN gbm_rbi_bond_scheme_details   t3 ON t3.sch_code = t2.sub_code WHERE t2.code = '012' AND t2.verified_status = 'Y' AND t2.is_active = 'Y' AND t2.is_deleted = 'N'");
                }

                //stmt.setString(index, metaDataRequestDto.getReferenceId());
                resultSet = stmt.executeQuery();

                if (resultSet != null) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    List<HashMap<String, String>> resultList = new ArrayList<>();

                    System.out.println("Result Set Data:");

                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("subCode", resultSet.getString("subCode"));
                        resultMap.put("subCodeDescription", resultSet.getString("subCodeDescription"));
                        resultMap.put("schemeStatus", resultSet.getString("SCHEME_STATUS"));

                        resultList.add(resultMap);
                    }
                    responseDataDTO.setResult(resultList);
                    resultMsg = CommonConstants.SUCCESS_CONST;

                    if(resultList.isEmpty()) {
                        resultMsg = "NO ACTIVE SCHEMES AVAILABLE";
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
    public ResponseDataDTO getModule(RbiBondMetaDataRequestDto metaDataRequestDto) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        ResultSet resultSet = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareStatement("SELECT TO_CHAR(PARAMETERS_EFF_FROM_DATE,'YYYY-MM-DD HH24:MI:SS') AS PARAMETERS_EFF_FROM_DATE FROM GBM_RBI_BOND_SCHEME_DETAILS WHERE SCH_CODE =? AND verified_status ='Y' AND IS_ACTIVE='Y' AND IS_DELETED ='N'");
                stmt.setString(1, metaDataRequestDto.getReferenceId());
                resultSet = stmt.executeQuery();
                if (resultSet != null) {
                    while (resultSet.next()) {
                        responseDataDTO.setResult(resultSet.getString("PARAMETERS_EFF_FROM_DATE"));
                    }
                    resultMsg = CommonConstants.SUCCESS_CONST;
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
    public ResponseDataDTO getFinancialYear(RbiBondMetaDataRequestDto metaDataRequestDto) {
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
                        "CASE WHEN EXTRACT(MONTH FROM sol_date) <= 3 THEN TO_CHAR(EXTRACT(YEAR FROM sol_date) - 1) || '-' || TO_CHAR(EXTRACT(YEAR FROM sol_date))\n" +
                        "     ELSE TO_CHAR(EXTRACT(YEAR FROM sol_date)) || '-' || TO_CHAR(EXTRACT(YEAR FROM sol_date) + 1)\n" +
                        "END AS cur_fin_year,\n" +
                        "CASE WHEN EXTRACT(MONTH FROM sol_date) <= 3 THEN TO_CHAR(EXTRACT(YEAR FROM sol_date) - 2) || '-' || TO_CHAR(EXTRACT(YEAR FROM sol_date)-1)\n" +
                        "     ELSE TO_CHAR(EXTRACT(YEAR FROM sol_date)-1) || '-' || TO_CHAR(EXTRACT(YEAR FROM sol_date) )\n" +
                        "END AS cur_fin_year_1\n" +
                        "FROM gbm_sol_date");
                resultSet = stmt.executeQuery();
                if (resultSet != null) {
//                    List<HashMap<String, String>> resultList = new ArrayList<>();

                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("cur_fin_year", resultSet.getString("cur_fin_year"));
                        resultMap.put("cur_fin_year_1", resultSet.getString("cur_fin_year_1"));
//                        resultList.add(resultMap);
                    }
                    responseDataDTO.setResult(resultMap);
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


    @Override
    public ResponseDataDTO getDocumentDetails() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        CommonFlagDTO mappedCommonFlagDTO = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareStatement("SELECT code AS CODE FROM GBM_RBI_BOND_DOC_MAN_NONMAN WHERE is_mandatory='Y' and verified_status='Y'");
                resultSet = stmt.executeQuery();
                if (resultSet != null) {
                    List<CommonFlagDTO> mappedList = new ArrayList<>();
                    while (resultSet.next()) {
                        mappedCommonFlagDTO=new CommonFlagDTO();
                        mappedCommonFlagDTO.setCode(resultSet.getString("CODE"));
                        mappedList.add(mappedCommonFlagDTO);
                    }
                    resultMsg = CommonConstants.SUCCESS_CONST;
                    responseDataDTO.setResult(mappedList);
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
    public ResponseDataDTO getMetaData(String referenceId) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_FETCH_METADATA.getProcName());
                stmt.setString(index++, referenceId);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(3) != null && stmt.getString(3).equals(CommonConstants.SUCCESS)) {
                    responseDataDTO.setResult(DataBaseUtils.getGenericColumnValueFromResultSet((ResultSet) stmt.getObject(2)));
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
    public ResponseDataDTO getRemarksList(String referenceId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";

        ResultSet resultSet = null;
        HashMap resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareStatement("SELECT  (sub_code) as subCode,  (sub_code_desc) as subCodeDescription\n" +
                        "        FROM  GBM_RBI_BOND_REJ_RESN_MST\n" +
                        "        WHERE code = ? and is_deleted = 'N'\n" +
                        "        order by subCodeDescription asc");
                stmt.setString(index, referenceId);
                resultSet = stmt.executeQuery();
                if (resultSet != null) {
                    List<Map> resultList = new ArrayList<>();

                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("subCode", resultSet.getString("subCode"));
                        resultMap.put("subCodeDescription", resultSet.getString("subCodeDescription"));
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

    @Override
    public ResponseDataDTO getMetaDatList(String listType) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";

        ResultSet resultSet = null;
        HashMap resultMap = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareStatement("SELECT sub_code as subCode,sub_code_desc as subCodeDescription FROM GBM_CBDT_SUB_CODE_MASTER    \n" +
                        "WHERE CODE =(SELECT code FROM GBM_CBDT_CODE_MASTER  WHERE CODE_NAME = ? )\n" +
                        "and IS_DELETED = 'N' order by sub_code");
                stmt.setString(index, listType);
                resultSet = stmt.executeQuery();
                if (resultSet != null) {
                    List<Map> resultList = new ArrayList<>();

                    while (resultSet.next()) {
                        resultMap = new HashMap<>();
                        resultMap.put("subCode", resultSet.getString("subCode"));
                        resultMap.put("subCodeDescription", resultSet.getString("subCodeDescription"));
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

    @Override
    public ResponseDataDTO genericSelect(RbiBondCommonGenericTableDto commonGenericTableDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                StructDescriptor columnListStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COLUMN_LIST, connection);
                ArrayDescriptor columnListDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_COLUMN_LIST, connection);

                STRUCT[] columnListStructs = new STRUCT[commonGenericTableDto.getColumnList().size()];
                Map<String, Object> paramMap = null;
                for (int index = 0; index < commonGenericTableDto.getColumnList().size(); index++) {
                    paramMap = new HashMap<>();
                    paramMap.put("COLUMN_NAME", commonGenericTableDto.getColumnList().get(index));

                    columnListStructs[index] = new STRUCT(columnListStruct, connection, paramMap);
                }
                ARRAY columnListArray = new ARRAY(columnListDescriptor, connection, columnListStructs);


                ARRAY conditionListArray = null;
                if (!CollectionUtils.isEmpty(commonGenericTableDto.getConditionList()))

                {
                    StructDescriptor conditionListStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COLUMN_CONDITIONS, connection);
                    ArrayDescriptor conditionListDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_COLUMN_CONDITIONS, connection);

                    STRUCT[] conditionListStructs = new STRUCT[commonGenericTableDto.getConditionList().size()];
                    Map<String, Object> parameterMap = null;
                    for (int index = 0; index < commonGenericTableDto.getConditionList().size(); index++) {
                        parameterMap = new HashMap<>();
                        parameterMap.put("COLUMN_NAME", commonGenericTableDto.getConditionList().get(index).getColName());
                        parameterMap.put("COL_VALUE", commonGenericTableDto.getConditionList().get(index).getValue());
                        parameterMap.put("CONDITION", commonGenericTableDto.getConditionList().get(index).getCondition());
                        parameterMap.put("CONJUCTION", commonGenericTableDto.getConditionList().get(index).getConjunction());
                        parameterMap.put("CON_ORDER", commonGenericTableDto.getConditionList().get(index).getOrder());

                        conditionListStructs[index] = new STRUCT(conditionListStruct, connection, parameterMap);
                    }
                    conditionListArray = new ARRAY(conditionListDescriptor, connection, conditionListStructs);
                }
                int index = 1;
                stmt = connection.prepareCall(CbdtProcNamesEnum.GENERIC_SELECT.getProcName());

                stmt.setString(index++, commonGenericTableDto.getTable());
                stmt.setArray(index++, columnListArray);

                if (conditionListArray != null)
                    stmt.setArray(index++, conditionListArray);
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COLUMN_CONDITIONS);

                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));

                resultMsg = stmt.getString(index);

                if (stmt.getString(5) != null && stmt.getString(5).equals(CommonConstants.SUCCESS)) {
                    responseDataDTO.setResult(DataBaseUtils.getGenericColumnValueFromResultSet((ResultSet) stmt.getObject(4)));
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
    public ResponseDataDTO genericTableInsert(RbiBondCommonGenericTableDto commonGenericTableDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                log.info("genericTableInsertDTO : {}" , commonGenericTableDto);
                int index = 1;
                stmt = connection.prepareCall(CbdtProcNamesEnum.GENERIC_INSERT.getProcName());
                stmt.setString(index++, commonGenericTableDto.getTable());
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, commonGenericTableDto.getColumnValues()));
                stmt.setString(index++, "PENSION");
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, "Report Master Page");
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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
    public ResponseDataDTO genericTableModify(RbiBondCommonGenericTableDto commonGenericTableDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                log.info("genericTableModifyDTO : {}" , commonGenericTableDto);
                int index = 1;
                stmt = connection.prepareCall(CbdtProcNamesEnum.GENERIC_MODIFY.getProcName());
                stmt.setString(index++, commonGenericTableDto.getTable());
                stmt.setArray(index++, DataBaseUtils.generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, commonGenericTableDto.getColumnValues()));
                stmt.setString(index++, commonGenericTableDto.getPkColumnValue().toString());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, "PENSION");
                stmt.setString(index++, "Remarks");
                stmt.setString(index++, "Report Master Page");
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
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

    public ResponseDataDTO genericTableVerifySelect(RbiBondCommonGenericTableDto commonGenericTableDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                StructDescriptor columnListStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COLUMN_LIST, connection);
                ArrayDescriptor columnListDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_COLUMN_LIST, connection);

                STRUCT[] columnListStructs = new STRUCT[commonGenericTableDto.getColumnList().size()];
                Map<String, Object> paramMap = null;
                for (int index = 0; index < commonGenericTableDto.getColumnList().size(); index++) {
                    paramMap = new HashMap<>();
                    paramMap.put("COLUMN_NAME", commonGenericTableDto.getColumnList().get(index));

                    columnListStructs[index] = new STRUCT(columnListStruct, connection, paramMap);
                }
                ARRAY columnListArray = new ARRAY(columnListDescriptor, connection, columnListStructs);

                int index = 1;
                stmt = connection.prepareCall(CbdtProcNamesEnum.GENERIC_SEL_VERIFY.getProcName());
                stmt.setString(index++, commonGenericTableDto.getTable());
                stmt.setString(index++, commonGenericTableDto.getPkColumnValue().toString());
                stmt.setArray(index++, columnListArray);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.TIMESTAMP);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.TIMESTAMP);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.TIMESTAMP);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(13) != null && stmt.getString(13).equals(CommonConstants.SUCCESS)) {
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("createdBy", stmt.getString(4));
                    resultMap.put("createdDate", (stmt.getTimestamp(5) == null) ? null : stmt.getTimestamp(5).getTime());
                    resultMap.put("lastModifiedBy", stmt.getString(6));
                    resultMap.put("lastModifiedDate", (stmt.getTimestamp(7) == null) ? null : stmt.getTimestamp(7).getTime());
                    resultMap.put("lastVerifiedBy", stmt.getString(8));
                    resultMap.put("lastVerifiedDate", (stmt.getTimestamp(9) == null) ? null : stmt.getTimestamp(9).getTime());
                    resultMap.put("previousOperation", stmt.getString(10));
                    resultMap.put("verifiedStatus", stmt.getString(11));
                    resultMap.put("model", DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(12)));
                    responseDataDTO.setResult(resultMap);
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
    public ResponseDataDTO genericTableVerify(RbiBondCommonGenericTableDto commonGenericTableDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                log.info("genericTableVerifyDTO : {}" , commonGenericTableDto);
                int index = 1;
                stmt = connection.prepareCall(CbdtProcNamesEnum.GENERIC_VERIFY.getProcName());
                stmt.setString(index++, commonGenericTableDto.getTable());
                stmt.setString(index++, commonGenericTableDto.getPkColumnValue().toString());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, "PENSION");
                stmt.setString(index++, "Remarks");
                stmt.setString(index++, "Report Master Page");
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
    public ResponseDataDTO genericTableDelete(RbiBondCommonGenericTableDto commonGenericTableDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                log.info("genericTableDeleteDTO : {}" ,commonGenericTableDto);
                int index = 1;
                stmt = connection.prepareCall(CbdtProcNamesEnum.GENERIC_DELETE.getProcName());
                stmt.setString(index++, commonGenericTableDto.getTable());
                stmt.setString(index++, commonGenericTableDto.getPkColumnValue().toString());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, "PENSION");
                stmt.setString(index++, "Remarks");
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
    public ResponseDataDTO genericSelectUnverified(RbiBondCommonGenericTableDto commonGenericTableDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                StructDescriptor columnListStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COLUMN_LIST, connection);
                ArrayDescriptor columnListDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_COLUMN_LIST, connection);

                STRUCT[] columnListStructs = new STRUCT[commonGenericTableDto.getColumnList().size()];
                Map<String, Object> paramMap = null;
                for (int index = 0; index < commonGenericTableDto.getColumnList().size(); index++) {
                    paramMap = new HashMap<>();
                    paramMap.put("COLUMN_NAME", commonGenericTableDto.getColumnList().get(index));

                    columnListStructs[index] = new STRUCT(columnListStruct, connection, paramMap);
                }
                ARRAY columnListArray = new ARRAY(columnListDescriptor, connection, columnListStructs);


                ARRAY conditionListArray = null;
                if (!CollectionUtils.isEmpty(commonGenericTableDto.getConditionList())) {
                    StructDescriptor conditionListStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COLUMN_CONDITIONS, connection);
                    ArrayDescriptor conditionListDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_COLUMN_CONDITIONS, connection);

                    STRUCT[] conditionListStructs = new STRUCT[commonGenericTableDto.getConditionList().size()];
                    Map<String, Object> parameterMap = null;
                    for (int index = 0; index < commonGenericTableDto.getConditionList().size(); index++) {
                        parameterMap = new HashMap<>();
                        parameterMap.put("COLUMN_NAME", commonGenericTableDto.getConditionList().get(index).getColName());
                        parameterMap.put("COL_VALUE", commonGenericTableDto.getConditionList().get(index).getValue());
                        parameterMap.put("CONDITION", commonGenericTableDto.getConditionList().get(index).getCondition());
                        parameterMap.put("CONJUCTION", commonGenericTableDto.getConditionList().get(index).getConjunction());
                        parameterMap.put("CON_ORDER", commonGenericTableDto.getConditionList().get(index).getOrder());

                        conditionListStructs[index] = new STRUCT(conditionListStruct, connection, parameterMap);
                    }
                    conditionListArray = new ARRAY(conditionListDescriptor, connection, conditionListStructs);
                }


                int index = 1;
                stmt = connection.prepareCall(CbdtProcNamesEnum.GENERIC_LIST_UNVER.getProcName());

                stmt.setString(index++, commonGenericTableDto.getTable());
                stmt.setArray(index++, columnListArray);

                if (conditionListArray != null)
                    stmt.setArray(index++, conditionListArray);
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COLUMN_CONDITIONS);

                stmt.setString(index++, "oer");
                stmt.setString(index++, "ee");
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.
                        execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(7) != null && stmt.getString(7).equals(CommonConstants.SUCCESS)) {
                    responseDataDTO.setResult(DataBaseUtils.getGenericColumnValueFromResultSet((ResultSet) stmt.getObject(6)));
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
    public ResponseDataDTO genericTableInquirySelect(RbiBondCommonGenericTableDto commonGenericTableDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {

                StructDescriptor columnListStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COLUMN_LIST, connection);
                ArrayDescriptor columnListDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_COLUMN_LIST, connection);

                STRUCT[] columnListStructs = new STRUCT[commonGenericTableDto.getColumnList().size()];
                Map<String, Object> paramMap = null;
                for (int index = 0; index < commonGenericTableDto.getColumnList().size(); index++) {
                    paramMap = new HashMap<>();
                    paramMap.put("COLUMN_NAME", commonGenericTableDto.getColumnList().get(index));

                    columnListStructs[index] = new STRUCT(columnListStruct, connection, paramMap);
                }
                ARRAY columnListArray = new ARRAY(columnListDescriptor, connection, columnListStructs);

                int index = 1;
                stmt = connection.prepareCall(CbdtProcNamesEnum.GENERIC_SEL_INQUIRY.getProcName());
                stmt.setString(index++, commonGenericTableDto.getTable());
                stmt.setString(index++, commonGenericTableDto.getPkColumnValue().toString());
                stmt.setArray(index++, columnListArray);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.TIMESTAMP);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.TIMESTAMP);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.TIMESTAMP);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(13) != null && stmt.getString(13).equals(CommonConstants.SUCCESS)) {
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("createdBy", stmt.getString(4));
                    resultMap.put("createdDate", (stmt.getTimestamp(5) == null) ? null : stmt.getTimestamp(5).getTime());
                    resultMap.put("lastModifiedBy", stmt.getString(6));
                    resultMap.put("lastModifiedDate", (stmt.getTimestamp(7) == null) ? null : stmt.getTimestamp(7).getTime());
                    resultMap.put("lastVerifiedBy", stmt.getString(8));
                    resultMap.put("lastVerifiedDate", (stmt.getTimestamp(9) == null) ? null : stmt.getTimestamp(9).getTime());
                    resultMap.put("previousOperation", stmt.getString(10));
                    resultMap.put("verifiedStatus", stmt.getString(11));
                    resultMap.put("model", DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(12)));
                    responseDataDTO.setResult(resultMap);
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
    public ResponseDataDTO getGbmSolDate() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareStatement("SELECT TO_CHAR(SOL_DATE,'DD/MM/YYYY') AS SOL_DATE  FROM GBM_SOL_DATE WHERE ROWNUM=1");
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet != null) {
                    while (resultSet.next()) {
                        Map<String, String> resultMap = new HashMap<>();
                        resultMap.put("solDate", resultSet.getString("SOL_DATE"));
                        responseDataDTO.setResult(resultMap);
                    }
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

    @Override
    public ResponseDataDTO getCommonMetaData(CommonDataDTO requestDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(CbdtProcNamesEnum.SP_GBM_CBDT_META_DATA.getProcName());
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(connection, requestDTO.getReferenceIdList()));
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_GEN_SEL_COL_OBJ(connection, requestDTO.getGenericRequestList()));
                stmt.registerOutParameter(index++,OracleTypes.CURSOR);
                stmt.registerOutParameter(index++,OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {
                    CommonDataDTO responseDTO = new CommonDataDTO();
                    resultSet = (ResultSet)stmt.getObject(3);
                    Map<String, String> metaDataMap = null;
                    if(resultSet!=null){
                        metaDataMap = new HashMap<>();
                        while (resultSet.next()) {
                            metaDataMap.put(resultSet.getString(1),resultSet.getString(2));
                        }
                    }
                    resultSet = (ResultSet)stmt.getObject(4);
                    Map<String, String> genericDataMap = null;

                    if(resultSet!=null){
                        genericDataMap = new HashMap<>();
                        while (resultSet.next()) {
                            genericDataMap.put(resultSet.getString(1),resultSet.getString(2));
                        }
                    }
                    responseDTO.setMetaDataList(metaDataMap);
                    responseDTO.setGenericDataList(genericDataMap);

                    responseDataDTO.setResult(responseDTO);
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
