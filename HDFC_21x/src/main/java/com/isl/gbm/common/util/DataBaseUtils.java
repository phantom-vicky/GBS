package com.isl.gbm.common.util;
import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.GenericDataDTO;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.commons.collections4.CollectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

;

public class DataBaseUtils {
    private static final String SEL_VERIFY_RESULTSET_OLD_VALUE = "OLD_VAL";
    private static final String SEL_VERIFY_RESULTSET_NEW_VALUE = "NEW_VAL";
    private static final String SEL_VERIFY_RESULTSET_COL_NUMBER = "COL_NO";
    private static final String SEL_VERIFY_RESULTSET_ROW_NUMBER = "ROW_NO";

    public static List<Map<String, Object>> getGenericColumnValueFromResultSet(ResultSet resultSet) throws Exception {
        List<Map<String, Object>> responseList = new ArrayList<>();

        if (resultSet != null) {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            Map<String, Object> tempMap;
            while (resultSet.next()) {
                tempMap = new HashMap<>();
                for (int index = 1; index <= resultSetMetaData.getColumnCount(); index++) {
                    tempMap.put(resultSetMetaData.getColumnName(index), resultSet.getString(index));
                }
                responseList.add(tempMap);
            }
        }

        return responseList;
    }

    public static List<ColumnValueDTO> getColumnNamePropertyList(ResultSet resultSet) throws Exception {
        List<ColumnValueDTO> responseList = new ArrayList<>();

        if (resultSet != null) {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            ColumnValueDTO tempMap = null;
            while (resultSet.next()) {

                for (int index = 1; index <= resultSetMetaData.getColumnCount(); index++) {
                    tempMap = new ColumnValueDTO();
                    tempMap.setColName(resultSetMetaData.getColumnName(index));
                    tempMap.setValue(resultSet.getString(index));
                    responseList.add(tempMap);
                }
            }
        }

        return responseList;
    }

    public static Map<String, Object> generateOldValueNewValueMap(ResultSet resultSet) throws Exception {
        List<Map<String, Object>> columnValuesList = getGenericColumnValueFromResultSet(resultSet);
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> tempMap = null;
        for (Map<String, Object> columnValueMap : columnValuesList) {
            tempMap = new HashMap<>();
            tempMap.put("oldValue", columnValueMap.get(SEL_VERIFY_RESULTSET_OLD_VALUE));
            tempMap.put("newValue", columnValueMap.get(SEL_VERIFY_RESULTSET_NEW_VALUE));
            resultMap.put(String.valueOf(columnValueMap.get(SEL_VERIFY_RESULTSET_COL_NUMBER)), tempMap);
        }
        return resultMap;
    }

    public static List<Map<String, Object>> generateOldValueNewValueMapList(ResultSet resultSet) throws Exception {
        List<Map<String, Object>> columnValuesList = getGenericColumnValueFromResultSet(resultSet);
        Map<Integer, Map<String, Object>> resultMap = new TreeMap<>();
        Map<String, Object> tempMap = null;
        int rowNumber;
        for (Map<String, Object> columnValueMap : columnValuesList) {
            tempMap = new HashMap<>();
            tempMap.put("oldValue", columnValueMap.get(SEL_VERIFY_RESULTSET_OLD_VALUE));
            tempMap.put("newValue", columnValueMap.get(SEL_VERIFY_RESULTSET_NEW_VALUE));

            rowNumber = Integer.valueOf(String.valueOf(columnValueMap.get(SEL_VERIFY_RESULTSET_ROW_NUMBER)));
            if (resultMap.containsKey(rowNumber)) {
                resultMap.get(rowNumber).put(String.valueOf(columnValueMap.get(SEL_VERIFY_RESULTSET_COL_NUMBER)), tempMap);
            } else {
                resultMap.put(rowNumber, new HashMap<>());
                resultMap.get(rowNumber).put(String.valueOf(columnValueMap.get(SEL_VERIFY_RESULTSET_COL_NUMBER)), tempMap);
            }
        }
        return new ArrayList(resultMap.values());
    }

    public static ARRAY generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(Connection connection, List<ColumnValueDTO> columnValueDTOList) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COLUMN_DETAILS, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_COLUMN_DETAILS, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[columnValueDTOList.size()];
        ColumnValueDTO columnValueDTO = null;
        for (int index = 0; index < columnValueDTOList.size(); index++) {
            columnValueDTO = columnValueDTOList.get(index);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("COLUMN_NAME", columnValueDTO.getColName());
            paramMap.put("COL_VALUE", columnValueDTO.getValue());

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramMap);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }


    public static ARRAY generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(Connection connection, List<List<ColumnValueDTO>> columnValueDTOList) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COL_DET_ARRAY, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = null;
        if (CollectionUtils.isEmpty(columnValueDTOList)) {
            genericTableInsertStructs = new STRUCT[0];
            return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
        } else {
            genericTableInsertStructs = new STRUCT[columnValueDTOList.size()];
        }
        ColumnValueDTO columnValueDTO = null;
        for (int index = 0; index < columnValueDTOList.size(); index++) {
            Object[] paramArray = new Object[1];
            paramArray[0] = generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection, columnValueDTOList.get(index));
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    public static ARRAY generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(Connection connection, List<String> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_LEVEL_MASTER_DETAILS, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_COLLECTION_LEVEL_MASTER_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[1];
            paramArray[0] = list.get(index);
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    public static ARRAY generateTYP_GBM_GEN_SEL_COL_OBJ(Connection connection, List<GenericDataDTO> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.GENERIC_DATA_OBJECT_DETAILS, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.GENERIC_DATA_OBJECT_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        GenericDataDTO genericDataDTO = null;
        for (int index = 0; index < list.size(); index++) {
            genericDataDTO = list.get(index);
            Object[] paramArray = new Object[3];
            paramArray[0] = genericDataDTO.getTableName();
            paramArray[1] = genericDataDTO.getColumnList();
            paramArray[2] = genericDataDTO.getConditionList();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

}
