package automation.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Login {

    Response response;
    static String token;
    public String requestPost(String username, String password){
        String requestBody = "{username: "+username+", password: "+password+"}";

        return requestBody;
    }

    @BeforeTest
    public void setup(){
        baseURI="https://dummyjson.com";
    }

    @Test
    public void postInvalidCredentialLogin(){

        response = given().body(requestPost("JeanAnjos", "Jla123")).post("/auth/login");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");

        System.out.println("Test ok. User invalid! Status Code: " + statusCode);

        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
        Assert.assertEquals(message, "Invalid credentials");
    }

    @Test
    public void postInvalidCharacterLogin(){

        response = given().body(requestPost("*#$254", "Jla123")).post("/auth/login");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");

        System.out.println("Test ok. It doesnt accept special character! Status Code: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
        Assert.assertEquals(message, "Invalid credentials");
    }

    @Test
    public void postValidLogin(){

        JSONObject requestParams = new JSONObject();

        requestParams.put("username", "kminchelle");
        requestParams.put("password", "0lelplR");

        response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .when()
                .post("/auth/login");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("id");
        token = jsonPath.get("token");

        System.out.println("User loged successfully and the status code of the response is " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        Assert.assertEquals(id, 15);
    }
}
