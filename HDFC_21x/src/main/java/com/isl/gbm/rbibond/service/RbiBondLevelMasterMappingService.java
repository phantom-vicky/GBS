package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondLevelMasterMappingDTO;

public interface RbiBondLevelMasterMappingService {

    public ResponseDataDTO getAddDetails(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO);

    public ResponseDataDTO add(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO);

    public ResponseDataDTO inquiry(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO);

    public ResponseDataDTO getModifyDetails(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO);

    public ResponseDataDTO getVerifyDeleteDetails(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO);

    public ResponseDataDTO verify(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO);

    public ResponseDataDTO modify(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO);

    public ResponseDataDTO delete(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO);


}
