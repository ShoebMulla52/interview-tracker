package interview_tracker.config;




import interview_tracker.entity.Admin;
import interview_tracker.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (adminRepository.findByUsername("admin").isEmpty()) {

            Admin admin = Admin.builder()
                    .username("admin")
                    .email("admin@interviewtracker.com")
                    .password(passwordEncoder.encode("admin123"))
                    .build();

            adminRepository.save(admin);

            System.out.println("Default admin created");
        }
    }
}