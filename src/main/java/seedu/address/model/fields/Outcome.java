package seedu.address.model.fields;

/**
 * Represents the possible outcome for an application/task
 */
public enum Outcome {
    FOLLOW_UP, GHOSTED, REJECTED, OFFERED, ACCEPTED;

    @Override
    public String toString() {
        switch (this) {
        case FOLLOW_UP:
            return "Follow-up";
        case GHOSTED:
            return "Ghosted";
        case REJECTED:
            return "Rejected";
        case OFFERED:
            return "Offered";
        case ACCEPTED:
            return "Accepted";
        default:
            return "";
        }
    }
}
