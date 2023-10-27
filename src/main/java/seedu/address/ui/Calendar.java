package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.LinkedList;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.logic.Logic;
import seedu.address.model.View;
import seedu.address.model.unique.UniqueModelWithDate;

/**
 * Calendar that displays assignments name
 */
public class Calendar extends UiPart<Region> {
    private static final String FXML = "Calendar.fxml";

    private static final int maxNumOfNamesToDisplay = 2;

    private YearMonth selectedCalendarView = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth());

    private ObservableList<? extends UniqueModelWithDate<?>> selectedList;

    private Logic logic;

    private ListChangeListener<View> handleViewChange = (change) -> {
        change.next();
        if (change.wasReplaced() || change.wasAdded()) {
            ObservableList<? extends View> selectedView = change.getList();
            View v = selectedView.get(0);
            switch (v) {
            case ASSIGNMENTS:
                selectedList = logic.getUnfilteredAssignmentList();
                break;
            case INTERNSHIPS:
                selectedList = logic.getFilteredInternshipTaskList();
                break;
            default:
                break;
            }
        }
        handleCalendarChange(0);
    };

    @FXML
    private Label calendarDate;


    @FXML
    private GridPane calendar;

    /**
     * Constructs a calendar with the provided list as info on what to display
     */
    public Calendar(Logic logic, View defaultView) {
        super(FXML);
        this.logic = logic;
        this.logic.subscribeViewChange(handleViewChange, defaultView);
    }


    @FXML
    private void handleCalendarLeftClick() {
        handleCalendarChange(-1);
    }

    /**
     * Shows next month when button is clicked
     */
    @FXML
    private void handleCalendarRightClick() {
        handleCalendarChange(1);
    }

    private void initCalendar() {
        handleCalendarChange(0);
    }

    private void addDaysIndicator(GridPane calendar) {
        Label sun = new Label("Sun");
        sun.getStyleClass().add("cal-enabled");
        sun.getStyleClass().add("bold");
        calendar.add(sun, 0, 0, 1, 1);

        Label mon = new Label("Mo");
        mon.getStyleClass().add("cal-enabled");
        mon.getStyleClass().add("bold");
        calendar.add(mon, 1, 0, 1, 1);

        Label tues = new Label("Tu");
        tues.getStyleClass().add("cal-enabled");
        tues.getStyleClass().add("bold");
        calendar.add(tues, 2, 0, 1, 1);

        Label wed = new Label("Wed");
        wed.getStyleClass().add("cal-enabled");
        wed.getStyleClass().add("bold");
        calendar.add(wed, 3, 0, 1, 1);

        Label thurs = new Label("Th");
        thurs.getStyleClass().add("cal-enabled");
        thurs.getStyleClass().add("bold");
        calendar.add(thurs, 4, 0, 1, 1);

        Label fri = new Label("Fri");
        fri.getStyleClass().add("cal-enabled");
        fri.getStyleClass().add("bold");
        calendar.add(fri, 5, 0, 1, 1);

        Label sat = new Label("Sat");
        sat.getStyleClass().add("cal-enabled");
        sat.getStyleClass().add("bold");
        calendar.add(sat, 6, 0, 1, 1);
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

    private void addNamesToCell(VBox vbox, LinkedList<String> names) {
        int currCount = 0;

        for (String name : names) {
            if (currCount >= maxNumOfNamesToDisplay) {
                break;
            }
            Label l = new Label(name);
            l.setWrapText(true);
            l.setMaxWidth(500000);
            l.setStyle("-fx-text-fill: #ebebeb; -fx-background-color: #262626; "
                    + "-fx-background-radius: 0.5em; -fx-max-height: 30;");
            vbox.getChildren().add(l);
            currCount += 1;
        }
    }

    /**
     * Shifts the current month of the calendar by the given input
     * @param monthsToAdd the amount to shift
     */
    public void handleCalendarChange(int monthsToAdd) {
        // Reset calendar
        calendar.getChildren().clear();

        // Updating calendar on button pressed by user
        selectedCalendarView = selectedCalendarView.plusMonths(monthsToAdd);

        calendarDate.setText(selectedCalendarView.getMonth() + " " + selectedCalendarView.getYear());
        addDaysIndicator(calendar);

        fillStartOfCurrentMonthToEndOfCalendar();
        fillStartOfCalendarToEndOfPrevMonth();
    }

    private void fillStartOfCalendarToEndOfPrevMonth() {
        LocalDate prevMonthDate = selectedCalendarView.plusMonths(-1).atEndOfMonth();
        int columnIndex = dayToIndex(prevMonthDate.getDayOfWeek());

        // If columnIndex == 6, it means first day of curr
        // month is at [0, 0], means grid is filled
        if (columnIndex != 6) {
            while (columnIndex >= 0) {
                VBox dayContainer = new VBox();
                dayContainer.setSpacing(3);
                dayContainer.getStyleClass().add("h-pane");
                Label day = new Label(Integer.toString(prevMonthDate.getDayOfMonth()));
                prevMonthDate = prevMonthDate.plusDays(-1);
                day.getStyleClass().add("cal-disabled");
                dayContainer.getChildren().add(day);
                calendar.add(dayContainer, columnIndex, 1, 1, 1);
                columnIndex -= 1;
            }
        }
    }

    private void fillStartOfCurrentMonthToEndOfCalendar() {
        int rowIndex = 1;

        LocalDate newMonthDate = selectedCalendarView.atDay(1);
        LocalDate endOfMonthDate = selectedCalendarView.atEndOfMonth();

        // Loop through the entire calendar array
        while (rowIndex <= 6) {
            int columnIndex = dayToIndex(newMonthDate.getDayOfWeek());
            int val = newMonthDate.getDayOfMonth();
            VBox dayContainer = new VBox();
            dayContainer.setSpacing(3);
            dayContainer.getStyleClass().add("h-pane");

            calendar.add(dayContainer, columnIndex, rowIndex, 1, 1);

            Label day = new Label(Integer.toString(val));
            VBox.setVgrow(day, Priority.ALWAYS);
            dayContainer.getChildren().add(day);

            HashMap<LocalDate, LinkedList<String>> map = new HashMap<>();
            for (UniqueModelWithDate<?> elem : selectedList) {
                elem.hashDateToNameWith(map);
            }

            // Assigning assignment names to current month only
            if (map.containsKey(newMonthDate) && !newMonthDate.isAfter(endOfMonthDate)) {
                addNamesToCell(dayContainer, map.get(newMonthDate));
            }

            // Setting styles accordingly
            if (!newMonthDate.isAfter(endOfMonthDate)) {
                day.getStyleClass().add("cal-enabled");
            } else {
                day.getStyleClass().add("cal-disabled");
            }

            if (newMonthDate.equals(LocalDate.now())) {
                day.setStyle("-fx-text-fill: #BB66FC;");
            }

            // Check if end of calendar column (Saturday)
            // if yes, move on to the next row (Sunday)
            if (columnIndex == 6) {
                rowIndex += 1;
            }
            newMonthDate = newMonthDate.plusDays(1);
        }
    }
}
