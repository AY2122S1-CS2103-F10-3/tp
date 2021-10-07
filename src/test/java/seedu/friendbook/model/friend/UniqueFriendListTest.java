package seedu.friendbook.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.friendbook.testutil.Assert.assertThrows;
import static seedu.friendbook.testutil.TypicalPersons.ALICE;
import static seedu.friendbook.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.friendbook.model.friend.exceptions.DuplicateFriendException;
import seedu.friendbook.model.friend.exceptions.FriendNotFoundException;
import seedu.friendbook.testutil.PersonBuilder;

public class UniqueFriendListTest {

    private final UniqueFriendList uniqueFriendList = new UniqueFriendList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueFriendList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueFriendList.add(ALICE);
        assertTrue(uniqueFriendList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFriendList.add(ALICE);
        Friend editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueFriendList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueFriendList.add(ALICE);
        assertThrows(DuplicateFriendException.class, () -> uniqueFriendList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendList.setFriend(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendList.setFriend(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(FriendNotFoundException.class, () -> uniqueFriendList.setFriend(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueFriendList.add(ALICE);
        uniqueFriendList.setFriend(ALICE, ALICE);
        UniqueFriendList expectedUniqueFriendList = new UniqueFriendList();
        expectedUniqueFriendList.add(ALICE);
        assertEquals(expectedUniqueFriendList, uniqueFriendList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueFriendList.add(ALICE);
        Friend editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueFriendList.setFriend(ALICE, editedAlice);
        UniqueFriendList expectedUniqueFriendList = new UniqueFriendList();
        expectedUniqueFriendList.add(editedAlice);
        assertEquals(expectedUniqueFriendList, uniqueFriendList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueFriendList.add(ALICE);
        uniqueFriendList.setFriend(ALICE, BOB);
        UniqueFriendList expectedUniqueFriendList = new UniqueFriendList();
        expectedUniqueFriendList.add(BOB);
        assertEquals(expectedUniqueFriendList, uniqueFriendList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueFriendList.add(ALICE);
        uniqueFriendList.add(BOB);
        assertThrows(DuplicateFriendException.class, () -> uniqueFriendList.setFriend(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(FriendNotFoundException.class, () -> uniqueFriendList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueFriendList.add(ALICE);
        uniqueFriendList.remove(ALICE);
        UniqueFriendList expectedUniqueFriendList = new UniqueFriendList();
        assertEquals(expectedUniqueFriendList, uniqueFriendList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendList.setFriends((UniqueFriendList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueFriendList.add(ALICE);
        UniqueFriendList expectedUniqueFriendList = new UniqueFriendList();
        expectedUniqueFriendList.add(BOB);
        uniqueFriendList.setFriends(expectedUniqueFriendList);
        assertEquals(expectedUniqueFriendList, uniqueFriendList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendList.setFriends((List<Friend>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueFriendList.add(ALICE);
        List<Friend> friendList = Collections.singletonList(BOB);
        uniqueFriendList.setFriends(friendList);
        UniqueFriendList expectedUniqueFriendList = new UniqueFriendList();
        expectedUniqueFriendList.add(BOB);
        assertEquals(expectedUniqueFriendList, uniqueFriendList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Friend> listWithDuplicateFriends = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateFriendException.class, () -> uniqueFriendList.setFriends(listWithDuplicateFriends));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFriendList.asUnmodifiableObservableList().remove(0));
    }
}
