package ru.productstar.outcoming.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.productstar.outcoming.homework.ContactConfiguration;

public class ContactMain {
    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(ContactConfiguration.class);

        var contactDao = applicationContext.getBean(ContactDao.class);
        var contact = contactDao.getContact(1000L);
        System.out.println(contact);
    }
}
