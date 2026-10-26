package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

public interface CbsIntegerationBulkUploadDao {

    public ResponseDataDTO saveDetails(CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto);

    public CbsIntegerationBulkUploadDto getUnverifiedBankDetails(String fileName,String flag);

    public ResponseDataDTO verifyBanks(String name, String fileName);

    public ResponseDataDTO deleteBanks(String fileName, String flag, String remarks);

}
