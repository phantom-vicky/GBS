package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureOnMaturDeathDTO;
import com.isl.gbm.rbibond.dto.RbiBondNomineeContinuationDTO;

/**
 * Interface for handling RBI Bond Nominee Continuation operations.
 *
 * @author Sneha Sampath
 * @date 2025-02-05
 */
public interface RbiBondNomineeContinuationDao {


    ResponseDataDTO getAddDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO getAddValidation(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO);
    ResponseDataDTO add(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO);
    ResponseDataDTO getInqDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO getVerDelDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO);
    ResponseDataDTO getWboBranchDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO delete(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO verify(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO);
    ResponseDataDTO updatePANStatus(PanValidationRetriggerResponseDTO responseDTO);

    ResponseDataDTO getPanValidation(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO);

}
