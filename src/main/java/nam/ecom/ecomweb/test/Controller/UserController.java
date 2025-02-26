package nam.ecom.ecomweb.test.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nam.ecom.ecomweb.test.DTO.ChangePasswordRequest;
import nam.ecom.ecomweb.test.Entity.User;
import nam.ecom.ecomweb.test.Service.UserService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private JavaMailSender javaMailSender;

    @Autowired
    public UserController(UserService userService, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.javaMailSender = javaMailSender;
    }

    // @Autowired
    // public UserController(UserService userService) {
    // this.userService = userService;
    // }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
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
            boolean isAuthenticated = userService.authenticateUser(user);
            if (isAuthenticated) {
                User returnUser = userService.findUserByEmail(user.getEmail());

                if (returnUser == null) {
                    return ResponseEntity.status(404).body(Collections.singletonMap("message", "User not found"));
                }

                Map<String, Object> response = new HashMap<>();
                response.put("id", returnUser.getId());
                response.put("username", returnUser.getUsername());
                response.put("email", returnUser.getEmail());

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401)
                        .body(Collections.singletonMap("message", "Invalid username or password"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", "An error occurred: " + e.getMessage()));
        }
    }

    @PostMapping("send-mail")
    public ResponseEntity<String> sendEmailToResetPassword(@RequestBody String emailString) {
        try {
            User user = userService.findUserByEmail(emailString);
            if (user == null)
                return ResponseEntity.status(404).body("Email not found");

            System.out.println(user.toString());
            String newPass = userService.generateRandomString();
            System.out.println("1");

            user.setPassword(newPass);

            System.out.println(user.toString());
            // System.out.println("1.5");

            userService.updateNewPassword(user);

            System.out.println("2");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailString);
            message.setSubject("New password is coming");
            message.setText("The new password is : " + newPass);

            javaMailSender.send(message);

            return ResponseEntity.ok("A new password has been sent to your email");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            userService.changePassword(request.getEmail(), request.getOldPassword(), request.getNewPassword());
            return ResponseEntity.ok(Collections.singletonMap("message", "Password changed successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", e.getMessage()));
        }
    }
}
