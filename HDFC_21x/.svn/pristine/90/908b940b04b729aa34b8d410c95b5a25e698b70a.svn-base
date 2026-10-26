package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TocSlabDetailsDto;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dao.RbiBondTocSlabMaintenanceDAO;
import com.isl.gbm.rbibond.dto.RbiBondTocSlabMaintenanceDTO;
import com.isl.gbm.rbibond.util.RbiBondProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RbiBondTocSlabMaintenanceDAOmpl implements RbiBondTocSlabMaintenanceDAO {
    private static final Logger log = LoggerFactory.getLogger(RbiBondTocSlabMaintenanceDAOmpl.class);
    private static final String CGST_REGEX =
            "^(100(\\.0)?|([1-9]?\\d(\\.\\d{1,2})?)|0(\\.\\d{1,2})?)$";

    private static final String GST_PERCENT_REGEX =
            "^(100(\\.0)?|([1-9]?\\d(\\.\\d{1,2})?)|0(\\.\\d{1,2})?)$";

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Override
    public ResponseDataDTO selectModifySlab(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO2;
        TocSlabDetailsDto output = new TocSlabDetailsDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SLAB_MOD_SEL.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getEffeFromDate());
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
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(6);
                    List<HashMap<String, String>> torchOut = null;
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("schemeCode", resultSet.getString(1));
                                temp.put("schemeName", resultSet.getString(2));
                                temp.put("schemeStatus", resultSet.getString(3));
                                temp.put("versionNo", resultSet.getString(4));
                                temp.put("effFromDate", resultSet.getString(5));
                                   torchOut.add(temp);
                            }
                        }
                        output.setSchemeTorchList(torchOut);
                    }

                     resultSet = (ResultSet) stmt.getObject(5);
                    if (rbiBondTocSlabMaintenanceDTO.getEffeFromDate() == null) {
                        rbiBondTocSlabMaintenanceDTO2 = new RbiBondTocSlabMaintenanceDTO();
                        while (resultSet.next()) {
                            rbiBondTocSlabMaintenanceDTO2.setEffeFromDate(resultSet.getString(2));
                        }
                        responseDataDTO.setResult(rbiBondTocSlabMaintenanceDTO2);
                    } else {
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(5)));
                        output.setPreviousOperationType(stmt.getString(7));
                        output.setCreatedBy(stmt.getString(8));
                        output.setCreatedDate(stmt.getString(9));
                        output.setModifiedBy(stmt.getString(10));
                        output.setModifiedDate(stmt.getString(11));
                        output.setVerifiedBy(stmt.getString(12));
                        output.setVerifiedDate(stmt.getString(13));
                        output.setVerifiedStatus(stmt.getString(14));
                        output.setRejectionRemarks(stmt.getString(15));
                        responseDataDTO.setResult(output);
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
    public ResponseDataDTO selectVerifySlab(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        TocSlabDetailsDto output = new TocSlabDetailsDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SLAB_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getVersionNo());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getEffeFromDate());
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
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (rbiBondTocSlabMaintenanceDTO.getSchemeCode() == null) {
                        ResultSet resultSet = (ResultSet) stmt.getObject(15);


                        if (resultSet != null) {

                            HashMap<String, String> temp = null;
                            List<HashMap<String, String>> torchOut = null;
                            torchOut = new ArrayList<>();

                            while (resultSet.next()) {
                                temp = new HashMap<>();
                                temp.put("schemeCode", resultSet.getString(1));
                                temp.put("schemeName", resultSet.getString(2));
                                temp.put("schemeStatus", resultSet.getString(3));
                                temp.put("versionNo", resultSet.getString(4));
                                temp.put("effeFromDate", resultSet.getString(5));
                                torchOut.add(temp);
                            }

                            output.setSchemeTorchList(torchOut);
                        }
                    }
                    else if (rbiBondTocSlabMaintenanceDTO.getSchemeCode() != null){
                        output.setCreatedBy(stmt.getString(6));
                        output.setCreatedDate(stmt.getString(7));
                        output.setModifiedBy(stmt.getString(8));
                        output.setModifiedDate(stmt.getString(9));
                        output.setVerifiedBy(stmt.getString(10));
                        output.setVerifiedDate(stmt.getString(11));
                        output.setPreviousOperationType(stmt.getString(12));
                        output.setVerifiedStatus(stmt.getString(13));
                        output.setRejectionRemarks(stmt.getString(14));
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList((ResultSet) stmt.getObject(15)));
                        responseDataDTO.setResult(output);
                    }
                }
                } else {
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
    public ResponseDataDTO getAddDetails(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        TocSlabDetailsDto output = new TocSlabDetailsDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SLAB_INS_TOR.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getSchemeCode());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) stmt.getObject(4);
                    List<HashMap<String, String>> torchOut = null;
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("schemeCode", resultSet.getString(1));
                                temp.put("schemeName", resultSet.getString(2));
                                temp.put("schemeStatus", resultSet.getString(3));
                                torchOut.add(temp);
                            }
                        }
                        output.setSchemeTorchList(torchOut);
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

    @Override
    public ResponseDataDTO save(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String cgst = rbiBondTocSlabMaintenanceDTO.getCgst();

        if (cgst == null || cgst.trim().isEmpty() || !cgst.trim().matches(CGST_REGEX)) {
            responseDataDTO.setMessage(
                    "CGST is mandatory and must be a valid percentage between 0 and 100 (up to 2 decimal places)"
            );
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String sgst = rbiBondTocSlabMaintenanceDTO.getSgst();

        if (sgst == null || sgst.trim().isEmpty() || !sgst.trim().matches(GST_PERCENT_REGEX)) {
            responseDataDTO.setMessage(
                    "SGST is mandatory and must be a valid percentage between 0 and 100 (up to 2 decimal places)"
            );
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SLAB_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getVersionNo());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getEffeFromDate());
                stmt.setArray(index++, generateArray(connection, rbiBondTocSlabMaintenanceDTO));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO modify(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String cgst = rbiBondTocSlabMaintenanceDTO.getCgst();

        if (cgst == null || cgst.trim().isEmpty() || !cgst.trim().matches(CGST_REGEX)) {
            responseDataDTO.setMessage(
                    "CGST is mandatory and must be a valid percentage between 0 and 100 (up to 2 decimal places)"
            );
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String sgst = rbiBondTocSlabMaintenanceDTO.getSgst();

        if (sgst == null || sgst.trim().isEmpty() || !sgst.trim().matches(GST_PERCENT_REGEX)) {
            responseDataDTO.setMessage(
                    "SGST is mandatory and must be a valid percentage between 0 and 100 (up to 2 decimal places)"
            );
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SLAB_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getSchemeCode());
                if (CollectionUtils.isNotEmpty(rbiBondTocSlabMaintenanceDTO.getColumnValueDetailsList()))
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, rbiBondTocSlabMaintenanceDTO.getColumnValueDetailsList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);

                if (CollectionUtils.isNotEmpty(rbiBondTocSlabMaintenanceDTO.getColumnValueDetailsAddList()))
                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, rbiBondTocSlabMaintenanceDTO.getColumnValueDetailsAddList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                if (CollectionUtils.isNotEmpty(rbiBondTocSlabMaintenanceDTO.getColumnValueDetailsDelList()))

                    stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_DET_ARRAY_OBJ(connection, rbiBondTocSlabMaintenanceDTO.getColumnValueDetailsDelList()));
                else
                    stmt.setNull(index++, Types.ARRAY, CommonConstants.DATABASE_COLLECTION_COL_DET_ARRAY);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO verify(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SLAB_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getVersionNo());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getEffeFromDate());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO delete(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (rbiBondTocSlabMaintenanceDTO.getRejectionRemarks() == null
                || rbiBondTocSlabMaintenanceDTO.getRejectionRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(rbiBondTocSlabMaintenanceDTO.getRejectionRemarks())) {
            responseDataDTO.setMessage("Invalid Rejection Remarks");
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SLAB_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getVersionNo());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getEffeFromDate());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getRejectionRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
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
    public ResponseDataDTO inquiry(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        TocSlabDetailsDto output = new TocSlabDetailsDto();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_TOC_SLAB_INQ.getProcName());
