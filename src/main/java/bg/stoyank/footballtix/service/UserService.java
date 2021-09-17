package bg.stoyank.footballtix.service;

import bg.stoyank.footballtix.model.User;
import bg.stoyank.footballtix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
