package ru.productstar.outcoming;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import ru.productstar.outcoming.homework.Contact;
import ru.productstar.outcoming.homework.ContactDao;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("testDI")
public class DI_HzClientTest {
    @Autowired
    private ContactDao contactDao;
    private Contact testContact = new Contact(1000l, "Ivan", "Ivanov", "iivanov@gmail.com", "0003");
    @Test
    void h2Test() {
        assertEquals(testContact,contactDao.getContact(1000l));
        assertThrows( EmptyResultDataAccessException.class, () -> contactDao.getContact(1001l));
    }
}
