package harouane.u5w3d5weeklyproject.Services;

import harouane.u5w3d5weeklyproject.DAOs.EventsDAO;
import harouane.u5w3d5weeklyproject.DAOs.UserDAO;
import harouane.u5w3d5weeklyproject.Entities.Event;
import harouane.u5w3d5weeklyproject.Entities.User;
import harouane.u5w3d5weeklyproject.Enums.Role;
import harouane.u5w3d5weeklyproject.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UsersService {
    @Autowired
    private UserDAO usersDAO;

@Autowired
private EventsService eventsService;
    public Page<User> getUsers(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return usersDAO.findAll(pageable);
    }
    public User findByEmail(String email) {
        return usersDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " non trovata"));
    }

    public User findById(int userId) {
        return usersDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByIdAndUpdate(int userId, User modifiedUser) {
        User found = this.findById(userId);
        found.setSurname(modifiedUser.getSurname());
        found.setName(modifiedUser.getName());
        found.setEmail(modifiedUser.getEmail());
        found.setPassword(modifiedUser.getPassword());
        return usersDAO.save(found);
    }
    public void findByIdAndDelete(int userId) {
        User found = this.findById(userId);
        usersDAO.delete(found);
    }

    public User modifyRole(int id, String role) {
        User found = this.findById(id);
        found.setRole(Role.valueOf(role.toUpperCase()));
        return usersDAO.save(found);
    }

    public void attendToEvent(User currentAuthenticatedUser, int eventId) {
        Set<Event> events=currentAuthenticatedUser.getEvents();
        events.add(eventsService.findById(eventId));
        currentAuthenticatedUser.setEvents(events);
        usersDAO.save(currentAuthenticatedUser);
    }
}
