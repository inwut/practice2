package org.ukma;

import lombok.*;

import java.io.Serializable;
import java.time.Duration;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Movie implements Serializable {
    private String title;
    private String director;
    private Duration duration;
}
