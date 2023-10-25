package seedu.address.model.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CycleTest {
    private final String validCycle1 = "Jan to June";
    private final String validCycle2 = "Summer";

    private final String invalidCycle = " ";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cycle(null));
    }

    @Test
    public void isValidCycle() {
        assertThrows(NullPointerException.class, () -> Cycle.isValidCycle(null));

        assertTrue(Cycle.isValidCycle(validCycle1));

        assertFalse(Cycle.isValidCycle(invalidCycle));
    }

    @Test
    public void equals() {
        Cycle cycle = new Cycle(validCycle1);

        // same values -> returns true
        assertEquals(cycle, new Cycle(validCycle1));

        // same object -> returns true
        assertEquals(cycle, cycle);

        // null -> returns false
        assertNotEquals(null, cycle);

        // different types -> returns false
        assertFalse(cycle.equals(5.0f));

        // different values -> returns false
        assertNotEquals(cycle, new Cycle(validCycle2));
    }
}
