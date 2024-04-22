package com.fpmislata.daw1.projectedaw1.unit.persistance.dao.memory;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.AutorDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.EscriuDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.AutorTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.EscriuTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.EscriuRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EscriuDaoMemoryTest {
    @Mock
    private EscriuTableMemory escriuTableMemory;
    @Mock
    private LlibreTableMemory llibreTableMemory;
    @Mock
    private AutorTableMemory autorTableMemory;

    @InjectMocks
    private EscriuDaoMemory escriuDao;

    private final List<EscriuRecord> expectedEscriuRecordList = List.of(
            new EscriuRecord("isbn1", 1),
            new EscriuRecord("isbn1", 2),
            new EscriuRecord("isbn2", 1),
            new EscriuRecord("isbn3", 2)
    );
    private final List<AutorRecord> expectedAutorRecordList = List.of(
            new AutorRecord(1, "Autor 1", "Biografia 1", LocalDate.parse("2000-01-01"), "rutaImatge1"),
            new AutorRecord(2, "Autor 2", "Biografia 2", LocalDate.parse("2000-01-02"), "rutaImatge2"),
            new AutorRecord(3, "Autor 3", "Biografia 3", LocalDate.parse("2000-01-03"), "rutaImatge3")
    );

    private final List<LlibreRecord> expectedLlibreRecordList = List.of(
            new LlibreRecord("isbn1", "Titol 1", "Title 1", "Resum 1", "Summary 1", LocalDate.parse("2000-01-01"), 100, "rutaPortada1.jpg"),
            new LlibreRecord("isbn2", "Titol 2", "Title 2", "Resum 2", "Summary 2", LocalDate.parse("2000-01-02"), 200, "rutaPortada2.jpg"),
            new LlibreRecord("isbn3", "Titol 3", "Title 3", "Resum 3", "Summary 3", LocalDate.parse("2000-01-03"), 300, "rutaPortada3.jpg")
    );

    @Test
    void findAutorsByIsbn() {
        when(autorTableMemory.get()).thenReturn(expectedAutorRecordList);
        when(escriuTableMemory.get()).thenReturn(expectedEscriuRecordList);

        List<Autor> result = escriuDao.findAutorsByIsbn("isbn1");

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals(expectedAutorRecordList.get(0).getId(), result.get(0).getId()),
                () -> assertEquals(expectedAutorRecordList.get(1).getId(), result.get(1).getId())
        );
    }

    @Test
    void findLlibresByAutor() {
        when(llibreTableMemory.get()).thenReturn(expectedLlibreRecordList);
        when(escriuTableMemory.get()).thenReturn(expectedEscriuRecordList);

        List<Llibre> result = escriuDao.findLlibresByAutor(2);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals(expectedLlibreRecordList.get(0).getIsbn(), result.get(0).getIsbn()),
                () -> assertEquals(expectedLlibreRecordList.get(2).getIsbn(), result.get(1).getIsbn())
        );
    }
}