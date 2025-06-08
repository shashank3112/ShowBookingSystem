package model;

import java.util.concurrent.atomic.AtomicInteger;
public class Booking {
    static AtomicInteger idGenerator = new AtomicInteger(1000);
    public String id;
    public String userName;// name of person/user against whom booking is made
    public String showName; //name of show
    public String time; //timing of show
    public int persons; //number of persons for which booking has been made

    public Booking(String userName, String showName, String time, int persons) {
        this.id = String.valueOf(idGenerator.getAndIncrement());
        this.userName = userName;
        this.showName = showName;
        this.time = time;
        this.persons = persons;
    }
}
