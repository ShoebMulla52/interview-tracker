package interview_tracker.dto;



import interview_tracker.entity.InterviewMode;
import interview_tracker.entity.InterviewStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewResponse {

    private Long id;

    private String candidateName;

    private String companyName;

    private String interviewSupporter;

    private LocalDate interviewDate;

    private LocalTime interviewTime;

    private String round;

    private String role;

    //private String mode;

   //private String status;

    private InterviewMode mode;
    private InterviewStatus status;

    private String feedback;

    private String remarks;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}