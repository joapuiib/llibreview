package com.fpmislata.daw1.projectedaw1.unit.persistance.dao.jdbc;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.AutorDaoJdbc;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AutorDaoJdbcTest extends JdbcTest {
    private final AutorDao autorDao = new AutorDaoJdbc();

    public final List<Autor> expectedAutorList = AutorData.AUTOR_LIST;

    @Nested
    class FindAll {
        @Test
        void findAll_shouldReturnAllAutors() {
            List<Autor> result = autorDao.findAll();
            assertEquals(expectedAutorList, result);
        }
    }

    @Nested
    class FindById {
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
}
