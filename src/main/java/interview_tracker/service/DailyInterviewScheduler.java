package interview_tracker.service;

import interview_tracker.entity.Interview;
import interview_tracker.entity.InterviewStatus;
import interview_tracker.repository.InterviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyInterviewScheduler {


    private final InterviewRepository interviewRepository;

    private final EmailService emailService;

    // for testing
    //@Scheduled(cron = "0 */1 * * * *")
    @Scheduled(cron = "0 0 8 * * * ", zone = "Asia/Kolkata")

    public void sendDailyInterviewSummary() {


        LocalDate today = LocalDate.now();


        List<Interview> interviews =
                interviewRepository
                        .findByStatusAndInterviewDate(
                                InterviewStatus.SCHEDULED,
                                today
                        );


        StringBuilder body =
                new StringBuilder();


        body.append("Today's Scheduled Interviews\n\n");


        if(interviews.isEmpty()) {

            body.append(
                    "No interviews scheduled today."
            );

        } else {


            int count = 1;


            for(Interview interview : interviews) {


                body.append(count++)
                        .append(". Candidate: ")
                        .append(interview.getCandidateName())
                        .append("\n")

                        .append("Company: ")
                        .append(interview.getCompanyName())
                        .append("\n")

                        .append("Role: ")
                        .append(interview.getRole())
                        .append("\n")

                        .append("Round: ")
                        .append(interview.getRound())
                        .append("\n")

                        .append("Time: ")
                        .append(interview.getInterviewTime())
                        .append("\n")

                        .append("Mode: ")
                        .append(interview.getMode())
                        .append("\n\n");
            }
        }


        emailService.sendEmail(
                "admin-email@gmail.com",
                "Today's Scheduled Interviews",
                body.toString()
        );

    }
}