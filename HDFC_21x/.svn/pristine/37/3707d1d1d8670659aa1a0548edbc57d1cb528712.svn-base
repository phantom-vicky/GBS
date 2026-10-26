/**
 *
 */
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.GbmDtdViewDto;
import com.isl.gbm.cbs.integration.dto.GbmOzhViewDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.dto.wrapper.TellerDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.TranInqDTO;
import com.isl.gbm.cbs.integration.manager.TranDetailsIntegrationManager;
import com.isl.gbm.cbs.integration.service.TranDetailsIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * The Class TranDetailsIntegrationServiceImpl.
 *
 * @author Vasanthi
 */
@Service
public class TranDetailsIntegrationServiceImpl implements TranDetailsIntegrationService {

    /** The manager. */
    @Autowired
    private TranDetailsIntegrationManager manager;

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.TranDetailsIntegrationService#
     * getClearingTranDetails(java.lang.String, java.lang.String,
     * java.util.Date)
     */
    @Override
    public GbmOzhViewDto getClearingTranDetails(final String solId, final String clearingZoneCode,
                                                final Date clearingZoneDate) {
        return this.manager.getClearingTranDetails(solId, clearingZoneCode, clearingZoneDate);
    }

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.TranDetailsIntegrationService#
     * getDailyTransactionDetails(java.util.List, java.lang.String,
     * java.util.Date, java.lang.String)
     */
    @Override
    public GbmDtdViewDto getDailyTransactionDetails(final List<String> tranRefIdList, final String solId,
                                                    final Date tranDate, final String entryUserId) {
        return this.manager.getDailyTransactionDetails(tranRefIdList, solId, tranDate, entryUserId);
    }

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.TranDetailsIntegrationService#
     * getDailyUnverifiedTransactionDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<GbmDtdViewDto> getDailyUnverifiedTransactionDetails(final String acid, final String entryUserId) {
        return this.manager.getDailyUnverifiedTransactionDetails(acid, entryUserId);
    }

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.TranDetailsIntegrationService#
     * getDtdTransactionDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<GbmDtdViewDto> getDtdTransactionDetails(final String acid, final String entryUserId) {

        return this.manager.getDtdTransactionDetails(acid, entryUserId);

    }

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.TranDetailsIntegrationService#
     * getTranDetails(java.lang.String)
     */
    @Override
    public List<TranDetailsIntegrationDto> getTranDetails(final String tranCode) {
        return this.manager.getTranDetails(tranCode);
    }

    /**
     * Sets the manager.
     *
     * @param manager
     *            the new manager
     */
    public void setManager(final TranDetailsIntegrationManager manager) {
        this.manager = manager;
    }

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.TranDetailsIntegrationService#
     * tranIdDetails(java.lang.String, java.util.Date, java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<GbmDtdViewDto> tranIdDetails(final String solId, final Date solDate, final String tranId,
                                             final String acid) {
        return this.manager.tranIdDetails(solId, solDate, tranId, acid);
    }

    @Override
    public TellerDTO tellerAmtAPI(String tranDate, String tranId) {
        return this.manager.tellerAmtAPI(tranDate, tranId);
    }
}
