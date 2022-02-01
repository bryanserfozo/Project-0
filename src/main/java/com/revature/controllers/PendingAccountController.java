package com.revature.controllers;


import com.revature.loggingSingleton.LoggingSingleton;
import com.revature.models.Account;
import com.revature.models.PendingAccount;
import com.revature.models.Person;
import com.revature.services.AccountService;
import com.revature.services.PendingAccountService;
import com.revature.services.PersonService;
import io.javalin.http.Context;

import java.util.List;

public class PendingAccountController {

    private final PendingAccountService pas = new PendingAccountService();
    private final AccountService as = new AccountService();
    private final PersonService ps = new PersonService();
    private final LoggingSingleton logger = LoggingSingleton.getLogger();

    public void handleGetAll(Context ctx) {
        List<PendingAccount> pa = pas.getAll();
        ctx.json(pa);
    }

    public void handleApproval(Context ctx) {
        String pendingIdString = ctx.formParam("pendingId");
        String decision = ctx.formParam("decision");
        String username = ctx.formParam("username");
        int pendingIdParam = Integer.parseInt(pendingIdString);
        PendingAccount pa = pas.getByPendingId(pendingIdParam);

        boolean success;

        if(decision.equals("approved")){
            boolean first = as.createAccount(username);
            boolean second = pas.deletePendingAccount(pa);
            if(first && second){
                success = true;
                logger.info(ps.getByUsername(username).getFirst() + " "+
                        ps.getByUsername(username).getLast()+ " has been approved for a new account.");
            }
            else{
                success = false;
            }
        } else if (decision.equals("denied")){
            success = pas.deletePendingAccount(pa);
            logger.info(ps.getByUsername(username).getFirst() + " "+
                    ps.getByUsername(username).getLast()+ " has been denied for a new account.");
        } else{
            success = false;
        }

        if (success) {
            ctx.status(201);

        } else {
            ctx.status(400);
        }
    }

    public void handleCreate(Context ctx) {
        String username = ctx.pathParam("username");
        Person p = ps.getByUsername(username);

        boolean success = pas.createPendingAccount(p);

        if (success) {
            ctx.status(201);
            logger.info(ps.getByUsername(username).getFirst() + " "+
                    ps.getByUsername(username).getLast()+ " has applied for a new account.");
        } else {
            ctx.status(400);
        }
    }
}
