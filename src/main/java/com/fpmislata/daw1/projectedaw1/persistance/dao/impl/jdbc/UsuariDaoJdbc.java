package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UsuariDao;
import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.rowmapper.UsuariRowMapper;

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
        String sql = "SELECT * FROM usuari where username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            List<Usuari> usuariList = usuariRowMapper.map(rs);
            return usuariList.isEmpty() ? null : usuariList.getFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Usuari findByEmail(String email) {
        String sql = "SELECT * FROM usuari where email = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            List<Usuari> usuariList = usuariRowMapper.map(rs);
            return usuariList.isEmpty() ? null : usuariList.getFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int insert(Usuari usuari, String passwordHash) {
        String sql = """
            INSERT INTO usuari (username, email, data_registre, password_hash)
            VALUES (?, ?, ?, ?)
            """;
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuari.getUsername());
            preparedStatement.setString(2, usuari.getEmail());
            preparedStatement.setObject(3, usuari.getDataRegistre());
            // preparedStatement.setString(4, EncryptionUtils.hashPassword(passwordHash));
            preparedStatement.setString(4, passwordHash);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
