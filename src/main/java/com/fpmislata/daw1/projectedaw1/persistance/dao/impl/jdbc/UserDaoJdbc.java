package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.common.Utils;
import com.fpmislata.daw1.projectedaw1.domain.entity.User;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UserDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper.UserRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {


    private final DatabaseConnection databaseConnection;
    private final UserRowMapper userRowMapper;

    public UserDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        this.userRowMapper = new UserRowMapper();
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user where username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            List<User> userList = userRowMapper.map(rs);
            return userList.isEmpty() ? null : userList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM user where email = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            List<User> userList = userRowMapper.map(rs);
            return userList.isEmpty() ? null : userList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void create(User user, String password) {
        String sql = "INSERT INTO user (username, email, data_registre, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setObject(3, user.getDataRegistre());
            preparedStatement.setString(4, Utils.hashPassword(password));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean login(String username, String password) {
        User user = findByUsername(username);

        String errorMessage = "No s'ha trobat l'usuari o la contrasenya és incorrecta.";
        if (user == null) {
            throw new RuntimeException(errorMessage);
        }
        boolean passwordMatch = Utils.checkPassword(password, user.getHashedPassword());
        if (!passwordMatch) {
            throw new RuntimeException(errorMessage);
        }
        return true;
    }
}
