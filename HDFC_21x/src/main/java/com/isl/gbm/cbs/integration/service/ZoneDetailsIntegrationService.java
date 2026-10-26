/*
 *
 */
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.GbmOciViewDto;
import com.isl.gbm.cbs.integration.dto.GbmOzhViewDto;

import java.util.Date;
import java.util.List;

/**
 * The Interface ZoneDetailsIntegrationService.
 */
public interface ZoneDetailsIntegrationService {

    /**
     * Gets the cbs instrument details.
     *
     * @return the cbs instrument details
     */
    public List<GbmOciViewDto> getCbsInstrumentDetails();

    /**
     * Gets the clearing set num details.
     *
     * @param instrumentNumber the instrument number
     * @param solId            the sol id
     * @param zoneCode         the zone code
     * @param zoneDate         the zone date
     * @return the clearing set num details
     */
    public List<GbmOciViewDto> getClearingSetNumDetails(final String instrumentNumber, final String solId,
                                                        final String zoneCode, final Date zoneDate);

    /**
     * Gets the clearing zone details.
     *
     * @param ociViewDto the oci view dto
     * @return the clearing zone details
     */
    public List<GbmOciViewDto> getClearingZoneDetails(final GbmOciViewDto ociViewDto);

    /**
     * Gets the zone details.
     *
     * @param solId    the sol id
     * @param zoneCode the zone code
     * @return the zone details
     */
    List<GbmOzhViewDto> getZoneDetails(String solId, String zoneCode);

    /**
     * Checks if is valid zone code.
     *
     * @param zoneCode the zone code
     * @param zoneDate the zone date
     * @param solId    the sol id
     * @return the list
     */
    List<GbmOzhViewDto> isValidZoneCode(String zoneCode, Date zoneDate, String solId);

}
