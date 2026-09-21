package interview_tracker.service.impl;




import interview_tracker.dto.DashboardStatsResponse;
import interview_tracker.entity.InterviewStatus;
import interview_tracker.repository.InterviewRepository;
import interview_tracker.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final InterviewRepository interviewRepository;

    @Override
    public DashboardStatsResponse getDashboardStats() {

        LocalDate today = LocalDate.now();

        // This Week: Monday to Sunday
        LocalDate weekStart = today.with(
                TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
        );

        LocalDate weekEnd = today.with(
                TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)
        );

        // This Month: First day to last day of current month
        LocalDate monthStart = today.with(
                TemporalAdjusters.firstDayOfMonth()
        );

        LocalDate monthEnd = today.with(
                TemporalAdjusters.lastDayOfMonth()
        );

        // This Year: January 1 to December 31
        LocalDate yearStart = today.with(
                TemporalAdjusters.firstDayOfYear()
        );

        LocalDate yearEnd = today.with(
                TemporalAdjusters.lastDayOfYear()
        );

        // Existing dashboard counts
        long totalInterviews = interviewRepository.count();

        long scheduled = interviewRepository.countByStatus(
                InterviewStatus.SCHEDULED
        );

        long completed = interviewRepository.countByStatus(
                InterviewStatus.COMPLETED
        );

        long selected = interviewRepository.countByStatus(
                InterviewStatus.SELECTED
        );

        long rejected = interviewRepository.countByStatus(
                InterviewStatus.REJECTED
        );

        long onHold = interviewRepository.countByStatus(
                InterviewStatus.ON_HOLD
        );

        // New date-based interview counts
        long thisWeek = interviewRepository.countByInterviewDateBetween(
                weekStart,
                weekEnd
        );

        long thisMonth = interviewRepository.countByInterviewDateBetween(
                monthStart,
                monthEnd
        );

        long thisYear = interviewRepository.countByInterviewDateBetween(
                yearStart,
                yearEnd
        );

        return DashboardStatsResponse.builder()
                .totalInterviews(totalInterviews)
                .scheduled(scheduled)
                .completed(completed)
                .selected(selected)
                .rejected(rejected)
                .onHold(onHold)
                .thisWeek(thisWeek)
                .thisMonth(thisMonth)
                .thisYear(thisYear)
                .build();
    }
}