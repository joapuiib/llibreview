package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreGenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.rowmapper.GenereRowMapper;
import com.fpmislata.daw1.projectedaw1.persistance.rowmapper.LlibreRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LlibreGenereDaoJdbc implements LlibreGenereDao {

    private final DatabaseConnection databaseConnection;
    private final LlibreRowMapper llibreRowMapper;
    private final GenereRowMapper genereRowMapper;
    public LlibreGenereDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        this.llibreRowMapper = new LlibreRowMapper();
        this.genereRowMapper = new GenereRowMapper();
    }

    @Override
    public List<Llibre> findLlibresByGenereId(int id) {
        String sql = "SELECT * FROM llibre l inner join llibre_genere lg on l.isbn = lg.isbn where lg.id_genere = ?";
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Genere> findGeneresByLlibreIsbn(String isbn) {
        String sql = "SELECT * FROM genere g inner join llibre_genere lg on g.id_genere = lg.id_genere where lg.isbn = ?";
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            return genereRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
