package com.fpmislata.daw1.projectedaw1.unit.persistance.dao.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.AutorDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AutorDaoJdbcTest {
    private final AutorDao autorDao = new AutorDaoJdbc();
    private static final DatabaseConnection connection = DatabaseConnection.getInstance();

    public final List<Autor> expectedAutorList = List.of(
            new Autor(1, "Autor 1", "Biografia 1", LocalDate.parse("2000-01-01"), "imatge1.png"),
            new Autor(2, "Autor 2", "Biografia 2", LocalDate.parse("2000-01-02"), "imatge2.png"),
            new Autor(3, "Autor 3", "Biografia 3", LocalDate.parse("2000-01-03"), "imatge3.png")
    );

    @BeforeAll
    static void setup() throws SQLException {
        connection.executeScript("schema.sql");
        connection.executeScript("data.sql");
        connection.getConnection().setAutoCommit(false);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.getConnection().rollback();
    }

    @Test
    void findAll_shouldReturnAllAutors() {
        List<Autor> result = autorDao.findAll();
        assertEquals(expectedAutorList, result);
    }

    @Test
    void findById_shouldReturnAutor() {
        Autor result = autorDao.findById(1);
        assertEquals(expectedAutorList.get(0), result);
    }

    @Test
    void findByDifferentId_shouldReturnDifferentAutor() {
        Autor result = autorDao.findById(2);
        assertEquals(expectedAutorList.get(1), result);
    }

    @Test
    void findByNonExistentId_shouldReturnNull() {
        Autor result = autorDao.findById(4);
        assertNull(result);
    }
}
