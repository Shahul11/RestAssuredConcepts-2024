package GETApis;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GETAPIRequest_NON_BDD {

    RequestSpecification request;

    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "https://gorest.co.in/";
        request = RestAssured.given().log().all();
        request.header("Authorization", "Bearer 7b513e32051b9d502026377a37b1ff5df54f26aaa47bb0ea7c434f5a453e0649").log().all();
    }

    @Test
    public void getUserAPITest() {

        Response response = request.get("public/v2/users");
        // Status Code
        int statusCode = response.statusCode();
        System.out.println("status code " + statusCode);
        Assert.assertEquals(statusCode, 200);

        //Status Message
        String statusMessage = response.statusLine();
        System.out.println(statusMessage);

        //fetch Response headers
        String contentType = response.header("Content-Type");
        System.out.println(contentType);

        // Printing all the headers
        List<Header> headersList = response.headers().asList();
        for (Header h : headersList) {
            System.out.println(h.getName() + ": " + h.getValue());
        }

        //fetch body in pretty format . it will print automatically on cansole
       // response.prettyPrint();
    }

    @Test
    public void getUserWithQueryParamaterAPITest() {

        request.queryParam("name", "shahul");
        Response response = request.get("public/v2/users");

        int statusCode = response.statusCode();
        System.out.println("status code " + statusCode);
        Assert.assertEquals(statusCode, 200);

        System.out.println(response.prettyPrint());
    }

    @Test
    public void getUserWithQueryParamater_WithHashMap_APITest() {

        Map<String, String> queryParamsMap = new HashMap<String, String>();
        queryParamsMap.put("name", "shahul");
        queryParamsMap.put("gender", "male");
        request.queryParams(queryParamsMap);

        Response response = request.get("public/v2/users");

        int statusCode = response.statusCode();
        System.out.println("status code " + statusCode);
        Assert.assertEquals(statusCode, 200);

        System.out.println(response.prettyPrint());

    }
}
