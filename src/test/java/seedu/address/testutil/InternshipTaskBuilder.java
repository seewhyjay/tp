package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Status;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.internshiptask.InternshipTask;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A class to create new InternshipTask objects
 */
public class InternshipTaskBuilder {
    private final String defaultName = "Grind Leetcode";
    private final LocalDateTime defaultDate = LocalDateTime.parse("2024-04-01 23:59",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    private final boolean defaultStatus = false;
    private final Outcome defaultOutcome = Outcome.FOLLOW_UP;

    private Name name;
    private IsoDate deadline;
    private Status status;
    private TaskOutcome outcome;
    private Set<Tag> tags;

    /**
     * Creates an InternshipTaskBuilder with default fields
     */
    public InternshipTaskBuilder() {
        name = new Name(defaultName);
        deadline = new IsoDate(defaultDate);
        status = new Status(defaultStatus);
        outcome = new TaskOutcome(defaultOutcome);
        tags = new HashSet<>();
    }


    /**
     * Creates an InternshipTaskBuilder with the given input
     * @param taskToCopy over to the builder
     */
    public InternshipTaskBuilder(InternshipTask taskToCopy) {
        name = taskToCopy.getName();
        deadline = taskToCopy.getDeadline();
        status = taskToCopy.getStatus();
        outcome = taskToCopy.getOutcome();
        tags = new HashSet<>();
    }

    /**
     * @param name of the task
     * @return InternshipBuilder with the given name
     */
    public InternshipTaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * @param date of the task
     * @return InternshipBuilder with the given date
     */
    public InternshipTaskBuilder withDeadline(LocalDateTime date) {
        this.deadline = new IsoDate(date);
        return this;
    }

    /**
     * @param status of task
     * @return InternshipBuilder with the given status
     */
    public InternshipTaskBuilder withStatus(boolean status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * @param outcome of task
     * @return InternshipBuilder with the given outcome
     */
    public InternshipTaskBuilder withOutcome(Outcome outcome) {
        this.outcome = new TaskOutcome(outcome);
        return this;
    }

    /**
     * @param tags of task
     * @return InternshipBuilder with the given tags
     */
    public InternshipTaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    /**
     * @return an InternshipTask with the current fields
     */
    public InternshipTask build() {
        return new InternshipTask(name, deadline, status, outcome, tags);
    }

}
