package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.rowmapper.LlibreRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LlibreDaoJdbc implements LlibreDao {

    private final DatabaseConnection databaseConnection;
    private final LlibreRowMapper llibreRowMapper;
    public LlibreDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        this.llibreRowMapper = new LlibreRowMapper();
    }

    @Override
    public Llibre findByIsbn(String isbn) {
        String sql = "SELECT * FROM llibre where isbn = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            List<Llibre> llibreList = llibreRowMapper.map(rs);
            return llibreList.isEmpty() ? null : llibreList.getFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Llibre> findAll() {
        String sql = "SELECT * FROM llibre";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Llibre> findLlibresByAutorId(int idAutor) {
        String sql = """
            SELECT *
            FROM llibre l
            INNER JOIN escriu e ON l.isbn = e.isbn
            WHERE e.id_autor = ?
            """;
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idAutor);
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Llibre> findLlibresByGenereId(int id) {
        String sql = """
            SELECT *
            FROM llibre l
            INNER JOIN llibre_genere lg ON l.isbn = lg.isbn
            WHERE lg.id_genere = ?
            """;
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Llibre> findLatest(int n) {
        String sql = "SELECT * FROM llibre order by data_publicacio desc limit ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, n);
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Llibre> findMostRead(int n) {
        String sql = """
            SELECT l.*
            FROM llibre l
            INNER JOIN valoracio r ON l.isbn = r.isbn
            GROUP BY l.isbn
            ORDER BY COUNT(r.isbn) DESC, l.titol, l.isbn
            LIMIT ?
            """;
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, n);
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Llibre> findBestRated(int n) {
        String sql = """
            SELECT l.*
            FROM llibre l
            INNER JOIN valoracio v ON l.isbn = v.isbn
            GROUP BY l.isbn
            ORDER BY AVG(v.puntuacio) DESC, l.titol, l.isbn
            LIMIT ?
            """;
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, n);
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
