package com.revature.controllers;

import com.revature.loggingSingleton.LoggingSingleton;
import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.services.AccountService;
import com.revature.services.PersonService;
import io.javalin.http.Context;

import java.util.List;

public class AccountController {

    private final AccountService accountService = new AccountService();
    private final PersonService personService = new PersonService();
    private final LoggingSingleton logger = LoggingSingleton.getLogger();


    public void handleGetAllByUser(Context ctx){
        String userParam = ctx.pathParam("username");

        try{
            Person p = personService.getByUsername(userParam);
            List<Account> accounts = accountService.getAllAccountsByPerson(p.getId());
            if(accounts!=null){
                ctx.json(accounts);
            } else {
                ctx.status(404);
            }
        } catch (NullPointerException e){
            ctx.status(404);
            ctx.result("No such user found");

        }
    }

    public void handleGetAll(Context ctx){
        List<Account> accounts = accountService.getAll();
        ctx.json(accounts);
        ctx.status(200);
    }


    public void handleDelete(Context ctx) {
        String idParam = ctx.pathParam("accountId");
        int accountid = Integer.parseInt(idParam);
        Account a = accountService.getAccountById(accountid);

        String authHeader = ctx.header("Authorization");
        String[] authParts = authHeader.split("-");


        Person admin = personService.getByUsername(authParts[1]);

        boolean success = false;


        if (authParts[0].equals("ADMIN")) {
            success = accountService.deleteAccount(a);
        } else if (authParts[0].equals("EMPLOYEE")) {
            ctx.status(401);
            ctx.result("Only Admins can delete users");
        }

        if (success) {
            ctx.status(202);
            logger.info("Admin "+ admin.getFirst() + " " + admin.getLast() + " deleted account: " + accountid);
        } else {
            ctx.status(400);
        }
    }
}
