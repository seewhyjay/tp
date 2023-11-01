package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.NoDate;
import seedu.address.model.fields.Status;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static Assignment[] getSampleAssignments() {
        return new Assignment[] {
            new Assignment(new seedu.address.model.fields.Name("Task1"),
                    new IsoDate(LocalDateTime.parse("2023-10-18 23:59",
                            DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                    new Status(true), new Description("description"),
                    new IsoDate(LocalDateTime.parse("2023-11-11 11:11",
                            DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                    Set.of(new Tag("cs2100"))),
            new Assignment(new seedu.address.model.fields.Name("Task2"),
                    new IsoDate(LocalDateTime.parse("2023-10-31 23:59",
                            DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                    new Status(true), new Description("description"),
                    new NoDate(), Set.of(new Tag("cs2103"))),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();

        for (Assignment assignment : getSampleAssignments()) {
            sampleAb.addAssignment(assignment);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
