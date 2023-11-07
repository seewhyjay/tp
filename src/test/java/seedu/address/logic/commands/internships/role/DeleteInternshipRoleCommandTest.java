package seedu.address.logic.commands.internships.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_IN_LIST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandAssignmentTestUtil;
import seedu.address.logic.commands.internship.role.DeleteInternshipRoleCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.testutil.TypicalInternshipRoles;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteInternshipRoleCommand}.
 */

public class DeleteInternshipRoleCommandTest {

    private Model model = new ModelManager(TypicalInternshipRoles.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        InternshipRole internshipRoleToDelete = model.getFilteredInternshipRoleList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        DeleteInternshipRoleCommand deleteCommand = new DeleteInternshipRoleCommand(INDEX_FIRST_IN_LIST);

        String expectedMessage = String.format(DeleteInternshipRoleCommand.MESSAGE_DELETE_INTERNSHIP_ROLE_SUCCESS,
                Messages.format(internshipRoleToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteInternshipRole(internshipRoleToDelete);
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        InternshipRole internshipRoleToDelete = model.getFilteredInternshipRoleList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        DeleteInternshipRoleCommand deleteCommand = new DeleteInternshipRoleCommand(INDEX_FIRST_IN_LIST);

        String expectedMessage = String.format(DeleteInternshipRoleCommand.MESSAGE_DELETE_INTERNSHIP_ROLE_SUCCESS,
                Messages.format(internshipRoleToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteInternshipRole(internshipRoleToDelete);
        showNoAssignment(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipRoleList().size() + 1);
        DeleteInternshipRoleCommand deleteCommand = new DeleteInternshipRoleCommand(outOfBoundIndex);

        CommandAssignmentTestUtil.assertCommandFailure(deleteCommand, model,
                Messages.MESSAGE_INVALID_INTERNSHIP_ROLE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteInternshipRoleCommand deleteFirstCommand = new DeleteInternshipRoleCommand(INDEX_FIRST_IN_LIST);
        DeleteInternshipRoleCommand deleteSecondCommand = new DeleteInternshipRoleCommand(INDEX_SECOND_IN_LIST);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteInternshipRoleCommand deleteFirstCommandCopy = new DeleteInternshipRoleCommand(INDEX_FIRST_IN_LIST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteInternshipRoleCommand deleteCommand = new DeleteInternshipRoleCommand(targetIndex);
        String expected = DeleteInternshipRoleCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAssignment(Model model) {
        model.updateFilteredInternshipRoleList(p -> false);

        assertTrue(model.getFilteredInternshipRoleList().isEmpty());
    }
}
