package com.fpmislata.daw1.projectedaw1.unit.persistance.dao.memory;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.AutorDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.AutorTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutorDaoMemoryTest {
    @Mock
    private AutorTableMemory autorTableMemory;

    @InjectMocks
    private AutorDaoMemory autorDao;

    private final List<AutorRecord> autorRecordList = AutorData.autorRecordList;

    @BeforeEach
    void setUp() {
        when(autorTableMemory.get()).thenReturn(autorRecordList);
    }

    @Test
    void findById_shouldReturnAutor() {
        AutorRecord expectedAutorRecord = autorRecordList.get(0);
        Autor result = autorDao.findById(1);
        assertAll(
                () -> assertEquals(expectedAutorRecord.getId(), result.getId()),
                () -> assertEquals(expectedAutorRecord.getNom(), result.getNom()),
                () -> assertEquals(expectedAutorRecord.getBiografia(), result.getBiografia()),
                () -> assertEquals(expectedAutorRecord.getDataNaixement(), result.getDataNaixement()),
                () -> assertEquals(expectedAutorRecord.getRutaImatge(), result.getRutaImatge())
        );
    }

    @Test
    void findByDifferentId_shouldReturnOtherAutor() {
        AutorRecord expectedAutorRecord = autorRecordList.get(1);
        Autor result = autorDao.findById(2);

        assertAll(
                () -> assertEquals(expectedAutorRecord.getId(), result.getId()),
                () -> assertEquals(expectedAutorRecord.getNom(), result.getNom()),
                () -> assertEquals(expectedAutorRecord.getBiografia(), result.getBiografia()),
                () -> assertEquals(expectedAutorRecord.getDataNaixement(), result.getDataNaixement()),
                () -> assertEquals(expectedAutorRecord.getRutaImatge(), result.getRutaImatge())
        );
    }

    @Test
    void findAll_shouldReturnAllAutors() {
        List<Autor> result = autorDao.findAll();
        assertEquals(autorRecordList.size(), result.size());
        for (int i = 0; i < autorRecordList.size(); i++) {
            AutorRecord expectedAutorRecord = autorRecordList.get(i);
            Autor autor = result.get(i);
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