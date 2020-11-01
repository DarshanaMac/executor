package com.executor.qa.serviceautomation;

import com.executor.qa.automation.uiautomation.base.ConfigService;
import com.executor.qa.uiautomation.serviceclient.RequestBuilder;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class DemoServiceTest extends ConfigService {

    @Test(testName = "Post request test", dataProvider = "getDefaultUser")
    @Step("Post request test")
    public void postRequest(Map<String, String> testData) {

        RequestBuilder requestBuilder = new RequestBuilder();
        RequestSpecification requestSpecification = requestBuilder.addPathParam("id", testData.get("userid")).build();
        Response response = given().spec(requestSpecification).when().get("https://jsonplaceholder.typicode.com/posts/{id}");
        String bodyAsString = response.asString();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Correct status code returned");
        Assert.assertEquals(bodyAsString.contains(testData.get("userid")), true, "Response body contains 10");
    }


    @Test(testName = "Update request test", dataProvider = "getDefaultUser")
    @Step("Update request test")
    public void updateRequest(Map<String, String> testData) {
        RestAssured.baseURI = testData.get("baseUrl");
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");

        JSONObject requestParams = new JSONObject();
        requestParams.put("id", testData.get("userid"));
        requestParams.put("title", testData.get("title"));
        requestParams.put("body", testData.get("body"));
        requestParams.put("userId", testData.get("userid"));
        request.body(requestParams.toString());
        Response response = request.put("/posts/1");
        String bodyAsString = response.asString();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(bodyAsString.contains(testData.get("title")), true, "Response body contains darshana");
    }

    @Test(testName = "Filter user by id", dataProvider = "getDefaultUser")
    @Step("Filter users by id")
    public void filterUsersById(Map<String, String> testData) {
        RestAssured.baseURI = testData.get("baseUrl");
        RequestSpecification request = given();

        Response response = request.queryParam(testData.get("filteridattribute"), testData.get("filterid")).get("/users");

        int statusCode = response.getStatusCode();
        String bodyAsString = response.asString();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(bodyAsString.contains(testData.get("filterid")), true, "Response body contains 7");
    }

    @Test(testName = "Filter user by name", dataProvider = "getDefaultUser")
    @Step("Filter users by name")
    public void filterUsersByName(Map<String, String> testData) {
        RestAssured.baseURI = testData.get("baseUrl");
        RequestSpecification request = given();

        Response response = request.queryParam(testData.get("filternameattribute"), testData.get("filtername")).get("/users");

        String bodyAsString = response.asString();
        Assert.assertEquals(bodyAsString.contains(testData.get("filtername")), true, "Response body contains Kurtis");
    }

    @Test(testName = "Filter user by name and id", dataProvider = "getDefaultUser")
    @Step("Filter users by name and id")
    public void filterUsersByIDandName(Map<String, String> testData) {
        RestAssured.baseURI = testData.get("baseUrl");
        RequestSpecification request = given();

        Response response = request.queryParam(testData.get("filteridattribute"), testData.get("filterid")).queryParam(testData.get("filternameattribute"), testData.get("filtername")).get("/users");

        String bodyAsString = response.asString();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(bodyAsString.contains(testData.get("filtername")), true, "Response body contains Kurtis");
        Assert.assertEquals(bodyAsString.contains(testData.get("filterid")), true, "Response body contains 7");
    }

    @Test(testName = "Filter user by email", dataProvider = "getDefaultUser")
    @Step("Filter users by email")
    public void filterUsersByEmail(Map<String, String> testData) {
        RestAssured.baseURI = testData.get("baseUrl");
        RequestSpecification request = given();

        Response response = request.queryParam(testData.get("filteremailattribute"), testData.get("emailaddress")).get("/users");

        String bodyAsString = response.asString();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(bodyAsString.contains(testData.get("emailaddress")), true, "Chaim_McDermott@dana.io");
    }
}
