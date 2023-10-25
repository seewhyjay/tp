package seedu.address.model.internshiptask;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Status;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.tag.Tag;

/**
 * Represents a task needed for an intern application/role
 */
public class InternshipTask {
    private final Name name;
    private final IsoDate deadline;
    private final Status status;
    private final TaskOutcome outcome;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates an InternshipTask object
     * @param name of the task
     * @param deadline of the task
     * @param status of the task
     * @param outcome of the task
     * @param tags of the task
     */
    public InternshipTask(Name name, IsoDate deadline, Status status, TaskOutcome outcome, Set<Tag> tags) {
        requireAllNonNull(name, deadline, status, outcome);
        this.name = name;
        this.deadline = deadline;
        this.status = status;
        this.outcome = outcome;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public IsoDate getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    public TaskOutcome getOutcome() {
        return outcome;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * @param otherTask to be verifed
     * @return true if otherTask has the same task name
     */
    public boolean isSameInternshipTask(InternshipTask otherTask) {
        if (this == otherTask) {
            return true;
        }

        return otherTask != null && name.equals(otherTask.name);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline, status, outcome);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InternshipTask)) {
            return false;
        }

        InternshipTask otherTask = (InternshipTask) other;

        return name.equals(otherTask.name)
                && deadline.equals(otherTask.deadline)
                && status.equals(otherTask.status)
                && outcome.equals(otherTask.outcome)
                && tags.equals(otherTask.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("deadline", deadline)
                .add("status", status)
                .add("outcome", outcome)
                .add("tags", tags)
                .toString();
    }

}
