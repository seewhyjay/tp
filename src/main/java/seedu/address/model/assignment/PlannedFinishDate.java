package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent a date the user
 * wants to finish the assignment by.
 */
public class PlannedFinishDate {

    public final LocalDateTime plannedFinishDate;

    /**
     * Constructs a {@code PlannedFinishDate}.
     *
     * @param plannedFinishDate A valid date.
     */
    public PlannedFinishDate(LocalDateTime plannedFinishDate) {
        requireNonNull(plannedFinishDate);
        this.plannedFinishDate = plannedFinishDate;
    }

    @Override
    public String toString() {
        return plannedFinishDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.assignment.PlannedFinishDate)) {
            return false;
        }

        seedu.address.model.assignment.PlannedFinishDate otherPlannedDate =
                (seedu.address.model.assignment.PlannedFinishDate) other;
        return plannedFinishDate.equals(otherPlannedDate.plannedFinishDate);
    }

    @Override
    public int hashCode() {
        return plannedFinishDate.hashCode();
    }
}
