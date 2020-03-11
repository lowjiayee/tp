package seedu.command.attendance;

import seedu.command.Command;
import seedu.exception.DukeException;
import seedu.attendance.Attendance;
import seedu.storage.Storage;
import seedu.ui.UI;

public class AddAttendanceCommand extends Command {

    private final Attendance attendance;

    /**
     * Creates a new AddAttendanceCommand with the given task.
     *
     * @param attendance The student attendance to add.
     */

    public AddAttendanceCommand(Attendance attendance) {
        this.attendance = attendance;
    }

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The duke.storage object containing task list.
     */

    @Override
    public void execute(UI ui, Storage storage) throws DukeException {
        storage.getAttendance().add(attendance);
    }
}
