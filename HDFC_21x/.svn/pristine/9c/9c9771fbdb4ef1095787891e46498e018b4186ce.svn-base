/*
 *
 *//*

package com.isl.gbm.cbs.integration.manager;


import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.common.CommonConstants;
import com.isl.gbm.common.DateUtils;
import com.isl.gbm.common.dao.GbmNpsRegistrationDao;
import com.isl.gbm.common.dao.GbmTransactionDao;
import com.isl.gbm.common.dao.GbmUnverifiedOperationDao;
import com.isl.gbm.common.dto.GbmNpsRegistrationDto;
import com.isl.gbm.common.dto.GbmTransactionMasterDto;
import com.isl.gbm.common.model.GbmNpsRegistrationMaster;
import com.isl.gbm.common.model.GbmTransactionMaster;
import com.isl.gbm.common.model.GbmUnverifiedOperations;
import com.isl.gbm.nps.dao.NpsCorporateReceiptNumberDao;
import com.isl.gbm.nps.dao.NpsRejectionDao;
import com.isl.gbm.nps.dto.NpsGbmCorporateReceiptDto;
import com.isl.gbm.nps.dto.NpsGbmRejectionDto;
import com.isl.gbm.nps.dto.NpsGbmRejectionHandlingDto;
import com.isl.gbm.nps.dto.TransferModesDto;
import com.isl.gbm.nps.model.GbmNpsCorporateMaster;
import com.isl.gbm.nps.model.NpsGbmRejectionHandlingMaster;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

*/
/**
 * The Class NpsRejectionManager.
 * The nps corporate receipt number dao.  The nps eod dao.  The gbm unverified operation dao.  The gbm transaction dao.  The nps rejection dao.
 * Creates the.
 *
 * @param npsRejectionDto
 * the nps rejection dto
 * <p>
 * Delete.
 * @param rejectionDto
 * the rejection dto
 * <p>
 * Gets the corporate reg num.
 * @param solId
 * the sol id
 * @param regNum
 * the reg num
 * @return the corporate reg num
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
 * Gets the rejection details for inquiry.
 * @param sReceiptNum
 * the s receipt num
 * @param sCorpId
 * the s corp id
 * @param sSolId
 * the s sol id
 * @return the rejection details for inquiry
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
 * Checks if is receipt details rejected.
 * @param receiptNum
 * the receipt num
 * @param corpId
 * the corp id
 * @param solId
 * the sol id
 * @return the character
 * <p>
 * Map registration model to dto.
 * @param gbmNpsRegistrationMaster
 * the gbm nps registration master
 * @return the gbm nps registration dto
 * <p>
 * Map rejection model to dto.
 * @param master
 * the master
 * @return the nps gbm rejection handling dto
 * <p>
 * Map transaction dto to model.
 * @param gbmTransactionMasterDto
 * the gbm transaction master dto
 * @return the gbm transaction master
 * <p>
 * Modify.
 * @param rejectionDto
 * the rejection dto
 * <p>
 * Save transaction details.
 * @paramgbmTransactionMasterDto the gbm transaction master dto
 * @paramsolDateDto the sol date dto
 * <p>
 * Sets the gbm transaction dao.
 * @param gbmTransactionDao
 * the new gbm transaction dao
 * <p>
 * Sets the gbm unverified operation dao.
 * @param gbmUnverifiedOperationDao
 * the new gbm unverified operation dao
 * <p>
 * Sets the nps corporate receipt number dao.
 * @param npsCorporateReceiptNumberDao
 * the new nps corporate receipt number dao
 * <p>
 * Sets the nps eod dao.
 * @param npsEodDao
 * the new nps eod dao
 * <p>
 * Sets the nps rejection dao.
 * @param npsRejectionDao
 * the new nps rejection dao
 *//*

@Component
public class NpsRejectionManager {

	*/
/** The nps corporate receipt number dao. *//*

	@Autowired
	private NpsCorporateReceiptNumberDao npsCorporateReceiptNumberDao;

	*/
/** The nps eod dao. *//*

	@Autowired
	private NpsEodDao npsEodDao;

	@Autowired
	private UnlockRecordsDao unlockRecordsDao;

	*/
/** The gbm unverified operation dao. *//*

	@Autowired
	private GbmUnverifiedOperationDao gbmUnverifiedOperationDao;

	*/
/** The gbm transaction dao. *//*

	@Autowired
	private GbmTransactionDao gbmTransactionDao;

	*/
/** The nps rejection dao. *//*

	@Autowired
	private NpsRejectionDao npsRejectionDao;

	@Autowired
	private GbmNpsRegistrationDao gbmNpsRegistrationDao;

	*/
/**
 * Creates the.
 *
 * @param npsRejectionDto
 *            the nps rejection dto
 *//*


	public String create(final NpsGbmRejectionDto npsRejectionDto) {

		this.saveTransactionDetails(npsRejectionDto);

		final NpsGbmRejectionHandlingMaster rejectionHandlingMaster = new NpsGbmRejectionHandlingMaster();
		rejectionHandlingMaster.setSolId(npsRejectionDto.getNpsRegistration().getSolId());
		rejectionHandlingMaster.setReceiptNumber(npsRejectionDto.getNpsRegistration().getReceiptNumber());
		rejectionHandlingMaster.setRejectionDate(DateUtils.getDateWithoutTime(npsRejectionDto.getSolDateDto().getSolDate()));
		rejectionHandlingMaster.setReason(npsRejectionDto.getRejectionHandlingDto().getReason());
		rejectionHandlingMaster.setModeOfPayment(npsRejectionDto.getRejectionHandlingDto().getModeOfPayment());
		rejectionHandlingMaster.setRejectionAmount(npsRejectionDto.getNpsRegistration().getTotalAmount());
		rejectionHandlingMaster.setCreatedBy(UserAccountUtils.getCurrentUsername());
		rejectionHandlingMaster.setCreatedDate(DateUtils.getDateWithoutTime(new Date()));
		rejectionHandlingMaster.setIsDeleted(CommonConstants.NO);
		rejectionHandlingMaster.setVerifiedStatus(CommonConstants.NO);
		rejectionHandlingMaster.setTranRefId(npsRejectionDto.getNpsTransaction().getTranRefId());
		rejectionHandlingMaster.setAccountNumber(npsRejectionDto.getRejectionHandlingDto().getAccountNumber());
		rejectionHandlingMaster.setCorporateId(npsRejectionDto.getNpsRegistration().getCorporateId());
		this.npsRejectionDao.create(rejectionHandlingMaster);

		final GbmUnverifiedOperations unverifiedOperations = new GbmUnverifiedOperations();
		unverifiedOperations.setSolId(npsRejectionDto.getNpsRegistration().getSolId());
		unverifiedOperations.setOperationId(npsRejectionDto.getNpsTransaction().getTranRefId());
		unverifiedOperations.setOperation(CommonConstants.TRAN_OPERATION_ADD);
		unverifiedOperations.setSchCode(SchemeCodes.NPS.getSchemeCode());
		unverifiedOperations.setVerifiedStatus(CommonConstants.NO);
		unverifiedOperations.setEnteredDate(new Date());
		unverifiedOperations.setIsTranFinancial(CommonConstants.YES);
		this.gbmUnverifiedOperationDao.createRejection(unverifiedOperations);

		return npsRejectionDto.getNpsTransaction().getTranId();

	}

	*/
