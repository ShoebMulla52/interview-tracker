package interview_tracker.service;


import interview_tracker.dto.AdminLoginRequest;
import interview_tracker.dto.LoginResponse;

public interface AdminService {

    LoginResponse login(AdminLoginRequest request);
}