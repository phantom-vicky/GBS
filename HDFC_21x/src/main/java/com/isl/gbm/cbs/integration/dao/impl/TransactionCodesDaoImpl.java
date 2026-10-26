/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao.impl;

import com.isl.gbm.cbs.integration.common.ProcNameEnum;
import com.isl.gbm.cbs.integration.dao.TransactionCodesDao;
import com.isl.gbm.cbs.integration.dto.TransactionCodesDto;
import com.isl.gbm.cbs.integration.model.TransactionCodes;
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
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.*;

/**
 * The Class TransactionCodesDaoImpl.
 */
@Repository
public class TransactionCodesDaoImpl extends HibernateDao implements TransactionCodesDao {


    private static final Logger log = LoggerFactory.getLogger(TransactionCodesDaoImpl.class);
    /**
     * The Constant FILE_NAME.
     */
    private static final String FILE_NAME = "fileName";

    /**
     * The Constant VERIFIED.
     */
    private static final String VERIFIED = "isVerified";

    /**
     * The Constant CLG_TRAN_CODE.
     */
    private static final String CLG_TRAN_CODE = "clgTranCode";

    /**
     * The Constant ID.
     */
    private static final String ID = "id";

    /**
     * The Constant INSTRUMENT_TYPE.
     */
    private static final String INSTRUMENT_TYPE = "instrumentType";

    /**
     * The Constant DELETE.
     */
    private static final String DELETE = "deleteFlag";

    /**
     * The session factory.
     */
    private SessionFactory sessionFactory;

    /**
     * This is EntityManager
     */
    @PersistenceContext
    EntityManager em;

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;



