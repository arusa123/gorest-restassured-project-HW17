package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
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


    // 1. Extract the All Ids
    @Test
    public void getAllTheId() {

        List<Integer> listOfIds = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void extractAllName() {
        List<String> names = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total names are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void nameOf5thobject() {
        String names = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void nameOfAllObjectInactive() {
        List<String> names = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all inactive objects are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void genderOfAllActiveObject() {
        List<String> names = response.extract().path("findAll{it.status=='active'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all active objects are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void namesOfFemaleGender() {
        List<String> femaleGender = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all objects are : " + femaleGender);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void emailOfAllInactive() {
        List<String> emailList = response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email of all inactive : " + emailList);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void idsOfAllMale() {
        List<String> maleList = response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of all male : " + maleList);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void statusOfAll() {
        List<String> statusList = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of all male : " + statusList);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void emailOfSingleUser() {
       List<String> emailofSingle = response.extract().path("findAll{it.name=='Deeptimay Guha'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email of Deeptimay Guha : " + emailofSingle);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get gender of id = 5471
    @Test
    public void getGender() {
        List<String> gender = response.extract().path("findAll{it.id==2322226}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of 2322226 is  : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }


}
