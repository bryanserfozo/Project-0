package com.revature.models;

import java.io.Serializable;

public class PendingAccount implements Serializable {

    private int pendingID;
    private Person person;
    private int numOtherAccounts;

    public PendingAccount() {
    }

    public PendingAccount(int pendingID, Person person, int numOtherAccounts) {
        this.pendingID = pendingID;
        this.person = person;
        this.numOtherAccounts = numOtherAccounts;
    }

    public int getPendingID() {
        return pendingID;
    }

    public void setPendingID(int pendingID) {
        this.pendingID = pendingID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getNumOtherAccounts() {
        return numOtherAccounts;
    }

    public void setNumOtherAccounts(int numOtherAccounts) {
        this.numOtherAccounts = numOtherAccounts;
    }

    @Override
    public String toString() {
        return "PendingAccount{" +
                "pendingID=" + pendingID +
                ", person=" + person +
                ", numOtherAccounts=" + numOtherAccounts +
                '}';
    }
}
