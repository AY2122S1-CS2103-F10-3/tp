package seedu.friendbook.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.friendbook.model.friend.*;
import seedu.friendbook.model.friend.Friend;
import seedu.friendbook.model.tag.Tag;
import seedu.friendbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building Friend objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BIRTHDAY = "1994-09-12";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Birthday birthday;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code friendToCopy}.
     */
    public PersonBuilder(Friend friendToCopy) {
        name = friendToCopy.getName();
        phone = friendToCopy.getPhone();
        email = friendToCopy.getEmail();
        address = friendToCopy.getAddress();
        birthday = friendToCopy.getBirthday();
        tags = new HashSet<>(friendToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Friend} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Friend} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Friend} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Friend} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Friend} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Friend} that we are building.
     */
    public PersonBuilder withBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
        return this;
    }

    public Friend build() {
        return new Friend(name, phone, email, address, tags, birthday);
    }

}
