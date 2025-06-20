package ru.productstar.outcoming.service;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.productstar.outcoming.homework.Contact;
import ru.productstar.outcoming.homework.ContactDao;

import java.util.List;

@Service
@Profile("testDI")
public class ContactDaoImpl implements ContactDao {
    private Contact testContact = new Contact(1000l, "Ivan", "Ivanov", "iivanov@gmail.com", "0003");
    public Contact getContact(long contactId) throws EmptyResultDataAccessException {
        if (contactId == 1000l)
            return testContact;
        else
            throw new EmptyResultDataAccessException("Нет такого ID в контактах",0);
    }
}
