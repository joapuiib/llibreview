package com.fpmislata.daw1.projectedaw1.unit.persistance.dao;

import com.fpmislata.daw1.projectedaw1.common.factory.LlibreFactory;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LlibreDaoTest {
    private final LlibreDao llibreDao = LlibreFactory.createLlibreDao();

    private final Llibre cantoJo = new Llibre("1", "Canto jo i la muntanya balla", "Resum 1", LocalDate.parse("2019-05-08"), 162);
    private final Llibre unAmor = new Llibre("2", "Un amor", "Resum 2", LocalDate.parse("2020-09-02"), 192);
    private final Llibre laFamilia = new Llibre("3", "La familia", "Resum 3", LocalDate.parse("2022-09-14"), 232);

    @BeforeAll
    static void setup() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.executeScript("schema.sql");
        databaseConnection.executeScript("data.sql");
    }

    @Test
    void findAll_shouldReturnAllLlibres() {
        List<Llibre> llibres = llibreDao.findAll();
        assertEquals(6, llibres.size());
    }

    @Test
    void findByIsbn_shouldReturnLlibre() {
        Llibre llibre = llibreDao.findByIsbn("1");
        assertEquals(cantoJo, llibre);
        /*
        assertAll(
                () -> assertEquals("1", llibre.getIsbn()),
                () -> assertEquals("Canto jo i la muntanya balla", llibre.getTitol()),
                () -> assertEquals("Resum 1", llibre.getResum()),
                () -> assertEquals("2019-05-08", llibre.getDataPublicacio().toString()),
                () -> assertEquals(162, llibre.getNombrePagines())
        );
        */
    }

    @Test
    public void findLatest_1_shouldReturnLatestBook() {
        List<Llibre> llibres = llibreDao.findLatest(1);
        assertAll(
            () -> assertEquals(1, llibres.size()),
            () -> assertEquals(laFamilia, llibres.get(0))

            /*
            () -> assertEquals("3", llibres.get(0).getIsbn()),
            () -> assertEquals("La familia", llibres.get(0).getTitol()),
            () -> assertEquals("Resum 3", llibres.get(0).getResum()),
            () -> assertEquals("2022-09-14", llibres.get(0).getDataPublicacio().toString()),
            () -> assertEquals(232, llibres.get(0).getNombrePagines())
            */
        );
    }

    @Test
    public void findLatest_3_shouldReturnThreeLatestBooks() {
        List<Llibre> llibres = llibreDao.findLatest(3);
        assertAll(
                () -> assertEquals(3, llibres.size()),
                () -> assertEquals(laFamilia, llibres.get(0)),
                () -> assertEquals(unAmor, llibres.get(1)),
                () -> assertEquals(cantoJo, llibres.get(2))

                /*
                () -> assertEquals("3", llibres.get(0).getIsbn()),
                () -> assertEquals("La familia", llibres.get(0).getTitol()),
                () -> assertEquals("Resum 3", llibres.get(0).getResum()),
                () -> assertEquals("2022-09-14", llibres.get(0).getDataPublicacio().toString()),
                () -> assertEquals(232, llibres.get(0).getNombrePagines()),

                () -> assertEquals("2", llibres.get(1).getIsbn()),
                () -> assertEquals("Un amor", llibres.get(1).getTitol()),
                () -> assertEquals("Resum 2", llibres.get(1).getResum()),
                () -> assertEquals("2020-09-02", llibres.get(1).getDataPublicacio().toString()),
                () -> assertEquals(192, llibres.get(1).getNombrePagines()),

                () -> assertEquals("1", llibres.get(2).getIsbn()),
                () -> assertEquals("Canto jo i la muntanya balla", llibres.get(2).getTitol()),
                () -> assertEquals("Resum 1", llibres.get(2).getResum()),
                () -> assertEquals("2019-05-08", llibres.get(2).getDataPublicacio().toString()),
                () -> assertEquals(162, llibres.get(2).getNombrePagines())
                */
        );
    }
}
