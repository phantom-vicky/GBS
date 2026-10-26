/**
 *
 */
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.InstrumentStatusDto;
import com.isl.gbm.cbs.integration.dto.InstrumentTypesIntegrationDto;
import com.isl.gbm.cbs.integration.manager.InstrumentTypesIntegrationManager;
import com.isl.gbm.cbs.integration.service.InstrumentTypesIntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Msocadmin
 *
 */
@Service
public class InstrumentTypesIntegrationServiceImpl implements InstrumentTypesIntegrationService {
    @Autowired
    private InstrumentTypesIntegrationManager manager;

    private static final Logger log = LoggerFactory.getLogger(InstrumentTypesIntegrationServiceImpl.class);
    @Override
    public List<InstrumentTypesIntegrationDto> getInstrumentTypesBulkUpload(final String foracid) {
        return this.manager.getInstrumentTypesBulkUpload(foracid);
    }

    @Override
    public List<InstrumentTypesIntegrationDto> getSchmCode(final String solId, final String foracid) {

        return this.manager.getSchmCode(solId, foracid);
    }

    @Override
    public InstrumentStatusDto getInstrumentCheck(String instrumentNum, String foracid) {
        log.info("The request For instrumentcheck " + instrumentNum,foracid);
        return this.manager.getInstrumentCheck(instrumentNum, foracid);
    }
}
