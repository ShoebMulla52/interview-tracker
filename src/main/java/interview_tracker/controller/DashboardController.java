package interview_tracker.controller;

;
import interview_tracker.dto.ApiResponse;
import interview_tracker.dto.DashboardStatsResponse;
import interview_tracker.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<DashboardStatsResponse>>
    getDashboardStats() {

        DashboardStatsResponse stats =
                dashboardService.getDashboardStats();

        return ResponseEntity.ok(
                ApiResponse.<DashboardStatsResponse>builder()
                        .success(true)
                        .message(
                                "Dashboard statistics fetched successfully"
                        )
                        .data(stats)
                        .build()
        );
    }
}