/**
 * Delete.
 *
 * @param rejectionDto
 *            the rejection dto
 *//*



	public void delete(final NpsGbmRejectionDto rejectionDto) {
		final NpsGbmRejectionHandlingMaster master = new NpsGbmRejectionHandlingMaster();
		master.setIsDeleted(CommonConstants.YES);
		master.setTranRefId(rejectionDto.getRejectionHandlingDto().getTranRefId());
		this.npsRejectionDao.delete(master);

		final GbmUnverifiedOperations unverifiedOperations = new GbmUnverifiedOperations();
		unverifiedOperations.setVerifiedStatus('D');
		unverifiedOperations.setOperationId(rejectionDto.getRejectionHandlingDto().getTranRefId());
		this.gbmUnverifiedOperationDao.deleteRejection(unverifiedOperations);

		final GbmTransactionMaster transactionMaster = new GbmTransactionMaster();
		transactionMaster.setIsDeleted(CommonConstants.YES);
		transactionMaster.setTranId(rejectionDto.getNpsTransaction().getTranId());
		transactionMaster.setTranDate(rejectionDto.getNpsTransaction().getTranDate());
		transactionMaster.setSolId(rejectionDto.getNpsTransaction().getSolId());
		this.gbmTransactionDao.deleteRejection(transactionMaster);

	}

	public String getAccountNumber(final String solId, final String schemePlaceHolder, final String schemeCode) {

		String paramName = null;
		if (StringUtils.equals(schemePlaceHolder, "CDAC")) {
			paramName = "PH_BANKERS_CHQ_AC";
		}

		else if (StringUtils.equals(schemePlaceHolder, "PO")) {
			paramName = "PH_POAC";
		} else if (StringUtils.equals(schemePlaceHolder, "DD")) {
			paramName = "PH_DDAC";
		}

		return this.npsRejectionDao.getAccountNumber(solId, paramName, schemeCode);
	}

	public String getCorporateCreditNumber(final String corporateId) {
		return this.npsCorporateReceiptNumberDao.getCorporateCreditNumber(corporateId);
	}

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

	public List<NpsGbmCorporateReceiptDto> getCorporateRegNum(final String solId, final String regNum) {
		final List<GbmNpsCorporateMaster> npsGbmCorpMstList = this.npsCorporateReceiptNumberDao
				.getCorporateRegNumForRejection(solId, regNum);
		final List<NpsGbmCorporateReceiptDto> dtoList = new ArrayList<>();
		for (final GbmNpsCorporateMaster npsGbmCorporateMaster : npsGbmCorpMstList) {
			final NpsGbmCorporateReceiptDto npsGbmCorporateReceiptDto = new NpsGbmCorporateReceiptDto();
			npsGbmCorporateReceiptDto.setCorporateRegNumber(npsGbmCorporateMaster.getCorporateRegNumber());
			npsGbmCorporateReceiptDto.setCorporateId(npsGbmCorporateMaster.getCorporateId());
			npsGbmCorporateReceiptDto.setCorporateName(npsGbmCorporateMaster.getCorporateName());
			dtoList.add(npsGbmCorporateReceiptDto);
		}
		return dtoList;
	}

	public String getNPSRegAccount(final String solId, final String levelCode) {
		return this.npsRejectionDao.getNPSRegAccount(solId, levelCode);
	}

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

	public NpsGbmRejectionDto getReceiptDetails(final String sReceiptNum, final String sCorpId, final String sSolId) {

		final GbmNpsRegistrationMaster registrationMaster = this.npsRejectionDao.getReceiptDetails(sReceiptNum, sCorpId,
				sSolId);

		final GbmNpsRegistrationDto dto = this.mapRegistrationModelToDto(registrationMaster);

		final NpsGbmRejectionHandlingDto gbmRejectionHandlingDto = new NpsGbmRejectionHandlingDto();

		// gbmRejectionHandlingDto.setReason(sCorpId);
		gbmRejectionHandlingDto
				.setAccountNumber(this.npsRejectionDao.getNPSRegAccount(sSolId, LevelCodes.POP_SP.getLevelCode()));
		gbmRejectionHandlingDto.setRegistrationNumber(this.npsRejectionDao.getNPSRegistrationNumber(sSolId));
		final GbmTransactionMasterDto transactionDto = new GbmTransactionMasterDto();
		final NpsGbmRejectionDto rejectionDto = new NpsGbmRejectionDto();
		rejectionDto.setNpsRegistration(dto);
		rejectionDto.setRejectionHandlingDto(gbmRejectionHandlingDto);
		rejectionDto.setNpsTransaction(transactionDto);
		return rejectionDto;
	}

	public NpsGbmRejectionDto getReceiptDetailsForDelete(final String sReceiptNum, final String sCorpId,
			final String sSolId) {

		final GbmNpsRegistrationMaster registrationMaster = this.npsRejectionDao.getReceiptDetails(sReceiptNum, sCorpId,
				sSolId);
		final GbmNpsRegistrationDto dto = this.mapRegistrationModelToDto(registrationMaster);

		final NpsGbmRejectionHandlingMaster rejectionMaster = this.npsRejectionDao
				.getRejectionDetailsForInquiry(sReceiptNum, sCorpId, sSolId);
		final NpsGbmRejectionHandlingDto rejetionDto = this.mapRejectionModelToDto(rejectionMaster);
		rejetionDto.setRegistrationNumber(this.npsRejectionDao.getNPSRegistrationNumber(sSolId));
		final NpsGbmRejectionDto npsGbmRejectionDto = new NpsGbmRejectionDto();
		npsGbmRejectionDto.setNpsRegistration(dto);
		npsGbmRejectionDto.setRejectionHandlingDto(rejetionDto);
		return npsGbmRejectionDto;

	}

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

	public NpsGbmRejectionDto getReceiptDetailsForInquiry(final String sReceiptNum, final String sCorpId,
			final String sSolId) {

		final GbmNpsRegistrationMaster registrationMaster = this.npsRejectionDao
				.getReceiptDetailsForInquiry(sReceiptNum, sCorpId, sSolId);
		final GbmNpsRegistrationDto dto = this.mapRegistrationModelToDto(registrationMaster);

		final NpsGbmRejectionHandlingMaster rejectionMaster = this.npsRejectionDao
				.getRejectionDetailsForInquiry(sReceiptNum, sCorpId, sSolId);
		final NpsGbmRejectionHandlingDto rejetionDto = this.mapRejectionModelToDto(rejectionMaster);
		rejetionDto.setRegistrationNumber(this.npsRejectionDao.getNPSRegistrationNumber(sSolId));
		final NpsGbmRejectionDto npsGbmRejectionDto = new NpsGbmRejectionDto();

		final GbmTransactionMaster transactionMaster = this.gbmTransactionDao
				.getTransactionDetails(rejetionDto.getTranRefId());
		final GbmTransactionMasterDto gttDto = this.mapTransactionModelToDto(transactionMaster);
		npsGbmRejectionDto.setNpsRegistration(dto);
		npsGbmRejectionDto.setRejectionHandlingDto(rejetionDto);
		npsGbmRejectionDto.setNpsTransaction(gttDto);
		return npsGbmRejectionDto;

	}

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

	public NpsGbmRejectionDto getReceiptDetailsForModify(final String sReceiptNum, final String sCorpId,
			final String sSolId) {

		final GbmNpsRegistrationMaster registrationMaster = this.npsRejectionDao.getReceiptDetails(sReceiptNum, sCorpId,
				sSolId);
		final GbmNpsRegistrationDto dto = this.mapRegistrationModelToDto(registrationMaster);

		final NpsGbmRejectionHandlingMaster rejectionMaster = this.npsRejectionDao.getRejectionDetails(sReceiptNum,
				sCorpId, sSolId);
		final NpsGbmRejectionHandlingDto rejetionDto = this.mapRejectionModelToDto(rejectionMaster);
		rejetionDto.setRegistrationNumber(this.npsRejectionDao.getNPSRegistrationNumber(sSolId));

		final GbmTransactionMaster transactionMaster = this.gbmTransactionDao
				.getTransactionDetails(rejetionDto.getTranRefId());
		final GbmTransactionMasterDto gttDto = this.mapTransactionModelToDto(transactionMaster);

		final NpsGbmRejectionDto npsGbmRejectionDto = new NpsGbmRejectionDto();
		npsGbmRejectionDto.setNpsRegistration(dto);
		npsGbmRejectionDto.setRejectionHandlingDto(rejetionDto);
		npsGbmRejectionDto.setNpsTransaction(gttDto);
		return npsGbmRejectionDto;

	}

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
			final String regNum) {
		final List<GbmNpsRegistrationMaster> npsGbmCorpMstList = this.npsRejectionDao.getReceiptNumber(solId,
				corporateId, regNum);
		final List<GbmNpsRegistrationDto> dtoList = new ArrayList<>();
		for (final GbmNpsRegistrationMaster npsGbmRegistrationMaster : npsGbmCorpMstList) {
			final GbmNpsRegistrationDto gbmNpsRegistrationDto = new GbmNpsRegistrationDto();
			gbmNpsRegistrationDto.setReceiptNumber(npsGbmRegistrationMaster.getReceiptNumber());
			gbmNpsRegistrationDto.setSubsFirstName(npsGbmRegistrationMaster.getSubsFirstName());
			gbmNpsRegistrationDto.setSubsMiddleName(npsGbmRegistrationMaster.getSubsMiddleName());
			gbmNpsRegistrationDto.setSubsLastName(npsGbmRegistrationMaster.getSubsLastName());
			gbmNpsRegistrationDto.setTierCode(npsGbmRegistrationMaster.getTierCode());
			dtoList.add(gbmNpsRegistrationDto);
		}
		return dtoList;
	}

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

	public List<GbmNpsRegistrationDto> getReceiptNumberForInquiry(final String solId, final String corporateId,
			final String regNum) {
		final List<GbmNpsRegistrationMaster> npsGbmCorpMstList = this.npsRejectionDao.getReceiptNumberForInquiry(solId,
				corporateId, regNum);
		final List<GbmNpsRegistrationDto> dtoList = new ArrayList<>();
		for (final GbmNpsRegistrationMaster npsGbmRegistrationMaster : npsGbmCorpMstList) {
			final GbmNpsRegistrationDto gbmNpsRegistrationDto = new GbmNpsRegistrationDto();
			gbmNpsRegistrationDto.setReceiptNumber(npsGbmRegistrationMaster.getReceiptNumber());
			gbmNpsRegistrationDto.setSubsFirstName(npsGbmRegistrationMaster.getSubsFirstName());
			gbmNpsRegistrationDto.setSubsMiddleName(npsGbmRegistrationMaster.getSubsMiddleName());
			gbmNpsRegistrationDto.setSubsLastName(npsGbmRegistrationMaster.getSubsLastName());
			gbmNpsRegistrationDto.setIsDeleted(npsGbmRegistrationMaster.getIsDeleted());
			dtoList.add(gbmNpsRegistrationDto);
		}
		return dtoList;
	}

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
			final String regNum) {
		final List<GbmNpsRegistrationMaster> npsGbmCorpMstList = this.npsRejectionDao.getReceiptNumberForModify(solId,
				corporateId, regNum);
		final List<GbmNpsRegistrationDto> dtoList = new ArrayList<>();
		for (final GbmNpsRegistrationMaster npsGbmRegistrationMaster : npsGbmCorpMstList) {
			final GbmNpsRegistrationDto gbmNpsRegistrationDto = new GbmNpsRegistrationDto();
			gbmNpsRegistrationDto.setReceiptNumber(npsGbmRegistrationMaster.getReceiptNumber());
			gbmNpsRegistrationDto.setSubsFirstName(npsGbmRegistrationMaster.getSubsFirstName());
			gbmNpsRegistrationDto.setSubsMiddleName(npsGbmRegistrationMaster.getSubsMiddleName());
			gbmNpsRegistrationDto.setSubsLastName(npsGbmRegistrationMaster.getSubsLastName());
			dtoList.add(gbmNpsRegistrationDto);
		}
		return dtoList;
	}

	public List<GbmNpsRegistrationDto> getReceiptNumberForVerifyDelete(final String solId, final String corporateId,
			final String regNum) {
		final List<GbmNpsRegistrationMaster> npsGbmCorpMstList = this.npsRejectionDao
				.getReceiptNumberForVerifyDelete(solId, corporateId, regNum);
		final List<GbmNpsRegistrationDto> dtoList = new ArrayList<>();
		for (final GbmNpsRegistrationMaster npsGbmRegistrationMaster : npsGbmCorpMstList) {
			final GbmNpsRegistrationDto gbmNpsRegistrationDto = new GbmNpsRegistrationDto();
			gbmNpsRegistrationDto.setReceiptNumber(npsGbmRegistrationMaster.getReceiptNumber());
			gbmNpsRegistrationDto.setSubsFirstName(npsGbmRegistrationMaster.getSubsFirstName());
			gbmNpsRegistrationDto.setSubsMiddleName(npsGbmRegistrationMaster.getSubsMiddleName());
			gbmNpsRegistrationDto.setSubsLastName(npsGbmRegistrationMaster.getSubsLastName());
			dtoList.add(gbmNpsRegistrationDto);
		}
		return dtoList;
	}

	*/
