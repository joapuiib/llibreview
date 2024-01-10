package com.fpmislata.daw1.projectedaw1.persistance;

import com.fpmislata.daw1.projectedaw1.persistance.impl.Repository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class RepositoryTest {
    private static Repository repository;

    @BeforeAll
    static void setup() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("sql/schema/create_llibreview_schema.sql")
                .addScript("sql/data/insert_data_llibreview.sql")
                .build();
        repository.setDatasource(dataSource);
    }
}
