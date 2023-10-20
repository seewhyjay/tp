package seedu.address.logic.commands.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Description;

public class EditAssignmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        String newDesc = "new desc";

        EditAssignmentCommand editCommand = new EditAssignmentCommand(INDEX_FIRST_ASSIGNMENT,
                new Description(newDesc));

        String commandResult = editCommand.execute(model).getFeedbackToUser();
        assertTrue(commandResult.contains(newDesc));
        assertEquals(newDesc,
                model.getFilteredAssignmentList()
                        .get(INDEX_FIRST_ASSIGNMENT.getZeroBased())
                        .getDescription().toString());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        EditAssignmentCommand editCommand = new EditAssignmentCommand(outOfBoundIndex, new Description(""));
        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

}