/**
 * Gets the rejection details for inquiry.
 *
 * @param sReceiptNum
 *            the s receipt num
 * @param sCorpId
 *            the s corp id
 * @param sSolId
 *            the s sol id
 * @return the rejection details for inquiry
 *//*

	public NpsGbmRejectionHandlingMaster getRejectionDetailsForInquiry(final String sReceiptNum, final String sCorpId,
			final String sSolId) {
		return this.npsRejectionDao.getRejectionDetailsForInquiry(sReceiptNum, sCorpId, sSolId);
	}

	public String getRejectReceiptAcDetails(final String sReceiptNum, final String corporateId, final String solId) {
		return this.npsRejectionDao.getRejectReceiptAcDetails(sReceiptNum, corporateId, solId);
	}

	public List<TransferModesDto> getTranModes() {
		final List<TransferModesDto> transactionTypeDtoList = new ArrayList<>();
		for (final TransferModes transactionType : TransferModes.values()) {
			final TransferModesDto transactionTypeDto = new TransferModesDto();
			transactionTypeDto.setTransferModeName(transactionType.getTransferModeName());
			transactionTypeDto.setTransferModeKey(transactionType.getTransferModeKey());
			transactionTypeDto.setTransferModeValue(transactionType.getTransferModeValue());
			transactionTypeDtoList.add(transactionTypeDto);
		}
		return transactionTypeDtoList;
	}

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

	public boolean isEODOverForSchemeAndDay(final String schemeCode, final String levelCode, final String solId,
			final Date solDate) {

		return this.npsEodDao.isEodOver(schemeCode, levelCode, solId, solDate);
	}

	public boolean isLockedRecord(final NpsGbmRejectionDto dto) {
		final String solId = dto.getNpsTransaction().getSolId();
		final String stranId = dto.getNpsTransaction().getTranId();
		return this.unlockRecordsDao.isLockedRecord(solId, stranId, SchemeCodes.NPS.getSchemeCode(),
				dto.getNpsTransaction().getTranDate());
	}

	*/
