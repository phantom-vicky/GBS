package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;
import com.isl.gbm.cbs.integration.dto.CustomerDetails;
import com.isl.gbm.cbs.integration.manager.BCIFApiManager;
import com.isl.gbm.cbs.integration.service.CustomerDetailsIntegrationService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.BondLedgerAccountMaintenanceBo;
import com.isl.gbm.rbibond.dao.BondLedgerAccountMaintenancDAO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


@Component
public class BondLedgerAccountMaintenanceBoImpl implements BondLedgerAccountMaintenanceBo {

    @Autowired
    private BondLedgerAccountMaintenancDAO bondLedgerAccountMaintenancDAO;

    @Autowired
    private CustomerDetailsIntegrationService customerDetailsIntegrationService;

    @Autowired
    private BCIFApiManager bcifApiManager;



    @Override
    public ResponseDataDTO getBcifApi(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            CustomerDetailsValidatorDTO customerDetailsValidatorDTO = customerDetailsIntegrationService.getPPCustomerDetails(bondLedgerAccountMaintenanceDTO.getCifId(), bondLedgerAccountMaintenanceDTO.getOperativeAccNo());

            if (customerDetailsValidatorDTO.getStatus().equalsIgnoreCase(CommonConstants.SUCCESS)) {

                CustomerDetails customerDetails = customerDetailsValidatorDTO.getCustomerDetails();
                responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);


                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                String dob = "";
                if(customerDetails.getDob() != null) {
                    dob = formatter.format(customerDetails.getDob());
                }

                Map<String, String> map = new HashMap<>();
                map.put("firstName", customerDetails.getCustomerFName());
                map.put("middleName", customerDetails.getCustomerMName());
                map.put("lastName", customerDetails.getCustomerLName());
                map.put("fatherName", customerDetails.getFatherName());
                map.put("motherName", customerDetails.getMotherName());
                map.put("gender", customerDetails.getCustomerSex());
                map.put("dateOfBirth", dob);
                map.put("isMinor", customerDetails.getCustomerminor());
                map.put("addressOne", customerDetails.getAddressLine1());
                map.put("addressTwo", customerDetails.getAddressLine2());
                map.put("addressThree", customerDetails.getAddressLine3());
                map.put("city", customerDetails.getCity());
                map.put("pinCode", customerDetails.getZipCode());
                map.put("phoneNumber", customerDetails.getPhoneNumber());
                map.put("mobileNumber", customerDetails.getMobileNumber());
                map.put("email", customerDetails.getEmail());
                map.put("operativeAccNo", bondLedgerAccountMaintenanceDTO.getOperativeAccNo());
                map.put("operativeBranchId", bondLedgerAccountMaintenanceDTO.getOperativeAccBranchId());
                map.put("pan", customerDetails.getPanNumber());
                map.put("cif", customerDetails.getCifId());
                map.put("aadhar", customerDetails.getAadharNumber());
                responseDataDTO.setResult(map);
            } else {
                responseDataDTO.setMessage(customerDetailsValidatorDTO.getReason());
            }
        } catch (Exception e) {
            responseDataDTO.setMessage("Error occucured while Fetching the details");
            e.printStackTrace();
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBlaNumDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenancDAO.getBlaNumDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO add(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenancDAO.add(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getGuardianDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenancDAO.getGuardianDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiry(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenancDAO.inquiry(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getTdsRateCode(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenancDAO.getTdsRateCode(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenancDAO.modifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenancDAO.modify(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenancDAO.verifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenancDAO.verify(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO delete(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenancDAO.delete(bondLedgerAccountMaintenanceDTO);
    }


}
