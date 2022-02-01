package com.revature.controllers;

import com.revature.models.Person;
import com.revature.services.PersonService;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;

public class AuthController {

    private final PersonService personService = new PersonService();

    public void authenticateLogin(Context ctx){

        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        Person person =  personService.getByUsernameAndPassword(username, password);

        if(person==null){
            throw new UnauthorizedResponse("Incorrect credentials");
        } else {
            String simpleToken = person.getType()+"-"+ username;
            ctx.header("Authorization", simpleToken);
            ctx.status(200);
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

        try {
            if (authHeader != null) {
                if (authParts[1].equals(userParam) && authParts[0].equals("CUSTOMER")) {
                    return;
                } else {
                    throw new ForbiddenResponse("You are unable to access this page");
                }
            }
        } catch (Exception e){
            throw new UnauthorizedResponse("Please log in and try again, if you'd like to register, please" +
                    " go to the /register path and add a post request with your details");
        }


    }

}