/**
 * Checks if is receipt details rejected.
 *
 * @param receiptNum
 *            the receipt num
 * @param corpId
 *            the corp id
 * @param solId
 *            the sol id
 * @return the character
 *//*

	public Character isReceiptDetailsRejected(final String receiptNum, final String corpId, final String solId) {
		return this.npsRejectionDao.isReceiptDetailsRejected(receiptNum, corpId, solId);
	}

	*/
/**
 * Map registration model to dto.
 *
 * @param gbmNpsRegistrationMaster
 *            the gbm nps registration master
 * @return the gbm nps registration dto
 *//*

	private GbmNpsRegistrationDto mapRegistrationModelToDto(final GbmNpsRegistrationMaster gbmNpsRegistrationMaster) {

		final GbmNpsRegistrationDto npsRegistrationDto = new GbmNpsRegistrationDto();
		npsRegistrationDto.setAcctNum(gbmNpsRegistrationMaster.getAcctNum());
		npsRegistrationDto.setAccType(gbmNpsRegistrationMaster.getAccType());
		npsRegistrationDto.setAggregatorId(gbmNpsRegistrationMaster.getAggregatorId());
		npsRegistrationDto.setBankMicrCode(gbmNpsRegistrationMaster.getBankMicrCode());
		npsRegistrationDto.setBankName(gbmNpsRegistrationMaster.getBankName());
		npsRegistrationDto.setBothOption(gbmNpsRegistrationMaster.getBothOption());
		npsRegistrationDto.setBranchAddress(gbmNpsRegistrationMaster.getBranchAddress());
		npsRegistrationDto.setBranchName(gbmNpsRegistrationMaster.getBranchName());
		npsRegistrationDto.setBranchPincode(gbmNpsRegistrationMaster.getBranchPincode());
		npsRegistrationDto.setCategory(gbmNpsRegistrationMaster.getCategory());
		npsRegistrationDto.setContAmt(gbmNpsRegistrationMaster.getContAmt());
		npsRegistrationDto.setContCommAmt(gbmNpsRegistrationMaster.getContCommAmt());
		npsRegistrationDto.setContReceiptNum(gbmNpsRegistrationMaster.getContReceiptNum());
		npsRegistrationDto.setContServTaxAmt(gbmNpsRegistrationMaster.getContServTaxAmt());
		npsRegistrationDto.setCorporate(gbmNpsRegistrationMaster.getCorporate());
		npsRegistrationDto.setCorporateId(gbmNpsRegistrationMaster.getCorporateId());
		npsRegistrationDto.setCreatedBy(gbmNpsRegistrationMaster.getCreatedBy());
		npsRegistrationDto.setCreatedDate(gbmNpsRegistrationMaster.getCreatedDate());
		npsRegistrationDto.setCustId(gbmNpsRegistrationMaster.getCustId());
		npsRegistrationDto.setDateOfDocIssuedAdd(gbmNpsRegistrationMaster.getDateOfDocIssuedAdd());
		npsRegistrationDto.setIsDeleted(gbmNpsRegistrationMaster.getIsDeleted());
		npsRegistrationDto.setDob(gbmNpsRegistrationMaster.getDob());
		npsRegistrationDto.setDobForDetails(gbmNpsRegistrationMaster.getDobForDetails());
		npsRegistrationDto.setDocForAddress(gbmNpsRegistrationMaster.getDocForAddress());
		npsRegistrationDto.setDocOfDob(gbmNpsRegistrationMaster.getDocOfDob());
		npsRegistrationDto.setDocOfIdentity(gbmNpsRegistrationMaster.getDocOfIdentity());
		npsRegistrationDto.setEduQua(gbmNpsRegistrationMaster.getEduQua());
		npsRegistrationDto.setEmailId(gbmNpsRegistrationMaster.getEmailId());
		npsRegistrationDto.setEmployeeContAmt(gbmNpsRegistrationMaster.getEmployeeContAmt());
		npsRegistrationDto.setEmployeeId(gbmNpsRegistrationMaster.getEmployeeId());
		npsRegistrationDto.setEmployerContAmt(gbmNpsRegistrationMaster.getEmployeeContAmt());
		npsRegistrationDto.setEquity(gbmNpsRegistrationMaster.getEquity());
		npsRegistrationDto.setFathersFirstName(gbmNpsRegistrationMaster.getFathersFirstName());
		npsRegistrationDto.setFathersFirstNameInHindi(gbmNpsRegistrationMaster.getFathersFirstNameInHindi());
		npsRegistrationDto.setFathersLastName(gbmNpsRegistrationMaster.getFathersLastName());
		npsRegistrationDto.setFathersLastNameInHindi(gbmNpsRegistrationMaster.getFathersLastNameInHindi());
		npsRegistrationDto.setFathersMiddleName(gbmNpsRegistrationMaster.getFathersMiddleName());
		npsRegistrationDto.setFathersMiddleNameInHindi(gbmNpsRegistrationMaster.getFathersMiddleNameInHindi());
		npsRegistrationDto.setForeignAddState(gbmNpsRegistrationMaster.getForeignAddState());
		npsRegistrationDto.setGender(gbmNpsRegistrationMaster.getGender());
		npsRegistrationDto.setGilt(gbmNpsRegistrationMaster.getGilt());
		npsRegistrationDto.setId(gbmNpsRegistrationMaster.getId());
		npsRegistrationDto.setIfsCode(gbmNpsRegistrationMaster.getIfsCode());
		npsRegistrationDto.setIncomeRange(gbmNpsRegistrationMaster.getIncomeRange());
		npsRegistrationDto.setIsActive(gbmNpsRegistrationMaster.getIsActive());
		npsRegistrationDto.setIsMigrated(gbmNpsRegistrationMaster.getIsMigrated());
		npsRegistrationDto.setIsNpsInGov(gbmNpsRegistrationMaster.getIsNpsInGov());
		npsRegistrationDto.setIsOperAccNum(gbmNpsRegistrationMaster.getIsOperAccNum());
		npsRegistrationDto.setIsResigned(gbmNpsRegistrationMaster.getIsResigned());
		npsRegistrationDto.setIsSiEnabled(gbmNpsRegistrationMaster.getIsSiEnabled());
		npsRegistrationDto.setIsSwavalamban(gbmNpsRegistrationMaster.getIsSwavalamban());
		npsRegistrationDto.setLastModifiedBy(gbmNpsRegistrationMaster.getLastModifiedBy());
		npsRegistrationDto.setLastModifiedDate(gbmNpsRegistrationMaster.getLastModifiedDate());
		npsRegistrationDto.setMembershipNum(gbmNpsRegistrationMaster.getMembershipNum());
		npsRegistrationDto.setMobileNum(gbmNpsRegistrationMaster.getMobileNum());
		npsRegistrationDto.setModeOfPayment(gbmNpsRegistrationMaster.getModeOfPayment());
		npsRegistrationDto.setNpsCustId(gbmNpsRegistrationMaster.getNpsCustId());
		npsRegistrationDto.setNriBankAcctStatus(gbmNpsRegistrationMaster.getNriBankAcctStatus());
		npsRegistrationDto.setNriFlag(gbmNpsRegistrationMaster.getNriFlag());
		npsRegistrationDto.setPanNumber(gbmNpsRegistrationMaster.getPanNumber());
		npsRegistrationDto.setPermanentAddress(gbmNpsRegistrationMaster.getPermanentAddress());
		npsRegistrationDto.setPermanentCountry(gbmNpsRegistrationMaster.getPermanentCountry());
		npsRegistrationDto.setPermanentDistrict(gbmNpsRegistrationMaster.getPermanentDistrict());
		npsRegistrationDto.setPermanentLandMark(gbmNpsRegistrationMaster.getPermanentLandMark());
		npsRegistrationDto.setPermanentPincode(gbmNpsRegistrationMaster.getPermanentPincode());
		npsRegistrationDto.setPermanentRoadStreetLane(gbmNpsRegistrationMaster.getPermanentRoadStreetLane());
		npsRegistrationDto.setPermanentStateCode(gbmNpsRegistrationMaster.getPermanentStateCode());
		npsRegistrationDto.setPfmChoice(gbmNpsRegistrationMaster.getPfmChoice());
		npsRegistrationDto.setPhoneNum(gbmNpsRegistrationMaster.getPhoneNum());
		npsRegistrationDto.setPolitExposed(gbmNpsRegistrationMaster.getPolitExposed());
		npsRegistrationDto.setPranCardInHindi(gbmNpsRegistrationMaster.getPranCardInHindi());
		npsRegistrationDto.setPranCardIssued(gbmNpsRegistrationMaster.getPranCardIssued());
		npsRegistrationDto.setPranNum(gbmNpsRegistrationMaster.getPranNum());
		npsRegistrationDto.setPreAddCommu(gbmNpsRegistrationMaster.getPreAddCommu());
		npsRegistrationDto.setPresentAddress(gbmNpsRegistrationMaster.getPresentAddress());
		npsRegistrationDto.setPresentCountry(gbmNpsRegistrationMaster.getPresentCountry());
		npsRegistrationDto.setPresentDistrict(gbmNpsRegistrationMaster.getPresentDistrict());
		npsRegistrationDto.setPresentLandMark(gbmNpsRegistrationMaster.getPresentLandMark());
		npsRegistrationDto.setPresentPincode(gbmNpsRegistrationMaster.getPresentPincode());
		npsRegistrationDto.setPresentRoadStreetLane(gbmNpsRegistrationMaster.getPresentRoadStreetLane());
		npsRegistrationDto.setPresentStateCode(gbmNpsRegistrationMaster.getPresentStateCode());
		npsRegistrationDto.setProofOfAddress(gbmNpsRegistrationMaster.getProofOfAddress());
		npsRegistrationDto.setProofOfDob(gbmNpsRegistrationMaster.getProofOfDob());
		npsRegistrationDto.setProofOfIdentity(gbmNpsRegistrationMaster.getProofOfIdentity());
		npsRegistrationDto.setPsprtDateOfExpiry(gbmNpsRegistrationMaster.getPsprtDateOfExpiry());
		npsRegistrationDto.setPsprtDateOfIssue(gbmNpsRegistrationMaster.getPsprtDateOfIssue());
		npsRegistrationDto.setPsprtNum(gbmNpsRegistrationMaster.getPsprtNum());
		npsRegistrationDto.setPsprtPlaceOfIssue(gbmNpsRegistrationMaster.getPsprtPlaceOfIssue());
		npsRegistrationDto.setPsprtVisaWorkPermit(gbmNpsRegistrationMaster.getPsprtVisaWorkPermit());
		npsRegistrationDto.setReceiptNumber(gbmNpsRegistrationMaster.getReceiptNumber());
		npsRegistrationDto.setRegiCommAmt(gbmNpsRegistrationMaster.getRegiCommAmt());
		npsRegistrationDto.setRegiServiceTaxAmt(gbmNpsRegistrationMaster.getRegiServiceTaxAmt());
		npsRegistrationDto.setRemarks(gbmNpsRegistrationMaster.getRemarks());
		npsRegistrationDto.setSchChoice(gbmNpsRegistrationMaster.getSchChoice());
		npsRegistrationDto.setSmsAlerts(gbmNpsRegistrationMaster.getSmsAlerts());
		npsRegistrationDto.setSolId(gbmNpsRegistrationMaster.getSolId());
		npsRegistrationDto.setStdCode(gbmNpsRegistrationMaster.getStdCode());
		npsRegistrationDto.setSubFirstNameInHindi(gbmNpsRegistrationMaster.getSubFirstNameInHindi());
		npsRegistrationDto.setSubLastNameInHindi(gbmNpsRegistrationMaster.getSubLastNameInHindi());
		npsRegistrationDto.setSubsMiddleName(gbmNpsRegistrationMaster.getSubsMiddleName());
		npsRegistrationDto.setSubsFirstName(gbmNpsRegistrationMaster.getSubsFirstName());
		npsRegistrationDto.setSubsLastName(gbmNpsRegistrationMaster.getSubsLastName());
		npsRegistrationDto.setSubsMiddleName(gbmNpsRegistrationMaster.getSubsMiddleName());
		npsRegistrationDto.setTierCode(gbmNpsRegistrationMaster.getTierCode());
		npsRegistrationDto.setTitle(gbmNpsRegistrationMaster.getTitle());
		npsRegistrationDto.setTranDate(gbmNpsRegistrationMaster.getTranDate());
		npsRegistrationDto.setTranId(gbmNpsRegistrationMaster.getTranId());
		npsRegistrationDto.setTranRefId(gbmNpsRegistrationMaster.getTranRefId());
		npsRegistrationDto.setValueDate(gbmNpsRegistrationMaster.getValueDate());
		npsRegistrationDto.setVerifiedBy(gbmNpsRegistrationMaster.getVerifiedBy());
		npsRegistrationDto.setVerifiedDate(gbmNpsRegistrationMaster.getVerifiedDate());
		npsRegistrationDto.setVerifiedStatus(gbmNpsRegistrationMaster.getVerifiedStatus());
		npsRegistrationDto.setVerifiedStatusPop(gbmNpsRegistrationMaster.getVerifiedStatusPop());

		final double commForRegistration = gbmNpsRegistrationMaster.getRegiCommAmt();
		final double sTaxForRegistration = gbmNpsRegistrationMaster.getRegiServiceTaxAmt();
		double commForContribution =0;
		double sTaxForContribution =0;
		double initialContAmount  =0;
		if(gbmNpsRegistrationMaster.getContCommAmt()!=null){
		 commForContribution = gbmNpsRegistrationMaster.getContCommAmt();
		 sTaxForContribution = gbmNpsRegistrationMaster.getContServTaxAmt();
		 initialContAmount = gbmNpsRegistrationMaster.getContAmt();}
		final double totalAmount = commForRegistration + sTaxForRegistration + commForContribution + sTaxForContribution
				+ initialContAmount;
		npsRegistrationDto.setTotalAmount(totalAmount);
		return npsRegistrationDto;
	}

	*/
