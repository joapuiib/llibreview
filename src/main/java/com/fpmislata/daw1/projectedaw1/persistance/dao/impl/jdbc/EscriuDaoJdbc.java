package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper.AutorRowMapper;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper.LlibreRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EscriuDaoJdbc implements EscriuDao {
    private final DatabaseConnection databaseConnection;
    private final AutorRowMapper autorRowMapper;
    private final LlibreRowMapper llibreRowMapper;

    public EscriuDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        this.autorRowMapper = new AutorRowMapper();
        this.llibreRowMapper = new LlibreRowMapper();
    }

    @Override
    public List<Autor> findAutorsByLlibreIsbn(String isbn) {
        String sql = "SELECT * FROM autor a inner join escriu e on a.id_autor = e.id_autor where e.isbn = ?";
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            return autorRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Llibre> findLlibresByAutorId(int idAutor) {
        String sql = "SELECT * FROM llibre l inner join escriu e on l.isbn = e.isbn where e.id_autor = ?";
        try (PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idAutor);
            ResultSet rs = preparedStatement.executeQuery();
            return llibreRowMapper.map(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
