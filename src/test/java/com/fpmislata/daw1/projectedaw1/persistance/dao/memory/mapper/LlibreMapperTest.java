package com.fpmislata.daw1.projectedaw1.persistance.dao.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.LlibreMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LlibreMapperTest {
    private final LlibreMapper llibreMapper = new LlibreMapper();

    @Test
    void map_shouldReturnLlibre() {
        LlibreRecord llibreRecord = new LlibreRecord("isbn1", "Titol 1", "Title 1", "Resum 1", "Summary 1", LocalDate.parse("2000-01-01"), 100, "rutaImatge1");
        Llibre result = llibreMapper.map(llibreRecord);

        assertAll(
                () -> assertEquals("isbn1", result.getIsbn()),
                () -> assertEquals("Titol 1", result.getTitol()),
                () -> assertEquals("Resum 1", result.getResum()),
                () -> assertEquals(LocalDate.parse("2000-01-01"), result.getDataPublicacio()),
                () -> assertEquals(100, result.getNombrePagines()),
                () -> assertEquals("rutaImatge1", result.getRutaImatge())
        );
    }

    @Test
    void map_shouldReturnNull() {
        Llibre result = llibreMapper.map((LlibreRecord) null);
        assertNull(result);
    }

    @Test
    void mapLlibreRecordList_shouldReturnLlibreList() {
        List<LlibreRecord> llibreRecordList = List.of(
                new LlibreRecord("isbn1", "Titol 1", "Title 1", "Resum 1", "Summary 1", LocalDate.parse("2000-01-01"), 100, "rutaImatge1"),
                new LlibreRecord("isbn2", "Titol 2", "Title 2", "Resum 2", "Summary 2", LocalDate.parse("2000-01-02"), 200, "rutaImatge2"),
                new LlibreRecord("isbn3", "Titol 3", "Title 3", "Resum 3", "Summary 3", LocalDate.parse("2000-01-03"), 300, "rutaImatge3")
        );

        List<Llibre> result = llibreMapper.map(llibreRecordList);

        assertEquals(llibreRecordList.size(), result.size());
        for (int i = 0; i < llibreRecordList.size(); i++) {
            LlibreRecord llibreRecord = llibreRecordList.get(i);
            Llibre llibre = result.get(i);

            assertAll(
                    () -> assertEquals(llibreRecord.getIsbn(), llibre.getIsbn()),
                    () -> assertEquals(llibreRecord.getTitol_ca(), llibre.getTitol()),
                    () -> assertEquals(llibreRecord.getResum_ca(), llibre.getResum()),
                    () -> assertEquals(llibreRecord.getDataPublicacio(), llibre.getDataPublicacio()),
                    () -> assertEquals(llibreRecord.getNombrePagines(), llibre.getNombrePagines()),
                    () -> assertEquals(llibreRecord.getRutaImatge(), llibre.getRutaImatge())
            );
        }
    }
}