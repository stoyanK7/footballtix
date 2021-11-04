package bg.stoyank.footballtix.registration;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
@Validated
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping
    public String register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return registrationService.register(registrationRequest);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}") String token) {
        return registrationService.confirmToken(token);
    }
}
