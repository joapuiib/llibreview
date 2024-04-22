package com.fpmislata.daw1.projectedaw1.unit.persistance.dao.memory;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.AutorDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.AutorTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutorDaoMemoryTest {
    @Mock
    private AutorTableMemory autorTableMemory;

    @InjectMocks
    private AutorDaoMemory autorDao;

    private final List<AutorRecord> expectedAutorRecordList = List.of(
            new AutorRecord(1, "Autor 1", "Biografia 1", LocalDate.parse("2000-01-01"), "rutaImatge1"),
            new AutorRecord(2, "Autor 2", "Biografia 2", LocalDate.parse("2000-01-02"), "rutaImatge2"),
            new AutorRecord(3, "Autor 3", "Biografia 3", LocalDate.parse("2000-01-03"), "rutaImatge3")
    );

    @BeforeEach
    void setUp() {
        when(autorTableMemory.get()).thenReturn(expectedAutorRecordList);
    }

    @Test
    void findById_shouldReturnAutor() {
        var result = autorDao.findById(1);
        assertAll(
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("Autor 1", result.getNom()),
                () -> assertEquals("Biografia 1", result.getBiografia()),
                () -> assertEquals(LocalDate.parse("2000-01-01"), result.getDataNaixement()),
                () -> assertEquals("rutaImatge1", result.getRutaImatge())
        );
    }

    @Test
    void findByDifferentId_shouldReturnOtherAutor() {
        var result = autorDao.findById(2);

        assertAll(
                () -> assertEquals(2, result.getId()),
                () -> assertEquals("Autor 2", result.getNom()),
                () -> assertEquals("Biografia 2", result.getBiografia()),
                () -> assertEquals(LocalDate.parse("2000-01-02"), result.getDataNaixement()),
                () -> assertEquals("rutaImatge2", result.getRutaImatge())
        );
    }

    @Test
    void findAll_shouldReturnAllAutors() {
        var result = autorDao.findAll();
        assertEquals(expectedAutorRecordList.size(), result.size());
        for (int i = 0; i < expectedAutorRecordList.size(); i++) {
            var expectedAutorRecord = expectedAutorRecordList.get(i);
            var autor = result.get(i);
            assertAll(
                    () -> assertEquals(expectedAutorRecord.getId(), autor.getId()),
                    () -> assertEquals(expectedAutorRecord.getNom(), autor.getNom()),
                    () -> assertEquals(expectedAutorRecord.getBiografia(), autor.getBiografia()),
                    () -> assertEquals(expectedAutorRecord.getDataNaixement(), autor.getDataNaixement()),
                    () -> assertEquals(expectedAutorRecord.getRutaImatge(), autor.getRutaImatge())
            );
        }
    }
}