package seedu.address.model.internshiprole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternshipRoles.getTypicalInternshipRole1;
import static seedu.address.testutil.TypicalInternshipRoles.getTypicalInternshipRole2;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.fields.Outcome;
import seedu.address.model.internshiprole.exceptions.DuplicateInternshipRoleException;
import seedu.address.model.internshiprole.exceptions.InternshipRoleNotFoundException;
import seedu.address.testutil.InternshipRoleBuilder;

public class UniqueInternshipRoleListTest {

    private final UniqueInternshipRoleList uniqueRoleList = new UniqueInternshipRoleList();

    private final InternshipRole role1 = getTypicalInternshipRole1();

    private final InternshipRole role2 = getTypicalInternshipRole2();

    private final String desc = "123";

    @Test
    public void contains_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.contains(null));
    }

    @Test
    public void contains_roleNotInList_returnsFalse() {
        assertFalse(uniqueRoleList.contains(role1));
    }

    @Test
    public void contains_roleInList_returnsTrue() {
        uniqueRoleList.add(role1);
        assertTrue(uniqueRoleList.contains(role1));
    }

    @Test
    public void contains_roleWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRoleList.add(role1);
        InternshipRole editedRole = new InternshipRoleBuilder(role1)
                .withOutcome(Outcome.REJECTED)
                .withDescription(desc)
                .withLocation(desc)
                .withPay(new BigDecimal("00.00"))
                .withTags("tag")
                .build();
        assertTrue(uniqueRoleList.contains(editedRole));
    }

    @Test
    public void add_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.add(null));
    }

    @Test
    public void add_duplicateRoleField_success() {
        uniqueRoleList.add(role1);
        InternshipRole editedRole = new InternshipRoleBuilder(role1)
                .withRole(desc)
                .build();
        uniqueRoleList.add(editedRole);
        assertTrue(uniqueRoleList.contains(editedRole));
    }

    @Test
    public void add_duplicateNameField_success() {
        uniqueRoleList.add(role1);
        InternshipRole editedRole = new InternshipRoleBuilder(role1)
                .withName(desc)
                .build();
        uniqueRoleList.add(editedRole);
        assertTrue(uniqueRoleList.contains(editedRole));
    }

    @Test
    public void add_duplicateRole_throwsDuplicateInternshipRoleException() {
        uniqueRoleList.add(role2);
        assertThrows(DuplicateInternshipRoleException.class, () -> uniqueRoleList.add(role2));
    }


    @Test
    public void setRole_nullTargetRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.setRole(null, role1));
    }

    @Test
    public void setRole_nullEditedRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.setRole(role1,
                null));
    }

    @Test
    public void setRole_targetRoleNotInList_throwsRoleNotFoundException() {
        assertThrows(InternshipRoleNotFoundException.class, () -> uniqueRoleList.setRole(role1,
                role1));
    }

    @Test
    public void setRole_editedRoleIsSameRole_success() {
        uniqueRoleList.add(role1);
        uniqueRoleList.setRole(role1, role1);
        UniqueInternshipRoleList expectedUniqueInternshipRoleList = new UniqueInternshipRoleList();
        expectedUniqueInternshipRoleList.add(role1);
        assertEquals(expectedUniqueInternshipRoleList, uniqueRoleList);
    }

    @Test
    public void setRole_editedRoleHasSameIdentity_success() {
        uniqueRoleList.add(role1);
        InternshipRole editedRole = new InternshipRoleBuilder(role1)
                .withDescription(desc)
                .withTags("tag")
                .build();
        uniqueRoleList.setRole(role1, editedRole);
        UniqueInternshipRoleList expectedUniqueInternshipRoleList = new UniqueInternshipRoleList();
        expectedUniqueInternshipRoleList.add(editedRole);
        assertEquals(expectedUniqueInternshipRoleList, uniqueRoleList);
    }

    @Test
    public void setRole_editedRoleHasDifferentIdentity_success() {
        uniqueRoleList.add(role1);
        uniqueRoleList.setRole(role1, role2);
        UniqueInternshipRoleList expectedUniqueInternshipRoleList = new UniqueInternshipRoleList();
        expectedUniqueInternshipRoleList.add(role2);
        assertEquals(expectedUniqueInternshipRoleList, uniqueRoleList);
    }

    @Test
    public void setRole_editedRoleHasNonUniqueIdentity_throwsDuplicateInternshipRoleException() {
        uniqueRoleList.add(role1);
        uniqueRoleList.add(role2);
        assertThrows(DuplicateInternshipRoleException.class, () -> uniqueRoleList
                .setRole(role1, role2));
    }

    @Test
    public void remove_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.remove(null));
    }

    @Test
    public void remove_roleDoesNotExist_throwsRoleNotFoundException() {
        assertThrows(InternshipRoleNotFoundException.class, () -> uniqueRoleList.remove(role1));
    }

    @Test
    public void remove_existingRole_removesRole() {
        uniqueRoleList.add(role1);
        uniqueRoleList.remove(role1);
        UniqueInternshipRoleList expectedUniqueInternshipRoleList = new UniqueInternshipRoleList();
        assertEquals(expectedUniqueInternshipRoleList, uniqueRoleList);
    }

    @Test
    public void setRoles_nullUniqueInternshipRoleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList
                .setRoles((UniqueInternshipRoleList) null));
    }

    @Test
    public void setRoles_uniqueRoleList_replacesOwnListWithProvidedUniqueInternshipRoleList() {
        uniqueRoleList.add(role1);
        UniqueInternshipRoleList expectedUniqueInternshipRoleList = new UniqueInternshipRoleList();
        expectedUniqueInternshipRoleList.add(role2);
        uniqueRoleList.setRoles(expectedUniqueInternshipRoleList);
        assertEquals(expectedUniqueInternshipRoleList, uniqueRoleList);
    }

    @Test
    public void setRoles_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.setRoles((List<InternshipRole>) null));
    }

    @Test
    public void setRoles_list_replacesOwnListWithProvidedList() {
        uniqueRoleList.add(role1);
        List<InternshipRole> roleList = Collections.singletonList(role2);
        uniqueRoleList.setRoles(roleList);
        UniqueInternshipRoleList expectedUniqueInternshipRoleList = new UniqueInternshipRoleList();
        expectedUniqueInternshipRoleList.add(role2);
        assertEquals(expectedUniqueInternshipRoleList, uniqueRoleList);
    }

    @Test
    public void setRoles_listWithDuplicateRoles_throwsDuplicateInternshipRoleException() {
        List<InternshipRole> listWithDuplicateRoles = Arrays.asList(role1, role1);
        assertThrows(DuplicateInternshipRoleException.class, () -> uniqueRoleList
                .setRoles(listWithDuplicateRoles));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueRoleList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueRoleList.asUnmodifiableObservableList().toString(), uniqueRoleList.toString());
    }
}
