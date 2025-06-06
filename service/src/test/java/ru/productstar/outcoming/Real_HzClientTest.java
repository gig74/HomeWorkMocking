package ru.productstar.outcoming;

import static org.junit.jupiter.api.Assertions.*;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.productstar.outcoming.homework.Contact;
import ru.productstar.outcoming.homework.ContactDao;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
class Real_HzClientTest {
	@Autowired
	private ContactDao contactDao;
	private Contact testContact = new Contact(1000l, "Ivan", "Ivanov", "iivanov@gmail.com", "01234567");

	@Test
	void h2Test() {
		assertEquals(testContact,contactDao.getContact(1000l));
		assertThrows( EmptyResultDataAccessException.class, () -> contactDao.getContact(1001l));
	}

}
