package com.yaram.Spring.DAO;

import com.yaram.Spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private static int PEOPLE_CONT;
    private List<Person> People;

    {
        People = new ArrayList<>();
        People.add(new Person(++PEOPLE_CONT, "Alexandr", "Yaramishyan@mail.com"));
        People.add(new Person(++PEOPLE_CONT, "Vladislav", "VladikM@dodik.com"));
        People.add(new Person(++PEOPLE_CONT, "Sergey", "Serezha@shiz.com"));
        People.add(new Person(++PEOPLE_CONT, "Kirill", "Kirill@caban.com"));
    }

    public List<Person> GetAll(){
        return People;
    }

    public Person Show(int id){
        return People.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void Create(Person person){
        person.setId(++PEOPLE_CONT);
        People.add(person);
    }

    public void Update(int id, Person person){
        Person PersonToBeUpdated = Show(id);
        PersonToBeUpdated.setName(person.getName());
        PersonToBeUpdated.setEmail(person.getEmail());
    }


    public void Delete(int id){
        People.removeIf(p -> p.getId() == id);
    }
}
