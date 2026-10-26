/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.user.management.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * The Enum FeatureNames.
 */
@JsonFormat(shape = Shape.OBJECT)
public enum FeatureNames {

    /**
     * The nps params.
     */
    NPS_PARAMS("NPS_PARAMS", "NPS PARAMS", "New Pension Scheme Parameter"),

    /**
     * The new pension hierarchy.
     */
    NEW_PENSION_HIERARCHY("NEW_PENSION_HIERARCHY", "NEW PENSION HIERARCHY", "New Pension Hierarchy"),

    /**
     * The nps lite scheme params.
     */
    NPS_LITE_SCHEME_PARAMS("NPS_LITE_SCHEME_PARAMS", "NPS SCHEME PARAMETER LITE", "New Pension Scheme Parameter Lite"),

    /**
     * The system parameter.
     */
    SYSTEM_PARAMETER("SYSTEM_PARAMETER", "SYSTEM PARAMETER", "System Parameter"),

    /**
     * The lite service request master.
     */
    LITE_SERVICE_REQUEST_MASTER("LITE_SERVICE_REQUEST_MASTER", "LITE SERVICE REQUEST MASTER", "Lite Service Request Master"),

    /**
     * The uos service request master.
     */
    UOS_SERVICE_REQUEST_MASTER("UOS_SERVICE_REQUEST_MASTER", "UOS SERVICE REQUEST MASTER", "UOS Service Request Master"),

    /**
     * The corporate service request master.
     */
    CORPORATE_SERVICE_REQUEST_MASTER("CORPORATE_SERVICE_REQUEST_MASTER", "CORPORATE SERVICE REQUEST MASTER", "Corporate Service Request Master"),

    /**
     * The corporate receipt number.
     */
    CORPORATE_RECEIPT_NUMBER("CORPORATE_RECEIPT_NUMBER", "CORPORATE RECEIPT NUMBER", "Corporate Receipt Number"),

    /**
     * The receipt number.
     */
    RECEIPT_NUMBER("RECEIPT_NUMBER", "RECEIPT NUMBER", "Receipt Number"),
    LITE_RECEIPT_NUMBER("LITE_RECEIPT_NUMBER", "LITE_RECEIPT NUMBER", "Lite Receipt Number"),

    /**
     * The general scheme parameter maintenance.
     */
    GENERAL_SCHEME_PARAMETER_MAINTENANCE("GENERAL_SCHEME_PARAMETER_MAINTENANCE", "GENERAL SCHEME PARAMETER MAINTENANCE", "Geneal Scheme Parameter Maintenance"),

    /**
     * The commission share definition.
     */
    COMMISSION_SHARE_DEFINITION("COMMISSION_SHARE_DEFINITION", "COMMISSION SHARE DEFINITION", "COMMISSION SHARE DEFINITION"),

    /**
     * The population of sol date.
     */
    POPULATION_OF_SOL_DATE("POPULATION_OF_SOL_DATE", "POPULATION OF SOL DATE", "Population of Sol Date"),

    /**
     * The cbs integration general setup.
     */
    CBS_INTEGRATION_GENERAL_SETUP("CBS_INTEGRATION_GENERAL_SETUP", "CBS INTEGRATION GENERAL SETUP", "CBS Integration General Setup"),

    /**
     * The instrument details.
     */
    INSTRUMENT_DETAILS("INSTRUMENT_DETAILS", "INSTRUMENT DETAILS", "CBS Instrument Details"),

    /**
     * The scheme codes.
     */
    SCHEME_CODES("SCHEME_CODES", "SCHEME CODES", "CBS Scheme Codes"),

    /**
     * The office account place holder.
     */
    OFFICE_ACCOUNT_PLACE_HOLDER("OFFICE_ACCOUNT_PLACE_HOLDER", "OFFICE ACCOUNT PLACE HOLDER", "Office Account Place Holder"),

    /**
     * The branch ifsc codes.
     */
    BRANCH_IFSC_CODES("BRANCH_IFSC_CODES", "BRANCH IFSC CODES", "Branch IFSC Codes"),

    /**
     * The cbs bulk upload.
     */
    CBS_BULK_UPLOAD("CBS_BULK_UPLOAD", "CBS BULK UPLOAD", "CBS Bulk Upload"),

    /**
     * The cbs user work class information.
     */
    CBS_USER_WORK_CLASS_INFORMATION("CBS_USER_WORK_CLASS_INFORMATION", "CBS USER WORK CLASS INFORMATION", "CBS User Work Class Information"),

    /**
     * The cbs other bank details.
     */
    CBS_OTHER_BANK_DETAILS("CBS_OTHER_BANK_DETAILS", "CBS OTHER BANK DETAILS", "CBS Other Bank Details"),

