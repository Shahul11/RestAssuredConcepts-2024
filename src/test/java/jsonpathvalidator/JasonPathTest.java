package jsonpathvalidator;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;


public class JasonPathTest {

    @Test
    public void getCircuit_With_JSON_PATH_Library_TEST() {
        RestAssured.baseURI = "http://ergast.com";
        Response response = given().log().all()
                .when().log().all()
                .get("api/f1/2016/circuits.json");

        String jsonResponse = response.asString();
        List<String> countryList = JsonPath.read(jsonResponse, "$..Circuits..country");

        //Reading the Country Lis using the JsonPath of JayWay library
        System.out.println(countryList.size());
        System.out.println(countryList);

        //Reading the Circuits
        int numberOfCircuits = JsonPath.read(jsonResponse, "$..MRData.CircuitTable.Circuits.length()");
        System.out.println("Total Number of Circusts are " + numberOfCircuits);
    }


    @Test
    public void getProduts_With_JSON_PATH_Library_TEST() {
        Response response = given()
                .when().log().all()
                .get("https://fakestoreapi.com/products");

        String jsonResponse = response.asString();
        System.out.println(jsonResponse);

        List<Float> ratingRateLessthan3 = JsonPath.read(jsonResponse, "$[?(@.rating.rate<3)].rating.rate");
        System.out.println(ratingRateLessthan3);

        System.out.println("=======================");

        // One thing to remember, in https://jsonpath.com/ the locator will work correctly i.e $[?(@.category == 'jewelery')].[title,price]
        //However in Java code we need to escp seq character  $[?(@.category == 'jewelery')].[\"title\",\"price\"]"

        // with two attributes
        List<Map<String, Object>> jwelleryList = JsonPath.read(jsonResponse, "$[?(@.category == 'jewelery')].[\"title\",\"price\"]");
        System.out.println(jwelleryList);

        for (Map<String, Object> product : jwelleryList) {
            String title = (String) product.get("title");
            Object price = (Object) product.get("price");
            System.out.println("title: " + title);
            System.out.println("price: " + price);
        }

        // with three attributes
        List<Map<String, Object>> jwelleryList2 = JsonPath.read(jsonResponse, "$[?(@.category == 'jewelery')].[\"title\",\"price\",\"id\"]");
        System.out.println(jwelleryList);

        for (Map<String, Object> product : jwelleryList2) {
            String title = (String) product.get("title");
            Object price = (Object) product.get("price");
            int id = (Integer) product.get("id");

            System.out.println("title: " + title);
            System.out.println("price: " + price);
            System.out.println("id: " + id);
        }


    }

}
