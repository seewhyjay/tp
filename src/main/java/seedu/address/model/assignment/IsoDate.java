package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task's end date.
 * Should be instantiated in the add-date
 * parser if date is given.
 */
public class IsoDate extends Date {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String MESSAGE_CONSTRAINTS = "yyyy-MM-dd HH:mm\"";

    public final LocalDateTime endDate;

    /**
     * Constructs a {@code EndDate}.
     *
     * @param endDate A valid date.
     */
    public IsoDate(LocalDateTime endDate) {
        requireNonNull(endDate);
        this.endDate = endDate;
    }

    /**
     * @param date to be verified
     * @return true if valid date, false otherwise
     */
    public static boolean isValidIsoDate(String date) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT);
            df.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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
        if (!(other instanceof IsoDate)) {
            return false;
        }

        IsoDate otherEndDate = (IsoDate) other;
        return endDate.equals(otherEndDate.endDate);
    }

    @Override
    public int hashCode() {
        return endDate.hashCode();
    }

    @Override
    public String toSaveData() {
        return endDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
