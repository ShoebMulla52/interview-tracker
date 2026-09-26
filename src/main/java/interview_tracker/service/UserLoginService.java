package interview_tracker.service;


import interview_tracker.dto.UserLoginRequest;

public interface UserLoginService {

    String login(UserLoginRequest request);
}
