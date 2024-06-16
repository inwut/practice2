package org.ukma;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Statistics {
    private static final String TICKETS_FILE = "C:\\Users\\Lenovo\\Desktop\\automation\\cinema\\cinema-statistics\\src\\main\\resources\\tickets.txt";

    public static Movie getMostPopularMovie() {
        List<Ticket> tickets = getTickets();
        if (tickets == null || tickets.isEmpty()) {
            return null;
        }
        Map<Movie, Integer> movieCountMap = new HashMap<>();
        for (Ticket ticket : tickets) {
            Movie movie = ticket.getMovie();
            movieCountMap.put(movie, movieCountMap.getOrDefault(movie, 0) + 1);
        }
        Optional<Map.Entry<Movie, Integer>> mostPopularEntry = movieCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        return mostPopularEntry.map(Map.Entry::getKey).orElse(null);
    }

    private static List<Ticket> getTickets() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TICKETS_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                return (List<Ticket>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
