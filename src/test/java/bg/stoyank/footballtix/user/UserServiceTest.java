package bg.stoyank.footballtix.user;

import bg.stoyank.footballtix.registration.confirmationtoken.ConfirmationTokenService;
import bg.stoyank.footballtix.user.exception.EmailAlreadyTakenException;
import bg.stoyank.footballtix.user.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService componentUnderTest;

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @Test
    @DisplayName("Ensure the user is being passed on to save() from createUser().")
    void testCreateUser() {
        User user = new User("Full name", "email@gmail.com", "password", UserRole.USER);

        componentUnderTest.createUser(user);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    @DisplayName("Ensure UserAlreadyExistsException is thrown in createUser().")
    void testCreateUserUserAlreadyExistsException() {
        given(userRepository.existsByEmail(any()))
                .willReturn(true);

        assertThatThrownBy(() -> componentUnderTest.createUser(mock(User.class)))
                .isInstanceOf(EmailAlreadyTakenException.class);
    }

    @Test
    @DisplayName("Ensure the user id is being passed on to getById().")
    void testGetUserById() {
        int userId = anyInt();
        given(userRepository.existsById(userId))
                .willReturn(true);

        componentUnderTest.getUserById(userId);

        ArgumentCaptor<Integer> userIdArgumentCaptor = ArgumentCaptor.forClass(int.class);

        verify(userRepository).getById(userIdArgumentCaptor.capture());

        int capturedUserId = userIdArgumentCaptor.getValue();
        assertThat(capturedUserId).isEqualTo(userId);
    }

    @Test
    @DisplayName("Ensure UserNotFoundException is thrown in getUserById().")
    void testGetUserByIdUserNotFoundException() {
        given(userRepository.existsById(anyInt()))
                .willReturn(false);

        assertThatThrownBy(() -> componentUnderTest.getUserById(anyInt()))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("Ensure the method findAll() is invoked.")
    void testGetAllUsers() {
        componentUnderTest.getAllUsers();

        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("Ensure the email is being passed on to getUserByEmail().")
    void testLoadUserByUsername() {
        String userEmail = "test@gmail.com";
        UserService spyComponent = Mockito.spy(componentUnderTest);

        given(userRepository.existsByEmail(userEmail))
                .willReturn(true);

        spyComponent.loadUserByUsername(userEmail);

        ArgumentCaptor<String> userEmailArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(spyComponent).getUserByEmail(userEmailArgumentCaptor.capture());

        String capturedUserEmail = userEmailArgumentCaptor.getValue();
        assertThat(capturedUserEmail).isEqualTo(userEmail);
    }

    @Test
    @DisplayName("Ensure enableUser() works.")
    void testEnableUser() {
        User testUser = mock(User.class);

        given(userRepository.existsByEmail(testUser.getEmail()))
                .willReturn(true);
        given(userRepository.getByEmail(testUser.getEmail()))
                .willReturn(testUser);

        componentUnderTest.enableUser(testUser.getEmail());

        verify(testUser).setEnabled(true);
    }

    @Test
    @DisplayName("Ensure email is being passed on to getByEmail().")
    void testGetUserByEmail() {
        String userEmail = anyString();
        given(userRepository.existsByEmail(userEmail))
                .willReturn(true);

        componentUnderTest.getUserByEmail(userEmail);

        ArgumentCaptor<String> userEmailArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userRepository).getByEmail(userEmailArgumentCaptor.capture());

        String capturedUserEmail = userEmailArgumentCaptor.getValue();
        assertThat(capturedUserEmail).isEqualTo(userEmail);
    }

    @Test
    @DisplayName("Ensure UserNotFoundException is thrown in getUserByEmail().")
    void testGetUserByEmailUserNotFoundException() {
        given(userRepository.existsByEmail(anyString()))
                .willReturn(false);

        assertThatThrownBy(() -> componentUnderTest.getUserByEmail(anyString()))
                .isInstanceOf(UserNotFoundException.class);
    }
}