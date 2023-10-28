package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.internshiprole.InternshipRole;
import seedu.address.model.internshiptask.InternshipTask;
import seedu.address.model.person.Person;
import seedu.address.model.unique.UniqueList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueList<Person> persons;

    private final UniqueList<Assignment> assignments;

    private final UniqueList<InternshipRole> internshipRoles;

    private final UniqueList<InternshipTask> internshipTasks;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueList<>();
        assignments = new UniqueList<>();
        internshipRoles = new UniqueList<>();
        internshipTasks = new UniqueList<>();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setList(persons);
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments.setList(assignments);
    }

    public void setInternshipRoles(List<InternshipRole> internRoles) {
        this.internshipRoles.setList(internRoles);
    }

    public void setInternshipTasks(List<InternshipTask> internshipTasks) {
        this.internshipTasks.setList(internshipTasks);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setAssignments(newData.getAssignmentList());
        setInternshipRoles(newData.getInternshipRoleList());
        setInternshipTasks(newData.getInternshipTaskList());
    }


    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.set(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }


    /**
     * @param assignment to be checked
     * @return true if assignment is present, false otherwise
     */
    // Assignments
    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return assignments.contains(assignment);
    }

    @Override
    public ObservableList<Assignment> getAssignmentList() {
        return assignments.asUnmodifiableObservableList();
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void removeAssignment(Assignment key) {
        assignments.remove(key);
    }


    /**
     * Sort assignments by endDate
     */
    public void sortAssignments() {
        assignments.sort(Comparator.comparing(Assignment::getEnd));
    }


    /**
     * Edits the target assignment's description
     *
     * @param newAssignment to replace target
     */
    public void editAssignment(Assignment target, Assignment newAssignment) {
        requireNonNull(target);
        assignments.set(target, newAssignment);
    }

    // ================ Internship Roles =====================================================================

    /**
     * Add an internship role
     * @param role to be added
     */
    public void addInternshipRoles(InternshipRole role) {
        requireNonNull(role);
        internshipRoles.add(role);
    }

    public void removeInternshipRole(InternshipRole key) {
        internshipRoles.remove(key);
    }

    /**
     * Verify if the given role is in the list
     * @param role to be checked
     * @return true if role is present
     */
    public boolean hasInternshipRoles(InternshipRole role) {
        requireNonNull(role);
        return internshipRoles.contains(role);
    }

    @Override
    public ObservableList<InternshipRole> getInternshipRoleList() {
        return internshipRoles.asUnmodifiableObservableList();
    }
    // ================ Internship Tasks =====================================================================

    /**
     * @param internshipTask to be checked
     * @return true if internship task is present, false otherwise
     */
    public boolean hasInternshipTask(InternshipTask internshipTask) {
        requireNonNull(internshipTask);
        return internshipTasks.contains(internshipTask);
    }

    @Override
    public ObservableList<InternshipTask> getInternshipTaskList() {
        return internshipTasks.asUnmodifiableObservableList();
    }

    public void addInternshipTask(InternshipTask internshipTask) {
        internshipTasks.add(internshipTask);
    }

    public void removeInternshipTask(InternshipTask key) {
        internshipTasks.remove(key);
    }

    /**
     * Sort assignments by endDate
     */
    public void sortInternshipTasks() {
        internshipTasks.sort(Comparator.comparing(InternshipTask::getDeadline));
    }

    /**
     * Replaces task with newTask as InternshipTasks are immutable, and as such any changes made will have to be
     * reflected in the internshipTasks list through replaing the old InternshipTask
     *
     * @param newTask to replace target
     */
    public void setInternshipTask(InternshipTask task, InternshipTask newTask) {
        requireNonNull(task);
        internshipTasks.set(task, newTask);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

}
