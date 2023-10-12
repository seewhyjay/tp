package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

/**
 * Represents a task description.
 */
public class Description {

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.assignment.Description)) {
            return false;
        }

        seedu.address.model.assignment.Description otherDescription =
                (seedu.address.model.assignment.Description) other;
        return description.equals(otherDescription.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
