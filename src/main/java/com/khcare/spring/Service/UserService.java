package com.khcare.spring.Service;

import com.khcare.spring.dto.User;

public interface UserService {

    User findByUsername(String username);
}
