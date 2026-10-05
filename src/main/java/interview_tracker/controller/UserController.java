package interview_tracker.controller;


import interview_tracker.dto.UserChangePasswordRequest;
import interview_tracker.dto.UserSignupRequest;
import interview_tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @Valid @RequestBody UserSignupRequest request) {

        userService.signup(request);

        return ResponseEntity.ok(
                "User registered successfully"
        );
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @Valid @RequestBody UserChangePasswordRequest request,
            Authentication authentication) {

        String username = authentication.getName();

        userService.changePassword(
                username,
                request
        );

        return ResponseEntity.ok(
                "Password changed successfully"
        );
    }
}