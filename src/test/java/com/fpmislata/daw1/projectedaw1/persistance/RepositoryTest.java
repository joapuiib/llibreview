package com.fpmislata.daw1.projectedaw1.persistance;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class RepositoryTest {
    private static DataSource testDataSource;

    public static DataSource getTestDatasource(){
        if (testDataSource == null){
            testDataSource = new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .setName("testdb;MODE=MYSQL")
                    .addScript("sql/schema/create_llibreview_schema.sql")
                    .addScript("sql/data/insert_data_test.sql")
                    .build();

        }
        return testDataSource;
    }
}
