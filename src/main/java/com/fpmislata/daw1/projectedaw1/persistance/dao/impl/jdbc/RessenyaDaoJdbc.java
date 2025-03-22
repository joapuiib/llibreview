package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RessenyaDao;
import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.rowmapper.RessenyaRowMapper;

import java.sql.PreparedStatement;
import java.util.List;

public class RessenyaDaoJdbc implements RessenyaDao {

    private final DatabaseConnection databaseConnection;
    private final RessenyaRowMapper ressenyaRowMapper;
    public RessenyaDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        this.ressenyaRowMapper = new RessenyaRowMapper();
    }

    @Override
    public Ressenya findByLlibreIsbnAndUsername(String isbn, String username) {
        String sql = "SELECT * FROM ressenya where isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, username);
            List<Ressenya> valoracioList = ressenyaRowMapper.map(preparedStatement.executeQuery());
            return valoracioList.isEmpty() ? null : valoracioList.getFirst();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int insert(Ressenya ressenya) {
        String sql = "INSERT INTO ressenya (isbn, username, comentari, data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, ressenya.getIsbn());
            preparedStatement.setString(2, ressenya.getUsername());
            preparedStatement.setString(3, ressenya.getComentari());
            preparedStatement.setObject(4, ressenya.getData());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int update(Ressenya ressenya) {
        String sql = "UPDATE ressenya SET comentari = ?, data = ? WHERE isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, ressenya.getComentari());
            preparedStatement.setObject(2, ressenya.getData());
            preparedStatement.setString(3, ressenya.getIsbn());
            preparedStatement.setString(4, ressenya.getUsername());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int delete(String isbn, String username) {
        String sql = "DELETE FROM ressenya WHERE isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, username);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
