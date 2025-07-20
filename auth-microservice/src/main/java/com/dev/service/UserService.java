package com.dev.service;

import com.dev.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean existsByUsername(String username);

//    boolean existsByUserName(String userName);

    void save(User user);

    void updatePassword(String userName, String password);
}
