package com.revature.services;


import com.revature.daos.PendingAccountDao;
import com.revature.daos.PendingAccountDaoImpl;
import com.revature.models.PendingAccount;
import com.revature.models.Person;

import java.util.List;

public class PendingAccountService {

    private final PendingAccountDao pad = new PendingAccountDaoImpl();

    public List<PendingAccount> getAll(){
        return pad.getAll();
    }

    public PendingAccount getByPendingId(int pendingId){
        return pad.getByPendingId(pendingId);
    }

    public boolean deletePendingAccount(PendingAccount pendingAccount){
        return pad.deletePendingAccount(pendingAccount);
    }

    public boolean createPendingAccount(Person p) {
        return pad.createPendingAccount(p);

    }
}
