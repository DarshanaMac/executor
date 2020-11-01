package com.executor.qa.uiautomation.serviceclient;

import com.executor.qa.uiautomation.utils.FileHelper;
import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidationException;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;*/
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServiceClient {

    private Headers commonHeaders;
    private JsonSchemaFactory jsonSchemaFactory;
    private RequestSpecBuilder requestSpecBuilder;
    private String baseUri;
    private String basePath;
    private RestAssuredConfig restAssuredConfig;
    //final static Logger logger = LogManager.getLogger(ServiceClient.class);

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public JsonSchemaFactory getJsonSchemaFactory() {
        return jsonSchemaFactory;
    }

    public final void setJsonSchemaFactory(SchemaVersion schemaVersion) {
        jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(
                        ValidationConfiguration.newBuilder()
                                .setDefaultVersion(schemaVersion)
                                .freeze()).freeze();
    }

    public ServiceClient(boolean isLogEnable) {
        setJsonSchemaFactory(SchemaVersion.DRAFTV4);
        requestSpecBuilder = new RequestSpecBuilder();
        restAssuredConfig = new RestAssuredConfig();
        if (isLogEnable) {
            /*RestAssuredConfig restAssuredConfig = new RestAssuredConfig();
            PrintStream logStream = IoBuilder.forLogger(logger).buildPrintStream();
            requestSpecBuilder = requestSpecBuilder.addFilter(ResponseLoggingFilter.logResponseTo(logStream))
                    .addFilter(RequestLoggingFilter.logRequestTo(logStream))
                    .setConfig(restAssuredConfig);*/
        }
    }

    public ServiceClient(String baseUri, String basePath, boolean isLogEnable) {
        this(isLogEnable);
        this.baseUri = baseUri;
        this.basePath = basePath;
    }


    public boolean jsonSchemaValidation(Response response, String path) {
        File file = FileHelper.getFileFromPath(path);
        JsonSchemaValidator jsonSchemaValidator = (JsonSchemaValidator) matchesJsonSchema(file).using(jsonSchemaFactory);
        String jsonResponse = response.body().asString();
        try {
            assertThat(jsonResponse, jsonSchemaValidator);
            return true;
        } catch (JsonSchemaValidationException e) {
            return false;
        }
    }

    /**
     * This method is used to POST REST request to server
     *
     * @param response
     */

    public int getResponseCode(Response response) {
        int statusCode = response.getStatusCode();
        return statusCode;
    }

    public Response get(RequestSpecification requestSpecification, String path) {
        Response response = requestSpec(requestSpecification, path)
                .get(path);
        response.then().log().all();
        return response;
    }
    
    public Response get(RequestSpecification requestSpecification) {
        return get(requestSpecification,"");
    }

    public Response post(RequestSpecification requestSpecification, String path) {
        Response response = requestSpec(requestSpecification, path)
                .post(path);
        response.then().log().all();
        return response;
    }

    public Response put(RequestSpecification requestSpecification, String path) {
        Response response = requestSpec(requestSpecification, path)
                .put(path);
        response.then().log().all();
        return response;
    }

    private RequestSpecification requestSpec(RequestSpecification requestSpecification, String path) {
        RequestSpecification tempRequestSpecification = requestSpecBuilder.build()
                .spec(requestSpecification)
                .baseUri(baseUri)
                .basePath(basePath);
        return given()
                .spec(tempRequestSpecification)
                .when()
                .log()
                .all();
    }

    public Response get(String path) {
        return given()
                .baseUri(baseUri).basePath(basePath)
                .when().get(path);
    }

    public static JSONObject getResponseBodyAsJsonObject(Response response) {
        return new JSONObject(response.body().asString());
    }

    public static JSONArray getResponseBodyAsJsonArray(Response response) {
        return new JSONArray(response.body().asString());
    }
    
}

