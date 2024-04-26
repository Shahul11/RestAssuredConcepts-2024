package requestspecification;

import groovy.transform.ToString;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ReqResSpecTogetherTest {

    // Common Utility for Request Spec
    public static RequestSpecification user_Req_Spec() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .build();
        return requestSpec;
    }

    // Common Utility for Response Spec
    public static ResponseSpecification get_res_spec_200_OK() {
        ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectHeader("Server", "cloudflare").build();
        return res_spec_200_ok;
    }


  @Test
    public void getUser_With_Req_Resp_Spect_Test() {
        given().log().all()
                .spec(user_Req_Spec())
                .when()
                .get("/public/v2/users")
                .then().log().all()
                .assertThat()
                .spec(get_res_spec_200_OK());

    }


}
