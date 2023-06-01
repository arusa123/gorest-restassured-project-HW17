package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2";

        response = given()

                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //    1. Extract the title
    @Test
    public void extractTitle() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void extractTheTotlRecrod() {
        int total = response.extract().path("size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total records are : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void extractTheRecrodOf15th() {
        String body = response.extract().path("[15].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of record 15th is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void extractAllTheUserId() {
        List<String> userId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The record of all user ids are : " + userId);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void extractTheTitleOfAllRecords() {
        List<String> titlesOfRecords = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title is : " + titlesOfRecords);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Extract the title of all records whose user_id = 5456
    @Test
    public void extractTheRecrodOfuserid() {
        List<String> userIds = response.extract().path("findAll{it.user_id==2322259}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The record of user_Id 2322259 is  : " + userIds);
        System.out.println("------------------End of Test---------------------------");
    }
//7. Extract the body of all records whose id = 2671
@Test
public void extractTheRecrodOfId() {
    List<String> userRecord = response.extract().path("findAll{it.user_id==2322259}.body");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The record of user_Id 2322259 is  : " + userRecord);
    System.out.println("------------------End of Test---------------------------");
}

}

