package com.isl.gbm.rbibond.externalservice.utility;

public enum PanStatus {
        E("Existing and Valid"),
        F("Marked as Fake"),
        X("Marked as Deactivated"),
        D("Deleted"),
        N("Record (PAN) Not Found in ITD Database/Invalid PAN"),
        EA("Existing and Valid but event marked as Amalgamation in ITD database"),
        EC("Existing and Valid but event marked as Acquisition in ITD database"),
        ED("Existing and Valid but event marked as Death in ITD database"),
        EI("Existing and Valid but event marked as Dissolution in ITD database"),
        EL("Existing and Valid but event marked as Liquidated in ITD database"),
        EM("Existing and Valid but event marked as Merger in ITD database"),
        EP("Existing and Valid but event marked as Partition in ITD database"),
        ES("Existing and Valid but event marked as Split in ITD database"),
        EU("Existing and Valid but event marked as Under Liquidation in ITD database");
        private final String description;

        PanStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static String getMessage(String flag) {
            if (flag == null) {
                return "Unknown flag";
            }
            try {
                return PanStatus.valueOf(flag).getDescription();
            } catch (IllegalArgumentException e) {
                return "Unknown flag";
            }
        }
}
