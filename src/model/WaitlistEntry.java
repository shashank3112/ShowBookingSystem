package model;

public class WaitlistEntry {
    String userName, showName, time;
    int persons;

    public WaitlistEntry(String userName, String showName, String time, int persons) {
        this.userName = userName;
        this.showName = showName;
        this.time = time;
        this.persons = persons;
    }
}
