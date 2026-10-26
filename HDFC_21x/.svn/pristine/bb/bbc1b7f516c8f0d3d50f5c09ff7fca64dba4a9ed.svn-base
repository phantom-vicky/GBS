package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.FreezeUnfreezeDAO;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.FreezUnfreezDTO;
import com.isl.gbm.rbibond.dto.FreezeUnFreezeWrapperDTO;
import com.isl.gbm.rbibond.dto.OldValueNewValueDTO;
import com.isl.gbm.rbibond.dto.SubscriptionAccClosingOnMaturityDTO;
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

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Repository
public class FreezeUnfreezeDAOImpl implements FreezeUnfreezeDAO {

    private static final Logger log = LoggerFactory.getLogger(FreezeUnfreezeDAOImpl.class);
    private static final String PAN_REGEX =
            "^[A-Z]{5}[0-9]{4}[A-Z]$";

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;


    @Override
    public ResponseDataDTO getAddDetails(FreezUnfreezDTO freezUnfreezDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        FreezUnfreezDTO freezeUnFreezeDTO;
        List<FreezUnfreezDTO> freezeList;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_ADD_VAL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.setString(index++, freezUnfreezDTO.getBlaNo() != null ? freezUnfreezDTO.getBlaNo().trim() : freezUnfreezDTO.getBlaNo());
                statement.setString(index++, freezUnfreezDTO.getSubId() != null ? freezUnfreezDTO.getSubId().trim() : freezUnfreezDTO.getSubId());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(8);
                    if (freezUnfreezDTO.getSchemeCode() == null && resultSet != null) {
                        freezeList = new LinkedList<>();
                        while (resultSet.next()) {
                            freezeUnFreezeDTO = new FreezUnfreezDTO();
                            freezeUnFreezeDTO.setSchemeCode(resultSet.getString(1));
                            freezeUnFreezeDTO.setSchemeName(resultSet.getString(2));
                            freezeUnFreezeDTO.setSchemeStatus(resultSet.getString(3));
                            freezeList.add(freezeUnFreezeDTO);
                        }
                        responseDataDTO.setResult(freezeList);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else if (freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        freezeList = new LinkedList<>();
                        while (resultSet.next()) {
                            freezeUnFreezeDTO = new FreezUnfreezDTO();
                            freezeUnFreezeDTO.setName(resultSet.getString(1));
                            freezeUnFreezeDTO.setDob(resultSet.getString(2));
                            freezeUnFreezeDTO.setBlaNo(resultSet.getString(3));
                            freezeUnFreezeDTO.setSubscriptionId(resultSet.getString(4));
                            freezeUnFreezeDTO.setBondType(resultSet.getString(5));
                            freezeUnFreezeDTO.setSubscriptionAmount(resultSet.getString(6));
                            freezeUnFreezeDTO.setAccountOpenDate(resultSet.getString(7));
                            freezeUnFreezeDTO.setMaturityDate(resultSet.getString(8));
                            freezeUnFreezeDTO.setLastInterestPaid(resultSet.getString(9));
                            freezeList.add(freezeUnFreezeDTO);
                        }
                        responseDataDTO.setResult(freezeList);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    }
                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAddPANDetails(FreezUnfreezDTO freezUnfreezDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new ArrayList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_ADD_PAN_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode() != null ? freezUnfreezDTO.getSchemeCode().trim() : freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(6);
                    if (freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setName(resultSet.getString(1));
                            maturityDTO.setPanNoName(resultSet.getString(2));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    }
                }
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getAddBLADetails(FreezUnfreezDTO freezUnfreezDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new LinkedList<>();
        List<FreezUnfreezDTO> listOfSubIds = new LinkedList<>();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_ADD_BLA_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, "sub".equalsIgnoreCase(freezUnfreezDTO.getPageType()) ? "S" : "B");
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.setString(index++, freezUnfreezDTO.getBlaNo() != null ? freezUnfreezDTO.getBlaNo().trim() : freezUnfreezDTO.getBlaNo());
                statement.setString(index++, freezUnfreezDTO.getSubId() != null ? freezUnfreezDTO.getSubId().trim() : freezUnfreezDTO.getSubId()); // Sub Id
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
                    if ("bla".equalsIgnoreCase(freezUnfreezDTO.getPageType()) && freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setPanNoName(resultSet.getString(1));
                            maturityDTO.setBlaNo(resultSet.getString(2));
                            maturityDTO.setName(resultSet.getString(3));
                            maturityDTO.setModeOfHolding(resultSet.getString(4));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else if ("sub".equalsIgnoreCase(freezUnfreezDTO.getPageType()) && freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        while (resultSet.next()) {
                            FreezUnfreezDTO freezeUnfreezeDTO = new FreezUnfreezDTO();
                            freezeUnfreezeDTO.setPanNoName(resultSet.getString(1));
                            freezeUnfreezeDTO.setBlaNo(resultSet.getString(2));
                            freezeUnfreezeDTO.setName(resultSet.getString(3));
                            freezeUnfreezeDTO.setModeOfHolding(resultSet.getString(4));
                            freezeUnfreezeDTO.setSubId(resultSet.getString(5));
                            listOfSubIds.add(freezeUnfreezeDTO);
                        }
                        responseDataDTO.setResult(listOfSubIds);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;

                    }
                }
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addSubmit(FreezUnfreezDTO freezUnfreezDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_ADD_SUB.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode() != null ? freezUnfreezDTO.getSchemeCode().trim() : freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.setString(index++, freezUnfreezDTO.getBlaNo() != null ? freezUnfreezDTO.getBlaNo().trim() : freezUnfreezDTO.getBlaNo());
                statement.setString(index++, freezUnfreezDTO.getAmountFreeze());
                statement.setString(index++, freezUnfreezDTO.getInterestFreeze());
                statement.setString(index++, freezUnfreezDTO.getTransferAnotherBank());
                statement.setString(index++, freezUnfreezDTO.getMakerRemarks());
                statement.setArray(index++, generateArrayForRejectBondInterestPayments(connection, freezUnfreezDTO.getListOfStrings()));
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    private Array generateArrayForRejectBondInterestPayments(Connection connection, List<String> list) throws
            SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection); // typ_single_col_obj
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection); //typ_single_col
        Struct[] struct = list != null && !list.isEmpty() ?
                list.stream().map(
                        item -> {
                            try {
                                String parsedStr;
                                if (item != null) {
                                    parsedStr = item.trim();
                                } else {
                                    parsedStr = item;
                                }
                                return new STRUCT(genericTableStruct, connection, new Object[]{
                                        parsedStr
                                });
                            } catch (SQLException e) {
                                e.printStackTrace();
                                throw new IllegalArgumentException("Error in creation of Type", e);
                            }
                        }
                ).toArray(Struct[]::new) : new Struct[0];
        return new ARRAY(genericTableDescriptor, connection, struct);
    }


