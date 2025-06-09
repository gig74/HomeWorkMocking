package ru.productstar.outcoming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import ru.productstar.outcoming.homework.Contact;
import ru.productstar.outcoming.homework.ContactDao;
import java.sql.SQLException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "testBasic");
		var applicationContext = SpringApplication.run(Application.class, args);
//		var contactDao = applicationContext.getBean(ContactDao.class);
//		var contact = contactDao.getContact(1000L);
//		System.out.println(contact);
	}
}
