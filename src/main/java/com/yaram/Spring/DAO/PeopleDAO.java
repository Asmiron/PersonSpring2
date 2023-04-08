package com.yaram.Spring.DAO;

import com.yaram.Spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> GetAll(){
        return jdbcTemplate.query("Select * from People", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person Show(int id){
        return jdbcTemplate.query("Select * from People where id = ?", new BeanPropertyRowMapper<>(Person.class),
                        new Object[]{id}).stream().findAny().orElse(null);
    }

    public void Create(Person person){
        jdbcTemplate.update("INSERT INTO PEOPLE VALUES (DEFAULT, ?, ?)", person.getName(), person.getEmail());

    }

    public void Update(int id, Person person){
        jdbcTemplate.update("UPDATE PEOPLE SET NAME = ?, email = ? where id = ?",
                person.getName(), person.getEmail(), id);
    }


    public void Delete(int id){
        jdbcTemplate.update("DELETE FROM people WHERE id = ?", id);
    }
}
