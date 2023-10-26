package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class InternPanel extends UiPart<Region> {

    private final static String FXML = "InternPanel.fxml";

    @FXML
    private VBox internshipRolePlaceHolder;

    @FXML
    private VBox internshipTaskPlaceHolder;

    public InternPanel(InternshipRolePanel rolePanel, InternshipTaskPanel taskPanel) {
        super(FXML);
        internshipRolePlaceHolder.getChildren().add(rolePanel.getRoot());
        internshipTaskPlaceHolder.getChildren().add(taskPanel.getRoot());
    }

}
