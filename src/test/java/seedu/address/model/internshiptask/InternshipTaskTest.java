package seedu.address.model.internshiptask;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternshipTasks.getTypicalInternshipTask1;
import static seedu.address.testutil.TypicalInternshipTasks.getTypicalInternshipTask2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.fields.Outcome;
import seedu.address.testutil.InternshipTaskBuilder;

public class InternshipTaskTest {
    private final InternshipTask task1 = getTypicalInternshipTask1();

    private final InternshipTask task2 = getTypicalInternshipTask2();

    private final String obscureInput = "123";

    private final LocalDateTime date2 = LocalDateTime.parse("2024-06-01 23:59",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        InternshipTask role = new InternshipTaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> role.getTags().remove(0));
    }

    @Test
    public void isSameInternshipRole() {
        // same object -> returns true
        assertTrue(task1.isSameInternshipTask(task1));

        // null -> returns false
        assertFalse(task1.isSameInternshipTask(null));

        // same name and role, all other attributes different -> returns true
        InternshipTask editedTask = new InternshipTaskBuilder(task1)
                .withStatus(true)
                .withOutcome(Outcome.REJECTED)
                .withDeadline(date2)
                .withTags("tag").build();

        assertTrue(task1.isSameInternshipTask(editedTask));

        // different name all other attributes same -> returns false
        editedTask = new InternshipTaskBuilder(task1).withName(obscureInput).build();
        assertFalse(task1.isSameInternshipTask(editedTask));
    }

    @Test
    public void equals() {
        // same values -> returns true
        InternshipTask internshipTaskCopy = getTypicalInternshipTask1();
        assertTrue(task1.equals(internshipTaskCopy));

        // same object -> returns true
        assertTrue(task1.equals(task1));

        // null -> returns false
        assertFalse(task1.equals(null));

        // different type -> returns false
        assertFalse(task1.equals(5));

        // different person -> returns false
        assertFalse(task1.equals(task2));

        // different name -> returns false
        InternshipTask editedTask = new InternshipTaskBuilder(task1).withName(obscureInput).build();
        assertFalse(task1.equals(editedTask));

        // different status -> returns false
        editedTask = new InternshipTaskBuilder(task1).withStatus(true).build();
        assertFalse(task1.equals(editedTask));

        // different outcome -> returns false
        editedTask = new InternshipTaskBuilder(task1).withOutcome(Outcome.REJECTED).build();
        assertFalse(task1.equals(editedTask));

        // different deadline -> returns false
        editedTask = new InternshipTaskBuilder(task1).withDeadline(date2).build();
        assertFalse(task1.equals(editedTask));

        // different tags -> returns false
        editedTask = new InternshipTaskBuilder(task1).withTags("tag").build();
        assertFalse(task1.equals(editedTask));
    }
}
