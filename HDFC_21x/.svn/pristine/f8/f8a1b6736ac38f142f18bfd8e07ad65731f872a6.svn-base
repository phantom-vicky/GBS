/*
 *
 *//*

package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.common.dto.GbmNpsRegistrationDto;
import com.isl.gbm.nps.dto.NpsGbmCorporateReceiptDto;
import com.isl.gbm.nps.dto.NpsGbmRejectionDto;
import com.isl.gbm.nps.dto.TransferModesDto;

import java.util.Date;
import java.util.List;

*/
/**
 * The Interface NpsRejectionService.
 * <p>
 * Creates the.
 *
 * @param npsRejectionDto
 * the nps rejection dto
 * <p>
 * Delete.
 * @param rejectionDto
 * the rejection dto
 * <p>
 * Gets the account number.
 * @param solId
 * the sol id
 * @param schemePlaceHolder
 * the scheme place holder
 * @param schemeCode
 * the scheme code
 * @return the account number
 * <p>
 * Gets the corporate credit number.
 * @param corporateId
 * the corporate id
 * @return the corporate credit number
 * <p>
 * Gets the corporate reg num.
 * @param solId
 * the sol id
 * @param regNum
 * the reg num
 * @return the corporate reg num
 * <p>
 * Gets the NPS reg account.
 * @param solId
 * the sol id
 * @param levelCode
 * the level code
 * @return the NPS reg account
 * <p>
 * Gets the receipt details.
 * @param sReceiptNum
 * the s receipt num
 * @param sCorpId
 * the s corp id
 * @param sSolId
 * the s sol id
 * @return the receipt details
 * <p>
 * Gets the receipt details for inquiry.
 * @param sReceiptNum
 * the s receipt num
 * @param sCorpId
 * the s corp id
 * @param sSolId
 * the s sol id
 * @return the receipt details for inquiry
 * <p>
 * Gets the receipt details for modify.
 * @param sReceiptNum
 * the s receipt num
 * @param sCorpId
 * the s corp id
 * @param sSolId
 * the s sol id
 * @return the receipt details for modify
 * <p>
 * Gets the receipt number.
 * @param solId
 * the sol id
 * @param corporateId
 * the corporate id
 * @param regNum
 * the reg num
 * @return the receipt number
 * <p>
 * Gets the receipt number for inquiry.
 * @param solId
 * the sol id
 * @param corporateId
 * the corporate id
 * @param regNum
 * the reg num
 * @return the receipt number for inquiry
 * <p>
 * Gets the receipt number for modify.
 * @param solId
 * the sol id
 * @param corporateId
 * the corporate id
 * @param regNum
 * the reg num
 * @return the receipt number for modify
 * <p>
 * Gets the receipt number for verify delete.
 * @param solId
 * the sol id
 * @param corporateId
 * the corporate id
 * @param regNum
 * the reg num
 * @return the receipt number for verify delete
 * <p>
 * Gets the reject receipt ac details.
 * @param sReceiptNum
 * the s receipt num
 * @param corporateId
 * the corporate id
 * @param solId
 * the sol id
 * @return the reject receipt ac details
 * <p>
 * Gets the tran modes.
 * @return the tran modes
 * <p>
 * Checks if is EOD over for scheme and day.
 * @param schemeCode
 * the scheme code
 * @param levelCode
 * the level code
 * @param solId
 * the sol id
 * @param solDate
 * the sol date
 * @return true, if is EOD over for scheme and day
 * <p>
 * Checks if is locked record.
 * @param dto
 * the dto
 * @return true, if is locked record
 * <p>
 * Checks if is receipt details rejected.
 * @param sReceiptNum
 * the s receipt num
 * @param sCorpId
 * the s corp id
 * @param sSolId
 * the s sol id
 * @return the character
 * <p>
 * Modify.
 * @param rejectionDto
 * the rejection dto
 *//*

public interface NpsRejectionService {

	*/
/**
 * Creates the.
 *
 * @param npsRejectionDto
 *            the nps rejection dto
 *//*

	public String create(NpsGbmRejectionDto npsRejectionDto);

	*/
/**
 * Delete.
 *
 * @param rejectionDto
 *            the rejection dto
 *//*

	public void delete(NpsGbmRejectionDto rejectionDto);

	*/
/**
 * Gets the account number.
 *
 * @param solId
 *            the sol id
 * @param schemePlaceHolder
 *            the scheme place holder
 * @param schemeCode
 *            the scheme code
 * @return the account number
 *//*

	String getAccountNumber(String solId, String schemePlaceHolder, String schemeCode);

	*/
/**
 * Gets the corporate credit number.
 *
 * @param corporateId
 *            the corporate id
 * @return the corporate credit number
 *//*

	public String getCorporateCreditNumber(final String corporateId);

	*/
