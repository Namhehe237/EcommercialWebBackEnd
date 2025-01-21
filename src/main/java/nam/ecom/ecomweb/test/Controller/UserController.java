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

@CrossOrigin("http://localhost:3000") 
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
            User tmp = user;
            System.out.println(tmp);
            System.out.println(tmp.toString());
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        try {
            boolean isAuthenticated = userService.authenticateUser(user);
            if (isAuthenticated) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(user);
            System.out.println(user.toString());
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

}
