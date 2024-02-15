package automation.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Products {

    Response response;

    @BeforeTest
    public static void setup(){
        baseURI="https://dummyjson.com";
    }

    @Test
    public void getAllProducts(){

        response = given().get("/products");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        int limit = jsonPath.get("limit");
        int total = jsonPath.get("total");

        System.out.println("Get All automation.api.Products is OK. Status Code: " + statusCode);

        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        Assert.assertEquals(limit, 30);
        Assert.assertEquals(total, 100);
    }

    @Test
    public void getProductsById(){

        String idUrl = "100";

        response = given().get("/products/" + idUrl);

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("id");
        String title = jsonPath.get("title");
        String description = jsonPath.get("description");


        System.out.println("Get automation.api.Products by ID is OK. Status Code: " + statusCode);

        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        Assert.assertEquals(id, 100);
        Assert.assertEquals(title, "Crystal chandelier maria theresa for 12 light");
        Assert.assertEquals(description, "Crystal chandelier maria theresa for 12 light");
    }

    @Test
    public void getProductsByIdNotExist(){

        String idUrl = "1000";

        response = given().get("/products/" + idUrl);

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");


        System.out.println("Test ok. Id doesnt exist! Status Code: " + statusCode);

        Assert.assertEquals(statusCode, HttpStatus.SC_NOT_FOUND);
        Assert.assertEquals(message, "Product with id '"+idUrl+"' not found");
    }

    @Test
    public void getAllProductsWithTokenExpired(){

        String tokenExpired = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTUsInVzZXJuYW1lIjoia21pbmNoZWxsZSIsImVtYWlsIjoia21pbmNoZWxsZUBxcS5jb20iLCJmaXJzdE5hbWUiOiJKZ" +
                "WFubmUiLCJsYXN0TmFtZSI6IkhhbHZvcnNvbiIsImdlbmRlciI6ImZlbWFsZSIsImltYWdlIjoiaHR0cHM6Ly9yb2JvaGFzaC5vcmcvSmVhbm5lLnBuZz9zZXQ9c2V0NCIsImlhdCI6MTcwNzUwNDExM" +
                "CwiZXhwIjoxNzA3NTA3NzEwfQ.96PK5QVgWTbm6DzyWuzKaWB1mwONtptTHIXa24jVA6c";

        response = given()
                .header("Authorization", tokenExpired)
                .get("/auth/products");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("name");
        String message = jsonPath.get("message");

        System.out.println("Test ok. Token Expired! Status Code: " + statusCode);

        Assert.assertEquals(statusCode, HttpStatus.SC_UNAUTHORIZED);
        Assert.assertEquals(name, "TokenExpiredError");
        Assert.assertEquals(message, "Token Expired!");
    }

    @Test
    public void getAllProductsWithTokenInvalid(){

        String tokenInvalid = "teste";

        response = given()
                .header("Authorization", tokenInvalid)
                .get("/auth/products");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("name");
        String message = jsonPath.get("message");

        System.out.println("Test ok. Token Invalid! Status Code: " + statusCode);

        Assert.assertEquals(statusCode, HttpStatus.SC_UNAUTHORIZED);
        Assert.assertEquals(name, "JsonWebTokenError");
        Assert.assertEquals(message, "Invalid/Expired Token!");
    }

    @Test
    public void getAllProductsWithTokenValid(){

        Login login = new Login();

        login.postValidLogin();

        response = given()
                .header("Authorization", login.token)
                .get("/auth/products");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        int limit = jsonPath.get("limit");
        int total = jsonPath.get("total");

        System.out.println("Get all products with authetication is work. Status Code: " + statusCode);

        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        Assert.assertEquals(limit, 30);
        Assert.assertEquals(total, 100);
    }

    @Test
    public void postAddProduct(){

        JSONObject requestParams = new JSONObject();

        requestParams.put("title", "Perfume Oil");
        requestParams.put("description", "Mega Discount, Impression of A...");
        requestParams.put("price", 13);
        requestParams.put("discountPercentage", 8.4);
        requestParams.put("rating", 4.26);
        requestParams.put("stock", 65);
        requestParams.put("brand", "Impression of Acqua Di Gio");
        requestParams.put("category", "fragrances");
        requestParams.put("thumbnail", "https://i.dummyjson.com/data/products/11/thumnail.jpg");

        response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .when()
                .post("/products/add");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("id");
        String title = jsonPath.get("title");

        System.out.println("Product added and the status code of the response is " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        Assert.assertEquals(id, 101);
        Assert.assertEquals(title, "Perfume Oil");
    }
}
