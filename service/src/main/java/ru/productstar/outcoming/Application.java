package ru.productstar.outcoming;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.productstar.outcoming.homework.Contact;
import ru.productstar.outcoming.homework.ContactDao;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

	@Autowired
	private ContactDao contactDao;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		var contactDao = context.getBean(ContactDao.class);

		Contact contact = contactDao.getContact(1000L);
		System.out.println(contact);
//		var applicationContext = SpringApplication.run(Application.class, args);
//		var contactDao = applicationContext.getBean(ContactDao.class);
//		var contact = contactDao.getContact(1000L);
//		System.out.println(contact);
	}

//	@Bean
//	public Contact getId1000ContactDao() throws SQLException {
//		Contact contact = contactDao.getContact(1000L);
//		System.out.println(contact);
//		return contact;
//	}
}
