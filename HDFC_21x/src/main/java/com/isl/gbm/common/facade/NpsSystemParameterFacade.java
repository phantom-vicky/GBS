package com.isl.gbm.common.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.wrapper.NpsSystemParameterAuditLogWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NpsSystemParameterFacade {
    ResponseEntity<List<NpsSystemParameterAuditLogWrapper>> getSysParmList();

    ResponseEntity<List<NpsSystemParameterAuditLogWrapper>> getCommonList();

    ResponseEntity<List<NpsSystemParameterAuditLogWrapper>> getSysParmModuleList(String moduleId);

    ResponseEntity<ResponseDataDTO> updateSysParam(final List<NpsSystemParameterAuditLogWrapper> wrapperList);

    ResponseEntity<ResponseDataDTO> verifySysParam(final List<NpsSystemParameterAuditLogWrapper> wrapperList);
}
