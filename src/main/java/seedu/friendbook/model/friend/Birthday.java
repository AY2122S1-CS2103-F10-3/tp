package seedu.friendbook.model.friend;

import static java.util.Objects.requireNonNull;
import static seedu.friendbook.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Birthday {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthdays should be in 'YYYY-MM-DD' format and should not be blank";

    public final String value;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday in 'YYYY-MM-DD' format.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        value = birthday;
    }

    /**
     * Checks if the specified birthday has passed
     * @param birthday A valid birthday in 'YYYY-MM-DD' format
     * @return false if birthday has yet to occur
     */
    public static boolean hasBirthdayPassed(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() > birthday.getYear()
                || currentDate.getYear() == birthday.getYear()
                    && currentDate.getMonthValue() > birthday.getMonthValue()
                || currentDate.getYear() == birthday.getYear()
                    && currentDate.getMonthValue() == birthday.getMonthValue()
                    && currentDate.getDayOfMonth() >= birthday.getDayOfMonth();
    }

    /**
     * Returns true if the input birthday follows the 'yyyy-MM-dd' format and if it has occurred
     */
    public static boolean isValidBirthday(String test) {
        boolean isValid;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate date = formatter.parse(test, LocalDate::from);
            isValid = hasBirthdayPassed(date);
        } catch (DateTimeParseException e) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Converts stored value of 'yyyy-MM-dd' format to 'MMM d yyyy' for ease of viewing
     */
    public String getActualDate() {
        LocalDate date = LocalDate.parse(value);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Birthday
                && value.equals(((Birthday) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
