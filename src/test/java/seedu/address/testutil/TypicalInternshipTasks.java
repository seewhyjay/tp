package seedu.address.testutil;


import static seedu.address.testutil.TypicalInternshipRoles.getTypicalInternshipRole1;
import static seedu.address.testutil.TypicalInternshipRoles.getTypicalInternshipRole2;
import static seedu.address.testutil.TypicalInternshipRoles.INTERNSHIP_ROLE_2;

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

    private static final LocalDateTime DATE1 = LocalDateTime.parse("2024-06-01 23:59",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    private static final LocalDateTime DATE2 = LocalDateTime.parse("2024-07-01 23:59",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    private static final LocalDateTime DATE3 = LocalDateTime.parse("2024-07-03 23:59",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    public static final InternshipTask INTERNSHIP_TASK_1 = new InternshipTaskBuilder()
            .withInternshipRole(getTypicalInternshipRole1())
            .withTaskName("Leetcode")
            .withStatus(true)
            .withDeadline(DATE1)
            .withOutcome(Outcome.GHOSTED)
            .build();

    public static final InternshipTask INTERNSHIP_TASK_2 = new InternshipTaskBuilder()
            .withInternshipRole(INTERNSHIP_ROLE_2)
            .withTaskName("OA 1")
            .withStatus(true)
            .withDeadline(DATE2)
            .withOutcome(Outcome.AWAITING)
            .build();

    public static final InternshipTask INTERNSHIP_TASK_3 = new InternshipTaskBuilder()
            .withInternshipRole(INTERNSHIP_ROLE_2)
            .withTaskName("Interview")
            .withStatus(true)
            .withDeadline(DATE3)
            .withOutcome(Outcome.AWAITING)
            .build();
    public static InternshipTask getTypicalInternshipTask1() {
        return INTERNSHIP_TASK_1;
    }

    public static InternshipTask getTypicalInternshipTask2() {
        return new InternshipTaskBuilder().build();
    }
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (InternshipTask internshipTask : getTypicalInternshipTask()) {
            ab.addInternshipTask(internshipTask);
        }
        return ab;
    }

    public static List<InternshipTask> getTypicalInternshipTask() {
        return new ArrayList<>(Arrays.asList(INTERNSHIP_TASK_1, INTERNSHIP_TASK_2,
                INTERNSHIP_TASK_3));
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
