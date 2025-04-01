package com.fpmislata.daw1.projectedaw1.persistance.database;

import com.fpmislata.daw1.projectedaw1.common.utils.AppPropertiesReader;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Log4j2
public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private final Connection connection;

    private DatabaseConnection() {
        dbUrl = AppPropertiesReader.getProperty("app.datasource.url");
        dbUser = AppPropertiesReader.getProperty("app.datasource.username");
        dbPassword = AppPropertiesReader.getProperty("app.datasource.password");

        log.info("Estableciendo conexión....");
        try {
            connection = DriverManager.getConnection(
                    dbUrl,
                    dbUser,
                    dbPassword
            );
            log.info("Conexión establecida con la bbdd con los parámetros: ");
            log.info(this.getParameters());
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(
                    "Connection paramaters :\n\n"
                            + getParameters()
                            + "\nOriginal exception message: "
                            + e.getMessage());
        }
    }

    private String getParameters() {
        return String.format("url: %s\nUser: %s\nPassword: %s\n",
                dbUrl,
                dbUser,
                dbPassword
        );
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public void executeScript(String scriptPath) {
        try {
            ScriptRunner scriptRunner = new ScriptRunner(connection, false, false);

            InputStream scriptStream = getClass().getClassLoader().getResourceAsStream(scriptPath);
            if (scriptStream == null)
                throw new RuntimeException("Script not found: " + scriptPath);

            scriptRunner.runScript(new InputStreamReader(scriptStream));
        } catch (IOException | SQLException e) {
            log.error(String.format(
                    "Error executing script %s:\n    %s\n",
                    scriptPath,
                    e.getMessage()
            ));
            throw new RuntimeException(e);
        }
    }
}