/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Boundary.CharityUI;
import Utility.MessageUI;
import ADT.*;

import Entity.Date;
import Entity.Event;
import Entity.Volunteer;
import java.util.Iterator;
import DAO.EntityInitializer;

/**
 *
 * @author USER
 */
public class EventMaintenance {

    private static CharityUI charityUI = new CharityUI();
    //private EntityInitializer entityInitialize = new EntityInitializer();

    //private QueueSetInterface<Event> events = entityInitialize.getEvents();
    //private QueueSetInterface<Volunteer> volunteers = entityInitialize.getVolunteers();

//    public void EventManagement(EntityInitializer entityInitialize) {
//        QueueSetInterface<Event> events = entityInitialize.getEvents();
//        QueueSetInterface<Volunteer> volunteers = entityInitialize.getVolunteers();
//       
//        int opt = 0;
//        do {
//            try {
//                opt = Integer.parseInt(charityUI.getEventMenu());
//
//                switch (opt) {
//                    case 1:
//                        Event newEvent = new Event("E003","new added Event",new Date(30,7,2024),"TARUMT");
//                        events.enqueue(newEvent);
//                        break;
//                    case 2:
//                        break;
//                    case 3:
//                        Iterator<Event> iterator = events.getIterator();
//                        do{
//                            Event event = iterator.next();
//                            if("TARUMT".equals(event.getLocation())){
//                                System.out.println(event);
//                            }
//                        }while(iterator.hasNext());
//                        break;
//                    case 4:
//                        Iterator<Event> iterator1 = events.getIterator();
//                        do {
//                            Event event = iterator1.next();
//                            if (event.getEventId().equals("E002")) {
//                                event.setName("Event Name Changed");
//                            }
//                        } while (iterator1.hasNext());
//                        break;
//                    case 5:
//                        System.out.println(events);
//                        break;
//                    case 6:
//                        break;
//                    case 7:
//                        Iterator<Volunteer> iterator2 = volunteers.getIterator();
//                        do{
//                            Volunteer volunteer = iterator2.next();
//                            
//                            if("012-6730810".equals(volunteer.getContact())){
//                                System.out.println(volunteer.getMyEvents());
//                            }
//                        }while(iterator2.hasNext());
//                        break;
//                    case 8:
//                        break;
//                    case 9:
//                        break;
//                    default:
//                        MessageUI.displayInvalidOptionMessage();
//                }
//            } catch (NumberFormatException ex) {
//                MessageUI.displayInvalidIntegerMessage();
//            }
//
//        } while (opt != 9);
//    }
}
