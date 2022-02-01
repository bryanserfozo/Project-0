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
    public static void setup(){
        JavalinApp app = new JavalinApp();
        app.start(8080);

    }



//    @Test
//    public void POST_to_create_users_throws_for_invalid_username() {
//        when(ctx.queryParam("username")).thenReturn(null);
//        UserController.create(ctx); // the handler we're testing
//    }


}
