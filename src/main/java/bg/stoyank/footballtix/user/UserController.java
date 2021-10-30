package bg.stoyank.footballtix.user;

import bg.stoyank.footballtix.security.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping("/info")
    public Map<String, String> getInfo(String jwt) {
        jwt = jwt.substring(1, jwt.length() - 1);
        HashMap<String, String> map = new HashMap<>();
        String email = jwtUtil.extractUsername(jwt);
        String fullName = userService.getFullNameByEmail(email);
        map.put("email", email);
        map.put("fullName", fullName);
        return map;
    }

    @PutMapping("/info")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateInfo(@RequestBody Map<String, String> json) {
        userService.updateInfo(json.get("oldEmail"), json.get("newEmail"), json.get("newFullName"));
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @PutMapping("/password")
    public void updatePassword(@RequestBody Map<String, String> json) {
        String jwt = json.get("jwt").substring(1, json.get("jwt").length() - 1);
        HashMap<String, String> map = new HashMap<>();
        String email = jwtUtil.extractUsername(jwt);
        String currentPassword = json.get("currentPassword");
        String newPassword = json.get("newPassword");
        String confirmPassword = json.get("confirmPassword");
        userService.updatePassword(email, currentPassword, newPassword, confirmPassword);
    }
}
