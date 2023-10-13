package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task's end date.
 * Should be instantiated in the add-date
 * parser if date is given.
 */
public class EndDate extends Date {

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
        if (!(other instanceof EndDate)) {
            return false;
        }

        EndDate otherEndDate = (EndDate) other;
        return endDate.equals(otherEndDate.endDate);
    }

    @Override
    public int hashCode() {
        return endDate.hashCode();
    }
}
