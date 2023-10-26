package seedu.address.logic.commands.internship.task;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.InternshipCommand;
import seedu.address.model.Model;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Status;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.internshiprole.InternshipRole;
import seedu.address.model.internshiptask.InternshipTask;
import seedu.address.model.tag.Tag;

import java.util.Set;

import static java.util.Objects.requireNonNull;

public class AddInternshipTaskCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "add-i-task";
    public static final String MESSAGE_USAGE = "TO BE MODIFIED, from AddInternshipTask";
    public static final String MESSAGE_SUCCESS = "New internship task added: %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This internship task already exists in the address book";
    public static final String MESSAGE_INVALID_ROLE_INDEX = "There is no such internship role with that index";
    private final Index internshipRoleIndex;
    private final Name taskName;
    private final IsoDate deadline;
    private final Status status;
    private final TaskOutcome outcome;
    private final Set<Tag> tags;


    public AddInternshipTaskCommand(Index internshipRoleIndex, Name taskName, IsoDate deadline,
                                    Status status, TaskOutcome outcome, Set<Tag> tags) {
        requireNonNull(internshipRoleIndex);
        requireNonNull(taskName);
        requireNonNull(deadline);
        requireNonNull(status);
        requireNonNull(outcome);
        requireNonNull(tags);
        this.internshipRoleIndex = internshipRoleIndex;
        this.taskName = taskName;
        this.deadline = deadline;
        this.status = status;
        this.outcome = outcome;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getUnfilteredInternshipRoleList().size() < internshipRoleIndex.getOneBased() ||
        internshipRoleIndex.getOneBased() < 0) {
            throw new CommandException(MESSAGE_INVALID_ROLE_INDEX);
        }

        InternshipRole internshipRole = model.getUnfilteredInternshipRoleList().get(internshipRoleIndex.getZeroBased());

        InternshipTask toAdd = new InternshipTask(internshipRole, taskName, deadline, status, outcome, tags);

        if (model.hasInternshipTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.addInternshipTask(toAdd);
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
        return this.internshipRoleIndex.equals(otherAddCommand.internshipRoleIndex)
                && this.taskName.equals(otherAddCommand.taskName)
                && this.deadline.equals(otherAddCommand.deadline)
                && this.status.equals(otherAddCommand.status)
                && this.outcome.equals(otherAddCommand.outcome)
                && this.tags.equals(otherAddCommand.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("internshipRoleIndex", internshipRoleIndex)
                .add("taskName", taskName)
                .add("deadline", deadline)
                .add("status", status)
                .add("outcome", outcome)
                .add("tags", tags)
                .toString();
    }


}
