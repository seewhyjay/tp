package seedu.address.logic.parser.assigment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.assignment.EditAssignmentCommand;
import seedu.address.logic.parser.assignment.EditAssignmentParser;
import seedu.address.logic.parser.assignment.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Description;

public class EditAssignmentParserTest {

    private EditAssignmentParser parser = new EditAssignmentParser();

    @Test
    public void parse_duplicateDescriptionPrefix_failure() {
        String userInput = " i/i d/desc d/valid input";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(e.getMessage(), "Multiple values specified for the following single-valued field(s): d/");
    }

    @Test
    public void parse_duplicateIndexPrefix_failure() {
        String userInput = " i/i i/2 d/valid input";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(e.getMessage(), "Multiple values specified for the following single-valued field(s): i/");
    }

    @Test
    public void parse_missingIndexPrefix_failure() {
        String userInput = "  d/valid input";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(e.getMessage(), String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditAssignmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingDescriptionPrefix_failure() {
        String userInput = "  i/1";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(e.getMessage(), String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditAssignmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyInput_failure() {
        String userInput = " ";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(e.getMessage(), String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditAssignmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_failure() {
        String userInput = " i/1 1 d/valid input";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(e.getMessage(), ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidIndex2_failure() {
        String userInput = " i/0 d/valid input";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(e.getMessage(), ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidIndex3_failure() {
        String userInput = " i/-1 d/valid input";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(e.getMessage(), ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        String userInput = " i/2 d/valid input";
        EditAssignmentCommand c = new EditAssignmentCommand(ParserUtil.parseIndex("2"),
                new Description("valid input"));
        assertEquals(parser.parse(userInput), c);
    }
}
