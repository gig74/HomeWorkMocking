# HomeWorkMocking
 Домашнее задание по теме "Тестирование исходящих взаимодействий: outcoming. Мокинг." (ProductStar)

# Постановка задачи
1. Вам дан проект.
2. Необходимо скачать код проекта и открыть в любой IDE.
3. Добавить запуск in-memory H2 базы данных
4. Реализовать какой-либо сервис по сохранению и чтению данных в БД
5. Покрыть сервис unit тестами, использую 4 подхода, рассмотренных в уроке. Тесты необходимо реализовать в классах:
DI_HzClientTest
InMemory_HzClientTest
Mock_HzClientTest
Real_HzClientTest
6. Прислать на проверку домашнее задание.

## Подключенные зависимости и плагины

### Для проекта h2

- h2 - Библиотека БД H2
- spring-boot-starter-data-jpa - зависимость, которая используется для эффективного подключения приложения Spring к реляционной базе данных.

### Для проекта service

- spring-boot-starter -  набор предустановленных зависимостей (стартер) для создания приложений на основе Spring Boot.
- spring-boot-starter-test - набор предварительно настроенных зависимостей, который предоставляет инструменты для тестирования приложений Spring Boot(включает JUnit).
- h2 - Библиотека БД H2
- spring-jdbc - Spring JDBC предоставляет уровень абстракции, который упрощает использование кода JDBC
- commons-dbcp - Apache Commons DBCP software implements Database Connection Pooling (содержит класс BasicDataSource для реализации интерфейса DataSource)
- mockito-core - это стандартная версия фреймворка Mockito для тестирования приложений на языке Java. Он позволяет создавать фиктивные объекты (моки), которые имитируют поведение реальных объектов, и определять их поведение в тестах.
#### Плагины:
- spring-boot-maven-plugin, maven-surefire-plugin, maven-jar-plugin - не разобрался, но java сама их запросила (spring-boot-maven-plugin был в исходном проекте)



## Описание основных файлов
### Для проекта h2
- h2/src/main/resources/application.properties - описание подключения к БД H2 (для создания "реальной" БД)
- h2/src/main/resources/contact.sql - скрипт sql для создания таблицы и её предзаполнения в "реальной" БД
- h2/src/main/java/ru/productstar/outcoming/Main.java - программа запуска "реальной" БД
### Для проекта service
- service/src/main/resources/jdbc.properties - параметры для подключения к БД
- service/src/main/resources/contact.sql - скрипт sql для создания таблицы и её предзаполнения БД "в памяти"
- service/src/main/java/ru/productstar/outcoming/homework/Contact.java - класс объекта одного контакта
- service/src/main/java/ru/productstar/outcoming/homework/ContactDao.java - интерфейс для основных методов уровня DAO
- service/src/main/java/ru/productstar/outcoming/homework/ContactDaoImpl.java - основная реализация методов уровня DAO интерфейса ContactDao ( @Profile("testBasic") )
- service/src/main/java/ru/productstar/outcoming/common/JdbcConfig.java - дополнительный файл конфигурации Spring для создания соединения с БД и объекта NamedParameterJdbcTemplate
- service/src/main/java/ru/productstar/outcoming/common/PropertiesConfiguration.java - дополнительный файл конфигурации Spring для указания на файл описания соединения
- service/src/main/java/ru/productstar/outcoming/Application.java - основной запуск SpringApplication
- service/src/test/java/ru/productstar/outcoming/service/ContactDaoImpl.java - вспомогательная реализация методов интерфейса ContactDao для "подмены зависимостей" ( @Profile("testDI") )
#### Непосредственно тесты service
- service/src/test/java/ru/productstar/outcoming/Real_HzClientTest.java - тест в "реальной базе"
- service/src/test/java/ru/productstar/outcoming/InMemory_HzClientTest.java - тест в "базе в памяти"
- service/src/test/java/ru/productstar/outcoming/DI_HzClientTest.java - тест с "подменой зависимостей"
- service/src/test/java/ru/productstar/outcoming/Mock_HzClientTest.java - тест с "мокированием зависимостей"

## Примечания
Проект "HomeWorkMocking" был перекопирован изначально из предложенного спикером проекта "dz" .
В /service в pom.xml были добавлены plugin'ы maven-surefire-plugin и maven-jar-plugin потому как без них не работало .
В тестах для разных случаев для ID 1000 заведены по умолчанию разные номера телефонов:
- 0001 - номер для "реальной базы"
- 0002 - номер для "базы в памяти"
- 0003 - номер для "подмены зависимостей"
- 0004 - номер для "мокирования зависимостей" <br/>

Для тестирования "реальной базы" необходимо предварительно её запустить программой Main из проекта "h2". 
При этом перед тестированием базы "в памяти" "реальную базу" необходимо предварительно остановить (завершить процесс Main).  