/**
 * Gets the corporate reg num.
 *
 * @param solId
 *            the sol id
 * @param regNum
 *            the reg num
 * @return the corporate reg num
 *//*

	public List<NpsGbmCorporateReceiptDto> getCorporateRegNum(final String solId, final String regNum);

	*/
/**
 * Gets the NPS reg account.
 *
 * @param solId
 *            the sol id
 * @param levelCode
 *            the level code
 * @return the NPS reg account
 *//*

	String getNPSRegAccount(String solId, String levelCode);

	*/
/**
 * Gets the receipt details.
 *
 * @param sReceiptNum
 *            the s receipt num
 * @param sCorpId
 *            the s corp id
 * @param sSolId
 *            the s sol id
 * @return the receipt details
 *//*

	public NpsGbmRejectionDto getReceiptDetails(String sReceiptNum, String sCorpId, String sSolId);

	*/
/**
 * Gets the receipt details for inquiry.
 *
 * @param sReceiptNum
 *            the s receipt num
 * @param sCorpId
 *            the s corp id
 * @param sSolId
 *            the s sol id
 * @return the receipt details for inquiry
 *//*

	NpsGbmRejectionDto getReceiptDetailsForInquiry(String sReceiptNum, String sCorpId, String sSolId);

	*/
/**
 * Gets the receipt details for modify.
 *
 * @param sReceiptNum
 *            the s receipt num
 * @param sCorpId
 *            the s corp id
 * @param sSolId
 *            the s sol id
 * @return the receipt details for modify
 *//*

	NpsGbmRejectionDto getReceiptDetailsForModify(String sReceiptNum, String sCorpId, String sSolId);

	*/
/**
 * Gets the receipt number.
 *
 * @param solId
 *            the sol id
 * @param corporateId
 *            the corporate id
 * @param regNum
 *            the reg num
 * @return the receipt number
 *//*

	public List<GbmNpsRegistrationDto> getReceiptNumber(final String solId, final String corporateId,
                                                        final String regNum);

	*/
/**
 * Gets the receipt number for inquiry.
 *
 * @param solId
 *            the sol id
 * @param corporateId
 *            the corporate id
 * @param regNum
 *            the reg num
 * @return the receipt number for inquiry
 *//*

	List<GbmNpsRegistrationDto> getReceiptNumberForInquiry(final String solId, final String corporateId,
                                                           final String regNum);

	*/
/**
 * Gets the receipt number for modify.
 *
 * @param solId
 *            the sol id
 * @param corporateId
 *            the corporate id
 * @param regNum
 *            the reg num
 * @return the receipt number for modify
 *//*

	public List<GbmNpsRegistrationDto> getReceiptNumberForModify(final String solId, final String corporateId,
                                                                 final String regNum);

	*/
/**
 * Gets the receipt number for verify delete.
 *
 * @param solId
 *            the sol id
 * @param corporateId
 *            the corporate id
 * @param regNum
 *            the reg num
 * @return the receipt number for verify delete
 *//*

	List<GbmNpsRegistrationDto> getReceiptNumberForVerifyDelete(final String solId, final String corporateId,
                                                                final String regNum);

	*/
/**
 * Gets the reject receipt ac details.
 *
 * @param sReceiptNum
 *            the s receipt num
 * @param corporateId
 *            the corporate id
 * @param solId
 *            the sol id
 * @return the reject receipt ac details
 *//*

	public String getRejectReceiptAcDetails(String sReceiptNum, String corporateId, String solId);

	*/
/**
 * Gets the tran modes.
 *
 * @return the tran modes
 *//*

	public List<TransferModesDto> getTranModes();

	*/
/**
 * Checks if is EOD over for scheme and day.
 *
 * @param schemeCode
 *            the scheme code
 * @param levelCode
 *            the level code
 * @param solId
 *            the sol id
 * @param solDate
 *            the sol date
 * @return true, if is EOD over for scheme and day
 *//*

	public boolean isEODOverForSchemeAndDay(String schemeCode, String levelCode, String solId, Date solDate);

	*/
/**
 * Checks if is locked record.
 *
 * @param dto
 *            the dto
 * @return true, if is locked record
 *//*

	public boolean isLockedRecord(NpsGbmRejectionDto dto);

	*/
/**
 * Checks if is receipt details rejected.
 *
 * @param sReceiptNum
 *            the s receipt num
 * @param sCorpId
 *            the s corp id
 * @param sSolId
 *            the s sol id
 * @return the character
 *//*

	Character isReceiptDetailsRejected(String sReceiptNum, String sCorpId, String sSolId);

	*/
/**
 * Modify.
 *
 * @param rejectionDto
 *            the rejection dto
 *//*

	public void modify(NpsGbmRejectionDto rejectionDto);

	public void verify(NpsGbmRejectionDto dto);

}
*/
