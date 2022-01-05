package bg.stoyank.footballtix.user;

import bg.stoyank.footballtix.registration.confirmationtoken.ConfirmationTokenService;
import bg.stoyank.footballtix.user.exception.EmailAlreadyTakenException;
import bg.stoyank.footballtix.user.exception.InvalidCredentialsException;
import bg.stoyank.footballtix.user.exception.PasswordsDoNotMatchException;
import bg.stoyank.footballtix.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Disabled;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
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
    @Mock
    private UserStatsService userStatsService;

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
    @DisplayName("Ensure createUser() invokes createConfirmationToken().")
    void testCreateUserInvokesCreateConfirmationToken() {
        User user = mock(User.class, RETURNS_DEEP_STUBS);

        given(userRepository.existsByEmail(any()))
                .willReturn(false);

        componentUnderTest.createUser(user);

        verify(confirmationTokenService).createConfirmationToken(user);
    }

    @Test
    @DisplayName("Ensure getFullNameByEmail() invokes getFullNameByEmail().")
    void testGetFullNameByEmail() {
        String email = "test@mail.com";

        given(userRepository.existsByEmail(any()))
                .willReturn(true);

        given(userRepository.getFullNameByEmail(any()))
                .willReturn(mock(UserFullName.class, RETURNS_DEEP_STUBS));

        componentUnderTest.getFullNameByEmail(email);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userRepository).getFullNameByEmail(stringArgumentCaptor.capture());
        verify(userRepository.getFullNameByEmail(any())).getFullName();

        String capturedValue = stringArgumentCaptor.getValue();
        assertThat(capturedValue).isEqualTo(email);
    }

    @Test
    @DisplayName("Ensure deleteUser() invokes deleteByEmail().")
    void testDeleteUser() {
        String email = "asd";

        given(userRepository.existsByEmail(any()))
                .willReturn(true);

        componentUnderTest.deleteUser(email);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userRepository).deleteByEmail(stringArgumentCaptor.capture());

        String capturedValue = stringArgumentCaptor.getValue();
        assertThat(capturedValue).isEqualTo(email);
    }

    @Test
    @DisplayName("Ensure updatePassword() encodes new password, sets it and saves user.")
    void testUpdatePasswordEncodeNewPassword() {
        String email = "";
        String newPassword = "asd";
        String confirmPassword = "asd";
        User user = mock(User.class, RETURNS_DEEP_STUBS);

        given(userRepository.existsByEmail(email))
                .willReturn(true);

        given(userRepository.getByEmail(email))
                .willReturn(user);

        componentUnderTest.updatePassword(email, newPassword, confirmPassword);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(bCryptPasswordEncoder).encode(stringArgumentCaptor.capture());
        verify(user).setPassword(any());
        verify(userRepository).save(user);

        String capturedValue = stringArgumentCaptor.getValue();
        assertThat(capturedValue).isEqualTo(newPassword);
    }

    @Test
    @DisplayName("Ensure updateInfo() sets new values and saves user.")
    void testUpdateInfoSetNewValuesAndSaveUser() {
        String oldEmail = "oldEmail@mail.com";
        String newEmail = "newEmail@mail.com";
        String fullNewName = "New name";
        User user = mock(User.class, RETURNS_DEEP_STUBS);

        given(userRepository.existsByEmail(newEmail))
                .willReturn(false);

        given(userRepository.existsByEmail(oldEmail))
                .willReturn(true);

        given(userRepository.getByEmail(any()))
                .willReturn(user);

        componentUnderTest.updateInfo(oldEmail, newEmail, fullNewName);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userRepository).getByEmail(stringArgumentCaptor.capture());
        verify(user).setEmail(any());
        verify(user).setFullName(any());
        verify(userRepository).save(user);

        String capturedValue = stringArgumentCaptor.getValue();
        assertThat(capturedValue).isEqualTo(oldEmail);
    }

    @Test
    @DisplayName("Ensure updatePassword(x,x,x) throws PasswordsDoNotMatchException.")
    void testUpdatePasswordThrowsPasswordsDoNotMatchException() {
        String email = "";
        String newPassword = "as";
        String confirmPassword = "asd";

        assertThatThrownBy(() -> componentUnderTest.updatePassword(email, newPassword, confirmPassword))
                .isInstanceOf(PasswordsDoNotMatchException.class);
    }

    @Test
    @DisplayName("Ensure updatePassword(x,x,x,x) throws PasswordsDoNotMatchException.")
    void testUpdatePassword2ThrowsPasswordsDoNotMatchException() {
        String email = "";
        String currentPassword = "asdasdas";
        String newPassword = "as";
        String confirmPassword = "asd";

        assertThatThrownBy(() -> componentUnderTest
                .updatePassword(email,
                        currentPassword,
                        newPassword,
                        confirmPassword))
                .isInstanceOf(PasswordsDoNotMatchException.class);
    }

    @Test
    @DisplayName("Ensure updatePassword(x,x,x,x) throws InvalidCredentialsException.")
    void testUpdatePassword2ThrowsInvalidCredentialsException() {
        String email = "";
        String currentPassword = "addd";
        String newPassword = "asd";
        String confirmPassword = "asd";
        User user = mock(User.class, RETURNS_DEEP_STUBS);

        given(userRepository.existsByEmail(email))
                .willReturn(true);

        given(userRepository.getByEmail(email))
                .willReturn(user);

        assertThatThrownBy(() -> componentUnderTest
                .updatePassword(email,
                        currentPassword,
                        newPassword,
                        confirmPassword))
                .isInstanceOf(InvalidCredentialsException.class);
    }

    @Test
    @DisplayName("Ensure deleteUser() throws UserNotFoundException.")
    void testDeleteUserThrowsUserNotFoundException() {
        given(userRepository.existsByEmail(any()))
                .willReturn(false);

        assertThatThrownBy(() -> componentUnderTest
                .deleteUser(any()))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("Ensure updateInfo() throws EmailAlreadyTakenException when email is taken.")
    void testUpdateInfoThrowsEmailAlreadyTakenException() {
        String oldEmail = "oldEmail@mail.com";
        String newEmail = "newEmail@mail.com";
        String fullNewName = "New name";

        given(userRepository.existsByEmail(newEmail))
                .willReturn(true);

        assertThatThrownBy(() -> componentUnderTest
                .updateInfo(oldEmail,
                        newEmail,
                        fullNewName))
                .isInstanceOf(EmailAlreadyTakenException.class);
    }

    @Test
    @DisplayName("Ensure updateInfo() throws EmailAlreadyTakenException when old email is same as new one.")
    void testUpdateInfoThrowsEmailAlreadyTakenExceptionWhenOldEmailSameAsNewOne() {
        String oldEmail = "oldEmail@mail.com";
        String newEmail = "oldEmail@mail.com";
        String fullNewName = "New name";

        assertThatThrownBy(() -> componentUnderTest
                .updateInfo(oldEmail,
                        newEmail,
                        fullNewName))
                .isInstanceOf(EmailAlreadyTakenException.class);
    }

    @Test
    @DisplayName("Ensure updateInfo(x,x,x,x) encodes new password, sets it and saves user.")
    void testUpdatePassword2EncodeNewPassword() {
        String email = "";
        String currentPassword = "asdasdas";
        String newPassword = "asd";
        String confirmPassword = "asd";
        User user = mock(User.class, RETURNS_DEEP_STUBS);

        given(userRepository.existsByEmail(any()))
                .willReturn(true);
        given(userRepository.getByEmail(any()))
                .willReturn(user);
        given(bCryptPasswordEncoder.matches(any(), any()))
                .willReturn(true);

        componentUnderTest
                .updatePassword(email,
                        currentPassword,
                        newPassword,
                        confirmPassword);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(bCryptPasswordEncoder).encode(stringArgumentCaptor.capture());
        verify(user).setPassword(any());
        verify(userRepository).save(user);

        String capturedValue = stringArgumentCaptor.getValue();
        assertThat(capturedValue).isEqualTo(newPassword);
    }

    @Test
    @DisplayName("Ensure the getFullNameByEmail() throws UserNotFoundException.")
    void testGetFullNameByEmailThrowsUserNotFoundException() {
        given(userRepository.existsByEmail(any()))
                .willReturn(false);

        assertThatThrownBy(() -> componentUnderTest.getFullNameByEmail(any()))
                .isInstanceOf(UserNotFoundException.class);
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