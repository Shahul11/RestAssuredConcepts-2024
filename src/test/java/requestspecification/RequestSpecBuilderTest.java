package requestspecification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class RequestSpecBuilderTest {


    @Test
    public void getUser_With_Request_Spec() {
        RestAssured.given().log().all()
                .spec(user_Req_Spec())
                .get("/public/v2/users")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void getUser_With_Param_Request_Spec() {
        RestAssured.given().log().all()
                .queryParam("name","shahul")
                .queryParam("status","active")
                .spec(user_Req_Spec())
                .get("/public/v2/users")
                .then().log().all()
                .statusCode(200);
    }


    // Making has common utility now
    public static RequestSpecification user_Req_Spec() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .build();
        return requestSpec;
    }


}
