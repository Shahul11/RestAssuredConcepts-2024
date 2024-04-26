package requestspecification;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;


public class ResponseSpecBuliderTest {

    public static ResponseSpecification get_res_spec_200_OK() {
        ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectHeader("Server", "cloudflare").build();
        return res_spec_200_ok;
    }

    public static ResponseSpecification get_res_spec_401_AUT_FAIL() {
        ResponseSpecification res_spec_400_ok = new ResponseSpecBuilder()
                .expectStatusCode(401)
                .expectHeader("Server", "cloudflare").build();
        return res_spec_400_ok;
    }


    public static ResponseSpecification get_res_spec_200_OK_WITH_BODY() {
        ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectHeader("Server", "cloudflare")
                .expectBody("$.size()", equalTo(10))
                .expectBody("id", hasSize(10)).build();

        return res_spec_200_ok;
    }


    @Test
    public void get_user_res_200_spec_Test() {
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .header("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .when().log().all()
                .get("/public/v2/users")
                .then().spec(get_res_spec_200_OK()).log().all();
    }


    @Test
    public void get_user_res_401_AUTH_FAIL_Test() {
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .header("Authorization", "Bearer 7b513e3a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .when().log().all()
                .get("/public/v2/users")
                .then().spec(get_res_spec_401_AUT_FAIL()).log().all();
    }

    @Test
    public void get_user_res_200_spec_WITH_BODY_Test() {
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .header("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .when().log().all()
                .get("/public/v2/users")
                .then().spec(get_res_spec_200_OK_WITH_BODY()).log().all();
    }

}
