package com.isl.gbm.user.isac.service.impl;

import com.isl.gbm.user.isac.bo.IsacBO;
import com.isl.gbm.user.isac.dto.*;
import com.isl.gbm.user.isac.service.IsacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
@Service
public class IsacServiceImpl implements IsacService {

    @Autowired
    IsacBO isacBO;
    @Override
    public IsacResponseDTO userCreation(UserRequestDTO userRequestDTO, BindingResult result) {
        return isacBO.userCreation(userRequestDTO, result);
    }

    @Override
    public IsacResponseDTO userModify(IsacUserRequestModifyDTO userRequestDTO, BindingResult result) {
        return isacBO.userModify(userRequestDTO, result);
    }
     @Override
    public IsacResponseDTO unlockUser(UnlockUserRequestDTO unlockUserRequestDTO, BindingResult result) {
        return isacBO.unlockUser(unlockUserRequestDTO, result);
    }
    @Override
    public IsacResponseDTO sackUser(SackUserRequestDTO sackUserRequestDTO, BindingResult result) {
        return isacBO.sackUser(sackUserRequestDTO, result);
    }
 @Override
    public IsacResponseDTO enableDisableRevokeUser(UserEnableDisableRevokeRequestDTO userEnableDisableRevokeRequestDTO, BindingResult result) {
        return isacBO.enableDisableRevokeUser(userEnableDisableRevokeRequestDTO, result);
    }


}
