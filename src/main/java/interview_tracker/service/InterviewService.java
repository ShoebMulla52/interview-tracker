package interview_tracker.service;





import interview_tracker.dto.InterviewRequest;
import interview_tracker.entity.Interview;

import java.util.List;

public interface InterviewService {

    Interview createInterview(InterviewRequest request);

    List<Interview> getAllInterviews();

    Interview getInterviewById(Long id);

    Interview updateInterview(Long id, InterviewRequest request);

    void deleteInterview(Long id);
}
