package seedu.friendbook.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.model.friend.Friend;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Friend> PREDICATE_SHOW_ALL_FRIENDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' friend book file path.
     */
    Path getFriendBookFilePath();

    /**
     * Sets the user prefs' friend book file path.
     */
    void setFriendBookFilePath(Path friendBookFilePath);

    /**
     * Replaces friend book data with the data in {@code friendBook}.
     */
    void setFriendBook(ReadOnlyFriendBook friendBook);

    /** Returns the FriendBook */
    ReadOnlyFriendBook getFriendBook();

    /**
     * Returns true if a friend with the same identity as {@code friend} exists in the friend book.
     */
    boolean hasFriend(Friend friend);

    /**
     * Deletes the given friend.
     * The friend must exist in the friend book.
     */
    void deleteFriend(Friend target);

    /**
     * Adds the given friend.
     * {@code friend} must not already exist in the friend book.
     */
    void addFriend(Friend friend);

    /**
     * Replaces the given friend {@code target} with {@code editedFriend}.
     * {@code target} must exist in the friend book.
     * The friend identity of {@code editedFriend} must not be the same as another existing friend in the friend book.
     */
    void setFriend(Friend target, Friend editedFriend);

    /** Returns an unmodifiable view of the filtered friend list */
    ObservableList<Friend> getFilteredFriendList();

    /**
     * Updates the filter of the filtered friend list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFriendList(Predicate<Friend> predicate);
}
