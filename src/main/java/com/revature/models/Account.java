package com.revature.models;

import java.io.Serializable;

public class Account implements Serializable {

    private int accountID;
    private double balance;
    private Person customer;

    public Account() {
    }

    public Account(int accountID, double balance, Person customer) {
        this.accountID = accountID;
        this.balance = balance;
        this.customer = customer;
    }

    public Account(double balance, Person customer) {
        this.balance = balance;
        this.customer = customer;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", balance=" + balance +
                ", customer=" + customer +
                '}';
    }

    
}
