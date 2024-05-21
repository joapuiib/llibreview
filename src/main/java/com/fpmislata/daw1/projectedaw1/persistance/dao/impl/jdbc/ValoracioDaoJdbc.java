package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ValoracioDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper.ValoracioRowMapper;

import java.sql.PreparedStatement;
import java.util.List;

public class ValoracioDaoJdbc implements ValoracioDao {

    private final DatabaseConnection databaseConnection;
    private final ValoracioRowMapper valoracioRowMapper;
    public ValoracioDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        this.valoracioRowMapper = new ValoracioRowMapper();
    }

    @Override
    public Valoracio findByLlibreIsbnAndUsername(String isbn, String username) {
        String sql = "SELECT * FROM valoracio where isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, username);
            List<Valoracio> valoracioList = valoracioRowMapper.map(preparedStatement.executeQuery());
            return valoracioList.isEmpty() ? null : valoracioList.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Valoracio> findByLlibreIsbn(String isbn) {
        String sql = "SELECT * FROM valoracio where isbn = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            return valoracioRowMapper.map(preparedStatement.executeQuery());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Valoracio> findByUsername(String username) {
        String sql = "SELECT * FROM valoracio where username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            return valoracioRowMapper.map(preparedStatement.executeQuery());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void save(Valoracio valoracio) {
        if (findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername()) != null) {
            update(valoracio);
        } else {
            insert(valoracio);
        }
    }

    @Override
    public void insert(Valoracio valoracio) {
        String sql = "INSERT INTO valoracio (isbn, username, valoracio, data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, valoracio.getIsbn());
            preparedStatement.setString(2, valoracio.getUsername());
            preparedStatement.setInt(3, valoracio.getValoracio());
            preparedStatement.setDate(4, java.sql.Date.valueOf(valoracio.getData()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Valoracio valoracio) {
        String sql = "UPDATE valoracio SET valoracio = ?, data = ? WHERE isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, valoracio.getValoracio());
            preparedStatement.setDate(2, java.sql.Date.valueOf(valoracio.getData()));
            preparedStatement.setString(3, valoracio.getIsbn());
            preparedStatement.setString(4, valoracio.getUsername());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String isbn, String username) {
        String sql = "DELETE FROM valoracio WHERE isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
