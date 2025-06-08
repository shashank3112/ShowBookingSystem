
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import service.ShowManager;
import service.BookingManager;

public class Main {
    public static void main(String[] args) {
        ShowManager.registerShow("STalks", "Intensity");
        ShowManager.onboardShowSlots("STalks","10:00-11:00", "4");

        ShowManager.registerShow("djTalks", "Action");
        ShowManager.onboardShowSlots("djTalks","11:00-12:00", "5");

        ShowManager.registerShow("Rishav captcha", "comedy");
        ShowManager.onboardShowSlots("Rishav captcha","13:00-14:00", "7");

        ShowManager.registerShow("TMKOC", "comedy");
        ShowManager.onboardShowSlots("TMKOC","17:00-18:00", "2");
        ShowManager.showAvailByGenre("comedy");

        BookingManager.book("shashank", "Rishav captcha", "13:00-14:00", 2);
        ShowManager.showAvailByGenre("comedy");

        BookingManager.book("shashank", "Rishav captcha", "13:00-14:00", 2);
        ShowManager.showAvailByGenre("comedy");

        BookingManager.viewBookings("shashank");
       // test1();

    }
    public static void test1(){
        ShowManager.onboardShowSlots("TMKOC", "9:00-10:00", "3", "12:00-13:00", "2", "15:00-16:00", "5");

        ShowManager.registerShow("TMKOC", "Comedy");
        ShowManager.registerShow("The Sonu Nigam Live Event", "Singing");
        ShowManager.onboardShowSlots("The Sonu Nigam Live Event", "10:00-11:00", "3", "13:00-14:00", "2", "17:00-18:00", "1");

        ShowManager.showAvailByGenre("Comedy");

        BookingManager.book("UserA", "TMKOC", "12:00-13:00", 2);

        ShowManager.showAvailByGenre("Comedy");

        BookingManager.cancel("1000");

        ShowManager.showAvailByGenre("Comedy");

        BookingManager.book("UserB", "TMKOC", "12:00-13:00", 1);

        ShowManager.registerShow("The Arijit Singh Live Event", "Singing");
        ShowManager.onboardShowSlots("The Arijit Singh Live Event", "11:00-12:00", "3", "14:00-15:00", "2");

        ShowManager.showAvailByGenre("Singing");
    }
}