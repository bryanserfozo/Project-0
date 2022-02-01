package com.revature.models;

import java.io.Serializable;
import java.util.Date;

import static java.time.LocalDateTime.now;

public class Transaction implements Serializable {

    private int transactionID;
    private Account account;
    private double amount;
    private TransactionType type;

    public Transaction() {
    }

    public Transaction(int transactionID, Account account, double amount, TransactionType type) {
        this.transactionID = transactionID;
        this.account = account;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(Account account, double amount, TransactionType type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
    }


    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", account=" + account +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}
