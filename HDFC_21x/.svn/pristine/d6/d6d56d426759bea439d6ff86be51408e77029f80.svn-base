package com.isl.gbm.common.validator;

import com.isl.gbm.common.GbmCBDTSchemeParameterKeys;
import com.isl.gbm.common.GbmCBECSchemeParameterKeys;
import com.isl.gbm.common.GbmCommonSchemeParameterKeys;
import com.isl.gbm.common.SchemeCodes;
import com.isl.gbm.common.dto.wrapper.GbmSchemeParameterAuditLogWrapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GeneralSchemeParameterMaintenanceValidator {

    public Boolean validateRecord(final List<GbmSchemeParameterAuditLogWrapper> dtoList,
                                  final Map<String, String> errorMessages) {

        for (final GbmSchemeParameterAuditLogWrapper wrapper : dtoList) {
            final SchemeCodes schemeCode = SchemeCodes.findByKey(wrapper.getDto().getSchmCode());
            switch (schemeCode) {
                case COMMON:
                    if (null == GbmCommonSchemeParameterKeys.findByKey(wrapper.getDto().getParamName())) {
                        errorMessages.put("Invalid Parameter", "Please enter Paramter Name related to COMMON scheme");
                        return Boolean.FALSE;
                    }
                    break;

                case CBDT:
                    if (null == GbmCBDTSchemeParameterKeys.findByKey(wrapper.getDto().getParamName())) {
                        errorMessages.put("Invalid Parameter", "Please enter Paramter Name related to CBDT scheme");
                        return Boolean.FALSE;
                    }
                    break;

                case CBEC:
                    if (null == GbmCBECSchemeParameterKeys.findByKey(wrapper.getDto().getParamName())) {
                        errorMessages.put("Invalid Parameter", "Please enter Paramter Name related to CBEC scheme");
                        return Boolean.FALSE;
                    }
                    break;

                default:

                    return Boolean.FALSE;
            }
        }

        return true;
    }
}
