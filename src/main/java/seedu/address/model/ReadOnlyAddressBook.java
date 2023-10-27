package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.internshiprole.InternshipRole;
import seedu.address.model.internshiptask.InternshipTask;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ObservableList<Assignment> getAssignmentList();

    ObservableList<InternshipRole> getInternshipRoleList();

    ObservableList<InternshipTask> getInternshipTaskList();

}
