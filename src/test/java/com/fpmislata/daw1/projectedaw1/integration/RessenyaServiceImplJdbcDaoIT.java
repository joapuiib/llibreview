package com.fpmislata.daw1.projectedaw1.integration;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.RessenyaData;
import com.fpmislata.daw1.projectedaw1.data.UsuariData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RessenyaServiceImplJdbcDaoIT extends JdbcTest {
    private final RessenyaService ressenyaService =  new RessenyaServiceImpl(
            new RessenyaRepositoryImpl(
                    new RessenyaDaoJdbc()
            )
    );

    private final List<Llibre> LLIBRE_LIST = LlibreData.LLIBRE_LIST;
    private final List<Usuari> USUARI_LIST = UsuariData.USUARI_LIST;
    private final List<Ressenya> RESSENYA_LIST = RessenyaData.RESSENYA_LIST;

    @Nested
    class FindByLlibreAndUser {
        @Test
        void whenLlibreHasNoValoracions_givenLlibreAndUser_shouldReturnNull() {
            Llibre llibre = LLIBRE_LIST.getFirst();
            Usuari usuari = USUARI_LIST.get(2);
            Ressenya result = ressenyaService.findByLlibreAndUsuari(llibre, usuari);
            assertNull(result);
        }

        @Test
        void whenLlibreValoracions_givenLlibreAndUser_shouldReturnValoracio() {
            Llibre llibre = LLIBRE_LIST.getFirst();
            Usuari usuari = USUARI_LIST.getFirst();
            Ressenya expected = RESSENYA_LIST.getFirst();
            Ressenya result = ressenyaService.findByLlibreAndUsuari(llibre, usuari);
            assertEquals(expected, result);
        }
    }

    @Nested
    class Save {
        @Test
        void whenRessenyaExists_givenRessenya_shouldUpdateRessenya() {
            Llibre llibre = LLIBRE_LIST.getFirst();
            Usuari usuari = USUARI_LIST.getFirst();
            Ressenya prevRessenya = RESSENYA_LIST.getFirst();
            Ressenya newRessenya = prevRessenya.clone();
            newRessenya.setComentari("Nou contingut de la ressenya");

            Ressenya prev = ressenyaService.findByLlibreAndUsuari(llibre, usuari);
            ressenyaService.save(newRessenya);
            Ressenya result = ressenyaService.findByLlibreAndUsuari(llibre, usuari);

            assertAll(
                    () -> assertEquals(prevRessenya, prev),
                    () -> assertEquals(newRessenya, result)
            );
        }

        @Test
        void whenRessenyaNotExists_givenRessenya_shouldInsertRessenya() {
            Llibre llibre = LLIBRE_LIST.get(1);
            Usuari usuari = USUARI_LIST.get(1);
            String isbn = llibre.getIsbn();
            String username = usuari.getUsername();
            Ressenya ressenya = new Ressenya(isbn, username, "Contingut de la ressenya", LocalDate.parse("2021-05-01"));

            Ressenya prev = ressenyaService.findByLlibreAndUsuari(llibre, usuari);
            ressenyaService.save(ressenya);
            Ressenya after = ressenyaService.findByLlibreAndUsuari(llibre, usuari);

            assertAll(
                    () -> assertNull(prev),
                    () -> assertEquals(ressenya, after)
            );
        }
    }

    @Nested
    class Delete {
        @Test
        void whenRessenyaExists_givenIsbnAndUsername_shouldDeleteRessenya() {
            Llibre llibre = LLIBRE_LIST.get(1);
            Usuari usuari = USUARI_LIST.get(1);
            Ressenya ressenya = RESSENYA_LIST.getFirst();

            ressenyaService.delete(ressenya);
            Ressenya result = ressenyaService.findByLlibreAndUsuari(llibre, usuari);

            assertNull(result);
        }

        @Test
        void whenRessenyaNotExists_givenIsbnAndUsername_shouldNotDeleteRessenya() {
            Ressenya ressenya = new Ressenya("isbn3", "user3", "Contingut de la ressenya", LocalDate.parse("2021-05-01"));
            boolean result = ressenyaService.delete(ressenya);
            assertFalse(result);
        }
    }
}