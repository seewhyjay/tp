package seedu.address.logic.commands.internships.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.assertCommandSuccess;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandAssignmentTestUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.role.EditInternshipRoleCommand;
import seedu.address.logic.commands.internship.role.FindInternshipRoleCommand;
import seedu.address.logic.commands.internship.task.FindInternshipTaskCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.fields.ApplicationOutcome;
import seedu.address.model.fields.Cycle;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.Location;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Pay;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.role.InternshipRoleNameContainsKeywordsPredicate;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.model.internship.task.InternshipTaskContainsInternshipRolesPredicate;
import seedu.address.model.internship.task.InternshipTaskNameContainsKeywordsPredicate;
import seedu.address.testutil.InternshipRoleBuilder;
import seedu.address.testutil.InternshipTaskBuilder;

public class EditInternshipRoleCommandTest {
    private final InternshipRole role1 = new InternshipRoleBuilder().withName("Company1")
            .withRole("SWE").withCycle("Summer").build();

    private final InternshipRole role2 = new InternshipRoleBuilder().withName("Company1")
            .withRole("SWE").withCycle("Winter").build();

    private final InternshipTask task1BelongingToRole1 = new InternshipTaskBuilder()
            .withInternshipRole(role1).withTaskName("Interview Preparation_1").build();

    private final InternshipTask task2BelongingToRole1 = new InternshipTaskBuilder()
            .withInternshipRole(role1).withTaskName("OA_1").build();

    private final InternshipTask task1BelongingToRole2 = new InternshipTaskBuilder()
            .withInternshipRole(role2).withTaskName("Interview Preparation_2").build();

    private final InternshipTask task2BelongingToRole2 = new InternshipTaskBuilder()
            .withInternshipRole(role2).withTaskName("OA_2").build();

    private ModelManager model;

