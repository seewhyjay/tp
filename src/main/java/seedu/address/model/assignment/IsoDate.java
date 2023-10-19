package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Represents a task's end date.
 * Should be instantiated in the add-date
 * parser if date is given.
 */
public class IsoDate extends Date {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String DATE_FORMAT_WITHOUT_TIME = "yyyy-MM-dd";

    public static final String MESSAGE_CONSTRAINTS = "yyyy-MM-dd HH:mm\"";

    private final LocalDateTime endDate;

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
            LocalDateTime d = LocalDateTime.parse(date, df);
            return !d.isBefore(LocalDateTime.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * @param date to be verified
     * @return true if valid date, false otherwise
     */
    public static boolean isValidIsoDateWithoutTime(String date) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT_WITHOUT_TIME);
            LocalDate d = LocalDate.parse(date, df);
            return !d.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * @param date to be verified
     * @return true if valid date, false otherwise
     */
    public static boolean isValidSavedDate(String date) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDateTime.parse(date, df);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public Optional<LocalDateTime> getDate() {
        return Optional.of(endDate);
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

    @Override
    public int compareTo(Date o) {
        if (o instanceof IsoDate) {
            IsoDate other = (IsoDate) o;
            if (this.endDate.isEqual(other.endDate)) {
                return 0;
            } else if (this.endDate.isAfter(other.endDate)) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
}
