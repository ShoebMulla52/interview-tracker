package interview_tracker.controller;



import interview_tracker.dto.AdminLoginRequest;
import interview_tracker.dto.ChangePasswordRequest;
import interview_tracker.dto.LoginResponse;
import interview_tracker.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody AdminLoginRequest request) {

        return ResponseEntity.ok(
                adminService.login(request)
        );
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Authentication authentication) {

        String username = authentication.getName();

        adminService.changePassword(
                username,
                request
        );

        return ResponseEntity.ok(
                "Password changed successfully"
        );
    }
}
