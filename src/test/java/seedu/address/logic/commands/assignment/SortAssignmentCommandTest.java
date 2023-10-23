package seedu.address.logic.commands.assignment;

import static seedu.address.logic.Messages.MESSAGE_ASSIGNMENTS_SORTED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.View;

/**
 * Contains integration tests (interaction with the Model) for {@code SortAssignmentCommand}.
 */
public class SortAssignmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void init() {
        model.setView(View.ASSIGNMENTS);
    }

    @Test
    public void execute_sortAssignmentCommand_assignmentsAreSorted() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_SORTED);
        SortAssignmentCommand command = new SortAssignmentCommand();
        expectedModel.sortAssignments();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_wrongOnWrongViewValidInput_throwsCommandException() {
        model.setView(View.PERSONS);
        SortAssignmentCommand command = new SortAssignmentCommand();
        CommandTestUtil.assertCommandFailure(command, model, Model.MESSAGE_WRONG_VIEW_FIRST_HALF
                + View.ASSIGNMENTS + Model.MESSAGE_WRONG_VIEW_SECOND_HALF);
    }
}
