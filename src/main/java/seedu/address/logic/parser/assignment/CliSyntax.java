package seedu.address.logic.parser.assignment;

import seedu.address.logic.parser.person.Prefix;

/**
 * Accepted syntax for adding a new assignment
 */
public class CliSyntax {
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_ENDDATE = new Prefix("e/");
    public static final Prefix PREFIX_PLANNEDFINISHDATE = new Prefix("p/");
}