    @Override
    public ResponseDataDTO add(TransactionCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_LST_TRAN_CODE_SP_ADD.getProcName());
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
    public ResponseDataDTO modifySelect(final TransactionCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        TransactionCodesDto transactionCodesDto = null;
        List<TransactionCodesDto> transactionCodesDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_LST_TRAN_CODE_SP_MOD_SEL.getProcName());
                stmt.setString("p_modifier_id_in", UserAccountUtils.getCurrentUsername());
                stmt.setString("p_modifier_sol_id_in", UserAccountUtils.getCurrentSolId());
                stmt.setString("p_tran_code_in", dto.getClgTranCode());
                  stmt.registerOutParameter("p_cursor_val_out", OracleTypes.CURSOR);
                stmt.registerOutParameter("p_msg_out", Types.VARCHAR);
                stmt.registerOutParameter("p_msg_desc_out", Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString("p_msg_desc_out"));
                resultMsg = stmt.getString("p_msg_desc_out");
                ResultSet resultSet = null;
                if (stmt.getString("p_msg_desc_out")  != null && stmt.getString("p_msg_desc_out").equals("SUCCESS")) {

                    if(dto.getClgTranCode() == null) {
                        resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                transactionCodesDto = new TransactionCodesDto();
                                transactionCodesDto.setClgTranCode(resultSet.getString("CLG_TRAN_CODE"));
                                transactionCodesDto.setClgTranCodeDesc(resultSet.getString("CLG_TRAN_CODE_DESC"));
                                transactionCodesDtoList.add(transactionCodesDto);
                            }
                        }
                    }
                    else {
                        resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                transactionCodesDto = new TransactionCodesDto();
                                transactionCodesDto.setId(Integer.valueOf(resultSet.getString("ID")));
                                transactionCodesDto.setClgTranCode(resultSet.getString("CLG_TRAN_CODE"));
                                transactionCodesDto.setClgTranCodeDesc(resultSet.getString("CLG_TRAN_CODE_DESC"));
                                transactionCodesDto.setInstrumentType(resultSet.getString("INSTRUMENT_TYPE"));
                                transactionCodesDto.setSchemeCode(resultSet.getString("SCHEME_CODE"));
                                transactionCodesDto.setCreatedBy(resultSet.getString("CREATED_BY"));
                                transactionCodesDto.setCreatDate(resultSet.getString("CREATED_DATE"));
                                transactionCodesDto.setModifiedBy(resultSet.getString("MODIFIED_BY"));
                                transactionCodesDto.setModDate(resultSet.getString("LAST_MOD_DATE"));
                                transactionCodesDto.setVerifiedFlag(resultSet.getString("IS_VERIFIED"));
                                transactionCodesDto.setVerifiedBy(resultSet.getString("VERIFIED_BY"));
                                transactionCodesDto.setVerDate(resultSet.getString("VERIFIED_DATE"));
                                transactionCodesDto.setFileName(resultSet.getString("FILE_NAME"));
                                transactionCodesDto.setAddText1(resultSet.getString("ADD_TEXT1"));
                                transactionCodesDto.setAddText2(resultSet.getString("ADD_TEXT2"));
                                transactionCodesDto.setAddText3(resultSet.getString("ADD_TEXT3"));
                                transactionCodesDtoList.add(transactionCodesDto);
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
        responseDataDTO.setResult(transactionCodesDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO modifySubmit(TransactionCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_LST_TRAN_CODE_SP_MOD.getProcName());
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
    public ResponseDataDTO inquiry(final TransactionCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        TransactionCodesDto transactionCodesDto = null;
        List<TransactionCodesDto> transactionCodesDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_LST_TRAN_CODE_SP_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getClgTranCode());
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

                    if(dto.getClgTranCode() == null) {
                        resultSet = (ResultSet) stmt.getObject(11);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                transactionCodesDto = new TransactionCodesDto();
                                transactionCodesDto.setClgTranCode(resultSet.getString(1));
                                transactionCodesDto.setClgTranCodeDesc(resultSet.getString(2));
                                transactionCodesDtoList.add(transactionCodesDto);
                            }
                        }
                    }
                    else {
                        transactionCodesDto = new TransactionCodesDto();
                        transactionCodesDto.setListofTransactionCodesOldNewValue(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(11)));
                        transactionCodesDto.setCreatedBy(stmt.getString(3));
                        transactionCodesDto.setCreatDate(stmt.getString(4));
                        transactionCodesDto.setModifiedBy(stmt.getString(5));
                        transactionCodesDto.setModDate(stmt.getString(6));
                        transactionCodesDto.setVerifiedBy(stmt.getString(7));
                        transactionCodesDto.setVerDate(stmt.getString(8));
                        transactionCodesDto.setPreviousOperationType(stmt.getString(9));
                        transactionCodesDto.setVerifiedFlag(stmt.getString(10));
                        transactionCodesDtoList.add(transactionCodesDto);
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
        responseDataDTO.setResult(transactionCodesDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(final TransactionCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        TransactionCodesDto transactionCodesDto = null;
        List<TransactionCodesDto> transactionCodesDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_LST_TRAN_CODE_SP_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getClgTranCode());
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

                    if(dto.getClgTranCode() == null) {
                        resultSet = (ResultSet) stmt.getObject(13);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                transactionCodesDto = new TransactionCodesDto();
                                transactionCodesDto.setClgTranCode(resultSet.getString(1));
                                transactionCodesDto.setClgTranCodeDesc(resultSet.getString(2));
                                transactionCodesDtoList.add(transactionCodesDto);
                            }
                        }
                    }
                    else {
                        transactionCodesDto = new TransactionCodesDto();
                        transactionCodesDto.setListofTransactionCodesOldNewValue(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(13)));
                        transactionCodesDto.setId(Integer.valueOf(stmt.getString(4)));
                        transactionCodesDto.setCreatedBy(stmt.getString(5));
                        transactionCodesDto.setCreatDate(stmt.getString(6));
                        transactionCodesDto.setModifiedBy(stmt.getString(7));
                        transactionCodesDto.setModDate(stmt.getString(8));
                        transactionCodesDto.setVerifiedBy(stmt.getString(9));
                        transactionCodesDto.setVerDate(stmt.getString(10));
                        transactionCodesDto.setPreviousOperationType(stmt.getString(11));
                        transactionCodesDto.setVerifiedFlag(stmt.getString(12));
                        transactionCodesDtoList.add(transactionCodesDto);
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
        responseDataDTO.setResult(transactionCodesDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO verifySubmit(final TransactionCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_LST_TRAN_CODE_SP_VER.getProcName());
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
    public ResponseDataDTO deleteSubmit(final  TransactionCodesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_LST_TRAN_CODE_SP_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,  String.valueOf(dto.getId()));
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


    /**
     * This ios
     */

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.TransactionCodesDao#delete(int)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void delete(final int id) {
        final Session session = getSession();
        ;
        final Query query = session.createQuery(
                "update TransactionCodes set deleteFlag = :deleteFlag where id = :id and isVerified=:isVerified");
        query.setParameter(DELETE, Boolean.TRUE);
        query.setParameter(ID, id);
        query.setParameter(VERIFIED, Boolean.FALSE);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#delete(java.lang.
     * String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void delete(final String fileName) {
        final Session session = getSession();
        ;
        final Query<Integer> query = session.createQuery("delete from TransactionCodes where fileName = :fileName");
        query.setParameter(FILE_NAME, fileName);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#delete(com.isl.gbm.
     * cbs. integration.model.TransactionCodes)
     */
    @Override
    public void delete(final TransactionCodes transactionCodes) {
        final Session session = getSession();
        ;
        session.delete(transactionCodes);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#fileNameExists(java.
     * lang.String)
     */
    @Override
    public boolean fileNameExists(final String transactionFileName) {
        final Session session = getSession();
        ;
        final Query<String> query = session.createQuery(
                "select fileName from TransactionCodes where fileName = :fileName group by fileName", String.class);
        query.setParameter(FILE_NAME, transactionFileName);
        final List<String> fileNameList = query.list();
        return CollectionUtils.size(fileNameList) > 0;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.TransactionCodesDao#getAllRecords()
     */
    @Override
    public List<TransactionCodes> getAllRecords() {
        return this.getUnDeletedRecords(Boolean.FALSE);
    }

    /**
     * Gets the records.
     *
     * @param verified the verified
     * @return the records
     */
    private List<TransactionCodes> getRecords(final Boolean verified) {
        final Session session = getSession();
        ;
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<TransactionCodes> criteriaQuery = builder.createQuery(TransactionCodes.class);
        final Root<TransactionCodes> root = criteriaQuery.from(TransactionCodes.class);
        criteriaQuery.select(root).where(builder.equal(root.get(VERIFIED), verified),
                builder.equal(root.get(DELETE), Boolean.FALSE));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#getTranDetailsList(
     * java. lang.String)
     */
    @Override
    public List<TransactionCodes> getTranDetailsList(final String tranCode) {
        final Session session = getSession();
        String hql;
        hql = "from TransactionCodes where deleteFlag= :deleteFlag";
        if (StringUtils.isNotBlank(tranCode)) {
            hql = "from TransactionCodes c where c.clgTranCode like :clgTranCode and c.deleteFlag= :deleteFlag";
        }
        final Query<TransactionCodes> query = session.createQuery(hql, TransactionCodes.class);
        if (StringUtils.isNotBlank(tranCode)) {
            query.setParameter(CLG_TRAN_CODE, tranCode + "%");
        }
        query.setParameter(DELETE, Boolean.FALSE);
        return query.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.TransactionCodesDao#
     * getTransactionDetails( java.lang.Integer)
     */
    @Override
    public TransactionCodes getTransactionDetails(final Integer id) {
        final Session session = getSession();
        ;
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<TransactionCodes> criteriaQuery = builder.createQuery(TransactionCodes.class);
        final Root<TransactionCodes> root = criteriaQuery.from(TransactionCodes.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), id));
        final TransactionCodes codes = session.createQuery(criteriaQuery).getResultList()
                .stream().findFirst().orElse(null);
        if (null != codes) {
            session.evict(codes);
        }
        return codes;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.TransactionCodesDao#
     * getTransactionDetails( java.lang.String, java.lang.String)
     */
    @Override
    public List<TransactionCodes> getTransactionDetails(final String clgTranCode, final String instrumentType) {
        final Session session = getSession();
        ;
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<TransactionCodes> criteriaQuery = builder.createQuery(TransactionCodes.class);
        final Root<TransactionCodes> root = criteriaQuery.from(TransactionCodes.class);
        criteriaQuery.select(root).where(builder.equal(root.get(CLG_TRAN_CODE), clgTranCode),
                builder.equal(root.get(INSTRUMENT_TYPE), instrumentType));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Gets the un deleted records.
     *
     * @param deleteFlag the delete flag
     * @return the un deleted records
     */

    private List<TransactionCodes> getUnDeletedRecords(final Boolean deleteFlag) {
        final Session session = getSession();
        ;
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<TransactionCodes> criteriaQuery = builder.createQuery(TransactionCodes.class);
        final Root<TransactionCodes> root = criteriaQuery.from(TransactionCodes.class);
        criteriaQuery.select(root).where(builder.equal(root.get(DELETE), deleteFlag));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        final Session session = getSession();
        ;
        final Query<String> query = session.createQuery(
                "select fileName from TransactionCodes where isVerified = :isVerified  and fileName is not null group by fileName",
                String.class);
        query.setParameter(VERIFIED, Boolean.FALSE);
        return query.list();
    }

    @Override
    public List<String> getInquiryFiles() {
        final Session session = getSession();
        ;
        final Query<String> query = session.createQuery(
                "select fileName from TransactionCodes where fileName is not null group by fileName",
                String.class);
        //query.setParameter(VERIFIED, Boolean.FALSE);
        return query.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#getUnverifiedRecords(
     * )
     */
    @Override
    public List<TransactionCodes> getUnverifiedRecords() {
        return this.getRecords(Boolean.FALSE);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.TransactionCodesDao#
     * getUnverifiedTransactions(java.lang.String)
     */
    @Override
    public List<TransactionCodes> getUnverifiedTransactions(final String fileName) {
        final Session session = getSession();
        ;
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<TransactionCodes> criteriaQuery = builder.createQuery(TransactionCodes.class);
        final Root<TransactionCodes> root = criteriaQuery.from(TransactionCodes.class);
        criteriaQuery.select(root).where(builder.equal(root.get(FILE_NAME), fileName));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#getVerifiedRecords()
     */
    @Override
    public List<TransactionCodes> getVerifiedRecords() {
        return this.getRecords(Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#isTranCode(java.lang.
     * String)
     */
    @Override
    public boolean isTranCode(final String clgTranCode) {
        final Session session = getSession();
        ;
        final Query<String> query = session.createQuery(
                "select clgTranCode from TransactionCodes where clgTranCode = :clgTranCode group by clgTranCode",
                String.class);
        query.setParameter(CLG_TRAN_CODE, clgTranCode);
        final List<String> tranCodeList = query.list();
        return CollectionUtils.size(tranCodeList) > 0;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.TransactionCodesDao#list()
     */
    @Override
    public List<TransactionCodes> list() {
        return this.getUnDeletedRecords(Boolean.FALSE);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#saveOrUpdate(com.isl.
     * gbm.cbs.integration.model.TransactionCodes)
     */
    @Override
    public void saveOrUpdate(final TransactionCodes transactionCodes) {
        final Session session = getSession();
        ;
        session.saveOrUpdate(transactionCodes);
    }

    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#upload(java.util.
     * List)
     */
    @Override
    public void upload(final List<TransactionCodes> transactionList) {
        final Session session = getSession();
        ;
        for (final TransactionCodes transaction : transactionList) {
            session.saveOrUpdate(transaction);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#verify(java.lang.
     * String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void verify(final String fileName) {
        final Session session = getSession();
        ;
        final Query<Integer> query = session
                .createQuery("update TransactionCodes set isVerified = :isVerified ,verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where fileName = :fileName");
        query.setParameter(VERIFIED, Boolean.TRUE);
        query.setParameter(FILE_NAME, fileName);
        query.setParameter("verifiedBy", UserAccountUtils.getCurrentUsername());
        query.setParameter("verifiedDate", new Date());
        query.setParameter("auditable", Boolean.TRUE);
        query.executeUpdate();

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.TransactionCodesDao#verify(com.isl.gbm.
     * cbs. integration.model.TransactionCodes)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final TransactionCodes transactionCodes) {
        final Session session = getSession();
        ;
        final String HQL = "update TransactionCodes set isVerified = :verified, verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where id = :id";
        final Query query = session.createQuery(HQL);
        query.setParameter("verified", Boolean.TRUE);
        query.setParameter("verifiedBy", transactionCodes.getVerifiedBy());
        query.setParameter("verifiedDate", transactionCodes.getVerifiedDate());
        query.setParameter("auditable", Boolean.TRUE);
        query.setParameter(ID, transactionCodes.getId());
        query.executeUpdate();
    }
}
