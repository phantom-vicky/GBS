package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondInwardOutwardDTO;

public interface BondInwardOutwardFacade {

    
    public ResponseDataDTO getAddInRefNo(BondInwardOutwardDTO bondInwardOutwardDTO);

    public ResponseDataDTO addOutward(BondInwardOutwardDTO bondInwardOutwardDTO);
    public ResponseDataDTO rejectOutward(BondInwardOutwardDTO bondInwardOutwardDTO);
    public ResponseDataDTO addInward(BondInwardOutwardDTO bondInwardOutwardDTO);

    public ResponseDataDTO getPanNoTor(BondInwardOutwardDTO bondInwardOutwardDTO);

    public ResponseDataDTO inrRefNum(BondInwardOutwardDTO bondInwardOutwardDTO);
    public ResponseDataDTO upload(BondInwardOutwardDTO bondInwardOutwardDTO);
    public ResponseDataDTO download(BondInwardOutwardDTO bondInwardOutwardDTO);

    public ResponseDataDTO getBlaNoTor(BondInwardOutwardDTO bondInwardOutwardDTO);

    public ResponseDataDTO getBlaSubsIdTor(BondInwardOutwardDTO bondInwardOutwardDTO);

    public ResponseDataDTO delete(BondInwardOutwardDTO bondInwardOutwardDTO);


   public ResponseDataDTO tranValidation(BondInwardOutwardDTO bondInwardOutwardDTO);

}
