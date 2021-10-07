package seedu.friendbook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.friendbook.model.friend.Friend;
import seedu.friendbook.model.friend.UniqueFriendList;

/**
 * Wraps all data at the friend-book level
 * Duplicates are not allowed (by .isSameFriend comparison)
 */
public class FriendBook implements ReadOnlyFriendBook {

    private final UniqueFriendList friends;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        friends = new UniqueFriendList();
    }

    public FriendBook() {}

    /**
     * Creates a FriendBook using the Friends in the {@code toBeCopied}
     */
    public FriendBook(ReadOnlyFriendBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the friend list with {@code friends}.
     * {@code friends} must not contain duplicate friends.
     */
    public void setFriends(List<Friend> friends) {
        this.friends.setFriends(friends);
    }

    /**
     * Resets the existing data of this {@code FriendBook} with {@code newData}.
     */
    public void resetData(ReadOnlyFriendBook newData) {
        requireNonNull(newData);

        setFriends(newData.getFriendList());
    }

    //// friend-level operations

    /**
     * Returns true if a friend with the same identity as {@code friend} exists in the friend book.
     */
    public boolean hasFriend(Friend friend) {
        requireNonNull(friend);
        return friends.contains(friend);
    }

    /**
     * Adds a friend to the friend book.
     * The friend must not already exist in the friend book.
     */
    public void addFriend(Friend p) {
        friends.add(p);
    }

    /**
     * Replaces the given friend {@code target} in the list with {@code editedFriend}.
     * {@code target} must exist in the friend book.
     * The friend identity of {@code editedFriend} must not be the same as another existing friend in the friend book.
     */
    public void setFriend(Friend target, Friend editedFriend) {
        requireNonNull(editedFriend);

        friends.setFriend(target, editedFriend);
    }

    /**
     * Removes {@code key} from this {@code FriendBook}.
     * {@code key} must exist in the friend book.
     */
    public void removeFriend(Friend key) {
        friends.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return friends.asUnmodifiableObservableList().size() + " friends";
        // TODO: refine later
    }

    @Override
    public ObservableList<Friend> getFriendList() {
        return friends.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendBook // instanceof handles nulls
                && friends.equals(((FriendBook) other).friends));
    }

    @Override
    public int hashCode() {
        return friends.hashCode();
    }
}
