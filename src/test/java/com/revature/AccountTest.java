package com.revature;

import com.revature.controllers.AccountController;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AccountTest {

    private Context ctx = mock(Context.class);
    private final AccountController accountController = new AccountController();

    @BeforeAll
    public static void setup() {
        JavalinApp app = new JavalinApp();
        app.start(8080);

    }

    @Test
    public void getAllAdminAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/people/{username}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(200, response.getStatus());

    }

    @Test
    public void getAllAdminEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/people/{username}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(200, response.getStatus());

    }

    @Test
    public void getAllAdminCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/people/{username}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());

    }

    @Test
    public void getAllAdminNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        HttpResponse response = Unirest.get("http://localhost:8080/people/{username}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(401, response.getStatus());

    }

    @Test
    public void getAllAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts")
                .headers(headers)
                .asString();
        assertEquals(200, response.getStatus());

    }

    @Test
    public void getAllEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts")
                .headers(headers)
                .asString();
        assertEquals(200, response.getStatus());

    }

    @Test
    public void getAllCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts")
                .headers(headers)
                .asString();
        assertEquals(403, response.getStatus());

    }

    @Test
    public void getAllNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts")
                .headers(headers)
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void getAllCustomerAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());

    }

    @Test
    public void getAllCustomerEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());

    }

    @Test
    public void getAllCustomerCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-bryanserfozo");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(200, response.getStatus());

    }

    @Test
    public void getAllCustomerNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(401, response.getStatus());

    }

    @Test
    public void deleteAccountAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-bryanserfozo");

        HttpResponse response = Unirest.delete("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .routeParam("accountId", "11")
                .asString();
        assertEquals(202, response.getStatus());
    }

    @Test
    public void deleteAccountEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        HttpResponse response = Unirest.delete("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .routeParam("accountId", "12")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void deleteAccountCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");

        HttpResponse response = Unirest.delete("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .routeParam("accountId", "13")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void deleteAccountNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        HttpResponse response = Unirest.delete("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .routeParam("accountId", "14")
                .asString();
        assertEquals(401, response.getStatus());
    }
}
