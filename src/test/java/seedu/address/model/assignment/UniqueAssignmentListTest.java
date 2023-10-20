package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ASSIGN_1;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT1;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;
import seedu.address.testutil.AssignmentBuilder;

public class UniqueAssignmentListTest {
    private final UniqueAssignmentList uniqueAssignmentList = new UniqueAssignmentList();

    @Test
    public void contains_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueAssignmentList.contains(ASSIGNMENT1));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueAssignmentList.add(ASSIGNMENT1);
        assertTrue(uniqueAssignmentList.contains(ASSIGNMENT1));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAssignmentList.add(ASSIGNMENT1);
        Assignment editedAssignment = new AssignmentBuilder(ASSIGNMENT1).withDescription(VALID_DESCRIPTION_1).build();
        assertTrue(uniqueAssignmentList.contains(editedAssignment));
    }

    @Test
    public void add_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.add(null));
    }

    @Test
    public void add_duplicateAssignment_throwsDuplicateAssignmentException() {
        uniqueAssignmentList.add(ASSIGNMENT2);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList.add(ASSIGNMENT2));
    }

    @Test
    public void setAssignment_nullTargetAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignment(null, ASSIGNMENT1));
    }

    @Test
    public void setAssignment_nullEditedAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignment(ASSIGNMENT1,
                null));
    }

    @Test
    public void setAssignment_targetAssignmentNotInList_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> uniqueAssignmentList.setAssignment(ASSIGNMENT1,
                ASSIGNMENT1));
    }

    @Test
    public void setAssignment_editedAssignmentIsSameAssignment_success() {
        uniqueAssignmentList.add(ASSIGNMENT1);
        uniqueAssignmentList.setAssignment(ASSIGNMENT1, ASSIGNMENT1);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ASSIGNMENT1);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasSameIdentity_success() {
        uniqueAssignmentList.add(ASSIGNMENT1);
        Assignment editedAssignment = new AssignmentBuilder(ASSIGNMENT1)
                .withDescription(VALID_DESCRIPTION_1)
                .withTags(VALID_TAG_ASSIGN_1)
                .build();
        uniqueAssignmentList.setAssignment(ASSIGNMENT1, editedAssignment);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(editedAssignment);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasDifferentIdentity_success() {
        uniqueAssignmentList.add(ASSIGNMENT1);
        uniqueAssignmentList.setAssignment(ASSIGNMENT1, ASSIGNMENT2);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ASSIGNMENT2);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasNonUniqueIdentity_throwsDuplicateAssignmentException() {
        uniqueAssignmentList.add(ASSIGNMENT1);
        uniqueAssignmentList.add(ASSIGNMENT2);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList
                .setAssignment(ASSIGNMENT1, ASSIGNMENT2));
    }

    @Test
    public void remove_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> uniqueAssignmentList.remove(ASSIGNMENT1));
    }

    @Test
    public void remove_existingAssignment_removesAssignment() {
        uniqueAssignmentList.add(ASSIGNMENT1);
        uniqueAssignmentList.remove(ASSIGNMENT1);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_nullUniqueAssignmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList
                .setAssignments((UniqueAssignmentList) null));
    }

    @Test
    public void setAssignments_uniqueAssignmentList_replacesOwnListWithProvidedUniqueAssignmentList() {
        uniqueAssignmentList.add(ASSIGNMENT1);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ASSIGNMENT2);
        uniqueAssignmentList.setAssignments(expectedUniqueAssignmentList);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignments((List<Assignment>) null));
    }

    @Test
    public void setAssignments_list_replacesOwnListWithProvidedList() {
        uniqueAssignmentList.add(ASSIGNMENT1);
        List<Assignment> personList = Collections.singletonList(ASSIGNMENT2);
        uniqueAssignmentList.setAssignments(personList);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ASSIGNMENT2);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_listWithDuplicateAssignments_throwsDuplicateAssignmentException() {
        List<Assignment> listWithDuplicateAssignments = Arrays.asList(ASSIGNMENT1, ASSIGNMENT1);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList
                .setAssignments(listWithDuplicateAssignments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueAssignmentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueAssignmentList.asUnmodifiableObservableList().toString(), uniqueAssignmentList.toString());
    }
}
