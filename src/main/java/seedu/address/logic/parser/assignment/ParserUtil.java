package seedu.address.logic.parser.assignment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Date;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.IsoDate;
import seedu.address.model.assignment.Name;
import seedu.address.model.assignment.Status;

/**
 * Utility parser for assignments
 */
public class ParserUtil {

    /**
     * @param name the taskname
     * @return a name object
     * @throws ParseException
     */
    // Validation required?
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        return new Name(trimmedName);
    }

    /**
     * @param description the description of task
     * @return a description object
     * @throws ParseException
     */
    // Validation required?
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        return new Description(description.trim());
    }

    /**
     * @param status the completion status
     * @return a status object
     * @throws ParseException when input is not done or undone
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(status)) {
            throw new ParseException("Enter a valid status input: s/done, s/undone");
        }
        return new Status(trimmedStatus.equals("done"));
    }

    /**
     * @param date to be parsed
     * @return a date object
     * @throws ParseException when not in yyyy-mm-dd HH:mm format
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!IsoDate.isValidIsoDate(date)) {
            throw new ParseException("Enter date in yyyy-mm-dd HH:mm format");
        }
        return new IsoDate(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));
    }
}
