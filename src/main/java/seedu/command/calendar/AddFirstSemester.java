package seedu.command.calendar;

import seedu.command.Command;
import seedu.event.EventList;
import seedu.exception.DukeException;

public class AddFirstSemester extends Command {
    private EventList eventList;
    private Integer year;

    public AddFirstSemester(EventList eventList, Integer year) {
        this.eventList = eventList;
        this.year = year;
    }

    @Override
    public void execute() throws DukeException {
        CalendarList.showFirstSemester(eventList, year);
    }
}