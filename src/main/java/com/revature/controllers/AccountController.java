package com.revature.controllers;

import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.services.AccountService;
import com.revature.services.PersonService;
import io.javalin.http.Context;

import java.util.List;

public class AccountController {

    private final AccountService accountService = new AccountService();
    private final PersonService personService = new PersonService();

    public void handleGetOne(Context ctx){
        String idParam = ctx.pathParam("id");
        int accountId = Integer.parseInt(idParam);
        Account account = accountService.getAccountById(accountId);
        if(account!=null){
            ctx.json(account);
        } else {
            ctx.status(404);
        }
    }

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
    }


}
