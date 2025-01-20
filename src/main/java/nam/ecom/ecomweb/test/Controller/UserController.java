package nam.ecom.ecomweb.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import nam.ecom.ecomweb.test.Entity.User;
import nam.ecom.ecomweb.test.Service.UserService;

@CrossOrigin("http://localhost:3000")  // Allowing React frontend to make requests
@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User foundUser = userService.findUserByEmail(user.getEmail());
            if (foundUser != null) {
                // Checking password match
                String checkPassword = foundUser.getPassword();
                if (checkPassword.equals(user.getPassword())) {
                    // If credentials are correct, send "OK"
                    return ResponseEntity.ok("OK");
                } else {
                    // If password doesn't match, return error
                    return ResponseEntity.status(404).body("Incorrect password");
                }
            } else {
                // If user is not found, return error
                return ResponseEntity.status(404).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while checking login");
        }
    }
}
