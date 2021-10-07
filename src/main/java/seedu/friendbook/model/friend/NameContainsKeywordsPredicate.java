package seedu.friendbook.model.friend;

import java.util.List;
import java.util.function.Predicate;

import seedu.friendbook.commons.util.StringUtil;

/**
 * Tests that a {@code Friend}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Friend> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Friend friend) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(friend.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
