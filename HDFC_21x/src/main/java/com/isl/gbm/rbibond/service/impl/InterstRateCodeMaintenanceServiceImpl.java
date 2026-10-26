package com.isl.gbm.rbibond.service.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.InterstRateCodeMaintenanceBO;
import com.isl.gbm.rbibond.dto.RateCodeMaintenanceDTO;
import com.isl.gbm.rbibond.service.InterstRateCodeMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterstRateCodeMaintenanceServiceImpl implements InterstRateCodeMaintenanceService {


    @Autowired
    private InterstRateCodeMaintenanceBO interstRateCodeMaintenanceBO;

      ResponseDataDTO responseDataDTO = new ResponseDataDTO();
    @Override
    public ResponseDataDTO add(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {

         if(rateCodeMaintenanceDTO.getFlag().equalsIgnoreCase("D")){

             if(rateCodeMaintenanceDTO.getSchemeCode()==null){
                 responseDataDTO.setMessage("SchemeName Should not be blank");
                 return responseDataDTO;
             }

             if(rateCodeMaintenanceDTO.getRateCodeMaintenance()==null || !rateCodeMaintenanceDTO.getRateCodeMaintenance().matches("^[A-Za-z1-9][A-Za-z0-9]{0,4}$")){
                 responseDataDTO.setMessage("Interest Rate Code must be alphanumeric and should not start with zero or be blank");
                 return responseDataDTO;
             }
             if(rateCodeMaintenanceDTO.getRateCodeMaintenanceDescription()==null || rateCodeMaintenanceDTO.getRateCodeMaintenanceDescription().isEmpty() || !rateCodeMaintenanceDTO.getRateCodeMaintenanceDescription().matches("^[A-Za-z0-9]{1,30}$")){
                 responseDataDTO.setMessage("Interest Rate Code Description Should not be blank");
                 return responseDataDTO;
             }

             if(rateCodeMaintenanceDTO.getEffFromDate()==null){
                 responseDataDTO.setMessage("Effective From Date Should not be blank");
                 return responseDataDTO;
             }


             if(rateCodeMaintenanceDTO.getFixedFloatingInterest()==null){
                 responseDataDTO.setMessage("Fixed Interest rate / Floating Interest Rate Should not be blank");
                 return responseDataDTO;
             }

             if(rateCodeMaintenanceDTO.getFixedFloatingInterest()!=null) {

                 if (rateCodeMaintenanceDTO.getFixedFloatingInterest().equalsIgnoreCase("Y")) {
                     if (rateCodeMaintenanceDTO.getCouponCode()==null||rateCodeMaintenanceDTO.getCouponCode().isEmpty() || !rateCodeMaintenanceDTO.getCouponCode().matches("^\\d+(\\.\\d{1,2})?$")){
                         responseDataDTO.setMessage("Coupon / Interest Rate for Bond (%) should not be blank");
                     return responseDataDTO;
                 }
             }

                 if (rateCodeMaintenanceDTO.getFixedFloatingInterest().equalsIgnoreCase("N")){


                     if (rateCodeMaintenanceDTO.getInterestRateNatSavCertiIn()==null||rateCodeMaintenanceDTO.getInterestRateNatSavCertiIn().isEmpty() || !rateCodeMaintenanceDTO.getInterestRateNatSavCertiIn().matches("^\\d+(\\.\\d{1,2})?$")){
                         responseDataDTO.setMessage("Interest Rate (%) on National Savings Certificate should not be blank");
                         return responseDataDTO;
                     }

                     if (rateCodeMaintenanceDTO.getSpreadRate()==null || rateCodeMaintenanceDTO.getSpreadRate().isEmpty() || !rateCodeMaintenanceDTO.getSpreadRate().matches("^\\d+(\\.\\d{1,2})?$")){
                         responseDataDTO.setMessage("Spread Rate (%) should not be blank");
                         return responseDataDTO;
                     }

                 }
             }

         }

        return interstRateCodeMaintenanceBO.add(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {

          if( rateCodeMaintenanceDTO.getFlag() != null &&rateCodeMaintenanceDTO.getFlag().equalsIgnoreCase("I")) {

              if (rateCodeMaintenanceDTO.getSchemeCode() == null || rateCodeMaintenanceDTO.getSchemeCode().isEmpty()){


                  responseDataDTO.setMessage("schmeCode should not be blank");
              return responseDataDTO;
          }

          }



        if( rateCodeMaintenanceDTO.getFlag().equalsIgnoreCase("D")) {

            if(rateCodeMaintenanceDTO.getSchemeCode()==null || rateCodeMaintenanceDTO.getSchemeCode().isEmpty()) {
                responseDataDTO.setMessage("schmeCode should not be blank");
                return responseDataDTO;

            }

            else if(rateCodeMaintenanceDTO.getInterestRateCode()==null || rateCodeMaintenanceDTO.getInterestRateCode().isEmpty()) {
                responseDataDTO.setMessage("Interest Rate Code should not be blank");
                return responseDataDTO;

            }

            else if(rateCodeMaintenanceDTO.getEffFromDate()==null || rateCodeMaintenanceDTO.getEffFromDate().isEmpty()) {
                responseDataDTO.setMessage("Effective From Date should not be blank");
                return responseDataDTO;

            }

        }
        return interstRateCodeMaintenanceBO.modifySelect(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modify(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {

        if(rateCodeMaintenanceDTO.getFixedFloatingInterest()!= null &&rateCodeMaintenanceDTO.getFixedFloatingInterest().equalsIgnoreCase("N")){



            if(rateCodeMaintenanceDTO.getInterestRateNatSavCertiIn()== null || rateCodeMaintenanceDTO.getInterestRateNatSavCertiIn().isEmpty() || !rateCodeMaintenanceDTO.getInterestRateNatSavCertiIn().matches("^\\d+(\\.\\d{1,2})?$")) {
                responseDataDTO.setMessage("Interest Rate (%) on National Savings Certificate should not be blank");
                return responseDataDTO;
            }


            else if(rateCodeMaintenanceDTO.getSpreadRate()== null ||rateCodeMaintenanceDTO.getSpreadRate().isEmpty() || !rateCodeMaintenanceDTO.getSpreadRate().matches("^\\d+(\\.\\d{1,2})?$")) {
                responseDataDTO.setMessage("Spread Rate (%) should not be blank");
                return responseDataDTO;
            }


            else if(rateCodeMaintenanceDTO.getCoupanInterestRateBond()== null||rateCodeMaintenanceDTO.getCoupanInterestRateBond().isEmpty() || !rateCodeMaintenanceDTO.getCoupanInterestRateBond().matches("^\\d+(\\.\\d{1,2})?$")) {
                responseDataDTO.setMessage("Coupon / Interest Rate for Bond (%) should not be blank");
                return responseDataDTO;
            }

        }


        if(rateCodeMaintenanceDTO.getFixedFloatingInterest()!= null &&rateCodeMaintenanceDTO.getFixedFloatingInterest().equalsIgnoreCase("Y")){

            if(rateCodeMaintenanceDTO.getCoupanInterestRateBond()== null ||rateCodeMaintenanceDTO.getCoupanInterestRateBond().isEmpty()) {
                responseDataDTO.setMessage("Coupon / Interest Rate for Bond (%) should not be blank");
                return responseDataDTO;
            }



        }


      return interstRateCodeMaintenanceBO.modify(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verify(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {


        if(rateCodeMaintenanceDTO.getFlag()!=null&&rateCodeMaintenanceDTO.getFlag().equalsIgnoreCase("D")){

            if(rateCodeMaintenanceDTO.getSchemeCode() == null || rateCodeMaintenanceDTO.getSchemeCode().isEmpty()){

                responseDataDTO.setMessage("SchemeCode Should not be blank");
                return responseDataDTO;
            }

            else if(rateCodeMaintenanceDTO.getInterestRateCode() == null || rateCodeMaintenanceDTO.getInterestRateCode().isEmpty()){

                responseDataDTO.setMessage("Interest Rate Code Should not be blank");
            }


        }


        return interstRateCodeMaintenanceBO.verify(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO unVerifySelect(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {


        if(rateCodeMaintenanceDTO.getFlag()!=null&&rateCodeMaintenanceDTO.getFlag().equalsIgnoreCase("I")){

            if(rateCodeMaintenanceDTO.getSchemeCode() == null || rateCodeMaintenanceDTO.getSchemeCode().isEmpty()){

                responseDataDTO.setMessage("SchemeCode Should not be blank");
                return responseDataDTO;
            }


        }


        if(rateCodeMaintenanceDTO.getFlag()!=null&&rateCodeMaintenanceDTO.getFlag().equalsIgnoreCase("D")){

            if(rateCodeMaintenanceDTO.getSchemeCode() == null || rateCodeMaintenanceDTO.getSchemeCode().isEmpty()){

                responseDataDTO.setMessage("SchemeCode Should not be blank");
                return responseDataDTO;
            }

            if (rateCodeMaintenanceDTO.getInterestRateCode() == null || rateCodeMaintenanceDTO.getInterestRateCode().isEmpty()) {

                responseDataDTO.setMessage("Interest Rate Code Should not be blank");
                return responseDataDTO;
            }


        }
        return interstRateCodeMaintenanceBO.unVerifySelect(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO delete(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {



        if(rateCodeMaintenanceDTO.getRejectionRemarks() == null || rateCodeMaintenanceDTO.getRejectionRemarks().isEmpty()){

            responseDataDTO.setMessage("Rejection Remarks Should not be blank");
            return responseDataDTO;
        }
        return interstRateCodeMaintenanceBO.delete(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {


        if(rateCodeMaintenanceDTO.getFlag() != null &&rateCodeMaintenanceDTO.getFlag().equalsIgnoreCase("V")) {
            if (rateCodeMaintenanceDTO.getSchemeCode() == null || rateCodeMaintenanceDTO.getSchemeCode().isEmpty()) {

                responseDataDTO.setMessage("SchemeCode Should not be blank");
                return responseDataDTO;
            }
        }

        else if(rateCodeMaintenanceDTO.getFlag() != null &&rateCodeMaintenanceDTO.getFlag().equalsIgnoreCase("V")) {

            if (rateCodeMaintenanceDTO.getInterestRateCode() == null || rateCodeMaintenanceDTO.getInterestRateCode().isEmpty()) {

                responseDataDTO.setMessage("Interest Rate Code Should not be blank");
                return responseDataDTO;
            }
        }



        else if(rateCodeMaintenanceDTO.getFlag() != null &&rateCodeMaintenanceDTO.getFlag().equalsIgnoreCase("D")) {



            if (rateCodeMaintenanceDTO.getSchemeCode() == null || rateCodeMaintenanceDTO.getSchemeCode().isEmpty()) {

                responseDataDTO.setMessage("SchemeCode Should not be blank");
                return responseDataDTO;
            }

            if (rateCodeMaintenanceDTO.getInterestRateCode() == null || rateCodeMaintenanceDTO.getInterestRateCode().isEmpty()) {

                responseDataDTO.setMessage("Interest Rate Code Should not be blank");
                return responseDataDTO;
            }

            if(rateCodeMaintenanceDTO.getVersionNo() == null || rateCodeMaintenanceDTO.getVersionNo().isEmpty()){

                responseDataDTO.setMessage("Version No Should not be blank");
                return responseDataDTO;
            }
        }


        return interstRateCodeMaintenanceBO.inquiry(rateCodeMaintenanceDTO);
    }
}
