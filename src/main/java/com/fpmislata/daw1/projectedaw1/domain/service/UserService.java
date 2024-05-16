package com.fpmislata.daw1.projectedaw1.domain.service;

import com.fpmislata.daw1.projectedaw1.domain.entity.User;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    void create(String username, String email, String password, String passwordConfirmation);
    void login(String username, String password);
}
