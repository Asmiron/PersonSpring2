package com.yaram.Spring.DAO;

import com.yaram.Spring.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/SpringJDBC";
    private static final String USERNAME = "Spring";
    private static final String PASSWORD = "12345";

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Person> GetAll(){
        List<Person> People = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from People");

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                People.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return People;
    }

    public Person Show(int id){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * from people where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Person person = new Person();
            while(resultSet.next()){
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
            }
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void Create(Person person){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO people  VALUES(?, ?, ?)");
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void Update(int id, Person person){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE people set name = ?, email = ? where id = ?");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void Delete(int id){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete from people where id = ?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
