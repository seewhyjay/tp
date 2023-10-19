package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedAssignmentTest {
    private String invalidName = "";
    private String invalidDate = "1111-23-11";

    private String validName = "task1";
    private String validDescription = "description";
    private boolean validStatus = true;
    private String validDate = "2025-10-15 23:59";
    private String validDate1 = "2022-10-15 23:59";

    @Test
    public void toModel_validAssignment_doesNotThrow() {
        assertDoesNotThrow(() -> new JsonAdaptedAssignment(validName, validDescription, validStatus, validDate,
                validDate, List.of(new JsonAdaptedTag("cs2100"))).toModelType());
    }

    @Test
    public void toModel_validAssignmentDateBeforeNow_doesNotThrow() {
        assertDoesNotThrow(() -> new JsonAdaptedAssignment(validName, validDescription, validStatus, validDate1,
                validDate, List.of(new JsonAdaptedTag("cs2100"))).toModelType());
    }

    @Test
    public void toModel_invalidName_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new JsonAdaptedAssignment(invalidName, validDescription,
                validStatus, validDate, validDate, List.of(new JsonAdaptedTag("cs2100"))).toModelType());
    }

    @Test
    public void toModel_invalidEndDate_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new JsonAdaptedAssignment(validName, validDescription,
                validStatus, invalidDate, validDate, List.of(new JsonAdaptedTag("cs2100"))).toModelType());
    }

    @Test
    public void toModel_invalidPlannedDate_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new JsonAdaptedAssignment(validName, validDescription,
                validStatus, validDate, invalidDate, List.of(new JsonAdaptedTag("cs2100"))).toModelType());
    }
}
