package bg.stoyank.footballtix.user;

import bg.stoyank.footballtix.user.exception.UserAlreadyExistsException;
import bg.stoyank.footballtix.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import bg.stoyank.footballtix.registration.token.ConfirmationToken;
import bg.stoyank.footballtix.registration.token.ConfirmationTokenService;
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
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public String createUser(User user) {
        if (userExistsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException();
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

    public User getUserById(int userId) {
        if (userExistsById(userId)) {
            return userRepository.getById(userId);
        }
        throw new UserNotFoundException();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!userExistsByEmail(email)) {
            throw new UserNotFoundException();
        }
        return userRepository.getByEmail(email);
    }

    public void enableUser(String email) {
        if (!userExistsByEmail(email)) {
            throw new UserNotFoundException();
        }
        userRepository.enableUser(email);
    }

    private boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean userExistsById(int userId) {
        return userRepository.existsById(userId);
    }
}
