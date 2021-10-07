package seedu.friendbook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.friend.Friend;

/**
 * Panel containing the list of friends' birthdays.
 */
public class BirthdayListPanel extends UiPart<Region> {

    private static final String FXML = "BirthdayListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FriendListPanel.class);

    @javafx.fxml.FXML
    private ListView<Friend> birthdayListView;

    /**
     * Creates a {@code FriendListPanel} with the given {@code ObservableList}.
     */
    public BirthdayListPanel(ObservableList<Friend> friendList) {
        super(FXML);
        birthdayListView.setItems(friendList);
        birthdayListView.setCellFactory(listView -> new BirthdayListPanel.BirthdayListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Friend} using a {@code FriendCard}.
     */
    class BirthdayListViewCell extends ListCell<Friend> {
        @Override
        protected void updateItem(Friend friend, boolean empty) {
            super.updateItem(friend, empty);

            if (empty || friend == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BirthdayCard(friend, getIndex() + 1).getRoot());
            }
        }
    }
}
