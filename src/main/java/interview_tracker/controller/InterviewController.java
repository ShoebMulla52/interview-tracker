package interview_tracker.controller;




import interview_tracker.dto.ApiResponse;
import interview_tracker.dto.InterviewRequest;
import interview_tracker.dto.InterviewResponse;
import interview_tracker.entity.InterviewMode;
import interview_tracker.entity.InterviewStatus;
import interview_tracker.service.InterviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<InterviewResponse>>
    createInterview(
            @Valid @RequestBody InterviewRequest request) {

        InterviewResponse response =
                interviewService.createInterview(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.<InterviewResponse>builder()
                        .success(true)
                        .message("Interview created successfully")
                        .data(response)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<InterviewResponse>>>
    getAllInterviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "interviewDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Page<InterviewResponse> response =
                interviewService.getAllInterviews(
                        page, size, sortBy, direction
                );

        return ResponseEntity.ok(
                ApiResponse.<Page<InterviewResponse>>builder()
                        .success(true)
                        .message("Interviews fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InterviewResponse>>
    getInterviewById(@PathVariable Long id) {

        InterviewResponse response =
                interviewService.getInterviewById(id);

        return ResponseEntity.ok(
                ApiResponse.<InterviewResponse>builder()
                        .success(true)
                        .message("Interview fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InterviewResponse>>
    updateInterview(
            @PathVariable Long id,
            @Valid @RequestBody InterviewRequest request) {

        InterviewResponse response =
                interviewService.updateInterview(id, request);

        return ResponseEntity.ok(
                ApiResponse.<InterviewResponse>builder()
                        .success(true)
                        .message("Interview updated successfully")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>
    deleteInterview(@PathVariable Long id) {

        interviewService.deleteInterview(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Interview deleted successfully")
                        .data(null)
                        .build()
        );
    }

    @GetMapping("/search/candidate")
    public ResponseEntity<ApiResponse<Page<InterviewResponse>>>
    searchByCandidate(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                ApiResponse.<Page<InterviewResponse>>builder()
                        .success(true)
                        .message("Search completed")
                        .data(
                                interviewService.searchByCandidate(
                                        name, page, size
                                )
                        )
                        .build()
        );
    }

    @GetMapping("/search/company")
    public ResponseEntity<ApiResponse<Page<InterviewResponse>>>
    searchByCompany(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                ApiResponse.<Page<InterviewResponse>>builder()
                        .success(true)
                        .message("Search completed")
                        .data(
                                interviewService.searchByCompany(
                                        name, page, size
                                )
                        )
                        .build()
        );
    }

    @GetMapping("/filter/status")
    public ResponseEntity<ApiResponse<Page<InterviewResponse>>>
    filterByStatus(
            @RequestParam InterviewStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                ApiResponse.<Page<InterviewResponse>>builder()
                        .success(true)
                        .message("Status filter applied")
                        .data(
                                interviewService.filterByStatus(
                                        status, page, size
                                )
                        )
                        .build()
        );
    }

    @GetMapping("/filter/mode")
    public ResponseEntity<ApiResponse<Page<InterviewResponse>>>
    filterByMode(
            @RequestParam InterviewMode mode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                ApiResponse.<Page<InterviewResponse>>builder()
                        .success(true)
                        .message("Mode filter applied")
                        .data(
                                interviewService.filterByMode(
                                        mode, page, size
                                )
                        )
                        .build()
        );
    }

    //Date filter Controller

    @GetMapping("/filter/date")
    public ResponseEntity<ApiResponse<Page<InterviewResponse>>>
    filterByDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                ApiResponse.<Page<InterviewResponse>>builder()
                        .success(true)
                        .message("Date filter applied")
                        .data(
                                interviewService.filterByDate(
                                        startDate,
                                        endDate,
                                        page,
                                        size
                                )
                        )
                        .build()
        );
    }
}