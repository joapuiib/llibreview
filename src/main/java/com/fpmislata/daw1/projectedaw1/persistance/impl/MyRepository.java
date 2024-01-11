package com.fpmislata.daw1.projectedaw1.persistance.impl;

import com.fpmislata.daw1.projectedaw1.database.DatabaseConnection;
import javax.sql.DataSource;

public class MyRepository {

    protected DatabaseConnection connection;
    public MyRepository() {
    }
    public void setDatasource(DataSource dataSource) {
        this.connection.setDatasource(dataSource);
    }
}
