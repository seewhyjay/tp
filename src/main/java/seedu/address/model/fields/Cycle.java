package seedu.address.model.fields;

import static java.util.Objects.requireNonNull;

/**
 * Represents the period of an internship
 */
public class Cycle {
    public static final String MESSAGE_CONSTRAINTS = "Empty string not allowed";

    // Matches everything except empty string
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    private final String cycle;

    /**
     * Constructs a {@code Cycle}.
     *
     * @param cycle A valid cycle.
     */
    public Cycle(String cycle) {
        requireNonNull(cycle);
        this.cycle = cycle;
    }

    public String getCycle() {
        return cycle;
    }

    /**
     * @param taskCycle be verified
     * @return true when not an empty string, false otherwise
     */
    public static boolean isValidCycle(String taskCycle) {
        return taskCycle.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return cycle;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Cycle)) {
            return false;
        }

        Cycle otherCycle = (Cycle) other;
        return cycle.equals(otherCycle.cycle);
    }

    @Override
    public int hashCode() {
        return cycle.hashCode();
    }
}
