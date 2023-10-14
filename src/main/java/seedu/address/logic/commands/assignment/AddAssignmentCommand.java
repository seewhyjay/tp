package seedu.address.logic.commands.assignment;

import static java.util.Objects.requireNonNull;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "add-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": TO BE EDITED!!!";

    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";

    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in the address book";

    private final Assignment toAdd;

    public AddAssignmentCommand(Assignment assignment) {
        requireNonNull(assignment);
        this.toAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAssignment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.addAssignment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, "Assignment"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAssignmentCommand)) {
            return false;
        }

        AddAssignmentCommand otherAddCommand = (AddAssignmentCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }

}
