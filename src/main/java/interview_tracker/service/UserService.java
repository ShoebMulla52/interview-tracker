package interview_tracker.service;


import interview_tracker.dto.UserSignupRequest;

public interface UserService {

    void signup(UserSignupRequest request);
}