package POSTAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Credentials;

import static io.restassured.RestAssured.given;

public class BookingAuthWithPOJOTest {

    //POJO  Plain  old java object
    // Pojo cannot extend any class
    // Need to use encapsulation , Getter/Setter Properties
    //Pirvate Class variable according to my Json body

    // Serilization -->Java Object to other object  format : file, media , json/xml/text/ pdf -
    //pojo to json ---> Serialization
    //json to pojo  = De -Serialization





    @Test
    public void getBookingAuthToken_With_JSON_String() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Credentials creds = new Credentials("admin", "password123");
        String tokenId =
                given().log().all()
                        .contentType(ContentType.JSON)
                        .body(creds)
                        .when().log().all()
                        .post("/auth")
                        .then().log().all()
                        .assertThat()
                        .statusCode(200)
                        .extract().path("token");
        System.out.println("Token Id is " + tokenId);
        Assert.assertNotNull(tokenId);

    }
}
