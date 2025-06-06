package ru.productstar.outcoming;

import java.sql.Connection;
import javax.sql.DataSource;

//import org.h2.jdbcx.JdbcDataSource;
//import org.h2.tools.Server;
import jakarta.annotation.PostConstruct;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    // Версия 1
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    private DataSource dataSource;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }

    @PostConstruct
    private void initDb() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "";
        try (InputStream inputStream = this.getClass().getResource("/contact.sql").openStream()) {
            sql = new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        jdbcTemplate.execute(sql);
    }

    // Query test data and print results

//        @PostConstruct
//    private void initDb() {
//        try (InputStream inputStream = this.getClass().getResource("/contact.sql").openStream()) { // "/initial.sql"
//            String sql = new String(inputStream.readAllBytes());
//            try (
//                    Connection connection = dataSource.getConnection();
//                    Statement statement = connection.createStatement()
//            ) {
//                statement.executeUpdate(sql);
//            }
//
//        } catch (IOException | SQLException e) {
//            throw new RuntimeException(e);
//        }

    // Версия 2
//    public static void main(String[] args) throws InterruptedException {
//        // run H2
//        Main mainClass = new Main();
//        mainClass.setUp();
////        Thread.currentThread().join(); // keep the main running
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Для останова БД H2 in-memory нажмите '0' + ENTER");
//        String data = null;
//        while (data == null || !data.equals("0")) {
//            data = scanner.nextLine();
//        }
//    }
//    public void setUp() {
//        JdbcDataSource dataSource = new JdbcDataSource();
//        dataSource.setURL("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
//        dataSource.setUser("user");
//        dataSource.setPassword("password");
//
//        initializeDb(dataSource);
////            taskDao = new TaskDao(dataSource);
//    }
//
//    private void initializeDb(DataSource dataSource) {
//        try (InputStream inputStream = this.getClass().getResource("/contact.sql").openStream()) { // "/initial.sql"
//            String sql = new String(inputStream.readAllBytes());
//            try (
//                    Connection connection = dataSource.getConnection();
//                    Statement statement = connection.createStatement()
//            ) {
//                statement.executeUpdate(sql);
//            }
//        } catch (IOException | SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
