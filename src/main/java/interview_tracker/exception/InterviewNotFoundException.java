package interview_tracker.exception;



public class InterviewNotFoundException extends RuntimeException {

    public InterviewNotFoundException(String message) {
        super(message);
    }
}
