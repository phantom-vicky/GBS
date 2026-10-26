package com.isl.gbm.common.service.impl;

import com.isl.gbm.cbs.integration.dto.NpsGbmPlaceholderDto;
import com.isl.gbm.cbs.integration.manager.AccountPlaceHolderIntegrationManager;
import com.isl.gbm.common.GbmCommonSchemeParameterKeys;
import com.isl.gbm.common.SchemeCodes;
import com.isl.gbm.common.dto.NpsGbmSubCodeMasterDto;
import com.isl.gbm.common.dto.wrapper.GbmSchemeParameterAuditLogWrapper;
import com.isl.gbm.common.manager.GeneralSchemeParameterMaintenanceManager;
import com.isl.gbm.common.service.GeneralSchemeParameterMaintenanceService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GeneralSchemeParameterMaintenanceServiceImpl implements GeneralSchemeParameterMaintenanceService {

    /**
     * The manager.
     */
    @Autowired
    private GeneralSchemeParameterMaintenanceManager manager;

    /**
     * The account place holder integration manager.
     */
    @Autowired
    private AccountPlaceHolderIntegrationManager accountPlaceHolderIntegrationManager;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.common.service.DcGspmService#getGeneralSchemeParameterMaintenance
     * (java.lang.String)
     */
    @Override
    public List<GbmSchemeParameterAuditLogWrapper> getGeneralSchemeParameterMaintenance(final String schemeType) {
        final List<GbmSchemeParameterAuditLogWrapper> paramlList = this.manager
                .getGeneralSchemeParameterMaintenance(schemeType);
        final List<String> placeholderList = new ArrayList<>();

        if (schemeType.equals(SchemeCodes.COMMON.getSchemeCode())) {

            for (final GbmSchemeParameterAuditLogWrapper wrapper : paramlList) {

                final GbmCommonSchemeParameterKeys keys = GbmCommonSchemeParameterKeys.findByKey(wrapper.getDto().getParamName());

                switch (keys) {
                    case CASH_ACCOUNT_PH:
                    case CLEARING_ACCOUNT_PH:
                    case RBI_RELIEF_ACCOUNT_PH:
                    case DD_ACCOUNT_PH:
                    case PO_ACCOUNT_PH:
                    case ECS_ACCOUNT_PH:
                    case PLACEHOLDER_TDS_ACCOUNT:
                    case OTHER_BRANCH_CR_AC_PH:
                    case MAIL_TRANSFER_AC_PH:
                    case LOCAL_RBI_AC_PH:
                    case BANKERS_CHQ_AC_PH:
                    case FOCAL_POINT_CONSOLIDATE_AC_PH:
                    case RBI_PSB_AC_PH:
                    case SUNDRY_ACC_PH:
                    case HOACLOCAL_ACC_PH:
                        placeholderList.add(wrapper.getDto().getParamValue());
                        break;

                    default:
                        break;
                }
            }

            final List<NpsGbmPlaceholderDto> accountTitleList = this.setAccountTitle(placeholderList);

            for (final GbmSchemeParameterAuditLogWrapper wrapper : paramlList) {

                for (final NpsGbmPlaceholderDto accountTitle : accountTitleList) {
                    if (wrapper.getDto().getParamValue().equals(accountTitle.getBankAccountId())) {
                        wrapper.getDto().setAccountTitle(accountTitle.getBankAccountIdDescription());
                    }
                }

            }
        }
        return paramlList;
    }

    /**
     * Gets whether internet enabled or not.
     *
     * @param schemeType scheme code
     * @return String
     */
    @Override
    public Boolean getInternetEnabledForScheme(final String schemeType) {
        return this.manager.getInternetEnabledForScheme(schemeType);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.common.service.DcGspmService#getSchemeTypes()
     */
    @Override
    public List<NpsGbmSubCodeMasterDto> getSchemeTypes() {
        return this.manager.getSchemeTypes();
    }

    /**
     * @param accountPlaceHolderIntegrationManager the accountPlaceHolderIntegrationManager to set
     */
    public void setAccountPlaceHolderIntegrationManager(
            final AccountPlaceHolderIntegrationManager accountPlaceHolderIntegrationManager) {
        this.accountPlaceHolderIntegrationManager = accountPlaceHolderIntegrationManager;
    }

    private List<NpsGbmPlaceholderDto> setAccountTitle(final List<String> paramList) {

        final List<NpsGbmPlaceholderDto> placeHolderDto = this.accountPlaceHolderIntegrationManager
                .getClearingAccountDetailsForList(paramList);

        return (CollectionUtils.isNotEmpty(placeHolderDto) ? placeHolderDto : new ArrayList<>());

    }

    /**
     * @param manager the manager to set
     */
    public void setManager(final GeneralSchemeParameterMaintenanceManager manager) {
        this.manager = manager;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.common.service.DcGspmService#
     * updateGeneralSchemeParameterMaintenance(com.isl.gbm.common.dto.wrapper.
     * GbmSchemeParameterAuditLogWrapper)
     */
    @Override
    public void updateGeneralSchemeParameterMaintenance(final List<GbmSchemeParameterAuditLogWrapper> dtoList) {
        this.manager.updateGeneralSchemeParameterMaintenance(dtoList);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.common.service.DcGspmService#
     * verifyGeneralSchemeParameterMaintenance(com.isl.gbm.common.dto.wrapper.
     * GbmSchemeParameterAuditLogWrapper)
     */
    @Override
    public void verifyGeneralSchemeParameterMaintenance(final List<GbmSchemeParameterAuditLogWrapper> dtoList) {
        this.manager.verifyGeneralSchemeParameterMaintenance(dtoList);

    }

}
