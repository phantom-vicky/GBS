/*
 *
 *//*

package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.common.dto.GbmNpsRegistrationDto;
import com.isl.gbm.nps.dto.NpsGbmCorporateReceiptDto;
import com.isl.gbm.nps.dto.NpsGbmRejectionDto;
import com.isl.gbm.nps.dto.TransferModesDto;
import com.isl.gbm.nps.manager.NpsRejectionManager;
import com.isl.gbm.nps.service.NpsRejectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc

*/
/**
 * The Class NpsRejectionServiceImpl.
 * The nps rejection manager.
 * Sets the nps rejection manager.
 *
 * @param npsRejectionManager
 * the new nps rejection manager
 *//*

@Service
@Transactional
public class NpsRejectionServiceImpl implements NpsRejectionService {

	*/
/** The nps rejection manager. *//*

	@Autowired
	private NpsRejectionManager npsRejectionManager;

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#create(com.isl.gbm.nps.dto.
 * NpsGbmRejectionDto)
 *//*

	@Override
	public String create(final NpsGbmRejectionDto npsRejectionDto) {
		return this.npsRejectionManager.create(npsRejectionDto);

	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#delete(com.isl.gbm.nps.dto.
 * NpsGbmRejectionDto)
 *//*

	@Override
	public void delete(final NpsGbmRejectionDto rejectionDto) {
		this.npsRejectionManager.delete(rejectionDto);

	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#getAccountNumber(java.lang.
 * String, java.lang.String, java.lang.String)
 *//*

	@Override
	public String getAccountNumber(final String solId, final String schemePlaceHolder, final String schemeCode) {
		return this.npsRejectionManager.getAccountNumber(solId, schemePlaceHolder, schemeCode);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#getCorporateCreditNumber(java
 * .lang.String)
 *//*

	@Override
	public String getCorporateCreditNumber(final String corporateId) {
		return this.npsRejectionManager.getCorporateCreditNumber(corporateId);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#getCorporateRegNum(java.lang.
 * String, java.lang.String)
 *//*

	@Override
	public List<NpsGbmCorporateReceiptDto> getCorporateRegNum(final String solId, final String regNum) {
		return this.npsRejectionManager.getCorporateRegNum(solId, regNum);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#getNPSRegAccount(java.lang.
 * String, java.lang.String)
 *//*

	@Override
	public String getNPSRegAccount(final String solId, final String levelCode) {
		return this.npsRejectionManager.getNPSRegAccount(solId, levelCode);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#getReceiptDetails(java.lang.
 * String, java.lang.String, java.lang.String)
 *//*

	@Override
	public NpsGbmRejectionDto getReceiptDetails(final String sReceiptNum, final String sCorpId, final String sSolId) {
		return this.npsRejectionManager.getReceiptDetails(sReceiptNum, sCorpId, sSolId);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#getReceiptDetailsForInquiry(
 * java.lang.String, java.lang.String, java.lang.String)
 *//*

	@Override
	public NpsGbmRejectionDto getReceiptDetailsForInquiry(final String sReceiptNum, final String sCorpId,
			final String sSolId) {
		return this.npsRejectionManager.getReceiptDetailsForInquiry(sReceiptNum, sCorpId, sSolId);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#getReceiptDetailsForModify(
 * java.lang.String, java.lang.String, java.lang.String)
 *//*

	@Override
	public NpsGbmRejectionDto getReceiptDetailsForModify(final String sReceiptNum, final String sCorpId,
			final String sSolId) {
		return this.npsRejectionManager.getReceiptDetailsForModify(sReceiptNum, sCorpId, sSolId);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#getReceiptNumber(java.lang.
 * String, java.lang.String, java.lang.String)
 *//*

	@Override
	public List<GbmNpsRegistrationDto> getReceiptNumber(final String solId, final String corporateId,
			final String regNum) {
		return this.npsRejectionManager.getReceiptNumber(solId, corporateId, regNum);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#getReceiptNumberForInquiry(
 * java.lang.String, java.lang.String, java.lang.String)
 *//*

	@Override
	public List<GbmNpsRegistrationDto> getReceiptNumberForInquiry(final String solId, final String corporateId,
			final String regNum) {
		return this.npsRejectionManager.getReceiptNumberForInquiry(solId, corporateId, regNum);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#getReceiptNumberForModify(
 * java.lang.String, java.lang.String, java.lang.String)
 *//*

	@Override
	public List<GbmNpsRegistrationDto> getReceiptNumberForModify(final String solId, final String corporateId,
			final String regNum) {
		return this.npsRejectionManager.getReceiptNumberForModify(solId, corporateId, regNum);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see com.isl.gbm.nps.service.NpsRejectionService#
 * getReceiptNumberForVerifyDelete(java.lang.String, java.lang.String,
 * java.lang.String)
 *//*

	@Override
	public List<GbmNpsRegistrationDto> getReceiptNumberForVerifyDelete(final String solId, final String corporateId,
			final String regNum) {
		return this.npsRejectionManager.getReceiptNumberForVerifyDelete(solId, corporateId, regNum);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#getRejectReceiptAcDetails(
 * java.lang.String, java.lang.String, java.lang.String)
 *//*

	@Override
	public String getRejectReceiptAcDetails(final String receiptNumber, final String corporateId, final String solId) {
		return this.npsRejectionManager.getRejectReceiptAcDetails(receiptNumber, corporateId, solId);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see com.isl.gbm.nps.service.NpsRejectionService#getTranModes()
 *//*

	@Override
	public List<TransferModesDto> getTranModes() {
		return this.npsRejectionManager.getTranModes();
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#isEODOverForSchemeAndDay(java
 * .lang.String, java.lang.String, java.lang.String, java.util.Date)
 *//*

	@Override
	public boolean isEODOverForSchemeAndDay(final String schemeCode, final String levelCode, final String solId,
			final Date solDate) {
		return this.npsRejectionManager.isEODOverForSchemeAndDay(schemeCode, levelCode, solId, solDate);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#isLockedRecord(com.isl.gbm.
 * nps.dto.NpsGbmRejectionDto)
 *//*

	@Override
	public boolean isLockedRecord(final NpsGbmRejectionDto dto) {
		return this.npsRejectionManager.isLockedRecord(dto);

	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#isReceiptDetailsRejected(java
 * .lang.String, java.lang.String, java.lang.String)
 *//*

	@Override
	public Character isReceiptDetailsRejected(final String receiptNum, final String corpId, final String solId) {
		return this.npsRejectionManager.isReceiptDetailsRejected(receiptNum, corpId, solId);
	}

	*/
/*
 * (non-Javadoc)
 *
 * @see
 * com.isl.gbm.nps.service.NpsRejectionService#modify(com.isl.gbm.nps.dto.
 * NpsGbmRejectionDto)
 *//*

	@Override
	public void modify(final NpsGbmRejectionDto rejectionDto) {

		this.npsRejectionManager.modify(rejectionDto);
	}

	*/
/**
 * Sets the nps rejection manager.
 *
 * @param npsRejectionManager
 *            the new nps rejection manager
 *//*

	public void setNpsRejectionManager(final NpsRejectionManager npsRejectionManager) {
		this.npsRejectionManager = npsRejectionManager;
	}

	@Override
	public void verify(final NpsGbmRejectionDto dto) {
		this.npsRejectionManager.verify(dto);
	}

}
*/
