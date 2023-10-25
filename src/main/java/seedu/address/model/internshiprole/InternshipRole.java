package seedu.address.model.internshiprole;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.fields.ApplicationOutcome;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.Location;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Pay;
import seedu.address.model.fields.Role;
import seedu.address.model.tag.Tag;

/**
 * Represents an internship role
 */
public class InternshipRole {
    private final Name name;
    private final Role role;
    private final Description description;
    private final Pay pay;
    private final ApplicationOutcome outcome;
    private final Location location;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates an InternshipRole object
     * @param name of the company
     * @param role of the internship
     * @param description of the internship
     * @param pay of the internship
     * @param outcome of the internship application
     * @param location of the internship
     */
    public InternshipRole(Name name, Role role, Description description, Pay pay, ApplicationOutcome outcome,
                          Location location, Set<Tag> tags) {
        requireAllNonNull(name, role, description, pay, outcome, location, tags);
        this.name = name;
        this.role = role;
        this.description = description;
        this.pay = pay;
        this.outcome = outcome;
        this.location = location;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public Description getDescription() {
        return description;
    }

    public ApplicationOutcome getApplicationOutcome() {
        return outcome;
    }

    public Pay getPay() {
        return pay;
    }

    public Location getLocation() {
        return location;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, role, description, description, pay, outcome, location, tags);
    }


    /**
     * @param otherRole to be checked
     * @return true if name and role is the same
     */
    public boolean isSameInternshipRole(InternshipRole otherRole) {
        if (this == otherRole) {
            return true;
        }

        return otherRole != null && role.equals(otherRole.role) && name.equals(otherRole.name);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InternshipRole)) {
            return false;
        }

        InternshipRole otherRole = (InternshipRole) other;

        return name.equals(otherRole.name)
                && role.equals(otherRole.role)
                && description.equals(otherRole.description)
                && pay.equals(otherRole.pay)
                && outcome.equals(otherRole.outcome)
                && location.equals(otherRole.location)
                && tags.equals(otherRole.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("role", role)
                .add("description", description)
                .add("pay", pay)
                .add("outcome", outcome)
                .add("location", location)
                .add("tags", tags)
                .toString();
    }

}
