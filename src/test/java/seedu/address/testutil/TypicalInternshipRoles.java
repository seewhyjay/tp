package seedu.address.testutil;

import java.math.BigDecimal;

import seedu.address.model.fields.Outcome;
import seedu.address.model.internshiprole.InternshipRole;

/**
 * A class to get typical internship roles
 */
public class TypicalInternshipRoles {
    // Using static variables cause edit test cases to break if not careful
    public static InternshipRole getTypicalInternshipRole1() {
        return new InternshipRoleBuilder().build();
    }

    public static InternshipRole getTypicalInternshipRole2() {
        return new InternshipRoleBuilder()
                .withName("Gov Tech")
                .withCycle("Winter")
                .withRole("Software Engineer")
                .withDescription("Good Knowledge of React")
                .withOutcome(Outcome.OFFERED)
                .withLocation("Pasir Panjang")
                .withPay(new BigDecimal("1500.00"))
                .build();
    }


}
