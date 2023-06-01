package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static ValidatableResponse response;
    static String name = TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "lily@gmail.com";

    @Test
    public void verifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName("john");
        userPojo.setEmail(email);
        userPojo.setGender("male");
        userPojo.setStatus("active");
        Response response = given()
                .header("Authorization", "Bearer e319da6e2718c70ab799e2b6645523809b0695e38428f3b7ef95bd2fbc36884f")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .post("/users");
        response.prettyPrint();
        response.then().log().all().statusCode(201);
    }
    @Test
    public void getUser(){
       Response response = given()
               .when()
               .get("/users");
       response.then().statusCode(200);
       response.prettyPrint();

    }
    @Test
    public void updateUserDetails(){
        UserPojo userPojo = new UserPojo();
        userPojo.setName("lily");
        userPojo.setEmail("prime1231@gmail");
        userPojo.setGender("female");
        userPojo.setStatus("active");
        Response response = given()
                .header("Authorization", "Bearer e319da6e2718c70ab799e2b6645523809b0695e38428f3b7ef95bd2fbc36884f")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .put("/users/2272464");
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }
    @Test
    public void deleteUser(){
        given()
                .header("Authorization", "Bearer e319da6e2718c70ab799e2b6645523809b0695e38428f3b7ef95bd2fbc36884f")
                .header("Content-Type", "application/json")
                .when()
                .delete("users/2272466")
                .then().statusCode(404);

    }


}
