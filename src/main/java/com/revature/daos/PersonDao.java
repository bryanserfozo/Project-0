package com.revature.daos;

import com.revature.models.Person;

import java.util.List;

public interface PersonDao {

    public boolean createPerson(Person p);
    public List<Person> getAllPeople();
    public Person getPersonById(int id);
    public boolean updatePerson(Person p);
    public Person getPersonByUsernameAndPassword(String username, String password);
    public Person getPersonByUsername(String username);
    public boolean deletePerson(Person p);

}
