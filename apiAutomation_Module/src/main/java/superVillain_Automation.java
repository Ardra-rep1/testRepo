
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
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
//         requestParams = new JSONObject();
//         requestParams.put("username", "Sati");
//         requestParams.put("score", 500);
//         resp = request.body(requestParams.toJSONString()).post("v1/user");
//         valid_response = resp.then();
//         valid_response.assertThat()
//                .statusCode(201)
//                .body("data", not(emptyArray()));
//       body = resp.getBody();
//       System.out.println("Post Request Response: " + body.asString());
//
//        Put Request
        requestParams = new JSONObject();
        requestParams.put("username", "Sati");
        requestParams.put("score", 200);
        resp = request.body(requestParams.toJSONString()).put("v1/user");
        valid_response = resp.then();
        valid_response.assertThat()
                .statusCode(204);

////        delete Request
//        requestParams = new JSONObject();
//        requestParams.put("username","Sati");
//        resp = request.body(requestParams.toJSONString()).delete("v1/user");
//        valid_response = resp.then();
//




//

////        user register
//         requestParams = new JSONObject();
//         requestParams.put("username","Sain");
//         requestParams.put("password","1234");
//         resp = request.body(requestParams.toJSONString()).post("auth/user/register");
//         valid_response = resp.then();
//         valid_response
//                 .assertThat()
//                .statusCode(200);



//         user login
        requestParams = new JSONObject();
        requestParams.put("username","helen");
        requestParams.put("password","1234");
        resp = request.body(requestParams.toJSONString()).post("auth/user/login");
        valid_response = resp.then();
        valid_response
                .assertThat()
                .statusCode(200)
                .body("data", not(emptyArray()));
       body = resp.getBody();
       System.out.println("Post Request Response: " + body.asString());



    }



}
