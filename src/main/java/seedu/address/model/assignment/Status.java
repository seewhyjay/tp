package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

public class Status {
  public final Boolean isCompleted;

  /**
   * Constructs a {@code Name}.
   *
   * @param isCompleted A valid name.
   */
  public Status(Boolean isCompleted) {
    requireNonNull(isCompleted);
    this.isCompleted = isCompleted;
  }

  @Override
  public String toString() {
    return isCompleted ? "done" : "undone";
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    // instanceof handles nulls
    if (!(other instanceof seedu.address.model.assignment.Status)) {
      return false;
    }

    seedu.address.model.assignment.Status otherStatus = (seedu.address.model.assignment.Status) other;
    return isCompleted.equals(otherStatus.isCompleted);
  }

  @Override
  public int hashCode() {
    return isCompleted.hashCode();
  }
}
