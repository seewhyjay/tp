package seedu.address.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.fxml.FXML;

import seedu.address.model.internshiprole.InternshipRole;

import java.util.Comparator;


public class InternshipRoleCard extends UiPart<Region>{
    private static final String FXML = "InternshipRoleCard.fxml";

    private final InternshipRole internshipRole;

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private Label role;

    @FXML
    private Label cycle;

    @FXML
    private Label description;

    @FXML
    private Label pay;

    @FXML
    private Label outcome;

    @FXML
    private Label location;

    @FXML
    private FlowPane tags;

    public InternshipRoleCard(InternshipRole internshipRole, int displayedIndex) {
        super(FXML);
        this.internshipRole = internshipRole;
        id.setText(displayedIndex + ". ");
        name.setText(this.internshipRole.getName().toString());
        role.setText(this.internshipRole.getRole().toString());
        cycle.setText(this.internshipRole.getCycle().toString());
        description.setText(this.internshipRole.getDescription().toString());
        pay.setText(this.internshipRole.getPay().toString());
        outcome.setText(this.internshipRole.getApplicationOutcome().toString());
        location.setText(this.internshipRole.getLocation().toString());
        internshipRole.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }


}
