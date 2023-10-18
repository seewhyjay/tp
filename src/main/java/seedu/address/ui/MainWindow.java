package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private YearMonth selectedCalendarView = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth());

    // Independent Ui parts residing in this Ui container

    // Assignments
    private AssignmentListPanel assignmentListPanel;

    // Persons
    private PersonListPanel personListPanel;

    private ResultDisplay resultDisplay;

    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private VBox personList;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private VBox assignmentList;

    @FXML
    private StackPane assignmentListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private HBox viewSwitcher;

    @FXML
    private VBox selectedList;

    @FXML
    private StackPane selectedListPanelPlaceholder;

    @FXML
    private GridPane calendar;

    @FXML
    private Label calendarDate;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        assignmentListPanel = new AssignmentListPanel(logic.getFilteredAssignmentList());
        selectedListPanelPlaceholder.getChildren().add(assignmentListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        initCalender();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public AssignmentListPanel getAssignmentListPanel() {
        return assignmentListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Display a list of person when
     * button is clicked
     */
    @FXML
    public void handleSetPersonView() {
        selectedListPanelPlaceholder.getChildren().clear();
        selectedListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
    }

    /**
     * Display a list of assignment
     * when button is clicked
     */
    @FXML
    public void handleSetAssignmentView() {
        selectedListPanelPlaceholder.getChildren().clear();
        selectedListPanelPlaceholder.getChildren().add(assignmentListPanel.getRoot());
    }

    private void initCalender() {
        handleCalenderChange(0);
    }

    private void addDaysIndicator(GridPane calender) {
        Label sun = new Label("Sun");
        sun.getStyleClass().add("cal-enabled");
        sun.getStyleClass().add("bold");
        calender.add(sun, 0, 0, 1, 1);

        Label mon = new Label("Mo");
        mon.getStyleClass().add("cal-enabled");
        mon.getStyleClass().add("bold");
        calender.add(mon, 1, 0, 1, 1);

        Label tues = new Label("Tu");
        tues.getStyleClass().add("cal-enabled");
        tues.getStyleClass().add("bold");
        calender.add(tues, 2, 0, 1, 1);

        Label wed = new Label("Wed");
        wed.getStyleClass().add("cal-enabled");
        wed.getStyleClass().add("bold");
        calender.add(wed, 3, 0, 1, 1);

        Label thurs = new Label("Th");
        thurs.getStyleClass().add("cal-enabled");
        thurs.getStyleClass().add("bold");
        calender.add(thurs, 4, 0, 1, 1);

        Label fri = new Label("Fri");
        fri.getStyleClass().add("cal-enabled");
        fri.getStyleClass().add("bold");
        calender.add(fri, 5, 0, 1, 1);

        Label sat = new Label("Sat");
        sat.getStyleClass().add("cal-enabled");
        sat.getStyleClass().add("bold");
        calender.add(sat, 6, 0, 1, 1);
    }


    private int dayToIndex(DayOfWeek d) {
        if (d == DayOfWeek.SUNDAY) {
            return 0;
        } else if (d == DayOfWeek.MONDAY) {
            return 1;
        } else if (d == DayOfWeek.TUESDAY) {
            return 2;
        } else if (d == DayOfWeek.WEDNESDAY) {
            return 3;
        } else if (d == DayOfWeek.THURSDAY) {
            return 4;
        } else if (d == DayOfWeek.FRIDAY) {
            return 5;
        } else {
            return 6;
        }
    }

    private void handleCalenderChange(int monthsToAdd) {
        // Updating calendar by on the button pressed by user
        selectedCalendarView = selectedCalendarView.plusMonths(monthsToAdd);

        calendarDate.setText(selectedCalendarView.getMonth() + " " + selectedCalendarView.getYear());
        addDaysIndicator(calendar);

        int rowIndex = 1;

        LocalDate newMonthDate = selectedCalendarView.atDay(1);
        LocalDate endOfMonthDate = selectedCalendarView.atEndOfMonth();

        // Loop through the entire calendar array
        while (rowIndex <= 6) {
            int columnIndex = dayToIndex(newMonthDate.getDayOfWeek());
            int val = newMonthDate.getDayOfMonth();
            Label day = new Label(Integer.toString(val));

            // Setting styles accordingly
            if (!newMonthDate.isAfter(endOfMonthDate)) {
                day.getStyleClass().add("cal-enabled");
            } else {
                day.getStyleClass().add("cal-disabled");
            }

            if (newMonthDate.equals(LocalDate.now())) {
                day.setStyle("-fx-background-color: #282828; -fx-background-radius: 0.5em;");
            }

            calendar.add(day, columnIndex, rowIndex, 1, 1);

            // Check if end of calendar column (Saturday)
            // if yes, move on to the next row (Sunday)
            if (columnIndex == 6) {
                rowIndex += 1;
            }
            newMonthDate = newMonthDate.plusDays(1);
        }

        LocalDate prevMonthDate = selectedCalendarView.plusMonths(-1).atEndOfMonth();
        int columnIndex = dayToIndex(prevMonthDate.getDayOfWeek());

        // If columnIndex == 6, it means all the days are filed
        if (columnIndex != 6) {
            while (columnIndex >= 0) {
                Label day = new Label(Integer.toString(prevMonthDate.getDayOfMonth()));

                if (prevMonthDate.equals(LocalDate.now())) {
                    day.setStyle("-fx-background-color: #282828; -fx-background-radius: 0.5em;");
                }

                prevMonthDate = prevMonthDate.plusDays(-1);
                day.getStyleClass().add("cal-disabled");
                calendar.add(day, columnIndex, 1, 1, 1);
                columnIndex -= 1;
            }
        }
    }


    /**
     * Shows previous month when button is clicked
     */
    @FXML
    public void handleCalendarLeftClick() {
        calendar.getChildren().clear();
        handleCalenderChange(-1);
    }

    /**
     * Shows next month when button is clicked
     */
    @FXML
    public void handleCalendarRightClick() {

        calendar.getChildren().clear();
        handleCalenderChange(1);
    }
}