/**
 * Map rejection model to dto.
 *
 * @param master
 *            the master
 * @return the nps gbm rejection handling dto
 *//*

	private NpsGbmRejectionHandlingDto mapRejectionModelToDto(final NpsGbmRejectionHandlingMaster master) {

		final NpsGbmRejectionHandlingDto dto = new NpsGbmRejectionHandlingDto();
		dto.setSolId(master.getSolId());
		dto.setReceiptNumber(master.getReceiptNumber());
		dto.setRejectionDate(master.getRejectionDate());
		dto.setReason(master.getReason());
		dto.setModeOfPayment(master.getModeOfPayment());
		dto.setTranRefId(master.getTranRefId());
		dto.setCreatedBy(master.getCreatedBy());
		dto.setCreatedDate(master.getCreatedDate());
		dto.setLastModifiedBy(master.getLastModifiedBy());
		dto.setLastModifiedDate(master.getLastModifiedDate());
		dto.setIsDeleted(master.getIsDeleted());
		dto.setVerifiedStatus(master.getVerifiedStatus());
		dto.setRejectionAmount(master.getRejectionAmount());
		dto.setAccountNumber(master.getAccountNumber());
		dto.setDdPoNumber(master.getDdPoNumber());
		dto.setDdPoDate(master.getDdPoDate());
		dto.setDrwanOn(master.getDrwanOn());
		dto.setEmployeeId(master.getEmployeeId());
		dto.setCorporateId(master.getCorporateId());
		return dto;

	}

	*/
