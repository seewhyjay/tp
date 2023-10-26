package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.internshiprole.InternshipRole;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;

    private final UserPrefs userPrefs;

    private final FilteredList<Person> filteredPersons;

    private final FilteredList<Assignment> filteredAssignments;

    private final ObservableList<View> selectedView = FXCollections.observableArrayList();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredAssignments = new FilteredList<>(this.addressBook.getAssignmentList());

        initView();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== Views =====================================================================================

    private void initView() {
        selectedView.add(View.ASSIGNMENTS);
    }

    @Override
    public void setView(View v) {
        selectedView.set(0, v);
    }

    @Override
    public void addViewChangeListener(ListChangeListener<View> listener) {
        selectedView.addListener(listener);
    }

    @Override
    public void removeViewChangeListener(ListChangeListener<View> listener) {
        selectedView.removeListener(listener);
    }

    @Override
    public boolean isValidOperationWith(View correctView) {
        View currView = selectedView.get(0);
        return currView == correctView;
    }

    //========== Internship Roles ===========================================================================

    @Override
    public void addInternshipRole(InternshipRole role) {
        addressBook.addInternshipRoles(role);
    }

    @Override
    public boolean hasInternshipRole(InternshipRole role) {
        return addressBook.hasInternshipRoles(role);
    }


    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //====== Filtered Assignment List Accessors==========================

    @Override
    public boolean hasAssignment(Assignment assignment) {
        return addressBook.hasAssignment(assignment);
    }

    @Override
    public void deleteAssignment(Assignment target) {
        addressBook.removeAssignment(target);
    }

    @Override
    public void sortAssignments() {
        addressBook.sortAssignments();
    }

    @Override
    public void addAssignment(Assignment assignment) {
        requireNonNull(assignment);
        addressBook.addAssignment(assignment);
    }

    @Override
    public void setAssignment(Assignment assignment, Assignment newAssignment) {
        requireNonNull(assignment);
        addressBook.editAssignment(assignment, newAssignment);
    }

    @Override
    public ObservableList<Assignment> getFilteredAssignmentList() {
        return filteredAssignments;
    }

    @Override
    public ObservableList<Assignment> getUnfilteredAssignmentList() {
        return addressBook.getAssignmentList();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredAssignmentList(Predicate<Assignment> predicate) {
        requireNonNull(predicate);
        filteredAssignments.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredAssignments.equals(otherModelManager.filteredAssignments);
    }
}
