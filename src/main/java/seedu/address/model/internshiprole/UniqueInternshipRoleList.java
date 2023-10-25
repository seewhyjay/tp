package seedu.address.model.internshiprole;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.internshiprole.exceptions.DuplicateInternshipRoleException;
import seedu.address.model.internshiprole.exceptions.InternshipRoleNotFoundException;

/**
 * Represents a unique list of InternshipRole with unique names and roles
 */
public class UniqueInternshipRoleList implements Iterable<InternshipRole> {
    private final ObservableList<InternshipRole> internalList = FXCollections.observableArrayList();
    private final ObservableList<InternshipRole> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent role as the given argument.
     */
    public boolean contains(InternshipRole toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameInternshipRole);
    }

    /**
     * Adds a role to the list.
     * The role must not already exist in the list.
     */
    public void add(InternshipRole toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateInternshipRoleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the role {@code target} in the list with {@code editedRole}.
     * {@code target} must exist in the list.
     * The role identity of {@code editedRole} must not be the same as another existing role in the list.
     */
    public void setRole(InternshipRole target, InternshipRole editedRole) {
        requireAllNonNull(target, editedRole);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new InternshipRoleNotFoundException();
        }

        if (!target.isSameInternshipRole(editedRole) && contains(editedRole)) {
            throw new DuplicateInternshipRoleException();
        }

        internalList.set(index, editedRole);
    }

    /**
     * Removes the equivalent role from the list.
     * The role must exist in the list.
     */
    public void remove(InternshipRole toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new InternshipRoleNotFoundException();
        }
    }

    public void setRoles(UniqueInternshipRoleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code roles}.
     * {@code roles} must not contain duplicate roles.
     */
    public void setRoles(List<InternshipRole> roles) {
        requireAllNonNull(roles);
        if (!rolesAreUnique(roles)) {
            throw new DuplicateInternshipRoleException();
        }

        internalList.setAll(roles);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<InternshipRole> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<InternshipRole> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueInternshipRoleList)) {
            return false;
        }

        UniqueInternshipRoleList otherUniqueRoleList = (UniqueInternshipRoleList) other;
        return internalList.equals(otherUniqueRoleList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code roles} contains only unique roles.
     */
    private boolean rolesAreUnique(List<InternshipRole> roles) {
        for (int i = 0; i < roles.size() - 1; i++) {
            for (int j = i + 1; j < roles.size(); j++) {
                if (roles.get(i).isSameInternshipRole(roles.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
