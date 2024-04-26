package PUTAPIs;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class UpdateUserTest {

    public  static String getRandomEmailId(){
        return "api"+System.currentTimeMillis()+"gmail.com";
    }


    

}


