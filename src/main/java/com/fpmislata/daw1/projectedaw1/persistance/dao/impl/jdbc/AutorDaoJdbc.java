package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.rowmapper.AutorRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AutorDaoJdbc implements AutorDao {


    private final DatabaseConnection databaseConnection;
    private final AutorRowMapper autorRowMapper;

    public AutorDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        this.autorRowMapper = new AutorRowMapper();
    }

    @Override
    public Autor findById(int id) {
        String sql = "SELECT * FROM autor where id_autor = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<Autor> autorList = autorRowMapper.map(rs);
            return autorList.isEmpty() ? null : autorList.getFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Autor> findAll() {
        String sql = "SELECT * FROM autor";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            return autorRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Autor> findAutorsByLlibreIsbn(String isbn) {
        String sql = """
            SELECT *
            FROM autor a
            INNER JOIN escriu e on a.id_autor = e.id_autor
            WHERE e.isbn = ?
            """;
        try (
                PreparedStatement preparedStatement = databaseConnection
                        .prepareStatement(sql)
        ) {
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            return autorRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
