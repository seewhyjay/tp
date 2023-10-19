package seedu.address.logic.parser.assigment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_DATE_WITH_TIME_EARLIER;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_DATE_WITH_TIME_LATER;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_DATE_WITHOUT_TIME_EARLIER;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_DATE_WITHOUT_TIME_LATER;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_DATE_INFINITE_EARLY;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_DATE_INFINITE_LATE;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_STARTFILTER;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_ENDFILTER;
import static seedu.address.logic.commands.assignment.ListAssignmentCommand.COMMAND_WORD;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.assignment.AddAssignmentCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.assignment.ListAssignmentCommand;
import seedu.address.logic.commands.person.AddCommand;
import seedu.address.logic.parser.assignment.ListAssignmentParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.*;

public class ListAssignmentParserTest {
    private final ListAssignmentParser parser = new ListAssignmentParser();

    @Test
    public void parse_noFieldsPresent_success() throws ParseException {
        String validInput = " ";

        String[] dates = new String[] {VALID_DATE_INFINITE_EARLY, VALID_DATE_INFINITE_LATE};
        AssignmentBetweenStartandEndPredicate predicate =
                new AssignmentBetweenStartandEndPredicate(Arrays.asList(dates));

        assertParseSuccess(parser, validInput, new ListAssignmentCommand(predicate));
    }

    @Test
    public void parse_allFieldsPresentWithTime_success() throws ParseException {
        String validInput = " " + PREFIX_STARTFILTER + VALID_DATE_WITH_TIME_EARLIER + " "
                + PREFIX_ENDFILTER + VALID_DATE_WITH_TIME_LATER;

        String[] dates = new String[] {VALID_DATE_WITH_TIME_EARLIER, VALID_DATE_WITH_TIME_LATER};
        AssignmentBetweenStartandEndPredicate predicate =
                new AssignmentBetweenStartandEndPredicate(Arrays.asList(dates));

        assertParseSuccess(parser, validInput, new ListAssignmentCommand(predicate));
    }

    @Test
    public void parse_allFieldsPresentWithoutTime_success() throws ParseException {
        String validInput = " " + PREFIX_STARTFILTER + VALID_DATE_WITHOUT_TIME_EARLIER + " "
                + PREFIX_ENDFILTER + VALID_DATE_WITHOUT_TIME_LATER;

        String[] dates = new String[] {VALID_DATE_WITHOUT_TIME_EARLIER + " 00:00",
                VALID_DATE_WITHOUT_TIME_LATER + " 23:59"};
        AssignmentBetweenStartandEndPredicate predicate =
                new AssignmentBetweenStartandEndPredicate(Arrays.asList(dates));

        assertParseSuccess(parser, validInput, new ListAssignmentCommand(predicate));
    }

    @Test
    public void parse_onlyStartDateWithTime_success() throws ParseException {
        String validInput = " " + PREFIX_STARTFILTER + VALID_DATE_WITH_TIME_EARLIER;

        String[] dates = new String[] {VALID_DATE_WITH_TIME_EARLIER, VALID_DATE_INFINITE_LATE};
        AssignmentBetweenStartandEndPredicate predicate =
                new AssignmentBetweenStartandEndPredicate(Arrays.asList(dates));

        assertParseSuccess(parser, validInput, new ListAssignmentCommand(predicate));
    }

    @Test
    public void parse_onlyEndDateWithTime_success() throws ParseException {
        String validInput = " " + PREFIX_ENDFILTER + VALID_DATE_WITH_TIME_LATER;

        String[] dates = new String[] {VALID_DATE_INFINITE_EARLY, VALID_DATE_WITH_TIME_LATER};
        AssignmentBetweenStartandEndPredicate predicate =
                new AssignmentBetweenStartandEndPredicate(Arrays.asList(dates));

        assertParseSuccess(parser, validInput, new ListAssignmentCommand(predicate));
    }

    @Test
    public void parse_onlyStartDateWithoutTime_success() throws ParseException {
        String validInput = " " + PREFIX_STARTFILTER + VALID_DATE_WITHOUT_TIME_EARLIER;

        String[] dates = new String[] {VALID_DATE_WITHOUT_TIME_EARLIER + " 00:00", VALID_DATE_INFINITE_LATE};
        AssignmentBetweenStartandEndPredicate predicate =
                new AssignmentBetweenStartandEndPredicate(Arrays.asList(dates));

        assertParseSuccess(parser, validInput, new ListAssignmentCommand(predicate));
    }

    @Test
    public void parse_onlyEndDateWithoutTime_success() throws ParseException {
        String validInput = " " + PREFIX_ENDFILTER + VALID_DATE_WITHOUT_TIME_LATER;

        String[] dates = new String[] {VALID_DATE_INFINITE_EARLY, VALID_DATE_WITHOUT_TIME_LATER + " 23:59"};
        AssignmentBetweenStartandEndPredicate predicate =
                new AssignmentBetweenStartandEndPredicate(Arrays.asList(dates));

        assertParseSuccess(parser, validInput, new ListAssignmentCommand(predicate));
    }

    @Test
    public void parse_invalidDateFormat_fail() throws ParseException {
        String invalidInput = " s/200 e/22";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(invalidInput));
        assertParseFailure(parser, invalidInput, IsoDate.MESSAGE_CONSTRAINTS);
    }

}
