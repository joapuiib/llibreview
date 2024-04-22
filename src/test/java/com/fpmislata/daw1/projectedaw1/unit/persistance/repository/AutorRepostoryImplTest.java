package com.fpmislata.daw1.projectedaw1.unit.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.AutorRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutorRepostoryImplTest {

    @Mock
    private AutorDao autorDao;
    @Mock
    private EscriuDao escriuDao;

    @InjectMocks
    private AutorRepositoryImpl autorRepository;

    private final List<Autor> expectedAutorList = List.of(
            new Autor(1, "Autor 1", "Biografia 1", LocalDate.parse("2000-01-01"), "rutaImatge1"),
            new Autor(2, "Autor 2", "Biografia 2", LocalDate.parse("2000-01-02"), "rutaImatge2"),
            new Autor(3, "Autor 3", "Biografia 3", LocalDate.parse("2000-01-03"), "rutaImatge3")
    );

    @Test
    public void findAll_shouldReturnAllLlibres() {
        when(autorDao.findAll()).thenReturn(expectedAutorList);

        List<Autor> result = autorRepository.findAll();
        assertEquals(expectedAutorList, result);
    }

    @Test
    public void findById_shouldReturnAutor() {
        Autor expectedAutor = expectedAutorList.get(0);
        when(autorDao.findById(expectedAutor.getId())).thenReturn(expectedAutor);

        Autor result = autorRepository.findById(expectedAutor.getId());
        assertEquals(expectedAutor, result);
    }

    @Test
    public void findByIsbn_shouldReturnAutors() {
        String isbn = "isbn";
        List<Autor> expectedAutorList = this.expectedAutorList.subList(0, 2);
        when(escriuDao.findAutorsByIsbn(isbn)).thenReturn(expectedAutorList);

        List<Autor> result = autorRepository.findByIsbn(isbn);
        assertEquals(expectedAutorList, result);
    }
}