package com.revature;

import com.revature.controllers.AccountController;
import com.revature.controllers.TransactionController;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TransactionTest {
    private Context ctx = mock(Context.class);
    private final TransactionController transactionController = new TransactionController();

    @BeforeAll
    public static void setup(){
        JavalinApp app = new JavalinApp();
        app.start(8080);

    }

    @Test
    public void getAllTransAdmin() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getAllTransEmployee() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getAllTransCustomer() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void getAllTransNoAuth() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        HttpResponse response = Unirest.get("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void postDepositAdminAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "deposit");
        fields.put("amount", "5");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(201, response.getStatus());
    }

    @Test
    public void postDepositAdminEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "deposit");
        fields.put("amount", "5");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void postDepositAdminCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "deposit");
        fields.put("amount", "5");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void postDepositAdminNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "deposit");
        fields.put("amount", "5");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void testFaultyWithdrawal() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-TEST");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "withdrawal");
        fields.put("amount", "500000");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    public void testCorrectWithdrawal() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-TEST");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "withdrawal");
        fields.put("amount", "50");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(201, response.getStatus());
    }

    @Test
    public void testFaultyTransfer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-TEST");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "transfer");
        fields.put("amount", "50");
        fields.put("secondAccountID", "3");


        HttpResponse response = Unirest.post("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    public void testCorrectTransfer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-TEST");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "transfer");
        fields.put("amount", "50");
        fields.put("secondAccountID", "11");


        HttpResponse response = Unirest.post("http://localhost:8080/accounts/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("accountId", "16")
                .asString();
        assertEquals(201, response.getStatus());
    }

    @Test
    public void getAllTransCustomerAdmin() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void getAllTransCustomerEmployee() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void getAllTransCustomerCustomer() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-bryanserfozo");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getAllTransCustomerNoAuth() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void postDepositCustomerAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "deposit");
        fields.put("amount", "5");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void postDepositCustomerEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "deposit");
        fields.put("amount", "5");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void postDepositCustomerCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-bryanserfozo");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "deposit");
        fields.put("amount", "5");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(201, response.getStatus());
    }

    @Test
    public void postDepositCustomerNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "deposit");
        fields.put("amount", "5");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void testFaultyWithdrawalCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-bryanserfozo");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "withdrawal");
        fields.put("amount", "500000");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    public void testCorrectWithdrawalCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-bryanserfozo");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "withdrawal");
        fields.put("amount", "50");
        fields.put("secondAccountID", "");


        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(201, response.getStatus());
    }

    @Test
    public void testFaultyTransferCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-bryanserfozo");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "transfer");
        fields.put("amount", "50");
        fields.put("secondAccountID", "3");


        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(400, response.getStatus());
    }

    @Test
    public void testCorrectTransferCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-bryanserfozo");

        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "transfer");
        fields.put("amount", "50");
        fields.put("secondAccountID", "11");


        HttpResponse response = Unirest.post("http://localhost:8080/user/{username}/{accountId}")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .routeParam("accountId", "16")
                .asString();
        assertEquals(201, response.getStatus());
    }
}
