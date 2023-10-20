package seedu.address.logic.commands.assignment;

import static seedu.address.logic.Messages.MESSAGE_ASSIGNMENTS_SORTED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SortAssignmentCommand}.
 */
public class SortAssignmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortAssignmentCommand_assignmentsAreSorted() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_SORTED);
        SortAssignmentCommand command = new SortAssignmentCommand();
        expectedModel.sortAssignments();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
