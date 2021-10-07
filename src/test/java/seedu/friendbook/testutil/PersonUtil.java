package seedu.friendbook.testutil;

import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.friendbook.logic.commands.AddCommand;
import seedu.friendbook.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.friendbook.model.friend.Friend;
import seedu.friendbook.model.tag.Tag;


/**
 * A utility class for Friend.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code friend}.
     */
    public static String getAddCommand(Friend friend) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(friend);
    }

    /**
     * Returns the part of command string for the given {@code friend}'s details.
     */
    public static String getPersonDetails(Friend friend) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + friend.getName().fullName + " ");
        sb.append(PREFIX_PHONE + friend.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + friend.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + friend.getAddress().value + " ");
        sb.append(PREFIX_BIRTHDAY + friend.getBirthday().value + " ");
        friend.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getBirthday().ifPresent(birthday -> sb.append(PREFIX_BIRTHDAY).append(birthday.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
