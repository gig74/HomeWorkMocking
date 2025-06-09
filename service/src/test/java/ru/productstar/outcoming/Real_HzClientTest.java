package ru.productstar.outcoming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import ru.productstar.outcoming.homework.Contact;
import ru.productstar.outcoming.homework.ContactDao;

@SpringBootTest
@ActiveProfiles("testBasic")
class Real_HzClientTest {
	@Autowired
	private ContactDao contactDao;
	private Contact testContact = new Contact(1000l, "Ivan", "Ivanov", "iivanov@gmail.com", "0001");

	@Test
	void h2Test() {
		assertEquals(testContact,contactDao.getContact(1000l));
		assertThrows( EmptyResultDataAccessException.class, () -> contactDao.getContact(1001l));
	}

}
