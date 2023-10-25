package seedu.address.model.fields;

import static java.util.Objects.requireNonNull;


/**
 * A class representing the role of a job
 */
public class Role {
    public static final String MESSAGE_CONSTRAINTS = "Empty string not allowed";

    // Matches everything except empty string
    private static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    private final String role;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role name.
     */
    public Role(String role) {
        requireNonNull(role);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    /**
     * @param role be verified
     * @return true when not an empty string, false otherwise
     */
    public static boolean isValidRoleName(String role) {
        return role.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Role)) {
            return false;
        }

        Role otherRole = (Role) other;
        return role.equals(otherRole.role);
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }
}
