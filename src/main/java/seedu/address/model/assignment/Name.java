package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

/**
 * Represents a task name.
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS = "Empty string not allowed";

    // Matches everything except empty string
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    public final String taskName;

    /**
     * Constructs a {@code Name}.
     *
     * @param taskName A valid name.
     */
    public Name(String taskName) {
        requireNonNull(taskName);
        this.taskName = taskName;
    }

    /**
     * @param taskName be verified
     * @return true when not an empty string, false otherwise
     */
    public static boolean isValidTaskName(String taskName) {
        return taskName.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return taskName.equals(otherName.taskName);
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }
}
