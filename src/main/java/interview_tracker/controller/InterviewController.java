package interview_tracker.controller;


import interview_tracker.dto.InterviewRequest;
import interview_tracker.entity.Interview;
import interview_tracker.service.InterviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<Interview> createInterview(
            @Valid @RequestBody InterviewRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(interviewService.createInterview(request));
    }

    @GetMapping
    public ResponseEntity<List<Interview>> getAllInterviews() {

        return ResponseEntity.ok(
                interviewService.getAllInterviews()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interview> getInterviewById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                interviewService.getInterviewById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interview> updateInterview(
            @PathVariable Long id,
            @Valid @RequestBody InterviewRequest request) {

        return ResponseEntity.ok(
                interviewService.updateInterview(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInterview(
            @PathVariable Long id) {

        interviewService.deleteInterview(id);

        return ResponseEntity.ok("Interview deleted successfully");
    }
}