package org.ukma;


import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CinemaService {
    private static String MOVIES_FILE = "C:\\Users\\Lenovo\\Desktop\\automation\\cinema\\cinema-service\\src\\main\\resources\\movies.txt";
    private static String TICKETS_FILE = "C:\\Users\\Lenovo\\Desktop\\automation\\cinema\\cinema-service\\src\\main\\resources\\tickets.txt";

    private List<Movie> movies;
    private List<Ticket> tickets;

    public CinemaService() {
        movies = new ArrayList<>();
        tickets = new ArrayList<>();
        loadMoviesFromFile();
        loadTicketsFromFile();
    }

    public void addMovie(String title, String director, Duration duration) {
        Movie newMovie = new Movie(title, director, duration);
        movies.add(newMovie);
        saveMovieToFile(newMovie);
    }

    public void addTicket(Movie movie, int seat, double price, LocalDateTime startTime) {
        Ticket newTicket = new Ticket(movie, seat, price, startTime);
        tickets.add(newTicket);
        saveTicketToFile(newTicket);
    }

    public void loadMoviesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MOVIES_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                movies = (List<Movie>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            movies = new ArrayList<>();
        }
    }

    public void loadTicketsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TICKETS_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                tickets = (List<Ticket>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            tickets = new ArrayList<>();
        }
    }

    private void saveMovieToFile(Movie movie) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MOVIES_FILE))) {
            oos.writeObject(movies);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveTicketToFile(Ticket ticket) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TICKETS_FILE))) {
            oos.writeObject(tickets);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public static void setMoviesFile(String moviesFile) {
        MOVIES_FILE = moviesFile;
    }

    public static void setTicketsFile(String ticketsFile) {
        TICKETS_FILE = ticketsFile;
    }
}
