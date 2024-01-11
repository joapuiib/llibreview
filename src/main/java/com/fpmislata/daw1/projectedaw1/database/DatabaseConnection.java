package com.fpmislata.daw1.projectedaw1.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

public class DatabaseConnection {
    @Value("${db.host}")
    public String DB_HOST;

    @Value("${db.name}")
    public String DB_NAME;

    @Value("${db.user}")
    public String DB_USER;

    @Value("${db.passwd}")
    public String DB_PASSWD;

    protected JdbcTemplate jdbcTemplate;
    public DatabaseConnection() {
        this.jdbcTemplate = new JdbcTemplate(defaultDatasource());
    }
    public void setDatasource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate(){
        return jdbcTemplate;
    }

    public DataSource defaultDatasource(){
        System.out.println("DBHOST: " + DB_HOST);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + DB_HOST + "/" + DB_NAME);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWD);
        return dataSource;
    }
}
