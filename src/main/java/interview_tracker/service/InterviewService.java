package interview_tracker.service;






import interview_tracker.dto.InterviewRequest;
import interview_tracker.dto.InterviewResponse;
import interview_tracker.entity.InterviewMode;
import interview_tracker.entity.InterviewStatus;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface InterviewService {

    InterviewResponse createInterview(InterviewRequest request);

    Page<InterviewResponse> getAllInterviews(
            int page,
            int size,
            String sortBy,
            String direction
    );

    InterviewResponse getInterviewById(Long id);

    InterviewResponse updateInterview(
            Long id,
            InterviewRequest request
    );

    void deleteInterview(Long id);

    Page<InterviewResponse> searchByCandidate(
            String candidateName,
            int page,
            int size
    );

    Page<InterviewResponse> searchByCompany(
            String companyName,
            int page,
            int size
    );

//    Page<InterviewResponse> filterByStatus(
//            String status,
//            int page,
//            int size
//    );

    Page<InterviewResponse> filterByStatus(
            InterviewStatus status,
            int page,
            int size
    );

    Page<InterviewResponse> filterByMode(
            InterviewMode mode,
            int page,
            int size
    );

    Page<InterviewResponse> filterByDate(
            LocalDate startDate,
            LocalDate endDate,
            int page,
            int size
    );
}


