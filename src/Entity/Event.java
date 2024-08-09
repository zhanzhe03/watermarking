/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author USER
 */
import ADT.*;
import java.util.Objects;

public class Event {
    private String eventId;
    private String name;
    private Date eventDate;
    private String location;
    private QueueSetInterface<Volunteer> myVolunteers;

    public Event(String eventId, String name, Date eventDate, String location){
        this.eventId = eventId;
        this.name = name;
        this.eventDate = eventDate;
        this.location = location;
        this.myVolunteers = new CircularLinkedQueueSet<>();
    }
    
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public QueueSetInterface<Volunteer> getMyVolunteers() {
        return myVolunteers;
    }

    public void setMyVolunteers(QueueSetInterface<Volunteer> myVolunteers) {
        this.myVolunteers = myVolunteers;
    }
    
    public void assignVolunteers(Volunteer volunteer) {
        if (myVolunteers.enqueue(volunteer)) {
            volunteer.getMyEvents().enqueue(this);
        }
    }

//    public void removeVolunteer(Volunteer volunteer) {
//        if (myVolunteers.remove(volunteer)) {
//            volunteer.getMyEvents().remove(this);
//        }
//    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (!Objects.equals(this.eventId, other.eventId)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.eventDate, other.eventDate)) {
            return false;
        }
        return Objects.equals(this.myVolunteers, other.myVolunteers);
    }
    
    @Override
    public String toString() {
        return "\nEvent ID = " + eventId
                + "\nEvent Name = " + name
                + "\nEvent Date = " + eventDate
                + "\nLocation = " + location;
    }
}
