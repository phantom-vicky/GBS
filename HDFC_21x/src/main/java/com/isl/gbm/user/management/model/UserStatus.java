package com.isl.gbm.user.management.model;

/**
 * The Enum UserStatus.
 */
public enum UserStatus {

    /**
     * The inactive.
     */
    INACTIVE("I01", "InActive"),

    /**
     * The Deactive
     */

    DEACTIVE("D03", "Deactivate"),

    /**
     * The active.
     */
    ACTIVE("A01", "Active"),

    /**
     * The disabled.
     */
    DISABLED("D01", "Disabled"),

    /**
     * The locked.
     */
    LOCKED("L01", "Locked"),

    /**
     * The locked.
     */
    TERMINATED("T01", "Terminated"),

    /**
     * The deleted.
     */
    DELETED("D02", "Deleted"),

    /**
     * The expired.
     */
    EXPIRED("E01", "Expired");

    /**
     * The id.
     */
    private String id;

    /**
     * The status.
     */
    private String status;

    /**
     * Instantiates a new user status.
     *
     * @param id     the id
     * @param status the status
     */
    private UserStatus(String id, String status) {
        this.id = id;
        this.status = status;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Find by status.
     *
     * @param status the status
     * @return the user status
     */
    public static UserStatus findById(final String id) {
        for (final UserStatus userStatus : values()) {
            if (userStatus.getId().equals(id)) {
                return userStatus;
            }
        }
        return null;
    }

}
