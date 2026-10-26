package com.isl.gbm.rbibond.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.BrokerageInvoiceConfirmationDAO;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.*;
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
import java.util.Date;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BrokerageInvoiceConfirmationDAOImpl implements BrokerageInvoiceConfirmationDAO {
    private static final Logger log = LoggerFactory.getLogger(BrokerageInvoiceConfirmationDAOImpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Override
    public ResponseDataDTO getAgencyCode(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO){
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BrokerageInvoiceConfirmationDTO output = new BrokerageInvoiceConfirmationDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BROKERAGE_INVOICE_CONF_AGENCY_CODE.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, brokerageInvoiceConfirmationDTO.getSchemeCode());
                stmt.setString(index++, brokerageInvoiceConfirmationDTO.getFlag());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {
                    List<HashMap<String, String>> noOfLevels = null;
                    resultSet = (ResultSet)
                            stmt.getObject(5);
                    if (resultSet != null) {
                        HashMap<String, String> temp = null;
                        noOfLevels = new ArrayList<>();
                        while (resultSet.next()) {
                            if (resultSet.getString(1) != null) {
                                temp = new HashMap<>();
                                temp.put("brokerName", resultSet.getString(1));
                                temp.put("brokerAgencyCode", resultSet.getString(2));
                                noOfLevels.add(temp);
                            }
                        }
                        output.setAgencyOuts(noOfLevels);
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
    public ResponseDataDTO getInvoiceConfirmationDetails(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO){
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BrokerageInvoiceConfirmationResponseDTO output = null;
        List<BrokerageInvoiceConfirmationResponseDTO> brokerageInvoiceConfirmationResponseDTOList = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BROKERAGE_INVOICE_CONF_DETAILS.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, brokerageInvoiceConfirmationDTO.getSchemeCode());
                stmt.setString(index++, brokerageInvoiceConfirmationDTO.getAgencyCode());
                stmt.setString(index++, brokerageInvoiceConfirmationDTO.getInvoiceNumber());
                stmt.setString(index++, brokerageInvoiceConfirmationDTO.getSubFromDate());
                stmt.setString(index++, brokerageInvoiceConfirmationDTO.getSubToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (brokerageInvoiceConfirmationDTO.getSchemeCode() == null) {
                        resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("schemeName", resultSet.getString(2));
                                item.put("schemeStatus", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else {
                        resultSet = (ResultSet) stmt.getObject(8);
                        brokerageInvoiceConfirmationResponseDTOList = new ArrayList<>();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                output = new BrokerageInvoiceConfirmationResponseDTO();
                                output.setBrokerCode(resultSet.getString("broker_code"));
                                output.setInvoiceNumber(resultSet.getString("invoice_no"));
                                output.setInvoiceDate(resultSet.getString("invoice_date"));
                                output.setBrokerName(resultSet.getString("broker_name"));
                                output.setBrokerPan(resultSet.getString("broker_pan"));
                                output.setBrokerEmailId(resultSet.getString("broker_email_id"));
                                output.setGrossBrokerage(resultSet.getString("gross_brokerage"));
                                output.setGst(resultSet.getString("gst"));
                                output.setNetBrokerage(resultSet.getString("net_brokerage"));
                                brokerageInvoiceConfirmationResponseDTOList.add(output);
                            }
                            responseDataDTO.setResult(brokerageInvoiceConfirmationResponseDTOList);

                        }
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
    public ResponseDataDTO invoiceVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO){
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BROKERAGE_INVOICE_CONF_VERIFY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, brokerageInvoiceConfirmationVerifyDTO.getSchemeCode());
                stmt.setArray(index++, generateArrayForBrokerageConfirmation(connection,brokerageInvoiceConfirmationVerifyDTO.getBrokerageInvoiceConfirmationResponseDTOList()));
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

    private Array generateArrayForBrokerageConfirmation(Connection connection, List<BrokerageInvoiceConfirmationResponseDTO> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_TYP_RBI_BOND_BRK_CON_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_TYP_RBI_BOND_BRK_CON, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
      /*  SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // Format of the input string
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy"); // Desired output format
*/
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[14];
            BrokerageInvoiceConfirmationResponseDTO item = list.get(index);
            paramArray[0] = item.getInvoiceNumber();
            paramArray[1] = item.getInvoiceDate();
            paramArray[2] = item.getBrokerName();
            paramArray[3] = item.getBrokerCode();
            paramArray[4] = item.getBrokerPan();
            paramArray[5] = item.getBrokerEmailId();
            paramArray[6] = item.getGrossBrokerage();
            paramArray[7] = item.getGst();
            paramArray[8] = item.getNetBrokerage();
            paramArray[9] = item.getVbsRefNum();
            paramArray[10] = item.getInvRecieveDate(); // Format the Date to desired output
            paramArray[11] = item.getInvRaisedOnDate(); // Format the Date to desired output

           /* // Handle InvRecieveDate
            String invRecieveDateObj = item.getInvRecieveDate(); // Get the date string
            if (invRecieveDateObj != null) {
                try {
                    Date date = inputFormat.parse(invRecieveDateObj); // Parse string to Date
                    paramArray[10] = outputFormat.format(date); // Format the Date to desired output
                } catch (ParseException e) {
                    paramArray[10] = null; // Set to null if parsing fails
                }
            } else {
                paramArray[10] = null; // Set to null if the input is null
            }

            // Handle InvRaisedOnDate
            String invRaisedOnDateObj = item.getInvRaisedOnDate(); // Get the date string
            if (invRaisedOnDateObj != null) {
                try {
                    Date date = inputFormat.parse(invRaisedOnDateObj); // Parse string to Date
                    paramArray[11] = outputFormat.format(date); // Format the Date to desired output
                } catch (ParseException e) {
                    paramArray[11] = null; // Set to null if parsing fails
                }
            } else {
                paramArray[11] = null; // Set to null if the input is null
            }*/

            paramArray[12] = item.getVbsMakerId();
            paramArray[13] = item.getVbsCheckerId();
            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    @Override
    public ResponseDataDTO upload(List<BrokerageInvoiceConfirmationVerifyDTO> dtoList,BrokerageInvoiceConfirmationVerifyDTO dto1){
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BROKERAGE_INVOICE_CONF_UPLOAD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto1.getSchemeCode());
                stmt.setArray(index++, generateArrayForUpload(connection,dtoList));
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
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
    public ResponseDataDTO getInvoiceConfirmationVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO) {
        System.out.println("verify working");
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BrokerageInvoiceConfirmationResponseDTO output = null;
        List<BrokerageInvoiceConfirmationResponseDTO> brokerageInvoiceConfirmationResponseDTOList = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BROKERAGE_INVOICE_CONF_VERIFY_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, brokerageInvoiceConfirmationVerifyDTO.getSchemeCode());
                stmt.setString(index++, brokerageInvoiceConfirmationVerifyDTO.getAgencyCode());
                stmt.setString(index++, brokerageInvoiceConfirmationVerifyDTO.getInvoiceNumber());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (brokerageInvoiceConfirmationVerifyDTO.getSchemeCode() == null) {
                        resultSet = (ResultSet) stmt.getObject(6);
                        if (resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("schemeName", resultSet.getString(2));
                                item.put("schemeStatus", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else {
                        resultSet = (ResultSet) stmt.getObject(6);
                        brokerageInvoiceConfirmationResponseDTOList = new ArrayList<>();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                output = new BrokerageInvoiceConfirmationResponseDTO();
                                output.setBrokerCode(resultSet.getString("broker_code"));
                                output.setInvoiceNumber(resultSet.getString("invoice_no"));
                                output.setInvoiceDate(resultSet.getString("invoice_date"));
                                output.setBrokerName(resultSet.getString("broker_name"));
                                output.setBrokerPan(resultSet.getString("broker_pan"));
                                output.setBrokerEmailId(resultSet.getString("broker_email_id"));
                                output.setGrossBrokerage(resultSet.getString("gross_brokerage"));
                                output.setGst(resultSet.getString("gst"));
                                output.setNetBrokerage(resultSet.getString("net_brokerage"));
                                output.setVbsRefNum(resultSet.getString("vbms_ref_no"));
                                output.setInvRecieveDate(resultSet.getString("invoice_received_date"));
                                output.setInvRaisedOnDate(resultSet.getString("invoice_raised_on"));
                                output.setVbsMakerId(resultSet.getString("vbms_maker"));
                                output.setVbsCheckerId(resultSet.getString("vbms_checker"));
                                brokerageInvoiceConfirmationResponseDTOList.add(output);
                            }
                            responseDataDTO.setResult(brokerageInvoiceConfirmationResponseDTOList);

                        }
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
    public ResponseDataDTO getInvoiceConfirmationInquiry(BrokerageInvoiceConfirmationInquiryDTO brokerageInvoiceConfirmationInquiryDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BrokerageInvoiceConfirmationResponseDTO output = null;
        List<BrokerageInvoiceConfirmationResponseDTO> brokerageInvoiceConfirmationResponseDTOList = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BROKERAGE_INVOICE_CONF_INQUIRY.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, brokerageInvoiceConfirmationInquiryDTO.getSchemeCode());
                stmt.setString(index++, brokerageInvoiceConfirmationInquiryDTO.getAgencyCode());
                stmt.setString(index++, brokerageInvoiceConfirmationInquiryDTO.getInvoiceNumber());
                stmt.setString(index++, brokerageInvoiceConfirmationInquiryDTO.getSubFromDate());
                stmt.setString(index++, brokerageInvoiceConfirmationInquiryDTO.getSubToDate());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info("{}: {}", CommonConstants.STATUS_MESSAGE, stmt.getString(index));
                resultMsg = stmt.getString(index);
                ResultSet resultSet = null;
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if (brokerageInvoiceConfirmationInquiryDTO.getSchemeCode() == null) {
                        resultSet = (ResultSet) stmt.getObject(8);
                        if (resultSet != null) {
                            List<Map<String, String>> list = new ArrayList<>();

                            Map<String, String> item = null;
                            while (resultSet.next()) {
                                item = new HashMap<>();
                                item.put("schemeCode", resultSet.getString(1));
                                item.put("schemeName", resultSet.getString(2));
                                item.put("schemeStatus", resultSet.getString(3));
                                list.add(item);
                            }
                            responseDataDTO.setResult(list);
                        }
                    } else {
                        resultSet = (ResultSet) stmt.getObject(8);
                        brokerageInvoiceConfirmationResponseDTOList = new ArrayList<>();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                output = new BrokerageInvoiceConfirmationResponseDTO();
                                output.setBrokerCode(resultSet.getString("broker_code"));
                                output.setInvoiceNumber(resultSet.getString("invoice_no"));
                                output.setInvoiceDate(resultSet.getString("invoice_date"));
                                output.setBrokerName(resultSet.getString("broker_name"));
                                output.setBrokerPan(resultSet.getString("broker_pan"));
                                output.setBrokerEmailId(resultSet.getString("broker_email_id"));
                                output.setGrossBrokerage(resultSet.getString("gross_brokerage"));
                                output.setGst(resultSet.getString("gst"));
                                output.setNetBrokerage(resultSet.getString("net_brokerage"));
                                output.setStatus(resultSet.getString("invoice_flg"));
                                output.setVbsCheckerId(resultSet.getString("VBMS_CHECKER"));
                                output.setVbsMakerId(resultSet.getString("VBMS_MAKER"));
                                output.setPaymentRef(resultSet.getString("PAYMENT_REF_NO"));
                                output.setInvRaisedOnDate(resultSet.getString("INVOICE_RAISED_ON"));
                                output.setInvRecieveDate(resultSet.getString("INVOICE_RECEIVE_DATE"));
                                output.setVbsRefNum(resultSet.getString("VBS_REF_NO"));
                                output.setPaidOn(resultSet.getString("PAID_ON"));
                                brokerageInvoiceConfirmationResponseDTOList.add(output);
                            }
                            responseDataDTO.setResult(brokerageInvoiceConfirmationResponseDTOList);

                        }
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
    public ResponseDataDTO submitDeleteForm(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (brokerageInvoiceConfirmationDTO.getRemarks() == null
                || brokerageInvoiceConfirmationDTO.getRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(brokerageInvoiceConfirmationDTO.getRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
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
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BROKERAGE_INVOICE_CONF_ADD_REJECT.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, brokerageInvoiceConfirmationDTO.getSchemeCode());
                stmt.setString(index++, brokerageInvoiceConfirmationDTO.getRemarks());

                List<BrokerageInvoiceConfirmationResponseDTO> dtoList = brokerageInvoiceConfirmationDTO.getBrokerageInvoiceConfirmationResponseDTOList();
                ArrayDescriptor desc = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_BRK_CON_ADD_REJECT", connection);

                // Map DTO list to Oracle array
                Object[] objArray = new Object[dtoList.size()];
                for (int i = 0; i < dtoList.size(); i++) {
                    BrokerageInvoiceConfirmationResponseDTO dto = dtoList.get(i);

                    log.info("Invoice Number: {}" , dto.getInvoiceNumber());

                    // Make sure that the array matches the expected structure in Oracle
                    objArray[i] = new Object[] {

                            dto.getInvoiceNumber()

                    };
                }

                // Step 4: Set the Oracle array as a parameter
                ARRAY brokerageInvoiceConfirmationResponseArray = new ARRAY(desc, connection, objArray);
                stmt.setArray(index++, brokerageInvoiceConfirmationResponseArray);
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
    public ResponseDataDTO getDeleteSubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BROKERAGE_INVOICE_CONF_VERIFY_REJECT.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, brokerageInvoiceConfirmationVerifyDTO.getSchemeCode());
                stmt.setString(index++, brokerageInvoiceConfirmationVerifyDTO.getRemarks());

                List<BrokerageInvoiceConfirmationResponseDTO> dtoList = brokerageInvoiceConfirmationVerifyDTO.getBrokerageInvoiceConfirmationResponseDTOList();
                ArrayDescriptor desc = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_BRK_CON_VER_REJECT", connection);

                // Map DTO list to Oracle array
                Object[] objArray = new Object[dtoList.size()];
                for (int i = 0; i < dtoList.size(); i++) {
                    BrokerageInvoiceConfirmationResponseDTO dto = dtoList.get(i);

                    log.info("Invoice Number: {}" , dto.getInvoiceNumber());

                    // Make sure that the array matches the expected structure in Oracle
                    objArray[i] = new Object[] {

                            dto.getInvoiceNumber()

                    };
                }

                // Step 4: Set the Oracle array as a parameter
                ARRAY brokerageInvoiceConfirmationResponseArray = new ARRAY(desc, connection, objArray);
                stmt.setArray(index++, brokerageInvoiceConfirmationResponseArray);
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
    public ResponseDataDTO verifySubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(RbiBondProcNameEnum.SP_GBM_RBI_BOND_BROKERAGE_INVOICE_CONF_VERIFY_DETAILS.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, brokerageInvoiceConfirmationVerifyDTO.getSchemeCode());

                List<BrokerageInvoiceConfirmationResponseDTO> dtoList = brokerageInvoiceConfirmationVerifyDTO.getBrokerageInvoiceConfirmationResponseDTOList();
                ArrayDescriptor desc = ArrayDescriptor.createDescriptor("TYP_RBI_BOND_BRK_CON_VER", connection);

                // Map DTO list to Oracle array
                Object[] objArray = new Object[dtoList.size()];
                for (int i = 0; i < dtoList.size(); i++) {
                    BrokerageInvoiceConfirmationResponseDTO dto = dtoList.get(i);

                    log.info("Invoice Number: {}" , dto.getInvoiceNumber());

                    // Make sure that the array matches the expected structure in Oracle
                    objArray[i] = new Object[] {

                            dto.getInvoiceNumber()

                    };
                }

                // Step 4: Set the Oracle array as a parameter
                ARRAY brokerageInvoiceConfirmationResponseArray = new ARRAY(desc, connection, objArray);
                stmt.setArray(index++, brokerageInvoiceConfirmationResponseArray);
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


    private Array generateArrayForUpload(Connection connection, List<BrokerageInvoiceConfirmationVerifyDTO> list) throws SQLException {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor(CommonConstants.DATABASE_OBJECT_TYP_RBI_BOND_BRK_PYMT_OBJ, connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor(CommonConstants.DATABASE_TYP_RBI_BOND_BRK_PYMT, connection);
        STRUCT[] genericTableInsertStructs = new STRUCT[list.size()];
        for (int index = 0; index < list.size(); index++) {
            Object[] paramArray = new Object[7];
            BrokerageInvoiceConfirmationVerifyDTO item = list.get(index);
            paramArray[0] = item.getVbmsReferenceNumber();
            paramArray[1] = item.getPaymentRef();
            paramArray[2] = item.getPaidOn();
            paramArray[3] = item.getInvoiceReceiveDate();
            paramArray[4] = item.getInvoiceRaisedOn();
            paramArray[5] = item.getVbmsMaker();
            paramArray[6] = item.getVbmsChecker();
            genericTableInsertStructs[index] = new STRUCT(genericTableStruct, connection, paramArray);
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

}
