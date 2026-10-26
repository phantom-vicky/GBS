/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao.impl;

import com.isl.gbm.cbs.integration.common.ProcNameEnum;
import com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.model.OtherBankBranchesMaster;
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
 * The Class OtherBankBranchesDaoImpl.
 */
@Repository
public class OtherBankBranchesDaoImpl extends HibernateDao implements OtherBankBranchesDao {


    private static final Logger log = LoggerFactory.getLogger(OtherBankBranchesDaoImpl.class);

    /**
     * The Constant DEL_FLG.
     */
    private static final String DEL_FLG = "delFlg";

    /**
     * The Constant FILE_NAME.
     */
    private static final String FILE_NAME = "filename";

    /**
     * The Constant IS_VERIFIED.
     */
    private static final String IS_VERIFIED = "isVerified";

    private static final String BANK_CODE = "bankCode";

    private static final String BRANCH_CODE = "branchCode";


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;
    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#
     * createOtherBankBranches(java.util.List)
     */
    @Override
    public void createOtherBankBranches(final List<OtherBankBranchesMaster> otherBankBranches) {
        final Session session = getSession();
        for (final OtherBankBranchesMaster offAccPlaceHolderMaster : otherBankBranches) {
            session.saveOrUpdate(offAccPlaceHolderMaster);
        }

    }


