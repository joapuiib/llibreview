package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.User;
import com.fpmislata.daw1.projectedaw1.domain.service.UserService;
import com.fpmislata.daw1.projectedaw1.persistance.repository.UserRepository;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void create(String username, String email, String password, String passwordConfirmation) {
        if (!password.equals(passwordConfirmation)) {
            throw new RuntimeException("Les contrasenyes no coincideixen.");
        } else if (findByUsername(username) != null) {
            throw new RuntimeException("Ja hi ha un usuari amb aquest nom d'usuari.");
        } else if (findByEmail(email) != null) {
            throw new RuntimeException("Ja hi ha un usuari associat a aquest correu electrònic.");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        userRepository.create(user, password);
    }

    @Override
    public void login(String username, String password) {
        userRepository.login(username, password);
    }

}
