package com.isl.gbm.common.controller;

import com.isl.gbm.common.dto.NpsGbmSubCodeMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.wrapper.GbmSchemeParameterAuditLogWrapper;
import com.isl.gbm.common.facade.GeneralSchemeParameterMaintenanceFacade;
import com.isl.gbm.common.service.GeneralSchemeParameterMaintenanceService;
import com.isl.gbm.common.validator.GeneralSchemeParameterMaintenanceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gbmultra/dc/general_scheme_parameter_maintenance")
public class GeneralSchemeParameterMaintenanceController {


    /**
     * The gspm hierarchy service.
     */
    @Autowired
    private GeneralSchemeParameterMaintenanceService generalSchemeParameterMaintenanceService;

    @Autowired
    private GeneralSchemeParameterMaintenanceValidator generalSchemeParameterMaintenanceValidator;

    @Autowired
    private GeneralSchemeParameterMaintenanceFacade generalSchemeParameterMaintenanceFacade;


    /**
     * Gets the list of scheme parameters
     *
     * @param schemeType the subcode
     * @return the list of scheme parameters
     */
    @RequestMapping(value = "/schemedetails/{schemeType}", method = RequestMethod.GET)
    public ResponseEntity<List<GbmSchemeParameterAuditLogWrapper>> getGeneralSchemeParameterMaintenance(
            @PathVariable final String schemeType) {

        return this.generalSchemeParameterMaintenanceFacade.getGeneralSchemeParameterMaintenance(schemeType);
    }

    /**
     * Gets the list of scheme parameters
     *
     * @param schemeType the subcode
     * @return the list of scheme parameters
     */

    @RequestMapping(value = "/schemedetails/inet/{schemeType}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Boolean>> getInternetEnabledForScheme(@PathVariable final String schemeType) {

        return this.generalSchemeParameterMaintenanceFacade.getInternetEnabledForScheme(schemeType);
    }

    /**
     * Gets the subcode list
     *
     * @return the subcode list
     */
    @RequestMapping(value = "/schemedetails/schemetypes", method = RequestMethod.GET)
    public ResponseEntity<List<NpsGbmSubCodeMasterDto>> getSchemeTypes() {

        return this.generalSchemeParameterMaintenanceFacade.getSchemeTypes();

    }

    /**
     * update the gspm .
     *
     * @return the response entity
     * @paramwrapper the wrapper
     */
    @RequestMapping(value = "/schemedetails", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> updateGeneralSchemeParameterMaintenance(
            @RequestBody @Validated final List<GbmSchemeParameterAuditLogWrapper> dtoList) {
        return this.generalSchemeParameterMaintenanceFacade.updateGeneralSchemeParameterMaintenance(dtoList);
    }

    /**
     * Verify the gspm.
     *
     * @return the response entity
     * @paramwrapper the wrapper
     */
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifyGeneralSchemeParameterMaintenance(
            @RequestBody @Validated final List<GbmSchemeParameterAuditLogWrapper> dtoList) {
        return this.generalSchemeParameterMaintenanceFacade.verifyGeneralSchemeParameterMaintenance(dtoList);

    }

}
