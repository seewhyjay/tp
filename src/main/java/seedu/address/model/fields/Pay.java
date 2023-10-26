package seedu.address.model.fields;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * A class representing salary
 */
public class Pay {
    // Matches positive numbers, any dp. Inputs cannot start with a period
    private static final String VALID_PAY_REGEX = "^[+]?\\d+([.]\\d+)?$";

    public static final String MESSAGE_CONSTRAINTS = "Pay must be positive number";

    private final BigDecimal pay;

    private final NumberFormat moneyFormatter;

    /**
     * Creates a pay object
     * @param pay the salary amount
     */
    public Pay(BigDecimal pay) {
        requireNonNull(pay);
        this.pay = pay;

        moneyFormatter = NumberFormat.getCurrencyInstance();
        moneyFormatter.setRoundingMode(RoundingMode.DOWN);
        moneyFormatter.setMinimumFractionDigits(2);
        moneyFormatter.setMaximumFractionDigits(2);
    }

    /**
     * @param pay to be verified
     * @return true if input is a positive number and does not start with a period
     */
    public static boolean isValidPay(String pay) {
        return pay.matches(VALID_PAY_REGEX);
    }

    public BigDecimal getPay() {
        return pay;
    }

    /**
     * @return a string in "money" representation with commas and unit
     */
    @Override
    public String toString() {
        return moneyFormatter.format(pay);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Pay)) {
            return false;
        }

        Pay otherPay = (Pay) other;
        return pay.equals(otherPay.pay);
    }

    @Override
    public int hashCode() {
        return pay.hashCode();
    }

}
