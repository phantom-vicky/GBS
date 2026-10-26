package com.isl.gbm.common.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.wrapper.NpsSystemParameterAuditLogWrapper;
import com.isl.gbm.common.facade.NpsSystemParameterFacade;
import com.isl.gbm.common.service.NpsSystemParameterService;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.utils.APIResponseBuilder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class NpsSystemParameterFacadeImpl implements NpsSystemParameterFacade {

    /**
     * The nps system parameter service.
     */
    @Autowired
    private NpsSystemParameterService npsSystemParameterService;


    /**
     * Gets the sys parm list.
     *
     * @return the sys parm list
     */
    @RequiresPermissions("DC:SYSTEM_PARAMETER:ENQUIRY")
    public ResponseEntity<List<NpsSystemParameterAuditLogWrapper>> getSysParmList() {
        final List<NpsSystemParameterAuditLogWrapper> resultList = this.npsSystemParameterService.getSysParamList();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    public ResponseEntity<List<NpsSystemParameterAuditLogWrapper>> getCommonList() {
        final List<NpsSystemParameterAuditLogWrapper> resultList = this.npsSystemParameterService.getCommonList();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }


    public ResponseEntity<List<NpsSystemParameterAuditLogWrapper>> getSysParmModuleList(String moduleId) {
        final List<NpsSystemParameterAuditLogWrapper> npsSystemParameterAuditLogWrappers = this.npsSystemParameterService.getSysParamModuleList(moduleId);
        return new ResponseEntity<>(npsSystemParameterAuditLogWrappers, HttpStatus.OK);
    }

    /**
     * Update sys param.
     *
     * @param wrapperList the wrapper list
     * @return the response entity
     */
    @RequiresPermissions("DC:SYSTEM_PARAMETER:MODIFY")
    public ResponseEntity<ResponseDataDTO> updateSysParam(
            @RequestBody final List<NpsSystemParameterAuditLogWrapper> wrapperList) {
        this.npsSystemParameterService.updateSysParam(wrapperList);
        final ResponseDataDTO dataDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_UPDATED_MESSAGE);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Verify sys param.
     *
     * @param wrapperList the wrapper list
     * @return the response entity
     */
    @RequiresPermissions("DC:SYSTEM_PARAMETER:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifySysParam(
            @RequestBody final List<NpsSystemParameterAuditLogWrapper> wrapperList) {
        this.npsSystemParameterService.verifySysParam(wrapperList);
        final ResponseDataDTO dataDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_VERIFIED_MESSAGE);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }
}
