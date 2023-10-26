package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.person.Prefix;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.internshiprole.InternshipRole;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX = "The assignment index provided is invalid";
    public static final String MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW = "%1$d assignments listed!";

    public static final String MESSAGE_ASSIGNMENTS_SORTED = "Sorted all assignments by date!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code assignment} for display to the user.
     */
    public static String format(Assignment assignment) {
        final StringBuilder builder = new StringBuilder();
        builder.append(assignment.getName())
                .append("; Description: ")
                .append(assignment.getDescription())
                .append("; Status: ")
                .append(assignment.getStatus())
                .append("; Deadline: ")
                .append(assignment.getEnd())
                .append("; Planned End date: ")
                .append(assignment.getPlannedFinishDate())
                .append("; Tags: ");
        assignment.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code assignment} for display to the user.
     */
    public static String format(InternshipRole role) {
        final StringBuilder builder = new StringBuilder();
        builder.append(role.getName())
                .append("; role: ")
                .append(role.getRole())
                .append("; cycle: ")
                .append(role.getRole())
                .append("; description: ")
                .append(role.getDescription())
                .append("; pay: ")
                .append(role.getPay())
                .append("; outcome: ")
                .append(role.getApplicationOutcome())
                .append("; location: ")
                .append(role.getLocation())
                .append("; Tags: ");
        role.getTags().forEach(builder::append);
        return builder.toString();
    }

}