/**
 * Map transaction dto to model.
 *
 * @param gbmTransactionMasterDto
 *            the gbm transaction master dto
 * @return the gbm transaction master
 *//*

	private GbmTransactionMaster mapTransactionDtoToModel(final GbmTransactionMasterDto gbmTransactionMasterDto) {
		final GbmTransactionMaster gbmNpsTransactionMaster = new GbmTransactionMaster();
		gbmNpsTransactionMaster.setSolId(gbmTransactionMasterDto.getSolId());
		gbmNpsTransactionMaster.setTranRefId(gbmTransactionMasterDto.getTranRefId());
		gbmNpsTransactionMaster.setTranId(gbmTransactionMasterDto.getTranId());
		gbmNpsTransactionMaster.setPartTranId(gbmTransactionMasterDto.getPartTranId());
		gbmNpsTransactionMaster.setSchemeCode(gbmTransactionMasterDto.getSchemeCode());
		gbmNpsTransactionMaster.setTranMode(gbmTransactionMasterDto.getTranMode());
		gbmNpsTransactionMaster.setTranType(gbmTransactionMasterDto.getTranType());
		gbmNpsTransactionMaster.setAcctNum(gbmTransactionMasterDto.getAcctNum());
		gbmNpsTransactionMaster.setIsDeleted(gbmTransactionMasterDto.getIsDeleted());
		gbmNpsTransactionMaster.setVerified(gbmTransactionMasterDto.getVerifiedStatus());
		gbmNpsTransactionMaster.setTranLeg(gbmTransactionMasterDto.getTranLeg());
		gbmNpsTransactionMaster.setAmount(gbmTransactionMasterDto.getAmount());
		gbmNpsTransactionMaster.setInterSolId(gbmTransactionMasterDto.getInterSolId());
		System.out.println("inter sol id in manager 2" + gbmTransactionMasterDto.getInterSolId());
		gbmNpsTransactionMaster.setTranDate(gbmTransactionMasterDto.getTranDate());
		gbmNpsTransactionMaster.setCreatedBy(gbmTransactionMasterDto.getCreatedBy());
		gbmNpsTransactionMaster.setCreatedDate(new Date());
		return gbmNpsTransactionMaster;
	}

	private GbmTransactionMasterDto mapTransactionModelToDto(final GbmTransactionMaster gbmTransactionMaster) {

		final GbmTransactionMasterDto gttDto = new GbmTransactionMasterDto();
		gttDto.setTranType(gbmTransactionMaster.getTranType());
		gttDto.setTranId(gbmTransactionMaster.getTranId());
		gttDto.setTranMode(gbmTransactionMaster.getTranMode());
		gttDto.setTranLeg(gbmTransactionMaster.getTranLeg());
		gttDto.setAmount(gbmTransactionMaster.getAmount());
		gttDto.setAcctNum(gbmTransactionMaster.getAcctNum());
		gttDto.setLastModifiedBy(gbmTransactionMaster.getLastModifiedBy());
		gttDto.setLastModifiedDate(gbmTransactionMaster.getLastModifiedDate());
		gttDto.setVerifiedStatus(gbmTransactionMaster.getVerified());
		gttDto.setIsDeleted(gbmTransactionMaster.getIsDeleted());
		gttDto.setTranRefId(gbmTransactionMaster.getTranRefId());
		gttDto.setTranDate(gbmTransactionMaster.getTranDate());
		gttDto.setInterSolId(gbmTransactionMaster.getInterSolId());
		gttDto.setSchemeCode(gbmTransactionMaster.getSchemeCode());

		return gttDto;
	}

	*/
