package com.fpmislata.daw1.projectedaw1.domain.service;

import com.fpmislata.daw1.projectedaw1.domain.entity.User;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    boolean checkLogin(String username, String password);
}
