package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task's end date.
 */
public class IsoDate extends Date {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_WITHOUT_TIME = "yyyy-MM-dd";

    public static final String MESSAGE_CONSTRAINTS = "Enter date in yyyy-mm-dd HH:mm or yyyy-mm-dd format.";

    public static final String MESSAGE_CONSTRAINTS_LIST =
            MESSAGE_CONSTRAINTS + " Start date must be before end date.";

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
     * Compares if a given date is after today's date.
     *
     * @param date User input of a date to be verified against today's date.
     * @return True if valid date (ie after today's date). False otherwise.
     */
    public static boolean isValidDateNotBeforeToday(String date) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDateTime d = LocalDateTime.parse(date, df);
            return !d.isBefore(LocalDateTime.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Compares if a given start date is before an end date.
     *
     * @param startDate Start Date to be compared against {@param endDate}.
     * @param endDate End Date to be compared against {@param startDate}.
     * @return True if {@param startDate} is before {@param endDate}.
     */
    public static boolean isDateBefore(String startDate, String endDate) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDateTime startDateFilter = LocalDateTime.parse(startDate, df);
            LocalDateTime endDateFilter = LocalDateTime.parse(endDate, df);

            return startDateFilter.isBefore(endDateFilter);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if a {@code date} is of the format {@code DATE_FORMAT}
     *
     * @param date The date to be checked.
     * @return True if the date is of the format. False otherwise.
     */
    public static boolean isValidIsoDate(String date) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDate d = LocalDate.parse(date, df);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if a {@code date} is of the format {@code DATE_FORMAT_WITHOUT_TIME}
     *
     * @param date The date to be checked.
     * @return True if the date is of the format. False otherwise.
     */
    public static boolean isValidIsoDateWithoutTime(String date) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT_WITHOUT_TIME);
            LocalDate d = LocalDate.parse(date, df);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * @param date User input of a date without timing to be verified.
     * @return True if valid date (ie after today's date). False otherwise.
     */
    public static boolean isValidIsoDateWithoutTimeAfterCurrent(String date) {
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

    public String toParseString() {
        return endDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
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
