package products;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetProduct_TEST_USING_POJO {
    @Test
    public void getProdutsTest_POJO() {
        Response response = given()
                .when().log().all()
                .get("https://fakestoreapi.com/products");

        // Json to POJO  mapping - De-Serilization
        ObjectMapper mapper = new ObjectMapper();
        try {
            Product[] product = mapper.readValue(response.getBody().asString(), Product[].class);
            for (Product p : product) {
                System.out.println("ID: " + p.getId());
                System.out.println("Title: " + p.getTitle());
                System.out.println("Price: " + p.getPrice());
                System.out.println("Description: " + p.getDescription());
                System.out.println("Category: " + p.getCategory());
                System.out.println("Image: " + p.getImage());
                System.out.println("Rating: " + p.getRating().getRate());  //p.getRating() from outer class will return
                //Rating class object, using that we call the inner Class Rating getRate() method
                System.out.println("Rating: " + p.getRating().getCount());
                System.out.println("---------------------");

            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void getProdutsTest_POJO_LOMBOK() {
        Response response = given()
                .when().log().all()
                .get("https://fakestoreapi.com/products");

        // Json to POJO  mapping - De-Serilization
        ObjectMapper mapper = new ObjectMapper();
        try {
            ProductLombok[] product = mapper.readValue(response.getBody().asString(), ProductLombok[].class);
            for (ProductLombok p : product) {
                System.out.println("ID: " + p.getId());
                System.out.println("Title: " + p.getTitle());
                System.out.println("Price: " + p.getPrice());
                System.out.println("Description: " + p.getDescription());
                System.out.println("Category: " + p.getCategory());
                System.out.println("Image: " + p.getImage());
                System.out.println("Rating: " + p.getRating().getRate());  //p.getRating() from outer class will return
                //Rating class object, using that we call the inner Class Rating getRate() method
                System.out.println("Rating: " + p.getRating().getCount());
                System.out.println("---------------------");

            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
