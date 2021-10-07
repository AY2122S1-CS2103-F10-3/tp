package seedu.friendbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.friendbook.commons.exceptions.IllegalValueException;
import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.friend.Friend;

/**
 * An Immutable FriendBook that is serializable to JSON format.
 */
@JsonRootName(value = "friendbook")
class JsonSerializableFriendBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate friend(s).";

    private final List<JsonAdaptedFriend> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFriendBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableFriendBook(@JsonProperty("persons") List<JsonAdaptedFriend> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyFriendBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFriendBook}.
     */
    public JsonSerializableFriendBook(ReadOnlyFriendBook source) {
        persons.addAll(source.getFriendList().stream().map(JsonAdaptedFriend::new).collect(Collectors.toList()));
    }

    /**
     * Converts this friend book into the model's {@code FriendBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FriendBook toModelType() throws IllegalValueException {
        FriendBook friendBook = new FriendBook();
        for (JsonAdaptedFriend jsonAdaptedFriend : persons) {
            Friend friend = jsonAdaptedFriend.toModelType();
            if (friendBook.hasFriend(friend)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            friendBook.addFriend(friend);
        }
        return friendBook;
    }

}
