package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2";

        response = given()

                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //    1. Verify the if the total record is 20
    @Test
    public void verifyTotalRecord() {
        response.body("size", equalTo(20));
    }

    //    2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void verifyId() {
        response.body("find{it.id==2322228}.name", equalTo("Chandrabhaga Malik"));
    }

    //    3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void getSinglename() {
        response.body("[1].name", equalTo("Shubhaprada Sethi"));
    }

    //    4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void verifyMultipleNames() {
        response.body("name", hasItems("Aaryan Sharma", "Dharitri Nayar", "Mayoor Adiga"));
    }

    //    5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void verifyEmailbyUserId() {
        response.body("findAll{it.id==2322229}.email", hasItem("shubhaprada_sethi@kris.example"));
    }

    //    6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void verifyStatus() {
        response.body("find{it.name=='Dwaipayana Varma Jr.'}.status", equalTo("active"));
    }

    //    7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void verifyGender() {
        response.body("find{it.name=='Kanchan Dwivedi'}.gender", equalTo("male"));
    }

}
