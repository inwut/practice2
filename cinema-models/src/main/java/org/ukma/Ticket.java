package org.ukma;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Ticket implements Serializable {
    private Movie movie;
    private int seat;
    private double price;
    private LocalDateTime startTime;
}
