package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.dto.SubscriptionAccClosingOnMaturityDTO;
import org.springframework.web.multipart.MultipartFile;

public interface SubscriptionAccClosingOnMaturityService {


    ResponseDataDTO add(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO addValidation(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO inquiry(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO verifySelect(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO verify(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO delete(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO);

    ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO uploadFile(MultipartFile textFile, String schemeCode, String fromDate, String toDate);
}

