package seedu.address.model.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.fields.ApplicationOutcome;
import seedu.address.model.fields.Cycle;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Location;
import seedu.address.model.fields.NoDate;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Pay;
import seedu.address.model.fields.Role;
import seedu.address.model.fields.Status;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    private static Assignment[] getSampleAssignments() {
        return new Assignment[] {
            new Assignment(new seedu.address.model.fields.Name("Task1"),
                    new IsoDate(LocalDateTime.parse("2023-10-18 23:59",
                            DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                    new Status(true), new Description("description"),
                    new IsoDate(LocalDateTime.parse("2023-11-11 11:11",
                            DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                    Set.of(new Tag("cs2100"))),
            new Assignment(new seedu.address.model.fields.Name("Task2"),
                    new IsoDate(LocalDateTime.parse("2023-10-31 23:59",
                            DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                    new Status(true), new Description("description"),
                    new NoDate(), Set.of(new Tag("cs2103"))),
        };
    }

    private static InternshipRole getSampleRole1() {
        return new InternshipRole(new seedu.address.model.fields.Name("Google"),
                new Role("SWE"), new Cycle("Summer"), new Description(""),
                new Pay(new BigDecimal("2000")), new ApplicationOutcome(Outcome.FOLLOW_UP),
                new Location(""), new HashSet<>());
    }

    private static InternshipRole getSampleRole2() {
        return  new InternshipRole(new seedu.address.model.fields.Name("Tiktok"),
                new Role("SWE"), new Cycle("Summer"), new Description(""),
                new Pay(new BigDecimal("1800")), new ApplicationOutcome(Outcome.FOLLOW_UP),
                new Location(""), new HashSet<>());
    }

    private static InternshipRole[] getSampleInternshipRoles() {
        return new InternshipRole[] { getSampleRole1(), getSampleRole2() };
    }

    private static InternshipTask[] getSampleInternshipTasks() {
       return new InternshipTask[] {
               new InternshipTask(getSampleRole1(), new seedu.address.model.fields.Name("OA"),
                       new IsoDate(LocalDateTime.parse("2023-11-11 23:59",
                       DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))), new Status(false),
                       new TaskOutcome(Outcome.AWAITING), new HashSet<>())
       };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Assignment assignment : getSampleAssignments()) {
            sampleAb.addAssignment(assignment);
        }

        for (InternshipRole role : getSampleInternshipRoles()) {
            sampleAb.addInternshipRole(role);
        }

        for (InternshipTask task : getSampleInternshipTasks()) {
            sampleAb.addInternshipTask(task);
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
