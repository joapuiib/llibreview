package com.fpmislata.daw1.projectedaw1.unit.persistance.dao.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.AutorMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutorMapperTest {
    private final AutorMapper autorMapper = new AutorMapper();

    @Test
    void map_shouldReturnAutor() {
        AutorRecord autorRecord = new AutorRecord(1, "Autor 1", "Biografia 1", LocalDate.parse("2000-01-01"), "rutaImatge1");
        Autor result = autorMapper.map(autorRecord);

        assertAll(
            () -> assertEquals(1, result.getId()),
            () -> assertEquals("Autor 1", result.getNom()),
            () -> assertEquals("Biografia 1", result.getBiografia()),
            () -> assertEquals(LocalDate.parse("2000-01-01"), result.getDataNaixement()),
            () -> assertEquals("rutaImatge1", result.getRutaImatge())
        );
    }

    @Test
    void map_shouldReturnNull() {
        Autor result = autorMapper.map((AutorRecord) null);
        assertNull(result);
    }

    @Test
    void mapAutorRecordList_shouldReturnAutorList(){
        List<AutorRecord> autorRecordList = List.of(
            new AutorRecord(1, "Autor 1", "Biografia 1", LocalDate.parse("2000-01-01"), "rutaImatge1"),
            new AutorRecord(2, "Autor 2", "Biografia 2", LocalDate.parse("2000-01-02"), "rutaImatge2"),
            new AutorRecord(3, "Autor 3", "Biografia 3", LocalDate.parse("2000-01-03"), "rutaImatge3")
        );

        List<Autor> result = autorMapper.map(autorRecordList);

        assertEquals(autorRecordList.size(), result.size());
        for (int i = 0; i < autorRecordList.size(); i++) {
            AutorRecord autorRecord = autorRecordList.get(i);
            Autor autor = result.get(i);

            assertAll(
                () -> assertEquals(autorRecord.getId(), autor.getId()),
                () -> assertEquals(autorRecord.getNom(), autor.getNom()),
                () -> assertEquals(autorRecord.getBiografia(), autor.getBiografia()),
                () -> assertEquals(autorRecord.getDataNaixement(), autor.getDataNaixement()),
                () -> assertEquals(autorRecord.getRutaImatge(), autor.getRutaImatge())
            );
        }
    }
}