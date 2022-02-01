package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.Person;

import java.util.List;

public interface AccountDao {

    public Account getAccountById(int id);
    public boolean applyForAccount(Person customer);
    public List<Account> getAllAccountsByPerson(Person p);
    public boolean createAccount(String username);
    public List<Account> getAllAccounts();



}
