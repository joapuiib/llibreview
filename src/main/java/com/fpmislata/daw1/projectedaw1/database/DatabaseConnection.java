package com.fpmislata.daw1.projectedaw1.database;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnection {
    @Value("${spring.datasource.url}")
    public String dbUrl;

    @Value("${spring.datasource.username}")
    public String dbUser;

    @Value("${spring.datasource.password}")
    public String dbPassword;

    protected Connection connection;

    public Connection getConnection() {
        return connection;
    }

    @PostConstruct
    // Esta anotación sirve para asegurarnos que el método se ejecuta después de que las propiedades hayan sido inyectadas
    private void init() {
        System.out.println("Estableciendo conexión....");
        try {
            connection = DriverManager.getConnection(
                    dbUrl,
                    dbUser,
                    dbPassword
            );
            System.out.println("Conexión establecida con la bbdd con los parámetros: ");
            System.out.println(this.getParameters());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Connection paramaters :\n\n" + getParameters() + "\nOriginal exception message: " + e.getMessage());
        }
    }


    private String getParameters (){
        return String.format("url: %s\nUser: %s\nPassword: %s\n",
                dbUrl,
                dbUser,
                dbPassword
        );
    }
}
