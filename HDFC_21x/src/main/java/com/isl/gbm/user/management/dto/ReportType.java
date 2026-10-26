package com.isl.gbm.user.management.dto;

/**
 * The Enum ReportTypes.
 */
public enum ReportType {

    /**
     * The unsuccessful login.
     */
    UNSUCCESSFUL_LOGIN(1, "Unsuccessful Login Report"),

    /**
     * The login logout.
     */
    LOGIN_LOGOUT(2, "Login Logout Report"),

    /**
     * The multiple ip login.
     */
    MULTIPLE_IP_LOGIN(3, "Multuiple IP Login Report"),

    /**
     * The dormant.
     */
    DORMANT(4, "Dormant Users Report"),

    /**
     * The admin activities.
     */
    ADMIN_ACTIVITIES(5, "Admin Activities Report"),

    /**
     * The permissions report.
     */
    PERMISSIONS_REPORT(6, "Permissions Report");

    /**
     * The id.
     */
    private int id;

    /**
     * The status.
     */
    private String type;

    /**
     * Instantiates a new report types.
     *
     * @param id   the id
     * @param type the type
     */
    private ReportType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

}
