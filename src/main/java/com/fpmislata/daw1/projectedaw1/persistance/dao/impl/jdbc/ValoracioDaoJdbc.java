package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ValoracioDao;
import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.rowmapper.ValoracioRowMapper;

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
            ResultSet rs = preparedStatement.executeQuery();
            List<Valoracio> valoracioList = valoracioRowMapper.map(rs);
            return valoracioList.isEmpty() ? null : valoracioList.getFirst();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Valoracio> findByLlibreIsbn(String isbn) {
        String sql = "SELECT * FROM valoracio where isbn = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            return valoracioRowMapper.map(rs);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Valoracio> findByUsername(String username) {
        String sql = "SELECT * FROM valoracio where username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            return valoracioRowMapper.map(rs);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int insert(Valoracio valoracio) {
        String sql = "INSERT INTO valoracio (isbn, username, puntuacio, data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, valoracio.getIsbn());
            preparedStatement.setString(2, valoracio.getUsername());
            preparedStatement.setInt(3, valoracio.getPuntuacio());
            preparedStatement.setDate(4, java.sql.Date.valueOf(valoracio.getData()));
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int update(Valoracio valoracio) {
        String sql = "UPDATE valoracio SET puntuacio = ?, data = ? WHERE isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, valoracio.getPuntuacio());
            preparedStatement.setDate(2, java.sql.Date.valueOf(valoracio.getData()));
            preparedStatement.setString(3, valoracio.getIsbn());
            preparedStatement.setString(4, valoracio.getUsername());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int delete(String isbn, String username) {
        String sql = "DELETE FROM valoracio WHERE isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, username);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
