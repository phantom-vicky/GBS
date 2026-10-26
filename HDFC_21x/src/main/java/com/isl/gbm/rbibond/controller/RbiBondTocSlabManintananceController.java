package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondTocSlabMaintenanceDTO;
import com.isl.gbm.rbibond.facade.RbiBondTocSlabMaintananceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("gbmultra/rbiBond/tocSlab")
public class RbiBondTocSlabManintananceController {
    @Autowired
    private RbiBondTocSlabMaintananceFacade rbiBondTocSlabMaintananceFacade;

    /********************************************TOC Slab Maintainence Starts****************************************************/
    /**
     * @param rbiBondTocSlabMaintenanceDTO
     * @return
     */
    @PostMapping(value = "/selectModifySlab")

    public ResponseEntity<ResponseDataDTO> selectModify(@RequestBody final RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSlabMaintananceFacade.selectModifySlab(rbiBondTocSlabMaintenanceDTO));
    }

    /**
     * @param rbiBondTocSlabMaintenanceDTO
     * @return
     */
    @PostMapping(value = "/selectVerifySlab")
    public ResponseEntity<ResponseDataDTO> selectVerify(@RequestBody final RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSlabMaintananceFacade.selectVerifySlab(rbiBondTocSlabMaintenanceDTO));
    }

    /**
     * @param rbiBondTocSlabMaintenanceDTO
     * @return
     */
    @PostMapping(value = "/getAddDetails")
    public ResponseEntity<ResponseDataDTO> getAddDetails(@RequestBody final RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSlabMaintananceFacade.getAddDetails(rbiBondTocSlabMaintenanceDTO));
    }

    /**
     * @param rbiBondTocSlabMaintenanceDTO
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDataDTO> save(@RequestBody final RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSlabMaintananceFacade.save(rbiBondTocSlabMaintenanceDTO));
    }

    /**
     * @param rbiBondTocSlabMaintenanceDTO
     * @return
     */
    @PostMapping(value = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody final RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSlabMaintananceFacade.modify(rbiBondTocSlabMaintenanceDTO));
    }

    /**
     * @param rbiBondTocSlabMaintenanceDTO
     * @return
     */
    @PostMapping(value = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody final RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSlabMaintananceFacade.verify(rbiBondTocSlabMaintenanceDTO));
    }

    /**
     * @param rbiBondTocSlabMaintenanceDTO
     * @return
     */
    @PostMapping(value = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody final RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSlabMaintananceFacade.delete(rbiBondTocSlabMaintenanceDTO));
    }

    /**
     * @param rbiBondTocSlabMaintenanceDTO
     * @return
     */
    @PostMapping(value = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody final RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSlabMaintananceFacade.inquiry(rbiBondTocSlabMaintenanceDTO));
    }
    /********************************************TOC Slab Maintainence Ends****************************************************/

}
