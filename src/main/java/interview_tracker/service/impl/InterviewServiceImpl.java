package interview_tracker.service.impl;

import interview_tracker.entity.InterviewMode;
import interview_tracker.entity.InterviewStatus;
import interview_tracker.exception.InterviewNotFoundException;
import interview_tracker.dto.InterviewRequest;
import interview_tracker.dto.InterviewResponse;
import interview_tracker.entity.Interview;
import interview_tracker.repository.InterviewRepository;
import interview_tracker.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;

    @Override
    public InterviewResponse createInterview(
            InterviewRequest request) {

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

        return mapToResponse(
                interviewRepository.save(interview)
        );
    }

    @Override
    public Page<InterviewResponse> getAllInterviews(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(page, size, sort);

        return interviewRepository
                .findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public InterviewResponse getInterviewById(Long id) {

        Interview interview = interviewRepository
                .findById(id)
                .orElseThrow(() ->
                        new InterviewNotFoundException(
                                "Interview not found with id: " + id
                        ));

        return mapToResponse(interview);
    }

    @Override
    public InterviewResponse updateInterview(
            Long id,
            InterviewRequest request) {

        Interview interview = interviewRepository
                .findById(id)
                .orElseThrow(() ->
                        new InterviewNotFoundException(
                                "Interview not found with id: " + id
                        ));

        interview.setCandidateName(request.getCandidateName());
        interview.setCompanyName(request.getCompanyName());
        interview.setInterviewSupporter(
                request.getInterviewSupporter()
        );
        interview.setInterviewDate(request.getInterviewDate());
        interview.setInterviewTime(request.getInterviewTime());
        interview.setRound(request.getRound());
        interview.setRole(request.getRole());
        interview.setMode(request.getMode());
        interview.setStatus(request.getStatus());
        interview.setFeedback(request.getFeedback());
        interview.setRemarks(request.getRemarks());

        return mapToResponse(
                interviewRepository.save(interview)
        );
    }

    @Override
    public void deleteInterview(Long id) {

        Interview interview = interviewRepository
                .findById(id)
                .orElseThrow(() ->
                        new InterviewNotFoundException(
                                "Interview not found with id: " + id
                        ));

        interviewRepository.delete(interview);
    }

    @Override
    public Page<InterviewResponse> searchByCandidate(
            String candidateName,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return interviewRepository
                .findByCandidateNameContainingIgnoreCase(
                        candidateName,
                        pageable
                )
                .map(this::mapToResponse);
    }

    @Override
    public Page<InterviewResponse> searchByCompany(
            String companyName,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return interviewRepository
                .findByCompanyNameContainingIgnoreCase(
                        companyName,
                        pageable
                )
                .map(this::mapToResponse);
    }



    @Override
    public Page<InterviewResponse> filterByStatus(
            InterviewStatus status,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return interviewRepository
                .findByStatus(status, pageable)
                .map(this::mapToResponse);
    }

    @Override
    public Page<InterviewResponse> filterByMode(
            InterviewMode mode,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return interviewRepository
                .findByMode(mode, pageable)
                .map(this::mapToResponse);
    }


    //Date Filter Logic
    @Override
    public Page<InterviewResponse> filterByDate(
            LocalDate startDate,
            LocalDate endDate,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return interviewRepository
                .findByInterviewDateBetween(
                        startDate,
                        endDate,
                        pageable
                )
                .map(this::mapToResponse);
    }

    private InterviewResponse mapToResponse(
            Interview interview) {

        return InterviewResponse.builder()
                .id(interview.getId())
                .candidateName(interview.getCandidateName())
                .companyName(interview.getCompanyName())
                .interviewSupporter(
                        interview.getInterviewSupporter()
                )
                .interviewDate(interview.getInterviewDate())
                .interviewTime(interview.getInterviewTime())
                .round(interview.getRound())
                .role(interview.getRole())
                .mode(interview.getMode())
                .status(interview.getStatus())
                .feedback(interview.getFeedback())
                .remarks(interview.getRemarks())
                .createdAt(interview.getCreatedAt())
                .updatedAt(interview.getUpdatedAt())
                .build();
    }
}