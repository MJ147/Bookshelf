package com.mja;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SimpleBlogAppTest {

    private static final String POST_1 = "{\"title\":\"Post 1\"," +
                                          "\"author\":\"Author 1\"," +
                                          "\"content\":\"Content 1\"}";


    private static final String POST_2 =  "{\"title\":\"Post 2\"," +
                                           "\"author\":\"Author 2\"," +
                                           "\"content\":\"Content 2\"}";

    private static final int APP_PORT = 8090;

    private SimpleBlogApp simpleBlogApp;

    @BeforeAll
    public static void beforeAll() {
        RestAssured.port = APP_PORT;
    }

    @BeforeEach
    public void beforeEach() throws Exception {
        simpleBlogApp = new SimpleBlogApp(APP_PORT);
    }

    @AfterEach
    public void afterEach() {
        simpleBlogApp.stop();
    }

    @Test
    public void addMethod_correctBody_shouldReturnStatus200() throws Exception {
        with().body(POST_1).when().post("/blog/add").then().statusCode(200).body(startsWith("Post has been successfully added, id ="));
    }

    @Test
    public void addMethod_fieldTypeMismatch_shouldReturnStatus500() {
        String blogPostWithFieldTypeMismatch = "{\"title\":\"Post 1\"," +
                                                "\"author\":\"Author 1\"," +
                                                "\"content\":1+1}";
        with().body(blogPostWithFieldTypeMismatch).when().post("/blog/add").then().statusCode(500);
    }

    @Test
    public void addMethod_unexpectedField_shouldReturnStatus500() {
        with().body("{\"numberOfLetters\":200}").when().post("/blog/add").then().statusCode(500);
    }
}