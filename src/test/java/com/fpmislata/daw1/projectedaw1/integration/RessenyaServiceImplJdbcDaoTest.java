package com.fpmislata.daw1.projectedaw1.integration;

import com.fpmislata.daw1.projectedaw1.data.RessenyaData;
import com.fpmislata.daw1.projectedaw1.data.ValoracioData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.RessenyaService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.RessenyaServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.RessenyaDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.RessenyaRepositoryImpl;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class RessenyaServiceImplJdbcDaoTest extends JdbcTest {
    private final RessenyaService ressenyaService =  new RessenyaServiceImpl(
            new RessenyaRepositoryImpl(
                    new RessenyaDaoJdbc()
            )
    );

    private final List<Valoracio> VALORACIO_LIST = ValoracioData.VALORACIO_LIST;
    private final List<Ressenya> RESSENYA_LIST = RessenyaData.RESSENYA_LIST;

    @Nested
    class FindByValoracio {
        @Test
        void whenValoracioHasNoRessenya_givenValoracio_shouldReturnNull() {
            Valoracio valoracio = VALORACIO_LIST.get(2);

            Ressenya result = ressenyaService.findByValoracio(valoracio);
            assertNull(result);
        }

        @Test
        void whenValoracioHasRessenya_givenValoracio_shouldReturnRessenya() {
            Valoracio valoracio = VALORACIO_LIST.getFirst();

            Ressenya expected = RESSENYA_LIST.getFirst();
            Ressenya result = ressenyaService.findByValoracio(valoracio);

            assertEquals(expected, result);
        }
    }

    @Nested
    class Save {
        @Test
        void whenRessenyaExists_givenRessenya_shouldUpdateRessenya() {
            Valoracio valoracio = VALORACIO_LIST.getFirst();
            Ressenya ressenya = RESSENYA_LIST.getFirst().clone();
            ressenya.setComentari("Nou contingut de la ressenya");

            ressenyaService.save(ressenya);
            Ressenya result = ressenyaService.findByValoracio(valoracio);

            assertEquals(ressenya, result);
        }

        @Test
        void whenRessenyaNotExists_givenRessenya_shouldInsertRessenya() {
            Valoracio valoracio = VALORACIO_LIST.get(2);
            String isbn = valoracio.getIsbn();
            String username = valoracio.getUsername();
            Ressenya ressenya = new Ressenya(isbn, username, "Contingut de la ressenya", LocalDate.parse("2021-05-01"));

            Ressenya prev = ressenyaService.findByValoracio(valoracio);
            ressenyaService.save(ressenya);
            Ressenya after = ressenyaService.findByValoracio(valoracio);

            assertNull(prev);
            assertEquals(ressenya, after);
        }
    }

    @Nested
    class Delete {
        @Test
        void whenRessenyaExists_givenIsbnAndUsername_shouldDeleteRessenya() {
            Valoracio valoracio = VALORACIO_LIST.getFirst();
            Ressenya ressenya = RESSENYA_LIST.getFirst();

            ressenyaService.delete(ressenya.getIsbn(), ressenya.getUsername());
            Ressenya result = ressenyaService.findByValoracio(valoracio);

            assertNull(result);
        }

        @Test
        void whenRessenyaNotExists_givenIsbnAndUsername_shouldNotDeleteRessenya() {
            Valoracio valoracio = VALORACIO_LIST.get(2);

            ressenyaService.delete(valoracio.getIsbn(), valoracio.getUsername());
            Ressenya result = ressenyaService.findByValoracio(valoracio);

            assertNull(result);
        }
    }
}