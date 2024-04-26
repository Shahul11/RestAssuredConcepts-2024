package GETApis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GETAPIRequest_WITH_BDD {

    @Test
    public void getProdutsTest() {
        given()
                .when().log().all()
                .get("https://fakestoreapi.com/products")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("$.size()", equalTo(20))
                .and()
                .body("id", is(notNullValue()))
                .body("title", hasItem("Mens Casual Premium Slim Fit T-Shirts"));
    }


    @Test     // Second Example with Passing Headers
    public void getUserAPITest() {
        RestAssured.baseURI = "https://gorest.co.in/";
        given().log().all()
                .header("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .when().log().all()
                .get("public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("$.size()", equalTo(10));


    }

    @Test  // Third Example with QueryParams
    public void getProductWithLimit() {
        RestAssured.baseURI = "https://fakestoreapi.com";

        given().log().all()
                .when().log().all()
                .queryParams("Limit", 5)
                .get("/products")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }


    @Test  // Fourth Example Extract values from Json Body
    public void getProduct_With_Extract_Body() {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response = given().log().all()
                .when().log().all()
                .queryParams("limit", 5)
                .get("/products");

        JsonPath js = response.jsonPath();

        // Get the id of the first product
        int id = js.getInt("[0].id");
        System.out.println("Product Id is " + id);

        // Get the productTitle
        String productTitle = js.getString("[0].title");
        System.out.println("Product title is " + productTitle);

        // Get the price
        double productPrice = js.getDouble("[0].price");
        System.out.println("Product price is " + productPrice);

        //Get the Product Count
        float productCount = js.getFloat("[0].rating.count");
        System.out.println("Product count  is " + productCount);

    }

    @Test  // 5th  Example Extract values from Json Body all arrays
    public void getProduct_With_Extract_Body_Array() {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response = given().log().all()
                .when().log().all()
                .queryParams("limit", 5)
                .get("/products");

        JsonPath js = response.jsonPath();

        // This is one way to print
        List<Integer> idList = js.getList("id");
        System.out.println("All the id List is " + idList);
        List<String> allTitleNames = js.getList("title");
        System.out.println("All the Title Names is " + allTitleNames);
        List<Object> allRate = js.getList("rating.rate");
        System.out.println("All the Rating rate is " + allRate);
        List<Integer> allRateCount = js.getList("rating.count");
        System.out.println("All the Rating Count is " + allRateCount);

        System.out.println("=========================");

        // More efficient way to print is as follows
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            String name = allTitleNames.get(i);
            Object rate = allRate.get(i);
            int count = allRateCount.get(i);
            System.out.println("ID: " + id + " " + "Name: " + name + " " + "Rate " + rate + " Count " + count);
        }

    }

    @Test
    public void getUserAPI_With_Extract_Body_JSON_Array() {
        RestAssured.baseURI = "https://gorest.co.in/";
        Response response = given().log().all()
                .header("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .when().log().all()
                .get("public/v2/users");

        JsonPath js = response.jsonPath();
        System.out.println(js.getString("id"));
        System.out.println(js.getString("email"));

    }

    @Test  // Extract Method used only once from chain method
    public void getUserAPI_With_Extract_Body_JSON_EXTRACT_METHOD() {
        RestAssured.baseURI = "https://gorest.co.in/";
        int idValue = given().log().all()
                .header("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .when().log().all()
                .get("public/v2/users/6798320")
                .then().extract().path("id");
        System.out.println("===========> " + idValue);

    }


    @Test  // Extract Method to fetch multiple values
    public void getUserAPI_With_Extract_Body_JSON_EXTRACT_METHOD1() {
        RestAssured.baseURI = "https://gorest.co.in/";
        Response response = given().log().all()
                .header("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649")
                .when().log().all()
                .get("public/v2/users/6798320").then().extract().response();

        // This also works less code efficient
//        int id= response.then().extract().path("id");
//        String email= response.then().extract().path("email");

        //Little Code efficient would be
        int id = response.path("id");
        String email = response.path("email");

        System.out.println("ID: " + id + " " + "email: " + email);
    }

}
