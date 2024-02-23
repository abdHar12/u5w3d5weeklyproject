package harouane.u5w3d5weeklyproject.Controllers;

import harouane.u5w3d5weeklyproject.Entities.User;
import harouane.u5w3d5weeklyproject.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.usersService.getUsers(page, size, orderBy);
    }

    @PatchMapping("/modify-role/{role}/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public User modifyRole(@PathVariable int id, @PathVariable String role){
        return this.usersService.modifyRole(id, role);
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public User getMeAndUpdate(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody User updatedUser) {
        return this.usersService.findByIdAndUpdate(currentAuthenticatedUser.getId(), updatedUser);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getMeAndDelete(@AuthenticationPrincipal User currentAuthenticatedUser) {
        this.usersService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }

    @PostMapping("/me/subscribe-event/{eventId}")
    public void attendToEvent(@AuthenticationPrincipal User currentAuthenticatedUser, @PathVariable int eventId){
        this.usersService.attendToEvent(currentAuthenticatedUser, eventId);
    }


}
