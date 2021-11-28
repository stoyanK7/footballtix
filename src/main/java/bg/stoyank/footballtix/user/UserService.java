package bg.stoyank.footballtix.user;

import bg.stoyank.footballtix.user.exception.InvalidCredentialsException;
import bg.stoyank.footballtix.user.exception.PasswordsDoNotMatchException;
import bg.stoyank.footballtix.user.exception.EmailAlreadyTakenException;
import bg.stoyank.footballtix.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import bg.stoyank.footballtix.registration.confirmationtoken.ConfirmationToken;
import bg.stoyank.footballtix.registration.confirmationtoken.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService confirmationTokenService;

    public String createUser(User user) throws EmailAlreadyTakenException {
        if (userExistsByEmail(user.getEmail()))
            throw new EmailAlreadyTakenException(user.getEmail());

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

        return token;
    }

    public User getUserById(int userId) throws UserNotFoundException {
        if (!userExistsById(userId))
            throw new UserNotFoundException(Integer.toString(userId));

        return userRepository.getById(userId);
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        if (!userExistsByEmail(email))
            throw new UserNotFoundException(email);

        return userRepository.getByEmail(email);
    }

    public String getFullNameByEmail(String email) throws UserNotFoundException {
        if (!userExistsByEmail(email))
            throw new UserNotFoundException(email);

        return userRepository.getFullNameByEmail(email).getFullName();
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

    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean userExistsById(int userId) {
        return userRepository.existsById(userId);
    }

    public void updateInfo(String oldEmail, String newEmail, String newFullName) {
        if (oldEmail.equals(newEmail) || userExistsByEmail(newEmail))
            throw new EmailAlreadyTakenException(newEmail);

        User user = getUserByEmail(oldEmail);
        user.setEmail(newEmail);
        user.setFullName(newFullName);
        userRepository.save(user);
    }

    public void updatePassword(String email, String currentPassword, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword))
            throw new PasswordsDoNotMatchException("Passwords do not match.");

        User user = getUserByEmail(email);
        if (!bCryptPasswordEncoder.matches(currentPassword, user.getPassword()))
            throw new InvalidCredentialsException("Provided current password is invalid.");

        String encodedNewPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }

    public void updatePassword(String email, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword))
            throw new PasswordsDoNotMatchException("Passwords do not match.");

        User user = getUserByEmail(email);
        String encodedNewPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String email) {
        if (!userExistsByEmail(email))
            throw new UserNotFoundException(email);

        confirmationTokenService.deleteTokenByEmail(email);
        userRepository.deleteByEmail(email);
    }
}
