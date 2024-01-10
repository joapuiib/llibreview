package com.fpmislata.daw1.projectedaw1.persistance.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class Repository {

    @Value("${db.host}")
    public static String DB_HOST;

    @Value("${db.name}")
    public static String DB_NAME;

    @Value("${db.user}")
    public static String DB_USER;

    @Value("${db.passwd}")
    public static String DB_PASSWD;

    private static DataSource defaultDatasource;
    private static DataSource getDataSource(){
        if(defaultDatasource == null){
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://" + DB_HOST + "/" + DB_NAME);
            dataSource.setUsername(DB_USER);
            dataSource.setPassword(DB_PASSWD);
            defaultDatasource = dataSource;
        }
        return defaultDatasource;
    }

    protected JdbcTemplate jdbcTemplate;
    public Repository() {
        this.jdbcTemplate = new JdbcTemplate(getDataSource());
    }
    public void setDatasource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
