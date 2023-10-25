package seedu.address.model.fields;

import static java.util.Objects.requireNonNull;

/**
 * A class representing a location
 */
public class Location {
    public static final String MESSAGE_CONSTRAINTS = "Empty string not allowed";

    // Matches everything except empty string
    private static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    private final String location;

    /**
     * Constructs a {@code Location}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    /**
     * @param taskLocation be verified
     * @return true when not an empty string, false otherwise
     */
    public static boolean isValidLocation(String taskLocation) {
        return taskLocation.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return location;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Location)) {
            return false;
        }

        Location otherLocation = (Location) other;
        return location.equals(otherLocation.location);
    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }
}
