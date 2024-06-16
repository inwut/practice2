import org.junit.jupiter.api.Test;
import org.ukma.CinemaService;
import org.ukma.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CinemaServiceTest {
    @Test
    public void testAddMovie() throws IOException {
        CinemaService cinemaService = new CinemaService();
        Path tempMoviesFile = Files.createTempFile("movies", ".txt");
        CinemaService.setMoviesFile(tempMoviesFile.toString());
        String title = "Interstellar";
        String director = "Christopher Nolan";
        Duration duration = Duration.ofMinutes(169);
        cinemaService.addMovie(title, director, duration);
        List<Movie> movies = cinemaService.getMovies();
        assertEquals(1, movies.size());
        assertEquals(title, movies.getFirst().getTitle());
        assertEquals(director, movies.getFirst().getDirector());
        assertEquals(duration, movies.getFirst().getDuration());
        Files.deleteIfExists(tempMoviesFile);
    }
}
