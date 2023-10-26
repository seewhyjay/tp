package seedu.address.logic.commands.internship.task;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.internship.InternshipCommand;
import seedu.address.model.Model;

public class AddInternshipTaskCommand extends InternshipCommand {
    public static final String MESSAGE_USAGE = "TO BE MODIFIED, from AddInternshipRole";

    public AddInternshipTaskCommand() {}

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(null);
    }

}
