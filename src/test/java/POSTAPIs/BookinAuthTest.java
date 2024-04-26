package POSTAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;


public class BookinAuthTest {

    @Test
    public void getBookingAuthToken_With_JSON_String() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String tokenId =
                given().log().all()
                        .contentType(ContentType.JSON)
                        .body("{\n" +
                                "    \"username\" : \"admin\",\n" +
                                "    \"password\" : \"password123\"\n" +
                                "}")
                        .when()
                        .post("/auth")
                        .then().log().all()
                        .assertThat()
                        .statusCode(200)
                        .extract().path("token");
        System.out.println("Token Id is " + tokenId);


    }

    @Test
    public void getBookingAuthToken_With_JSON_FILE() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String tokenId =
                given()
                        .contentType(ContentType.JSON)
                        .body(new File("K:\\Automation\\RSCoreConcepts\\src\\main\\resources\\dataFolder\\basicAuth.json"))
                        .when()
                        .post("/auth")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().path("token");
        System.out.println("Token Id is " + tokenId);


    }

    @Test
    public void addUser_Test() {
        RestAssured.baseURI = "https://gorest.co.in";

        //1.  Add the User
        int userID = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .body(new File("K:\\Automation\\RSCoreConcepts\\src\\main\\resources\\dataFolder\\AddUser.json"))
                .when().log().all()
                .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .and()
                .body("name", equalTo("Shah1"))
                .extract().path("id");
        System.out.println("User id is------->" + userID);

        //2. Get the same user and verify it

        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .when().log().all()
                .get("/public/v2/users/" + userID)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(userID));

    }

}
