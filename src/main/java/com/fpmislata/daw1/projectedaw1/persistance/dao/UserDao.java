package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.User;

public interface UserDao {
    User findByUsername(String username);
    User findByEmail(String email);
    void create(User user, String password);
    void login(String username, String password);
}
