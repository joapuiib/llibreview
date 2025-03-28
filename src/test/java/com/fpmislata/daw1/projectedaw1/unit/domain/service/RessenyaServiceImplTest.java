package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.RessenyaData;
import com.fpmislata.daw1.projectedaw1.data.UsuariData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.RessenyaServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RessenyaRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RessenyaServiceImplTest {
    @Mock
    private RessenyaRepository ressenyaRepository;

    @InjectMocks
    private RessenyaServiceImpl ressenyaService;

    private final List<Llibre> LLIBRE_LIST = LlibreData.LLIBRE_LIST;
    private final List<Usuari> USUARI_LIST = UsuariData.USUARI_LIST;
    private final List<Ressenya> RESSENYA_LIST = RessenyaData.RESSENYA_LIST;


    @Nested
    class FindByLlibreAndUser {
        @Test
        void whenLlibreHasNoRessenyes_givenLlibreAndUser_shouldReturnNull() {
            Llibre llibre = LLIBRE_LIST.getFirst();
            Usuari usuari = USUARI_LIST.get(2);

            String isbn = llibre.getIsbn();
            String username = usuari.getUsername();
            when(ressenyaRepository.findByLlibreIsbnAndUsername(isbn, username)).thenReturn(null);

            Ressenya result = ressenyaService.findByLlibreAndUsuari(llibre, usuari);
            assertNull(result);
        }

        @Test
        void whenLlibreRessenyes_givenLlibreAndUser_shouldReturnRessenya() {
            Llibre llibre = LLIBRE_LIST.getFirst();
            Usuari usuari = USUARI_LIST.getFirst();
            Ressenya expected = RESSENYA_LIST.getFirst();

            String isbn = llibre.getIsbn();
            String username = usuari.getUsername();
            when(ressenyaRepository.findByLlibreIsbnAndUsername(isbn, username)).thenReturn(expected);

            Ressenya result = ressenyaService.findByLlibreAndUsuari(llibre, usuari);
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindByLlibre {
        @Test
        void whenLlibreHasNoRessenyes_shouldReturnEmptyList() {
            Llibre llibre = LLIBRE_LIST.get(1);

            String isbn = llibre.getIsbn();
            when(ressenyaRepository.findByLlibreIsbn(isbn)).thenReturn(List.of());

            List<Ressenya> result = ressenyaService.findByLlibre(llibre);
            assertTrue(result.isEmpty());
        }

        @Test
        void whenLlibreHasRessenyes_shouldReturnRessenyes() {
            Llibre llibre = LLIBRE_LIST.getFirst();
            List<Ressenya> expected = List.of(RESSENYA_LIST.getFirst());

            String isbn = llibre.getIsbn();
            when(ressenyaRepository.findByLlibreIsbn(isbn)).thenReturn(expected);

            List<Ressenya> result = ressenyaService.findByLlibre(llibre);
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindByUsuari {
        @Test
        void whenUsuariHasNoRessenyes_shouldReturnEmptyList() {
            Usuari usuari = USUARI_LIST.get(1);
            List<Ressenya> expected = List.of();

            String username = usuari.getUsername();
            when(ressenyaRepository.findByUsername(username)).thenReturn(expected);

            List<Ressenya> result = ressenyaService.findByUsuari(usuari);
            assertTrue(result.isEmpty());
        }

        @Test
        void whenUsuariHasRessenyes_shouldReturnRessenyes() {
            Usuari usuari = USUARI_LIST.getFirst();
            List<Ressenya> expected = RESSENYA_LIST;

            String username = usuari.getUsername();
            when(ressenyaRepository.findByUsername(username)).thenReturn(expected);

            List<Ressenya> result = ressenyaService.findByUsuari(usuari);
            assertEquals(expected, result);
        }
    }

    @Nested
    class SaveTests {
        @Test
        void givenNewRessenya_shouldSaveRessenya() {
            Ressenya ressenya = RESSENYA_LIST.getFirst();

            ressenyaService.save(ressenya);
            verify(ressenyaRepository).save(ressenya);
        }

    }

    @Nested
    class DeleteTests {
        @Test
        void givenExistingRessenya_shouldDeleteRessenya() {
            Ressenya ressenya = RESSENYA_LIST.getFirst();

            when(ressenyaRepository.delete(ressenya)).thenReturn(true);

            boolean result = ressenyaService.delete(ressenya);
            assertAll(
                    () -> assertTrue(result),
                    () -> verify(ressenyaRepository).delete(ressenya)
            );
        }

        @Test
        void givenNonExistingRessenya_shouldNotDeleteRessenya() {
            Ressenya ressenya = RESSENYA_LIST.getFirst();

            when(ressenyaRepository.delete(ressenya)).thenReturn(false);

            boolean result = ressenyaService.delete(ressenya);
            assertAll(
                    () -> assertFalse(result),
                    () -> verify(ressenyaRepository).delete(ressenya)
            );
        }
    }
}