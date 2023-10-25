package seedu.address.model.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RoleTest {

    private final String validRole1 = "Software Engineer";

    private final String validRole2 = "Backend Dev";

    private final String invalidRole = " ";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void isValidRole() {
        assertThrows(NullPointerException.class, () -> Role.isValidRoleName(null));

        assertTrue(Role.isValidRoleName(validRole1));

        assertFalse(Role.isValidRoleName(invalidRole));
    }

    @Test
    public void equals() {
        Role role = new Role(validRole1);

        // same values -> returns true
        assertEquals(role, new Role(validRole1));

        // same object -> returns true
        assertEquals(role, role);

        // null -> returns false
        assertNotEquals(null, role);

        // different types -> returns false
        assertFalse(role.equals(5.0f));

        // different values -> returns false
        assertNotEquals(role, new Role(validRole2));
    }
}
