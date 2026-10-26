package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondNomineeContinuationBo;
import com.isl.gbm.rbibond.dao.RbiBondNomineeContinuationDao;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureOnMaturDeathDTO;
import com.isl.gbm.rbibond.dto.RbiBondNomineeContinuationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Business Object for handling RBI Bond Nominee Continuation operations.
 *
 * @author Sneha Sampath
 * @date 2025-02-05
 */
@Component
public class RbiBondNomineeContinuationBoImpl implements RbiBondNomineeContinuationBo {


    @Autowired
    RbiBondNomineeContinuationDao rbiBondNomineeContinuationDao;


    @Override
    public ResponseDataDTO getAddDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationDao.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }
    @Override
    public ResponseDataDTO getAddValidation(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationDao.getAddValidation(rbiBondClosureBeforeMaturDTO);
    }
    @Override
    public ResponseDataDTO add(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationDao.add(rbiBondClosureBeforeMaturDTO);
    }
    @Override
    public ResponseDataDTO getInqDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationDao.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }
    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationDao.getVerDelDetails(rbiBondClosureBeforeMaturDTO);
    }
    @Override
    public ResponseDataDTO getWboBranchDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationDao.getWboBranchDetails(rbiBondClosureBeforeMaturDTO);
    }
    @Override
    public ResponseDataDTO delete(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationDao.delete(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationDao.verify(rbiBondClosureBeforeMaturDTO);

    }
    @Override
    public ResponseDataDTO updatePANStatus(PanValidationRetriggerResponseDTO responseDTO) {
        return this.rbiBondNomineeContinuationDao.updatePANStatus(responseDTO);
    }
    @Override
    public ResponseDataDTO getPanValidation(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationDao.getPanValidation(rbiBondClosureBeforeMaturDTO);
    }
}
