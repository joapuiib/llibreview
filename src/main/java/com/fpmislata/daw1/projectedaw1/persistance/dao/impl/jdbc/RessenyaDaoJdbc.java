package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RessenyaDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper.RessenyaRowMapper;

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
            return valoracioList.isEmpty() ? null : valoracioList.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void save(Ressenya ressenya) {
        if (findByLlibreIsbnAndUsername(ressenya.getIsbn(), ressenya.getUsername()) != null) {
            update(ressenya);
        } else {
            insert(ressenya);
        }
    }

    @Override
    public void insert(Ressenya ressenya) {
        String sql = "INSERT INTO ressenya (isbn, username, comentari, data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, ressenya.getIsbn());
            preparedStatement.setString(2, ressenya.getUsername());
            preparedStatement.setString(3, ressenya.getComentari());
            preparedStatement.setObject(4, ressenya.getData());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Ressenya ressenya) {
        String sql = "UPDATE ressenya SET comentari = ?, data = ? WHERE isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, ressenya.getComentari());
            preparedStatement.setObject(2, ressenya.getData());
            preparedStatement.setString(3, ressenya.getIsbn());
            preparedStatement.setString(4, ressenya.getUsername());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String isbn, String username) {
        String sql = "DELETE FROM ressenya WHERE isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