//                stmt.setString(index++,"VELINI");
//                stmt.setString(index++,"101");
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getSchemeCode());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getVersionNo());
                stmt.setString(index++, rbiBondTocSlabMaintenanceDTO.getEffeFromDate());
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
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                ResultSet resultSet2 = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    TocSlabDetailsDto schemCodeList1 = null;
                    List<TocSlabDetailsDto> detailsDtoList1 = new ArrayList<>();
                    resultSet = (ResultSet) stmt.getObject(17);
                    List<HashMap<String, String>> torchOut = null;
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        torchOut = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("schemeCode", resultSet.getString(1));
                                temp.put("schemeName", resultSet.getString(2));
                                temp.put("schemeStatus", resultSet.getString(3));
                                torchOut.add(temp);
                            }
                        }
                        output.setSchemeTorchList(torchOut);
                    }

                    if (StringUtils.isEmpty(rbiBondTocSlabMaintenanceDTO.getVersionNo())) {
                        resultSet2 = (ResultSet) stmt.getObject(15);
                        if (resultSet2 != null) {
                            while (resultSet2.next()) {
                                schemCodeList1 = new TocSlabDetailsDto();
                                schemCodeList1.setVersionNo(resultSet2.getString(1));
                                schemCodeList1.setEffeFromDate(resultSet2.getString(2));
//                                schemCodeList1.setVerifiedStatus(resultSet2.getString(3));
                                detailsDtoList1.add(schemCodeList1);
                            }
                            output.setEffeFromDateList(detailsDtoList1);
                        }
                    } else {
                        resultSet = (ResultSet) stmt.getObject(16);
                        output.setOldNewValueList(DataBaseUtils.generateOldValueNewValueMapList(resultSet));
                        output.setCreatedBy(stmt.getString(6));
                        output.setCreatedDate(stmt.getString(7));
                        output.setModifiedBy(stmt.getString(8));
                        output.setModifiedDate(stmt.getString(9));
                        output.setVerifiedBy(stmt.getString(10));
                        output.setVerifiedDate(stmt.getString(11));
                        output.setPreviousOperationType(stmt.getString(12));
                        output.setVerifiedStatus(stmt.getString(13));
                        output.setRejectionRemarks(stmt.getString(14));

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


    private Array generateArray(Connection connection, RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_COL_TOC, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_TOC_ARRAY, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[rbiBondTocSlabMaintenanceDTO.getTocSlabSubDetailsDTOList().size()];
        RbiBondTocSlabMaintenanceDTO item = null;
        for (int index = 0; index < rbiBondTocSlabMaintenanceDTO.getTocSlabSubDetailsDTOList().size(); index++) {
            Object[] paramArray = new Object[12];
            item = rbiBondTocSlabMaintenanceDTO.getTocSlabSubDetailsDTOList().get(index);
            paramArray[0] = null;
            paramArray[1] = item.getIsFlatrate();
            paramArray[2] = item.getPaymentNature();
            paramArray[3] = item.getMinSlab();
            paramArray[4] = item.getMaxSlab();
            if (item.getTocAmount() == null) {
                paramArray[5] = 0;
            } else {
                paramArray[5] = item.getTocAmount();
            }
            paramArray[6] = item.getTocSlabValue();
            paramArray[7] = item.getRoundOff();
            paramArray[8] = item.getChallanType();
            paramArray[9] = item.getBankGSTN();
            paramArray[10] = rbiBondTocSlabMaintenanceDTO.getCgst();
            paramArray[11] = rbiBondTocSlabMaintenanceDTO.getSgst();
            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

}

