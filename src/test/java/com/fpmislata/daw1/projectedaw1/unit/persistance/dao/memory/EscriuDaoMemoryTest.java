package com.fpmislata.daw1.projectedaw1.unit.persistance.dao.memory;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.data.EscriuData;
import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.EscriuDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.AutorTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.EscriuTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.EscriuRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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
class EscriuDaoMemoryTest {
    @Mock
    private EscriuTableMemory escriuTableMemory;
    @Mock
    private LlibreTableMemory llibreTableMemory;
    @Mock
    private AutorTableMemory autorTableMemory;

    @InjectMocks
    private EscriuDaoMemory escriuDao;

    private final List<EscriuRecord> escriuRecordList = EscriuData.ESCRIU_RECORD_LIST;
    private final List<AutorRecord> autorRecordList = AutorData.AUTOR_RECORD_LIST;
    private final List<LlibreRecord> llibreRecordList = LlibreData.LLIBRE_RECORD_LIST;

    @Nested
    class FindAuthorsByIsbn {
        @BeforeEach
        void setup(){
            when(escriuTableMemory.get()).thenReturn(escriuRecordList);
            when(autorTableMemory.get()).thenReturn(autorRecordList);
        }
        @Test
        void givenBookWithNoAuthors_whenFindAuthorsByIsbn_thenEmptyList() {
            List<Autor> expectedAutorList = List.of();
            List<Autor> autorList = escriuDao.findAutorsByLlibreIsbn("isbn1");
            assertEquals(expectedAutorList, autorList);
        }

        @Test
        void givenBookWithSingleAuthor_whenFindAuthorsByIsbn_thenListWithSingleAuthor() {
            List<Autor> result = escriuDao.findAutorsByLlibreIsbn("isbn2");
            assertAll(
                    () -> assertEquals(1, result.size()),
                    () -> assertEquals(autorRecordList.get(0).getId(), result.get(0).getId()),
                    () -> assertEquals(autorRecordList.get(0).getNom(), result.get(0).getNom()),
                    () -> assertEquals(autorRecordList.get(0).getBiografia(), result.get(0).getBiografia()),
                    () -> assertEquals(autorRecordList.get(0).getDataNaixement(), result.get(0).getDataNaixement()),
                    () -> assertEquals(autorRecordList.get(0).getRutaImatge(), result.get(0).getRutaImatge())
            );
        }

        @Test
        void givenBookWithMultipleAuthors_whenFindAuthorsByIsbn_thenListWithMultipleAuthors() {
            List<Autor> result = escriuDao.findAutorsByLlibreIsbn("isbn3");
            assertAll(
                    () -> assertEquals(2, result.size()),
                    () -> assertEquals(autorRecordList.get(0).getId(), result.get(0).getId()),
                    () -> assertEquals(autorRecordList.get(0).getNom(), result.get(0).getNom()),
                    () -> assertEquals(autorRecordList.get(0).getBiografia(), result.get(0).getBiografia()),
                    () -> assertEquals(autorRecordList.get(0).getDataNaixement(), result.get(0).getDataNaixement()),
                    () -> assertEquals(autorRecordList.get(0).getRutaImatge(), result.get(0).getRutaImatge()),
                    () -> assertEquals(autorRecordList.get(1).getId(), result.get(1).getId()),
                    () -> assertEquals(autorRecordList.get(1).getNom(), result.get(1).getNom()),
                    () -> assertEquals(autorRecordList.get(1).getBiografia(), result.get(1).getBiografia()),
                    () -> assertEquals(autorRecordList.get(1).getDataNaixement(), result.get(1).getDataNaixement()),
                    () -> assertEquals(autorRecordList.get(1).getRutaImatge(), result.get(1).getRutaImatge())
            );
        }
    }

    @Nested
    class FindBooksByAuthor {
        @BeforeEach
        void setup(){
            when(escriuTableMemory.get()).thenReturn(escriuRecordList);
            when(llibreTableMemory.get()).thenReturn(llibreRecordList);
        }

        @Test
        void givenAuthorWithNoBooks_whenFindBooksByAuthor_thenEmptyList() {
            List<Llibre> expectedLlibreList = List.of();
            List<Llibre> result = escriuDao.findLlibresByAutorId(3);
            assertEquals(expectedLlibreList, result);
        }

        @Test
        void givenAuthorWithSingleBook_whenFindBooksByAuthor_thenListWithSingleBook() {
            List<Llibre> result = escriuDao.findLlibresByAutorId(2);
            assertAll(
                    () -> assertEquals(1, result.size()),
                    () -> assertEquals(llibreRecordList.get(2).getIsbn(), result.get(0).getIsbn())
            );
        }

        @Test
        void givenAuthorWithMultipleBooks_whenFindBooksByAuthor_thenListWithMultipleBooks() {
            List<Llibre> result = escriuDao.findLlibresByAutorId(1);
            assertAll(
                    () -> assertEquals(2, result.size()),
                    () -> assertEquals(llibreRecordList.get(1).getIsbn(), result.get(0).getIsbn()),
                    () -> assertEquals(llibreRecordList.get(2).getIsbn(), result.get(1).getIsbn())
            );
        }
    }
}