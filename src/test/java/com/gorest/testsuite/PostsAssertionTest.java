package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest {
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

//     1. Verify the if the total record is 10
    @Test
    public void verifyTotalRecord(){
        response.body("size",equalTo(25));
    }


    //     2. Verify the if the title of id = 2272457 is equal to ”.”
    @Test
    public void verifytitleOfId() {
        response.body("findAll{it.id==39951}.title", hasItem("Astrum consequuntur voluptas carcer versus optio."));
    }
//     3. Check the single user_id in the Array list (5522)
    @Test
    public void singleIdList(){

        response.body("[0].id",equalTo(39951));
    }
//    4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void multipleIds(){
        response.body("id",hasItems( 39947, 39945, 39938));
    }
//    5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
//    spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
//    Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
//    Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
//    antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
//    cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
//    adflicto. Assentator umquam pel."”
    @Test
    public void verifyBody(){
        response.body("findAll{it.user_id==2322259}.body",hasItem("Utpote enim xiphias. Virgo at statua. Benevolentia triginta copia. Voluptatem comis defluo. Amita volva creator. Saepe possimus speculum. Nihil tamisium denique. Incidunt infit suasoria. Autus temeritas sortitus. Cursus eos natus. Adipiscor conduco tandem. Somniculosus denuncio derelinquo."));
    }

}
