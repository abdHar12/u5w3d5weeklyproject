package harouane.u5w3d5weeklyproject.Controllers;

import harouane.u5w3d5weeklyproject.DTOs.EventsDTO;
import harouane.u5w3d5weeklyproject.Entities.Event;
import harouane.u5w3d5weeklyproject.Entities.User;
import harouane.u5w3d5weeklyproject.Services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventsController {
    @Autowired
    private EventsService eventsService;
    @GetMapping
    public Page<Event> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.eventsService.getElements(page, size, orderBy);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('EVENTS_ORGANIZER')")
    public Event saveNewElement( @RequestBody EventsDTO newEvent){
        return eventsService.saveElement(newEvent);
    }
    @GetMapping("/{id}")
    public Event findById(@PathVariable int id) {
        return this.eventsService.findById(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EVENTS_ORGANIZER')")
    public Event findByIdAndUpdate(@PathVariable int id, @RequestBody EventsDTO updatedUser) {
        return this.eventsService.findByIdAndUpdate(id, updatedUser);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EVENTS_ORGANIZER')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Status Code 204
    public void findByIdAndDelete(@PathVariable int id) {
        this.eventsService.findByIdAndDelete(id);
    }
}
