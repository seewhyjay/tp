package seedu.address.logic.commands.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkAssignmentCommand}.
 */

public class MarkAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_mark_success() {
        Assignment assignmentToMark = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        model.markAsComplete(assignmentToMark);
        assertTrue(assignmentToMark.getStatus().toString().equals("complete"));
    }

    @Test
    public void execute_markAlreadyCompletedAssignment_throwsCommandException() {
        Assignment assignmentToMark = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        MarkAssignmentCommand markCommand = new MarkAssignmentCommand(INDEX_FIRST_ASSIGNMENT);

        model.markAsComplete(assignmentToMark);
        assertCommandFailure(markCommand, model,
                String.format(MarkAssignmentCommand.MESSAGE_ASSIGNMENT_ALREADY_COMPLETE));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        MarkAssignmentCommand markCommand = new MarkAssignmentCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkAssignmentCommand markFirstCommand = new MarkAssignmentCommand(INDEX_FIRST_ASSIGNMENT);
        MarkAssignmentCommand markSecondCommand = new MarkAssignmentCommand(INDEX_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkAssignmentCommand markFirstCommandCopy = new MarkAssignmentCommand(INDEX_FIRST_ASSIGNMENT);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        MarkAssignmentCommand markCommand = new MarkAssignmentCommand(targetIndex);
        String expected = MarkAssignmentCommand.class.getCanonicalName() + "{mark=" + targetIndex + "}";
        assertEquals(expected, markCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAssignment(Model model) {
        model.updateFilteredAssignmentList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
