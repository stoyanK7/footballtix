package bg.stoyank.footballtix.service;

import bg.stoyank.footballtix.model.User;
import bg.stoyank.footballtix.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(User user) {
        this.userRepository.save(user);
    }

    public User getUserById(int userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }

        return userOptional.get();
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
