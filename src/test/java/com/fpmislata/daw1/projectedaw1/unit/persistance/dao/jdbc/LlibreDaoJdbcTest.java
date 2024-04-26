package com.fpmislata.daw1.projectedaw1.unit.persistance.dao.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.LlibreDaoJdbc;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LlibreDaoJdbcTest extends JdbcTest {
    private final LlibreDao llibreDao = new LlibreDaoJdbc();
    public final List<Llibre> expectedLlibreList = List.of(
            new Llibre( "1", "Llibre 1", "Resum 1", LocalDate.parse("2024-01-01"), 100, "imatge1.png" ),
            new Llibre( "2", "Llibre 2", "Resum 2", LocalDate.parse("2024-01-02"), 200, "imatge2.png" ),
            new Llibre( "3", "Llibre 3", "Resum 3", LocalDate.parse("2024-01-03"), 300, "imatge3.png" ),
            new Llibre( "4", "Llibre 4", "Resum 4", LocalDate.parse("2024-01-04"), 400, "imatge4.png" ),
            new Llibre( "5", "Llibre 5", "Resum 5", LocalDate.parse("2024-01-05"), 500, "imatge5.png" ),
            new Llibre( "6", "Llibre 6", "Resum 6", LocalDate.parse("2024-01-06"), 600, "imatge6.png" )
    );

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
    void findByDifferentIsbn_shouldReturnDifferentLlibre() {
        Llibre result = llibreDao.findByIsbn("2");
        assertEquals(expectedLlibreList.get(1), result);
    }

    @Test
    void findByNonExistingIsbn_shouldReturnNull() {
        Llibre result = llibreDao.findByIsbn("7");
        assertNull(result);
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
