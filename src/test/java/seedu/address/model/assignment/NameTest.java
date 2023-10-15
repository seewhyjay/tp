package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void isValidName() {
        assertThrows(NullPointerException.class, () -> Name.isValidTaskName(null));

        assertTrue(Name.isValidTaskName("task"));

        assertFalse(Name.isValidTaskName(""));
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertEquals(name, new Name("Valid Name"));

        // same object -> returns true
        assertEquals(name, name);

        // null -> returns false
        assertNotEquals(null, name);

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertNotEquals(name, new Name("Other Valid Name"));
    }
}
