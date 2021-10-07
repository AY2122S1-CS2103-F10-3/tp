package seedu.friendbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.ReadOnlyUserPrefs;
import seedu.friendbook.model.friend.Friend;
import seedu.friendbook.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Friend validFriend = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validFriend).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validFriend), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFriend), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Friend validFriend = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validFriend);
        ModelStub modelStub = new ModelStubWithPerson(validFriend);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Friend alice = new PersonBuilder().withName("Alice").build();
        Friend bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different friend -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getFriendBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFriendBookFilePath(Path friendBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFriend(Friend friend) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFriendBook(ReadOnlyFriendBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFriendBook getFriendBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFriend(Friend friend) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFriend(Friend target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFriend(Friend target, Friend editedFriend) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Friend> getFilteredFriendList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFriendList(Predicate<Friend> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single friend.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Friend friend;

        ModelStubWithPerson(Friend friend) {
            requireNonNull(friend);
            this.friend = friend;
        }

        @Override
        public boolean hasFriend(Friend friend) {
            requireNonNull(friend);
            return this.friend.isSameFriend(friend);
        }
    }

    /**
     * A Model stub that always accept the friend being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Friend> personsAdded = new ArrayList<>();

        @Override
        public boolean hasFriend(Friend friend) {
            requireNonNull(friend);
            return personsAdded.stream().anyMatch(friend::isSameFriend);
        }

        @Override
        public void addFriend(Friend friend) {
            requireNonNull(friend);
            personsAdded.add(friend);
        }

        @Override
        public ReadOnlyFriendBook getFriendBook() {
            return new FriendBook();
        }
    }

}
