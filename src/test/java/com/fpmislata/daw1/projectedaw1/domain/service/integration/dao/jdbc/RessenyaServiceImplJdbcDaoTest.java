package com.fpmislata.daw1.projectedaw1.domain.service.integration.dao.jdbc;

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
    class FindByLlibreAndUser {
        @Test
        void whenValoracioHasNoRessenya_givenValoracio_shouldReturnNull() {
            Valoracio valoracio = VALORACIO_LIST.get(2);

            Ressenya result = ressenyaService.findByValoracio(valoracio);
            assertNull(result);
        }

        @Test
        void whenValoracioHasRessenya_givenValoracio_shouldReturnRessenya() {
            Valoracio valoracio = VALORACIO_LIST.get(0);

            Ressenya expected = RESSENYA_LIST.get(0);
            Ressenya result = ressenyaService.findByValoracio(valoracio);

            assertEquals(expected, result);
        }
    }

}