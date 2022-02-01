package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.models.Type;
import com.revature.services.PersonService;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao{
    @Override
    public Account getAccountById(int AccountID) {
        try (Connection c = ConnectionUtil.getConnection();){
            PreparedStatement s = c.prepareStatement("select * from {oj account left join person on " +
                    "person.id = account.customerID where account.id = ? } ");
            s.setInt(1, AccountID);
            ResultSet rs = s.executeQuery();
            if(rs.next()){
                Account a = new Account();
                a.setAccountID(rs.getInt("id"));
                a.setBalance(rs.getDouble("balance"));
                int customerID = rs.getInt("customerID");
                if(customerID!=0){
                    Person customer = new Person();
                    customer.setId(rs.getInt("id"));
                    customer.setType(Type.values()[rs.getInt("type")]);
                    customer.setFirst(rs.getString("first"));
                    customer.setLast(rs.getString("last"));
                    customer.setEmail(rs.getString("email"));
                    customer.setUsername(rs.getString("username"));
                    customer.setPassword(rs.getString("password"));
                    a.setCustomer(customer);
                }
                return a;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean applyForAccount(Person customer) {
        return false;
    }

    @Override
    public List<Account> getAllAccountsByPerson(Person p) {
        String sql = "select * from {oj account left join person on " +
                "person.id = account.customerID where person.username = ? } ";
        List<Account> accounts = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();){
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, p.getUsername());
            ResultSet rs = s.executeQuery();
            while(rs.next()){
                Account a = new Account();
                a.setAccountID(rs.getInt("id"));
                a.setBalance(rs.getDouble("balance"));
                int customerID = rs.getInt("customerID");
                if(customerID!=0){
                    Person customer = new Person();
                    customer.setId(rs.getInt("customerid"));
                    customer.setType(Type.values()[rs.getInt("type")]);
                    customer.setFirst(rs.getString("first"));
                    customer.setLast(rs.getString("last"));
                    customer.setEmail(rs.getString("email"));
                    customer.setUsername(rs.getString("username"));
                    customer.setPassword("HIDDEN");
                    a.setCustomer(customer);
                }
                accounts.add(a);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public boolean createAccount(String username) {
        PersonService personService = new PersonService();
        Person p = personService.getByUsername(username);
        String sql = "insert into account (balance, customerid) values (0, ?)";
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


    @Override
    public List<Account> getAllAccounts() {
        String sql = "select * from {oj account left join person on person.id = account.customerID}";
        List<Account> accounts = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();){
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                Account a = new Account();
                a.setAccountID(rs.getInt("id"));
                a.setBalance(rs.getDouble("balance"));
                int customerID = rs.getInt("customerID");
                if(customerID!=0){
                    Person customer = new Person();
                    customer.setId(rs.getInt("id"));
                    customer.setType(Type.values()[rs.getInt("type")]);
                    customer.setFirst(rs.getString("first"));
                    customer.setLast(rs.getString("last"));
                    customer.setEmail(rs.getString("email"));
                    customer.setUsername(rs.getString("username"));
                    customer.setPassword("HIDDEN");
                    a.setCustomer(customer);
                }
                accounts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

}
