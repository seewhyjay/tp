package seedu.address.logic.parser.assignment;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.assignment.EditAssignmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.person.Prefix;
import seedu.address.model.assignment.Description;

/**
 * Parses an input that starts with edit-a
 */
public class EditAssignmentParser {

    /**
     * Parses the input command that starts with edit-a
     * @param args The input command from the user
     * @return The EditAssignmentCommand which can be executed
     * @throws ParseException
     */
    public EditAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_DESCRIPTION);
        if (!arePrefixesPresent(argumentMultimap, PREFIX_NAME, PREFIX_ENDDATE)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAssignmentCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_INDEX).get());
        Description newDescription = ParserUtil.parseDescription(
                argumentMultimap.getValue(PREFIX_DESCRIPTION).get());
        return new EditAssignmentCommand(index, newDescription);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
