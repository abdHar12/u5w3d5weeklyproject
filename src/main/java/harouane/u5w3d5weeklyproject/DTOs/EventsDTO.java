package harouane.u5w3d5weeklyproject.DTOs;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EventsDTO {
    String title;
    String description;
    String date;
    String location;
    int numberOfSeats;
}
