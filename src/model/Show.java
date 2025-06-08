package model;

import java.util.*;

public class Show {
    public String name;
    String genre;

    public Map<String, Slot> slots = new HashMap<>(); // maps time-> Slot obj

    public Show(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    public boolean addSlot(String time, int capacity) {//adding slot to the particular show
        if (slots.containsKey(time)) return false; // if the slot is already present in slots hashmap
        Slot slot = new Slot();
        if(slot.isValidTimeSlot(time) && slot.isValidCapacity(capacity)){ // check if slot is valid or not
            slot.setTime(time);
            slot.setCapacity(capacity);
        }
        else {
            System.out.println("Invalid slot data provided ");
        }
        slots.put(time, slot); // simply add slot to slots hashmap . time->Slot obj
        return true;
    }




}
