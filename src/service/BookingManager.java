package service;

import model.*;

import java.util.*;
public class BookingManager {

    public static Map<String, User> users = new HashMap<>();//username -> User obj
    public static Map<String, Booking> bookingMap = new HashMap<>();// booking id -> Booking obj

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
        Show show = ShowManager.shows.get(showName);  // fetching show object which will also tell us slots of that show
        if (show == null || !show.slots.containsKey(time)) {
            System.out.println("Invalid show or time");
            return;
        }
        Slot slot = show.slots.get(time);
        if (slot.isAvailable(persons)) {
            Booking booking = new Booking(userName, showName, time, persons); //create new booking
            slot.booked += persons; // persons number of seats is occupied so add it
            slot.bookings.add(booking); // adding the booking in that particular slot's bookings array list
            user.addBooking(booking); // adding the booking into users profile
            bookingMap.put(booking.id, booking); // keeping the booking for booking manager

            if (!isWaitlistFulfillment) { // i.e if the booking is fresh and was not in waiting list
                System.out.println("Booked. Booking id: " + booking.id);
            }
        } else { // if ths slot is not available for any new booking  just add to waitlist
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
        Show show = ShowManager.shows.get(booking.showName); // fetching show obj i.e show details from shows map
        Slot slot = show.slots.get(booking.time); //fetching slot obj against the booking time
        User user = users.get(booking.userName);  // fetching user obj against the username

        slot.booked -= booking.persons;  // decrease booked seats for that slot
        slot.bookings.remove(booking); // remove the booking obj from slot's bookings list
        user.cancelBooking(booking);  // removing the booking from user's profile
        bookingMap.remove(bookingId); // removing booking from booking Map present in Booking manager class
        System.out.println("Booking Canceled");
        slot.processWaitlist();  // since the booking has been canceled , we can try allowing seats to people in waiting list
    }

    public static void viewBookings(String userName) {
        User user = users.get(userName);
        System.out.println("booking of "+userName);
        if (user == null) {
            System.out.println("No such user");
            return;
        }
        user.showBookings();
    }
}
