package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.TrailRunBrokerageDTO;
import com.isl.gbm.rbibond.facade.TrailRunBrokerageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("gbmultra/rbiBond/trailRunBrokerage")
public class TrailRunBrokerageController {
    
    
    @Autowired
    private TrailRunBrokerageFacade trailRunBrokerageFacade;



    @PostMapping(path = "/getBrokerDetails")
    public ResponseEntity<ResponseDataDTO> getBrokerDetails(@RequestBody TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return APIResponseBuilder.buildResponseFromDto(trailRunBrokerageFacade.getBrokerDetails(trailRunBrokerageDTO));
    }


    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return APIResponseBuilder.buildResponseFromDto(trailRunBrokerageFacade.add(trailRunBrokerageDTO));
    }


    @PostMapping(path = "/getTrailRun")
    public ResponseEntity<ResponseDataDTO> getTrailRun(@RequestBody TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return APIResponseBuilder.buildResponseFromDto(trailRunBrokerageFacade.getTrailRun(trailRunBrokerageDTO));
    }

    @PostMapping(path = "/getTrailRunVerDetail")
    public ResponseEntity<ResponseDataDTO> getTrailRunVerDetail(@RequestBody TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return APIResponseBuilder.buildResponseFromDto(trailRunBrokerageFacade.getTrailRunVerDetail(trailRunBrokerageDTO));
    }
    @PostMapping(path = "/getAgencyCodeAddTor")
    public ResponseEntity<ResponseDataDTO> getAgencyCodeAddTor(@RequestBody TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return APIResponseBuilder.buildResponseFromDto(trailRunBrokerageFacade.getAgencyCodeAddTor(trailRunBrokerageDTO));
    }

    @PostMapping(path = "/getAgencyCodeVerTor")
    public ResponseEntity<ResponseDataDTO> getAgencyCodeVerTor(@RequestBody TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return APIResponseBuilder.buildResponseFromDto(trailRunBrokerageFacade.getAgencyCodeVerTor(trailRunBrokerageDTO));
    }
    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return APIResponseBuilder.buildResponseFromDto(trailRunBrokerageFacade.verifySelect(trailRunBrokerageDTO));
    }


    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return APIResponseBuilder.buildResponseFromDto(trailRunBrokerageFacade.verify(trailRunBrokerageDTO));
    }




    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return APIResponseBuilder.buildResponseFromDto(trailRunBrokerageFacade.delete(trailRunBrokerageDTO));
    }

    @PostMapping(path = "/tranValidation")
    public ResponseEntity<ResponseDataDTO> tranValidation(@RequestBody TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return APIResponseBuilder.buildResponseFromDto(trailRunBrokerageFacade.tranValidation(trailRunBrokerageDTO));
    }

    @PostMapping(path = "/download", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> download(@RequestBody TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return APIResponseBuilder.buildResponseFromDto(trailRunBrokerageFacade.download(trailRunBrokerageDTO));
    }

}
