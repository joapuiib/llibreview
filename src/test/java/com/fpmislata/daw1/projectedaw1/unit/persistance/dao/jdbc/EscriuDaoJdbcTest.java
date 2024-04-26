package com.fpmislata.daw1.projectedaw1.unit.persistance.dao.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.EscriuDaoJdbc;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EscriuDaoJdbcTest extends JdbcTest {
    private final EscriuDao escriuDao = new EscriuDaoJdbc();
    public final List<Autor> expectedAutorList = List.of(
            new Autor(1, "Autor 1", "Biografia 1", LocalDate.parse("2000-01-01"), "imatge1.png"),
            new Autor(2, "Autor 2", "Biografia 2", LocalDate.parse("2000-01-02"), "imatge2.png"),
            new Autor(3, "Autor 3", "Biografia 3", LocalDate.parse("2000-01-03"), "imatge3.png")
    );

    public final List<Llibre> expectedLlibreList = List.of(
            new Llibre( "1", "Llibre 1", "Resum 1", LocalDate.parse("2024-01-01"), 100, "imatge1.png" ),
            new Llibre( "2", "Llibre 2", "Resum 2", LocalDate.parse("2024-01-02"), 200, "imatge2.png" ),
            new Llibre( "3", "Llibre 3", "Resum 3", LocalDate.parse("2024-01-03"), 300, "imatge3.png" ),
            new Llibre( "4", "Llibre 4", "Resum 4", LocalDate.parse("2024-01-04"), 400, "imatge4.png" ),
            new Llibre( "5", "Llibre 5", "Resum 5", LocalDate.parse("2024-01-05"), 500, "imatge5.png" ),
            new Llibre( "6", "Llibre 6", "Resum 6", LocalDate.parse("2024-01-06"), 600, "imatge6.png" )
    );

    @Test
    void givenBookWithNoAuthors_whenFindAuthorsByIsbn_thenEmptyList() {
        List<Autor> autorList = escriuDao.findAutorsByIsbn("1");
        assertEquals(0, autorList.size());
    }

    @Test
    void givenBookWithSingleAuthor_whenFindAuthorsByIsbn_thenListWithSingleAuthor() {
        List<Autor> autorList = escriuDao.findAutorsByIsbn("2");
        assertAll(
                () -> assertEquals(1, autorList.size()),
                () -> assertEquals(expectedAutorList.get(0), autorList.get(0))
        );
    }

    @Test
    void givenBookWithMultipleAuthors_whenFindAuthorsByIsbn_thenListWithMultipleAuthors() {
        List<Autor> autorList = escriuDao.findAutorsByIsbn("3");
        assertAll(
                () -> assertEquals(2, autorList.size()),
                () -> assertEquals(expectedAutorList.get(0), autorList.get(0)),
                () -> assertEquals(expectedAutorList.get(1), autorList.get(1))
        );
    }

    @Test
    void givenAuthorWithNoBooks_whenFindBooksByAuthor_thenEmptyList() {
        List<Llibre> autorList = escriuDao.findLlibresByAutor(3);
        assertEquals(0, autorList.size());
    }

    @Test
    void givenAuthorWithSingleBook_whenFindBooksByAuthor_thenListWithSingleBook() {
        List<Llibre> autorList = escriuDao.findLlibresByAutor(2);
        assertAll(
                () -> assertEquals(1, autorList.size()),
                () -> assertEquals(expectedLlibreList.get(2), autorList.get(0))
        );
    }

    @Test
    void givenAuthorWithMultipleBooks_whenFindBooksByAuthor_thenListWithMultipleBooks() {
        List<Llibre> autorList = escriuDao.findLlibresByAutor(1);
        assertAll(
                () -> assertEquals(2, autorList.size()),
                () -> assertEquals(expectedLlibreList.get(1), autorList.get(0)),
                () -> assertEquals(expectedLlibreList.get(2), autorList.get(1))
        );
    }

}
