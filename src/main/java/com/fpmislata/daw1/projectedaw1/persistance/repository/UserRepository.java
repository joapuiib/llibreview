package com.fpmislata.daw1.projectedaw1.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.User;

public interface UserRepository {
    User findByUsername(String username);
    User findByEmail(String email);
    void create(User user, String password);
    boolean login(String username, String password);
}