    private AddressBook getAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addInternshipRole(role1);
        ab.addInternshipRole(role2);
        ab.addInternshipTask(task1BelongingToRole1);
        ab.addInternshipTask(task2BelongingToRole1);
        ab.addInternshipTask(task1BelongingToRole2);
        ab.addInternshipTask(task2BelongingToRole2);
        return ab;
    }

    private void editRolesAndTask(InternshipRole roleToBeEdited, InternshipRole editedRole, ModelManager model) {
        for (InternshipRole r : model.getFilteredInternshipRoleList()) {
            if (r.equals(roleToBeEdited)) {
                model.setInternshipRole(r, editedRole);
            }
        }

        for (InternshipTask t : model.getUnfilteredInternshipTaskList()) {
            if (t.getInternshipRole().equals(roleToBeEdited)) {
                model.setInternshipTask(t, t.editInternshipRole(editedRole));
            }
        }
    }

    @BeforeEach
    public void init() {
        model = new ModelManager(getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_allFieldsProvided_allRoleAndAssociatedTasksEdited() {
        Index index = Index.fromOneBased(1);
        EditInternshipRoleCommand editCommand =
                new EditInternshipRoleCommand(index, new Cycle("newCycle"), new Description("newDesc"),
                new Pay(new BigDecimal("2000")), new ApplicationOutcome(Outcome.ACCEPTED),
                new Location("newLocation"));

        ModelManager expectedModel = new ModelManager(getAddressBook(), new UserPrefs());

        InternshipRole editedRole = new InternshipRoleBuilder().withName("Company1")
                .withRole("SWE").withCycle("newCycle").withDescription("newDesc").withPay(new BigDecimal("2000"))
                .withOutcome(Outcome.ACCEPTED).withLocation("newLocation").build();

        editRolesAndTask(role1, editedRole, expectedModel);

        String expectedMessage = String.format(EditInternshipRoleCommand.MESSAGE_SUCCESS, Messages.format(editedRole));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_roleWithoutChangingCycle_allRoleAndAssociatedTasksEdited() {
        Index index = Index.fromOneBased(1);
        EditInternshipRoleCommand editCommand =
                new EditInternshipRoleCommand(index, null, new Description("newDesc"),
                null, null, null);

        ModelManager expectedModel = new ModelManager(getAddressBook(), new UserPrefs());

        InternshipRole editedRole = role1.getNewInternshipRoleWithDescription(new Description("newDesc"));

        editRolesAndTask(role1, editedRole, expectedModel);

        String expectedMessage = String.format(EditInternshipRoleCommand.MESSAGE_SUCCESS,
                Messages.format(editedRole));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    // Integration Test with Find Task Command
    @Test
    public void execute_afterFindTaskCommand_allRoleAndAssociatedTasksEdited() throws CommandException {
        Index index = Index.fromOneBased(1);

        EditInternshipRoleCommand editCommand =
                new EditInternshipRoleCommand(index, new Cycle("newCycle"), new Description("newDesc"),
                null, null, null);

        FindInternshipTaskCommand findTaskCommand = new FindInternshipTaskCommand(new
                InternshipTaskNameContainsKeywordsPredicate(List.of("OA")));

        findTaskCommand.execute(model);

        InternshipRole editedRole = role1.getNewInternshipRoleWithDescription(new Description("newDesc"))
                .getNewInternshipRoleWithCycle(new Cycle("newCycle"));

        ModelManager expectedModel = new ModelManager(getAddressBook(), new UserPrefs());

        editRolesAndTask(role1, editedRole, expectedModel);

        expectedModel.updateFilteredInternshipTaskList(new InternshipTaskNameContainsKeywordsPredicate(
                List.of("OA")));

        expectedModel.updateFilteredInternshipRoleList(role -> {
            for (InternshipTask internshipTask : model.getFilteredInternshipTaskList()) {
                if (internshipTask.getInternshipRole().equals(role)) {
                    return true;
                }
            }
            return false;
        });

        String expectedMessage = String.format(EditInternshipRoleCommand.MESSAGE_SUCCESS,
                Messages.format(editedRole));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    // Integration Test with Find Role Command
    @Test
    public void execute_afterFindRoleCommand_allRoleAndAssociatedTasksEdited() throws CommandException {
        Index index = Index.fromOneBased(1);

        EditInternshipRoleCommand editCommand =
                new EditInternshipRoleCommand(index, new Cycle("newCycle"), new Description("newDesc"),
                null, null, null);

        FindInternshipRoleCommand findRoleCommand = new FindInternshipRoleCommand(new
                InternshipRoleNameContainsKeywordsPredicate(List.of("Company1")));

        findRoleCommand.execute(model);

        InternshipRole editedRole = role1.getNewInternshipRoleWithDescription(new Description("newDesc"))
                .getNewInternshipRoleWithCycle(new Cycle("newCycle"));

        ModelManager expectedModel = new ModelManager(getAddressBook(), new UserPrefs());

        editRolesAndTask(role1, editedRole, expectedModel);

        expectedModel.updateFilteredInternshipRoleList(new
                InternshipRoleNameContainsKeywordsPredicate(List.of("Company1")));

        InternshipTaskContainsInternshipRolesPredicate taskPredicate =
                new InternshipTaskContainsInternshipRolesPredicate(expectedModel.getFilteredInternshipRoleList());

        expectedModel.updateFilteredInternshipTaskList(taskPredicate);

        String expectedMessage = String.format(EditInternshipRoleCommand.MESSAGE_SUCCESS,
                Messages.format(editedRole));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipRoleList().size() + 1);
        EditInternshipRoleCommand editCommand =
                new EditInternshipRoleCommand(outOfBoundIndex, null, null,
                null, null, null);

        CommandAssignmentTestUtil.assertCommandFailure(editCommand, model,
                Messages.MESSAGE_INVALID_INTERNSHIP_ROLE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noChangeInFields_throwsCommandException() {
        Index index = Index.fromOneBased(model.getFilteredInternshipRoleList().size());
        EditInternshipRoleCommand editCommand =
                new EditInternshipRoleCommand(index, null, null,
                null, null, null);

        CommandAssignmentTestUtil.assertCommandFailure(editCommand, model,
                Messages.MESSAGE_EDITED_FIELDS_ARE_THE_SAME);
    }

    @Test
    public void execute_duplicateRoles_throwsCommandException() {
        Index index = Index.fromOneBased(1);
        EditInternshipRoleCommand editCommand =
                new EditInternshipRoleCommand(index, new Cycle("Winter"), null,
                null, null, null);
        CommandAssignmentTestUtil.assertCommandFailure(editCommand, model,
                Messages.MESSAGE_EDIT_LEADS_TO_DUPLICATE_ROLES);
    }


    @Test
    public void equals() {
        EditInternshipRoleCommand editFirstCommand = new EditInternshipRoleCommand(Index.fromOneBased(1),
                new Cycle("cycle1"), null, null, null, null);
        EditInternshipRoleCommand editSecondCommand = new EditInternshipRoleCommand(Index.fromOneBased(1),
                new Cycle("cycle2"), null, null, null, null);

        // same object -> returns true
        assertEquals(editFirstCommand, editFirstCommand);

        // same values -> returns true
        EditInternshipRoleCommand editFirstCommandCopy = new EditInternshipRoleCommand(Index.fromOneBased(1),
                new Cycle("cycle1"), null, null, null, null);
        assertEquals(editFirstCommand, editFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, editFirstCommand);

        // null -> returns false
        assertNotEquals(null, editFirstCommand);

        // different edit cmd -> returns false
        assertNotEquals(editFirstCommand, editSecondCommand);
    }


}
