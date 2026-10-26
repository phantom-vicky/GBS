package com.isl.gbm.user.management.constants;

public enum UserLogOutReasons {
    NORMAL_LOGOUT("N", "NORMAL LOGOUT"),
    SESSION_EXPIRED("SE", "SESSION_EXPIRED"),
    BROWSER_WINDOW_CLOSED("WC", "BROWSER_WINDOW_CLOSED"),
    MIDDLEWARE_SHUTDOWN("MS", "MIDDLEWARE_SHUTDOWN"),
    SACKED_BY_OTHER("SK", "SACKED_BY_OTHER_USER")
    ;

    private final String reasonName;

    private final String reasonDesc;

    UserLogOutReasons(String reasonName, String reasonDesc) {
        this.reasonName = reasonName;
        this.reasonDesc = reasonDesc;
    }

    public String getReasonName() {
        return reasonName;
    }

    public String getReasonDesc() {
        return reasonDesc;
    }

}
