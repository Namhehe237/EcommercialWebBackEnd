package nam.ecom.ecomweb.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    //     this.userService = userService;
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
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("send-mail")
    public ResponseEntity<String>  sendEmailToResetPassword(@RequestBody String emailString){
        try{
            User user = userService.findUserByEmail(emailString);
            if (user.equals(null)) return ResponseEntity.status(404).body("Email not found");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailString);
            message.setSubject("New password is coming");
            message.setText("The new password is : Nam123");

            javaMailSender.send(message);
            
            return ResponseEntity.ok("A new password has been sent to your email");
        }catch(Exception e){
           e.printStackTrace();
           return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

 
}
