package com.isl.gbm.cbs.integration.dao.impl;

import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.common.ProcNameEnum;
import com.isl.gbm.cbs.integration.dao.CbsIntegerationBulkUploadDao;
import com.isl.gbm.cbs.integration.dto.*;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.common.DateUtils;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CbsIntegerationBulkUploadDaoImpl extends HibernateDao implements CbsIntegerationBulkUploadDao {


    private static final Logger log = LoggerFactory.getLogger(CbsIntegerationBulkUploadDaoImpl.class);


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    private static ARRAY generateArrayOfBankBranchesList(Connection connection, List<BankBranchMaster> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_BRANCHES_OF_BANK_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_BRANCHES_OF_BANK_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        BankBranchMaster bankBranchMaster = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[32];
            bankBranchMaster = list.get(index);
            paramArray[0] = bankBranchMaster.getSolId();
            paramArray[1] = bankBranchMaster.getSolDesc();
            paramArray[2] = bankBranchMaster.getBankCode();
            paramArray[3] = bankBranchMaster.getBranchCode();
            paramArray[4] = bankBranchMaster.getMicrCertreCode();
            paramArray[5] = bankBranchMaster.getMicrBankCode();
            paramArray[6] = bankBranchMaster.getMicrBranchCode();
            paramArray[7] = bankBranchMaster.getAddr1();
            paramArray[8] = bankBranchMaster.getAddr2();
            paramArray[9] = bankBranchMaster.getCityCode();
            paramArray[10] = bankBranchMaster.getStateCode();
            paramArray[11] = bankBranchMaster.getPinCode();
            paramArray[12] = bankBranchMaster.getPanGirNumber();
            paramArray[13] = bankBranchMaster.getTaxDednDetails();
            paramArray[14] = bankBranchMaster.getTdsCircleName();
            paramArray[15] = bankBranchMaster.getDivisionName();
            paramArray[16] = bankBranchMaster.getCirclOfficeName();
            paramArray[17] = bankBranchMaster.getUniformBrCode();
            paramArray[18] = bankBranchMaster.isDeleteFlag();
            paramArray[19] = bankBranchMaster.getVerifiedFlag();
            paramArray[20] = bankBranchMaster.getAdditionalText1();
            paramArray[21] = bankBranchMaster.getAdditionalText2();
            paramArray[22] = bankBranchMaster.getAdditionalText3();
            paramArray[23] = bankBranchMaster.getFileName() != null ? bankBranchMaster.getFileName() : bankBranchMaster.getFileName();
            paramArray[24] = bankBranchMaster.getStatus();
            paramArray[25] = bankBranchMaster.getBranchLocation();
            paramArray[26] = bankBranchMaster.getZoneCode();
            paramArray[27] = bankBranchMaster.getZoneName();
            paramArray[28] = bankBranchMaster.getPdoCode();
            paramArray[29] = bankBranchMaster.getEmailID();
            paramArray[30] = bankBranchMaster.getShortName();
            paramArray[31] = bankBranchMaster.getIfscCode();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private static ARRAY generateArrayOfPlaceHolderList(Connection connection, List<OfficeAccountPlaceHolderDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_OFFICE_ACCT_HOLDER_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_OFFICE_ACCT_HOLDER_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        OfficeAccountPlaceHolderDto placeHolderDto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[7];
            placeHolderDto = list.get(index);
            paramArray[0] = placeHolderDto.getBacid();
            paramArray[1] = placeHolderDto.getBacidDesc();
            paramArray[2] = placeHolderDto.getSchmType();
            paramArray[3] = placeHolderDto.getAdditionalText1();
            paramArray[4] = placeHolderDto.getAdditionalText2();
            paramArray[5] = placeHolderDto.getAdditionalText3();
            paramArray[6] = placeHolderDto.getFilename() != null ? placeHolderDto.getFilename().toUpperCase() : placeHolderDto.getFilename();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private static ARRAY generateArrayOfInstDetailsList(Connection connection, List<InstrumentDetailsDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_INST_DET_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_INST_DET_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        InstrumentDetailsDto instrumentDetailsDto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[7];
            instrumentDetailsDto = list.get(index);
            paramArray[0] = instrumentDetailsDto.getDrCrIndicator();
            paramArray[1] = instrumentDetailsDto.getInstrumentType();
            paramArray[2] = instrumentDetailsDto.getSchemeCode();
            paramArray[3] = instrumentDetailsDto.getFileName() != null ? instrumentDetailsDto.getFileName().toUpperCase() : instrumentDetailsDto.getFileName();
            paramArray[4] = instrumentDetailsDto.getAdditionalText1();
            paramArray[5] = instrumentDetailsDto.getAdditionalText2();
            paramArray[6] = instrumentDetailsDto.getAdditionalText3();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private static ARRAY generateArrayOfTransactionCodeList(Connection connection, List<TransactionCodesDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_TRAN_CODE_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_TRAN_CODES_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        TransactionCodesDto transactionCodesDto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[8];
            transactionCodesDto = list.get(index);
            paramArray[0] = transactionCodesDto.getClgTranCode();
            paramArray[1] = transactionCodesDto.getClgTranCodeDesc();
            paramArray[2] = transactionCodesDto.getInstrumentType();
            paramArray[3] = transactionCodesDto.getSchemeCode();
            paramArray[4] = transactionCodesDto.getFileName() != null ? transactionCodesDto.getFileName().toUpperCase() : transactionCodesDto.getFileName();
            paramArray[5] = transactionCodesDto.getAddText1();
            paramArray[6] = transactionCodesDto.getAddText2();
            paramArray[7] = transactionCodesDto.getAddText3();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private static ARRAY generateArrayOfOtherBankDetailsList(Connection connection, List<OtherBankDetailsDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_OTH_BANK_DETAILS_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_OTH_BANK_DETAILS_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        OtherBankDetailsDto otherBankDetailsDto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[8];
            otherBankDetailsDto = list.get(index);
            paramArray[0] = otherBankDetailsDto.getBankCode();
            paramArray[1] = otherBankDetailsDto.getBankName();
            paramArray[2] = otherBankDetailsDto.getBankShortName();
            paramArray[3] = otherBankDetailsDto.getFileName() != null ? otherBankDetailsDto.getFileName().toUpperCase() : otherBankDetailsDto.getFileName();
            paramArray[4] = otherBankDetailsDto.getAddOne();
            paramArray[5] = otherBankDetailsDto.getAddTwo();
            paramArray[6] = otherBankDetailsDto.getCity();
            paramArray[7] = otherBankDetailsDto.getPinCode();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private static ARRAY generateArrayOfOtherBankBranchesList(Connection connection, List<OtherBankBranchesDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_OTH_BANK_BRANCHES_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_OTH_BANK_BRANCHES_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        OtherBankBranchesDto otherBankDetailsDto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[17];
            otherBankDetailsDto = list.get(index);
            paramArray[0] = otherBankDetailsDto.getBranchCode();
            paramArray[1] = otherBankDetailsDto.getBankCode();
            paramArray[2] = otherBankDetailsDto.getMicrCentreCode();
            paramArray[3] = otherBankDetailsDto.getMicrBankCode();
            paramArray[4] = otherBankDetailsDto.getMicrBranchCode();
            paramArray[5] = otherBankDetailsDto.getBranchName();
            paramArray[6] = otherBankDetailsDto.getBranchAddr1();
            paramArray[7] = otherBankDetailsDto.getBranchAddr2();
            paramArray[8] = otherBankDetailsDto.getBranchAddr3();
            paramArray[9] = otherBankDetailsDto.getBranchCityCode();
            paramArray[10] = otherBankDetailsDto.getBranchStateCode();
            paramArray[11] = otherBankDetailsDto.getBranchPinCode();
            paramArray[12] = otherBankDetailsDto.getAdditionalText1();
            paramArray[13] = otherBankDetailsDto.getAdditionalText2();
            paramArray[14] = otherBankDetailsDto.getAdditionalText3();
            paramArray[15] = otherBankDetailsDto.getFilename() != null ? otherBankDetailsDto.getFilename().toUpperCase() : otherBankDetailsDto.getFilename();
            paramArray[16] = otherBankDetailsDto.getBankIfscCode();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private static ARRAY generateArrayOfCalenderEntriesList(Connection connection, List<CalendarEntriesDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_CAL_ENTRIES_DETAILS_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_CAL_ENTRIES_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        CalendarEntriesDto calendarEntriesDto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[8];
            calendarEntriesDto = list.get(index);
            paramArray[0] = calendarEntriesDto.getCalendarType();
            paramArray[1] = calendarEntriesDto.getMonthYear();
            paramArray[2] = calendarEntriesDto.getCalendarCode();
            paramArray[3] = calendarEntriesDto.isDeleteFlag();
            paramArray[4] = calendarEntriesDto.getHolidayString();
            paramArray[5] = calendarEntriesDto.getStatus().toString();
            paramArray[6] = calendarEntriesDto.getFileName() != null ? calendarEntriesDto.getFileName().toUpperCase() : calendarEntriesDto.getFileName();

            paramArray[7] = (calendarEntriesDto.getHolidayRemarks() != null) ? CLOB.createTemporary(connection, true, CLOB.DURATION_SESSION) : null;
            if (paramArray[7] != null) ((CLOB) paramArray[7]).setString(1, calendarEntriesDto.getHolidayRemarks());

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private static ARRAY generateArrayOfSchemCodeList(Connection connection, List<SchemeCodesDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_SCH_CODE_DETAILS_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_SCH_CODE_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        SchemeCodesDto schemeCodesDto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[8];
            schemeCodesDto = list.get(index);
            paramArray[0] = schemeCodesDto.getSchemeCode();
            paramArray[1] = schemeCodesDto.getDescription();
            paramArray[2] = schemeCodesDto.getGlSubHeadCode();
            paramArray[3] = schemeCodesDto.getSchemeType();
            paramArray[4] = schemeCodesDto.getFileName() != null ? schemeCodesDto.getFileName().toUpperCase() : schemeCodesDto.getFileName();
            paramArray[5] = schemeCodesDto.getAddText1();
            paramArray[6] = schemeCodesDto.getAddText2();
            paramArray[7] = schemeCodesDto.getAddText3();
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private static ARRAY generateArrayOfUserDetailsList(Connection connection, List<GbmUserDetailsDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_USER_WORK_DETAILS_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_USER_WORK_DETAILS_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        GbmUserDetailsDto userDetailsDto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[16];
            userDetailsDto = list.get(index);
            paramArray[0] = userDetailsDto.getUserId();
            paramArray[1] = "";
            paramArray[2] = userDetailsDto.getUserWorkClass();
            paramArray[3] = userDetailsDto.getSolId();
            paramArray[4] = userDetailsDto.getUserSolTenor();
            paramArray[5] = userDetailsDto.getUserMaxInactiveTime();
            paramArray[6] = userDetailsDto.getUserAccountExpiryDate() != null ? new SimpleDateFormat("dd/MM/yyyy").format(userDetailsDto.getUserAccountExpiryDate()) : null;
            paramArray[7] = userDetailsDto.getUserPasswordExpiryDate() != null ? new SimpleDateFormat("dd/MM/yyyy").format(userDetailsDto.getUserPasswordExpiryDate()) : null;
            paramArray[8] = userDetailsDto.getUserApplicationName();
            paramArray[9] = userDetailsDto.getNewUserFlag();
            paramArray[10] = userDetailsDto.getDefaultTermId();
            paramArray[11] = userDetailsDto.getVirtualFlag();
            paramArray[12] = userDetailsDto.getFileName() != null ? userDetailsDto.getFileName().toUpperCase() : userDetailsDto.getFileName();
            paramArray[13] = userDetailsDto.getAdditionalText1();
            paramArray[14] = userDetailsDto.getAdditionalText2();
            paramArray[15] = userDetailsDto.getAdditionalText3();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    private static ARRAY generateArrayOfBranchIfscCodeList(Connection connection, List<BranchIfscDetailsDto> list) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_BRANCH_IFSC_CODE_DETAILS_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_BRANCH_IFSC_CODE_DETAILS_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        BranchIfscDetailsDto branchIfscDetailsDto = null;
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[8];
            branchIfscDetailsDto = list.get(index);
            paramArray[0] = branchIfscDetailsDto.getBankCode();
            paramArray[1] = branchIfscDetailsDto.getBranchCode();
            paramArray[2] = branchIfscDetailsDto.getBankIdentifier();
            paramArray[3] = branchIfscDetailsDto.getPaysysId();
            paramArray[4] = branchIfscDetailsDto.getFileName() != null ? branchIfscDetailsDto.getFileName().toUpperCase() : branchIfscDetailsDto.getFileName();
            paramArray[5] = branchIfscDetailsDto.getAdditionalText1();
            paramArray[6] = branchIfscDetailsDto.getAdditionalText2();
            paramArray[7] = branchIfscDetailsDto.getAdditionalText3();

            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[index] = struct;
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    @Override
    public ResponseDataDTO saveDetails(CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_CBS_BLUK_UPLOAD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, cbsIntegerationBulkUploadDto.getFlag());
                stmt.setString(index++, cbsIntegerationBulkUploadDto.getFileName());
                if (cbsIntegerationBulkUploadDto.getBranchBankList() != null) {
                    stmt.setArray(index++, generateArrayOfBankBranchesList(connection, cbsIntegerationBulkUploadDto.getBranchBankList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_BRANCHES_OF_BANK_ARRAY);
                }
                if (cbsIntegerationBulkUploadDto.getPlaceHolderDtoList() != null) {
                    stmt.setArray(index++, generateArrayOfPlaceHolderList(connection, cbsIntegerationBulkUploadDto.getPlaceHolderDtoList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_OFFICE_ACCT_HOLDER_ARRAY);
                }
                if (cbsIntegerationBulkUploadDto.getInstrumentDetailsDtoList() != null) {
                    stmt.setArray(index++, generateArrayOfInstDetailsList(connection, cbsIntegerationBulkUploadDto.getInstrumentDetailsDtoList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_INST_DET_ARRAY);
                }
                if (cbsIntegerationBulkUploadDto.getTransactionCodesDtoList() != null) {
                    stmt.setArray(index++, generateArrayOfTransactionCodeList(connection, cbsIntegerationBulkUploadDto.getTransactionCodesDtoList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_TRAN_CODES_ARRAY);
                }
                if (cbsIntegerationBulkUploadDto.getOtherBankDetailsDtoList() != null) {
                    stmt.setArray(index++, generateArrayOfOtherBankDetailsList(connection, cbsIntegerationBulkUploadDto.getOtherBankDetailsDtoList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_OTH_BANK_DETAILS_ARRAY);
                }
                if (cbsIntegerationBulkUploadDto.getOtherBankBranchesDtoList() != null) {
                    stmt.setArray(index++, generateArrayOfOtherBankBranchesList(connection, cbsIntegerationBulkUploadDto.getOtherBankBranchesDtoList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_OTH_BANK_BRANCHES_ARRAY);
                }
                if (cbsIntegerationBulkUploadDto.getCalendarEntriesDtoList() != null) {
                    stmt.setArray(index++, generateArrayOfCalenderEntriesList(connection, cbsIntegerationBulkUploadDto.getCalendarEntriesDtoList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_CAL_ENTRIES_ARRAY);
                }
                if (cbsIntegerationBulkUploadDto.getSchemeCodesDtoList() != null) {
                    stmt.setArray(index++, generateArrayOfSchemCodeList(connection, cbsIntegerationBulkUploadDto.getSchemeCodesDtoList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_SCH_CODE_ARRAY);
                }
                if (cbsIntegerationBulkUploadDto.getUserDetailsDtoList() != null) {
                    stmt.setArray(index++, generateArrayOfUserDetailsList(connection, cbsIntegerationBulkUploadDto.getUserDetailsDtoList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_USER_WORK_DETAILS_ARRAY);
                }
                if (cbsIntegerationBulkUploadDto.getBranchIfscDetailsDtoList() != null) {
                    stmt.setArray(index++, generateArrayOfBranchIfscCodeList(connection, cbsIntegerationBulkUploadDto.getBranchIfscDetailsDtoList()));
                } else {
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_BRANCH_IFSC_CODE_DETAILS_ARRAY);
                }
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

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
    public CbsIntegerationBulkUploadDto getUnverifiedBankDetails(String fileName, String flag) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = new CbsIntegerationBulkUploadDto();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.
                        PKG_CBS_BLUK_UPLOAD_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, fileName);
                stmt.setString(index++, flag);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(5);

                    if (flag.equals(CbsBulkUploadEnum.BB.getCbsCode())) {
                        List<BankBranchDto> bankBranchDtoList = new ArrayList<>();
                        BankBranchDto bankBranchDto = null;
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                bankBranchDto = new BankBranchDto();
                                bankBranchDto.setId(Integer.parseInt(resultSet.getString(1)));
                                bankBranchDto.setSolId(resultSet.getString(2));
                                bankBranchDto.setSolDesc(resultSet.getString(3));
                                bankBranchDto.setBankCode(resultSet.getString(4));
                                bankBranchDto.setBranchCode(resultSet.getString(5));
                                bankBranchDto.setMicrCertreCode(resultSet.getString(6));
                                bankBranchDto.setMicrBankCode(resultSet.getString(7));
                                bankBranchDto.setMicrBranchCode(resultSet.getString(8));
                                bankBranchDto.setAddr1(resultSet.getString(9));
                                bankBranchDto.setAddr2(resultSet.getString(10));
                                bankBranchDto.setCityCode(resultSet.getString(11));
                                bankBranchDto.setStateCode(resultSet.getString(12));
                                bankBranchDto.setPinCode(resultSet.getString(13));
                                bankBranchDto.setPanGirNumber(resultSet.getString(14));
                                bankBranchDto.setTaxDednDetails(resultSet.getString(15));
                                bankBranchDto.setTdsCircleName(resultSet.getString(16));
                                bankBranchDto.setDivisionName(resultSet.getString(17));
                                bankBranchDto.setCirclOfficeName(resultSet.getString(18));
                                bankBranchDto.setUniformBrCode(resultSet.getString(19));
                                bankBranchDto.setDeleteFlag(Boolean.parseBoolean(resultSet.getString(20)));
                                bankBranchDto.setVerifiedFlag(Boolean.parseBoolean(resultSet.getString(21)));
                                bankBranchDto.setCreatedBy(resultSet.getString(22));
                                bankBranchDto.setCreatedDate(DateUtils.convertStringToDate(resultSet.getString(23)));
                                bankBranchDto.setAdditionalText1(resultSet.getString(24));
                                bankBranchDto.setAdditionalText2(resultSet.getString(25));
                                bankBranchDto.setAdditionalText3(resultSet.getString(26));
                                bankBranchDto.setFileName(resultSet.getString(27));
                                bankBranchDto.setBranchStatus(Integer.parseInt(resultSet.getString(28)));
                                bankBranchDto.setBranchLocation(resultSet.getString(29));
                                bankBranchDto.setZoneCode(resultSet.getString(30));
                                bankBranchDto.setZoneName(resultSet.getString(31));
                                bankBranchDto.setPdoCode(resultSet.getString(32));
                                bankBranchDto.setBranchEmailId(resultSet.getString(33));
                                bankBranchDto.setShortName(resultSet.getString(34));
                                bankBranchDto.setIfscCode(resultSet.getString(36));

                                bankBranchDtoList.add(bankBranchDto);
                            }
                            cbsIntegerationBulkUploadDto.setBankBranchDtoList(bankBranchDtoList);
                        }
                    } else if (flag.equals(CbsBulkUploadEnum.OAHP.getCbsCode())) {
                        List<OfficeAccountPlaceHolderDto> placeHolderDtoList = new ArrayList<>();
                        OfficeAccountPlaceHolderDto dto = null;
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                dto = new OfficeAccountPlaceHolderDto();
                                dto.setBacid(resultSet.getString(1));
                                dto.setBacidDesc(resultSet.getString(2));
                                dto.setSchmType(resultSet.getString(3));
                                dto.setCreatedBy(resultSet.getString(4));
                                dto.setCreatedDate(DateUtils.convertStringToDate(resultSet.getString(5)));
                                dto.setOfficeId(Integer.parseInt(resultSet.getString(6)));
                                dto.setAdditionalText1(resultSet.getString(7));
                                dto.setAdditionalText2(resultSet.getString(8));
                                dto.setAdditionalText3(resultSet.getString(9));
                                dto.setFilename(resultSet.getString(10));
                                placeHolderDtoList.add(dto);
                            }
                            cbsIntegerationBulkUploadDto.setPlaceHolderDtoList(placeHolderDtoList);
                        }
                    } else if (flag.equals(CbsBulkUploadEnum.ID.getCbsCode())) {
                        InstrumentDetailsDto dto = null;
                        List<InstrumentDetailsDto> instrumentDetailsDtoList = new ArrayList<>();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                dto = new InstrumentDetailsDto();
                                dto.setInstrumentId(Integer.parseInt(resultSet.getString(1)));
                                dto.setDrCrIndicator(resultSet.getString(2).charAt(0));
                                dto.setInstrumentType(resultSet.getString(3));
                                dto.setSchemeCode(resultSet.getString(4));
                                dto.setCreatedBy(resultSet.getString(5));
                                dto.setCreatedDate(DateUtils.convertStringToDate(resultSet.getString(6)));
                                dto.setFileName(resultSet.getString(7));
                                dto.setAdditionalText1(resultSet.getString(8));
                                dto.setAdditionalText2(resultSet.getString(9));
                                dto.setAdditionalText3(resultSet.getString(10));
                                instrumentDetailsDtoList.add(dto);
                            }
                            cbsIntegerationBulkUploadDto.setInstrumentDetailsDtoList(instrumentDetailsDtoList);
                        }
                    } else if (flag.equals(CbsBulkUploadEnum.TC.getCbsCode())) {
                        TransactionCodesDto dto = null;
                        List<TransactionCodesDto> transactionCodesDtoList = new ArrayList<>();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                dto = new TransactionCodesDto();
                                dto.setId(Integer.parseInt(resultSet.getString(1)));
                                dto.setClgTranCode(resultSet.getString(2));
                                dto.setClgTranCodeDesc(resultSet.getString(3));
                                dto.setInstrumentType(resultSet.getString(4));
                                dto.setSchemeCode(resultSet.getString(5));
                                dto.setCreatedBy(resultSet.getString(6));
                                dto.setCreatedDate(DateUtils.convertStringToDate(resultSet.getString(7)));
                                dto.setFileName(resultSet.getString(8));
                                dto.setAddText1(resultSet.getString(9));
                                dto.setAddText2(resultSet.getString(10));
                                dto.setAddText3(resultSet.getString(11));
                                transactionCodesDtoList.add(dto);
                            }
                            cbsIntegerationBulkUploadDto.setTransactionCodesDtoList(transactionCodesDtoList);
                        }
                    } else if (flag.equals(CbsBulkUploadEnum.OBD.getCbsCode())) {
                        List<OtherBankDetailsDto> otherBankDetailsDtoList = new ArrayList<>();
                        OtherBankDetailsDto dto = null;
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                dto = new OtherBankDetailsDto();
                                dto.setId(Integer.parseInt(resultSet.getString(1)));
                                dto.setBankCode(resultSet.getString(2));
                                dto.setBankName(resultSet.getString(3));
                                dto.setBankShortName(resultSet.getString(4));
                                dto.setDeleteFlag(Boolean.parseBoolean(resultSet.getString(5)));
                                dto.setCreatedBy(resultSet.getString(6));
                                dto.setCreatedDate(DateUtils.convertStringToDate(resultSet.getString(7)));
                                dto.setFileName(resultSet.getString(8));
                                dto.setAddOne(resultSet.getString(9));
                                dto.setAddTwo(resultSet.getString(10));
                                dto.setCity(resultSet.getString(11));
                                dto.setPinCode(resultSet.getString(12));
                                otherBankDetailsDtoList.add(dto);
                            }
                            cbsIntegerationBulkUploadDto.setOtherBankDetailsDtoList(otherBankDetailsDtoList);
                        }
                    } else if (flag.equals(CbsBulkUploadEnum.BOB.getCbsCode())) {
                        OtherBankBranchesDto dto = null;
                        List<OtherBankBranchesDto> otherBankBranchesDtoList = new ArrayList<>();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                dto = new OtherBankBranchesDto();
                                dto.setBranchCode(resultSet.getString(1));
                                dto.setBankCode(resultSet.getString(2));
                                dto.setMicrCentreCode(resultSet.getString(3));
                                dto.setMicrBankCode(resultSet.getString(4));
                                dto.setMicrBranchCode(resultSet.getString(5));
                                dto.setBranchName(resultSet.getString(6));
                                dto.setBranchAddr1(resultSet.getString(7));
                                dto.setBranchAddr2(resultSet.getString(8));
                                dto.setBranchAddr3(resultSet.getString(9));
                                dto.setBranchCityCode(resultSet.getString(10));
                                dto.setBranchStateCode(resultSet.getString(11));
                                dto.setBranchPinCode(resultSet.getString(12));
                                dto.setCreatedBy(resultSet.getString(13));
                                dto.setCreatedDate(DateUtils.convertStringToDate(resultSet.getString(14)));
                                dto.setId(resultSet.getString(15));
                                dto.setAdditionalText1(resultSet.getString(16));
                                dto.setAdditionalText2(resultSet.getString(17));
                                dto.setAdditionalText3(resultSet.getString(18));
                                dto.setFilename(resultSet.getString(19));
                                dto.setBankIfscCode(resultSet.getString(20));
                                otherBankBranchesDtoList.add(dto);



                            }
                            cbsIntegerationBulkUploadDto.setOtherBankBranchesDtoList(otherBankBranchesDtoList);
                        }
                    } else if (flag.equals(CbsBulkUploadEnum.MOCE.getCbsCode())) {
                        List<CalendarEntriesDto> calendarEntriesDtoList = new ArrayList<>();
                        CalendarEntriesDto calendarEntriesDto = null;
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                calendarEntriesDto = new CalendarEntriesDto();
                                calendarEntriesDto.setId(Integer.parseInt(resultSet.getString(1)));
                                calendarEntriesDto.setCalendarType(resultSet.getString(2));
                                calendarEntriesDto.setMonthYear(resultSet.getString(3));
                                calendarEntriesDto.setCalendarCode(resultSet.getString(4));
                                calendarEntriesDto.setDeleteFlag(Boolean.parseBoolean(resultSet.getString(5)));
                                calendarEntriesDto.setHolidayString(resultSet.getString(6));
                                Character ch = ' ';
                                calendarEntriesDto.setStatus((resultSet.getString(7) != null) ?  (resultSet.getString(7).charAt(0)) : ch);
                                calendarEntriesDto.setVerifiedFlag(Boolean.parseBoolean(resultSet.getString(8)));
                                calendarEntriesDto.setFileName(resultSet.getString(9));
                                calendarEntriesDto.setCreatedBy(resultSet.getString(10));
                                calendarEntriesDto.setCreatedDate(DateUtils.convertStringToDate(resultSet.getString(11)));
                                calendarEntriesDto.setHolidayRemarks(resultSet.getString(15));





                                /*calendarEntriesDto.setAdditionalText2(resultSet.getString(13));
                                calendarEntriesDto.setAdditionalText3(resultSet.getString(14));*/
                                calendarEntriesDtoList.add(calendarEntriesDto);
                            }
                            cbsIntegerationBulkUploadDto.setCalendarEntriesDtoList(calendarEntriesDtoList);
                        }
                    } else if (flag.equals(CbsBulkUploadEnum.SC.getCbsCode())) {
                        SchemeCodesDto schemeCodesDto = null;
                        List<SchemeCodesDto> schemeCodesDtoList = new ArrayList<>();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                schemeCodesDto.setSchemeCode(resultSet.getString(1));
                                schemeCodesDto.setDescription(resultSet.getString(2));
                                schemeCodesDto.setGlSubHeadCode(resultSet.getString(3));
                                schemeCodesDto.setSchemeType(resultSet.getString(4));
                                schemeCodesDto.setCreatedBy(resultSet.getString(5));
                                schemeCodesDto.setCreatedDate(DateUtils.convertStringToDate(resultSet.getString(6)));
                                schemeCodesDto.setFileName(resultSet.getString(7));
                                schemeCodesDto.setAddText1(resultSet.getString(8));
                                schemeCodesDto.setAddText2(resultSet.getString(9));
                                schemeCodesDto.setAddText3(resultSet.getString(10));
                                schemeCodesDto.setId(Integer.parseInt(resultSet.getString(11)));
                                schemeCodesDtoList.add(schemeCodesDto);
                            }
                            cbsIntegerationBulkUploadDto.setSchemeCodesDtoList(schemeCodesDtoList);
                        }
                    } else if (flag.equals(CbsBulkUploadEnum.UWCI.getCbsCode())) {
                        List<GbmUserDetailsDto> userDetailsDtoList = new ArrayList<>();
                        GbmUserDetailsDto dto = null;
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                dto = new GbmUserDetailsDto();
                                dto.setGbmUserDetailsId(Integer.parseInt(resultSet.getString(1)));
                                dto.setUserId(resultSet.getString(2));
                                dto.setUserPassword(resultSet.getString(3));
                                dto.setUserWorkClass(resultSet.getString(4));
                                dto.setSolId(resultSet.getString(5));
                                dto.setUserSolTenor(resultSet.getString(6).charAt(0));
                                dto.setUserMaxInactiveTime(Integer.parseInt(resultSet.getString(7)));
                                dto.setUserAccountExpiryDate(DateUtils.convertStringToDate(resultSet.getString(8)));
                                dto.setUserPasswordExpiryDate(DateUtils.convertStringToDate(resultSet.getString(9)));
                                dto.setUserApplicationName(resultSet.getString(10));
                                dto.setNewUserFlag(Boolean.parseBoolean(resultSet.getString(11)));
                                dto.setDefaultTermId(resultSet.getString(12));
                                dto.setVirtualFlag(Boolean.parseBoolean(resultSet.getString(13)));
                                dto.setCreatedBy(resultSet.getString(14));
                                dto.setCreatedDate(DateUtils.convertStringToDate(resultSet.getString(15)));
                                dto.setVerified(Boolean.parseBoolean(resultSet.getString(16)));
                                dto.setFileName(resultSet.getString(17));
                                dto.setAdditionalText1(resultSet.getString(18));
                                dto.setAdditionalText2(resultSet.getString(19));
                                dto.setAdditionalText3(resultSet.getString(20));
                                userDetailsDtoList.add(dto);
                            }
                            cbsIntegerationBulkUploadDto.setUserDetailsDtoList(userDetailsDtoList);
                        }
                    } else if (flag.equals(CbsBulkUploadEnum.BIFSCC.getCbsCode())) {
                        BranchIfscDetailsDto dto = null;
                        List<BranchIfscDetailsDto> branchIfscDetailsDtoList = new ArrayList<>();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                dto = new BranchIfscDetailsDto();
                                dto.setBranchDetailsId(Integer.parseInt(resultSet.getString(1)));
                                dto.setBankCode(resultSet.getString(2));
                                dto.setBranchCode(resultSet.getString(3));
                                dto.setBankIdentifier(resultSet.getString(4));
                                dto.setPaysysId(resultSet.getString(5));
                                dto.setDeleted(Boolean.parseBoolean(resultSet.getString(6)));
                                dto.setCreatedBy(resultSet.getString(7));
                                dto.setCreatedDate(DateUtils.convertStringToDate(resultSet.getString(8)));
                                dto.setVerified(Boolean.parseBoolean(resultSet.getString(9)));
                                dto.setFileName(resultSet.getString(10));
                                dto.setAdditionalText1(resultSet.getString(11));
                                dto.setAdditionalText2(resultSet.getString(12));
                                dto.setAdditionalText3(resultSet.getString(13));
                                branchIfscDetailsDtoList.add(dto);
                            }
                            cbsIntegerationBulkUploadDto.setBranchIfscDetailsDtoList(branchIfscDetailsDtoList);
                        }
                    }
                }
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);


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
        return cbsIntegerationBulkUploadDto;
    }

    @Override
    public ResponseDataDTO verifyBanks(String fileName, String flag) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_CBS_BLUK_UPLOAD_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, fileName);
                stmt.setString(index++, flag);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
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
         if (resultMsg.equalsIgnoreCase("SUCCESS")) {
            responseDataDTO.setMessage("Record verification is successful");
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO deleteBanks(String fileName, String flag, String remarks) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_CBS_BLUK_UPLOAD_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, fileName);
                stmt.setString(index++, flag);
                stmt.setString(index++, remarks);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
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
}
