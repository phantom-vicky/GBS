package com.isl.gbm.user.isac.service;
import com.isl.gbm.user.isac.dto.*;
import org.springframework.validation.BindingResult;

public interface IsacService {

    public IsacResponseDTO userCreation(UserRequestDTO userRequestDTO, BindingResult result);
    public IsacResponseDTO unlockUser(UnlockUserRequestDTO unlockUserRequestDTO, BindingResult result);
    public IsacResponseDTO sackUser(SackUserRequestDTO sackUserRequestDTO, BindingResult result);
    public IsacResponseDTO enableDisableRevokeUser(UserEnableDisableRevokeRequestDTO userEnableDisableRevokeRequestDTO, BindingResult result);

    IsacResponseDTO userModify(IsacUserRequestModifyDTO userRequestDTO, BindingResult result);
}
