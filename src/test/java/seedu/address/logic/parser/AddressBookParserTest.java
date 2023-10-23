package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.assignment.DeleteAssignmentCommand;
import seedu.address.logic.commands.assignment.EditAssignmentCommand;
import seedu.address.logic.commands.assignment.FindAssignmentCommand;
import seedu.address.logic.commands.assignment.ListAssignmentCommand;
import seedu.address.logic.commands.assignment.MarkAssignmentCommand;
import seedu.address.logic.commands.assignment.SortAssignmentCommand;
import seedu.address.logic.commands.assignment.UnMarkAssignmentCommand;
import seedu.address.logic.commands.person.AddCommand;
import seedu.address.logic.commands.person.DeleteCommand;
import seedu.address.logic.commands.person.EditCommand;
import seedu.address.logic.commands.person.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.person.FindCommand;
import seedu.address.logic.commands.person.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.View;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private static final String MESSAGE_WRONG_VIEW_FIRST_HALF = "Pls switch to ";

    private static final String MESSAGE_WRONG_VIEW_SECOND_HALF = " before performing this operation "
            + "using the respective list commands";

    private static final Function<View, Boolean> verifierTrue = (v) -> true;

    private static final Function<View, Boolean> verifierFalse = (v) -> false;

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommandAddCorrectView_success() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person), verifierTrue);
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommandAddWrongView_failure() {
        Person person = new PersonBuilder().build();
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.PERSONS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(PersonUtil.getAddCommand(person), verifierFalse));
    }

    @Test
    public void parseCommandCorrectViewClear_success() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, verifierTrue) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", verifierTrue) instanceof ClearCommand);
    }

    @Test
    public void parseCommandWrongViewClear_success() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, verifierFalse) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", verifierFalse)
                instanceof ClearCommand);
    }

    @Test
    public void parseCommandCorrectViewDelete_success() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(), verifierTrue);
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommandWrongViewDelete_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.PERSONS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(
                        DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(), verifierFalse));
    }

    @Test
    public void parseCommandCorrectViewEdit_success() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor),
                verifierTrue);
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommandWrongViewEdit_failure() {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.PERSONS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                                + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor),
                        verifierFalse));
    }


    @Test
    public void parseCommandCorrectViewExit_success() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, verifierTrue) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", verifierTrue) instanceof ExitCommand);
    }

    @Test
    public void parseCommandWrongViewExit_success() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, verifierFalse) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", verifierFalse) instanceof ExitCommand);
    }

    @Test
    public void parseCommandFindCorrectView_success() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")),
                verifierTrue);
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommandFindWrongView_failure() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.PERSONS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(FindCommand.COMMAND_WORD + " "
                                + keywords.stream().collect(Collectors.joining(" ")),
                        verifierFalse));
    }

    @Test
    public void parseCommandCorrectViewHelp_success() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, verifierTrue) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", verifierTrue) instanceof HelpCommand);
    }

    @Test
    public void parseCommandWrongViewHelp_success() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, verifierFalse) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", verifierFalse) instanceof HelpCommand);
    }

    @Test
    public void parseCommandCorrectViewList_success() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, verifierTrue) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3", verifierTrue) instanceof ListCommand);
    }

    @Test
    public void parseCommandWrongViewList_success() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, verifierFalse) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3", verifierFalse) instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", verifierTrue));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser
                .parseCommand("unknownCommand", verifierTrue));
    }

    //========= Assignments =================================================================================

    @Test
    public void parseCommandCorrectViewListA_success() throws Exception {
        assertTrue(parser.parseCommand(ListAssignmentCommand.COMMAND_WORD, verifierTrue)
                instanceof ListAssignmentCommand);
    }

    @Test
    public void parseCommandWrongViewListA_success() throws Exception {
        assertTrue(parser.parseCommand(ListAssignmentCommand.COMMAND_WORD, verifierFalse)
                instanceof ListAssignmentCommand);
    }

    @Test
    public void parseCommandCorrectViewSortA_success() throws Exception {
        assertTrue(parser.parseCommand(SortAssignmentCommand.COMMAND_WORD, verifierTrue)
                instanceof SortAssignmentCommand);
    }

    @Test
    public void parseCommandWrongViewSortA_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.ASSIGNMENTS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(SortAssignmentCommand.COMMAND_WORD, verifierFalse));
    }

    @Test
    public void parseCommandCorrectViewFindA_success() throws Exception {
        assertTrue(parser.parseCommand(FindAssignmentCommand.COMMAND_WORD + " 1", verifierTrue)
                instanceof FindAssignmentCommand);
    }

    @Test
    public void parseCommandWrongViewFindA_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.ASSIGNMENTS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(FindAssignmentCommand.COMMAND_WORD, verifierFalse));
    }

    @Test
    public void parseCommandCorrectViewDeleteA_success() throws Exception {
        assertTrue(parser.parseCommand(DeleteAssignmentCommand.COMMAND_WORD + " 1", verifierTrue)
                instanceof DeleteAssignmentCommand);
    }

    @Test
    public void parseCommandWrongViewDeleteA_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.ASSIGNMENTS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(DeleteAssignmentCommand.COMMAND_WORD, verifierFalse));
    }

    @Test
    public void parseCommandCorrectViewMarkA_success() throws Exception {
        assertTrue(parser.parseCommand(MarkAssignmentCommand.COMMAND_WORD + " 1", verifierTrue)
                instanceof MarkAssignmentCommand);
    }

    @Test
    public void parseCommandWrongViewMarkA_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.ASSIGNMENTS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(MarkAssignmentCommand.COMMAND_WORD + " 1", verifierFalse));
    }

    @Test
    public void parseCommandCorrectViewUnmarkA_success() throws Exception {
        assertTrue(parser.parseCommand(UnMarkAssignmentCommand.COMMAND_WORD + " 1", verifierTrue)
                instanceof UnMarkAssignmentCommand);
    }

    @Test
    public void parseCommandWrongViewUnmarkA_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.ASSIGNMENTS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(UnMarkAssignmentCommand.COMMAND_WORD + " 1", verifierFalse));
    }

    @Test
    public void parseCommandWrongViewEditA_failure() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_WRONG_VIEW_FIRST_HALF + View.ASSIGNMENTS + MESSAGE_WRONG_VIEW_SECOND_HALF, () ->
                        parser.parseCommand(EditAssignmentCommand.COMMAND_WORD, verifierFalse));
    }

}
