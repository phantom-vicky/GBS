package com.isl.gbm.cbs.integration.facade.impl;
import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.StateMasterDTO;
import com.isl.gbm.cbs.integration.facade.StateFacade;
import com.isl.gbm.cbs.integration.parser.StateParser;
import com.isl.gbm.cbs.integration.service.StateMasterService;
import com.isl.gbm.cbs.integration.validator.StateValidator;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class StateFacadeImpl implements StateFacade {

    @Autowired
    StateValidator stateValidator;

    @Autowired
    StateParser stateParser;

    @Autowired
    StateMasterService stateMasterService;

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:ADD")
    public ResponseDataDTO save(final MultipartFile file) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        List<StateMasterDTO> stateMasterDTOList;
        try {
            stateMasterDTOList = this.stateParser.parseFile1(file);
            if(null!=stateMasterDTOList&&stateMasterDTOList.isEmpty()){
                responseDataDTO.setMessage("File is Empty: ");
                return responseDataDTO;
            }
        } catch (final DuplicateRecordException | InvalidDataException e) {
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        }catch(Exception e){
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        }
        return this.stateMasterService.save(stateMasterDTOList);
    }

    private ResponseEntity<ResponseDataDTO> buildErrorResponse(final String message,
                                                               final Map<String, String> errorMessages) {
        final ResponseDataDTO errorDto = this.buildResponse(message);
        return new ResponseEntity<>(errorDto, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ResponseDataDTO buildResponse(final String message) {
        final ResponseDataDTO dataDto = new ResponseDataDTO();
        dataDto.setMessage(message);
        return dataDto;
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:ENQUIRY")
    public ResponseDataDTO getInquiryDetails(StateMasterDTO stateMasterDTO) {
        return this.stateMasterService.getInquiryDetails(stateMasterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:VERIFY")
    public ResponseDataDTO getVerifyDetails(StateMasterDTO stateMasterDTO) {
        return this.stateMasterService.getVerifyDetails(stateMasterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:VERIFY")
    public ResponseDataDTO verifySubmit() {
        return this.stateMasterService.verifySubmit();
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:VERIFY")
    public ResponseDataDTO deleteSubmit(StateMasterDTO stateMasterDTO) {
        return this.stateMasterService.deleteSubmit(stateMasterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:ADD")
    public ResponseDataDTO dataExistsCheck() {
        return this.stateMasterService.dataExistsCheck();
    }

    @Override
    public ResponseEntity<ResponseDataDTO> downloadPincode() throws IOException, InvalidFormatException {
        return this.stateMasterService.downloadPincode();
    }

}
