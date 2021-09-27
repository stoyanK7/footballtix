package bg.stoyank.footballtix.user;

import bg.stoyank.footballtix.registration.token.ConfirmationTokenService;
import bg.stoyank.footballtix.user.exception.UserAlreadyExistsException;
import bg.stoyank.footballtix.user.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        // given
        User user = new User("Full name", "email@gmail.com", "password", UserRole.USER);
        // when
        componentUnderTest.createUser(user);
        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    @DisplayName("Ensure UserAlreadyExistsException is thrown in createUser().")
    void testCreateUserUserAlreadyExistsException() {
        // given
        given(userRepository.existsByEmail(any()))
                .willReturn(true);
        // then
        assertThatThrownBy(() -> componentUnderTest.createUser(mock(User.class)))
                .isInstanceOf(UserAlreadyExistsException.class);
    }

    @Test
    @DisplayName("Ensure the user id is being passed on to getById().")
    void testGetUserById() {
        // given
        int userId = 1;
        given(userRepository.existsById(userId))
                .willReturn(true);
        // when
        componentUnderTest.getUserById(userId);
        // then
        ArgumentCaptor<Integer> userIdArgumentCaptor = ArgumentCaptor.forClass(int.class);

        verify(userRepository).getById(userIdArgumentCaptor.capture());

        int capturedUserId = userIdArgumentCaptor.getValue();
        assertThat(capturedUserId).isEqualTo(userId);
    }

    @Test
    @DisplayName("Ensure UserNotFoundException is thrown in getUserById().")
    void testGetUserByIdUserNotFoundException() {
        // given
        given(userRepository.existsById(anyInt()))
                .willReturn(false);
        // then
        assertThatThrownBy(() -> componentUnderTest.getUserById(anyInt()))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("Ensure the method findAll() is invoked.")
    void testGetAllUsers() {
        // when
        componentUnderTest.getAllUsers();
        // then
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("Ensure the email is being passed on to getByEmail().")
    void testLoadUserByUsername() {
        // given
        String userEmail = "test@gmail.com";
        given(userRepository.existsByEmail(userEmail))
                .willReturn(true);
        // when
        componentUnderTest.loadUserByUsername(userEmail);
        // then
        ArgumentCaptor<String> userEmailArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userRepository).getByEmail(userEmailArgumentCaptor.capture());

        String capturedUserEmail = userEmailArgumentCaptor.getValue();
        assertThat(capturedUserEmail).isEqualTo(userEmail);
    }

    @Test
    @DisplayName("Ensure UserNotFoundException is thrown in loadByUsername().")
    void testLoadByUsernameUserNotFoundException() {
        // given
        given(userRepository.existsByEmail(anyString()))
                .willReturn(false);
        // then
        assertThatThrownBy(() -> componentUnderTest.loadUserByUsername(anyString()))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("Ensure enableUser() works.")
    void testEnableUser() {
        // given
        User testUser = mock(User.class);
        given(userRepository.existsByEmail(testUser.getEmail()))
                .willReturn(true);
        given(userRepository.getByEmail(testUser.getEmail()))
                .willReturn(testUser);
        // when
        componentUnderTest.enableUser(testUser.getEmail());
        // then
       verify(testUser).setEnabled(true);
    }

    @Test
    @DisplayName("Ensure UserNotFoundException is thrown in enableUser().")
    void testEnableUserUserNotFoundException() {
        // given
        given(userRepository.existsByEmail(anyString()))
                .willReturn(false);
        // then
        assertThatThrownBy(() -> componentUnderTest.enableUser(anyString()))
                .isInstanceOf(UserNotFoundException.class);
    }
}