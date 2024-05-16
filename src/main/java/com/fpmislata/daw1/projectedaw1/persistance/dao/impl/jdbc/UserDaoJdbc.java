package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.User;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UserDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.database.DatabaseConnection;

public class UserDaoJdbc implements UserDao {


    private final DatabaseConnection databaseConnection;
    // private final UserRowMapper userRowMapper;

    public UserDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        // this.userRowMapper = new UserRowMapper();
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public void create(User user, String password) {
    }

    @Override
    public void login(String username, String password) {
    }
}
