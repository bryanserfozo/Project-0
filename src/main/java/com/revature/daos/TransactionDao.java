package com.revature.daos;


import com.revature.models.Account;
import com.revature.models.Transaction;

import java.util.List;

public interface TransactionDao {

    public boolean createTransaction(Transaction trans);
    public List<Transaction> getAllTransactions();
    public List<Transaction> getTransactionsByAccount(Account account);
    public Transaction getTransactionByID(int id);


    public List<Transaction> getAllById(int id);

    public boolean deposit(Transaction t);

    public boolean withdraw(Transaction t);

    public List<Transaction> getAll();
}
