package com.revature;

import com.revature.controllers.AccountController;
import com.revature.controllers.PersonController;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PersonTest {
    private Context ctx = mock(Context.class);
    private final PersonController personController = new PersonController();

    @BeforeAll
    public static void setup() {
        JavalinApp app = new JavalinApp();
        app.start(8080);

    }

    @Test
    public void getAllAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/people")
                .headers(headers)
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getAllEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/people")
                .headers(headers)
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getAllCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/people")
                .headers(headers)
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void getAllNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        HttpResponse response = Unirest.get("http://localhost:8080/people")
                .headers(headers)
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void postPeopleAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "employee");
        fields.put("first", "Dwayne");
        fields.put("last", "Therockjohnson");
        fields.put("email", "Dwayne.therockjohnson@built.com");
        fields.put("username", "DwayneisAwesome");
        fields.put("password", "Dwayneisawesomelysecure");

        HttpResponse response = Unirest.post("http://localhost:8080/people/admin")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(201, response.getStatus());
    }

    @Test
    public void deletePeopleAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "DwayneisAwesome");

        HttpResponse response = Unirest.delete("http://localhost:8080/people/admin")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void postPeopleEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "employee");
        fields.put("first", "Dwayne");
        fields.put("last", "Therockjohnson");
        fields.put("email", "Dwayne.therockjohnson@built.com");
        fields.put("username", "DwayneisAwesome");
        fields.put("password", "Dwayneisawesomelysecure");

        HttpResponse response = Unirest.post("http://localhost:8080/people/admin")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void deletePeopleEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "DwayneisAwesome");

        HttpResponse response = Unirest.delete("http://localhost:8080/people/admin")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void postPeopleCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "employee");
        fields.put("first", "Dwayne");
        fields.put("last", "Therockjohnson");
        fields.put("email", "Dwayne.therockjohnson@built.com");
        fields.put("username", "DwayneisAwesome");
        fields.put("password", "Dwayneisawesomelysecure");

        HttpResponse response = Unirest.post("http://localhost:8080/people/admin")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void deletePeopleCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "DwayneisAwesome");

        HttpResponse response = Unirest.delete("http://localhost:8080/people/admin")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void postPeopleNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");
        Map<String, Object> fields = new HashMap<>();
        fields.put("type", "employee");
        fields.put("first", "Dwayne");
        fields.put("last", "Therockjohnson");
        fields.put("email", "Dwayne.therockjohnson@built.com");
        fields.put("username", "DwayneisAwesome");
        fields.put("password", "Dwayneisawesomelysecure");

        HttpResponse response = Unirest.post("http://localhost:8080/people/admin")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void deletePeopleNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "DwayneisAwesome");

        HttpResponse response = Unirest.delete("http://localhost:8080/people/admin")
                .headers(headers)
                .fields(fields)
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void getPersonInfoAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/people/{username}/information")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getPersonInfoEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/people/{username}/information")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getPersonInfoCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/people/{username}/information")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void getPersonInfoNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        HttpResponse response = Unirest.get("http://localhost:8080/people/{username}/information")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void putAdminMethodUpdatePasswordAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "bryanserfozo");
        fields.put("old password", "extrasecretpassword");
        fields.put("new password", "extrasecretpassword");

        HttpResponse response = Unirest.put("http://localhost:8080/people/{username}/information")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void putAdminMethodUpdatePasswordEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "bryanserfozo");
        fields.put("old password", "extrasecretpassword");
        fields.put("new password", "extrasecretpassword");

        HttpResponse response = Unirest.put("http://localhost:8080/people/{username}/information")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void putAdminMethodUpdatePasswordCustomer() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "bryanserfozo");
        fields.put("old password", "extrasecretpassword");
        fields.put("new password", "extrasecretpassword");

        HttpResponse response = Unirest.put("http://localhost:8080/people/{username}/information")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void putAdminMethodUpdatePasswordNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "bryanserfozo");
        fields.put("old password", "extrasecretpassword");
        fields.put("new password", "extrasecretpassword");

        HttpResponse response = Unirest.put("http://localhost:8080/people/{username}/information")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void getUserInfoAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}/information")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void getUserInfoEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}/information")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void getUserInfoCustomerRight() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-bryanserfozo");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}/information")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getUserInfoCustomerWrong() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}/information")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void getUserInfoNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");

        HttpResponse response = Unirest.get("http://localhost:8080/user/{username}/information")
                .headers(headers)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void putCustomerMethodUpdatePasswordAdmin() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "bryanserfozo");
        fields.put("old password", "extrasecretpassword");
        fields.put("new password", "extrasecretpassword");

        HttpResponse response = Unirest.put("http://localhost:8080/user/{username}/information")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void putCustomerMethodUpdatePasswordEmployee() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "EMPLOYEE-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "bryanserfozo");
        fields.put("old password", "extrasecretpassword");
        fields.put("new password", "extrasecretpassword");

        HttpResponse response = Unirest.put("http://localhost:8080/user/{username}/information")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void putCustomerMethodUpdatePasswordCustomerRight() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-bryanserfozo");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "bryanserfozo");
        fields.put("old password", "extrasecretpassword");
        fields.put("new password", "extrasecretpassword");

        HttpResponse response = Unirest.put("http://localhost:8080/user/{username}/information")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void putCustomerMethodUpdatePasswordCustomerWrong() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "CUSTOMER-Test");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "bryanserfozo");
        fields.put("old password", "extrasecretpassword");
        fields.put("new password", "extrasecretpassword");

        HttpResponse response = Unirest.put("http://localhost:8080/user/{username}/information")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(403, response.getStatus());
    }

    @Test
    public void putCustomerMethodUpdatePasswordNoAuth() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "");
        Map<String, Object> fields = new HashMap<>();
        fields.put("username", "bryanserfozo");
        fields.put("old password", "extrasecretpassword");
        fields.put("new password", "extrasecretpassword");

        HttpResponse response = Unirest.put("http://localhost:8080/user/{username}/information")
                .headers(headers)
                .fields(fields)
                .routeParam("username", "bryanserfozo")
                .asString();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void postRegister() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "ADMIN-Test");

        Map<String, Object> fields = new HashMap<>();
        fields.put("first", "Willem");
        fields.put("last", "Dafoe");
        fields.put("email", "notgreengoblin@gmail.com");
        fields.put("username", "ILoveSpiderMan");
        fields.put("password", "KillSpiderMan");


        HttpResponse response = Unirest.post("http://localhost:8080/register")
                .fields(fields)
                .asString();
        assertEquals(201, response.getStatus());

        HttpResponse response2 = Unirest.delete("http://localhost:8080/people/admin")
                .headers(headers)
                .fields(fields)
                .asString();
    }
}
