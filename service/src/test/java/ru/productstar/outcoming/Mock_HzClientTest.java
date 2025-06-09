package ru.productstar.outcoming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import ru.productstar.outcoming.homework.Contact;
import ru.productstar.outcoming.homework.ContactDao;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class Mock_HzClientTest {
    @MockBean
    private ContactDao contactDao;
    private Contact testContact = new Contact(1000l, "Ivan", "Ivanov", "iivanov@gmail.com", "0004");
    @BeforeEach
    private void init() {
        Contact mockContact = new Contact(1000l, "Ivan", "Ivanov", "iivanov@gmail.com", "0004");
        when(contactDao.getContact(1000l)).thenReturn(mockContact);
        when(contactDao.getContact(1001l)).thenThrow(new EmptyResultDataAccessException("Нет такого ID в контактах",0));
    }
    @Test
    void h2Test() {
        assertEquals(testContact,contactDao.getContact(1000l));
        assertThrows( EmptyResultDataAccessException.class, () -> contactDao.getContact(1001l));
    }
}
