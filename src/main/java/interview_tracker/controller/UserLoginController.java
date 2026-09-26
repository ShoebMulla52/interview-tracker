package interview_tracker.controller;


import interview_tracker.dto.UserLoginRequest;
import interview_tracker.service.UserLoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginService userLoginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody UserLoginRequest request) {

        String token = userLoginService.login(request);

        return ResponseEntity.ok(token);
    }
}