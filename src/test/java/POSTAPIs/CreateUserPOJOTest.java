package POSTAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import pojo.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserPOJOTest {

    public static String generateRandomEmailId() {
        return "apiautomation" + System.currentTimeMillis() +"@gmail.com";
    }
    @Test
    public void addUser_Test() {
        RestAssured.baseURI = "https://gorest.co.in";
        User user = new User("Shahul", "male", generateRandomEmailId(), "active");

        //1.  Add the User
        int userID = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .body(user)
                .when().log().all()
                .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .and()
                .body("name", equalTo(user.getName()))
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
                .body("id", equalTo(userID))
                .body("name", equalTo(user.getName()))
                .body("status", equalTo(user.getStatus()));

    }
}


//So we have learnt 3 ways to pass  the data to post call
//1. Directly Supply the JSON string
//2.  Pass the JSON file
//3. POJO - java Object - to Json using the library Jackson-data bind