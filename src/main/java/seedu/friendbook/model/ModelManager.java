package seedu.friendbook.model;

import static java.util.Objects.requireNonNull;
import static seedu.friendbook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.friend.Friend;

/**
 * Represents the in-memory model of the friend book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FriendBook friendBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Friend> filteredFriends;

    /**
     * Initializes a ModelManager with the given friendBook and userPrefs.
     */
    public ModelManager(ReadOnlyFriendBook friendBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(friendBook, userPrefs);

        logger.fine("Initializing with friend book: " + friendBook + " and user prefs " + userPrefs);

        this.friendBook = new FriendBook(friendBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFriends = new FilteredList<>(this.friendBook.getFriendList());
    }

    public ModelManager() {
        this(new FriendBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFriendBookFilePath() {
        return userPrefs.getFriendBookFilePath();
    }

    @Override
    public void setFriendBookFilePath(Path friendBookFilePath) {
        requireNonNull(friendBookFilePath);
        userPrefs.setFriendBookFilePath(friendBookFilePath);
    }

    //=========== FriendBook ================================================================================

    @Override
    public void setFriendBook(ReadOnlyFriendBook friendBook) {
        this.friendBook.resetData(friendBook);
    }

    @Override
    public ReadOnlyFriendBook getFriendBook() {
        return friendBook;
    }

    @Override
    public boolean hasFriend(Friend friend) {
        requireNonNull(friend);
        return friendBook.hasFriend(friend);
    }

    @Override
    public void deleteFriend(Friend target) {
        friendBook.removeFriend(target);
    }

    @Override
    public void addFriend(Friend friend) {
        friendBook.addFriend(friend);
        updateFilteredFriendList(PREDICATE_SHOW_ALL_FRIENDS);
    }

    @Override
    public void setFriend(Friend target, Friend editedFriend) {
        requireAllNonNull(target, editedFriend);

        friendBook.setFriend(target, editedFriend);
    }

    //=========== Filtered Friend List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Friend} backed by the internal list of
     * {@code versionedFriendBook}
     */
    @Override
    public ObservableList<Friend> getFilteredFriendList() {
        return filteredFriends;
    }

    @Override
    public void updateFilteredFriendList(Predicate<Friend> predicate) {
        requireNonNull(predicate);
        filteredFriends.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return friendBook.equals(other.friendBook)
                && userPrefs.equals(other.userPrefs)
                && filteredFriends.equals(other.filteredFriends);
    }

}