    @Override
    public ResponseDataDTO getInquiryDetails(FreezUnfreezDTO freezUnfreezDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        FreezUnfreezDTO freezeUnFreezeDTO;
        List<FreezUnfreezDTO> freezeList;
        List<OldValueNewValueDTO> oldValueNewValueList;
        FreezeUnFreezeWrapperDTO wrapperDTO = new FreezeUnFreezeWrapperDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_INQ_VAL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.setString(index++, freezUnfreezDTO.getBlaNo() != null ? freezUnfreezDTO.getBlaNo().trim() : freezUnfreezDTO.getBlaNo());
                statement.setString(index++, freezUnfreezDTO.getSubId() != null ? freezUnfreezDTO.getSubId().trim() : freezUnfreezDTO.getSubId());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (freezUnfreezDTO.getSchemeCode() == null) {
                        resultSet = (ResultSet) statement.getObject(18);
                        freezeList = new LinkedList<>();
                        while (resultSet.next()) {
                            freezeUnFreezeDTO = new FreezUnfreezDTO();
                            freezeUnFreezeDTO.setSchemeCode(resultSet.getString(1));
                            freezeUnFreezeDTO.setSchemeName(resultSet.getString(2));
                            freezeUnFreezeDTO.setSchemeStatus(resultSet.getString(3));
                            freezeList.add(freezeUnFreezeDTO);
                        }
                        responseDataDTO.setResult(freezeList);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else if (freezUnfreezDTO.getSchemeCode() != null) {
                        oldValueNewValueList = new LinkedList<>();
                        resultSet = (ResultSet) statement.getObject(18);
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                OldValueNewValueDTO valueDTO = new OldValueNewValueDTO();
                                valueDTO.setColNo(resultSet.getString(1));
                                valueDTO.setOldValue(resultSet.getString(2));
                                valueDTO.setNewValue(resultSet.getString(3));
                                oldValueNewValueList.add(valueDTO);
                            }
                            oldValueNewValueList.forEach(
                                    element -> {
                                        if ("C005".equalsIgnoreCase(element.getColNo())) {
                                            wrapperDTO.setAmountFreezeOldValue(element.getOldValue());
                                            wrapperDTO.setAmountFreezeNewValue(element.getNewValue());
                                        } else if ("C006".equalsIgnoreCase(element.getColNo())) {
                                            wrapperDTO.setInterestFreezeOldValue(element.getOldValue());
                                            wrapperDTO.setInterestFreezeNewValue(element.getNewValue());
                                        } else if ("C007".equalsIgnoreCase(element.getColNo())) {
                                            wrapperDTO.setTransferAnotherBankOldValue(element.getOldValue());
                                            wrapperDTO.setTransferAnotherBankNewValue(element.getNewValue());
                                        } else if ("C008".equalsIgnoreCase(element.getColNo())) {
                                            wrapperDTO.setMakerRemarksOldValue(element.getOldValue());
                                            wrapperDTO.setMakerRemarksNewValue(element.getNewValue());
                                        } else if ("C009".equalsIgnoreCase(element.getColNo())) {
                                            wrapperDTO.setCheckerRemarksOldValue(element.getOldValue());
                                            wrapperDTO.setCheckerRemarksNewValue(element.getNewValue());
                                        }
                                    }
                            );
                        }

                        resultSet = (ResultSet) statement.getObject(19);
                        freezeList = new LinkedList<>();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                freezeUnFreezeDTO = new FreezUnfreezDTO();
                                freezeUnFreezeDTO.setPanNoName(resultSet.getString(1));
                                freezeUnFreezeDTO.setName(resultSet.getString(2));
                                freezeUnFreezeDTO.setDob(resultSet.getString(3));
                                freezeUnFreezeDTO.setBlaNo(resultSet.getString(4));
                                freezeUnFreezeDTO.setSubscriptionId(resultSet.getString(5));
                                freezeUnFreezeDTO.setBondType(resultSet.getString(6));
                                freezeUnFreezeDTO.setAccountOpenDate(resultSet.getString(7));
                                freezeUnFreezeDTO.setMaturityDate(resultSet.getString(8));
                                freezeUnFreezeDTO.setSubscriptionAmount(resultSet.getString(9));
                                freezeUnFreezeDTO.setLastInterestPaid(resultSet.getString(10));
//                                wrapperDTO.setMakerRemarks(resultSet.getString(11));
//                                wrapperDTO.setCheckerRemarks(resultSet.getString(12));
                                freezeList.add(freezeUnFreezeDTO);
                            }
                        }


                        wrapperDTO.setOldValueNewValueList(oldValueNewValueList);
                        wrapperDTO.setCreatedBy(statement.getString(9));
                        wrapperDTO.setCreatedDate(statement.getString(10));
                        wrapperDTO.setModifiedBy(statement.getString(11));
                        wrapperDTO.setModifiedDate(statement.getString(12));
                        wrapperDTO.setVerifiedBy(statement.getString(13));
                        wrapperDTO.setVerifiedDate(statement.getString(14));
                        wrapperDTO.setPreOrpTyp(statement.getString(15));
                        wrapperDTO.setVerifiedStatus(statement.getString(16));
                        wrapperDTO.setFreezeUnfreezeDTOList(freezeList);
                        responseDataDTO.setResult(wrapperDTO);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    }
                }
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getInquiryPANDetails(FreezUnfreezDTO freezUnfreezDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_INQ_PAN_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(6);
                    if (freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new ArrayList<>();
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setName(resultSet.getString(1));
                            maturityDTO.setPanNoName(resultSet.getString(2));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    }
                }
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getInquiryBLADetails(FreezUnfreezDTO freezUnfreezDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_INQ_BLA_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode() != null ? freezUnfreezDTO.getSchemeCode().trim() : freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, "sub".equalsIgnoreCase(freezUnfreezDTO.getPageType()) ? "S" : "B");
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.setString(index++, freezUnfreezDTO.getBlaNo() != null ? freezUnfreezDTO.getBlaNo().trim() : freezUnfreezDTO.getBlaNo());
                statement.setString(index++, freezUnfreezDTO.getSubId() != null ? freezUnfreezDTO.getSubId().trim() : freezUnfreezDTO.getSubId());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info("Executed the sql query :");
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
                    if ("bla".equalsIgnoreCase(freezUnfreezDTO.getPageType()) && freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs = new ArrayList<>();
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setPanNoName(resultSet.getString(1));
                            maturityDTO.setBlaNo(resultSet.getString(2));
                            maturityDTO.setName(resultSet.getString(3));
                            maturityDTO.setModeOfHolding(resultSet.getString(4));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else if ("sub".equalsIgnoreCase(freezUnfreezDTO.getPageType()) && freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        List<FreezUnfreezDTO> listOfSubIds = new ArrayList<>();
                        while (resultSet.next()) {
                            FreezUnfreezDTO freezeUnfreezeDTO = new FreezUnfreezDTO();
                            freezeUnfreezeDTO.setPanNoName(resultSet.getString(1));
                            freezeUnfreezeDTO.setBlaNo(resultSet.getString(2));
                            freezeUnfreezeDTO.setName(resultSet.getString(3));
                            freezeUnfreezeDTO.setModeOfHolding(resultSet.getString(4));
                            freezeUnfreezeDTO.setSubId(resultSet.getString(5));
                            listOfSubIds.add(freezeUnfreezeDTO);
                        }
                        responseDataDTO.setResult(listOfSubIds);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;

                    }
                }
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO verifySelect(FreezUnfreezDTO freezUnfreezDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String schemeCode = freezUnfreezDTO.getSchemeCode();
        if (freezUnfreezDTO.getPageType().equalsIgnoreCase("SCHEME") && freezUnfreezDTO.getSchemeCode() != null) {
            String pan = freezUnfreezDTO.getPanNoName();
            if (pan != null
                    && !pan.trim().isEmpty()
                    && !pan.replaceAll("\\s+", "").toUpperCase().matches(PAN_REGEX)) {
                responseDataDTO.setMessage("PAN must be valid (e.g., ABCDE1234F)");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String bla = freezUnfreezDTO.getBlaNo();
            if (bla == null || bla.trim().isEmpty()) {
                responseDataDTO.setMessage("BLA Number is mandatory");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            String subId = freezUnfreezDTO.getSubId();
            if (subId == null || subId.trim().isEmpty()) {
                responseDataDTO.setMessage("Subscription ID is mandatory");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        FreezUnfreezDTO freezeUnFreezeDTO;
        List<FreezUnfreezDTO> freezeList;
        List<OldValueNewValueDTO> oldValueNewValueList;
        FreezeUnFreezeWrapperDTO wrapperDTO = new FreezeUnFreezeWrapperDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_VER_VAL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.setString(index++, freezUnfreezDTO.getBlaNo() != null ? freezUnfreezDTO.getBlaNo().trim() : freezUnfreezDTO.getBlaNo());
                statement.setString(index++, freezUnfreezDTO.getSubId() != null ? freezUnfreezDTO.getSubId().trim() : freezUnfreezDTO.getSubId());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);

                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (freezUnfreezDTO.getSchemeCode() == null) {
                        resultSet = (ResultSet) statement.getObject(18);
                        freezeList = new LinkedList<>();
                        while (resultSet.next()) {
                            freezeUnFreezeDTO = new FreezUnfreezDTO();
                            freezeUnFreezeDTO.setSchemeCode(resultSet.getString(1));
                            freezeUnFreezeDTO.setSchemeName(resultSet.getString(2));
                            freezeUnFreezeDTO.setSchemeStatus(resultSet.getString(3));
                            freezeList.add(freezeUnFreezeDTO);
                        }
                        responseDataDTO.setResult(freezeList);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else if (freezUnfreezDTO.getSchemeCode() != null) {
                        oldValueNewValueList = new LinkedList<>();
                        resultSet = (ResultSet) statement.getObject(18);
                        while (resultSet.next()) {
                            OldValueNewValueDTO valueDTO = new OldValueNewValueDTO();
                            valueDTO.setColNo(resultSet.getString(1));
                            valueDTO.setOldValue(resultSet.getString(2));
                            valueDTO.setNewValue(resultSet.getString(3));
                            oldValueNewValueList.add(valueDTO);
                        }
                        resultSet = (ResultSet) statement.getObject(19);
                        freezeList = new LinkedList<>();
                        while (resultSet.next()) {
                            freezeUnFreezeDTO = new FreezUnfreezDTO();
                            freezeUnFreezeDTO.setPanNoName(resultSet.getString(1));
                            freezeUnFreezeDTO.setName(resultSet.getString(2));
                            freezeUnFreezeDTO.setDob(resultSet.getString(3));
                            freezeUnFreezeDTO.setBlaNo(resultSet.getString(4));
                            freezeUnFreezeDTO.setSubscriptionId(resultSet.getString(5));
                            freezeUnFreezeDTO.setBondType(resultSet.getString(6));
                            freezeUnFreezeDTO.setAccountOpenDate(resultSet.getString(7));
                            freezeUnFreezeDTO.setMaturityDate(resultSet.getString(8));
                            freezeUnFreezeDTO.setSubscriptionAmount(resultSet.getString(9));
                            freezeUnFreezeDTO.setLastInterestPaid(resultSet.getString(10));
//                            wrapperDTO.setMakerRemarks(resultSet.getString(11));
//                            wrapperDTO.setCheckerRemarks(resultSet.getString(12));
                            freezeList.add(freezeUnFreezeDTO);
                        }
                        oldValueNewValueList.forEach(
                                element -> {
                                    if ("C005".equalsIgnoreCase(element.getColNo())) {
                                        wrapperDTO.setAmountFreezeOldValue(element.getOldValue());
                                        wrapperDTO.setAmountFreezeNewValue(element.getNewValue());
                                    } else if ("C006".equalsIgnoreCase(element.getColNo())) {
                                        wrapperDTO.setInterestFreezeOldValue(element.getOldValue());
                                        wrapperDTO.setInterestFreezeNewValue(element.getNewValue());
                                    } else if ("C007".equalsIgnoreCase(element.getColNo())) {
                                        wrapperDTO.setTransferAnotherBankOldValue(element.getOldValue());
                                        wrapperDTO.setTransferAnotherBankNewValue(element.getNewValue());
                                    } else if ("C008".equalsIgnoreCase(element.getColNo())) {
                                        wrapperDTO.setMakerRemarksOldValue(element.getOldValue());
                                        wrapperDTO.setMakerRemarksNewValue(element.getNewValue());
                                    } else if ("C009".equalsIgnoreCase(element.getColNo())) {
                                        wrapperDTO.setCheckerRemarksOldValue(element.getOldValue());
                                        wrapperDTO.setCheckerRemarksNewValue(element.getNewValue());
                                    }
                                }
                        );
                        wrapperDTO.setOldValueNewValueList(oldValueNewValueList);
                        wrapperDTO.setFreezeUnfreezeDTOList(freezeList);

                        wrapperDTO.setCreatedBy(statement.getString(9));
                        wrapperDTO.setCreatedDate(statement.getString(10));
                        wrapperDTO.setModifiedBy(statement.getString(11));
                        wrapperDTO.setModifiedDate(statement.getString(12));
                        wrapperDTO.setVerifiedBy(statement.getString(13));
                        wrapperDTO.setVerifiedDate(statement.getString(14));
                        wrapperDTO.setPreOrpTyp(statement.getString(15));
                        wrapperDTO.setVerifiedStatus(statement.getString(16));

                        responseDataDTO.setResult(wrapperDTO);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    }
                }

            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);

        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyBLADetails(FreezUnfreezDTO freezUnfreezDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs;
        List<FreezUnfreezDTO> listOfSubIds;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_VER_BLA_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, "sub".equalsIgnoreCase(freezUnfreezDTO.getPageType()) ? "S" : "B");
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.setString(index++, freezUnfreezDTO.getBlaNo() != null ? freezUnfreezDTO.getBlaNo().trim() : freezUnfreezDTO.getBlaNo());
                statement.setString(index++, freezUnfreezDTO.getSubId() != null ? freezUnfreezDTO.getSubId().trim() : freezUnfreezDTO.getSubId());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
                    if ("bla".equalsIgnoreCase(freezUnfreezDTO.getPageType()) && freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        listOfSubDTOs = new LinkedList<>();
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setPanNoName(resultSet.getString(1));
                            maturityDTO.setBlaNo(resultSet.getString(2));
                            maturityDTO.setName(resultSet.getString(3));
                            maturityDTO.setModeOfHolding(resultSet.getString(4));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else if ("sub".equalsIgnoreCase(freezUnfreezDTO.getPageType()) && freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        listOfSubIds = new LinkedList<>();
                        while (resultSet.next()) {
                            FreezUnfreezDTO freezeUnfreezeDTO = new FreezUnfreezDTO();
                            freezeUnfreezeDTO.setPanNoName(resultSet.getString(1));
                            freezeUnfreezeDTO.setBlaNo(resultSet.getString(2));
                            freezeUnfreezeDTO.setName(resultSet.getString(3));
                            freezeUnfreezeDTO.setModeOfHolding(resultSet.getString(4));
                            freezeUnfreezeDTO.setSubId(resultSet.getString(5));
                            listOfSubIds.add(freezeUnfreezeDTO);
                        }
                        responseDataDTO.setResult(listOfSubIds);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    }
                }

            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyPANDetails(FreezUnfreezDTO freezUnfreezDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_VER_PAN_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(6);
                    if (freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        listOfSubDTOs = new LinkedList<>();
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setName(resultSet.getString(1));
                            maturityDTO.setPanNoName(resultSet.getString(2));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    }
                }
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySelect(FreezUnfreezDTO freezUnfreezDTO) {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        FreezUnfreezDTO freezeUnFreezeDTO;
        List<FreezUnfreezDTO> freezeList;
        FreezeUnFreezeWrapperDTO wrapperDTO;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_MOD_VAL.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.setString(index++, freezUnfreezDTO.getBlaNo() != null ? freezUnfreezDTO.getBlaNo().trim() : freezUnfreezDTO.getBlaNo());
                statement.setString(index++, freezUnfreezDTO.getSubId() != null ? freezUnfreezDTO.getSubId().trim() : freezUnfreezDTO.getSubId());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);

                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    if (freezUnfreezDTO.getSchemeCode() == null) {
                        resultSet = (ResultSet) statement.getObject(18);
                        freezeList = new LinkedList<>();
                        while (resultSet.next()) {
                            freezeUnFreezeDTO = new FreezUnfreezDTO();
                            freezeUnFreezeDTO.setSchemeCode(resultSet.getString(1));
                            freezeUnFreezeDTO.setSchemeName(resultSet.getString(2));
                            freezeUnFreezeDTO.setSchemeStatus(resultSet.getString(3));
                            freezeList.add(freezeUnFreezeDTO);
                        }
                        responseDataDTO.setResult(freezeList);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else if (freezUnfreezDTO.getSchemeCode() != null) {
                        wrapperDTO = new FreezeUnFreezeWrapperDTO();
//                        resultSet = (ResultSet) statement.getObject(18);
//                        oldValueNewValueList = new LinkedList<>();
//                        if (resultSet != null) {
//                            while (resultSet.next()) {
//                                OldValueNewValueDTO valueDTO = new OldValueNewValueDTO();
//                                valueDTO.setColNo(resultSet.getString(1));
//                                valueDTO.setOldValue(resultSet.getString(2));
//                                valueDTO.setNewValue(resultSet.getString(3));
//                                oldValueNewValueList.add(valueDTO);
//                            }
//                            oldValueNewValueList.forEach(
//                                    element -> {
//                                        if ("C005".equalsIgnoreCase(element.getColNo())) {
//                                            wrapperDTO.setAmountFreezeOldValue(element.getOldValue());
//                                            wrapperDTO.setAmountFreezeNewValue(element.getNewValue());
//                                        } else if ("C006".equalsIgnoreCase(element.getColNo())) {
//                                            wrapperDTO.setInterestFreezeOldValue(element.getOldValue());
//                                            wrapperDTO.setInterestFreezeNewValue(element.getNewValue());
//                                        } else if ("C007".equalsIgnoreCase(element.getColNo())) {
//                                            wrapperDTO.setTransferAnotherBankOldValue(element.getOldValue());
//                                            wrapperDTO.setTransferAnotherBankNewValue(element.getNewValue());
//                                        }
//                                    }
//                            );
//                        }
                        resultSet = (ResultSet) statement.getObject(19);
                        freezeList = new LinkedList<>();
                        while (resultSet.next()) {
                            freezeUnFreezeDTO = new FreezUnfreezDTO();
                            freezeUnFreezeDTO.setPanNoName(resultSet.getString(1));
                            freezeUnFreezeDTO.setName(resultSet.getString(2));
                            freezeUnFreezeDTO.setDob(resultSet.getString(3));
                            freezeUnFreezeDTO.setBlaNo(resultSet.getString(4));
                            freezeUnFreezeDTO.setSubscriptionId(resultSet.getString(5));
                            freezeUnFreezeDTO.setBondType(resultSet.getString(6));
                            freezeUnFreezeDTO.setAccountOpenDate(resultSet.getString(7));
                            freezeUnFreezeDTO.setMaturityDate(resultSet.getString(8));
                            freezeUnFreezeDTO.setSubscriptionAmount(resultSet.getString(9));
                            freezeUnFreezeDTO.setLastInterestPaid(resultSet.getString(10));
                            wrapperDTO.setMakerRemarks(resultSet.getString(11));
                            wrapperDTO.setCheckerRemarks(resultSet.getString(12));
                            wrapperDTO.setAmountFreeze(resultSet.getString(13));
                            wrapperDTO.setInterestFreeze(resultSet.getString(14));
                            wrapperDTO.setTransferAnotherBank(resultSet.getString(15));
                            freezeList.add(freezeUnFreezeDTO);
                        }

                        wrapperDTO.setFreezeUnfreezeDTOList(freezeList);
                        wrapperDTO.setCreatedBy(statement.getString(9));
                        wrapperDTO.setCreatedDate(statement.getString(10));
                        wrapperDTO.setModifiedBy(statement.getString(11));
                        wrapperDTO.setModifiedDate(statement.getString(12));
                        wrapperDTO.setVerifiedBy(statement.getString(13));
                        wrapperDTO.setVerifiedDate(statement.getString(14));
                        wrapperDTO.setPreOrpTyp(statement.getString(15));
                        wrapperDTO.setVerifiedStatus(statement.getString(16));
                        responseDataDTO.setResult(wrapperDTO);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    }
                }
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifyBLADetails(FreezUnfreezDTO freezUnfreezDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs;
        List<FreezUnfreezDTO> listOfSubIds;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_MOD_BLA_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, "sub".equalsIgnoreCase(freezUnfreezDTO.getPageType()) ? "S" : "B");
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.setString(index++, freezUnfreezDTO.getBlaNo() != null ? freezUnfreezDTO.getBlaNo().trim() : freezUnfreezDTO.getBlaNo());
                statement.setString(index++, freezUnfreezDTO.getSubId() != null ? freezUnfreezDTO.getSubId().trim() : freezUnfreezDTO.getSubId());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(9);
                    if ("bla".equalsIgnoreCase(freezUnfreezDTO.getPageType()) && freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        listOfSubDTOs = new LinkedList<>();
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setPanNoName(resultSet.getString(1));
                            maturityDTO.setBlaNo(resultSet.getString(2));
                            maturityDTO.setName(resultSet.getString(3));
                            maturityDTO.setModeOfHolding(resultSet.getString(4));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    } else if ("sub".equalsIgnoreCase(freezUnfreezDTO.getPageType()) && freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        listOfSubIds = new LinkedList<>();
                        while (resultSet.next()) {
                            FreezUnfreezDTO freezeUnfreezeDTO = new FreezUnfreezDTO();
                            freezeUnfreezeDTO.setPanNoName(resultSet.getString(1));
                            freezeUnfreezeDTO.setBlaNo(resultSet.getString(2));
                            freezeUnfreezeDTO.setName(resultSet.getString(3));
                            freezeUnfreezeDTO.setModeOfHolding(resultSet.getString(4));
                            freezeUnfreezeDTO.setSubId(resultSet.getString(5));
                            listOfSubIds.add(freezeUnfreezeDTO);
                        }
                        responseDataDTO.setResult(listOfSubIds);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;

                    }
                }

            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifyPANDetails(FreezUnfreezDTO freezUnfreezDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        List<SubscriptionAccClosingOnMaturityDTO> listOfSubDTOs;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_MOD_PAN_TORCH.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setString(index++, freezUnfreezDTO.getName() != null ? freezUnfreezDTO.getName().trim() : freezUnfreezDTO.getName());
                statement.setString(index++, freezUnfreezDTO.getPanNoName() != null ? freezUnfreezDTO.getPanNoName().trim() : freezUnfreezDTO.getPanNoName());
                statement.registerOutParameter(index++, OracleTypes.CURSOR);
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
                if (statement.getString(index - 1) != null && statement.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    resultSet = (ResultSet) statement.getObject(6);
                    if (freezUnfreezDTO.getSchemeCode() != null && resultSet != null) {
                        listOfSubDTOs = new LinkedList<>();
                        while (resultSet.next()) {
                            SubscriptionAccClosingOnMaturityDTO maturityDTO = new SubscriptionAccClosingOnMaturityDTO();
                            maturityDTO.setName(resultSet.getString(1));
                            maturityDTO.setPanNoName(resultSet.getString(2));
                            listOfSubDTOs.add(maturityDTO);
                        }
                        responseDataDTO.setResult(listOfSubDTOs);
                        responseDataDTO.setMessage(resultMsg);
                        return responseDataDTO;
                    }
                }
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySubmit(FreezUnfreezDTO freezUnfreezDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_MOD_SUB.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setArray(index++, generateArrayForRejectBondInterestPayments(connection, freezUnfreezDTO.getListOfStrings()));
                statement.setString(index++, freezUnfreezDTO.getAmountFreeze());
                statement.setString(index++, freezUnfreezDTO.getInterestFreeze());
                statement.setString(index++, freezUnfreezDTO.getTransferAnotherBank());
                statement.setString(index++, freezUnfreezDTO.getMakerRemarks() != null ? freezUnfreezDTO.getMakerRemarks().trim() : freezUnfreezDTO.getMakerRemarks());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(FreezUnfreezDTO freezUnfreezDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (freezUnfreezDTO.getDeleteRemarks() == null
                || freezUnfreezDTO.getDeleteRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("Rejection Remarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(freezUnfreezDTO.getCheckerRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        String resultMsg;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_DELETE.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setArray(index++, generateArrayForRejectBondInterestPayments(connection, freezUnfreezDTO.getListOfStrings()));
                statement.setString(index++, freezUnfreezDTO.getCheckerRemarks());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement, resultSet);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(FreezUnfreezDTO freezUnfreezDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (freezUnfreezDTO.getCheckerRemarks() == null
                || freezUnfreezDTO.getCheckerRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("Checker Remarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        Connection connection = null;
        CallableStatement statement = null;
        String resultMsg;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                statement = connection.prepareCall(RbiBondProcNameEnum.PKG_RBI_BOND_FREEZE_UNFREEZE_SP_VER_SUB.getProcName());
                statement.setString(index++, UserAccountUtils.getCurrentUsername());
                statement.setString(index++, UserAccountUtils.getCurrentSolId());
                statement.setString(index++, freezUnfreezDTO.getSchemeCode());
                statement.setArray(index++, generateArrayForRejectBondInterestPayments(connection, freezUnfreezDTO.getListOfStrings()));
                statement.setString(index++, freezUnfreezDTO.getCheckerRemarks() != null ? freezUnfreezDTO.getCheckerRemarks().trim() : freezUnfreezDTO.getCheckerRemarks());
                statement.registerOutParameter(index++, Types.VARCHAR);
                statement.registerOutParameter(index, Types.VARCHAR);
                statement.execute();
                resultMsg = statement.getString(index);
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + "{}", resultMsg);
            } else
                resultMsg = CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE;

        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = e.getMessage();
        } finally {
            this.connectionPoolFactory.closeConnection(connection, statement);
        }

        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

}




