package com.spring.database;

import com.spring.database.entity.Person;
import com.spring.database.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootApplication
public class DatabaseDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseDemoApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(DatabaseDemoApplication.class);
    @Autowired
    private PersonJdbcDao personJdbcDao;

    @Override
    public void run(String... args) throws Exception {

        log.info(personJdbcDao.getAll().toString());

        log.info(personJdbcDao.findById(10001L).toString());

        log.info(personJdbcDao.findByName("Ranga").toString());
        personJdbcDao.deleteById(10001L);

        log.info("Deleted 10001L");
        personJdbcDao.createPerson(new Person(10001L, "Ranga", "Hyderabad", Date.valueOf(LocalDate.now())));
        log.info("Created 10001L");

        log.info(
                "%s %s".formatted(String.valueOf(personJdbcDao.updatePersonById(new Person(10001L, "Ranga Reddy", "Hyderabad", Date.valueOf(LocalDate.now())))), personJdbcDao.findById(10001L))
        );

    }
}
