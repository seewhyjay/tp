package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Description;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Assignment> PREDICATE_SHOW_ALL_ASSIGNMENTS = unused -> true;

    String MESSAGE_WRONG_VIEW_FIRST_HALF = "Pls switch to ";

    String MESSAGE_WRONG_VIEW_SECOND_HALF = " before performing this operation "
             + "by using the respective list commands";

    void setView(View v);

    void addViewChangeListener(ListChangeListener<View> e);

    void removeViewChangeListener(ListChangeListener<View> e);

    /**
     * @param correctView the view that this command should be executed in
     * @throws CommandException if current view is different from correct view
     */
    void checkValidOperation(View correctView) throws CommandException;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    boolean hasAssignment(Assignment assignment);

    void addAssignment(Assignment assignment);

    void deleteAssignment(Assignment target);

    /** Returns an unmodifiable view of the filtered assignment list */
    ObservableList<Assignment> getFilteredAssignmentList();

    ObservableList<Assignment> getUnfilteredAssignmentList();

    /**
     * Marks the given assignment as complete.
     * The assignment must exist in Campus Companion.
     */
    void markAsComplete(Assignment target);

    /**
     * Marks the given assignment as incomplete (UnMarks the assignment).
     * The assignment must exist in Campus Companion.
     */
    void markAsIncomplete(Assignment toUnMark);

    /**
     * Sorts UniqueAssignmentList by endDate
     */
    void sortAssignments();

    /**
     * Updates the filter of the filtered assignment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAssignmentList(Predicate<Assignment> predicate);

    /**
     * Edits the description of an assignment.
     * The assignment must exist in Campus Companion.
     */
    void editAssignment(Assignment assignment, Description newDescription);
}
