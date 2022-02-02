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

public class AuthTest {

    private Context ctx = mock(Context.class);
    private final AccountController accountController = new AccountController();

    @BeforeAll
    public static void setup(){
        JavalinApp app = new JavalinApp();
        app.start(8080);

    }

    @Test
    public void postLoginAttemptCorrect() {

        Map<String, String> headers = new HashMap<>();
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "bryanserfozo");
        fields.put("password", "extrasecretpassword");

        HttpResponse response = Unirest.post("http://localhost:8080/login")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void postLoginAttemptWrong() {

        Map<String, String> headers = new HashMap<>();
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "bryanserfozo");
        fields.put("password", "kindasecretpassword");

        HttpResponse response = Unirest.post("http://localhost:8080/login")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(401, response.getStatus());
    }
}
