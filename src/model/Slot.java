package model;

import service.BookingManager;

import java.util.*;
import java.util.LinkedList;

public class Slot {
    public String time; // e.g., "9:00-10:00"
    public int capacity;
    int MIN_CAPACITY=1;
    int MAX_CAPACITY=10;
    public int booked = 0;
    Queue<WaitlistEntry> waitlist = new LinkedList<>();
    public List<Booking> bookings = new ArrayList<>();//contains all the booking for this slot

    Slot(){// constructor for invalid slot timing and capacity

    }
    Slot(String time, int capacity) {//constructor for valid time and capacity
        this.time = time;
        this.capacity = capacity;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCapacity(int capacity) {
        if(isValidCapacity(capacity)) {
            this.capacity = capacity;
        }
    }
     boolean isValidTimeSlot(String time) {//11:00-12:00
        String[] parts = time.split("-");  // parts[0] = 11:00  and parts[1] = 12:00
        if(!(parts.length == 2 && parts[0] != null && parts[1] != null)) return false;


        String[] hourStartParts = parts[0].split(":"); // hourStartParts[0] = 11 and hourStartParts[1] = 00
        String[] hourEndParts = parts[1].split(":");   //// hourEndParts[0] = 12 and hourEndParts[1] = 00
        int startHour = Integer.parseInt(hourStartParts[0]); //11
        int startMins = Integer.parseInt(hourStartParts[1]); //00
        int endHour = Integer.parseInt(hourEndParts[0]);  //12
        int endMins = Integer.parseInt(hourEndParts[1]);  //00

        if(startMins!=0 || endMins!=0 || startHour<9 || endHour>21 || startHour + 1 !=endHour) return false;
        return true;
    }
    public boolean isValidCapacity(int capacity) {
        if(capacity < MIN_CAPACITY || capacity>MAX_CAPACITY) {
            System.out.println("Invalid Capacity : Allowed capacity MIN "+MIN_CAPACITY+" MAX "+MAX_CAPACITY);
            return false;}
        return true;
    }
    public boolean isAvailable(int persons) {// if seats are still available int this slot
        return (capacity - booked) >= persons;
    }

    public void addToWaitlist(WaitlistEntry entry) {
        waitlist.add(entry);
    }

    boolean hasWaitlist() {
        return !waitlist.isEmpty();
    }

    public void processWaitlist() {
        while (!waitlist.isEmpty()) { // for every waitlist entry in waitlist
            WaitlistEntry entry = waitlist.peek();
            if (isAvailable(entry.persons)) {
                BookingManager.book(entry.userName, entry.showName, time, entry.persons, true);
                waitlist.poll();
            } else {
                break;
            }
        }
    }
}
