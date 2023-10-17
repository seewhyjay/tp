package seedu.address.logic.parser.assigment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.assignment.AddAssignmentCommand;
import seedu.address.logic.parser.assignment.AddAssignmentParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.IsoDate;
import seedu.address.model.assignment.Name;
import seedu.address.model.assignment.NoDate;
import seedu.address.model.assignment.Status;
import seedu.address.model.tag.Tag;

// LAZY TO MAKE UNIT TEST WITH CURRENT DATE WITH AND WITHOUT TIME, BUT I USER TESTED IT!!!!!!!!!
public class AddAssignmentParserTest {
    private final AddAssignmentParser parser = new AddAssignmentParser();

    private final String invalidCommandMessage = "Invalid command format! \n"
            + "add-a: Adds an assignment to the app. Parameters: n/NAME [d/DESCRIPTION]\n"
            + "s/STATUS e/YYYY-MM-DD HH:mm [p/YYYY-MM-DD HH:mm ]\n"
            + "Example: add-a";

    @Test
    public void parse_allFieldsPresentSingleTag_success() throws ParseException {
        String validInput = " n/task1 s/complete e/2025-11-11 23:59 d/description p/2025-11-11 11:11 t/cs2100";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(true), new Description("description"),
                new IsoDate(LocalDateTime.parse("2025-11-11 11:11",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                Set.of(new Tag("cs2100")));

        assertEquals(parser.parse(validInput), new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_allFieldsPresentMultipleTag_success() throws ParseException {
        String validInput = " n/task1 s/complete e/2025-11-11 23:59 d/description p/2025-11-11 11:11 "
                + "t/cs2100 t/cs2103 t/gg";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(true), new Description("description"),
                new IsoDate(LocalDateTime.parse("2025-11-11 11:11",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                Set.of(new Tag("cs2100"), new Tag("cs2103"), new Tag("gg")));

        assertEquals(parser.parse(validInput), new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_missingStatus_success() throws ParseException {
        String validInput = " n/task1 e/2025-11-11 23:59 d/description p/2025-11-11 11:11 "
                + "t/cs2100 t/cs2103 t/gg";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(false), new Description("description"),
                new IsoDate(LocalDateTime.parse("2025-11-11 11:11",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                Set.of(new Tag("cs2100"), new Tag("cs2103"), new Tag("gg")));

        assertEquals(parser.parse(validInput), new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_missingPlannedDate_success() throws ParseException {
        String validInput = " n/task1 s/complete e/2025-11-11 23:59 d/description t/cs2100 t/cs2103 t/gg";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(true), new Description("description"),
                new NoDate(),
                Set.of(new Tag("cs2100"), new Tag("cs2103"), new Tag("gg")));

        assertEquals(parser.parse(validInput), new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_missingDescription_success() throws ParseException {
        String validInput = " n/task1 s/complete e/2025-11-11 23:59 p/2025-11-11 11:11 "
                + "t/cs2100 t/cs2103 t/gg";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(true), new Description(""),
                new IsoDate(LocalDateTime.parse("2025-11-11 11:11",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                Set.of(new Tag("cs2100"), new Tag("cs2103"), new Tag("gg")));

        assertEquals(parser.parse(validInput), new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_validDateWithoutTime_success() throws ParseException {
        String validInput = " n/task1 s/complete e/2025-11-11 d/description p/2025-11-11 11:11 t/cs2100";

        Assignment validAssignment = new Assignment(new Name("task1"),
                new IsoDate(LocalDateTime.parse("2025-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(true), new Description("description"),
                new IsoDate(LocalDateTime.parse("2025-11-11 11:11",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                Set.of(new Tag("cs2100")));

        assertEquals(parser.parse(validInput), new AddAssignmentCommand(validAssignment));
    }

    @Test
    public void parse_invalidDateWithoutTime_fail() throws ParseException {
        String validInput = " n/task1 s/complete e/2021-11-11 d/description p/2025-11-11 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(validInput));
        assertEquals(e.getMessage(), "Enter date in yyyy-mm-dd HH:mm or yyyy-mm-dd (default 23:59) "
                + "format and given date must not be before today's date");

    }

    @Test
    public void parse_invalidDateWithoutTime2_fail() throws ParseException {
        String validInput = " n/task1 s/complete e/2021-11-1 d/description p/2025-11-11 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(validInput));
        assertEquals(e.getMessage(), "Enter date in yyyy-mm-dd HH:mm or yyyy-mm-dd (default 23:59) "
                + "format and given date must not be before today's date");

    }

    @Test
    public void parse_multipleName_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete e/2025-11-11 23:59 "
                + "d/description p/2025-11-11 11:11 t/cs2100 n/task2";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertEquals(e.getMessage(), "Multiple values specified for the following single-valued field(s): n/");
    }

    @Test
    public void parse_multipleStatus_fail() throws ParseException {
        String invalidInput = " n/task1 s/incomplete s/complete e/2025-11-11 23:59 "
                + "d/description p/2025-11-11 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertEquals(e.getMessage(), "Multiple values specified for the following single-valued field(s): s/");
    }

    @Test
    public void parse_multipleEndDate_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete e/2025-11-11 23:59 e/2025-11-11 11:11 "
                + "d/description p/2025-11-11 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertEquals(e.getMessage(), "Multiple values specified for the following single-valued field(s): e/");
    }

    @Test
    public void parse_multipleDescription_fail() throws ParseException {
        String invalidInput = " n/task1 s/incomplete e/2025-11-11 23:59 "
                + "d/description d/description1 d/description3 p/2025-11-11 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertEquals(e.getMessage(), "Multiple values specified for the following single-valued field(s): d/");
    }

    @Test
    public void parse_multiplePlannedDate_fail() throws ParseException {
        String invalidInput = " n/task1 s/incomplete e/2025-11-11 23:59 "
                + "d/description p/2025-11-11 11:11 p/2025-11-11 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertEquals(e.getMessage(), "Multiple values specified for the following single-valued field(s): p/");
    }

    @Test
    public void parse_invalidName_fail() throws ParseException {
        String invalidInput = " n/ s/complete e/2025-11-11 23:59 d/description p/2025-11-11 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertEquals(e.getMessage(), "Name cannot be empty");
    }

    @Test
    public void parse_invalidDate_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete e/1111-11-11 23:59 d/description p/1111-11-11 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertEquals(e.getMessage(), "Enter date in yyyy-mm-dd HH:mm or yyyy-mm-dd (default 23:59) format "
                + "and given date must not be before today's date");
    }

    @Test
    public void parse_invalidStatus_fail() throws ParseException {
        String invalidInput = " n/task1 s/completee e/2025-11-11 23:59 d/description p/2025-11-11 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertEquals(e.getMessage(), "Enter a valid status input: s/complete, s/incomplete");
    }

    @Test
    public void parse_invalidPlannedDate_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete e/2025-11-11 23:59 d/description p/1111-11-1 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertEquals(e.getMessage(), "Enter date in yyyy-mm-dd HH:mm or yyyy-mm-dd (default 23:59) format "
                + "and given date must not be before today's date");
    }

    @Test
    public void parse_missingName_fail() throws ParseException {
        String invalidInput = " s/complete e/2025-11-11 23:59 d/description p/2025-11-11 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertEquals(invalidCommandMessage, e.getMessage());
    }

    @Test
    public void parse_missingEndDate_fail() throws ParseException {
        String invalidInput = " n/task1 s/complete d/description p/2025-11-11 11:11 t/cs2100";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertEquals(invalidCommandMessage, e.getMessage());
    }
}
