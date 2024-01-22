package com.fpmislata.daw1.projectedaw1.unit.persistance.dao;

import com.fpmislata.daw1.projectedaw1.common.factory.LlibreFactory;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.database.DatabaseConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LlibreDaoTest {
    private LlibreDao llibreDao = LlibreFactory.createLlibreDao();

    @BeforeAll
    static void setup() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.executeScript("classpath:schema.sql");
        databaseConnection.executeScript("classpath:data.sql");
    }

    @Test
    void findAll_shouldReturnAllLlibres() {
        List<Llibre> llibres = llibreDao.findAll();
        assertEquals(6, llibres.size());
    }
}
