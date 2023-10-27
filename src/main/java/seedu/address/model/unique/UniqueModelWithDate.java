package seedu.address.model.unique;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public interface UniqueModelWithDate<T> extends Unique<T> {
     void hashDateToNameWith(HashMap<LocalDate, LinkedList<String>> map);
}
