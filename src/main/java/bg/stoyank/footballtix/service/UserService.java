package bg.stoyank.footballtix.service;

import bg.stoyank.footballtix.model.User;
import bg.stoyank.footballtix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        this.userRepository.save(user);
    }

    public User getUserById(int userId) {
        Optional<User> userOptional =  this.userRepository.findById(userId);

        if(userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }

        return userOptional.get();
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

}
