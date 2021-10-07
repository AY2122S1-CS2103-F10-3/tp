package seedu.friendbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.friendbook.testutil.Assert.assertThrows;
import static seedu.friendbook.testutil.TypicalPersons.ALICE;
import static seedu.friendbook.testutil.TypicalPersons.getTypicalFriendBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.friendbook.model.friend.Friend;
import seedu.friendbook.model.friend.exceptions.DuplicateFriendException;
import seedu.friendbook.testutil.PersonBuilder;

public class FriendBookTest {

    private final FriendBook friendBook = new FriendBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), friendBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFriendBook_replacesData() {
        FriendBook newData = getTypicalFriendBook();
        friendBook.resetData(newData);
        assertEquals(newData, friendBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two friends with the same identity fields
        Friend editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Friend> newFriends = Arrays.asList(ALICE, editedAlice);
        FriendBookStub newData = new FriendBookStub(newFriends);

        assertThrows(DuplicateFriendException.class, () -> friendBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendBook.hasFriend(null));
    }

    @Test
    public void hasPerson_personNotInFriendBook_returnsFalse() {
        assertFalse(friendBook.hasFriend(ALICE));
    }

    @Test
    public void hasPerson_personInFriendBook_returnsTrue() {
        friendBook.addFriend(ALICE);
        assertTrue(friendBook.hasFriend(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInFriendBook_returnsTrue() {
        friendBook.addFriend(ALICE);
        Friend editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(friendBook.hasFriend(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> friendBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyFriendBook whose friends list can violate interface constraints.
     */
    private static class FriendBookStub implements ReadOnlyFriendBook {
        private final ObservableList<Friend> friends = FXCollections.observableArrayList();

        FriendBookStub(Collection<Friend> friends) {
            this.friends.setAll(friends);
        }

        @Override
        public ObservableList<Friend> getPersonList() {
            return friends;
        }
    }

}
