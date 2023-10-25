package seedu.address.model.internshiprole.exceptions;

/**
 * Signals that the operation will result in duplicate roles (roles are considered duplicates
 * if they have the same identity).
 */
public class DuplicateInternshipRoleException extends RuntimeException {
    public DuplicateInternshipRoleException() {
        super("Operation would result in duplicate internship application");
    }
}
