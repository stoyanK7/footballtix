package bg.stoyank.footballtix.user;

import bg.stoyank.footballtix.user.exception.UserAlreadyExistsException;
import bg.stoyank.footballtix.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import bg.stoyank.footballtix.registration.token.ConfirmationToken;
import bg.stoyank.footballtix.registration.token.ConfirmationTokenService;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService confirmationTokenService;

    public String createUser(User user) throws UserAlreadyExistsException {
        if (userExistsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with such email already exists: " + user.getEmail());
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO: send email

        return token;
    }

    public User getUserById(int userId) throws UserNotFoundException {
        if (userExistsById(userId)) {
            return userRepository.getById(userId);
        }
        throw new UserNotFoundException("Could not find user with id: " + userId);
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        if (userExistsByEmail(email)) {
            return userRepository.getByEmail(email);
        }
        throw new UserNotFoundException("Could not find user with email: " + email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return getUserByEmail(email);
    }

    public void enableUser(String email) {
        User user = getUserByEmail(email);
        user.setEnabled(true);
    }

    private boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean userExistsById(int userId) {
        return userRepository.existsById(userId);
    }
}
