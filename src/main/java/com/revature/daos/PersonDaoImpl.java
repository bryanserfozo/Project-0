package com.revature.daos;

import com.revature.models.Person;
import com.revature.models.Type;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {


    @Override
    public boolean createPerson(Person p) {
        String sql = "insert into person (type, first, last, email, username, password) " +
                "values (?, ?, ?, ?, ?, ?)";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1, p.getType().ordinal());
            ps.setString(2, p.getFirst());
            ps.setString(3, p.getLast());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getUsername());
            ps.setString(6, p.getPassword());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Person> getAllPeople() {
        String sql = "select * from person";
        List<Person> people = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();){
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                Person person = new Person();
                int id = rs.getInt("id");
                person.setId(id);


                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values();
                person.setType(types[typeOrdinal]);

                person.setFirst(rs.getString("first"));
                person.setLast(rs.getString("last"));
                person.setEmail(rs.getString("email"));
                person.setUsername(rs.getString("username"));
                person.setPassword("HIDDEN");
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }


    @Override
    public Person getPersonById(int id) {
        String sql = "select * from person where id = ? ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Person person = new Person();
                person.setId(id);

                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values();
                person.setType(types[typeOrdinal]);

                person.setFirst(rs.getString("first"));
                person.setLast(rs.getString("last"));
                person.setEmail(rs.getString("email"));
                person.setUsername(rs.getString("username"));
                person.setPassword(rs.getString("password"));
                return person;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updatePerson(Person p) {
        String sql = "update person set type = ?, first = ?, last = ?, email = ?, username = ?, password = ? where id = ?";

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, p.getType().ordinal());
            ps.setString(2, p.getFirst());
            ps.setString(3, p.getLast());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getUsername());
            ps.setString(6, p.getPassword());
            ps.setInt(7, p.getId());

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Person getPersonByUsernameAndPassword(String username, String password) {
        String sql = "select * from person where username = ? and password = ? ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Person person = new Person();
                person.setId(rs.getInt("id"));


                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values();
                person.setType(types[typeOrdinal]);

                person.setFirst(rs.getString("first"));
                person.setLast(rs.getString("last"));
                person.setEmail(rs.getString("email"));
                person.setUsername(rs.getString("username"));
                person.setPassword(rs.getString("password"));
                return person;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Person getPersonByUsername(String username) {

        String sql = "select * from person where username = ?";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Person person = new Person();
                person.setId(rs.getInt("id"));


                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values();
                person.setType(types[typeOrdinal]);

                person.setFirst(rs.getString("first"));
                person.setLast(rs.getString("last"));
                person.setEmail(rs.getString("email"));
                person.setUsername(rs.getString("username"));
                person.setPassword(rs.getString("password"));
                return person;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deletePerson(Person p) {
        String sql = "delete from person where id = ?;";
        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, p.getId());
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

