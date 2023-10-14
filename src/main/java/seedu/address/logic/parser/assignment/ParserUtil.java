package seedu.address.logic.parser.assignment;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Date;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.IsoDate;
import seedu.address.model.assignment.Name;
import seedu.address.model.assignment.Status;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.util.Objects.requireNonNull;

public class ParserUtil {

    // Validation required?
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        return new Name(trimmedName);
    }

    // Validation required?
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        return new Description(description.trim());
    }

    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(status)) {
            throw new ParseException("STATUS: TO BE EDITED");
        }
        return new Status(trimmedStatus.equals("done"));
    }

    public static Date parseDate(String date) throws ParseException  {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!IsoDate.isValidIsoDate(date)) {
            throw new ParseException("DATE: TO BE EDITED");
        }
        return new IsoDate(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));
    }
}
