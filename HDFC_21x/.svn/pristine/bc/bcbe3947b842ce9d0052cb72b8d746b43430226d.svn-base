package com.isl.gbm.rbibond.jobs;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dto.BondInterestPaymentFileDTO;
import com.isl.gbm.rbibond.dto.BondInterestPaymentFileDetailsDTO;
import com.isl.gbm.rbibond.dto.BondInterestPaymentFileHeaderDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentFileUtility {

    public static BondInterestPaymentFileHeaderDTO headerRowParser(String line) {
        BondInterestPaymentFileHeaderDTO headerDTO = new BondInterestPaymentFileHeaderDTO();
        headerDTO.setAchTranCode(line.substring(0, 2));
        headerDTO.setControl1(line.substring(2, 9));
        headerDTO.setUserName(line.substring(9, 49));
        headerDTO.setControl2(line.substring(49, 63));
        headerDTO.setAchFileNo(line.substring(63, 72));
        headerDTO.setControl3(line.substring(72, 81));
        headerDTO.setControl4(line.substring(81, 96));
        headerDTO.setLedgerFolioNo(line.substring(96, 99));
        headerDTO.setUserDefLimit(line.substring(99, 112));
        headerDTO.setTotalAmt(line.substring(112, 125));
        headerDTO.setSettlementDate(line.substring(125, 133));
        headerDTO.setItemSeqNo(line.substring(133, 143));
        headerDTO.setCheckSum(line.substring(143, 153));
        headerDTO.setFiller1(line.substring(153, 156));
        headerDTO.setUserNo(line.substring(156, 174));
        headerDTO.setUserRef(line.substring(174, 192));
        headerDTO.setSponsorBankIFSCMICR(line.substring(192, 203));
        headerDTO.setUserBankAccNo(line.substring(203, 238));
        headerDTO.setTotalItems(line.substring(238, 247));
        headerDTO.setSettlementCycle(line.substring(247, 249));
        headerDTO.setFiller2(line.substring(249, 305));
        return headerDTO;
    }

    public static BondInterestPaymentFileDetailsDTO detailsRowParser(String line) {
        BondInterestPaymentFileDetailsDTO detailsDTO = new BondInterestPaymentFileDetailsDTO();
        detailsDTO.setAchTranCode(line.substring(0, 2));
        detailsDTO.setControl1(line.substring(2, 11));
        detailsDTO.setDestAccType(line.substring(11, 13));
        detailsDTO.setLedgerFolioNo(line.substring(13, 16));
        detailsDTO.setControl2(line.substring(16, 31));
        detailsDTO.setBeneficiaryAccName(line.substring(31, 71));
        detailsDTO.setControl3(line.substring(71, 80));
        detailsDTO.setControl4(line.substring(80, 87));
        detailsDTO.setUserName(line.substring(87, 107));
        detailsDTO.setControl5(line.substring(107, 120));
        detailsDTO.setAmount(line.substring(120, 133));
        detailsDTO.setAchItemSeqNo(line.substring(133, 143));
        detailsDTO.setChecksum(line.substring(143, 153));
        detailsDTO.setFlagForSuccess(line.substring(153, 154));
        detailsDTO.setReasonCode(line.substring(154, 156));
        detailsDTO.setDestBankIFSCMICR(line.substring(156, 167));
        detailsDTO.setBeneficiaryBankAccNo(line.substring(167, 202));
        detailsDTO.setSponsorBankIFSCMICR(line.substring(202, 213));
        detailsDTO.setUserNo(line.substring(213, 231));
        detailsDTO.setTransRef(line.substring(231, 261).trim());
        detailsDTO.setProductType(line.substring(261, 264));
        detailsDTO.setBeneficiaryAadharNo(line.substring(264, 278));
        detailsDTO.setuMRN(line.substring(278, 298));
        detailsDTO.setFiller(line.substring(298, 305));
        return detailsDTO;
    }

    public static ResponseDataDTO parseTextFile(final InputStream inputStream) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondInterestPaymentFileDTO paymentFileDTO = new BondInterestPaymentFileDTO();
        List<BondInterestPaymentFileDetailsDTO> detailsDTOList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> listOfRows = bufferedReader.lines().collect(Collectors.toList());
            List<String> rows = listOfRows.stream().filter(row -> row.length() == 306)
                    .collect(Collectors.toList());
            if ((!listOfRows.isEmpty() && !rows.isEmpty()) && (listOfRows.size() == rows.size())) {
                paymentFileDTO.setFileHeaderDTO(headerRowParser(listOfRows.get(0)));
                listOfRows.stream().skip(1).forEach(
                        item -> detailsDTOList.add(detailsRowParser(item))
                );
                paymentFileDTO.setFileDetailsDTO(detailsDTOList);
                responseDataDTO.setResult(paymentFileDTO);
                responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                return responseDataDTO;
            } else {
                responseDataDTO.setMessage("Length of the Rows are less than 306 Character");
                return responseDataDTO;
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
        }
        return responseDataDTO;
    }

}
