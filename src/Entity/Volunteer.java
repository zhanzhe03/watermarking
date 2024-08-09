/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import ADT.*;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class Volunteer {

    private String volunteerId;
    private String name;
    private String email;
    private String contact;
    private QueueSetInterface<Event> myEvents;

    public Volunteer(String volunteerId, String name, String contact, String email) {
        this.volunteerId = volunteerId;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.myEvents = new CircularLinkedQueueSet<>();
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public QueueSetInterface<Event> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(QueueSetInterface<Event> myEvents) {
        this.myEvents = myEvents;
    }

    public void assignEvents(Event event) {
        if (myEvents.enqueue(event)) {
            event.getMyVolunteers().enqueue(this);
        }
    }
//
//    public void removeEvent(Event event) {
//        if (myEvents.remove(event)) {
//            event.getMyVolunteers().remove(this);
//        }
//    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Volunteer other = (Volunteer) obj;
        if (!Objects.equals(this.volunteerId, other.volunteerId)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        return Objects.equals(this.myEvents, other.myEvents);
    }

    @Override
    public String toString() {
        return "\nVolunteer ID = " + volunteerId
                + "\nName = " + name
                + "\nEmail = " + email
                + "\nContact = " + contact;
    }
}
