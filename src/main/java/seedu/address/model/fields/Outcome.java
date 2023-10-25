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
            return "follow up";
        case GHOSTED:
            return "ghosted";
        case REJECTED:
            return "rejected";
        case OFFERED:
            return "offered";
        case ACCEPTED:
            return "accepted";
        default:
            return "";
        }
    }
}
