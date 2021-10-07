package seedu.friendbook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.friendbook.model.friend.Friend;

public class BirthdayCard extends UiPart<Region> {

    private static final String FXML = "BirthdayListCard.fxml";

    public final Friend friend;

    @FXML
    private HBox cardPane;
    @FXML
    private ImageView friendImage;
    @FXML
    private Label name;
    @FXML
    private Label dob;
    @FXML
    private Label daysToBirthday;


    /**
     * Creates a {@code PersonCode} with the given {@code Friend} and index to display.
     */
    public BirthdayCard(Friend friend, int displayedIndex) {
        super(FXML);
        this.friend = friend;

        name.setText(friend.getName().fullName);

        //TODO: to update friendpicture

        dob.setText(friend.getBirthday().getActualDate());
        // TODO: add daysToBirthDay values here
        daysToBirthday.setText("0");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FriendCard)) {
            return false;
        }

        // state check
        FriendCard card = (FriendCard) other;
        return friend.equals(card.friend);
    }
}
