package automation.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Users {

    @Test
    public void getUsers(){
        baseURI="https://dummyjson.com";

        Response response;
        response = given().get("/users");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        int limit = jsonPath.get("limit");
        int total = jsonPath.get("total");

        System.out.println("Status code of the response is " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        Assert.assertEquals(limit, 30);
        Assert.assertEquals(total, 100);
    }
}
