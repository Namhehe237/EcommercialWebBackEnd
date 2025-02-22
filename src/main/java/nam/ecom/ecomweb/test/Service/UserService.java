package nam.ecom.ecomweb.test.Service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import nam.ecom.ecomweb.test.Dao.UserRepository;
import nam.ecom.ecomweb.test.Entity.User;

@Service
@Transactional
public class UserService {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "@$!%*?&"; 
    private static final String ALL_CHARACTERS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Autowired
    private Validator validator;

    private UserRepository userRepository;
    private JavaMailSender mailSender;

    @Autowired
    public UserService(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        return userRepository.save(user);
    }

    public void updateNewPassword(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<User> violation : violations) {
                System.err.println("Validation error: " + violation.getMessage());
            }
            throw new RuntimeException("Password validation failed!");
        }

        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public boolean authenticateUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(8);

        // Ensure at least one uppercase letter
        sb.append(UPPERCASE.charAt(RANDOM.nextInt(UPPERCASE.length())));

        // Ensure at least one special character
        sb.append(SPECIAL_CHARACTERS.charAt(RANDOM.nextInt(SPECIAL_CHARACTERS.length())));

        // Fill the rest randomly
        for (int i = 2; i < 8; i++) {
            sb.append(ALL_CHARACTERS.charAt(RANDOM.nextInt(ALL_CHARACTERS.length())));
        }

        // Shuffle to randomize character positions
        return shuffleString(sb.toString());
    }

    private static String shuffleString(String input) {
        char[] array = input.toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int index = RANDOM.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return new String(array);
    }

}
