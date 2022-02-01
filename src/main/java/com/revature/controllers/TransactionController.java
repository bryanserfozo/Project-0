package com.revature.controllers;

import com.revature.loggingSingleton.LoggingSingleton;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.TransactionType;
import com.revature.services.AccountService;
import com.revature.services.TransactionService;
import io.javalin.http.Context;

import java.util.List;

public class TransactionController {

    private final TransactionService transactionService = new TransactionService();
    private final AccountService accountService = new AccountService();
    private final LoggingSingleton logger = LoggingSingleton.getLogger();

    public void handleGetAllByID(Context ctx) {
        String idParam = ctx.pathParam("accountId");
        int id = Integer.parseInt(idParam);

        List<Transaction> transactions = transactionService.getAllById(id);
        ctx.json(transactions);

    }

    public void handleEmployeeTransaction(Context ctx) {
        String authHeader = ctx.header("Authorization");
        String[] authParts = authHeader.split("-");


        boolean success = false;

        String typeParam = ctx.formParam("type");
        TransactionType type = null;
        TransactionType type2 = null;
        if (typeParam.equals("deposit")) {
            type = TransactionType.DEPOSIT;
        } else if (typeParam.equals("withdrawal")) {
            type = TransactionType.WITHDRAWAL;
        } else if (typeParam.equals("transfer")) {
            type = TransactionType.WITHDRAWAL;
            type2 = TransactionType.DEPOSIT;
        } else {
            success = false;
        }


        String amountString = ctx.formParam("amount");
        double amount = Double.parseDouble(amountString);


        String idParam = ctx.formParam("accountID");
        int id = Integer.parseInt(idParam);

        Account a = accountService.getAccountById(id);


        if (amount >= 0 && (authParts[0].equals("ADMIN"))) {
            if (type.equals(TransactionType.DEPOSIT)) {
                Transaction t = new Transaction(a, amount, type);
                success = transactionService.deposit(t);
                if (success) {
                    logger.info("Account " + a.getAccountID() + " has deposited $" + amount);
                }
            } else if (type.equals(TransactionType.WITHDRAWAL) && type2.equals(null)) {
                if (amount <= a.getBalance()) {
                    Transaction t = new Transaction(a, amount, type);
                    success = transactionService.withdraw(t);
                    if (success) {
                        logger.info("Account " + a.getAccountID() + " has withdrawn $" + amount);
                    }
                }
            } else if (type2 != null) {
                String idParam2 = ctx.formParam("secondAccountID");
                int id2 = Integer.parseInt(idParam2);

                Account b = accountService.getAccountById(id2);

                if (a.getCustomer().getUsername().equals(b.getCustomer().getUsername())) {
                    if (amount <= a.getBalance()) {
                        Transaction t = new Transaction(a, amount, type);
                        Transaction t2 = new Transaction(b, amount, type2);

                        boolean success1 = transactionService.withdraw(t);
                        boolean success2 = transactionService.deposit(t2);

                        success = (success1 && success2);
                        if (success) {
                            logger.info("Account " + a.getAccountID() + " has transferred $" + amount +
                                    " to Account " + b.getAccountID());
                        }
                    }
                }
            }
        }

        if (success) {
            ctx.status(201);
        } else {
            ctx.status(400);
        }


    }

    public void handleCustomerTransaction(Context ctx) {
        String authHeader = ctx.header("Authorization");
        String[] authParts = authHeader.split("-");


        boolean success = false;

        String typeParam = ctx.formParam("type");
        TransactionType type = null;
        TransactionType type2 = null;
        if (typeParam.equals("deposit")) {
            type = TransactionType.DEPOSIT;
        } else if (typeParam.equals("withdrawal")) {
            type = TransactionType.WITHDRAWAL;
        } else if (typeParam.equals("transfer")) {
            type = TransactionType.WITHDRAWAL;
            type2 = TransactionType.DEPOSIT;
        } else {
            success = false;
        }
        ;


        String amountString = ctx.formParam("amount");
        double amount = Double.parseDouble(amountString);


        String idParam = ctx.formParam("accountID");
        int id = Integer.parseInt(idParam);

        Account a = accountService.getAccountById(id);


        if (amount >= 0 && (authParts[0].equals("CUSTOMER"))) {
            if (type.equals(TransactionType.DEPOSIT)) {
                Transaction t = new Transaction(a, amount, type);
                success = transactionService.deposit(t);
                if (success) {
                    logger.info("Account " + a.getAccountID() + " has deposited $" + amount);
                }
            } else if (type.equals(TransactionType.WITHDRAWAL) && type2.equals(null)) {
                if (amount <= a.getBalance()) {
                    Transaction t = new Transaction(a, amount, type);
                    success = transactionService.withdraw(t);
                    if (success) {
                        logger.info("Account " + a.getAccountID() + " has withdrawn $" + amount);
                    }
                }
            } else if (type2 != null) {
                String idParam2 = ctx.formParam("secondAccountID");
                int id2 = Integer.parseInt(idParam2);

                Account b = accountService.getAccountById(id2);

                if (a.getCustomer().getUsername().equals(b.getCustomer().getUsername())) {
                    if (amount <= a.getBalance()) {
                        Transaction t = new Transaction(a, amount, type);
                        Transaction t2 = new Transaction(b, amount, type2);

                        boolean success1 = transactionService.withdraw(t);
                        boolean success2 = transactionService.deposit(t2);

                        success = (success1 && success2);
                        if (success) {
                            logger.info("Account " + a.getAccountID() + " has transferred $" + amount +
                                    " to Account " + b.getAccountID());
                        }
                    }
                }
            }
        }

        if (success) {
            ctx.status(201);
        } else {
            ctx.status(400);
        }


    }
}
