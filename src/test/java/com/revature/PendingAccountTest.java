package com.revature;

import com.revature.controllers.AccountController;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PendingAccountTest {

    private Context ctx = mock(Context.class);

    @BeforeAll
    public static void setup(){
        JavalinApp app = new JavalinApp();
        app.start(8080);

    }

    @Test
    public void getPendingAccountsAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts/pending")
                .headers(headers)
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getPendingAccountsEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts/pending")
                .headers(headers)
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getPendingAccountsCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts/pending")
                .headers(headers)
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void getPendingAccountsNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts/pending")
                .headers(headers)
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void approvePendingAccountsAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("pendingId", "4");
        fields.put("decision", "approved");
        fields.put("username", "bryanserfozo");

        HttpResponse response = Unirest.post("http://localhost:8080/accounts/pending")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(201, response.getStatus());
    }


    @Test
    public void approvePendingAccountsCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("pendingId", "4");
        fields.put("decision", "approved");
        fields.put("username", "bryanserfozo");

        HttpResponse response = Unirest.post("http://localhost:8080/accounts/pending")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void approvePendingAccountsNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");
        Map<String, Object> fields = new HashMap<>();
        fields.put("pendingId", "4");
        fields.put("decision", "approved");
        fields.put("username", "bryanserfozo");

        HttpResponse response = Unirest.post("http://localhost:8080/accounts/pending")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void postPendingAccountAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/apply")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void postPendingAccountEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/apply")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void postPendingAccountCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-bryanserfozo");

        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/apply")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(201, response.getStatus());
    }

    @Test
    public void postPendingAccountNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/apply")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(401, response.getStatus());
    }
}
