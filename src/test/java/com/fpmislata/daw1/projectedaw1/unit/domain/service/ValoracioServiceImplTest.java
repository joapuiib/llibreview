package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.RessenyaData;
import com.fpmislata.daw1.projectedaw1.data.UsuariData;
import com.fpmislata.daw1.projectedaw1.data.ValoracioData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.ValoracioServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RessenyaRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.ValoracioRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValoracioServiceImplTest {
    @Mock
    private ValoracioRepository valoracioRepository;

    @Mock
    private RessenyaRepository ressenyaRepository;

    @InjectMocks
    private ValoracioServiceImpl valoracioService;

    private final List<Valoracio> valoracioList = ValoracioData.VALORACIO_LIST;
    private final List<Ressenya> ressenyaList = RessenyaData.RESSENYA_LIST;
    private final List<Llibre> llibreList = LlibreData.LLIBRE_LIST;
    private final List<Usuari> usuariList = UsuariData.USUARI_LIST;

    @Nested
    class FindByLlibreAndUsuari {
        @Test
        void givenNoValoracio_thenReturnNull() {
            Llibre llibre = llibreList.getFirst();
            Usuari usuari = usuariList.getFirst();

            when(valoracioRepository.findByLlibreIsbnAndUsername(
                    llibre.getIsbn(),
                    usuari.getUsername()
            )).thenReturn(null);

            Valoracio result = valoracioService.findByLlibreAndUser(llibre, usuari);

            assertNull(result);
        }

        @Test
        void givenValoracioWithNoRessenya_thenReturnValoracioWithNoRessenya() {
            Llibre llibre = llibreList.getFirst();
            Usuari usuari = usuariList.getFirst();
            Valoracio expected = valoracioList.getFirst();

            when(valoracioRepository.findByLlibreIsbnAndUsername(
                    llibre.getIsbn(),
                    usuari.getUsername()
            )).thenReturn(expected);
            when(ressenyaRepository.findByLlibreIsbnAndUsername(
                    llibre.getIsbn(),
                    usuari.getUsername()
            )).thenReturn(null);

            Valoracio result = valoracioService.findByLlibreAndUser(llibre, usuari);

            assertAll(
                    () -> assertEquals(expected, result),
                    () -> assertNull(result.getRessenya())
            );
        }

        @Test
        void givenValoracioWithRessenya_thenReturnValoracioWithRessenya() {
            Llibre llibre = llibreList.getFirst();
            Usuari usuari = usuariList.getFirst();
            Valoracio expected = valoracioList.getFirst();
            Ressenya expectedRessenya = ressenyaList.getFirst();

            when(valoracioRepository.findByLlibreIsbnAndUsername(
                    llibre.getIsbn(),
                    usuari.getUsername()
            )).thenReturn(expected);
            when(ressenyaRepository.findByLlibreIsbnAndUsername(
                    llibre.getIsbn(),
                    usuari.getUsername()
            )).thenReturn(expectedRessenya);

            Valoracio result = valoracioService.findByLlibreAndUser(llibre, usuari);

            assertAll(
                    () -> assertEquals(expected, result),
                    () -> assertEquals(expectedRessenya, result.getRessenya())
            );
        }
    }

    @Nested
    class FindByLlibre {
        @Test
        void givenBookWithNoValoracios_thenReturnEmptyList() {
            Llibre llibre = llibreList.get(2);
            List<Valoracio> expected = List.of();
            when(valoracioRepository.findByIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Valoracio> result = valoracioService.findByLlibre(llibre);

            assertEquals(expected, result);
        }

        @Test
        void givenBookWithSingleValoracios_thenReturnValoracioList() {
            Llibre llibre = llibreList.getFirst();
            List<Valoracio> expected = List.of(valoracioList.getFirst());
            when(valoracioRepository.findByIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Valoracio> result = valoracioService.findByLlibre(llibre);

            assertEquals(expected, result);
        }

        @Test
        void givenBookWithMultipleValoracios_thenReturnValoracioList() {
            Llibre llibre = llibreList.get(1);
            List<Valoracio> expected = List.of(valoracioList.get(1), valoracioList.get(2));
            when(valoracioRepository.findByIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Valoracio> result = valoracioService.findByLlibre(llibre);

            assertEquals(expected, result);
        }
    }

    @Nested
    class FindByUsuari {
        @Test
        void givenUserWithNoValoracios_thenReturnEmptyList() {
            Usuari usuari = usuariList.get(2);
            List<Valoracio> expected = List.of();
            when(valoracioRepository.findByUsername(usuari.getUsername())).thenReturn(expected);

            List<Valoracio> result = valoracioService.findByUser(usuari);

            assertEquals(expected, result);
        }

        @Test
        void givenUserWithSingleValoracios_thenReturnValoracioList() {
            Usuari usuari = usuariList.getFirst();
            List<Valoracio> expected = List.of(valoracioList.getFirst());
            when(valoracioRepository.findByUsername(usuari.getUsername())).thenReturn(expected);

            List<Valoracio> result = valoracioService.findByUser(usuari);

            assertEquals(expected, result);
        }

        @Test
        void givenUserWithMultipleValoracios_thenReturnValoracioList() {
            Usuari usuari = usuariList.get(1);
            List<Valoracio> expected = List.of(valoracioList.get(1), valoracioList.get(2));
            when(valoracioRepository.findByUsername(usuari.getUsername())).thenReturn(expected);

            List<Valoracio> result = valoracioService.findByUser(usuari);

            assertEquals(expected, result);
        }
    }
}