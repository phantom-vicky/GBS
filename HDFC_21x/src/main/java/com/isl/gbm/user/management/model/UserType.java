package com.isl.gbm.user.management.model;

/**
 * The Enum UserType.
 */
public enum UserType {

    /**
     * The bank.
     */
    BANK("B", "Bank"),

    /**
     * The contract.
     */
    CONTRACT("C", "Contract");

    /**
     * The id.
     */
    private String id;

    /**
     * The status.
     */
    private String type;

    /**
     * Instantiates a new user type.
     *
     * @param id   the id
     * @param type the type
     */
    private UserType(String id, String type) {
        this.id = id;
        this.type = type;
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
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the user type
     */
    public static UserType findById(final String id) {
        for (final UserType userType : values()) {
            if (userType.getId().equals(id)) {
                return userType;
            }
        }
        return null;
    }

}
