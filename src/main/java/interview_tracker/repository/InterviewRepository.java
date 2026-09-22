package interview_tracker.repository;




import interview_tracker.entity.Interview;
import interview_tracker.entity.InterviewMode;
import interview_tracker.entity.InterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;


public interface InterviewRepository
        extends JpaRepository<Interview, Long> {

    Page<Interview> findByCandidateNameContainingIgnoreCase(
            String candidateName,
            Pageable pageable
    );

    Page<Interview> findByCompanyNameContainingIgnoreCase(
            String companyName,
            Pageable pageable
    );

//    Page<Interview> findByStatusIgnoreCase(
//            String status,
//            Pageable pageable
//    );

    Page<Interview> findByStatus(
            InterviewStatus status,
            Pageable pageable
    );

    Page<Interview> findByMode(
            InterviewMode mode,
            Pageable pageable
    );

    Page<Interview> findByInterviewDateBetween(
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    long countByInterviewDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

//    long countByStatusIgnoreCase(String status);

    long countByStatus(
            InterviewStatus status
    );

    // Completed interviews between dates
    long countByStatusAndInterviewDateBetween(
            InterviewStatus status,
            LocalDate startDate,
            LocalDate endDate
    );
}