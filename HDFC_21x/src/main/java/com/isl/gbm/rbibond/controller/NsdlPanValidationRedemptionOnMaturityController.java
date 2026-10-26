package com.isl.gbm.rbibond.controller;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.NsdlPanValidationRedemptionOnMaturityReqAndResDTO;
import com.isl.gbm.rbibond.facade.NsdlPanValidationRedemptionOnMaturityFacade;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("gbmultra/rbiBond/nsdlPan/validation")
public class NsdlPanValidationRedemptionOnMaturityController {
    @Autowired
    NsdlPanValidationRedemptionOnMaturityFacade nsdlPanValidationRedemptionOnMaturityFacade;

@PostMapping(path = "/redemptionOnMaturity/api")
public ResponseEntity<ResponseDataDTO> api(@RequestBody NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
    return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationRedemptionOnMaturityFacade.api(dto));
}
@RequestMapping(value = "/redemptionOnMaturity/upload", method = RequestMethod.POST)
public ResponseEntity<ResponseDataDTO> upload(@RequestPart("file") final MultipartFile file,
                                              @RequestPart NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto){
    return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationRedemptionOnMaturityFacade.upload(file,dto));
}
@PostMapping(path = "/redemptionOnMaturity/delete")
public ResponseEntity<ResponseDataDTO> delete(@RequestBody NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
    return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationRedemptionOnMaturityFacade.delete(dto));
}
@PostMapping(path = "/redemptionOnMaturity/getUploadModuleName")
public ResponseEntity<ResponseDataDTO> getUploadModuleName(
        @RequestBody NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
  return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationRedemptionOnMaturityFacade.getUploadModuleName(dto));
}
@PostMapping(path = "/redemptionOnMaturity/verify")
public ResponseEntity<ResponseDataDTO> verify(@RequestBody NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
    return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationRedemptionOnMaturityFacade.verify(dto));
}
@PostMapping(path = "/redemptionOnMaturity/verifyApi")
public ResponseEntity<ResponseDataDTO> verifyApi(@RequestBody NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
    return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationRedemptionOnMaturityFacade.verifyApi(dto));
}
@PostMapping(path = "/redemptionOnMaturity/download")
public ResponseEntity<ResponseDataDTO> download(@RequestBody NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
    return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationRedemptionOnMaturityFacade.download(dto));
}
@PostMapping(path = "/redemptionOnMaturity/verifyDownload")
public ResponseEntity<ResponseDataDTO> verifyDownload(
        @RequestBody NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto){
    return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationRedemptionOnMaturityFacade.verifyDownload(dto));
}
@PostMapping(path = "/redemptionOnMaturity/inquiry")
public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
    return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationRedemptionOnMaturityFacade.inquiry(dto));
}
@PostMapping(path = "/redemptionOnMaturity/template")
public ResponseEntity<ResponseDataDTO> template(@RequestBody NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto)
        throws IOException, InvalidFormatException {
    return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationRedemptionOnMaturityFacade.template(dto));
}
}
