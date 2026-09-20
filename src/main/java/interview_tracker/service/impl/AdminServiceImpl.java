package interview_tracker.service.impl;


import interview_tracker.dto.AdminLoginRequest;
import interview_tracker.dto.LoginResponse;
import interview_tracker.entity.Admin;
import interview_tracker.repository.AdminRepository;
import interview_tracker.security.JwtService;
import interview_tracker.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(AdminLoginRequest request) {

        Admin admin = adminRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                admin.getPassword())) {

            throw new RuntimeException(
                    "Invalid username or password");
        }

        String token =
                jwtService.generateToken(admin.getUsername());

        return LoginResponse.builder()
                .token(token)
                .username(admin.getUsername())
                .message("Login successful")
                .build();
    }
}
