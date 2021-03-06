package com.revature.controllers;

import com.revature.loggingSingleton.LoggingSingleton;
import com.revature.models.Person;
import com.revature.services.EncryptionService;
import com.revature.services.PersonService;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;

import java.util.Arrays;

public class AuthController {

    private final PersonService personService = new PersonService();
    private final EncryptionService encryptionService = new EncryptionService();
    private final LoggingSingleton logger = LoggingSingleton.getLogger();

    public void authenticateLogin(Context ctx){

        String username = ctx.formParam("username");
        String passwordParam = ctx.formParam("password");
        String password = encryptionService.encrypt(passwordParam);

        Person person =  personService.getByUsernameAndPassword(username, password);

        if(person==null){
            throw new UnauthorizedResponse("Incorrect credentials");
        } else {
            String simpleToken = person.getType()+"-"+ username;
            ctx.header("Authorization", simpleToken);
            ctx.status(200);
            logger.info(person.getType()+ " " + person.getFirst() + " " + person.getLast() +
                    " has signed in");
        }
    }

    public void authorizeAdminToken(Context ctx) {
        String authHeader = ctx.header("Authorization");
        String[] authParts = authHeader.split("-");

        if (authParts[0].equals("ADMIN")) {
            return;
        } else if (authParts[0].equals("EMPLOYEE")) {
            throw new ForbiddenResponse("Only Admin are able to access this feature");
        } else if (authParts[0].equals("CUSTOMER")) {
            throw new ForbiddenResponse("Only Admin are able to access this feature");
        }


        throw new UnauthorizedResponse("Please log in and try again, if you'd like to register, please" +
                " go to the /register path and add a post request with your details");


    }

    public void authorizeEmployeeToken(Context ctx) {
        String authHeader = ctx.header("Authorization");
        String[] authParts = authHeader.split("-");

        if (authParts[0].equals("ADMIN") || authParts[0].equals("EMPLOYEE")) {
            return;
        } else if (authParts[0].equals("CUSTOMER")) {
            throw new ForbiddenResponse("Only Employees are able to access this feature");
        }

        throw new UnauthorizedResponse("Please log in and try again, if you'd like to register, please" +
                " go to the /register path and add a post request with your details");


    }


    public void authorizeCustomerToken(Context ctx) {
        String authHeader = ctx.header("Authorization");
        String[] authParts = authHeader.split("-");

        String userParam = ctx.pathParam("username");

        if (authParts.length == 2) {
            if (authParts[1].equals(userParam) && authParts[0].equals("CUSTOMER")) {
                return;
            } else if (authParts[0].equals("ADMIN") || authParts[0].equals("EMPLOYEE") || !authParts.equals(userParam)) {
                throw new ForbiddenResponse("You are unable to access this page");
            }
            throw new UnauthorizedResponse("Please log in and try again, if you'd like to register, please" +
                    " go to the /register path and add a post request with your details");
        } else {
            throw new UnauthorizedResponse("Please log in and try again, if you'd like to register, please" +
                    " go to the /register path and add a post request with your details");
        }

    }

}
