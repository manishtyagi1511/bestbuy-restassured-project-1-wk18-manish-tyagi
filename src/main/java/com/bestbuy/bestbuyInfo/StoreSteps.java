package com.bestbuy.bestbuyInfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class StoreSteps {

    @Step("CREATE store id")
    public ValidatableResponse createStoreID(String name, String type, String address, String address2, String city, String state, String zip) {

        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, city, state, zip);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post(EndPoints.CREATE_STORE).then(); // (/stores)


    }

    @Step("READ store id")
    public HashMap<String, Object> getStoreInfoById(int storeId) {

        return SerenityRest
                .given().log().all()
                .pathParam("storeID", storeId) // (/stores/storeID).
                .when()

                .get(EndPoints.GET_STORE_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");

    }
    @Step("UPDATE the store id")
    public ValidatableResponse updateTheStoreId(int storeId,String name, String type, String address, String address2, String city, String state, String zip){
        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, city, state, zip);

        return SerenityRest
                .given().log().all()
                .when()
                .contentType(ContentType.JSON)
                .pathParam("storeID",storeId)
                .when()
                .body(storePojo)
                .patch(EndPoints.PATCH_STORE_BY_ID)
                .then();


    }

    @Step("DELETE Store ID")
    public ValidatableResponse deleteStoreId(int storeId){
        return SerenityRest
                .given().log().all()
                .pathParam("storeID",storeId)
                .delete(EndPoints.DELETE_STORE_BY_ID).then();

    }


}
