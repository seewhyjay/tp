package seedu.address.testutil;

import static seedu.address.testutil.TypicalInternshipRoles.getTypicalInternshipRole2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
