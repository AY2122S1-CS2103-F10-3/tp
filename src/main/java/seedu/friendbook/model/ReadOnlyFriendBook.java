package seedu.friendbook.model;

import javafx.collections.ObservableList;
import seedu.friendbook.model.friend.Friend;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyFriendBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Friend> getFriendList();

}
