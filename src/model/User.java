package model;
import java.util.*;
public class User {
    String name;
    Map<String, Booking> bookings = new HashMap<>(); // bookingId -> Booking
    Set<String> occupiedSlots = new HashSet<>();

    public User(String name) {
        this.name = name;
    }

    public boolean canBook(String time) {
        return !occupiedSlots.contains(time);
    }

    public void addBooking(Booking b) {
        bookings.put(b.id, b);
        occupiedSlots.add(b.time);
    }

    public void cancelBooking(Booking b) {
        bookings.remove(b.id);
        occupiedSlots.remove(b.time);
    }

    public void showBookings() {
        for (Booking b : bookings.values()) {
            System.out.println("Booking ID: " + b.id + " Show: " + b.showName + " Time: " + b.time + " Persons: " + b.persons);
        }
    }
}
