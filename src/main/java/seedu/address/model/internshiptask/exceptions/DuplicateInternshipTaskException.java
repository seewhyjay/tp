package seedu.address.model.internshiptask.exceptions;

/**
 * Signals that the operation will result in duplicate tasks (tasks are considered duplicates
 * if they have the same identity).
 */
public class DuplicateInternshipTaskException extends RuntimeException {
    public DuplicateInternshipTaskException() {
        super("Operation would result in duplicate internship task for this internship application");
    }
}
