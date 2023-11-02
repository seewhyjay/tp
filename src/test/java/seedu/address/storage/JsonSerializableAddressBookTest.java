package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalAssignments;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_ASSIGNMENTS_FILE = TEST_DATA_FOLDER.resolve("typicalAddressBook.json");
    private static final Path INVALID_ASSIGNMENTS_FILE = TEST_DATA_FOLDER.resolve("invalidAssignmentAddressBook.json");
    private static final Path DUPLICATE_ASSIGNMENT_FILE = TEST_DATA_FOLDER
            .resolve("duplicateAssignmentAddressBook.json");
    private static final Path INVALID_NAME_ASSGN_FILE = TEST_DATA_FOLDER.resolve("emptyAssignmentNameAddressBook.json");
    private static final Path INVALID_DATE_ASSGN_FILE = TEST_DATA_FOLDER
            .resolve("invalidAssignmentDateAddressBook.json");
    private static final Path INVALID_PLAN_DATE_ASSGN_FILE = TEST_DATA_FOLDER
            .resolve("invalidPlanAssignmentDateAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_ASSIGNMENTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalAssignmentsAddressBook = TypicalAssignments.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalAssignmentsAddressBook);
    }

}
