package com.revature.services;

import com.revature.daos.PersonDao;
import com.revature.daos.PersonDaoImpl;
import com.revature.models.Person;
import com.revature.models.Type;

import java.util.List;
import java.util.Locale;

public class PersonService {

    private final PersonDao personDao = new PersonDaoImpl();


    public boolean createPerson(Type t, String first, String last, String email, String username, String password){
        email = email.toLowerCase();
        username = username.toLowerCase();
        Person p = new Person(t, first, last, email, username, password);
        return personDao.createPerson(p);
    }

    public boolean createPerson(String first, String last, String email ,String username, String password){
        email = email.toLowerCase();
        username = username.toLowerCase();
        Person p = new Person(Type.CUSTOMER, first, last, email, username, password);
        return personDao.createPerson(p);
    }

    public boolean createPerson(Person p){
        String email = p.getFirst() + "." + p.getLast() + "@example.com";
        email = email.toLowerCase();
        p.setEmail(email);
        return personDao.createPerson(p);
    }

    public List<Person> getAll(){
        return personDao.getAllPeople();
    }

    public boolean changePassword(String oldPass, String newPass, int id){
        // obtain the record from the database
        Person person = personDao.getPersonById(id);

        // compare the previous password to the password saved in the database
        if(person.getPassword().equals(oldPass)){

            // update the record with the new password
            person.setPassword(newPass);
            boolean updateSuccess = personDao.updatePerson(person);
            return updateSuccess;
        }
        return false;
    }

    public Person getById(int id){
        return personDao.getPersonById(id);
    }

    public boolean update(Person p){
        return personDao.updatePerson(p);
    }

    public Person getByUsernameAndPassword(String username, String password){
        return personDao.getPersonByUsernameAndPassword(username,password);
    }

    public Person getByUsername(String username){
        return personDao.getPersonByUsername(username);
    }

    public boolean deletePerson(Person p){
        return personDao.deletePerson(p);
    }

}
