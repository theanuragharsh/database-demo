package com.spring.database.jdbc;

import com.spring.database.entity.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong(1));
        person.setName(rs.getString(2));
        person.setLocation(rs.getString(3));
        person.setBirthDate(rs.getDate(4));
        return person;
    }
}
