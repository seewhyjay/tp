package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class IsoDateTest {

    private String invalidDate = "1111-11-40 11:50";

    private String validDate = "1111-11-11 11:50";
    private String validDate2 = "1111-11-11 11:51";

    @Test
    public void isValidDate() {
        assertTrue(IsoDate.isValidIsoDate(validDate));
        assertFalse(IsoDate.isValidIsoDate(invalidDate));
    }

    @Test
    public void returnsCorrectSaveData() {
        Date d = new IsoDate(LocalDateTime.parse(validDate, DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));
        assertEquals(validDate, d.toSaveData());
        assertNotEquals(invalidDate, d.toSaveData());
    }

    @Test
    public void equals() {
        Date d = new IsoDate(LocalDateTime.parse(validDate, DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));

        assertNotEquals(null, d);

        assertNotEquals(d, new IsoDate(LocalDateTime.parse(validDate2,
                DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))));

        assertEquals(d, new IsoDate(LocalDateTime.parse(validDate, DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))));

        assertEquals(d, d);
    }
}
