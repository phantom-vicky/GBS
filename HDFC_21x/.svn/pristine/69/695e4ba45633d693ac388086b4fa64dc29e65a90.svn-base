package com.isl.gbm.user.management.model;

/**
 * The Enum UserDomain.
 */
public enum UserDomain {

    /**
     * The stand alone.
     */
    STAND_ALONE("STAND_ALONE", "STAND ALONE"),

    /**
     * The finacle.
     */
    FINACLE("FINACLE", "FINACLE");

    /**
     * The id.
     */
    private String id;

    /**
     * The domain.
     */
    private String domain;

    /**
     * Instantiates a new user domain.
     *
     * @param id     the id
     * @param domain the domain
     */
    private UserDomain(String id, String domain) {
        this.id = id;
        this.domain = domain;
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
     * Gets the domain.
     *
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Find by status.
     *
     * @param id the id
     * @return the user status
     */
    public static UserDomain findById(final String id) {
        for (final UserDomain userDomain : values()) {
            if (userDomain.getId().equals(id)) {
                return userDomain;
            }
        }
        return null;
    }

    /**
     * Find by domain.
     *
     * @param domain the domain
     * @return the user domain
     */
    public static UserDomain findByDomain(final String domain) {
        for (final UserDomain userDomain : values()) {
            if (userDomain.getDomain().equals(domain)) {
                return userDomain;
            }
        }
        return null;
    }

}
