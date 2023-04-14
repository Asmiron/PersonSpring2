package com.yaram.Spring.DAO;

import com.yaram.Spring.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PeopleDAO {

    private final SessionFactory sessionFactory;

    public PeopleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> GetAll(){
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person Show(int id){
        Session session = sessionFactory.getCurrentSession();

        return session.get(Person.class, id);

    }

    @Transactional
    public void Create(Person person){
        Session session = sessionFactory.getCurrentSession();

        session.persist(person);
    }

    @Transactional
    public void Update(int id, Person person){
        Session session = sessionFactory.getCurrentSession();

        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setEmail(person.getEmail());
    }


    @Transactional
    public void Delete(int id){
        Session session = sessionFactory.getCurrentSession();

        session.remove(session.get(Person.class, id));

    }
}
