package com.fpmislata.daw1.projectedaw1.persistance;

import com.fpmislata.daw1.projectedaw1.persistance.impl.Repository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.SQLException;

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
