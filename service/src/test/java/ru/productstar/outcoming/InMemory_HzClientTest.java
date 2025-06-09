package ru.productstar.outcoming;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import ru.productstar.outcoming.homework.Contact;
import ru.productstar.outcoming.homework.ContactDao;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("testBasic")
public class InMemory_HzClientTest {
    private Contact testContact = new Contact(1000l, "Ivan", "Ivanov", "iivanov@gmail.com", "0002");
    @BeforeAll
    private static void setDB() throws SQLException {
        setUp();
    }
    public static void setUp() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUser("user");
        dataSource.setPassword("password");
        initializeDb(dataSource);
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090").start();
    }
    private static void initializeDb(@Autowired DataSource dataSource) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classloader.getResourceAsStream("contact.sql")) {
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
    @Autowired
    private ContactDao contactDao;
    @Test
    void h2Test() {
        assertEquals(testContact,contactDao.getContact(1000l));
        assertThrows( EmptyResultDataAccessException.class, () -> contactDao.getContact(1001l));
    }
}
