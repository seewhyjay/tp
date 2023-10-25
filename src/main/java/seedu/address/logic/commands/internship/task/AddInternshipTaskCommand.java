package seedu.address.logic.commands.internship.task;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.InternshipCommand;
import seedu.address.model.Model;
import seedu.address.model.internshiptask.InternshipTask;

import static java.util.Objects.requireNonNull;

public class AddInternshipTaskCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "add-itask";
    public static final String MESSAGE_USAGE = "TO BE MODIFIED, from AddInternshipTask";
    public static final String MESSAGE_SUCCESS = "New internship task added: %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This internship task already exists in the address book";
    private final InternshipTask toAdd;


    /**
     * @param internshipTask to be added
     */
    public AddInternshipTaskCommand(InternshipTask internshipTask) {
        requireNonNull(internshipTask);
        this.toAdd = internshipTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInternshipTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.addInternshipTask(toAdd);
        model.sortAssignments();
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddInternshipTaskCommand)) {
            return false;
        }

        AddInternshipTaskCommand otherAddCommand = (AddInternshipTaskCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }


}
