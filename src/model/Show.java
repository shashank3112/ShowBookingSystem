package model;

import java.util.*;

public class Show {
    public String name;
    String genre;

    public Map<String, Slot> slots = new HashMap<>();

    public Show(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    public boolean addSlot(String time, int capacity) {
        if (slots.containsKey(time)) return false;
        Slot slot = new Slot();
        if(slot.isValidTimeSlot(time) && slot.isValidCapacity(capacity)){
            slot.setTime(time);
            slot.setCapacity(capacity);
        }
        else {
            System.out.println("Invalid slot data provided ");
        }
        slots.put(time, slot);
        return true;
    }




}
