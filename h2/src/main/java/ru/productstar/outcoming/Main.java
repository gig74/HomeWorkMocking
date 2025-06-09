package ru.productstar.outcoming;

import javax.sql.DataSource;

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
}
