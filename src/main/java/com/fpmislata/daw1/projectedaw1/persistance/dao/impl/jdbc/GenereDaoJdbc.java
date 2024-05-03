package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.GenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper.GenereRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GenereDaoJdbc implements GenereDao {

    private final DatabaseConnection databaseConnection;
    private final GenereRowMapper genereRowMapper;
    public GenereDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        this.genereRowMapper = new GenereRowMapper();
    }
    @Override
    public List<Genere> findAll() {
        String sql = "SELECT * FROM genere";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            return genereRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Genere findById(int id) {
        String sql = "SELECT * FROM genere where id = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<Genere> genereList = genereRowMapper.map(rs);
            return genereList.isEmpty() ? null : genereList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
