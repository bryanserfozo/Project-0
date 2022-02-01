package com.revature.services;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImpl;
import com.revature.models.Account;
import com.revature.models.Person;

import java.util.List;

public class AccountService {

    private final AccountDao accountDao = new AccountDaoImpl();
    private final PersonService personService = new PersonService();

    public Account getAccountById(int accountId){
        return accountDao.getAccountById(accountId);
    }

    public List<Account> getAllAccountsByPerson(int accountId){
        Person p = personService.getById(accountId);
        return accountDao.getAllAccountsByPerson(p);
    }

    public List<Account> getAll(){
        return accountDao.getAllAccounts();
    }

    public boolean createAccount(String username){
        return accountDao.createAccount(username);
    }

    public boolean deleteAccount(Account a) {return accountDao.deleteAccount(a);}

}
