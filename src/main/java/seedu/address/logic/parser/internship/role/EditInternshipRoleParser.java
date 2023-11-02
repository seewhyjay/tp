package seedu.address.logic.parser.internship.role;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_CYCLE;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_OUTCOME;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_PAY;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.internship.role.EditInternshipRoleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.person.Prefix;
import seedu.address.model.fields.ApplicationOutcome;
import seedu.address.model.fields.Cycle;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.Location;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Pay;
import seedu.address.model.fields.Role;
import seedu.address.model.tag.Tag;

/**
 * Parser to create an EditInternshipRoleCommand from the given input
 */
public class EditInternshipRoleParser implements Parser<EditInternshipRoleCommand> {
    @Override
    public EditInternshipRoleCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_CYCLE,
                PREFIX_DESCRIPTION, PREFIX_PAY, PREFIX_OUTCOME, PREFIX_LOCATION);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_INDEX) || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditInternshipRoleCommand.MESSAGE_USAGE));
        }

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_INDEX, PREFIX_CYCLE, PREFIX_DESCRIPTION, PREFIX_PAY,
                PREFIX_OUTCOME, PREFIX_LOCATION);

        Index index = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_INDEX).get());

        Cycle newCycle = argumentMultimap.getValue(PREFIX_CYCLE).isEmpty()
                ? null
                : ParserUtil.parseCycle(argumentMultimap.getValue(PREFIX_CYCLE).get());

        Description newDescription = ParserUtil.parseDescription(argumentMultimap.getValue(PREFIX_DESCRIPTION)
                .orElse(""));

        Pay newPay = argumentMultimap.getValue(PREFIX_PAY).isEmpty()
                ? null
                : ParserUtil.parsePay(argumentMultimap.getValue(PREFIX_PAY).get());

        ApplicationOutcome newOutcome = argumentMultimap.getValue(PREFIX_OUTCOME).isEmpty()
                ? null
                : ParserUtil.parseApplicationOutcome(argumentMultimap.getValue(PREFIX_OUTCOME).get());

        // To be looked through again
        Location newLocation = argumentMultimap.getValue(PREFIX_LOCATION).isEmpty()
                ? null
                : ParserUtil.parseLocation(argumentMultimap.getValue(PREFIX_LOCATION).get());

        return new EditInternshipRoleCommand(index, newCycle, newDescription, newPay, newOutcome, newLocation);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
