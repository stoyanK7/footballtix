package bg.stoyank.footballtix.user;

import bg.stoyank.footballtix.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/info")
    public Map<String, String> getInfo(String jwt) {
        HashMap<String, String> map = new HashMap<>();
        String email = jwtService.extractUsername(jwt);
        String fullName = userService.getFullNameByEmail(email);
        map.put("email", email);
        map.put("fullName", fullName);
        return map;
    }

    @PutMapping("/info")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateInfo(@RequestBody Map<String, String> json) {
        userService.updateInfo(json.get("oldEmail"), json.get("newEmail"), json.get("newFullName"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/password")
    public void updatePassword(@RequestBody Map<String, String> json) {
        String email = jwtService.extractUsername(json.get("jwt"));
        String currentPassword = json.get("currentPassword");
        String newPassword = json.get("newPassword");
        String confirmPassword = json.get("confirmPassword");
        userService.updatePassword(email, currentPassword, newPassword, confirmPassword);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestParam("jwt") String jwt) {
        String email = jwtService.extractUsername(jwt);
        userService.deleteUser(email);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
