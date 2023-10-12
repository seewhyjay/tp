package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task's end date.
 */
public class EndDate {

    public final LocalDateTime endDate;

    /**
     * Constructs a {@code EndDate}.
     *
     * @param endDate A valid date.
     */
    public EndDate(LocalDateTime endDate) {
        requireNonNull(endDate);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.assignment.EndDate)) {
            return false;
        }

        seedu.address.model.assignment.EndDate otherEndDate = (seedu.address.model.assignment.EndDate) other;
        return endDate.equals(otherEndDate.endDate);
    }

    @Override
    public int hashCode() {
        return endDate.hashCode();
    }
}
