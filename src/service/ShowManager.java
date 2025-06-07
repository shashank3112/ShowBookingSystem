package service;
import model.Show;
import model.Slot;

import java.util.*;


public class ShowManager {
    static Map<String, Show> shows = new HashMap<>();
    static Map<String, List<Show>> genreMap = new HashMap<>();
    static String trendingShow = null;
    static Map<String, Integer> bookingCount = new HashMap<>();

    public static void registerShow(String name, String genre) {
        if (shows.containsKey(name)) {
            System.out.println("Show already registered!");
            return;
        }
        Show show = new Show(name, genre);
        shows.put(name, show);
        if(genreMap.containsKey(genre)){
            List<Show> showstmp = genreMap.get(genre);
            showstmp.add(show);
            genreMap.put(genre , showstmp);
        }
        else{
            List<Show> newlist = new ArrayList<>();
            newlist.add(show);
            genreMap.put(genre, newlist);
        }
        System.out.println(name + " show is registered !!");
    }

    public static void onboardShowSlots(String name, String... args) {
        Show show = shows.get(name);
        if (show == null) {
            System.out.println("Show not found!");
            return;
        }
        for (int i = 0; i < args.length; i += 2) {
            String time = args[i];
            int capacity = Integer.parseInt(args[i + 1]);

            if (!show.addSlot(time, capacity)) {
                System.out.println("Invalid timing or capacity " + time +" "+  capacity);
                return;
            }
        }
        System.out.println("Done!");
    }

    public static void showAvailByGenre(String genre) {
        List<Show> list = genreMap.getOrDefault(genre, new ArrayList<>());
        for (Show show : list) {
            for (Slot s : show.slots.values()) {
                if (s.capacity > s.booked) {
                    System.out.println(show.name + ": (" + s.time + ") " + (s.capacity - s.booked));
                }
            }
        }
    }


}
