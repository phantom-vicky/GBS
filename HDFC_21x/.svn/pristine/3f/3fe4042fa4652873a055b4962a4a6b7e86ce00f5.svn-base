/**
 *
 */
package com.isl.gbm.cbs.integration.common;

/**
 * The Enum CalendarTypes.
 *
 * @author harishbabu.t
 */
public enum CalendarTypes {

    /** The dc. */
    DC("DC", "Data Center"),
    /** The Branch. */
    BRANCH("BRANCH", "Branch");

    /**
     * Find by type.
     *
     * @param calendarType
     *            the calendar type
     * @return the calendar types
     */
    public static CalendarTypes findByType(final String calendarType) {
        for (final CalendarTypes calendarTypes : values()) {
            if (calendarTypes.getCalendarType().equals(calendarType)) {
                return calendarTypes;
            }
        }
        return null;
    }

    /** The Calendar type. */
    private String calendarType;

    /** The description. */
    private String description;

    /**
     * Instantiates a new calendar types.
     *
     * @param calendarType
     *            the calendar type
     * @param description
     *            the description
     */
    private CalendarTypes(final String calendarType, final String description) {
        this.calendarType = calendarType;
        this.description = description;
    }

    /**
     * Gets the calendar type.
     *
     * @return the calendarType
     */
    public String getCalendarType() {
        return this.calendarType;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }
}
