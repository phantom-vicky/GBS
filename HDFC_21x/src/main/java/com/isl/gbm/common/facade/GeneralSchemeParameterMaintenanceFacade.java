package com.isl.gbm.common.facade;

import com.isl.gbm.common.dto.NpsGbmSubCodeMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.wrapper.GbmSchemeParameterAuditLogWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface GeneralSchemeParameterMaintenanceFacade {

    public ResponseEntity<List<GbmSchemeParameterAuditLogWrapper>> getGeneralSchemeParameterMaintenance(final String schemeType);

    public ResponseEntity<Map<String, Boolean>> getInternetEnabledForScheme(final String schemeType);

    public ResponseEntity<List<NpsGbmSubCodeMasterDto>> getSchemeTypes();

    public ResponseEntity<ResponseDataDTO> updateGeneralSchemeParameterMaintenance(final List<GbmSchemeParameterAuditLogWrapper> dtoList);

    public ResponseEntity<ResponseDataDTO> verifyGeneralSchemeParameterMaintenance(final List<GbmSchemeParameterAuditLogWrapper> dtoList);

}
