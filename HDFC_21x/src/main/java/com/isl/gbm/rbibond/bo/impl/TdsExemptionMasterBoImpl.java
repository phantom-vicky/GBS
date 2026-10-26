package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.TdsExemptionMasterBo;
import com.isl.gbm.rbibond.dao.TdsExemptionMasterDAO;
import com.isl.gbm.rbibond.dto.BondTaxExcemptionMasterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component

public class TdsExemptionMasterBoImpl implements TdsExemptionMasterBo {

    @Autowired
    TdsExemptionMasterDAO tdsExemptionMasterDAO;

    @Override
    public ResponseDataDTO getAddPanNoDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.getAddPanNoDetails(bondTaxExcemptionMasterDTO);
    }


    @Override
    public ResponseDataDTO addCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
       /* if (Integer.parseInt(bondTaxExcemptionMasterDTO.getAggAmtIncome()) <0) {
            responseDataDTO.setMessage("Aggregate amount of income Should Not Be Negative");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (Integer.parseInt(bondTaxExcemptionMasterDTO.getEstimatedIncomeTaxDed()) <0) {
            responseDataDTO.setMessage("Estimated Income Tax Declaration Should Not Be Negative");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (Integer.parseInt(bondTaxExcemptionMasterDTO.getEstimatedTotalIncomeTaxDed()) <0) {
            responseDataDTO.setMessage("Estimated Total Income Tax Declaration Should Not Be Negative");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }*/
        return tdsExemptionMasterDAO.addCutomerDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
    public ResponseDataDTO getHolderAge(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.getHolderAge(bondTaxExcemptionMasterDTO);
    }

    @Override
    public ResponseDataDTO getModifyPanNoDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.getModifyPanNoDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
    public ResponseDataDTO getModifyDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.getModifyDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
    public ResponseDataDTO modifyCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        /*ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        for (ColumnValueDTO col : bondTaxExcemptionMasterDTO.getTaxExcemptionModifyDtoList()) {
            String colName = col.getColName();
            String value = col.getValue();
            if (("C013".equals(colName) || "C014".equals(colName) || "C015".equals(colName))
                    && value != null && !"null".equalsIgnoreCase(value)) {
                try {
                    int num = Integer.parseInt(value.trim());
                    if (num < 0) {
                        responseDataDTO.setMessage("Amount must not be negative");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                } catch (NumberFormatException e) {
                    responseDataDTO.setMessage("Invalid format Amount " );
                    responseDataDTO.setStatusCode("0");
                    return responseDataDTO;
                }
            }
        }*/

        return tdsExemptionMasterDAO.modifyCutomerDetails(bondTaxExcemptionMasterDTO);
    }


    @Override
    public ResponseDataDTO getVerDelPanDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.getVerDelPanDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
    public ResponseDataDTO getVerifyDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.getVerifyDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
    public ResponseDataDTO verifyCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.verifyCutomerDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
    public ResponseDataDTO getDeleteDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.getDeleteDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
    public ResponseDataDTO deleteCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.deleteCutomerDetails(bondTaxExcemptionMasterDTO);
    }


    @Override
    public ResponseDataDTO getVersionNoDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.getVersionNoDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
    public ResponseDataDTO getInquiryPanDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.getInquiryPanDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
    public ResponseDataDTO getInquiryDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.getInquiryDetails(bondTaxExcemptionMasterDTO);
    }


    @Override
    public ResponseDataDTO getCertNumber(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterDAO.getCertNumber(bondTaxExcemptionMasterDTO);
    }


    @Override
    public String getFinancialYear(final Date solDate) {
        return tdsExemptionMasterDAO.getFinancialYear(solDate);
    }


}
