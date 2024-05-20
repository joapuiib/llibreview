package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.common.utils.EncryptionUtils;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UsuariDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper.UsuariRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuariDaoJdbc implements UsuariDao {


    private final DatabaseConnection databaseConnection;
    private final UsuariRowMapper usuariRowMapper;

    public UsuariDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        this.usuariRowMapper = new UsuariRowMapper();
    }

    @Override
    public Usuari findByUsername(String username) {
        String sql = "SELECT * FROM user where username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            List<Usuari> usuariList = usuariRowMapper.map(rs);
            return usuariList.isEmpty() ? null : usuariList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Usuari findByEmail(String email) {
        String sql = "SELECT * FROM user where email = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            List<Usuari> usuariList = usuariRowMapper.map(rs);
            return usuariList.isEmpty() ? null : usuariList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void create(Usuari usuari, String password) {
        String sql = "INSERT INTO user (username, email, data_registre, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuari.getUsername());
            preparedStatement.setString(2, usuari.getEmail());
            preparedStatement.setObject(3, usuari.getDataRegistre());
            preparedStatement.setString(4, EncryptionUtils.hashPassword(password));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean login(String username, String password) {
        Usuari usuari = findByUsername(username);

        String errorMessage = "No s'ha trobat l'usuari o la contrasenya és incorrecta.";
        if (usuari == null) {
            throw new RuntimeException(errorMessage);
        }
        boolean passwordMatch = EncryptionUtils.checkPassword(password, usuari.getContrasenyaHash());
        if (!passwordMatch) {
            throw new RuntimeException(errorMessage);
        }
        return true;
    }
}
