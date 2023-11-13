package seedu.address.logic.parser.internship.task;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.internship.task.UnMarkInternshipTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new MarkAssignmentCommand object
 */
public class UnMarkInternshipTaskParser implements Parser<UnMarkInternshipTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnMarkInternshipTaskCommand
     * and returns a UnMarkInternshipTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnMarkInternshipTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnMarkInternshipTaskCommand(index);
        } catch (ParseException parseException) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            UnMarkInternshipTaskCommand.MESSAGE_USAGE), parseException);
        }
    }
}
