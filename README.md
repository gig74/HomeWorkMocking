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

[//]: # (## Подключенные зависимости и плагины)

[//]: # (- h2 - Библиотека БД H2)

[//]: # (- junit-bom - POM для упрощения управления зависимостями при обращении к нескольким артефактам JUnit с помощью Gradle или Maven)

[//]: # (- junit-jupiter - библиотека для unit-тестов.)

[//]: # (- assertj-core - это артефакт библиотеки AssertJ для тестирования &#40;нужен для assertThat&#41;)

- spring-jdbc - Spring JDBC предоставляет уровень абстракции, который упрощает использование кода JDBC
- commons-dbcp - Apache Commons DBCP software implements Database Connection Pooling (содержит класс BasicDataSource для реализации интерфейса DataSource)


[//]: # ()
[//]: # (## Описание основных файлов)

[//]: # (- main/java/dao/TaskDao.java - основные методы уровня DAO)

[//]: # (- main/java/entity/Task.java - класс объекта одного задания из ToDo-листа)

[//]: # (- main/resources/initial.sql - скрипт sql для создания таблицы task если не существует.)

[//]: # (- test/java/dao/TaskDaoTest.java - предопределённые Unit тесты . )


## Примечания
Проект "HomeWorkMocking" был перекопирован изначально из предложенного проекта dz .
В /service в pom.xml были добавлены plugin'ы maven-surefire-plugin и maven-jar-plugin потому как без них не работало .
В /service в pom.xml были добавлены зависимости 