package com.revature.controllers;

import com.revature.loggingSingleton.LoggingSingleton;
import com.revature.models.Person;
import com.revature.models.Type;
import com.revature.services.EncryptionService;
import com.revature.services.PersonService;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;

import java.util.List;

public class PersonController {

    private final PersonService personService = new PersonService();
    private final EncryptionService encryptionService = new EncryptionService();
    private final LoggingSingleton logger = LoggingSingleton.getLogger();

    public void handleGetAll(Context ctx) {
        List<Person> people = personService.getAll();
        ctx.json(people);
    }

    public void handleGetOne(Context ctx) {
        String userParam = ctx.pathParam("username");
        Person p = personService.getByUsername(userParam);
        p.setPassword(encryptionService.encrypt(p.getPassword()));

        String authHeader = ctx.header("Authorization");
        String[] authParts = authHeader.split("-");

        if (authHeader != null) {
            if (authParts[0].equals("ADMIN") || authParts[0].equals("CUSTOMER")) {
                ctx.json(p);
            } else if (authParts[0].equals("EMPLOYEE")) {
                p.setPassword("HIDDEN");
                ctx.json(p);
            }
        }

    }


    public void handleUpdatePassword(Context ctx) {
        // interpret incoming request
        String userParam = ctx.pathParam("username");
        Person personToUpdate = personService.getByUsername(userParam);

        String oldPassParam = ctx.formParam("old password");
        String oldpass = encryptionService.encrypt(oldPassParam);
        String newPassParam = ctx.formParam("new password");
        String newpass = encryptionService.encrypt(newPassParam);

        String authHeader = ctx.header("Authorization");
        String[] authParts = authHeader.split("-");

        boolean success = false;

        if (authHeader != null) {
            if (authParts[0].equals("ADMIN") || authParts[1].equals(userParam)) {
                success = personService.changePassword(oldpass, newpass, personToUpdate.getId());

            } else if (authParts[0].equals("EMPLOYEE")) {
                ctx.status(401);
                ctx.result("Only Admins can update another's password");
            }
                //respond to client
                if (success) {
                    ctx.status(200);
                    logger.info(personToUpdate.getFirst() + " "+ personToUpdate.getLast() +
                            "'s password has been updated");
                } else {
                    ctx.status(400);
                }

        }
    }

        public void handleCreate (Context ctx){
            // interpret request


            String firstParam = ctx.formParam("first");
            String lastParam = ctx.formParam("last");
            String emailParam = ctx.formParam("email");
            String userParam = ctx.formParam("username");
            String passParam = ctx.formParam("password");
            String password = encryptionService.encrypt(passParam);

            boolean success = personService.createPerson(firstParam, lastParam, emailParam, userParam, password);

            // prepare response
            if (success) {
                ctx.status(201);
                logger.info("New Person: " + firstParam + " " + lastParam +
                        " has been added to the database.");
            } else {
                ctx.status(400);
            }
        }

    public void handleAdminCreate (Context ctx){

        String typeParam = ctx.formParam("type");
        Type type;
        if (typeParam.equals("admin")){type = Type.ADMIN;}
        else if (typeParam.equals("employee")){type = Type.EMPLOYEE;}
        else {type = Type.CUSTOMER;}
        String firstParam = ctx.formParam("first");
        String lastParam = ctx.formParam("last");
        String emailParam = ctx.formParam("email");
        String userParam = ctx.formParam("username");
        String passParam = ctx.formParam("password");
        String password = encryptionService.encrypt(passParam);

        boolean success = personService.createPerson(type, firstParam, lastParam, emailParam, userParam, password);

        // prepare response
        if (success) {
            ctx.status(201);
            logger.info("New Person: " + firstParam + " " + lastParam +
                    " has been added to the database.");
        } else {
            ctx.status(400);
        }
    }

        public void handleDelete (Context ctx){
            String userParam = ctx.formParam("username");
            Person p = personService.getByUsername(userParam);

            String authHeader = ctx.header("Authorization");
            String[] authParts = authHeader.split("-");

            boolean success = false;


            if (authParts[0].equals("ADMIN")) {
                success = personService.deletePerson(p);
            } else if (authParts[0].equals("EMPLOYEE")) {
                ctx.status(401);
                ctx.result("Only Admins can delete users");
            }

            if (success) {
                ctx.status(200);
                logger.info("Person: " + p.getFirst() + " " + p.getLast() +
                        " (" + p.getUsername() + ") has been deleted from database.");
            } else {
                ctx.status(400);
            }
        }


}