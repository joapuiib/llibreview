package com.fpmislata.daw1.projectedaw1.unit.persistance.dao.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.mock.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.LlibreDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.database.DatabaseConnection;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LlibreDaoJdbcTest {
    private final LlibreDao llibreDao = new LlibreDaoJdbc();
    private final DatabaseConnection connection = DatabaseConnection.getInstance();

    private final List<Llibre> expectedLlibreList = LlibreData.llibreList;

    @BeforeAll
    static void setup() throws SQLException {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.executeScript("schema.sql");
        databaseConnection.executeScript("data.sql");
        databaseConnection.getConnection().setAutoCommit(false);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.getConnection().rollback();
    }

    @Test
    void findAll_shouldReturnAllLlibres() {
        List<Llibre> result = llibreDao.findAll();
        assertEquals(expectedLlibreList, result);
    }

    @Test
    void findByIsbn_shouldReturnLlibre() {
        Llibre result = llibreDao.findByIsbn("1");
        assertEquals(expectedLlibreList.get(0), result);
    }

    @Test
    public void findLatest_1_shouldReturnLatestBook() {
        List<Llibre> result = llibreDao.findLatest(1);
        assertAll(
            () -> assertEquals(1, result.size()),
            () -> assertEquals(expectedLlibreList.get(5), result.get(0))
        );
    }

    @Test
    public void findLatest_3_shouldReturnThreeLatestBooks() {
        List<Llibre> result = llibreDao.findLatest(3);
        assertAll(
                () -> assertEquals(3, result.size()),
                () -> assertEquals(expectedLlibreList.get(5), result.get(0)),
                () -> assertEquals(expectedLlibreList.get(4), result.get(1)),
                () -> assertEquals(expectedLlibreList.get(3), result.get(2))
        );
    }

    @Disabled("llibreDao::insert no s'ha implementat encara")
    @Test
    void insert_shouldInsertLlibre() {
        Llibre llibre = new Llibre("7", "Llibre 7", "Resum 7", LocalDate.parse("2023-09-02"), 111, "imatge7.png");
        boolean inserted = llibreDao.insert(llibre);
        Llibre insertedLlibre = llibreDao.findByIsbn("7");

        assertAll(
            () -> assertTrue(inserted),
            () -> assertEquals(llibre, insertedLlibre),
            () -> assertEquals(7, llibreDao.findAll().size())
        );
    }

    @Disabled("llibreDao::insert no s'ha implementat encara")
    @Test
    void insert_shouldThrowErrorIfLlibreExists() {
        Llibre llibre = new Llibre();
        llibre.setIsbn("1");

        assertAll(
                () -> assertThrows(RuntimeException.class, () -> llibreDao.insert(llibre)),
                () -> assertEquals(6, llibreDao.findAll().size())
        );
    }
}