    @Override
    public ResponseDataDTO add(OtherBankBranchesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_OTH_BNK_SP_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setArray(index++, generateTY_GBM_PP_COL_DETAILS_OBJ_ARRAY(connection,dto.getColumnValues()));
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
    public ResponseDataDTO modifySelect(final OtherBankBranchesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        OtherBankBranchesDto otherBankBranchesDto = null;
        List<OtherBankBranchesDto> otherBankBranchesDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_OTH_BNK_SP_MOD_SEL.getProcName());
                stmt.setString("p_modifier_id_in", UserAccountUtils.getCurrentUsername());
                stmt.setString("p_modifier_sol_id_in", UserAccountUtils.getCurrentSolId());
                stmt.setString("p_bank_code_in", dto.getBankCode());
                stmt.setString("p_ifsc_code_in", dto.getBankIfscCode());
                stmt.registerOutParameter("p_cursor_val_out", OracleTypes.CURSOR);
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
                                otherBankBranchesDto = new OtherBankBranchesDto();
                                otherBankBranchesDto.setBankCode(resultSet.getString("BANK_CODE"));
                                otherBankBranchesDto.setBankName(resultSet.getString("bank_name"));
                                otherBankBranchesDtoList.add(otherBankBranchesDto);
                            }
                        }
                    }
                    else if(dto.getBankIfscCode() == null){
                        resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                otherBankBranchesDto = new OtherBankBranchesDto();
//                                otherBankBranchesDto.setBranchCode(resultSet.getString("BRANCH_CODE"));
                                otherBankBranchesDto.setBankIfscCode(resultSet.getString("BANK_IFSC_CODE"));
                                otherBankBranchesDtoList.add(otherBankBranchesDto);
                            }
                        }
                    }
                    else {
                        resultSet = (ResultSet) stmt.getObject("p_cursor_val_out");
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                otherBankBranchesDto = new OtherBankBranchesDto();
                                otherBankBranchesDto.setId(resultSet.getString("ID"));
                                otherBankBranchesDto.setBankCode(resultSet.getString("BANK_CODE"));
                                otherBankBranchesDto.setBranchCode(resultSet.getString("BR_CODE"));
                                otherBankBranchesDto.setMicrCentreCode(resultSet.getString("MICR_CENTRE_CODE"));
                                otherBankBranchesDto.setMicrBankCode(resultSet.getString("MICR_BANK_CODE"));
                                otherBankBranchesDto.setMicrBranchCode(resultSet.getString("MICR_BRANCH_CODE"));
                                otherBankBranchesDto.setBranchName(resultSet.getString("BR_NAME"));
                                otherBankBranchesDto.setBranchAddr1(resultSet.getString("BR_ADDR_1"));
                                otherBankBranchesDto.setBranchAddr2(resultSet.getString("BR_ADDR_2"));
                                otherBankBranchesDto.setBranchAddr3(resultSet.getString("BR_ADDR_3"));
                                otherBankBranchesDto.setBranchCityCode(resultSet.getString("BR_CITY_CODE"));
                                otherBankBranchesDto.setBranchStateCode(resultSet.getString("BR_STATE_CODE"));
                                otherBankBranchesDto.setBranchPinCode(resultSet.getString("BR_PIN_CODE"));
                                otherBankBranchesDto.setCreatedBy(resultSet.getString("CREATED_BY"));
                                otherBankBranchesDto.setCreatDate(resultSet.getString("CREATED_DATE"));
                                otherBankBranchesDto.setLastModifiedBy(resultSet.getString("LAST_MOD_BY"));
                                otherBankBranchesDto.setModDate(resultSet.getString("LAST_MOD_DATE"));
                                otherBankBranchesDto.setVerifiedFlag(resultSet.getString("IS_VERIFIED"));
                                otherBankBranchesDto.setVerifiedBy(resultSet.getString("VERIFIED_BY"));
                                otherBankBranchesDto.setVerDate(resultSet.getString("VERIFIED_DATE"));
                                otherBankBranchesDto.setFilename(resultSet.getString("FILENAME"));
                                otherBankBranchesDto.setAdditionalText1(resultSet.getString("ADDITIONAL_1"));
                                otherBankBranchesDto.setAdditionalText2(resultSet.getString("ADDITIONAL_2"));
                                otherBankBranchesDto.setAdditionalText3(resultSet.getString("ADDITIONAL_3"));
                                otherBankBranchesDto.setBankIfscCode(resultSet.getString("bank_ifsc_code"));
                                otherBankBranchesDtoList.add(otherBankBranchesDto);
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
        responseDataDTO.setResult(otherBankBranchesDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO modifySubmit(OtherBankBranchesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_OTH_BNK_SP_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getId());
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
    public ResponseDataDTO inquiry(final OtherBankBranchesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        OtherBankBranchesDto otherBankBranchesDto1 = null;
        List<OtherBankBranchesDto> otherBankBranchesDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_OTH_BNK_SP_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getBankCode());
                stmt.setString(index++, dto.getBankIfscCode());
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
                                otherBankBranchesDto1 = new OtherBankBranchesDto();
                                otherBankBranchesDto1.setBankCode(resultSet.getString(1));
                                otherBankBranchesDto1.setBankName(resultSet.getString(2));
                                otherBankBranchesDtoList.add(otherBankBranchesDto1);
                            }
                        }
                    }
                    else if(dto.getBankIfscCode() ==null){
                        resultSet = (ResultSet) stmt.getObject(12);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                otherBankBranchesDto1 = new OtherBankBranchesDto();
                                otherBankBranchesDto1.setBankIfscCode(resultSet.getString(1));
                                otherBankBranchesDtoList.add(otherBankBranchesDto1);
                            }
                        }
                    }
                    else {
                        otherBankBranchesDto1 = new OtherBankBranchesDto();
                        otherBankBranchesDto1.setCbsBranchesOfOtherBankDetailsOldNewValue(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(12)));
                        otherBankBranchesDto1.setCreatedBy(stmt.getString(4));
                        otherBankBranchesDto1.setCreatDate(stmt.getString(5));
                        otherBankBranchesDto1.setLastModifiedBy(stmt.getString(6));
                        otherBankBranchesDto1.setModDate(stmt.getString(7));
                        otherBankBranchesDto1.setVerifiedBy(stmt.getString(8));
                        otherBankBranchesDto1.setVerDate(stmt.getString(9));
                        otherBankBranchesDto1.setPreviousOperationType(stmt.getString(10));
                        otherBankBranchesDto1.setVerifiedFlag(stmt.getString(11));
                        otherBankBranchesDtoList.add(otherBankBranchesDto1);
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
        responseDataDTO.setResult(otherBankBranchesDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(final OtherBankBranchesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        OtherBankBranchesDto otherBankBranchesDto1 = null;
        List<OtherBankBranchesDto> otherBankBranchesDtoList = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_OTH_BNK_SP_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getBankCode());
                stmt.setString(index++, dto.getBankIfscCode());
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
                        resultSet = (ResultSet) stmt.getObject(14);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                otherBankBranchesDto1 = new OtherBankBranchesDto();
                                otherBankBranchesDto1.setBankCode(resultSet.getString(1));
                                otherBankBranchesDto1.setBankName(resultSet.getString(2));
                                otherBankBranchesDtoList.add(otherBankBranchesDto1);
                            }
                        }
                    }
                    else if(dto.getBankIfscCode() ==null){
                        resultSet = (ResultSet) stmt.getObject(14);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                otherBankBranchesDto1 = new OtherBankBranchesDto();
                                otherBankBranchesDto1.setBankIfscCode(resultSet.getString(1));
                                otherBankBranchesDtoList.add(otherBankBranchesDto1);
                            }
                        }
                    }
                    else {
                        otherBankBranchesDto1 = new OtherBankBranchesDto();
                        otherBankBranchesDto1.setCbsBranchesOfOtherBankDetailsOldNewValue(DataBaseUtils.generateOldValueNewValueMap((ResultSet) stmt.getObject(14)));
                        otherBankBranchesDto1.setId(stmt.getString(5));
                        otherBankBranchesDto1.setCreatedBy(stmt.getString(6));
                        otherBankBranchesDto1.setCreatDate(stmt.getString(7));
                        otherBankBranchesDto1.setLastModifiedBy(stmt.getString(8));
                        otherBankBranchesDto1.setModDate(stmt.getString(9));
                        otherBankBranchesDto1.setVerifiedBy(stmt.getString(10));
                        otherBankBranchesDto1.setVerDate(stmt.getString(11));
                        otherBankBranchesDto1.setPreviousOperationType(stmt.getString(12));
                        otherBankBranchesDto1.setVerifiedFlag(stmt.getString(13));
                        otherBankBranchesDtoList.add(otherBankBranchesDto1);
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
        responseDataDTO.setResult(otherBankBranchesDtoList);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO verifySubmit(final OtherBankBranchesDto dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_OTH_BNK_SP_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++,dto.getId());
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
    public ResponseDataDTO deleteSubmit(final  OtherBankBranchesDto dto) {
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
                stmt = connection.prepareCall(ProcNameEnum.PKG_GBM_CBS_BR_OTH_BNK_SP_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getId());
                stmt.setString(index++, dto.getRejectionRemarks());
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
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#delete(java.lang.
     * Integer)
     */
    @Override
    public void delete(final Integer id) {
        final Session session = getSession();
        final Query query = session.createQuery("update OtherBankBranchesMaster set delFlg = :delFlg where id = :id");
        query.setParameter(DEL_FLG, Boolean.TRUE);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#delete(java.lang.
     * String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void delete(final String fileName) {
        final Session session = getSession();
        final Query query = session.createQuery("delete from OtherBankBranchesMaster where filename = :filename");
        query.setParameter(FILE_NAME, fileName);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#fileNameExists(java.
     * lang.String)
     */
    @Override
    public boolean fileNameExists(final String fileName) {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select filename from OtherBankBranchesMaster where filename= :filename group by filename",
                String.class);
        query.setParameter(FILE_NAME, fileName);
        final List<String> fileNameList = query.list();
        return CollectionUtils.size(fileNameList) > 0;
    }

    @Override
    public List<OtherBankBranchesMaster> getVerifiedBankBranchesList(final String bankCode, final String branchCode) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<OtherBankBranchesMaster> criteriaQuery = builder.createQuery(OtherBankBranchesMaster.class);
        final Root<OtherBankBranchesMaster> root = criteriaQuery.from(OtherBankBranchesMaster.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get(DEL_FLG), Boolean.FALSE), builder.equal(root.get(IS_VERIFIED), Boolean.TRUE),
                builder.equal(root.get(BANK_CODE), bankCode), builder.like(root.get(BRANCH_CODE), branchCode + "%"));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#getBankBranchesList(
     * java .lang.String, java.lang.String)
     */
    @Override
    public List<OtherBankBranchesMaster> getBankBranchesList(final String bankCode, final String branchCode) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<OtherBankBranchesMaster> criteriaQuery = builder.createQuery(OtherBankBranchesMaster.class);
        final Root<OtherBankBranchesMaster> root = criteriaQuery.from(OtherBankBranchesMaster.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get(DEL_FLG), Boolean.FALSE),
                builder.equal(root.get(BANK_CODE), bankCode), builder.like(root.get(BRANCH_CODE), branchCode + "%"));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#
     * getOtherBankBranchesDetails(java.lang.Integer)
     */
    @Override
    public OtherBankBranchesMaster getOtherBankBranchesDetails(final Integer branchId) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<OtherBankBranchesMaster> criteriaQuery = builder.createQuery(OtherBankBranchesMaster.class);
        final Root<OtherBankBranchesMaster> root = criteriaQuery.from(OtherBankBranchesMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get("id"), branchId),
                builder.equal(root.get(DEL_FLG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        final OtherBankBranchesMaster master = session.createQuery(criteriaQuery).getResultList()
                .stream().findFirst().orElse(null);
        if (null != master) {
            session.evict(master);
        }
        return master;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#
     * getOtherBankBranchesDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<OtherBankBranchesMaster> getOtherBankBranchesDetails(final String branchCode, final String bankCode) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<OtherBankBranchesMaster> criteriaQuery = builder.createQuery(OtherBankBranchesMaster.class);
        final Root<OtherBankBranchesMaster> root = criteriaQuery.from(OtherBankBranchesMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(BRANCH_CODE), branchCode),
                builder.equal(root.get(BANK_CODE), bankCode), builder.equal(root.get(DEL_FLG), Boolean.FALSE));
        return session.createQuery(criteriaQuery).getResultList();

    }

    /**
     * Gets the records.
     *
     * @param verified the verified
     * @return the records
     */
    private List<OtherBankBranchesMaster> getRecords(final Boolean verified) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<OtherBankBranchesMaster> criteriaQuery = builder.createQuery(OtherBankBranchesMaster.class);
        final Root<OtherBankBranchesMaster> root = criteriaQuery.from(OtherBankBranchesMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(IS_VERIFIED), verified),
                builder.equal(root.get(DEL_FLG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select filename from OtherBankBranchesMaster where isVerified = :isVerified and filename is not null group by filename",
                String.class);
        query.setParameter(IS_VERIFIED, Boolean.FALSE);
        return query.list();
    }


    @Override
    public List<String> getInquiryFiles() {
        final Session session = getSession();
        final Query<String> query = session.createQuery(
                "select filename from OtherBankBranchesMaster where filename is not null group by filename",
                String.class);
        return query.list();
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#getUnverifiedRecords
     * ()
     */
    @Override
    public List<OtherBankBranchesMaster> getUnverifiedRecords() {
        return this.getRecords(Boolean.FALSE);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#getUnverifiedRecords
     * (java.lang.String)
     */
    @Override
    public List<OtherBankBranchesMaster> getUnverifiedRecords(final String fileName) {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<OtherBankBranchesMaster> criteriaQuery = builder.createQuery(OtherBankBranchesMaster.class);
        final Root<OtherBankBranchesMaster> root = criteriaQuery.from(OtherBankBranchesMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(FILE_NAME), fileName), builder.equal(root.get(DEL_FLG), Boolean.FALSE));
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#getVerifiedRecords()
     */
    @Override
    public List<OtherBankBranchesMaster> getVerifiedRecords() {
        return this.getRecords(Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#list()
     */
    @Override
    public List<OtherBankBranchesMaster> list() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create Criteria
        final CriteriaQuery<OtherBankBranchesMaster> criteriaQuery = builder.createQuery(OtherBankBranchesMaster.class);
        final Root<OtherBankBranchesMaster> root = criteriaQuery.from(OtherBankBranchesMaster.class);
        criteriaQuery.select(root).where(builder.equal(root.get(DEL_FLG), Boolean.FALSE));
        // Use criteria to query with session to fetch all accounts
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#recordList()
     */
    @Override
    public List<OtherBankBranchesMaster> recordList() {
        final Session session = getSession();
        final CriteriaBuilder builder = session.getCriteriaBuilder();
        final CriteriaQuery<OtherBankBranchesMaster> criteriaQuery = builder.createQuery(OtherBankBranchesMaster.class);
        final Root<OtherBankBranchesMaster> root = criteriaQuery.from(OtherBankBranchesMaster.class);
        criteriaQuery.select(root);
        return session.createQuery(criteriaQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#saveOrUpdate(com.isl
     * .gbm.cbs.integration.model.OtherBankBranchesMaster)
     */
    @Override
    public void saveOrUpdate(final OtherBankBranchesMaster masterDetails) {
        getSession().saveOrUpdate(masterDetails);
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#verify(com.isl.gbm.
     * cbs. integration.model.OtherBankBranchesMaster)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final OtherBankBranchesMaster details) {
        final Session session = getSession();
        final String HQL = "update OtherBankBranchesMaster set isVerified = :isVerified, verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where id = :id";
        final Query query = session.createQuery(HQL);
        query.setParameter(IS_VERIFIED, Boolean.TRUE);
        query.setParameter("verifiedBy", details.getVerifiedBy());
        query.setParameter("verifiedDate", details.getVerifiedDate());
        query.setParameter("auditable", Boolean.TRUE);
        query.setParameter("id", details.getId());
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao#verify(java.lang.
     * String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(final String fileName) {
        final Session session = getSession();
        final Query query = session
                .createQuery("update OtherBankBranchesMaster set isVerified = :isVerified ,verifiedBy = :verifiedBy, verifiedDate = :verifiedDate, auditable = :auditable where filename = :filename");
        query.setParameter(IS_VERIFIED, Boolean.TRUE);
        query.setParameter(FILE_NAME, fileName);
        query.setParameter("verifiedBy", UserAccountUtils.getCurrentUsername());
        query.setParameter("verifiedDate", new Date());
        query.setParameter("auditable", Boolean.TRUE);
        query.executeUpdate();
    }
}
