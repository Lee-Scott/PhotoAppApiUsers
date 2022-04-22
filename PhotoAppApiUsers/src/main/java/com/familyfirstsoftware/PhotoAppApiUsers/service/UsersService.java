package com.familyfirstsoftware.PhotoAppApiUsers.service;

import com.familyfirstsoftware.PhotoAppApiUsers.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);

    UserDto getUserDetailsByEmail(String email);
}
