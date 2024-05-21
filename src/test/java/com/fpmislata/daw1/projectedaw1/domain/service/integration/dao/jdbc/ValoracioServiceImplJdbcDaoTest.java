package com.fpmislata.daw1.projectedaw1.domain.service.integration.dao.jdbc;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.UsuariData;
import com.fpmislata.daw1.projectedaw1.data.ValoracioData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.ValoracioService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.ValoracioServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.ValoracioDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.ValoracioRepositoryImpl;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ValoracioServiceImplJdbcDaoTest extends JdbcTest {
    private final ValoracioService valoracioService = new ValoracioServiceImpl(
            new ValoracioRepositoryImpl(
                    new ValoracioDaoJdbc()
            )
    );

    private final List<Llibre> LLIBRE_LIST = LlibreData.LLIBRE_LIST;
    private final List<Usuari> USUARI_LIST = UsuariData.USUARI_LIST;
    private final List<Valoracio> VALORACIO_LIST = ValoracioData.VALORACIO_LIST;

    @Nested
    class FindByLlibreAndUser {
        @Test
        void whenLlibreHasNoValoracions_givenLlibreAndUser_shouldReturnNull() {
            Llibre llibre = LLIBRE_LIST.get(0);
            Usuari usuari = USUARI_LIST.get(2);
            Valoracio result = valoracioService.findByLlibreAndUser(llibre, usuari);
            assertNull(result);
        }

        @Test
        void whenLlibreValoracions_givenLlibreAndUser_shouldReturnValoracio() {
            Llibre llibre = LLIBRE_LIST.get(0);
            Usuari usuari = USUARI_LIST.get(0);
            Valoracio expectedValoracio = VALORACIO_LIST.get(0);
            Valoracio result = valoracioService.findByLlibreAndUser(llibre, usuari);
            assertEquals(expectedValoracio, result);
        }
    }

    @Nested
    class FindByLlibre {
        @Test
        void whenLlibreHasNoValoracions_givenLlibre_shouldReturnEmptyList() {
            Llibre llibre = LLIBRE_LIST.get(2);
            List<Valoracio> expected = List.of();

            List<Valoracio> result = valoracioService.findByLlibre(llibre);

            assertEquals(expected, result);
        }

        @Test
        void whenLlibreSingleValoracio_givenLlibre_shouldReturnListWithSingleValoracio() {
            Llibre llibre = LLIBRE_LIST.get(0);
            List<Valoracio> expectedValoracions = List.of(VALORACIO_LIST.get(0));
            List<Valoracio> result = valoracioService.findByLlibre(llibre);
            assertEquals(expectedValoracions, result);
        }

        @Test
        void whenLlibreMultipleValoracions_givenLlibre_shouldReturnListWithMultipleValoracions() {
            Llibre llibre = LLIBRE_LIST.get(1);
            List<Valoracio> expectedValoracions = List.of(VALORACIO_LIST.get(1), VALORACIO_LIST.get(2));
            List<Valoracio> result = valoracioService.findByLlibre(llibre);
            assertEquals(expectedValoracions, result);
        }
    }

    @Nested
    class FindByUser {
        @Test
        void whenUserHasNoValoracions_givenUser_shouldReturnEmptyList() {
            Usuari usuari = USUARI_LIST.get(2);
            List<Valoracio> expected = List.of();

            List<Valoracio> result = valoracioService.findByUser(usuari);

            assertEquals(expected, result);
        }

        @Test
        void whenUserSingleValoracio_givenUser_shouldReturnListWithSingleValoracio() {
            Usuari usuari = USUARI_LIST.get(1);
            List<Valoracio> expectedValoracions = List.of(VALORACIO_LIST.get(2));
            List<Valoracio> result = valoracioService.findByUser(usuari);
            assertEquals(expectedValoracions, result);
        }

        @Test
        void whenUserMultipleValoracions_givenUser_shouldReturnListWithMultipleValoracions() {
            Usuari usuari = USUARI_LIST.get(0);
            List<Valoracio> expectedValoracions = List.of(VALORACIO_LIST.get(0), VALORACIO_LIST.get(1));
            List<Valoracio> result = valoracioService.findByUser(usuari);
            assertEquals(expectedValoracions, result);
        }
    }

    @Nested
    class Save {
        @Test
        void whenValoracioNotExists_givenValoracio_shouldSaveValoracio() {
            Llibre llibre = LLIBRE_LIST.get(0);
            Usuari usuari = USUARI_LIST.get(2);
            Valoracio valoracio = new Valoracio(llibre, usuari, 5, LocalDate.parse("2024-05-01"));
            valoracioService.save(valoracio);
            Valoracio result = valoracioService.findByLlibreAndUser(llibre, usuari);
            assertEquals(valoracio, result);
        }

        @Test
        void whenValoracioExists_givenValoracio_shouldUpdateValoracio() {
            Llibre llibre = LLIBRE_LIST.get(0);
            Usuari usuari = USUARI_LIST.get(0);
            Valoracio valoracio = new Valoracio(llibre, usuari, 5, LocalDate.parse("2024-05-01"));
            valoracioService.save(valoracio);
            Valoracio result = valoracioService.findByLlibreAndUser(llibre, usuari);
            assertEquals(valoracio, result);
        }
    }

    @Nested
    class Delete {
        @Test
        void whenValoracioExists_givenLlibreAndUser_shouldDeleteValoracio() {
            Llibre llibre = LLIBRE_LIST.get(0);
            Usuari usuari = USUARI_LIST.get(0);
            valoracioService.delete(llibre.getIsbn(), usuari.getUsername());
            Valoracio result = valoracioService.findByLlibreAndUser(llibre, usuari);
            assertNull(result);
        }

        @Test
        void whenValoracioNotExists_givenLlibreAndUser_shouldDoNothing() {
            Llibre llibre = LLIBRE_LIST.get(2);
            Usuari usuari = USUARI_LIST.get(2);
            valoracioService.delete(llibre.getIsbn(), usuari.getUsername());
            Valoracio result = valoracioService.findByLlibreAndUser(llibre, usuari);
            assertNull(result);
        }
    }
}