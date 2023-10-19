package seedu.address.model.assignment;

/**
 * Represents a generic date
 */
public abstract class Date implements Comparable<Date> {
    public abstract String toSaveData();

    public abstract int compareTo(Date o);
}
