package seedu.address.model.assignment;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a generic date
 */
public abstract class Date {
    public abstract String toSaveData();

    public abstract Optional<LocalDateTime> getDate();
}
