package ru.productstar.outcoming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.productstar.outcoming.homework.ContactDao;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

//		var applicationContext = SpringApplication.run(Application.class, args);
//		var contactDao = applicationContext.getBean(ContactDao.class);
//		var contact = contactDao.getContact(1000L);
//		System.out.println(contact);
	}

}
