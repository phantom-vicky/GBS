package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.wrapper.SignatureDTO;
import com.isl.gbm.cbs.integration.manager.SignatureManager;
import com.isl.gbm.cbs.integration.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignatureServiceImpl implements SignatureService {
    @Autowired
    SignatureManager manager;
    @Override
    public SignatureDTO getSignature(SignatureDTO signatureDTO) {
        return this.manager.getSignature(signatureDTO.getAccountid(),signatureDTO.getAccountType());
    }
}
