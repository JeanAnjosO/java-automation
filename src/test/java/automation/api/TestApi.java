package automation.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestApi {

    @Test
    public void getTest(){
        baseURI="https://dummyjson.com";

        Response response;
        response = given().get("/test");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        String status = jsonPath.get("status");

        System.out.println("Status code of the response is " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        Assert.assertEquals(status, "ok");

    }
}
