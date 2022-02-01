package com.revature.daos;

import com.revature.models.PendingAccount;
import com.revature.models.Person;

import java.util.List;

public interface PendingAccountDao {
    PendingAccount getByPendingId(int pendingId);

    List<PendingAccount> getAll();

    boolean deletePendingAccount(PendingAccount pendingAccount);

    boolean createPendingAccount(Person p);
}
