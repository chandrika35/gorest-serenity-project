package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.models.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GoRestSteps {
    static final String token = "Bearer 9e79e0b8e8b9f3522766e131e222e146de7a0b82259937c0592ddef627d54003";
    @Step("Creating new user record with name: {0}, email: {1}, gender: {2} and status: {3}")
    public ValidatableResponse createUserRecord(String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .post("/users")
                .then().log().all().statusCode(201);
    }

    @Step("Getting all users records")
    public ValidatableResponse getUserIDs() {
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Connection", "keep-alive")
                .when()
                .get(EndPoints.GET_USERS)
                .then().log().all().statusCode(200);
    }

    @Step("Updating existing user record with id: {0}, name: {1}, email: {2}, gender: {3} and status: {4}")
    public void userRecordUpdate(int id, String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .pathParam("id", id)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Step("Getting single record with id: {0}")
    public ValidatableResponse getSingleUser(int id) {
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Connection", "keep-alive")
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then().log().all();
    }

    @Step("Deleting existing user record with id: {0}")
    public ValidatableResponse deleteUserRecord(int id) {
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Connection", "keep-alive")
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then().log().all();
    }
}
