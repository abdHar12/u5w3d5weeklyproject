package harouane.u5w3d5weeklyproject.Services;

import harouane.u5w3d5weeklyproject.DAOs.EventsDAO;
import harouane.u5w3d5weeklyproject.DTOs.EventsDTO;
import harouane.u5w3d5weeklyproject.Entities.Event;
import harouane.u5w3d5weeklyproject.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
public class EventsService {

    @Autowired
    EventsDAO eventsDAO;
    public Page<Event> getElements(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return eventsDAO.findAll(pageable);
    }
    public Event saveElement(EventsDTO newEvent) {
        Event event= new Event(newEvent.getTitle(), newEvent.getDescription(), LocalDate.parse(newEvent.getDate()), newEvent.getLocation(), newEvent.getNumberOfSeats());
        return eventsDAO.save(event);
    }
    public Event findById(int id) {
        return eventsDAO.findById(id).orElseThrow(()->new NotFoundException("L' "+id+"non è stato trovato"));
    }

    public Event findByIdAndUpdate(int id, EventsDTO updatedEvent) {
            Event found = this.findById(id);
            found.setTitle(updatedEvent.getTitle());
            found.setDescription(updatedEvent.getDescription());
            found.setDate(LocalDate.parse(updatedEvent.getDate()));
            found.setNumberOfSeats(updatedEvent.getNumberOfSeats());
            return eventsDAO.save(found);
        }

    public void findByIdAndDelete(int id) {
        eventsDAO.delete(this.findById(id));
    }
}

