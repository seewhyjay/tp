package seedu.address.model.assignment;

/**
 * Should be instantiated in the add-date parser
 * if no date is given
 */
public class NoDate extends Date {
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof NoDate);
    }

    @Override
    public String toString() {
        return "";
    }
}
