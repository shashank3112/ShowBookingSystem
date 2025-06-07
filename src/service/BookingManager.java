package service;

import model.*;

import java.util.*;
public class BookingManager {

    public static Map<String, User> users = new HashMap<>();
    public static Map<String, Booking> bookingMap = new HashMap<>();

    public static void book(String userName, String showName, String time, int persons) {
        book(userName, showName, time, persons, false);
    }

    public static void book(String userName, String showName, String time, int persons, boolean isWaitlistFulfillment) {
        User user = null;
        if(!users.containsKey(userName)){ // when the user is new  insert it into USER hashmap
            users.put(userName , new User(userName));
        }

        user = users.get(userName); // get the details of user object of USERNAME
        if (!user.canBook(time)) { // true means this time slot isalready booked
            System.out.println("User has another booking in this time slot");
            return;
        }
        Show show = ShowManager.shows.get(showName);
        if (show == null || !show.slots.containsKey(time)) {
            System.out.println("Invalid show or time");
            return;
        }
        Slot slot = show.slots.get(time);
        if (slot.isAvailable(persons)) {
            Booking booking = new Booking(userName, showName, time, persons);
            slot.booked += persons;
            slot.bookings.add(booking);
            user.addBooking(booking);
            bookingMap.put(booking.id, booking);

            if (!isWaitlistFulfillment)
                System.out.println("Booked. Booking id: " + booking.id);
        } else {
            if (!isWaitlistFulfillment) {
                slot.addToWaitlist(new WaitlistEntry(userName, showName, time, persons));
                System.out.println("Added to waitlist");
            }
        }
    }

    public static void cancel(String bookingId) {
        Booking booking = bookingMap.get(bookingId);
        if (booking == null) {
            System.out.println("Invalid booking ID");
            return;
        }
        Show show = ShowManager.shows.get(booking.showName);
        Slot slot = show.slots.get(booking.time);
        User user = users.get(booking.userName);

        slot.booked -= booking.persons;
        slot.bookings.remove(booking);
        user.cancelBooking(booking);
        bookingMap.remove(bookingId);
        System.out.println("Booking Canceled");
        slot.processWaitlist();
    }

    static void viewBookings(String userName) {
        User user = users.get(userName);
        if (user == null) {
            System.out.println("No such user");
            return;
        }
        user.showBookings();
    }
}
