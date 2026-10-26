package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.BrokerageInvoiceConfirmationBO;
import com.isl.gbm.rbibond.dao.BrokerageInvoiceConfirmationDAO;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationInquiryDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationResponseDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationVerifyDTO;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BrokerageInvoiceConfirmationBOImpl implements BrokerageInvoiceConfirmationBO {

    @Autowired
    BrokerageInvoiceConfirmationDAO brokerageInvoiceConfirmationDAO;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    private static final Logger log = LoggerFactory.getLogger(BrokerageInvoiceConfirmationBOImpl.class);


    @Override
    public ResponseDataDTO getAgencyCode(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO) {
        return brokerageInvoiceConfirmationDAO.getAgencyCode(brokerageInvoiceConfirmationDTO);
    }

    @Override
    public ResponseDataDTO getInvoiceConfirmationDetails(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO) {
        return brokerageInvoiceConfirmationDAO.getInvoiceConfirmationDetails(brokerageInvoiceConfirmationDTO);
    }

    @Override
    public ResponseDataDTO invoiceVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        if (brokerageInvoiceConfirmationVerifyDTO == null ||
                brokerageInvoiceConfirmationVerifyDTO.getBrokerageInvoiceConfirmationResponseDTOList() == null ||
                brokerageInvoiceConfirmationVerifyDTO.getBrokerageInvoiceConfirmationResponseDTOList().isEmpty()) {

            responseDataDTO.setMessage("Invalid request: No brokerage invoice data provided");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(brokerageInvoiceConfirmationVerifyDTO.getRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        for (BrokerageInvoiceConfirmationResponseDTO dto :
                brokerageInvoiceConfirmationVerifyDTO.getBrokerageInvoiceConfirmationResponseDTOList()) {

            BigDecimal grossBrokerage = parseAndValidateAmount(dto.getGrossBrokerage(), "Gross Brokerage", responseDataDTO);
            if (grossBrokerage == null) return responseDataDTO;

            BigDecimal gst = parseAndValidateAmount(dto.getGst(), "GST", responseDataDTO);
            if (gst == null) return responseDataDTO;

            BigDecimal netBrokerage = parseAndValidateAmount(dto.getNetBrokerage(), "Net Brokerage", responseDataDTO);
            if (netBrokerage == null) return responseDataDTO;

            dto.setGrossBrokerage(String.format("%.2f", grossBrokerage));
            dto.setGst(String.format("%.2f", gst));
            dto.setNetBrokerage(String.format("%.2f", netBrokerage));
            if(dto.getVbsRefNum() == null || dto.getVbsRefNum().trim().isEmpty() || !dto.getVbsRefNum().matches("^[a-zA-Z0-9]+$")){
                responseDataDTO.setMessage("VBMS Ref Number should not be null or empty and should be alphanumeric");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }

            if(dto.getVbsMakerId() != null && !dto.getVbsMakerId().trim().isEmpty() && !dto.getVbsMakerId().matches("^[a-zA-Z0-9]+$")){
                responseDataDTO.setMessage("VBMS Maker ID should be alphanumeric");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }

            if(dto.getVbsCheckerId() != null && !dto.getVbsCheckerId().trim().isEmpty() && !dto.getVbsCheckerId().matches("^[a-zA-Z0-9]+$")){
                responseDataDTO.setMessage("VBMS Checker ID should be alphanumeric");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }

        }

        return brokerageInvoiceConfirmationDAO.invoiceVerify(brokerageInvoiceConfirmationVerifyDTO);
    }

    private BigDecimal parseAndValidateAmount(String amountStr, String fieldName, ResponseDataDTO responseDataDTO) {
        if (amountStr == null || amountStr.trim().isEmpty()) {
            responseDataDTO.setMessage(fieldName + " should not be null or empty");
            responseDataDTO.setStatusCode("0");
            return null;
        }

        BigDecimal amount;
        try {
            amount = new BigDecimal(amountStr.trim()).setScale(2, RoundingMode.HALF_UP);
        } catch (NumberFormatException e) {
            responseDataDTO.setMessage(fieldName + " should be a valid numeric value");
            responseDataDTO.setStatusCode("0");
            return null;
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            responseDataDTO.setMessage(fieldName + " should not be negative");
            responseDataDTO.setStatusCode("0");
            return null;
        }

        return amount;
    }

    @Override
    public ResponseDataDTO getInvoiceConfirmationVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO) {
        return brokerageInvoiceConfirmationDAO.getInvoiceConfirmationVerify(brokerageInvoiceConfirmationVerifyDTO);
    }

    @Override
    public ResponseDataDTO getInvoiceConfirmationInquiry(BrokerageInvoiceConfirmationInquiryDTO brokerageInvoiceConfirmationInquiryDTO) {
        return brokerageInvoiceConfirmationDAO.getInvoiceConfirmationInquiry(brokerageInvoiceConfirmationInquiryDTO);
    }

    @Override
    public ResponseDataDTO submitDeleteForm(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO) {
        return brokerageInvoiceConfirmationDAO.submitDeleteForm(brokerageInvoiceConfirmationDTO);
    }

    @Override
    public ResponseDataDTO getDeleteSubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO) {
        return brokerageInvoiceConfirmationDAO.getDeleteSubmit(brokerageInvoiceConfirmationVerifyDTO);
    }

    @Override
    public ResponseDataDTO verifySubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO) {
        return brokerageInvoiceConfirmationDAO.verifySubmit(brokerageInvoiceConfirmationVerifyDTO);
    }


    @Override
    public ResponseDataDTO upload(MultipartFile file, String brokerageInvoiceConfirmationVerifyDTO , BrokerageInvoiceConfirmationVerifyDTO dto1) {
        List<BrokerageInvoiceConfirmationVerifyDTO> dtoList = new ArrayList<>();
        SimpleDateFormat inputFormat = new SimpleDateFormat("M/d/yy"); // Format for input date string
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy"); // Desired output format
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            // Deserialize the JSON DTO string to a Java object (for other fields)

            // Business Logic: Extract Excel data (.xls or .xlsx) and map to DTO
            InputStream fileInputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
            BrokerageInvoiceConfirmationVerifyDTO dto = new BrokerageInvoiceConfirmationVerifyDTO();
            DataFormatter dataFormatter = new DataFormatter();

            // Loop through rows and map data to DTO fields
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    // Skip the header row
                    continue;
                }
                // Assuming the columns are in order: VBMS Reference Number, Payment Ref, Paid On
                String vbmsRef = dataFormatter.formatCellValue(row.getCell(0));
                String paymentRef = dataFormatter.formatCellValue(row.getCell(1));
                String paidOn = dataFormatter.formatCellValue(row.getCell(2));
                String invoiceReceiveDate = dataFormatter.formatCellValue(row.getCell(3));
                String invoiceRaisedOn = dataFormatter.formatCellValue(row.getCell(4));
                String vbmsMaker = dataFormatter.formatCellValue(row.getCell(5));
                String vbmsChecker = dataFormatter.formatCellValue(row.getCell(6));

                // Check if the row is not empty (you can add more conditions based on your requirements)
                if (!vbmsRef.isEmpty() || !paymentRef.isEmpty() || !paidOn.isEmpty()) {
                    // Create a new DTO object for each row
 dto = new BrokerageInvoiceConfirmationVerifyDTO();
                    dto.setVbmsReferenceNumber(vbmsRef);
                    dto.setPaymentRef(paymentRef);
                    dto.setVbmsMaker(vbmsMaker);
                    dto.setVbmsChecker(vbmsChecker);
                    try {
                        Date date = inputFormat.parse(paidOn);
                        String formattedDate = outputFormat.format(date);

                        Date date1 = inputFormat.parse(invoiceReceiveDate);
                        String formattedDate1 = outputFormat.format(date1);

                        Date date2 = inputFormat.parse(invoiceRaisedOn);
                        String formattedDate2 = outputFormat.format(date2);
                        dto.setPaidOn(formattedDate);
                        dto.setInvoiceReceiveDate(formattedDate1);
                        dto.setInvoiceRaisedOn(formattedDate2);
                        log.info("Formatted Paid On Date: {}", formattedDate);
                        log.info("Formatted Invoice ReceiveDate: {}", formattedDate1);
                        log.info("Formatted Invoice RaisedOn: {}", formattedDate2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        dto.setPaidOn(null);
                        dto.setInvoiceReceiveDate(null);
                        dto.setInvoiceRaisedOn(null);
                    }
                    // Add the DTO to the list
                    dtoList.add(dto);
                }
                // Break after reading the first row if you only need one record
                // Remove this condition if you want to process all rows
            }

            workbook.close();

            // Business logic with DTO data (e.g., save to the database, validate data, etc.)
            log.info("Processed DTO: VBMS Ref - {}, Payment Ref - {}, Invoice ReceiveDate - {}, Invoice RaisedOn - {}, Vbms Maker - {}, Vbms Checker - {}, Paid On - {}",
                    dto.getVbmsReferenceNumber(),
                    dto.getPaymentRef(),
                    dto.getInvoiceReceiveDate(),
                    dto.getInvoiceRaisedOn(),
                    dto.getVbmsMaker(),
                    dto.getVbmsChecker(),
                    dto.getPaidOn());

            // Send success response

        } catch (IOException e) {
            // Handle errors (file reading or DTO processing)
            e.printStackTrace();
        }

        if(!dtoList.isEmpty() ) {
             responseDataDTO = brokerageInvoiceConfirmationDAO.upload(dtoList, dto1);
        } else {
            responseDataDTO.setMessage("No data entered for :");
        }
        return responseDataDTO;
    }
}
