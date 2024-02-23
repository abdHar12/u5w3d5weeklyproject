package harouane.u5w3d5weeklyproject.Services;

import harouane.u5w3d5weeklyproject.DAOs.UserDAO;
import harouane.u5w3d5weeklyproject.Entities.User;
import harouane.u5w3d5weeklyproject.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UserDAO usersDAO;


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

}
