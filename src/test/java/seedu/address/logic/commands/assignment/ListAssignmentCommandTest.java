package seedu.address.logic.commands.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.AssignmentBetweenStartandEndPredicate;

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

    @Test
    public void execute_listIsFiltered_showsFilteredList() {
        String[] dates = new String[] {"2023-11-31 18:45", "2024-11-31 18:45"};
        List<String> datesAsList = Arrays.asList(dates);

        CommandResult commandResult =
                new ListAssignmentCommand(new AssignmentBetweenStartandEndPredicate(datesAsList)).execute(model);

        assertEquals(
                String.format(Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW,
                        model.getFilteredAssignmentList().size()),
                commandResult.getFeedbackToUser()
        );
    }
}
