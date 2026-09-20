package interview_tracker.service.impl;


import interview_tracker.dto.DashboardStatsResponse;
import interview_tracker.entity.InterviewStatus;
import interview_tracker.repository.InterviewRepository;
import interview_tracker.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final InterviewRepository interviewRepository;

    @Override
    public DashboardStatsResponse getDashboardStats() {

        long totalInterviews =
                interviewRepository.count();

        long scheduled =
                interviewRepository.countByStatus(
                        InterviewStatus.SCHEDULED
                );

        long completed =
                interviewRepository.countByStatus(
                        InterviewStatus.COMPLETED
                );

        long selected =
                interviewRepository.countByStatus(
                        InterviewStatus.SELECTED
                );

        long rejected =
                interviewRepository.countByStatus(
                        InterviewStatus.REJECTED
                );

        long onHold =
                interviewRepository.countByStatus(
                        InterviewStatus.ON_HOLD
                );

        return DashboardStatsResponse.builder()
                .totalInterviews(totalInterviews)
                .scheduled(scheduled)
                .completed(completed)
                .selected(selected)
                .rejected(rejected)
                .onHold(onHold)
                .build();
    }
}