package com.fpmislata.daw1.projectedaw1.persistance.dao.impl;

import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.rowmapper.LlibreRowMapper;
import lombok.extern.log4j.Log4j2;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class LlibreDaoImpl implements LlibreDao {
    private final DatabaseConnection databaseConnection;
    private final LlibreRowMapper llibreRowMapper;

    public LlibreDaoImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        this.llibreRowMapper = new LlibreRowMapper();
    }

    @Override
    public List<Llibre> findAll() {
        String sql = "SELECT * FROM llibre";
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.toLlibreList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Llibre findByIsbn(String isbn) {
        String sql = "SELECT * FROM llibre WHERE isbn = ?";
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql)) {
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
        String sql = "SELECT * FROM llibre ORDER BY data_publicacio DESC LIMIT ?";
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql)) {
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

    @Override
    public boolean insert(Llibre llibre) {
        String sql = "INSERT INTO llibre (isbn, titol, resum, data_publicacio, nombre_pagines) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, llibre.getIsbn());
            preparedStatement.setString(2, llibre.getTitol());
            preparedStatement.setString(3, llibre.getResum());
            preparedStatement.setDate(4, Date.valueOf(llibre.getDataPublicacio()));
            preparedStatement.setInt(5, llibre.getNombrePagines());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Llibre delete(String isbn) {
        String sql = "DELETE FROM llibre WHERE isbn = ?";
        Llibre llibre = findByIsbn(isbn);
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.executeUpdate();
            return llibre;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
