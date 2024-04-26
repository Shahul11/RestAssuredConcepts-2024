package GETApis;

import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


public class GETAPI_WITH_QUERY_PARAM {

    @Test  // Showing how to use path Param and Navigating to the array if response
    public void getCircuitWithLimit() {
        RestAssured.baseURI = "http://ergast.com";

        given().log().all()
                .when().log().all()
                .pathParam("year", "2017")
                .get("api/f1/{year}/circuits.json")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("MRData.CircuitTable.season", equalTo("2017"))
                .and()
                .body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));

    }

    @DataProvider
    public Object[][] getCircuitYearData() {
        return new Object[][]{
                {"2016", 21},
                {"2017", 20},
                {"1996", 16},
                {"2023", 22},
        };
    }


    @Test (dataProvider = "getCircuitYearData")
    public void getCircuit_DATA_WITH_DATA_PROVIDER(String seasonYear, int totalCircuits) {
        RestAssured.baseURI = "http://ergast.com";

        given().log().all()
                .when().log().all()
                    .pathParam("year",seasonYear)
                .get("api/f1/{year}/circuits.json")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("MRData.CircuitTable.season", equalTo(seasonYear))
                .body("MRData.CircuitTable.Circuits.circuitId", hasSize(totalCircuits));

    }

}
