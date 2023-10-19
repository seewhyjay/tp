package seedu.address.logic.commands.assignment;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListAssignmentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(
                new ListAssignmentCommand(),
                model,
                String.format(Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size()),
                expectedModel
        );
    }

    // This one not relevant now
    //    @Test
    //    public void execute_listIsFiltered_showsEverything() {
    //        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);
    //        assertCommandSuccess(
    //                new ListAssignmentCommand(),
    //                model,
    //                String.format(Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW,
    //                model.getFilteredAssignmentList().size()),
    //                expectedModel
    //        );
    //    }
}
