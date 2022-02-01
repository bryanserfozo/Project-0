package com.revature.services;

import com.revature.daos.TransactionDao;
import com.revature.daos.TransactionDaoImpl;
import com.revature.models.Transaction;

import java.util.List;

public class TransactionService {

    private final TransactionDao transactionDao= new TransactionDaoImpl();

    public List<Transaction> getAllById(int id) {
        return transactionDao.getAllById(id);
    }

    public boolean deposit(Transaction t) { return transactionDao.deposit(t);
    }

    public boolean withdraw(Transaction t) {return transactionDao.withdraw(t);
    }

    public List<Transaction> getAll(int id) {
        return transactionDao.getAll();
    }
}
