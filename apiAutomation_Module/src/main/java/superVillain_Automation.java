
import io.restassured.RestAssured;
import io.restassured.response.Response;

import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.HashMap;

import static org.hamcrest.Matchers.*;

public class superVillain_Automation {



    @Test(priority = 0)
    public void  verifyToken(){
        ValidatableResponse valid_response;
        Response resp;
        RestAssured.baseURI = "https://supervillain.herokuapp.com/";
        RequestSpecification request = RestAssured
                .given()
                .header("Authorization", superVillainObjects.token)
                .header("Content-Type", "application/json");

//      Verifying my token:
        resp = request
                .get("auth/verifytoken");
        valid_response = resp.then();

        valid_response
                .assertThat()
                .statusCode(200)
                .body("data", not(emptyArray()))
                .log().body()
                .extract().response();
       Assert.assertEquals(resp.statusCode(),200);

    }

    @Test(priority = 1)
    public void getUserDetails(){

        ValidatableResponse valid_response;
        Response resp;
        RestAssured.baseURI = "https://supervillain.herokuapp.com/";
        RequestSpecification request = RestAssured
                .given()
                .header("Authorization", superVillainObjects.token)
                .header("Content-Type", "application/json");
//               Get Request
        resp = request.get("v1/user");
        valid_response = resp.then();
        valid_response.assertThat()
                .statusCode(200)
                .body("data", not(emptyArray()))
                .log().body()
                .extract().response();
        Assert.assertEquals(resp.statusCode(),200);


    }
    @Test(priority = 0)
    public void postUserDetails(){

        ValidatableResponse valid_response;
        Response resp;
        RestAssured.baseURI = "https://supervillain.herokuapp.com/";
        RequestSpecification request = RestAssured
                .given()
                .header("Authorization", superVillainObjects.token)
                .header("Content-Type", "application/json");
//               post Request
                JSONObject requestParams;
         requestParams = new JSONObject();
         requestParams.put("username", "hala");
         requestParams.put("score", 500);
         resp = request.body(requestParams.toJSONString()).post("v1/user");
         valid_response = resp.then();
         valid_response.assertThat()
                .statusCode(201)
                .body("data", not(emptyArray()))
                 .body("status",equalTo("success"))
                 .body("message",equalTo("User added."))
                 .log().body()
                 .extract().response();

        Assert.assertEquals(resp.statusCode(),201);
        String jsonString = resp.asString();
        Assert.assertEquals(jsonString.contains("User added."),true);

    }
    @Test(priority = 2)
    public static void putUserDetails(){

        ValidatableResponse valid_response;
        Response resp;
        RestAssured.baseURI = "https://supervillain.herokuapp.com/";
        RequestSpecification request = RestAssured
                .given()
                .header("Authorization", superVillainObjects.token)
                .header("Content-Type", "application/json");
//        Put Request
        JSONObject requestParams;
        requestParams = new JSONObject();
        requestParams.put("username", "Sati");
        requestParams.put("score", 200);
        resp = request.body(requestParams.toJSONString()).put("v1/user");
        valid_response = resp.then();
        valid_response
                .assertThat()
                .statusCode(204);
        Assert.assertEquals(resp.statusCode(),204);
    }

    @Test
    public void userRegister(){

        ValidatableResponse valid_response;
        Response resp;
        RestAssured.baseURI = "https://supervillain.herokuapp.com/";
        RequestSpecification request = RestAssured
                .given()
                .header("Authorization", superVillainObjects.token)
                .header("Content-Type", "application/json");

//                user register
         JSONObject requestParams;
         requestParams = new JSONObject();
         requestParams = new JSONObject();
         requestParams.put("username","Ariya");
         requestParams.put("password","1234");
         resp = request.body(requestParams.toJSONString()).post("auth/user/register");
         valid_response = resp.then();
         valid_response
                 .assertThat()
                .statusCode(200);
        Assert.assertEquals(resp.statusCode(),200);



    }
    @Test
    public void userLogin(){

        ValidatableResponse valid_response;
        Response resp;
        RestAssured.baseURI = "https://supervillain.herokuapp.com/";
        RequestSpecification request = RestAssured
                .given()
                .header("Authorization", superVillainObjects.token)
                .header("Content-Type", "application/json");

        ////         user login
        JSONObject data = new JSONObject();
        data.put("username","helen");
        data.put("password","1234");
        resp = request.body(data.toJSONString()).post("auth/user/login");
        valid_response = resp.then();
        valid_response
                .assertThat()
                .statusCode(200)
                .body("data", not(emptyArray()))
                .body("expiresIn",equalTo("3 min"))
                .log().body()
                .extract().response();

        Assert.assertEquals(resp.statusCode(),200);



    }

    #3
    @Test
    public void deleteUserDetails(){
        ValidatableResponse valid_response;
        Response resp;
        RestAssured.baseURI = "https://supervillain.herokuapp.com/";
        RequestSpecification request = RestAssured
                .given()
                .header("Authorization", superVillainObjects.token)
                .header("Content-Type", "application/json");

        JSONObject data = new JSONObject();
        data.put("username","helania");
        resp = request.body(data.toJSONString()).delete("v1/user");
        valid_response = resp.then();
//        valid_response
//                .assertThat()
//                .statusCode(200);

//        Assert.assertEquals(resp.statusCode(),200);

    }



}
