package model;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
public class Booking {
    static AtomicInteger idGenerator = new AtomicInteger(1000);
    public String id;
    public String userName;
    public String showName;
    public String time;
    public int persons;

    public Booking(String userName, String showName, String time, int persons) {
        this.id = String.valueOf(idGenerator.getAndIncrement());
        this.userName = userName;
        this.showName = showName;
        this.time = time;
        this.persons = persons;
    }
}
