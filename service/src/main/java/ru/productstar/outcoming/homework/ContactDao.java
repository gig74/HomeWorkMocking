package ru.productstar.outcoming.homework;

import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface ContactDao {
    public Contact getContact(long contactId) throws EmptyResultDataAccessException;
}
