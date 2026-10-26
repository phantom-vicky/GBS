/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao.impl;

import com.isl.gbm.cbs.integration.common.ProcNameEnum;
import com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao;
import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.model.OtherBankDetailsMaster;
import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.*;

/**
 * The Class OtherBankDetailsDaoImpl.
 */
@Repository
public class OtherBankDetailsDaoImpl extends HibernateDao implements OtherBankDetailsDao {



    private static final Logger log = LoggerFactory.getLogger(OtherBankDetailsDaoImpl.class);
    /**
     * The Constant FILE_NAME.
     */
    private static final String FILE_NAME = "fileName";

    /**
     * The Constant VERIFIED.
     */
    private static final String VERIFIED = "verified";

    /**
     * The Constant BANK_CODE.
     */
    private static final String BANK_CODE = "bankCode";

    /**
     * The Constant ID.
     */
    private static final String ID = "id";

    /**
     * The Constant BANK_NAME.
     */
    private static final String BANK_NAME = "bankName";

    /**
     * The Constant DELETE_FLAG.
     */
    private static final String DELETE_FLAG = "deleteFlag";

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Override
    public ResponseDataDTO add(OtherBankDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_OTH_BANK_DTLS_SP_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setArray(index++, generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection,dto.getColumnValues()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }
    @Override
    public ResponseDataDTO modifySelect(final OtherBankDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        OtherBankDetailsDto otherBankDetailsDto = null;
        List<OtherBankDetailsDto> otherBankDetailsDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_OTH_BANK_DTLS_SP_MOD_SEL.getProcName());
                stmt.setString("p_modifier_id_in", UserAccountUtils.getCurrentUsername());
                stmt.setString("p_modifier_sol_id_in", UserAccountUtils.getCurrentSolId());
                stmt.setString("p_bank_code_in", dto.getBankCode());
                stmt.registerOutParameter("p_cursor_val_out", OracleTypes.CURSOR);
                stmt.registerOutParameter("p_delete_remarks_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_desc_out", Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString("p_msg_desc_out"));
                resultMsg = stmt.getString("p_msg_desc_out");
                ResultSet resultSet = null;
                if (stmt.getString("p_msg_desc_out")  != null && stmt.getString("p_msg_desc_out").equals("SUCCESS")) {

                    if(dto.getBankCode() == null) {
                        resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                otherBankDetailsDto = new OtherBankDetailsDto();
                                otherBankDetailsDto.setBankCode(resultSet.getString("BANK_CODE"));
                                otherBankDetailsDto.setBankName(resultSet.getString("BANK_NAME"));
                                otherBankDetailsDtoList.add(otherBankDetailsDto);
                            }
                        }
                    }
                    else {
                        resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                otherBankDetailsDto = new OtherBankDetailsDto();
                                otherBankDetailsDto.setId(Integer.parseInt(resultSet.getString("ID")));
                                otherBankDetailsDto.setBankCode(resultSet.getString("BANK_CODE"));
                                otherBankDetailsDto.setBankName(resultSet.getString("BANK_NAME"));
                                otherBankDetailsDto.setBankShortName(resultSet.getString("BANK_SHORT_NAME"));
                                otherBankDetailsDto.setFileName(resultSet.getString("FILE_NAME"));
                                otherBankDetailsDto.setAdditionalText1(resultSet.getString("ADDL_TEXT1"));
                                otherBankDetailsDto.setAdditionalText2(resultSet.getString("ADDL_TEXT2"));
                                otherBankDetailsDto.setAdditionalText3(resultSet.getString("ADDL_TEXT3"));
                                otherBankDetailsDto.setAddOne(resultSet.getString("ADDR1"));
                                otherBankDetailsDto.setAddTwo(resultSet.getString("ADDR2"));
                                otherBankDetailsDto.setCity(resultSet.getString("CITY"));
                                otherBankDetailsDto.setPinCode(resultSet.getString("PIN_CODE"));
                                otherBankDetailsDto.setCreatDate(resultSet.getString("CREATED_DATE"));
                                otherBankDetailsDto.setModifiedBy(resultSet.getString("MODIFIED_BY"));
                                otherBankDetailsDto.setModDate(resultSet.getString("LAST_MOD_DATE"));
                                otherBankDetailsDto.setVerifiedFlag(resultSet.getString("IS_VERIFIED"));
                                otherBankDetailsDto.setVerifiedBy(resultSet.getString("VERIFIED_BY"));
                                otherBankDetailsDto.setVerDate(resultSet.getString("VERIFIED_DATE"));

                                String rejectionRemarks = stmt.getString("p_delete_remarks_out");
                                otherBankDetailsDto.setRejectionRemarks(rejectionRemarks);
                                otherBankDetailsDtoList.add(otherBankDetailsDto);
                            }



                        }


                    }
                }


            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(otherBankDetailsDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO modifySubmit(OtherBankDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_OTH_BANK_DTLS_SP_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, String.valueOf(dto.getId()));
                stmt.setArray(index++, generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection,dto.getColumnValues()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

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


    @Override
    public ResponseDataDTO inquiry(final OtherBankDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        OtherBankDetailsDto otherBankDetailsDto = null;
        List<OtherBankDetailsDto> otherBankDetailsDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_OTH_BANK_DTLS_SP_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getBankCode());
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index)  != null && stmt.getString(index).equalsIgnoreCase("SUCCESS")) {

                    if(dto.getBankCode() == null) {
                        resultSet = (ResultSet) stmt.getObject(12);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                otherBankDetailsDto = new OtherBankDetailsDto();
                                otherBankDetailsDto.setBankCode(resultSet.getString(1));
                                otherBankDetailsDto.setBankName(resultSet.getString(2));
                                otherBankDetailsDtoList.add(otherBankDetailsDto);
                            }
                        }
                    }
                    else {
                        otherBankDetailsDto = new OtherBankDetailsDto();
                        otherBankDetailsDto.setCbsOtherBankDetailsOldNewValue(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(12)));
                        otherBankDetailsDto.setCreatedBy(stmt.getString(3));
                        otherBankDetailsDto.setCreatDate(stmt.getString(4));
                        otherBankDetailsDto.setModifiedBy(stmt.getString(5));
                        otherBankDetailsDto.setModDate(stmt.getString(6));
                        otherBankDetailsDto.setVerifiedBy(stmt.getString(7));
                        otherBankDetailsDto.setVerDate(stmt.getString(8));
                        otherBankDetailsDto.setPreviousOperationType(stmt.getString(9));
                        otherBankDetailsDto.setVerifiedFlag(stmt.getString(10));
                        otherBankDetailsDto.setRejectionRemarks(stmt.getString(11));
                        otherBankDetailsDtoList.add(otherBankDetailsDto);
                    }
                }


            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(otherBankDetailsDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(final OtherBankDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        OtherBankDetailsDto otherBankDetailsDto = null;
        List<OtherBankDetailsDto> otherBankDetailsDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_OTH_BANK_DTLS_SP_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getBankCode());
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index)  != null && stmt.getString(index).equalsIgnoreCase("SUCCESS")) {

                    if(dto.getBankCode() == null) {
                        resultSet = (ResultSet) stmt.getObject(13);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                otherBankDetailsDto = new OtherBankDetailsDto();
                                otherBankDetailsDto.setBankCode(resultSet.getString(1));
                                otherBankDetailsDto.setBankName(resultSet.getString(2));
                                otherBankDetailsDtoList.add(otherBankDetailsDto);
                            }
                        }
                    }
                    else {
                        otherBankDetailsDto = new OtherBankDetailsDto();
                        otherBankDetailsDto.setCbsOtherBankDetailsOldNewValue(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(13)));
                        otherBankDetailsDto.setId(Integer.parseInt(stmt.getString(4)));
                        otherBankDetailsDto.setCreatedBy(stmt.getString(5));
                        otherBankDetailsDto.setCreatDate(stmt.getString(6));
                        otherBankDetailsDto.setModifiedBy(stmt.getString(7));
                        otherBankDetailsDto.setModDate(stmt.getString(8));
                        otherBankDetailsDto.setVerifiedBy(stmt.getString(9));
                        otherBankDetailsDto.setVerDate(stmt.getString(10));
                        otherBankDetailsDto.setPreviousOperationType(stmt.getString(11));
                        otherBankDetailsDto.setVerifiedFlag(stmt.getString(12));
                        otherBankDetailsDtoList.add(otherBankDetailsDto);
                    }
                }


            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(otherBankDetailsDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO verifySubmit(final OtherBankDetailsDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_OTH_BANK_DTLS_SP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, String.valueOf(dto.getId()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }
    @Override
    public ResponseDataDTO deleteSubmit(final  OtherBankDetailsDto dto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (dto.getRejectionRemarks() == null
                || dto.getRejectionRemarks().trim().isEmpty()) {
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
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_OTH_BANK_DTLS_SP_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, String.valueOf(dto.getId()));
                stmt.setString(index++, String.valueOf(dto.getRejectionRemarks()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;

    }


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#
     * deleteOtherBankDetails( java.lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void deleteOtherBankDetails(final String fileName) {

        final Session session = getSession();
        final Query query = session.createQuery("delete from OtherBankDetailsMaster where fileName = :fileName");
        query.setParameter(FILE_NAME, fileName);
        query.executeUpdate();
    }


    @SuppressWarnings("rawtypes")
    @Override
    public void deleteOtherBankDetailsId(Integer id) {

        final Session session = getSession();
        final Query query = session.createQuery("delete from OtherBankDetailsMaster where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#
     * deleteOtherBankRecords( java.lang.Integer)
     */
    @SuppressWarnings("rawtypes")
    @Override

    public void deleteOtherBankRecords(final Integer id) {
        final Session session = getSession();
        final Query query = session
                .createQuery("update OtherBankDetailsMaster set deleteFlag = :deleteFlag where id = :id");
        query.setParameter(DELETE_FLAG, Boolean.TRUE);
        query.setParameter(ID, id);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#fileNameExists(java.
     * lang. String)
     */
    @Override

    public boolean fileNameExists(final String otherBankFileName) {

        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from OtherBankDetailsMaster where fileName = :fileName group by fileName",
                String.class);
        query.setParameter(FILE_NAME, otherBankFileName);
        final List<String> fileNameList = query.list();
        return CollectionUtils.size(fileNameList) > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#getBankDetailsList(
     * java. lang.String)
     */
    @Override

    public List<OtherBankDetailsMaster> getBankDetailsList(final String bankCode) {

        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<OtherBankDetailsMaster> criteriaQuery = builder.createQuery(OtherBankDetailsMaster.class);
        final Root<OtherBankDetailsMaster> otherBankDetailsRoot = criteriaQuery.from(OtherBankDetailsMaster.class);
        criteriaQuery.select(otherBankDetailsRoot);

        criteriaQuery.where(builder.equal(otherBankDetailsRoot.get(DELETE_FLAG), Boolean.FALSE));
        if (StringUtils.isNotBlank(bankCode)) {
            criteriaQuery.where(builder.equal(otherBankDetailsRoot.get(DELETE_FLAG), Boolean.FALSE),
                    builder.like(otherBankDetailsRoot.get(BANK_CODE), bankCode));
        }

        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#getOtherBankDetails(
     * java. lang.Integer)
     */
    @Override

    public OtherBankDetailsMaster getOtherBankDetails(final Integer id) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<OtherBankDetailsMaster> criteriaQuery = builder.createQuery(OtherBankDetailsMaster.class);
        final Root<OtherBankDetailsMaster> root = criteriaQuery.from(OtherBankDetailsMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), id));
        // Use criteria to query with session to fetch all accounts
        final OtherBankDetailsMaster details = (OtherBankDetailsMaster) session.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);

        if (null != details) {
            session.evict(details);
        }
        return details;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#getOtherBankDetails(
     * java. lang.String, java.lang.String)
     */
    @Override

    public List<OtherBankDetailsMaster> getOtherBankDetails(final String bankCode, final String bankName) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<OtherBankDetailsMaster> criteriaQuery = builder.createQuery(OtherBankDetailsMaster.class);
        final Root<OtherBankDetailsMaster> root = criteriaQuery.from(OtherBankDetailsMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(BANK_CODE), bankCode),
                builder.equal(root.get(BANK_NAME), bankName));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#
     * getOtherBankDetailsList()
     */
    @Override

    public List<OtherBankDetailsMaster> getOtherBankDetailsList() {

        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<OtherBankDetailsMaster> criteriaQuery = builder.createQuery(OtherBankDetailsMaster.class);
        final Root<OtherBankDetailsMaster> otherBankDetailsRoot = criteriaQuery.from(OtherBankDetailsMaster.class);
        criteriaQuery.select(otherBankDetailsRoot);
        return session.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Gets the records.
     *
     * @param verified the verified
     * @return the records
     */
    private List<OtherBankDetailsMaster> getRecords(final Boolean verified) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<OtherBankDetailsMaster> criteriaQuery = builder.createQuery(OtherBankDetailsMaster.class);
        final Root<OtherBankDetailsMaster> root = criteriaQuery.from(OtherBankDetailsMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(VERIFIED), verified),
                builder.equal(root.get(DELETE_FLAG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#getUnverifiedFiles()
     */
    @Override

    public List<String> getUnverifiedFiles() {

        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from OtherBankDetailsMaster where verified = :verified and fileName is not null group by fileName",
                String.class);
        query.setParameter(VERIFIED, Boolean.FALSE);
        return query.list();
    }

    @Override

    public List<String> getInquiryFiles() {

        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select fileName from OtherBankDetailsMaster where  fileName is not null group by fileName",
                String.class);
        return query.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#
     * getUnverifiedOtherBankDetails(java.lang.String)
     */
    @Override

    public List<OtherBankDetailsMaster> getUnverifiedOtherBankDetails(final String fileName) {

        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<OtherBankDetailsMaster> criteriaQuery = builder.createQuery(OtherBankDetailsMaster.class);
        final Root<OtherBankDetailsMaster> root = criteriaQuery.from(OtherBankDetailsMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(FILE_NAME), fileName));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#getUnverifiedRecords(
     * )
     */
    @Override

    public List<OtherBankDetailsMaster> getUnverifiedRecords() {
        return this.getRecords(Boolean.FALSE);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#getVerifiedRecords()
     */
    @Override

    public List<OtherBankDetailsMaster> getVerifiedRecords() {
        return this.getRecords(Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#list()
     */
    @Override

    public List<OtherBankDetailsMaster> list() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<OtherBankDetailsMaster> criteriaQuery = builder.createQuery(OtherBankDetailsMaster.class);
        final Root<OtherBankDetailsMaster> root = criteriaQuery.from(OtherBankDetailsMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(DELETE_FLAG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#saveDetails(java.
     * util. List)
     */
    @Override

    public void saveDetails(final List<OtherBankDetailsMaster> otherBankDetailsList) {

        final Session session = getSession();
        for (final OtherBankDetailsMaster otherBankDetailsMaster : otherBankDetailsList) {
            session.saveOrUpdate(otherBankDetailsMaster);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#saveOrUpdate(com.isl.
     * gbm. cbs.integration.model.OtherBankDetailsMaster)
     */
    @Override

    public void saveOrUpdate(final OtherBankDetailsMaster otherBankDetailsMaster) {
        final Session session = getSession();

        session.saveOrUpdate(otherBankDetailsMaster);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#verify(com.isl.gbm.
     * cbs. integration.model.OtherBankDetailsMaster)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final OtherBankDetailsMaster otherBankDetailsMaster) {
        final Session session = getSession();
        final String HQL = "update OtherBankDetailsMaster set verified = :verified, verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where id = :id";
        final Query query = session.createQuery(HQL);
        query.setParameter(VERIFIED, Boolean.TRUE);
        query.setParameter("verifiedBy", otherBankDetailsMaster.getVerifiedBy());
        query.setParameter("verifiedDate", otherBankDetailsMaster.getVerifiedDate());
        query.setParameter("auditable", Boolean.TRUE);
        query.setParameter(ID, otherBankDetailsMaster.getId());
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao#
     * verifyOtherBankDetails( java.lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override

    public void verifyOtherBankDetails(final String fileName) {

        final Session session = getSession();
        final Query query = session
                .createQuery("update OtherBankDetailsMaster set verified = :verified ,verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where fileName = :fileName");
        query.setParameter(VERIFIED, Boolean.TRUE);
        query.setParameter(FILE_NAME, fileName);
        query.setParameter("verifiedBy", UserAccountUtils.getCurrentUsername());
        query.setParameter("verifiedDate", new Date());
        query.setParameter("auditable", Boolean.TRUE);
        query.executeUpdate();
    }
}
