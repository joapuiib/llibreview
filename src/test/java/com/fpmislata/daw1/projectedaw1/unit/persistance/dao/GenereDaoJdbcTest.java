package com.fpmislata.daw1.projectedaw1.unit.persistance.dao;

import com.fpmislata.daw1.projectedaw1.common.i18n.Language;
import com.fpmislata.daw1.projectedaw1.data.GenereData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.GenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.GenereDaoJdbc;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.*;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

// https://stackoverflow.com/questions/46182118/junit5-parameterized-tests-at-class-level
public class GenereDaoJdbcTest extends JdbcTest {
    private final GenereDao genereDao = new GenereDaoJdbc();

    @Nested
    class EnglishLocale extends TestTemplate {
        public EnglishLocale() {

            super(Language.EN.getLocale(), GenereData.GENERE_LIST_EN);
        }
    }

    @Nested
    class CatalanLocale extends TestTemplate {
        public CatalanLocale() {
            super(Language.CA.getLocale(), GenereData.GENERE_LIST);
        }
    }

    abstract class TestTemplate {
        private final Locale locale;
        private final List<Genere> genereList;

        public TestTemplate(Locale locale, List<Genere> genereList) {
            this.locale = locale;
            this.genereList = genereList;
        }

        @BeforeEach
        void setup() {
            LocaleContextHolder.setLocale(locale);
        }

        @Nested
        class FindById {
            @Test
            void findByIsbn_shouldReturnGenere() {
                Genere result = genereDao.findById(1);
                assertEquals(genereList.getFirst(), result);
            }

            @Test
            void findByDifferentIsbn_shouldReturnDifferentGenere() {
                Genere result = genereDao.findById(2);
                assertEquals(genereList.get(1), result);
            }

            @Test
            void findByNonExistingIsbn_shouldReturnNull() {
                Genere result = genereDao.findById(-1);
                assertNull(result);
            }
        }

        @Nested
        class FindAll {
            @Test
            void findAll_shouldReturnAllGeneres() {
                List<Genere> result = genereDao.findAll();
                assertEquals(genereList, result);
            }
        }

        @Nested
        class FindGeneresByLlibreIsbn {
            @Test
            void givenLlibreWithNoGenere_shouldReturnEmptyList() {
                List<Genere> result = genereDao.findGeneresByLlibreIsbn("isbn0");
                assertEquals(0, result.size());
            }

            @Test
            void givenLlibreWithSingleGenere_shouldReturnListWithSingleGenere() {
                List<Genere> result = genereDao.findGeneresByLlibreIsbn("isbn2");
                assertAll(
                        () -> assertEquals(1, result.size()),
                        () -> assertEquals(genereList.getFirst(), result.getFirst())
                );
            }

            @Test
            void givenLlibreWithMultipleGeneres_shouldReturnListWithMultipleGeneres() {
                List<Genere> result = genereDao.findGeneresByLlibreIsbn("isbn1");
                assertAll(
                        () -> assertEquals(2, result.size()),
                        () -> assertEquals(genereList.getFirst(), result.getFirst()),
                        () -> assertEquals(genereList.get(1), result.get(1))
                );
            }
        }
    }
}
