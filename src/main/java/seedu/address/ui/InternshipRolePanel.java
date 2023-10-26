package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Region;
import seedu.address.model.internshiprole.InternshipRole;
import javafx.scene.control.ListView;

public class InternshipRolePanel extends UiPart<Region> {
    private static final String FXML = "InternshipRolePanel.fxml";

    @FXML
    private ListView<InternshipRole> internshipRoleView;

    public InternshipRolePanel(ObservableList<InternshipRole> roles) {
        super(FXML);
        internshipRoleView.setItems(roles);
        internshipRoleView.setCellFactory(listView -> new InternshipRoleListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of 
     * a {@code InternshipRole} using a {@code InternshipRoleCard}.
     */
    class InternshipRoleListViewCell extends ListCell<InternshipRole> {
        @Override
        protected void updateItem(InternshipRole role, boolean empty) {
            super.updateItem(role, empty);

            if (empty || role == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InternshipRoleCard(role, getIndex() + 1).getRoot());
            }
        }
    }
}
