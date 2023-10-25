package seedu.address.logic.parser.internship.role;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_OUTCOME;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_PAY;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.internship.role.AddInternshipRoleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.person.Prefix;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.Location;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Pay;
import seedu.address.model.fields.Role;
import seedu.address.model.internshiprole.InternshipRole;
import seedu.address.model.tag.Tag;

public class AddInternshipRoleParser implements Parser<AddInternshipRoleCommand> {
    @Override
    public AddInternshipRoleCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ROLE,
                PREFIX_DESCRIPTION, PREFIX_PAY, PREFIX_OUTCOME, PREFIX_LOCATION, PREFIX_TAG);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_NAME, PREFIX_ROLE)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddInternshipRoleCommand.MESSAGE_USAGE));
        }

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ROLE, PREFIX_DESCRIPTION, PREFIX_PAY,
                PREFIX_OUTCOME, PREFIX_LOCATION);

        Name name = ParserUtil.parseName(argumentMultimap.getValue(PREFIX_NAME).get());

        Role role = ParserUtil.parseRole(argumentMultimap.getValue(PREFIX_ROLE).get());

        Description description = ParserUtil.parseDescription(argumentMultimap.getValue(PREFIX_DESCRIPTION)
                .orElse(""));

        Pay pay;

        Outcome outcome;

        Location location;

        Set<Tag> tagList = ParserUtil.parseTags(argumentMultimap.getAllValues(PREFIX_TAG));

        
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
