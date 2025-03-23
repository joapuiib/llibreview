package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.GenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.rowmapper.GenereRowMapper;

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
        String sql = "SELECT * FROM genere where id_genere = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<Genere> genereList = genereRowMapper.map(rs);
            return genereList.isEmpty() ? null : genereList.getFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Genere> findGeneresByLlibreIsbn(String isbn) {
        String sql = """
            SELECT *
            FROM genere g
            INNER JOIN llibre_genere lg ON g.id_genere = lg.id_genere
            WHERE lg.isbn = ?
            """;
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            return genereRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
