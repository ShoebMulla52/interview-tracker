package interview_tracker.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewRequest {

    @NotBlank(message = "Candidate name is required")
    private String candidateName;

    @NotBlank(message = "Company name is required")
    private String companyName;

    private String interviewSupporter;

    private LocalDate interviewDate;

    private LocalTime interviewTime;

    private String round;

    private String role;

    private String mode;

    private String status;

    private String feedback;

    private String remarks;
}