/**
 * Modify.
 *
 * @param rejectionDto
 *            the rejection dto
 *//*

	public void modify(final NpsGbmRejectionDto rejectionDto) {

		final GbmTransactionMaster transactionMaster = new GbmTransactionMaster();
		transactionMaster.setAmount(rejectionDto.getNpsRegistration().getTotalAmount());
		transactionMaster.setRemarks(rejectionDto.getRejectionHandlingDto().getReason());
		transactionMaster.setLastModifiedBy(UserAccountUtils.getCurrentUsername());
		transactionMaster.setLastModifiedDate(new Date());
		transactionMaster.setTranType(rejectionDto.getNpsTransaction().getTranType());
		transactionMaster.setTranMode(rejectionDto.getNpsTransaction().getTranMode());
		transactionMaster.setTranId(rejectionDto.getNpsTransaction().getTranId());
		transactionMaster.setSolId(rejectionDto.getNpsTransaction().getSolId());
		transactionMaster.setSchemeCode(rejectionDto.getNpsTransaction().getSchemeCode());
		transactionMaster.setTranDate(rejectionDto.getNpsTransaction().getTranDate());
		transactionMaster.setInterSolId(rejectionDto.getNpsTransaction().getInterSolId());
		transactionMaster.setTranLeg(CommonConstants.TRAN_LEG_DEBIT);
		this.gbmTransactionDao.modifyRejection(transactionMaster);
		transactionMaster.setTranLeg(CommonConstants.TRAN_LEG_CREDIT);
		transactionMaster.setAcctNum(rejectionDto.getRejectionHandlingDto().getAccountNumber());
		this.gbmTransactionDao.modifyRejection(transactionMaster);

		final NpsGbmRejectionHandlingMaster rejectionHandlingMaster = new NpsGbmRejectionHandlingMaster();
		rejectionHandlingMaster.setReason(rejectionDto.getRejectionHandlingDto().getReason());
		rejectionHandlingMaster.setModeOfPayment(rejectionDto.getRejectionHandlingDto().getModeOfPayment());
		rejectionHandlingMaster.setAccountNumber(rejectionDto.getRejectionHandlingDto().getAccountNumber());
		rejectionHandlingMaster.setReceiptNumber(rejectionDto.getRejectionHandlingDto().getReceiptNumber());
		rejectionHandlingMaster.setSolId(rejectionDto.getRejectionHandlingDto().getSolId());
		rejectionHandlingMaster.setIsDeleted(CommonConstants.NO);
		rejectionHandlingMaster.setVerifiedStatus(CommonConstants.NO);
		rejectionHandlingMaster.setLastModifiedBy(UserAccountUtils.getCurrentUsername());
		rejectionHandlingMaster.setLastModifiedDate(new Date());
		this.npsRejectionDao.modify(rejectionHandlingMaster);

	}

	*/
