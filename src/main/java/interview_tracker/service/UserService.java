package interview_tracker.service;


import interview_tracker.dto.UserChangePasswordRequest;
import interview_tracker.dto.UserSignupRequest;

public interface UserService {

    void signup(UserSignupRequest request);

    //User Change-password
    void changePassword(
            String username,
            UserChangePasswordRequest request);
}