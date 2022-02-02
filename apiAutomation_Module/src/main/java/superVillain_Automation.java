
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;





import static org.hamcrest.Matchers.*;

public class superVillain_Automation {



    public static void main(String[] args) {
        ValidatableResponse valid_response;
        Response resp;
        ResponseBody body;
        JSONObject requestParams;

        RestAssured.baseURI = "https://supervillain.herokuapp.com/";
        RequestSpecification request = RestAssured.given().header("Authorization", superVillainObjects.token)
                .header("Content-Type", "application/json");

//      Verifying my token:
        resp = request
                .get("auth/verifytoken");
        valid_response = resp.then().statusCode(200);
        valid_response
                .assertThat()
                .statusCode(200)
                .body("data", not(emptyArray()));

        body = resp.getBody();
        System.out.println("Response Body is: " + body.asString());


//       Get Request

        resp = request.get("v1/user");
        valid_response = resp.then();
        valid_response.assertThat()
                .statusCode(200)
                .body("data", not(emptyArray()));
        body = resp.getBody();
        System.out.println("Get Request Response: " + body.asString());


//       post Request
         requestParams = new JSONObject();
         requestParams.put("username", "Sati");
         requestParams.put("score", 500);
         resp = request.body(requestParams.toJSONString()).post("v1/user");
         valid_response = resp.then();
         valid_response.assertThat()
                .statusCode(201)
                .body("data", not(emptyArray()));
       body = resp.getBody();
       System.out.println("Post Request Response: " + body.asString());

//        Put Request
        requestParams = new JSONObject();
        requestParams.put("username", "Sati");
        requestParams.put("score", 400);
        resp = request.body(requestParams.toJSONString()).put("v1/user");
        valid_response = resp.then();
        valid_response.statusCode(200)
                .body("data", not(emptyArray()));


        
       
       

    }


}
