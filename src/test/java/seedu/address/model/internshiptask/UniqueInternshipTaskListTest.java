package seedu.address.model.internshiptask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternshipRoles.getTypicalInternshipRole2;
import static seedu.address.testutil.TypicalInternshipTasks.getTypicalInternshipTask1;
import static seedu.address.testutil.TypicalInternshipTasks.getTypicalInternshipTask2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.fields.Outcome;
import seedu.address.model.internshiptask.exceptions.DuplicateInternshipTaskException;
import seedu.address.model.internshiptask.exceptions.InternshipTaskNotFoundException;
import seedu.address.testutil.InternshipTaskBuilder;

public class UniqueInternshipTaskListTest {

    private final InternshipTask task1 = getTypicalInternshipTask1();
    private final InternshipTask task2 = getTypicalInternshipTask2();

    private final String desc = "123";

    private final LocalDateTime date = LocalDateTime.parse("2070-04-01 23:59",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    private final UniqueInternshipTaskList uniqueTaskList = new UniqueInternshipTaskList();

    @Test
    public void contains_nullInternshipTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(task1));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(task1);
        assertTrue(uniqueTaskList.contains(task1));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(task1);
        InternshipTask editedTask = new InternshipTaskBuilder(task1)
                .withDeadline(date)
                .withOutcome(Outcome.REJECTED)
                .withStatus(true)
                .build();
        assertTrue(uniqueTaskList.contains(editedTask));
    }

    @Test
    public void contains_taskWithDifferentRole_returnsFalse() {
        uniqueTaskList.add(task1);
        InternshipTask editedTask = new InternshipTaskBuilder(task1)
                .withInternshipRole(getTypicalInternshipRole2())
                .withDeadline(date)
                .withOutcome(Outcome.REJECTED)
                .withStatus(true)
                .build();
        assertFalse(uniqueTaskList.contains(editedTask));
    }

    @Test
    public void contains_taskWithDifferentTaskName_returnsFalse() {
        uniqueTaskList.add(task1);
        InternshipTask editedTask = new InternshipTaskBuilder(task1)
                .withTaskName(desc)
                .withDeadline(date)
                .withOutcome(Outcome.REJECTED)
                .withStatus(true)
                .build();
        assertFalse(uniqueTaskList.contains(editedTask));
    }

    @Test
    public void add_nullInternshipTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateInternshipTask_throwsDuplicateInternshipTaskException() {
        uniqueTaskList.add(task1);
        assertThrows(DuplicateInternshipTaskException.class, () -> uniqueTaskList.add(task1));
    }

    @Test
    public void setTask_nullTargetInternshipTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, task1));
    }

    @Test
    public void setTask_nullEditedInternshipTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(task1, null));
    }

    @Test
    public void setTask_targetInternshipTaskNotInList_throwsInternshipTaskNotFoundException() {
        assertThrows(InternshipTaskNotFoundException.class, () -> uniqueTaskList.setTask(task1, task1));
    }

    @Test
    public void setTask_editedInternshipTaskIsSameInternshipTask_success() {
        uniqueTaskList.add(task1);
        uniqueTaskList.setTask(task1, task1);
        UniqueInternshipTaskList expectedUniqueInternshipTaskList = new UniqueInternshipTaskList();
        expectedUniqueInternshipTaskList.add(task1);
        assertEquals(expectedUniqueInternshipTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedInternshipTaskHasSameIdentity_success() {
        uniqueTaskList.add(task1);
        InternshipTask editedTask = new InternshipTaskBuilder(task1)
                .withStatus(true)
                .withOutcome(Outcome.REJECTED)
                .withDeadline(date)
                .withTags("tag")
                .build();
        uniqueTaskList.setTask(task1, editedTask);
        UniqueInternshipTaskList expectedUniqueInternshipTaskList = new UniqueInternshipTaskList();
        expectedUniqueInternshipTaskList.add(editedTask);
        assertEquals(expectedUniqueInternshipTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedInternshipTaskHasDifferentIdentity_success() {
        uniqueTaskList.add(task1);
        uniqueTaskList.setTask(task1, task2);
        UniqueInternshipTaskList expectedUniqueInternshipTaskList = new UniqueInternshipTaskList();
        expectedUniqueInternshipTaskList.add(task2);
        assertEquals(expectedUniqueInternshipTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedInternshipTaskHasNonUniqueIdentity_throwsDuplicateInternshipTaskException() {
        uniqueTaskList.add(task1);
        uniqueTaskList.add(task2);
        assertThrows(DuplicateInternshipTaskException.class, () -> uniqueTaskList.setTask(task1, task2));
    }

    @Test
    public void remove_nullInternshipTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsInternshipTaskNotFoundException() {
        assertThrows(InternshipTaskNotFoundException.class, () -> uniqueTaskList.remove(task1));
    }

    @Test
    public void remove_existingInternshipTask_removesInternshipTask() {
        uniqueTaskList.add(task1);
        uniqueTaskList.remove(task1);
        UniqueInternshipTaskList expectedUniqueInternshipTaskList = new UniqueInternshipTaskList();
        assertEquals(expectedUniqueInternshipTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullUniqueInternshipTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueInternshipTaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedUniqueInternshipTaskList() {
        uniqueTaskList.add(task1);
        UniqueInternshipTaskList expectedUniqueInternshipTaskList = new UniqueInternshipTaskList();
        expectedUniqueInternshipTaskList.add(task2);
        uniqueTaskList.setTasks(expectedUniqueInternshipTaskList);
        assertEquals(expectedUniqueInternshipTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<InternshipTask>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.add(task1);
        List<InternshipTask> taskList = Collections.singletonList(task2);
        uniqueTaskList.setTasks(taskList);
        UniqueInternshipTaskList expectedUniqueInternshipTaskList = new UniqueInternshipTaskList();
        expectedUniqueInternshipTaskList.add(task2);
        assertEquals(expectedUniqueInternshipTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_listWithDuplicateInternshipTasks_throwsDuplicateInternshipTaskException() {
        List<InternshipTask> listWithDuplicateInternshipTasks = Arrays.asList(task1, task1);
        assertThrows(DuplicateInternshipTaskException.class, () -> uniqueTaskList
                .setTasks(listWithDuplicateInternshipTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueTaskList.asUnmodifiableObservableList().toString(), uniqueTaskList.toString());
    }
}
