package seedu.address.logic.commands.internship.role;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_OUTCOME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.InternshipCommand;
import seedu.address.model.Model;
import seedu.address.model.fields.ApplicationOutcome;
import seedu.address.model.internship.role.InternshipRole;

/**
 * A command that edits the outcome of an InternshipRole when executed.
 */
public class EditInternshipRoleCommand extends InternshipCommand {
    public static final String COMMAND_WORD = "edit-i-role";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the outcome of an InternshipRole to the app. "
            + "Parameters: "
            + "[" + PREFIX_INDEX + "INDEX (must be a positive integer)" + "]\n"
            + "[" + PREFIX_OUTCOME + "OUTCOME" + "]\n"
            + "Example: " + COMMAND_WORD + "i/ 1" + " o/ accepted";

    public static final String MESSAGE_SUCCESS = "Internship role outcome updated to: %1$s";

    public static final String MESSAGE_INVALID_ROLE = "This internship role does not exist in the address book";

    private final Index index;
    private final ApplicationOutcome newOutcome;

    /**
     * The constructor for an EditInternshipRoleCommand
     * @param index The index of the InternshipRole to be edited
     * @param newOutcome The new outcome for the target InternshipRole
     */
    public EditInternshipRoleCommand(Index index, ApplicationOutcome newOutcome) {
        this.index = index;
        this.newOutcome = newOutcome;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<InternshipRole> lastShownList = model.getFilteredInternshipRoleList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        InternshipRole roleToEdit = lastShownList.get(index.getZeroBased());
        if (!model.hasInternshipRole(roleToEdit)) {
            throw new CommandException(MESSAGE_INVALID_ROLE);
        }

        InternshipRole roleWithNewOutcome = roleToEdit.getNewInternshipRoleWithOutcome(newOutcome);
        model.setInternshipRole(roleToEdit, roleWithNewOutcome);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(roleWithNewOutcome)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInternshipRoleCommand)) {
            return false;
        }

        EditInternshipRoleCommand otherEditInternshipRoleCommand = (EditInternshipRoleCommand) other;
        return index.equals(otherEditInternshipRoleCommand.index)
                && newOutcome.equals(otherEditInternshipRoleCommand.newOutcome);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("edit", index)
                .add("outcome", newOutcome)
                .toString();
    }
}
