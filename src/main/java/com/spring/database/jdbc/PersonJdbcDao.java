package com.spring.database.jdbc;

import com.spring.database.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJdbcDao {

    //    TODO: place sql queries in separate variables
/*
    BeanPropertyRowMapper : this can be used when all the field names are matching in db and entity classes
     PersonRowMapper : this is required when we need to do explicit mappings
*/

    private static final String SELECT_PERSON = "SELECT * from person";
    private static final String SELECT_PERSON_BY_ID = "SELECT * FROM person where id = ?";
    private static final String SELECT_PERSON_BY_NAME = "SELECT * FROM person where name = ?";
    private static final String DELETE_PERSON_BY_ID = "DELETE from person where id = ?";
    private static final String CREATE_PERSON = "INSERT INTO PERSON (id, name, location, birth_date) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PERSON_BY_ID = "UPDATE person SET name=?, location=?, birth_date=? WHERE id =?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonRowMapper personRowMapper;

    public List<Person> getAll() {
//        return jdbcTemplate.query("SELECT * from person", new BeanPropertyRowMapper<>(Person.class));
        return jdbcTemplate.query(SELECT_PERSON, personRowMapper);

    }

    public Person findById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_PERSON_BY_ID,
                personRowMapper,
                id);
    }

    public Person findByName(String name) {
        return jdbcTemplate.queryForObject(SELECT_PERSON_BY_NAME,
                new BeanPropertyRowMapper<>(Person.class),
                name);
    }

    public int deleteById(long id) {
        return jdbcTemplate.update(DELETE_PERSON_BY_ID, id);
    }

    public int createPerson(Person person) {
        return jdbcTemplate.update(CREATE_PERSON, person.getId(), person.getName(), person.getLocation(), person.getBirthDate());
    }

    public int updatePersonById(Person person) {
        return jdbcTemplate.update(UPDATE_PERSON_BY_ID,
                person.getName(), person.getLocation(), person.getBirthDate(), person.getId());
    }
}
