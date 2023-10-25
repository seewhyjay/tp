package seedu.address.model.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LocationTest {

    private final String validLocation1 = "Kent Ridge";
    private final String validLocation2 = "Narnia";

    private final String invalidLocation1 = " ";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void isValidLocation() {
        assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        assertTrue(Location.isValidLocation(validLocation1));

        assertFalse(Location.isValidLocation(invalidLocation1));
    }

    @Test
    public void equals() {
        Location location = new Location(validLocation1);

        // same values -> returns true
        assertEquals(location, new Location(validLocation1));

        // same object -> returns true
        assertEquals(location, location);

        // null -> returns false
        assertNotEquals(null, location);

        // different types -> returns false
        assertFalse(location.equals(5.0f));

        // different values -> returns false
        assertNotEquals(location, new Location(validLocation2));
    }
}
