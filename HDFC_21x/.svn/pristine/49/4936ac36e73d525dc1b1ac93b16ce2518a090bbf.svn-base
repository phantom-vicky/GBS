package com.isl.gbm.common.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dao.TocSlabDetailsDao;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TocSlabDetailsDto;
import com.isl.gbm.common.dto.TocSlabSubDetailsDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.common.util.PpfProcNamesEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TocSlabDetailsDaoImpl implements TocSlabDetailsDao {

    private static final Logger log = LoggerFactory.getLogger(TocSlabDetailsDaoImpl.class);


    @Autowired
    ConnectionPoolFactory connectionPoolFactory;


    @Override
    public ResponseDataDTO selectModifySlab(TocSlabDetailsDto tocSlabDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        TocSlabDetailsDto output = new TocSlabDetailsDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(PpfProcNamesEnum.SP_GBM_KVP_TOC_SLAB_MOD_SEL.getProcName());
                //stmt.setString(index++,"PAVAN");
                //stmt.setString(index++,"101");
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, tocSlabDetailsDto.getSchemeCode());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(4)));
                    output.setPreviousOperationType(stmt.getString(5));
                    output.setCreatedBy(stmt.getString(6));
                    output.setCreatedDate(stmt.getString(7));
                    output.setModifiedBy(stmt.getString(8));
                    output.setModifiedDate(stmt.getString(9));
                    output.setVerifiedBy(stmt.getString(10));
                    output.setVerifiedDate(stmt.getString(11));
                    output.setVerifiedStatus(stmt.getString(12));
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
        responseDataDTO.setResult(output);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO selectVerifySlab(TocSlabDetailsDto tocSlabDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        TocSlabDetailsDto output = new TocSlabDetailsDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(PpfProcNamesEnum.SP_GBM_KVP_TOC_SLAB_VER_SEL.getProcName());
                //stmt.setString(index++,"PAVAN");
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, tocSlabDetailsDto.getSchemeCode());
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
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    output.setCreatedBy(stmt.getString(3));
                    output.setCreatedDate(stmt.getString(4));
                    output.setModifiedBy(stmt.getString(5));
                    output.setModifiedDate(stmt.getString(6));
                    output.setVerifiedBy(stmt.getString(7));
                    output.setVerifiedDate(stmt.getString(8));
                    output.setPreviousOperationType(stmt.getString(9));
                    output.setVerifiedStatus(stmt.getString(10));
                    output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(11)));
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
        responseDataDTO.setResult(output);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAddDetails(TocSlabDetailsDto tocSlabDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        TocSlabDetailsDto output = new TocSlabDetailsDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(PpfProcNamesEnum.SP_GBM_KVP_TOC_SLAB_INS_TOR.getProcName());
                stmt.setString(index++, tocSlabDetailsDto.getSchemeCode());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
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
    public ResponseDataDTO save(TocSlabDetailsDto tocSlabDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        TocSlabDetailsDto output = new TocSlabDetailsDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(PpfProcNamesEnum.SP_GBM_KVP_TOC_SLAB_INS.getProcName());
                stmt.setString(index++, tocSlabDetailsDto.getSchemeCode());
                stmt.setString(index++, tocSlabDetailsDto.getVersionNo());
                stmt.setString(index++, tocSlabDetailsDto.getEffeFromDate());
                stmt.setArray(index++, generateArray(connection, tocSlabDetailsDto));
                //stmt.setString(index++, "PRADEEP");
                //stmt.setString(index++, "101");
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
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
    public ResponseDataDTO modify(TocSlabDetailsDto tocSlabDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        TocSlabDetailsDto output = new TocSlabDetailsDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(PpfProcNamesEnum.SP_GBM_KVP_TOC_SLAB_MOD.getProcName());
                //stmt.setString(index++, "PRADEEP");
                //stmt.setString(index++, "101");
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, tocSlabDetailsDto.getSchemeCode());
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, tocSlabDetailsDto.getColumnValueDetailsList()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
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
    public ResponseDataDTO verify(TocSlabDetailsDto tocSlabDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(PpfProcNamesEnum.SP_GBM_KVP_TOC_SLAB_VER.getProcName());
                stmt.setString(index++, tocSlabDetailsDto.getSchemeCode());
                stmt.setString(index++, tocSlabDetailsDto.getVersionNo());
                stmt.setString(index++, tocSlabDetailsDto.getEffeFromDate());
                //stmt.setString(index++, "PRADEEP");
                //stmt.setString(index++, "101");
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
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
    public ResponseDataDTO delete(TocSlabDetailsDto tocSlabDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(PpfProcNamesEnum.SP_GBM_KVP_TOC_SLAB_DEL.getProcName());
                stmt.setString(index++, tocSlabDetailsDto.getSchemeCode());
                stmt.setString(index++, tocSlabDetailsDto.getVersionNo());
                stmt.setString(index++, tocSlabDetailsDto.getEffeFromDate());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
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
    public ResponseDataDTO inquiry(TocSlabDetailsDto tocSlabDetailsDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        TocSlabDetailsDto output = new TocSlabDetailsDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(PpfProcNamesEnum.SP_GBM_KVP_TOC_SLAB_INQ.getProcName());
                //stmt.setString(index++,"li");
                //stmt.setString(index++,"101");
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, tocSlabDetailsDto.getSchemeCode());
                stmt.setString(index++, tocSlabDetailsDto.getVersionNo());
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
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                ResultSet resultSet1 = null;
                ResultSet resultSet2 = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    TocSlabDetailsDto schemCodeList = null;
                    TocSlabDetailsDto schemCodeList1 = null;
                    List<TocSlabDetailsDto> detailsDtoList = new ArrayList<>();
                    List<TocSlabDetailsDto> detailsDtoList1 = new ArrayList<>();


                    if (StringUtils.isEmpty(tocSlabDetailsDto.getVersionNo())) {
                        resultSet2 = (ResultSet) stmt.getObject(13);
                        if (resultSet2 != null) {
                            while (resultSet2.next()) {
                                schemCodeList1 = new TocSlabDetailsDto();
                                schemCodeList1.setVersionNo(resultSet2.getString(1));
                                schemCodeList1.setEffeFromDate(resultSet2.getString(2));
                                schemCodeList1.setVerifiedStatus(resultSet2.getString(3));
                                detailsDtoList1.add(schemCodeList1);
                            }
                            output.setEffeFromDateList(detailsDtoList1);
                        }
                    } else {
                        resultSet = (ResultSet) stmt.getObject(14);
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        output.setCreatedBy(stmt.getString(5));
                        output.setCreatedDate(stmt.getString(6));
                        output.setModifiedBy(stmt.getString(7));
                        output.setModifiedDate(stmt.getString(8));
                        output.setVerifiedBy(stmt.getString(9));
                        output.setVerifiedDate(stmt.getString(10));
                        output.setPreviousOperationType(stmt.getString(11));
                        output.setVerifiedStatus(stmt.getString(12));

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
        responseDataDTO.setResult(output);
        return responseDataDTO;
    }

//    private Array generateArray(Connection connection, List<TocSlabSubDetailsDTO> slabSubDetailsDTOList) throws SQLException {
//        List<TocSlabSubDetailsDTO> slabSubDetailsDTOList=tocSlabDetailsDto.getSlabSubDetailsDTOList();
//        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COL_TOC, connection);
//        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_TOC_ARRAY, connection);
//        STRUCT[] genericTableInsertStructs = new STRUCT[slabSubDetailsDTOList.size()];
//        TocSlabSubDetailsDTO tocSlabSubDetailsDTO = null;
//        for (int index = 0; index < slabSubDetailsDTOList.size(); index++) {
//            Object[] paramArray = new Object[12];
//            tocSlabSubDetailsDTO = slabSubDetailsDTOList.get(index);
//            paramArray[0] = null;
//            paramArray[1] = tocSlabSubDetailsDTO.getIsFlatrate();
//            paramArray[2] = tocSlabSubDetailsDTO.getPaymentNature();
//            paramArray[3] = tocSlabSubDetailsDTO.getMinSlab();
//            paramArray[4] = tocSlabSubDetailsDTO.getMaxSlab();
//            if (tocSlabSubDetailsDTO.getTocAmount() == null) {
//                paramArray[5] = 0;
//            } else {
//                paramArray[5] = tocSlabSubDetailsDTO.getTocAmount();
//            }
//            paramArray[6] = tocSlabSubDetailsDTO.getTocSlabValue();
//            paramArray[7] = tocSlabSubDetailsDTO.getRoundOff();
//            paramArray[8] = tocSlabSubDetailsDTO.getChallanType();
//            paramArray[9] = tocslab.getBankGstTinNo();
//            paramArray[10] = tocSlabSubDetailsDTO.getCgst();
//            paramArray[11] = tocSlabSubDetailsDTO.getSgst();
//
//            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
//        }
//        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
//    }


    private Array generateArray(Connection connection, TocSlabDetailsDto tocSlabDetailsDto) throws SQLException {
        List<TocSlabSubDetailsDTO> slabSubDetailsDTOList=tocSlabDetailsDto.getSlabSubDetailsDTOList();
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COL_TOC, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_TOC_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[slabSubDetailsDTOList.size()];
        TocSlabSubDetailsDTO tocSlabSubDetailsDTO = null;
        for (int index = 0; index < slabSubDetailsDTOList.size(); index++) {
            Object[] paramArray = new Object[12];
            tocSlabSubDetailsDTO = slabSubDetailsDTOList.get(index);
            paramArray[0] = null;
            paramArray[1] = tocSlabSubDetailsDTO.getIsFlatrate();
            paramArray[2] = tocSlabSubDetailsDTO.getPaymentNature();
            paramArray[3] = tocSlabSubDetailsDTO.getMinSlab();
            paramArray[4] = tocSlabSubDetailsDTO.getMaxSlab();
            if (tocSlabSubDetailsDTO.getTocAmount() == null) {
                paramArray[5] = 0;
            } else {
                paramArray[5] = tocSlabSubDetailsDTO.getTocAmount();
            }
            paramArray[6] = tocSlabSubDetailsDTO.getTocSlabValue();
            paramArray[7] = tocSlabSubDetailsDTO.getRoundOff();
            paramArray[8] = tocSlabSubDetailsDTO.getChallanType();
            paramArray[9] = tocSlabDetailsDto.getBankGstTinNo();
            paramArray[10] = tocSlabDetailsDto.getCgst();
            paramArray[11] = tocSlabDetailsDto.getSgst();
            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

}
