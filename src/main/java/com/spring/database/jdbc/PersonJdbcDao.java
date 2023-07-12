package com.spring.database.jdbc;

import com.spring.database.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person where id = ?",
                new BeanPropertyRowMapper<>(Person.class),
                id);
    }

    public Person findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM person where name = ?",
                new BeanPropertyRowMapper<>(Person.class),
                name);
    }
}
