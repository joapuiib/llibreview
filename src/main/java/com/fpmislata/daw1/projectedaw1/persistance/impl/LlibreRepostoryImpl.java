package com.fpmislata.daw1.projectedaw1.persistance.impl;

import com.fpmislata.daw1.projectedaw1.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.LlibreRepository;
import com.fpmislata.daw1.projectedaw1.persistance.impl.rowmapper.LlibreRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class LlibreRepostoryImpl implements LlibreRepository {
    private final String tableName = "llibre";
    @Autowired
    private LlibreRowMapper llibreRowMapper;
    @Autowired
    protected DatabaseConnection connection;

    @Override
    public List<Llibre> findAll() {
        try {
            String sql = "SELECT * FROM " + tableName;
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.toLlibreList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Llibre> findLatest(int n) {
        try {
            String sql = "SELECT * FROM " + tableName + " ORDER BY data_publicacio DESC LIMIT ?";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(sql);
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