package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.dto.BondInterestPaymentFileDTO;
import com.isl.gbm.rbibond.dto.SubscriptionAccClosingOnMaturityDTO;

public interface SubscriptionAccClosingOnMaturityDAO {


    ResponseDataDTO add(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO addValidation(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO addPANTorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO addBLATorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO inquiry(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO inquiryPANTorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO inquiryBLATorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO verifySelect(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO verifyPANTorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO verifyBLATorch(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO verify(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO verifyResponse(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO delete(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO);
    ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO, String schemeCode, String fromDate, String toDate);

    ResponseDataDTO processPaymentFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO);

    ResponseDataDTO addSubmit(SubscriptionAccClosingOnMaturityDTO maturityDTO);

}