    /**
     * The cbs maintenance of calendar entries.
     */
    CBS_MAINTENANCE_OF_CALENDAR_ENTRIES("CBS_MAINTENANCE_OF_CALENDAR_ENTRIES", "CBS MAINTENANCE OF CALENDAR ENTRIES", "CBSMaintenance of Calender Entries"),

    /**
     * The cbs list of transaction codes.
     */
    CBS_LIST_OF_TRANSACTION_CODES("CBS_LIST_OF_TRANSACTION_CODES", "CBS LIST OF TRANSACTION CODES", "CBS List of Transaction Codes"),

    /**
     * The cbs branches of other bank.
     */
    CBS_BRANCHES_OF_OTHER_BANK("CBS_BRANCHES_OF_OTHER_BANK", "CBS BRANCHES OF OTHER BANK", "CBS Branches of Other Bank"),

    /**
     * The cbs branches of the bank.
     */
    CBS_BRANCHES_OF_THE_BANK("CBS_BRANCHES_OF_THE_BANK", "CBS BRANCHES OF THE BANK", "CBS Branches of the Bank"),

    NPS_PRAN_MASTER_WALK_IN_CUSTOMER("NPS_PRAN_MASTER_WALK_IN_CUSTOMER", "NPS PRAN MASTER WALK IN CUSTOMER",
            "NPS POP SP PRAN Master Walk-in Customer"),

    NPS_POP_WALK_IN_CUSTOMERS_UPDATION_OF_CORRECT_PRAN_NUMBER("NPS_POP_WALK_IN_CUSTOMERS_UPDATION_OF_CORRECT_PRAN_NUMBER", "NPS POP WALK IN CUSTOMERS UPDATION OF CORRECT PRAN NUMBER",
            "NPS POP Walk-in-Customers-Updation of Correct Pran Number"),

    NPS_TRANSFER_OF_PRAN_WITHIN_ONE_POPSP("NPS_TRANSFER_OF_PRAN_WITHIN_ONE_POPSP",
            "NPS TRANSFER OF PRAN WITHIN ONE POPSP", "NPS POP SP Master Updation Transfer Of PRAN Within One POPSP"),

    SI_MANDATE("SI_MANDATE", "SI MANDATE", "SI Mandate"),
    SOL_WISE_ACCOUNT_OPENED_MASTER("SOL_WISE_ACCOUNT_OPENED_MASTER", "SOL_WISE_ACCOUNT_OPENED_MASTER", "SOL WISE ACCOUNT OPENED MASTER"),
    POP_FREEZING_DEACTIVATION_MASTER_MAINTENANCE("POP_FREEZING_DEACTIVATION_MASTER_MAINTENANCE", "POP_FREEZING_DEACTIVATION_MASTER_MAINTENANCE", "Pop Freezing Deactivation Master Maintenance");

    /**
     * Find by description.
     *
     * @param description the description
     * @return the feature names
     */
    public static FeatureNames findByDescription(final String description) {
        for (final FeatureNames feature : values()) {
            if (StringUtils.equalsIgnoreCase(feature.getDescription(), description)) {
                return feature;
            }
        }
        return null;
    }

    /**
     * Find by feature display name.
     *
     * @param featureDisplayName the feature display name
     * @return the feature names
     */
    public static FeatureNames findByFeatureDisplayName(final String featureDisplayName) {
        for (final FeatureNames feature : values()) {
            if (StringUtils.equalsIgnoreCase(feature.getFeatureDisplayName(), featureDisplayName)) {
                return feature;
            }
        }
        return null;
    }

    /**
     * Find by feature name.
     *
     * @param featureName the feature name
     * @return the feature names
     */
    public static FeatureNames findByFeatureName(final String featureName) {
        for (final FeatureNames feature : values()) {
            if (StringUtils.equalsIgnoreCase(feature.getFeatureName(), featureName)) {
                return feature;
            }
        }
        return null;
    }

    /**
     * From object.
     *
     * @param obj the obj
     * @return the feature names
     */
    @JsonCreator
    public static FeatureNames fromObject(final Map<String, Object> obj) {
        if (obj != null && obj.containsKey("featureDisplayName")) {
            return findByFeatureDisplayName((String) obj.get("featureDisplayName"));
        }
        return null;
    }

    /**
     * The feature display name.
     */
    private String featureDisplayName;

    /**
     * The feature name.
     */
    private String featureName;

    /**
     * The description.
     */
    private String description;

    /**
     * Instantiates a new feature names.
     *
     * @param featureDisplayName the feature display name
     * @param featureName        the feature name
     * @param description        the description
     */
    private FeatureNames(final String featureDisplayName, final String featureName, final String description) {
        this.featureDisplayName = featureDisplayName;
        this.featureName = featureName;
        this.description = description;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the feature display name.
     *
     * @return the feature display name
     */
    public String getFeatureDisplayName() {
        return this.featureDisplayName;
    }

    /**
     * Gets the feature name.
     *
     * @return the feature name
     */
    public String getFeatureName() {
        return this.featureName;
    }

}
