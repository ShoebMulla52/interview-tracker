package interview_tracker.dto;



import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsResponse {

    private long totalInterviews;

    private long scheduled;

    private long completed;

    private long selected;

    private long rejected;

    private long onHold;

    //interview stats
    private long thisWeek;

    private long thisMonth;

    private long thisYear;


    // completed interview stats
    private long thisWeekCompleted;

    private long thisMonthCompleted;

    private long thisYearCompleted;
}
