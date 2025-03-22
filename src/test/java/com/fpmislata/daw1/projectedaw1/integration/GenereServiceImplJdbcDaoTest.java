package com.fpmislata.daw1.projectedaw1.integration;

import com.fpmislata.daw1.projectedaw1.common.i18n.Language;
import com.fpmislata.daw1.projectedaw1.data.GenereData;
import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.GenereService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.GenereServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.GenereDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.GenereRepositoryImpl;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GenereServiceImplJdbcDaoTest extends JdbcTest {

    private final GenereService genereService = new GenereServiceImpl(
            new GenereRepositoryImpl(
                    new GenereDaoJdbc()
            )
    );

    private final List<Llibre> LLIBRE_LIST = LlibreData.LLIBRE_LIST;

    @Nested
    class FindAll {
        @ParameterizedTest
        @MethodSource("com.fpmislata.daw1.projectedaw1.util.LanguageUtils#languagesProvider")
        void givenAllGeneres_shouldReturnAllGeneres(Language language) {
            LocaleContextHolder.setLocale(language.getLocale());

            List<Genere> expectedGenereList = GenereData.getGenereList(language);
            List<Genere> result = genereService.findAll();
            assertEquals(expectedGenereList, result);
        }
    }

    @Nested
    class FindById {
        @ParameterizedTest
        @MethodSource("com.fpmislata.daw1.projectedaw1.util.LanguageUtils#languagesProvider")
        void givenGenereId_shouldReturnGenere(Language language) {
            LocaleContextHolder.setLocale(language.getLocale());
            List<Genere> genereListByLanguage = GenereData.getGenereList(language);

            Genere expectedGenere = genereListByLanguage.getFirst();
            Genere result = genereService.findById(1);
            assertEquals(expectedGenere, result);
        }

        @ParameterizedTest
        @MethodSource("com.fpmislata.daw1.projectedaw1.util.LanguageUtils#languagesProvider")
        void givenDifferentGenereId_shouldReturnDifferentGenere(Language language) {
            LocaleContextHolder.setLocale(language.getLocale());
            List<Genere> genereListByLanguage = GenereData.getGenereList(language);

            Genere expectedGenere = genereListByLanguage.get(1);
            Genere result = genereService.findById(2);
            assertEquals(expectedGenere, result);
        }

        @Test
        void givenNonExistentGenereId_shouldReturnNull() {
            Genere result = genereService.findById(-1);
            assertNull(result);
        }
    }

    @Nested
    class FindByLlibre {
        @Test
        void givenLlibreWithNoGeneres_shouldReturnEmptyList() {
            Llibre llibre = LLIBRE_LIST.get(2);
            List<Genere> result = genereService.findByLlibre(llibre);
            assertEquals(List.of(), result);
        }

        @ParameterizedTest
        @MethodSource("com.fpmislata.daw1.projectedaw1.util.LanguageUtils#languagesProvider")
        void givenLlibreWithSingleGenere_shouldReturnListSingleGenere(Language language) {
            LocaleContextHolder.setLocale(language.getLocale());
            List<Genere> genereListByLanguage = GenereData.getGenereList(language);

            Llibre llibre = LLIBRE_LIST.get(1);
            List<Genere> expectedGenereList = List.of(genereListByLanguage.getFirst());
            List<Genere> result = genereService.findByLlibre(llibre);
            assertEquals(expectedGenereList, result);
        }

        @ParameterizedTest
        @MethodSource("com.fpmislata.daw1.projectedaw1.util.LanguageUtils#languagesProvider")
        void givenLlibreWithMultipleGeneres_shouldReturnListMultipleGeneres(Language language) {
            LocaleContextHolder.setLocale(language.getLocale());
            List<Genere> genereListByLanguage = GenereData.getGenereList(language);

            Llibre llibre = LLIBRE_LIST.getFirst();
            List<Genere> expectedGenereList = List.of(
                    genereListByLanguage.get(0),
                    genereListByLanguage.get(1)
            );
            List<Genere> result = genereService.findByLlibre(llibre);
            assertEquals(expectedGenereList, result);
        }
    }
}