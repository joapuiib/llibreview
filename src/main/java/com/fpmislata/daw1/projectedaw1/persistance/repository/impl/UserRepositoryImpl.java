package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.User;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UserDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;

    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void create(User user, String password) {
        userDao.create(user, password);
    }

    @Override
    public void login(String username, String password) {
        userDao.login(username, password);
    }
}