package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.SchemeMasterRequestResponseDTO;

public interface RbiBondSchemeMasterService {

    public ResponseDataDTO modifySelect(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO);
    public ResponseDataDTO verifySelect(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO);

    public ResponseDataDTO add(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO);

    public ResponseDataDTO inquiry(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO);

    public ResponseDataDTO verify(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO);

    public ResponseDataDTO modify(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO);
    public ResponseDataDTO delete(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO);
}
