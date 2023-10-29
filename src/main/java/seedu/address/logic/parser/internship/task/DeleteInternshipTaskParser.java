package seedu.address.logic.parser.internship.task;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.internship.task.DeleteInternshipTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteInternshipTaskCommand object
 */
public class DeleteInternshipTaskParser implements Parser<DeleteInternshipTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteInternshipTaskCommand
     * and returns a DeleteInternshipTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteInternshipTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteInternshipTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInternshipTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
