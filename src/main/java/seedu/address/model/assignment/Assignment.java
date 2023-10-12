package seedu.address.model.assignment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents an Assignment in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Assignment {

    // Identity fields
    private final Name name;

    private EndDate enddate;
    private Status status;
    private Description description;
    private PlannedFinishDate plannedFinishDate;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Assignment(Name name, EndDate end, Status status, Description description,
                      PlannedFinishDate plannedFinishDate, Set<Tag> tags) {
        requireAllNonNull(name, end, status, tags);
        this.name = name;
        this.enddate = end;
        this.status = status;
        this.tags.addAll(tags);
        this.description = description;
        this.plannedFinishDate = plannedFinishDate;
    }

    public Name getName() {
        return name;
    }

    public EndDate getEnd() {
        return this.enddate;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both assignments have the same name.
     * This defines a weaker notion of equality between two assignments.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }

        return otherAssignment != null
                && otherAssignment.getName().equals(getName());
    }

    /**
     * Returns true if both assignments have the same identity and data fields.
     * This defines a stronger notion of equality between two assignment.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return name.equals(otherAssignment.name)
                && this.enddate.equals(otherAssignment.enddate)
                && this.status.equals(otherAssignment.status)
                && this.tags.equals(otherAssignment.tags)
                && this.description.equals(otherAssignment.description)
                && this.plannedFinishDate.equals(otherAssignment.plannedFinishDate);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, enddate, status, tags, description, plannedFinishDate);
    }

    @Override
    public String toString() {
        DateTimeFormatter toStringFormatter = DateTimeFormatter.ofPattern("d MMM uuuu h:mm a");
        return new ToStringBuilder(this)
                .add("name", name)
                .add("completeness", status)
                .add("description", description)
                .add("plannedFinishDate", plannedFinishDate)
                .add("end", enddate)
                .add("tags", tags)
                .toString();
    }

}
