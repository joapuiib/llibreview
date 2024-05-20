package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Rating;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RatingDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper.RatingRowMapper;

import java.sql.PreparedStatement;
import java.util.List;

public class RatingDaoJdbc implements RatingDao {

    private final DatabaseConnection databaseConnection;
    private final RatingRowMapper ratingRowMapper;
    public RatingDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        this.ratingRowMapper = new RatingRowMapper();
    }

    @Override
    public Rating findByLlibreIsbnAndUsername(String isbn, String username) {
        String sql = "SELECT * FROM rating where isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, username);
            List<Rating> ratingList = ratingRowMapper.map(preparedStatement.executeQuery());
            return ratingList.isEmpty() ? null : ratingList.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Rating> findByLlibreIsbn(String isbn) {
        String sql = "SELECT * FROM rating where isbn = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            return ratingRowMapper.map(preparedStatement.executeQuery());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Rating> findByUsername(String username) {
        String sql = "SELECT * FROM rating where username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            return ratingRowMapper.map(preparedStatement.executeQuery());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void save(Rating rating) {
        if (findByLlibreIsbnAndUsername(rating.getIsbn(), rating.getUsername()) != null) {
            update(rating);
        } else {
            insert(rating);
        }
    }

    @Override
    public void insert(Rating rating) {
        String sql = "INSERT INTO rating (isbn, username, rating, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, rating.getIsbn());
            preparedStatement.setString(2, rating.getUsername());
            preparedStatement.setInt(3, rating.getRating());
            preparedStatement.setDate(4, java.sql.Date.valueOf(rating.getDate()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Rating rating) {
        String sql = "UPDATE rating SET rating = ?, date = ? WHERE isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, rating.getRating());
            preparedStatement.setDate(2, java.sql.Date.valueOf(rating.getDate()));
            preparedStatement.setString(3, rating.getIsbn());
            preparedStatement.setString(4, rating.getUsername());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String isbn, String username) {
        String sql = "DELETE FROM rating WHERE isbn = ? and username = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
