package interview_tracker.service.impl;


import interview_tracker.dto.InterviewRequest;
import interview_tracker.entity.Interview;
import interview_tracker.repository.InterviewRepository;
import interview_tracker.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;

    @Override
    public Interview createInterview(InterviewRequest request) {

        Interview interview = Interview.builder()
                .candidateName(request.getCandidateName())
                .companyName(request.getCompanyName())
                .interviewSupporter(request.getInterviewSupporter())
                .interviewDate(request.getInterviewDate())
                .interviewTime(request.getInterviewTime())
                .round(request.getRound())
                .role(request.getRole())
                .mode(request.getMode())
                .status(request.getStatus())
                .feedback(request.getFeedback())
                .remarks(request.getRemarks())
                .build();

        return interviewRepository.save(interview);
    }

    @Override
    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }

    @Override
    public Interview getInterviewById(Long id) {

        return interviewRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Interview not found with id: " + id));
    }

    @Override
    public Interview updateInterview(Long id, InterviewRequest request) {

        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Interview not found with id: " + id));

        interview.setCandidateName(request.getCandidateName());
        interview.setCompanyName(request.getCompanyName());
        interview.setInterviewSupporter(request.getInterviewSupporter());
        interview.setInterviewDate(request.getInterviewDate());
        interview.setInterviewTime(request.getInterviewTime());
        interview.setRound(request.getRound());
        interview.setRole(request.getRole());
        interview.setMode(request.getMode());
        interview.setStatus(request.getStatus());
        interview.setFeedback(request.getFeedback());
        interview.setRemarks(request.getRemarks());

        return interviewRepository.save(interview);
    }

    @Override
    public void deleteInterview(Long id) {

        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Interview not found with id: " + id));

        interviewRepository.delete(interview);
    }
}