/**
 * Save transaction details.
 *
 * @paramgbmTransactionMasterDto
 *            the gbm transaction master dto
 * @paramsolDateDto
 *            the sol date dto
 *//*

	private void saveTransactionDetails(final NpsGbmRejectionDto npsRejectionDto) {
		final GbmTransactionMasterDto gbmTransactionMasterDto = new GbmTransactionMasterDto();
		final SolDateDto solDateDto = npsRejectionDto.getSolDateDto();
		final String tranId = this.gbmTransactionDao.getNpsTranId();
		final Date solDate = solDateDto.getSolDate();
		final String solDateString = new SimpleDateFormat("ddMMyyyy").format(solDate);
		final String tranRefId = this.gbmTransactionDao.getNpsTranRefId(npsRejectionDto.getNpsRegistration().getSolId(),
				solDateString, tranId);
		final String tranRefId1 = this.gbmTransactionDao
				.getNpsNextTranRefId(npsRejectionDto.getNpsRegistration().getSolId(), tranRefId, tranId);
		gbmTransactionMasterDto.setSolId(npsRejectionDto.getNpsRegistration().getSolId());
		gbmTransactionMasterDto.setTranId(tranId);
		gbmTransactionMasterDto.setSchemeCode(SchemeCodes.NPS.getSchemeCode());
		gbmTransactionMasterDto.setTranMode(TransactionType.TRANSFER.getTranTypeKey());
		gbmTransactionMasterDto.setTranType(CommonConstants.TRAN_TYPE);
		gbmTransactionMasterDto.setTranDate(solDate);
		gbmTransactionMasterDto.setTranLeg(CommonConstants.TRAN_LEG_CREDIT);
		gbmTransactionMasterDto.setIsDeleted(CommonConstants.NO);
		gbmTransactionMasterDto.setVerifiedStatus(CommonConstants.NO);
		gbmTransactionMasterDto.setCreatedBy(UserAccountUtils.getCurrentUsername());
		gbmTransactionMasterDto.setTranDate(solDate);
		gbmTransactionMasterDto.setAmount(npsRejectionDto.getNpsRegistration().getTotalAmount());
		gbmTransactionMasterDto.setInterSolId(npsRejectionDto.getNpsTransaction().getInterSolId());
		final GbmTransactionMaster creditTransaction = this.mapTransactionDtoToModel(gbmTransactionMasterDto);
		final GbmTransactionMaster debitTransaction = this.mapTransactionDtoToModel(gbmTransactionMasterDto);
		creditTransaction.setTranRefId(tranRefId);
		creditTransaction.setPartTranId(CommonConstants.FIRST_PART_TRAN_NUM);
		creditTransaction.setAcctNum(npsRejectionDto.getRejectionHandlingDto().getAccountNumber());
		this.gbmTransactionDao.createRejection(creditTransaction);
		debitTransaction.setTranLeg(CommonConstants.TRAN_LEG_DEBIT);
		gbmTransactionMasterDto.setTranRefId(tranRefId1);
		npsRejectionDto.setNpsTransaction(gbmTransactionMasterDto);
		debitTransaction.setTranRefId(tranRefId1);
		debitTransaction.setPartTranId(CommonConstants.SECOND_PART_TRAN_NUM);
		debitTransaction.setAcctNum(this.npsRejectionDao
				.getNPSRegAccount(npsRejectionDto.getNpsRegistration().getSolId(), LevelCodes.POP_SP.getLevelCode()));
		this.gbmTransactionDao.createRejection(debitTransaction);

	}

	public void setGbmNpsRegistrationDao(final GbmNpsRegistrationDao gbmNpsRegistrationDao) {
		this.gbmNpsRegistrationDao = gbmNpsRegistrationDao;
	}

	*/
/**
 * Sets the gbm transaction dao.
 *
 * @param gbmTransactionDao
 *            the new gbm transaction dao
 *//*

	public void setGbmTransactionDao(final GbmTransactionDao gbmTransactionDao) {
		this.gbmTransactionDao = gbmTransactionDao;
	}

	*/
/**
 * Sets the gbm unverified operation dao.
 *
 * @param gbmUnverifiedOperationDao
 *            the new gbm unverified operation dao
 *//*

	public void setGbmUnverifiedOperationDao(final GbmUnverifiedOperationDao gbmUnverifiedOperationDao) {
		this.gbmUnverifiedOperationDao = gbmUnverifiedOperationDao;
	}

	*/
/**
 * Sets the nps corporate receipt number dao.
 *
 * @param npsCorporateReceiptNumberDao
 *            the new nps corporate receipt number dao
 *//*

	public void setNpsCorporateReceiptNumberDao(final NpsCorporateReceiptNumberDao npsCorporateReceiptNumberDao) {
		this.npsCorporateReceiptNumberDao = npsCorporateReceiptNumberDao;
	}

	*/
/**
 * Sets the nps eod dao.
 *
 * @param npsEodDao
 *            the new nps eod dao
 *//*

	public void setNpsEodDao(final NpsEodDao npsEodDao) {
		this.npsEodDao = npsEodDao;
	}

	*/
/**
 * Sets the nps rejection dao.
 *
 * @param npsRejectionDao
 *            the new nps rejection dao
 *//*

	public void setNpsRejectionDao(final NpsRejectionDao npsRejectionDao) {
		this.npsRejectionDao = npsRejectionDao;
	}

	public void setUnlockRecordsDao(final UnlockRecordsDao unlockRecordsDao) {
		this.unlockRecordsDao = unlockRecordsDao;
	}

	public void verify(final NpsGbmRejectionDto rejectionDto) {
		this.npsRejectionDao.verify(rejectionDto.getRejectionHandlingDto().getTranRefId());
		final GbmNpsRegistrationMaster registrationMaster = new GbmNpsRegistrationMaster();
		registrationMaster.setId(rejectionDto.getNpsRegistration().getId());
		registrationMaster.setLastModifiedBy(UserAccountUtils.getCurrentUsername());
		this.gbmNpsRegistrationDao.delete(registrationMaster);
		final GbmUnverifiedOperations unverifiedMaster = new GbmUnverifiedOperations();

		unverifiedMaster.setSolId(rejectionDto.getNpsRegistration().getSolId());
		unverifiedMaster.setSchCode(rejectionDto.getNpsTransaction().getSchemeCode());
		unverifiedMaster.setTranDate(rejectionDto.getNpsRegistration().getTranDate());
		unverifiedMaster.setVerifiedStatus(CommonConstants.YES);
		unverifiedMaster.setTranId(rejectionDto.getNpsTransaction().getTranId());
		this.gbmUnverifiedOperationDao.updateCbsUnverifiedOperation(unverifiedMaster);

	}

}
*/
