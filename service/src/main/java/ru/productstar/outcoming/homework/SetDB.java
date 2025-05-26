package ru.productstar.outcoming.homework;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetDB {
    public SetDB() {
        setUp();
    }
    public void setUp() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUser("user");
        dataSource.setPassword("password");

        initializeDb(dataSource);
//            taskDao = new TaskDao(dataSource);
    }

    private void initializeDb(DataSource dataSource) {
        try (InputStream inputStream = this.getClass().getResource("/contact.sql").openStream()) { // "/initial.sql"
            String sql = new String(inputStream.readAllBytes());
            try (
                    Connection connection = dataSource.getConnection();
                    Statement statement = connection.createStatement()
            ) {
                statement.executeUpdate(sql);
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
