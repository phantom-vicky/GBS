/**
 *
 */
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.GbmDtdViewDto;
import com.isl.gbm.cbs.integration.dto.GbmOzhViewDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.dto.wrapper.TellerDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.TranInqDTO;

import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Interface TranDetailsIntegrationService.
 *
 * @author Vasanthi
 */
public interface TranDetailsIntegrationService {

    /**
     * Gets the clearing tran details.
     *
     * @param solId
     *            the sol id
     * @param clearingZoneCode
     *            the clearing zone code
     * @param clearingZoneDate
     *            the clearing zone date
     * @return the clearing tran details
     */
    public GbmOzhViewDto getClearingTranDetails(final String solId, final String clearingZoneCode,
                                                final Date clearingZoneDate);

    /**
     * Gets the daily transaction details.
     *
     * @param tranRefIdList
     *            the tran ref id list
     * @param solId
     *            the sol id
     * @param tranDate
     *            the tran date
     * @param entryUserId
     *            the entry user id
     * @return the daily transaction details
     */
    public GbmDtdViewDto getDailyTransactionDetails(final List<String> tranRefIdList, final String solId,
                                                    final Date tranDate, final String entryUserId);

    /**
     * Gets the daily unverified transaction details.
     *
     * @param acid
     *            the acid
     * @param entryUserId
     *            the entry user id
     * @return the daily unverified transaction details
     */
    public List<GbmDtdViewDto> getDailyUnverifiedTransactionDetails(final String acid, final String entryUserId);

    /**
     * Gets the dtd transaction details.
     *
     * @param acid
     *            the acid
     * @param entryUserId
     *            the entry user id
     * @return the dtd transaction details
     */
    public List<GbmDtdViewDto> getDtdTransactionDetails(final String acid, final String entryUserId);

    /**
     * Gets the tran details.
     *
     * @param tranCode
     *            the tran code
     * @return the tran details
     */
    public List<TranDetailsIntegrationDto> getTranDetails(String tranCode);

    /**
     * Tran id details.
     *
     * @param solId
     *            the sol id
     * @param solDate
     *            the sol date
     * @param tranId
     *            the tran id
     * @param acid
     *            the acid
     * @return the list
     */
    public List<GbmDtdViewDto> tranIdDetails(final String solId, final Date solDate, final String tranId,
                                             final String acid);

   public TellerDTO tellerAmtAPI(String tranDate, String tranId);
}
