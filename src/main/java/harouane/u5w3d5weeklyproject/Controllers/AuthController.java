package harouane.u5w3d5weeklyproject.Controllers;

import harouane.u5w3d5weeklyproject.DTOs.LoginResponseDTO;
import harouane.u5w3d5weeklyproject.DTOs.NewUserDTO;
import harouane.u5w3d5weeklyproject.DTOs.UserLoginDTO;
import harouane.u5w3d5weeklyproject.Entities.User;
import harouane.u5w3d5weeklyproject.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody UserLoginDTO payload) {
        return new LoginResponseDTO(authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // Status Code 201
    public User saveUser(@RequestBody NewUserDTO newUser) {
        return this.authService.saveUser(newUser);
    }
}
