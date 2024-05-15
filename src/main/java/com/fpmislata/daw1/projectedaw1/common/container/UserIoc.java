package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.common.AppPropertiesReader;
import com.fpmislata.daw1.projectedaw1.domain.service.UserService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.UserServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UserDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.UserDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.UserDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.UserTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.UserRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.UserRepositoryImpl;

public class UserIoc {
    private static UserService userService;
    private static UserRepository userRepository;
    private static UserDao userDao;

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl(getUserRepository());
        }
        return userService;
    }

    public static UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepositoryImpl(getUserDao());
        }
        return userRepository;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            if(AppPropertiesReader.getProperty("dao").equals("jdbc"))
                userDao = new UserDaoJdbc();
            else {
                UserTableMemory userTableMemory = new UserTableMemory();
                userDao = new UserDaoMemory(userTableMemory);
            }
        }
        return userDao;
    }
}
