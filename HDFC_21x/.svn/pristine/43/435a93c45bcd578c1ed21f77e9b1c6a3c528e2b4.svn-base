package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.CommonDataDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondLedgerKartaBO;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.RbiBondLedgerKartaDTO;
import com.isl.gbm.rbibond.service.RbiBondLedgerKartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RbiBondLedgerKartaServiceImpl implements RbiBondLedgerKartaService {

    @Autowired
    RbiBondLedgerKartaBO rbiBondLedgerKartaBo;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;


    ResponseDataDTO responseDataDTO = new ResponseDataDTO();
    @Override
    public ResponseDataDTO getTorchPan(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaBo.getTorchPan(rbiBondLedgerKartaDTO);
    }


    @Override
    public ResponseDataDTO getModifyTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {



        if(rbiBondLedgerKartaDTO.getFlag()!=null &&rbiBondLedgerKartaDTO.getFlag().equalsIgnoreCase("D")) {
            if (rbiBondLedgerKartaDTO.getSchemeCode() == null || rbiBondLedgerKartaDTO.getSchemeCode().trim().isEmpty() || !rbiBondLedgerKartaDTO.getSchemeCode().matches("\\d+")) {
                responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }

            else if (rbiBondLedgerKartaDTO.getNameOfTheBondHolder() != null && !rbiBondLedgerKartaDTO.getNameOfTheBondHolder().trim().isEmpty() &&!rbiBondLedgerKartaDTO.getNameOfTheBondHolder().matches("[A-Za-z ]+")) {

                responseDataDTO.setMessage("Name must contain only characters");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }

            else if (rbiBondLedgerKartaDTO.getPanNo() != null &&! rbiBondLedgerKartaDTO.getPanNo().trim().isEmpty() && !rbiBondLedgerKartaDTO.getPanNo().matches("[A-Za-z0-9]+") || rbiBondLedgerKartaDTO.getPanNo().length() > 10) {

                responseDataDTO.setMessage("PAN must contain only characters and digits and length should not exceed 10");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            else if (rbiBondLedgerKartaDTO.getBlaNo() == null || rbiBondLedgerKartaDTO.getBlaNo().trim().isEmpty() || !rbiBondLedgerKartaDTO.getBlaNo().matches("[A-Za-z0-9]+")) {

                responseDataDTO.setMessage("Bond Ledger Account Number should not be blank and must contain only characters and digits");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        return rbiBondLedgerKartaBo.getModifyTorchBla(rbiBondLedgerKartaDTO);
    }

    @Override
    public ResponseDataDTO getInquiryTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {


        if(rbiBondLedgerKartaDTO.getFlag()!=null &&rbiBondLedgerKartaDTO.getFlag().equalsIgnoreCase("B")) {
            if (rbiBondLedgerKartaDTO.getSchemeCode() == null || rbiBondLedgerKartaDTO.getSchemeCode().trim().isEmpty() || !rbiBondLedgerKartaDTO.getSchemeCode().matches("\\d+")) {
                responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        return rbiBondLedgerKartaBo.getInquiryTorchBla(rbiBondLedgerKartaDTO);
    }

    @Override
    public ResponseDataDTO getModifyBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {

        if(rbiBondLedgerKartaDTO.getFlag()!=null &&rbiBondLedgerKartaDTO.getFlag().equalsIgnoreCase("B")) {
            if (rbiBondLedgerKartaDTO.getSchemeCode() == null || rbiBondLedgerKartaDTO.getSchemeCode().trim().isEmpty() || !rbiBondLedgerKartaDTO.getSchemeCode().matches("\\d+")) {
                responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        return rbiBondLedgerKartaBo.getModifyBla(rbiBondLedgerKartaDTO);
    }

    @Override
    public ResponseDataDTO getVerDelTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaBo.getVerDelTorchBla(rbiBondLedgerKartaDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaBo.modifySelect(rbiBondLedgerKartaDTO);
    }

    @Override
    public ResponseDataDTO addModify(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {



        if (rbiBondLedgerKartaDTO.getSchemeCode() == null || rbiBondLedgerKartaDTO.getSchemeCode().trim().isEmpty() || !rbiBondLedgerKartaDTO.getSchemeCode().matches("\\d+")) {
            responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
         if (rbiBondLedgerKartaDTO.getBlaNo() == null || rbiBondLedgerKartaDTO.getBlaNo().trim().isEmpty() || !rbiBondLedgerKartaDTO.getBlaNo().matches("[A-Za-z0-9]+")) {

            responseDataDTO.setMessage("Bond Ledger Account Number should not be blank and must contain only characters and digits");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (rbiBondLedgerKartaDTO.getKartaAddDtoList() != null
                && rbiBondLedgerKartaDTO.getKartaAddDtoList().size() >= 1) {

            List<List<ColumnValueDTO>> columnList =
                    rbiBondLedgerKartaDTO.getKartaAddDtoList();

            for (List<ColumnValueDTO> innerList : columnList) {
                for (ColumnValueDTO rrr : innerList) {

                    if ("C004".equals(rrr.getColName())) {
                        if (rrr.getValue() == null
                                || rrr.getValue().trim().isEmpty()
                                || !rrr.getValue().trim()
                                .matches("[A-Za-z]+(\\s+[A-Za-z]+)*")) {

                            responseDataDTO.setMessage(
                                    "Coparceners Name should not be blank and must contain only characters");
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }
                    }
                }
            }
        }

        if (rbiBondLedgerKartaDTO.getKartaModifyDtoList() != null
                && rbiBondLedgerKartaDTO.getKartaModifyDtoList().size() >= 1) {

            List<List<ColumnValueDTO>> columnList =
                    rbiBondLedgerKartaDTO.getKartaModifyDtoList();

            for (List<ColumnValueDTO> innerList : columnList) {

                for (ColumnValueDTO rrr : innerList) {

                    if ("C004".equals(rrr.getColName())) {
                        if (rrr.getValue() == null
                                || rrr.getValue().trim().isEmpty()
                                || !rrr.getValue().trim()
                                .matches("[A-Za-z]+(\\s+[A-Za-z]+)*")) {

                            responseDataDTO.setMessage(
                                    "Coparceners Name should not be blank and must contain only characters");
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }
                    }

                }
            }
        }

        if (rbiBondLedgerKartaDTO.getKartaDelDtoList() != null
                && rbiBondLedgerKartaDTO.getKartaDelDtoList().size() >= 1) {

            List<List<ColumnValueDTO>> columnList =
                    rbiBondLedgerKartaDTO.getKartaDelDtoList();

            for (List<ColumnValueDTO> innerList : columnList) {

                for (ColumnValueDTO rrr : innerList) {

                    if ("C004".equals(rrr.getColName())) {
                        if (rrr.getValue() == null
                                || rrr.getValue().trim().isEmpty()
                                || !rrr.getValue().trim()
                                .matches("[A-Za-z]+(\\s+[A-Za-z]+)*")) {

                            responseDataDTO.setMessage(
                                    "Coparceners Name should not be blank and must contain only characters");
                            responseDataDTO.setStatusCode("0");
                            return responseDataDTO;
                        }
                    }

                }
            }
        }

        return rbiBondLedgerKartaBo.addModify(rbiBondLedgerKartaDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {



        if(rbiBondLedgerKartaDTO.getFlag()!=null &&rbiBondLedgerKartaDTO.getFlag().equalsIgnoreCase("D")) {
            if (rbiBondLedgerKartaDTO.getSchemeCode() == null || rbiBondLedgerKartaDTO.getSchemeCode().trim().isEmpty() || !rbiBondLedgerKartaDTO.getSchemeCode().matches("\\d+")) {
                responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }

            else if (rbiBondLedgerKartaDTO.getNameOfTheBondHolder() != null && !rbiBondLedgerKartaDTO.getNameOfTheBondHolder().trim().isEmpty() &&!rbiBondLedgerKartaDTO.getNameOfTheBondHolder().matches("[A-Za-z ]+")) {

                responseDataDTO.setMessage("Name must contain only characters");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }

            else if (rbiBondLedgerKartaDTO.getPanNo() != null &&! rbiBondLedgerKartaDTO.getPanNo().trim().isEmpty() && !rbiBondLedgerKartaDTO.getPanNo().matches("[A-Za-z0-9]+") || rbiBondLedgerKartaDTO.getPanNo().length() > 10) {

                responseDataDTO.setMessage("PAN must contain only characters and digits and length should not exceed 10");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            else if (rbiBondLedgerKartaDTO.getBlaNo() == null || rbiBondLedgerKartaDTO.getBlaNo().trim().isEmpty() || !rbiBondLedgerKartaDTO.getBlaNo().matches("[A-Za-z0-9]+")) {

                responseDataDTO.setMessage("Bond Ledger Account Number should not be blank and must contain only characters and digits");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }




        }
        return rbiBondLedgerKartaBo.inquiry(rbiBondLedgerKartaDTO);
    }

    @Override
    public ResponseDataDTO inquirySelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaBo.inquirySelect(rbiBondLedgerKartaDTO);
    }

    @Override
    public ResponseDataDTO inquiryPan(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaBo.inquiryPan(rbiBondLedgerKartaDTO);
    }

    @Override
    public ResponseDataDTO verDelSelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {

        if(rbiBondLedgerKartaDTO.getFlag()!=null &&rbiBondLedgerKartaDTO.getFlag().equalsIgnoreCase("B")) {
            if (rbiBondLedgerKartaDTO.getSchemeCode() == null || rbiBondLedgerKartaDTO.getSchemeCode().trim().isEmpty() || !rbiBondLedgerKartaDTO.getSchemeCode().matches("\\d+")) {
                responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }

        if(rbiBondLedgerKartaDTO.getFlag()!=null &&rbiBondLedgerKartaDTO.getFlag().equalsIgnoreCase("D")) {
            if (rbiBondLedgerKartaDTO.getSchemeCode() == null || rbiBondLedgerKartaDTO.getSchemeCode().trim().isEmpty() || !rbiBondLedgerKartaDTO.getSchemeCode().matches("\\d+")) {
                responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            else if (rbiBondLedgerKartaDTO.getBlaNo() == null || rbiBondLedgerKartaDTO.getBlaNo().trim().isEmpty() || !rbiBondLedgerKartaDTO.getBlaNo().matches("[A-Za-z0-9]+")) {

                responseDataDTO.setMessage("Bond Ledger Account Number should not be blank and must contain only characters and digits");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        return rbiBondLedgerKartaBo.verDelSelect(rbiBondLedgerKartaDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        if(rbiBondLedgerKartaDTO.getFlag()!=null &&rbiBondLedgerKartaDTO.getFlag().equalsIgnoreCase("D")) {
            if (rbiBondLedgerKartaDTO.getSchemeCode() == null || rbiBondLedgerKartaDTO.getSchemeCode().trim().isEmpty() || !rbiBondLedgerKartaDTO.getSchemeCode().matches("\\d+")) {
                responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            else if (rbiBondLedgerKartaDTO.getBlaNo() == null || rbiBondLedgerKartaDTO.getBlaNo().trim().isEmpty() || !rbiBondLedgerKartaDTO.getBlaNo().matches("[A-Za-z0-9]+")) {

                responseDataDTO.setMessage("Bond Ledger Account Number should not be blank and must contain only characters and digits");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        return rbiBondLedgerKartaBo.verify(rbiBondLedgerKartaDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {



        if (rbiBondLedgerKartaDTO.getSchemeCode() == null || rbiBondLedgerKartaDTO.getSchemeCode().trim().isEmpty() || !rbiBondLedgerKartaDTO.getSchemeCode().matches("\\d+")) {
            responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (rbiBondLedgerKartaDTO.getBlaNo() == null || rbiBondLedgerKartaDTO.getBlaNo().trim().isEmpty() || !rbiBondLedgerKartaDTO.getBlaNo().matches("[A-Za-z0-9]+")) {

            responseDataDTO.setMessage("Bond Ledger Account Number should not be blank and must contain only characters and digits");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }


        if (rbiBondLedgerKartaDTO.getRemarks()== null || rbiBondLedgerKartaDTO.getRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("Remarks should not be blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(rbiBondLedgerKartaDTO.getRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        return rbiBondLedgerKartaBo.delete(rbiBondLedgerKartaDTO);
    }
}
