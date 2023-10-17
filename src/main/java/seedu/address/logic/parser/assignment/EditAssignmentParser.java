package seedu.address.logic.parser.assignment;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.assignment.Description;
import seedu.address.logic.commands.assignment.EditAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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
        try {
            ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_DESCRIPTION);
            Index index = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_INDEX).get());
            Description newDescription = ParserUtil.parseDescription(
                    argumentMultimap.getValue(PREFIX_DESCRIPTION).get());
            return new EditAssignmentCommand(index, newDescription);
        } catch (ParseException parseException) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAssignmentCommand.MESSAGE_USAGE), parseException);
        }
    }
}
