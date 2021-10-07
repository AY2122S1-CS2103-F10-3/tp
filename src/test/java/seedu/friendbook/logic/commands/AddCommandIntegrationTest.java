package seedu.friendbook.logic.commands;

import static seedu.friendbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.friendbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.friendbook.testutil.TypicalPersons.getTypicalFriendBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.friendbook.model.Model;
import seedu.friendbook.model.ModelManager;
import seedu.friendbook.model.UserPrefs;
import seedu.friendbook.model.friend.Friend;
import seedu.friendbook.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFriendBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Friend validFriend = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getFriendBook(), new UserPrefs());
        expectedModel.addFriend(validFriend);

        assertCommandSuccess(new AddCommand(validFriend), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validFriend), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Friend friendInList = model.getFriendBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(friendInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
