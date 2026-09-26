package interview_tracker.service.impl;


import interview_tracker.dto.UserLoginRequest;
import interview_tracker.entity.User;
import interview_tracker.repository.UserRepository;
import interview_tracker.security.JwtService;
import interview_tracker.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String login(UserLoginRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new BadCredentialsException(
                                "Invalid username or password"
                        ));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new BadCredentialsException(
                    "Invalid username or password"
            );
        }

        return jwtService.generateToken(user.getUsername());
    }
}