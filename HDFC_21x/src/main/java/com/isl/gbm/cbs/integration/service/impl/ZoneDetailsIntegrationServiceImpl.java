/*
 *
 */
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.GbmOciViewDto;
import com.isl.gbm.cbs.integration.dto.GbmOzhViewDto;
import com.isl.gbm.cbs.integration.manager.ZoneDetailsIntegrationManager;
import com.isl.gbm.cbs.integration.service.ZoneDetailsIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * The Class ZoneDetailsIntegrationServiceImpl.
 */
@Service
public class ZoneDetailsIntegrationServiceImpl implements ZoneDetailsIntegrationService {

    /**
     * The zone manager.
     */
    @Autowired
    private ZoneDetailsIntegrationManager zoneManager;

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.ZoneDetailsIntegrationService#
     * getCbsInstrumentDetails()
     */
    @Override
    public List<GbmOciViewDto> getCbsInstrumentDetails() {
        return this.zoneManager.getCbsInstrumentDetails();
    }

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.ZoneDetailsIntegrationService#
     * getClearingSetNumDetails(java.lang.String, java.lang.String,
     * java.lang.String, java.util.Date)
     */
    @Override
    public List<GbmOciViewDto> getClearingSetNumDetails(final String instrumentNumber, final String solId,
                                                        final String zoneCode, final Date zoneDate) {
        return this.zoneManager.getClearingSetNumDetails(instrumentNumber, solId, zoneCode, zoneDate);
    }

    @Override
    public List<GbmOciViewDto> getClearingZoneDetails(final GbmOciViewDto ociViewDto) {
        return this.zoneManager.getClearingZoneDetails(ociViewDto);
    }

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.ZoneDetailsIntegrationService#
     * getZoneDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<GbmOzhViewDto> getZoneDetails(final String solId, final String zoneCode) {
        return this.zoneManager.getZoneDetails(solId, zoneCode);
    }

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.ZoneDetailsIntegrationService#
     * isValidZoneCode(java.lang.String, java.util.Date, java.lang.String)
     */
    @Override
    public List<GbmOzhViewDto> isValidZoneCode(final String zoneCode, final Date zoneDate, final String solId) {
        return this.zoneManager.isValidZoneCode(zoneCode, zoneDate, solId);
    }

    /**
     * Sets the zone manager.
     *
     * @param zoneManager the new zone manager
     */
    public void setZoneManager(final ZoneDetailsIntegrationManager zoneManager) {
        this.zoneManager = zoneManager;
    }

}
