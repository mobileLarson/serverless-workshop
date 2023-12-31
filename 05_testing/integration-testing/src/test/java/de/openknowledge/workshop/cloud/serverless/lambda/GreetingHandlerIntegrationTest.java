/*
 * Copyright (C) open knowledge GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package de.openknowledge.workshop.cloud.serverless.lambda;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Integration tests for <code>GreetingHandlerV2</code>.
 */
public class GreetingHandlerIntegrationTest {

    private static final String errorMissingParameter = "Missing or incorrect parameters. Expected 'firstName' and 'lastName'.";

    private final static Integer HTTP_STATUS_CODE_OK = 200;
    private final static Integer HTTP_STATUS_CREATED = 201;
    private final static Integer HTTP_STATUS_CODE_BAD_REQUEST = 400;
    private final static Integer HTTP_STATUS_CODE_UNPROCESSABLE_ENTITY = 422;

    private final static String CONTENT_TYPE_JSON = "application/json";

    @BeforeAll
    public static void setup() {

        // TODO take a look at this setup
        //
        RestAssured.port = 8080;
        RestAssured.basePath = "/greetings/";
        RestAssured.baseURI = "http://localhost";
    }

    /**
     * Test with payload:
     * - body = {"firstName":"Lars", "lastName":"Roewekamp"}
     * Expected result:
     * - OK 200
     * - body {"greeting" : "Hello, Lars Roewekamp! i am pleased to meet you."}
     */
    @Test
    public void whenPostWithBody_thenCorrect(){

        // TODO goto GreetingHandlerV2 and make this test work

        Map<String,String> request = new HashMap<>();
        request.put("firstName", "Lars");
        request.put("lastName", "Roewekamp");
        given()
                .contentType(CONTENT_TYPE_JSON)
                .body(request)
                .when()
                    .post()
                .then()
                    .body("greeting", equalTo("Hello, Lars Roewekamp! i am pleased to meet you."))
                    .statusCode(HTTP_STATUS_CREATED);

    }

    /**
     * Test without payload:
     * - body = {}
     * Expected result:
     * - BAD REQUEST 400
     * - body { "errorCode" : "400" , "errorMessage" : "Missing or incorrect parameters. Expected \"firstName\" and \"lastName\"."}
     */
    @Test
    public void whenPostWithoutBody_thenInCorrect(){

        // TODO goto GreetingHandlerV2 and make this test work

        Map<String,String> request = new HashMap<>();

        given()
                .contentType(CONTENT_TYPE_JSON)
                .when()
                    .post()
                .then()
                    .statusCode(HTTP_STATUS_CODE_UNPROCESSABLE_ENTITY)
                    .body("errorCode", equalTo(422))
                    .body("errorMessage", equalTo(errorMissingParameter));
    }
}
