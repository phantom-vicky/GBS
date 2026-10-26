/**
 *
 */
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.InstrumentStatusDto;
import com.isl.gbm.cbs.integration.dto.InstrumentTypesIntegrationDto;

import java.util.List;

/**
 * @author Msocadmin
 *
 */
public interface InstrumentTypesIntegrationService {

    public List<InstrumentTypesIntegrationDto> getInstrumentTypesBulkUpload(String foracid);

    public List<InstrumentTypesIntegrationDto> getSchmCode(String solId, String foracid);

    public InstrumentStatusDto getInstrumentCheck(String instrumentNum, String foracid);
}
