package com.fpmislata.daw1.projectedaw1.persistance.dao.impl;

import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.rowmapper.LlibreRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LlibreDaoImpl implements LlibreDao {
    private DatabaseConnection databaseConnection;
    private LlibreRowMapper llibreRowMapper;

    public LlibreDaoImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        this.llibreRowMapper = new LlibreRowMapper();
    }

    @Override
    public List<Llibre> findAll() {
        try {
            String sql = "SELECT * FROM llibre";
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.toLlibreList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Llibre findByIsbn(String isbn) {
        try {
            String sql = "SELECT * FROM llibre WHERE isbn = ?";
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return llibreRowMapper.toLlibre(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Llibre> findLatest(int n) {
        try {
            String sql = "SELECT * FROM llibre ORDER BY data_publicacio DESC LIMIT ?";
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, n);
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.toLlibreList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Llibre> findMostRead(int n) {
        return null;
    }

    @Override
    public List<Llibre> findBestReview(int n) {
        return null;
    }
}
