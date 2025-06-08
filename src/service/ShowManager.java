package service;
import model.Show;
import model.Slot;

import java.util.*;


public class ShowManager {
    static Map<String, Show> shows = new HashMap<>(); //maintaining show details(show obj) against show name
    static Map<String, List<Show>> genreMap = new HashMap<>();// ex : comedy -> [TMKOC obj , FUNNY obj] to enable search using genre

    public static void registerShow(String name, String genre) {
        if (shows.containsKey(name)) {
            System.out.println("Show already registered!");
            return;
        }
        Show show = new Show(name, genre); // creating new Show obj of the new show
        shows.put(name, show); // adding this show to shows hashmap i.e showname to show obj
        if(genreMap.containsKey(genre)){ // if genre map already contains the genre key ..we simply add this show to existing map[key]
            List<Show> showstmp = genreMap.get(genre); //fetching the list of shows corresponding to that genre
            showstmp.add(show); //adding this show to that list
            genreMap.put(genre , showstmp); // adding the new list to genremap
        }
        else{// if the genre is new and not present in genremap
            List<Show> newlist = new ArrayList<>();  // creating a new list and adding to genre map
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
        for (int i = 0; i < args.length; i += 2) { // fetching time(args i) and capacity(args i+1) for each slot , hence doing i+=2
            String time = args[i];  //time
            int capacity = Integer.parseInt(args[i + 1]); //capacity

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
