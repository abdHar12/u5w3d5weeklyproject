package harouane.u5w3d5weeklyproject.Services;

import harouane.u5w3d5weeklyproject.DAOs.UserDAO;
import harouane.u5w3d5weeklyproject.DTOs.NewUserDTO;
import harouane.u5w3d5weeklyproject.DTOs.UserLoginDTO;
import harouane.u5w3d5weeklyproject.Entities.User;
import harouane.u5w3d5weeklyproject.Exceptions.BadRequestException;
import harouane.u5w3d5weeklyproject.Exceptions.UnauthorizedException;
import harouane.u5w3d5weeklyproject.JWT.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private UserDAO usersDAO;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {
        User user = usersService.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(), user.getPassword())) {

            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali sbagliate!");
        }
    }

    public User saveUser(NewUserDTO payload) {
        usersDAO.findByEmail(payload.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });
        User newUser = new User(payload.name(), payload.surname(),
                payload.email(), bcrypt.encode(payload.password()),
                "https://ui-avatars.com/api/?name" + payload.name() + "+" + payload.surname());

        User savedUser = usersDAO.save(newUser);
        return savedUser;
    }
}
