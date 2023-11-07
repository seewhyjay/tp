package seedu.address.testutil;

import static seedu.address.testutil.TypicalInternshipRoles.getTypicalInternshipRole1;
import static seedu.address.testutil.TypicalInternshipRoles.getTypicalInternshipRole2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.fields.Outcome;
import seedu.address.model.internship.task.InternshipTask;

/**
 * A class to get typical internship tasks
 */
public class TypicalInternshipTasks {
    private static LocalDateTime date2 = LocalDateTime.parse("2024-06-01 23:59",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    // Using static variables cause edit test cases to break if not careful
    public static InternshipTask getTypicalInternshipTask1() {
        return new InternshipTaskBuilder().build();
    }

    public static InternshipTask getTypicalInternshipTask2() {
        return new InternshipTaskBuilder()
                .withInternshipRole(getTypicalInternshipRole2())
                .withTaskName("Leetcode")
                .withStatus(true)
                .withDeadline(date2)
                .withOutcome(Outcome.GHOSTED)
                .build();
    }

    /**
     * Returns an {@code AddressBook} with all the typical internship tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addInternshipRole(getTypicalInternshipRole1());
        ab.addInternshipRole(getTypicalInternshipRole2());
        for (InternshipTask task : getTypicalInternshipTasks()) {
            ab.addInternshipTask(task);
        }
        return ab;
    }

    public static List<InternshipTask> getTypicalInternshipTasks() {
        InternshipTask task1 = getTypicalInternshipTask1();
        InternshipTask task2 = getTypicalInternshipTask2();
        return new ArrayList<>(Arrays.asList(task1, task2));
    }
}
