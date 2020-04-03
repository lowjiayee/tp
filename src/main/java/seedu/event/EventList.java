package seedu.event;

import seedu.exception.PacException;
import seedu.ui.DisplayList;
import seedu.ui.UI;

import java.util.ArrayList;

public class EventList {
    public ArrayList<Event> list;
    private DisplayList displayList;
    private UI ui;

    /**
     * Empty constructor. Creates an empty list.
     */
    public EventList() {
        this.list = new ArrayList<>();
        this.displayList = new DisplayList();
        this.ui = new UI();
    }

    /**
     * Add the specified event to the end of list.
     * @param event the event to be appended
     */
    public void add(Event event) throws PacException {
        checkDuplicateName(event.getName());
        if (!event.dateTimeIsParsed()) {
            ui.printInvalidDateTimeFormat();
        }
        list.add(event);
        if (event instanceof Seminar) {
            ui.addEventMessage("Seminar", event.getName());
        } else {
            ui.addEventMessage("Event", event.getName());
        }
    }

    private void checkDuplicateName(String newEventName) throws PacException {
        for (Event event : list) {
            String oldEventName = event.getName().toLowerCase();
            if (oldEventName.equals(newEventName.toLowerCase())) {
                throw new PacException("Event already exists in the list. Please add a new Event.");
            }
        }
    }

    /**
     * Delete the first instance of the specified event in the list.
     * Returns {@code true} if the specified event is found (and removed)
     * from the list.
     * @param event the event to be deleted
     * @return {@code true} if the specified event is found (and removed)
     *      from the list
     */
    public boolean delete(Event event) {
        return list.remove(event);
    }

    /**
     * Removes the event at the specified position in this list.
     * Shifts any subsequent events to the left (subtracts one from their indices).
     *
     * @param index the index of the element to be removed
     */
    public void delete(int index) throws PacException {
        if (index < 0) {
            throw new PacException("Invalid index, must start from 1.");
        }
        if (index >= list.size()) {
            throw new PacException("Index not found.");
        }

        if (list.get(index) instanceof Seminar) {
            ui.deleteEventMessage("Seminar", list.get(index).getName());
        } else {
            ui.deleteEventMessage("Event", list.get(index).getName());
        }
        list.remove(index);
    }

    /**
     * Returns the event at the specified position in this list.
     * @param index index of the event to find.
     * @return the event in the specified position.
     * @throws PacException If list is empty.
     */
    public Event find(int index) throws PacException {
        if (index < 0) {
            throw new PacException("Invalid index, must start from 1.");
        }
        if (index >= list.size()) {
            throw new PacException("Index not found.");
        }
        return list.get(index);
    }

    /**
     * Change the name of the event in the specified index.
     * @param index index of the event
     * @param name new name for the event
     */
    public void editName(int index, String name) throws PacException {
        if (name.isEmpty()) {
            throw new PacException("Event name is empty");
        }
        checkDuplicateName(name);
        Event event = this.find(index);
        if (event instanceof Seminar) {
            ui.editEventNameMessage(event.getName(), name, "Seminar");
        } else {
            ui.editEventNameMessage(event.getName(), name, "Event");
        }
        event.setName(name);
    }

    /**
     * Change the datetime of the event in the specified index.
     * @param index index of the event
     * @param datetime new datetime for the event
     */
    public void editDatetime(int index, String datetime) throws PacException {
        Event event = this.find(index);
        String oldDateTime = event.getDatetime();
        event.setDatetime(datetime);
        if (!event.dateTimeIsParsed()) {
            throw new PacException("Wrong format for date and time. Please enter: yyyy-MM-dd HHmm");
        }
        String newDateTime = event.getDatetime();
        if (event instanceof Seminar) {
            ui.editEventDateTimeMessage(oldDateTime, newDateTime, "Seminar");
        } else {
            ui.editEventDateTimeMessage(oldDateTime, newDateTime, "Event");
        }
    }

    /**
     * Change the venue of the event in the specified index.
     * @param index index of the event
     * @param venue new venue for the event
     */
    public void editVenue(int index, String venue) throws PacException {
        if (venue.isEmpty()) {
            throw new PacException("Venue is empty");
        }
        Event event = this.find(index);
        if (event instanceof Seminar) {
            ui.editEventVenueMessage(event.getVenue(), venue, "Seminar");
        } else {
            ui.editEventVenueMessage(event.getVenue(), venue, "Event");
        }
        event.setVenue(venue);
    }

    /**
     * Edits all the fields in the event, ie. name, date and time, venue.
     *
     * @param index Index of the event to be edited.
     * @param event New event that user inputs.
     * @throws PacException If list is empty.
     */
    public void editEvent(int index, Event event) throws PacException {
        if (index >= list.size()) {
            throw new PacException("Index not found.");
        }
        if (event instanceof Seminar) {
            ui.editEventMessage(list.get(index).toString(), event.toString(), "Seminar");
        } else {
            ui.editEventMessage(list.get(index).toString(), event.toString(), "Event");
        }
        list.remove(index);
        list.add(index, event);
        if (!event.dateTimeIsParsed()) {
            ui.printInvalidDateTimeFormat();
        }
    }

    public int getSize() {
        return list.size();
    }

    public Event getEvent(String eventName) throws PacException {
        if (list.isEmpty()) {
            throw new PacException("The event list is empty.");
        }
        for (Event event : list) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }
        throw new PacException("Event is not found in the list.");
    }

    /**
     * Lists all types of events.
     *
     * @throws PacException If list is empty.
     */
    public void listEvent() throws PacException {
        if (list.isEmpty()) {
            throw new PacException("The event list is empty.");
        }
        displayList.printEventList(list, "event");
    }

    /**
     * Lists out events that are of seminar type only.
     *
     * @throws PacException If list is empty.
     */
    public void listSeminar() throws PacException {
        if (list.isEmpty()) {
            throw new PacException("The event list is empty.");
        }
        ArrayList<Event> seminarList = new ArrayList<>();
        for (Event item : list) {
            if (item instanceof Seminar) {
                seminarList.add(item);
            }
        }
        displayList.printEventList(seminarList, "seminar");
    }
}
